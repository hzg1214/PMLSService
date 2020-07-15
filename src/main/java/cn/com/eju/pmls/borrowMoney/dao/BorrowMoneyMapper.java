package cn.com.eju.pmls.borrowMoney.dao;

import cn.com.eju.pmls.borrowMoney.model.BorrowMoneyDetailDto;
import cn.com.eju.pmls.borrowMoney.model.BorrowMoneyDto;
import cn.com.eju.pmls.borrowMoney.model.HkPlanDto;
import cn.com.eju.pmls.borrowMoney.model.HkPlanInterestDto;
import cn.com.eju.pmls.channelBusiness.model.BusinessManagerDto;
import cn.com.eju.pmls.channelBusiness.model.JsStatementDto;

import java.util.List;
import java.util.Map;

public interface BorrowMoneyMapper {
    List<BorrowMoneyDto> getBorrowMoneyList(Map<String, Object> queryParam);
    List<BorrowMoneyDetailDto> getBorrowMoneyDetailList(Map<String, Object> queryParam);
    List<BorrowMoneyDto> getBorrowMoneyProgressList(Map<String, Object> queryParam);
    BorrowMoneyDto getBorrowMoneyInfo(BorrowMoneyDto dto);

    List<HkPlanDto> getHkPlanList(Map<String, Object> queryParam);
    List<HkPlanInterestDto> getHkPlanInterestList(Map<String, Object> queryParam);
    HkPlanDto getHkPlanInfo(HkPlanDto dto);
    int updateHkPlan(HkPlanDto dto);
}
