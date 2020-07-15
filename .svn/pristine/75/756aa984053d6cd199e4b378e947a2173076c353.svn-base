package cn.com.eju.deal.scene.commission.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.util.AmountUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.scene.commission.CommissionResultDto;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateMapper;
import cn.com.eju.deal.scene.commission.dao.LnkYjImportspecialuserMapper;
import cn.com.eju.deal.scene.commission.dao.LnkYjSjdyMapper;
import cn.com.eju.deal.scene.commission.dao.LnkYjYjdyMapper;
import cn.com.eju.deal.scene.commission.dao.LnkYjYjsrMapper;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * service层
 *
 * @author (luhaidan)
 * @date 2019/04/03
 */
@Service("lnkYjDyService")
public class LnkYjDyService extends BaseService {

    @Resource
    private EstateMapper estateMapper;

    @Resource
    LnkYjYjdyMapper lnkYjYjdyMapper;

    @Resource
    LnkYjSjdyMapper lnkYjSjdyMapper;

    @Resource
    LnkYjYjsrMapper lnkYjYjsrMapper;

    @Resource
    LnkYjImportspecialuserMapper lnkYjImportspecialuserMapper;

    /**
     * 获取垫佣列表
     *
     * @param map
     * @return
     * @throws Exception
     */
    public ResultData<List<Map<String, Object>>> getLnkYjDyList(Map<String, Object> map) throws Exception {
        ResultData<List<Map<String, Object>>> resultData = new ResultData<>();
        List<Map<String, Object>> relList = new ArrayList<>();
        String estateType = (String) map.get("estateType");

        int pageIndex = 1;
        int pageSize = 10;
        int curPage = 1;

        if (map.get("pageIndex") != null)
            pageIndex = Integer.parseInt(map.get("pageIndex").toString());
        if (map.get("pageSize") != null)
            pageSize = Integer.parseInt(map.get("pageSize").toString());
        if (map.get("curPage") != null)
            curPage = Integer.parseInt(map.get("curPage").toString());

        map.remove("pageIndex");
        map.remove("pageSize");
        map.remove("curPage");

        if ("yj".equals(estateType)) {
            relList = lnkYjYjdyMapper.getList(map);
        } else if ("sj".equals(estateType)) {
            relList = lnkYjSjdyMapper.getList(map);
        }

        if (null != relList && relList.size() > 0) {
            int size = relList.size();
            resultData.setTotalCount(String.valueOf(size));
            if (map.get("optFlag") == null) {//导出标记
                int endRow = pageIndex * pageSize;
                relList = relList.subList((pageIndex - 1) * pageSize, endRow > size ? size : endRow);

                map.put("pageIndex", pageIndex);
                map.put("pageSize", pageSize);
                map.put("curPage", curPage);
            }
            resultData.setReturnData(relList);
        }
        return resultData;
    }

    /**
     * 导入新增
     *
     * @param
     * @return
     */
    public ResultData dyImport(List<Map<String, Object>> queryParam) throws Exception {
        ResultData resultData = new ResultData();
        int count = 0;
        boolean flag = false;
        boolean flag2 = false;
        boolean flag3 = false;
        String msg = "";
        String gzLastMonth = "";
        String userCode = queryParam.get(0).get("userCode").toString();
        Integer userCount = lnkYjImportspecialuserMapper.checkUserImport(userCode);
        LinkedHashMap<String, String> recordMap = new LinkedHashMap<String, String>();
        LinkedHashMap<String, String> recordMap3 = new LinkedHashMap<String, String>();
        String checkFlag = SystemParam.getWebConfigValue("yjCheckFlag");
        if (queryParam != null && queryParam.size() > 0) {
            String estateType = (String) queryParam.get(0).get("templateType");
            //modify by haidan 实际垫佣 Cash_Bill_Company中id >='15144' AND approveStatus IN (25401,25402,25403)的记录不可以导入
            List<Map<String, Object>> checkRelMapList = new ArrayList<>();
            if ("sj".equals(estateType) && StringUtil.isNotEmpty(checkFlag) && "1".equals(checkFlag)) {
                Map<String, Object> checkMap = new HashMap<>();
                checkMap.put("mapList", queryParam);
                checkRelMapList = lnkYjYjsrMapper.checkSj(checkMap);
                if (CollectionUtils.isNotEmpty(checkRelMapList) && userCount == 0) {
                    for (Map<String, Object> checkRelMap : checkRelMapList) {
                        String reportId = (String) checkRelMap.get("reportId");
                        msg = msg + reportId + ",";
                    }
                    if (StringUtil.isNotEmpty(msg)) {
                        msg = msg.substring(0, msg.length() - 1);
                    }
                }
            }
            //实际垫佣时判断判定实际垫佣税前是否为空或为0，如是，则提示：BB201900001,BB201900002应计垫佣为空，请先导入应计垫佣！
            //当应计垫佣税前不为空且不为0：判断订单正记录行的|实际垫佣小计（税前/税后）|<=|应计垫佣小计（税前/税后）|
            /*if("sj".equals(estateType)){
                Map<String, Object> checkMap = new HashMap<>();
                checkMap.put("mapList", queryParam);
                List<Map<String, Object>> checkRelMapList = lnkYjYjdyMapper.checkYjdyTotal(checkMap);
                if (CollectionUtils.isNotEmpty(checkRelMapList)) {
                    String aType = "", bType = "", cType = "";
                    for (Map<String, Object> checkRelMap : checkRelMapList) {
                        String relType = (String) checkRelMap.get("relType");
                        String reportId = (String) checkRelMap.get("reportId");
                        switch (relType) {
                            case "a":
                                aType = aType + reportId + ",";
                                break;
                            case "b":
                                bType = bType + reportId + ",";
                                break;
                            case "c":
                                cType = cType + reportId + ",";
                                break;
                        }
                    }
                    String msg = "";
                    if (StringUtil.isNotEmpty(aType)) {
                        aType = aType.substring(0, aType.length() - 1);
                        msg = msg + aType + "应计垫佣为空，请先导入应计垫佣！\r\n";
                    }
                    if (StringUtil.isNotEmpty(bType)) {
                        bType = bType.substring(0, bType.length() - 1);
                        msg = msg + bType + "实际垫佣小计（税前）应当小于等于应计垫佣小计（税前）！\r\n";
                    }
                    if (StringUtil.isNotEmpty(cType) && !cType.equals(bType)) {
                        cType = cType.substring(0, cType.length() - 1);
                        msg = msg + cType + "实际垫佣小计（税后）应当小于等于应计垫佣小计（税后）！\r\n";
                    }
                    resultData.setFail("导入表中，" + msg + "请调整后再次导入！");
                    return resultData;
                }

            }*/
            //获取当前城市关账到的月份
            Map<?, ?> map = estateMapper.checkCitySwitchMonthByCityNo((String) queryParam.get(0).get("cityNo"));
            Calendar c = Calendar.getInstance();
            Date closeLastDay = null;
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            if (map != null && map.size() > 0) {
                gzLastMonth = map.get("year").toString() + "年" + map.get("month").toString() + "月";
                String closeLastDayStr = map.get("year").toString() + "-" + (Integer.valueOf((String) map.get("month")) + 1) + "-01";
                closeLastDay = format1.parse(closeLastDayStr);
            }
            boolean DataErrorFlag = false;
            boolean imperfectFlag = false;
            for (Map<String, Object> queryMap : queryParam) {
                CommissionResultDto cDto = new CommissionResultDto();
                cDto.setBefTaxAmount((String) queryMap.get("befTaxAmount"));
                cDto.setAftTaxAmount((String) queryMap.get("aftTaxAmount"));
                cDto.setRecordDate((String) queryMap.get("recordDate"));
                cDto.setBefTaxAmount1((String) queryMap.get("befTaxAmount1"));
                cDto.setAftTaxAmount1((String) queryMap.get("aftTaxAmount1"));
                cDto.setRecordDate1((String) queryMap.get("recordDate1"));
                cDto.setBefTaxAmount2((String) queryMap.get("befTaxAmount2"));
                cDto.setAftTaxAmount2((String) queryMap.get("aftTaxAmount2"));
                cDto.setRecordDate2((String) queryMap.get("recordDate2"));
                cDto.setBefTaxAmount3((String) queryMap.get("befTaxAmount3"));
                cDto.setAftTaxAmount3((String) queryMap.get("aftTaxAmount3"));
                cDto.setRecordDate3((String) queryMap.get("recordDate3"));
                cDto.setBefTaxAmount4((String) queryMap.get("befTaxAmount4"));
                cDto.setAftTaxAmount4((String) queryMap.get("aftTaxAmount4"));
                cDto.setRecordDate4((String) queryMap.get("recordDate4"));
                cDto.setBefTaxAmount5((String) queryMap.get("befTaxAmount5"));
                cDto.setAftTaxAmount5((String) queryMap.get("aftTaxAmount5"));
                cDto.setRecordDate5((String) queryMap.get("recordDate5"));
                cDto.setBefTaxAmount6((String) queryMap.get("befTaxAmount6"));
                cDto.setAftTaxAmount6((String) queryMap.get("aftTaxAmount6"));
                cDto.setRecordDate6((String) queryMap.get("recordDate6"));


                if (StringUtil.isNotEmpty(cDto.getRecordDate())
                        && StringUtil.isNotEmpty(cDto.getBefTaxAmount()) && StringUtil.isNotEmpty(cDto.getAftTaxAmount())) {
                    //正常数据
                    if (!AmountUtils.isValidAmount(cDto.getBefTaxAmount()) || !AmountUtils.isValidAmount(cDto.getAftTaxAmount())) {
                        DataErrorFlag = true;
                        break;
                    }

                } else {
                    if (StringUtil.isNotEmpty(cDto.getRecordDate())
                            || StringUtil.isNotEmpty(cDto.getBefTaxAmount()) || StringUtil.isNotEmpty(cDto.getAftTaxAmount())) {
                        imperfectFlag = true;
                        break;
                    }
                }

                if (StringUtil.isNotEmpty(cDto.getRecordDate1())
                        && StringUtil.isNotEmpty(cDto.getBefTaxAmount1()) && StringUtil.isNotEmpty(cDto.getAftTaxAmount1())) {
                    //正常数据
                    if (!AmountUtils.isValidAmount(cDto.getBefTaxAmount1()) || !AmountUtils.isValidAmount(cDto.getAftTaxAmount1())) {
                        DataErrorFlag = true;
                        break;
                    }
                } else {
                    if (StringUtil.isNotEmpty(cDto.getRecordDate1())
                            || StringUtil.isNotEmpty(cDto.getBefTaxAmount1()) || StringUtil.isNotEmpty(cDto.getAftTaxAmount1())) {
                        imperfectFlag = true;
                        break;
                    }
                }

                if (StringUtil.isNotEmpty(cDto.getRecordDate2())
                        && StringUtil.isNotEmpty(cDto.getBefTaxAmount2()) && StringUtil.isNotEmpty(cDto.getAftTaxAmount2())) {
                    //正常数据
                    if (!AmountUtils.isValidAmount(cDto.getBefTaxAmount2()) || !AmountUtils.isValidAmount(cDto.getAftTaxAmount2())) {
                        DataErrorFlag = true;
                        break;
                    }
                } else {
                    if (StringUtil.isNotEmpty(cDto.getRecordDate2())
                            || StringUtil.isNotEmpty(cDto.getBefTaxAmount2()) || StringUtil.isNotEmpty(cDto.getAftTaxAmount2())) {
                        imperfectFlag = true;
                        break;
                    }
                }

                if (StringUtil.isNotEmpty(cDto.getRecordDate3())
                        && StringUtil.isNotEmpty(cDto.getBefTaxAmount3()) && StringUtil.isNotEmpty(cDto.getAftTaxAmount3())) {
                    //正常数据
                    if (!AmountUtils.isValidAmount(cDto.getBefTaxAmount3()) || !AmountUtils.isValidAmount(cDto.getAftTaxAmount3())) {
                        DataErrorFlag = true;
                        break;
                    }
                } else {
                    if (StringUtil.isNotEmpty(cDto.getRecordDate3())
                            || StringUtil.isNotEmpty(cDto.getBefTaxAmount3()) || StringUtil.isNotEmpty(cDto.getAftTaxAmount3())) {
                        imperfectFlag = true;
                        break;
                    }
                }

                if (StringUtil.isNotEmpty(cDto.getRecordDate4())
                        && StringUtil.isNotEmpty(cDto.getBefTaxAmount4()) && StringUtil.isNotEmpty(cDto.getAftTaxAmount4())) {
                    //正常数据
                    if (!AmountUtils.isValidAmount(cDto.getBefTaxAmount4()) || !AmountUtils.isValidAmount(cDto.getAftTaxAmount4())) {
                        DataErrorFlag = true;
                        break;
                    }
                } else {
                    if (StringUtil.isNotEmpty(cDto.getRecordDate4())
                            || StringUtil.isNotEmpty(cDto.getBefTaxAmount4()) || StringUtil.isNotEmpty(cDto.getAftTaxAmount4())) {
                        imperfectFlag = true;
                        break;
                    }
                }

                if (StringUtil.isNotEmpty(cDto.getRecordDate5())
                        && StringUtil.isNotEmpty(cDto.getBefTaxAmount5()) && StringUtil.isNotEmpty(cDto.getAftTaxAmount5())) {
                    //正常数据
                    if (!AmountUtils.isValidAmount(cDto.getBefTaxAmount5()) || !AmountUtils.isValidAmount(cDto.getAftTaxAmount5())) {
                        DataErrorFlag = true;
                        break;
                    }
                } else {
                    if (StringUtil.isNotEmpty(cDto.getRecordDate5())
                            || StringUtil.isNotEmpty(cDto.getBefTaxAmount5()) || StringUtil.isNotEmpty(cDto.getAftTaxAmount5())) {
                        imperfectFlag = true;
                        break;
                    }
                }

                if (StringUtil.isNotEmpty(cDto.getRecordDate6())
                        && StringUtil.isNotEmpty(cDto.getBefTaxAmount6()) && StringUtil.isNotEmpty(cDto.getAftTaxAmount6())) {
                    //正常数据
                    if (!AmountUtils.isValidAmount(cDto.getBefTaxAmount6()) || !AmountUtils.isValidAmount(cDto.getAftTaxAmount6())) {
                        DataErrorFlag = true;
                        break;
                    }
                } else {
                    if (StringUtil.isNotEmpty(cDto.getRecordDate6())
                            || StringUtil.isNotEmpty(cDto.getBefTaxAmount6()) || StringUtil.isNotEmpty(cDto.getAftTaxAmount6())) {
                        imperfectFlag = true;
                        break;
                    }
                }
            }
            if (imperfectFlag) {
                resultData.setFail();
                resultData.setReturnMsg("由于数据缺少税前、税后、日期，导入不成功，请确认！");
                return resultData;
            }
            if (DataErrorFlag) {
                resultData.setFail();
                resultData.setReturnMsg("由于数据金额格式不正确，导入不成功，请确认！");
                return resultData;
            }


            for (Map<String, Object> queryMap : queryParam) {
                boolean sjdyFlag = true;
                // Start add by huangmc 2020-06-19  for 应计垫佣税前不能大于应返佣税前*总控垫佣比例！
                if ("yj".equals(estateType)) {
                    if (checkYJdyRatio(queryMap)) {
                        flag3 = true;
                        recordMap3.put((String) queryMap.get("reportId"), (String) queryMap.get("reportId"));
                        continue;
                    }
                }
                // End add by huangmc 2020-06-19  for 应计垫佣税前不能大于应返佣税前*总控垫佣比例！
                if ("sj".equals(estateType) && CollectionUtils.isNotEmpty(checkRelMapList)) {
                    for (Map<String, Object> checkRelMap : checkRelMapList) {
                        String reportId = (String) checkRelMap.get("reportId");
                        String queryReportId = (String) queryMap.get("reportId");
                        if (queryReportId.equals(reportId)) {
                            if (userCount == 0) {
                                sjdyFlag = false;
                                break;
                            }
                        }
                    }
                }
                // Start add by huangmc 2020-06-19  for 代码优化调整
                //验证报单编号和业绩城市是否相符
                int value2 = estateMapper.checkCityNoByMap(queryMap);

                // 应计（实际）垫佣 ０～６ to mergeInsert
                this.toMergerInsert(queryMap, count, closeLastDay, estateType, sjdyFlag, flag, value2, flag2, recordMap, "recordDate", "befTaxAmount", "aftTaxAmount", "switchFlag");
                this.toMergerInsert(queryMap, count, closeLastDay, estateType, sjdyFlag, flag, value2, flag2, recordMap, "recordDate1", "befTaxAmount1", "aftTaxAmount1", "switchFlag1");
                this.toMergerInsert(queryMap, count, closeLastDay, estateType, sjdyFlag, flag, value2, flag2, recordMap, "recordDate2", "befTaxAmount2", "aftTaxAmount2", "switchFlag2");
                this.toMergerInsert(queryMap, count, closeLastDay, estateType, sjdyFlag, flag, value2, flag2, recordMap, "recordDate3", "befTaxAmount3", "aftTaxAmount3", "switchFlag3");
                this.toMergerInsert(queryMap, count, closeLastDay, estateType, sjdyFlag, flag, value2, flag2, recordMap, "recordDate4", "befTaxAmount4", "aftTaxAmount4", "switchFlag4");
                this.toMergerInsert(queryMap, count, closeLastDay, estateType, sjdyFlag, flag, value2, flag2, recordMap, "recordDate5", "befTaxAmount5", "aftTaxAmount5", "switchFlag5");
                this.toMergerInsert(queryMap, count, closeLastDay, estateType, sjdyFlag, flag, value2, flag2, recordMap, "recordDate6", "befTaxAmount6", "aftTaxAmount6", "switchFlag6");

                //应计（实际）垫佣
//                if (StringUtil.isNotEmpty((String) queryMap.get("recordDate"))) {
//                    count++;
//                    queryMap.put("recordDate", queryMap.get("recordDate"));
//                    //验证当前记录是否关账
//                    Integer switchFlag = Integer.valueOf((String) queryMap.get("switchFlag"));
//                    if (switchFlag == null || switchFlag <= 0) {
//                        Date recordDate = format1.parse((String) queryMap.get("recordDate"));
//                        queryMap.put("befTaxAmount", queryMap.get("befTaxAmount").toString().trim());
//                        queryMap.put("aftTaxAmount", queryMap.get("aftTaxAmount").toString().trim());
//                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
//                            //int value2 = estateMapper.checkCityNoByMap(queryMap);
//                            //if (value2 > 0) {
//                                if ("yj".equals(estateType)) lnkYjYjdyMapper.mergeInsert(queryMap);
//                                else if ("sj".equals(estateType) && sjdyFlag) lnkYjSjdyMapper.mergeInsert(queryMap);
////                            } else {
////                                flag2 = true;
////                                recordMap.put((String) queryMap.get("reportId"), (String) queryMap.get("reportId"));
////                            }
//                        } else {
//                            flag = true;
//                        }
//                    }
//                }

                //应计（实际）垫佣调整1
//                if (StringUtil.isNotEmpty((String) queryMap.get("recordDate1"))) {
//                    count++;
//                    queryMap.put("recordDate", queryMap.get("recordDate1"));
//                    queryMap.put("befTaxAmount", queryMap.get("befTaxAmount1").toString().trim());
//                    queryMap.put("aftTaxAmount", queryMap.get("aftTaxAmount1").toString().trim());
//                    //验证当前记录是否关账
//                    Integer switchFlag = Integer.valueOf((String) queryMap.get("switchFlag1"));
//                    if (switchFlag == null || switchFlag <= 0) {
//                        Date recordDate = format1.parse((String) queryMap.get("recordDate1"));
//                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
//                            //验证报单编号和业绩城市是否相符
//                           // int value2 = estateMapper.checkCityNoByMap(queryMap);
//                            //if (value2 > 0) {
//                                if ("yj".equals(estateType)) lnkYjYjdyMapper.mergeInsert(queryMap);
//                                else if ("sj".equals(estateType) && sjdyFlag) lnkYjSjdyMapper.mergeInsert(queryMap);
////                            } else {
////                                flag2 = true;
////                                recordMap.put((String) queryMap.get("reportId"), (String) queryMap.get("reportId"));
////                            }
//                        } else {
//                            flag = true;
//                        }
//                    }
//                }
                //应计（实际）垫佣调整2
//                if (StringUtil.isNotEmpty((String) queryMap.get("recordDate2"))) {
//                    count++;
//                    queryMap.put("recordDate", queryMap.get("recordDate2"));
//                    queryMap.put("befTaxAmount", queryMap.get("befTaxAmount2").toString().trim());
//                    queryMap.put("aftTaxAmount", queryMap.get("aftTaxAmount2").toString().trim());
//                    //验证当前记录是否关账
//                    Integer switchFlag = Integer.valueOf((String) queryMap.get("switchFlag2"));
//                    if (switchFlag == null || switchFlag <= 0) {
//                        Date recordDate = format1.parse((String) queryMap.get("recordDate2"));
//                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
//                            //验证报单编号和业绩城市是否相符
//                            //int value2 = estateMapper.checkCityNoByMap(queryMap);
//                            //if (value2 > 0) {
//                                if ("yj".equals(estateType)) lnkYjYjdyMapper.mergeInsert(queryMap);
//                                else if ("sj".equals(estateType) && sjdyFlag) lnkYjSjdyMapper.mergeInsert(queryMap);
////                            } else {
////                                flag2 = true;
////                                recordMap.put((String) queryMap.get("reportId"), (String) queryMap.get("reportId"));
////                            }
//                        } else {
//                            flag = true;
//                        }
//                    }
//                }
//                //应计（实际）垫佣调整3
//                if (StringUtil.isNotEmpty((String) queryMap.get("recordDate3"))) {
//                    count++;
//                    queryMap.put("recordDate", queryMap.get("recordDate3"));
//                    queryMap.put("befTaxAmount", queryMap.get("befTaxAmount3").toString().trim());
//                    queryMap.put("aftTaxAmount", queryMap.get("aftTaxAmount3").toString().trim());
//                    //验证当前记录是否关账
//                    Integer switchFlag = Integer.valueOf((String) queryMap.get("switchFlag3"));
//                    if (switchFlag == null || switchFlag <= 0) {
//                        Date recordDate = format1.parse((String) queryMap.get("recordDate3"));
//                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
//                            //验证报单编号和业绩城市是否相符
//                           // int value2 = estateMapper.checkCityNoByMap(queryMap);
//                            //if (value2 > 0) {
//                                if ("yj".equals(estateType)) lnkYjYjdyMapper.mergeInsert(queryMap);
//                                else if ("sj".equals(estateType) && sjdyFlag) lnkYjSjdyMapper.mergeInsert(queryMap);
////                            } else {
////                                flag2 = true;
////                                recordMap.put((String) queryMap.get("reportId"), (String) queryMap.get("reportId"));
////                            }
//                        } else {
//                            flag = true;
//                        }
//                    }
//                }
                //应计（实际）垫佣调整4
//                if (StringUtil.isNotEmpty((String) queryMap.get("recordDate4"))) {
//                    count++;
//                    queryMap.put("recordDate", queryMap.get("recordDate4"));
//                    queryMap.put("befTaxAmount", queryMap.get("befTaxAmount4").toString().trim());
//                    queryMap.put("aftTaxAmount", queryMap.get("aftTaxAmount4").toString().trim());
//                    //验证当前记录是否关账
//                    Integer switchFlag = Integer.valueOf((String) queryMap.get("switchFlag4"));
//                    if (switchFlag == null || switchFlag <= 0) {
//                        Date recordDate = format1.parse((String) queryMap.get("recordDate4"));
//                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
//                            //验证报单编号和业绩城市是否相符
//                           // int value2 = estateMapper.checkCityNoByMap(queryMap);
//                            //if (value2 > 0) {
//                                if ("yj".equals(estateType)) lnkYjYjdyMapper.mergeInsert(queryMap);
//                                else if ("sj".equals(estateType) && sjdyFlag) lnkYjSjdyMapper.mergeInsert(queryMap);
////                            } else {
////                                flag2 = true;
////                                recordMap.put((String) queryMap.get("reportId"), (String) queryMap.get("reportId"));
////                            }
//                        } else {
//                            flag = true;
//                        }
//                    }
//                }
                //应计（实际）垫佣调整5
//                if (StringUtil.isNotEmpty((String) queryMap.get("recordDate5"))) {
//                    count++;
//                    queryMap.put("recordDate", queryMap.get("recordDate5"));
//                    queryMap.put("befTaxAmount", queryMap.get("befTaxAmount5").toString().trim());
//                    queryMap.put("aftTaxAmount", queryMap.get("aftTaxAmount5").toString().trim());
//                    //验证当前记录是否关账
//                    Integer switchFlag = Integer.valueOf((String) queryMap.get("switchFlag5"));
//                    if (switchFlag == null || switchFlag <= 0) {
//                        Date recordDate = format1.parse((String) queryMap.get("recordDate5"));
//                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
//                            //验证报单编号和业绩城市是否相符
//                           // int value2 = estateMapper.checkCityNoByMap(queryMap);
//                            //if (value2 > 0) {
//                                if ("yj".equals(estateType)) lnkYjYjdyMapper.mergeInsert(queryMap);
//                                else if ("sj".equals(estateType) && sjdyFlag) lnkYjSjdyMapper.mergeInsert(queryMap);
////                            } else {
////                                flag2 = true;
////                                recordMap.put((String) queryMap.get("reportId"), (String) queryMap.get("reportId"));
////                            }
//                        } else {
//                            flag = true;
//                        }
//                    }
//                }
                //应计（实际）垫佣调整6
//                if (StringUtil.isNotEmpty((String) queryMap.get("recordDate6"))) {
//                    count++;
//                    queryMap.put("recordDate", queryMap.get("recordDate6"));
//                    queryMap.put("befTaxAmount", queryMap.get("befTaxAmount6").toString().trim());
//                    queryMap.put("aftTaxAmount", queryMap.get("aftTaxAmount6").toString().trim());
//                    //验证当前记录是否关账
//                    Integer switchFlag = Integer.valueOf((String) queryMap.get("switchFlag6"));
//                    if (switchFlag == null || switchFlag <= 0) {
//                        Date recordDate = format1.parse((String) queryMap.get("recordDate6"));
//                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
//                            //验证报单编号和业绩城市是否相符
//                            //int value2 = estateMapper.checkCityNoByMap(queryMap);
//                            //if (value2 > 0) {
//                                if ("yj".equals(estateType)) lnkYjYjdyMapper.mergeInsert(queryMap);
//                                else if ("sj".equals(estateType) && sjdyFlag) lnkYjSjdyMapper.mergeInsert(queryMap);
////                            } else {
////                                flag2 = true;
////                                recordMap.put((String) queryMap.get("reportId"), (String) queryMap.get("reportId"));
////                            }
//                        } else {
//                            flag = true;
//                        }
//                    }
//                }
                // End add by huangmc 2020-06-19  for 代码优化调整
            }
        }
        if (flag) {
            resultData.setReturnMsg("由于联动业务已关账至" + gzLastMonth + "，部分数据导入不成功，请确认！");
        } else {
            String retMsg = "";
            if (flag3 || flag2) {
                retMsg += "部分数据导入未成功，";
                if (flag3) {
                    String reportNoStr = "";
                    for (String key : recordMap3.keySet()) {
                        reportNoStr += key + "，";
                    }
                    if (StringUtil.isNotEmpty(reportNoStr)) {
                        reportNoStr = reportNoStr.substring(0, reportNoStr.length() - 1);
                    }
                    retMsg += "订单编号：" + reportNoStr + "的应计垫佣税前不能大于应返佣税前*总控垫佣比例！";
                }
                if (flag2) {
                    String recordIdStr = "";
                    for (String key : recordMap.keySet()) {
                        recordIdStr += key + "；";
                    }
                    if (StringUtil.isNotEmpty(recordIdStr)) {
                        recordIdStr = recordIdStr.substring(0, recordIdStr.length() - 1);
                    }
                    resultData.setReturnMsg("订单编号：" + recordIdStr + "的业绩城市不一致或订单状态已变更！");
                }
            } else if (StringUtil.isNotEmpty(msg)) {
                retMsg = "部分数据导入成功！" + msg + "无需手动导入实垫信息！";
            } else {
                retMsg = "导入数据成功！";
            }
            resultData.setReturnMsg(retMsg);
        }

        System.out.println("===更新数据：" + count + "条");
        resultData.setReturnData(count);
        return resultData;
    }


    private void toMergerInsert(Map<String, Object> queryMap, int count, Date closeLastDay, String estateType, boolean sjdyFlag, boolean flag, int value2, boolean flag2
            , LinkedHashMap<String, String> recordMap, String recordDateKey, String befTaxAmountKey, String aftTaxAmountKey, String switchFlagKey
    ) throws Exception {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        //应计（实际）垫佣调整1
        if (StringUtil.isNotEmpty((String) queryMap.get(recordDateKey))) {
            count++;
            queryMap.put("recordDate", queryMap.get(recordDateKey));
            queryMap.put("befTaxAmount", queryMap.get(befTaxAmountKey).toString().trim());
            queryMap.put("aftTaxAmount", queryMap.get(aftTaxAmountKey).toString().trim());
            //验证当前记录是否关账
            Integer switchFlag = Integer.valueOf((String) queryMap.get(switchFlagKey));
            if (switchFlag == null || switchFlag <= 0) {
                Date recordDate = format1.parse((String) queryMap.get(recordDateKey));
                if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
                    //验证报单编号和业绩城市是否相符
                    // int value2 = estateMapper.checkCityNoByMap(queryMap);
                    if (value2 > 0) {
                        if ("yj".equals(estateType)) lnkYjYjdyMapper.mergeInsert(queryMap);
                        else if ("sj".equals(estateType) && sjdyFlag) lnkYjSjdyMapper.mergeInsert(queryMap);
                    } else {
                        flag2 = true;
                        recordMap.put((String) queryMap.get("reportId"), (String) queryMap.get("reportId"));
                    }
                } else {
                    flag = true;
                }
            }
        }
    }

    /**
     * 应计垫佣税前不能大于应返佣税前*总控垫佣比例！
     */
    private boolean checkYJdyRatio(Map<String, Object> queryParam) {
        Map<String, Object> param = new HashMap<>();
        param.put("reportId", StringUtil.isNotEmpty(queryParam.get("reportId").toString()) ? queryParam.get("reportId") : null);
        param.put("companyNo", StringUtil.isNotEmpty(queryParam.get("companyNo").toString()) ? queryParam.get("companyNo") : null);
        param.put("befTaxAmount", StringUtil.isNotEmpty(queryParam.get("befTaxAmount").toString()) ? queryParam.get("befTaxAmount") : null);
        param.put("befTaxAmount1", StringUtil.isNotEmpty(queryParam.get("befTaxAmount1").toString()) ? queryParam.get("befTaxAmount1") : null);
        param.put("befTaxAmount2", StringUtil.isNotEmpty(queryParam.get("befTaxAmount2").toString()) ? queryParam.get("befTaxAmount2") : null);
        ;
        param.put("befTaxAmount3", StringUtil.isNotEmpty(queryParam.get("befTaxAmount3").toString()) ? queryParam.get("befTaxAmount3") : null);
        ;
        param.put("befTaxAmount4", StringUtil.isNotEmpty(queryParam.get("befTaxAmount4").toString()) ? queryParam.get("befTaxAmount4") : null);
        ;
        param.put("befTaxAmount5", StringUtil.isNotEmpty(queryParam.get("befTaxAmount5").toString()) ? queryParam.get("befTaxAmount5") : null);
        ;
        param.put("befTaxAmount6", StringUtil.isNotEmpty(queryParam.get("befTaxAmount6").toString()) ? queryParam.get("befTaxAmount6") : null);
        ;
        param.put("recordDate", StringUtil.isNotEmpty(queryParam.get("recordDate").toString()) ? queryParam.get("recordDate") : null);
        param.put("recordDate1", StringUtil.isNotEmpty(queryParam.get("recordDate1").toString()) ? queryParam.get("recordDate1") : null);
        param.put("recordDate2", StringUtil.isNotEmpty(queryParam.get("recordDate2").toString()) ? queryParam.get("recordDate2") : null);
        param.put("recordDate3", StringUtil.isNotEmpty(queryParam.get("recordDate3").toString()) ? queryParam.get("recordDate3") : null);
        param.put("recordDate4", StringUtil.isNotEmpty(queryParam.get("recordDate4").toString()) ? queryParam.get("recordDate4") : null);
        param.put("recordDate5", StringUtil.isNotEmpty(queryParam.get("recordDate5").toString()) ? queryParam.get("recordDate5") : null);
        param.put("recordDate6", StringUtil.isNotEmpty(queryParam.get("recordDate6").toString()) ? queryParam.get("recordDate6") : null);

        int result = lnkYjYjdyMapper.checkYJdyRatio(param);

        if (result == 0) {
            return true;
        }

        return false;
    }

}
