package cn.com.eju.deal.scene.settleconfirm.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.houseLinkage.report.ReportDto;
import cn.com.eju.deal.dto.scene.padCommission.PadCommissionResultDto;
import cn.com.eju.deal.scene.settleconfirm.dao.SettleConfirmMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yinkun on 2018/5/3.
 */
@Service("settleConfirmService")
public class SettleConfirmService extends BaseService {

    @Resource
    private SettleConfirmMapper settleConfirmMapper;

    @Resource
    private CommonService commonService;

    public List<PadCommissionResultDto> getSettleConfirmList(Map<String, Object> queryParam) throws Exception {
        return settleConfirmMapper.getSettleConfirmList(queryParam);
    }

    public ResultData batchUpdateSettleConfirmDate(List<ReportDto> reportDtoList) {

        ResultData resultData = new ResultData();

        int eCount = 0;
        int count = 0;
        Map<String,Object> map = new HashMap<>();
        
        for(ReportDto dto : reportDtoList){

            String cityNo = dto.getCityNo();
            int relateSystem= 17402;
            map.put("cityNo",cityNo);
            map.put("relateSystem",relateSystem);
            String yearMonth = null;
            try {
                Map<String, Object> result = commonService.getSwitchLnk(map);

                if (null != result && !result.isEmpty()) {
                    if ("52F1D510-DDA2-4A8D-9FD3-E165E933CEEC".equals(cityNo)) {//昆山当做上海处理
                        cityNo = "AAAD4421-21B1-46FD-9DE4-D5A3183CE260";
                    }
                    yearMonth = (String) result.get(cityNo);

                    if (StringUtil.isEmpty(yearMonth)) {
                        yearMonth = "1970-01-01";
                    }
                }

                if(dto.getSettleConfirmDate().compareTo(DateUtil.getDate(yearMonth,"yyyy-MM-dd")) >= 0){
                    int i = settleConfirmMapper.updateSettleConfirmDate(dto);
                    if(i>0){
                        count++;
                    }else{
                        //结算日期小于成销日期
                        eCount++;
                    }
                }else{
                    //已关账
                    eCount++;
                }
            } catch (Exception e) {
                eCount++;
                continue;
            }
        }

        if(eCount > 0){
            resultData.setReturnMsg("由于系统关账或结算日期不符合要求，部分数据未导入成功！");
        }

        return resultData;
    }
}
