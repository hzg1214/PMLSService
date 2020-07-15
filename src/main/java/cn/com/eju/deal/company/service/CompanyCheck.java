package cn.com.eju.deal.company.service;

import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.company.dao.CompanyStoreMapper;
import cn.com.eju.deal.contacts.dao.ContactsMapper;
import cn.com.eju.deal.core.support.AppMsg;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.company.CompanyDto;

import javax.annotation.Resource;

import java.util.Objects;

/**
 * Created by Sky on 2016/3/28.
 * 公司通用底层
 */
public class CompanyCheck {

    @Resource
    public CompanyMapper companyMapper;

    @Resource
    public ContactsMapper contactsMapper;

    @Resource
    public CompanyStoreMapper companyStoreMapper;


    /**
     * 验证更新公司信息 DTO
     *
     * @return
     */
    public static ResultData<Boolean> checkUpdateCompanyDto(CompanyDto companyDto){
        ResultData<CompanyDto> addResultCheck = checkAddCompanyDto(companyDto);

        ResultData<Boolean> resultData = new ResultData<>();
        resultData.setSuccess();

        if (addResultCheck.getReturnCode() != "200") {
            resultData.setReturnCode(addResultCheck.getReturnCode());
            resultData.setReturnMsg(addResultCheck.getReturnMsg());
            return resultData;
        }


        if (companyDto.getId() < 1) {
            resultData.setFail(AppMsg.getString("COMPANY.ID_IS_EMPTY"));
            return resultData;
        }

        return resultData;
    }


    /**
     * 验证添加公司信息dto
     *
     * @param dto 公司内容请求dto
     * @return 验证结果
     */
    public static ResultData<CompanyDto> checkAddCompanyDto(CompanyDto dto){

        ResultData<CompanyDto> resultData = new ResultData<>();
        resultData.setSuccess();

        if (dto.getStoreList().isEmpty()) {
            resultData.setFail(AppMsg.getString("COMPANY.STORE_IS_EMPTY"));
            return resultData;
        }

        if (dto.getContactList().isEmpty()) {
            resultData.setFail(AppMsg.getString("COMPANY.CONTACT_IS_EMPTY"));
            return resultData;
        }

        resultData = checkCompanyDto(dto);
        if (!Objects.equals(resultData.getReturnCode(), "200"))
            return resultData;

        return resultData;
    }

    /**
     * 验证公司DTO
     *
     * @param dto 公司dto对象
     * @return 验证结果
     */
    public static ResultData<CompanyDto> checkCompanyDto(CompanyDto dto){
        ResultData<CompanyDto> resultData = new ResultData<>();
        resultData.setSuccess();

        CompanyDto companyDto = dto;

        if (!StringUtil.isNotEmpty(companyDto.getCompanyName())) {
            resultData.setFail(AppMsg.getString("COMPANY.NAME_IS_EMPTY"));
            return resultData;
        }
//        if (!StringUtil.isNotEmpty(companyDto.getStoreName())) {
//            resultData.setFail(AppMsg.getString("COMPANY.STORE_NAME_IS_EMPTY"));
//            return resultData;
//        }

        return resultData;
    }
}
