package cn.com.eju.deal.scene.commission.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.eju.deal.dto.houseLinkage.estate.EstateDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.core.support.AppMsg;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateSearchResultDto;
import cn.com.eju.deal.dto.scene.commission.SceneCommissionDetailSearchResultDto;
import cn.com.eju.deal.dto.scene.commission.SceneCommissionSearchResultDto;
import cn.com.eju.deal.fangyou.service.FangyouService;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateMapper;
import cn.com.eju.deal.houseLinkage.estate.model.Estate;
import cn.com.eju.deal.houseLinkage.estate.model.EstateSearchResult;
import cn.com.eju.deal.houseLinkage.report.dao.ReportDetailMapper;
import cn.com.eju.deal.houseLinkage.report.dao.ReportMapper;
import cn.com.eju.deal.houseLinkage.report.model.Report;
import cn.com.eju.deal.houseLinkage.report.model.ReportDetail;
import cn.com.eju.deal.houseLinkage.statistic.service.StatisticService;
import cn.com.eju.deal.scene.commission.model.SceneCommissionDetailSearchResult;
import cn.com.eju.deal.store.service.StoreService;

/**
 * service层
 *
 * @author qianwei
 * @date 2016年3月22日 下午7:57:09
 */
@Service("sceneCommissionService")
public class SceneCommissionService extends BaseService {
	
	@Resource
    private EstateMapper estateMapper;
	
	@Resource
    private StatisticService statisticService;
	
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

    /**
     * 查询-佣金楼盘list
     *
     * @param queryParam
     * @return
     */
    //@Cacheable(cacheName = "testCache")
    public ResultData<List<SceneCommissionSearchResultDto>> querySceneCommissionList(Map<?, ?> param) throws Exception{
        //构建返回
        ResultData<List<SceneCommissionSearchResultDto>> resultData = new ResultData<List<SceneCommissionSearchResultDto>>();
        //查询
        final List<EstateDto> list = estateMapper.getSceneCommissionList(param);
        //转换
        List<SceneCommissionSearchResultDto> estateDtoList = new ArrayList<SceneCommissionSearchResultDto>();
        if (null != list && !list.isEmpty()) {
        	SceneCommissionSearchResultDto dto = null;
            for (int i = 0; i < list.size(); i++) {
            	dto = new SceneCommissionSearchResultDto();
                BeanUtils.copyProperties(list.get(i), dto);
                
                if(StringUtils.isNotBlank(list.get(i).getRealityCityNo()))
                    dto.setRealityCityNm(SystemParam.getCityNameByCityNo(list.get(i).getRealityCityNo()));
                if(StringUtils.isNotBlank(list.get(i).getCityNo()))
                    dto.setCityNoNm(SystemParam.getCityNameByCityNo(list.get(i).getCityNo()));
                
                dto.setEstateType(list.get(i).getMgtKbn());
                dto.setEstateTypeNm(list.get(i).getMgtKbnStr());
                //countDateTypeKbn
                // 带看
                String relation = statisticService.getCountReward(list.get(i).getEstateId(), "", 13502, (String)param.get("countDateTypeKbn"), (String)param.get("countDateStart"), (String)param.get("countDateEnd"), 13601, "0").split(":")[1];
                // 认筹
                String pledged = statisticService.getCountReward(list.get(i).getEstateId(), "", 13503, (String)param.get("countDateTypeKbn"), (String)param.get("countDateStart"), (String)param.get("countDateEnd"), 13601, "0").split(":")[1];
                // 认购
                String subscribed = statisticService.getCountReward(list.get(i).getEstateId(), "", 13504, (String)param.get("countDateTypeKbn"), (String)param.get("countDateStart"), (String)param.get("countDateEnd"), 13601, "0").split(":")[1];
                // 成交
                String bargain = statisticService.getCountReward(list.get(i).getEstateId(), "", 13505, (String)param.get("countDateTypeKbn"), (String)param.get("countDateStart"), (String)param.get("countDateEnd"), 13601, "0").split(":")[1];
                // 结佣
                String commission = statisticService.getCountReward(list.get(i).getEstateId(), "", 13505, (String)param.get("countDateTypeKbn"), (String)param.get("countDateStart"), (String)param.get("countDateEnd"), 13601, "1").split(":")[1];
                // 总业绩
                BigDecimal totalPerformance = new BigDecimal(bargain).add(new BigDecimal(commission));
                if(null == totalPerformance) {
                	dto.setTotalPerformance("");
                } else {
                	dto.setTotalPerformance(totalPerformance.toString()); // 总业绩
                }
                if ("14101".equals(param.get("rewardType")) && "0.00".equals(relation)) {
                	continue;
                }
                if ("14102".equals(param.get("rewardType")) && "0.00".equals(pledged)) {
                	continue;
                }
                if ("14103".equals(param.get("rewardType")) && "0.00".equals(subscribed)) {
                	continue;
                }
                if ("14104".equals(param.get("rewardType")) && "0.00".equals(bargain)) {
                	continue;
                }
                if ("14105".equals(param.get("rewardType")) && "0.00".equals(commission)) {
                	continue;
                }
                dto.setRelationReward(relation); // 带看奖励
                dto.setPledgedReward(String.valueOf(pledged)); // 认筹奖励
                dto.setSubscribedReward(String.valueOf(subscribed)); // 认购奖励
                dto.setBargainReward(String.valueOf(bargain)); // 成交奖励
                dto.setCommission(String.valueOf(commission)); // 佣金
                                
                estateDtoList.add(dto);
            }
        }
        resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(estateDtoList);
        return resultData;
    }
    
    
    /**
     * 查询-佣金明细list
     *
     * @param queryParam
     * @return
     */
    //@Cacheable(cacheName = "testCache")
    public ResultData<List<SceneCommissionDetailSearchResultDto>> querySceneCommissionDetailList(Map<?, ?> param) throws Exception{
    	Map<String, String> reMap = (Map<String, String>) param;
    	Integer pageSize = Integer.parseInt(reMap.get("pageSize"));
        Integer pageIndex = Integer.parseInt(reMap.get("pageIndex"));
        reMap.remove("pageSize");
        reMap.remove("pageIndex");
    	//构建返回
        ResultData<List<SceneCommissionDetailSearchResultDto>> resultData = new ResultData<List<SceneCommissionDetailSearchResultDto>>();
        //转换
        List<SceneCommissionDetailSearchResultDto> detailDtoList = new ArrayList<SceneCommissionDetailSearchResultDto>();
        //查询
        if (!StringUtil.isNotEmpty((String)reMap.get("progress"))) {
        	reMap.put("progress", "13502,13503,13504,13505,13507");
        }
        String[] progressTypeList = reMap.get("progress").toString().split(",");
        for (int i = 0; progressTypeList != null && i < progressTypeList.length; i++) {
        	reMap.put("status", progressTypeList[i]);
        	
        	final List<SceneCommissionDetailSearchResult> list = reportDetailMapper.selectSceneCommissionDetailList(reMap);
            
            if (null != list && !list.isEmpty()) {
            	SceneCommissionDetailSearchResultDto dto = null;
                for (int j = 0; j < list.size(); j++) {
                	dto = new SceneCommissionDetailSearchResultDto();
                	if (progressTypeList[i].equals("13507"))
                    {//实际不存在结佣进度 ，从成交进度转变
                		list.get(j).setProgress(13507);
                    }
                    BeanUtils.copyProperties(list.get(j), dto);

                    // 客户电话
                    String tel = list.get(j).getCustomerTel();
                    if (StringUtil.isNotEmpty(tel) && tel.length() == 11) {
                        if ("1".equals(list.get(j).getHideNumberFlg())) {
                            tel = tel.substring(0, 3) + "-****-" + tel.substring(7);
                        } else {
                            tel = tel.substring(0, 3) + "-" + tel.substring(3, 7) + "-" + tel.substring(7);
                        }
                    }
                    dto.setCustomerTel(tel);
                    // 进度
                    dto.setProgressNm(SystemParam.getDicValueByDicCode(String.valueOf(dto.getProgress())));
                    // 结算状态
                    dto.setAccountStatusNm(SystemParam.getDicValueByDicCode(String.valueOf(dto.getAccountStatus())));
                                    
                    detailDtoList.add(dto);
                }
            }
        }
        
        //返回分页的数据
        List<SceneCommissionDetailSearchResultDto> pageList = new ArrayList<SceneCommissionDetailSearchResultDto>();
        Integer endpage = pageIndex * pageSize;
        if(endpage > detailDtoList.size()){
            endpage = detailDtoList.size();
        }
       //当前显示页的数据
        for(int i = (pageIndex-1)*pageSize; i < endpage; i++){
            pageList.add(detailDtoList.get(i));
        }
                
        resultData.setTotalCount(detailDtoList.size()+"");
        resultData.setReturnData(pageList);
        
        //resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
        //resultData.setReturnData(detailDtoList);
        return resultData;
    }
    
    
    /**
     * 修改佣金
     *
     * @param param
     * @return
     */
    //@TriggersRemove(cacheName = "testCache", removeAll = true)
    public ResultData<Integer> sceneCommissionDetailModify(Map<?, ?> param)
        	throws Exception
    {
    	ResultData<Integer> resultData = new ResultData<Integer>();
    	int count = 0;
    	List<ReportDetail> reportDetails = getReportDetailsByParams(param, 0);
    	
    	for (ReportDetail reportDetail: reportDetails)
    	{
    		count += reportDetailMapper.updateReportDetailReward(reportDetail);
    	}
    	
    	resultData.setReturnData(count);
    	resultData.setReturnMsg(AppMsg.getString("COMMON.OPERATE_SUCCESS"));
    	return resultData;
    }
    
    /**
     * 确认结算
     *
     * @param param
     * @return
     */
    //@TriggersRemove(cacheName = "testCache", removeAll = true)
    public ResultData<Integer> sceneCommissionDetailConfirm(Map<?, ?> param)
        	throws Exception
    {
    	ResultData<Integer> resultData = new ResultData<Integer>();
    	int count = 0;
    	List<ReportDetail> reportDetails = getReportDetailsByParams(param, 1);
    	
    	for (ReportDetail reportDetail: reportDetails)
    	{
    		count += reportDetailMapper.updateReportDetailAccountReward(reportDetail);
    	}
    	
    	resultData.setReturnData(count);
    	resultData.setReturnMsg(AppMsg.getString("COMMON.OPERATE_SUCCESS"));
    	return resultData;
    }
    
    public List<ReportDetail> getReportDetailsByParams(Map<?, ?>param, int type) throws Exception
    {
    	List<ReportDetail> reportDetails = new ArrayList<ReportDetail>();
    	List<String> reportIds = new ArrayList<String>();
    	List<Integer> progresss = new ArrayList<Integer>();
    	List<BigDecimal> rewards = new ArrayList<BigDecimal>();//奖励金额或者结算金额
    	String[] reportStrs = ((String)param.get("reports")).split("\\|");
    	String[] params;
    	BigDecimal reward;
    	BigDecimal accountReward;
    	for (String reportStr: reportStrs)
    	{
    		params = reportStr.split(",");
    		reportIds.add(params[0]);
    		progresss.add(Integer.parseInt(params[1]));
    		Double rewardD = Double.parseDouble(params[2]);
    		rewards.add(BigDecimal.valueOf(rewardD.doubleValue()));
    	}
    	
    	Report report;
    	ReportDetail reportDetail;
    	HashMap<String, Object> queryParams = new HashMap<String, Object>();
    	for (int i = 0; i < reportIds.size(); i++)
    	{
    		queryParams.clear();
    		queryParams.put("reportId", reportIds.get(i));    	
    		report = reportMapper.getReport(queryParams).get(0);
    		queryParams.clear();
    		queryParams.put("estateId", report.getEstateId());
    		queryParams.put("companyId", report.getCompanyId());
    		queryParams.put("customerId", report.getCustomerId());
    		queryParams.put("progress", progresss.get(i));
    		reportDetail = reportDetailMapper.getReportDetail(queryParams).get(0);
    		if (type == 0)
    		{//修改佣金
    			reportDetail.setReward(rewards.get(i));
    		}
    		else
    		{//确认结算
    			reward = reportDetail.getReward();
    			accountReward = rewards.get(i);
    			reportDetail.setAccountReward(accountReward);
    			reportDetail.setAccountStatus(accountReward.intValue() >= reward.intValue()? 14201: (accountReward.intValue() > 0? 14203: 14202));
    		}
    		reportDetail.setProgress(progresss.get(i));//结佣 的重置为13507 
    		reportDetails.add(reportDetail);
    	}
    	return reportDetails;
    }
    
    /**
     * 查询楼盘名
     *
     * @param estateId
     * @return
     */
    public ResultData<String> getByEstateId(String estateId)
    	throws Exception
    {
    	ResultData<String> resultData = new ResultData<String>();
    	Estate estate = estateMapper.selectEstateInfo(estateId).get(0);
    	resultData.setReturnData(estate.getEstateNm());
    	resultData.setReturnMsg(AppMsg.getString("COMMON.OPERATE_SUCCESS"));
        return resultData;
    }
    
    /**
     * 对象转换MO--DTO
     *
     * @param craList
     * @return List<EstateSearchResultDto>
     */
    private List<EstateSearchResultDto> convertEstateSearchResultData(List<EstateSearchResult> craList)  throws Exception{
        List<EstateSearchResultDto> estateDtoList = new ArrayList<EstateSearchResultDto>();

        if (null != craList && !craList.isEmpty()) {
        	EstateSearchResultDto craDto = null;
            for (EstateSearchResult cra : craList) {
                craDto = new EstateSearchResultDto();
                BeanUtils.copyProperties(cra, craDto);
                estateDtoList.add(craDto);
            }
        }
        return estateDtoList;
    }
   
}
