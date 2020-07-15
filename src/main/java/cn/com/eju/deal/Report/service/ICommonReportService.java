package cn.com.eju.deal.Report.service;

import cn.com.eju.deal.Report.dto.UsrOrgHisDto;
import cn.com.eju.deal.core.support.ResultData;

import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2018/1/18.
 */
public interface ICommonReportService {

    /**
     * 获取归属区域
     *
     * @param map
     * @return
     * @throws Exception
     */
    ResultData<List<UsrOrgHisDto>> getRegion(Map<String, Object> map) throws Exception;

    /**
     * 获取归属城市
     *
     * @param map
     * @return
     * @throws Exception
     */
    ResultData<List<UsrOrgHisDto>> getAreaCity(Map<String, Object> map) throws Exception;

    /**
     * 获取所在城市
     *
     * @param map
     * @return
     * @throws Exception
     */
    ResultData<List<UsrOrgHisDto>> getCityGroup(Map<String, Object> map) throws Exception;

    /**
     * 获取归属中心
     *
     * @param map
     * @return
     * @throws Exception
     */
    ResultData<List<UsrOrgHisDto>> getCenterGroup(Map<String, Object> map) throws Exception;

    /**
     * 获取项目归属部门
     * @param queryParam
     * @return
     */
    ResultData<List<UsrOrgHisDto>> getDeptGroup(Map<String, Object> queryParam) throws Exception;
}
