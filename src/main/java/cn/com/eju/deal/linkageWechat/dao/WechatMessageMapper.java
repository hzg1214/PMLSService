package cn.com.eju.deal.linkageWechat.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.linkageWechat.model.WechatMessage;
import cn.com.eju.deal.linkageWechat.model.WechatUser;

/**
 * 微信消息接口
 * @author len
 *
 */
public interface WechatMessageMapper {
	/**
	 * 获取已保存的微信消息记录
	 * @param map
	 * @return
	 */
	List<WechatMessage> getWechatMessageByParam(Map<String, Object> map) throws Exception;
	/**
	 * 新增微信消息
	 * @param wechatMessage
	 * @return
	 */
    int saveWechatMessage(WechatMessage wechatMessage) throws Exception;
    /**
     * 新增微信用户
     * @param wechatUser
     * @return
     */
    int saveWechatUser(WechatUser wechatUser) throws Exception;
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
//    
//    /**
//     * 查询token
//     * @param tokenCode
//     * @return
//     */
//    WechatAccessToken getWechatAccessTokenByCode(String tokenCode);
//    /**
//     * 更新token
//     * @param wechatAccessToken
//     * @return
//     */
//    int updateWechatAccessToken(WechatAccessToken wechatAccessToken);
//    /**
//     * 新增token
//     * @param wechatAccessToken
//     * @return
//     */
//    int saveWechatAccessToken(WechatAccessToken wechatAccessToken);
    /**
	 * 根据手机号查询微信ID
	 * @param mobile
	 * @return
	 */
	String getWechatIdByMobile(String mobile);
	
}