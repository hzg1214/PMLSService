package cn.com.eju.deal.linkageWechat.model;

import java.util.Date;

/**   
* 新房联动微信 注册信息
* @author wenhui.zhang
* @date 2016年5月17日 下午5:24:44
*/
public class WXRegister {
    private Integer id;

    private String companyName;

    private String userName;

    private String phoneNumber;

    private String verifyCode;

    private String isAcceptFlag;

    private Date dateCreate;

    private Integer userIdCreate;

    private String delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode == null ? null : verifyCode.trim();
    }

    public String getIsAcceptFlag() {
        return isAcceptFlag;
    }

    public void setIsAcceptFlag(String isAcceptFlag) {
        this.isAcceptFlag = isAcceptFlag == null ? null : isAcceptFlag.trim();
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getUserIdCreate() {
        return userIdCreate;
    }

    public void setUserIdCreate(Integer userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}