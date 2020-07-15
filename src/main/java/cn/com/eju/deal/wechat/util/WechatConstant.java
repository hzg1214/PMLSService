package cn.com.eju.deal.wechat.util;

import cn.com.eju.deal.base.support.SystemParam;

/**
 * 微信相关常量定义
 * @author wenhui.zhang 
 * date: 2017年4月13日 下午5:34:39
 */
public interface WechatConstant {
	// CorpId 企业号的标识
	public static final String CORPID = SystemParam.getWebConfigValue("wechatCorpID");
	// Secret 管理组凭证密钥
	public static final String SECRET = SystemParam.getWebConfigValue("wechatSecret");
	// 获取accessToken接口URL
	public static final String GET_TOKEN_URL = SystemParam.getWebConfigValue("wechatAccessTokenApi");
	// 发送消息接口URL
	public static final String SEND_MSG_URL = SystemParam.getWebConfigValue("wechatSendMsgUrl");
	// 文本消息类型 text
	public static final String TEXT_SMG_TYPE = "19201";
	// 图文消息类型 news
	public static final String NEWS_SMG_TYPE = "19206";
	// 发送内容的编码
	public static final String ENCODING = "UTF-8";
}
