package cn.com.eju.deal.houseLinkage.statistic.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.eju.deal.dto.houseLinkage.estate.EstateDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.CityCascadeMapper;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.contract.model.ContractSearchResult;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.contract.ContractSearchResultDto;
import cn.com.eju.deal.dto.houseLinkage.statistic.StatisticCompanySearchResultDto;
import cn.com.eju.deal.dto.houseLinkage.statistic.StatisticDetailSearchResultDto;
import cn.com.eju.deal.dto.houseLinkage.statistic.StatisticSearchResultDto;
import cn.com.eju.deal.dto.scene.statistic.SceneStatisticEstateSearchResultDto;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateMapper;
import cn.com.eju.deal.houseLinkage.estate.model.Estate;
import cn.com.eju.deal.houseLinkage.report.dao.ReportDetailMapper;
import cn.com.eju.deal.houseLinkage.report.dao.ReportMapper;
import cn.com.eju.deal.houseLinkage.report.model.ReportDetail;
import cn.com.eju.deal.houseLinkage.report.model.StatisticCompanySearchResult;
import cn.com.eju.deal.houseLinkage.report.model.StatisticDetailSearchResult;
import cn.com.eju.deal.store.service.StoreService;

/**
 * service层
 *
 * @author qianwei
 * @date 2016年3月22日 下午7:57:09
 */
@Service("statisticService")
public class StatisticService extends BaseService
{
    
    @Resource
    private EstateMapper estateMapper;
    
    @Resource
    private ReportMapper reportMapper;
    
    @Resource
    private ReportDetailMapper reportDetailMapper;
    
    @Resource
    private StoreService storeService;
    
    @Resource
    private ContractMapper contractMapper;
    
    @Resource
    private CityCascadeMapper cityCascadeMapper;
    
    /**
     * 查询-list
     *
     * @param queryParam
     * @return
     */
    //    @Cacheable(cacheName = "testCache")
    public ResultData<List<StatisticSearchResultDto>> queryList(Map<?, ?> param)
        throws Exception
    {
        //构建返回
        ResultData<List<StatisticSearchResultDto>> resultData = new ResultData<List<StatisticSearchResultDto>>();
        //查询
        final List<EstateDto> list = estateMapper.getStatisticForEstate(param);
        //转换
        List<StatisticSearchResultDto> statisticResultDtoList = new ArrayList<StatisticSearchResultDto>();
        if (null != list && !list.isEmpty())
        {
            StatisticSearchResultDto dto = null;
            for (int i = 0; i < list.size(); i++)
            {
                dto = new StatisticSearchResultDto();
                BeanUtils.copyProperties(list.get(i), dto);
                dto.setCityNoNm(SystemParam.getCityNameByCityNo(list.get(i).getCityNo()));
                dto.setDistrictNm(SystemParam.getDistrictNameByDistrictNo(list.get(i).getDistrictId()));
                dto.setAddress(list.get(i).getAddress());
                dto.setEstateType(list.get(i).getMgtKbn());
                dto.setEstateTypeNm(list.get(i).getMgtKbnStr());
                dto.setCooperationDtStart(getFormatDateString(list.get(i).getCooperationDtStart(), "yyyy-MM-dd"));
                dto.setCooperationDtEnd(getFormatDateString(list.get(i).getCooperationDtEnd(), "yyyy-MM-dd"));
                
                if (param.get("countDateTypeKbn") != null && !"".equals((String)param.get("countDateTypeKbn"))
                    && !"null".equals((String)param.get("countDateTypeKbn")))
                {
                    dto.setCountDateTypeKbn((String)param.get("countDateTypeKbn"));
                }
                else
                {
                    dto.setCountDateTypeKbn("null");
                }
                if (param.get("countDateStart") != null && !"".equals((String)param.get("countDateStart"))
                    && !"null".equals((String)param.get("countDateStart")))
                {
                    dto.setCountDateStart((String)param.get("countDateStart"));
                }
                else
                {
                    dto.setCountDateStart("null");
                }
                if (param.get("countDateEnd") != null && !"".equals((String)param.get("countDateEnd"))
                    && !"null".equals((String)param.get("countDateEnd")))
                {
                    dto.setCountDateEnd((String)param.get("countDateEnd"));
                }
                else
                {
                    dto.setCountDateEnd("null");
                }
                
                String reportPeopleNum =
                    getCountRewardConfirmStatusValid(list.get(i).getEstateId(),
                        "",
                        13501,
                        (String)param.get("countDateTypeKbn"),
                        (String)param.get("countDateStart"),
                        (String)param.get("countDateEnd"),
                        13601,
                        "0");
                String relationPeopleNum =
                    getCountRewardConfirmStatusValid(list.get(i).getEstateId(),
                        "",
                        13502,
                        (String)param.get("countDateTypeKbn"),
                        (String)param.get("countDateStart"),
                        (String)param.get("countDateEnd"),
                        13601,
                        "0");
                String pledgedPeopleNum =
                    getCountRewardConfirmStatusValid(list.get(i).getEstateId(),
                        "",
                        13503,
                        (String)param.get("countDateTypeKbn"),
                        (String)param.get("countDateStart"),
                        (String)param.get("countDateEnd"),
                        13601,
                        "0");
                String subscribedPeopleNum =
                    getCountRewardConfirmStatusValid(list.get(i).getEstateId(),
                        "",
                        13504,
                        (String)param.get("countDateTypeKbn"),
                        (String)param.get("countDateStart"),
                        (String)param.get("countDateEnd"),
                        13601,
                        "0");
                String signPeopleNum =
                    getCountRewardConfirmStatusValid(list.get(i).getEstateId(),
                        "",
                        13505,
                        (String)param.get("countDateTypeKbn"),
                        (String)param.get("countDateStart"),
                        (String)param.get("countDateEnd"),
                        13601,
                        "0");
                String getBackPeopleNum =
                    getCountRewardConfirmStatusValid(list.get(i).getEstateId(),
                        "",
                        13506,
                        (String)param.get("countDateTypeKbn"),
                        (String)param.get("countDateStart"),
                        (String)param.get("countDateEnd"),
                        13601,
                        "0");
                String commission =
                    getCountRewardConfirmStatusValid(list.get(i).getEstateId(),
                        "",
                        13505,
                        (String)param.get("countDateTypeKbn"),
                        (String)param.get("countDateStart"),
                        (String)param.get("countDateEnd"),
                        13601,
                        "1");
                
                //增加面积等四列
                SceneStatisticEstateSearchResultDto ssesrDtoTemp =
                    getCountAreaAndAmount(list.get(i).getEstateId(),
                        "",
                        (String)param.get("countDateTypeKbn"),
                        (String)param.get("countDateStart"),
                        (String)param.get("countDateEnd"),
                        13601,
                        "0");
                if (ssesrDtoTemp != null)
                {
                    dto.setRoughAreaSum(ssesrDtoTemp.getRoughAreaSum());
                    dto.setRoughAmountSum(ssesrDtoTemp.getRoughAmountSum());
                    dto.setDealAreaSum(ssesrDtoTemp.getDealAreaSum());
                    dto.setDealAmountSum(ssesrDtoTemp.getDealAmountSum());
                }
                else
                {
                    dto.setRoughAreaSum("0.00");
                    dto.setRoughAmountSum("0.00");
                    dto.setDealAreaSum("0.00");
                    dto.setDealAmountSum("0.00");
                }
                
                dto.setReportPeopleNum(reportPeopleNum.split(":")[0]);
                dto.setRelationPeopleNum(relationPeopleNum.split(":")[0]);
                dto.setPledgedPeopleNum(pledgedPeopleNum.split(":")[0]);
                dto.setSubscribedPeopleNum(subscribedPeopleNum.split(":")[0]);
                dto.setSignPeopleNum(signPeopleNum.split(":")[0]);
                dto.setGetBackPeopleNum(getBackPeopleNum.split(":")[0]);
                dto.setRelationReward(relationPeopleNum.split(":")[1]);
                dto.setPledgedReward(pledgedPeopleNum.split(":")[1]);
                dto.setSubscribedReward(subscribedPeopleNum.split(":")[1]);
                dto.setBargainReward(signPeopleNum.split(":")[1]);
                dto.setCommission(commission.split(":")[1]);
                
                String tempSignValue = dto.getBargainReward();
                if (dto.getBargainReward().indexOf(".") > 0)
                {
                    tempSignValue = dto.getBargainReward().substring(0, dto.getBargainReward().indexOf("."));
                }
                String tempCommissionValue = dto.getCommission();
                if (dto.getCommission().indexOf(".") > 0)
                {
                    tempCommissionValue = dto.getCommission().substring(0, dto.getCommission().indexOf("."));
                }
                BigDecimal bargain = new BigDecimal(Integer.valueOf(tempSignValue));
                dto.setTotalPerformance(String.valueOf(bargain.add(new BigDecimal(Integer.valueOf(tempCommissionValue)))));
                
                statisticResultDtoList.add(dto);
            }
        }
        resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(statisticResultDtoList);
        return resultData;
    }
    
    /**
     * 查询-公司统计list
     *
     * @param queryParam
     * @return
     */
    //    @Cacheable(cacheName = "testCache")
    public ResultData<List<StatisticCompanySearchResultDto>> queryCompanyList(Map<?, ?> param)
        throws Exception
    {
        //构建返回
        ResultData<List<StatisticCompanySearchResultDto>> resultData =
            new ResultData<List<StatisticCompanySearchResultDto>>();
        //查询
        final List<StatisticCompanySearchResult> list = reportMapper.getStatisticForCompany(param);
        //转换
        List<StatisticCompanySearchResultDto> statisticResultDtoList = new ArrayList<StatisticCompanySearchResultDto>();
        if (null != list && !list.isEmpty())
        {
            StatisticCompanySearchResultDto dto = null;
            for (int i = 0; i < list.size(); i++)
            {
                dto = new StatisticCompanySearchResultDto();
                dto.setCompanyId(list.get(i).getCompanyId());
                dto.setCompanyNm(list.get(i).getCompanyNm());
                dto.setEstateId(list.get(i).getEstateId());
                if (param.get("countDateTypeKbn") != null && !"".equals((String)param.get("countDateTypeKbn"))
                    && !"null".equals((String)param.get("countDateTypeKbn")))
                {
                    dto.setCountDateTypeKbn((String)param.get("countDateTypeKbn"));
                }
                else
                {
                    dto.setCountDateTypeKbn("null");
                }
                if (param.get("countDateStart") != null && !"".equals((String)param.get("countDateStart"))
                    && !"null".equals((String)param.get("countDateStart")))
                {
                    dto.setCountDateStart((String)param.get("countDateStart"));
                }
                else
                {
                    dto.setCountDateStart("null");
                }
                if (param.get("countDateEnd") != null && !"".equals((String)param.get("countDateEnd"))
                    && !"null".equals((String)param.get("countDateEnd")))
                {
                    dto.setCountDateEnd((String)param.get("countDateEnd"));
                }
                else
                {
                    dto.setCountDateEnd("null");
                }
                String reportPeopleNum =
                    getCountReward(list.get(i).getEstateId(),
                        list.get(i).getCompanyId(),
                        13501,
                        (String)param.get("countDateTypeKbn"),
                        (String)param.get("countDateStart"),
                        (String)param.get("countDateEnd"),
                        13601,
                        "0");
                String relationPeopleNum =
                    getCountReward(list.get(i).getEstateId(),
                        list.get(i).getCompanyId(),
                        13502,
                        (String)param.get("countDateTypeKbn"),
                        (String)param.get("countDateStart"),
                        (String)param.get("countDateEnd"),
                        13601,
                        "0");
                String pledgedPeopleNum =
                    getCountReward(list.get(i).getEstateId(),
                        list.get(i).getCompanyId(),
                        13503,
                        (String)param.get("countDateTypeKbn"),
                        (String)param.get("countDateStart"),
                        (String)param.get("countDateEnd"),
                        13601,
                        "0");
                String subscribedPeopleNum =
                    getCountReward(list.get(i).getEstateId(),
                        list.get(i).getCompanyId(),
                        13504,
                        (String)param.get("countDateTypeKbn"),
                        (String)param.get("countDateStart"),
                        (String)param.get("countDateEnd"),
                        13601,
                        "0");
                String signPeopleNum =
                    getCountReward(list.get(i).getEstateId(),
                        list.get(i).getCompanyId(),
                        13505,
                        (String)param.get("countDateTypeKbn"),
                        (String)param.get("countDateStart"),
                        (String)param.get("countDateEnd"),
                        13601,
                        "0");
                String getBackPeopleNum =
                    getCountReward(list.get(i).getEstateId(),
                        list.get(i).getCompanyId(),
                        13506,
                        (String)param.get("countDateTypeKbn"),
                        (String)param.get("countDateStart"),
                        (String)param.get("countDateEnd"),
                        13601,
                        "0");
                String commission =
                    getCountReward(list.get(i).getEstateId(),
                        list.get(i).getCompanyId(),
                        13505,
                        (String)param.get("countDateTypeKbn"),
                        (String)param.get("countDateStart"),
                        (String)param.get("countDateEnd"),
                        13601,
                        "1");
                
                dto.setReportPeopleNum(reportPeopleNum.split(":")[0]);
                dto.setRelationPeopleNum(relationPeopleNum.split(":")[0]);
                dto.setPledgedPeopleNum(pledgedPeopleNum.split(":")[0]);
                dto.setSubscribedPeopleNum(subscribedPeopleNum.split(":")[0]);
                dto.setSignPeopleNum(signPeopleNum.split(":")[0]);
                dto.setGetBackPeopleNum(getBackPeopleNum.split(":")[0]);
                dto.setRelationReward(relationPeopleNum.split(":")[1]);
                dto.setPledgedReward(pledgedPeopleNum.split(":")[1]);
                dto.setSubscribedReward(subscribedPeopleNum.split(":")[1]);
                dto.setBargainReward(signPeopleNum.split(":")[1]);
                dto.setCommission(commission.split(":")[1]);
                
                String tempSignValue = dto.getBargainReward();
                if (dto.getBargainReward().indexOf(".") > 0)
                {
                    tempSignValue = dto.getBargainReward().substring(0, dto.getBargainReward().indexOf("."));
                }
                String tempCommissionValue = dto.getCommission();
                if (dto.getCommission().indexOf(".") > 0)
                {
                    tempCommissionValue = dto.getCommission().substring(0, dto.getCommission().indexOf("."));
                }
                BigDecimal bargain = new BigDecimal(Integer.valueOf(tempSignValue));
                dto.setTotalPerformance(String.valueOf(bargain.add(new BigDecimal(Integer.valueOf(tempCommissionValue)))));
                
                statisticResultDtoList.add(dto);
            }
        }
        resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(statisticResultDtoList);
        return resultData;
    }
    
    public String getCountReward(String estateId, String companyId, int status, String countDateTypeKbn,
        String countDateStart, String countDateEnd, Integer confirmStatus, String commissionFlg)
        throws Exception
    {
        Map<Object, Object> param = new HashMap<Object, Object>();
        param.put("estateId", estateId);
        param.put("companyId", companyId);
        param.put("status", status);
        param.put("countDateTypeKbn", countDateTypeKbn);
        param.put("countDateStart", countDateStart);
        param.put("countDateEnd", countDateEnd);
        param.put("confirmStatus", confirmStatus);
        param.put("commissionFlg", commissionFlg);
        int cnt = 0;
        BigDecimal resultReward = new BigDecimal("0.00");
        List<ReportDetail> rewardList = reportDetailMapper.getReward(param);
        for (int i = 0; i < rewardList.size(); i++)
        {
            if (status != 13501 && status != 13506)
            { // 不是报备和退筹的场合
                resultReward = numAdd(resultReward, rewardList.get(i).getReward());
            }
            cnt++;
        }
        return cnt + ":" + bigDecimalToString(resultReward);
    }
    
    
    public String getCountRewardConfirmStatusValid(String estateId, String companyId, int status, String countDateTypeKbn,
            String countDateStart, String countDateEnd, Integer confirmStatus, String commissionFlg)
            throws Exception
        {
            Map<Object, Object> param = new HashMap<Object, Object>();
            param.put("estateId", estateId);
            param.put("companyId", companyId);
            param.put("status", status);
            param.put("countDateTypeKbn", countDateTypeKbn);
            param.put("countDateStart", countDateStart);
            param.put("countDateEnd", countDateEnd);
            param.put("confirmStatus", confirmStatus);
            param.put("commissionFlg", commissionFlg);
            int cnt = 0;
            BigDecimal resultReward = new BigDecimal("0.00");
            List<ReportDetail> rewardList = reportDetailMapper.getCountRewardConfirmStatusValid(param);
            for (int i = 0; i < rewardList.size(); i++)
            {
                if (status != 13501 && status != 13506)
                { // 不是报备和退筹的场合
                    resultReward = numAdd(resultReward, rewardList.get(i).getReward());
                }
                cnt++;
            }
            return cnt + ":" + bigDecimalToString(resultReward);
       }
    
    public SceneStatisticEstateSearchResultDto getCountAreaAndAmount(String estateId, String companyId,
        String countDateTypeKbn, String countDateStart, String countDateEnd, Integer confirmStatus, String commissionFlg)
        throws Exception
    {
        Map<Object, Object> param = new HashMap<Object, Object>();
        param.put("estateId", estateId);
        param.put("companyId", companyId);
        //param.put("status", status);
        param.put("countDateTypeKbn", countDateTypeKbn);
        param.put("countDateStart", countDateStart);
        param.put("countDateEnd", countDateEnd);
        //param.put("confirmStatus", confirmStatus);
        param.put("commissionFlg", commissionFlg);
        
        SceneStatisticEstateSearchResultDto ssesrDtoTemp = new SceneStatisticEstateSearchResultDto();
        ssesrDtoTemp = reportDetailMapper.getCountAreaAndAmount(param);
        return ssesrDtoTemp;
    }
    
    /**
     * 查询-统计明细list
     *
     * @param queryParam
     * @return
     */
    //    @Cacheable(cacheName = "testCache")
    public ResultData<List<StatisticDetailSearchResultDto>> queryStatisticDetailList(Map<?, ?> param)
        throws Exception
    {
        //构建返回
        ResultData<List<StatisticDetailSearchResultDto>> resultData =
            new ResultData<List<StatisticDetailSearchResultDto>>();
        Map<String, String> reMap = (Map<String, String>)param;
        Integer pageSize = Integer.parseInt(reMap.get("pageSize"));
        Integer pageIndex = Integer.parseInt(reMap.get("pageIndex"));
        reMap.remove("pageSize");
        reMap.remove("pageIndex");
        // reMap.remove("companyId");
        //查询
        final List<StatisticDetailSearchResult> list = reportDetailMapper.getStatisticDetail(reMap);
        //转换
        List<StatisticDetailSearchResultDto> statisticResultDtoList = new ArrayList<StatisticDetailSearchResultDto>();
        if (null != list && !list.isEmpty())
        {
            StatisticDetailSearchResultDto dto = null;
            
            for (int i = 0; i < list.size(); i++)
            {
                // 区分佣金和成交，设置佣金
                if (13505 == list.get(i).getRewardType()
                    && (param.get("rewardType") == null || "".equals(param.get("rewardType")) || (param.get("rewardType") != null
                        && !"".equals(param.get("rewardType")) && 14104 != Integer.valueOf((String)param.get("rewardType")))))
                {
                    //            		list.get(i).setRewardType(13507);
                    //            		list.get(i).setAccountStatus(list.get(i).getCommissionAccountStatus());
                    StatisticDetailSearchResultDto commissionDto = new StatisticDetailSearchResultDto();
                    BeanUtils.copyProperties(list.get(i), commissionDto);
                    String countId = list.get(i).getCountId().substring(0, list.get(i).getCountId().length() - 1);
                    int t = Integer.valueOf(list.get(i).getCountId().substring(list.get(i).getCountId().length() - 1));
                    t = t + 1;
                    commissionDto.setCountId(countId + String.valueOf(t));
                    commissionDto.setRewardType(13507);
                    commissionDto.setRewardTypeNm(SystemParam.getDicValueByDicCode("13507"));
                    commissionDto.setAccountStatus(list.get(i).getCommissionAccountStatus());
                    commissionDto.setRewardMoney(list.get(i).getCommission());
                    commissionDto.setAccountMoney(list.get(i).getAccountCommission());
                    statisticResultDtoList.add(commissionDto);
                }
                dto = new StatisticDetailSearchResultDto();
                BeanUtils.copyProperties(list.get(i), dto);
                
                if (13502 == list.get(i).getRewardType())
                {// 带看奖励
                    dto.setRewardMoney(list.get(i).getRelationReward());
                    dto.setAccountMoney(list.get(i).getAccountRelationReward());
                }
                else if (13503 == list.get(i).getRewardType())
                {// 认筹奖励
                    dto.setRewardMoney(list.get(i).getPledgedReward());
                    dto.setAccountMoney(list.get(i).getAccountPledgedReward());
                }
                else if (13504 == list.get(i).getRewardType())
                {// 认购奖励
                    dto.setRewardMoney(list.get(i).getSubscribedReward());
                    dto.setAccountMoney(list.get(i).getAccountSubscribedReward());
                }
                else if (13505 == list.get(i).getRewardType())
                {// 成交奖励
                    dto.setRewardMoney(list.get(i).getBargainReward());
                    dto.setAccountMoney(list.get(i).getAccountBargainReward());
                }
                else if (13506 == list.get(i).getRewardType())
                {// 退筹
                    dto.setRewardMoney("0");
                    dto.setAccountMoney("0");
                }
                else if (13507 == list.get(i).getRewardType())
                {// 佣金
                    dto.setRewardMoney(list.get(i).getCommission());
                    dto.setAccountMoney(list.get(i).getAccountCommission());
                }
                if (param.get("rewardType") == null
                    || "".equals(param.get("rewardType"))
                    || (param.get("rewardType") != null && !"".equals(param.get("rewardType")) && 14105 != Integer.valueOf((String)param.get("rewardType"))))
                {
                    statisticResultDtoList.add(dto);
                }
            }
        }
        
        //返回分页的数据
        List<StatisticDetailSearchResultDto> pageList = new ArrayList<StatisticDetailSearchResultDto>();
        Integer endpage = pageIndex * pageSize;
        if (endpage > statisticResultDtoList.size())
        {
            endpage = statisticResultDtoList.size();
        }
        //当前显示页的数据
        for (int i = (pageIndex - 1) * pageSize; i < endpage; i++)
        {
            pageList.add(statisticResultDtoList.get(i));
        }
        
        resultData.setTotalCount(statisticResultDtoList.size() + "");
        resultData.setReturnData(pageList);
        
        /* resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
         resultData.setReturnData(statisticResultDtoList);*/
        return resultData;
    }
    
    /**
     * 对象转换MO--DTO
     *
     * @param craList
     * @return List<ContractSearchResultDto>
     */
    private List<ContractSearchResultDto> convertContractSearchResultData(List<ContractSearchResult> craList)
        throws Exception
    {
        List<ContractSearchResultDto> contractDtoList = new ArrayList<ContractSearchResultDto>();
        
        if (null != craList && !craList.isEmpty())
        {
            ContractSearchResultDto craDto = null;
            for (ContractSearchResult cra : craList)
            {
                craDto = new ContractSearchResultDto();
                BeanUtils.copyProperties(cra, craDto);
                contractDtoList.add(craDto);
            }
        }
        return contractDtoList;
    }
    
    /**
     * 取得格式化的日期字符串
     * @param dat 日期对象
     * @param strFormat 格式
     * @return 格式化后的字符串
     */
    private String getFormatDateString(Date dat, String strFormat)
    {
        if (null == dat)
        {
            return "";
        }
        SimpleDateFormat myFmt = new SimpleDateFormat(strFormat);
        return myFmt.format(dat);
    }
    
    /**
     * 计算两个数字的合计值(允许数字为NULL)
     * @param numericA 数字A
     * @param numericB 数字B
     * @return A+B的结果
     */
    private BigDecimal numAdd(BigDecimal numericA, BigDecimal numericB)
    {
        if (null == numericA)
        {
            numericA = new BigDecimal("0");
        }
        if (null == numericB)
        {
            numericB = new BigDecimal("0");
        }
        return numericA.add(numericB);
    }
    
    /**
     * 从数字类型BigDecimal转化为字符串
     * @param decimal
     * @return 字符串
     */
    private String bigDecimalToString(BigDecimal decimal)
    {
        // NULL的场合返回空
        if (null == decimal)
        {
            return "";
        }
        return decimal.toString();
    }
}
