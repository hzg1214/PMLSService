package cn.com.eju.deal.linkageWechat.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.linkageWechat.dao.LinkageUserMapper;
import cn.com.eju.deal.linkageWechat.dao.VerificationCodeMapper;
import cn.com.eju.deal.linkageWechat.dao.WechatMessageMapper;
import cn.com.eju.deal.linkageWechat.dao.WechatUserMapper;
import cn.com.eju.deal.linkageWechat.model.LinkageUser;
import cn.com.eju.deal.linkageWechat.model.VerificationCode;
import cn.com.eju.deal.linkageWechat.model.WechatMessage;
import cn.com.eju.deal.linkageWechat.model.WechatUser;

/**
 * 新房联动微信 项目接口
 * 
 * @author wenhui.zhang
 * @date 2016年5月17日 下午1:23:05
 */
@Service("wechatUserService")
public class WechatUserService {

	@Resource
	private WechatUserMapper wechatDao;
	@Resource
	private WechatMessageMapper wechatMessageDao;
	@Resource
	private VerificationCodeMapper VerificationCodeDao;
	@Resource
	private LinkageUserMapper linkageuserDao;

	/**
	 * 查询微信用户
	 * 
	 * @param wechatId
	 * @return
	 * @throws Exception
	 */
	public WechatUser getWechatUserByWechatId(String wechatId) throws Exception {
		return wechatDao.getWechatUserByWechatId(wechatId);
	}

	/**
	 * 新增微信用户
	 * 
	 * @param wechatUser
	 * @return
	 * @throws Exception
	 */
	public int saveWechatUser(WechatUser wechatUser) throws Exception {
		return wechatDao.saveWechatUser(wechatUser);
	}

	public int updateWechatUser(WechatUser wechatUser) throws Exception {
		return wechatDao.updateWechatUser(wechatUser);
	}

	/**
	 * 新增微信消息
	 * 
	 * @param message
	 * @return
	 */
	public int saveWechatMessage(WechatMessage message) throws Exception {
		return wechatMessageDao.saveWechatMessage(message);
	}

	/**
	 * 新增一条验证码信息
	 * 
	 * @param verificationCode
	 * @return
	 */
	public int saveVerifyCode(VerificationCode verificationCode)
			throws Exception {
		return VerificationCodeDao.saveVerificationCode(verificationCode);
	}

	/**
	 * 根据phoneNumber , userName查询验证码信息
	 * 
	 * @param verificationCode
	 *            验证码model
	 * @return
	 */
	public VerificationCode getBackVerifyCode(VerificationCode verificationCode)
			throws Exception {
		return VerificationCodeDao.getBackVerifyCode(verificationCode);
	}

	public int updateVerificationCode(VerificationCode verificationCode) throws Exception{
		return VerificationCodeDao.updateVerificationCode(verificationCode);
	}

	/**
	 * 根据手机号码查询用户信息
	 * 
	 * @param phoneNumber
	 *            手机号码
	 * @return
	 */
	public LinkageUser getByPhoneNumber(String phoneNumber) throws Exception {
		return linkageuserDao.getByPhoneNumber(phoneNumber);
	}

	/**
	 * 根据 用户账号、密码 查询用户信息
	 * 
	 * @param userCardNo
	 *            用户账号
	 * @param userPassword
	 *            密码
	 * @return
	 */
	public LinkageUser queryLinkageUser(LinkageUser linkageUser)
			throws Exception {
		return linkageuserDao.queryLinkageUser(linkageUser);
	}

	/**
	 * 保存用户注册信息
	 * 
	 * @param linkageUser
	 *            用户信息model
	 * @return
	 */
	public int saveLinkageUser(LinkageUser linkageUser) throws Exception {
		return linkageuserDao.saveWechatUser(linkageUser);
	}

	/**
	 * 重置密码操作
	 * 
	 * @param infoUser
	 * @return
	 */
	public int updatePassword(LinkageUser infoUser) throws Exception {
		return linkageuserDao.updatePassword(infoUser);
	}

	/**
	 * 用户注销操作
	 * 
	 * @param infoUser
	 * @return
	 */
	public int deleteLinkageUser(LinkageUser infoUser) throws Exception {
		return linkageuserDao.deleteLinkageUser(infoUser);
	}

}
