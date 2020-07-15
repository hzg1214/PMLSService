package cn.com.eju.deal.scene.inCommission.service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.scene.inCommission.InCommissionLogDto;
import cn.com.eju.deal.dto.scene.inCommission.InCommissionResultDto;
import cn.com.eju.deal.fangyou.service.FangyouService;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateMapper;
import cn.com.eju.deal.houseLinkage.report.dao.ReportDetailMapper;
import cn.com.eju.deal.houseLinkage.report.dao.ReportMapper;
import cn.com.eju.deal.houseLinkage.statistic.service.StatisticService;
import cn.com.eju.deal.scene.inCommission.dao.InCommissionLogMapper;
import cn.com.eju.deal.scene.inCommission.dao.SceneInCommissionMapper;
import cn.com.eju.deal.store.service.StoreService;
import cn.com.eju.deal.scene.inCommission.model.InCommissionLog;

/**
 * service层
 *
* @author (sucen)
* @date 2017年8月7日 下午9:25:30
 */
@Service("sceneInCommissionService")
public class SceneInCommissionService extends BaseService {
	
	@Resource
    private EstateMapper estateMapper;
	
	@Resource
    private StatisticService statisticService;
	
	@Resource
    private ReportMapper reportMapper;
	
	@Resource
    private ReportDetailMapper reportDetailMapper;
	
    @Resource
    private StoreService storeService;

    @Resource
    private FangyouService fangyouService;

    @Resource
    private ContractMapper contractMapper;

    @Resource
    SceneInCommissionMapper sceneInCommissionMapper;
     
    @Resource
    InCommissionLogMapper inCommissionLogMapper;
     
     /**
      * 得到内佣List
      * @return
      */
     public List<InCommissionResultDto> getInCommissionList(Map<String,Object> map)
     {
    	 List<InCommissionResultDto> list = null;
	     try {
	    		 list = sceneInCommissionMapper.getInCommissionList(map);
		  } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		
		  }
	    	 
	    	 return list;
    }
    
        


     /*
      * 获得佣金详情列表，并提交日志
      * created by wang kanlin 2017/8/15
      */
     public List<InCommissionResultDto> getInCommissionListForExport(Map<String,Object> map, InCommissionLogDto dto) {
 		List<InCommissionResultDto> list = null;
 		try{
 			list = sceneInCommissionMapper.getInCommissionList(map);
			String templateType = map.get("estateType").toString(); 
 			if (list != null) for (InCommissionResultDto icDto : list){
	 			InCommissionLog icLog1 = new InCommissionLog();
	 			BeanUtils.copyProperties(dto, icLog1);
	 			icLog1.setInCommissionResult(icDto, 1);
	 			inCommissionLogMapper.create(icLog1);
	 			if (templateType.equals("20602")) continue;
	 			InCommissionLog icLog2 = new InCommissionLog();
	 			BeanUtils.copyProperties(dto, icLog2);
	 			icLog2.setInCommissionResult(icDto, 2);
	 			inCommissionLogMapper.create(icLog2);
 			}
 		}catch (Exception e) {
 			// TODO: handle exception
 			e.printStackTrace();
 		}
 		
 		return list;
 	}
     
     /*
      * 获得对应城市关账年月
      * created by wang kanlin 2017/8/15
      */
     public Map<String, String> getInCommissionSwitchMonth(Map<String,Object> map){

  		Map<String, String> switchMonth = null;
		try {
			switchMonth = sceneInCommissionMapper.getInCommissionSwitchMonth(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return switchMonth;
     }
   
    /**
     * 导入新增
     *
     * @param param
     * @return
     */
    public ResultData<String> insertLnkImport(Map<?, ?> param)
    	throws Exception
    {
    	ResultData<String> resultData = new ResultData<String>();
    	int result = estateMapper.insertLnkImport(param);
    	resultData.setReturnData(String.valueOf(result));
        return resultData;
    }
    
    /**
     * 导入更新
     *
     * @param param
     * @return
     */
    public ResultData<String> updateLnkImport(Map<?, ?> param)
    	throws Exception
    {
    	ResultData<String> resultData = new ResultData<String>();
    	int result = estateMapper.updateLnkImport(param);
    	resultData.setReturnData(String.valueOf(result));
        return resultData;
    }
    
    /**
     * 导入查询
     *
     * @param estateId
     * @return
     */
    public ResultData<Map<?,?>> getLnkImportByReportId(Map<?,?> map)
    	throws Exception
    {
    	ResultData<Map<?,?>> resultData = new ResultData<Map<?,?>>();
    	Map<?,?> list = estateMapper.getLnkImportByReportId(map);
    	resultData.setReturnData(list);
        return resultData;
    }
    
    /**
     * 导入，写入Log日志
     * @param map
     */
    public int createLogLnkImport(Map<?,?> queryParam) throws Exception
    {
		InCommissionLog icLog = new InCommissionLog();
		icLog.setOperType(queryParam.get("operType").toString());
		icLog.setOperUserId(Integer.valueOf(queryParam.get("operUserId").toString()));
		icLog.setOperUserName(queryParam.get("operUserName").toString());
		icLog.setOperDt(new Date());
		icLog.setFileName(queryParam.get("fileName").toString());
		icLog.setRemarks("");
		icLog.setAmountType(queryParam.get("amountType").toString());
		icLog.setYear(Integer.valueOf(queryParam.get("year").toString()));
		icLog.setProjectNo("");
		icLog.setTemplateType(queryParam.get("templateType").toString());
		icLog.setEstateId(queryParam.get("estateId").toString());
		icLog.setEstateNm(queryParam.get("estateNm").toString());
		icLog.setReportId(queryParam.get("reportId").toString());
		icLog.setDetailId(Integer.valueOf(queryParam.get("detailId").toString()));
		icLog.setBuildingNo(queryParam.get("buildingNo").toString());
		icLog.setNum(Integer.valueOf(queryParam.get("num").toString()));
		icLog.setDealAmount(queryParam.get("dealAmount").toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		icLog.setDealDate(sdf.parse(queryParam.get("dealDate").toString()));
		icLog.setSubTotalPreTax(toDoubleChange(queryParam.get("subTotalPreTax")));
		icLog.setBeforeAmountPreTax(toDoubleChange(queryParam.get("beforeAmountPreTax")));
		icLog.setJanPreTax(toDoubleChange(queryParam.get("janPreTax")));
		icLog.setFebPreTax(toDoubleChange(queryParam.get("febPreTax")));
		icLog.setMarPreTax(toDoubleChange(queryParam.get("marPreTax")));
		icLog.setAprPreTax(toDoubleChange(queryParam.get("aprPreTax")));
		icLog.setMayPreTax(toDoubleChange(queryParam.get("mayPreTax")));
		icLog.setJunPreTax(toDoubleChange(queryParam.get("junPreTax")));
		icLog.setJulPreTax(toDoubleChange(queryParam.get("julPreTax")));
		icLog.setAugPreTax(toDoubleChange(queryParam.get("augPreTax")));
		icLog.setSepPreTax(toDoubleChange(queryParam.get("sepPreTax")));
		icLog.setOctPreTax(toDoubleChange(queryParam.get("octPreTax")));
		icLog.setNovPreTax(toDoubleChange(queryParam.get("novPreTax")));
		icLog.setDecPreTax(toDoubleChange(queryParam.get("decPreTax")));
		icLog.setSubTotalAfterTax(toDoubleChange(queryParam.get("subTotalAfterTax")));
		icLog.setBeforeAmountAfterTax(toDoubleChange(queryParam.get("beforeAmountAfterTax")));
		icLog.setJanAfterTax(toDoubleChange(queryParam.get("janAfterTax")));
		icLog.setFebAfterTax(toDoubleChange(queryParam.get("febAfterTax")));
		icLog.setMarAfterTax(toDoubleChange(queryParam.get("marAfterTax")));
		icLog.setAprAfterTax(toDoubleChange(queryParam.get("aprAfterTax")));
		icLog.setMayAfterTax(toDoubleChange(queryParam.get("mayAfterTax")));
		icLog.setJunAfterTax(toDoubleChange(queryParam.get("junAfterTax")));
		icLog.setJulAfterTax(toDoubleChange(queryParam.get("julAfterTax")));
		icLog.setAugAfterTax(toDoubleChange(queryParam.get("augAfterTax")));
		icLog.setSepAfterTax(toDoubleChange(queryParam.get("sepAfterTax")));
		icLog.setOctAfterTax(toDoubleChange(queryParam.get("octAfterTax")));
		icLog.setNovAfterTax(toDoubleChange(queryParam.get("novAfterTax")));
		icLog.setDecAfterTax(toDoubleChange(queryParam.get("decAfterTax")));
		return inCommissionLogMapper.create(icLog);
    }
    
    /**
     * 导入去年数据后，更新今年的当年以前
     * @param map
     */
    public ResultData<String> updateLnkImportBefore(Map<?, ?> param)
        	throws Exception
    {
    	ResultData<String> resultData = new ResultData<String>();
    	int result = estateMapper.updateLnkImportBefore(param);
    	resultData.setReturnData(String.valueOf(result));
        return resultData;
    }
    
    private Double toDoubleChange(Object value)
    {
    	if(null == value)
    	{
    		return 0.0;
    	}
    	if("".equals(value.toString()))
    	{
    		return 0.0;
    	}
    	return Double.valueOf(value.toString());
    }
}
