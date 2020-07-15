package cn.com.eju.deal.Signed.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.Report.model.UserCenterAuthDto;
import cn.com.eju.deal.Signed.model.SignDetail;

public interface SignDetailMapper {
	
	 /**
     * 根据Id查询合同信息
    * @param contractNo
    * @return
     */
    List<SignDetail> getById(Map<String, Object> reqMap) throws Exception;
    
    /**
     * 根据cityId查询中心
     * @param map
     * @return
     * @throws Exception
     */
    List<UserCenterAuthDto> getCenterGroupName(Map<String, Object> map) throws Exception;

}
