package cn.com.eju.deal.user.model;

import java.util.Date;

public class User
{
    private Integer userId;
    
    private String jobNo;
    
    private String userCode;
    
    private String password;
    
    private String userName;
    
    private String sex;
    
    private String cellphone;
    
    private String credentialsNum; //证件号码
    
    private Integer groupId;
    
    // 岗位ID
    private Integer postId;
    
    private Integer credentials; //证件类型
    
    private Date dateCreate;
    
    private Integer userIdCreate;
    
    private String delFlag;
    
    private String postName;
    
    /*    //权限List
        private List<Auth> authList;
        //角色List
        private List<Role> roleList;*/
    
    private String[] postIds;
    
    private String[] userApproveIdxs;
    
    private Integer userApproveIdx;
    
    /**
     * 在职状态
     */
    private Integer inStatus;
    
    /**
     * 入职时间
     */
    private Date inDate;
    
    /**
     * 离职时间
     */
    private Date outDate;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 所属组名
     */
    private String groupName;
    
    public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
    
	/**
	 * 用户所属中心名
	 */
	private String centerName;
	
	/**
	 * 用户所属中心Id
	 */
	private Integer centerId;
	
    public Integer getCenterId() {
		return centerId;
	}

	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public Integer getUserId()
    {
        return userId;
    }
    
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }
    
    public String getUserCode()
    {
        return userCode;
    }
    
    public void setUserCode(String userCode)
    {
        this.userCode = userCode == null ? null : userCode.trim();
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password == null ? null : password.trim();
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName == null ? null : userName.trim();
    }
    
    public String getSex()
    {
        return sex;
    }
    
    public void setSex(String sex)
    {
        this.sex = sex == null ? null : sex.trim();
    }
    
    public Integer getGroupId()
    {
        return groupId;
    }
    
    public void setGroupId(Integer groupId)
    {
        this.groupId = groupId;
    }
    
    public Date getDateCreate()
    {
        return dateCreate;
    }
    
    public void setDateCreate(Date dateCreate)
    {
        this.dateCreate = dateCreate;
    }
    
    public Integer getUserIdCreate()
    {
        return userIdCreate;
    }
    
    public void setUserIdCreate(Integer userIdCreate)
    {
        this.userIdCreate = userIdCreate;
    }
    
    public String getDelFlag()
    {
        return delFlag;
    }
    
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }
    
    //	public List<Auth> getAuthList()
    //    {
    //        return authList;
    //    }
    //
    //    public void setAuthList(List<Auth> authList)
    //    {
    //        this.authList = authList;
    //    }
    //
    //    public List<Role> getRoleList()
    //    {
    //        return roleList;
    //    }
    //
    //    public void setRoleList(List<Role> roleList)
    //    {
    //        this.roleList = roleList;
    //    }
    
    public String getPostName()
    {
        return postName;
    }
    
    public void setPostName(String postName)
    {
        this.postName = postName;
    }
    
    public String[] getPostIds()
    {
        return postIds;
    }
    
    public void setPostIds(String[] postIds)
    {
        this.postIds = postIds;
    }
    
    public String getCellphone()
    {
        return cellphone;
    }
    
    public void setCellphone(String cellphone)
    {
        this.cellphone = cellphone;
    }
    
    public String getCredentialsNum()
    {
        return credentialsNum;
    }
    
    public void setCredentialsNum(String credentialsNum)
    {
        this.credentialsNum = credentialsNum;
    }
    
    public Integer getCredentials()
    {
        return credentials;
    }
    
    public void setCredentials(Integer credentials)
    {
        this.credentials = credentials;
    }
    
    public Integer getUserApproveIdx()
    {
        return userApproveIdx;
    }
    
    public void setUserApproveIdx(Integer userApproveIdx)
    {
        this.userApproveIdx = userApproveIdx;
    }
    
    public Integer getInStatus()
    {
        return inStatus;
    }
    
    public void setInStatus(Integer inStatus)
    {
        this.inStatus = inStatus;
    }
    
    public Date getInDate()
    {
        return inDate;
    }
    
    public void setInDate(Date inDate)
    {
        this.inDate = inDate;
    }
    
    public Date getOutDate()
    {
        return outDate;
    }
    
    public void setOutDate(Date outDate)
    {
        this.outDate = outDate;
    }
    
    public String[] getUserApproveIdxs()
    {
        return userApproveIdxs;
    }
    
    public void setUserApproveIdxs(String[] userApproveIdxs)
    {
        this.userApproveIdxs = userApproveIdxs;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getJobNo()
    {
        return jobNo;
    }
    
    public void setJobNo(String jobNo)
    {
        this.jobNo = jobNo;
    }

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}
    
}