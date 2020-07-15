package cn.com.eju.deal.houseLinkage.estate.dao;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateDto;
import cn.com.eju.deal.dto.scene.padCommission.PadCommissionResultDto;
import cn.com.eju.deal.houseLinkage.estate.model.BigCutomer;
import cn.com.eju.deal.houseLinkage.estate.model.Estate;
import cn.com.eju.deal.houseLinkage.estate.model.EstateSearchResult;
import cn.com.eju.deal.houseLinkage.estate.model.MattressNail;

import java.util.List;
import java.util.Map;

public interface EstateMapper extends IDao<Estate> {

	List<EstateSearchResult> selectEstateList(Map<?, ?> param) throws Exception;

	List<EstateDto> getStatisticForEstate(Map<?, ?> param) throws Exception;

	List<EstateDto> getSceneEstateList(Map<?, ?> param) throws Exception;

	List<EstateDto> getSceneStatisticEstateList(Map<?, ?> param) throws Exception;

	List<EstateDto> getSceneCommissionList(Map<?, ?> param) throws Exception;

	List<Estate> getSceneInCommissionList(Map<?, ?> param) throws Exception;

	List<Estate> selectEstateInfo(String estateId) throws Exception;

	int appointmentRelease(String releaseDate) throws Exception;

	int insertLnkImport(Map<?, ?> param) throws Exception;

	int updateLnkImport(Map<?, ?> param) throws Exception;

	Map<?,?> getLnkImportByReportId(Map<?, ?> param) throws Exception;

	int updateLnkImportBefore(Map<?, ?> param) throws Exception;

	List<EstateDto> getProjectDepartment(Map<String, Object> param) throws Exception;

	Map<?,?> checkCitySwitchMonth(PadCommissionResultDto dto);

	Map<?,?> checkCitySwitchMonthByCityNo(String cityNo);

	int checkSwitchMonthByMap(Map<String, Object> param);

	int checkSwitchMonth(PadCommissionResultDto dto);

	int checkCityNoByMap(Map<String, Object> param);
	int checkCityNo(PadCommissionResultDto dto);

	void merageYjReportdy(PadCommissionResultDto dto);

	void merageSjReportdy(PadCommissionResultDto dto);

	List<Estate> queryByOpEstateId(String opEstateId);

    List<Map<String,String>> queryCountryList();

	List<BigCutomer> getBigCustomerList();

	List<MattressNail> getMattressNail();

	MattressNail selectMattressNail(Map<String, Object> param);

	MattressNail getMattressNailByMattressNailId(Map<String, Object> param);

	List<String> getEstateNmList(Map<String, Object> param);

	Estate getByProjectNo(String projectNo) throws Exception;
	Estate getLastProject() throws Exception;
	//其他收入-项目列表
	List<EstateDto> getProjectList(Map<?, ?> param) throws Exception;

	//同步项目到 mysql
	int syncEstate(Estate dto);

	Estate getCenterInfo(Integer id);

	Estate getLastProjectByCity(Map<String, Object> param) throws Exception;

	int updateDeveloperInfo(Estate estate);
}
