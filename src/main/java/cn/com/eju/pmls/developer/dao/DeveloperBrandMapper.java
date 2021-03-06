package cn.com.eju.pmls.developer.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.pmls.developer.dto.DeveloperBrandDto;
import cn.com.eju.pmls.developer.model.Developer;
import cn.com.eju.pmls.developer.model.PmlsBcMattressNaillog;


public interface DeveloperBrandMapper {
    //新增合作方品牌
    int addDeveloperBrand(DeveloperBrandDto dto);
    //删除合作方品牌
    int deleteDeveloperBrand(DeveloperBrandDto dto);
    //修改合作方品牌
    int updateDeveloperBrand(DeveloperBrandDto dto);
    //获取合作方品牌信息
    DeveloperBrandDto getDeveloperBrandInfo(Map<String, Object> queryParam);
    //获取合作方品牌列表
    List<DeveloperBrandDto> getDeveloperBrandList(Map<String, Object> queryParam);
    //校验合作方品牌名称是否存在
    int checkDeveloperInfo(DeveloperBrandDto dto);
    //校验合作方品牌是否有对应的合作方，如果有不让删除
    int checkDeveloper(DeveloperBrandDto dto);
    
    int updateDeveloperBrandById(DeveloperBrandDto dto);
    //修改开发商品牌20200401 修改节点一下所有子节点
    int updateDeveloperBrandByOrgId(DeveloperBrandDto dto);
    //是否可以修改合作方品牌信息
    int updateCheck(DeveloperBrandDto dto);
    
    //查询选择该合作方品牌的草稿项目-大客户
    List<Map<String,Object>> getEstateBigCustomerListByBrandId(String orgId);
    //查询选择该合作方品牌的草稿项目-垫佣甲方
    List<Map<String,Object>> getEstateMattressNailListByBrandId(String orgId);
    //更新项目大客户和垫佣甲方
    int updateEstateByBrand(Map<String, Object> queryParam);
    //新增日志
    int addBcMattressNailLog(PmlsBcMattressNaillog pmlsBcMattressNaillog);
    //查询绑定该品牌的合作方
    List<Developer> selDeveloperByBrandId(String orgId);

    //获取合作方品牌列表
    List<DeveloperBrandDto> getDeveloperBrandListByPage(Map<String, Object> queryParam);
    
    //更新合作方
    int updateDeveloperByBrandId(Map<String, Object> queryParam);
}
