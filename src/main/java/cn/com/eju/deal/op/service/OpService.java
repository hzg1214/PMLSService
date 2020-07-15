package cn.com.eju.deal.op.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.contract.dao.StoreFangyouAccountMapper;

@Service("opService")
public class OpService extends BaseService{

    @Resource
    private CompanyMapper companyMapper;
    
    @Resource
    private StoreFangyouAccountMapper storeFangyouAccountMapper;
    
    /**
     * OP返回判定接口
     * @param companyNo
     * @return
     */
    public int opRollBackCRM(String companyNo) throws Exception{
    	int result = 0;
    	result = storeFangyouAccountMapper.updateStoreFangyouAccountByOp(companyNo);
    	result += companyMapper.updateFangyouOpenStatus(companyNo);
    	return result;
    }
}
