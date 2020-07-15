package cn.com.eju.deal.user.api.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.user.api.IRoleAPI;
import cn.com.eju.deal.user.dao.RoleMapper;
import cn.com.eju.deal.user.model.Role;

@Service("roleAPI")
public class RoleAPIImpl implements IRoleAPI
{
    @Resource
    private RoleMapper roleDao;
    
    /**
     * 根据用户id获取用户角色
    * TODO (这里用一句话描述这个方法的作用)
    * @param operateUserId
    * @param userId
    * @return
     */
    public ResultData<List<Role>> getRoleByUserId(int operateUserId, int userId)
    {
        
        ResultData<List<Role>> roleResult = new ResultData<List<Role>>();
        try
        {
            
            List<Role> list = this.roleDao.selectByUserId(userId);
            if (list != null)
            {
                roleResult.setReturnCode("200");
                roleResult.setReturnMsg("调用成功");
                roleResult.setReturnData(list);
            }
            else
            {
                roleResult.setReturnCode("-1");
                roleResult.setReturnMsg("未找到数据");
            }
        }
        catch (Exception e)
        {
            roleResult.setReturnCode("0");
            roleResult.setReturnMsg("调用出错");
        }
        return roleResult;
    }
}
