package cn.com.eju.deal.company.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import cn.com.eju.deal.dto.op.OpCompanyCreditCodeDto;
//import cn.com.eju.deal.dubbo.op.api.IOPCompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.common.util.HttpClientUtil;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.company.service.CompanyService;
import cn.com.eju.deal.company.service.CompanysService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.company.CompanyDto;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.dto.op.OpCompanyDto;
import cn.com.eju.deal.dto.open.OPCompanyDto;
//import cn.com.eju.deal.dubbo.op.api.IOPCompanyService;
import cn.com.eju.deal.fangyou.service.FangyouAccountService;
import cn.com.eju.deal.user.dto.GroupDto;

/**
 * Created by Sky on 2016/3/24.
 * 公司信息接口
 */
@RestController
@RequestMapping(value = "companys")
public class CompanyController extends BaseController
{
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "companyService")
    private CompanyService companyService;
    
    @Resource(name = "companysService")
    private CompanysService companysService;
    
//    @Resource
//	private IOPCompanyService opCompanyService;
    
    @Resource
    private CompanyMapper companyMapper;
    
    @Resource
    private FileRecordMainMapper fileRecordMainMapper;
    
    @Resource(name = "fangyouAccountService")
    private FangyouAccountService fangyouAccountService;

    /**
     * 根据公司编号查询公司明细
     *
     * @param id 公司的编号
     * @return 公司信息
     */
    @RequestMapping(value = "/brief/{id}", method = RequestMethod.GET)
    public String getBriefById(@PathVariable int id)
    {
        // 构建返回
        ResultData<Map<String,Object>> rstData = new ResultData<Map<String,Object>>();

        String companyDetail = new String();
        try
        {
            companyDetail = companyService.getCompanyInfo(id);
        }
        catch (Exception e)
        {
            logger.error("company", "CompanyController", "getBriefById", "", null, "", "根据公司编号查询公司基本信息失败", e);
        }
        return companyDetail;
    }

    /**
     * 根据公司编号查询公司明细
     *
     * @param id 公司的编号
     * @return 公司信息
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable int id)
    {
    	  // 构建返回
        ResultData<Map<String,Object>> rstData = new ResultData<Map<String,Object>>();
        
        String companyDetail = new String();
        try
        {
        	companyDetail = companyService.getCompanyDetail(id);
        }
        catch (Exception e)
        {
            logger.error("company", "CompanyController", "getById", "", null, "", "根据公司编号查询公司明细失败", e);
        }
        return companyDetail;
    }
    
    /**
     * 图片信息
     *
     * @param
     * @return
     */
    private String pushFileRecord(List<FileRecordMain> fileMdlList, String fileRecordMainIds, List<ContractFileDto> fileList)
        throws Exception
    {
        if (null != fileMdlList && fileMdlList.size() > 0)
        {
            for (int i = 0; i < fileMdlList.size(); i++)
            {
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
     * @param contractFileDto
     * @param fileRecordMain
     * @throws Exception
     */
     private void handleFileRecordMain(ContractFileDto contractFileDto, FileRecordMain fileRecordMain)
         throws Exception
     {
         //获取图片路径
          contractFileDto.setFileAbbrUrl(fileRecordMain.getFileAbbrUrl());
          contractFileDto.setFileName(fileRecordMain.getFileFullName());
          contractFileDto.setFileRecordMainId(fileRecordMain.getFileRecordMainId().toString());
          contractFileDto.setFileUrl(fileRecordMain.getFileUrl());
     }
     
    
    /**
     * 列表查询接口
     *
     * @param param 查询条件
     * @return 查询列表
     */
    @RequestMapping(value = "/q/{param}", method = RequestMethod.GET)
    public String list(@PathVariable String param)
    {
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        String selectOrgIdStr = (String)queryParam.remove("selectOrgId");
        String selectOrgId = selectOrgIdStr.replaceAll("Z", "/");
        queryParam.put("selectOrgId", selectOrgId);
        //权限控制,参数转换
        //  authParam(queryParam);
        String companyList = new String();
        try
        {
            companyList = companyService.getCompanyList(queryParam);
        }
        catch (Exception e)
        {
            logger.error("company", "CompanyController", "list", "", null, "", "列表查询", e);
        }
        return companyList;
    }
    
    /**
     * 列表查询接口 -给合同选取公司使用
     *
     * @param param 查询条件
     * @return 查询列表
     */
    @RequestMapping(value = "/q/own/{param}", method = RequestMethod.GET)
    public String ownlist(@PathVariable String param)
    {
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        // CRMCRM新增合同页面的获取公司列表信息的场合、需要加岗位条件
        if (queryParam.get("selectOrgId") != null) {
            String selectOrgIdStr = (String)queryParam.remove("selectOrgId");
            String selectOrgId = selectOrgIdStr.replaceAll("Z", "/");
            queryParam.put("selectOrgId", selectOrgId);
        }
        String ownCompanyList = new String();
        try
        {
            ownCompanyList = companyService.getOwnCompanyList(queryParam);
        }
        catch (Exception e)
        {
            logger.error("company", "CompanyController", "ownlist", "", null, "", "列表查询接口 -给合同选取公司使用失败", e);
        }
        return ownCompanyList;
    }
    
    /**
     * 创建公司
     *
     * @return 返回公司信息
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String Create(@RequestBody String jsonDto)
    {
        
        CompanyDto dto = JsonUtil.parseToObject(jsonDto, CompanyDto.class);
        String addCompany = new String();
        try
        {
        	dto.setFangyouCompanyId(UUID.randomUUID().toString());
            addCompany = companyService.addCompany(dto);
            // 调用OP 同步新增公司 1.构造OPCompanyDto 2.调用createCmpl方法
            //OPCompanyDto opCompanyDto = buildOPCompany(dto);
            //new CmpOpThread(opCompanyService,JsonUtil.parseToJson(opCompanyDto),null,"create").start();
            /*opCompanyService.createCmpl(JsonUtil.parseToJson(opCompanyDto));*/
        }
        catch (Exception e)
        {
            logger.error("company", "CompanyController", "Create", "", null, "", "创建公司失败", e);
        }
        return addCompany;
    }
    
    /**
     * 更新公司信息
     *
     * @param jsonDto 更新公司请求参数
     * @return 返回公司信息
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(@RequestBody String jsonDto)
    {
        
        CompanyDto company = JsonUtil.parseToObject(jsonDto, CompanyDto.class);
        String updateCompany = new String();
        try
        {
            updateCompany = companyService.updateCompany(company);
            // 调用OP 同步修改公司 1.构造OPCompanyDto 2.调用updateCmpl方法
            //OPCompanyDto opCompanyDto = buildOPCompany(company);
            /*opCompanyService.updateCmpl(JsonUtil.parseToJson(opCompanyDto));*/
            //new CmpOpThread(opCompanyService,JsonUtil.parseToJson(opCompanyDto),null,"update").start();
        }
        catch (Exception e)
        {
            logger.error("company", "CompanyController", "update", "", null, "", "更新公司信息失败", e);
        }
        return updateCompany;
    }
    
    /**
     * 删除公司信息
     *
     * @param id 公司编号
     * @return 删除结果
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable int id)
    {
        String deleteCompany = new String();
        try
        {
            deleteCompany = companyService.deleteCompany(id);
            // 调用OP 同步删除公司 1.调用delCmpl方法
            Company company = companyMapper.getById(id);
            //if(null != company){
                /*opCompanyService.delCmpl(company.getFangyouCompanyId());*/
            	//new CmpOpThread(opCompanyService,null,company.getFangyouCompanyId(),"delete").start();
            //}
        }
        catch (Exception e)
        {
            logger.error("company", "CompanyController", "delete", "", null, "", "删除公司信息失败", e);
        }
        return deleteCompany;
    }
    
    @RequestMapping(value = "/{companyId}/Store", method = RequestMethod.POST)
    public String AddCompanyStore(@RequestBody String jsonDto, @PathVariable Integer companyId)
    {
        
        return "";
    }
    
    /**
     * 根据公司名字查询公司
     *
     * @param id 公司的编号
     * @return 公司信息
     */
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public String getCompanyByName(@PathVariable String name)
    {
        //构建返回
        ResultData<CompanyDto> resultData = new ResultData<CompanyDto>();
        //查询
        Company mo;
        try
        {
            mo = companysService.getCompanyByName(name);
            
            //Model转换Dto
            CompanyDto dto = new CompanyDto();
            
            if (null != mo)
            {
                BeanUtils.copyProperties(mo, dto);
            }
            
            resultData.setReturnData(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Company", "CompanyController", "getCompanyByName", "", 0, "", "根据公司名字查询公司失败", e);
        }
        
        return resultData.toString();
    }
    
    /**
     * 
        * @Title: getOrgIdByPostId
        * @Description: 查询orgId
        * @return
        * @throws Exception
     */
    @RequestMapping(value = "/postId/{selectPostId}", method = RequestMethod.GET)
    public String getOrgIdByPostId(@PathVariable Integer selectPostId)
    {
        //构建返回
        ResultData<GroupDto> resultData = new ResultData<>();
        GroupDto groupDto = new GroupDto();
        try
        {
            groupDto = companysService.getOrgIdByPostId(selectPostId);
            resultData.setReturnData(groupDto);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return resultData.toString();
    }
    
    /**
     * 构造OP公司DTO
     */
    private OPCompanyDto buildOPCompany(CompanyDto dto){
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
    /*class CmpOpThread extends Thread{
    	private IOPCompanyService opCompanyService;
    	private String opdto;
    	private String fyCompanyId;
    	private String opType;
    	public CmpOpThread(IOPCompanyService opCompanyService, String opdto,String fyCompanyId, String opType){
    		this.opCompanyService = opCompanyService;
    		this.opdto = opdto;
    		this.fyCompanyId = fyCompanyId;
    		this.opType = opType;
    	}
    	
    	@Override
    	public void run() {
    		if(opType.equals("create")){
    			opCompanyService.createCmpl(this.opdto);
    		}
    		if(opType.equals("update")){
    			opCompanyService.updateCmpl(this.opdto);
    		}
    		if(opType.equals("delete")){
    			opCompanyService.delCmpl(this.fyCompanyId);
    		}
    	}
    }*/
    
    /**
     * 更新公司信息
     *
     * @param jsonDto 更新公司请求参数
     * @return 返回公司信息
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResultData<CompanyDto> updateCompany(@RequestBody String jsonDto)
    {        
        CompanyDto company = JsonUtil.parseToObject(jsonDto, CompanyDto.class);
        int cnt = 0;
        //构建返回
        ResultData<CompanyDto> resultData = new ResultData<CompanyDto>();
        
        try
        {
            cnt = companyService.update(company);
            
            //调用OP接口
            if(cnt>0)
            {
            	String url = SystemParam.getWebConfigValue("opUrl") + "company";
            	String url18 = SystemParam.getWebConfigValue("opUrl18") + "companies";
                String url18_2 = SystemParam.getWebConfigValue("opUrl18") + "companies/creditCodeSync";
            	      	
            	OpCompanyDto companyDto = fangyouAccountService.getOPCompanyById(company.getId());
                OpCompanyCreditCodeDto companyCreditCodeDto = fangyouAccountService.getOPCompanyCreditCodeById(company.getId());
            	companyDto.setUserIdUpdate(company.getUserUpdate());
            	companyDto.setUserNameUpdate(company.getUserUpdateName());
            	
	        	 //jsonString设值
            	if(null != companyDto.getCompanyNo() && "" != companyDto.getCompanyNo()){
		             String jsonData = JsonUtil.parseToJson(companyDto);
		             String jsonData_2 = JsonUtil.parseToJson(companyCreditCodeDto);
                    try{
                        logger.info("修改公司信息申请接口: #####请求#url="+url+"##userIdUpdate="+ companyDto.getUserIdUpdate());
                        String opResult = HttpClientUtil.jsonPut(url,jsonData);

                        //op返回值
//                        Map<String,Object> opMap = (Map<String,Object>) JsonUtil.parseToObject(opResult,  Map.class);
//                        logger.info("CRM房友账号绑定申请接口返回码："+opMap.get("returnCode").toString()+",返回信息："+opMap.get("returnMsg").toString());
                    } catch (Exception e){

                    }

		             
		             if("1".equals(SystemParam.getWebConfigValue("opUrl18Flag"))) {

                         try{
                                 // 18版op
                                 logger.info("OP18版修改公司信息申请接口: #####请求开始#url=" + url18 + "##userIdUpdate=" + companyDto.getUserIdUpdate());
                                 String opResult18 = HttpClientUtil.jsonPut(url18, jsonData);
                                 //op返回值
                                 // Map<String, Object> opMap18 = (Map<String, Object>) JsonUtil.parseToObject(opResult18, Map.class);
                                 //logger.info("OP18版修改公司信息申请接口返回码：" + opMap18.get("returnCode").toString() + ",返回信息：" + opMap18.get("returnMsg").toString());
                                 logger.info("OP18版修改公司信息申请接口: #####请求结束#url=" + url18 + "##userIdUpdate=" + companyDto.getUserIdUpdate());
                             }catch (Exception e){

                             }
                             try{
                                 // 18版-2op
                                 logger.info("OP18版修改公司信息申请接口: #####请求开始#url2=" + url18_2 + "##userIdUpdate=" + companyDto.getUserIdUpdate());
                                 String opResult18_2 = HttpClientUtil.jsonPost(url18_2, jsonData_2);

                               //op返回值
                                //   Map<String, Object> opMap18_2 = (Map<String, Object>) JsonUtil.parseToObject(opResult18_2, Map.class);
                                logger.info("OP18版修改公司信息申请接口: ####请求结束##url2=" + url18_2 + "##userIdUpdate=" + companyDto.getUserIdUpdate());
                             }catch (Exception e){

                             }

                     }
            	}
            }
            resultData.setTotalCount("1");
        }
        catch (Exception e)
        {
        	resultData.setFail();
            logger.error("company", "CompanyController", "update", "", null, "", "更新公司信息失败", e);
        }
        return resultData;
    }    

    /**
     * 公司营业执照重复验证
     *
     * @param param 查询条件
     * @return 查询列表
     */
    @RequestMapping(value = "/check/{businessLicenseNo}", method = RequestMethod.GET)
    public String checkBusinessLicenseNo(@PathVariable String businessLicenseNo)
    {
        String companyList = new String();
        try
        {
            companyList = companyService.checkBusinessLicenseNo(businessLicenseNo);
        }
        catch (Exception e)
        {
            logger.error("company", "CompanyController", "checkBusinessLicenseNo", "", null, "", "公司营业执照重复验证", e);
        }
        return companyList;
    }
    
    /**
     * 获取公司修改日志列表
     *
     * @param param 查询条件
     * @return 查询列表
     */
    @RequestMapping(value = "/log/{param}", method = RequestMethod.GET)
    public String logList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        String companyList = new String();
        try
        {
            companyList = companyService.getLogList(queryParam);
        }
        catch (Exception e)
        {
            logger.error("company", "CompanyController", "list", "", null, "", "列表查询", e);
        }
        return companyList;
    }
    
    /**
     * 添加公司修改日志
     *
     * @param jsonDto 更新公司请求参数
     * @return 返回公司信息
     */
    @RequestMapping(value = "addLog", method = RequestMethod.PUT)
    public String createCompanyLog(@RequestBody String jsonDto)
    {
        
        Map<String,Object> queryParam = JsonUtil.parseToObject(jsonDto, Map.class);
        String updateCompany = new String();
        try
        {

        	companyService.createCompanyLog(queryParam);
        }
        catch (Exception e)
        {
            logger.error("company", "CompanyController", "update", "", null, "", "更新公司信息失败", e);
        }
        return updateCompany;
    }
    
    /**
     * 获取修改日志详细
     *
     * @param param 查询条件
     * @return 查询列表
     */
    @RequestMapping(value = "/logDetail/{logId}", method = RequestMethod.GET)
    public String logDetail(@PathVariable Integer logId)
    {
    	  // 构建返回
        ResultData<Map<String,Object>> rstData = new ResultData<Map<String,Object>>();
        
        String companyList = new String();
        try
        {
            companyList = companyService.getLogDetail(logId);
            
            Map<String,Object> map = (Map<String,Object>)JsonUtil.parseToObject(companyList, Map.class);
        	Map<String,Object> mapData = (Map<String,Object>)map.get("returnData");
        	
        	// 获取文件信息
            String fileRecordMainIds = "";
            // 营业证
            List<ContractFileDto> fileBusinessList = new ArrayList<ContractFileDto>();
            FileRecordMain businessFile = new FileRecordMain();
            businessFile.setRefId(Integer.valueOf(mapData.get("id").toString()));
            businessFile.setIsDelete(false);

            List<FileRecordMain> fileMdlList = fileRecordMainMapper.getBusinessByCompanyId(businessFile);
            fileRecordMainIds = pushFileRecord(fileMdlList, fileRecordMainIds, fileBusinessList);
            mapData.put("fileRecordMainIds", fileRecordMainIds);
            mapData.put("fileRecordMainBusiness", fileBusinessList);
            
            rstData.setReturnData(mapData);
        }
        catch (Exception e)
        {
            logger.error("company", "CompanyController", "list", "", null, "", "列表查询", e);
        }
        return rstData.toString();
    }
    
    /**
     * 根据公司编号查询公司明细
     *
     * @param id 公司的编号
     * @return 公司信息
     */
    @RequestMapping(value = "/byNo/{companyNo}", method = RequestMethod.GET)
    public String getByNo(@PathVariable String companyNo)
    {
    	  // 构建返回
        ResultData<Map<String,Object>> rstData = new ResultData<Map<String,Object>>();
        
        String companyDetail = new String();
        try
        {
        	companyDetail = companyService.getByNo(companyNo);        	
        }
        catch (Exception e)
        {
            logger.error("company", "CompanyController", "getByNo", "", null, "", "根据公司编号查询公司信息失败", e);
        }
        return companyDetail;
    }
    @RequestMapping(value="/queryCompanyName/{param}",method= RequestMethod.GET)
	 public ResultData<?> name(@PathVariable String param){
		ResultData<?> resultData = companysService.queryCompanyName(param);
		return resultData;
		 
	 }
    
}


