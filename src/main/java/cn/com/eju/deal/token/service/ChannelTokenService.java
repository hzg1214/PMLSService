package cn.com.eju.deal.token.service;

import cn.com.eju.deal.token.dao.ChannelTokenMapper;
import cn.com.eju.deal.token.model.ChannelToken;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Sky on 2016/4/20.
 * 渠道Token
 */
@Service("channelTokenService")
public class ChannelTokenService {

    @Resource
    private ChannelTokenMapper channelTokenMapper;

    /**
     * 获取渠道token信息
     *
     * @param typeId token类型
     * @return token信息
     */
    public ChannelToken getTokenByTypeId(Integer typeId)  throws Exception{
        ChannelToken channelToken = channelTokenMapper.getTokenByTypeId(typeId);
        return channelToken;
    }

    /**
     * 新增token
     *
     * @param channelToken token信息
     * @return 影响行数
     */
    public int addToken(ChannelToken channelToken) {
        return this.channelTokenMapper.create(channelToken);
    }
}
