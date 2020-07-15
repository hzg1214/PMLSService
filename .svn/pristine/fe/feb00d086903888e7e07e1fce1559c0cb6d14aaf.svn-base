package cn.com.eju.deal.company.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.core.dao.IDao;


public interface CompanyMapper extends IDao<Company> {
    
    List<Company> getCompanyByStoreId(Integer storeId) throws Exception;
    
    List<Company> queryOwnList(Map<?, ?> param) throws Exception;
    
    Company getCompanyByName(String name) throws Exception;
    
    
    List<Company> queryListSearsh(Map<?, ?> param);
    /**
     * 根据房友公司Id查询公司
     * @param fangyouCompanyId
     * @return Company
     * @throws Exception
     */
    Company getCompanyByFyCompanyId(String fangyouCompanyId) throws Exception;
    
    /**
     * 根据公司名称模糊查询公司
     * @param companyName
     * @return Company
     * @throws Exception
     */
    List<Company> getCompanyByNameFuzzy(Map<String, Object> queryParam) throws Exception;

    /**
     * 根据多个公司编码查询公司
     * @param companyName
     * @return Company
     * @throws Exception
     */
    List<Company> getCompanyByNOs(String companyNos) throws Exception;
    
    /**
     * 根据公司编号查询公司信息
     * @param companyNo
     * @return company
     */
    Company getByNo(@Param("companyNo") String companyNo) throws Exception;
    
    
    /**
     * 根据公司ID查询公司
     * @param companyName
     * @return Company
     * @throws Exception
     */
    Company getById(Integer companyId) throws Exception;
    		
    /**
     * 公司营业执照重复验证
     * @param businessLicenseNo
     * @return List<Company>
     * @throws Exception
     */
    List<Company> checkBusinessLicenseNo(String businessLicenseNo) throws Exception;

	/**
	 * 
	 * @param companyNo 公司编号
	 * @return
	 */
	int updateFangyouOpenStatus(String companyNo) throws Exception;
	
	/**
	 * 根据名称查询
	 * @param queryParam
	 * @return
	 */
	public List<Company> queryCompanyName(Map<String, Object> queryParam);

    /**
     * 微信端调用
     * @param queryParam
     * @return
     * @throws Exception
     */
    List<Company> checkBusinessLicenseNoWX(Map<String, Object> queryParam) throws Exception;
	
}