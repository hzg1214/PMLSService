package cn.com.eju.deal.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.user.model.Group;

public interface GroupMapper extends IDao<Group>
{
    int deleteByPrimaryKey(Integer groupId) throws Exception;
    
    int insert(Group record) throws Exception;
    
    int insertSelective(Group record) throws Exception;
    
    Group selectByPrimaryKey(Integer groupId) throws Exception;
    
    int updateByPrimaryKeySelective(Group record) throws Exception;
    
    int updateByPrimaryKeyWithBLOBs(Group record) throws Exception;
    
    int updateByPrimaryKey(Group record) throws Exception;
    
    List<Group> selectByParentId(Integer parentId) throws Exception;
    
    Group selectByGroupName(String groupName) throws Exception;
    
    List<Group> list() throws Exception;
    
    List<Group> selectAllParentByPostId(Integer postId) throws Exception;
    
    List<Group> selectAllGroupByTypeId() throws Exception;
    
    List<Group> selectAllParentByGroupId(Integer groupId) throws Exception;
    
    List<Group> selectAllChildrenByGroupId(Integer groupId) throws Exception;
    
    Group selectByGroupId(Integer parentId) throws Exception;
    
    List<Group> selectGroupByTypeId(Integer typeId) throws Exception;

    /**
     * 
     * @Title: getOrgIdByPostId
     * @Description: 查询orgId
     * @return
     * @throws Exception
     */
    Group getOrgIdByPostId(Integer postId);
    
    /**
     * 根据用户Code查询用户组
     * @param param userCode
     * @return
     * @throws Exception
     */
    Group selectByUserCode(Map<String, Object> param) throws Exception;
    
    /**
     * 根据人找中心
     * @param userCode
     * @return 中心id
     */
    Integer getCenterByUser(@Param("userCode") String userCode,@Param("typeCode") String typeCode);
    
    /**
     * 根据人找中心
     * @param userCode
     * @return 中心id
     */
    String getCenterNameByUser(@Param("userCode") String userCode,@Param("typeCode") String typeCode);
    
    /**
     * 业绩归属-联动的组信息
     * @return map
     */
    Map<String,String> getAchieveLinkGroupInfo(Map<String,String> param);

    //Add By tong 2017/04/07 报表用 Start
    List<Group> selectAllGroupByTypeIdAndCityId(Map<String,Object> map);
    
    List<Group> selectAllGroupByOrgIdAndTypeId(Map<String,Object> map);
    
    List<Group> selectGroupByOrgId(Map<String,Object> map);
   //Add By tong 2017/04/07 End
    
    String getCityNoByGroupId(@Param("groupId") Integer groupId);
}