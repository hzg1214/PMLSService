package cn.com.eju.pmls.performswitch.dao;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.pmls.performswitch.dto.CheckRoughDate;
import cn.com.eju.pmls.performswitch.dto.PerformSwitchDto;
import cn.com.eju.pmls.performswitch.model.PerformSwitch;


import java.util.List;
import java.util.Map;

/**
 * 开关账dao
 */
public interface PerformSwitchMapper extends IDao<PerformSwitch>{

	/**
	 * 列表查询开关账记录
	 * @param param
	 * @return
	 */
	List<PerformSwitchDto> queryLisDto(Map<?, ?> param);

	/**
	 * 查询开关账记录
	 * @param param
	 * @return
	 */
	PerformSwitch selectBYSwitch(PerformSwitch param);

	/**
	 * 判断当前时间前后一个月开关账记录
	 * @param param
	 * @return
	 */
	PerformSwitch checkAroundDate(PerformSwitch param);

	/**
	 * 判断当前时间有没有开关账记录
	 * @param param
	 * @return
	 */
	PerformSwitch checkInDate(PerformSwitch param);

	/**
	 * 查看该月份下城市是否尚有大定待审核的记录
	 * @param param
	 */
	List<CheckRoughDate> checkRoughToValid(Map<?, ?> param);

}