package cn.com.eju.pmls.rptCompany.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.rptCompany.dao.RptCompanyDetailMapper;
import cn.com.eju.pmls.rptCompany.dto.CompanyStoreDetailTitleDto;
import cn.com.eju.pmls.rptCompany.dto.RptCompanyDetailDto;

@Service("rptCompanyDetailService")
public class RptCompanyDetailService extends BaseService {
	
    @Resource
    private RptCompanyDetailMapper rptCompanyDetailMapper;
    
    /**
     * desc:经纪公司明细列表
     * 2020年6月17日
     */
    public List<RptCompanyDetailDto> queryCompanyDetailList(Map<String, Object> queryParam) {
//        ResultData<List<RptCompanyDetailDto>> resultData = new ResultData<>();
//        int pageIndex = 1;
//        int pageSize = 10;
//        int curPage = 1;
//
//        if (queryParam.get("pageIndex") != null)
//            pageIndex = Integer.parseInt(queryParam.get("pageIndex").toString());
//        if (queryParam.get("pageSize") != null)
//            pageSize = Integer.parseInt(queryParam.get("pageSize").toString());
//        if (queryParam.get("curPage") != null)
//            curPage = Integer.parseInt(queryParam.get("curPage").toString());
//
//        queryParam.remove("pageIndex");
//        queryParam.remove("pageSize");
//        queryParam.remove("curPage");


        List<RptCompanyDetailDto> list = rptCompanyDetailMapper.queryCompanyDetailListNew(queryParam);  

//        if (null != list && list.size() > 0) {
//            int size = list.size();
//            resultData.setTotalCount(String.valueOf(size));
//            if (queryParam.get("optFlag") == null) {//导出标记
//                int endRow = pageIndex * pageSize;
//                list = list.subList((pageIndex - 1) * pageSize, endRow > size ? size : endRow);
//
//                queryParam.put("pageIndex", pageIndex);
//                queryParam.put("pageSize", pageSize);
//                queryParam.put("curPage", curPage);
//            }
//            resultData.setReturnData(list);
//        }
        return list;
    }
    
    /**
     * desc:获取回款跟踪数据表头
     * 2020年6月18日
     */
    public ResultData getTitle(Map<String, Object> queryParam) {
    	ResultData<CompanyStoreDetailTitleDto> resultData = new ResultData<>();
    	CompanyStoreDetailTitleDto companyStoreDetailDto= rptCompanyDetailMapper.getCompanyStoreDetailTitle(queryParam);
    	resultData.setReturnData(companyStoreDetailDto);
    	return resultData;
    }
    
}
