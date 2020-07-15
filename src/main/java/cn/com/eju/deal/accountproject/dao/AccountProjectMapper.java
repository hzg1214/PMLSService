package cn.com.eju.deal.accountproject.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.accountproject.model.AccountProject;
import cn.com.eju.deal.dto.accountproject.AccountProjectList;

/**
 * desc:核算主体维护
 * @author :zhenggang.Huang
 * @date   :2019年7月26日
 */
public interface AccountProjectMapper {

    int insert(AccountProject record);
    
    //批量插入
    int insertBatch(List<AccountProject> insertList) throws Exception;

    int update(AccountProject record);

    int delete(Integer id);

    AccountProject getById(Integer id);
//    查询核算主体基础表
    List<AccountProject> selBasAPBycondition(Map<String,Object> param);
    //查询核算主体维护表
    List<AccountProject> selAPBycondition(Map<String,Object> param);

    List<Map<String,Object>> queryList(Map<?, ?> param);
    
    //编辑-核算主体下拉列表
    List<AccountProjectList> getAccountProjectList(Map<?, ?> param);

}