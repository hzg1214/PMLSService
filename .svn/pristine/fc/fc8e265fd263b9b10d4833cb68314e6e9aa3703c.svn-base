package cn.com.eju.deal.linkageWechat.dao;

import cn.com.eju.deal.linkageWechat.model.LinkageUser;
import cn.com.eju.deal.linkageWechat.model.VerificationCode;

/**   
* 用户注册信息接口
* @author sunmm
* @date 2016年5月31日 上午11:11:55
*/
public interface LinkageUserMapper {
   
	/** 
     * 根据手机号码查询用户信息
     * @param phoneNumber
     * @return
     */
	LinkageUser getByPhoneNumber(String phoneNumber) throws Exception;
	
	/** 
     * 根据 用户账号、密码 查询用户信息 
     * @param mobilePhone 手机号码
     * @param userPassword 密码
     * @return
     */
	LinkageUser queryLinkageUser(LinkageUser linkageUser) throws Exception;
    
    /**
     * 新增用户注册信息
     * @param linkageUser
     * @return
     */
    int saveWechatUser(LinkageUser linkageUser) throws Exception;
	
    /** 
     * 重置密码操作
     * @param linkageUser
     * @return
     */
     int updatePassword(LinkageUser linkageUser) throws Exception;
     
     /** 
    * 用户注销操作
    * @param linkageUser
    * @return
    */
    int deleteLinkageUser(LinkageUser linkageUser) throws Exception;
     
}