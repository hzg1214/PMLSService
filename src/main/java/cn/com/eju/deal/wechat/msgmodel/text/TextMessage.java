package cn.com.eju.deal.wechat.msgmodel.text;

import cn.com.eju.deal.wechat.msgmodel.BaseMessage;

/**
 * 文本消息
 * @author wenhui.zhang
 * date: 2017年4月11日 下午5:31:26
 */
public class TextMessage extends BaseMessage{

	// 序列化
	private static final long serialVersionUID = 1L;
	
	private Text text;

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

}
