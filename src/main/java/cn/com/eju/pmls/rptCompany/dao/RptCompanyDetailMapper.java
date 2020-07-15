package cn.com.eju.pmls.rptCompany.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.pmls.rptCompany.dto.CompanyStoreDetailTitleDto;
import cn.com.eju.pmls.rptCompany.dto.RptCompanyDetailDto;

public interface RptCompanyDetailMapper {
    //经纪公司明细列表
    List<RptCompanyDetailDto> queryCompanyDetailList(Map<String, Object> queryParam);
    //改成存储过程查询或导出
    List<RptCompanyDetailDto> queryCompanyDetailListNew(Map<String, Object> queryParam);
    //获取表头
    CompanyStoreDetailTitleDto getCompanyStoreDetailTitle(Map<String,Object> resMap);
}