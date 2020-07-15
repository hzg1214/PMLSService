package cn.com.eju.deal.contacts.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.contacts.model.Contacts;
import cn.com.eju.deal.core.dao.IDao;

/**
 * 联系人DAO
 * 
 * @author (li_xiaodong)
 * @date 2016年3月24日 上午9:34:16
 */
public interface ContactsMapper extends IDao<Contacts> {

	/**
	 * 查询当前User和当前User下属创建的联系人信息（提供给CRM微信端）
	 * 
	 * @param param
	 * @return
	 */
	List<Contacts> getByUserId(Map<?, ?> param);

	Integer getContracts(Contacts contacts);

}