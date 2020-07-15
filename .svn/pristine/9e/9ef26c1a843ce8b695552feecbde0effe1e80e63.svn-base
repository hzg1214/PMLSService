package cn.com.eju.deal.company.service.concrete;

import cn.com.eju.deal.common.base.ConvertTo;
import cn.com.eju.deal.common.base.MapperFactory;
import cn.com.eju.deal.company.base.CompanyServiceTemplate;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.company.CompanyDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**   
* 公司列表查询 -给合同选取公司使用
* @author (li_xiaodong)
* @date 2016年4月22日 上午9:41:40
*/
public class CompanyOwnListConcrete extends CompanyServiceTemplate<Map<?, ?>, List<CompanyDto>> {

    private CompanyMapper companyMapper = MapperFactory.getMapper("companyMapper", CompanyMapper.class);

    /**
     * 总数量
     */
    private String totalCount;

    @Override
    protected ResultData<List<CompanyDto>> checkRequest(Map<?, ?> request) {
        ResultData<List<CompanyDto>> result = new ResultData<>();
        
        result.setSuccess();
        return result;
    }

    @Override
    protected List<CompanyDto> coreExecute(Map<?, ?> request) throws Exception{
        
        List<Company> companyList = companyMapper.queryOwnList(request);
        List<CompanyDto> companyDtoList = new ArrayList<>();

        for (Company item : companyList) {
            companyDtoList.add(ConvertTo.CompanyDto(item));
        }

        totalCount = (String) request.get(QueryConst.TOTAL_COUNT);
        return companyDtoList;
    }

    @Override
    protected void setResultState(ResultData<List<CompanyDto>> resultData) {
        resultData.setTotalCount(totalCount);
        resultData.setSuccess();
    }

}
