package cn.com.eju.deal.scene.padCommission.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateInfoDto;
import cn.com.eju.deal.dto.scene.padCommission.PadCommissionResultDto;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateMapper;
import cn.com.eju.deal.scene.padCommission.dao.ScenePadCommissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * service层
 *
 * @author (xuliang)
 * @date 2018年1月31日22:06:34
 */
@Service("scenePadCommissionService")
public class ScenePadCommissionService extends BaseService {

    @Resource
    private EstateMapper estateMapper;
    @Resource
    ScenePadCommissionMapper scenePadCommissionMapper;


    /**
     * 得到垫佣List
     *
     * @return
     */
    public List<PadCommissionResultDto> getPadCommissionList(Map<String, Object> map) {
        List<PadCommissionResultDto> list = null;
        try {
            list = scenePadCommissionMapper.getPadCommissionList(map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return list;
    }

    public ResultData del(Map<String, Object> queryParam) {
        ResultData resultData = new ResultData();
        try {
            scenePadCommissionMapper.del(queryParam);
            resultData.setSuccess("删除成功！");
        } catch (Exception e) {
            resultData.setFail("删除异常！");
        }
        return resultData;
    }


    /**
     * 导入新增
     *
     * @param
     * @return
     */
    public ResultData insertLnkImport(EstateInfoDto dto)
            throws Exception {
        ResultData resultData = new ResultData();
        List<PadCommissionResultDto> padCommissionResultList = dto.getPadCommissionResultList();
        int count = 0;
        boolean flag = false;
        boolean flag2 = false;
        String gzLastMonth = "";
        LinkedHashMap<String, String> recordMap = new LinkedHashMap<String, String>();
        if (padCommissionResultList != null && padCommissionResultList.size() > 0) {
            //获取当前城市关账到的月份
            Map<?, ?> map = estateMapper.checkCitySwitchMonth(padCommissionResultList.get(0));
            if (map != null && map.size() > 0) {
                gzLastMonth = map.get("year").toString() + "年" + map.get("month").toString() + "月";
            }
            for (PadCommissionResultDto prDto : padCommissionResultList) {
                //应垫佣金
                if (StringUtil.isNotEmpty(prDto.getYjRecordDate())) {
                    count++;
                    prDto.setRecordDate(prDto.getYjRecordDate());
                    //验证当前记录是否关账
                    int value = estateMapper.checkSwitchMonth(prDto);
                    //验证报单编号和业绩城市是否相符
                    int value2 = estateMapper.checkCityNo(prDto);
                    if (value <= 0) {
                        if (value2 > 0) {
                            estateMapper.merageYjReportdy(prDto);
                        } else {
                            flag2 = true;
                            recordMap.put(prDto.getReportId(), prDto.getReportId());
                        }
                    } else {
                        flag = true;
                    }
                }

                //应垫佣金调整1
                if (StringUtil.isNotEmpty(prDto.getYjRecordDate1())) {
                    count++;
                    prDto.setRecordDate(prDto.getYjRecordDate1());
                    //验证当前记录是否关账
                    int value = estateMapper.checkSwitchMonth(prDto);
                    //验证报单编号和业绩城市是否相符
                    int value2 = estateMapper.checkCityNo(prDto);
                    if (value <= 0) {
                        if (value2 > 0) {
                            prDto.setBefTaxYjAmount(prDto.getBefTaxYjAmount1());
                            prDto.setAftTaxYjAmount(prDto.getAftTaxYjAmount1());
                            estateMapper.merageYjReportdy(prDto);
                        } else {
                            flag2 = true;
                            recordMap.put(prDto.getReportId(), prDto.getReportId());
                        }
                    } else {
                        flag = true;
                    }
                }
                //应垫佣金调整2
                if (StringUtil.isNotEmpty(prDto.getYjRecordDate2())) {
                    count++;
                    prDto.setRecordDate(prDto.getYjRecordDate2());
                    //验证当前记录是否关账
                    int value = estateMapper.checkSwitchMonth(prDto);
                    //验证报单编号和业绩城市是否相符
                    int value2 = estateMapper.checkCityNo(prDto);
                    if (value <= 0) {
                        if (value2 > 0) {
                            prDto.setBefTaxYjAmount(prDto.getBefTaxYjAmount2());
                            prDto.setAftTaxYjAmount(prDto.getAftTaxYjAmount2());
                            estateMapper.merageYjReportdy(prDto);
                        } else {
                            flag2 = true;
                            recordMap.put(prDto.getReportId(), prDto.getReportId());
                        }
                    } else {
                        flag = true;
                    }
                }
                //应垫佣金调整3
                if (StringUtil.isNotEmpty(prDto.getYjRecordDate3())) {
                    count++;
                    prDto.setRecordDate(prDto.getYjRecordDate3());
                    //验证当前记录是否关账
                    int value = estateMapper.checkSwitchMonth(prDto);
                    //验证报单编号和业绩城市是否相符
                    int value2 = estateMapper.checkCityNo(prDto);
                    if (value <= 0) {
                        if (value2 > 0) {
                            prDto.setBefTaxYjAmount(prDto.getBefTaxYjAmount3());
                            prDto.setAftTaxYjAmount(prDto.getAftTaxYjAmount3());
                            estateMapper.merageYjReportdy(prDto);
                        } else {
                            flag2 = true;
                            recordMap.put(prDto.getReportId(), prDto.getReportId());
                        }
                    } else {
                        flag = true;
                    }
                }

                //实垫佣金
                if (StringUtil.isNotEmpty(prDto.getSjRecordDate())) {
                    count++;
                    prDto.setRecordDate(prDto.getSjRecordDate());
                    //验证当前记录是否关账
                    int value = estateMapper.checkSwitchMonth(prDto);
                    //验证报单编号和业绩城市是否相符
                    int value2 = estateMapper.checkCityNo(prDto);
                    if (value <= 0) {
                        if (value2 > 0) {
                            estateMapper.merageSjReportdy(prDto);
                        } else {
                            flag2 = true;
                            recordMap.put(prDto.getReportId(), prDto.getReportId());
                        }
                    } else {
                        flag = true;
                    }
                }
                //实垫佣金调整1
                if (StringUtil.isNotEmpty(prDto.getSjRecordDate1())) {
                    count++;
                    prDto.setRecordDate(prDto.getSjRecordDate1());
                    //验证当前记录是否关账
                    int value = estateMapper.checkSwitchMonth(prDto);
                    //验证报单编号和业绩城市是否相符
                    int value2 = estateMapper.checkCityNo(prDto);
                    if (value <= 0) {
                        if (value2 > 0) {
                            prDto.setBefTaxSjAmount(prDto.getBefTaxSjAmount1());
                            prDto.setAftTaxSjAmount(prDto.getAftTaxSjAmount1());
                            estateMapper.merageSjReportdy(prDto);
                        } else {
                            flag2 = true;
                            recordMap.put(prDto.getReportId(), prDto.getReportId());
                        }
                    } else {
                        flag = true;
                    }
                }
                //实垫佣金调整2
                if (StringUtil.isNotEmpty(prDto.getSjRecordDate2())) {
                    count++;
                    prDto.setRecordDate(prDto.getSjRecordDate2());
                    //验证当前记录是否关账
                    int value = estateMapper.checkSwitchMonth(prDto);
                    //验证报单编号和业绩城市是否相符
                    int value2 = estateMapper.checkCityNo(prDto);
                    if (value <= 0) {
                        if (value2 > 0) {
                            prDto.setBefTaxSjAmount(prDto.getBefTaxSjAmount2());
                            prDto.setAftTaxSjAmount(prDto.getAftTaxSjAmount2());
                            estateMapper.merageSjReportdy(prDto);
                        } else {
                            flag2 = true;
                            recordMap.put(prDto.getReportId(), prDto.getReportId());
                        }
                    } else {
                        flag = true;
                    }
                }
            }
        }
        if (flag) {
            resultData.setReturnMsg("由于联动业务已关账至" + gzLastMonth + "，部分数据导入不成功，请确认！");
        } else {
            if (flag2) {
                String recordIdStr = "";
                for (String key : recordMap.keySet()) {
                    recordIdStr += key + "；";
                }
                if (recordIdStr != null && !"".equals(recordIdStr)) {
                    recordIdStr = recordIdStr.substring(0, recordIdStr.length() - 1);
                }
                resultData.setReturnMsg("部分数据导入未成功，报备编号：" + recordIdStr + "！");
            } else {
                resultData.setReturnMsg("导入数据成功！");
            }

        }

        System.out.println("===更新数据：" + count + "条");
        resultData.setReturnData(count);
        return resultData;
    }

}
