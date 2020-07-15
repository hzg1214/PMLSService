package cn.com.eju.deal.houseLinkage.report.dao;

import cn.com.eju.deal.houseLinkage.report.model.SendContractFangyouLog;

public interface SendContractFangyouLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SendContractFangyouLog record);

    int insertSelective(SendContractFangyouLog record);

    SendContractFangyouLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SendContractFangyouLog record);

    int updateByPrimaryKey(SendContractFangyouLog record);
}