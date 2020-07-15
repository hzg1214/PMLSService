package cn.com.eju.deal.houseLinkage.estate.dao;

import java.util.List;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateReleaseCityDto;
import cn.com.eju.deal.open.model.ContractFlowDto;

/** 
* @ClassName: EstatetReleaseMapper 
* @Description: 项目发布关联城市dao
*/
public interface EstatetReleaseMapper extends IDao<EstateReleaseCityDto>{
    
    /**
    * @Title: queryCityListByEstateId
    * @Description: 根据楼盘id查询城市列表
    * @param estateId 楼盘id
    * @return
    */
    List<EstateReleaseCityDto> queryCityListByEstateId(String estateId) throws Exception;
    /**
     * 批量插入数据
     * @param dtoList
     * @return
     */
    int batchInsertEstatetRelease(List<EstateReleaseCityDto> releaseCitylist);
    /**
     * 对楼盘原来的变更城市进行删除
     * @param estateId
     * @return
     */
    int deleteEstatetReleaseByEstateId(String estateId);
}
