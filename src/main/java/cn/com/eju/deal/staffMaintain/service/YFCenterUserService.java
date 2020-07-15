package cn.com.eju.deal.staffMaintain.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.dao.CityMapper;
import cn.com.eju.deal.common.model.City;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.common.CenterDto;
import cn.com.eju.deal.dto.staffMaintain.YFCenterUserDto;
import cn.com.eju.deal.staffMaintain.dao.YFCenterUserMapper;
import cn.com.eju.deal.staffMaintain.model.YFCenterUser;
import cn.com.eju.deal.user.dao.GroupMapper;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.Group;
import cn.com.eju.deal.user.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("yfCenterUserService")
public class YFCenterUserService {


    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private YFCenterUserMapper yfCenterUserMapper;
    @Resource
    private UserMapper userMapper;

    @Resource
    private GroupMapper groupMapper;

    @Resource
    private CityMapper cityMapper;

    /*
        查询queryList
     */
    public ResultData<List<YFCenterUserDto>> queryList(Map<String, Object> queryParam)
            throws Exception {
        ResultData<List<YFCenterUserDto>> resultData = new ResultData<List<YFCenterUserDto>>();

        List<YFCenterUserDto> yfCenterUserDtos = yfCenterUserMapper.queryList(queryParam);

        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(yfCenterUserDtos);

        return resultData;
    }

    /**
     * 查询getById
     */
    public ResultData<YFCenterUserDto> getById(int id)
            throws Exception {
        //构建返回
        ResultData<YFCenterUserDto> resultData = new ResultData<YFCenterUserDto>();
        //查询详情
        YFCenterUserDto yfCenterUserDto = yfCenterUserMapper.getById(id);

        resultData.setReturnData(yfCenterUserDto);

        return resultData;
    }

    /*
      新增保存人员维护信息
     */
    public ResultData<YFCenterUserDto> save(YFCenterUserDto yfCenterUserDto)
            throws Exception {

        //构建返回
        ResultData<YFCenterUserDto> resultData = new ResultData<YFCenterUserDto>();
        YFCenterUser yfCenterUser = new YFCenterUser();
        //赋值
        BeanUtils.copyProperties(yfCenterUserDto, yfCenterUser);

        //保存时须与UMS进行校验
        if (!this.checkSaveWithUMS(yfCenterUser)) {
            resultData.setFail("员工工号和员工姓名不一致，请重新填写！");
            resultData.setReturnCode("201");
            return resultData;
        }
        // 员工信息不允许重复提交, flag：1-新增，2-修改
        if (!this.checkSave(yfCenterUser, 1)) {
            resultData.setFail("该员工信息已维护，请勿重复提交！");
            resultData.setReturnCode("202");
            return resultData;
        }
        setYFCenterUser(yfCenterUser);

        // 保存处理
        int count = yfCenterUserMapper.insert(yfCenterUser);
        if (count < 0) {
            resultData.setFail();
        } else {
            yfCenterUserDto.setId(yfCenterUser.getId());
            resultData.setReturnData(yfCenterUserDto);
        }

        return resultData;
    }

    private boolean checkSaveWithUMS(YFCenterUser yfCenterUser) {

        // 取得用户信息
        User user = userMapper.getUserByCode(yfCenterUser.getUserCode());

        // 如果没有取到用户，或者用户的名称不一致
        if (user == null || user.getUserName() == null || !user.getUserName().equals(yfCenterUser.getUserName())) {
            return false;
        }
        yfCenterUser.setUserId(user.getUserId());
        return true;
    }

    /*
        flag：1-新增，2-修改
     */
    private boolean checkSave(YFCenterUser yfCenterUser, int flag) {
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", yfCenterUser.getUserCode());
        YFCenterUserDto yfCenterUserDto = yfCenterUserMapper.getByUserCode(param);

        if (flag == 1) {
            // 新增的场合，表示重复数据
            if (yfCenterUserDto != null) {
                return false;
            }
        } else if (flag == 2) {
            // 修改的场合，id 不一致的场合表示重复数据
            if (yfCenterUserDto != null && !yfCenterUserDto.getId().equals(yfCenterUser.getId())) {
                return false;
            }
        }

        return true;
    }

    private void setYFCenterUser(YFCenterUser yfCenterUser) throws Exception {


        Group group = groupMapper.selectByGroupId(yfCenterUser.getCenterId());
        yfCenterUser.setCenterName(group.getGroupName());

    }

    /*
     更新提人员维护信息
    */
    public ResultData<YFCenterUserDto> update(YFCenterUserDto yfCenterUserDto)
            throws Exception {

        //构建返回
        ResultData<YFCenterUserDto> resultData = new ResultData<YFCenterUserDto>();
        YFCenterUser yfCenterUser = new YFCenterUser();
        //赋值
        BeanUtils.copyProperties(yfCenterUserDto, yfCenterUser);

        //保存时须与UMS进行校验
        if (!this.checkSaveWithUMS(yfCenterUser)) {
            resultData.setFail("员工工号和员工姓名不一致，请重新填写！");
            resultData.setReturnCode("201");
            return resultData;
        }
        // 员工信息不允许重复提交
        if (!this.checkSave(yfCenterUser, 2)) {
            resultData.setFail("该员工信息已维护，请勿重复提交！");
            resultData.setReturnCode("202");
            return resultData;
        }
        setYFCenterUser(yfCenterUser);

        // 更新处理
        int count = yfCenterUserMapper.update(yfCenterUser);
        if (count < 0) {
            resultData.setFail();
        } else {
            resultData.setReturnData(yfCenterUserDto);
        }
        return resultData;
    }


    public ResultData delete(YFCenterUserDto yfCenterUserDto) throws Exception {
        ResultData<?> resultData = new ResultData<>();
        // 删除处理
        int count = yfCenterUserMapper.delete(yfCenterUserDto.getId());
        if (count < 0) {
            resultData.setFail();
        }
        return resultData;
    }

    public ResultData<List<City>> getAreaCity(Map<String, Object> map) throws Exception {
        ResultData resultData = new ResultData();

        List<City> list = yfCenterUserMapper.getAreaCity(map);

        resultData.setReturnData(list);

        return resultData;
    }

    public ResultData<List<CenterDto>> getCenterGroup(Map<String, Object> map) throws Exception {
        ResultData resultData = new ResultData();

        List<CenterDto> list = yfCenterUserMapper.getCenterGroup(map);
        resultData.setReturnData(list);

        return resultData;
    }

    public ResultData<List<Map<?, ?> >> getCenterAuth(Map<String, Object> map) throws Exception {

        ResultData resultData = new ResultData();
        List<Map<?, ?> > authCenterIdList = yfCenterUserMapper.getCenterAuth(map);

        resultData.setReturnData(authCenterIdList);

        return resultData;
    }

    public ResultData<Integer> queryYfCenterUser(String userCode) {
        ResultData<Integer> resultData = new ResultData<Integer>();
        Integer count = yfCenterUserMapper.queryYfCenterUser(userCode);
        if(count==0){
            resultData.setReturnData(2);
        }else{
            resultData.setReturnData(0);
        }
        return resultData;
    }
}
