/**
 * Copyright (c) 2017, www.ehousechina.com.
 * Project Name:PMLSService
 * File Name:IFeebackService.java
 * Package Name:cn.com.eju.deal.feeback.service
 * Date:2017年9月19日下午3:52:09
 */
package cn.com.eju.deal.feeback.service;

import cn.com.eju.deal.feeback.model.Feeback;

/**
 * ClassName: IFeebackService <br/>
 * Description: 意见反馈 <br/>
 * 
 * @author yinkun
 * @date: 2017年9月19日 下午3:52:09 <br/>
 * @version V1.0
 */
public interface IFeebackService {

    /**
     * 【描述】: 新增意见反馈
     *
     * @author yinkun
     * @date: 2017年9月19日 下午3:52:36
     * @param feeback
     * @return
     */
    int save(Feeback feeback);
}

