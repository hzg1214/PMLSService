/**
 * @Title: FollowService.java
 * @Package cn.com.eju.deal.follow.service
 * @Description: 跟进Service包
 * @author 陆海丹
 * @date 2016年3月24日 下午12:13:40
 * @version V1.0
 */
package cn.com.eju.deal.storeStructure.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.storeStructure.StoreStructureDto;
import cn.com.eju.deal.storeStructure.dao.StoreStructureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("storeStructureService")
public class StoreStructureService extends BaseService {

    @Autowired
    private StoreStructureMapper storeStructureMapper;

    /**
     * .
     * 获取门店结构报表
     *
     * @param dto
     * @return
     */
    public ResultData<List<StoreStructureDto>> queryStoreStructure(Map<?, ?> queryParam) throws Exception {
        //构建返回
        ResultData<List<StoreStructureDto>> resultData = new ResultData<>();
        //查询
        List<StoreStructureDto> list = storeStructureMapper.queryStoreStructure(queryParam);

        resultData.setReturnData(list);
        return resultData;
    }

}
