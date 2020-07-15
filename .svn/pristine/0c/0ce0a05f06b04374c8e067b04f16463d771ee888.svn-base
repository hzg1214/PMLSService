/**
 * Copyright (c) 2017, www.ehousechina.com.
 * Project Name:PMLSService
 * File Name:FeebackServiceImpl.java
 * Package Name:cn.com.eju.deal.feeback.service.impl
 * Date:2017年9月19日下午3:53:38
 */
package cn.com.eju.deal.feeback.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.feeback.dao.FeebackMapper;
import cn.com.eju.deal.feeback.model.Feeback;
import cn.com.eju.deal.feeback.service.IFeebackService;

/**
 * ClassName: FeebackServiceImpl <br/>
 * Description: TODO <br/>
 * 
 * @author yinkun
 * @date: 2017年9月19日 下午3:53:38 <br/>
 * @version V1.0
 */
@Service("feebackService")
public class FeebackServiceImpl implements IFeebackService {
    
    @Resource(name="feebackMapper")
    private FeebackMapper feebackMapper;

    /**
     * @see cn.com.eju.deal.feeback.service.IFeebackService#save(cn.com.eju.deal.feeback.model.Feeback)
     */
    @Override
    public int save(Feeback feeback) {
        return feebackMapper.create(feeback);
    }

}

