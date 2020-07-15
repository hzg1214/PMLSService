package cn.com.eju.deal.service.storeAudit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.store.model.StoreAuthCheckDto;
import cn.com.eju.deal.store.model.StoreBizStop;
import cn.com.eju.deal.store.model.StoreStopCancel;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.mapper.storeAudit.StoreAuditMapper;
import cn.com.eju.deal.model.sweepStreets.StoreAuditRecordDto;
import cn.com.eju.deal.model.sweepStreets.StoreNewDto;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.ExchangeCenter;

/**
 * Created by xu on 2017/4/19.
 */

@Service("storeAuditService")
public class StoreAuditService {
    @Resource
    private StoreAuditMapper storeAuditMapper;

    @Resource
    private UserMapper userDao;

    public ResultData<List<StoreNewDto>> getStoreList(Map<String, Object> queryParam) throws Exception{
        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        Integer userId = (Integer)queryParam.get("userId");
        List<ExchangeCenter> centerList = userDao.getCenterListByUserId(userId);
        List<Integer> centerIdList = new ArrayList<>();
        if (null != centerList && !centerList.isEmpty()) {
            for (ExchangeCenter exc : centerList) {
                centerIdList.add(exc.getExchangeCenterId());
            }
        }
        if (!centerIdList.isEmpty()) {
            Integer[] arr = centerIdList.toArray(new Integer[centerIdList.size()]);
            queryParam.put("centerIdList", arr);
        }
        List<StoreNewDto> list=storeAuditMapper.getStoreList(queryParam);
        resultData.setTotalCount("0");
        if(null!=list && !list.isEmpty()){
            resultData.setTotalCount((String)queryParam.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(list);
        }
        return resultData;
    }
    public ResultData<List<StoreNewDto>> getStoreAuthCheckListData(Map<String, Object> queryParam) throws Exception{
        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        Integer userId = (Integer)queryParam.get("userId");
        List<ExchangeCenter> centerList = userDao.getCenterListByUserId(userId);
        List<Integer> centerIdList = new ArrayList<>();
        if (null != centerList && !centerList.isEmpty()) {
            for (ExchangeCenter exc : centerList) {
                centerIdList.add(exc.getExchangeCenterId());
            }
        }
        if (!centerIdList.isEmpty()) {
            Integer[] arr = centerIdList.toArray(new Integer[centerIdList.size()]);
            queryParam.put("centerIdList", arr);
        }

        if(queryParam.get("auditStatus") != null){
            String  auditStatus = queryParam.get("auditStatus").toString();
            switch (auditStatus){
                case "10":
                    auditStatus = "23301";
                    break;
                case "20":
                    auditStatus = "23302";
                    break;
                case "30":
                    auditStatus = "23303";
                    break;
            }
            queryParam.put("auditStatus",auditStatus);
        }
        List<StoreNewDto> list=storeAuditMapper.getStoreAuthCheckListData(queryParam);
        resultData.setTotalCount("0");
        if(null!=list && !list.isEmpty()){
            resultData.setTotalCount((String)queryParam.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(list);
        }
        return resultData;
    }
    public ResultData<List<StoreNewDto>> getStoreBizStopListData(Map<String, Object> queryParam) throws Exception{
        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        Integer userId = (Integer)queryParam.get("userId");
        List<ExchangeCenter> centerList = userDao.getCenterListByUserId(userId);
        List<Integer> centerIdList = new ArrayList<>();
        if (null != centerList && !centerList.isEmpty()) {
            for (ExchangeCenter exc : centerList) {
                centerIdList.add(exc.getExchangeCenterId());
            }
        }
        if (!centerIdList.isEmpty()) {
            Integer[] arr = centerIdList.toArray(new Integer[centerIdList.size()]);
            queryParam.put("centerIdList", arr);
        }

        if(queryParam.get("auditStatus") != null){
            String  auditStatus = queryParam.get("auditStatus").toString();
            switch (auditStatus){
                case "10":
                    auditStatus = "21001";
                    break;
                case "20":
                    auditStatus = "21002";
                    break;
                case "30":
                    auditStatus = "21003";
                    break;
            }
            queryParam.put("auditStatus",auditStatus);
        }
        List<StoreNewDto> list=storeAuditMapper.getStoreBizStopListData(queryParam);
        resultData.setTotalCount("0");
        if(null!=list && !list.isEmpty()){
            resultData.setTotalCount((String)queryParam.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(list);
        }
        return resultData;
    }
    public ResultData<List<StoreNewDto>> getStoreStopCancelListData(Map<String, Object> queryParam) throws Exception{
        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        Integer userId = (Integer)queryParam.get("userId");
        List<ExchangeCenter> centerList = userDao.getCenterListByUserId(userId);
        List<Integer> centerIdList = new ArrayList<>();
        if (null != centerList && !centerList.isEmpty()) {
            for (ExchangeCenter exc : centerList) {
                centerIdList.add(exc.getExchangeCenterId());
            }
        }
        if (!centerIdList.isEmpty()) {
            Integer[] arr = centerIdList.toArray(new Integer[centerIdList.size()]);
            queryParam.put("centerIdList", arr);
        }

        if(queryParam.get("auditStatus") != null){
            String  auditStatus = queryParam.get("auditStatus").toString();
            switch (auditStatus){
                case "10":
                    auditStatus = "21001";
                    break;
                case "20":
                    auditStatus = "21002";
                    break;
                case "30":
                    auditStatus = "21003";
                    break;
            }
            queryParam.put("auditStatus",auditStatus);
        }
        List<StoreNewDto> list=storeAuditMapper.getStoreStopCancelListData(queryParam);
        resultData.setTotalCount("0");
        if(null!=list && !list.isEmpty()){
            resultData.setTotalCount((String)queryParam.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(list);
        }
        return resultData;
    }

    public ResultData<List<StoreAuditRecordDto>> getStoreAuditRecordList(Map<String, Object> queryParam) throws Exception{
        ResultData<List<StoreAuditRecordDto>> resultData = new ResultData<List<StoreAuditRecordDto>>();
        List<StoreAuditRecordDto> list=storeAuditMapper.getStoreAuditRecordList(queryParam);
        resultData.setTotalCount("0");
        if(null!=list && !list.isEmpty()){
            resultData.setTotalCount((String)queryParam.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(list);
        }
        return resultData;
    }
    public ResultData<StoreNewDto> getStoreById(StoreNewDto dto){
        ResultData<StoreNewDto> resultData = new ResultData<StoreNewDto>();
        StoreNewDto storeNewDto = storeAuditMapper.getStoreById(dto);
        resultData.setReturnData(storeNewDto);
        return resultData;
    }

    public ResultData<StoreAuthCheckDto> getStoreAuthCheckById(Map<String, Object> queryParam) {
        ResultData<StoreAuthCheckDto> resultData = new ResultData<>();
        StoreAuthCheckDto storeAuthCheckDto = storeAuditMapper.getStoreAuthCheckById(queryParam);
        resultData.setReturnData(storeAuthCheckDto);
        return resultData;
    }

    public ResultData<StoreBizStop> getStoreBizStopById(Map<String, Object> queryParam) {
        ResultData<StoreBizStop> resultData = new ResultData<>();
        StoreBizStop storeBizStop = storeAuditMapper.getStoreBizStopById(queryParam);
        resultData.setReturnData(storeBizStop);
        return resultData;
    }

    public ResultData<StoreStopCancel> getStoreStopCancelById(Map<String, Object> queryParam) {
        ResultData<StoreStopCancel> resultData = new ResultData<>();
        StoreStopCancel storeStopCancel = storeAuditMapper.getStoreStopCancelById(queryParam);
        resultData.setReturnData(storeStopCancel);
        return resultData;
    }
}
