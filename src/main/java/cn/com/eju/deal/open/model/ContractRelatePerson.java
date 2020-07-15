package cn.com.eju.deal.open.model;

import java.util.Date;

/**
 * 业绩关联人员信息
 * 
 * @author sunmm
 * @date 2016年8月22日 下午3:35:01
 */
public class ContractRelatePerson {

	/**
	 * 主键ID
	 */
	private Integer id;

	/**
	 * 合同ID
	 */
	private Integer contractId;

	/**
	 * 业绩拓展专员工号
	 */
	private String expandingPersonnelId;

	/**
	 * 业绩拓展专员姓名
	 */
	private String expandingPersonnel;

	/**
	 * 业绩拓展专员所属组(业绩归属团队四)
	 */
	private String performTeam4;

	/**
	 * 业绩拓展专员所属岗位
	 */
	private String postTypeName4;

	/**
	 * 拓展经理工号
	 */
	private String expandLeaderId;

	/**
	 * 拓展经理姓名
	 */
	private String expandLeader;

	/**
	 * 拓展经理所属组(业绩归属团队三)
	 */
	private String performTeam3;

	/**
	 * 拓展经理所属岗位
	 */
	private String postTypeName3;

	/**
	 * 区域总监工号
	 */
	private String regionalDirectorId;

	/**
	 * 区域总监姓名
	 */
	private String regionalDirector;

	/**
	 * 区域总监所属组(业绩归属团队二)
	 */
	private String performTeam2;

	/**
	 * 区域总监所属岗位
	 */
	private String postTypeName2;

	/**
	 * 业绩归属团队一
	 */
	private String performTeam1;

	/**
	 * 创建人
	 */
	private Integer userIdCreate;

	/**
	 * 创建日期
	 */
	private Date dateCreate;

	/**
	 * 更新人
	 */
	private Integer updateCreate;

	/**
	 * 更新日期
	 */
	private Date updateDate;

	/**
	 * 是否删除标识(0:true 1:false)
	 */
	private Boolean delFlag;
	
	/**
	 * 拓展专员所属组Id
	 */
	private Integer expandingPersonnelGroupId;
	
	/**
	 * 拓展专员所属岗位Id
	 */
	private Integer expandingPersonnelPostId;
	
	/**
	 * 拓展经理所属组Id
	 */
	private Integer expandLeaderGroupId;
	
	/**
	 * 拓展经理所属岗位Id
	 */
	private Integer expandLeaderPostId;
	
	/**
	 * 区域总监所属组Id
	 */
	private Integer regionalDirectorGroupId;
	
	/**
	 * 区域总监所属岗位Id
	 */
	private Integer regionalDirectorPostId;
	
	/**
     * 事业部总经理工号
     */
    private String businessManagerId;

    /**
     * 事业部总经理姓名
     */
    private String businessManager;

    /**
     * 事业部总经理所属岗位
     */
    private String postTypeName1;
    
    /**
     * 事业部总经理所属组Id
     */
    private Integer businessManagerGroupId;
    
    /**
     * 事业部总经理所属岗位Id
     */
    private Integer businessManagerPostId;

	/**
	 * 获取主键ID
	 * 
	 * @return id 主键ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置主键ID
	 * 
	 * @param id
	 *            主键ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取合同ID
	 * 
	 * @return contractId 合同ID
	 */
	public Integer getContractId() {
		return contractId;
	}

	/**
	 * 设置合同ID
	 * 
	 * @param contractId
	 *            合同ID
	 */
	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	/**
	 * 获取业绩拓展专员工号
	 * 
	 * @return expandingPersonnelId 业绩拓展专员工号
	 */
	public String getExpandingPersonnelId() {
		return expandingPersonnelId;
	}

	/**
	 * 设置业绩拓展专员工号
	 * 
	 * @param expandingPersonnelId
	 *            业绩拓展专员工号
	 */
	public void setExpandingPersonnelId(String expandingPersonnelId) {
		this.expandingPersonnelId = expandingPersonnelId;
	}

	/**
	 * 获取业绩拓展专员姓名
	 * 
	 * @return expandingPersonnel 业绩拓展专员姓名
	 */
	public String getExpandingPersonnel() {
		return expandingPersonnel;
	}

	/**
	 * 设置业绩拓展专员姓名
	 * 
	 * @param expandingPersonnel
	 *            业绩拓展专员姓名
	 */
	public void setExpandingPersonnel(String expandingPersonnel) {
		this.expandingPersonnel = expandingPersonnel;
	}

	/**
	 * 获取业绩拓展专员所属岗位
	 * 
	 * @return postTypeName4 业绩拓展专员所属岗位
	 */
	public String getPostTypeName4() {
		return postTypeName4;
	}

	/**
	 * 设置业绩拓展专员所属岗位
	 * 
	 * @param postTypeName4
	 *            业绩拓展专员所属岗位
	 */
	public void setPostTypeName4(String postTypeName4) {
		this.postTypeName4 = postTypeName4;
	}

	/**
	 * 获取拓展经理工号
	 * 
	 * @return expandLeaderId 拓展经理工号
	 */
	public String getExpandLeaderId() {
		return expandLeaderId;
	}

	/**
	 * 设置拓展经理工号
	 * 
	 * @param expandLeaderId
	 *            拓展经理工号
	 */
	public void setExpandLeaderId(String expandLeaderId) {
		this.expandLeaderId = expandLeaderId;
	}

	/**
	 * 获取拓展经理姓名
	 * 
	 * @return expandLeader 拓展经理姓名
	 */
	public String getExpandLeader() {
		return expandLeader;
	}

	/**
	 * 设置拓展经理姓名
	 * 
	 * @param expandLeader
	 *            拓展经理姓名
	 */
	public void setExpandLeader(String expandLeader) {
		this.expandLeader = expandLeader;
	}

	/**
	 * 获取区域总监所属组(业绩归属团队二)
	 * 
	 * @return performTeam2 区域总监所属组(业绩归属团队二)
	 */
	public String getPerformTeam2() {
		return performTeam2;
	}

	/**
	 * 设置区域总监所属组(业绩归属团队二)
	 * 
	 * @param performTeam2
	 *            区域总监所属组(业绩归属团队二)
	 */
	public void setPerformTeam2(String performTeam2) {
		this.performTeam2 = performTeam2;
	}

	/**
	 * 获取区域总监所属岗位
	 * 
	 * @return postTypeName2 区域总监所属岗位
	 */
	public String getPostTypeName2() {
		return postTypeName2;
	}

	/**
	 * 设置区域总监所属岗位
	 * 
	 * @param postTypeName2
	 *            区域总监所属岗位
	 */
	public void setPostTypeName2(String postTypeName2) {
		this.postTypeName2 = postTypeName2;
	}

	/**
	 * 获取区域经理工号
	 * 
	 * @return regionalDirectorId 区域经理工号
	 */
	public String getRegionalDirectorId() {
		return regionalDirectorId;
	}

	/**
	 * 设置区域经理工号
	 * 
	 * @param regionalDirectorId
	 *            区域经理工号
	 */
	public void setRegionalDirectorId(String regionalDirectorId) {
		this.regionalDirectorId = regionalDirectorId;
	}

	/**
	 * 获取区域经理姓名
	 * 
	 * @return regionalDirector 区域经理姓名
	 */
	public String getRegionalDirector() {
		return regionalDirector;
	}

	/**
	 * 设置区域经理姓名
	 * 
	 * @param regionalDirector
	 *            区域经理姓名
	 */
	public void setRegionalDirector(String regionalDirector) {
		this.regionalDirector = regionalDirector;
	}

	/**
	 * 获取拓展经理所属组(业绩归属团队三)
	 * 
	 * @return performTeam3 拓展经理所属组(业绩归属团队三)
	 */
	public String getPerformTeam3() {
		return performTeam3;
	}

	/**
	 * 设置拓展经理所属组(业绩归属团队三)
	 * 
	 * @param performTeam3
	 *            拓展经理所属组(业绩归属团队三)
	 */
	public void setPerformTeam3(String performTeam3) {
		this.performTeam3 = performTeam3;
	}

	/**
	 * 获取拓展经理所属岗位
	 * 
	 * @return postTypeName3 拓展经理所属岗位
	 */
	public String getPostTypeName3() {
		return postTypeName3;
	}

	/**
	 * 设置拓展经理所属岗位
	 * 
	 * @param postTypeName3
	 *            拓展经理所属岗位
	 */
	public void setPostTypeName3(String postTypeName3) {
		this.postTypeName3 = postTypeName3;
	}

	/**
	 * 获取业绩归属团队一
	 * 
	 * @return performTeam1 业绩归属团队一
	 */
	public String getPerformTeam1() {
		return performTeam1;
	}

	/**
	 * 设置业绩归属团队一
	 * 
	 * @param performTeam1
	 *            业绩归属团队一
	 */
	public void setPerformTeam1(String performTeam1) {
		this.performTeam1 = performTeam1;
	}

	/**
	 * 获取创建人
	 * 
	 * @return userIdCreate 创建人
	 */
	public Integer getUserIdCreate() {
		return userIdCreate;
	}

	/**
	 * 设置创建人
	 * 
	 * @param userIdCreate
	 *            创建人
	 */
	public void setUserIdCreate(Integer userIdCreate) {
		this.userIdCreate = userIdCreate;
	}

	/**
	 * 获取创建时间
	 * 
	 * @return dateCreate 创建时间
	 */
	public Date getDateCreate() {
		return dateCreate;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param dateCreate
	 *            创建时间
	 */
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	/**
	 * 获取更新人
	 * 
	 * @return updateCreate 更新人
	 */
	public Integer getUpdateCreate() {
		return updateCreate;
	}

	/**
	 * 设置更新人
	 * 
	 * @param updateCreate
	 *            更新人
	 */
	public void setUpdateCreate(Integer updateCreate) {
		this.updateCreate = updateCreate;
	}

	/**
	 * 获取更新时间
	 * 
	 * @return updateDate 更新时间
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * 设置更新时间
	 * 
	 * @param updateDate
	 *            更新时间
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 获取删除标识(0:未删除 1:已删除)
	 * 
	 * @return delFlag 删除标识(0:未删除 1:已删除)
	 */
	public Boolean getDelFlag() {
		return delFlag;
	}

	/**
	 * 设置删除标识(0:未删除 1:已删除)
	 * 
	 * @param delFlag
	 *            删除标识(0:未删除 1:已删除)
	 */
	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * 获取业绩拓展专员所属组(业绩归属团队四)
	 * 
	 * @return performTeam4 业绩拓展专员所属组(业绩归属团队四)
	 */
	public String getPerformTeam4() {
		return performTeam4;
	}

	/**
	 * 设置业绩拓展专员所属组(业绩归属团队四)
	 * 
	 * @param performTeam4
	 *            业绩拓展专员所属组(业绩归属团队四)
	 */
	public void setPerformTeam4(String performTeam4) {
		this.performTeam4 = performTeam4;
	}

	/** 
	* 获取拓展专员所属组Id
	* @return expandingPersonnelGroupId 拓展专员所属组Id
	*/
	public Integer getExpandingPersonnelGroupId() {
		return expandingPersonnelGroupId;
	}

	/** 
	* 设置拓展专员所属组Id
	* @param expandingPersonnelGroupId 拓展专员所属组Id
	*/
	public void setExpandingPersonnelGroupId(Integer expandingPersonnelGroupId) {
		this.expandingPersonnelGroupId = expandingPersonnelGroupId;
	}

	/** 
	* 获取拓展专员所属岗位Id
	* @return expandingPersonnelPostId 拓展专员所属岗位Id
	*/
	public Integer getExpandingPersonnelPostId() {
		return expandingPersonnelPostId;
	}

	/** 
	* 设置拓展专员所属岗位Id
	* @param expandingPersonnelPostId 拓展专员所属岗位Id
	*/
	public void setExpandingPersonnelPostId(Integer expandingPersonnelPostId) {
		this.expandingPersonnelPostId = expandingPersonnelPostId;
	}

	/** 
	* 获取拓展经理所属组Id
	* @return expandLeaderGroupId 拓展经理所属组Id
	*/
	public Integer getExpandLeaderGroupId() {
		return expandLeaderGroupId;
	}

	/** 
	* 设置拓展经理所属组Id
	* @param expandLeaderGroupId 拓展经理所属组Id
	*/
	public void setExpandLeaderGroupId(Integer expandLeaderGroupId) {
		this.expandLeaderGroupId = expandLeaderGroupId;
	}

	/** 
	* 获取拓展经理所属岗位Id
	* @return expandLeaderPostId 拓展经理所属岗位Id
	*/
	public Integer getExpandLeaderPostId() {
		return expandLeaderPostId;
	}

	/** 
	* 设置拓展经理所属岗位Id
	* @param expandLeaderPostId 拓展经理所属岗位Id
	*/
	public void setExpandLeaderPostId(Integer expandLeaderPostId) {
		this.expandLeaderPostId = expandLeaderPostId;
	}

	/** 
	* 获取区域总监所属组Id
	* @return regionalDirectorGroupId 区域总监所属组Id
	*/
	public Integer getRegionalDirectorGroupId() {
		return regionalDirectorGroupId;
	}

	/** 
	* 设置区域总监所属组Id
	* @param regionalDirectorGroupId 区域总监所属组Id
	*/
	public void setRegionalDirectorGroupId(Integer regionalDirectorGroupId) {
		this.regionalDirectorGroupId = regionalDirectorGroupId;
	}

	/** 
	* 获取区域总监所属岗位Id
	* @return regionalDirectorPostId 区域总监所属岗位Id
	*/
	public Integer getRegionalDirectorPostId() {
		return regionalDirectorPostId;
	}

	/** 
	* 设置区域总监所属岗位Id
	* @param regionalDirectorPostId 区域总监所属岗位Id
	*/
	public void setRegionalDirectorPostId(Integer regionalDirectorPostId) {
		this.regionalDirectorPostId = regionalDirectorPostId;
	}

    /** 
    * 获取事业部总经理工号
    * @return businessManagerId 事业部总经理工号
    */
    public String getBusinessManagerId()
    {
        return businessManagerId;
    }

    /** 
    * 设置事业部总经理工号
    * @param businessManagerId 事业部总经理工号
    */
    public void setBusinessManagerId(String businessManagerId)
    {
        this.businessManagerId = businessManagerId;
    }

    /** 
    * 获取事业部总经理姓名
    * @return businessManager 事业部总经理姓名
    */
    public String getBusinessManager()
    {
        return businessManager;
    }

    /** 
    * 设置事业部总经理姓名
    * @param businessManager 事业部总经理姓名
    */
    public void setBusinessManager(String businessManager)
    {
        this.businessManager = businessManager;
    }

    /** 
    * 获取事业部总经理所属岗位
    * @return postTypeName1 事业部总经理所属岗位
    */
    public String getPostTypeName1()
    {
        return postTypeName1;
    }

    /** 
    * 设置事业部总经理所属岗位
    * @param postTypeName1 事业部总经理所属岗位
    */
    public void setPostTypeName1(String postTypeName1)
    {
        this.postTypeName1 = postTypeName1;
    }

    /** 
    * 获取事业部总经理所属组Id
    * @return businessManagerGroupId 事业部总经理所属组Id
    */
    public Integer getBusinessManagerGroupId()
    {
        return businessManagerGroupId;
    }

    /** 
    * 设置事业部总经理所属组Id
    * @param businessManagerGroupId 事业部总经理所属组Id
    */
    public void setBusinessManagerGroupId(Integer businessManagerGroupId)
    {
        this.businessManagerGroupId = businessManagerGroupId;
    }

    /** 
    * 获取事业部总经理所属岗位Id
    * @return businessManagerPostId 事业部总经理所属岗位Id
    */
    public Integer getBusinessManagerPostId()
    {
        return businessManagerPostId;
    }

    /** 
    * 设置事业部总经理所属岗位Id
    * @param businessManagerPostId 事业部总经理所属岗位Id
    */
    public void setBusinessManagerPostId(Integer businessManagerPostId)
    {
        this.businessManagerPostId = businessManagerPostId;
    }

}
