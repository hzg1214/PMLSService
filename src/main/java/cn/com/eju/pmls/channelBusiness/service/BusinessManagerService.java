package cn.com.eju.pmls.channelBusiness.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.model.sweepStreets.MaintainerInfoDto;
import cn.com.eju.pmls.channelBusiness.dao.BusinessManagerMapper;
import cn.com.eju.pmls.channelBusiness.model.BusinessManagerDto;
import cn.com.eju.pmls.channelBusiness.model.JsStatementDto;
import cn.com.eju.pmls.jsStatement.model.PmlsJsStatement;
import cn.com.eju.pmls.utils.UpdateExcelUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service("businessManagerService")
public class BusinessManagerService extends BaseService {
    @Resource
    private BusinessManagerMapper businessManagerMapper;
    @Resource
    private FileRecordMainMapper fileRecordMainMapper;

    //获取商户列表
    public ResultData getBusinessManagerList(Map<String, Object> queryParam) {
        ResultData<List<BusinessManagerDto>> resultData = new ResultData<List<BusinessManagerDto>>();
        //List<BusinessManagerDto> list = businessManagerMapper.getBusinessManagerList(queryParam);
        List<BusinessManagerDto> list = businessManagerMapper.getNewBusinessManagerList(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }

    //获取商户信息
    public ResultData getBusinessInfo(BusinessManagerDto dto) {
        ResultData<BusinessManagerDto> resultData = new ResultData<BusinessManagerDto>();
        BusinessManagerDto businessManagerDto = businessManagerMapper.getBusinessInfo(dto);
        if (businessManagerDto != null && businessManagerDto.getId() != 0) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("refId", businessManagerDto.getId().toString());
//            map.put("fileTypeId","1069");
//            map.put("fileSourceId","21");
            map.put("fileTypeId", "1044");
            map.put("fileSourceId", "3");

            List<FileRecordMain> fileList = fileRecordMainMapper.queryList(map);
            businessManagerDto.setFileList(fileList);

            map.put("companyNo", businessManagerDto.getCompanyNo());
            List<BusinessManagerDto> logsList = businessManagerMapper.getOperationLogList(map);
            businessManagerDto.setLogsList(logsList);
        }
        resultData.setReturnData(businessManagerDto);
        return resultData;
    }

    //获取商户信息
    public ResultData getBusinessInfo2(BusinessManagerDto dto) {
        ResultData<BusinessManagerDto> resultData = new ResultData<BusinessManagerDto>();
        BusinessManagerDto businessManagerDto = businessManagerMapper.getBusinessInfo2(dto);
        resultData.setReturnData(businessManagerDto);
        return resultData;
    }

    //新增商户
    @Transactional(rollbackFor = Exception.class)
    public ResultData addBusiness(BusinessManagerDto dto) throws Exception {
        ResultData resultData = new ResultData();
        //校验联系人是否存在
        BusinessManagerDto businessManagerDto = businessManagerMapper.getContactsInfoByTel(dto);
        if (businessManagerDto != null && businessManagerDto.getDockingPeoTel() != null) {
            resultData.setFail("新增失败,管理员手机号码已存在，请更换管理员手机号码");
            return resultData;
        }
        //校验渠道是否存在
        BusinessManagerDto bDto = businessManagerMapper.getBusinessInfo2(dto);
        if (bDto != null) {
            resultData.setFail("操作失败，该渠道信息已存在！");
            return resultData;
        }
        int count = businessManagerMapper.addBusiness(dto);

        if (dto.getId() != null && dto.getId() != 0) {

            //新增门店信息
            businessManagerMapper.addStore(dto);
            //如果有图片，更新信息
            if (dto.getFileIds() != null && !"".equals(dto.getFileIds())) {
                String fileIdArray[] = dto.getFileIds().split(",");
                for (int i = 0; i < fileIdArray.length; i++) {
                    FileRecordMain fileRecordMain = new FileRecordMain();
                    fileRecordMain.setRefId(dto.getId());
                    fileRecordMain.setFileRecordMainId(Integer.parseInt(fileIdArray[i]));
                    fileRecordMainMapper.update(fileRecordMain);
                }
            }
            dto.setOperationType("1");
            businessManagerMapper.execCompany(dto);//执行存储过程同步友房通数据

            //添加新的维护人
            businessManagerMapper.addCompanyMaintainer(dto);

            //新增联系人信息
            businessManagerMapper.addContacts(dto);

            dto.setIsDelete("0");
            businessManagerMapper.execCompanyAdmin(dto);//执行存储过程同步友房通数据

        }
        if (count > 0) {
            resultData.setSuccess("新增成功");
        } else {
            resultData.setFail("新增失败");
        }
        return resultData;
    }

    //修改商户
    @Transactional(rollbackFor = Exception.class)
    public ResultData updateBusiness(BusinessManagerDto dto) throws Exception {
        ResultData resultData = new ResultData();

        BusinessManagerDto businessManagerDto = businessManagerMapper.getBusinessInfo(dto);
        String operationContent = "";
        String oldAddress = businessManagerDto.getCityName() + businessManagerDto.getDistrictName() + businessManagerDto.getAddress();
        String newAddress = dto.getCityName() + dto.getDistrictName() + dto.getAddress();

        //改过渠道名称或者统一社会代码的需要校验是否存在
        if (!businessManagerDto.getCompanyName().equals(dto.getCompanyName()) || !businessManagerDto.getBusinessLicenseNo().equals(dto.getBusinessLicenseNo())) {
            //校验渠道是否存在
            BusinessManagerDto bDto = businessManagerMapper.getBusinessInfo2(dto);
            if (bDto != null) {
                resultData.setFail("操作失败，该渠道信息已存在！");
                return resultData;
            }
        }

        if (!businessManagerDto.getCompanyName().equals(dto.getCompanyName())) {
            operationContent += "渠道名称：" + businessManagerDto.getCompanyName() + "——>" + dto.getCompanyName() + "；";
        }
        if (!businessManagerDto.getBusinessLicenseNo().equals(dto.getBusinessLicenseNo())) {
            operationContent += "统一社会信用代码：" + businessManagerDto.getBusinessLicenseNo() + "——>" + dto.getBusinessLicenseNo() + "；";
        }
        if (!(oldAddress).equals(newAddress)) {
            operationContent += "注册地址：" + businessManagerDto.getAddress() + "——>" + dto.getAddress() + "；";
        }
        if (!businessManagerDto.getBrandName().equals(dto.getBrandName())) {
            operationContent += "渠道品牌：" + businessManagerDto.getBrandName() + "——>" + dto.getBrandName() + "；";
        }
        if (!businessManagerDto.getChannelType().equals(dto.getChannelType())) {
            operationContent += "渠道分类：" + businessManagerDto.getChannelTypeName() + "——>" + dto.getChannelTypeName() + "；";
        }
        if (!businessManagerDto.getChannelLevel().equals(dto.getChannelLevel())) {
            operationContent += "渠道等级：" + businessManagerDto.getChannelLevelName() + "——>" + dto.getChannelLevelName() + "；";
        }
        if (!businessManagerDto.getTaxRate().equals(dto.getTaxRate())) {
            operationContent += "增值税税率：" + businessManagerDto.getTaxRateName() + "——>" + dto.getTaxRateName() + "；";
        }
        if (!businessManagerDto.getLegalPerson().equals(dto.getLegalPerson())) {
            operationContent += "法定代表人：" + businessManagerDto.getLegalPerson() + "——>" + dto.getLegalPerson() + "；";
        }
        if (!businessManagerDto.getContactNumber().equals(dto.getContactNumber())) {
            operationContent += "法人手机号码：" + businessManagerDto.getContactNumber() + "——>" + dto.getContactNumber() + "；";
        }
        /*if(!businessManagerDto.getRemark().equals(dto.getRemark())){
            operationContent+="备注："+businessManagerDto.getRemark()+"==>"+dto.getRemark()+"；";
        }*/
        int count = businessManagerMapper.updateBusiness(dto);

        //记录修改日志
        if (operationContent != null && !"".equals(operationContent)) {
            operationContent = operationContent.substring(0, operationContent.length() - 1);//去掉最后一个分号
            dto.setCompanyNo(businessManagerDto.getCompanyNo());
            dto.setOperationContent(operationContent);
            businessManagerMapper.addOperationLog(dto);
        }

        if (dto.getFileIds() != null && !"".equals(dto.getFileIds())) {
            //先删除图片
//            fileRecordMainMapper.updateStatusByContractId(Integer.parseInt(dto.getId().toString()));
        	Map<String,Object> param = new HashMap<>();
            param.put("refId", Integer.parseInt(dto.getId().toString()));
            param.put("fileSourceId", 3);
            fileRecordMainMapper.updateStatusByRefId(param);

            String fileIdArray[] = dto.getFileIds().split(",");
            for (int i = 0; i < fileIdArray.length; i++) {
                FileRecordMain fileRecordMain = new FileRecordMain();
                fileRecordMain.setRefId(dto.getId());
                fileRecordMain.setIsDelete(false);
                fileRecordMain.setFileRecordMainId(Integer.parseInt(fileIdArray[i]));
                fileRecordMainMapper.update(fileRecordMain);
            }
        }
        dto.setOperationType("2");
        businessManagerMapper.execCompany(dto);//执行存储过程同步友房通数据

        if (count > 0) {
            resultData.setSuccess("修改成功");
        } else {
            resultData.setFail("修改失败");
        }
        return resultData;
    }

    //删除商户
    @Transactional(rollbackFor = Exception.class)
    public ResultData deleteBusiness(BusinessManagerDto dto) throws Exception {
        ResultData resultData = new ResultData();
        int count = businessManagerMapper.deleteBusiness(dto);
        if (count > 0) {
            resultData.setSuccess("删除成功");
        } else {
            resultData.setFail("删除失败");
        }
        return resultData;
    }

    //新增联系人信息
    @Transactional(rollbackFor = Exception.class)
    public ResultData addContacts(BusinessManagerDto dto) throws Exception {
        ResultData resultData = new ResultData();

        //校验联系人是否存在
        BusinessManagerDto businessManagerDto = businessManagerMapper.getContactsInfoByTel(dto);
        if (businessManagerDto != null && businessManagerDto.getDockingPeoTel() != null) {
            resultData.setFail("新增失败，管理员手机号码已存在，请更换管理员手机号码");
            return resultData;
        }

        int count = businessManagerMapper.addContacts(dto);
        String operationContent = "";
        operationContent += "新增管理员：" + dto.getDockingPeo() + "（" + dto.getDockingPeoTel() + "）";
        //记录公司日志
        if (operationContent != null && !"".equals(operationContent)) {
//            operationContent+="管理员ID："+dto.getId();
            dto.setCompanyNo(dto.getCompanyNo());
            dto.setOperationContent(operationContent);
            businessManagerMapper.addOperationLog(dto);
        }

        dto.setOperationType("1");//新增管理员
        dto.setIsDelete("0");
        businessManagerMapper.execCompanyAdmin(dto);//执行存储过程同步友房通数据

        if (count > 0) {
            resultData.setSuccess("新增成功");
        } else {
            resultData.setFail("新增失败");
        }
        return resultData;
    }

    //修改联系人信息
    @Transactional(rollbackFor = Exception.class)
    public ResultData updateContacts(BusinessManagerDto dto) throws Exception {
        ResultData resultData = new ResultData();

        BusinessManagerDto businessManagerDto = businessManagerMapper.getContactsInfo(dto);
        String operationContent = "";

        if (dto.getIsDelete() != null && "1".equals(dto.getIsDelete())) {
            operationContent += "删除管理员：" + businessManagerDto.getDockingPeo() + "（" + businessManagerDto.getDockingPeoTel() + "）";
            dto.setOperationType("3");//删除管理员
            //校验是否是最后一个管理员，最后一个管理员不让删除
            int dockingPeoNum = businessManagerMapper.getContactsCount(dto);
            if (dockingPeoNum <= 1) {
                resultData.setFail("当前渠道只有一个管理员，不允许删除！");
                return resultData;
            }
        } else {
            if (!businessManagerDto.getDockingPeo().equals(dto.getDockingPeo()) || !businessManagerDto.getDockingPeoTel().equals(dto.getDockingPeoTel())) {
                operationContent += "修改管理员：" + businessManagerDto.getDockingPeo() + "（" + businessManagerDto.getDockingPeoTel() + "）——>" + dto.getDockingPeo() + "（" + dto.getDockingPeoTel() + "）；";
            }
            dto.setOperationType("2");//修改管理员

            //校验联系人是否存在
            BusinessManagerDto bDto = businessManagerMapper.getContactsInfoByTel(dto);
            if (bDto != null && bDto.getDockingPeoTel() != null) {
                resultData.setFail("修改失败,管理员手机号码已存在，请更换管理员手机号码");
                return resultData;
            }
        }
        //记录修改日志
        if (operationContent != null && !"".equals(operationContent)) {
            //operationContent+="管理员ID："+dto.getId();
            dto.setCompanyNo(businessManagerDto.getCompanyNo());
            dto.setOperationContent(operationContent);
            businessManagerMapper.addOperationLog(dto);
        }
        int count = businessManagerMapper.updateContacts(dto);
        businessManagerMapper.execCompanyAdmin(dto);//执行存储过程同步友房通数据
        if (count > 0) {
            resultData.setSuccess("操作成功");
        } else {
            resultData.setFail("操作失败");
        }
        return resultData;
    }

    //获取联系人列表
    public ResultData getContactsList(Map<String, Object> queryParam) {
        ResultData<List<BusinessManagerDto>> resultData = new ResultData<List<BusinessManagerDto>>();
        List<BusinessManagerDto> list = businessManagerMapper.getContactsList(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }

    //获取联系人信息
    public ResultData getContactsInfo(BusinessManagerDto dto) {
        ResultData<BusinessManagerDto> resultData = new ResultData<BusinessManagerDto>();
        BusinessManagerDto businessManagerDto = businessManagerMapper.getContactsInfo(dto);
        resultData.setReturnData(businessManagerDto);
        return resultData;
    }

    //获取日志列表
    public ResultData getOperationLogList(Map<String, Object> queryParam) {
        ResultData<List<BusinessManagerDto>> resultData = new ResultData<List<BusinessManagerDto>>();
        List<BusinessManagerDto> list = businessManagerMapper.getOperationLogList(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }

    //新增商户城市关系表
    @Transactional(rollbackFor = Exception.class)
    public ResultData addCompanyReleaseCity(BusinessManagerDto dto) throws Exception {
        ResultData resultData = new ResultData();
        int count = businessManagerMapper.addCompanyReleaseCity(dto);
        if (count > 0) {
            resultData.setSuccess("新增成功");
        } else {
            resultData.setFail("新增失败");
        }
        return resultData;
    }

    //获取未生成附件的三方协议列表并生成附件
    public ResultData getNotFileJssList(HttpServletRequest request) {
        ResultData<List<PmlsJsStatement>> resultData = new ResultData<List<PmlsJsStatement>>();
        List<PmlsJsStatement> list = businessManagerMapper.getNotFileJssList();
        if (list != null && list.size() > 0) {
            for (PmlsJsStatement jsStatementDto : list) {
                List<JsStatementDto> jssDetailList = businessManagerMapper.getJssDetailListForExcel(jsStatementDto.getId());
                //生成附件并返回URL
                String fileName = "中介服务费确认明细_" + jsStatementDto.getOaNo()  + ".xlsx";//生成随机文件名字
                //上传附件到服务器
                JSONObject jsonObject = UpdateExcelUtils.createExcel(request, fileName, jssDetailList);
                if (jsonObject != null) {//添加附件表数据
                    Map<String, Object> param = new HashMap<String, Object>();
                    param.put("jssId", jsStatementDto.getId().toString());
                    param.put("fileName", fileName);
                    param.put("fileUrl", jsonObject.get("url").toString());
                    businessManagerMapper.insertJsStatementFile(param);
                }
            }
        }
        resultData.setReturnData(list);
        return resultData;
    }

    //获取维护人列表
    public ResultData getMaintainerList(Map<String, Object> queryParam) {
        ResultData<List<MaintainerInfoDto>> resultData = new ResultData<List<MaintainerInfoDto>>();
        List<MaintainerInfoDto> list = null;
        if (queryParam.containsKey("projectCityNos") && !StringUtils.isEmpty(queryParam.get("projectCityNos"))) {
            String projectCityNos = (String) queryParam.get("projectCityNos");
            String[] projectCityNoArrs = projectCityNos.split(",");
            List<String> projectCityNoLists = Arrays.asList(projectCityNoArrs);
            queryParam.put("projectCityNoLists", projectCityNoLists);
            queryParam.remove("cityNo");//业绩维护：去掉登陆人城市权限
        }
        if (queryParam.containsKey("fyFlag")) {//房友中心标志
            queryParam.remove("pageIndex");
            queryParam.remove("pageSize");
            list = businessManagerMapper.getFyCenterList(queryParam);
        } else {
            list = businessManagerMapper.getMaintainerList(queryParam);
        }
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }

    //获取公司维护人记录列表
    public ResultData getCompanyMaintainerList(Map<String, Object> queryParam) {
        ResultData<List<MaintainerInfoDto>> resultData = new ResultData<List<MaintainerInfoDto>>();
        List<MaintainerInfoDto> list = businessManagerMapper.getCompanyMaintainerList(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }

    //修改渠道维护人
    @Transactional(rollbackFor = Exception.class)
    public ResultData updateMaintainer(BusinessManagerDto dto) throws Exception {
        ResultData resultData = new ResultData();

        //修改上一个维护人的结束日期,和公司维护人信息
        businessManagerMapper.updateCompanyMaintainer(dto);
        businessManagerMapper.updateBusiness(dto);
        //添加新的维护人
        int count = businessManagerMapper.addCompanyMaintainer(dto);

        if (count > 0) {
            resultData.setSuccess("操作成功");
        } else {
            resultData.setFail("操作失败");
        }
        return resultData;
    }

    //获取未生成附件的结算书列表并生成附件
    public ResultData createSfxyFile(HttpServletRequest request) {
        ResultData<List<JsStatementDto>> resultData = new ResultData<List<JsStatementDto>>();
        List<JsStatementDto> list = businessManagerMapper.getNotFileSfxyList();
        if (list != null && list.size() > 0) {
            for (JsStatementDto jsStatementDto : list) {
                //生成附件并返回URL
                String fileName = UUID.randomUUID().toString() + ".xlsx";//生成随机文件名字
                //上传附件到服务器
//                JSONObject jsonObject=UpdateExcelUtils.createExcel(request,fileName,jssDetailList);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("url", "http://pic.ehousechina.com//FangYou/20191202/6953fc29-259e-4cca-bf07-1139d47c1ad7.xlsx");
                if (jsonObject != null) {//添加附件表数据
                    Map<String, Object> param = new HashMap<String, Object>();
                    param.put("qyId", jsStatementDto.getId().toString());
                    param.put("fileName", fileName);
                    param.put("fileUrl", jsonObject.get("url").toString());
                    businessManagerMapper.insertSfxyFile(param);
                }
            }
        }
        resultData.setReturnData(list);
        return resultData;
    }


    //调整为是否借佣渠道
    @Transactional(rollbackFor = Exception.class)
    public ResultData adjustToProcuration(BusinessManagerDto dto) throws Exception {
        ResultData resultData = new ResultData();

        int count = businessManagerMapper.updateBusiness(dto);
        String operationContent = "";
        if (dto.getIsProcuration() == 0) {
            operationContent += "公司调整为非借佣渠道";
        } else {
            operationContent += "公司调整非借佣渠道";
        }
        dto.setCompanyNo(dto.getCompanyNo());
        dto.setOperationContent(operationContent);
        businessManagerMapper.addOperationLog(dto);
        resultData.setSuccess("操作成功");
        return resultData;
    }
}
