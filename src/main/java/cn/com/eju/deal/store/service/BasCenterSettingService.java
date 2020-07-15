package cn.com.eju.deal.store.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.store.dao.BasCenterSettingMapper;
import cn.com.eju.deal.store.model.BasCenterSetting;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class BasCenterSettingService extends BaseService<BasCenterSetting> {

    @Resource
    private BasCenterSettingMapper basCenterSettingMapper;
    
    /**
     * 根据合同编号(合同里的centerId)查询
     * @param contractNo
     * @return
     */
    public BasCenterSetting selectByContractNo(String contractNo) {
        Map map = new HashMap();
        map.put("contractNo", contractNo);
        return basCenterSettingMapper.selectByContractNo(map);
    }

    /**
     * 根据门店编号(门店里的centerId)查询
     * @param storeNo
     * @return
     */
    public BasCenterSetting selectByStoreNo(String storeNo) {
        Map map = new HashMap();
        map.put("storeNo", storeNo);
        return basCenterSettingMapper.selectByStoreNo(map);
    }
}
