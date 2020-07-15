package cn.com.eju.deal.movementdeposit.dao;

import org.springframework.stereotype.Repository;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.movementdeposit.model.MovementDeposit;

/**
 * 推送保证金DAO层
 * @author zhenggang.Huang
 * date: 2019年2月19日
 */
@Repository
public interface MovementDepositMapper extends IDao<MovementDeposit>
{
//	根据公盘合同编号查询
	MovementDeposit selectByPrimaryKey(String gpContractNo);
	
	int insert(MovementDeposit movementDeposit);
}