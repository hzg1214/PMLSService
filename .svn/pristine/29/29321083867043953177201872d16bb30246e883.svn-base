package cn.com.eju.pmls.skStatement.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.common.dao.CommonMapper;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.skStatement.dao.PmlsSkAllocateLogMapper;
import cn.com.eju.pmls.skStatement.dao.PmlsSkAllocateMatMapper;
import cn.com.eju.pmls.skStatement.dao.PmlsSkStatementMapper;
import cn.com.eju.pmls.skStatement.dto.PmlsSkAllocateLogDto;
import cn.com.eju.pmls.skStatement.dto.PmlsSkAllocateMatDto;
import cn.com.eju.pmls.skStatement.dto.PmlsSkStatementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("skStatementService")
public class SkStatementService {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    PmlsSkStatementMapper pmlsSkStatementMapper;

    @Autowired
    CommonMapper commonMapper;

    @Autowired
    PmlsSkAllocateMatMapper pmlsSkAllocateMatMapper;

    @Autowired
    PmlsSkAllocateLogMapper pmlsSkAllocateLogMapper;

    /**
     * 查询
     */
    public ResultData<List<PmlsSkStatementDto>> queryList(Map<String, Object> queryParam) throws Exception {
        ResultData<List<PmlsSkStatementDto>> resultData = new ResultData<List<PmlsSkStatementDto>>();

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String cityNo = queryParam.get("cityNo").toString();
        String closeDateStr = this.getCloseDate(cityNo);
        LocalDate closeDate = LocalDate.parse(closeDateStr, fmt);

        List<PmlsSkStatementDto> list = pmlsSkStatementMapper.queryList(queryParam);
        for (PmlsSkStatementDto dto : list) {
            // 关账判断
            LocalDate recordDate = LocalDate.parse(dto.getRecordDate(), fmt);
            if (recordDate.isBefore(closeDate)) {
                dto.setSwitchFlag(1);
            } else {
                dto.setSwitchFlag(0);
            }
        }
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }

    /**
     * 详细信息取得
     */
    public ResultData<PmlsSkStatementDto> getSkStatementById(Map<String, Object> queryParam) throws Exception {
        ResultData<PmlsSkStatementDto> resultData = new ResultData<PmlsSkStatementDto>();

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String cityNo = queryParam.get("cityNo").toString();
        String closeDateStr = this.getCloseDate(cityNo);
        LocalDate closeDate = LocalDate.parse(closeDateStr, fmt);

        PmlsSkStatementDto dto = pmlsSkStatementMapper.getSkStatementById(queryParam);
        if (null != dto) {
            // 关账判断
            LocalDate recordDate = LocalDate.parse(dto.getRecordDate(), fmt);
            if (recordDate.isBefore(closeDate)) {
                dto.setSwitchFlag(1);
            } else {
                dto.setSwitchFlag(0);
            }

            if (dto.getDateUpdate() != null) {
                dto.setExclude(dto.getDateUpdate().getTime());
            } else {
                dto.setExclude((new Date()).getTime());
            }
        }

        resultData.setReturnData(dto);
        return resultData;
    }

    private String getCloseDate(String cityNo) throws Exception {
        // 关账日期
        Map<?, ?> map = commonMapper.checkCitySwitchMonth(cityNo);
        String closeDate = "2016-01-01";
        if (map != null && map.size() > 0) {
            String closeDateStr = map.get("year").toString() + "-" + map.get("month").toString() + "-01";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = sdf.parse(closeDateStr);
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            c.add(Calendar.MONTH, 1);
            Date dt1 = c.getTime();
            closeDate = sdf.format(dt1);
        }
        return closeDate;
    }


    public ResultData<PmlsSkStatementDto> allocateCheck(Map<String, Object> queryParam) throws Exception {

        ResultData<PmlsSkStatementDto> resultData = new ResultData<PmlsSkStatementDto>();

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String cityNo = queryParam.get("cityNo").toString();
        String closeDateStr = this.getCloseDate(cityNo);
        LocalDate closeDate = LocalDate.parse(closeDateStr, fmt);

        PmlsSkStatementDto dto = pmlsSkStatementMapper.getSkStatementById(queryParam);
        resultData.setReturnData(dto);
        if (null != dto) {
            if (dto.getAllocatedFlag().equals(-1) || dto.getStayAmount_bef().doubleValue() == 0) {
                resultData.setFail("收款单已拆分完毕或待拆分金额为零！");
                return resultData;
            }
            // 关账判断（0-未关账标记，1-已关账标记）
            LocalDate recordDate = LocalDate.parse(dto.getRecordDate(), fmt);
            if (recordDate.isBefore(closeDate)) {
                dto.setSwitchFlag(1);
                resultData.setFail("入账日期处于关账月份，不允许拆分！");
                return resultData;
            } else {
                dto.setSwitchFlag(0);
            }

        } else {
            resultData.setFail("数据未找到或已删除！");
            return resultData;
        }
        return resultData;
    }
}


