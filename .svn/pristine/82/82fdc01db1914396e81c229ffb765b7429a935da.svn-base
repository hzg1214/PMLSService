package cn.com.eju.deal.store.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.dto.store.StorePaymentDto;
import cn.com.eju.deal.store.model.StorePayment;

public interface StorePaymentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StorePayment record);

    int insertSelective(StorePayment record);

    StorePayment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StorePayment record);

    int updateByPrimaryKey(StorePayment record);
    /**
     * 查询退款列表
     * @param reqMap
     * @return
     */
    List<StorePaymentDto> getStorePaymentList(Map<String,Object> reqMap);

    /**
     * 查询退款详情
     * @param id
     * @return
     */
	StorePaymentDto getBriefById(int id);
	List<StoreDto> getStoreInfoById(int id);
	/**
	 * 删除退款
	 * @param param
	 * @return
	 */
	public Integer updateStr(Map<String,Object> param);
	/**
	 * 新增保证金退款时查询合同列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<ContractDto> getPaymentContractList(Map<?, ?> param) throws Exception;
	/**
	 * 新增保证金时根据合同id查询相关信息
	 */
	/**
     * 查询退款详情
     * @param id
     * @return
     */
	ContractDto getContractInfoById(int id);
	List<StoreDto> getStoreInfoByContractId(int id);
	List<StoreDto> getLockingStore(int id);//查询已经退完的

	int create(StorePayment storePayment);
	/**
     * 根据业绩城市编号查询其核算主体
     */
    List<StorePayment> queryAccountProject(String cityNo);

}