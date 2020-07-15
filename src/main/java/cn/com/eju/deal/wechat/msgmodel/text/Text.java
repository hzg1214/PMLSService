package cn.com.eju.deal.wechat.msgmodel.text;

import java.io.Serializable;

/**
 * 文本消息
 * @author wenhui.zhang
 * date: 2017年4月11日 下午5:31:04
 */
public class Text implements Serializable{

	// 序列化
	private static final long serialVersionUID = 1L;
	
	// 消息内容
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
