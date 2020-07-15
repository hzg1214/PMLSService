package cn.com.eju.deal.scene.batchSale.dao;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.scene.batchSale.dto.BatchSaleDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BatchSaleDetailMapper extends IDao<BatchSaleDetailMapper> {

    Integer insertBatchSaleDetail(BatchSaleDetail batchSaleDetail);

    Integer deleteBachSaleDetailByBatchId(Map<?,?> map);

    Integer updateBachSaleDetail(Map<?,?> map);

    List<BatchSaleDetail> getBatchSaleDetailList(Integer batchId);

    Integer countBatchSaleDetailNum(Map<?,?> map);

    Integer countBatchSaleDetail(Map<?,?> map);

    Integer whetherExistenceBatchSaleDetail(Map<?,?> map);

    Integer yesOrNoDaDingStatus(Map<?,?> map);

    Integer updateBatchSaleLog(Map<?,?> map);

    Integer checkAccountProject(@Param("accountProject") String accountProject, @Param("cityNo") String cityNo);

    List<BatchSaleDetail> selectByBatachId(Integer batchId);
    
    //校验核算主体-通过项目找到收入类合同(LNK_Estate_Srlht)对应得核算
    Integer checkAccountProjectByProjectNo(@Param("accountProject") String accountProject, @Param("projectNo") String projectNo);
}
