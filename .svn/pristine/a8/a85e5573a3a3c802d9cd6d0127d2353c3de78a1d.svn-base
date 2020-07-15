package cn.com.eju.pmls.developer.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.pmls.developer.dto.DeveloperDto;

public interface DeveloperMapper {
    //新增开发商
    int addDeveloper(DeveloperDto dto);
    //删除开发商
    int deleteDeveloper(DeveloperDto dto);
    //修改开发商
    int updateDeveloper(DeveloperDto dto);
    //跟据Id获取开发商信息
    DeveloperDto getDeveloperInfo(DeveloperDto dto);
    //跟据Id获取开发商信息
    DeveloperDto getDeveloperInfo2(DeveloperDto dto);
    //获取开发商列表
    List<DeveloperDto> getDeveloperList(Map<String, Object> queryParam);
    //新增开发商城市关系表
    int addDeveloperReleaseCity(DeveloperDto dto);
    //判断开发商名称是否存在
    int getDeveloperCountByName(DeveloperDto dto);
    //是否大客户
    Map<String, Object> selBigCustomerByName(Map<String, Object> queryParam);
    //是否垫佣
    Map<String, Object> selMattressNailByName(Map<String, Object> queryParam);

    DeveloperDto getDeveloperInfoById(Integer id);
}
