package cn.com.eju.deal.scene.batchReback.dao;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.scene.batchReback.dto.BatchReback;
import cn.com.eju.deal.scene.batchReback.dto.ReportDetailDto;

import java.util.List;
import java.util.Map;

public interface BatchRebackMapper extends IDao {

    /**
     *添加批量退房记录
     * @param batchReback
     * @return
     */
    Integer insertBatchReback(BatchReback batchReback);

    /**
     * 删除批量退房记录
     * @param batchId
     * @return
     */
    Integer deleteBatchRebackById(Integer batchId);

    /**
     * 根据项目编号和用户编号查询批量退房
     * @param map
     * @return
     */
    BatchReback getBatchRebackByProjectNo(Map<?, ?> map);

    /**
     *批量退房主表是否存在
     * @param map
     * @return
     */
    Integer whetherExistenceBatchReback(Map<?, ?> map);

    ReportDetailDto getReportDetail(String reportId);

    List<FileRecordMainDto> getFileList(Integer batchId);

    Integer saveFileInfo(FileRecordMainDto fileRecordMainDto);
}
