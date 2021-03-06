package cn.com.eju.deal.houseLinkage.estate.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.houseLinkage.estate.LnkEstateIncomeplanDto;
import cn.com.eju.deal.dto.houseLinkage.estate.LnkYjPlanDto;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateChangeMapper;
import cn.com.eju.deal.houseLinkage.estate.dao.LnkEstateIncomeplanMapper;
import cn.com.eju.deal.houseLinkage.estate.dao.LnkYjPlanMapper;
import cn.com.eju.deal.houseLinkage.estate.model.EstateChange;
import cn.com.eju.deal.houseLinkage.estate.model.LnkEstateIncomeplan;
import cn.com.eju.deal.houseLinkage.estate.model.LnkYjPlan;
import cn.com.eju.pmls.cooperation.model.CooperationDto;

/**
 * Created by eju on 2019/12/6.
 */
@Service("lnkEstateIncomeplanSeivice")
public class LnkEstateIncomeplanSeivice extends BaseService {

	private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private LnkEstateIncomeplanMapper lnkEstateIncomeplanMapper;
    @Resource
    private EstateChangeMapper estateChangeMapper;
    @Resource
    private LnkYjPlanMapper lnkYjPlanMapper;

    public int addIncomePlan(Map<String, Object> map) {
        String projectNo = map.get("projectNo").toString();
        int countNum = lnkEstateIncomeplanMapper.queryCountByProject(projectNo);
        int count = 0;
        LnkEstateIncomeplan plan = new LnkEstateIncomeplan();
        plan.setCreateDate(new Date());
        plan.setDelFlag(false);
        plan.setIsEnable(false);
        plan.setProjectNo(projectNo);
        plan.setUserIdCreate(Integer.valueOf(map.get("userId").toString()));
        plan.setUserIdCreate(Integer.valueOf(map.get("userId").toString()));
        plan.setUpdateDate(new Date());
        if (!StringUtils.isEmpty(map.get("fixAmount").toString())) {
            plan.setFixAmount(new BigDecimal(map.get("fixAmount").toString()));
        }
        if (!StringUtils.isEmpty(map.get("totalPercentage").toString())) {
            plan.setTotalPercentage(new BigDecimal(map.get("totalPercentage").toString()));
        }
        plan.setPlanName("方案" + int2chineseNum(countNum + 1));
        plan.setPlanType(Integer.valueOf(map.get("planType").toString()));
        count = lnkEstateIncomeplanMapper.insertSelective(plan);
        return count;
    }

    public static String int2chineseNum(int src) {
        final String num[] = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        final String unit[] = {"", "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};
        String dst = "";
        int count = 0;
        while (src > 0) {
            dst = (num[src % 10] + unit[count]) + dst;
            src = src / 10;
            count++;
        }
        if (dst.indexOf("一十") == 0) {
            dst = dst.replaceFirst("一十", "十");
        }
        return dst.replaceAll("零[千百十]", "零").replaceAll("零+万", "万")
                .replaceAll("零+亿", "亿").replaceAll("亿万", "亿零")
                .replaceAll("零+", "零").replaceAll("零$", "");
    }

    public int toProhibit(Map<String, Object> map) {
        int count = 0;
        LnkEstateIncomeplan plan = new LnkEstateIncomeplan();
        plan.setUpdateDate(new Date());
        plan.setUserIdCreate(Integer.valueOf(map.get("userId").toString()));
        plan.setId(Integer.valueOf(map.get("id").toString()));
        Integer type = Integer.valueOf(map.get("type").toString());
        String str = "";
        String changeType = "28601";
        if (type == 1) {
            plan.setIsEnable(true);
            str = "禁用";
        } else {
            plan.setIsEnable(false);
            str = "启用";
            changeType = "28602";
        }
        count = lnkEstateIncomeplanMapper.updateByPrimaryKeySelective(plan);
        LnkEstateIncomeplanDto lnkEstateIncomeplan = lnkEstateIncomeplanMapper.queryById(Integer.valueOf(map.get("id").toString()));
        if(count>0){
            EstateChange estateChange = new EstateChange();
            estateChange.setChangeType(changeType);
            estateChange.setChangeName("收入方案状态变更");
            estateChange.setChangeDetail("收入"+lnkEstateIncomeplan.getPlanName()+"："+str);
            estateChange.setChangeDate(new Date());
            estateChange.setOperator(Integer.valueOf(map.get("userId").toString()));
            estateChange.setOperatorCode(map.get("userCode").toString());
            estateChange.setOperatorName(map.get("userName").toString());
            estateChange.setEstateId(lnkEstateIncomeplan.getEstateId());
            estateChangeMapper.create(estateChange);
        }
        return count;
    }

    public ResultData<LnkEstateIncomeplanDto> queryPlanById(Integer id) {
        ResultData<LnkEstateIncomeplanDto> resultData = new ResultData<>();
        LnkEstateIncomeplanDto dto = lnkEstateIncomeplanMapper.queryPlanById(id);
        resultData.setReturnData(dto);
        return resultData;
    }

    public int editIncomePlan(Map<String, Object> map) {
        Integer id = Integer.valueOf(map.get("id").toString());
        LnkEstateIncomeplanDto old = lnkEstateIncomeplanMapper.queryById(id);
        int count = 0;
        LnkEstateIncomeplan plan = new LnkEstateIncomeplan();
        plan.setId(id);
        plan.setUserIdCreate(Integer.valueOf(map.get("userId").toString()));
        plan.setUserIdCreate(Integer.valueOf(map.get("userId").toString()));
        plan.setUpdateDate(new Date());
        if (!StringUtils.isEmpty(map.get("fixAmount").toString())) {
            plan.setFixAmount(new BigDecimal(map.get("fixAmount").toString()));
        }else{
            plan.setFixAmount(null);
        }
        if (!StringUtils.isEmpty(map.get("totalPercentage").toString())) {
            plan.setTotalPercentage(new BigDecimal(map.get("totalPercentage").toString()));
        }else{
            plan.setTotalPercentage(null);
        }
        plan.setPlanType(Integer.valueOf(map.get("planType").toString()));
        count = lnkEstateIncomeplanMapper.updatePlan(plan);
        LnkEstateIncomeplanDto newDto = lnkEstateIncomeplanMapper.queryById(id);
        if(count>0){
            EstateChange estateChange = new EstateChange();
            estateChange.setChangeType("28603");
            estateChange.setChangeName("收入方案变更");
            estateChange.setChangeDetail("收入"+old.getPlanName()+"：由"+old.getPlanInfo()+"变为"+newDto.getPlanInfo());
            estateChange.setChangeDate(new Date());
            estateChange.setOperator(Integer.valueOf(map.get("userId").toString()));
            estateChange.setOperatorCode(map.get("userCode").toString());
            estateChange.setOperatorName(map.get("userName").toString());
            estateChange.setEstateId(old.getEstateId());
            estateChangeMapper.create(estateChange);
        }
        return count;
    }

    public ResultData<LnkEstateIncomeplanDto> queryCXSQAmount(Map<?, ?> queryParam) {
        ResultData<LnkEstateIncomeplanDto> resultData = new ResultData<>();
        LnkEstateIncomeplanDto dto = new LnkEstateIncomeplanDto();
        if (StringUtils.isEmpty(queryParam.get("id").toString())) {
            dto = lnkEstateIncomeplanMapper.queryTopCXSQAmount(queryParam);
        } else {
            dto = lnkEstateIncomeplanMapper.queryCXSQAmount(queryParam);
        }
        resultData.setReturnData(dto);
        return resultData;
    }

    public ResultData<List<LnkEstateIncomeplanDto>> queryIncomeplanList(Map<String, Object> param) {
        ResultData<List<LnkEstateIncomeplanDto>> resultData = new ResultData<>();
        List<LnkEstateIncomeplanDto> incomeplanList = lnkEstateIncomeplanMapper.queryIncomeplanList(param);
        resultData.setReturnData(incomeplanList);
        resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
        return resultData;
    }

    /**
     * desc:佣金方案维护-经纪公司列表
     * 2020年2月27日
     */
    public ResultData<List<CooperationDto>> getCompanyList(Map<?, ?> param) throws Exception {
     	ResultData<List<CooperationDto>> resultData = new ResultData<>();
     	List<CooperationDto> comanyDtoList = lnkEstateIncomeplanMapper.getCompanyList(param);
     	resultData.setReturnData(comanyDtoList);
     	resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
     	return resultData;
     }
    
    /**
     * desc:禁用佣金方案
     * 2020年3月2日
     */
    public int toIsEnable(Map<String, Object> map) {
        int count = 0;
        Integer id = Integer.valueOf(map.get("id").toString());
        LnkYjPlan plan = new LnkYjPlan();
        plan.setUpdateDate(new Date());
        plan.setUserIdUpdate(Integer.valueOf(map.get("userId").toString()));
        plan.setId(id);
        Integer type = Integer.valueOf(map.get("type").toString());
        String str = "";
        String changeType = "28601";
        if (type == 1) {
            plan.setIsEnable(true);//否  1
            str = "启用";
            plan.setIsEdit("0");//启用操作将isEdit 改为0
        } else {
            plan.setIsEnable(false);//是  0 
            str = "禁用";
            changeType = "28602";
        }
        count = lnkYjPlanMapper.updateByPrimaryKeySelective(plan);
        LnkYjPlanDto lnkYjPlanDto = lnkYjPlanMapper.queryById(map);
        String planType = lnkYjPlanDto.getPlanType();
        if(count>0){
        	//调用存储过程 begin
        	Map<String, Object> callMap = new HashMap<>();
        	logger.info("佣金方案，调用存储过程begin，入参id:"+id);
        	callMap.put("id", id);
        	lnkYjPlanMapper.callYjPlan(callMap);
        	logger.info("佣金方案，调用存储过程end，入参id:"+id);
        	//end
            EstateChange estateChange = new EstateChange();
            estateChange.setChangeType(changeType);
            if("28701".equals(planType)) {//收入方案
            	estateChange.setChangeName("收入方案状态变更");
            	estateChange.setChangeDetail("收入,"+lnkYjPlanDto.getPlanName()+"："+str);
            }else if("28702".equals(planType)) {//返佣方案
            	estateChange.setChangeName("返佣方案状态变更");
            	estateChange.setChangeDetail("返佣,"+lnkYjPlanDto.getPlanName()+"："+str);
            }
            estateChange.setChangeDate(new Date());
            estateChange.setOperator(Integer.valueOf(map.get("userId").toString()));
            estateChange.setOperatorCode(map.get("userCode").toString());
            estateChange.setOperatorName(map.get("userName").toString());
            estateChange.setEstateId(lnkYjPlanDto.getEstateId());
            estateChangeMapper.create(estateChange);
        }
        return count;
    }

}
