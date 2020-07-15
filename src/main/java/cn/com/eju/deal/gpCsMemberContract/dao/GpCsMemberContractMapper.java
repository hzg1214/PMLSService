package cn.com.eju.deal.gpCsMemberContract.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.gpContract.model.GpContractDto;
import cn.com.eju.deal.gpCsMemberContract.model.GpCsMemberContract;
import cn.com.eju.deal.gpCsMemberContract.model.GpCsMemberContractDto;

public interface GpCsMemberContractMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GpCsMemberContract record);

    int insertSelective(GpCsMemberContract record);

    GpCsMemberContract selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GpCsMemberContract record);

    int updateByPrimaryKey(GpCsMemberContract record);
    /**
     * 初始会员列表
     * @param reqMap
     * @return
     */
    List<GpCsMemberContractDto> getGpCsMemberContractList(Map<String, Object> reqMap);
    /**
     * 根据id查询公盘初始会员合同详情
     * @param id
     * @return
     */
	GpCsMemberContractDto getGpCsMemberContractById(Integer id);

	GpContractDto getGpInfoByCompanyId(Map<?, ?> param);

	Integer getCsMemberCtByCompanyId(Map<?, ?> param);

	GpCsMemberContract getCsMemberContractByFlowId(String flowId);

	Integer updateGpCsMemberStatusByFlowId(GpCsMemberContract gpCsMemberContract);

	Integer updateStatus(Map<String, Object> queryParam);

	List<GpCsMemberContract> getListByAgreementNo(Map<String, Object> hashMap);

	int deleteAchivAchievementByrelateId(Integer relateId);

    int insertGpCsMemberContractReturn(Integer id);
}