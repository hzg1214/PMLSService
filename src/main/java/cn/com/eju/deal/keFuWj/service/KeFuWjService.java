package cn.com.eju.deal.keFuWj.service;

import java.util.*;

import javax.annotation.Resource;

import cn.com.eju.deal.keFuWj.dao.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.common.model.City;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.keFuWj.dto.KeFuWjDto;
import cn.com.eju.deal.keFuWj.dto.KefuWjDTmDto;
import cn.com.eju.deal.keFuWj.model.KefuWjCitymapping;
import cn.com.eju.deal.keFuWj.model.KefuWjDDa;
import cn.com.eju.deal.keFuWj.model.KefuWjDTm;
import cn.com.eju.deal.keFuWj.model.KefuWjH;

@Service("keFuWjService")
public class KeFuWjService extends BaseService {

	@Resource
	private KefuWjHMapper kefuWjHMapper;
	
	@Resource
	private KefuWjDTmMapper kefuWjDTmMapper;
	
	@Resource
	private KefuWjDDaMapper kefuWjDDaMapper;
	
	@Resource
	private KefuWjCitymappingMapper kefuWjCitymappingMapper;

	@Resource
	private KefuWjSatisfactionMapper kefuWjSatisfactionMapper;

	@Resource
	private KefuWjSatisfactionEvaluationMapper kefuWjSatisfactionEvaluationMapper;

    /**
     * 
     * desc:适用城市列表
     * 2019年6月18日
     * author:zhenggang.Huang
     */
    public ResultData<List<City>> getWjCityList() throws Exception {
    	ResultData<List<City>> resultData = new ResultData<>();
    	List<City> keFuWjList = kefuWjHMapper.getWjCityList();
    	if (keFuWjList != null && keFuWjList.size() > 0) {
    		resultData.setReturnData(keFuWjList);
    		resultData.setSuccess();
    	}
    	return resultData;
    }

    /**
     * 查询客服反馈工单列表
     * @param
     * @return
     */
    public List<Map<String, Object>> getKeFuWjList(Map<String, Object> queryParam) throws Exception {
    	if(queryParam.containsKey("wjCitys") && !StringUtils.isEmpty(queryParam.get("wjCitys"))) {
        	List<String> centerIdList = (List<String>) queryParam.get("wjCitys");
        	String centerIds = "";
        	for (String integer : centerIdList) {
        		centerIds +="'"+integer+"',";
        	}
        	if(!StringUtil.isEmpty(centerIds)) {
        		centerIds = centerIds.substring(0,centerIds.length()-1);
        	}
        	queryParam.put("wjCitys", centerIds);
        }
    	return kefuWjHMapper.getKeFuWjList(queryParam);
    }
    
    /**
     * desc:获取问卷已调查列表
     * 2019年7月29日
     * author:zhenggang.Huang
     */
    public ResultData<List<Map<String, Object>>> getInvestedList(Map<String, Object> queryParam) throws Exception {
    	ResultData<List<Map<String, Object>>> resultData = new ResultData<List<Map<String, Object>>>();
        resultData.setFail();
        List<Map<String, Object>> keFuOrderDtoList = kefuWjHMapper.getInvestedList(queryParam);
        if (keFuOrderDtoList != null && keFuOrderDtoList.size() > 0) {
            resultData.setReturnData(keFuOrderDtoList);
            resultData.setTotalCount(keFuOrderDtoList.size()+"");
            resultData.setSuccess();
        }
        return resultData;
    }

    public int finalize(Map<String, Object> queryParam) {
		int count = 0;
		if(queryParam.containsKey("id")){
			KefuWjH kefuWjH = new KefuWjH();
			kefuWjH.setId(Integer.valueOf(queryParam.get("id").toString()));
			kefuWjH.setWjStatus("24702");
			kefuWjH.setWjConfirmUser(Integer.valueOf(queryParam.get("wjConfirmUser").toString()));
			kefuWjH.setWjConfirmDate(new Date());
			count = kefuWjHMapper.updateByPrimaryKeySelective(kefuWjH);
		}
		return count;
    }

	public KeFuWjDto queryKeFuWjList(int id) {
		KeFuWjDto keFuWjDto = kefuWjHMapper.queryKeFuWjList(id);
		return keFuWjDto;
	}

	public KefuWjH queryKeFuWjHById(Integer id) {
		KefuWjH kefuWjH = kefuWjHMapper.selectByPrimaryKey(id);
		return kefuWjH;
	}

	public ResultData<?> queryCityIsAvailable(String strs,Integer id) {
		ResultData<?> resultData = new ResultData<>();
		String[] strList = strs.split(",");
		String msg = "";
		for (int i = 0;i<strList.length;i++){
			KefuWjCitymapping kefuWjCitymappings = kefuWjCitymappingMapper.queryCityIsAvailable(strList[i],id);
			if(kefuWjCitymappings!=null){
				msg += kefuWjCitymappings.getCityName()+"已绑定模板"+kefuWjCitymappings.getWjCode()+",";
			}
		}
		resultData.setReturnMsg(msg);
		return resultData;
	}

    public ResultData<KefuWjCitymapping> update(Map<?, ?> queryParam) {
        ResultData<KefuWjCitymapping> resultData = new ResultData<>();
        String strs = queryParam.get("cityNos").toString();
        String type = queryParam.get("type").toString();
        Integer id = Integer.valueOf(queryParam.get("id").toString());
        Integer userCreate = Integer.valueOf(queryParam.get("userCreate").toString());
        KefuWjH wjH = kefuWjHMapper.selectByPrimaryKey(id);
		String[] strList = new String[]{};
		if(strs!=""){
			strList = strs.split(",");
		}
		if(strList.length!=0){
			//针对传入str做新增操作
			for (int i = 0;i<strList.length;i++){
				KefuWjCitymapping kefuWjCitymapping = kefuWjCitymappingMapper.queryCityAvailable(strList[i]);
				try{
					if(type.equals("1")){
						if(kefuWjCitymapping!=null){
							//判断是否是当前wjcode，相等则不更新
							if(wjH.getWjCode().equals(kefuWjCitymapping.getWjCode())){
								continue;
							}else{
								//删除kefuWjCitymappings
								KefuWjCitymapping kefuWjCity = new KefuWjCitymapping();
								kefuWjCity.setId(kefuWjCitymapping.getId());
								kefuWjCity.setEnbledFlag("0");
								kefuWjCitymappingMapper.updateByPrimaryKeySelective(kefuWjCity);
								//如果没有城市则修改kefuWjH为未启用
								int num = kefuWjCitymappingMapper.queryNumByWjCode(kefuWjCitymapping.getWjCode());
								if(num==0){
									KefuWjH kefuWjH = new KefuWjH();
									kefuWjH.setWjCode(kefuWjCitymapping.getWjCode());
									kefuWjH.setWjStatus(DictionaryConstants.Wj_Status_Not_Enabled);
									kefuWjHMapper.updateByWjCode(kefuWjH);
								}
								this.updateKefuWjCity(strList[i],id,userCreate,wjH);
							}
						}else{
							this.updateKefuWjCity(strList[i],id,userCreate,wjH);
						}
					}else if(type.equals("2")){
						if(kefuWjCitymapping!=null){
							continue;
						}else{
							this.updateKefuWjCity(strList[i],id,userCreate,wjH);
						}
					}
				}catch (Exception e) {
					resultData.setFail();
				}
			}
		}
		//针对传入str做删除操作
		List<KefuWjCitymapping> kefuWjCitymappings = kefuWjCitymappingMapper.getWjCheckCityList(id);
		for (KefuWjCitymapping kefuWjCitymapping : kefuWjCitymappings){
			if(strList.length==0){
				//删除kefuWjCitymapping
				KefuWjCitymapping kefuWjCity = new KefuWjCitymapping();
				kefuWjCity.setId(kefuWjCitymapping.getId());
				kefuWjCity.setEnbledFlag("0");
				kefuWjCitymappingMapper.updateByPrimaryKeySelective(kefuWjCity);
			}else{
				if(Arrays.asList(strList).contains(kefuWjCitymapping.getCityNo())){
					continue;
				}else{
					//删除kefuWjCitymapping
					KefuWjCitymapping kefuWjCity = new KefuWjCitymapping();
					kefuWjCity.setId(kefuWjCitymapping.getId());
					kefuWjCity.setEnbledFlag("0");
					kefuWjCitymappingMapper.updateByPrimaryKeySelective(kefuWjCity);
				}
			}
		}
        return resultData;
    }

    private void updateKefuWjCity(String cityNo, Integer id, Integer userCreate, KefuWjH wjH) {
        //新增kefuWjCitymappings
        KefuWjCitymapping kefuWjCitymapping = new KefuWjCitymapping();
        kefuWjCitymapping.setCityNo(cityNo);
        kefuWjCitymapping.setWjCode(wjH.getWjCode());
        kefuWjCitymapping.setEnbledFlag("1");
        kefuWjCitymapping.setDateCreate(new Date());
        kefuWjCitymapping.setUserCreate(userCreate);
        kefuWjCitymapping.setDelFlag(false);
        kefuWjCitymappingMapper.insertOrUpdate(kefuWjCitymapping);
        //修改kefuWjH已启用
		if(!wjH.getWjStatus().equals(DictionaryConstants.Wj_Status_Enabled)){
			KefuWjH kefuWjH = new KefuWjH();
			kefuWjH.setId(id);
			kefuWjH.setWjStatus(DictionaryConstants.Wj_Status_Enabled);
			kefuWjHMapper.updateByPrimaryKeySelective(kefuWjH);
		}
    }
	
	
    /**
     * desc:新增导入
     * 2019年6月20日
     * author:zhenggang.Huang
     */
    public ResultData<String> imputAdd(KeFuWjDto keFuWjDto)
    	throws Exception
    {
    	ResultData<String> resultData = new ResultData<String>();
    	int count = 0;
    	if(keFuWjDto != null) {//问卷主表
    		KefuWjH kefuWjH = getKefuWjH(keFuWjDto);
    		//插入主表
    		count = kefuWjHMapper.insertSelective(kefuWjH);
    		//题目
    		List<KefuWjDTmDto> wjDTmList = keFuWjDto.getWjDTmList();
    		if(wjDTmList != null && wjDTmList.size() >0) {
    			for (KefuWjDTmDto kefuWjDTmDto : wjDTmList) {
    				KefuWjDTm kefuWjDTm = getKefuWjDTm(kefuWjDTmDto,kefuWjH);
    				//插入题目
    				count = kefuWjDTmMapper.insertSelective(kefuWjDTm);
    				List<KefuWjDDa> kefuWjDDaList = kefuWjDTmDto.getWjDDaList();//答案
    				if(kefuWjDDaList != null && kefuWjDDaList.size() > 0) {
    					for (KefuWjDDa kefuWjDDa : kefuWjDDaList) {
    						KefuWjDDa kefuWjDDa1 = getKefuWjDDa(kefuWjDDa,kefuWjDTm,kefuWjDTmDto);
    						if(kefuWjDDa1 != null) {
    							//插入答案
    							count =kefuWjDDaMapper.insertSelective(kefuWjDDa1);
    						}
						}
    				}
				}
    		}
    	}
    	if(count == 0) {
    		 resultData.setReturnMsg("导入数据失败！");
    	}
    	
        return resultData;
    }

    /**
     * desc:组装答案 
     * 2019年6月21日
     * author:zhenggang.Huang
     */
    private KefuWjDDa getKefuWjDDa(KefuWjDDa kefuWjDDa, KefuWjDTm kefuWjDTm,KefuWjDTmDto kefuWjDTmDto) {
    	if(kefuWjDDa.getIndex().equals(kefuWjDTmDto.getIndex())) {
    		kefuWjDDa.setWjdid(kefuWjDTm.getId());//题目id
    		kefuWjDDa.setWjhid(kefuWjDTm.getWjhid());//问卷id
    		kefuWjDDa.setDelFlag(false);
    		kefuWjDDa.setDateCreate(new Date());
    		return kefuWjDDa;
    	}
    	return null;
	}

	/**
     * desc:组装题目
     * 2019年6月21日
     * author:zhenggang.Huang
     */
    private KefuWjDTm getKefuWjDTm(KefuWjDTmDto kefuWjDTmDto,KefuWjH kefuWjH) {
    	KefuWjDTm kefuWjDTm = new KefuWjDTm();
    	String wjtmflType =kefuWjDTmDto.getWjtmflType(); //业务类型
		String wjtmType =kefuWjDTmDto.getWjtmType();//题型 (单选、多选、问答题)
		String wjtmValue = kefuWjDTmDto.getWjtmValue();//题目
		Integer userCreate = kefuWjDTmDto.getUserCreate();
		Integer wjtmSortindex = kefuWjDTmDto.getWjtmSortindex();
		Integer wjtmMaxScore = kefuWjDTmDto.getWjtmMaxScore();
		kefuWjDTm.setWjhid(kefuWjH.getId());//问卷id
		kefuWjDTm.setWjtmflType(wjtmflType);
		kefuWjDTm.setWjtmType(wjtmType);
		kefuWjDTm.setWjtmValue(wjtmValue);
		kefuWjDTm.setUserCreate(userCreate);
		kefuWjDTm.setDateCreate(new Date());
		kefuWjDTm.setDelFlag(false);
		kefuWjDTm.setWjtmSortindex(wjtmSortindex);
		kefuWjDTm.setWjtmMaxScore(wjtmMaxScore);
		return kefuWjDTm;
	}

    /**
     * desc:获取题型(单选、多选、问答题)
     * 2019年6月28日
     * author:zhenggang.Huang
     */
    private String getWjtmType(String wjtmType) {
    	if(StringUtil.isEmpty(wjtmType)) {
			return wjtmType;
		}
		switch(wjtmType){
		    case "单选题" :
		    	wjtmType = DictionaryConstants.Wj_wjtmType_dx;
		       break; 
		    case "多选题" :
		    	wjtmType = DictionaryConstants.Wj_wjtmType_dxs;
		    	break; 
		    case "问答题" :
		    	wjtmType = DictionaryConstants.Wj_wjtmType_wd;
		       break; 
		    default :
		    	wjtmType = null;
		    	
		}
		return wjtmType;
	}

	/**
     * desc:获取业务类型
     * 2019年6月28日
     * author:zhenggang.Huang
     */
	private String getWjtmflType(String wjtmflType) {
		if(StringUtil.isEmpty(wjtmflType)) {
			return wjtmflType;
		}
		switch(wjtmflType){
		    case "品牌服务" :
		    	wjtmflType = DictionaryConstants.Wj_wjtmflType_pp;
		       break; 
		    case "培训服务" :
		    	wjtmflType = DictionaryConstants.Wj_wjtmflType_px;
		    	break; 
		    case "招聘服务" :
		    	wjtmflType = DictionaryConstants.Wj_wjtmflType_zp;
		       break; 
		    case "系统服务" :
		    	wjtmflType = DictionaryConstants.Wj_wjtmflType_xt;
		    	break; 
		    case "交易服务" :
		    	wjtmflType = DictionaryConstants.Wj_wjtmflType_jy;
		    	break; 
		    case "公盘业务" :
		    	wjtmflType = DictionaryConstants.Wj_wjtmflType_gp;
		    	break; 
		    case "联动业务" :
		    	wjtmflType = DictionaryConstants.Wj_wjtmflType_ld;
		    	break; 
		    case "其他" :
		    	wjtmflType = DictionaryConstants.Wj_wjtmflType_qt;
		    	break; 
		    default :
		    	wjtmflType = null;
		}
		return wjtmflType;
	}

	/**
     * desc:组装主表
     * 2019年6月21日
     * author:zhenggang.Huang
     */
	private KefuWjH getKefuWjH(KeFuWjDto keFuWjDto) {
		KefuWjH kefuWjH = new KefuWjH();
		String wjCode = keFuWjDto.getWjCode();
		String wjName = keFuWjDto.getWjName();
		String wjTitle = keFuWjDto.getWjTitle();
		Integer userCreate = keFuWjDto.getUserCreate();
		kefuWjH.setWjCode(wjCode);
		kefuWjH.setWjName(wjName);
		kefuWjH.setWjTitle(wjTitle);
		kefuWjH.setUserCreate(userCreate);
		kefuWjH.setWjStatus(DictionaryConstants.Wj_Status_Initition);//草签
		kefuWjH.setDelFlag(false);
		kefuWjH.setDateCreate(new Date());
		return kefuWjH;
	}
    
	public int remove(Map<String, Object> queryParam) {
		int count = 0;
		if(queryParam.containsKey("id")){
			count = kefuWjHMapper.removeOne(Integer.valueOf(queryParam.get("id").toString()));
		}
		return count;
	}

    public ResultData getWjCheckCityList(Integer id) {
		ResultData<List<KefuWjCitymapping>> resultData = new ResultData<>();
		List<KefuWjCitymapping> keFuWjList = kefuWjCitymappingMapper.getWjCheckCityList(id);
		if (keFuWjList != null && keFuWjList.size() > 0) {
			resultData.setReturnData(keFuWjList);
			resultData.setSuccess();
		}
		return resultData;
    }

    public ResultData getEvaluationList(Map<String, Object> reqMap) {
		ResultData<List<Map<String, Object>>> resultData = new ResultData<List<Map<String, Object>>>();
		resultData.setFail();
		List<Map<String, Object>> keFuEvaluationList = kefuWjHMapper.getEvaluationList(reqMap);
		if (keFuEvaluationList != null && keFuEvaluationList.size() > 0) {
			resultData.setReturnData(keFuEvaluationList);
			resultData.setTotalCount(keFuEvaluationList.size()+"");
			resultData.setSuccess();
		}
		return resultData;
    }

	public ResultData getEvaluationData(Integer id) {
		ResultData resultData = new ResultData();
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> evaluationData = kefuWjSatisfactionMapper.getSurveyData(id);
		if (null != evaluationData) {
			map.put("data", evaluationData);
			String wjdcStatus = (String) evaluationData.get("wjdcStatus");
			if (wjdcStatus.equals("25002")) {//如果已完成 获取记录
				List<Map<String, Object>> evaluationList = kefuWjSatisfactionEvaluationMapper.getEvaluationList(id);
				if (null != evaluationList && !evaluationList.isEmpty()) {
					map.put("evaluationList", evaluationList);
				}
			}
		}
		resultData.setReturnData(map);
		return resultData;
	}
}

