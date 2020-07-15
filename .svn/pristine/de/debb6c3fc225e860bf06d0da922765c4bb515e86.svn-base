package cn.com.eju.deal.api.store.service;

import cn.com.eju.deal.api.store.dto.APPStoreDto;
import cn.com.eju.deal.store.dao.StoreMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by haidan on 2018/3/13.
 */
@Service("apiStoreService")
public class APIStoreService {
    @Resource
    private StoreMapper storeMapper;

    /**
     * 根据房友APP提供的 公司编号信息，返回合作门店信息（合同审核通过）
     * @param queryParam
     * @return
     * @throws Exception
     */
    public List<APPStoreDto> getStoresByCompanyNo(Map<String, Object> queryParam) throws Exception{
        List<APPStoreDto> storeDtos = storeMapper.getStoresByCompanyNoForApp(queryParam);
        return storeDtos;
    }

    public APPStoreDto getStoreDetailByNo(Map<String, Object> queryParam) throws Exception{
        APPStoreDto store = storeMapper.getStoreDetailByNoForApp(queryParam);
        return store;
    }

    public List<APPStoreDto> getStoresByCompanyNoGp(Map<String, Object> queryParam) throws Exception{
        List<APPStoreDto> storeDtos = storeMapper.getStoresByCompanyNoGpForApp(queryParam);
        return storeDtos;
    }
}
