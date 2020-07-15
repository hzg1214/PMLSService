package cn.com.eju.deal.company.service;

import cn.com.eju.deal.company.dao.CompanyReleaseCityMapper;
import cn.com.eju.deal.company.model.CompanyReleaseCity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("companyReleaseCityService")
public class CompanyReleaseCityService {

    @Resource
    private CompanyReleaseCityMapper companyReleaseCityMapper;

    public String getReleaseCityName(Integer companyId, String accCityName) {
        List<CompanyReleaseCity> releaseCities = companyReleaseCityMapper.getByCompanyId(companyId);
        String releaseCityName = accCityName;
        for (CompanyReleaseCity item : releaseCities) {
            if(!accCityName.equals(item.getReleaseCityName())) {
                releaseCityName = releaseCityName + "、" + item.getReleaseCityName();
            }
        }
        return releaseCityName;
    }

}
