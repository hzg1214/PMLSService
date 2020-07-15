package cn.com.eju.deal.company.service;

import cn.com.eju.deal.base.code.dao.CommonCodeMapper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.base.ConvertTo;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.company.base.ICompanyServer;
import cn.com.eju.deal.company.dao.CompanyLogMapper;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.company.model.CompanyLog;
import cn.com.eju.deal.company.service.concrete.*;
import cn.com.eju.deal.contract.dao.StoreFangyouAccountMapper;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.company.CompanyDto;
import cn.com.eju.deal.dto.company.CompanyStoreDto;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sky on 2016/3/25.
 * 公司服务层
 */
@Service("companyService")
public class CompanyService extends BaseService {

    @Resource
    private CompanyMapper companyMapper;

    @Resource
    private CompanyLogMapper companyLogMapper;

    @Resource
    private FileRecordMainMapper fileRecordMainMapper;

    @Resource
    private CommonCodeMapper commonCodeMapper;

    @Resource
    private CompanyReleaseCityService companyReleaseCityService;


    @Resource
    private StoreFangyouAccountMapper storeFangyouAccountMapper;

    public String getCompanyDetail(int id) throws Exception {

        ICompanyServer<Integer, CompanyDto> service = new CompanyDetailConcrete();
        return service.execute(id).toString();
    }

    /**
     * 获取公司基本信息
     *
     * @param companyId 公司编号
     * @return 返回
     * @throws Exception 异常信息
     */
    public String getCompanyInfo(int companyId) throws Exception {
        ResultData<CompanyDto> resultData = new ResultData<>();
        CompanyDto companyDto = new CompanyDto();
        Company company = companyMapper.getById(companyId);
        if (company == null) {
            companyDto = new CompanyDto();
        }
        companyDto = ConvertTo.CompanyDto(company);
        //给拓展字段赋name值
        //城市Name
        companyDto.setCityName(SystemParam.getCityNameByCityNo(companyDto.getCityNo()));

        // 业绩归属城市
        companyDto.setAcCityName(SystemParam.getCityNameByCityNo(companyDto.getAcCityNo()));
        // 发布城市
        companyDto.setRealseCityName(companyReleaseCityService.getReleaseCityName(companyId, companyDto.getAcCityName()));

        //行政区Name
        companyDto.setDistrictName(SystemParam.getDistrictNameByDistrictNo(companyDto.getDistrictNo()));
        //营业执照性质
        companyDto.setBusinessLicenseNatureNm(SystemParam.getDicValueByDicCode(companyDto.getBusinessLicenseNature()));
        //公司类型
        companyDto.setCompanyTypeNm(SystemParam.getDicValueByDicCode(company.getCompanyType() + ""));
        //业务类型
        String bizType = companyDto.getBizType();
        if (bizType != null && !bizType.equals("")) {
            String bizTypeNm = null;
            if (bizType.contains("|")) {
                String[] bizTypeArr = bizType.split("\\|");
                bizTypeNm = commonCodeMapper.queryByDicCode(bizTypeArr[0]).getDicValue();
                bizTypeNm += "、" + commonCodeMapper.queryByDicCode(bizTypeArr[1]).getDicValue();

            } else {
                bizTypeNm = commonCodeMapper.queryByDicCode(bizType).getDicValue();
            }
            companyDto.setBizTypeNm(bizTypeNm);
        }
        //附件 -->营业执照
        String fileRecordMainIds = "";
        List<ContractFileDto> fileBusinessList = new ArrayList<ContractFileDto>();
        FileRecordMain businessFile = new FileRecordMain();
        businessFile.setRefId(companyId);
        businessFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList = fileRecordMainMapper.getBusinessLicenceByCompanyId(businessFile);
        fileRecordMainIds = pushFileRecord(fileMdlList, fileRecordMainIds, fileBusinessList);
        if (StringUtil.isNotEmpty(fileRecordMainIds) && fileRecordMainIds.length() > 0) {
            fileRecordMainIds = fileRecordMainIds.substring(0, fileRecordMainIds.length() - 1);
        }
        if (null != companyDto) {
            companyDto.setFileRecordMainBusiness(fileBusinessList);
            companyDto.setFileRecordMainIds(fileRecordMainIds);
        }
        resultData.setReturnData(companyDto);
        return resultData.toString();
    }

    /**
     * 图片信息
     *
     * @param
     * @return
     */
    public String pushFileRecord(List<FileRecordMain> fileMdlList, String fileRecordMainIds, List<ContractFileDto> fileList)
            throws Exception {
        if (null != fileMdlList && fileMdlList.size() > 0) {
            for (int i = 0; i < fileMdlList.size(); i++) {
                ContractFileDto contractFileDto = new ContractFileDto();
                FileRecordMain fileRecordMain = fileMdlList.get(i);
                //对文件数据进行组装处理
                handleFileRecordMain(contractFileDto, fileRecordMain);

                //重新组装返回list
                fileList.add(contractFileDto);
                fileRecordMainIds = fileRecordMainIds + contractFileDto.getFileRecordMainId() + ",";

            }
        }
        return fileRecordMainIds;
    }

    /**
     * 对文件数据进行组装处理
     *
     * @param contractFileDto
     * @param fileRecordMain
     * @throws Exception
     */
    private void handleFileRecordMain(ContractFileDto contractFileDto, FileRecordMain fileRecordMain)
            throws Exception {
        //获取图片路径
        contractFileDto.setFileAbbrUrl(fileRecordMain.getFileAbbrUrl());
        contractFileDto.setFileName(fileRecordMain.getFileFullName());
        contractFileDto.setFileRecordMainId(fileRecordMain.getFileRecordMainId().toString());
        contractFileDto.setFileUrl(fileRecordMain.getFileUrl());
        contractFileDto.setUrl50(fileRecordMain.getUrl50());
    }

    /**
     * 获取公司列表
     *
     * @param queryParam 查询条件
     * @return 公司列表
     */
    public String getCompanyList(Map<?, ?> queryParam) throws Exception {
        ICompanyServer server = new CompanyListConcrete();
        return server.execute(queryParam).toString();
    }

    /**
     * 获取公司列表--给合同选取公司使用
     *
     * @param queryParam 查询条件
     * @return 公司列表
     */
    public String getOwnCompanyList(Map<?, ?> queryParam) throws Exception {
        ICompanyServer server = new CompanyOwnListConcrete();
        return server.execute(queryParam).toString();
    }

    /**
     * 增加公司
     *
     * @param request 公司信息
     * @return 新增的公司实体
     */
    public String addCompany(CompanyDto request) throws Exception {

        ICompanyServer<CompanyDto, CompanyDto> server = new CompanyAddConcrete();
        return server.execute(request).toString();
    }

    public int updateCompanyOnly(CompanyDto request) throws Exception {
        Company company = new Company();

        BeanUtils.copyProperties(request, company);
        return companyMapper.update(company);
    }

    /**
     * 更新公司信息
     *
     * @param request 公司信息请求参数
     * @return 更新结果
     */
    public String updateCompany(CompanyDto request) throws Exception {

        ICompanyServer<CompanyDto, Boolean> server = new CompanyUpdateConcrete();
        return server.execute(request).toString();

    }

    /**
     * 删除公司信息
     *
     * @param id 公司编号
     * @return 删除的结果
     */
    public String deleteCompany(Integer id) throws Exception {
        ICompanyServer<Integer, Boolean> server = new CompanyDeleteConcrete();

        return server.execute(id).toString();
    }

    /**
     * 添加公司门店信息
     *
     * @param companyStoreDto 添加公司门店信息
     * @return 返回添加结果
     */
    public String addCompanyStore(CompanyStoreDto companyStoreDto) throws Exception {
        ICompanyServer<CompanyStoreDto, CompanyStoreDto> server = new CompanyStoreAddConcrete();

        return server.execute(companyStoreDto).toString();
    }

    /**
     * 更新公司信息
     *
     * @param request 公司信息请求参数
     * @return 更新结果数
     */
    public int update(CompanyDto request) throws Exception {
        Company company = new Company();

        BeanUtils.copyProperties(request, company);
        int reslut = companyMapper.update(company);
        if (reslut > 0) {
            //查询最新公司修改日志ID
            int logId = 0;
            Map<String, Object> map = new HashMap<>();
            map.put("companyId", company.getId());
            CompanyLog comLog = companyLogMapper.selectNewByCompanyId(map);
            if (null != comLog) {
                logId = comLog.getId();
            }
            // 关联相关文件(RefId)
            List<ContractFileDto> newcontractFiles = request.getFileRecordMain();
            if (null != newcontractFiles && !newcontractFiles.isEmpty()) {
                //对原上传营业执照文件删除
                FileRecordMain attachmentFile = new FileRecordMain();
                attachmentFile.setRefId(company.getId());
                attachmentFile.setIsDelete(false);
                List<FileRecordMain> fileMdlList = fileRecordMainMapper.getBusinessLicenceByCompanyId(attachmentFile);
                if (null != fileMdlList && fileMdlList.size() > 0) {
                    for (FileRecordMain fileRecordMain : fileMdlList) {
                        FileRecordMain fileRecord = new FileRecordMain();
                        fileRecord.setFileRecordMainId(fileRecordMain.getFileRecordMainId());
                        fileRecord.setRefId(null);
                        fileRecord.setIsDelete(true);
                        fileRecordMainMapper.update(fileRecord);
                    }
                }
                for (int i = 0; i < newcontractFiles.size(); i++) {

                    String fileId = newcontractFiles.get(i).getFileRecordMainId();
                    if (!"null".equals(fileId) && StringUtil.isNotEmpty(fileId)) {
                        //关联新上传的营业执照
                        FileRecordMain fileRecordMain = new FileRecordMain();
                        fileRecordMain.setFileRecordMainId(Integer.valueOf(fileId));
                        fileRecordMain.setRefId(logId);
                        fileRecordMain.setIsDelete(false);
                        fileRecordMainMapper.update(fileRecordMain);
                        //新建记录覆盖原来的营业执照
                        List<FileRecordMain> fileRecordMainList = fileRecordMainMapper.getById(Integer.valueOf(fileId));
                        if (null != fileRecordMainList && fileRecordMainList.size() > 0) {
                            for (FileRecordMain fileRecordMain2 : fileRecordMainList) {
                                fileRecordMain2.setFileTypeId(1044);
                                fileRecordMain2.setRefId(company.getId());
                                fileRecordMain2.setCompanyId(company.getId());
                                fileRecordMainMapper.create(fileRecordMain2);
                            }
                        }


                    }
                }
            }
        }
        return reslut;
    }

    /**
     * 获取公司修改日志列表
     *
     * @param queryParam 查询条件
     * @return 日志列表
     */
    public String getLogList(Map<?, ?> queryParam) throws Exception {
        ResultData<List<CompanyLog>> resultData = new ResultData<>();

        List<CompanyLog> list = companyLogMapper.queryListByCompanyId(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);

        return resultData.toString();
    }

    /**
     * 获取修改日志详细
     *
     * @param queryParam 查询条件
     * @return 日志列表
     */
    public String getLogDetail(Integer logId) throws Exception {
        ResultData<CompanyLog> resultData = new ResultData<>();

        CompanyLog companyLog = companyLogMapper.getLogDetail(logId);
        resultData.setReturnData(companyLog);

        return resultData.toString();
    }

    /**
     * 更新公司信息
     *
     * @param request 公司信息请求参数
     * @return 更新结果数
     */
    public int createCompanyLog(Map<?, ?> queryParam) throws Exception {

        return companyLogMapper.createCompanyLog(queryParam);
    }

    /**
     * 公司营业执照重复验证
     *
     * @param request 公司信息请求参数
     * @return 更新结果数
     */
    public String checkBusinessLicenseNo(String businessLicenseNo) throws Exception {
        ResultData<List<Company>> resultData = new ResultData<>();
        List<Company> list = companyMapper.checkBusinessLicenseNo(businessLicenseNo);
        resultData.setReturnData(list);
        return resultData.toString();
    }


    /**
     * 查询公司信息根据公司NO
     *
     * @param queryParam 查询条件
     * @return 日志列表
     */
    public String getByNo(String companyNo) throws Exception {
        ResultData<Company> resultData = new ResultData<>();

        Company company = companyMapper.getByNo(companyNo);
        resultData.setReturnData(company);

        return resultData.toString();
    }

    /**
     * 查询公司信息根据公司NO
     *
     * @param queryParam 查询条件
     * @return 日志列表
     */
    public Company getByCompanyId(Integer companyId) throws Exception {
        Company company = companyMapper.getById(companyId);

        return company;
    }

    /**
     * 查询公司信息根据公司NO
     *
     * @param queryParam 查询条件
     * @return 日志列表
     */
    public Company getByCompanyNo(String companyNo) throws Exception {

        Company company = companyMapper.getByNo(companyNo);

        return company;
    }
}
