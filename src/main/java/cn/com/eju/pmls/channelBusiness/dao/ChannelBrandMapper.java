package cn.com.eju.pmls.channelBusiness.dao;

import cn.com.eju.pmls.channelBusiness.model.ChannelBrandDto;

import java.util.List;
import java.util.Map;

public interface ChannelBrandMapper {
    //新增渠道品牌
    int addBrand(ChannelBrandDto dto);
    //校验渠道品牌名称是否存在
    int checkBrandInfo(ChannelBrandDto dto);
    //删除渠道品牌
    int deleteBrand(ChannelBrandDto dto);
    //校验渠道品牌是否有对应的渠道，如果有不让删除
    int checkBusiness(ChannelBrandDto dto);
    //修改渠道品牌
    int updateBrand(ChannelBrandDto dto);
    //获取渠道品牌信息
    ChannelBrandDto getBrandInfo(Map<String, Object> queryParam);
    //获取渠道品牌列表
    List<ChannelBrandDto> getChannelBrandList(Map<String, Object> queryParam);
}
