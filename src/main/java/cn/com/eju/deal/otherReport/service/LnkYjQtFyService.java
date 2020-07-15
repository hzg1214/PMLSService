package cn.com.eju.deal.otherReport.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.common.dao.CommonMapper;
import cn.com.eju.deal.common.util.AmountUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.otherReport.dao.LnkYjQtSjfyMapper;
import cn.com.eju.deal.otherReport.dao.LnkYjQtYjfyMapper;
import cn.com.eju.deal.otherReport.dto.LnkYjFyDto;
import cn.com.eju.deal.otherReport.dto.LnkYjWyDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by eju on 2019/10/29.
 */
@Service("lnkYjQtFyService")
public class LnkYjQtFyService extends BaseService {

    @Resource
    LnkYjQtYjfyMapper lnkYjQtYjfyMapper;
    @Resource
    LnkYjQtSjfyMapper lnkYjQtSjfyMapper;
    @Resource
    CommonMapper commonMapper;


    public ResultData<List<LnkYjFyDto>> getLnkYjQtFyList(Map<String, Object> map) {
        ResultData<List<LnkYjFyDto>> resultData = new ResultData<>();
        List<LnkYjFyDto> relList = new ArrayList<>();
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

        if ("yjfy".equals(estateType)) {
            relList = lnkYjQtYjfyMapper.getList(map);
        } else if ("sjfy".equals(estateType)) {
            relList = lnkYjQtSjfyMapper.getList(map);
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

    public ResultData<String> insertLnkQtFy(LnkYjFyDto dto) throws ParseException {
        ResultData resultData = new ResultData();
        List<LnkYjFyDto> lnkYjFyDtoList = dto.getLnkYjFyDtoList();
        int count = 0;
        boolean flag = false;
        String msg = "";
        String gzLastMonth = "";
        String estateType = lnkYjFyDtoList.get(0).getEstateType();
        Integer empId = new Integer(0);
        if (lnkYjFyDtoList != null && lnkYjFyDtoList.size() > 0) {

            List<Map<String, Object>> checkRelMapList = new ArrayList<>();

            empId = Integer.valueOf(lnkYjFyDtoList.get(0).getCrtEmpId());
            //获取当前城市关账到的月份
            Map<?, ?> map = commonMapper.checkCitySwitchMonth(lnkYjFyDtoList.get(0).getCityNo());
            Calendar c = Calendar.getInstance();
            Date closeLastDay = null;
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            if (map != null && map.size() > 0) {
                gzLastMonth = map.get("year").toString() + "年" + map.get("month").toString() + "月";
                String closeLastDayStr = map.get("year").toString() + "-" + (Integer.valueOf((String) map.get("month")) + 1) + "-01";
                closeLastDay = format1.parse(closeLastDayStr);
            }


            boolean imperfectFlag = false;
            boolean DataErrorFlag = false;
            for (LnkYjFyDto cDto : lnkYjFyDtoList) {
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


            for (LnkYjFyDto lDto : lnkYjFyDtoList) {
                boolean sjfyFlag = true;
                //外佣收入
                if (StringUtil.isNotEmpty(lDto.getRecordDate())) {
                    count++;
                    lDto.setRecordDate(lDto.getRecordDate());
                    lDto.setBefTaxAmount(lDto.getBefTaxAmount().trim());
                    lDto.setAftTaxAmount(lDto.getAftTaxAmount().trim());
                    //验证当前记录是否关账
                    Integer switchFlag = lDto.getSwitchFlag();
                    if (switchFlag == null || switchFlag <= 0) {
                        Date recordDate = format1.parse(lDto.getRecordDate());
                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
                            if (estateType.equals("yjfy")) {
                                lnkYjQtYjfyMapper.mergeInsertFy(lDto);
                            } else if (estateType.equals("sjfy")) {
                                lnkYjQtSjfyMapper.mergeInsertFy(lDto);
                            }
                        } else {
                            flag = true;
                        }
                    }
                }

                //调整1
                if (StringUtil.isNotEmpty(lDto.getRecordDate1())) {
                    count++;
                    lDto.setRecordDate(lDto.getRecordDate1());
                    //验证当前记录是否关账
                    Integer switchFlag = lDto.getSwitchFlag1();
                    if (switchFlag == null || switchFlag <= 0) {
                        Date recordDate = format1.parse(lDto.getRecordDate());
                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
                            lDto.setBefTaxAmount(lDto.getBefTaxAmount1().trim());
                            lDto.setAftTaxAmount(lDto.getAftTaxAmount1().trim());
                            if (estateType.equals("yjfy")) {
                                lnkYjQtYjfyMapper.mergeInsertFy(lDto);
                            } else if (estateType.equals("sjfy")) {
                                lnkYjQtSjfyMapper.mergeInsertFy(lDto);
                            }
                        } else {
                            flag = true;
                        }
                    }
                }
                //调整2
                if (StringUtil.isNotEmpty(lDto.getRecordDate2())) {
                    count++;
                    lDto.setRecordDate(lDto.getRecordDate2());
                    //验证当前记录是否关账
                    Integer switchFlag = lDto.getSwitchFlag2();
                    if (switchFlag == null || switchFlag <= 0) {
                        Date recordDate = format1.parse(lDto.getRecordDate());
                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
                            lDto.setBefTaxAmount(lDto.getBefTaxAmount2().trim());
                            lDto.setAftTaxAmount(lDto.getAftTaxAmount2().trim());
                            if (estateType.equals("yjfy")) {
                                lnkYjQtYjfyMapper.mergeInsertFy(lDto);
                            } else if (estateType.equals("sjfy")) {
                                lnkYjQtSjfyMapper.mergeInsertFy(lDto);
                            }
                        } else {
                            flag = true;
                        }
                    }
                }
                //调整3
                if (StringUtil.isNotEmpty(lDto.getRecordDate3())) {
                    count++;
                    lDto.setRecordDate(lDto.getRecordDate3());
                    //验证当前记录是否关账
                    Integer switchFlag = lDto.getSwitchFlag3();
                    if (switchFlag == null || switchFlag <= 0) {
                        Date recordDate = format1.parse(lDto.getRecordDate());
                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
                            lDto.setBefTaxAmount(lDto.getBefTaxAmount3().trim());
                            lDto.setAftTaxAmount(lDto.getAftTaxAmount3().trim());
                            if (estateType.equals("yjfy")) {
                                lnkYjQtYjfyMapper.mergeInsertFy(lDto);
                            } else if (estateType.equals("sjfy")) {
                                lnkYjQtSjfyMapper.mergeInsertFy(lDto);
                            }
                        } else {
                            flag = true;
                        }
                    }
                }
                //调整4
                if (StringUtil.isNotEmpty(lDto.getRecordDate4())) {
                    count++;
                    lDto.setRecordDate(lDto.getRecordDate4());
                    //验证当前记录是否关账
                    Integer switchFlag = lDto.getSwitchFlag4();
                    if (switchFlag == null || switchFlag <= 0) {
                        Date recordDate = format1.parse(lDto.getRecordDate());
                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
                            lDto.setBefTaxAmount(lDto.getBefTaxAmount4().trim());
                            lDto.setAftTaxAmount(lDto.getAftTaxAmount4().trim());
                            if (estateType.equals("yjfy")) {
                                lnkYjQtYjfyMapper.mergeInsertFy(lDto);
                            } else if (estateType.equals("sjfy")) {
                                lnkYjQtSjfyMapper.mergeInsertFy(lDto);
                            }
                        } else {
                            flag = true;
                        }
                    }
                }
                //调整5
                if (StringUtil.isNotEmpty(lDto.getRecordDate5())) {
                    count++;
                    lDto.setRecordDate(lDto.getRecordDate5());
                    //验证当前记录是否关账
                    Integer switchFlag = lDto.getSwitchFlag5();
                    if (switchFlag == null || switchFlag <= 0) {
                        Date recordDate = format1.parse(lDto.getRecordDate());
                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
                            lDto.setBefTaxAmount(lDto.getBefTaxAmount5().trim());
                            lDto.setAftTaxAmount(lDto.getAftTaxAmount5().trim());
                            if (estateType.equals("yjfy")) {
                                lnkYjQtYjfyMapper.mergeInsertFy(lDto);
                            } else if (estateType.equals("sjfy")) {
                                lnkYjQtSjfyMapper.mergeInsertFy(lDto);
                            }
                        } else {
                            flag = true;
                        }
                    }
                }
                //调整6
                if (StringUtil.isNotEmpty(lDto.getRecordDate6())) {
                    count++;
                    lDto.setRecordDate(lDto.getRecordDate6());
                    //验证当前记录是否关账
                    Integer switchFlag = lDto.getSwitchFlag6();
                    if (switchFlag == null || switchFlag <= 0) {
                        Date recordDate = format1.parse(lDto.getRecordDate());
                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
                            lDto.setBefTaxAmount(lDto.getBefTaxAmount6().trim());
                            lDto.setAftTaxAmount(lDto.getAftTaxAmount6().trim());
                            if (estateType.equals("yjfy")) {
                                lnkYjQtYjfyMapper.mergeInsertFy(lDto);
                            } else if (estateType.equals("sjfy")) {
                                lnkYjQtSjfyMapper.mergeInsertFy(lDto);
                            }
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
            resultData.setReturnMsg("部分数据导入成功！" + msg + "无需手动导入返佣信息！");
        } else {
            resultData.setReturnMsg("导入数据成功！");
        }

        System.out.println("===更新数据：" + count + "条");
        resultData.setReturnData(count);
        return resultData;
    }
}
