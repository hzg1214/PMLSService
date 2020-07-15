package cn.com.eju.deal.wechat.msgmodel.news;

import cn.com.eju.deal.wechat.msgmodel.BaseMessage;

/**
 * 图文消息模型
 * @author wenhui.zhang
 * date: 2017年4月10日 下午4:18:06
 */
public class NewsMessage extends BaseMessage {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	private News news;

	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}

}
