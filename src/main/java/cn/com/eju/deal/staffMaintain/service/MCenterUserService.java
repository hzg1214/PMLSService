package cn.com.eju.deal.staffMaintain.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.dao.CityMapper;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.staffMaintain.MCenterUserDto;
import cn.com.eju.deal.staffMaintain.dao.MCenterUserMapper;
import cn.com.eju.deal.staffMaintain.model.MCenterUser;
import cn.com.eju.deal.user.dao.GroupMapper;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.Group;
import cn.com.eju.deal.user.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("mCenterUserService")
public class MCenterUserService {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private MCenterUserMapper mCenterUserMapper;
    @Resource
    private UserMapper userMapper;

    @Resource
    private GroupMapper groupMapper;

    @Resource
    private CityMapper cityMapper;

    /**
     * desc:列表
     * 2019年5月17日
     * author:zhenggang.Huang
     */
    public ResultData<List<Map<String, Object>>> queryList(Map<String, Object> queryParam)
            throws Exception {
        ResultData<List<Map<String, Object> >> resultData = new ResultData<>();
        if(queryParam.containsKey("centerIds") && !StringUtils.isEmpty(queryParam.get("centerIds"))) {
        	List<String> centerIdList = (List<String>) queryParam.get("centerIds");
        	String centerIds = "";
        	for (String integer : centerIdList) {
        		centerIds +=integer+",";
        	}
        	queryParam.put("centerIds", centerIds+0);
        }
        List<Map<String, Object> > yfCenterUserDtos = mCenterUserMapper.queryList(queryParam);

        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(yfCenterUserDtos);

        return resultData;
    }

    /**
     * desc:编辑-根据id查询
     * 2019年5月17日
     * author:zhenggang.Huang
     */
    public ResultData<MCenterUser> getById(int id)
            throws Exception {
        //构建返回
        ResultData<MCenterUser> resultData = new ResultData<>();
        //查询详情
        MCenterUser mCenterUser = mCenterUserMapper.getById(id);

        resultData.setReturnData(mCenterUser);

        return resultData;
    }

    /**
     * desc:新增保存
     * 2019年5月17日
     * author:zhenggang.Huang
     */
    public ResultData<MCenterUserDto> save(MCenterUserDto mCenterUserDto)
            throws Exception {

        //构建返回
        ResultData<MCenterUserDto> resultData = new ResultData<MCenterUserDto>();
        MCenterUser mCenterUser = getMCenterUser(mCenterUserDto);
        List<String> centerIds = mCenterUserDto.getCenterIds();
        //赋值
//        BeanUtils.copyProperties(mCenterUserDto, mCenterUser);
        
        //保存时须与UMS进行校验
        if (!this.checkSaveWithUMS(mCenterUser)) {
        	resultData.setFail("员工工号和员工姓名不一致，请重新填写！");
        	resultData.setReturnCode("201");
        	return resultData;
        }
        if(centerIds != null && centerIds.size() > 0) {
        	for (String centerId : centerIds) {
        		mCenterUser.setCenterId(Integer.parseInt(centerId));
        		setMCenterUser(mCenterUser);
        		// 员工信息不允许重复提交, flag：1-新增，2-修改
        		if (this.checkSave(mCenterUser, 1)) {
        			int count = mCenterUserMapper.insert(mCenterUser);
        		}
			}
        }

        return resultData;
    }

    /**
     * desc:组装entity
     * 2019年5月17日
     * author:zhenggang.Huang
     */
    private MCenterUser getMCenterUser(MCenterUserDto mCenterUserDto) {
    	MCenterUser mCenterUser = new MCenterUser();
        mCenterUser.setUserId(mCenterUserDto.getUserId());
        mCenterUser.setCityNo(mCenterUserDto.getCityNo());
        mCenterUser.setUserCode(mCenterUserDto.getUserCode());
        mCenterUser.setUserIdCreate(mCenterUserDto.getUserIdCreate());
        mCenterUser.setUserName(mCenterUserDto.getUserName());
        return mCenterUser;
	}

    /**
     * desc:判断用户工号是否和名字匹配
     * 2019年5月17日
     * author:zhenggang.Huang
     */
	private boolean checkSaveWithUMS(MCenterUser yfCenterUser) {

        // 取得用户信息
        User user = userMapper.getUserByCode(yfCenterUser.getUserCode());

        // 如果没有取到用户，或者用户的名称不一致
        if (user == null || user.getUserName() == null || !user.getUserName().equals(yfCenterUser.getUserName())) {
            return false;
        }
        yfCenterUser.setUserId(user.getUserId());
        return true;
    }

    /**
     * desc:保存check 
     * 2019年5月17日
     * author:zhenggang.Huang
     */
    private boolean checkSave(MCenterUser yfCenterUser, int flag) {
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", yfCenterUser.getUserCode());
        param.put("cityNo", yfCenterUser.getCityNo());
        //根据工号和城市编号查询该员工已有的中心
        List<MCenterUser> mCenterUsers = mCenterUserMapper.getByUserCode(param);
        if (mCenterUsers != null && mCenterUsers.size() > 0) {
        	for (MCenterUser mCenterUser : mCenterUsers) {
        		if(flag == 1) {
        			if (mCenterUser != null && mCenterUser.getUserCode().equals(yfCenterUser.getUserCode()) 
        					&& mCenterUser.getCenterId().equals(yfCenterUser.getCenterId())
        					&& mCenterUser.getCityNo().equals(yfCenterUser.getCityNo())) {
        				return false;
        			}
        		}else if(flag == 2) {
    				if(mCenterUser != null && mCenterUser.getUserCode().equals(yfCenterUser.getUserCode())
    						&& mCenterUser.getCenterId().equals(yfCenterUser.getCenterId())
    								&& mCenterUser.getCityNo().equals(yfCenterUser.getCityNo())) {//重复
    					return false;
    				}
    			}
			}
        }

        return true;
    }

    /**
     * desc:根据中心id获取中心名称
     * 2019年5月17日
     * author:zhenggang.Huang
     */
    private void setMCenterUser(MCenterUser mCenterUser) throws Exception {
        Group group = groupMapper.selectByGroupId(mCenterUser.getCenterId());
        mCenterUser.setCenterName(group.getGroupName());

    }

    /**
     * desc:编辑保存
     * 2019年5月17日
     * author:zhenggang.Huang
     */
    public ResultData<MCenterUserDto> update(MCenterUserDto mCenterUserDto)
            throws Exception {

        //构建返回
        ResultData<MCenterUserDto> resultData = new ResultData<>();
        MCenterUser mCenterUser = new MCenterUser();
        //赋值
        BeanUtils.copyProperties(mCenterUserDto, mCenterUser);

        //保存时须与UMS进行校验
        if (!this.checkSaveWithUMS(mCenterUser)) {
            resultData.setFail("员工工号和员工姓名不一致，请重新填写！");
            resultData.setReturnCode("201");
            return resultData;
        }
        setMCenterUser(mCenterUser);
        // 员工信息不允许重复提交
        if (!this.checkSave(mCenterUser, 2)) {
        	resultData.setFail("该员工信息已维护，请勿重复提交！");
        	resultData.setReturnCode("202");
        	return resultData;
        }

        // 更新处理
        int count = mCenterUserMapper.update(mCenterUser);
        if (count < 0) {
            resultData.setFail();
        } else {
            resultData.setReturnData(mCenterUserDto);
        }
        return resultData;
    }

    /**
     * desc:逻辑删除
     * 2019年5月17日
     * author:zhenggang.Huang
     */
    public ResultData delete(MCenterUserDto mCenterUserDto) throws Exception {
        ResultData<?> resultData = new ResultData<>();
        // 删除处理
        int count = mCenterUserMapper.delete(mCenterUserDto.getId());
        if (count < 0) {
            resultData.setFail();
        }
        return resultData;
    }

    public ResultData getManagerCheck(Map<String, Object> queryParam) {
        ResultData<Integer> resultData = new ResultData<>();
        Integer relCount = 0;
        List<MCenterUser> mCenterUsers = mCenterUserMapper.getByUserCode(queryParam);
        if (mCenterUsers != null && mCenterUsers.size() > 0) {
            relCount = mCenterUsers.size();
        }
        resultData.setReturnData(relCount);
        return resultData;
    }
}
