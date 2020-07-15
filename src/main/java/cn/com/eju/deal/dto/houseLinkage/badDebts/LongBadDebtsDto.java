package cn.com.eju.deal.dto.houseLinkage.badDebts;

import java.io.Serializable;

/**
 * Created by luhaidan on 2019/12/16.
 */
public class LongBadDebtsDto implements Serializable{

    private static final long serialVersionUID = 9223217567716907503L;

    private Integer Id;
    //序号
    private String rowNum;
    //城市编号
    private String cityNo;
    //城市名称
    private String cityName;
    //项目编号
    private String projectNo;
    //项目名称
    private String projectName;
    //报备编号
    private String reportId;
    //项目归属核算
    private String accountProject;

    //甲方名称
    private String partnerNm;

    //开发商名称
    private String devCompany;

    private String isBigCustomer;
    private String bigCustomer;

    //CRM建项日期
    private String projectCrtDt;
    //销售状态
    private String saleStatusNm;
    //签约状态
    private String signStatusNm;
    //OA立项完成日期
    private String budgetDate;
    //甲方结算节点
    private String commissionConditionNm;
    //合同周期
    private String htDate;
    //垫佣跟踪-是否垫佣
    private String isPadCom;

    //账龄跟踪-应计未回款-账龄合计
    private String yjAmount;

    //账龄跟踪-应收未回款-账龄合计
    private String ysAmount;


    public String getHtDate() {
        return htDate;
    }

    public void setHtDate(String htDate) {
        this.htDate = htDate;
    }

    public String getIsBigCustomer() {
        return isBigCustomer;
    }

    public void setIsBigCustomer(String isBigCustomer) {
        this.isBigCustomer = isBigCustomer;
    }

    public String getBigCustomer() {
        return bigCustomer;
    }

    public void setBigCustomer(String bigCustomer) {
        this.bigCustomer = bigCustomer;
    }


    public String getRowNum() {
        return rowNum;
    }

    public void setRowNum(String rowNum) {
        this.rowNum = rowNum;
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public String getProjectCrtDt() {
        return projectCrtDt;
    }

    public void setProjectCrtDt(String projectCrtDt) {
        this.projectCrtDt = projectCrtDt;
    }

    public String getSaleStatusNm() {
        return saleStatusNm;
    }

    public void setSaleStatusNm(String saleStatusNm) {
        this.saleStatusNm = saleStatusNm;
    }

    public String getSignStatusNm() {
        return signStatusNm;
    }

    public void setSignStatusNm(String signStatusNm) {
        this.signStatusNm = signStatusNm;
    }

    public String getBudgetDate() {
        return budgetDate;
    }

    public void setBudgetDate(String budgetDate) {
        this.budgetDate = budgetDate;
    }

    public String getCommissionConditionNm() {
        return commissionConditionNm;
    }

    public void setCommissionConditionNm(String commissionConditionNm) {
        this.commissionConditionNm = commissionConditionNm;
    }

    public String getIsPadCom() {
        return isPadCom;
    }

    public void setIsPadCom(String isPadCom) {
        this.isPadCom = isPadCom;
    }

    public String getYjAmount() {
        return yjAmount;
    }

    public void setYjAmount(String yjAmount) {
        this.yjAmount = yjAmount;
    }

    public String getYsAmount() {
        return ysAmount;
    }

    public void setYsAmount(String ysAmount) {
        this.ysAmount = ysAmount;
    }

    public String getPartnerNm() {
        return partnerNm;
    }

    public void setPartnerNm(String partnerNm) {
        this.partnerNm = partnerNm;
    }

    public String getDevCompany() {
        return devCompany;
    }

    public void setDevCompany(String devCompany) {
        this.devCompany = devCompany;
    }

    public String getAccountProject() {
        return accountProject;
    }

    public void setAccountProject(String accountProject) {
        this.accountProject = accountProject;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
