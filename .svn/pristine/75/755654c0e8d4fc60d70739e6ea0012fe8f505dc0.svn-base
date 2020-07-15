package cn.com.eju.deal.shareaccount.dao;

import org.springframework.stereotype.Repository;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.shareaccount.model.ShareAccount;

/**
 * DAO层
 * @author zhenggang.Huang
 * date: 2019年2月19日
 */
@Repository
public interface ShareAccountMapper extends IDao<ShareAccount>
{
	ShareAccount selectByPrimaryKey(String companyNo);
	
	int insert(ShareAccount shareAccount);
}