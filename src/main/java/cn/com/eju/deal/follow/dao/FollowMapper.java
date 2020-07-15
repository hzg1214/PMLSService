package cn.com.eju.deal.follow.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.follow.model.Follow;

public interface FollowMapper extends IDao<Follow> {
    List<Follow> getStoreFollows(Integer userCreate)throws Exception;
    List<Follow> getMyFollows(Map<?, ?> param)throws Exception;
}