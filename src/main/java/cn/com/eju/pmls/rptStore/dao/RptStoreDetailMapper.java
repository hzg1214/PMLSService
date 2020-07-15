package cn.com.eju.pmls.rptStore.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.pmls.rptCompany.dto.RptCompanyDetailDto;
import cn.com.eju.pmls.rptStore.dto.RptStoreDetailDto;

public interface RptStoreDetailMapper {
    //门店明细列表
    List<RptStoreDetailDto> queryStoreDetailList(Map<String, Object> queryParam);
    
    //改成存储过程查询或导出
    List<RptStoreDetailDto> queryStoreDetailListNew(Map<String, Object> queryParam);
}