package cn.com.eju.deal.wechat.quartz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.wechat.model.MsgData;
import cn.com.eju.deal.wechat.service.MsgDataService;
import cn.com.eju.deal.wechat.service.WechatSendService;

/**
 * 发送微信 定时任务
 * 
 * @author wenhui.zhang 
 * date: 2017年4月11日 下午6:17:57
 */
@Component("wechatSendTimeTask")
public class WechatSendTimeTask {

	/**
	 * 日志
	 */
	private final LogHelper logger = LogHelper.getLogger(this.getClass());

	// 微信数据Service
	@Resource
	private MsgDataService msgDataService;

	// 微信发送Service
	@Resource
	private WechatSendService WechatSendService;

	/**
	 * 定时任务方法
	 */
	public void doTask() {
		try {
			// 获取微信数据
			List<MsgData> msgDataList = msgDataService.queryUnsendMsgData();
			// 发送微信
			if (null != msgDataList && !msgDataList.isEmpty()) {
				for (MsgData msgData : msgDataList) {
					Boolean isSucess = WechatSendService.sendMsg(msgData);
					if(isSucess){
						msgDataService.updateMsgToSended(msgData.getId());
					}
				}
			}
		} catch (Exception e) {
			logger.error("weixin", "WechatSendTimeTask", "doTask", "", null, "", "", e);
		}
	}
}
