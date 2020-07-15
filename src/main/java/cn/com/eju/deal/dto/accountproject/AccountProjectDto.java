package cn.com.eju.deal.dto.accountproject;

import java.util.Date;
import java.util.List;

public class AccountProjectDto {

	private Integer rowNum;
	
	private Integer id;

	private String cityNo;
	
    private String cityName;

    private String accountProjectNo;
    
    private String accountProject;//编辑

    private List<String> accountProjectNos;//新增用到

    private Date dateCreate;

    private Date dateUpdate;

    private Integer userIdCreate;

    private String userNameCreate;

    private Boolean delFlag;
    
    public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountProjectNo() {
		return accountProjectNo;
	}

	public void setAccountProjectNo(String accountProjectNo) {
		this.accountProjectNo = accountProjectNo;
	}

	public String getAccountProject() {
		return accountProject;
	}

	public void setAccountProject(String accountProject) {
		this.accountProject = accountProject;
	}

	public List<String> getAccountProjectNos() {
		return accountProjectNos;
	}

	public void setAccountProjectNos(List<String> accountProjectNos) {
		this.accountProjectNos = accountProjectNos;
	}

	public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Integer getUserIdCreate() {
        return userIdCreate;
    }

    public void setUserIdCreate(Integer userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    public String getUserNameCreate() {
        return userNameCreate;
    }

    public void setUserNameCreate(String userNameCreate) {
        this.userNameCreate = userNameCreate;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

}
