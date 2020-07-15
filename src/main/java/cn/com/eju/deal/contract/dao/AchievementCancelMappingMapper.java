package cn.com.eju.deal.contract.dao;

import cn.com.eju.deal.contract.model.AchievementCancelMapping;
import cn.com.eju.deal.core.dao.IDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AchievementCancelMappingMapper extends IDao<AchievementCancelMapping>
{
    /**
     * 
    * 新增记录
    * @param queryParam
    * @return
     */
    int createNewRecord(Map<String, Object> queryParam)
        throws Exception;
    
    /**
     * 
    * /更新旧的撤销关系表delFlag 为 1
    * @param queryParam
    * @return
     */
    int updateOldCancelMappingRecord(Map<String, Object> queryParam);
    
    /**
     * 
    * 根据flowId查询合同撤销关系表的合同编码和门店编码【OA定时任务拉取数据用】
    * @param flowId
    * @return
     */
    List<AchievementCancelMapping> getAchievementCancelMappingsByFlowId(@Param(value="flowId") String flowId);
    
}