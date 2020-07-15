package cn.com.eju.deal.wechat.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.wechat.dao.MsgDataMapper;
import cn.com.eju.deal.wechat.model.MsgData;

/**
 * MsgData 服务层
 * @author wenhui.zhang
 * date: 2017年4月11日 下午4:46:48
 */
@Service("msgDataService")
public class MsgDataService {
	@Resource
	private MsgDataMapper msgDataMapper;
	
	/**
	 * 查询未发送的消息
	 * @return 未发送的消息集合
	 * @throws Exception 
	 */
	public List<MsgData> queryUnsendMsgData() throws Exception{
		return msgDataMapper.queryUnsendMsgData();
	}
	
	/**
	 * 更新消息 发送成功
	 * @return 影响记录数
	 * @throws Exception 
	 */
	public int updateMsgToSended(Integer id) throws Exception{
		return msgDataMapper.updateMsgToSended(id);
	}
}
