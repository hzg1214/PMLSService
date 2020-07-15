package cn.com.eju.deal.houseLinkage.totalPackage.dao;


import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.dto.scene.commission.ReportdyDto;
import cn.com.eju.deal.houseLinkage.totalPackage.model.TotalPackageReport;

import java.util.List;
import java.util.Map;

public interface TotalPackageReportMapper extends IDao<TotalPackageReport> {
    /**
     * 获取待处理的数据
     *
     * @return
     */
    List<TotalPackageReport> getTotalPackageReportListForHandle();

    List<ReportdyDto> getReportdy(Map<String, Object> param);

    Integer createReportdy(ReportdyDto reportdy);

    Integer updateReportdy(ReportdyDto reportdy);




}