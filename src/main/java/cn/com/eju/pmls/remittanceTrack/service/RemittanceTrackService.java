package cn.com.eju.pmls.remittanceTrack.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.performswitch.dao.PmlsPerformSwitchWeekMapper;
import cn.com.eju.pmls.performswitch.dao.PmlsWeekInfoMapper;
import cn.com.eju.pmls.performswitch.dto.PmlsPerformSwitchWeekDto;
import cn.com.eju.pmls.performswitch.model.PmlsWeekInfo;
import cn.com.eju.pmls.remittanceTrack.dao.RemittanceTrackMapper;
import cn.com.eju.pmls.remittanceTrack.dto.RemittanceTrackDto;
import cn.com.eju.pmls.remittanceTrack.dto.RemittanceTrackImportDto;
import cn.com.eju.pmls.remittanceTrack.model.RemittanceTrack;

@Service("remittanceTrackService")
public class RemittanceTrackService extends BaseService {
	
    @Resource
    private RemittanceTrackMapper remittanceTrackMapper;
    
    @Resource
    private PmlsWeekInfoMapper pmlsWeekInfoMapper;
    
    @Resource
    private PmlsPerformSwitchWeekMapper pmlsPerformSwitchWeekMapper;
    
    /**
     * desc:回款跟踪数据初始化-年月-周
     * 2020年4月7日
     */
    public ResultData<List<PmlsWeekInfo>> getWeeks(Map<String, Object> map) throws Exception {
        ResultData<List<PmlsWeekInfo>> resultData = new ResultData();
        List<PmlsWeekInfo> list = pmlsWeekInfoMapper.getWeeks(map);
        resultData.setReturnData(list);
        return resultData;
    }
    
    /**
     * desc:列表
     * 2020年4月7日
     */
    public ResultData<List<RemittanceTrackDto>> queryRemitanceTrackList(Map<String, Object> queryParam) {
        ResultData<List<RemittanceTrackDto>> resultData = new ResultData<>();
        List<RemittanceTrackDto> list = remittanceTrackMapper.queryRemitanceTrackList(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }
    
    /**
     * desc:获取回款跟踪数据表头
     * 2020年4月8日
     */
    public ResultData getTitle(Map<String, Object> queryParam) {
    	ResultData<RemittanceTrackDto> resultData = new ResultData<>();
    	RemittanceTrackDto remittanceTrackDto= remittanceTrackMapper.getRemittanceTitle(queryParam);
    	resultData.setReturnData(remittanceTrackDto);
    	return resultData;
    }
    
    /**
     * desc:导入
     * 2020年4月16日
     */
    public ResultData<String> insertImput(RemittanceTrackImportDto dto) throws ParseException {
        ResultData resultData = new ResultData();
        List<RemittanceTrackDto> remittanceTrackDtoList = dto.getRemittanceTrackDtoList();
        int count = 0;
        Map<String,Object> map = new HashMap<>();
        for (RemittanceTrackDto remittanceTrackDto : remittanceTrackDtoList) {
			Integer sortNo = remittanceTrackDto.getSortNo();
			String partnerName = remittanceTrackDto.getPartnerName();
			String projectNo = remittanceTrackDto.getProjectNo();
			String trackType = remittanceTrackDto.getTrackType();
			map.put("sortNo", sortNo);
			map.put("partnerName", partnerName);
			map.put("projectNo", projectNo);
			map.put("trackType", trackType);
			
			RemittanceTrack oldRemittanceTrack = remittanceTrackMapper.queryUpRemittanceTrackBySortNo(map);
			//更新
			if(oldRemittanceTrack!=null) {
				Integer oldSortNo = oldRemittanceTrack.getSortNo();
				//复制上一版本
				if(oldSortNo!=sortNo) {
					int insertCount = remittanceTrackMapper.insertOldRemittanceTrack(oldRemittanceTrack);
				}
				remittanceTrackDto.setId(oldRemittanceTrack.getId());
				remittanceTrackMapper.updateImport(remittanceTrackDto);
			}else {//插入
				remittanceTrackMapper.insertImport(remittanceTrackDto);
			}
		}
        resultData.setReturnData(count);
        return resultData;
    }
    
    
    /**
     * desc:导入时查询关账日期
     * 2020年4月7日
     */
    public ResultData<PmlsPerformSwitchWeekDto> querySwitchWeek(Map<String, Object> queryParam) {
        ResultData<PmlsPerformSwitchWeekDto> resultData = new ResultData<>();
        PmlsPerformSwitchWeekDto hisDto = pmlsPerformSwitchWeekMapper.getPmlsPerformSwitchWeekSelective(queryParam);
        resultData.setReturnData(hisDto);
        return resultData;
    }
    
    public ResultData<PmlsWeekInfo> querySwitchFinishWeek(Map<String, Object> queryParam) {
    	ResultData<PmlsWeekInfo> resultData = new ResultData<>();
    	PmlsWeekInfo hisDto = pmlsWeekInfoMapper.getWeekInfoBySelective(queryParam);
    	resultData.setReturnData(hisDto);
    	return resultData;
    }
    
    public Integer getNums(Map<String, Object> map) throws Exception {
        List<PmlsWeekInfo> list = pmlsWeekInfoMapper.getWeeks(map);
        Integer size = 0;
        if(!CollectionUtils.isEmpty(list)) {
        	size=list.size();
        }
        return size;
    }
}
