package cn.com.eju.deal.wechat.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.token.model.ChannelToken;
import cn.com.eju.deal.token.service.ChannelTokenService;
import cn.com.eju.deal.wechat.model.MsgData;
import cn.com.eju.deal.wechat.model.MsgDataDetail;
import cn.com.eju.deal.wechat.msgmodel.news.Article;
import cn.com.eju.deal.wechat.msgmodel.news.News;
import cn.com.eju.deal.wechat.msgmodel.news.NewsMessage;
import cn.com.eju.deal.wechat.msgmodel.text.Text;
import cn.com.eju.deal.wechat.msgmodel.text.TextMessage;
import cn.com.eju.deal.wechat.util.WechatConstant;
import cn.com.eju.deal.wechat.util.WechatSendUtil;

/**
 * 发送微信服务
 * @author wenhui.zhang 
 * date: 2017年4月7日 上午10:38:42
 */
@Service("wechatSendService")
public class WechatSendService {
	@Resource
	private ChannelTokenService channelTokenService;

	/**
	 * 发送消息
	 * @return 发送消息是否成功
	 * @throws Exception
	 */
	public boolean sendMsg(MsgData msgData) throws Exception {
		boolean isSucess = false;
		String msgType = msgData.getMsgtype();
		// 文本消息
		if(WechatConstant.TEXT_SMG_TYPE.equals(msgType)){
			return this.sendTextMsg(msgData);
		}
		if(WechatConstant.NEWS_SMG_TYPE.equals(msgType)){
			return this.sendNewsMsg(msgData);
		}
		return isSucess;
	}
	
	/**
	 * 发送文本消息
	 * @return 发送消息是否成功
	 * @throws Exception
	 */
	public boolean sendTextMsg(MsgData msgData) throws Exception {
		boolean isSucess = false;
		// 构造发送数据
		String content = buildTextMsg(msgData);
		// 获取Token
		String accessToken = this.getAccessToken(WechatConstant.CORPID, WechatConstant.SECRET);
		// 发送消息
		String url = String.format("%s?access_token=%s", WechatConstant.SEND_MSG_URL, accessToken);
		String result = WechatSendUtil.sendPost(url, content);
		JSONObject jsonObject = JSONObject.parseObject(result);
		int size = jsonObject.size();
		// 发送消息成功
		if (size == 2 && (Integer.valueOf(jsonObject.get("errcode").toString()) == 0) && (jsonObject.get("errmsg").equals("ok"))) {
			isSucess = true;
		}
		return isSucess;
	}
	
	/**
	 * 发送图文消息
	 * @return 发送消息是否成功
	 * @throws Exception
	 */
	public boolean sendNewsMsg(MsgData msgData) throws Exception {
		boolean isSucess = false;
		// 构造发送数据
		String content = buildNewsMsg(msgData);
		// 获取Token
		String accessToken = this.getAccessToken(WechatConstant.CORPID, WechatConstant.SECRET);
		// 发送消息
		String url = String.format("%s?access_token=%s", WechatConstant.SEND_MSG_URL, accessToken);
		String result = WechatSendUtil.sendPost(url, content);
		JSONObject jsonObject = JSONObject.parseObject(result);
		int size = jsonObject.keySet().size();
		// 发送消息成功
		if (size == 2 && (Integer.valueOf(jsonObject.get("errcode").toString()) == 0) && (jsonObject.get("errmsg").equals("ok"))) {
			isSucess = true;
		}
		return isSucess;
	}

	/**
	 * 获取微信的AccessToken
	 * @param corpId 企业编号
	 * @param secret 企业管理组密钥
	 * @return token信息
	 */
	public String getAccessToken(String corpId, String secret) throws Exception {
		String accessToken = "";
		// 本地数据库里的token
		String localToken = this.getLocalToken(12701);
		if (!StringUtil.isEmpty(localToken)) {
			accessToken = localToken;
		} else {
			// 调用接口生成token
			accessToken = getNewAccessToken(corpId, secret);
		}
		return accessToken;
	}

	/**
	 * 获取数据库里的token
	 * @param typeId
	 * @throws Exception
	 */
	private String getLocalToken(Integer typeId) throws Exception {
		ChannelToken channelToken = channelTokenService.getTokenByTypeId(typeId);
		Date currentDate = new Date();
		if (channelToken.getDateExpiration().getTime() < currentDate.getTime()) {
			return "";
		}
		return channelToken.getToken();
	}

	/**
	 * 获取新的Token
	 */
	private String getNewAccessToken(String corpId, String secret) throws Exception {
		String url = String.format("%s?corpid=%s&corpsecret=%s", WechatConstant.GET_TOKEN_URL, corpId, secret);
		String newToken = WechatSendUtil.sendGet(url);
		JSONObject returnToken = JSONObject.parseObject(newToken);
		String token = returnToken.getString("access_token");

		ChannelToken channelToken = new ChannelToken();
		channelToken.setTokenType(12701);
		channelToken.setToken(token);
		Date currentDate = new Date();
		long expirationTime = currentDate.getTime() + 5000000;
		Date expirationDate = new Date(expirationTime);
		channelToken.setDateCreate(currentDate);
		channelToken.setDateExpiration(expirationDate);
		channelTokenService.addToken(channelToken);

		return token;
	}
	
	/**
	 * 构造图文消息信息结构
	 * @param msgInfo
	 * @return JSON字符串格式的图文消息
	 */
	private String buildNewsMsg(MsgData msgData) {
		NewsMessage nm = new NewsMessage();
		nm.setTouser(msgData.getToUser());
		nm.setToparty(msgData.getToParty());
		nm.setTotag(msgData.getTotag());
		nm.setMsgtype(SystemParam.getDicValueByDicCode(msgData.getMsgtype()));
		nm.setAgentid(msgData.getAgentid());
		News nws = new News();
		List<Article> articles = new ArrayList<Article>();
		List<MsgDataDetail> detailList = msgData.getMsgDataDetailList();
		if (null != detailList && !detailList.isEmpty()) {
			for (MsgDataDetail detail : detailList) {
				Article article = new Article();
				article.setTitle(detail.getTitle());
				article.setDescription(detail.getDiscription());
				article.setUrl(detail.getUrl());
				article.setPicurl(detail.getPicurl());
				articles.add(article);
			}
			nws.setArticles(articles);
			nm.setNews(nws);
		}
		String jsonData = JSON.toJSONString(nm);
		return jsonData;
	}
	
	/**
	 * 构造文本消息信息结构
	 * @param msgInfo
	 * @return JSON字符串格式的文本消息
	 */
	private String buildTextMsg(MsgData msgData){
		TextMessage nm = new TextMessage();
		nm.setTouser(msgData.getToUser());
		nm.setToparty(msgData.getToParty());
		nm.setTotag(msgData.getTotag());
		nm.setMsgtype(SystemParam.getDicValueByDicCode(msgData.getMsgtype()));
		nm.setAgentid(msgData.getAgentid());
		nm.setSafe(msgData.getSafe());
		List<MsgDataDetail> detailList = msgData.getMsgDataDetailList();
		if (null != detailList && !detailList.isEmpty()) {
			// 文本消息 只有一个 MsgDataDetail
			MsgDataDetail detail = detailList.get(0);
			Text txt = new Text();
			txt.setContent(detail.getContent());
			nm.setText(txt);
		}
		String jsonData = JSON.toJSONString(nm);
		return jsonData;
	}
}
