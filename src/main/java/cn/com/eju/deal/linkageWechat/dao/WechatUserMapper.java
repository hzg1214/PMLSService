package cn.com.eju.deal.linkageWechat.dao;

import cn.com.eju.deal.linkageWechat.model.WechatUser;

/**
 * 微信消息接口
 * @author len
 *
 */
public interface WechatUserMapper {
    /**
     * 更新微信用户
     * @param wechatUser
     * @return
     */
    int updateWechatUser(WechatUser wechatUser) throws Exception;

    /**
     * 查询微信用户
     * @param wechatId
     * @return
     */
    WechatUser getWechatUserByWechatId(String wechatId) throws Exception;
    
    /**
     * 新增微信用户
     * @param wechatUser
     * @return
     */
    int saveWechatUser(WechatUser wechatUser) throws Exception;
	
}