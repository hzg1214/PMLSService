package cn.com.eju.deal.user.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.user.model.FlowModule;
import cn.com.eju.deal.user.model.UserOrderCount;
import cn.com.eju.deal.user.model.UserOrderDist;

public interface UserOrderMapper extends IDao<FlowModule>
{
    UserOrderCount getBySignedUserId(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
        @Param("userId") int userId, @Param("exchangeCenterId") int exchangeCenterId) throws Exception;
    
    UserOrderCount getByLoanUserId(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
        @Param("userId") int userId, @Param("exchangeCenterId") int exchangeCenterId) throws Exception;
    
    UserOrderCount getByPermitUserId(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
        @Param("userId") int userId, @Param("exchangeCenterId") int exchangeCenterId) throws Exception;
    
    List<UserOrderDist> getSignedByExchangeCenterId(int exchangeCenterId) throws Exception;
    
    List<UserOrderDist> getLoanByExchangeCenterId(int exchangeCenterId) throws Exception;
    
    List<UserOrderDist> getPermitByExchangeCenterId(int exchangeCenterId) throws Exception;
    
    //----Upd Start By Guowei 2015/12/17  Start  For product error---
    /*  List<UserOrderDist> getByPostCodeAndExCenterId(@Param("exchangeCenterId") int exchangeCenterId,
        @Param("PostTypeCode") String PostTypeCode);*/
    List<UserOrderDist> getByPostCodeAndExCenterId(Map<String, Object> map) throws Exception;
    //----Upd Start By Guowei 2015/12/17  End  For product error---
}
