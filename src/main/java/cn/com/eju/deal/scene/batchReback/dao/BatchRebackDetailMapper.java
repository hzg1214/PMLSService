package cn.com.eju.deal.scene.batchReback.dao;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.scene.batchReback.dto.BatchRebackDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BatchRebackDetailMapper extends IDao {

    Integer insertBatchRebackDetail(BatchRebackDetail batchRebackDetail);

    Integer deleteBachRebackDetailByBatchId(Map<?, ?> map);

    Integer updateBachRebackDetail(Map<?, ?> map);

    List<BatchRebackDetail> getBatchRebackDetailList(Integer batchId);

    Integer countBatchRebackDetailNum(Map<?, ?> map);

    Integer countBatchRebackDetail(Map<?, ?> map);

    Integer whetherExistenceBatchRebackDetail(Map<?, ?> map);

    Integer updateBatchRebackLog(Map<?, ?> map);

    Integer checkAccountProject(@Param("accountProject") String accountProject, @Param("cityNo") String cityNo);

    Integer checkReback(Map<?, ?> param);

    List<BatchRebackDetail> selectByBatachId(Integer batchId);
}
