package cn.com.eju.deal.api.contractChange.mapper;


import java.util.List;
import java.util.Map;

import cn.com.eju.deal.api.contractChange.model.AchievementCancelEdDto;
import cn.com.eju.deal.api.contractChange.model.CenterSetting;
import cn.com.eju.deal.api.contractChange.model.ContractChangeNewDto;
import cn.com.eju.deal.api.contractChange.model.OaProcessDto;

/**
 * 合同撤销
 */
public interface APIContractChangeMapper {
	/**
	 * 合同撤销列表
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
    List<ContractChangeNewDto> getContractList(Map<String,Object> reqMap) throws Exception;
    /**
     * 撤销合同提交页面
     */
    ContractChangeNewDto getContractChangeInfo(Map<String,Object> reqMap) throws Exception;
    /**
     * 已撤销的合同列表
     * @param reqMap
     * @return
     * @throws Exception
     */
    List<AchievementCancelEdDto> getContractChangeEdList(Map<String,Object> reqMap) throws Exception;
    /**
     * 已撤销详情
     */
    AchievementCancelEdDto getContractChangeEdInfo(Map<String,Object> reqMap) throws Exception;
    /**
     * 根据flowId查询其流程
     */
    List<OaProcessDto> getOaProcessByFlowId(String flowId);
	CenterSetting getSenderLoginNameAndCode(Integer contractId);
	/**
	 * 合同可以终止列表
	 * @param reqMap
	 * @return
	 */
	List<ContractChangeNewDto> getContractEndList(Map<String, Object> reqMap) throws Exception;
	/**
     * 合同终止提交填写页面
     */
    ContractChangeNewDto toContractEndSubmit(Map<String,Object> reqMap) throws Exception;
    /**
     * 合同终止查看页面
     */
	ContractChangeNewDto getContractEndToView(Map<String, Object> reqMap) throws Exception;
	/**
	 * 查询已终止的待提交合同列表
	 */
	List<ContractChangeNewDto> getContractEndToSumbmitList(Map<String,Object> reqMap) throws Exception;
	/**
	 * 合同解约列表查询
	 */
    List<ContractChangeNewDto> getContractEndProgressList(Map<String,Object> reqMap) throws Exception;

	Map getStoreDecorateInfo(Integer storeId);

    Integer checkContractStoreForTerminate(Map<String, Object> map);
    Map checkStoreApplyInfo(Map<String,Object> param);
}
