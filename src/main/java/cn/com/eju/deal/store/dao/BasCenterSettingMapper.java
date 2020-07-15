/**
 * 
 */
package cn.com.eju.deal.store.dao;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.store.model.BasCenterSetting;

import java.util.Map;

/**
 * @author yinkun
 *
 */
public interface BasCenterSettingMapper extends IDao<BasCenterSetting>{

    BasCenterSetting selectByContractNo(Map map);

    BasCenterSetting selectByStoreNo(Map map);
}
