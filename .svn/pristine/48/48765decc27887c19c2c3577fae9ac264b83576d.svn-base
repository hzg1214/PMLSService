package cn.com.eju.pmls.borrowMoneyFrameContract.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.dto.company.CompanyDto;
import cn.com.eju.deal.frameContract.model.FrameAccountProjectMappingDto;
import cn.com.eju.deal.frameContract.model.OaLnkFrameContractFileReturnDto;
import cn.com.eju.deal.model.sweepStreets.CompanyNewDto;
import cn.com.eju.pmls.borrowMoneyFrameContract.model.BorrowMoneyCompany;
import cn.com.eju.pmls.borrowMoneyFrameContract.model.BorrowMoneyCompanyRelated;
import cn.com.eju.pmls.borrowMoneyFrameContract.model.BorrowMoneyFrameContract;
import cn.com.eju.pmls.borrowMoneyFrameContract.model.BorrowMoneyFrameContractDto;

public interface BorrowMoneyFrameContractMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BorrowMoneyFrameContract record);

    int insertSelective(BorrowMoneyFrameContract record);


//    int insertFromSpContract(Map<String,Object> map);

    int insertFromSpContractFile(Map<String,Object> map);

    BorrowMoneyFrameContract selectByPrimaryKey(Integer id);
    BorrowMoneyFrameContractDto getFrameContractAccountProject(Integer id);

    int updateByPrimaryKeySelective(BorrowMoneyFrameContract record);
    int addCompanyBandInfo(BorrowMoneyFrameContract record);
    int updateCompanyVendorInfo(BorrowMoneyFrameContract record);

    int updateByPrimaryKey(BorrowMoneyFrameContract record);

    /**
     * 借佣框架协议列表
     * @param reqMap
     * @return
     */
    List<BorrowMoneyFrameContractDto> getBorrowMoneyFrameContractList(Map<String,Object> reqMap) throws Exception;

    /**
     * 查询盖章合同附件列表
     * @param reqMap
     * @return
     */
    List<OaLnkFrameContractFileReturnDto> getOaFileList(BorrowMoneyFrameContractDto frameContractDto) throws Exception;
    /**
     * 根据id查询联动框架合同
     */
    BorrowMoneyFrameContractDto getBriefById(int id);


    List<BorrowMoneyFrameContractDto>  getBriefList();
    /**
	 * 更新框架合同信息
	 * @param param
	 * @return
	 */
	public Integer updateBorrowMoneyFrameContract(BorrowMoneyFrameContractDto frameContract);
	/**
	 * 查询新增框架合同时候选择公司列表
	 * @param param
	 * @throws Exception
	 */
	List<CompanyDto> getBorrowMoneyFrameContractCompanyList(Map<?, ?> param) throws Exception;
	/**
	 * 根据合同id查询其信息
	 */
	List<CompanyDto> getCompanyInfoById(int id);
	/**
	 * 创建框架合同
	 * @param frameContract
	 * @return
	 */
	int createBorrowMoneyFrameContract(BorrowMoneyFrameContractDto frameContract);
	public Integer updateByCompanyNo(Map<String,Object> param);
	/**
	 * 根据companyNo查询其信息
	 */
	List<CompanyDto> getCompanyInfoByCompanyNo(String companyNo);
	/**
	 * 根据flowId查询框架合同
	 * @param flowId
	 */
	BorrowMoneyFrameContract getByFlowId(String flowId)throws Exception;

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
    List<BorrowMoneyFrameContractDto> getFrameContractByCompanyNo(String companyNo) throws Exception;
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
    List<BorrowMoneyFrameContractDto> queryAccountProject(String cityNo);
    /**
	 * 查询登陆人的核算主体
	 */
	List<FrameAccountProjectMappingDto> getUserMappingAccountProject(String userCode);

    List<BorrowMoneyFrameContract> selectNewestContractByCompanyId(Integer companyId);
    //运营维护公司合同状态维护补数据
    int insertFrameContractReturn(Map<String,Object> queryParam);
    
    BorrowMoneyFrameContractDto getOldBMFCBankInfo(Map<?, ?> param);

    //查询对公账户信息列表
    List<BorrowMoneyFrameContractDto> getBankInfoList(Map<String,Object> param);
    //删除对公账户
    int deleteBankInfo(Map<String,Object> param);
    
//    新增框架合同根据登录城市获取核算主体
    List<BorrowMoneyFrameContractDto> getLnkAccountProjectByCityNo(String cityNo);
    
    //插入借佣框架协议关联公司表
    int insertBorrowMoneyCompany(BorrowMoneyCompany borrowMoneyCompany);
    //新增公司与关联公司关系表
    int insertBorrowMoneyCompanyRelated(BorrowMoneyCompanyRelated borrowMoneyCompanyRelated);
    //更新借佣框架协议关联公司
//    int updateBorrowMoneyCompany(BorrowMoneyCompany borrowMoneyCompany);
    int updateBorrowMoneyCompany(Map<String,Object> param);
    //更新借佣框架协议公司与关联公司关系表
    int updateBorrowMoneyCompanyRelated(Map<String,Object> param);
//    int updateBorrowMoneyRelated(BorrowMoneyCompanyRelated borrowMoneyCompanyRelated);
    int updateBorrowMoneyRelated(Map<String,Object> param);
    
    //发送oa更新
    int updateBorrowMoneyFrameContractToOa(BorrowMoneyFrameContract frameContract);
    
    //移除
    int removeBorrowMoneyCompany(Map<String,Object> param);
    int removeBorrowMoneyCompanyRelated(Map<String,Object> param);
}
