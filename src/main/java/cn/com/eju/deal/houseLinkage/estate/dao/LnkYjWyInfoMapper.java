package cn.com.eju.deal.houseLinkage.estate.dao;

import cn.com.eju.deal.houseLinkage.estate.model.LnkYjWyInfo;

import java.util.List;
import java.util.Map;

public interface LnkYjWyInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LnkYjWyInfo record);

    int insertSelective(LnkYjWyInfo record);

    LnkYjWyInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LnkYjWyInfo record);

    int updateByPrimaryKey(LnkYjWyInfo record);

    List<LnkYjWyInfo> getWyInfoList();
    
    LnkYjWyInfo getWyTpyeNameByCode(Map<String,Object> map);
}