package cn.com.eju.deal.accountproject.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.accountproject.dao.AccountProjectMapper;
import cn.com.eju.deal.accountproject.model.AccountProject;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.dao.CityMapper;
import cn.com.eju.deal.base.linkage.model.City;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.accountproject.AccountProjectDto;
import cn.com.eju.deal.dto.accountproject.AccountProjectList;
import cn.com.eju.deal.staffMaintain.dao.YFCenterUserMapper;
import cn.com.eju.deal.user.dao.UserMapper;

@Service("accountProjectService")
public class AccountProjectService {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private AccountProjectMapper accountProjectMapper;
    @Resource
    private UserMapper userMapper;

    @Resource
    private CityMapper cityMapper;
    
    @Resource
    private YFCenterUserMapper yfCenterUserMapper;

    /**
     * desc:列表
     * 2019年7月26日
     * author:zhenggang.Huang
     */
    public ResultData<List<Map<String, Object>>> queryList(Map<String, Object> queryParam)
            throws Exception {
        ResultData<List<Map<String, Object> >> resultData = new ResultData<>();
        Map<String,Object> reqMap = new HashMap<>();
        reqMap.put("userId", queryParam.get("userId"));
        List<Map<String, Object> > yfCenterUserDtos = accountProjectMapper.queryList(queryParam);

        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(yfCenterUserDtos);

        return resultData;
    }

    /**
     * desc:编辑-根据id查询
     * 2019年7月26日
     * author:zhenggang.Huang
     */
    public ResultData<AccountProject> getById(int id)
            throws Exception {
        //构建返回
        ResultData<AccountProject> resultData = new ResultData<>();
        //查询详情
        AccountProject mCenterUser = accountProjectMapper.getById(id);

        resultData.setReturnData(mCenterUser);

        return resultData;
    }

    /**
     * desc:新增保存
     * 2019年7月26日
     * author:zhenggang.Huang
     */
    public ResultData<AccountProjectDto> save(AccountProjectDto accountProjectDto)
            throws Exception {

        //构建返回
        ResultData<AccountProjectDto> resultData = new ResultData<AccountProjectDto>();
        
        //保存时判断该城市下是否已绑定该核算主体
        String msg = this.checkSaveWithAccountProjectNo(accountProjectDto);
        if (msg !="" && null != msg) {
        	resultData.setFail(msg);
        	resultData.setReturnCode("201");
        	return resultData;
        }
        List<String> accountProjectNos = accountProjectDto.getAccountProjectNos();
        AccountProject accountProject = new AccountProject();
    	accountProject.setCityNo(accountProjectDto.getCityNo());
	    accountProject.setUserIdCreate(accountProjectDto.getUserIdCreate());
	    accountProject.setDateCreate(new Date());
	    accountProject.setDateUpdate(new Date());
        for (String accountPorjectNo : accountProjectNos) {
        	accountProject = getAccountProject(accountPorjectNo,accountProject);
        	int count = accountProjectMapper.insert(accountProject);
		}
        return resultData;
    }

    /**
     * desc:组装entity
     * 2019年7月28日
     * author:zhenggang.Huang
     */
    private AccountProject getAccountProject(
    		String accountPorjectNo,AccountProject accountProject) {
    	String accountProjectName = getAccountProject(accountPorjectNo);
    	accountProject.setAccountProject(accountProjectName);
    	accountProject.setAccountProjectNo(accountPorjectNo);
		return accountProject;
	}

    /**
     * desc:根据核算主体编号获取核算主体名字
     * 2019年7月28日
     * author:zhenggang.Huang
     */
	private String getAccountProject(String accountProjectNo) {
		Map<String,Object> param = new HashMap<>();
		param.put("accountProjectNo", accountProjectNo);
		List<AccountProject> accountProjects = null;
//		flag=1新增 ；flag=2更新
		//查询核算主体基础表
		accountProjects = accountProjectMapper.selBasAPBycondition(param);
		String accountProjectStr = "";
		if(accountProjects != null && accountProjects.size() > 0) {
			accountProjectStr = accountProjects.get(0).getAccountProject();
		}
		return accountProjectStr;
	}

	/**
     * desc:组装entity
     * 2019年7月28日
     * author:zhenggang.Huang
     */
   private List<AccountProject> getAccountProjects(AccountProjectDto accountProjectDto) {
	   List<AccountProject> accountProjects = new ArrayList<>();
	   List<String> accountProjectNos = accountProjectDto.getAccountProjectNos();
	   AccountProject accountProject = new AccountProject();
	   for (String accountProjectNo : accountProjectNos) {
		   accountProject.setCityNo(accountProjectDto.getCityNo());
		   //获取核算主体名字
//		   String accountProjectStr = getAccountProject(accountProjectDto,accountProjectNo);
		   accountProject.setAccountProject(accountProjectDto.getAccountProject());
		   accountProject.setAccountProjectNo(accountProjectDto.getAccountProjectNo());
		   accountProject.setUserIdCreate(accountProjectDto.getUserIdCreate());
		   accountProject.setDateCreate(new Date());
		   accountProject.setDateUpdate(new Date());
		
	   }
        return accountProjects;
	}

	/**
     * desc:判断该城市下是否已绑定该核算主体
     * 2019年7月28日
     * author:zhenggang.Huang
     */
	private String checkSaveWithAccountProjectNo(AccountProjectDto accountProjectDto) {
		String msg = "";
		List<String> accountProjectNos = accountProjectDto.getAccountProjectNos();
		if(accountProjectNos == null || accountProjectNos.size() == 0) {
			msg= "请选择核算主体!";
		}
		Map<String,Object> map = new HashMap<>();
		map.put("cityNo", accountProjectDto.getCityNo());
		City city = cityMapper.getCityByCityNo(accountProjectDto.getCityNo());
		//获取该城市下已绑定的核算主体
        List<AccountProject> accountProjectList = accountProjectMapper.selAPBycondition(map);

        if(accountProjectList != null && accountProjectList.size() > 0) {
        	for(AccountProject s : accountProjectList) {
        		for(String accountProjectNo : accountProjectNos) {
        			if(accountProjectNo.equals(s.getAccountProjectNo())) {//核算主体一致，已绑定
        				msg= city.getCityName()+"城市的"+s.getAccountProject()+"核算主体已创建，请勿重复提交！";
        			}
        		}
        	}
        }
        return msg;
    }

    /**
     * desc:编辑保存
     * 2019年7月26日
     * author:zhenggang.Huang
     */
    public ResultData<AccountProjectDto> update(AccountProjectDto accountProjectDto)
            throws Exception {

        //构建返回
        ResultData<AccountProjectDto> resultData = new ResultData<>();
        AccountProject accountProject = new AccountProject();
        //赋值
        BeanUtils.copyProperties(accountProjectDto, accountProject);
        //保存时判断该城市下是否已绑定该核算主体
        String msg = this.checkSaveWithAccountProjectNo(accountProjectDto);
        if (msg !="" && null != msg) {
        	resultData.setFail(msg);
        	resultData.setReturnCode("201");
        	return resultData;
        }
        List<String> accountProjectNos = accountProjectDto.getAccountProjectNos();
//    	accountProject.setCityNo(accountProjectDto.getCityNo());
//	    accountProject.setUserIdCreate(accountProjectDto.getUserIdCreate());
	    accountProject.setDateUpdate(new Date());
	    int count = -1;
        for (String accountPorjectNo : accountProjectNos) {
        	accountProject = getAccountProject(accountPorjectNo,accountProject);
        	count = accountProjectMapper.update(accountProject);
		}
        // 更新处理
        if (count <= 0) {
            resultData.setFail();
        } else {
            resultData.setReturnData(accountProjectDto);
        }
        return resultData;
    }

    /**
     * desc:逻辑删除
     * 2019年7月26日
     * author:zhenggang.Huang
     */
    public ResultData delete(AccountProjectDto accountProjectDto) throws Exception {
        ResultData<?> resultData = new ResultData<>();
        // 删除处理
        int count = accountProjectMapper.delete(accountProjectDto.getId());
        if (count < 0) {
            resultData.setFail();
        }
        return resultData;
    }

    /**
     * desc:获取新建\编辑核算主体下拉列表
     * 2019年7月26日
     * author:zhenggang.Huang
     */
    public ResultData<List<AccountProjectList>> getAccountProjectList(Map<String, Object> map) throws Exception {
        ResultData resultData = new ResultData();

        List<AccountProjectList> list = accountProjectMapper.getAccountProjectList(map);
        resultData.setReturnData(list);

        return resultData;
    }
}
