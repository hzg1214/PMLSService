package cn.com.eju.deal.Reportconfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.com.eju.deal.core.support.SystemCfg;

/**
 * 报表服务配置
 */
public class ReportServiceConfiguration {

	private static List<Integer> achievementTypeIds = Arrays.asList(7, 8, 4);
	
	private static Map<Integer, String> achievementTypes = new HashMap<Integer, String>(){
		private static final long serialVersionUID = -297282870343506159L;
		{
			put(7, "事业部");
			put(8, "部门");
			put(4, "小组");
		}
	};
	
	private static void init() {
		if (achievementTypes == null) {
			String[] typeIds = StringUtils.split(SystemCfg.getString("report-service.achievement-type-id"), ",");
			String[] typeNames = StringUtils.split(SystemCfg.getString("report-service.achievement-type-name"), ",");
			achievementTypeIds = new ArrayList<>(typeIds.length);
			achievementTypes = new HashMap<>(achievementTypeIds.size());
			for (int i = 0; i < typeIds.length; i++) {
				achievementTypeIds.add(Integer.valueOf(typeIds[i].trim()));
				achievementTypes.put(achievementTypeIds.get(i), typeNames[i].trim());
			}
		}
	}
	
	/**
	 * 获取业绩归属组的所有类型id
	 * @return 业绩归属组的类型id集合
	 */
	public static List<Integer> getAchievementTypeIds() {
		init();
		return achievementTypeIds;
	}
	
	/**
	 * 获取业绩归属组的类型名称
	 * @param typeId 类型id
	 * @return 类型名称
	 */
	public static String getAchievementName(Integer typeId) {
		init();
		return achievementTypes.get(typeId);
	}
}
