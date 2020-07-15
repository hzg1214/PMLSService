package cn.com.eju.deal.followDetail.dao;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.dto.followDetail.FollowDetailDto;
import cn.com.eju.deal.model.sweepStreets.WXPictureDto;

import java.util.List;
import java.util.Map;

public interface FollowDetailMapper extends IDao<FollowDetailDto> {

    List<FollowDetailDto> query(Map<String, Object> param);

    List<FollowDetailDto> getMapInfo(Map<String, Object> param);

    List<WXPictureDto> getFollowPictureList(Map<String, Object> param);

    List<WXPictureDto> getDecorationPictureList(Map<String, Object> param);
}