package cn.com.eju.deal.wechat.msgmodel.news;

import java.io.Serializable;
import java.util.List;

/**
 * 图文信息组
 * @author wenhui.zhang
 * date: 2017年4月10日 下午4:36:17
 */
public class News implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Article> articles;

	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
}
