package cn.com.eju.deal.contract.dao;

import java.util.List;

import cn.com.eju.deal.contract.model.Product;
import cn.com.eju.deal.core.dao.IDao;

public interface ProductMapper extends IDao<Product>{
	
	List<Product> selectProductByContractId(Integer id) throws Exception;
	
	List<Product> selectProductByParentId(Integer id) throws Exception;
	
	List<Product> selectProductInfo() throws Exception;
}