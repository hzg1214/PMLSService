package cn.com.eju.deal.company.service.concrete;

import cn.com.eju.deal.common.base.MapperFactory;
import cn.com.eju.deal.company.base.CompanyServiceTemplate;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.company.dao.CompanyStoreMapper;
import cn.com.eju.deal.core.support.AppMsg;
import cn.com.eju.deal.core.support.ResultData;

/**
 * Created by Sky on 2016/3/25.
 * 删除客户信息
 */
public class CompanyDeleteConcrete extends CompanyServiceTemplate<Integer, Boolean> {

    /**
     * 成功状态
     */
    private boolean isSuccess;
    /**
     * 状态描述
     */
    private String statusMessage;


    private CompanyMapper companyMapper = MapperFactory.getMapper("companyMapper", CompanyMapper.class);


    private CompanyStoreMapper companyStoreMapper = MapperFactory.getMapper("companyStoreMapper", CompanyStoreMapper.class);

    @Override
    protected ResultData<Boolean> checkRequest(Integer request) {
        ResultData<Boolean> resultData = new ResultData<>();
        resultData.setSuccess();

        if (request < 1)
            resultData.setFail();

        return resultData;
    }

    @Override
    protected Boolean coreExecute(Integer request) throws Exception{
        Integer resultCount = companyMapper.deleteById(request);

        isSuccess = resultCount > 0;
        statusMessage = resultCount <= 0
                ? AppMsg.getString("COMPANY.DELETE_ERROR")
                : AppMsg.getString("COMMON.UPDATE_SUCCESS");

        if (resultCount > 0)
            companyStoreMapper.deleteCompanyStore(request);

        return isSuccess;
    }

    @Override
    protected void setResultState(ResultData<Boolean> resultData) {

        if (isSuccess)
            resultData.setSuccess();
        else
            resultData.setFail(statusMessage);

    }
}
