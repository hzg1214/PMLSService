package cn.com.eju.pmls.report.projectDetail.dto;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.util.StringUtil;

/**
 * Created by haidan on 2020/6/17.
 */
public class ProjectDetailDto {
    private Integer rowNum;
    /**
     * 项目归属区域
     */
    private String gsRegion;
    /**
     * 项目归属城市
     */
    private String gsCity;
    /**
     * 项目所在城市
     */
    private String szCity;
    /**
     * 项目归属项目部
     */
    private String gsDepartment;
    /**
     * 项目编码
     */
    private String projectNo;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 项目状态
     */
    private String projectStatus;
    /**
     * 业务模式
     */
    private String businessModel;
    /**
     * 开发商品牌
     */
    private String developerBrandName;
    /**
     * 开发商名称
     */
    private String developerName;
    /**
     * 是否大客户
     */
    private String isBigCustomer;
    /**
     * 合作方类型
     */
    private String partnerType;
    /**
     * 合作方品牌
     */
    private String partnerBrandName;
    /**
     * 合作方名称
     */
    private String partnerName;
    /**
     * 共场
     */
    private String totalFieldFlag;
    /**
     * 是否直签
     */
    private String directSignFlag;
    /**
     * 合作开始日期
     */
    private String cooperationDtStart;
    /**
     * 合作结束时间
     */
    private String cooperationDtEnd;
    /**
     * 物业类型
     */
    private String mgtKbn;
    private String managementType;
    /**
     * 楼盘地理城市
     */
    private String realCity;
    /**
     * 楼盘区域
     */
    private String realDistrict;
    /**
     * 开发负责人
     */
    private String sceneEmpName;
    /**
     * 开发负责人工号
     */
    private String sceneEmpCode;
    /**
     * 项目负责人
     */
    private String projectLeaderEmpName;
    /**
     * 项目负责人工号
     */
    private String projectLeaderEmpCode;

    /**
     * 合同编号
     */
    private String srlHtNo;

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public String getGsRegion() {
        return gsRegion;
    }

    public void setGsRegion(String gsRegion) {
        this.gsRegion = gsRegion;
    }

    public String getGsCity() {
        return gsCity;
    }

    public void setGsCity(String gsCity) {
        this.gsCity = gsCity;
    }

    public String getSzCity() {
        return szCity;
    }

    public void setSzCity(String szCity) {
        this.szCity = szCity;
    }

    public String getGsDepartment() {
        return gsDepartment;
    }

    public void setGsDepartment(String gsDepartment) {
        this.gsDepartment = gsDepartment;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getBusinessModel() {
        return businessModel;
    }

    public void setBusinessModel(String businessModel) {
        this.businessModel = businessModel;
    }

    public String getDeveloperBrandName() {
        return developerBrandName;
    }

    public void setDeveloperBrandName(String developerBrandName) {
        this.developerBrandName = developerBrandName;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getIsBigCustomer() {
        return isBigCustomer;
    }

    public void setIsBigCustomer(String isBigCustomer) {
        this.isBigCustomer = isBigCustomer;
    }

    public String getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(String partnerType) {
        this.partnerType = partnerType;
    }

    public String getPartnerBrandName() {
        return partnerBrandName;
    }

    public void setPartnerBrandName(String partnerBrandName) {
        this.partnerBrandName = partnerBrandName;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getTotalFieldFlag() {
        return totalFieldFlag;
    }

    public void setTotalFieldFlag(String totalFieldFlag) {
        this.totalFieldFlag = totalFieldFlag;
    }

    public String getDirectSignFlag() {
        return directSignFlag;
    }

    public void setDirectSignFlag(String directSignFlag) {
        this.directSignFlag = directSignFlag;
    }

    public String getCooperationDtStart() {
        return cooperationDtStart;
    }

    public void setCooperationDtStart(String cooperationDtStart) {
        this.cooperationDtStart = cooperationDtStart;
    }

    public String getCooperationDtEnd() {
        return cooperationDtEnd;
    }

    public void setCooperationDtEnd(String cooperationDtEnd) {
        this.cooperationDtEnd = cooperationDtEnd;
    }

    public String getManagementType() {
        return getStrByCode(mgtKbn);
    }

    public void setManagementType(String managementType) {
        this.managementType = managementType;
    }

    public String getRealCity() {
        return realCity;
    }

    public void setRealCity(String realCity) {
        this.realCity = realCity;
    }

    public String getRealDistrict() {
        return realDistrict;
    }

    public void setRealDistrict(String realDistrict) {
        this.realDistrict = realDistrict;
    }

    public String getSceneEmpName() {
        return sceneEmpName;
    }

    public void setSceneEmpName(String sceneEmpName) {
        this.sceneEmpName = sceneEmpName;
    }

    public String getSceneEmpCode() {
        return sceneEmpCode;
    }

    public void setSceneEmpCode(String sceneEmpCode) {
        this.sceneEmpCode = sceneEmpCode;
    }

    public String getProjectLeaderEmpName() {
        return projectLeaderEmpName;
    }

    public void setProjectLeaderEmpName(String projectLeaderEmpName) {
        this.projectLeaderEmpName = projectLeaderEmpName;
    }

    public String getProjectLeaderEmpCode() {
        return projectLeaderEmpCode;
    }

    public void setProjectLeaderEmpCode(String projectLeaderEmpCode) {
        this.projectLeaderEmpCode = projectLeaderEmpCode;
    }

    public String getSrlHtNo() {
        return srlHtNo;
    }

    public void setSrlHtNo(String srlHtNo) {
        this.srlHtNo = srlHtNo;
    }

    public String getMgtKbn() {
        return mgtKbn;
    }

    public void setMgtKbn(String mgtKbn) {
        this.mgtKbn = mgtKbn;
    }

    private String getStrByCode(String code) {
        String value = "";
        if (StringUtil.isNotEmpty(code)) {
            String[] typeArr = code.split(",");
            for (String type : typeArr) {
                if (StringUtil.isEmpty(value)) {
                    value = SystemParam.getDicValueByDicCode(type);
                } else {
                    value = value + "、" + SystemParam.getDicValueByDicCode(type);
                }
            }
        }
        return value;
    }
}
