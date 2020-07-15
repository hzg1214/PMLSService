package cn.com.eju.pmls.rptStore.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.pmls.rptStore.dao.RptStoreDetailMapper;
import cn.com.eju.pmls.rptStore.dto.RptStoreDetailDto;

@Service("rptStoreDetailService")
public class RptStoreDetailService extends BaseService {
	
    @Resource
    private RptStoreDetailMapper rptStoreDetailMapper;
    
    /**
     * desc:门店明细列表
     * 2020年6月17日
     */
    public  List<RptStoreDetailDto> queryStoreDetailList(Map<String, Object> queryParam) {
        return rptStoreDetailMapper.queryStoreDetailListNew(queryParam);  
    }
    
}
