package cn.com.eju.deal.store.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.store.model.StoreReceiveDtl;

public interface StoreReceiveDtlMapper extends IDao<StoreReceiveDtl>{
    int deleteByPrimaryKey(Integer id);

    int insert(StoreReceiveDtl record);

    int insertSelective(StoreReceiveDtl record);

    StoreReceiveDtl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreReceiveDtl record);

    int updateByPrimaryKey(StoreReceiveDtl record);

    List<StoreReceiveDtl> getListByParentId(Map map);

    int batchUpdateDtl(StoreReceiveDtl updateDtl);
}