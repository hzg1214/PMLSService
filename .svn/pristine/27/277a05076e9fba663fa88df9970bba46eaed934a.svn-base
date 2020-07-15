package cn.com.eju.deal.service.satisfactionSurvey;

import cn.com.eju.deal.base.linkage.dao.CityMapper;
import cn.com.eju.deal.base.linkage.model.City;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.enums.SatisEvaluationEnum;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.statisfactionSurvey.SurveyDaDto;
import cn.com.eju.deal.dto.statisfactionSurvey.SurveyDto;
import cn.com.eju.deal.dto.statisfactionSurvey.SurveyFlDto;
import cn.com.eju.deal.keFuWj.dao.*;
import cn.com.eju.deal.keFuWj.dto.KeFuWjDto;
import cn.com.eju.deal.keFuWj.model.*;
import cn.com.eju.deal.store.dao.StoreMapper;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by haidan on 2019/6/25.
 */
@Service("satisfactionSurveyService")
public class SatisfactionSurveyService {

    @Resource
    private KefuWjSatisfactionMapper kefuWjSatisfactionMapper;

    @Resource
    private KefuWjCitymappingMapper kefuWjCitymappingMapper;

    @Resource
    private KefuWjHMapper kefuWjHMapper;

    @Resource
    private KefuWjSatisfactionFlMapper kefuWjSatisfactionFlMapper;

    @Resource
    private KefuWjSatisfactionDMapper kefuWjSatisfactionDMapper;

    @Resource
    private KefuWjDTmMapper kefuWjDTmMapper;

    @Resource
    private KefuWjDDaMapper kefuWjDDaMapper;

    @Resource
    private FileRecordMainMapper fileRecordMainMapper;

    @Resource
    private CityMapper cityMapper;

    @Resource
    private StoreMapper storeMapper;

    @Resource
    private KefuWjSatisfactionEvaluationMapper kefuWjSatisfactionEvaluationMapper;

    public ResultData getStoreData(Integer id) throws Exception {
        ResultData resultData = new ResultData();
        Map<String, Object> storeMap = kefuWjSatisfactionMapper.getStoreData(id);
        if (storeMap != null) {
            resultData.setReturnData(storeMap);
        }
        return resultData;
    }

    public ResultData checkSurvey(String storeNo) throws Exception {
        ResultData resultData = new ResultData();
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("storeNo", storeNo);

        Calendar c = Calendar.getInstance();

        int currentMonthLast = c.get(Calendar.MONTH) + 1;
        int currentQuarterLast = 0;
        if (currentMonthLast >= 1 && currentMonthLast <= 3) {
            currentQuarterLast = 1;
        } else if (currentMonthLast >= 4 && currentMonthLast <= 6) {
            currentQuarterLast = 2;
        } else if (currentMonthLast >= 7 && currentMonthLast <= 9) {
            currentQuarterLast = 3;
        } else if (currentMonthLast >= 10 && currentMonthLast <= 12) {
            currentQuarterLast = 4;
        }
        int currentYearLast = c.get(Calendar.YEAR);
        String wjdcdate = currentYearLast + "年第" + currentQuarterLast + "季度";
        reqMap.put("wjdcdate", wjdcdate);
        Integer relCount = kefuWjSatisfactionMapper.checkSurvey(reqMap);
        if (relCount > 0) {
            resultData.setFail("该门店本季度已做满意度调查!");
        }
        return resultData;
    }

    public ResultData getSurveyData(Integer id) throws Exception {
        ResultData resultData = new ResultData();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> surveyData = kefuWjSatisfactionMapper.getSurveyData(id);
        if (null != surveyData) {
            map.put("data", surveyData);
            String storeStatus = (String) surveyData.get("storeStatus");
            String wjdcStatus = (String) surveyData.get("wjdcStatus");
            if (!storeStatus.equals("24801")) {//如果为非营业状态 获取门店照片
                Map<String, Object> reqMap = new HashMap<>();
                reqMap.put("refId", id);
                reqMap.put("fileTypeId", 1063);
                reqMap.put("fileSourceId", 18);
                List<FileRecordMain> fileList = fileRecordMainMapper.queryList(reqMap);
                if (null != fileList && !fileList.isEmpty()) {
                    map.put("imgList", fileList);
                }
            } else {
                //add by haidan 2019-09-23 获取门店测评数据
                List<Map<String,Object>> cpList = kefuWjSatisfactionEvaluationMapper.getEvaluationList(id);
                if (null != cpList && !cpList.isEmpty()) {
                    map.put("cpList", cpList);
                }
                if (wjdcStatus.equals("25002")) {//如果问卷调查已完成 获取答题记录
                    //获取问卷类型
                    List<Map<String, Object>> flList = kefuWjSatisfactionFlMapper.getFLScore(id);
                    if (null != flList && !flList.isEmpty()) {
                        map.put("flList", flList);
                    }
                    //获取题目及选项
                    List<Map<String, Object>> tmList = kefuWjSatisfactionDMapper.getTmList(id);
                    if (null != tmList && !tmList.isEmpty()) {
                        for (Map<String, Object> tm : tmList) {
                            Integer wjdid = (Integer) tm.get("id");
                            List<Map<String, Object>> daLis = kefuWjDDaMapper.getDaByTmId(wjdid);
                            if (null != daLis && !daLis.isEmpty()) {
                                String checkedDas = (String) tm.get("wjdaids");
                                if (StringUtil.isNotEmpty(checkedDas)) {
                                    String[] daArray = checkedDas.split(",");
                                    for (Map<String, Object> da : daLis) {
                                        String daId = String.valueOf(da.get("id"));
                                        if (Arrays.asList(daArray).contains(daId)) {
                                            da.put("checked", 1);
                                        }
                                    }
                                }
                                tm.put("daList", daLis);
                            }

                        }
                        map.put("tmList", tmList);
                    }
                }
            }
            resultData.setReturnData(map);
        }
        return resultData;
    }

    public ResultData saveSurvey(Map<String, Object> reqMap) throws Exception {
        ResultData resultData = new ResultData();
        KefuWjSatisfaction dto = this.mapToKefuWjSatisfaction(reqMap);

        if (StringUtil.isEmpty(dto.getStoreStatus())) {
            resultData.setFail("门店现状保存失败，请联系管理员！");
            return resultData;
        }

        KefuWjCitymapping kefuWjCitymappings = kefuWjCitymappingMapper.queryCityAvailable(dto.getAcCityNo());
        if (kefuWjCitymappings == null) {
            String cityName = "";
            City city = cityMapper.getCityByCityNo(dto.getAcCityNo());
            if (city != null) {
                cityName = city.getCityName();
            }
            resultData.setFail(cityName + "问卷模板未维护，请联系集团业管！");
            return resultData;
        }

        dto.setWjCode(kefuWjCitymappings.getWjCode());
        Integer relCount = kefuWjSatisfactionMapper.insert(dto);

        if (relCount > 0) {
            resultData.setReturnData(dto);
            if ("24801".equals(dto.getStoreStatus())) {
                //add by haidan 2019-09-23
                List<KefuWjSatisfactionEvaluation> evalDtoList = this.mapToEvaluation(reqMap, dto.getId(), dto.getUserCreate());
                kefuWjSatisfactionEvaluationMapper.batchCreate(evalDtoList);

                //计算测评总分
                Integer cpTotalScore = 0;
                for (KefuWjSatisfactionEvaluation eval : evalDtoList) {
                    cpTotalScore = cpTotalScore + eval.getCpScore();
                }
                KefuWjSatisfaction sa = new KefuWjSatisfaction();
                sa.setId(dto.getId());
                sa.setCpTotalScore(new BigDecimal(cpTotalScore));
                kefuWjSatisfactionMapper.updateByPrimaryKeySelective(sa);

                /*//修改对应门店表的门店规模字段
                Integer cpMdgm = Integer.valueOf(reqMap.get("cpMdgm").toString());
                Integer storeId = Integer.valueOf(reqMap.get("storeId").toString());
                if (storeId > 0) {
                    Store store = new Store();
                    store.setStoreId(storeId);
                    store.setStoreSizeScale(cpMdgm);
                    storeMapper.update(store);
                }*/

                List<Map<String, Object>> wjFlMaps = kefuWjHMapper.getWjFlByWjcode(kefuWjCitymappings.getWjCode());
                if (null != wjFlMaps && wjFlMaps.size() > 0) {
                    for (Map<String, Object> wjFlMap : wjFlMaps) {
                        KefuWjSatisfactionFl wjFl = new KefuWjSatisfactionFl();
                        wjFl.setSatisfactionId(dto.getId());
                        wjFl.setWjflCode((String) wjFlMap.get("wjtmflType"));
                        wjFl.setUserCreate(dto.getUserCreate());
                        kefuWjSatisfactionFlMapper.insert(wjFl);
                    }
                }
                List<Map<String, Object>> wjTmMaps = kefuWjDTmMapper.getWjTmByWjcode(kefuWjCitymappings.getWjCode());
                if (null != wjTmMaps && wjTmMaps.size() > 0) {
                    for (Map<String, Object> wjTmMap : wjTmMaps) {
                        KefuWjSatisfactionD wjTm = new KefuWjSatisfactionD();
                        wjTm.setSatisfactionId(dto.getId());
                        wjTm.setUserCreate(dto.getUserCreate());
                        wjTm.setWjtmid((Integer) wjTmMap.get("id"));
                        kefuWjSatisfactionDMapper.insert(wjTm);
                    }
                }
            } else {
                //图片处理
                String fileIds = (String) reqMap.get("fileIds");
                if (StringUtil.isNotEmpty(fileIds)) {
                    //旧文件处理 --没有旧文件
                    //新文件处理
                    String[] fileIdArr = fileIds.split(",");
                    List<Integer> fileIdList = new ArrayList<>();
                    for (String fileId : fileIdArr) {
                        fileIdList.add(Integer.valueOf(fileId));
                    }
                    Map<String, Object> uptmap = new HashMap<>();
                    uptmap.put("contractId", dto.getId());
                    uptmap.put("ids", fileIdList);
                    fileRecordMainMapper.updateByCondition(uptmap);
                }
            }
        }
        return resultData;
    }

    private KefuWjSatisfaction mapToKefuWjSatisfaction(Map<String, Object> reqMap) {
        KefuWjSatisfaction kefuWjSatisfaction = new KefuWjSatisfaction();
        kefuWjSatisfaction.setDcNo(reqMap.get("dcNo") == null ? "" : (String) reqMap.get("dcNo"));
        kefuWjSatisfaction.setStoreNo(reqMap.get("storeNo") == null ? "" : (String) reqMap.get("storeNo"));
        kefuWjSatisfaction.setWjdcdate(new Date());
        kefuWjSatisfaction.setWjdcjd(reqMap.get("wjdcjd") == null ? "" : (String) reqMap.get("wjdcjd"));
        kefuWjSatisfaction.setWjdcStatus("25001");
        kefuWjSatisfaction.setStoreStatus(reqMap.get("storeStatus") == null ? "" : (String) reqMap.get("storeStatus"));
        kefuWjSatisfaction.setDcObjectName(reqMap.get("dcObjectName") == null ? "" : (String) reqMap.get("dcObjectName"));
        kefuWjSatisfaction.setDcObjectTel(reqMap.get("dcObjectTel") == null ? "" : (String) reqMap.get("dcObjectTel"));
        kefuWjSatisfaction.setDzStatus(reqMap.get("dzStatus") == null ? "" : (String) reqMap.get("dzStatus"));
        kefuWjSatisfaction.setBjqStatus(reqMap.get("bjqStatus") == null ? "" : (String) reqMap.get("bjqStatus"));
        kefuWjSatisfaction.setKtbStatus(reqMap.get("ktbStatus") == null ? "" : (String) reqMap.get("ktbStatus"));
        kefuWjSatisfaction.setOtherRemark(reqMap.get("otherRemark") == null ? "" : (String) reqMap.get("otherRemark"));
        kefuWjSatisfaction.setComments(reqMap.get("comments") == null ? "" : (String) reqMap.get("comments"));
        kefuWjSatisfaction.setUserCreate((Integer) reqMap.get("userCreate"));
        kefuWjSatisfaction.setAcCityNo(reqMap.get("cityNo") == null ? "" : (String) reqMap.get("cityNo"));
        return kefuWjSatisfaction;
    }

    private List<KefuWjSatisfactionEvaluation> mapToEvaluation(Map<String, Object> reqMap, Integer satisfactionId, Integer userId) {
        List<KefuWjSatisfactionEvaluation> list = new ArrayList<>();
        //区域 主城中心区、非主城中心区、非主城区，分数分别为15分、10分、5分。
        KefuWjSatisfactionEvaluation evaluDto = new KefuWjSatisfactionEvaluation();
        evaluDto.setUserCreate(userId);
        evaluDto.setSatisfactionId(satisfactionId);
        String cpQyStr = reqMap.get("cpQyStr").toString();
        String cpQy = reqMap.get("cpQy").toString();
        evaluDto.setCpTitle(25601);
        evaluDto.setCpTitleStr("区域");
        evaluDto.setCpAnswer(cpQy);
        evaluDto.setCpAnswerStr(cpQyStr);
        evaluDto.setCpScore(SatisEvaluationEnum.getNameByCode(cpQy));
        list.add(evaluDto);

        //凝聚力 铁粉、路人粉、僵尸粉，分数分别为10分、5分、0分。
        evaluDto = new KefuWjSatisfactionEvaluation();
        evaluDto.setUserCreate(userId);
        evaluDto.setSatisfactionId(satisfactionId);
        String cpNjlStr = reqMap.get("cpNjlStr").toString();
        String cpNjl = reqMap.get("cpNjl").toString();
        evaluDto.setCpTitle(25602);
        evaluDto.setCpTitleStr("凝聚力");
        evaluDto.setCpAnswer(cpNjl);
        evaluDto.setCpAnswerStr(cpNjlStr);
        evaluDto.setCpScore(SatisEvaluationEnum.getNameByCode(cpNjl));
        list.add(evaluDto);

        //服务需求 品牌、招聘、培训、服务、系统、交易、公盘、联动，分数分别各为5分。
        evaluDto = new KefuWjSatisfactionEvaluation();
        evaluDto.setUserCreate(userId);
        evaluDto.setSatisfactionId(satisfactionId);
        String cpFwxqStr = reqMap.get("cpFwxqStr").toString();
        String cpFwxq = reqMap.get("cpFwxq").toString();
        evaluDto.setCpTitle(25603);
        evaluDto.setCpTitleStr("服务需求");
        evaluDto.setCpAnswer(cpFwxq);
        evaluDto.setCpAnswerStr(cpFwxqStr);
        Integer fwxqLen = cpFwxq.split(",").length;
        evaluDto.setCpScore(fwxqLen * 5);
        list.add(evaluDto);

        //服务潜力 品牌、招聘、培训、服务、系统、交易、公盘、联动，分数分别各为5分。
        evaluDto = new KefuWjSatisfactionEvaluation();
        evaluDto.setUserCreate(userId);
        evaluDto.setSatisfactionId(satisfactionId);
        String cpFwqlStr = reqMap.get("cpFwqlStr").toString();
        String cpFwql = reqMap.get("cpFwql").toString();
        evaluDto.setCpTitle(25604);
        evaluDto.setCpTitleStr("服务潜力");
        evaluDto.setCpAnswer(cpFwql);
        evaluDto.setCpAnswerStr(cpFwqlStr);
        boolean status = cpFwql.contains("26807");
        if(status){
            Integer fwqlLen = cpFwql.split(",").length-1;
            evaluDto.setCpScore(fwqlLen * 5);
        }else{
            Integer fwqlLen = cpFwql.split(",").length;
            evaluDto.setCpScore(fwqlLen * 5);
        }
        list.add(evaluDto);

        //门店规模 大型、小型 和微型，分数分别为20分，15分，10分。测评提交后更新门店上的门店规模。
        evaluDto = new KefuWjSatisfactionEvaluation();
        evaluDto.setUserCreate(userId);
        evaluDto.setSatisfactionId(satisfactionId);
        String cpMdgmStr = reqMap.get("cpMdgmStr").toString();
        String cpMdgm = reqMap.get("cpMdgm").toString();
        evaluDto.setCpTitle(25605);
        evaluDto.setCpTitleStr("门店规模");
        evaluDto.setCpAnswer(cpMdgm);
        evaluDto.setCpAnswerStr(cpMdgmStr);
        evaluDto.setCpScore(SatisEvaluationEnum.getNameByCode(cpMdgm));
        list.add(evaluDto);

        //品牌服务 店招合规、统一着装、场所规范、无负面报道，分数分别各为5分。
        evaluDto = new KefuWjSatisfactionEvaluation();
        evaluDto.setUserCreate(userId);
        evaluDto.setSatisfactionId(satisfactionId);
        String cpPpfwStr = reqMap.get("cpPpfwStr").toString();
        String cpPpfw = reqMap.get("cpPpfw").toString();
        evaluDto.setCpTitle(25606);
        evaluDto.setCpTitleStr("品牌服务");
        evaluDto.setCpAnswer(cpPpfw);
        evaluDto.setCpAnswerStr(cpPpfwStr);
        Integer ppfwLen = cpPpfw.split(",").length;
        evaluDto.setCpScore(ppfwLen * 5);
        list.add(evaluDto);

        //系统服务 经常使用房友系统、偶尔使用房友系统、开通但未使用房友系统、未开通房友系统，分数分别为20分，10分，5分，0分。
        evaluDto = new KefuWjSatisfactionEvaluation();
        evaluDto.setUserCreate(userId);
        evaluDto.setSatisfactionId(satisfactionId);
        String cpXtfwStr = reqMap.get("cpXtfwStr").toString();
        String cpXtfw = reqMap.get("cpXtfw").toString();
        evaluDto.setCpTitle(25607);
        evaluDto.setCpTitleStr("系统服务");
        evaluDto.setCpAnswer(cpXtfw);
        evaluDto.setCpAnswerStr(cpXtfwStr);
        evaluDto.setCpScore(SatisEvaluationEnum.getNameByCode(cpXtfw));
        list.add(evaluDto);

        //培训服务 经常参加培训且注册人才学院、经常参加培训、偶尔参加培训、未参加培训，分数分别为20分，15分，10分，0分。
        evaluDto = new KefuWjSatisfactionEvaluation();
        evaluDto.setUserCreate(userId);
        evaluDto.setSatisfactionId(satisfactionId);
        String cpPxfwStr = reqMap.get("cpPxfwStr").toString();
        String cpPxfw = reqMap.get("cpPxfw").toString();
        evaluDto.setCpTitle(25608);
        evaluDto.setCpTitleStr("培训服务");
        evaluDto.setCpAnswer(cpPxfw);
        evaluDto.setCpAnswerStr(cpPxfwStr);
        evaluDto.setCpScore(SatisEvaluationEnum.getNameByCode(cpPxfw));
        list.add(evaluDto);

        //招聘服务 达成招聘需求、发布招聘需求、注册直聘、未注册直聘，分数分别为20分，15分，10分，0分。
        evaluDto = new KefuWjSatisfactionEvaluation();
        evaluDto.setUserCreate(userId);
        evaluDto.setSatisfactionId(satisfactionId);
        String cpZpfwStr = reqMap.get("cpZpfwStr").toString();
        String cpZpfw = reqMap.get("cpZpfw").toString();
        evaluDto.setCpTitle(25609);
        evaluDto.setCpTitleStr("招聘服务");
        evaluDto.setCpAnswer(cpZpfw);
        evaluDto.setCpAnswerStr(cpZpfwStr);
        evaluDto.setCpScore(SatisEvaluationEnum.getNameByCode(cpZpfw));
        list.add(evaluDto);

        //交易服务 有交易进件、无交易进件，分数分别为20分、0分。
        evaluDto = new KefuWjSatisfactionEvaluation();
        evaluDto.setUserCreate(userId);
        evaluDto.setSatisfactionId(satisfactionId);
        String cpJyywStr = reqMap.get("cpJyywStr").toString();
        String cpJyyw = reqMap.get("cpJyyw").toString();
        evaluDto.setCpTitle(25610);
        evaluDto.setCpTitleStr("交易服务");
        evaluDto.setCpAnswer(cpJyyw);
        evaluDto.setCpAnswerStr(cpJyywStr);
        evaluDto.setCpScore(SatisEvaluationEnum.getNameByCode(cpJyyw));
        list.add(evaluDto);

        //联动业务 已成交联动项目、已参加联动项目、未参加联动项目，分数分别为20分、10分、0分。
        evaluDto = new KefuWjSatisfactionEvaluation();
        evaluDto.setUserCreate(userId);
        evaluDto.setSatisfactionId(satisfactionId);
        String cpLdywStr = reqMap.get("cpLdywStr").toString();
        String cpLdyw = reqMap.get("cpLdyw").toString();
        evaluDto.setCpTitle(25611);
        evaluDto.setCpTitleStr("联动业务");
        evaluDto.setCpAnswer(cpLdyw);
        evaluDto.setCpAnswerStr(cpLdywStr);
        evaluDto.setCpScore(SatisEvaluationEnum.getNameByCode(cpLdyw));
        list.add(evaluDto);

        //公盘业务 已成交公盘业务、已参加公盘业务、未参加公盘业务，分数分别为20分、10分、0分。
        evaluDto = new KefuWjSatisfactionEvaluation();
        evaluDto.setUserCreate(userId);
        evaluDto.setSatisfactionId(satisfactionId);
        String cpGpywStr = reqMap.get("cpGpywStr").toString();
        String cpGpyw = reqMap.get("cpGpyw").toString();
        evaluDto.setCpTitle(25612);
        evaluDto.setCpTitleStr("公盘业务");
        evaluDto.setCpAnswer(cpLdyw);
        evaluDto.setCpAnswerStr(cpGpywStr);
        evaluDto.setCpScore(SatisEvaluationEnum.getNameByCode(cpGpyw));
        list.add(evaluDto);
        return list;
    }

    public ResultData savePushInfo(Map<String, Object> reqMap) {
        ResultData resultData = new ResultData();
        int relCount = kefuWjSatisfactionMapper.savePushInfo(reqMap);
        if (relCount > 0) {
            resultData.setSuccess();
        } else {
            resultData.setFail();
        }
        return resultData;
    }

    public ResultData getSurveyDetailByCode(String wjCode) {
        ResultData resultData = new ResultData();
        KeFuWjDto keFuWjDto = kefuWjHMapper.getSurveyDetailByCode(wjCode);
        if (keFuWjDto != null) {
            resultData.setReturnData(keFuWjDto);
        }
        return resultData;
    }

    public ResultData updateSurvey(SurveyDto dto) {
        ResultData resultData = new ResultData();
        //校验手机号次数
        String userTel = dto.getWjfkTel();
        int telCount = kefuWjSatisfactionMapper.selectTelCount(userTel);
        if (telCount >= 3) {
            resultData.setReturnCode("-1");
            resultData.setReturnMsg("手机号码使用多次，无法提交问卷！");
            resultData.setReturnData(telCount);
            return resultData;
        }
        KefuWjSatisfaction kefuWjSatisfaction = new KefuWjSatisfaction();
        kefuWjSatisfaction.setId(dto.getSId());
        kefuWjSatisfaction.setWjfkTel(dto.getWjfkTel());
        if (dto.getAllMaxScore() == 0) {
            kefuWjSatisfaction.setWjdcTotalscore(new BigDecimal(0));
        } else {
            Double score = Double.valueOf(dto.getAlldirectScore()) / Double.valueOf(dto.getAllMaxScore()) * 100;
            kefuWjSatisfaction.setWjdcTotalscore(new BigDecimal(score).setScale(2, RoundingMode.HALF_UP));
        }
        kefuWjSatisfaction.setWjfkdate(new Date());
        kefuWjSatisfaction.setWjdcStatus(DictionaryConstants.WJDCSTATUS_WC);
        //更新Kefu_Wj_Satisfaction
        int count = kefuWjSatisfactionMapper.updateByPrimaryKeySelective(kefuWjSatisfaction);
        if (count >= 1) {
            //更新Kefu_Wj_Satisfaction_D
            List<SurveyDaDto> daDtoList = JSON.parseArray(dto.getDaList(), SurveyDaDto.class);
            if (daDtoList != null && daDtoList.size() > 0) {
                for (SurveyDaDto surveyDaDto : daDtoList) {
                    KefuWjSatisfactionD kefuWjSatisfactionD = new KefuWjSatisfactionD();
                    kefuWjSatisfactionD.setSatisfactionId(dto.getSId());
                    kefuWjSatisfactionD.setWjtmid(surveyDaDto.getTmid());
                    kefuWjSatisfactionD.setTmScore(surveyDaDto.getDirectScore());
                    kefuWjSatisfactionD.setWjdaids(surveyDaDto.getWjdaids());
                    kefuWjSatisfactionD.setWjdacomments(surveyDaDto.getWjdacomments());
                    if (surveyDaDto.getWjtmMaxScore() == 0) {
                        kefuWjSatisfactionD.setWjtmScore(new BigDecimal(0));
                    } else {
                        Double score = Double.valueOf(surveyDaDto.getDirectScore()) / Double.valueOf(surveyDaDto.getWjtmMaxScore()) * 100;
                        kefuWjSatisfactionD.setWjtmScore(new BigDecimal(score).setScale(2, RoundingMode.HALF_UP));
                    }
                    kefuWjSatisfactionDMapper.updateBySatisfactionIdAndTmid(kefuWjSatisfactionD);
                }
            }
            //查询各分类总分
            List<SurveyFlDto> flDtoList = kefuWjDTmMapper.getFlDtoList(dto.getHid());
            if (flDtoList != null) {
                for (SurveyFlDto surveyFlDto : flDtoList) {
                    KefuWjSatisfactionFl kefuWjSatisfactionFl = new KefuWjSatisfactionFl();
                    Integer flAllScore = 0;
                    //判断被除数是否为0
                    if (surveyFlDto.getWjflScore() == 0) {
                        kefuWjSatisfactionFl.setWjflScore(new BigDecimal(0));
                        kefuWjSatisfactionFl.setSatisfactionId(dto.getSId());
                        kefuWjSatisfactionFl.setWjflCode(surveyFlDto.getWjtmflType());
                        kefuWjSatisfactionFlMapper.updateByFlCodeAndSid(kefuWjSatisfactionFl);
                    } else {
                        if (daDtoList != null && daDtoList.size() > 0) {
                            for (SurveyDaDto surveyDaDto : daDtoList) {
                                if (surveyFlDto.getWjtmflType().equals(surveyDaDto.getWjtmflType())) {
                                    flAllScore += surveyDaDto.getDirectScore();
                                }
                            }
                            Double score = Double.valueOf(flAllScore) / Double.valueOf(surveyFlDto.getWjflScore()) * 100;
                            kefuWjSatisfactionFl.setWjflScore(new BigDecimal(score).setScale(2, RoundingMode.HALF_UP));
                            kefuWjSatisfactionFl.setSatisfactionId(dto.getSId());
                            kefuWjSatisfactionFl.setWjflCode(surveyFlDto.getWjtmflType());
                            kefuWjSatisfactionFlMapper.updateByFlCodeAndSid(kefuWjSatisfactionFl);
                        } else {
                            //如果被除数不为0且没有答案list
                            resultData.setFail("操作失败");
                            return resultData;
                        }
                    }
                }
            }
        } else {
            resultData.setFail("操作失败");
        }
        return resultData;
    }

    public ResultData querySatisfaction(Integer id) {
        ResultData resultData = new ResultData();
        Integer count = kefuWjSatisfactionMapper.querySatisfaction(id);
        resultData.setReturnData(count);
        return resultData;
    }
}
