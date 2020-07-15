package cn.com.eju.deal.scene.estate.service;

import cn.com.eju.deal.Report.model.ApproDecideDto;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.common.service.FileRecordMainService;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.core.support.AppMsg;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateSearchResultDto;
import cn.com.eju.deal.dto.scene.estate.SceneEstateSearchResultDto;
import cn.com.eju.deal.dto.scene.estate.SceneRecognitionSearchResultDto;
import cn.com.eju.deal.fangyou.service.FangyouService;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateMapper;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateRuleMapper;
import cn.com.eju.deal.houseLinkage.estate.model.Estate;
import cn.com.eju.deal.houseLinkage.estate.model.EstateRule;
import cn.com.eju.deal.houseLinkage.estate.model.EstateSearchResult;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.dao.AchieveMentChangeLogMapper;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.model.AchieveMentChangeLog;
import cn.com.eju.deal.houseLinkage.report.dao.FangyouReportFileMapper;
import cn.com.eju.deal.houseLinkage.report.dao.FangyouReportLogMapper;
import cn.com.eju.deal.houseLinkage.report.dao.ReportDetailMapper;
import cn.com.eju.deal.houseLinkage.report.dao.ReportMapper;
import cn.com.eju.deal.houseLinkage.report.model.FangyouReportFile;
import cn.com.eju.deal.houseLinkage.report.model.FangyouReportLog;
import cn.com.eju.deal.houseLinkage.report.model.Report;
import cn.com.eju.deal.houseLinkage.report.model.ReportDetail;
import cn.com.eju.deal.houseLinkage.statistic.service.StatisticService;
import cn.com.eju.deal.scene.estate.model.SceneRecognitionSearchResult;
import cn.com.eju.deal.scene.inCommission.dao.SceneInCommissionMapper;
import cn.com.eju.deal.store.service.StoreService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * service层
 */
@Service("fangyouReportLogService")
public class FangyouReportLogService extends BaseService {
	 
	private final LogHelper logger = LogHelper.getLogger(this.getClass());
	 
	@Resource
    private FangyouReportLogMapper fangyouReportLogMapper;
    
    /**
     *addLog
     */
    public ResultData<Integer> addLog(FangyouReportLog fangyouReportLog){
    	ResultData<Integer> resultData = new ResultData<Integer>();
    	try {
          	fangyouReportLog.setDateCreate(new Date());
        	fangyouReportLog.setDelFlag("N");
        	fangyouReportLogMapper.insertSelective(fangyouReportLog);
        	resultData.setReturnMsg(AppMsg.getString("COMMON.OPERATE_SUCCESS"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("api.fangyouReportLogService", "FangyouReportLogService", "addLog", JSON.toJSONString(fangyouReportLog), null, "", "", e);
		}
        return resultData;
    }
}
