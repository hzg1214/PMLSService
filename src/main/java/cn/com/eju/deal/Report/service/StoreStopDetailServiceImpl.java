package cn.com.eju.deal.Report.service;

import cn.com.eju.deal.Report.dao.StoreStopDetailMapper;
import cn.com.eju.deal.Report.model.ExpandDetail;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("storeStopDetailService")
public class StoreStopDetailServiceImpl {
	@Resource
	private StoreStopDetailMapper storeStopDetailMapper;

	public List<ExpandDetail> queryStoreStopDetailList(Map<String, Object> map) {
		List<ExpandDetail> list =null;
		try {

			list = storeStopDetailMapper.searchStoreStopDetail(map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
