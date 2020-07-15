package cn.com.eju.deal.service.followMap;

import cn.com.eju.deal.achievement.dao.CitySettingMapper;
import cn.com.eju.deal.base.seqNo.api.ISeqNoAPI;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.contract.dao.AchievementCancelMapper;
import cn.com.eju.deal.contract.dao.AchievementCancelMappingMapper;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.contract.dao.ContractStoreMapper;
import cn.com.eju.deal.contract.model.AchievementCancel;
import cn.com.eju.deal.contract.model.Contract;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.mapper.followMap.StoreStopAuditMapper;
import cn.com.eju.deal.mapper.image.ImgMapper;
import cn.com.eju.deal.model.followMap.StoreStopAuditDto;
import cn.com.eju.deal.model.sweepStreets.WXPictureDto;
import cn.com.eju.deal.oa.model.OaOperator;
import cn.com.eju.deal.oa.service.OaOperatorService;
import cn.com.eju.deal.store.dao.StoreBizStopMapper;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.store.model.Store;
import cn.com.eju.deal.store.model.StoreBizStop;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 门店位置变更审批
 */
@Service("storeStopAuditService")
public class StoreStopAuditService extends BaseService<StoreStopAuditDto> {

    @Resource(name = "storeStopAuditMapper")
    private StoreStopAuditMapper storeStopAuditMapper;
    @Resource
    private StoreMapper storeMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private ImgMapper imgMapper;

    //
    @Resource
    private StoreBizStopMapper storeBizStopMapper;
    @Resource
    private ContractMapper contractMapper;
    @Resource
    private AchievementCancelMapper achievementCancelMapper;
    @Resource
    private AchievementCancelMappingMapper achievementCancelMappingMapper;
    @Resource
    private ContractStoreMapper contractStoreMapper;
    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;
    @Resource(name = "seqNoAPI")
    ISeqNoAPI seqNoAPI;
    
    @Resource
    private CitySettingMapper citySettingMapper;


    /**
     * 根据门店ID 获取门口位置变更审批记录 并且在审批中的
     * @param storeId
     * @return
     */
    public ResultData<StoreStopAuditDto> getStoreStopAuditByStoreId(Integer storeId) throws Exception{
        ResultData<StoreStopAuditDto> resultData = new ResultData<StoreStopAuditDto>();
        StoreStopAuditDto dto = storeStopAuditMapper.getStoreStopAuditByStoreId(storeId);
        resultData.setReturnData(dto);
        return resultData;
    }

    /**
     * 根据ID 获取门店位置审批记录信息
     * @param Id
     * @return
     */
    public ResultData<StoreStopAuditDto> getStoreStopAuditById(Integer Id) throws Exception{
        ResultData<StoreStopAuditDto> resultData = new ResultData<StoreStopAuditDto>();
        StoreStopAuditDto dto = storeStopAuditMapper.getStoreStopAuditById(Id);
        resultData.setReturnData(dto);
        return resultData;
    }
    /**
     * 获取逾期未审核的停业上报列表
     * @param
     * @return
     */
    public ResultData getNotStoreStopAuditList() throws Exception{
        ResultData resultData = new ResultData();
        List<StoreStopAuditDto> list = storeStopAuditMapper.getNotStoreStopAuditList();
        resultData.setReturnData(list);
        return resultData;
    }

    /**
     * 根据门店ID  检查门店是否存在重复审批记录、是否有门店中心、以及是否有中心负责人信息
     * @param storeId
     * @return
     */
    public ResultData checkStoreCenterByStoreId(Integer storeId) throws Exception{
        ResultData resultData = new ResultData();
        //根据门店获取待审批记录
        StoreStopAuditDto dto = storeStopAuditMapper.getStoreStopAuditByStoreId(storeId);
        if(dto!=null){
            //重复
            dto.setCheckStatus(1);
            dto.setCheckResult("已存在审核记录");
            resultData.setReturnData(dto);//重复审批流程
            return resultData;
        }else{
            dto=new StoreStopAuditDto();
        }
        //获取门店信息
        Store store = storeMapper.getById(storeId);
        if(null==store || store.getCenterId()==null){
            dto.setCheckStatus(2);
            dto.setCheckResult("该门店没有所属中心，不能发起停业上报");
            resultData.setReturnData(dto);//门店无中心
            return resultData;
        }else{
            Map<String,Object>  centerMap = storeStopAuditMapper.getCentralDirectorByCenterId(store.getCenterId());
            if(centerMap==null){
                dto.setCheckStatus(3);
                dto.setCheckResult("该门店所属中心没有中心负责人，不能发起停业上报");
                resultData.setReturnData(dto);//无中心负责人
                return resultData;
            }
        }
        //获取门店基础信息
        StoreStopAuditDto storeInfoDto = storeStopAuditMapper.getStoreInfoById(storeId);

        if(storeInfoDto.getContractStatus() != null && storeInfoDto.getContractStatus() == 10402) {
            //合同审核中不能发起停业申请
            dto.setCheckStatus(4);
            dto.setCheckResult("该门店合同审核中，不能发起停业上报");
            resultData.setReturnData(dto);
            return resultData;
        }else if(storeInfoDto.getApproveState() != null && storeInfoDto.getApproveState()==17202){
            dto.setCheckStatus(5);
            dto.setCheckResult("该门店合同撤销申请中，不能发起停业上报");
            resultData.setReturnData(dto);
            return resultData;
        }else if(storeInfoDto.getDecorationStatus() != null && storeInfoDto.getDecorationStatus()!=16304 && storeInfoDto.getDecorationStatus()!=16301){
            dto.setCheckStatus(6);
            dto.setCheckResult("该门店翻牌申请中，不能发起停业上报");
            resultData.setReturnData(dto);
            return resultData;
        }

        dto.setCheckStatus(0);
        resultData.setReturnData(dto);//正常
        return resultData;
    }

    /**
     * 创建门店停业上报审批记录
     * @param dto
     * @return
     */
    public int addStoreStopAudit(StoreStopAuditDto dto) throws Exception{
        //根据门店ID获取门店信息
        Store store = storeMapper.getById(Integer.parseInt(dto.getStoreId()));
        //根据门店中心 获取中心负责人
        Map<String,Object>  centerMap = storeStopAuditMapper.getCentralDirectorByCenterId(store.getCenterId());
        //获取中心负责人ID
        //Integer centerUserId =  Integer.parseInt(centerMap.get("userId").toString());
        //获取中心负责人Code (微信用户ID = 工号)
        String centerUserCode = centerMap.get("userCode").toString();

        dto.setCenterUserCode(centerUserCode);
        dto.setStoreAddress(store.getAddressDetail());
        //插入并且发送微信通知消息

        if (dto.getPicString() != null && !"-".equals(dto.getPicString()) && !"".equals(dto.getPicString())) {
            String[] picArray = dto.getPicString().split(",");
            String pRefId = UUID.randomUUID().toString();
            List<WXPictureDto> pictureList = new ArrayList<WXPictureDto>();
            for (String picString : picArray) {
                String[] picDtoArray = picString.split("\\^");
                if (picDtoArray.length > 0) {
                    WXPictureDto pictureDto = new WXPictureDto();
                    pictureDto.setPictureRefId(pRefId);
                    pictureDto.setSmallPictureUrl(picDtoArray[0]);
                    pictureDto.setMiddlePictureUrl(picDtoArray[1]);
                    pictureDto.setBigPictureUrl(picDtoArray[2]);
                    pictureDto.setCreateUser(dto.getUserCreate() + "");
                    pictureList.add(pictureDto);
                }
            }
            //上传图片
            imgMapper.addImg(pictureList);
            dto.setStopPicId(pRefId);
        }

        dto.setAuditStatus("21001");//待审核
        dto.setStoreNo(store.getStoreNo());
        dto.setStoreName(store.getName());
        int result = storeStopAuditMapper.addStoreStopAudit(dto);

        //更新门店停业状态为停业上报审核中
        dto.setBusinessStatus("20902");
        dto.setUpdateUserId(dto.getUserCreate());
        int count=storeStopAuditMapper.updateStoreStopStatus(dto);

        return result;
    }

    /**
     * 修改门店停业上报状态
     * @param dto
     * @return
     */
    public int updateStoreStopAudit(StoreStopAuditDto dto) throws Exception{
        //更新
        int conunt = storeStopAuditMapper.updateStoreStopAudit(dto);
        //
        StoreStopAuditDto newStoreStopAuditDto = storeStopAuditMapper.getStoreStopAuditById(Integer.parseInt(dto.getStopId()));
        //获取创建用户Code
        User createUser = userMapper.getUserByUserId(Integer.parseInt(newStoreStopAuditDto.getUserCreate()));
        dto.setUserCreateCode(createUser.getUserCode());
        dto.setStoreNo(newStoreStopAuditDto.getStoreNo());
        dto.setStoreName(newStoreStopAuditDto.getStoreName());
        dto.setStoreAddress(newStoreStopAuditDto.getStoreAddress());
        //状态为审批通过 更新门店表停业状态为已停业
        if("21002".equals(newStoreStopAuditDto.getAuditStatus())){
            //更新门店表停业状态
            newStoreStopAuditDto.setBusinessStatus("20903");
            newStoreStopAuditDto.setUpdateUserId(dto.getUserCreate());
            conunt = storeStopAuditMapper.updateStoreStopStatus(newStoreStopAuditDto);
            //判断门店是否有合同，并补一条撤销记录
            StoreBizStop storeBizStop=new StoreBizStop();
            storeBizStop.setStoreId(Integer.parseInt(newStoreStopAuditDto.getStoreId()));
            storeBizStop.setAuditUserId(Integer.parseInt(dto.getAuditUserId()));
            storeBizStop.setUserCode(dto.getUserCode());
            storeBizStop.setStopReasonName(newStoreStopAuditDto.getStopReasonName());   
            storeBizStop.setFollowDetail(newStoreStopAuditDto.getFollowDetail());           
            this.updateContractInfo(storeBizStop);

        }else if("21003".equals(newStoreStopAuditDto.getAuditStatus())){//状态为驳回，更新门店状态为正常营业
            newStoreStopAuditDto.setBusinessStatus("20901");
            newStoreStopAuditDto.setUpdateUserId(dto.getUserCreate());
            conunt = storeStopAuditMapper.updateStoreStopStatus(newStoreStopAuditDto);
        }
        return conunt;
    }

    //停业上报审核通过，判断门店是否有合同，如果有补一条撤销记录
    public void updateContractInfo(StoreBizStop storeBizStop) throws Exception{
        StoreBizStop contractStatusInfo = storeBizStopMapper.getContractStatusInfo(storeBizStop);
        Integer contractStatus = null;
        boolean cancelRecord = false;
        contractStatus = 10405;
        if(contractStatusInfo.getContractId() != null && (contractStatusInfo.getContractStatus() == 10403 || contractStatusInfo.getContractStatus() == 10406)) {
            contractStatus = null;
            cancelRecord = true;
        }
        //修改合同状态为作废
        if(contractStatusInfo.getContractId() != null && contractStatus != null) {
            Contract contract = new Contract();
            contract.setId(contractStatusInfo.getContractId());
            contract.setContractStatus(contractStatus);
            contractMapper.update(contract);
        }

        //生成合同撤销记录
        if(cancelRecord) {

            //存在非撤销失败的撤销记录,不重复生成撤销记录
            Map<String,Object> param = new HashMap<>();
            param.put("storeId", storeBizStop.getStoreId());
            param.put("contractId", contractStatusInfo.getContractId());
            List<Map<String,Object>> cancelList = storeBizStopMapper.queryCancelList(param);
            if(cancelList != null && !cancelList.isEmpty()) {
                return ;
            }

            //不发起oa撤销,仅本地记录
            AchievementCancel achievementCancel = new AchievementCancel();
            achievementCancel.setAchievementCancelNo(seqNoAPI.getSeqNoByTypeCode("TYPE_BUSINESS_CANCEL").getReturnData());
            achievementCancel.setContractId(contractStatusInfo.getContractId());
//             achievementCancel.setCancelReason("门店停业上报申请审核通过,合同自动撤销");
            String cancelReson = "停业原因：" +storeBizStop.getStopReasonName() +"  描述："+ storeBizStop.getFollowDetail();
            achievementCancel.setCancelReason(cancelReson);
            achievementCancel.setRemarks("门店停业");
            achievementCancel.setDateCreate(new Date());
            achievementCancel.setUserCreate(storeBizStop.getAuditUserId());
            achievementCancel.setApproveState(DictionaryConstants.CONTRACT_ISCANCEL_ISCANCELLED);
            achievementCancel.setApproveDate(new Date());
            achievementCancel.setFlowId("");
            achievementCancel.setUpdateDate(new Date());
            achievementCancel.setUpdateUser(storeBizStop.getAuditUserId());

          //获取事业部区域及其核算主体编码
            String bussineArea ="";
            String accountProjectCode ="";
            Map<String, Object> map = citySettingMapper.getCitySettingByCityNo(contractStatusInfo.getAcCityNo());
            if(null != map) {
            	bussineArea=map.get("busCode").toString();
    			accountProjectCode=map.get("accountProjectCode").toString();
    		}
            //核算主体
            achievementCancel.setAccountSubject(accountProjectCode);
            //事业部区域
            achievementCancel.setBusDepartment(bussineArea);

            achievementCancel.setStopKbn("1");
            //插入门店撤销记录
            achievementCancelMapper.createNewCancelRecord(achievementCancel);

            Map<String, Object> cancelMapping = new HashMap<>();
            cancelMapping.put("achievementCancelId", achievementCancel.getId());
            cancelMapping.put("storeId", storeBizStop.getStoreId());
            cancelMapping.put("contractId", contractStatusInfo.getContractId());
            //合同，门店撤销关联表
            achievementCancelMappingMapper.createNewRecord(cancelMapping);

            Map<String, Object> contractStore = new HashMap<>();
            contractStore.put("isCancel", DictionaryConstants.STORESTATE_ISCANCEL_ISCANCEL);
            contractStore.put("achievementCancelNo", achievementCancel.getAchievementCancelNo());
            contractStoreMapper.updateIsCancel(contractStore);

        }
        //解除门店合同关系
        if((contractStatusInfo.getContractId() != null && contractStatus != null) || cancelRecord) {
            Map<String, Object> contractStore = new HashMap<>();
            contractStore.put("contractId", contractStatusInfo.getContractId());
            contractStore.put("storeIdList", new String[] {storeBizStop.getStoreId().toString()});
            contractStoreMapper.updateFlag(contractStore);
        }
    }


    private String getBussineArea(String busNo,String userCode) throws Exception{
        // 事业部区域
        String bussineArea = null;
        // 查询是否是经办人
        OaOperator oa = oaOperatorService.getByUserCode(userCode);

        if (null != oa){
            // （取经办人的事业部区域，如果是跨区的，取其选择的区域）
            // 暂时不考虑跨区的情况，后期有需求再改
            Boolean isCombine = oa.getIsCombine();
            if (isCombine){
                bussineArea = busNo;
            }
            else{
                bussineArea = oa.getBusCode();
            }
        }
        return bussineArea;
    }

}
