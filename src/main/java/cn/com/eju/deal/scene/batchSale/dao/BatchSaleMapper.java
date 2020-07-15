package cn.com.eju.deal.scene.batchSale.dao;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.houseLinkage.report.model.ReportDetail;
import cn.com.eju.deal.scene.batchSale.dto.BatchSale;
import cn.com.eju.deal.scene.batchSale.dto.ReportDetailDto;

import java.util.List;
import java.util.Map;

public interface BatchSaleMapper extends IDao<BatchSale> {

    /**
     *添加批量成销记录
     * @param batchSale
     * @return
     */
    Integer insertBatchSale(BatchSale batchSale);
    /**
     * 删除批量成销记录
     * @param batchId
     * @return
     */
    Integer deleteBatchSaleById(Integer batchId);

    /**
     *修改批量成销记录
     * @param map
     * @return
     */
    Integer updateBatchSaleByBatchId(Map<?,?> map);
    /**
     * 根据项目编号和用户编号查询批量成销
     * @param map
     * @return
     */
    BatchSale getBatchSaleByProjectNo(Map<?,?> map);

    /**
     *批量成销主表是否存在
     * @param map
     * @return
     */
    Integer whetherExistenceBatchSale(Map<?,?> map);

    ReportDetailDto getReportDetail(String reportId);

    List<FileRecordMainDto> getFileList(Integer batchId);

    Integer saveFileInfo(FileRecordMainDto fileRecordMainDto);
}
