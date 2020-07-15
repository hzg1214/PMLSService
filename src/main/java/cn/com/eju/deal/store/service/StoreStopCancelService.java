package cn.com.eju.deal.store.service;

import cn.com.eju.deal.achievement.dao.CitySettingMapper;
import cn.com.eju.deal.base.seqNo.api.ISeqNoAPI;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.contract.dao.AchievementCancelMapper;
import cn.com.eju.deal.contract.dao.AchievementCancelMappingMapper;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.contract.dao.ContractStoreMapper;
import cn.com.eju.deal.contract.model.AchievementCancel;
import cn.com.eju.deal.contract.model.ContractStore;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.mapper.followMap.StoreStopAuditMapper;
import cn.com.eju.deal.store.dao.StoreBizStopMapper;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.store.dao.StoreStopCancelMapper;
import cn.com.eju.deal.store.model.Store;
import cn.com.eju.deal.store.model.StoreStopCancel;
import cn.com.eju.deal.store.model.StoreStopCancelLog;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.ExchangeCenter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class StoreStopCancelService extends BaseService<StoreStopCancel> {

    @Resource
    private StoreStopCancelMapper storeStopCancelMapper;

    @Resource
    private FileRecordMainMapper fileRecordMainMapper;

    @Resource
    private StoreMapper storeMapper;

    @Resource
    private ContractMapper contractMapper;
    @Resource
    private AchievementCancelMapper achievementCancelMapper;
    @Resource
    private AchievementCancelMappingMapper achievementCancelMappingMapper;
    @Resource
    private ContractStoreMapper contractStoreMapper;
    @Resource(name = "seqNoAPI")
    ISeqNoAPI seqNoAPI;

    @Resource
    private UserMapper userDao;

    @Resource(name = "storeStopAuditMapper")
    private StoreStopAuditMapper storeStopAuditMapper;

    @Resource
    private CitySettingMapper citySettingMapper;

    @Resource
    private StoreBizStopMapper storeBizStopMapper;

    public ResultData<String> storeStopCancelAdd(StoreStopCancel storeStopCancel) {
        ResultData<String> resultData = new ResultData<>();
        int num = storeStopCancelMapper.create(storeStopCancel);

        if (num != 1) {
            resultData.setFail("保存门店撤销申请失败");
            return resultData;
        }

        //更新门店停业撤销状态
        Store store = new Store();
        store.setStoreId(storeStopCancel.getStoreId());
        store.setStopCancelStatus(23201);
        storeMapper.update(store);

        //图片
        Map map = new HashMap();
        map.put("contractId", storeStopCancel.getId());

        String fileRecordMainIds = storeStopCancel.getFileRecordMainIds();
        String[] fileIdArr = fileRecordMainIds.split(",");
        List<Integer> fileIdList = new ArrayList<>();
        for (String fileId : fileIdArr) {
            fileIdList.add(Integer.valueOf(fileId));
        }
        map.put("ids", fileIdList);

        fileRecordMainMapper.updateByCondition(map);

        return resultData;
    }

    public ResultData<List<StoreStopCancel>> queryList(Map<String, Object> param) throws Exception {
        ResultData<List<StoreStopCancel>> resultData = new ResultData<>();

        Integer userId = (Integer) param.get("userId");
        List<ExchangeCenter> centerList = userDao.getCenterListByUserId(userId);
        String centerIdStr = (String) param.get("centerIdStr");
        if (StringUtil.isEmpty(centerIdStr)) {
            if (null != centerList && !centerList.isEmpty()) {
                for (ExchangeCenter exc : centerList) {
                    if (StringUtil.isEmpty(centerIdStr)) {
                        centerIdStr = exc.getExchangeCenterId().toString();
                    } else {
                        centerIdStr = centerIdStr + ',' + exc.getExchangeCenterId().toString();
                    }
                }
            }
            if (!centerIdStr.isEmpty()) param.put("centerIdStr", centerIdStr);
        }
        List<StoreStopCancel> list = storeStopCancelMapper.queryList(param);
        resultData.setReturnData(list);

        return resultData;
    }

    public StoreStopCancel getByStopCancelId(Integer id) {

        StoreStopCancel storeStopCancel = storeStopCancelMapper.getById(id);

        List<FileRecordMainDto> list = storeStopCancelMapper.queryFileList(id);
        storeStopCancel.setFileList(list);

        return storeStopCancel;
    }

    public ResultData<StoreStopCancel> rejectStopCancel(StoreStopCancel storeStopCancel) {

        ResultData<StoreStopCancel> result = new ResultData<>();

        //更新停业撤销
        storeStopCancelMapper.update(storeStopCancel);

        //修改门店停业撤销状态
        Store store = new Store();
        store.setStoreId(storeStopCancel.getStoreId());
        store.setStopCancelStatus(23203);
        storeMapper.update(store);

        return result;
    }

    public ResultData<String> auditPass(StoreStopCancel storeStopCancel) throws Exception {

        ResultData<String> result = new ResultData<>();
        Integer storeId = storeStopCancel.getStoreId();
        Map<String, Object> contractInfo = storeStopCancelMapper.getLastestContract(storeId);

        //停业撤销审核通过
        int count = storeStopCancelMapper.update(storeStopCancel);

        //修改门店停业撤销状态
        Store store = new Store();
        store.setStoreId(storeId);
        store.setStopCancelStatus(23202);
        storeMapper.update(store);

        //删除门店停业记录
        //storeStopCancelMapper.deleteStoreBizStop(storeId);

        //修改门店为正常营业
        Map<String, Object> param = new HashMap<>();
        param.put("businessStatus", 20901);
        param.put("storeId", storeId);
        storeBizStopMapper.updateStoreBusinessStatus(param);

        if (count == 1 && contractInfo != null) {
            Integer contractId = Integer.valueOf(contractInfo.get("ContractId").toString());
            param.put("contractId", contractId);
            /*//修改合同状态为审核通过
            Contract contract = new Contract();
            contract.setId(contractId);
            contract.setContractStatus(10403);
            contractMapper.update(contract);*/

            //合同状态为审核通过的新增正负记录
            Integer contractStatus = Integer.valueOf(contractInfo.get("ContractStatus").toString());
            if (DictionaryConstants.CONTRACT_STATUS_AUDIT_PASS.equals(contractStatus)) {
                //删除业绩撤销记录
                AchievementCancel achievementCancel = storeStopCancelMapper.getAchievementCancel(param);
                if (achievementCancel != null) {

                    //修改合同门店
                    ContractStore contractStore = new ContractStore();
                    contractStore.setStoreId(storeId);
                    contractStore.setContractId(contractId);
                    contractStore.setIsCancel("17201");
                    contractStore.setFlag(0);
                    contractStoreMapper.update(contractStore);

                    storeStopCancelMapper.deleteAchievementCancel(achievementCancel.getId());
                    Map map = new HashMap();
                    map.put("achievementCancelId", achievementCancel.getId());
                    achievementCancelMappingMapper.updateOldCancelMappingRecord(map);

                    Date approveDate = achievementCancel.getApproveDate();
                    Integer cancelId = achievementCancel.getId();

                    //新增负记录日志
                    StoreStopCancelLog log1 = new StoreStopCancelLog();
                    log1.setContractId(contractId);
                    log1.setStoreId(storeId);
                    log1.setStatDate(approveDate);
                    log1.setCalcFlag(-1);
                    log1.setNew_calcFlag(0);
                    log1.setSortFlag(0);
                    log1.setSignStatus("门店停业");
                    log1.setRemark("门店停业");
                    log1.setCancelId(cancelId);
                    log1.setUserCreate(storeStopCancel.getUserUpdate());
                    storeStopCancelMapper.insertLog(log1);

                    //新增正记录日志
                    StoreStopCancelLog log2 = new StoreStopCancelLog();
                    log2.setContractId(contractId);
                    log2.setStoreId(storeId);
                    log2.setStatDate(new Date());
                    log2.setCalcFlag(1);
                    log2.setNew_calcFlag(0);
                    log2.setSortFlag(0);
                    log2.setSignStatus("停业撤销恢复");
                    log2.setRemark("停业撤销恢复");
                    log2.setCancelId(cancelId);
                    log2.setUserCreate(storeStopCancel.getUserUpdate());
                    storeStopCancelMapper.insertLog(log2);
                }


            }
        }

        return result;
    }
}
