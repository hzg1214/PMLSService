package cn.com.eju.deal.company.base;

import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;

import java.util.Map;
import java.util.Objects;

/**
 * Created by Sky on 2016/3/24.
 * 公司服务模板
 */
public abstract class CompanyServiceTemplate<requestT, resultDataT> implements ICompanyServer<requestT, resultDataT> {


    /**
     * 执行操作
     *
     * @param requestModel 请求参数
     * @return 响应数据
     */
    @Override
    public ResultData<resultDataT> execute(requestT requestModel) throws Exception{

        ResultData<resultDataT> checkResult = checkRequest(requestModel);

        if (checkResult == null || !Objects.equals(checkResult.getReturnCode(), "200"))
            return checkResult;

        resultDataT coreResult = coreExecute(requestModel);

        ResultData<resultDataT> resultData = new ResultData<>();
        
        resultData.setReturnData(coreResult);
        setResultState(resultData);

        return resultData;
    }

    /**
     * 验证请求信息
     *
     * @param request 请求参数
     * @return 验证结果
     * @throws Exception 
     */
    protected abstract ResultData<resultDataT> checkRequest(requestT request);

    /**
     * 执行核心逻辑
     *
     * @param request 请求参数
     * @return 核心模块执行结果
     * @throws Exception 
     */
    protected abstract resultDataT coreExecute(requestT request) throws Exception;

    /**
     * 设置返回结果
     *
     * @param resultData 返回结果值
     */
    protected abstract void setResultState(ResultData<resultDataT> resultData);


}
