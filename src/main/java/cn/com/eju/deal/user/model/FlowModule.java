package cn.com.eju.deal.user.model;

import cn.com.eju.deal.core.model.BaseModel;

/**   
* 流程模块
* @author xulong
* @date 2015年11月20日 上午11:55:10
*/
public class FlowModule extends BaseModel {
    private Integer flowModuleId;

    private String flowModuleName;

    private String flowModuleCode;
    
    private Integer sortNo;
    
    /**
     * 流程状态内容
     */
    private String flowStatusName;

    /** 
    * 获取流程状态内容
    * @return flowStatusName
    */
    public String getFlowStatusName() {
        return flowStatusName;
    }

    /** 
    * 设置流程状态内容
    * @param flowStatusName
    */
    public void setFlowStatusName(String flowStatusName) {
        this.flowStatusName = flowStatusName == null ? null : flowStatusName.trim();
    }
    
    public Integer getFlowModuleId() {
        return flowModuleId;
    }

    public void setFlowModuleId(Integer flowModuleId) {
        this.flowModuleId = flowModuleId;
    }

    public String getFlowModuleName() {
        return flowModuleName;
    }

    public void setFlowModuleName(String flowModuleName) {
        this.flowModuleName = flowModuleName == null ? null : flowModuleName.trim();
    }

    public String getFlowModuleCode() {
        return flowModuleCode;
    }

    public void setFlowModuleCode(String flowModuleCode) {
        this.flowModuleCode = flowModuleCode == null ? null : flowModuleCode.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
}