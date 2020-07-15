package cn.com.eju.deal.scene.commission.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.CommonMapper;
import cn.com.eju.deal.common.util.AmountUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.scene.commission.CommissionResultDto;
import cn.com.eju.deal.scene.commission.dao.LnkYjFyfyMapper;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by eju on 2019/12/18.
 */
@Service("pmlsLnkYjFyFyService")
public class PmlsLnkYjFyFyService extends BaseService {

    @Resource
    LnkYjFyfyMapper lnkYjFyfyMapper;

    @Resource
    CommonMapper commonMapper;

    public ResultData<List<Map<String, Object>>> queryList(Map<String, Object> map) throws Exception {
        ResultData<List<Map<String, Object>>> resultData = new ResultData<>();
        List<Map<String, Object>> relList = new ArrayList<>();
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
        relList = lnkYjFyfyMapper.queryList(map);

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

    public ResultData insertLinkFyFy(List<Map<String, Object>> queryParam) throws ParseException {
        ResultData resultData = new ResultData();
        int count = 0;
        boolean flag = false;
        String msg = "";
        String gzLastMonth = "";
        String estateType = queryParam.get(0).get("estateType").toString();
        Integer empId = new Integer(0);
        if (queryParam != null && queryParam.size() > 0) {

            empId = Integer.valueOf(queryParam.get(0).get("CrtEmpId").toString());
            //获取当前城市关账到的月份
            Map<?, ?> map = commonMapper.checkCitySwitchMonth(queryParam.get(0).get("cityNo").toString());
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
                cDto.setBefTaxAmount((String)queryMap.get("befTaxAmount"));
                cDto.setAftTaxAmount((String)queryMap.get("aftTaxAmount"));
                cDto.setRecordDate((String)queryMap.get("recordDate"));
                cDto.setBefTaxAmount1((String)queryMap.get("befTaxAmount1"));
                cDto.setAftTaxAmount1((String)queryMap.get("aftTaxAmount1"));
                cDto.setRecordDate1((String)queryMap.get("recordDate1"));
                cDto.setBefTaxAmount2((String)queryMap.get("befTaxAmount2"));
                cDto.setAftTaxAmount2((String)queryMap.get("aftTaxAmount2"));
                cDto.setRecordDate2((String)queryMap.get("recordDate2"));
                cDto.setBefTaxAmount3((String)queryMap.get("befTaxAmount3"));
                cDto.setAftTaxAmount3((String)queryMap.get("aftTaxAmount3"));
                cDto.setRecordDate3((String)queryMap.get("recordDate3"));
                cDto.setBefTaxAmount4((String)queryMap.get("befTaxAmount4"));
                cDto.setAftTaxAmount4((String)queryMap.get("aftTaxAmount4"));
                cDto.setRecordDate4((String)queryMap.get("recordDate4"));
                cDto.setBefTaxAmount5((String)queryMap.get("befTaxAmount5"));
                cDto.setAftTaxAmount5((String)queryMap.get("aftTaxAmount5"));
                cDto.setRecordDate5((String)queryMap.get("recordDate5"));
                cDto.setBefTaxAmount6((String)queryMap.get("befTaxAmount6"));
                cDto.setAftTaxAmount6((String)queryMap.get("aftTaxAmount6"));
                cDto.setRecordDate6((String)queryMap.get("recordDate6"));


                if(StringUtil.isNotEmpty(cDto.getRecordDate())
                        && StringUtil.isNotEmpty(cDto.getBefTaxAmount()) && StringUtil.isNotEmpty(cDto.getAftTaxAmount())){
                    //正常数据
                    if(!AmountUtils.isValidAmount(cDto.getBefTaxAmount()) || !AmountUtils.isValidAmount(cDto.getAftTaxAmount())){
                        DataErrorFlag = true;
                        break;
                    }
                }else {
                    if(StringUtil.isNotEmpty(cDto.getRecordDate())
                            || StringUtil.isNotEmpty(cDto.getBefTaxAmount()) || StringUtil.isNotEmpty(cDto.getAftTaxAmount()) ){
                        imperfectFlag = true;
                        break;
                    }
                }

                if(StringUtil.isNotEmpty(cDto.getRecordDate1())
                        && StringUtil.isNotEmpty(cDto.getBefTaxAmount1()) && StringUtil.isNotEmpty(cDto.getAftTaxAmount1())){
                    //正常数据
                    if(!AmountUtils.isValidAmount(cDto.getBefTaxAmount1()) || !AmountUtils.isValidAmount(cDto.getAftTaxAmount1())){
                        DataErrorFlag = true;
                        break;
                    }
                }else {
                    if(StringUtil.isNotEmpty(cDto.getRecordDate1())
                            || StringUtil.isNotEmpty(cDto.getBefTaxAmount1()) || StringUtil.isNotEmpty(cDto.getAftTaxAmount1()) ){
                        imperfectFlag = true;
                        break;
                    }
                }

                if(StringUtil.isNotEmpty(cDto.getRecordDate2())
                        && StringUtil.isNotEmpty(cDto.getBefTaxAmount2()) && StringUtil.isNotEmpty(cDto.getAftTaxAmount2())){
                    //正常数据
                    if(!AmountUtils.isValidAmount(cDto.getBefTaxAmount2()) || !AmountUtils.isValidAmount(cDto.getAftTaxAmount2())){
                        DataErrorFlag = true;
                        break;
                    }
                }else {
                    if(StringUtil.isNotEmpty(cDto.getRecordDate2())
                            || StringUtil.isNotEmpty(cDto.getBefTaxAmount2()) || StringUtil.isNotEmpty(cDto.getAftTaxAmount2()) ){
                        imperfectFlag = true;
                        break;
                    }
                }

                if(StringUtil.isNotEmpty(cDto.getRecordDate3())
                        && StringUtil.isNotEmpty(cDto.getBefTaxAmount3()) && StringUtil.isNotEmpty(cDto.getAftTaxAmount3())){
                    //正常数据
                    if(!AmountUtils.isValidAmount(cDto.getBefTaxAmount3()) || !AmountUtils.isValidAmount(cDto.getAftTaxAmount3())){
                        DataErrorFlag = true;
                        break;
                    }
                }else {
                    if(StringUtil.isNotEmpty(cDto.getRecordDate3())
                            || StringUtil.isNotEmpty(cDto.getBefTaxAmount3()) || StringUtil.isNotEmpty(cDto.getAftTaxAmount3()) ){
                        imperfectFlag = true;
                        break;
                    }
                }

                if(StringUtil.isNotEmpty(cDto.getRecordDate4())
                        && StringUtil.isNotEmpty(cDto.getBefTaxAmount4()) && StringUtil.isNotEmpty(cDto.getAftTaxAmount4())){
                    //正常数据
                    if(!AmountUtils.isValidAmount(cDto.getBefTaxAmount4()) || !AmountUtils.isValidAmount(cDto.getAftTaxAmount4())){
                        DataErrorFlag = true;
                        break;
                    }
                }else {
                    if(StringUtil.isNotEmpty(cDto.getRecordDate4())
                            || StringUtil.isNotEmpty(cDto.getBefTaxAmount4()) || StringUtil.isNotEmpty(cDto.getAftTaxAmount4()) ){
                        imperfectFlag = true;
                        break;
                    }
                }

                if(StringUtil.isNotEmpty(cDto.getRecordDate5())
                        && StringUtil.isNotEmpty(cDto.getBefTaxAmount5()) && StringUtil.isNotEmpty(cDto.getAftTaxAmount5())){
                    //正常数据
                    if(!AmountUtils.isValidAmount(cDto.getBefTaxAmount5()) || !AmountUtils.isValidAmount(cDto.getAftTaxAmount5())){
                        DataErrorFlag = true;
                        break;
                    }
                }else {
                    if(StringUtil.isNotEmpty(cDto.getRecordDate5())
                            || StringUtil.isNotEmpty(cDto.getBefTaxAmount5()) || StringUtil.isNotEmpty(cDto.getAftTaxAmount5()) ){
                        imperfectFlag = true;
                        break;
                    }
                }

                if(StringUtil.isNotEmpty(cDto.getRecordDate6())
                        && StringUtil.isNotEmpty(cDto.getBefTaxAmount6()) && StringUtil.isNotEmpty(cDto.getAftTaxAmount6())){
                    //正常数据
                    if(!AmountUtils.isValidAmount(cDto.getBefTaxAmount6()) || !AmountUtils.isValidAmount(cDto.getAftTaxAmount6())){
                        DataErrorFlag = true;
                        break;
                    }
                }else {
                    if(StringUtil.isNotEmpty(cDto.getRecordDate6())
                            || StringUtil.isNotEmpty(cDto.getBefTaxAmount6()) || StringUtil.isNotEmpty(cDto.getAftTaxAmount6()) ){
                        imperfectFlag = true;
                        break;
                    }
                }
            }
            if(imperfectFlag){
                resultData.setFail();
                resultData.setReturnMsg("由于数据缺少税前、税后、日期，导入不成功，请确认！");
                return resultData;
            }
            if(DataErrorFlag){
                resultData.setFail();
                resultData.setReturnMsg("由于数据金额格式不正确，导入不成功，请确认！");
                return resultData;
            }

            for (Map<String, Object> queryMap : queryParam) {
                if (StringUtil.isNotEmpty((String) queryMap.get("recordDate"))) {
                    count++;
                    queryMap.put("recordDate", queryMap.get("recordDate"));
                    //验证当前记录是否关账
                    Integer switchFlag = Integer.valueOf((String) queryMap.get("switchFlag"));
                    if (switchFlag == null || switchFlag <= 0) {
                        Date recordDate = format1.parse((String) queryMap.get("recordDate"));
                        queryMap.put("befTaxAmount", queryMap.get("befTaxAmount").toString().trim());
                        queryMap.put("aftTaxAmount", queryMap.get("aftTaxAmount").toString().trim());
                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
                            lnkYjFyfyMapper.mergeInsert(queryMap);
                        } else {
                            flag = true;
                        }
                    }
                }

                //调整1
                if (StringUtil.isNotEmpty((String) queryMap.get("recordDate1"))) {
                    count++;
                    queryMap.put("recordDate", queryMap.get("recordDate1"));
                    //验证当前记录是否关账
                    Integer switchFlag = Integer.valueOf((String) queryMap.get("switchFlag1"));
                    if (switchFlag == null || switchFlag <= 0) {
                        Date recordDate = format1.parse((String) queryMap.get("recordDate1"));
                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
                            queryMap.put("befTaxAmount", queryMap.get("befTaxAmount1").toString().trim());
                            queryMap.put("aftTaxAmount", queryMap.get("aftTaxAmount1").toString().trim());
                            lnkYjFyfyMapper.mergeInsert(queryMap);
                        } else {
                            flag = true;
                        }
                    }
                }
                //调整2
                if (StringUtil.isNotEmpty((String) queryMap.get("recordDate2"))) {
                    count++;
                    queryMap.put("recordDate", queryMap.get("recordDate2"));
                    //验证当前记录是否关账
                    Integer switchFlag = Integer.valueOf((String) queryMap.get("switchFlag2"));
                    if (switchFlag == null || switchFlag <= 0) {
                        Date recordDate = format1.parse((String) queryMap.get("recordDate2"));
                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
                            queryMap.put("befTaxAmount", queryMap.get("befTaxAmount2").toString().trim());
                            queryMap.put("aftTaxAmount", queryMap.get("aftTaxAmount2").toString().trim());
                            lnkYjFyfyMapper.mergeInsert(queryMap);
                        } else {
                            flag = true;
                        }
                    }
                }
                //调整3
                if (StringUtil.isNotEmpty((String) queryMap.get("recordDate3"))) {
                    count++;
                    queryMap.put("recordDate", queryMap.get("recordDate3"));
                    //验证当前记录是否关账
                    Integer switchFlag = Integer.valueOf((String) queryMap.get("switchFlag3"));
                    if (switchFlag == null || switchFlag <= 0) {
                        Date recordDate = format1.parse((String) queryMap.get("recordDate3"));
                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
                            queryMap.put("befTaxAmount", queryMap.get("befTaxAmount3").toString().trim());
                            queryMap.put("aftTaxAmount", queryMap.get("aftTaxAmount3").toString().trim());
                            lnkYjFyfyMapper.mergeInsert(queryMap);
                        } else {
                            flag = true;
                        }
                    }
                }
                //调整4
                if (StringUtil.isNotEmpty((String) queryMap.get("recordDate4"))) {
                    count++;
                    queryMap.put("recordDate", queryMap.get("recordDate4"));
                    //验证当前记录是否关账
                    Integer switchFlag = Integer.valueOf((String) queryMap.get("switchFlag4"));
                    if (switchFlag == null || switchFlag <= 0) {
                        Date recordDate = format1.parse((String) queryMap.get("recordDate4"));
                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
                            queryMap.put("befTaxAmount", queryMap.get("befTaxAmount4").toString().trim());
                            queryMap.put("aftTaxAmount", queryMap.get("aftTaxAmount4").toString().trim());
                            lnkYjFyfyMapper.mergeInsert(queryMap);
                        } else {
                            flag = true;
                        }
                    }
                }
                //调整5
                if (StringUtil.isNotEmpty((String) queryMap.get("recordDate5"))) {
                    count++;
                    queryMap.put("recordDate", queryMap.get("recordDate5"));
                    //验证当前记录是否关账
                    Integer switchFlag = Integer.valueOf((String) queryMap.get("switchFlag5"));
                    if (switchFlag == null || switchFlag <= 0) {
                        Date recordDate = format1.parse((String) queryMap.get("recordDate5"));
                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
                            queryMap.put("befTaxAmount", queryMap.get("befTaxAmount5").toString().trim());
                            queryMap.put("aftTaxAmount", queryMap.get("aftTaxAmount5").toString().trim());
                            lnkYjFyfyMapper.mergeInsert(queryMap);
                        } else {
                            flag = true;
                        }
                    }
                }
                //调整6
                if (StringUtil.isNotEmpty((String) queryMap.get("recordDate6"))) {
                    count++;
                    queryMap.put("recordDate", queryMap.get("recordDate6"));
                    //验证当前记录是否关账
                    Integer switchFlag = Integer.valueOf((String) queryMap.get("switchFlag6"));
                    if (switchFlag == null || switchFlag <= 0) {
                        Date recordDate = format1.parse((String) queryMap.get("recordDate6"));
                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
                            queryMap.put("befTaxAmount", queryMap.get("befTaxAmount6").toString().trim());
                            queryMap.put("aftTaxAmount", queryMap.get("aftTaxAmount6").toString().trim());
                            lnkYjFyfyMapper.mergeInsert(queryMap);
                        } else {
                            flag = true;
                        }
                    }
                }
            }
        }
        if (flag) {
            resultData.setReturnMsg("由于联动业务已关账至" + gzLastMonth + "，部分数据导入不成功，请确认！");
        } else if (StringUtil.isNotEmpty(msg)) {
            resultData.setReturnMsg("部分数据导入成功！" + msg + "无需手动导入实返信息！");
        } else {
            resultData.setReturnMsg("导入数据成功！");
        }

        System.out.println("===更新数据：" + count + "条");
        resultData.setReturnData(count);
        return resultData;
    }
}
