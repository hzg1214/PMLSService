package cn.com.eju.deal.fangyou.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.dto.op.OpCompanyCreditCodeDto;
import cn.com.eju.deal.dto.op.OpCompanyDto;
import cn.com.eju.deal.fangyou.model.FangyouAccount;
import cn.com.eju.deal.fangyou.model.FangyouAccountLog;

public interface FangyouAccountMapper  extends IDao<FangyouAccount> {

    List<FangyouAccount> queryList(Integer storeId);
    
    FangyouAccount getById(Integer storeId);
    
    void createAccount(Map<String ,Object> map);
    
    void updateAccount(Map<String ,Object> map);
    
    Map<String,Object> getOPByStoreId(Integer storeId);

    OpCompanyDto getOPCompanyById(Integer companyId);
    OpCompanyCreditCodeDto getOPCompanyCreditCodeById(Integer companyId);

    List<FangyouAccountLog> getFangyouAccountList(Integer storeId);
    int insertfangyouAccountLog(FangyouAccountLog fangyouAccountLog);   
}
