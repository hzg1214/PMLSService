package cn.com.eju.deal.houseLinkage.custom.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.CityCascadeMapper;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.contract.dao.ContractProductMapper;
import cn.com.eju.deal.contract.dao.ContractStoreMapper;
import cn.com.eju.deal.contract.dao.ProductMapper;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.houseLinkage.custom.CustomInfoDto;
import cn.com.eju.deal.dto.houseLinkage.report.ReportDetailDto;
import cn.com.eju.deal.dto.houseLinkage.report.ReportDto;
import cn.com.eju.deal.dto.houseLinkage.report.ReportSearchResultDto;
import cn.com.eju.deal.fangyou.service.FangyouService;
import cn.com.eju.deal.houseLinkage.report.dao.ReportDetailMapper;
import cn.com.eju.deal.houseLinkage.report.dao.ReportMapper;
import cn.com.eju.deal.houseLinkage.report.model.Report;
import cn.com.eju.deal.houseLinkage.report.model.ReportDetail;
import cn.com.eju.deal.houseLinkage.report.model.ReportSearchResult;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.store.service.StoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * service层
 *
 * @author qianwei
 * @date 2016年3月22日 下午7:57:09
 */
@Service("customService")
public class CustomService extends BaseService {
    
	@Resource
    private ReportMapper reportMapper;
	
	@Resource
    private ReportDetailMapper reportDetailMapper;
	
	@Resource
    private StoreService storeService;

    @Resource
    private FangyouService fangyouService;

    @Resource
    private ContractMapper contractMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ContractProductMapper contractProductMapper;

    @Resource
    private ContractStoreMapper contractStoreMapper;

    @Resource
    private StoreMapper storeMapper;

    @Resource
    private CompanyMapper companyMapper;

    @Resource
    private FileRecordMainMapper fileRecordMainMapper;

    @Resource
    private CityCascadeMapper cityCascadeMapper;


//    @Cacheable(cacheName = "testCache")
    public CustomInfoDto getCustom(String estateId, String companyId, String customerId) throws Exception{
    	CustomInfoDto customInfoDto = new CustomInfoDto();
    	Map<String, String> param = new HashMap<String, String>();
    	param.put("estateId", estateId);
    	param.put("companyId", companyId);
    	param.put("customerId", customerId);
        // 获取客户信息
    	ReportDto ctaDto = new ReportDto();
    	List<Report> reportMdl = reportMapper.getReport(param);
    	if (reportMdl != null && reportMdl.size() > 0) {
    		//Model转换Dto
            BeanUtils.copyProperties(reportMdl.get(0), ctaDto);
    	}
        List<ReportDetail> detail = reportDetailMapper.getReportDetail(param);
        //转换
        List<ReportDetailDto> reportDtoList = convertReportDetailData(detail);
        
        customInfoDto.setReport(ctaDto);
        customInfoDto.setReportDetails(reportDtoList);
        return customInfoDto;
    }
    
    /**
     * 查询-list
     *
     * @param param
     * @return
     */
//    @Cacheable(cacheName = "testCache")
    public ResultData<List<ReportSearchResultDto>> queryList(Map<?, ?> param)  throws Exception{
        //构建返回
        ResultData<List<ReportSearchResultDto>> resultData = new ResultData<List<ReportSearchResultDto>>();
        //查询
        Map<String, String> reMap = (Map<String, String>) param;
        if (StringUtil.isNotEmpty(reMap.get("latestProgress"))) {
            //进程在成销之前-进度加1，否则不变
            Integer latestProgress=Integer.valueOf(reMap.get("latestProgress")) ;

            if (StringUtil.isNotEmpty(reMap.get("latestProgress")) && Integer.valueOf(reMap.get("latestProgress")) < 13505) {
                reMap.put("latestProgress", String.valueOf(Integer.valueOf(reMap.get("latestProgress")) + 1));
            } else {
                reMap.put("latestProgress", reMap.get("latestProgress"));
            }
            //进程是成销-状态有效，否则状态未认定
            if ( latestProgress.intValue() == 13505) {
                reMap.put("confirmStatus", "13601");
                reMap.put("brokerageStatus", "22001");
            } else if ( latestProgress.intValue() == 13504){
                reMap.put("confirmStatus", "13603");
            }else if(latestProgress.intValue() == 13507){
                reMap.put("latestProgress", "13505");
                reMap.put("confirmStatus", "13601");
                reMap.put("brokerageStatus", "22002,22003");
            }
        }
        final List<ReportSearchResult> craList = reportMapper.selectReportList(reMap);

        //转换
        List<ReportSearchResultDto> reportDtoList = convertReportSearchResultData(craList);
        resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(reportDtoList);
        return resultData;
    }

    /**
     * 对象转换MO--DTO
     *
     * @param craList
     * @return List<ContractSearchResultDto>
     */
    private List<ReportSearchResultDto> convertReportSearchResultData(List<ReportSearchResult> craList)  throws Exception{
        List<ReportSearchResultDto> customDtoList = new ArrayList<ReportSearchResultDto>();

        if (null != craList && !craList.isEmpty()) {
        	ReportSearchResultDto craDto = null;
            for (ReportSearchResult cra : craList) {
            	if (cra.getConfirmStatus() == 13603) {
            		cra.setConfirmStatus(13601);
            		cra.setConfirmStatusNm(SystemParam.getDicValueByDicCode(String.valueOf(cra.getConfirmStatus())));
            		cra.setLatestProgress(cra.getLatestProgress() - 1);
            		cra.setLatestProgressNm(SystemParam.getDicValueByDicCode(String.valueOf(cra.getLatestProgress())));
            	}
                craDto = new ReportSearchResultDto();
                BeanUtils.copyProperties(cra, craDto);
                customDtoList.add(craDto);
            }
        }
        return customDtoList;
    }
    
    /**
     * 对象转换MO--DTO
     *
     * @param craList
     * @return List<ReportDetailDto>
     */
    private List<ReportDetailDto> convertReportDetailData(List<ReportDetail> craList)  throws Exception{
        List<ReportDetailDto> reportDtoList = new ArrayList<ReportDetailDto>();

        if (null != craList && !craList.isEmpty()) {
        	ReportDetailDto craDto = null;
            for (ReportDetail cra : craList) {
            	if (cra.getConfirmStatus() == 13603) {
            		continue;
            	}
                craDto = new ReportDetailDto();
                BeanUtils.copyProperties(cra, craDto);
                reportDtoList.add(craDto);
            }
        }
        return reportDtoList;
    }
}
