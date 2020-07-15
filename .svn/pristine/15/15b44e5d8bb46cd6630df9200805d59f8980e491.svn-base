package cn.com.eju.deal.service.sweepStreets;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.dao.DistrictMapper;
import cn.com.eju.deal.base.seqNo.api.ISeqNoAPI;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.common.util.HttpClientUtil;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.company.dao.CompanyReleaseCityMapper;
import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.company.model.CompanyReleaseCity;
import cn.com.eju.deal.company.service.CompanyReleaseCityService;
import cn.com.eju.deal.contract.service.ContractService;
import cn.com.eju.deal.contract.service.OPCompanyHttpService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.CityDto;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.dto.op.OpCompanyDto;
import cn.com.eju.deal.dto.open.OPCompanyDto;
//import cn.com.eju.deal.dubbo.op.api.IOPCompanyService;
import cn.com.eju.deal.fangyou.service.FangyouAccountService;
import cn.com.eju.deal.frameContract.dao.FrameContractMapper;
import cn.com.eju.deal.mapper.followMap.FollowMapMapper;
import cn.com.eju.deal.mapper.image.ImgMapper;
import cn.com.eju.deal.mapper.sweepStreets.SweepStreetsMapper;
import cn.com.eju.deal.mapper.workLog.WorkLogMapper;
import cn.com.eju.deal.model.followMap.ContactsDto;
import cn.com.eju.deal.model.sweepStreets.*;
import cn.com.eju.deal.service.myCollection.MyCollectionService;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.user.dao.GroupMapper;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.User;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by xu on 2017/4/13.
 */
@Service("sweepStreetsService")
public class SweepStreetsService {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private StoreMapper storeMapper;
    @Resource
    private SweepStreetsMapper sweepStreetsMapper;
    @Resource
    private ImgMapper imgMapper;
    @Resource
    private CompanyMapper companyMapper;
    //    @Resource
//    private IOPCompanyService opCompanyService;
    @Resource
    private MyCollectionService myCollectionService;
    @Resource
    private FollowMapMapper followMapMapper;

    @Resource
    private FrameContractMapper frameContractMapper;

    @Resource
    private ISeqNoAPI seqNoAPI;
    @Resource
    private WorkLogMapper workLogMapper;
    @Resource(name = "groupMapper")
    private GroupMapper groupMapper;

    @Resource(name = "fangyouAccountService")
    private FangyouAccountService fangyouAccountService;

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Resource
    private FileRecordMainMapper fileRecordMainMapper;
    @Resource(name = "contractService")
    private ContractService contractService;

    @Resource
    private OPCompanyHttpService oPCompanyHttpService;

    @Resource
    private CompanyReleaseCityMapper companyReleaseCityMapper;

    @Resource
    private CompanyReleaseCityService companyReleaseCityService;

    @Resource
    private DistrictMapper districtMapper;

    public ResultData<List<StoreInfoDto>> getStoreListData(StoreNewDto dto) throws Exception {
        ResultData<List<StoreInfoDto>> resultData = new ResultData<List<StoreInfoDto>>();
        List<StoreInfoDto> list = sweepStreetsMapper.getStoreListData(dto);
        resultData.setTotalCount("0");
        if (null != list && !list.isEmpty()) {
            resultData.setReturnData(list);
            resultData.setTotalCount(list.get(0).getDataCount() + "");
        }
        return resultData;
    }

    public ResultData<List<StoreInfoDto>> getLocalStoreListData(StoreNewDto dto) throws Exception {
        ResultData<List<StoreInfoDto>> resultData = new ResultData<List<StoreInfoDto>>();
        List<StoreInfoDto> list = sweepStreetsMapper.getLocalStoreListData(dto);
        if (null != list && !list.isEmpty()) {
            resultData.setReturnData(list);
        }
        return resultData;
    }

    public int addStore(StoreNewDto dto) {

        if (dto.getPicString() != null && !"-".equals(dto.getPicString()) && !"".equals(dto.getPicString())) {
            String[] picArray = dto.getPicString().split(",");
            String pRefId = UUID.randomUUID().toString();
            List<WXPictureDto> pictureList = new ArrayList<WXPictureDto>();
            for (String picString : picArray) {
                String[] picDtoArray = picString.split("\\^");
                if (picDtoArray.length > 0) {
                    WXPictureDto pictureDto = new WXPictureDto();
                    pictureDto.setPictureRefId(pRefId);
                    pictureDto.setSmallPictureUrl(picDtoArray[0]);
                    pictureDto.setMiddlePictureUrl(picDtoArray[1]);
                    pictureDto.setBigPictureUrl(picDtoArray[2]);
                    pictureDto.setCreateUser(dto.getUserCreate() + "");
                    pictureList.add(pictureDto);
                }
            }
            //上传图片
            imgMapper.addImg(pictureList);
            dto.setPictureRefId(pRefId);
        }
        String cityNoString = null;
        if (dto.getCenterId() != null) {
            cityNoString = groupMapper.getCityNoByGroupId(dto.getCenterId());
            if (StringUtil.isNotEmpty(cityNoString)) {
                dto.setAcCityNo(cityNoString);
            }
        }
        if (dto.getStoreType().intValue() != 23401) {
            dto.setStorePersonNum(null);
            dto.setStoreSizeScale(null);
            dto.setBusinessPlaceType(null);
            dto.setAgentNum(null);
        }
        int count = sweepStreetsMapper.addStore(dto);

        //记录门店交易中心记录表
        sweepStreetsMapper.addStoreCenterHis(dto);


        //判断是否填写门店负责人，如果有，添加到联系人表
        if (null != dto.getStoreManager() && !dto.getStoreManager().isEmpty() && null != dto.getStoreManagerPhone() && !dto.getStoreManagerPhone().isEmpty()) {
            //创建门店联系人
            ContactsDto contactsDto = new ContactsDto();
            //
            ResultData<String> back = seqNoAPI.getSeqNoByTypeCode("TYPE_CONTACT");
            String contactsNo = back.getReturnData();
            contactsDto.setContactsNo(contactsNo);
            contactsDto.setStoreId(dto.getStoreId());
            contactsDto.setContactsName(dto.getStoreManager());
            contactsDto.setContactsPhone(dto.getStoreManagerPhone());
            contactsDto.setUserCreate(dto.getUserCreate() + "");
            //判断联系人是否重复
            int result = followMapMapper.checkContacts(contactsDto);
            if (result > 0) {
                //如果重复 忽略
            } else {
                //插入门店联系人信息
                followMapMapper.addContacts(contactsDto);
            }
        }
        return count;
    }

    public int checkStore(StoreNewDto dto) {
        int count = sweepStreetsMapper.checkStore(dto);
        return count;
    }

    public int updateStore(StoreNewDto dto) {
        if (dto.getPicString() != null && !"-".equals(dto.getPicString()) && !"".equals(dto.getPicString())) {
            WXPictureDto wxPictureDto = new WXPictureDto();
            wxPictureDto.setPictureRefId(dto.getPictureRefId());
            //审核通过的修改门店只增不删
            if (dto.getAuditStatus() != 20) {
                imgMapper.deleteImg(wxPictureDto);
                String[] picArray = dto.getPicString().split(",");
                String pRefId = UUID.randomUUID().toString();
                List<WXPictureDto> pictureList = new ArrayList<WXPictureDto>();
                for (String picString : picArray) {
                    String[] picDtoArray = picString.split("\\^");
                    if (picDtoArray.length > 0) {
                        WXPictureDto pictureDto = new WXPictureDto();
                        pictureDto.setPictureRefId(pRefId);
                        pictureDto.setSmallPictureUrl(picDtoArray[0]);
                        pictureDto.setMiddlePictureUrl(picDtoArray[1]);
                        pictureDto.setBigPictureUrl(picDtoArray[2]);
                        pictureDto.setCreateUser(dto.getUserCreate() + "");
                        pictureList.add(pictureDto);
                    }
                }
                //上传图片
                imgMapper.addImg(pictureList);
                dto.setPictureRefId(pRefId);
            } else {
                String[] picArray = dto.getPicString().split(",");
                String pRefId = "";
                if (dto.getPictureRefId() == null || "".equals(dto.getPictureRefId())) {
                    pRefId = UUID.randomUUID().toString();
                } else {
                    pRefId = dto.getPictureRefId();
                }

                List<WXPictureDto> pictureList = new ArrayList<WXPictureDto>();
                for (String picString : picArray) {
                    String[] picDtoArray = picString.split("\\^");
                    if (picDtoArray.length > 0) {
                        WXPictureDto pictureDto = new WXPictureDto();
                        pictureDto.setPictureRefId(pRefId);
                        pictureDto.setSmallPictureUrl(picDtoArray[0]);
                        pictureDto.setMiddlePictureUrl(picDtoArray[1]);
                        pictureDto.setBigPictureUrl(picDtoArray[2]);
                        pictureDto.setCreateUser(dto.getUserCreate() + "");
                        pictureList.add(pictureDto);
                    }
                }
                //上传图片
                imgMapper.addImg(pictureList);
            }


        }
        int count = sweepStreetsMapper.updateStore(dto);

        //判断是否填写门店负责人，如果有，添加到联系人表
        if (null != dto.getStoreManager() && !dto.getStoreManager().isEmpty() && null != dto.getStoreManagerPhone() && !dto.getStoreManagerPhone().isEmpty()) {
            //创建门店联系人
            ContactsDto contactsDto = new ContactsDto();
            //
            ResultData<String> back = seqNoAPI.getSeqNoByTypeCode("TYPE_CONTACT");
            String contactsNo = back.getReturnData();
            contactsDto.setContactsNo(contactsNo);
            contactsDto.setStoreId(dto.getStoreId());
            contactsDto.setContactsName(dto.getStoreManager());
            contactsDto.setContactsPhone(dto.getStoreManagerPhone());
            contactsDto.setUserCreate(dto.getUserCreate() + "");
            //判断联系人是否重复
            int result = followMapMapper.checkContacts(contactsDto);
            if (result > 0) {
                //如果重复 忽略
            } else {
                //插入门店联系人信息
                followMapMapper.addContacts(contactsDto);
            }
        }

        return count;
    }

    @Transactional
    public int updateStoreAuditStatus(StoreNewDto dto) {
        int count = sweepStreetsMapper.updateStoreAuditStatus(dto);
        if (count >= 1) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("storeId", dto.getStoreId().toString());
            if (dto.getAuditStatus() == 10) {
                //判断维护记录是否存在，如果不存在，添加维护人记录
                StoreNewDto storeNewDto = sweepStreetsMapper.getStoreById(dto);
                if (storeNewDto != null) {
                    if (storeNewDto.getMaintainerName() == null || "".equals(storeNewDto.getMaintainerName())) {
                        sweepStreetsMapper.updateStoreMainTainerInfo(dto);
                        sweepStreetsMapper.addStoreMainTainer(dto);
                    }
                }
                map.put("auditDesc", "提交");
            } else if (dto.getAuditStatus() == 20) {
                map.put("auditDesc", "审核通过");
            } else if (dto.getAuditStatus() == 30) {
                map.put("auditDesc", "驳回；原因：" + dto.getAuditReturnReason());
            }
            if (dto.getUserCreate() != null && !"".equals(dto.getUserCreate())) {
                map.put("auditUser", dto.getUserCreate().toString());
            } else if (dto.getUserUpdate() != null && !"".equals(dto.getUserUpdate())) {
                map.put("auditUser", dto.getUserUpdate().toString());
            } else {
                map.put("auditUser", "");
            }
            int result = sweepStreetsMapper.addAuditRecord(map);

            return result;
        }
        return count;
    }

    public int deleteStoreById(StoreNewDto dto) {
        int count = sweepStreetsMapper.deleteStoreById(dto);
        return count;
    }

    public ResultData<StoreNewDto> getStoreById(StoreNewDto dto) throws Exception {
        ResultData<StoreNewDto> resultData = new ResultData<StoreNewDto>();
        StoreNewDto storeNewDto = sweepStreetsMapper.getStoreById(dto);
        if (storeNewDto != null && storeNewDto.getPictureRefId() == null) {
            WXPictureDto wxPictureDto = myCollectionService.getImages(storeNewDto.getStoreId());
            if (wxPictureDto != null && wxPictureDto.getSmallPictureUrl() != null) {
                List<WXPictureDto> wxPictureDtoList = new ArrayList<WXPictureDto>();
                wxPictureDtoList.add(wxPictureDto);
                storeNewDto.setStorePicList(wxPictureDtoList);
            }
        }
        if (storeNewDto != null) {
            //判断经营场所类型是否可编辑
            String storeNo = storeNewDto.getStoreNo();
            if (!"".equals(storeNo)) {
                Integer storeBusinessPlaceEditFlag = this.getStoreBusinessPlaceEditFlag(storeNo);
                storeNewDto.setBusinessPlaceEditFlag(storeBusinessPlaceEditFlag);
            }
        }

        resultData.setReturnData(storeNewDto);
        return resultData;
    }


    public ResultData<List<CompanyNewDto>> getCompanyListData(CompanyNewDto dto) throws Exception {
        ResultData<List<CompanyNewDto>> resultData = new ResultData<List<CompanyNewDto>>();
        List<CompanyNewDto> list = sweepStreetsMapper.getCompanyListData(dto);
        resultData.setTotalCount("0");
        if (null != list && !list.isEmpty()) {
            resultData.setTotalCount(list.get(0).getDataCount() + "");
            resultData.setReturnData(list);
        }
        return resultData;
    }

    @Transactional
    public ResultData addCompany(CompanyNewDto dto) throws Exception {
        ResultData resultData = new ResultData();
        //验证城市和区域是否一致
        int relCount = districtMapper.checkCityNoDistrictNo(dto);
        if (relCount < 1) {
            resultData.setFail("公司注册地址的城市与区域不一致，请修改！");
            return resultData;
        }
        String storeNameStr = "";
        if (dto.getStoreIdStr() != null && !"".equals(dto.getStoreIdStr())) {
            //验证门店是否签约
            List<StoreNewDto> contractStoreList = sweepStreetsMapper.checkStoreIsContract(dto);
            if (contractStoreList != null && contractStoreList.size() > 0) {
                for (int s = 0; s < contractStoreList.size(); s++) {
                    storeNameStr += contractStoreList.get(s).getStoreName() + ",";
                }
            }
            if (!"".equals(storeNameStr)) {
                storeNameStr = storeNameStr.substring(0, storeNameStr.length() - 1);
                resultData.setFail("门店 " + storeNameStr + " 已被其他公司关联，不能关联");
                return resultData;
            }
        }

        String cityNoString = null;
        if (dto.getCenterId() != null) {
            cityNoString = groupMapper.getCityNoByGroupId(dto.getCenterId());
            if (StringUtil.isNotEmpty(cityNoString)) {
                dto.setAcCityNo(cityNoString);
            }
        }

        int count = sweepStreetsMapper.addCompany(dto);

        if (count >= 1) {
            //添加公司门店关系
            if (dto.getStoreIdStr() != null && !"".equals(dto.getStoreIdStr())) {
                String storeIds[] = dto.getStoreIdStr().split(",");
                List<StoreNewDto> newlist = new ArrayList<StoreNewDto>();
                for (int i = 0; i < storeIds.length; i++) {
                    Integer storeId = Integer.parseInt(storeIds[i]);
                    CompanyStoreNewDto companyStoreNewDto = new CompanyStoreNewDto();
                    companyStoreNewDto.setCompanyId(dto.getCompanyId());
                    companyStoreNewDto.setStoreId(storeId);
                    companyStoreNewDto.setDelete(0);
                    companyStoreNewDto.setFlag(0);
                    companyStoreNewDto.setUserCreate(dto.getUserCreate());
                    companyStoreNewDto.setUserUpdate(dto.getUserUpdate());
                    sweepStreetsMapper.addCompanyStore(companyStoreNewDto);
                }
            }
            //更新FileRecordMain的refId字段
            String fileRecordMainIds = dto.getFileRecordMainIds();
            if (StringUtil.isNotEmpty(fileRecordMainIds)) {
                String[] arrays = fileRecordMainIds.split(",");
                // 关联相关文件(RefId)
                for (int i = 0; i < arrays.length; i++) {
                    if (StringUtil.isNotEmpty(arrays[i])) {
                        FileRecordMain fileRecordMain = new FileRecordMain();
                        fileRecordMain.setFileRecordMainId(Integer.valueOf(arrays[i]));
                        fileRecordMain.setRefId(dto.getCompanyId());
                        fileRecordMain.setIsDelete(false);
                        fileRecordMainMapper.update(fileRecordMain);
                    }
                }
            }
            resultData.setSuccess();

            // 调用OP 同步新增公司 1.构造OPCompanyDto 2.调用createCmpl方法
            Company newDto = companyMapper.getById(dto.getCompanyId());
            OPCompanyDto opCompanyDto = buildOPCompany(newDto);
            String jsonDto = JsonUtil.parseToJson(opCompanyDto);

            try {
                String opDubboOpen = SystemParam.getWebConfigValue("opDubboOpen");
                if ("1".equals(opDubboOpen)) {
                    //new CmpOpThread(opCompanyService, jsonDto, null, "create").start();
                } else {
                    new CmpOpThreadHttp(oPCompanyHttpService, jsonDto, null, "create").start();

                }


            } catch (Exception e) {
                logger.error("sweepStreet", "SweepStreetsService", "createCompany", null, null, null, "新增公司通知房友原接口！CompanyId：" + newDto.getId(), e);
            }

            try {
                if ("1".equals(SystemParam.getWebConfigValue("opUrl18Flag"))) {
                    //18版
                    String url18 = SystemParam.getWebConfigValue("opUrl18") + "companies";
                    logger.error("sweepStreet", "SweepStreetsService", "createCompany", jsonDto, null, null, "新增公司通知房友！CompanyId：" + newDto.getId(), null);
                    String opResult18 = HttpClientUtil.jsonPost(url18, jsonDto);

                    //op返回值
                    Map<String, Object> opMap18 = (Map<String, Object>) JsonUtil.parseToObject(opResult18, Map.class);
                    logger.error("sweepStreet", "SweepStreetsService", "createCompany", opResult18, null, null, "新增公司通知房友！CompanyId：" + newDto.getId(), null);
                }
            } catch (Exception e) {
                logger.error("sweepStreet", "SweepStreetsService", "createCompany", null, null, null, "新增公司通知房友！CompanyId：" + newDto.getId(), e);
            }
        } else {
            resultData.setFail("新增公司失败");
        }

        return resultData;
    }

    public List<CompanyNewDto> checkCompanyByName(CompanyNewDto dto) {
        List<CompanyNewDto> list = sweepStreetsMapper.checkCompanyByName(dto);
        return list;
    }

    public List<CompanyNewDto> checkCompany(CompanyNewDto dto) {
        List<CompanyNewDto> list = sweepStreetsMapper.checkCompany(dto);
        return list;
    }

    @Transactional
    public ResultData updateCompany(CompanyNewDto dto) throws Exception {
        ResultData resultData = new ResultData();
        //验证城市和区域是否一致
        int relCount = districtMapper.checkCityNoDistrictNo(dto);
        if (relCount < 1) {
            resultData.setFail("公司注册地址的城市" + dto.getCityName() + "与区域" + dto.getDistrictName() + "不一致，请修改！");
            return resultData;
        }

        String storeNameFangyouStr = "";//开通房友账号的门店
        String storeNameStr = "";
        if (dto.getStoreIdStr() != null && !"".equals(dto.getStoreIdStr())) {
            //验证门店是否签约
            List<StoreNewDto> contractStoreList = sweepStreetsMapper.checkStoreIsContract(dto);
            if (contractStoreList != null && contractStoreList.size() > 0) {
                for (int s = 0; s < contractStoreList.size(); s++) {
                    storeNameStr += contractStoreList.get(s).getStoreName() + ",";
                }
            }
            if (!"".equals(storeNameStr)) {
                storeNameStr = storeNameStr.substring(0, storeNameStr.length() - 1);
                resultData.setFail("门店 " + storeNameStr + " 已被其他公司关联，不能关联");
                return resultData;
            }
        }
        //获取已签约的公司门店
        List<StoreNewDto> storeList = sweepStreetsMapper.getContractStoreByCompanyId(dto);

        if (storeList != null && storeList.size() > 0) {
            for (int i = 0; i < storeList.size(); i++) {
                if (dto.getStoreIdStr().indexOf(storeList.get(i).getStoreId() + "") < 0) {
                    storeNameStr += storeList.get(i).getStoreName() + ",";
                }
            }
        }
        if (!"".equals(storeNameStr)) {
            storeNameStr = storeNameStr.substring(0, storeNameStr.length() - 1);
            resultData.setFail("门店 " + storeNameStr + " 已签约，不能取消关联");
            return resultData;
        }

        //更新公司信息
        sweepStreetsMapper.updateCompany(dto);
        //modify by haidan 2019-09-10
        //如取的是全国（除当前城市）的公司数据[即：acCityNo!=checkCityNo]，则保存时增加查询得到的公司的归属城市为发布城市，其他情况不追加发布城市。
        if (!dto.getCheckCityNo().equals(dto.getAcCityNo())) {
            Boolean isAdd = false;
            List<CompanyReleaseCity> crcList = companyReleaseCityMapper.getByCompanyId(dto.getCompanyId());
            if (CollectionUtils.isNotEmpty(crcList)) {
                for (CompanyReleaseCity crc : crcList) {
                    if (crc.getReleaseCityNo().equals(dto.getCheckCityNo())) {
                        isAdd = false;
                        break;
                    } else {
                        isAdd = true;
                    }
                }
            } else {
                isAdd = true;
            }
            if (isAdd) {
                CompanyReleaseCity crc = new CompanyReleaseCity();
                crc.setCompanyId(dto.getCompanyId());
                crc.setReleaseCityNo(dto.getCheckCityNo());
                crc.setReleaseCenterId(dto.getCenterId());
                crc.setIsDelete(false);
                crc.setUserCreate(dto.getUserUpdate());
                crc.setDateCreate(new Date());
                companyReleaseCityMapper.insert(crc);
            }
        }

        //删除原来的附件图片，并重新上传
        String delFileRecordMainIds = dto.getDelFileRecordMainIds();
        if (StringUtil.isNotEmpty(delFileRecordMainIds)) {
            String[] arrays = delFileRecordMainIds.split(",");
            for (int i = 0; i < arrays.length; i++) {
                if (StringUtil.isNotEmpty(arrays[i])) {
                    FileRecordMain fileRecordMain = new FileRecordMain();
                    fileRecordMain.setFileRecordMainId(Integer.valueOf(arrays[i]));
                    fileRecordMain.setIsDelete(true);
                    fileRecordMainMapper.update(fileRecordMain);
                }
            }
        }
        //重新更新附件
        String fileRecordMainIds = dto.getFileRecordMainIds();
        if (StringUtil.isNotEmpty(fileRecordMainIds)) {
            String[] arrays = fileRecordMainIds.split(",");
            // 关联相关文件(RefId)
            for (int i = 0; i < arrays.length; i++) {
                if (StringUtil.isNotEmpty(arrays[i])) {
                    FileRecordMain fileRecordMain = new FileRecordMain();
                    fileRecordMain.setFileRecordMainId(Integer.valueOf(arrays[i]));
                    fileRecordMain.setRefId(dto.getCompanyId());
                    fileRecordMain.setIsDelete(false);
                    fileRecordMainMapper.update(fileRecordMain);
                }
            }
        }
        //更新联动框架协议中的公司信息
        frameContractMapper.updateCompanyByCompanyNo(dto);

        try {
            // 调用OP 同步修改公司
            String url = SystemParam.getWebConfigValue("opUrl") + "company";
            String url18 = SystemParam.getWebConfigValue("opUrl18") + "companies";
            OpCompanyDto companyDto = fangyouAccountService.getOPCompanyById(dto.getCompanyId());
            companyDto.setUserIdUpdate(dto.getUserUpdate());
            User user = userMapper.getUserByUserId(dto.getUserUpdate());
            if (null != user) {
                companyDto.setUserNameUpdate(user.getUserName());
            }
            //jsonString设值
            if (null != companyDto.getCompanyNo() && "" != companyDto.getCompanyNo()) {

                String jsonData = JsonUtil.parseToJson(companyDto);
                try {
                    logger.info("修改公司信息申请接口: #####请求#url=" + url + "##userIdUpdate=" + companyDto.getUserIdUpdate());
                    String opResult = HttpClientUtil.jsonPut(url, jsonData);
                    //op返回值
                    //Map<String,Object> opMap = (Map<String,Object>) JsonUtil.parseToObject(opResult,  Map.class);
                    // logger.info("CRM房友账号绑定申请接口返回码："+opMap.get("returnCode").toString()+",返回信息："+opMap.get("returnMsg").toString());
                } catch (Exception e) {

                }


                if ("1".equals(SystemParam.getWebConfigValue("opUrl18Flag"))) {
                    try {
                        // 18版op
                        logger.info("OP18版修改公司信息申请接口: #####请求#url=" + url18 + "##userIdUpdate=" + companyDto.getUserIdUpdate());
                        String opResult18 = HttpClientUtil.jsonPut(url18, jsonData);

                        //op返回值
                        //Map<String,Object> opMap18 = (Map<String,Object>) JsonUtil.parseToObject(opResult18,  Map.class);
                        //logger.info("OP18版修改公司信息申请接口返回码："+opMap18.get("returnCode").toString()+",返回信息："+opMap18.get("returnMsg").toString());
                    } catch (Exception e) {

                    }
                }
            }
        } catch (Exception e) {
            logger.error("sweepStreets", "SweepStreetsService", "updateCompany", "", null, "", "调用OP同步修改公司失败", e);
        }

        //更新公司与门店关系表
        //获取old的公司与门店关系
        List<StoreNewDto> storeNewDtoList = sweepStreetsMapper.getCompanyStore(dto);
        String storeIds[];
        if (dto.getStoreIdStr() != null && !"".equals(dto.getStoreIdStr())) {
            storeIds = dto.getStoreIdStr().split(",");
        } else {
            storeIds = new String[0];
        }
        //新增
        for (int i = 0; i < storeIds.length; i++) {
            Integer storeId = Integer.parseInt(storeIds[i]);
            CompanyStoreNewDto companyStoreNewDto = new CompanyStoreNewDto();
            companyStoreNewDto.setCompanyId(dto.getCompanyId());
            companyStoreNewDto.setStoreId(storeId);
            companyStoreNewDto.setDelete(0);
            companyStoreNewDto.setFlag(0);
            companyStoreNewDto.setUserCreate(dto.getUserUpdate());
            companyStoreNewDto.setUserUpdate(dto.getUserUpdate());

            boolean flag = true;
            for (StoreNewDto storeNewDtoTemp : storeNewDtoList) {
                if (storeIds[i].equals(storeNewDtoTemp.getStoreNo())) {
                    flag = false;
                }
            }
            if (flag) {
                //增加
                sweepStreetsMapper.addCompanyStore(companyStoreNewDto);
            }
        }

        //删除
        for (StoreNewDto storeNewDtoTemp : storeNewDtoList) {
            Integer storeId = Integer.parseInt(storeNewDtoTemp.getStoreNo());
            CompanyStoreNewDto companyStoreNewDto = new CompanyStoreNewDto();

            companyStoreNewDto.setId(storeNewDtoTemp.getStoreId());
            companyStoreNewDto.setCompanyId(dto.getCompanyId());
            companyStoreNewDto.setStoreId(storeId);
            companyStoreNewDto.setDelete(1);
            companyStoreNewDto.setFlag(1);
            companyStoreNewDto.setUserCreate(dto.getUserCreate());
            companyStoreNewDto.setUserUpdate(dto.getUserUpdate());

            boolean flag = true;
            for (int i = 0; i < storeIds.length; i++) {
                if (storeIds[i].equals(storeNewDtoTemp.getStoreNo())) {
                    flag = false;
                }
            }
            if (flag) {
                //验证门店是否开通房友账号（等房友升级再放开，修改时间：2017年7月26日16:59:32）
                /*int count=sweepStreetsMapper.checkStoreFangyouNo(storeNewDtoTemp.getStoreNo());
                if(count>0){
                    storeNameFangyouStr+=storeNewDtoTemp.getStoreName()+",";
                }*/
                //删除
                sweepStreetsMapper.updateCompanyStore(companyStoreNewDto);
            }
        }
        if (!"".equals(storeNameFangyouStr)) {
            storeNameFangyouStr = storeNameFangyouStr.substring(0, storeNameFangyouStr.length() - 1);
        }
        resultData.setSuccess();
        resultData.setReturnData(storeNameFangyouStr);
        return resultData;
    }

    public ResultData<CompanyNewDto> getCompanyById(CompanyNewDto dto) throws Exception {
        ResultData<CompanyNewDto> resultData = new ResultData<CompanyNewDto>();
        // 获取文件信息
        String fileRecordMainIds = "";
        // 营业执照  
        List<ContractFileDto> fileBusinessList = new ArrayList<ContractFileDto>();
        FileRecordMain businessFile = new FileRecordMain();
        Integer companyId = dto.getCompanyId();
        String checkCityNo = dto.getCheckCityNo();
        if (companyId == null) {
            //modify by haidan 2019-09-10
            //优先取当前操作人员归属城市的公司数据，如没有则取全国（除当前城市）的公司数据
            Map<String, Object> queryParam = new HashMap<>();
            queryParam.put("businessLicenseNo", dto.getBusinessLicenseNo());
            queryParam.put("checkCityNo", checkCityNo);
            List<Company> list = companyMapper.checkBusinessLicenseNoWX(queryParam);
            if (CollectionUtils.isNotEmpty(list)) {
                companyId = list.get(0).getId();
            } else {
                queryParam.remove("checkCityNo");//取全国
                list = companyMapper.checkBusinessLicenseNoWX(queryParam);
                if (CollectionUtils.isNotEmpty(list)) {
                    companyId = list.get(0).getId();
                }
            }
        }
        if (companyId != null) {
            dto.setCompanyId(companyId);
            businessFile.setRefId(companyId);
            businessFile.setIsDelete(false);
            List<FileRecordMain> fileMdlList = fileRecordMainMapper.getBusinessLicenceByCompanyId(businessFile);
            fileRecordMainIds = contractService.pushFileRecord(fileMdlList, fileRecordMainIds, fileBusinessList);
            if (StringUtil.isNotEmpty(fileRecordMainIds) && fileRecordMainIds.length() > 0) {
                fileRecordMainIds = fileRecordMainIds.substring(0, fileRecordMainIds.length() - 1);
            }
        }

        CompanyNewDto companyNewDto = sweepStreetsMapper.getCompanyById(dto);
        if (null != companyNewDto && companyId != null) {
            companyNewDto.setCheckCityNo(checkCityNo);
            companyNewDto.setFileRecordMainIds(fileRecordMainIds);
            companyNewDto.setBusinessLicenceFileList(fileBusinessList);
            companyNewDto.setReleaseCity(companyReleaseCityService.getReleaseCityName(companyId, companyNewDto.getAcCityName()));
        }
        resultData.setReturnData(companyNewDto);
        return resultData;
    }

    public ResultData<List<StoreNewDto>> getNotBindStoreList(StoreNewDto dto) throws Exception {
        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        List<StoreNewDto> list = sweepStreetsMapper.getNotBindStoreList(dto);
        if (null != list && !list.isEmpty()) {
            resultData.setReturnData(list);
        }
        return resultData;
    }

    public ResultData<List<StoreNewDto>> getBToAStoreList(StoreNewDto dto) throws Exception {
        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        List<StoreNewDto> list = sweepStreetsMapper.getBToAStoreList(dto);
        if (null != list && !list.isEmpty()) {
            resultData.setReturnData(list);
        }
        return resultData;
    }

    public ResultData<List<StoreNewDto>> getBToAStoreListForPush(StoreNewDto dto) throws Exception {
        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        List<StoreNewDto> list = sweepStreetsMapper.getBToAStoreListForPush(dto);
        if (null != list && !list.isEmpty()) {
            resultData.setReturnData(list);
        }
        return resultData;
    }

    public ResultData<List<StoreNewDto>> updateStoreBToAAlert(StoreNewDto dto) throws Exception {
        ResultData resultData = new ResultData();
        int count = sweepStreetsMapper.updateStoreBToAAlert(dto);
        if (count > 0) {
            resultData.setReturnData(dto);
            resultData.setSuccess();
        } else {
            resultData.setFail("修改失败");
        }
        return resultData;
    }

    public ResultData addCompanyBusinessInfo(CompanyBusinessDto dto) throws Exception {
        ResultData resultData = new ResultData();
        CompanyBusinessDto companyBusinessDto = sweepStreetsMapper.checkCompanyBusiness(dto);
        if (companyBusinessDto == null) {
            int count = sweepStreetsMapper.addCompanyBusinessInfo(dto);
            if (count > 0) {
                resultData.setReturnData(dto);
                resultData.setSuccess();
            } else {
                resultData.setFail("新增营业执照信息失败");
            }
        } else {
            resultData.setReturnData(companyBusinessDto);
        }
        return resultData;
    }

    public ResultData refershStoreContacts() {
        ResultData resultData = new ResultData();
        List<StoreNewDto> storeList = sweepStreetsMapper.getStoreManagerList();
        int totalNum = 0;
        int addNum = 0;
        if (storeList != null && storeList.size() > 0) {
            totalNum = storeList.size();
            for (StoreNewDto dto : storeList) {
                //创建门店联系人
                ContactsDto contactsDto = new ContactsDto();

                ResultData<String> back = seqNoAPI.getSeqNoByTypeCode("TYPE_CONTACT");
                String contactsNo = back.getReturnData();
                contactsDto.setContactsNo(contactsNo);
                contactsDto.setStoreId(dto.getStoreId());
                contactsDto.setContactsName(dto.getStoreManager());
                contactsDto.setContactsPhone(dto.getStoreManagerPhone());
                contactsDto.setUserCreate(dto.getUserCreate() + "");
                contactsDto.setDateCreate(dto.getDateCreate());

                //判断联系人是否重复
                int result = followMapMapper.checkContacts(contactsDto);
                if (result > 0) {
                    //如果重复 忽略
                } else {
                    addNum++;
                    //插入门店联系人信息
                    followMapMapper.addContacts(contactsDto);
                }
            }
        }
        resultData.setReturnData("有门店负责人的总数：" + totalNum + "个；新增联系人数量：" + addNum + "个");
        return resultData;
    }

    public ResultData getAllCityList() throws Exception {
        ResultData resultData = new ResultData();
        List<CityDto> list = sweepStreetsMapper.getAllCityList();
        if (null != list && !list.isEmpty()) {
            resultData.setReturnData(list);
        }
        return resultData;
    }


    /**
     * 构造OP公司DTO
     */
    private OPCompanyDto buildOPCompany(Company dto) {
        OPCompanyDto opdto = new OPCompanyDto();
        BeanUtils.copyProperties(dto, opdto);

        String cityNo = dto.getCityNo();
        opdto.setCityName(SystemParam.getCityNameByCityNo(cityNo));
        String districtNo = dto.getDistrictNo();
        opdto.setDistrictName(SystemParam.getDistrictNameByDistrictNo(districtNo));
        String areaNo = dto.getAreaNo();
        opdto.setAreaName(SystemParam.getAreaNameByAreaNo(areaNo));
        opdto.setCompanyAddr(dto.getAddress());
        opdto.setLinkMan(dto.getLegalPerson());
        opdto.setLinkPhone(dto.getContactNumber());
        opdto.setFyCompanyId(dto.getFangyouCompanyId());
        opdto.setUserIdCreate(dto.getUserCreate());
        return opdto;
    }

    /*
     * 单独起线程掉用接口
     */
    /*class CmpOpThread extends Thread {
        private IOPCompanyService opCompanyService;
        private String opdto;
        private String fyCompanyId;
        private String opType;

        public CmpOpThread(IOPCompanyService opCompanyService, String opdto, String fyCompanyId, String opType) {
            this.opCompanyService = opCompanyService;
            this.opdto = opdto;
            this.fyCompanyId = fyCompanyId;
            this.opType = opType;
        }

        @Override
        public void run() {
            if (opType.equals("create")) {
                opCompanyService.createCmpl(this.opdto);
            }
            if (opType.equals("update")) {
                opCompanyService.updateCmpl(this.opdto);
            }
            if (opType.equals("delete")) {
                opCompanyService.delCmpl(this.fyCompanyId);
            }
        }
    }*/


    /*
     * 单独起线程掉用接口
     */
    class CmpOpThreadHttp extends Thread {
        private OPCompanyHttpService oPCompanyHttpService;
        private String opdto;
        private String fyCompanyId;
        private String opType;

        public CmpOpThreadHttp(OPCompanyHttpService oPCompanyHttpService, String opdto, String fyCompanyId, String opType) {
            this.oPCompanyHttpService = oPCompanyHttpService;
            this.opdto = opdto;
            this.fyCompanyId = fyCompanyId;
            this.opType = opType;
        }

        @Override
        public void run() {
            if (opType.equals("create")) {
                oPCompanyHttpService.createCmpl(this.opdto);
            }
            if (opType.equals("update")) {//无调用
                oPCompanyHttpService.updateCmpl(this.opdto);
            }
            if (opType.equals("delete")) {//无调用
                oPCompanyHttpService.delCmpl(this.fyCompanyId);
            }
        }
    }

    /**
     * 根据门店编号查询是否可以编辑
     * 0 标识自费装修且有翻牌完成日期 ，1表示我司装修且翻牌申请中，2表示我司装修且翻牌审核通过，其他表示可以编辑
     */
    public Integer getStoreBusinessPlaceEditFlag(String storeNo) throws Exception {
        Integer editFlag = this.storeMapper.getStoreBusinessPlaceEditFlag(storeNo);
        return editFlag;
    }
}
