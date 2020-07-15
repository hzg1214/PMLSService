package cn.com.eju.deal.Report.service;

import java.util.List;
import java.util.Map;
import cn.com.eju.deal.user.model.Group;

public interface ReportIGroupService {

    
	/**
	 * 根据Typeid和cityID获取事业部
	 * @param map  typeId=7 事业部   
	 * @return
	 */
    List<Group> selectAllGroupByTypeIdAndCityId(Map<String,Object> map);
    
    /**
     * 根据Typeid和OrgId获取部门和小组
     * @param map typeId=8 部门 typeId=4 小组
     * @return
     */
    List<Group> selectAllGroupByOrgIdAndTypeId(Map<String,Object> map);
    /**
     * 根据groupId查询
     * @param map 
     * @return
     */
    List<Group> selectGroupByOrgId(Map<String,Object> map);
}
