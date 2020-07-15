package cn.com.eju.deal.oa.dao;

import org.apache.ibatis.annotations.Param;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.oa.model.OaOperator;

/**   
* DAO
* @author li_xiaodong
* @date 2016年2月4日 上午9:52:36
*/
public interface OaOperatorMapper extends IDao<OaOperator>
{
    
    OaOperator getByUserCode(@Param(value="operCode") String operCode) throws Exception;
}