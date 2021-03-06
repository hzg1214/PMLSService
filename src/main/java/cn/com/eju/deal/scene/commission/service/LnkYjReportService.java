package cn.com.eju.deal.scene.commission.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.scene.commission.dao.LnkYjReportMapper;
import cn.com.eju.deal.scene.commission.model.CompanyEntity;
import cn.com.eju.deal.scene.commission.model.CompanyMatin;
import cn.com.eju.deal.scene.commission.model.LnkYjReport;
import cn.com.eju.deal.scene.commission.model.LnkYjReportInfo;
import cn.com.eju.deal.scene.commission.model.LnkYjReportLog;
import cn.com.eju.deal.scene.commission.model.YjCompany;

/**
 * desc:
 * 
 * @author :zhenggang.Huang
 * @date :2019年4月29日
 */
@Service("lnkYjReportService")
public class LnkYjReportService extends BaseService {

	private final LogHelper logger = LogHelper.getLogger(this.getClass());
	@Resource
	private LnkYjReportMapper lnkYjReportMapper;

	/**
	 * 获取垫佣列表
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getLnkYjReportList(Map<String, Object> map) throws Exception {
		return lnkYjReportMapper.getLnkYjReportList(map);
	}

	/**
	 * desc:查看返佣对象详情 
	 * 2019年4月29日 
	 * author:zhenggang.Huang
	 */
	public LnkYjReportInfo getYjReportDeatilById(Map<String, Object> map) throws Exception {
		List<Map<String, Object>> list = lnkYjReportMapper.getYjReportDeatilById(map);
		Map<String, Object> param = new HashMap<>();
		param.put("reportId", (String) list.get(0).get("reportId"));
//		param.put("defaultFlag", 1);
		List<CompanyMatin> companyMatins = lnkYjReportMapper.selYjLogByReportId(param);// 维护记录

		Map<String, Object> buMap = new HashMap<>();
		if (list.size() == 1) {
			buMap.put("isDb", 0);
			list.add(buMap);
			list.add(buMap);
		} else if (list.size() == 2) {
			buMap.put("isDb", 0);
			list.add(buMap);
		}

		LnkYjReportInfo lnkYjReportInfo = new LnkYjReportInfo();
		// 项目编号
		if (list != null && !list.isEmpty()) {
			lnkYjReportInfo = getLnkYjReportInfo(list, lnkYjReportInfo);
			lnkYjReportInfo.setCompanyMatins(companyMatins);
		}
		return lnkYjReportInfo;
	}

	private LnkYjReportInfo getLnkYjReportInfo(List<Map<String, Object>> list, LnkYjReportInfo lnkYjReportInfo) {
		String reportId = (String) list.get(0).get("reportId");// 报备编号
		String estateNm = (String) list.get(0).get("estateNm");// 楼盘名称
		String companyNm = (String) list.get(0).get("companyNm");// 经纪公司
		String addressDetail = (String) list.get(0).get("addressDetail");// 门店地址
		String customerNm = (String) list.get(0).get("customerNm");// 客户
		String customerTel = (String) list.get(0).get("customerTel");// 客户电话
		String dicValue = (String) list.get(0).get("dicValue");// 最新进度
		Integer defaultFlag = (Integer) list.get(0).get("defaultFlag");
		String  projectNo = (String) list.get(0).get("projectNo");
		lnkYjReportInfo.setReportId(reportId);
		lnkYjReportInfo.setEstateNm(estateNm);
		lnkYjReportInfo.setCompanyNm(companyNm);
		lnkYjReportInfo.setAddressDetail(addressDetail);
		lnkYjReportInfo.setCustomerNm(customerNm);
		lnkYjReportInfo.setCustomerTel(customerTel);
		lnkYjReportInfo.setDicValue(dicValue);
		lnkYjReportInfo.setDefaultFlag(defaultFlag);
		lnkYjReportInfo.setProjectNo(projectNo);
		List<CompanyEntity> companys = new ArrayList<>();// 返佣对象
		// 搂出返佣对象和编号 (公司名称和公司编号)
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> result = list.get(i);
			CompanyEntity companyEntity = new CompanyEntity();
			String companyName = (String) result.get("companyName");
			String companyNo = (String) result.get("companyNo");
			Integer defaultFlagStr = (Integer) result.get("defaultFlag");
			Integer isDb = (Integer) result.get("isDb");
			companyEntity.setCompanyName(companyName);
			companyEntity.setCompanyNo(companyNo);
			companyEntity.setDefaultFlag(defaultFlagStr);
			companyEntity.setIsDb(isDb);
			companys.add(companyEntity);
		}
		lnkYjReportInfo.setCompanys(companys);
		return lnkYjReportInfo;
	}

	/**
	 * desc:变更返佣维护对象 2019年4月30日 author:zhenggang.Huang
	 */
	public ResultData saveMaintenance(Map<String, Object> reqMap) throws Exception {
		ResultData resultData = new ResultData();
		Integer checkType = 0;
//        List<String> companyNos = new ArrayList<>();
		String ids = (String) reqMap.get("ids");
		String reportIds = (String) reqMap.get("reportIds");
		if (StringUtil.isEmpty(reportIds)) {
			resultData.setFail("请至少选择一条返佣对象信息!");
			return resultData;
		}
		String yjReportFirst = (String) reqMap.get("yjReportFirst");// 上海赞助公司(123456)
		if (StringUtil.isEmpty(yjReportFirst)) {
			resultData.setFail("返佣对象一不能为空!");
			return resultData;
		}
		String companyNoOne = (String) reqMap.get("companyNo");
		if (StringUtil.isEmpty(companyNoOne)) {
			resultData.setFail("经纪公司录入有误，请输入关键字搜索后选择对应的经纪公司!");
			return resultData;
		}
		Map<String, Object> param = new HashMap<>();
		Company companyDto = null;
		if (!StringUtil.isEmpty(companyNoOne)) {
			param.put("companyNo", companyNoOne);
			param.put("companyNoOne", companyNoOne);
			companyDto = lnkYjReportMapper.selCompanyByCompanyNo(param);
			if (StringUtils.isEmpty(companyDto)) {
				resultData.setFail("经纪公司录入有误，请输入关键字搜索后选择对应的经纪公司!");
				return resultData;
			}
//        	companyNos.add(companyNo);
		}
		String companyNoTwo = (String) reqMap.get("companyNoTwo");
		String yjReportSecond = (String) reqMap.get("yjReportSecond");
		if (!StringUtil.isEmpty(yjReportSecond)) {
			if (StringUtil.isEmpty(companyNoTwo)) {
				resultData.setFail("经纪公司录入有误，请输入关键字搜索后选择对应的经纪公司!");
				return resultData;
			}
		}
//		param = new HashMap<>();
		if (!StringUtil.isEmpty(companyNoTwo)) {
			param.put("companyNo", companyNoTwo);
			param.put("companyNoTwo", companyNoTwo);
			companyDto = lnkYjReportMapper.selCompanyByCompanyNo(param);
			if (StringUtils.isEmpty(companyDto)) {
				resultData.setFail("经纪公司录入有误，请输入关键字搜索后选择对应的经纪公司!");
				return resultData;
			}
			// companyNos.add(companyNoTwo);
		}

		Integer crtEmpId = (Integer) reqMap.get("crtEmpId");

		String[] idArray = reportIds.split(",");

		// 结佣报备
		List<String> brokerageList = new ArrayList<String>();
		// 有佣金数据的报备
		List<String> yjReprotIdList = new ArrayList<String>();

		List<String> deleteList = new ArrayList<String>();

		for (String reportId : idArray) {
			param.put("reportId", reportId);
			param.put("defaultFlag", 0);// 导入对象
			
			
			List<LnkYjReport> oldYjDefaultReports = lnkYjReportMapper.selYjReportByReportId(param);
			param.put("defaultFlag", 1);// 维护对象
			List<LnkYjReport> oldYjOtherReports = lnkYjReportMapper.selYjReportByReportId(param);
			// 是否有返佣数据
			if (oldYjOtherReports != null && oldYjOtherReports.size() > 0) {
				int num = lnkYjReportMapper.decideYjReportByReportId(param);
				if (num > 0) {					
					yjReprotIdList.add(reportId);
					continue;
				}
			}
			// check 是否已结佣
			Map<String, Object> brokerageMap = lnkYjReportMapper.selLnkReportByReportId(param);
			if (brokerageMap != null && !brokerageMap.isEmpty() && brokerageMap.containsKey("brokerageStatus")) {
				String brokerage = (String) brokerageMap.get("brokerageStatus");
				if ("22003".equals(brokerage)) {// 已结佣
					brokerageList.add(reportId);
					continue;
				}
			}

			
			
			if (oldYjDefaultReports != null && oldYjDefaultReports.size() > 0) {// 导入对象
				LnkYjReport oldYjDefaultReport = oldYjDefaultReports.get(0);
				// 只输入返佣对象一:返佣对象2没有
				if (StringUtil.isEmpty(companyNoTwo)) {
					if (companyNoOne.equals(oldYjDefaultReport.getCompanyNo())) {
						if (oldYjOtherReports.size() > 0) {
							// Delete
							maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.getCompanyNo(), null, null, crtEmpId,
									"DEL",checkType);
						}
					} else {
						// insert
						maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.getCompanyNo(), companyNoOne, null, crtEmpId, "UPD",checkType);

					}
				} else {
					// 返佣对象一、二与导入对象一致
					if (companyNoOne.equals(oldYjDefaultReport.getCompanyNo())
							|| companyNoTwo.equals(oldYjDefaultReport.getCompanyNo())) {
						if (oldYjOtherReports.size() == 0) {
							// 一和导入对象相同 导入返佣2
							if (companyNoOne.equals(oldYjDefaultReport.getCompanyNo())) {
								// insert companyNoTwo
								maintainReport_insert(oldYjOtherReports,reportId, null, null, companyNoTwo, crtEmpId, "INS",checkType);
							}
							// 二和导入对象相同 导入返佣1
							if (companyNoTwo.equals(oldYjDefaultReport.getCompanyNo())) {
								// insert companyNoOne
								maintainReport_insert(oldYjOtherReports,reportId, null, companyNoOne, null, crtEmpId, "INS",checkType);
							}
							//完全一致
							//一个老得返佣对象
						} else if (oldYjOtherReports.size() == 1) {
							String oldCompanyNo = oldYjOtherReports.get(0).getCompanyNo();// 老的返佣对象
							if (companyNoTwo.equals(oldCompanyNo) || companyNoOne.equals(oldCompanyNo)) {
								deleteList.add(reportId);
								if (yjReprotIdList.contains(reportId))
									yjReprotIdList.remove(reportId);
								if (brokerageList.contains(reportId))
									brokerageList.remove(reportId);
								continue;
							}
							// 二个老的返佣对象
						} else if (oldYjOtherReports.size() == 2) {

							// 一和导入对象相同 导入返佣2
							if (companyNoOne.equals(oldYjDefaultReport.getCompanyNo())) {
								// delete orther
								// insert companyNoTwo
								maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.getCompanyNo(), null, companyNoTwo,
										crtEmpId, "UPD",checkType);
							}
							// 二和导入对象相同 导入返佣1
							if (companyNoTwo.equals(oldYjDefaultReport.getCompanyNo())) {
								// insert companyNoOne
								maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.getCompanyNo(), companyNoOne, null,
										crtEmpId, "UPD",checkType);
							}
						}
					} else {
						maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.getCompanyNo(), companyNoOne, companyNoTwo,
								crtEmpId, "UPD",checkType);
					}

				}
			}
		}

		String brokeragemessageStr = "";

		if (brokerageList.size() > 0) {
			brokeragemessageStr = "已结佣订单： ";
			for (String brkReportId : brokerageList) {
				brokeragemessageStr += brkReportId + ",";
			}
			brokeragemessageStr = brokeragemessageStr.substring(0, brokeragemessageStr.length() - 1)
					+ ",不允许维护返佣对象信息;</br>";
		}
		String yjReprotmessageStr = "";

		if (yjReprotIdList.size() > 0) {
			yjReprotmessageStr = "存在佣金数据的订单： ";
			for (String brkReportId : yjReprotIdList) {
				yjReprotmessageStr += brkReportId + ",";
			}
			yjReprotmessageStr = yjReprotmessageStr.substring(0, yjReprotmessageStr.length() - 1) + "如需调整，需冲销返佣和垫佣数据";
		}

		String msgBefore = "已维护" + (idArray.length - brokerageList.size() - yjReprotIdList.size()) + "条房源返佣对象数据;</br>";

		resultData.setReturnMsg(msgBefore + brokeragemessageStr + yjReprotmessageStr);
		logger.info(msgBefore + brokeragemessageStr + yjReprotmessageStr);
		return resultData;
	}

	/**
	 * desc:保存编辑后的返佣对象 2019年5月7日 author:zhenggang.Huang
	 */
	public ResultData saveEditMaintenance(Map<String, Object> reqMap) throws Exception {
		ResultData resultData = new ResultData();

		Integer checkType = 1;
		Integer crtEmpId = (Integer) reqMap.get("crtEmpId");
		String reportId = (String) reqMap.get("reportId");
		Map<String, Object> param = new HashMap<>();
		param.put("reportId", reportId);
		param.put("defaultFlag", 1);// 维护对象
		// 判断是否可修改返佣对象(判断原有的返佣对象是否有返佣数据)
		List<LnkYjReport> oldYjOtherReports = lnkYjReportMapper.selYjReportByReportId(param);
		param.put("defaultFlag", 0);// 导入对象
		List<LnkYjReport> oldYjDefaultReport = lnkYjReportMapper.selYjReportByReportId(param);
		
		List<String> companyNos = new ArrayList<>();

		// 判断输入经纪公司是否合法
		String companyNoOne = (String) reqMap.get("companyCode2");
		String inputCompanyName = (String) reqMap.get("inputCompanyName");
		if (!StringUtil.isEmpty(inputCompanyName) && StringUtil.isEmpty(companyNoOne)) {
			resultData.setFail("经纪公司录入有误，请输入关键字搜索后选择对应的经纪公司!");
			return resultData;
		}
		Company companyDto = null;
		if (!StringUtil.isEmpty(companyNoOne)) {
			param.put("companyNo", companyNoOne);
			param.put("companyNoOne", companyNoOne);
			companyDto = lnkYjReportMapper.selCompanyByCompanyNo(param);
			if (StringUtils.isEmpty(companyDto)) {
				resultData.setFail("经纪公司录入有误，请输入关键字搜索后选择对应的经纪公司!");
				return resultData;
			}
			companyNos.add(companyNoOne);
		}
		String companyNoTwo = (String) reqMap.get("companyCode3");
		String inputCompanyNameTwo = (String) reqMap.get("inputCompanyNameTwo");
		if (!StringUtil.isEmpty(inputCompanyNameTwo) && StringUtil.isEmpty(companyNoTwo)) {
			if (StringUtil.isEmpty(companyNoTwo)) {
				resultData.setFail("经纪公司录入有误，请输入关键字搜索后选择对应的经纪公司!");
				return resultData;
			}
		}
		if (!StringUtil.isEmpty(companyNoTwo)) {
			param.put("companyNo", companyNoTwo);
			param.put("companyNoTwo", companyNoTwo);
			companyDto = lnkYjReportMapper.selCompanyByCompanyNo(param);
			if (StringUtils.isEmpty(companyDto)) {
				resultData.setFail("经纪公司录入有误，请输入关键字搜索后选择对应的经纪公司!");
				return resultData;
			}
			companyNos.add(companyNoTwo);
		}
		if (oldYjOtherReports != null && oldYjOtherReports.size() > 0) {
			int num = lnkYjReportMapper.decideYjReportByReportId(param);
			if (num > 0) {
				resultData.setFail("订单编号:" + reportId + ",返佣对象二、三已有佣金数据，如需调整，需冲销返佣和垫佣数据!");
				return resultData;
			}
		}
		
		
		Map<String, Object> brokerageMap = lnkYjReportMapper.selLnkReportByReportId(param);
		if (brokerageMap != null && !brokerageMap.isEmpty() && brokerageMap.containsKey("brokerageStatus")) {
			String brokerage = (String) brokerageMap.get("brokerageStatus");
			if ("22003".equals(brokerage)) {// 已结佣
				resultData.setFail("订单房源" + reportId + "已结佣，不允许维护返佣对象信息");
				return resultData;
			}
		}
//		boolean flag = false;
		if (oldYjOtherReports != null && oldYjOtherReports.size() > 0) {// 导入对象
			// 只输入返佣对象一:返佣对象2没有	
			if(StringUtil.isEmpty(companyNoTwo)&&StringUtil.isEmpty(companyNoOne)) {
				maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.get(0).getCompanyNo(), null, null,
						crtEmpId, "DEL",checkType);
			}else if (StringUtil.isEmpty(companyNoTwo) ) {
				//完全一致  一个返佣对象
					if(oldYjOtherReports.size() == 1) {//一个老的返佣对象
						if(!companyNoOne.equals(oldYjOtherReports.get(0).getCompanyNo())) {
							maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.get(0).getCompanyNo(), companyNoOne, null,
									crtEmpId, "UPD",checkType);
						}
					}else {
						String oldCompanyNo1 = oldYjOtherReports.get(0).getCompanyNo();
						String oldCompanyNo2 = oldYjOtherReports.get(1).getCompanyNo();
						if(companyNoOne.equals(oldCompanyNo1)) {
							maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.get(0).getCompanyNo(), null, oldCompanyNo2,
									crtEmpId, "DEL2",checkType);
						}else if(companyNoOne.equals(oldCompanyNo2)) {
							maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.get(0).getCompanyNo(), oldCompanyNo1, null,
									crtEmpId, "DEL2",checkType);
						}else {
							maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.get(0).getCompanyNo(), companyNoOne, null,
									crtEmpId, "UPD",checkType);							
						}
					}
			} else {
				if (oldYjOtherReports.size() == 1) {
					String oldCompanyNo1 = oldYjOtherReports.get(0).getCompanyNo();
					if (companyNoOne.equals(oldCompanyNo1)) {
						//flag = true;	
//						resultData = maintainReport(oldYjOtherReports, companyNos, resultData, crtEmpId, lnkYjReport);
						maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.get(0).getCompanyNo(), null, companyNoTwo,
								crtEmpId, "INS",checkType);
					}else 
					if (companyNoTwo.equals(oldCompanyNo1)) {
						//flag = true;
//						resultData = maintainReport(oldYjOtherReports, companyNos, resultData, crtEmpId, lnkYjReport);
						maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.get(0).getCompanyNo(), companyNoOne, null,
								crtEmpId, "INS",checkType);
					}else {
						maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.get(0).getCompanyNo(), companyNoOne, companyNoTwo,
								crtEmpId, "UPD",checkType);
					}
				}				//完全一致 二个老得返佣对象
				else  if (oldYjOtherReports.size() > 1) {
					String oldCompanyNo1 = oldYjOtherReports.get(0).getCompanyNo();
					String oldCompanyNo2 = oldYjOtherReports.get(1).getCompanyNo();
					if(!companyNoOne.equals(oldCompanyNo1) &&  !companyNoOne.equals(oldCompanyNo2)&& !companyNoTwo.equals(oldCompanyNo1)&& !companyNoTwo.equals(oldCompanyNo2) ){
						maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.get(0).getCompanyNo(), companyNoOne, companyNoTwo,
								crtEmpId, "UPD",checkType);
					}else
						if(companyNoOne.equals(oldCompanyNo1) && !companyNoTwo.equals(oldCompanyNo2)){
						maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.get(0).getCompanyNo(), null, oldCompanyNo2,
								crtEmpId, "DEL2",checkType);
						maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.get(0).getCompanyNo(), null, companyNoTwo,
								crtEmpId, "INS",checkType);
					}else if(companyNoOne.equals(oldCompanyNo2) && !companyNoTwo.equals(oldCompanyNo1)){
						maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.get(0).getCompanyNo(),oldCompanyNo1, null,
								crtEmpId, "DEL2",checkType);
						maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.get(0).getCompanyNo(), null, companyNoTwo,
								crtEmpId, "INS",checkType);
						
					}else if(companyNoTwo.equals(oldCompanyNo1) && !companyNoOne.equals(oldCompanyNo2)){
						maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.get(0).getCompanyNo(), null, oldCompanyNo2,
								crtEmpId, "DEL2",checkType);
						maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.get(0).getCompanyNo(), companyNoOne, null,
								crtEmpId, "INS",checkType);
						
					}else if(companyNoTwo.equals(oldCompanyNo2) && !companyNoOne.equals(oldCompanyNo1)){
						maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.get(0).getCompanyNo(), oldCompanyNo1, null,
								crtEmpId, "DEL2",checkType);
						maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.get(0).getCompanyNo(), companyNoOne, null,
								crtEmpId, "INS",checkType);
					}
					
				}

			}
		}else {
			maintainReport_insert(oldYjOtherReports,reportId, oldYjDefaultReport.get(0).getCompanyNo(), companyNoOne, companyNoTwo,
					crtEmpId, "INS",checkType);
		}
	
		return resultData;
	}


	/**
	 * desc:保存编辑后返佣对象 2019年5月7日 author:zhenggang.Huang
	 */
	private ResultData maintainEditReport(List<LnkYjReport> oldYjReports, List<String> companyNames,
			ResultData resultData, Integer crtEmpId, LnkYjReport lnkYjReport) {

		Map<String, Object> map = new HashMap<>();
		List<String> companyNos = new ArrayList<>();
		for (String companyName : companyNames) {
			map.put("companyName", companyName);
			Company company = lnkYjReportMapper.selCompanyByCompanyNo(map);
			if (StringUtils.isEmpty(company)) {
				resultData.setFail("你输入的返佣对象:{" + companyName + "}不存在，请重新输入!");
				return resultData;
			}
			String companyNo = company.getCompanyNo();
			companyNos.add(companyNo);
		}
		if (oldYjReports != null && oldYjReports.size() > 0) {// 维护对象
			for (LnkYjReport oldYjReport : oldYjReports) {
				oldYjReport.setDelFlg(true);
				oldYjReport.setUptEmpId(crtEmpId);
				oldYjReport.setUptDt(new Date());
				lnkYjReportMapper.updateByPrimaryKeySelective(oldYjReport);
			}
		}
		// 无返佣对象
		for (String companyNo : companyNos) {
			lnkYjReport.setCompanyNo(companyNo);
			int count = lnkYjReportMapper.insertSelective(lnkYjReport);
			if (count <= 0) {
				resultData.setFail("变更佣金维护对象失败");
				return resultData;
			}
		}
		resultData.setSuccess();
		return resultData;
	}

	/**
	 * desc:返佣对象维护 
	 * 2019年5月6日
	 * checkType 1 表示编辑  0 维护返佣对象
	 *  author:zhenggang.Huang
	 */
	private void maintainReport_insert(List<LnkYjReport> oldYjOtherReports,String reportId, String defCompanyNo, String newcompanyNo1, String newcompanyNo2,
			Integer crtEmpId, String type,Integer checkType) {

		Map<String, Object> map = new HashMap<>();
		map.put("reportId", reportId);
		map.put("crtEmpId", crtEmpId);
		map.put("defcompanyNo", defCompanyNo);

		LnkYjReport lnkYjReportOne = new LnkYjReport();

		lnkYjReportOne.setReportId(reportId);
		lnkYjReportOne.setCompanyNo(newcompanyNo1);
		lnkYjReportOne.setCrtDt(new Date());
		lnkYjReportOne.setCrtEmpId(crtEmpId);
		lnkYjReportOne.setDefaultFlag(1);
		lnkYjReportOne.setDelFlg(false);

		LnkYjReport lnkYjReportTwo = new LnkYjReport();

		lnkYjReportTwo.setReportId(reportId);
		lnkYjReportTwo.setCompanyNo(newcompanyNo2);
		lnkYjReportTwo.setCrtDt(new Date());
		lnkYjReportTwo.setCrtEmpId(crtEmpId);
		lnkYjReportTwo.setDefaultFlag(1);
		lnkYjReportTwo.setDelFlg(false);
		if ("DEL".equals(type)) {			
			lnkYjReportMapper.deleteOther(map);
			if(oldYjOtherReports != null && oldYjOtherReports.size() > 0) {
				for (LnkYjReport lnkYjReport : oldYjOtherReports) {					
					map.put("companyNo", lnkYjReport.getCompanyNo());
					map.put("opkey", "去除");
					 lnkYjReportMapper.insertLog(map);
					 //编辑得时候删除返佣对象，删除对应aftTaxAmount=0，befTaxAmount=0得yjdy、yjfy、sjdy、shfy数据
					 if(checkType==1) {
						 lnkYjReportMapper.deleteDataByReportId(map);
					 }
				}
			}
		}
		if ("DEL2".equals(type)) {		
			if (StringUtil.isNotEmpty(newcompanyNo1)) {
				map.put("companyNo", newcompanyNo1);
				map.put("opkey", "去除");
				lnkYjReportMapper.deleteByReportAndCompany(map);
				lnkYjReportMapper.insertLog(map);
				//编辑得时候删除返佣对象，删除对应aftTaxAmount=0，befTaxAmount=0得yjdy、yjfy、sjdy、shfy数据
				 if(checkType==1) {
					 lnkYjReportMapper.deleteDataByReportId(map);
				 }
				
			}
			if (StringUtil.isNotEmpty(newcompanyNo2)) {
				map.put("companyNo", newcompanyNo2);
				map.put("opkey", "去除");
				lnkYjReportMapper.deleteByReportAndCompany(map);
				lnkYjReportMapper.insertLog(map);
				//编辑得时候删除返佣对象，删除对应aftTaxAmount=0，befTaxAmount=0得yjdy、yjfy、sjdy、shfy数据
				 if(checkType==1) {
					 lnkYjReportMapper.deleteDataByReportId(map);
				 }
			}
		}
		if ("INS".equals(type)) {
			if (StringUtil.isNotEmpty(newcompanyNo1)) {
				int count1 = lnkYjReportMapper.insertSelective(lnkYjReportOne);
				map.put("companyNo", newcompanyNo1);
				map.put("opkey", "新增");
				lnkYjReportMapper.insertLog(map);
//				Company c1 = lnkYjReportMapper.selCompanyByCompanyNo(map);
//				LnkYjReportLog lnkYjReportLog = getYjReportLog(reportId,newcompanyNo1,crtEmpId,c1.getCompanyName(),1);
//				int yjReportLogCount = lnkYjReportMapper.insertLnkYjReportLog(lnkYjReportLog);
			}
			if (StringUtil.isNotEmpty(newcompanyNo2)) {
				int count2 = lnkYjReportMapper.insertSelective(lnkYjReportTwo);
				map.put("companyNo", newcompanyNo2);
				map.put("opkey", "新增");
				lnkYjReportMapper.insertLog(map);
//				Company c2 = lnkYjReportMapper.selCompanyByCompanyNo(map);
//				LnkYjReportLog lnkYjReportLog = getYjReportLog(reportId,newcompanyNo2,crtEmpId,c2.getCompanyName(),1);
//				int yjReportLogCount = lnkYjReportMapper.insertLnkYjReportLog(lnkYjReportLog);
			}
		}
		if ("UPD".equals(type)) {
			lnkYjReportMapper.deleteOther(map);
			if(oldYjOtherReports != null && oldYjOtherReports.size() > 0) {
				for (LnkYjReport lnkYjReport : oldYjOtherReports) {
					map.put("companyNo", lnkYjReport.getCompanyNo());
					map.put("opkey", "去除");
					lnkYjReportMapper.insertLog(map);
//					Company oldYjOther =lnkYjReportMapper.selCompanyByCompanyNo(map);
//					LnkYjReportLog lnkYjReportLog = getYjReportLog(reportId,lnkYjReport.getCompanyNo(),crtEmpId,oldYjOther.getCompanyName(),0);
//					int yjReportLogCount = lnkYjReportMapper.insertLnkYjReportLog(lnkYjReportLog);
				}
			}
			if (StringUtil.isNotEmpty(newcompanyNo1)) {
				int count1 = lnkYjReportMapper.insertSelective(lnkYjReportOne);
				map.put("companyNo", newcompanyNo1);
				map.put("opkey", "新增");
				lnkYjReportMapper.insertLog(map);
//				Company newC1 =lnkYjReportMapper.selCompanyByCompanyNo(map);
//				LnkYjReportLog lnkYjReportLog = getYjReportLog(reportId,newcompanyNo1,crtEmpId,newC1.getCompanyName(),1);
//				int yjReportLogCount = lnkYjReportMapper.insertLnkYjReportLog(lnkYjReportLog);
			}
			if (StringUtil.isNotEmpty(newcompanyNo2)) {
				int count2 = lnkYjReportMapper.insertSelective(lnkYjReportTwo);
				map.put("companyNo", newcompanyNo2);
				map.put("opkey", "新增");
				lnkYjReportMapper.insertLog(map);
//				Company newC2 =lnkYjReportMapper.selCompanyByCompanyNo(map);
//				LnkYjReportLog lnkYjReportLog = getYjReportLog(reportId,newcompanyNo2,crtEmpId,newC2.getCompanyName(),1);
//				int yjReportLogCount = lnkYjReportMapper.insertLnkYjReportLog(lnkYjReportLog);
			}
		}
	}

	/**
	 * desc:插入维护返佣对象记录表
	 * 2019年5月10日
	 * author:zhenggang.Huang
	 */
	private LnkYjReportLog getYjReportLog(String reportId, String newcompanyNo, Integer crtEmpId,String companyName,Integer type) {
		LnkYjReportLog lnkYjReportLog = new LnkYjReportLog();
		lnkYjReportLog.setReportId(reportId);
		lnkYjReportLog.setCompanyNo(newcompanyNo);
		if(type == 1) {//插入
			lnkYjReportLog.setContent("增加返佣对象\""+companyName+"("+newcompanyNo+")\"");
		}else if(type==0) {//更新
			lnkYjReportLog.setContent("去除返佣对象\""+companyName+"("+newcompanyNo+")\"");
		}
		lnkYjReportLog.setCrtEmpId(crtEmpId);
		return lnkYjReportLog;
	}

	/**
	 * desc:返佣对象维护 2019年5月6日 author:zhenggang.Huang
	 */
	private ResultData maintainReport(List<LnkYjReport> oldYjReports, List<String> companyNos, ResultData resultData,
			Integer crtEmpId, LnkYjReport lnkYjReport) {
		Map<String,Object> map = new HashMap<>();
		if (oldYjReports != null && oldYjReports.size() > 0) {// 维护对象
			for (LnkYjReport oldYjReport : oldYjReports) {
				oldYjReport.setDelFlg(true);
				oldYjReport.setUptEmpId(crtEmpId);
				oldYjReport.setUptDt(new Date());
				lnkYjReportMapper.updateByPrimaryKeySelective(oldYjReport);
				map.put("companyNo", oldYjReport.getCompanyNo());
				Company c = lnkYjReportMapper.selCompanyByCompanyNo(map);
				LnkYjReportLog lnkYjReportLog = getYjReportLog(lnkYjReport.getReportId(),oldYjReport.getCompanyNo(),crtEmpId,c.getCompanyName(),0);
				int yjReportLogCount = lnkYjReportMapper.insertLnkYjReportLog(lnkYjReportLog);
			}
		}
		// 无返佣对象
		if(companyNos != null && companyNos.size()>0) {
			for (String companyNum : companyNos) {
				lnkYjReport.setCompanyNo(companyNum);
				int count = lnkYjReportMapper.insertSelective(lnkYjReport);
				map.put("companyNo", companyNum);
				Company c = lnkYjReportMapper.selCompanyByCompanyNo(map);
				LnkYjReportLog lnkYjReportLog = getYjReportLog(lnkYjReport.getReportId(),companyNum,crtEmpId,c.getCompanyName(),1);
				int yjReportLogCount = lnkYjReportMapper.insertLnkYjReportLog(lnkYjReportLog);
			}
		}
		resultData.setSuccess();
		return resultData;
	}

	/**
	 * desc:模糊查询返佣对象 2019年5月5日 author:zhenggang.Huang
	 */
	public ResultData getCompanyByCondition(Map<String, Object> queryParam) throws Exception {
		ResultData<List<YjCompany>> resultData = new ResultData<>();

		List<YjCompany> companys = lnkYjReportMapper.getYjCompany(queryParam);
		if (companys != null && companys.size() > 0) {
			resultData.setReturnData(companys);
			resultData.setSuccess();
		}
		return resultData;
	}
}
