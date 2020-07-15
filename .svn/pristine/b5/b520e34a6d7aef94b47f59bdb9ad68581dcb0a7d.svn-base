package cn.com.eju.deal.open.dao;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.open.model.OmsContractDto;
import cn.com.eju.deal.open.model.OmsStoreDto;
import cn.com.eju.deal.open.model.StorePerformDto;

/**
 * OMS系统中门店拓展业绩查看Dao层
 * 
 * @author sunmm
 * @date 2016年8月20日 下午9:40:36
 */
public interface OmsPerformMapper extends IDao<StorePerformDto> {

	/**
	 * 根据门店No查询门店相关信息
	 * 
	 * @param storeNo
	 *            门店No
	 * @return
	 */
	OmsStoreDto getStoreByNo(String storeNo) throws Exception;

	/**
	 * 根据合同No查询合同相关信息
	 * 
	 * @param contractNo
	 *            合同No
	 * @return
	 * @throws Exception
	 */
	OmsContractDto getContractByNo(String contractNo) throws Exception;

}
