package cn.com.eju.deal.scene.commission.dao;

import cn.com.eju.deal.scene.commission.model.LnkYjImportspecialuser;

public interface LnkYjImportspecialuserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LnkYjImportspecialuser record);

    int insertSelective(LnkYjImportspecialuser record);

    LnkYjImportspecialuser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LnkYjImportspecialuser record);

    int updateByPrimaryKey(LnkYjImportspecialuser record);

    Integer checkUserImport(String userCode);
}