package cn.com.eju.deal.linkageWechat.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.linkageWechat.model.LinkageUser;
import cn.com.eju.deal.linkageWechat.model.VerificationCode;
import cn.com.eju.deal.linkageWechat.model.WechatMessage;
import cn.com.eju.deal.linkageWechat.model.WechatUser;
import cn.com.eju.deal.linkageWechat.service.WechatUserService;

/**
 * 新房联动微信 项目接口
 * 
 * @author wenhui.zhang
 * @date 2016年5月17日 下午1:23:05
 */
@RestController
@RequestMapping(value = "wechatUser")
public class WechatUserController extends BaseController {

	/**
	 * 日志
	 */
	private final LogHelper logger = LogHelper.getLogger(this.getClass());

	/**
	 * 服务层
	 */
	@Resource(name = "wechatUserService")
	private WechatUserService wechatUserService;

	/**
	 * 查询微信用户
	 * 
	 * @param wechatId
	 *            类型编号
	 * @return wechatUser信息
	 */
	@RequestMapping(value = "/wechatId/{wechatId}", method = RequestMethod.GET)
	public String getWechatUserByWechatId(@PathVariable String wechatId) {

		ResultData<WechatUser> resultData = new ResultData<>();
		try {
			// 查询微信用户
			WechatUser wechatUser = wechatUserService
					.getWechatUserByWechatId(wechatId);

			if (wechatUser != null) {
				resultData.setSuccess();
				resultData.setReturnData(wechatUser);
			} else {
				resultData.setFail("查询失败");
			}
		} catch (Exception e) {
			resultData.setFail();
			logger.error("linkageWechat", "WechatUserController",
					"getWechatUserByWechatId", wechatId, 0, "", "", e);
		}

		return resultData.toString();
	}

	/**
	 * 保存微信用户
	 * 
	 * @param wechatId
	 *            类型编号
	 * @return wechatUser信息
	 */
	@RequestMapping(value = "addWechatUser", method = RequestMethod.POST)
	public String addWechatUser(@RequestBody String jsonParam) {
		ResultData<Integer> resultData = new ResultData<>();
		try {
			WechatUser wechatUser = JsonUtil.parseToObject(jsonParam,
					WechatUser.class);
			// 新增微信用户
			Integer num = wechatUserService.saveWechatUser(wechatUser);

			if (num > 0) {
				resultData.setSuccess();
			} else {
				resultData.setFail("新增失败");
			}
		} catch (Exception e) {
			resultData.setFail();
			logger.error("linkageWechat", "WechatUserController",
					"addWechatUser", jsonParam, 0, "", "", e);
		}

		return resultData.toString();
	}

	/**
	 * 更新微信用户
	 * 
	 * @param wechatUser
	 */
	@RequestMapping(value = "updateWechatUser", method = RequestMethod.PUT)
	public String updateWechatUser(@RequestBody String jsonParam) {
		ResultData<Integer> resultData = new ResultData<>();
		try {
			WechatUser wechatUser = JsonUtil.parseToObject(jsonParam,
					WechatUser.class);
			Integer num = wechatUserService.updateWechatUser(wechatUser);

			if (num > 0) {
				resultData.setSuccess();
			} else {
				resultData.setFail("更新失败");
			}
		} catch (Exception e) {
			resultData.setFail();
			logger.error("linkageWechat", "WechatUserController",
					"updateWechatUser", jsonParam, 0, "", "", e);
		}

		return resultData.toString();
	}

	/**
	 * 新增微信消息
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "addWechatMessage", method = RequestMethod.POST)
	public String saveWechatMessage(@RequestBody String jsonParam) {
		ResultData<Integer> resultData = new ResultData<>();
		try {
			WechatMessage message = JsonUtil.parseToObject(jsonParam,
					WechatMessage.class);
			// 新增微信消息
			Integer num = wechatUserService.saveWechatMessage(message);

			if (num > 0) {
				resultData.setSuccess();
			} else {
				resultData.setFail("新增微信消息失败");
			}
		} catch (Exception e) {
			resultData.setFail();
			logger.error("linkageWechat", "WechatUserController",
					"saveWechatMessage", jsonParam, 0, "", "", e);
		}

		return resultData.toString();
	}

	/**
	 * 
	 * 保存生成的验证码
	 * 
	 * @param verificationCode
	 */

	@RequestMapping(value = "addVerifyCode", method = RequestMethod.POST)
	public String saveVerifyCode(@RequestBody String jsonParam) {
		ResultData<Integer> resultData = new ResultData<>();
		try {
			VerificationCode verificationCode = JsonUtil.parseToObject(
					jsonParam, VerificationCode.class);
			//
			Integer num = wechatUserService.saveVerifyCode(verificationCode);

			if (num > 0) {
				resultData.setSuccess();
			} else {
				resultData.setFail("保存验证码失败");
			}
		} catch (Exception e) {
			resultData.setFail();
			logger.error("linkageWechat", "WechatUserController",
					"saveVerifyCode", jsonParam, 0, "", "", e);
		}

		return resultData.toString();
	}

	/**
	 * 从后台数据库获取验证码
	 * 
	 * @param mobile
	 * @param userName
	 */
	@RequestMapping(value = "/verifyCode/{phoneNumber}/{userName}", method = RequestMethod.GET)
	public String getBackVerifyCode(@PathVariable String phoneNumber,
			@PathVariable String userName) {

		ResultData<VerificationCode> resultData = new ResultData<>();
		try {
			// 参数设定
			VerificationCode verifyCode = new VerificationCode();
			verifyCode.setPhoneNumber(phoneNumber);
			verifyCode.setUserName(userName);
			// 根据phoneNumber , userName查询验证码信息
			VerificationCode verificationCode = wechatUserService
					.getBackVerifyCode(verifyCode);

			if (verificationCode != null) {
				resultData.setSuccess();
				resultData.setReturnData(verificationCode);
			} else {
				resultData.setFail("查询失败");
			}
		} catch (Exception e) {
			resultData.setFail();
			logger.error("linkageWechat", "WechatUserController",
					"getBackVerifyCode", phoneNumber + userName, 0, "", "", e);
		}

		return resultData.toString();
	}

	/**
	 * 更新验证码状态
	 * 
	 * @param wechatUser
	 */
	@RequestMapping(value = "updateVerifyCode", method = RequestMethod.PUT)
	public String updateVerificationCode(@RequestBody String jsonParam) {
		VerificationCode verificationCode = JsonUtil.parseToObject(jsonParam,
				VerificationCode.class);
		ResultData<Integer> resultData = new ResultData<>();
		Integer num = null;
        try
        {
            num = wechatUserService
            		.updateVerificationCode(verificationCode);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("linkageWechat", "WechatUserController",
                    "getByPhoneNumber", jsonParam, 0, "", "", e);
        }
		if (num > 0) {
		} else {
			resultData.setFail("更新失败");
		}
		return resultData.toString();
	}

	/**
	 * 根据手机号码查询用户信息
	 * 
	 * @param phoneNumber
	 *            手机号码
	 */
	@RequestMapping(value = "/getLinkageUser/{phoneNumber}", method = RequestMethod.GET)
	public String getByPhoneNumber(@PathVariable String phoneNumber) {

		ResultData<LinkageUser> resultData = new ResultData<>();
		try {
			// 根据手机号码查询用户信息
			LinkageUser linkageUser = wechatUserService
					.getByPhoneNumber(phoneNumber);
			// 非空判断
			if (linkageUser != null) {
				resultData.setSuccess();
				resultData.setReturnData(linkageUser);
			} else {
				resultData.setFail("查询失败");
			}
		} catch (Exception e) {
			resultData.setFail();
			logger.error("linkageWechat", "WechatUserController",
					"getByPhoneNumber", phoneNumber, 0, "", "", e);
		}

		return resultData.toString();
	}

	/**
	 * 保存用户注册信息
	 * 
	 * @param wechatId
	 *            类型编号
	 * @return wechatUser信息
	 */
	@RequestMapping(value = "saveLinkageUser", method = RequestMethod.POST)
	public String addLinkageUser(@RequestBody String jsonParam) {

		ResultData<Integer> resultData = new ResultData<>();
		try {
			LinkageUser linkageuser = JsonUtil.parseToObject(jsonParam,
					LinkageUser.class);
			// 用户注册信息
			Integer num = wechatUserService.saveLinkageUser(linkageuser);

			if (num > 0) {
				resultData.setSuccess();
			} else {
				resultData.setFail("新增失败");
			}
		} catch (Exception e) {
			resultData.setFail();
			logger.error("linkageWechat", "WechatUserController",
					"addLinkageUser", jsonParam, 0, "", "", e);
		}

		return resultData.toString();
	}

	/**
	 * 根据 用户账号、密码 查询用户信息
	 * 
	 * @param userCardNo
	 *            用户账号
	 * @param password
	 *            密码
	 */
	@RequestMapping(value = "/queryLinkageUser/{userCardNo}/{password}", method = RequestMethod.GET)
	public String queryLinkageUser(@PathVariable String userCardNo,
			@PathVariable String password) {

		ResultData<LinkageUser> resultData = new ResultData<>();
		try {
			// 参数设定
			LinkageUser linkageUser = new LinkageUser();
			linkageUser.setMobilePhone(userCardNo);
			linkageUser.setUserPassword(password);

			// 根据mobilePhone , userPassword查询用户信息
			LinkageUser info = wechatUserService.queryLinkageUser(linkageUser);

			if (info != null) {
				resultData.setSuccess();
				resultData.setReturnData(info);
			} else {
				resultData.setFail("查询失败");
			}
		} catch (Exception e) {
			resultData.setFail();
			logger.error("linkageWechat", "WechatUserController",
					"queryLinkageUser", userCardNo + password, 0, "", "", e);
		}

		return resultData.toString();
	}

	/**
	 * 重置密码操作
	 * 
	 * @param jsonParam
	 * @return
	 */
	@RequestMapping(value = "updatePassword", method = RequestMethod.PUT)
	public String updatePassword(@RequestBody String jsonParam) {
		ResultData<Integer> resultData = new ResultData<>();
		try {
			LinkageUser infoUser = JsonUtil.parseToObject(jsonParam,
					LinkageUser.class);
			// 重置密码操作
			Integer num = wechatUserService.updatePassword(infoUser);

			if (num > 0) {
				resultData.setSuccess();
			} else {
				resultData.setFail("重置密码失败");
			}
		} catch (Exception e) {
			resultData.setFail();
			logger.error("linkageWechat", "WechatUserController",
					"updatePassword", jsonParam, 0, "", "", e);
		}

		return resultData.toString();
	}

	/**
	 * 用户注销操作
	 * 
	 * @param jsonParam
	 * @return
	 */
	@RequestMapping(value = "deleteLinkageUser", method = RequestMethod.PUT)
	public String deleteLinkageUser(@RequestBody String jsonParam) {
		ResultData<Integer> resultData = new ResultData<>();
		try {
			LinkageUser infoUser = JsonUtil.parseToObject(jsonParam,
					LinkageUser.class);
			// 用户注销操作
			Integer num = wechatUserService.deleteLinkageUser(infoUser);

			if (num > 0) {
				resultData.setSuccess();
			} else {
				resultData.setFail("用户注销失败");
			}
		} catch (Exception e) {
			resultData.setFail();
			logger.error("linkageWechat", "WechatUserController",
					"deleteLinkageUser", jsonParam, 0, "", "", e);
		}

		return resultData.toString();
	}

}
