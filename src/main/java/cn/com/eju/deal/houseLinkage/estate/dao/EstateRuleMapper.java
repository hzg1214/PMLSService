package cn.com.eju.deal.houseLinkage.estate.dao;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.frameContract.model.FrameContractDto;
import cn.com.eju.deal.frameContract.model.OaLnkFrameContractFileReturnDto;
import cn.com.eju.deal.houseLinkage.estate.model.EstateDyRecord;
import cn.com.eju.deal.houseLinkage.estate.model.EstateContractDto;
import cn.com.eju.deal.houseLinkage.estate.model.EstateRule;
import cn.com.eju.deal.houseLinkage.estate.model.EstateRuleHisDto;

import java.util.List;
import java.util.Map;

public interface EstateRuleMapper extends IDao<EstateRule> {

	List<EstateRule> selectEstateRuleInfo(String estateId) throws Exception;

	List<EstateRuleHisDto> queryHtRecord(Map<?, ?> param) throws Exception;
//	List<EstateRuleHisDto> queryListHistory(Map<?, ?> param) throws Exception;
//	List<EstateRuleHisDto> queryListNow(Map<?, ?> param) throws Exception;
	List<EstateDyRecord> queryDyRecord(Map<?, ?> param) throws Exception;

	/**
	 * 项目收入类合同记录
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<EstateContractDto> querySrlHtRecord(Map<?, ?> param) throws Exception;

	/**
	 * 项目 进场确认函 记录
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<EstateContractDto> queryJCQRHRecord(Map<?, ?> param) throws Exception;



}