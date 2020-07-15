package cn.com.eju.deal.NoSigned.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.NoSigned.dao.NoSignDetailMapper;
import cn.com.eju.deal.NoSigned.model.NosignDetail;

@Service("noSignDetailService")
public class NoSignDetailServiceImpl {
	@Resource
	private NoSignDetailMapper noSignDetailMapper;

	/**
	 * 查询未签门店信息
	 * @param map
	 * @return
	 */
	public List<NosignDetail> queryNoSignDetailList(Map<String, Object> map) {
		List<NosignDetail> list =null;
		try {
			list = noSignDetailMapper.getById(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
