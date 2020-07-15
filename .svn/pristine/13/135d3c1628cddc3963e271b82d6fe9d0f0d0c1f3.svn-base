package cn.com.eju.deal.wechat.dao;

import java.util.List;

import cn.com.eju.deal.wechat.model.MsgData;

/**
 * MsgData DAO
 * @author wenhui.zhang
 * date: 2017年4月11日 下午3:59:07
 */
public interface MsgDataMapper {
	/**
	 * 查询未发送的消息
	 * @return 未发送的消息集合
	 */
	List<MsgData> queryUnsendMsgData() throws Exception;
	
	/**
	 * 更新消息 发送成功
	 * @param id
	 * @return 影响记录数
	 */
	int updateMsgToSended(Integer id)throws Exception;
}