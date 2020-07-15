package cn.com.eju.deal.store.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.store.dao.BasOaSupplierMapper;
import cn.com.eju.deal.store.model.BasOaSupplier;

@Service
public class BasOaSupplierService extends BaseService<BasOaSupplier> {
    
    @Resource
    private BasOaSupplierMapper basOaSupplierMapper;

    /**
     * 根据合同编号(合同里的registrId)查询
     * @param contractNo
     * @return
     */
    public BasOaSupplier selectByContractNo(String contractNo) {
        Map map = new HashMap();
        map.put("contractNo", contractNo);
        return basOaSupplierMapper.selectByContractNo(map);
    }
}
