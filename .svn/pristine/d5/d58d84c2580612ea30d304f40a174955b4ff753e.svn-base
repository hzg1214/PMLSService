package cn.com.eju.deal.youFangTongBind.dao;

import cn.com.eju.deal.keFuWj.model.KefuWjCitymapping;
import cn.com.eju.deal.youFangTongBind.dto.YouFangTongBindDto;
import cn.com.eju.deal.youFangTongBind.model.YftEjuBind;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface YouFangTongBindMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(YftEjuBind record);

    int insertSelective(YftEjuBind record);

    YftEjuBind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YftEjuBind record);

    int updateByPrimaryKey(YftEjuBind record);

    YouFangTongBindDto queryBindInfo(YouFangTongBindDto dto);

    Integer insertBindInfo(YouFangTongBindDto dto);

    List<YouFangTongBindDto> getBindList(Map<String, Object> queryParam);

    List<YouFangTongBindDto> getBindLogList(Map<String, Object> queryParam);

    void unBindById(Integer id);

    List<YouFangTongBindDto> queryYFTBind(Map<String, Object> map);
}