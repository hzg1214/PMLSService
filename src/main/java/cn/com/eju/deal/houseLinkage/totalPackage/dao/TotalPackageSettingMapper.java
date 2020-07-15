package cn.com.eju.deal.houseLinkage.totalPackage.dao;

import cn.com.eju.deal.houseLinkage.totalPackage.model.TotalPackageSetting;
import org.apache.ibatis.annotations.Param;

/**
 * 业绩归属 开关DAO
 * @author wenhui.zhang
 * date: 2017年4月21日 上午9:31:34
 */
public interface TotalPackageSettingMapper {
	
	TotalPackageSetting getTotalPackageSetting(@Param("serviceObj") String serviceObj);

}