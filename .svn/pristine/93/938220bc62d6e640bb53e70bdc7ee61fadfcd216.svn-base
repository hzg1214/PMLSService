package cn.com.eju.deal.otherReport.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.achievement.dao.AchievementSettingMapper;
import cn.com.eju.deal.achievement.dao.CitySettingMapper;
import cn.com.eju.deal.achievement.service.AchievementService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.seqNo.api.impl.SeqNoAPIImpl;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateMapper;
import cn.com.eju.deal.houseLinkage.estate.model.Estate;
import cn.com.eju.deal.otherReport.dao.LnkQtLogMapper;
import cn.com.eju.deal.otherReport.dao.LnkQtReportMapper;
import cn.com.eju.deal.otherReport.dao.LnkQtReportdetailMapper;
import cn.com.eju.deal.otherReport.dao.LnkQtYjReportMapper;
import cn.com.eju.deal.otherReport.dto.LnkQtReportDto;
import cn.com.eju.deal.otherReport.dto.QtReportDto;
import cn.com.eju.deal.otherReport.dto.YjQtReportDto;
import cn.com.eju.deal.otherReport.enums.QtReportEnum;
import cn.com.eju.deal.otherReport.model.LnkQtLog;
import cn.com.eju.deal.otherReport.model.LnkQtReport;
import cn.com.eju.deal.otherReport.model.LnkQtReportdetail;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.User;

/**
 * desc:其它收入-报备
 */
@Service("qtReportService")
public class QtReportService extends BaseService {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private LnkQtReportMapper qtReportMapper;

    @Resource
    private LnkQtLogMapper lnkQtLogMapper;

    @Resource
    private LnkQtReportdetailMapper qttReportdetailMapper;

    @Resource
    private CitySettingMapper citySettingMapper;

    @Resource
    private SeqNoAPIImpl seqNoAPI;

    @Resource
    private UserMapper userMapper;

    @Resource
    private EstateMapper estateMapper;

    @Resource(name = "achievementService")
    private AchievementService achievementService;

    @Resource
    private AchievementSettingMapper achievementSettingMapper;

	@Resource
    private LnkQtYjReportMapper lnkQtYjReportMapper;
    
    
    @SuppressWarnings("unchecked")
    public ResultData<List<LnkQtReportDto>> queryList(Map<?, ?> param)  throws Exception{
        //构建返回
        ResultData<List<LnkQtReportDto>> resultData = new ResultData<List<LnkQtReportDto>>();

        //查询REPORT信息
        final List<LnkQtReportDto> craList = qtReportMapper.queryList(param);

        resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(craList);
        return resultData;
    }


    /**
     * 查询所有归属城市
     */
    public ResultData<List< Map<String,Object>>> getBasCitySettingList(){
        ResultData<List< Map<String,Object>>> resultData = new ResultData<>();
        List< Map<String,Object>> list = citySettingMapper.getBasCitySettingList();
        resultData.setReturnData(list);
        return resultData;
    }

    /**
     * 查询归属城市下的归属中心
     * */
    public ResultData<List< Map<String,Object>>> getAchivAchievementLevelSettingList(String cityNo){
        ResultData<List< Map<String,Object>>> resultData = new ResultData<>();
        List< Map<String,Object>> list = citySettingMapper.getAchivAchievementLevelSettingList(cityNo);
        resultData.setReturnData(list);
        return resultData;
    }

    /**
     * 创建报备
     * */
    public ResultData<?> createReport(LnkQtReport lnkQtReport) throws Exception {
        ResultData<?> resultData = new ResultData<>();
        Integer userId = lnkQtReport.getCrtUserId();
        User user = userMapper.selectByPrimaryKey(userId);
        String estateId = lnkQtReport.getEstateId();
        List<Estate> estateList = estateMapper.selectEstateInfo(estateId);

        String accountProject = null;
        String accountProjectNo = null;
        if (null != estateList && !estateList.isEmpty()){
            Estate estate = estateList.get(0);
            accountProject = estate.getAccountProject();
            accountProjectNo = estate.getAccountProjectNo();
        }


        String reportNo = seqNoAPI.getSeqNoByTypeCode("TYPE_LDQT").getReturnData();
        lnkQtReport.setReportNo(reportNo);
        lnkQtReport.setReportStatus("27001");
        lnkQtReport.setValidStatus(0);
        lnkQtReport.setReportUserId(user.getUserId());
        lnkQtReport.setCrtUserId(user.getUserId());
        lnkQtReport.setCrtDate(new Date());
        lnkQtReport.setDelFlg(false);
        lnkQtReport.setAccountProject(accountProject);
        lnkQtReport.setAccountProjectNo(accountProjectNo);
        lnkQtReport.setBefYJSRAmount(lnkQtReport.getSrAmount());

        LnkQtReportdetail  lnkQtReportdetail = new LnkQtReportdetail();
        BeanUtils.copyProperties(lnkQtReport,lnkQtReportdetail);
        lnkQtReportdetail.setBusinessDate(lnkQtReport.getReportDate());
        lnkQtReportdetail.setBusinessType("27301");
        lnkQtReportdetail.setBusinessUserId(userId);

        LnkQtLog lnkQtLog = new LnkQtLog();
        BeanUtils.copyProperties(lnkQtReportdetail,lnkQtLog);
        lnkQtLog.setId(null);

        StringBuilder sb = new StringBuilder("");
        sb.append("报备（收入类型："+ QtReportEnum.getNameByCode(lnkQtReport.getSrType().toString()));
        sb.append("，收入金额："+ lnkQtReport.getSrAmount().setScale(2,BigDecimal.ROUND_HALF_UP)+"元");
        sb.append("，报备日期："+ DateUtil.fmtDate(lnkQtReport.getReportDate(),"yyyy-MM-dd")+")");
        lnkQtLog.setOpMemo(sb.toString());


        qtReportMapper.insertSelective(lnkQtReport);
        lnkQtReportdetail.setReportId(lnkQtReport.getId());
        qttReportdetailMapper.insertSelective(lnkQtReportdetail);
        lnkQtLog.setReportId(lnkQtReport.getId());
        lnkQtLogMapper.insertSelective(lnkQtLog);

       achievementService.saveQtAchievementInfo(user, lnkQtReport.getId(),DictionaryConstants.ACHIEVETYPE_QT, lnkQtReport.getCenterId(), lnkQtReport.getAccountProject(), lnkQtReport.getAccountProjectNo());
        return resultData;
    }


    /**
     * 创建报备
     * */
    public ResultData<?> validQtReport(Integer reportId,Integer userId) throws Exception {
        ResultData<?> resultData = new ResultData<>();
        LnkQtReport lnkQtReport = qtReportMapper.selectByPrimaryKey(reportId);
        if(!"27001".equals(lnkQtReport.getReportStatus()) || lnkQtReport.getValidStatus() ==1){
            resultData.setReturnCode("201");
            resultData.setFail("只有报备有效状态才能无效！");
            return resultData;
        }
        LnkQtReport uptReport = new LnkQtReport();
        uptReport.setId(reportId);
        uptReport.setValidStatus(1);
        uptReport.setValidDate(new Date());
        uptReport.setValidUserId(userId);
        uptReport.setUptDate(new Date());
        uptReport.setUptUserId(userId);
        qtReportMapper.updateByPrimaryKeySelective(uptReport);


        LnkQtLog lnkQtLog = new LnkQtLog();
        lnkQtLog.setBusinessDate( new Date());
        //lnkQtLog.setBusinessType();
        lnkQtLog.setBusinessUserId(userId);
        lnkQtLog.setCrtDate(new Date());
        lnkQtLog.setCrtUserId(userId);
        lnkQtLog.setDelFlg(false);
        lnkQtLog.setOpMemo("无效");
        lnkQtLog.setReportId(lnkQtReport.getId());
        lnkQtLog.setReportNo(lnkQtReport.getReportNo());
        lnkQtLogMapper.insertSelective(lnkQtLog);

        return resultData;
    }


    /**
     * desc:查看详情
     * 2019年10月15日
     * author:zhenggang.Huang
     */
    public ResultData getQtReportById(Integer id) throws Exception {
        ResultData<QtReportDto> resultData = new ResultData<>();
        resultData.setFail();
        QtReportDto qtReportDto = qtReportMapper.getQtReportById(id);
        qtReportDto.getEstate().setProjectDescription("");
        resultData.setReturnData(qtReportDto);
        resultData.setSuccess();
        return resultData;
    }
    
    /**
     * desc:业务节点-返佣对象
     * 2019年10月17日
     * author:zhenggang.Huang
     * @throws Exception 
     */
    public ResultData getOperDetail(Map<String,Object> map) throws Exception {
        
        ResultData<List<YjQtReportDto>> result = new ResultData<>();
        List<YjQtReportDto> dto = lnkQtYjReportMapper.getFyObjectByQtReportId(map);
        if(dto != null) {
            result.setReturnData(dto);
        }else {
            result.setFail();
        }
        
        return result;
        
    }
}
