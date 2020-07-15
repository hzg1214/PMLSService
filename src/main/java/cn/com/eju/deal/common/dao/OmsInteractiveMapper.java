package cn.com.eju.deal.common.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.common.model.Decoration;
import cn.com.eju.deal.dto.contract.DepositDto;
import cn.com.eju.deal.dto.contract.PerformNodeRecordDto;
import cn.com.eju.deal.dto.store.DecorationDto;
import cn.com.eju.deal.model.cashier.CashierDto;
import cn.com.eju.deal.open.model.ContractFlowDto;

/**
 * OMS和CRM交互的Mapper
 * 
 * @author wenhui.zhang
 * @date 2017年6月27日 下午3:22:03
 */
public interface OmsInteractiveMapper {

	/**
	 * 提供给外部系统查询关账状态的接口（非TMS）
	 * 
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> queryAPIListByCityNo(Map<?, ?> param);

	/**
	 * 新增装修
	 * 
	 * @param param
	 * @return
	 */
	Integer createDecrt(Decoration decoration);

	/**
	 * 【描述】: 批量新增合同流程
	 *
	 * @author yinkun
	 * @date: 2017年10月30日 下午3:52:50
	 * @param dtoList
	 * @return
	 */
    int batchContractFlow(List<ContractFlowDto> dtoList);

    /**
     * 【描述】: 批量新增装修
     *
     * @author yinkun
     * @date: 2017年10月30日 下午4:40:46
     * @param dtoList
     * @return
     */
    int batchCreateDecoration(List<DecorationDto> dtoList);

    /**
     * 【描述】: 批量更新合同状态
     *
     * @author yinkun
     * @date: 2017年10月30日 下午4:48:43
     * @param dtoList
     * @return
     */
    int batchUpdateContractState(List<DepositDto> dtoList);

    /**
     * 【描述】: 新增保证金
     *
     * @author yinkun
     * @date: 2017年10月30日 下午4:56:39
     * @param dto
     * @return
     */
    int createDeposit(DepositDto dto);

    /**
     * 【描述】: 新增合同业绩节点
     *
     * @author yinkun
     * @date: 2017年10月30日 下午5:19:09
     * @param dto
     * @return
     */
    int updatePerformNodeRecord(PerformNodeRecordDto dto);

    /**
     * 【描述】: 新增合同业绩节点
     *
     * @author yinkun
     * @date: 2017年10月30日 下午5:19:26
     * @param dto
     * @return
     */
    int createPerformNodeRecord(PerformNodeRecordDto dto);

    /**
     * 【描述】: 更新oms装修记录
     *
     * @author yinkun
     * @date: 2018年3月7日 下午2:55:43
     * @param dto
     * @return
     */
    int updateOmsDecoration(DecorationDto dto);
    
    /** 
	* 获取该合同变更的门店数
	* @param contractStopId 合同变更ID
	* @return
	* @throws Exception
	*/
	int getDecCountByMap(DecorationDto dto);

	/**
	 * 获取手续费
	 * @param paySeq
	 * @return
	 */
    CashierDto getPayFeeByPaySeq(String paySeq);

	int updateOmsDecorationById(Map<String, Object> hashMap);
}