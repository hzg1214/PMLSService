package cn.com.eju.pmls.developer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.pmls.developer.dao.DeveloperBrandMapper;
import cn.com.eju.pmls.developer.dao.DeveloperMapper;
import cn.com.eju.pmls.developer.dto.DeveloperBrandDto;
import cn.com.eju.pmls.developer.dto.DeveloperDto;

@Service("developerService")
public class DeveloperService extends BaseService {
	
    @Resource
    private DeveloperMapper developerMapper;
    
    @Resource
    private DeveloperBrandMapper developerBrandMapper;
    
    //获取合作方列表
    public ResultData getDeveloperList(Map<String, Object> queryParam) {
        ResultData<List<DeveloperDto>> resultData = new ResultData<List<DeveloperDto>>();
        List<DeveloperDto> list = developerMapper.getDeveloperList(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }
    
    //获取合作方信息
    public ResultData getDeveloperInfo(DeveloperDto dto) {
        ResultData<DeveloperDto> resultData = new ResultData<DeveloperDto>();
        DeveloperDto DeveloperDto = developerMapper.getDeveloperInfo(dto);
        resultData.setReturnData(DeveloperDto);
        return resultData;
    }
    
    //获取合作方信息
    public ResultData getDeveloperInfo2(DeveloperDto dto) {
    	ResultData<DeveloperDto> resultData = new ResultData<DeveloperDto>();
    	DeveloperDto DeveloperDto = developerMapper.getDeveloperInfo2(dto);
    	resultData.setReturnData(DeveloperDto);
    	return resultData;
    }
    
    //获取合作方信息
    public ResultData getDeveloperCountByName(DeveloperDto dto) {
    	ResultData resultData = new ResultData<>();
    	int count = developerMapper.getDeveloperCountByName(dto);
    	resultData.setReturnData(count);
    	return resultData;
    }
    
    //新增合作方
    @Transactional(rollbackFor=Exception.class)
    public ResultData addDeveloper(DeveloperDto dto) throws Exception{
        ResultData resultData = new ResultData();
        DeveloperDto developerDto = developerMapper.getDeveloperInfo2(dto);
        if(developerDto!=null){
            resultData.setFail("操作失败，该合作方信息已存在！");
            return  resultData;
        }
        int count = developerMapper.addDeveloper(dto);
        if(count>0){
            resultData.setSuccess("新增成功");
        }else{
            resultData.setFail("新增失败");
        }
        return resultData;
    }
    
    //修改合作方
    @Transactional(rollbackFor=Exception.class)
    public ResultData updateDeveloper(DeveloperDto dto) throws Exception{
        ResultData resultData = new ResultData();
        DeveloperDto selDto = new DeveloperDto();
//        selDto.setBusinessLicenseNo(dto.getBusinessLicenseNo());
//        selDto.setUpdateId(Integer.parseInt(dto.getId()+""));
        //排除自己查询
        DeveloperDto developerDto = developerMapper.getDeveloperInfo(selDto);

        //改过合作方名称或者统一社会代码的需要校验是否存在
        if(developerDto != null ) {
        	String oldAddress=developerDto.getAddressDetail();
        	String newAddress=dto.getCityName()+dto.getDistrictName()+dto.getAddress();
        	if(!developerDto.getDeveloperName().equals(dto.getDeveloperName()) || !developerDto.getBusinessLicenseNo().equals(dto.getBusinessLicenseNo())){
        		//校验渠道是否存在
        		DeveloperDto bDto=developerMapper.getDeveloperInfo2(dto);
        		if(bDto!=null){
        			resultData.setFail("操作失败，该合作方信息已存在！");
        			resultData.setReturnMsg("操作失败，该合作方信息已存在！");
        			return  resultData;
        		}
        	}
        }
        int count = developerMapper.updateDeveloper(dto);
        if(count>0){
            resultData.setSuccess("修改成功");
        }else{
            resultData.setFail("修改失败");
        }
        return resultData;
    }
    
    //删除合作方
    @Transactional(rollbackFor=Exception.class)
    public ResultData deleteDeveloper(DeveloperDto dto) throws Exception{
        ResultData resultData = new ResultData();
        DeveloperDto developerDto = developerMapper.getDeveloperInfo(dto);
        if(developerDto != null && !StringUtil.isEmpty(developerDto.getEatateDeveloperName())) {
        	resultData.setFail("此合作方已被项目关联，不可删除");
        	return resultData;
        }
        int count = developerMapper.deleteDeveloper(dto);
        if(count>0){
            resultData.setSuccess("删除成功");
        }else{
            resultData.setFail("删除失败");
        }
        return resultData;
    }
    
    //新增合作方城市关系表
    @Transactional(rollbackFor=Exception.class)
    public ResultData addDeveloperReleaseCity(DeveloperDto dto) throws Exception{
        ResultData resultData = new ResultData();
        int count = developerMapper.addDeveloperReleaseCity(dto);
        if(count>0){
            resultData.setSuccess("新增成功");
        }else{
            resultData.setFail("新增失败");
        }
        return resultData;
    }

  //根据合作方品牌获取垫佣和大客户
    public ResultData getCustomerAndYjDy(Map<String, Object> queryParam) {
    	ResultData resultData = new ResultData();
    	Map<String, Object> resultMap = new HashMap<>();
    	String bigCustomerId = ""; //大客户id
    	String bigCustomerName="";
    	String mattressNailId = ""; //是否垫佣id
    	String mattressNailName="";
    	String bigCustomerFlag = "22602";
    	String isYjDy = "0";
    	DeveloperBrandDto developerBrand = developerBrandMapper.getDeveloperBrandInfo(queryParam);
    	Integer topId = 0; 
    	//获取最顶级品牌ID
    	if(!StringUtils.isEmpty(developerBrand)) {
    		topId = Integer.parseInt(developerBrand.getOrgId().split("/")[1]);
    	}
    	if(topId != 0) {//有值
    		queryParam.put("id", topId);
    		queryParam.remove("developerBrandCode");
    		developerBrand = developerBrandMapper.getDeveloperBrandInfo(queryParam);
    	}
    	//品牌名称到dbo.LNK_BigCustomer和dbo.LNK_MattressNail表中查询是否有值,
    	//有:是垫佣，是大客户；否：没垫佣，不是大客户
    	String developerBrandName = "";
    	Integer partner =0;
//    	if(developerBrand!=null && developerBrand.getDeveloperBrandName() != null) {
//    		developerBrandName = developerBrand.getDeveloperBrandName();
//    		queryParam.put("developerBrandName", developerBrandName);
//    		Map<String, Object> customerResult = new HashMap<>();
//    		Map<String, Object> mattressResult = new HashMap<>();
//    		customerResult = developerMapper.selBigCustomerByName(queryParam);
//    		
//    		mattressResult = developerMapper.selMattressNailByName(queryParam);
//    		if(customerResult !=null) {//有值则为大客户
//    			bigCustomerFlag = "22601";
//    			bigCustomerId = customerResult.get("bigCustomerId")+"";
//    		}
//    		if(mattressResult!=null) {//有值则为垫佣
//    			isYjDy = "1";
//    			mattressNailId = mattressResult.get("mattressNailId")+"";
//    		}
//    		
//    		if(developerBrand.getPartner()!=null) {
//    			partner = developerBrand.getPartner();
//    		}
//    	}
    	if(developerBrand!=null) {
			developerBrandName = developerBrand.getDeveloperBrandName();
			if(developerBrand.getBigCustomerId() !=null) {//有值则为大客户
				bigCustomerId = developerBrand.getBigCustomerId()+"";
				bigCustomerFlag = developerBrand.getBigCustomerFlag()+"";
				bigCustomerName = developerBrand.getBigCustomerName();
			}
			if(developerBrand.getMattressNailId()!=null) {//有值则为垫佣
				mattressNailId = developerBrand.getMattressNailId()+"";
				isYjDy = developerBrand.getIsYjDy()+"";
				mattressNailName=developerBrand.getMattressNailName();
			}
			
			if(developerBrand.getPartner()!=null) {
				partner = developerBrand.getPartner();
			}
		}
    	resultMap.put("bigCustomerFlag", bigCustomerFlag);
    	resultMap.put("isYjDy", isYjDy);
    	resultMap.put("bigCustomerId", bigCustomerId);
    	resultMap.put("bigCustomerName", bigCustomerName);
    	resultMap.put("mattressNailId", mattressNailId);
    	resultMap.put("mattressNailName", mattressNailName);
    	resultMap.put("partner", partner);//合作类型
    	resultMap.put("partnerAbbreviationNm", developerBrandName);//合作方简称
    	resultData.setReturnData(resultMap);
    	return resultData;
    }
}
