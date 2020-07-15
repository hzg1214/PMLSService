package cn.com.eju.deal.keFuWj.dao;

import cn.com.eju.deal.keFuWj.model.KefuWjCitymapping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KefuWjCitymappingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(KefuWjCitymapping record);

    int insertSelective(KefuWjCitymapping record);

    KefuWjCitymapping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KefuWjCitymapping record);

    int updateByPrimaryKey(KefuWjCitymapping record);

    KefuWjCitymapping queryCityAvailable(String cityNo);

    KefuWjCitymapping queryCityIsAvailable(@Param("cityNo") String cityNo,@Param("id")Integer id);

    int queryNumByWjCode(String wjCode);

    void insertOrUpdate(KefuWjCitymapping kefuWjCitymapping);

    List<KefuWjCitymapping> getWjCheckCityList(Integer id);
}