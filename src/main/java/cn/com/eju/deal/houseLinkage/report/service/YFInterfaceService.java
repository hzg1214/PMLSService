package cn.com.eju.deal.houseLinkage.report.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.User;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.util.HttpClientUtil;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.houseLinkage.report.dao.FangyouReportFailLogMapper;
import cn.com.eju.deal.houseLinkage.report.dao.ReportMapper;
import cn.com.eju.deal.houseLinkage.report.model.FangyouReportFailLog;
import cn.com.eju.deal.houseLinkage.report.model.FangyouReportLog;
import cn.com.eju.deal.houseLinkage.report.model.Report;
import cn.com.eju.deal.houseLinkage.report.model.YFStatusSync;
import cn.com.eju.deal.scene.estate.service.FangyouReportLogService;
import cn.com.eju.deal.scene.estate.service.SceneEstateService;


@Service("yFInterfaceService")
public class YFInterfaceService extends BaseService {
	 /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource
    private FangyouReportLogService fangyouReportLogService;
    
    @Resource 
    private FangyouReportFailLogMapper fangyouReportFailLogMapper;
    
    @Resource
    private ReportService reportService;
    
    @Resource
    private SceneEstateService sceneEstatetService;
    
	@Resource
    private ReportMapper reportMapper;

	@Resource
	private UserMapper userMapper;

    
    public ResultData<String> getyFInterfaceInfo(String func,String paramMap ,String typeId,Integer userId,String reportId) {
		ResultData<String> resultData = new ResultData<String>();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		FangyouReportLog fangyouReportLog = new FangyouReportLog();
		resultData.setFail("调用有房接口失败");
		
		String returnDataStr = null;
        String url = SystemParam.getWebConfigValue("youfangReportUrl") +func;
        boolean flag = false;//处理失败
        
        logger.info("调用有房接口start#####请求#url="+url);
        try {
        	returnDataStr = HttpClientUtil.httpPostYF(url, paramMap);
			if(StringUtil.isNotEmpty(returnDataStr)){
				returnMap = JSON.parseObject(returnDataStr, Map.class);
				if(returnMap.containsKey("BFlag") && returnMap.containsKey("TData")){
					Integer bFlag = (Integer) returnMap.get("BFlag");
					Object tData = returnMap.get("TData");
					if(10==bFlag){
						resultData.setSuccess();
						resultData.setReturnData(JSON.toJSONString(tData));
						
						flag = true;
					}else{
						resultData.setFail("调用接口失败");
					}
				}
			}
		} catch (Exception e) {
			try {
				logger.error("yFInterfaceService","YFInterfaceService","getyFInterfaceInfo","userId="+ userId, userId,null,
						 "调用有房接口:#####请求参数#url="+url+"#####返回信息"+returnDataStr,e);
			} catch (Exception e2) {
				
			}
			
			resultData.setFail("调用有房接口异常");
		}
        if(StringUtil.isNotEmpty(typeId)){
        	fangyouReportLog.setTypeId(typeId); 
            fangyouReportLog.setReqParamJson(JSON.toJSONString(paramMap));
           	fangyouReportLog.setRspParamJson(returnDataStr);
           	fangyouReportLog.setDateCreate(new Date());
           	fangyouReportLog.setUserIdCreate(userId);
           	fangyouReportLog.setDelFlag("N");
            fangyouReportLogService.addLog(fangyouReportLog);
        }
        if(flag){
        	if(!"8".equals(typeId)){//大定审核无需处理
        		handleYFFailLog(reportId, typeId, paramMap, 0);
        	}
        }else{
        	if(!"8".equals(typeId)){
        		handleYFFailLog(reportId, typeId, paramMap, 1);
        	}
        }
        return resultData;
	}
    //如果退定退房后还可以重复流程  那么大定后FangyouReportFailLog需要还原
    public void handleYFFailLog(String reportId,String typeId,String reqParamJson,int yfsendStatus) {
    	try {
    		Map<String,Object> reqMap = new HashMap<String,Object>();
    		reqMap.put("reportId", reportId);
    		reqMap.put("typeId", typeId);
    		FangyouReportFailLog failLogDb = fangyouReportFailLogMapper.selectByReportType(reqMap);
    		
    	
    		if(yfsendStatus==0){//成功
    			if(failLogDb!=null){
    				FangyouReportFailLog failLogUpt = new FangyouReportFailLog();
        			failLogUpt.setSendNum(failLogDb.getSendNum()+1);//sendNum不会为null
        			failLogUpt.setSendStatus(1);
        			failLogUpt.setId(failLogDb.getId());
        			//failLogUpt.setReqParamJson(reqParamJson);
        			fangyouReportFailLogMapper.updateByPrimaryKeySelective(failLogUpt);
    			}
    		}else{//失败
    			if(failLogDb==null){
        			FangyouReportFailLog failLogAdd = new FangyouReportFailLog();
        			failLogAdd.setDateCreate(new Date());
        			failLogAdd.setDelFlag("N");
        			failLogAdd.setReportId(reportId);
        			failLogAdd.setSendNum(0);
        			failLogAdd.setSendStatus(0);
        			failLogAdd.setTypeId(typeId);
        			failLogAdd.setUserIdCreate(0);
        			failLogAdd.setReqParamJson(reqParamJson);
        			fangyouReportFailLogMapper.insertSelective(failLogAdd);
        		}else{
        			FangyouReportFailLog failLogUpt = new FangyouReportFailLog();
        			failLogUpt.setSendNum(failLogDb.getSendNum()+1);//默认为0 sendNum不会为null
        			failLogUpt.setId(failLogDb.getId());
        			//failLogUpt.setReqParamJson(reqParamJson);
        			fangyouReportFailLogMapper.updateByPrimaryKeySelective(failLogUpt);
        		}
    		}
    		
		} catch (Exception e) {
			logger.error("yFInterfaceService","YFInterfaceService","handleYFFailLog",null, null,null,
					 "调用有房接口失败创建日志异常:######reportId="+reportId+"#####typeId"+typeId+"####",e);
		}
    }
    
    public void taskYFFailLog() {
    	try {
    		List<FangyouReportFailLog> failList = fangyouReportFailLogMapper.selectAllFailRecord();
    		for (FangyouReportFailLog failLogDB : failList) {
    			Map<String, Object> reportMap = new HashMap<String, Object>();
    			reportMap.put("reportId", failLogDB.getReportId());
    			Report reportDb = reportMapper.getReport(reportMap).get(0);
    			
    			if(StringUtil.isEmpty(failLogDB.getReqParamJson()) || reportDb==null){
    				continue;
    			}
    			
    			
    			String reqParamJson = failLogDB.getReqParamJson();
    			YFStatusSync yFStatusSync = JSON.parseObject(reqParamJson, YFStatusSync.class);
    			
				if("4".equals(failLogDB.getTypeId())){
					reportService.backToFangyou(reportDb, "888888", 1);
				}
				if("5".equals(failLogDB.getTypeId())){
					Map<String,Object> dealParam = new HashMap<String,Object>();
					dealParam.put("reportId", reportDb.getReportId());
					
					dealParam.put("operateDate", yFStatusSync.getSuccess_sale_time());
					dealParam.put("buildingNo", yFStatusSync.getBuilding_house_code());
					dealParam.put("area", yFStatusSync.getBuilding_area());
					dealParam.put("dealAmount", yFStatusSync.getTotal_price());
					sceneEstatetService.dealCrm(dealParam);
				}
				if("6".equals(failLogDB.getTypeId())){
					reportService.backToFangyou(reportDb, "888888", 2);
				}
				//代码未测  不可能出现大定审核通知失败的情况
//				if("8".equals(failLogDB.getTypeId())){
//					if("大定审核已通过".equals(yFStatusSync.getReport_status())){
//						reportDb.setRoughAuditStatus("1");
//					}else if("大定审核未通过".equals(yFStatusSync.getReport_status())){
//						reportDb.setRoughAuditStatus("2");
//					}
//					reportDb.setRoughAuditDesc(yFStatusSync.getUnpass_suggestion());
//				}
			}
    		
		} catch (Exception e) {
			logger.error("yFInterfaceService","YFInterfaceService","taskYFFailLog",null, null,null,
					 "调用有房接口执行定时任务异常",e);
		}
    }


	public  void noticeYx(String reportId,String typeId,String userCode,String old_house_id){
		Map<String, Object> reportMap = new HashMap<String, Object>();
		try {
			reportMap.put("reportId", reportId);
			Report reportDb = reportMapper.getReport(reportMap).get(0);//reportDb数据库已经修改提交

			boolean sendFlag = false;
			if(reportDb != null
                    && reportDb.getCustomerFrom() != null
                    && !"17405".equals(reportDb.getCustomerFrom().toString())){
			    //历史数据处理
                if(reportDb.getIsWrap()==null){
                    reportDb.setIsWrap(0);
                }
				if(StringUtil.isEmpty(reportDb.getBuildingNoId())){//因为空传0
					reportDb.setBuildingNoId("0");
				}

                if(reportDb.getIsWrap()==1 && !"14".equals(typeId)){
                    sendFlag = true;
                }

                if("14".equals(typeId)){
                    if("0".equals(old_house_id) && reportDb.getIsWrap()==1){
                        sendFlag = true;
                    }
                    if(!"0".equals(old_house_id) && reportDb.getIsWrap()==0){
						sendFlag = true;
                    }
					if(!"0".equals(old_house_id) && reportDb.getIsWrap()==1 && !old_house_id.equals(reportDb.getBuildingNoId())){
						sendFlag = true;
					}
                }
            }

			if(sendFlag){

				User userinfo = new User();
				userinfo = userMapper.getUserByCode(userCode);
				if (userinfo == null) {
					userinfo = new User();
				}

				Map<String, Object> params = new HashMap<>();
				if("11".equals(typeId)){
					params.put("old_house_id",0);
					params.put("new_house_id",reportDb.getBuildingNoId());

				}
				if("12".equals(typeId)){
					params.put("old_house_id",reportDb.getBuildingNoId());
					params.put("new_house_id",0);
				}
				if("13".equals(typeId)){
					params.put("old_house_id",reportDb.getBuildingNoId());
					params.put("new_house_id",0);
				}
				if("15".equals(typeId)){
					params.put("old_house_id",reportDb.getBuildingNoId());
					params.put("new_house_id",0);
				}
				if("14".equals(typeId)){
					params.put("old_house_id",old_house_id);
					params.put("new_house_id",reportDb.getBuildingNoId());
				}

				params.put("project_no", reportDb.getProjectNo());
				noticeYFInterfaceInfo(reportDb.getReportId(),JSON.toJSONString(params),typeId,userinfo.getUserId());
			}
		} catch (Exception e) {
			logger.error("yFInterfaceService", "YFInterfaceService", "noticeYx",
					null, null, "", "调用营销组装数据失败", e);
		}

	}

	private ResultData<String> noticeYFInterfaceInfo(String reportId,String paramMap ,String typeId,Integer userId) {
		ResultData<String> resultData = new ResultData<String>();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		FangyouReportLog fangyouReportLog = new FangyouReportLog();
		resultData.setFail("调用有房接口失败");

		String returnDataStr = null;
		String url = SystemParam.getWebConfigValue("youfangReportUrl") +"/ChangeHouseStatus";

		logger.info("调用有房接口start#####请求#url="+url);
		try {
			returnDataStr = HttpClientUtil.httpPostYF(url, paramMap);
			if(StringUtil.isNotEmpty(returnDataStr)){
				returnMap = JSON.parseObject(returnDataStr, Map.class);
				if(returnMap.containsKey("BFlag") && returnMap.containsKey("TData")){
					Integer bFlag = (Integer) returnMap.get("BFlag");
					Object tData = returnMap.get("TData");
					if(10==bFlag){
						resultData.setSuccess();
						resultData.setReturnData(JSON.toJSONString(tData));
					}else{
						resultData.setFail("调用接口失败");
					}
				}
			}
		} catch (Exception e) {
			try {
				logger.error("yFInterfaceService","YFInterfaceService","getyFInterfaceInfoPmls","userId="+ userId, userId,null,
						"调用有房接口:#####请求参数#url="+url+"#####返回信息"+returnDataStr,e);
			} catch (Exception e2) {

			}

			resultData.setFail("调用有房接口异常");
		}
		if(StringUtil.isNotEmpty(typeId)){
			fangyouReportLog.setTypeId(typeId);
			fangyouReportLog.setReqParamJson("reportId="+reportId+";json="+JSON.toJSONString(paramMap));
			fangyouReportLog.setRspParamJson(returnDataStr);
			fangyouReportLog.setDateCreate(new Date());
			fangyouReportLog.setUserIdCreate(userId);
			fangyouReportLog.setDelFlag("N");
			fangyouReportLogService.addLog(fangyouReportLog);
		}

		return resultData;
	}
}