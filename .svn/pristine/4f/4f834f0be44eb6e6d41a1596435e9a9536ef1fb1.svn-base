package cn.com.eju.deal.houseLinkage.report.dao;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.dto.houseLinkage.report.ReportDetailDto;
import cn.com.eju.deal.dto.houseLinkage.report.ReportDto;
import cn.com.eju.deal.dto.houseLinkage.report.ReportSearchResultDto;
import cn.com.eju.deal.dto.scene.statistic.SceneStatisticEstateSearchResultDto;
import cn.com.eju.deal.houseLinkage.report.model.ReportDetail;
import cn.com.eju.deal.houseLinkage.report.model.StatisticDetailSearchResult;
import cn.com.eju.deal.scene.commission.model.SceneCommissionDetailSearchResult;
import cn.com.eju.deal.scene.statistic.model.SceneStatisticDetailSearchResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReportDetailMapper extends IDao<ReportDetail> {

	List<ReportDetail> getReportDetail(Map<?, ?>  param) throws Exception;
	
	List<ReportDetail> checkAccountOk(Map<?, ?>  param) throws Exception;
	
	List<ReportDetail> getReward(Map<?, ?>  param) throws Exception;
	
	List<ReportDetail> getCountRewardConfirmStatusValid(Map<?, ?>  param) throws Exception;
	
	
	List<StatisticDetailSearchResult> getStatisticDetail(Map<?, ?> param) throws Exception;
	
	List<SceneStatisticDetailSearchResult> getSceneStatisticDetail(Map<?, ?> param) throws Exception;
	
	List<SceneCommissionDetailSearchResult> selectSceneCommissionDetailList(Map<?, ?> param) throws Exception;
	
	int updateReportDetailReward(ReportDetail reportDetail) throws Exception;
	
	int updateReportDetailAccountReward(ReportDetail reportDetail) throws Exception;
	
	int updateBackProgress(Map<?, ?> param) throws Exception;
	
	int createReportDetail(Map<?, ?> param) throws Exception;
	
	List<ReportDetail> getSceneHouseInfo(Map<?, ?>  param) throws Exception;
	
	List<ReportDetail> getSceneHouseInfoDaDing(Map<?, ?>  param) throws Exception;
	
	SceneStatisticEstateSearchResultDto getCountAreaAndAmount(Map<?, ?>  param) throws Exception;
	/**
	 * 
	* TODO 根据项目编码EstateId，按项目id和公司id分组
	* @param param
	* @return
	* @throws Exception
	 */
	List<SceneStatisticEstateSearchResultDto> getCountAreaAndAmountGroupByTwoId(Map<String, Object>  param) throws Exception;

	int updateOtherStatus(ReportDetail reportDetail) throws Exception;
	
	int updateReback(ReportDetail reportDetail) throws Exception;
	
	int updateOperateDate(ReportDetail reportDetail);
	//用于克隆13503或13504的数据
	ReportDetail getCopyByPledged(ReportDetail reportDetail);
	//最大countId
	String getMaxCountId(ReportDetail reportDetail);

    ReportSearchResultDto getOperDetail(Map<String, Object> queryParam);

    int updateNextDetail(ReportDetail reportDetail);
    //更新13504 13505有效批次的大定信息
    int updateFangYouDetail(ReportDetail reportDetail);

	List<FileRecordMainDto> selectOperDetailPic(int id);

    int updateDetailRoughDate(ReportDto dto);
    
    ReportDetail selectReportDetailByDeal(String reportId);
    
    String selectBuildingNoRepeatCount(Map<String, Object> queryParam);

	List<ReportDetail> getTotalPackageReport(Map<String, Object> queryParam);

    void updateForInCome(ReportDetail reportDetail);

    ReportDetailDto quertInComeStatusByReportId(@Param("reportId") String reportId);
}