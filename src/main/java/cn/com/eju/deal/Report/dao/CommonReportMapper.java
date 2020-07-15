package cn.com.eju.deal.Report.dao;

import cn.com.eju.deal.Report.dto.UsrOrgHisDto;

import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2018/1/18.
 */
public interface CommonReportMapper {
    /**
     * 获取归属区域
     * @param map
     * @return
     * @throws Exception
     */
    List<UsrOrgHisDto> getRegion(Map<String, Object> map);

    /**
     * 获取归属城市
     * @param map
     * @return
     * @throws Exception
     */
    List<UsrOrgHisDto> getAreaCity(Map<String, Object> map);

    /**
     * 获取所在城市
     * @param map
     * @return
     * @throws Exception
     */
    List<UsrOrgHisDto> getCityGroup(Map<String, Object> map);

    /**
     * 获取归属中心
     * @param map
     * @return
     * @throws Exception
     */
    List<UsrOrgHisDto> getCenterGroup(Map<String, Object> map);

    /**
     * 获取项目归属部门
     * @param map
     * @return
     * @throws Exception
     */
    List<UsrOrgHisDto> getDeptGroup(Map<String, Object> map);
}
