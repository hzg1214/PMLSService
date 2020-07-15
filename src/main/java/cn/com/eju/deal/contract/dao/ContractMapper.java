package cn.com.eju.deal.contract.dao;

import cn.com.eju.deal.contract.model.Contract;
import cn.com.eju.deal.contract.model.ContractChange;
import cn.com.eju.deal.contract.model.ContractSearchResult;
import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.dto.company.CompanyStoreDto;
import cn.com.eju.deal.dto.company.CompanyStoreDtoNew;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractSearchResultDto;
import cn.com.eju.deal.dto.open.DeopositStore;
import cn.com.eju.deal.dto.store.StoreDecorationDto;
import cn.com.eju.deal.dto.store.StoreDto;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ContractMapper extends IDao<Contract>
{
    
    List<ContractSearchResult> selectContractList(Map<?, ?> param) throws Exception;
    
    List<Contract> queryContractTypeByStoreCompanyId(Map<?, ?> param) throws Exception;
    
    List<StoreDto> selectStoreByContractId(Integer id) throws Exception;
    
    List<Contract> getByCompanyId(Integer companyId) throws Exception;
    
    int updateFlowIdById(Contract contract) throws Exception;
    
    int updateContractStatusByFlowId(Contract contract) throws Exception;
    
    Integer getContractsByCompanyId(Integer companyId) throws Exception;
    /** 
    * 根据OA flowId 查询合同
    * @param flowId
    * @return
    */
    Contract getByFlowId(@Param(value="flowId") String flowId)
        throws Exception;
    
    /** 
     * 根据公司名称查询该公司是否存在意向合同
     * @param  companyName 公司名称
     * @return
     */
    Contract getContractInfo(String companyName) throws Exception;
    
    /** 
     * 根据门店Id查询签约的合同
     * @param  storeId 门店Id
     * @return
     */
    List<ContractSearchResult> getSignHisByStoreId(Map<?, ?> param) throws Exception;
    List<CompanyStoreDtoNew> getIsRelieveCompany(Map<?, ?> param) throws Exception;
    /** 
     * 根据门店Id查询签约的合同
     * @param  storeId 门店Id
     * @return
     */
    List<ContractSearchResult> getSignHisByStoreId2(Map<?, ?> param) throws Exception;
    
    /** 
     * 根据公司Id查询审核通过的合同
     * @param  companyId 公司Id
     * @return
     */
    List<Contract> getAuditpassByCompanyId(Integer companyId) throws Exception;
    
    /** 
    * 查询我的合同列表信息（提供给CRM微信端）
    * @param param
    * @return
    */
    List<ContractSearchResult> getByUserId(Map<?, ?> param);
    
    /** 
    * 根据合同ID查询门店信息 (新增合同变更页面用)
    * @param contractId 合同ID
    * @return
    * @throws Exception
    */
    List<StoreDto> getByContractIdOfAdd(Integer contractId) throws Exception;

    List<StoreDto> queryLockingStoreForStop(Integer contractId);
    
    /** 
     * 根据合同变更ID和合同ID查询门店信息 (更新合同变更页面用)
     * @param param contractId 合同ID、contractStopId 合同变更ID
     * @return
     */
     List<StoreDto> getByContractIdOfEdit(Map<?, ?> param) throws Exception;
     
     /**
      * 根据变更记录表的flowId 更新 合同状态
      */
     public Integer updateCtrctStateByChgFlowId(ContractChange contractChange) throws Exception;
     
     /** 
      * 根据合同变更ID查询门店地址
      * @param contractStopId 合同变更ID
      * @return
      * @throws Exception
      */
      List<StoreDto> getStoreInfo(Integer contractStopId) throws Exception;
      
      /** 
       * 根据合同变更表的FlowId 更新 合同表的 合同状态 
       * @param contractStopId 合同变更ID
       * @return
       * @throws Exception
       */
      public Integer updateCtrctStatusByChgFlowId(ContractChange contractChange) throws Exception;
      
    /** 
    * 根据合同Id查询合同信息
    * @param contractId 合同ID
    * @return
    * @throws Exception
    */
    Contract getContractById(Integer contractId) throws Exception;

    List<StoreDto> getByContractIdOfView(Map<String, Object> param) throws Exception;
    /**
     * 
        * @Title: getPassContractList
        * @Description: 根据flowIdList查询出门店装修信息
        * @return
        * @throws Exception
     */
    List<StoreDecorationDto> getPassContractList(Map<?, ?> reqMap) throws Exception;
    
    
    /**
     * 根据合同No查询合同下的门店
     * @param contractNo 合同No
     * @return
     */
    List<StoreDto> getStoreInfoByContractNo(Map<String, Object> queryParam) throws Exception;
    
    /** 
     * 根据合同编号 更新 到账保证金
     * @param contractNo 合同编号
     * @return 更新的记录数
     * @throws Exception
     */
    public Integer updateArvDepositFee(Contract contract) throws Exception;
    
    /**
     * 根据合同No查询合同信息
     * @param contractNo 合同No
     * @return
     */
    public Contract getContractByNo(@Param(value="contractNo") String contractNo) throws Exception;
    
    /** 
    * 根据门店ID查询该门店对应的最新合同信息
    * @param storeId 门店ID
    * @return
    * @throws Exception
    */
    Contract getByStoreId(Integer storeId) throws Exception;
    
    /**
     * 
        * @Title: getByFlowIdList
        * @Description: 根据FlowId查询合同信息更新OMS保证金合同状态（专用于调用OMS接口）
        * @return
        * @throws Exception
     */
    List<Contract> getByFlowIdList(Map<String, Object> reqMap) throws Exception;
    
    /** 
     * 根据OA flowId 查询保证金不等于0的合同
     * @param flowId
     * @return
     */
     Contract getDepoistNozeroCtrctByFlowId(@Param(value="flowId") String flowId)
         throws Exception;
     /**
      * OMS翻牌申请调用接口，根据合同编号获取申请数据返回
     * @param reqMap
     * @return
      */
    Contract returnDataToDecApplication(Map<String, Object> reqMap) throws Exception;

    /**
     * 
    * 根据门店ids查询最新非撤销合同Id
    * @param reqMap
    * @return
     */
    List<ContractDto> getTopOneContract(Map<String, Object> reqMap) throws Exception;

    /**
     * 获取保证金退款编辑所需详情信息
    * @param contractNo
    * @return
     */
    List<DeopositStore> getDepositRefundDetail(Map<String, Object> reqMap) throws Exception;

    /**
     * 
    * 剩余未分账金额查询
    * @param reqMap
    * @return
     */
    Map<String, BigDecimal> getRestSplitDepositFee(Map<String, Object> reqMap) throws Exception;

    /**
     * 
    * 查询当前合同中 已到账 和 已撤销总额
    * @param map
    * @return
     */
    ContractDto getDepositInfoByNo(Map<String, Object> map) throws Exception;

    /**
     * 
    * 更新保证金退款信息到合同
    * @param mop
    * @return
     */
    int updateRefundInfo(Map<String, Object> mop) throws Exception;

    /**
     * 
    * 获取合同剩余未分账保证金-用于校验
    * @param map
    * @return
     */
    Map<String, BigDecimal> checkRestDeposit(Map<String, Object> map);
    
    /**
     * 根据公司Id获取公司下的合同信息
     * @param companyId
     * @return contract list
     * @throws Exception
     */
    List<Contract> getCtrctRelateByCompanyId(@Param("companyId") Integer companyId) throws Exception;
    //Add By NingChao 2017/04/07 Start 
    /** 
     * 根据门店ID查询该门店对应的有效的B类最新合同信息
     * @param storeId 门店ID
     * @return
     * @throws Exception
     */
    Contract getByRenewStoreId(Integer storeId) throws Exception;
   //Add By NingChao 2017/04/07 End
    
    //Add By GuoPengFei 2017/05/25 合规性 Start    
    /** 
     *根据门店ID取得该门店审核通过的B和A2B合同信息
     * @param storeId
	 *            门店ID
     * @return
     * @throws Exception
     */
     Contract getContractByStoreId(Integer storeId) throws Exception;
     
     /**
      * 根据flowId 更新 合同里的 公司地址
      */
     public int updateContractCompanyAdressByFlowId(Contract contract) throws Exception;
     
   //Add By GuoPengFei 2017/05/25 合规性  end
     /**
      * 根据公司ID查询合同状态为未签，审核未通过的合同
      * @param companyId
      * @return
      * @throws Exception
      */
     List<Contract> getContractByCompanyId(Integer companyId) throws Exception;
     
     /**
      * 根据协议书编号查询合同
      * @param agreementNo
      * @return
      * @throws Exception
      */
     List<Contract> getContractByAgreementNo(String agreementNo) throws Exception;

    /**
     * 获取公司的关联合同列表
     * @param param
     * @return
     * @throws Exception
     */
    List<ContractSearchResultDto> getContractCompanyList(Map<?, ?> param) throws Exception;

    List<Contract> queryConfirmContractByCompanyId(Integer companyId);
    /**
     * 根据业绩城市编号查询其核算主体
     */
    List<Contract> queryAccountProject(String cityNo);

    Contract selectNewestContract(Integer storeId);

    List<Contract> selectNewestContractByCompanyId(Integer companyId);
    //运营维护公司合同状态维护补数据
    int insertContractReturn(Integer id);

    List<Map<String,Object>> checkCompanyId(Integer companyId) throws Exception;

    Contract selectDateLifeEnd(Integer storeId);
}