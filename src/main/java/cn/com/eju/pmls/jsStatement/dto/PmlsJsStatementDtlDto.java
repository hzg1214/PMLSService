package cn.com.eju.pmls.jsStatement.dto;

import cn.com.eju.pmls.jsStatement.model.PmlsJsStatementDtl;

public class PmlsJsStatementDtlDto extends PmlsJsStatementDtl{

    private Integer rId;//报备ID

    private Integer reportDetailId;

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public Integer getReportDetailId() {
        return reportDetailId;
    }

    public void setReportDetailId(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }
}