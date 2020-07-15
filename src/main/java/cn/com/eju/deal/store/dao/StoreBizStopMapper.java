/**
 * Copyright (c) 2017, www.ehousechina.com.
 * Project Name:PMLSService
 * File Name:StoreBizStopMapper.java
 * Package Name:cn.com.eju.deal.store.dao
 * Date:2017年9月26日下午4:13:47
 */
package cn.com.eju.deal.store.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.store.model.StoreBizStop;

/**
 * ClassName: StoreBizStopMapper <br/>
 * Description: 门店停业审核 <br/>
 * 
 * @author yinkun
 * @date: 2017年9月26日 下午4:13:47 <br/>
 * @version V1.0
 */
public interface StoreBizStopMapper extends IDao<StoreBizStop> {

    void updateStoreBusinessStatus(Map<String,Object> param);

    /**
     * 【描述】: 获取合同状态信息
     *
     * @author yinkun
     * @date: 2017年9月28日 下午3:42:10
     * @param storeBizStop
     * @return
     */
    StoreBizStop getContractStatusInfo(StoreBizStop storeBizStop);
    
    /**
     * 【描述】: 获取装修进度状态信息
     *
     * @author yinkun
     * @date: 2017年9月28日 下午3:42:31
     * @param storeBizStop
     * @return
     */
    StoreBizStop getDecorationStatusInfo(StoreBizStop storeBizStop);

    /**
     * 【描述】: 查询是否存在撤销记录
     *
     * @author yinkun
     * @date: 2017年9月29日 下午2:35:56
     * @param param
     * @return
     */
    List<Map<String, Object>> queryCancelList(Map<String, Object> param);
}

