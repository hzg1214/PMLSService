package cn.com.eju.pmls.report.frontPanel.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.pmls.report.frontPanel.dao.*;
import cn.com.eju.pmls.report.frontPanel.dto.FrontPanelDto;
import cn.com.eju.pmls.report.frontPanel.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service("frontPanelService")
public class FrontPanelService {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private PmlsNumMonthMapper pmlsNumMonthMapper;

    @Autowired
    private PmlsNumQuarterMapper pmlsNumQuarterMapper;

    @Autowired
    private PmlsNumWeekMapper pmlsNumWeekMapper;

    @Autowired
    private PmlsNumYearMapper pmlsNumYearMapper;

    @Autowired
    private PmlsYjMonthMapper pmlsYjMonthMapper;

    @Autowired
    private PmlsYjQuarterMapper pmlsYjQuarterMapper;

    @Autowired
    private PmlsYjWeekMapper pmlsYjWeekMapper;

    @Autowired
    private PmlsYjYearMapper pmlsYjYearMapper;


    public ResultData<FrontPanelDto> query(Map<String, Object> queryParam) {
        ResultData<FrontPanelDto> resultData = new ResultData<FrontPanelDto>();

//        Calendar calendar = Calendar.getInstance();
//        calendar.setFirstDayOfWeek(Calendar.MONDAY);
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        int year = calendar.get(Calendar.YEAR);
//        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);//获得当前日期属于今年的第几周
//        int monthOfYear = calendar.get(Calendar.MONTH);//获得当前日期属于今年的第几周
//        int quarterIndex = 1;
//        if (monthOfYear >= 1 && monthOfYear <= 3) {
//            quarterIndex = 1;
//        } else if (monthOfYear >= 4 && monthOfYear <= 6) {
//            quarterIndex = 2;
//        } else if (monthOfYear >= 7 && monthOfYear <= 9) {
//            quarterIndex = 3;
//        } else {
//            quarterIndex = 4;
//        }
//        queryParam.put("year", String.valueOf(year));
//        queryParam.put("weekIndex", String.valueOf(weekOfYear));
//        queryParam.put("monthIndex", String.valueOf(monthOfYear));
//        queryParam.put("quarterIndex", String.valueOf(quarterIndex));

        FrontPanelDto dto = new FrontPanelDto();
        String dateType = queryParam.get("dateType").toString();

        switch (dateType) {
            case "week":
                dto = getWeekInfo(queryParam);
                break;
            case "month":
                dto = getMonthInfo(queryParam);
                break;
            case "quarter":
                dto = getQuarterInfo(queryParam);
                break;
            case "year":
            default:
                dto = getYearInfo(queryParam);
                break;
        }

        convertFrontPanelDto(dto);

        resultData.setReturnData(dto);
        return resultData;
    }


    private FrontPanelDto getWeekInfo(Map<String, Object> queryParam) {
        FrontPanelDto dto = new FrontPanelDto();

        String dateIndex = (String) queryParam.get("dateIndex");
        String year = dateIndex.split("-")[0];
        String weekIndex = dateIndex.split("-")[1];
        queryParam.put("year", year);
        queryParam.put("weekIndex", Integer.parseInt(weekIndex));

        PmlsNumWeek weekNum = pmlsNumWeekMapper.getPmlsFrontPanelNum(queryParam);
        PmlsYjWeek weekYjInfo = pmlsYjWeekMapper.getPmlsFrontPanelYjInfo(queryParam);

        if (weekNum != null) {
            BeanUtils.copyProperties(weekNum, dto);
        }
        if (weekYjInfo != null) {
            BeanUtils.copyProperties(weekYjInfo, dto);
        }
        return dto;
    }

    private FrontPanelDto getMonthInfo(Map<String, Object> queryParam) {

        FrontPanelDto dto = new FrontPanelDto();
        String dateIndex = (String) queryParam.get("dateIndex");
        if(!StringUtil.isEmpty(dateIndex)) {
        	String year = dateIndex.split("-")[0];
        	String monthIndex = dateIndex.split("-")[1];
        	queryParam.put("year", year);
        	queryParam.put("monthIndex", Integer.parseInt(monthIndex));
        	
        	PmlsNumMonth monthNum = pmlsNumMonthMapper.getPmlsFrontPanelNum(queryParam);
        	PmlsYjMonth monthYjInfo = pmlsYjMonthMapper.getPmlsFrontPanelYjInfo(queryParam);
        	
        	if (monthNum != null) {
        		BeanUtils.copyProperties(monthNum, dto);
        	}
        	if (monthYjInfo != null) {
        		BeanUtils.copyProperties(monthYjInfo, dto);
        	}
        }
        return dto;
    }

    private FrontPanelDto getQuarterInfo(Map<String, Object> queryParam) {
        FrontPanelDto dto = new FrontPanelDto();

        String dateIndex = (String) queryParam.get("dateIndex");
        if(!StringUtil.isEmpty(dateIndex)) {
        	String year = dateIndex.split("-")[0];
        	String quarterIndex = dateIndex.split("-")[1];
        	queryParam.put("year", year);
        	queryParam.put("quarterIndex", Integer.parseInt(quarterIndex));
        	
        	PmlsNumQuarter quarterNum = pmlsNumQuarterMapper.getPmlsFrontPanelNum(queryParam);
        	PmlsYjQuarter quarterYjInfo = pmlsYjQuarterMapper.getPmlsFrontPanelYjInfo(queryParam);
        	
        	if (quarterNum != null) {
        		BeanUtils.copyProperties(quarterNum, dto);
        	}
        	if (quarterYjInfo != null) {
        		BeanUtils.copyProperties(quarterYjInfo, dto);
        	}
        }
        return dto;
    }

    private FrontPanelDto getYearInfo(Map<String, Object> queryParam) {
        FrontPanelDto dto = new FrontPanelDto();
        String year = (String) queryParam.get("dateIndex");
        queryParam.put("year", year);
        queryParam.put("datatype", "Y");

        PmlsNumYear yearNum = pmlsNumYearMapper.getPmlsFrontPanelNum(queryParam);
        PmlsYjYear yearYjInfo = pmlsYjYearMapper.getPmlsFrontPanelYjInfo(queryParam);

        if (yearNum != null) {
            BeanUtils.copyProperties(yearNum, dto);
        }
        if (yearYjInfo != null) {
            BeanUtils.copyProperties(yearYjInfo, dto);
        }
        return dto;
    }

    private FrontPanelDto convertFrontPanelDto(FrontPanelDto dto) {

        //region num
        if (dto.getBb_Num() == null) {
            dto.setBb_Num(0);
        }

        if (dto.getDk_Num() == null) {
            dto.setDk_Num(0);
        }

        if (dto.getDd_Num() == null) {
            dto.setDd_Num(0);
        }

        if (dto.getCx_Num() == null) {
            dto.setCx_Num(0);
        }
        //endregion

        //region 应计收入
        if (dto.getYjsr_lj() == null) {
            dto.setYjsr_lj(new BigDecimal("0"));
        }

        if (dto.getYjsr_ljhk() == null) {
            dto.setYjsr_ljhk(new BigDecimal("0"));
        }

        // 累计应计未回 = 累计应计收入-应计回款
        dto.setYjsr_ljwhk(dto.getYjsr_lj().subtract(dto.getYjsr_ljhk()));
        if(dto.getYjsr_ljwhk().compareTo(BigDecimal.ZERO)==-1) {//小于0
        	dto.setYjsrFlag(true);
        }

        // 应计收入 ：已回比例 & 未回比例
        if (dto.getYjsr_lj() == null || 0 == dto.getYjsr_lj().compareTo(new BigDecimal("0"))) {
            dto.setYjsr_yhbl(new BigDecimal("1"));
            dto.setYjsr_whbl(new BigDecimal("0"));
        } else {
            dto.setYjsr_yhbl(dto.getYjsr_ljhk().divide(dto.getYjsr_lj(), 4, BigDecimal.ROUND_HALF_UP));
            dto.setYjsr_whbl(new BigDecimal("1").subtract(dto.getYjsr_yhbl()));
        }
        //endregion

        //region 应收收入
        if (dto.getYssr_lj() == null) {
            dto.setYssr_lj(new BigDecimal("0"));
        }
        if (dto.getYssr_ljhk() == null) {
            dto.setYssr_ljhk(new BigDecimal("0"));
        }
        dto.setYssr_ljwhk(dto.getYssr_lj().subtract(dto.getYssr_ljhk()));
        if(dto.getYssr_ljwhk().compareTo(BigDecimal.ZERO)==-1) {//小于0
        	dto.setYssrFlag(true);
        }

        // 应收收入 ：已回比例 & 未回比例
        if (dto.getYssr_lj() == null || 0 == dto.getYssr_lj().compareTo(new BigDecimal("0"))) {
            dto.setYssr_yhbl(new BigDecimal("1"));
            dto.setYssr_whbl(new BigDecimal("0"));
        } else {
            dto.setYssr_yhbl(dto.getYssr_ljhk().divide(dto.getYssr_lj(), 4, BigDecimal.ROUND_HALF_UP));
            dto.setYssr_whbl(new BigDecimal("1").subtract(dto.getYssr_yhbl()));
        }
        //endregion

        //region 应计返佣

        if (dto.getYjfy_lj() == null) {
            dto.setYjfy_lj(new BigDecimal("0"));
        }
        if (dto.getSjfy_lj() == null) {
            dto.setSjfy_lj(new BigDecimal("0"));
        }
        dto.setYjfy_ljwf(dto.getYjfy_lj().subtract(dto.getSjfy_lj()));

        // 应计返佣 ：已回比例 & 未回比例
        if (dto.getYjfy_lj() == null || 0 == dto.getYjfy_lj().compareTo(new BigDecimal("0"))) {
            dto.setYjfy_yfbl(new BigDecimal("1"));
            dto.setYjfy_wfbl(new BigDecimal("0"));
        } else {
            dto.setYjfy_yfbl(dto.getSjfy_lj().divide(dto.getYjfy_lj(), 4, BigDecimal.ROUND_HALF_UP));
            dto.setYjfy_wfbl(new BigDecimal("1").subtract(dto.getYjfy_yfbl()));
        }
        //endregion

        //region 应计垫佣
        if (dto.getYjdy_lj() == null) {
            dto.setYjdy_lj(new BigDecimal("0"));
        }
        if (dto.getSjdy_lj() == null) {
            dto.setSjdy_lj(new BigDecimal("0"));
        }
        dto.setYjdy_ljwd(dto.getYjdy_lj().subtract(dto.getSjdy_lj()));

        // 应计垫佣 ：已回比例 & 未回比例
        if (dto.getYjdy_lj() == null || 0 == dto.getYjdy_lj().compareTo(new BigDecimal("0"))) {
            dto.setYjdy_ydbl(new BigDecimal("1"));
            dto.setYjdy_wdbl(new BigDecimal("0"));
        } else {
            dto.setYjdy_ydbl(dto.getSjdy_lj().divide(dto.getYjdy_lj(), 4, BigDecimal.ROUND_HALF_UP));
            dto.setYjdy_wdbl(new BigDecimal("1").subtract(dto.getYjdy_ydbl()));
        }
        //endregion

        //region 已垫佣
        if (dto.getDyyjsr_lj() == null) {
            dto.setDyyjsr_lj(new BigDecimal("0"));
        }

        if (dto.getDyyjss_lj() == null) {
            dto.setDyyjss_lj(new BigDecimal("0"));
        }

        if (dto.getDyyjsr_ljwh() == null) {
            dto.setDyyjsr_ljwh(new BigDecimal("0"));
        }

        if (dto.getDyyssr_lj() == null) {
            dto.setDyyssr_lj(new BigDecimal("0"));
        }

        if (dto.getDyyssr_ljwh() == null) {
            dto.setDyyssr_ljwh(new BigDecimal("0"));
        }
        //endregion

        if (dto.getDealAmount_lj() == null) {
            dto.setDealAmount_lj(new BigDecimal("0"));
        }


        return dto;
    }
}
