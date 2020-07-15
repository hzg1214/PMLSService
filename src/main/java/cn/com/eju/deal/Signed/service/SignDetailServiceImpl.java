package cn.com.eju.deal.Signed.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.Report.model.UserCenterAuthDto;
import cn.com.eju.deal.Signed.dao.SignDetailMapper;
import cn.com.eju.deal.Signed.model.SignDetail;

@Service("signDetailService")
public class SignDetailServiceImpl {
	@Resource
	private SignDetailMapper signDetailMapper;

	public List<SignDetail> querySignDetailList(Map<String, Object> map) {
		List<SignDetail> list =null;
		try {
			list = signDetailMapper.getById(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 *  根据城市ID对应中心
	 * @param map
	 * @return
	 */
	public  List<UserCenterAuthDto> getCenterGroupName(Map<String, Object> map)
	{
		List<UserCenterAuthDto> list =null;
		try {
			list = signDetailMapper.getCenterGroupName(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
