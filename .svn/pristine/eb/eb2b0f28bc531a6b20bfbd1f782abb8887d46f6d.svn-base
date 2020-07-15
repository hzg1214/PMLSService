package cn.com.eju.deal.gpContract.dao;

import cn.com.eju.deal.api.gpContract.dto.GPContractWXDto;
import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.gpContract.model.GpContract;
import cn.com.eju.deal.gpContract.model.GpContractDto;
import cn.com.eju.deal.gpContractChange.model.GpContractChange;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface GpContractMapper extends IDao<GpContract>
{

    GpContractDto getByGpId(Integer id);

    List<GpContract> getListByAgreementNo(String agreementNo) throws Exception;

    int updateFlowIdById(GpContract contract) throws Exception;

    List<StoreDto> selectStoreByContractId(Integer id) throws Exception;

    GpContract getByFlowId(@Param(value="flowId") String flowId) throws Exception;
    GpContractChange getContractChangeByFlowId(@Param(value="flowId") String flowId) throws Exception;

    int updateContractStatusByFlowId(GpContract contract) throws Exception;
    int updateContractChangeStatusByFlowId(GpContractChange contractChange) throws Exception;
    /**
     * 根据公司id查询公盘合同里公司信息
     * @param companyId
     */
    List<GpContract> getGpContractByCompanyId(Integer companyId) throws Exception;
    List<GpContract> getGpContractByCompanyInfo(Integer companyId) throws Exception;
    /**
     * 根据门店id跟新公盘合同里的门店信息
     * @return
     */
    public int updateByGpStoreId(Map<String,Object> param);
    List<GpContract> selectNewestGpContractByCompanyId(Integer companyId);
    GpContract selectNewestGpContract(Integer storeId);

	int deleteAchivAchievementByrelateId(Integer relateId);

    int insertContractReturn(Integer id);

    BigDecimal queryDeposit(Integer companyId);

    GpContractDto getRefIdByCompanyId(Integer companyId);

    GpContract selectByGpContractNo(String gpContractNo);

    List<Map<String,Object>> getTerminateList(Map<String,Object> param) throws Exception;

    GPContractWXDto getGpContractZZDetail(Map<String,Object> param) throws Exception;
}