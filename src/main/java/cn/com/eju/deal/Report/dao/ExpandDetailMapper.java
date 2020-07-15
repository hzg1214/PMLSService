package cn.com.eju.deal.Report.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.Report.model.ExpandDetail;
import cn.com.eju.deal.Report.model.UserCenterAuthDto;



public interface ExpandDetailMapper {
	
	 /**
     * 根据Id查询合同信息
    * @param contractNo
    * @return
     */
    List<ExpandDetail> getById(Map<String, Object> reqMap) throws Exception;

    /**
     * 根据条件查询合同信息
     * @param reqMap
     * @return
     */
    List<ExpandDetail> searchExpandDetail(Map<String, Object> reqMap) throws Exception;

    /**
     * 根据userId查询城市
     * @param userId
     * @return
     * @throws Exception
     */
    List<UserCenterAuthDto> getUserCenterAuthCityName(Map<String, Object> map) throws Exception;
    
    /**
     *根据userId城市Id查询区/事业部
     * @param map
     * @return
     * @throws Exception
     */
    List<UserCenterAuthDto> getAreaGroupName(Map<String, Object> map) throws Exception;
    
    /**
     * 根据登录人ID,城市ID，对应区/事业部ID 拿到对应部门/中心
     * @param map
     * @return
     * @throws Exception
     */
    List<UserCenterAuthDto> getCenterGroupName(Map<String, Object> map) throws Exception;
    
    /**
     *  根据登录人ID,城市ID，对应区/事业部ID 对应部门/中心ID查询组
     * @param map
     * @return
     * @throws Exception
     */
    List<UserCenterAuthDto> getGroupName(Map<String, Object> map) throws Exception;
	
}
