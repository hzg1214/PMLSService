package cn.com.eju.deal.Report.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.Report.dao.TempletMapper;
import cn.com.eju.deal.Report.model.Templet;


@Service("templetService")
public class TempletServiceImpl implements ITempletService {

	@Resource
	private TempletMapper templetMapper;
	
    //查询列表
    @Override
    public List<Templet> queryList(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return templetMapper.queryList(map);
    }

	@Override
	public int insert(Templet templet) {
		// TODO Auto-generated method stub
		return templetMapper.insert(templet);
	}

	@Override
	public Integer update(Templet templet) {
	    // TODO Auto-generated method stub
	    return templetMapper.update(templet);
    }
    
    @Override
    public Templet getByName(String templetName) {
        return templetMapper.getByName(templetName);
    }

	@Override
	public Integer updateTemplet(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return templetMapper.updateTemplet(map);
	}
}
