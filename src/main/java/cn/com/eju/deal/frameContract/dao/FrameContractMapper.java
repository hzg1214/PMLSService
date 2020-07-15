package cn.com.eju.deal.frameContract.dao;

import cn.com.eju.deal.dto.company.CompanyDto;
import cn.com.eju.deal.frameContract.model.FrameAccountProjectMappingDto;
import cn.com.eju.deal.frameContract.model.FrameContract;
import cn.com.eju.deal.frameContract.model.FrameContractDto;
import cn.com.eju.deal.frameContract.model.OaLnkFrameContractFileReturnDto;
import cn.com.eju.deal.model.sweepStreets.CompanyNewDto;

import java.util.List;
import java.util.Map;

public interface FrameContractMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FrameContract record);

    int insertSelective(FrameContract record);


    int insertFromSpContract(Map<String,Object> map);

    int insertFromSpContractFile(Map<String,Object> map);

    FrameContract selectByPrimaryKey(Integer id);
    FrameContractDto getFrameContractAccountProject(Integer id);

    int updateByPrimaryKeySelective(FrameContract record);
    int addCompanyBandInfo(FrameContract record);
    int updateCompanyVendorInfo(FrameContract record);

    int updateByPrimaryKey(FrameContract record);

    /**
     * 查询联动框架合同列表
     * @param reqMap
     * @return
     */
    List<FrameContractDto> getFrameContractList(Map<String,Object> reqMap) throws Exception;

    /**
     * 查询盖章合同附件列表
     * @param reqMap
     * @return
     */
    List<OaLnkFrameContractFileReturnDto> getOaFileList(FrameContractDto frameContractDto) throws Exception;
    /**
     * 根据id查询联动框架合同
     */
    FrameContractDto getBriefById(int id);


    List<FrameContractDto>  getBriefList();
    /**
	 * 更新框架合同信息
	 * @param param
	 * @return
	 */
	public Integer updateStr(Map<String,Object> param);
	/**
	 * 查询新增框架合同时候选择公司列表
	 * @param param
	 * @throws Exception
	 */
	List<CompanyDto> getFrameContractCompanyList(Map<?, ?> param) throws Exception;
	/**
	 * 根据合同id查询其信息
	 */
	List<CompanyDto> getCompanyInfoById(int id);
	/**
	 * 创建框架合同
	 * @param frameContract
	 * @return
	 */
	int create(FrameContractDto frameContract);
	public Integer updateByCompanyNo(Map<String,Object> param);
	/**
	 * 根据companyNo查询其信息
	 */
	List<CompanyDto> getCompanyInfoByCompanyNo(String companyNo);
	/**
	 * 根据flowId查询框架合同
	 * @param flowId
	 */
	FrameContract getByFlowId(String flowId)throws Exception;

	/**
	 * 根据框架协议更新公司业务类型为空
	 * @param queryParam
	 * @return
	 */
    int updateCompanyBizType(Map<String, Object> queryParam);
    /**
     * 根据公司编号查询框架协议
     * @param companyNo
     * @return
     * @throws Exception
     */
    List<FrameContractDto> getFrameContractByCompanyNo(String companyNo) throws Exception;
    /**
     * 跟新框架协议里面的公司信息
     * @param param
     * @return
     */
    public Integer updateCompanyInfo(Map<String,Object> param);

    void updateCompanyByCompanyNo(CompanyNewDto dto);
    /**
     * 根据业绩城市编号查询其核算主体
     */
    List<FrameContractDto> queryAccountProject(String cityNo);
    /**
	 * 查询登陆人的核算主体
	 */
	List<FrameAccountProjectMappingDto> getUserMappingAccountProject(String userCode);

    List<FrameContract> selectNewestContractByCompanyId(Integer companyId);
    //运营维护公司合同状态维护补数据
    int insertFrameContractReturn(Map<String,Object> queryParam);
    FrameContractDto getOldFtBankInfo(Map<?, ?> param);

    //查询对公账户信息列表
    List<FrameContractDto> getBankInfoList(Map<String,Object> param);
    //删除对公账户
    int deleteBankInfo(Map<String,Object> param);
    
//    新增框架合同根据登录城市获取核算主体
    List<FrameContractDto> getLnkAccountProjectByCityNo(String cityNo);
}
