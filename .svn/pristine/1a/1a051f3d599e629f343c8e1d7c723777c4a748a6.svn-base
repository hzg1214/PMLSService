/**
 * @Title: FollowService.java
 * @Package cn.com.eju.deal.follow.service
 * @Description: 跟进Service包
 * @author 陆海丹
 * @date 2016年3月24日 下午12:13:40
 * @version V1.0
 */
package cn.com.eju.deal.followDetail.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.followDetail.FollowDetailDto;
import cn.com.eju.deal.followDetail.dao.FollowDetailMapper;
import cn.com.eju.deal.model.sweepStreets.WXPictureDto;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("followDetailService")
public class FollowDetailService extends BaseService {

    @Autowired
    private FollowDetailMapper followDetailMapper;

    public ResultData<List<FollowDetailDto>> query(Map<String, Object> param) {
        //构建返回
        ResultData<List<FollowDetailDto>> resultData = new ResultData<>();
        //查询
        List<FollowDetailDto> list = followDetailMapper.query(param);
        // 转换
        resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);

        return resultData;
    }

    public ResultData<List<FollowDetailDto>> getMapInfo(Map<String, Object> param) {

        ResultData<List<FollowDetailDto>> resultData = new ResultData<>();
        List<FollowDetailDto> list = followDetailMapper.getMapInfo(param);
        resultData.setReturnData(list);

        return resultData;
    }

    public ResultData<FollowDetailDto> getSignDetail(Map<String, Object> param) {

        ResultData<FollowDetailDto> resultData = new ResultData<>();
        FollowDetailDto dto = JSON.parseObject(JSON.toJSONString(param), FollowDetailDto.class);
        List<WXPictureDto> followPictureList = followDetailMapper.getFollowPictureList(param);
        List<WXPictureDto> decorationPictureList = followDetailMapper.getDecorationPictureList(param);

        dto.setFollowPictureList(followPictureList);
        dto.setDecorationPictureList(decorationPictureList);
        resultData.setReturnData(dto);

        return resultData;
    }
}
