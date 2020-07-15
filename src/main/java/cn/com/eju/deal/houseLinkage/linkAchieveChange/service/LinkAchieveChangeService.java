package cn.com.eju.deal.houseLinkage.linkAchieveChange.service;

import cn.com.eju.deal.achievement.dao.AchievementMapper;
import cn.com.eju.deal.achievement.model.Achievement;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.houseLinkage.linkAchieveChange.LinkAchieveChangeDto;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.dao.AchieveMentChangeLogMapper;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.dao.LinkAchieveChangeMapper;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.model.AchieveMentChangeLog;
import cn.com.eju.deal.houseLinkage.report.dao.ReportMapper;
import cn.com.eju.deal.houseLinkage.report.model.Report;
import cn.com.eju.deal.scene.estate.constant.SceneEstateConstant;
import cn.com.eju.deal.user.dao.GroupMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 联动业绩调整
 * Created by hzy on 2017/10/23.
 */
@Service("linkAchieveChangeService")
public class LinkAchieveChangeService {

    @Resource
    LinkAchieveChangeMapper linkAchieveChangeMapper;

    @Resource
    AchievementMapper achievementMapper;

    @Resource
    GroupMapper groupMapper;

    @Resource
    ReportMapper reportMapper;

    @Resource
    AchieveMentChangeLogMapper achieveMentChangeLogMapper;

    @Resource(name = "commonService")
    CommonService commonService;

    public ResultData getLinkAchieveChangeList(Map<String,Object> reqMap)throws Exception{
        ResultData resultData = new ResultData();
        resultData.setFail();

        List<LinkAchieveChangeDto> LinkAchieveChangeList =  linkAchieveChangeMapper.getLinkAchieveChangeList(reqMap);

        if(LinkAchieveChangeList != null){
            //处理未认定的值
            for(LinkAchieveChangeDto linkAchieveChangeDto :LinkAchieveChangeList){
                if (linkAchieveChangeDto.getConfirmStatus().intValue() == SceneEstateConstant.CONFIRMSTATUS_NOTSURE) {
                    linkAchieveChangeDto.setConfirmStatus(SceneEstateConstant.CONFIRMSTATUS_USE);
                    linkAchieveChangeDto.setConfirmStatusName(SystemParam.getDicValueByDicCode(String.valueOf(linkAchieveChangeDto.getConfirmStatus())));
                    linkAchieveChangeDto.setLatestProgress(linkAchieveChangeDto.getLatestProgress() - 1);
                    linkAchieveChangeDto.setLatestProgressName(SystemParam.getDicValueByDicCode(String.valueOf(linkAchieveChangeDto.getLatestProgress())));
                }
            }

            resultData.setReturnData(LinkAchieveChangeList);
            resultData.setTotalCount((String)reqMap.get(QueryConst.TOTAL_COUNT));
            resultData.setSuccess();
        }

        return resultData;
    }

    public ResultData saveLinkAchieve(Map<String,Object> reqMap)throws Exception{
        ResultData resultData = new ResultData();
        resultData.setFail();

        String ids = (String)reqMap.get("ids");
        if(StringUtil.isEmpty(ids)){
            resultData.setFail("请至少选择一条报备!");
            return resultData;
        }
        String selectCenterId = (String)reqMap.get("selectCenterId");
        if(StringUtil.isEmpty(selectCenterId)){
            resultData.setFail("请选择一个业绩归属人！");
            return resultData;
        }

        String userCode = (String)reqMap.get("userCode");
        String userName = (String)reqMap.get("userName");
        String createUserCode = (String)reqMap.get("createUserCode");
        String createUserName = (String)reqMap.get("createUserName");

        String idArray[] = ids.split(",");

        //获取CRM关账日期
        Map<String,Object> switchQueryMap = new HashMap<>();
        switchQueryMap.put("relateSystem", "17402");
        Map<String, Object> switchMap = commonService.getSwitchLnk(switchQueryMap);
        String recordReportNo = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(int i = 0,length = idArray.length;i < length;i++){
            Report oldReport = reportMapper.getById(Integer.parseInt(idArray[i]));
            if(oldReport != null){
                //处理一下未认定的值
                if (oldReport.getConfirmStatus().intValue() == SceneEstateConstant.CONFIRMSTATUS_NOTSURE) {
                    oldReport.setConfirmStatus(SceneEstateConstant.CONFIRMSTATUS_USE);
                    oldReport.setConfirmStatusNm(SystemParam.getDicValueByDicCode(String.valueOf(oldReport.getConfirmStatus())));
                    oldReport.setLatestProgress(oldReport.getLatestProgress() - 1);
                    oldReport.setLatestProgressNm(SystemParam.getDicValueByDicCode(String.valueOf(oldReport.getLatestProgress())));
                }
                //大定和成销,并且有效的时候，需要判断是否关账
                String switchDateStr = (String)switchMap.get(oldReport.getCityNo());
                Date switchDate = sdf.parse(switchDateStr);
                Date reportDate = null;
                if(SceneEstateConstant.PROGRESS_SUBSCRIBED == oldReport.getLatestProgress().intValue()){
                    if(SceneEstateConstant.CONFIRMSTATUS_USE == oldReport.getConfirmStatus().intValue()){
                        reportDate = oldReport.getRoughDate();
                    }
                }
                if(SceneEstateConstant.PROGRESS_BARGAIN == oldReport.getLatestProgress().intValue()){
                    if(SceneEstateConstant.CONFIRMSTATUS_USE == oldReport.getConfirmStatus().intValue()) {
                        reportDate = oldReport.getDealDate();
                    }
                }

                if(reportDate != null){
                    //关账
                    if(reportDate.getTime() <= switchDate.getTime()){
                        recordReportNo += oldReport.getReportId() + ",";
                    }
                }
            }else{
                resultData.setFail("报备数据异常！");
                return resultData;
            }
        }
        if(StringUtil.isNotEmpty(recordReportNo)){
            recordReportNo = recordReportNo.substring(0,recordReportNo.length()-1);
            resultData.setFail("报备"+recordReportNo + "已成销或已大定，且已关账，无法调整业绩!");
            return resultData;
        }

        //根据选择的人的所属中心查询出此人的业绩归属信息
        Map<String,Object> achieveMap = new HashMap<>();
        achieveMap.put("centerGroupId",selectCenterId);
        achieveMap.put("achieveType", DictionaryConstants.ACHIEVETYPE_LINK);
        Achievement achievement = achievementMapper.getAchievementInfoContract(achieveMap);

        if(achievement != null){

            achievement.setExpenderCode(userCode);
            achievement.setExpenderName(userName);

            String groupUserFlag = achievement.getGroupUserFlag();
            if("1".equals(groupUserFlag)){
                //为1的时候，需要查找上级归属信息
                Map<String, String> groupMap = new HashMap<>();
                groupMap.put("userCode",userCode);
                // SJBM
                groupMap.put("typeCode", SystemParam.getWebConfigValue("lnkGroupConfig"));
                Map<String,String> groupInfoMap = groupMapper.getAchieveLinkGroupInfo(groupMap);
                if(groupInfoMap != null){

                    String groupId = groupInfoMap.get("groupId");
                    if(StringUtil.isNotEmpty(groupId)){
                        achievement.setGroupId(Integer.parseInt(groupId));
                    }else{
                        achievement.setGroupId(null);
                    }

                    String groupName = groupInfoMap.get("groupName");
                    if(StringUtil.isNotEmpty(groupName)){
                        achievement.setGroupName(groupName);
                    }else{
                        achievement.setGroupName(null);
                    }

                    String groupLeaderCode = groupInfoMap.get("groupLeaderCode");
                    if(StringUtil.isNotEmpty(groupLeaderCode)){
                        achievement.setGroupLeaderCode(groupLeaderCode);
                    }else{
                        achievement.setGroupLeaderCode(null);
                    }

                    String groupLeaderName = groupInfoMap.get("groupLeaderName");
                    if(StringUtil.isNotEmpty(groupLeaderName)){
                        achievement.setGroupLeaderName(groupLeaderName);
                    }else{
                        achievement.setGroupLeaderName(null);
                    }

                }else{
                    resultData.setFail("选择的人员没有上级业绩归属信息，请确认！");
                    return resultData;
                }
            }

            for(int i = 0,length = idArray.length;i < length;i++){
                Report oldReport = reportMapper.getById(Integer.parseInt(idArray[i]));
                achievement.setRelateId(Integer.parseInt(idArray[i]));
                int count = achievementMapper.updateAchieveInfo(achievement);
                if(count < 1){
                    //小于1说明当前选中的报备单还没有业绩信息，此时新增业绩信息
                    achievementMapper.create(achievement);
                }

                //更新联动报备表中的业绩人
                Report report = new Report();
                report.setId(Integer.parseInt(idArray[i]));
                report.setContactId(userCode);
                report.setContactNm(userName);
                report.setUptDt(new Date());
                reportMapper.update(report);

                //添加日志
                AchieveMentChangeLog achieveMentChangeLog = new AchieveMentChangeLog();
                achieveMentChangeLog.setRelateId(Integer.parseInt(idArray[i]));
                achieveMentChangeLog.setChangeContent("业绩归属人由" + oldReport.getContactNm() + "(" + oldReport.getContactId() + ")" + "调整为" + userName + "(" + userCode + ")");
                achieveMentChangeLog.setCreateUserCode(createUserCode);
                achieveMentChangeLog.setCreateUserName(createUserName);
                achieveMentChangeLog.setCreateDate(new Date());
                achieveMentChangeLog.setDelFlag(false);
                achieveMentChangeLogMapper.insertSelective(achieveMentChangeLog);

            }
            resultData.setSuccess("修改成功！");
        }else{
            resultData.setFail("选择的人员没有业绩归属信息，请确认！");
        }

        return resultData;
    }
}
