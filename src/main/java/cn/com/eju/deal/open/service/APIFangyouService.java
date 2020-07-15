package cn.com.eju.deal.open.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.store.model.Store;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
* APIExchangeService
* @author wenhui.zhang
* @date 2016年6月23日 下午4:23:45
*/
@Service("apiFangyouService")
public class APIFangyouService extends BaseService {
    
    @Resource
    private CompanyMapper companyMapper;
    
    @Resource
    private StoreMapper storeMapper;


    
    /**
     * 根据房友公司Id获取门店信息
     * @param fangyouCompanyId
     * @return StoreDto
     * @throws Exception 
     */
    public List<StoreDto> getStoreInfoByFyCompanyId(String fangyouCompanyId) throws Exception {
        List<StoreDto> storeDtoList = new ArrayList<StoreDto>();
        Company company = companyMapper.getCompanyByFyCompanyId(fangyouCompanyId);
        if (null != company) {
            Map<String,Object> paramsMap = new HashMap<String,Object>();
            paramsMap.put("companyId", company.getId());
            List<Store> storeList = storeMapper.getStoreListByCmIdForApi(paramsMap);
            for(Store store: storeList) {
                StoreDto storeDto = new StoreDto();
                BeanUtils.copyProperties(store, storeDto);
                storeDtoList.add(storeDto);
            }
        }
        return storeDtoList;
    }

}
