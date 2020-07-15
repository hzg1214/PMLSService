package cn.com.eju.deal.otherReport.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.common.dao.CommonMapper;
import cn.com.eju.deal.common.util.AmountUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.otherReport.dao.LnkYjQtNyMapper;
import cn.com.eju.deal.otherReport.dto.LnkYjNyDto;
import cn.com.eju.deal.otherReport.dto.LnkYjWyDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by eju on 2019/10/28.
 */
@Service("lnkYjQtNyService")
public class LnkYjQtNyService extends BaseService {

    @Resource
    LnkYjQtNyMapper lnkYjQtNyMapper;
    @Resource
    CommonMapper commonMapper;

    public ResultData<List<LnkYjNyDto>> getLnkYjQtNyList(Map<String, Object> map) {
        ResultData<List<LnkYjNyDto>> resultData = new ResultData<>();
        List<LnkYjNyDto> relList = new ArrayList<>();

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

        relList = lnkYjQtNyMapper.getList(map);

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

    public ResultData<String> insertLnkQtNy(LnkYjNyDto dto) throws ParseException {
        ResultData resultData = new ResultData();
        List<LnkYjNyDto> lnkYjNyDtoList = dto.getLnkYjNyDtoList();
        int count = 0;
        boolean flag = false;
        String msg = "";
        String gzLastMonth = "";
        Integer empId = new Integer(0);
        if (lnkYjNyDtoList != null && lnkYjNyDtoList.size() > 0) {

            List<Map<String, Object>> checkRelMapList = new ArrayList<>();


            empId = Integer.valueOf(lnkYjNyDtoList.get(0).getCrtEmpId());
            //获取当前城市关账到的月份
            Map<?, ?> map = commonMapper.checkCitySwitchMonth(lnkYjNyDtoList.get(0).getCityNo());
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
            for (LnkYjNyDto cDto : lnkYjNyDtoList) {
                if(StringUtil.isNotEmpty(cDto.getRecordDate())
                        && StringUtil.isNotEmpty(cDto.getPostAmount()) && StringUtil.isNotEmpty(cDto.getTotalAmount())){
                    //正常数据
                    if(!AmountUtils.isValidAmount(cDto.getPostAmount()) || !AmountUtils.isValidAmount(cDto.getTotalAmount())){
                        DataErrorFlag = true;
                        break;
                    }
                }else {
                    if(StringUtil.isNotEmpty(cDto.getRecordDate())
                            || StringUtil.isNotEmpty(cDto.getPostAmount()) || StringUtil.isNotEmpty(cDto.getTotalAmount()) ){
                        imperfectFlag = true;
                        break;
                    }
                }

                if(StringUtil.isNotEmpty(cDto.getRecordDate1())
                        && StringUtil.isNotEmpty(cDto.getPostAmount1()) && StringUtil.isNotEmpty(cDto.getTotalAmount1())){
                    //正常数据
                    if(!AmountUtils.isValidAmount(cDto.getPostAmount1()) || !AmountUtils.isValidAmount(cDto.getTotalAmount1())){
                        DataErrorFlag = true;
                        break;
                    }
                }else {
                    if(StringUtil.isNotEmpty(cDto.getRecordDate1())
                            || StringUtil.isNotEmpty(cDto.getPostAmount1()) || StringUtil.isNotEmpty(cDto.getTotalAmount1()) ){
                        imperfectFlag = true;
                        break;
                    }
                }

                if(StringUtil.isNotEmpty(cDto.getRecordDate2())
                        && StringUtil.isNotEmpty(cDto.getPostAmount2()) && StringUtil.isNotEmpty(cDto.getTotalAmount2())){
                    //正常数据
                    if(!AmountUtils.isValidAmount(cDto.getPostAmount2()) || !AmountUtils.isValidAmount(cDto.getTotalAmount2())){
                        DataErrorFlag = true;
                        break;
                    }
                }else {
                    if(StringUtil.isNotEmpty(cDto.getRecordDate2())
                            || StringUtil.isNotEmpty(cDto.getPostAmount2()) || StringUtil.isNotEmpty(cDto.getTotalAmount2()) ){
                        imperfectFlag = true;
                        break;
                    }
                }

                if(StringUtil.isNotEmpty(cDto.getRecordDate3())
                        && StringUtil.isNotEmpty(cDto.getPostAmount3()) && StringUtil.isNotEmpty(cDto.getTotalAmount3())){
                    //正常数据
                    if(!AmountUtils.isValidAmount(cDto.getPostAmount3()) || !AmountUtils.isValidAmount(cDto.getTotalAmount3())){
                        DataErrorFlag = true;
                        break;
                    }
                }else {
                    if(StringUtil.isNotEmpty(cDto.getRecordDate3())
                            || StringUtil.isNotEmpty(cDto.getPostAmount3()) || StringUtil.isNotEmpty(cDto.getTotalAmount3()) ){
                        imperfectFlag = true;
                        break;
                    }
                }

                if(StringUtil.isNotEmpty(cDto.getRecordDate4())
                        && StringUtil.isNotEmpty(cDto.getPostAmount4()) && StringUtil.isNotEmpty(cDto.getTotalAmount4())){
                    //正常数据
                    if(!AmountUtils.isValidAmount(cDto.getPostAmount4()) || !AmountUtils.isValidAmount(cDto.getTotalAmount4())){
                        DataErrorFlag = true;
                        break;
                    }
                }else {
                    if(StringUtil.isNotEmpty(cDto.getRecordDate4())
                            || StringUtil.isNotEmpty(cDto.getPostAmount4()) || StringUtil.isNotEmpty(cDto.getTotalAmount4()) ){
                        imperfectFlag = true;
                        break;
                    }
                }

                if(StringUtil.isNotEmpty(cDto.getRecordDate5())
                        && StringUtil.isNotEmpty(cDto.getPostAmount5()) && StringUtil.isNotEmpty(cDto.getTotalAmount5())){
                    //正常数据
                    if(!AmountUtils.isValidAmount(cDto.getPostAmount5()) || !AmountUtils.isValidAmount(cDto.getTotalAmount5())){
                        DataErrorFlag = true;
                        break;
                    }
                }else {
                    if(StringUtil.isNotEmpty(cDto.getRecordDate5())
                            || StringUtil.isNotEmpty(cDto.getPostAmount5()) || StringUtil.isNotEmpty(cDto.getTotalAmount5()) ){
                        imperfectFlag = true;
                        break;
                    }
                }

                if(StringUtil.isNotEmpty(cDto.getRecordDate6())
                        && StringUtil.isNotEmpty(cDto.getPostAmount6()) && StringUtil.isNotEmpty(cDto.getTotalAmount6())){
                    //正常数据
                    if(!AmountUtils.isValidAmount(cDto.getPostAmount6()) || !AmountUtils.isValidAmount(cDto.getTotalAmount6())){
                        DataErrorFlag = true;
                        break;
                    }
                }else {
                    if(StringUtil.isNotEmpty(cDto.getRecordDate6())
                            || StringUtil.isNotEmpty(cDto.getPostAmount6()) || StringUtil.isNotEmpty(cDto.getTotalAmount6()) ){
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

            for (LnkYjNyDto lDto : lnkYjNyDtoList) {
                boolean sjfyFlag = true;
                //外佣收入
                if (StringUtil.isNotEmpty(lDto.getRecordDate())) {
                    count++;
                    lDto.setRecordDate(lDto.getRecordDate());
                    lDto.setPostAmount(lDto.getPostAmount().trim());
                    lDto.setTotalAmount(lDto.getTotalAmount().trim());
                    //验证当前记录是否关账
                    Integer switchFlag = lDto.getSwitchFlag();
                    if (switchFlag == null || switchFlag <= 0) {
                        Date recordDate = format1.parse(lDto.getRecordDate());
                        if (closeLastDay != null && closeLastDay.getTime() <= recordDate.getTime()) {
                            lnkYjQtNyMapper.mergeInsertNy(lDto);
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
                            lDto.setPostAmount(lDto.getPostAmount1().trim());
                            lDto.setTotalAmount(lDto.getTotalAmount1().trim());
                            lnkYjQtNyMapper.mergeInsertNy(lDto);
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
                            lDto.setPostAmount(lDto.getPostAmount2().trim());
                            lDto.setTotalAmount(lDto.getTotalAmount2().trim());
                            lnkYjQtNyMapper.mergeInsertNy(lDto);
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
                            lDto.setPostAmount(lDto.getPostAmount3().trim());
                            lDto.setTotalAmount(lDto.getTotalAmount3().trim());
                            lnkYjQtNyMapper.mergeInsertNy(lDto);
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
                            lDto.setPostAmount(lDto.getPostAmount4().trim());
                            lDto.setTotalAmount(lDto.getTotalAmount4().trim());
                            lnkYjQtNyMapper.mergeInsertNy(lDto);
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
                            lDto.setPostAmount(lDto.getPostAmount5().trim());
                            lDto.setTotalAmount(lDto.getTotalAmount5().trim());
                            lnkYjQtNyMapper.mergeInsertNy(lDto);
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
                            lDto.setPostAmount(lDto.getPostAmount6().trim());
                            lDto.setTotalAmount(lDto.getTotalAmount6().trim());
                            lnkYjQtNyMapper.mergeInsertNy(lDto);
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
            resultData.setReturnMsg("部分数据导入成功！" + msg + "无需手动导入内佣信息！");
        } else {
            resultData.setReturnMsg("导入数据成功！");
        }

        System.out.println("===更新数据：" + count + "条");
        resultData.setReturnData(count);
        return resultData;
    }
}
