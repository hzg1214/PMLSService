package cn.com.eju.deal.company.service.concrete;

import cn.com.eju.deal.base.code.dao.CommonCodeMapper;
import cn.com.eju.deal.common.base.ConvertTo;
import cn.com.eju.deal.common.base.MapperFactory;
import cn.com.eju.deal.company.base.CompanyServiceTemplate;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.dto.company.CompanyDto;
import cn.com.eju.deal.user.api.impl.UserAPIImpl;
import cn.com.eju.deal.user.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Sky on 2016/3/25.
 * 公司列表查询
 */
public class CompanyListConcrete extends CompanyServiceTemplate<Map<?, ?>, List<CompanyDto>> {

    private CompanyMapper companyMapper = MapperFactory.getMapper("companyMapper", CompanyMapper.class);
    private UserAPIImpl userAPIImpl=MapperFactory.getService("userAPI", UserAPIImpl.class);
    private CommonCodeMapper commonCodeMapper = MapperFactory.getMapper("commonCodeMapper",CommonCodeMapper.class);

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
        
    //List<Company> companyList = companyMapper.queryList(request);
	List<Company> companyList = companyMapper.queryListSearsh(request);
        List<CompanyDto> companyDtoList = new ArrayList<>();

        for (Company item : companyList) {
            Integer userCreateId = item.getUserCreate();
            String userCreateName = this.getNameByUserId(userCreateId);
            CompanyDto companyDto = ConvertTo.CompanyDto(item);
            companyDto.setUserCreateName(userCreateName);

            //业务类型
            String bizType = companyDto.getBizType();
            if(bizType != null&& !bizType.equals("")){
                String bizTypeNm = null;
                if(bizType.contains("|")){
                    String[] bizTypeArr = bizType.split("\\|");
                    bizTypeNm = commonCodeMapper.queryByDicCode(bizTypeArr[0]).getDicValue();
                    bizTypeNm += "、" + commonCodeMapper.queryByDicCode(bizTypeArr[1]).getDicValue();

                }else{
                    bizTypeNm = commonCodeMapper.queryByDicCode(bizType).getDicValue();
                }
                companyDto.setBizTypeNm(bizTypeNm);
            }

            companyDtoList.add(companyDto);
        }

        totalCount = (String) request.get(QueryConst.TOTAL_COUNT);
        return companyDtoList;
    }

    @Override
    protected void setResultState(ResultData<List<CompanyDto>> resultData) {
        resultData.setTotalCount(totalCount);
        resultData.setSuccess();
    }
    
    /** 
     * @Title: getNameByUserId 
     * @Description: 跟进用户编号获取用户姓名
     * @param userId
     * @return     
     */
     private String getNameByUserId(Integer userId){
         String userName="";
         ResultData<User> userData=this.userAPIImpl.getUserById(userId);
         if(userData.getReturnCode()==ReturnCode.SUCCESS){
             userName=userData.getReturnData().getUserName();
         }
         return userName;
     }

}
