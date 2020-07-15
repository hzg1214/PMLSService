package cn.com.eju.deal.otherReport.service;

import cn.com.eju.deal.achievement.service.AchievementService;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.service.FileRecordMainService;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.otherReport.dao.*;
import cn.com.eju.deal.otherReport.enums.QtReportEnum;
import cn.com.eju.deal.otherReport.model.*;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("qtSuccessSaleService")
public class QtSuccessSaleService {

    @Resource
    private LnkQtReportMapper qtReportMapper;

    @Resource
    private LnkQtReportdetailMapper qtReportdetailMapper;

    @Resource
    private LnkQtLogMapper lnkQtLogMapper;

    @Resource(name = "achievementService")
    private AchievementService achievementService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private FileRecordMainService fileRecordMainService;

    @Resource(name = "commonService")
    private CommonService commonService;

    @Resource
    private LnkQtYjReportMapper lnkYjQtReportMapper;

    @Resource
    private LnkYjQtYjfyMapper lnkYjQtYjfyMapper;

    @Resource
    private LnkYjQtYjsrMapper lnkYjQtYjsrMapper;

    /**
     * 成销
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<?> successSale(Map<String, Object> reqMap) throws Exception {
        ResultData<?> resultData = new ResultData<>();

        LnkQtReport reportDb = qtReportMapper.selectByPrimaryKey(Integer.valueOf(reqMap.get("id").toString()));
        if(!"27001".equals(reportDb.getReportStatus())){
            resultData.setFail("请不要重复提交 刷新页面查看！");
            return resultData;
        }
        String operateDate = reqMap.get("dealDate").toString();
        String accCityNo = reqMap.get("accCityNo").toString();
        Date dealDate = getFormatStringDate(operateDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
        //判断联动关账月份
        //获取CRM关账日期
        Map<String, Object> switchQueryMap = new HashMap<>();
        switchQueryMap.put("relateSystem", "17402");
        Map<String, Object> switchMap = commonService.getSwitchLnk(switchQueryMap);
        String switchDateStr = (String) switchMap.get(accCityNo);
        Date switchDate = getFormatStringDate(switchDateStr + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
        if (dealDate.getTime() < switchDate.getTime()) {
            resultData.setFail("填写的成销日期已关账，请重新选择！");
            return resultData;
        }

        Integer userId = Integer.valueOf(reqMap.get("dealUserId").toString());
        User user = userMapper.selectByPrimaryKey(userId);
        //更新主表
        LnkQtReport lnkQtReport = new LnkQtReport();
        Integer reportId = Integer.valueOf(reqMap.get("id").toString());
        Integer srType = Integer.valueOf(reqMap.get("srType").toString());
        String dealAmount = reqMap.get("dealAmount").toString();
        String befYJSRAmount = reqMap.get("befYJSRAmount").toString();
        String aftYJSRAmount = reqMap.get("aftYJSRAmount").toString();
        String befYJFYAmount = reqMap.get("befYJFYAmount").toString();
        String aftYJFYAmount = reqMap.get("aftYJFYAmount").toString();
        Integer centerId = Integer.valueOf(reqMap.get("centerId").toString());
        String centerName = reqMap.get("centerName").toString();
        Integer srCenterId = Integer.valueOf(reqMap.get("srCenterId").toString());
        String srCenterName = reqMap.get("srCenterName").toString();
        String accountProjectNo = reqMap.get("accountProjectNo").toString();
        String accountProject = reqMap.get("accountProject").toString();
        lnkQtReport.setId(reportId);
        lnkQtReport.setReportStatus("27002");
        lnkQtReport.setSrType(srType);
        lnkQtReport.setDealUserId(userId);
        lnkQtReport.setDealAmount(new BigDecimal(dealAmount));
        lnkQtReport.setDealDate(dealDate);
        lnkQtReport.setSrAmount(new BigDecimal(befYJSRAmount));
        lnkQtReport.setBefYJSRAmount(new BigDecimal(befYJSRAmount));
        lnkQtReport.setAftYJSRAmount(new BigDecimal(aftYJSRAmount));
        lnkQtReport.setBefYJFYAmount(new BigDecimal(befYJFYAmount));
        lnkQtReport.setAftYJFYAmount(new BigDecimal(aftYJFYAmount));
        lnkQtReport.setAccCityNo(accCityNo);
        lnkQtReport.setCenterId(centerId);
        lnkQtReport.setCenterName(centerName);
        lnkQtReport.setSrCenterId(srCenterId);
        lnkQtReport.setSrCenterName(srCenterName);
        lnkQtReport.setAccountProjectNo(accountProjectNo);
        lnkQtReport.setAccountProject(accountProject);
        lnkQtReport.setUptDate(new Date());
        lnkQtReport.setUptUserId(userId);
        qtReportMapper.updateByPrimaryKeySelective(lnkQtReport);

        //从表添加成销记录
        LnkQtReportdetail lnkQtReportdetail = new LnkQtReportdetail();
        LnkQtReport oldReport = qtReportMapper.getQtReportById(lnkQtReport.getId());
        BeanUtils.copyProperties(oldReport, lnkQtReportdetail);
        lnkQtReportdetail.setId(null);
        lnkQtReportdetail.setReportId(reportId);
        lnkQtReportdetail.setBusinessType("27302");
        lnkQtReportdetail.setBusinessUserId(userId);
        lnkQtReportdetail.setBusinessDate(dealDate);
        lnkQtReportdetail.setCrtDate(new Date());
        lnkQtReportdetail.setCrtUserId(userId);
        lnkQtReportdetail.setUptUserId(null);
        lnkQtReportdetail.setUptDate(null);
        qtReportdetailMapper.insertSelective(lnkQtReportdetail);

        //添加日志 成销（收入类型：带看奖，应计收入税前：23550元，成销日期：2019-10-10）
        LnkQtLog lnkQtLog = new LnkQtLog();
        BeanUtils.copyProperties(lnkQtReportdetail, lnkQtLog);
        lnkQtLog.setId(null);
        StringBuilder sb = new StringBuilder("");
        sb.append("成销（收入类型：" + QtReportEnum.getNameByCode(lnkQtReport.getSrType().toString()));
        sb.append("，应计收入税前：" + new BigDecimal(befYJSRAmount).setScale(2,BigDecimal.ROUND_HALF_UP) + "元");
        sb.append("，成销日期：" + operateDate + "）");
        lnkQtLog.setOpMemo(sb.toString());
        lnkQtLog.setReportId(lnkQtReport.getId());
        lnkQtLogMapper.insertSelective(lnkQtLog);

        //返佣对象 佣金导入
        List<Map<String, Object>> fyList = JSON.parseObject(String.valueOf(reqMap.get("fyList")), new TypeReference<List<Map<String, Object>>>() {
        });
        for (Map<String, Object> fyObj : fyList) {
            Integer detailId = lnkQtReportdetail.getId();
            String companyNo = (String) fyObj.get("companyNo");
            String companyName = (String) fyObj.get("companyName");
            String yjfyaftTaxAmount = (String) fyObj.get("yjfyaftTaxAmount");
            String yjfybefTaxAmount = (String) fyObj.get("yjfybefTaxAmount");

            //返佣对象
            LnkYjQtReport lnkYjQtReport = new LnkYjQtReport();
            lnkYjQtReport.setDetailId(detailId);
            lnkYjQtReport.setReportId(reportId);
            lnkYjQtReport.setCompanyNo(companyNo);
            lnkYjQtReport.setCompanyName(companyName);
            lnkYjQtReport.setYjfybefTaxAmount(new BigDecimal(yjfybefTaxAmount));
            lnkYjQtReport.setYjfyaftTaxAmount(new BigDecimal(yjfyaftTaxAmount));
            lnkYjQtReport.setCrtEmpId(userId);
            lnkYjQtReportMapper.insert(lnkYjQtReport);

            //应计返佣
            LnkYjQtYjfy lnkYjQtYjfy = new LnkYjQtYjfy();
            lnkYjQtYjfy.setReportId(reportId);
            lnkYjQtYjfy.setCompanyNo(companyNo);
            lnkYjQtYjfy.setNum(1);
            lnkYjQtYjfy.setBefTaxAmount(new BigDecimal(yjfybefTaxAmount));
            lnkYjQtYjfy.setAftTaxAmount(new BigDecimal(yjfyaftTaxAmount));
            lnkYjQtYjfy.setRecordDate(dealDate);
            lnkYjQtYjfy.setCrtEmpId(userId);
            lnkYjQtYjfyMapper.mergeInsert(lnkYjQtYjfy);

        }
        //应计收入
        LnkYjQtYjsr lnkYjQtYjsr = new LnkYjQtYjsr();
        lnkYjQtYjsr.setReportId(reportId);
        lnkYjQtYjsr.setNum(1);
        lnkYjQtYjsr.setBefTaxAmount(new BigDecimal(befYJSRAmount));
        lnkYjQtYjsr.setAftTaxAmount(new BigDecimal(aftYJSRAmount));
        lnkYjQtYjsr.setRecordDate(dealDate);
        lnkYjQtYjsr.setCrtEmpId(userId);
        lnkYjQtYjsrMapper.mergeInsert(lnkYjQtYjsr);

        //文件
        if (reqMap.get("fileRecordMainIds") != null && StringUtil.isNotEmpty(reqMap.get("fileRecordMainIds").toString()) && reqMap.get("id") != null) {
            String[] fileIdArr = reqMap.get("fileRecordMainIds").toString().split(",");
            FileRecordMain file;
            for (String fileId : fileIdArr) {
                file = new FileRecordMain();
                file.setRefId(reportId);
                file.setFileRecordMainId(Integer.valueOf(fileId));
                fileRecordMainService.update(file);
            }
        }
        //业绩
        achievementService.deleteQtAchievement(reportId);
        achievementService.saveQtAchievementInfo(user, reportId, DictionaryConstants.ACHIEVETYPE_QT, centerId, accountProject, accountProjectNo);
        return resultData;
    }

    private Date getFormatStringDate(String sDat, String strFormat) throws ParseException {
        if (StringUtil.isNotEmpty(sDat)) {
            // 解析日期
            SimpleDateFormat myFmt = new SimpleDateFormat(strFormat);
            return myFmt.parse(sDat);
        }
        return null;
    }

    /**
     * 退成销
     *
     * @param lnkQtReport
     * @return
     * @throws Exception
     */
    public ResultData<?> dealBack(LnkQtReport lnkQtReport) throws Exception {
        ResultData<?> resultData = new ResultData<>();

        Integer userId = lnkQtReport.getBackDealUserId();
        User user = userMapper.selectByPrimaryKey(userId);
        //更新主表
        LnkQtReport newReport = new LnkQtReport();
        newReport.setId(lnkQtReport.getId());
        newReport.setBackDealDate(lnkQtReport.getBackDealDate());
        newReport.setBackDealUserId(lnkQtReport.getBackDealUserId());
        newReport.setReportStatus("27001");
        newReport.setValidStatus(1);
        newReport.setUptDate(new Date());
        newReport.setUptUserId(lnkQtReport.getBackDealUserId());
        qtReportMapper.updateByPrimaryKeySelective(newReport);
        //从表添加退成销记录
        LnkQtReportdetail lnkQtReportdetail = new LnkQtReportdetail();
        LnkQtReport oldReport = qtReportMapper.getQtReportById(lnkQtReport.getId());
        BeanUtils.copyProperties(oldReport, lnkQtReportdetail);
        lnkQtReportdetail.setMemo(lnkQtReport.getMemo());
        lnkQtReportdetail.setReportId(lnkQtReport.getId());
        lnkQtReportdetail.setBusinessType("27303");
        lnkQtReportdetail.setBusinessUserId(lnkQtReport.getBackDealUserId());
        lnkQtReportdetail.setBusinessDate(lnkQtReport.getBackDealDate());
        lnkQtReportdetail.setCrtDate(new Date());
        lnkQtReportdetail.setCrtUserId(lnkQtReport.getBackDealUserId());
        lnkQtReportdetail.setUptUserId(null);
        lnkQtReportdetail.setUptDate(null);
        qtReportdetailMapper.insertSelective(lnkQtReportdetail);
        //添加日志 退成销（成销金额：5000.00元，成销日期：2019-09-17）
        LnkQtLog lnkQtLog = new LnkQtLog();
        BeanUtils.copyProperties(lnkQtReportdetail, lnkQtLog);
        lnkQtLog.setId(null);
        StringBuilder sb = new StringBuilder("");
        sb.append("退成销（成销金额：" + oldReport.getDealAmount().setScale(2,BigDecimal.ROUND_HALF_UP) + "元");
        sb.append("，成销日期：" + DateUtil.fmtDate(oldReport.getDealDate(), "yyyy-MM-dd") + "）");
        lnkQtLog.setOpMemo(sb.toString());
        lnkQtLog.setReportId(lnkQtReport.getId());
        lnkQtLogMapper.insertSelective(lnkQtLog);

        //业绩
        achievementService.deleteQtAchievement(lnkQtReport.getId());
        achievementService.saveQtAchievementInfo(user, lnkQtReport.getId(), DictionaryConstants.ACHIEVETYPE_QT, oldReport.getCenterId(), oldReport.getAccountProject(), oldReport.getAccountProjectNo());
        return resultData;
    }


}
