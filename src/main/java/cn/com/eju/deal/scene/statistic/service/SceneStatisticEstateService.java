package cn.com.eju.deal.scene.statistic.service;

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
import cn.com.eju.deal.dto.scene.statistic.SceneStatisticCompanySearchResultDto;
import cn.com.eju.deal.dto.scene.statistic.SceneStatisticDetailSearchResultDto;
import cn.com.eju.deal.dto.scene.statistic.SceneStatisticEstateSearchResultDto;
import cn.com.eju.deal.fangyou.service.FangyouService;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateMapper;
import cn.com.eju.deal.houseLinkage.estate.model.Estate;
import cn.com.eju.deal.houseLinkage.estate.model.EstateSearchResult;
import cn.com.eju.deal.houseLinkage.report.dao.ReportDetailMapper;
import cn.com.eju.deal.houseLinkage.report.dao.ReportMapper;
import cn.com.eju.deal.houseLinkage.statistic.service.StatisticService;
import cn.com.eju.deal.scene.statistic.model.SceneStatisticCompanySearchResult;
import cn.com.eju.deal.scene.statistic.model.SceneStatisticDetailSearchResult;
import cn.com.eju.deal.store.service.StoreService;

/**
 * service层
 *
 * @author qianwei
 * @date 2016年3月22日 下午7:57:09
 */
@Service("sceneStatisticEstateService")
public class SceneStatisticEstateService extends BaseService {
	
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
     * 查询-统计楼盘list
     *
     * @param queryParam
     * @return
     */
//    @Cacheable(cacheName = "testCache")
    public ResultData<List<SceneStatisticEstateSearchResultDto>> querySceneStatisticEstateList(Map<?, ?> param) throws Exception{
        //构建返回
        ResultData<List<SceneStatisticEstateSearchResultDto>> resultData = new ResultData<List<SceneStatisticEstateSearchResultDto>>();
        //查询
        final List<EstateDto> list = estateMapper.getSceneStatisticEstateList(param);
        //转换
        List<SceneStatisticEstateSearchResultDto> estateDtoList = new ArrayList<SceneStatisticEstateSearchResultDto>();
        if (null != list && !list.isEmpty()) {
        	SceneStatisticEstateSearchResultDto dto = null;
            for (int i = 0; i < list.size(); i++) {
            	dto = new SceneStatisticEstateSearchResultDto();
                BeanUtils.copyProperties(list.get(i), dto);
                
                if(StringUtils.isNotBlank(list.get(i).getRealityCityNo()))
                    dto.setRealityCityNm(SystemParam.getCityNameByCityNo(list.get(i).getRealityCityNo()));
                if(StringUtils.isNotBlank(list.get(i).getCityNo()))
                    dto.setCityNoNm(SystemParam.getCityNameByCityNo(list.get(i).getCityNo()));
                
                
                dto.setEstateType(list.get(i).getMgtKbn());
                dto.setEstateTypeNm(list.get(i).getMgtKbnStr());
                if (param.get("countDateTypeKbn") != null && !"".equals((String)param.get("countDateTypeKbn")) && !"null".equals((String)param.get("countDateTypeKbn"))) {
                	dto.setCountDateTypeKbn((String)param.get("countDateTypeKbn"));
                } else {
                	dto.setCountDateTypeKbn("null");
                }
                if (param.get("countDateStart") != null && !"".equals((String)param.get("countDateStart")) && !"null".equals((String)param.get("countDateStart"))) {
                	dto.setCountDateStart((String)param.get("countDateStart"));
                } else {
                	dto.setCountDateStart("null");
                }
                if (param.get("countDateEnd") != null && !"".equals((String)param.get("countDateEnd")) && !"null".equals((String)param.get("countDateEnd"))) {
                	dto.setCountDateEnd((String)param.get("countDateEnd"));
                } else {
                	dto.setCountDateEnd("null");
                }
                String reportPeopleNum = statisticService.getCountRewardConfirmStatusValid(list.get(i).getEstateId(), "", 13501, (String)param.get("countDateTypeKbn"), (String)param.get("countDateStart"), (String)param.get("countDateEnd"), 13601, "0");
                String relationPeopleNum = statisticService.getCountRewardConfirmStatusValid(list.get(i).getEstateId(), "", 13502, (String)param.get("countDateTypeKbn"), (String)param.get("countDateStart"), (String)param.get("countDateEnd"), 13601, "0");
                String pledgedPeopleNum = statisticService.getCountRewardConfirmStatusValid(list.get(i).getEstateId(), "", 13503, (String)param.get("countDateTypeKbn"), (String)param.get("countDateStart"), (String)param.get("countDateEnd"), 13601, "0");
                String subscribedPeopleNum = statisticService.getCountRewardConfirmStatusValid(list.get(i).getEstateId(), "", 13504, (String)param.get("countDateTypeKbn"), (String)param.get("countDateStart"), (String)param.get("countDateEnd"), 13601, "0");
                String signPeopleNum = statisticService.getCountRewardConfirmStatusValid(list.get(i).getEstateId(), "", 13505, (String)param.get("countDateTypeKbn"), (String)param.get("countDateStart"), (String)param.get("countDateEnd"), 13601, "0");
                String getBackPeopleNum = statisticService.getCountRewardConfirmStatusValid(list.get(i).getEstateId(), "", 13506, (String)param.get("countDateTypeKbn"), (String)param.get("countDateStart"), (String)param.get("countDateEnd"), 13601, "0");
                String commissionPeopleNum = statisticService.getCountRewardConfirmStatusValid(list.get(i).getEstateId(), "", 13505, (String)param.get("countDateTypeKbn"), (String)param.get("countDateStart"), (String)param.get("countDateEnd"), 13601, "1");
                
                //增加面积等四列
                SceneStatisticEstateSearchResultDto ssesrDtoTemp = statisticService.getCountAreaAndAmount(list.get(i).getEstateId(), "", (String)param.get("countDateTypeKbn"), (String)param.get("countDateStart"), (String)param.get("countDateEnd"), 13601, "0");
                if(ssesrDtoTemp!=null){
                	dto.setRoughAreaSum(ssesrDtoTemp.getRoughAreaSum());
                	dto.setRoughAmountSum(ssesrDtoTemp.getRoughAmountSum());
                	dto.setDealAreaSum(ssesrDtoTemp.getDealAreaSum());
                	dto.setDealAmountSum(ssesrDtoTemp.getDealAmountSum());
                }else{
                	dto.setRoughAreaSum("0.00");
                	dto.setRoughAmountSum("0.00");
                	dto.setDealAreaSum("0.00");
                	dto.setDealAmountSum("0.00");
                }
                
                String[] arrayReport = reportPeopleNum.split(":");
                String[] arrayRelation = relationPeopleNum.split(":");
                String[] arrayPledged = pledgedPeopleNum.split(":");
                String[] arraySubscribed = subscribedPeopleNum.split(":");
                String[] arraySign = signPeopleNum.split(":");
                String[] arrayGetBack = getBackPeopleNum.split(":");
                String[] arrayCommission = commissionPeopleNum.split(":");

                dto.setReportPeopleNum(arrayReport[0]);
                dto.setRelationPeopleNum(arrayRelation[0]);
                dto.setPledgedPeopleNum(arrayPledged[0]);
                dto.setSubscribedPeopleNum(arraySubscribed[0]);
                dto.setSignPeopleNum(arraySign[0]);
                dto.setGetBackPeopleNum(arrayGetBack[0]);
                dto.setRelationReward(arrayRelation[1]);
                dto.setPledgedReward(arrayPledged[1]);
                dto.setSubscribedReward(arraySubscribed[1]);
                dto.setBargainReward(arraySign[1]);
                dto.setCommission(arrayCommission[1]);
                String tempSignValue = arraySign[1];
                if (arraySign[1].indexOf(".") > 0) {
                    tempSignValue = arraySign[1].substring(0, arraySign[1].indexOf("."));
                }
                String tempCommissionValue = dto.getCommission();
                if (dto.getCommission().indexOf(".") > 0) {
                    tempCommissionValue = dto.getCommission().substring(0, dto.getCommission().indexOf("."));
                }
                BigDecimal bargain = new BigDecimal(Integer.valueOf(tempSignValue));
                dto.setTotalPerformance(String.valueOf(bargain.add(new BigDecimal(Integer.valueOf(tempCommissionValue)))));
                                
                estateDtoList.add(dto);
            }
        }
        resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(estateDtoList);
        return resultData;
    }
    
    
    /**
     * 查询-统计公司list
     *
     * @param queryParam
     * @return
     */
//    @Cacheable(cacheName = "testCache")
    public ResultData<List<SceneStatisticCompanySearchResultDto>> querySceneStatisticCompanyList(Map<?, ?> param) throws Exception{
        //构建返回
        ResultData<List<SceneStatisticCompanySearchResultDto>> resultData = new ResultData<List<SceneStatisticCompanySearchResultDto>>();
        //查询
        final List<SceneStatisticCompanySearchResult> list = reportMapper.selectSceneStatisticCompanyList(param);
        //转换
        List<SceneStatisticCompanySearchResultDto> companyDtoList = new ArrayList<SceneStatisticCompanySearchResultDto>();
        if (null != list && !list.isEmpty()) {
        	SceneStatisticCompanySearchResultDto dto = null;
            for (int i = 0; i < list.size(); i++) {
            	dto = new SceneStatisticCompanySearchResultDto();
                BeanUtils.copyProperties(list.get(i), dto);
                if (param.get("countDateTypeKbn") != null && !"".equals((String)param.get("countDateTypeKbn")) && !"null".equals((String)param.get("countDateTypeKbn"))) {
                	dto.setCountDateTypeKbn((String)param.get("countDateTypeKbn"));
                } else {
                	dto.setCountDateTypeKbn("null");
                }
                if (param.get("countDateStart") != null && !"".equals((String)param.get("countDateStart")) && !"null".equals((String)param.get("countDateStart"))) {
                	dto.setCountDateStart((String)param.get("countDateStart"));
                } else {
                	dto.setCountDateStart("null");
                }
                if (param.get("countDateEnd") != null && !"".equals((String)param.get("countDateEnd")) && !"null".equals((String)param.get("countDateEnd"))) {
                	dto.setCountDateEnd((String)param.get("countDateEnd"));
                } else {
                	dto.setCountDateEnd("null");
                }
                String reportPeopleNum = statisticService.getCountReward(list.get(i).getEstateId(), list.get(i).getCompanyId(), 13501, (String)param.get("countDateTypeKbn"), (String)param.get("countDateStart"), (String)param.get("countDateEnd"), 13601, "0");
                String relationPeopleNum = statisticService.getCountReward(list.get(i).getEstateId(), list.get(i).getCompanyId(), 13502, (String)param.get("countDateTypeKbn"), (String)param.get("countDateStart"), (String)param.get("countDateEnd"), 13601, "0");
                String pledgedPeopleNum = statisticService.getCountReward(list.get(i).getEstateId(), list.get(i).getCompanyId(), 13503, (String)param.get("countDateTypeKbn"), (String)param.get("countDateStart"), (String)param.get("countDateEnd"), 13601, "0");
                String subscribedPeopleNum = statisticService.getCountReward(list.get(i).getEstateId(), list.get(i).getCompanyId(), 13504, (String)param.get("countDateTypeKbn"), (String)param.get("countDateStart"), (String)param.get("countDateEnd"), 13601, "0");
                String signPeopleNum = statisticService.getCountReward(list.get(i).getEstateId(), list.get(i).getCompanyId(), 13505, (String)param.get("countDateTypeKbn"), (String)param.get("countDateStart"), (String)param.get("countDateEnd"), 13601, "0");
                String getBackPeopleNum = statisticService.getCountReward(list.get(i).getEstateId(), list.get(i).getCompanyId(), 13506, (String)param.get("countDateTypeKbn"), (String)param.get("countDateStart"), (String)param.get("countDateEnd"), 13601, "0");
                String commissionPeopleNum = statisticService.getCountReward(list.get(i).getEstateId(), list.get(i).getCompanyId(), 13505, (String)param.get("countDateTypeKbn"), (String)param.get("countDateStart"), (String)param.get("countDateEnd"), 13601, "1");

                String[] arrayReport = reportPeopleNum.split(":");
                String[] arrayRelation = relationPeopleNum.split(":");
                String[] arrayPledged = pledgedPeopleNum.split(":");
                String[] arraySubscribed = subscribedPeopleNum.split(":");
                String[] arraySign = signPeopleNum.split(":");
                String[] arrayGetBack = getBackPeopleNum.split(":");
                String[] arrayCommission = commissionPeopleNum.split(":");
                
                //计算大定面积，金额和成销面积，金额 --开始
                Map<String, Object> queryMap = new HashMap<String, Object>();
                queryMap.put("estateId", dto.getEstateId());
                List<SceneStatisticEstateSearchResultDto> ssesrDtoTemp = reportDetailMapper.getCountAreaAndAmountGroupByTwoId(queryMap);
                if(ssesrDtoTemp!=null && ssesrDtoTemp.size()>0){
                    for (SceneStatisticEstateSearchResultDto ssesrdto : ssesrDtoTemp)
                    {
                        
                        if(ssesrdto.getCompanyId().equals(dto.getCompanyId())){
                            //大定面积
                            dto.setDealAreaSum(ssesrdto.getDealAreaSum());
                            //大定金额
                            dto.setDealAmountSum(ssesrdto.getDealAmountSum());
                            //成销金额
                            dto.setRoughAmountSum(ssesrdto.getRoughAmountSum());
                            //成销面积
                            dto.setRoughAreaSum(ssesrdto.getRoughAreaSum());
                           
                        }
                        
                        
                    }
                   
                }
                //计算大定面积，金额和成销面积，金额 --结束
                
                dto.setReportPeopleNum(arrayReport[0]);
                dto.setRelationPeopleNum(arrayRelation[0]);
                dto.setPledgedPeopleNum(arrayPledged[0]);
                dto.setSubscribedPeopleNum(arraySubscribed[0]);
                dto.setSignPeopleNum(arraySign[0]);
                dto.setGetBackPeopleNum(arrayGetBack[0]);
                dto.setRelationReward(arrayRelation[1]);
                dto.setPledgedReward(arrayPledged[1]);
                dto.setSubscribedReward(arraySubscribed[1]);
                dto.setBargainReward(arraySign[1]);
                dto.setCommission(arrayCommission[1]);
                String tempSignValue = arraySign[1];
                if (arraySign[1].indexOf(".") > 0) {
                    tempSignValue = arraySign[1].substring(0, arraySign[1].indexOf("."));
                }
                String tempCommissionValue = dto.getCommission();
                if (dto.getCommission().indexOf(".") > 0) {
                    tempCommissionValue = dto.getCommission().substring(0, dto.getCommission().indexOf("."));
                }
                BigDecimal bargain = new BigDecimal(Integer.valueOf(tempSignValue));
                dto.setTotalPerformance(String.valueOf(bargain.add(new BigDecimal(Integer.valueOf(tempCommissionValue)))));
                                
                companyDtoList.add(dto);
            }
        }
        resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(companyDtoList);
        return resultData;
    }

    /**
     * 查询-统计详细list
     *
     * @param queryParam
     * @return
     */
//    @Cacheable(cacheName = "testCache")
    public ResultData<List<SceneStatisticDetailSearchResultDto>> querySceneStatisticDetailList(Map<?, ?> param) throws Exception{
        //构建返回
        ResultData<List<SceneStatisticDetailSearchResultDto>> resultData = new ResultData<List<SceneStatisticDetailSearchResultDto>>();
        Map<String, String> reMap = (Map<String, String>) param;
        Integer pageSize = Integer.parseInt(reMap.get("pageSize"));
        Integer pageIndex = Integer.parseInt(reMap.get("pageIndex"));
        reMap.remove("pageSize");
        reMap.remove("pageIndex");
        //查询
        final List<SceneStatisticDetailSearchResult> list = reportDetailMapper.getSceneStatisticDetail(reMap);
        //转换
        List<SceneStatisticDetailSearchResultDto> detailDtoList = new ArrayList<SceneStatisticDetailSearchResultDto>();
        if (null != list && !list.isEmpty()) {
        	SceneStatisticDetailSearchResultDto dto = null;
            for (int i = 0; i < list.size(); i++) {
            	dto = new SceneStatisticDetailSearchResultDto();
                BeanUtils.copyProperties(list.get(i), dto);
                // 进度
                if (13502 == list.get(i).getRewardType()) {// 带看奖励
                    dto.setRewardMoney(list.get(i).getRelationReward());
                    dto.setAccountMoney(list.get(i).getAccountRelationReward());
                } else if (13503 == list.get(i).getRewardType()) {// 认筹奖励
                    dto.setRewardMoney(list.get(i).getPledgedReward());
                    dto.setAccountMoney(list.get(i).getAccountPledgedReward());
                } else if (13504 == list.get(i).getRewardType()) {// 认购奖励
                    dto.setRewardMoney(list.get(i).getSubscribedReward());
                    dto.setAccountMoney(list.get(i).getAccountSubscribedReward());
                } else if (13505 == list.get(i).getRewardType()) {// 成交奖励
                    dto.setRewardMoney(list.get(i).getBargainReward());
                    dto.setAccountMoney(list.get(i).getAccountBargainReward());
                }
                // 客户电话
                String tel = list.get(i).getCustomerTel();
                if (StringUtil.isNotEmpty(tel) && tel.length() == 11) {
                    if ("1".equals(list.get(i).getHideNumberFlg())) {
                        tel = tel.substring(0, 3) + "-****-" + tel.substring(7);
                    } else {
                        tel = tel.substring(0, 3) + "-" + tel.substring(3, 7) + "-" + tel.substring(7);
                    }
                }
                dto.setCustomerTel(tel);
                dto.setRewardTypeNm(SystemParam.getDicValueByDicCode(String.valueOf(dto.getRewardType())));
                dto.setAccountStatusNm(SystemParam.getDicValueByDicCode(String.valueOf(dto.getAccountStatus())));
                dto.setAccountTypeNm(SystemParam.getDicValueByDicCode(String.valueOf(dto.getAccountType())));
                
                detailDtoList.add(dto);
                if (13505 == list.get(i).getRewardType()) {
                	SceneStatisticDetailSearchResultDto commissiondto = new SceneStatisticDetailSearchResultDto();
                    BeanUtils.copyProperties(list.get(i), commissiondto);
                    commissiondto.setCustomerTel(tel);
                    // 佣金
                    commissiondto.setRewardType(13507);
                    commissiondto.setRewardTypeNm(SystemParam.getDicValueByDicCode("13507"));
                    commissiondto.setRewardMoney(list.get(i).getCommission());
                    commissiondto.setAccountMoney(list.get(i).getAccountCommission());
                    commissiondto.setAccountStatus(list.get(i).getCommissionAccountStatus());
                    String countId = list.get(i).getCountId().substring(0, list.get(i).getCountId().length() - 1);
            		int t = Integer.valueOf(list.get(i).getCountId().substring(list.get(i).getCountId().length() - 1));
            		t = t + 1;
            		commissiondto.setCountId(countId + String.valueOf(t));
                    detailDtoList.add(commissiondto);
                }
            }
        }
        
        //返回分页的数据
        List<SceneStatisticDetailSearchResultDto> pageList = new ArrayList<SceneStatisticDetailSearchResultDto>();
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
        
      /*  resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(detailDtoList);*/
        return resultData;
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
    private List<EstateSearchResultDto> convertEstateSearchResultData(List<EstateSearchResult> craList) throws Exception{
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
