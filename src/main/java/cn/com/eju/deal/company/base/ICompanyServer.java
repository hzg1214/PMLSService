package cn.com.eju.deal.company.base;

import cn.com.eju.deal.core.support.ResultData;

/**
 * Created by Sky on 2016/3/24.
 * 公司服务模块规范
 */
public interface ICompanyServer<requestT, resultDataT> {

    /**
     * 执行操作
     *
     * @param requestModel 请求的参数
     * @return 响应参数
     * @throws Exception 
     */
    ResultData<resultDataT> execute(requestT requestModel) throws Exception;

}
