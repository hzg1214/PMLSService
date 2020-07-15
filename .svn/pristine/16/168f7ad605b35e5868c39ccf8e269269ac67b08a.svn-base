package cn.com.eju.pmls.borrowMoney.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.borrowMoney.dao.BorrowMoneyMapper;
import cn.com.eju.pmls.borrowMoney.model.BorrowMoneyDetailDto;
import cn.com.eju.pmls.borrowMoney.model.BorrowMoneyDto;
import cn.com.eju.pmls.borrowMoney.model.HkPlanDto;
import cn.com.eju.pmls.borrowMoney.model.HkPlanInterestDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("borrowMoneyService")
public class BorrowMoneyService  extends BaseService {
    @Resource
    private BorrowMoneyMapper borrowMoneyMapper;

    //获取借佣申请列表
    public ResultData getBorrowMoneyList(Map<String, Object> queryParam) {
        ResultData<List<BorrowMoneyDto>> resultData = new ResultData<List<BorrowMoneyDto>>();
        List<BorrowMoneyDto> list = borrowMoneyMapper.getBorrowMoneyList(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }
    //获取借佣进度列表
    public ResultData getBorrowMoneyProgressList(Map<String, Object> queryParam) {
        ResultData<List<BorrowMoneyDto>> resultData = new ResultData<List<BorrowMoneyDto>>();
        List<BorrowMoneyDto> list = borrowMoneyMapper.getBorrowMoneyProgressList(queryParam);
        resultData.setReturnData(list);
        return resultData;
    }

    //获取借佣明细列表
    public ResultData getBorrowMoneyDetailList(Map<String, Object> queryParam) {
        ResultData<List<BorrowMoneyDetailDto>> resultData = new ResultData<List<BorrowMoneyDetailDto>>();
        //借佣明细
        List<BorrowMoneyDetailDto> list = borrowMoneyMapper.getBorrowMoneyDetailList(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }


    //获取借佣详情信息
    public ResultData getBorrowMoneyInfo(BorrowMoneyDto dto) {
        ResultData<BorrowMoneyDto> resultData = new ResultData<BorrowMoneyDto>();
        BorrowMoneyDto borrowMoneyDto = borrowMoneyMapper.getBorrowMoneyInfo(dto);
        if(borrowMoneyDto!=null && borrowMoneyDto.getId()!=0){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("jssId",borrowMoneyDto.getJssId().toString());
            List<BorrowMoneyDetailDto> list = borrowMoneyMapper.getBorrowMoneyDetailList(map);
            borrowMoneyDto.setDetailList(list);
            borrowMoneyDto.setDetailNum(list.size());

            List<BorrowMoneyDto> progressList = borrowMoneyMapper.getBorrowMoneyProgressList(map);
            borrowMoneyDto.setProgressList(progressList);
        }
        resultData.setReturnData(borrowMoneyDto);
        return resultData;
    }

    //获取还款计划列表
    public ResultData getHkPlanList(Map<String, Object> queryParam) {
        ResultData<List<HkPlanDto>> resultData = new ResultData<List<HkPlanDto>>();
        List<HkPlanDto> list = borrowMoneyMapper.getHkPlanList(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }

    //获取还款利息列表
    public ResultData getHkPlanInterestList(Map<String, Object> queryParam) {
        ResultData<List<HkPlanInterestDto>> resultData = new ResultData<List<HkPlanInterestDto>>();
        List<HkPlanInterestDto> list = borrowMoneyMapper.getHkPlanInterestList(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }

    //获取还款计划详情
    public ResultData getHkPlanInfo(HkPlanDto dto) {
        ResultData<HkPlanDto> resultData = new ResultData<HkPlanDto>();
        HkPlanDto hkPlanDto = borrowMoneyMapper.getHkPlanInfo(dto);
        resultData.setReturnData(hkPlanDto);
        return resultData;
    }
    //还款操作
    public ResultData updateHkPlan(HkPlanDto dto) {
        ResultData resultData = new ResultData();
        int count = borrowMoneyMapper.updateHkPlan(dto);
        if(count>0){
            resultData.setSuccess("操作成功");
        }else{
            resultData.setFail("操作失败");
        }
        return resultData;
    }
}
