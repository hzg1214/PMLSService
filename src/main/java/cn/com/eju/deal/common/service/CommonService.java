package cn.com.eju.deal.common.service;

import cn.com.eju.deal.achievement.dao.CitySettingMapper;
import cn.com.eju.deal.common.dao.CommonMapper;
import cn.com.eju.deal.common.dao.OmsInteractiveMapper;
import cn.com.eju.deal.common.model.City;
import cn.com.eju.deal.common.model.CompanyFullName;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.common.CenterDto;
import cn.com.eju.deal.dto.common.OrgDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("commonService")
public class CommonService {
    @Resource
    private OmsInteractiveMapper omsInteractiveMapper;

    @Resource
    private CommonMapper commonMapper;

    @Resource
    private CitySettingMapper citySettingMapper;

    public Map<String, Object> getSwitchLnk(Map<String, Object> reqMap) throws Exception {

        List<Map<String, Object>> moList = omsInteractiveMapper.queryAPIListByCityNo(reqMap);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        for (Map<String, Object> map : moList) {
            // 时间处理
            String lastDateStr = "";
            String closeDateStr = String.valueOf(map.get("closeDate"));
            if (closeDateStr != null) {
                lastDateStr = convert(closeDateStr);
            }
            returnMap.put((String) map.get("cityNo"), lastDateStr);
        }
        return returnMap;
    }

    /**
     * 获取下个月的日期
     *
     * @param source
     * @return
     */
    private String convert(String source) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(source);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, 1);
            return sdf2.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public ResultData<List<OrgDto>> queryOrgList(Map<?, ?> param) throws Exception {

        ResultData<List<OrgDto>> resultData = new ResultData<>();
        List<OrgDto> list = commonMapper.queryOrgList(param);
        resultData.setReturnData(list);
        return resultData;
    }

    public ResultData<List<City>> queryCityList(Map<?, ?> param) throws Exception {

        ResultData<List<City>> resultData = new ResultData<>();
        List<City> list = commonMapper.queryCityList(param);
        resultData.setReturnData(list);
        return resultData;
    }

    public ResultData<List<CenterDto>> queryCenterList(Map<?, ?> param) throws Exception {

        ResultData<List<CenterDto>> resultData = new ResultData<>();
        List<CenterDto> list = commonMapper.queryCenterList(param);
        resultData.setReturnData(list);
        return resultData;
    }

    //根据cityNo查询模板编号
    public ResultData<Map<String, Object>> getCitySettingByCityNo(String cityNo) throws Exception {

        ResultData<Map<String, Object>> resultData = new ResultData<>();
        Map<String, Object> map = citySettingMapper.getCitySettingByCityNo(cityNo);
        resultData.setReturnData(map);
        return resultData;
    }

    public ResultData<List<CompanyFullName>> queryFullNameList(Map<?, ?> queryParam) throws Exception {
        ResultData<List<CompanyFullName>> resultData = new ResultData<>();
        List<CompanyFullName> list = commonMapper.queryFullNameList(queryParam);
        resultData.setReturnData(list);
        return resultData;
    }

}
