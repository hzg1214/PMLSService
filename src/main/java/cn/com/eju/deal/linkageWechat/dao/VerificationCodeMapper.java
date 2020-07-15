package cn.com.eju.deal.linkageWechat.dao;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.linkageWechat.model.VerificationCode;

/**   
* 验证码Dao接口
* @author mimi.sun
* @date 2015年12月22日 下午4:04:58
*/
public interface VerificationCodeMapper extends IDao<VerificationCode>
{
    
        /** 
         * 根据ID查询验证码信息
         * @param id
         * @return
         */
        VerificationCode getByID(Integer id) throws Exception;
    
        /** 
        * 新增一条验证码信息
        * @param record
        * @return
        */
        int saveVerificationCode(VerificationCode record) throws Exception;
    
        /** 
         * 根据phoneNumber , userName查询验证码信息
         * @param verificationCode 验证码model
         * @return
         */
        VerificationCode getBackVerifyCode(VerificationCode verificationCode) throws Exception;
        
        /** 
         * 更新验证码状态
         * @param verificationCode
         * @return
         */
         int updateVerificationCode(VerificationCode verificationCode) throws Exception;

}