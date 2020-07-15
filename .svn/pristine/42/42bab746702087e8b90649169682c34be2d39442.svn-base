package cn.com.eju.deal.company.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.company.model.CompanyLog;
import cn.com.eju.deal.core.dao.IDao;

public interface CompanyLogMapper extends IDao<CompanyLog>{
	
	  List<CompanyLog> queryListByCompanyId(Map<?, ?> param) throws Exception;
	  
	  int createCompanyLog(Map<?, ?> param) throws Exception;
	  
	  CompanyLog getLogDetail(Integer logId) throws Exception;
	  
	  CompanyLog selectNewByCompanyId(Map<?, ?> param) throws Exception;
}
