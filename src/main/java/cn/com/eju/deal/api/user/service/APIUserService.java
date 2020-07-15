package cn.com.eju.deal.api.user.service;

import cn.com.eju.deal.api.user.dto.APPUserDto;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.user.dao.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by haidan on 2018/3/14.
 */
@Service("apiUserService")
public class APIUserService {
    @Resource
    private UserMapper userMapper;

    /**
     *根据房友APP传入的经服人员工号或手机号码
     * @param param
     * @return
     * @throws Exception
     */
    public ResultData<List<APPUserDto>> getUserForFYAPP(Map<?, ?> param) throws Exception{
        ResultData<List<APPUserDto>> resultData = new ResultData<>();
        List<APPUserDto> userList = userMapper.getUserForFYAPP(param);
        resultData.setReturnData(userList);
        if(userList!=null && userList.size()>0){
            resultData.setTotalCount(String.valueOf(userList.size()));
        }
        else{
            resultData.setTotalCount("0");
        }
        return resultData;
    }
}
