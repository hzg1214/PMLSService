package cn.com.eju.pmls.remittanceTrack.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.pmls.remittanceTrack.dto.RemittanceTrackDto;
import cn.com.eju.pmls.remittanceTrack.model.RemittanceTrack;

public interface RemittanceTrackMapper {
	
	RemittanceTrack selectByPrimaryKey(Integer id);
	
	int deleteByPrimaryKey(Integer id);
	
	int insert(RemittanceTrack record);
	
	int updateByPrimaryKeySelective(RemittanceTrack record);

    int updateByPrimaryKey(RemittanceTrack record);
    //获取表头
    RemittanceTrackDto getRemittanceTitle(Map<String,Object> resMap);
    //获取数据
    List<RemittanceTrackDto> queryRemitanceTrackList(Map<String,Object> resMap);
    //查询合作方和sortNo版本号是否有数据
    RemittanceTrack queryRemittanceTrackBySortNo(Map<String,Object> map);
    
    RemittanceTrack queryUpRemittanceTrackBySortNo(Map<String,Object> map);
    //导入
    int updateImport(RemittanceTrackDto remittanceTrackDto);
    
    int insertImport(RemittanceTrackDto remittanceTrackDto);
    
    //插入上一版本数据
    int insertOldRemittanceTrack(RemittanceTrack record);
}
