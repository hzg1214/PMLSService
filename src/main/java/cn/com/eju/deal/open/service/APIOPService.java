package cn.com.eju.deal.open.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.contacts.dao.ContactsMapper;
import cn.com.eju.deal.open.model.OPCompanyDto;
import cn.com.eju.deal.store.dao.StoreMapper;

/**   
* APIOPService
* @author huan.zhang
* @date 2016年10月27日 下午2:51:45
*/
@Service("apiOPService")
public class APIOPService extends BaseService<Object> {
    
    @Resource
    private CompanyMapper companyMapper;
    
    @Resource
    private StoreMapper storeMapper;
    
    @Resource
    private ContactsMapper contactsMapper;
    
    /**
     * 根据公司名称获取公司信息
     * @param companyName
     * @return CompanyInfoDto
     * @throws Exception 
     */
    public List<OPCompanyDto> getCompanyInfo(String companyNos) throws Exception {
        List<OPCompanyDto> companyInfoList = new ArrayList<OPCompanyDto>();
        // 公司信息
        List<Company> companyList = companyMapper.getCompanyByNOs(companyNos);
        if (null != companyList && !companyList.isEmpty()) {
            for (Company company : companyList) {
                // 公司信息
                OPCompanyDto companyInfoDto = new OPCompanyDto();
                BeanUtils.copyProperties(company, companyInfoDto);
                companyInfoList.add(companyInfoDto);
            }
        }
        return companyInfoList;
    }
    
}
