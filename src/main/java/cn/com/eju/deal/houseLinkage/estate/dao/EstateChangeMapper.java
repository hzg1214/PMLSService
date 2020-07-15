package cn.com.eju.deal.houseLinkage.estate.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.dto.houseLinkage.estate.MattressControlRuleDetailDto;
import cn.com.eju.deal.houseLinkage.estate.model.EstateChange;

public interface EstateChangeMapper extends IDao<EstateChange>{
	
	List<EstateChange> getByOperator(Map<?, ?> param) throws Exception;
	
	List<EstateChange> getByEstateId(Map<?, ?> param) throws Exception;
	
	List<MattressControlRuleDetailDto> getByRuleListByProjectNo(Map<?, ?> param) throws Exception;
	
}	