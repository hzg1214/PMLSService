package cn.com.eju.deal.company.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.company.CompanyDto;
import cn.com.eju.deal.user.dao.GroupMapper;
import cn.com.eju.deal.user.dto.GroupDto;
import cn.com.eju.deal.user.model.Group;

/**   
* service层
* @author (li_xiaodong)
* @date 2016年3月24日 上午10:35:18
*/
@Service("companysService")
public class CompanysService extends BaseService
{
    
    @Resource
    private CompanyMapper companyMapper;
    
    @Resource
    private GroupMapper groupMapper;
    
    /** 
    * 查询
    * @param id
    * @return
    */
    
    public Company getCompanyByName(String name)
        throws Exception
    {
        Company company = companyMapper.getCompanyByName(name);
        return company;
    }
    /**
     * 查找输入的公司名字是否存在
     * @param param
     * @return
     */
	public ResultData<?> queryCompanyName(String param) {
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("companyName", param);
		List<Company> companyList = null;
		ResultData<List<Company>> resultData = new ResultData<>();
		try {
			companyList = companyMapper.queryCompanyName(queryParam);
		}catch(Exception e){
			resultData.setFail();
			return resultData;
		}
		resultData.setReturnData(companyList);
		if(companyList!=null && companyList.size()>0){
            resultData.setTotalCount(String.valueOf(companyList.size()));
        }
        else{
            resultData.setTotalCount("0");
        }
		resultData.setSuccess();
		return resultData;
	}
    	 
    
    /**
     * 
        * @Title: getOrgIdByPostId
        * @Description: 查询orgId
        * @return
        * @throws Exception
     */
    public GroupDto getOrgIdByPostId(Integer postId) throws Exception
    {
       
        Group group = groupMapper.getOrgIdByPostId(postId);
        GroupDto groupDto = new GroupDto();
        groupDto.setOrgIdStr(group.getOrgIdStr());
        return groupDto;
    }
    
}
