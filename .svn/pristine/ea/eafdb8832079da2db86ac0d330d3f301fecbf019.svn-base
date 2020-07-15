package cn.com.eju.deal.Report.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.user.dao.GroupMapper;
import cn.com.eju.deal.user.model.Group;

@Service("groupService")
public class GroupServiceImpl implements ReportIGroupService {

	@Resource
    private GroupMapper groupDao;
	
	//*******加载组******************
	//@Override
	public List<Group> selectAllGroupByTypeIdAndCityId(Map<String, Object> map) {
		// TODO Auto-generated method stub
	    
	    // 不进行拦截Sql处理
        map.put("sqlUnControl", false);
        
		return groupDao.selectAllGroupByTypeIdAndCityId(map);
	}

	//选择事业部查询部门
	@Override
	public List<Group> selectAllGroupByOrgIdAndTypeId(Map<String, Object> map) {
		// TODO Auto-generated method stub
	    
	    // 不进行拦截Sql处理
        map.put("sqlUnControl", false);
	    
		return groupDao.selectAllGroupByOrgIdAndTypeId(map);
	}

	@Override
    public List<Group> selectGroupByOrgId(Map<String,Object> map) {
        // TODO Auto-generated method stub
	    
	    // 不进行拦截Sql处理
        map.put("sqlUnControl", false);
	    
        return groupDao.selectGroupByOrgId(map);
    }
	
	
	
}
