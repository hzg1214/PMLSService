package cn.com.eju.deal.movementdeposit.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.movementdeposit.dao.MovementDepositMapper;
import cn.com.eju.deal.movementdeposit.model.MovementDeposit;

/**
 * 业绩归属人操作
 * @author wenhui.zhang
 * date: 2017年3月6日 下午4:51:40
 */
@Service
public class MovementDepositService extends BaseService<MovementDeposit> {
	
	@Resource
	private MovementDepositMapper movementDepositMapper;
	
	/**
	 * 新增推送保证金信息
	 */
	public int insert(MovementDeposit movementDeposit) {
		return movementDepositMapper.insert(movementDeposit);
	}

}
