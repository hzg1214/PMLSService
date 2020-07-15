package cn.com.eju.deal.service.sweepStreets;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.mapper.sweepStreets.StoreLocationAuditMapper;
import cn.com.eju.deal.model.sweepStreets.StoreLocationAuditDto;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.store.model.Store;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 门店位置变更审批
 */
@Service("storeLocationAuditService")
public class StoreLocationAuditService extends BaseService<StoreLocationAuditDto> {

    @Resource(name = "storeLocationAuditMapper")
    private StoreLocationAuditMapper storeLocationAuditMapper;

    @Resource
    private StoreMapper storeMapper;

    @Resource
    private UserMapper userMapper;


    /**
     * 根据门店ID 获取门口位置变更审批记录 并且在审批中的
     * @param storeId
     * @return
     */
    public ResultData<StoreLocationAuditDto> getStoreLocationAuditByStoreIdAndAudit(Integer storeId) throws Exception{
        ResultData<StoreLocationAuditDto> resultData = new ResultData<StoreLocationAuditDto>();
        StoreLocationAuditDto dto = storeLocationAuditMapper.getStoreLocationAuditByStoreIdAndAudit(storeId);
        resultData.setReturnData(dto);
        return resultData;
    }

    /**
     * 根据ID 获取门店位置审批记录信息
     * @param Id
     * @return
     */
    public ResultData<StoreLocationAuditDto> getStoreLocationAuditById(Integer Id) throws Exception{
        ResultData<StoreLocationAuditDto> resultData = new ResultData<StoreLocationAuditDto>();
        StoreLocationAuditDto dto = storeLocationAuditMapper.getStoreLocationAuditById(Id);
        resultData.setReturnData(dto);
        return resultData;
    }

    /**
     * 根据门店ID  检查门店是否存在重复审批记录、是否有门店中心、以及是否有中心负责人信息
     * @param storeId
     * @return
     */
    public ResultData<Integer> checkStoreCenterByStoreId(Integer storeId) throws Exception{
        ResultData<Integer> resultData = new ResultData<Integer>();
        //根据门店获取待审批记录
        /*StoreLocationAuditDto dto = storeLocationAuditMapper.getStoreLocationAuditByStoreIdAndAudit(storeId);
        if(dto!=null){
            //重复
            resultData.setReturnData(1);//重复审批流程
            return resultData;
        }*/
        //获取门店信息
        Store store = storeMapper.getById(storeId);
        if(null==store || store.getCenterId()==null){
            resultData.setReturnData(2);//门店无中心
            return resultData;
        }else{
            Map<String,Object>  centerMap = storeLocationAuditMapper.getCentralDirectorByCenterId(store.getCenterId());
            if(centerMap==null){
                resultData.setReturnData(3);//无中心负责人
                return resultData;
            }
        }
        resultData.setReturnData(0);//正常
        return resultData;
    }

    /**
     * 更新门店位置
     * @param dto
     * @return
     */
    public int updateStoreLocation(StoreLocationAuditDto dto) throws Exception{
        int result = storeLocationAuditMapper.updateStoreLocation(dto);
        return result;
    }

    /**
     * 创建门店位置变更审批记录
     * @param dto
     * @return
     */
    public int updateStoreLocationAuditAndAudit(StoreLocationAuditDto dto) throws Exception{
        //更新
        int conunt = storeLocationAuditMapper.updateStoreLocationAuditAndAudit(dto);
        //
        StoreLocationAuditDto newStoreLocationAuditDto = storeLocationAuditMapper.getStoreLocationAuditById(dto.getId());
        //获取创建用户Code
        User createUser = userMapper.getUserByUserId(newStoreLocationAuditDto.getCreateUserId());
        dto.setCreateUserCode(createUser.getUserCode());
        dto.setStoreNewDto(newStoreLocationAuditDto.getStoreNewDto());
        //状态为审批通过 更新门店表
        if(newStoreLocationAuditDto.getAuditStatus()==1){
            //更新门店表位置信息
            conunt = storeLocationAuditMapper.updateStoreLocation(newStoreLocationAuditDto);
        }
        return conunt;
    }

}
