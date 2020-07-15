package cn.com.eju.deal.store.dao;

import cn.com.eju.deal.api.store.dto.APPStoreDto;
import cn.com.eju.deal.common.model.Decoration;
import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.store.DecorationDto;
import cn.com.eju.deal.dto.store.StoreDecorationDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.open.model.ExchangeStoreDto;
import cn.com.eju.deal.store.model.Store;
import cn.com.eju.deal.store.model.StoreLog;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

public interface StoreMapper extends IDao<Store> {
    Store getBriefById(int id);
    List<Store> getCompanyStore(Map<?, ?> param);

    /**
     * @param id 门店编号
     * @return 0:未锁定；1:已锁定
     * @Title: getStatusById
     * @Description: 查询门店的锁定状态
     */
    Integer getStatusById(Integer id) throws Exception;
    
    List<Store> queryListByDistance(Map<?, ?> param) throws Exception;
    
    List<StoreDto> querylistByCompanyId(Map<?, ?> param) throws Exception;

    List<Store> queryCompanyStore(Map<?, ?> param) throws Exception;
    
    /**
     *
     * @param var1
     * @return
     */
    List<StoreDto> queryListNew(Map<?, ?> var1);
    /** 
    * 根据维护的员工编号查询门店信息
    * @param param 维护的员工编号
    * @return
    */
    List<Store> getByMaintainer(Map<?, ?> param) throws Exception;
    
    /**
     * 给接口用：根据公司Id查询门店信息
     * @param param
     * @return
     * @throws Exception
     */
    List<Store> getStoreListByCmIdForApi(Map<?, ?> param) throws Exception;
    
    /**
     * 
        * @Title: getDecorationInfo
        * @Description:根据门店Id获取到门店装修所需信息 
        * @return
        * @throws Exception
     */
    StoreDecorationDto getDecorationInfo(Map<String, Object> reqMap);
    
    /**
     * 获取所有未签约门店名称（给交易中心使用）
     * @param reqMap
     * @return
     */
    List<ExchangeStoreDto> queryWqList(Map<String, Object> reqMap);
    
    /**
     * 查询门店最新合同
     * */
    StoreDto getTopOneContractStore(Integer storeId);
    
    /**
     * 根据storeNo查询门店城市CityNo
     */
    String getCityNoByStoreNo(String storeNo);
    
    /**
     * 根据storeNo查询门店城市CityNo
     */
    String getAcCityNoByStoreNo(String storeNo);
    
    /**
     * 根据companyId 查询门店信息
     * @param companyId
     * @return list store
     * @throws Exception
     */
    List<Store> getByCompanyId(@Param("companyId") Integer companyId) throws Exception;
    
    /**
     * 根据contractId 查询门店信息
     * @param companyId
     * @return list store
     * @throws Exception
     */
    List<Store> getBycontractId(@Param("contractId") Integer contractId) throws Exception;

  //ADD By NingChao 2017/04/07 Start
    /**
     * 根据contractId 带续签门店的合同查询
     * @param companyId
     * @return list store
     * @throws Exception
     */
    ContractDto getRenewContractByStoreId(@Param("storeId") Integer storeId) throws Exception;

    /**
     * 修改门店带续签状态为正常
     * @param companyId
     * @return list store
     * @throws Exception
     */
    Integer updateRenewFlag(Map<?, ?> param) throws Exception;
//ADD By NingChao 2017/04/07 End

    /**
     * @param userCode 当前登陆人code
     * @return 待续签门店列表
     * @throws Exception
     */
    List<Store> getRenewStoreList(Map<String,Object> param) throws Exception;

    /**
     * @param id
     * @return
     * @Title: getReceivedMoneyByStoreId
     * @Description: 根据门店ID查询收款
     */
    List<StoreDto> getReceivedMoneyByStoreId(Integer id);
    
    /**
     * 分配维护人时 check选择的维护人是否是当前维护人
     * @param param
     * @return
     * @throws Exception
     */
    Boolean checkMtner(Map<String,Object> param) throws Exception;
    
    /**
     * 
    * 根据门店ids 清空门店等级信息
    * @param queryParam
    * @return
     */
    int clearStoreABtype(Map<String, Object> queryParam);

    /**
     * 修改OMS装修记录的门店地址
     * @param companyId
     * @return list store
     * @throws Exception
     */
    Integer UpdateOmsStoreAdress(Map<?, ?> param) throws Exception;
    //Add by cning 2017/07/07 Start
    /**
     * @param id 门店ID
     * @return 门店审核状态
     * @Title: getStatusById
     * @Description: 查询门店的审核状态
     */
    Integer getAuditStatusById(Integer id) throws Exception;
  //Add by cning 2017/07/07 End

    //ADD By QJP 2017/07/12 Start
    /**
     * 查询门店修改日志LIST
     * @param id
     * @return
     * @throws Exception
     */
    List<StoreLog> getStoreLogList(Map<String, Object> param) throws Exception;
    //ADD By QJP 2017/07/12 End

    StoreLog getStoreLogById (Map<String, Object> param) throws Exception;
    
    /**
     * 插入门店修改日志表
     * @param 
     * @return  
     * @throws
     */
    Integer insertStoreLog(Map<?, ?> param) throws Exception;

    
    /**
     * check门店地址是否重复
     * @param addressDetail
     * @return
     * @throws Exception
     */
    Integer checkStoreAddress(Map<String,Object> map) throws Exception;

    //更新门店负责人、负责人电话
    Integer updateStoreManager(Map<String,Object> map) throws Exception;

    Integer checkBToAAlert(Integer id) throws Exception;

    /**
     * 根据房友APP提供的 公司编号信息，返回合作门店信息（合同审核通过）
     * 房友app用
     * @param param
     * @return
     * @throws Exception
     */
    List<APPStoreDto> getStoresByCompanyNoForApp(Map<String, Object> param) throws Exception;

    /**
     * 根据storeNo获取门店详情(房友app用)
     * @param param
     * @return
     * @throws Exception
     */
    APPStoreDto getStoreDetailByNoForApp(Map<String, Object> param) throws Exception;
    
    Store getStoreByStoreNo(String storeNo);
    /**
     * 门店装修类型是否可以编辑
     */
    Integer getStoreBusinessPlaceEditFlag(String storeNo) throws Exception;

    List<StoreDto> querygplistByCompanyId(Map<?, ?> param) throws Exception;

    List<Store> querygpCompanyStore(Map<?, ?> param) throws Exception;

    List<APPStoreDto> getStoresByCompanyNoGpForApp(Map<String, Object> param) throws Exception;

    Store getPlainInfoById(Integer storeId);

	Integer getStoreRelocationStatusById(int id);

	Decoration getDecorationByContractIdAndStoreId(Map<String, Object> hashMap);
	Integer getStorePartyChangeStatusById(int id);
	

	Integer updateWxStorefyAccount(Map<String, Object> hashMap);
	Map<String, Integer> getStoreRelocationStatusByMap(int id);

    void updateAuthCheckStatus(String flowId);

    Integer selectUpdateStoreReNewFlag(int storeId);

    Integer updateStoreReNewFlag(Map<String, Object> param);

    Store getCenterInfo(Integer storeId);


    List<FileRecordMainDto> selectFile(Map<String, Object> hashMap);
    List<FileRecordMainDto> selectDecorationFile(Map<String, Object> hashMap);
    
    //根据门店id查询关联公司
    Map<String,Object> getCompanyByStoreId(Map<String,Object> param);
}