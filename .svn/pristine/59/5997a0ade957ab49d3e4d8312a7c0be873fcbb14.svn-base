package cn.com.eju.deal.service.maintainStore;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.mapper.maintainStore.MaintainStoreMapper;
import cn.com.eju.deal.model.sweepStreets.MaintainerInfoDto;
import cn.com.eju.deal.model.sweepStreets.StoreNewDto;
import cn.com.eju.deal.model.sweepStreets.WXPictureDto;
import cn.com.eju.deal.model.sweepStreets.WXPushInfoDto;
import cn.com.eju.deal.service.myCollection.MyCollectionService;

/**
 * Created by xu on 2017/5/22.
 */
@Service("maintainStoreService")
public class MaintainStoreService {
    @Resource
    private MaintainStoreMapper maintainStoreMapper;
    @Resource
    private MyCollectionService myCollectionService;

    public ResultData<List<StoreNewDto>> getStoreListData(StoreNewDto dto) throws Exception{
        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        List<StoreNewDto> list=maintainStoreMapper.getStoreListData(dto);
        resultData.setTotalCount("0");
        if(null!=list && !list.isEmpty()){
            for(StoreNewDto sDto:list){
                WXPictureDto pictureDto=myCollectionService.getImages(sDto.getStoreId());
                if(pictureDto!=null && pictureDto.getSmallPictureUrl()!=null){
                    sDto.setStorePicUrl(pictureDto.getSmallPictureUrl());
                }
            }
            resultData.setTotalCount(list.get(0).getDataCount()+"");
            resultData.setReturnData(list);
        }
        return resultData;
    }

    public ResultData<List<Map<String,Object>>> getOverdueNotFollowStore()throws Exception{
        ResultData resultData = new ResultData();

        List<Map<String,Object>> mapList = maintainStoreMapper.getOverdueNotFollowStore();
        resultData.setReturnData(mapList);
        return resultData;
    }
    public ResultData checkMaintainerStatus() throws Exception{
        ResultData resultData = new ResultData();
        //更新维护人所属中心表
        maintainStoreMapper.updateMaintainerCenterData();
        List<MaintainerInfoDto> list=maintainStoreMapper.checkMaintainerStatus();
        resultData.setReturnData(list);
        return resultData;
    }
    public ResultData addPushInfo(WXPushInfoDto dto) throws Exception{
        ResultData resultData = new ResultData();
        maintainStoreMapper.addPushInfo(dto);
        return resultData;
    }
}
