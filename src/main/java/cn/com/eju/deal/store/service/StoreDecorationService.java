package cn.com.eju.deal.store.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.common.dao.OmsInteractiveMapper;
import cn.com.eju.deal.common.model.Decoration;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.store.StoreDecorationDto;
import cn.com.eju.deal.store.dao.StoreDecorationMapper;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.store.model.StoreDecoration;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.User;

/**
 * 门店装修Service
 * @author  wushuang
 * @date 2016年8月16日 下午6:16:08
 */
@Service("storeDecorationService")
public class StoreDecorationService extends BaseService<StoreDecorationDto>
{
    @Resource(name = "storeDecorationMapper")
    private StoreDecorationMapper storeDecorationMapper;
    
    @Resource(name = "storeMapper")
    private StoreMapper storeMapper;
    
    @Resource(name = "omsInteractiveMapper")
    private OmsInteractiveMapper omsInteractiveMapper;
    
    @Resource
    private UserMapper userMapper;
    /**
     * 
    * @Title: getStoreDecoration
    * @Description: 查询门店装修状态列表
    * @return
    * @throws Exception
     */
    public ResultData<List<StoreDecorationDto>> getStoreDecoration(Map<String, Object> reqMap)
        throws Exception
    {
        ResultData<List<StoreDecorationDto>> resultData = new ResultData<List<StoreDecorationDto>>();
        List<StoreDecorationDto> list = storeDecorationMapper.getStoreDecoration(reqMap);
        resultData.setReturnData(list);
        return resultData;
    }

    /**
     * 
        * @Title: insertBatch
        * @Description: 批量插入门店装修
        * @return
        * @throws Exception
     */
    public int insertBatch(List<StoreDecorationDto> list)
        throws Exception
    {
        
        List<StoreDecoration> insertList = new ArrayList<StoreDecoration>();      
        for(StoreDecorationDto storeDecorationDto : list){
            StoreDecoration storeDecoration = new StoreDecoration();
            BeanUtils.copyProperties(storeDecorationDto, storeDecoration);
            insertList.add(storeDecoration);
        }
        
        int count = storeDecorationMapper.insertBatch(insertList);
        return count;
    }

    /**
     * 
        * @Title: addDecorationRecord
        * @Description: 新增装修记录
        * @return
        * @throws Exception
     */
    public ResultData<Integer> addDecorationRecord(StoreDecorationDto storeDecorationDto)
        throws Exception
    {
        //返回定义
        ResultData<Integer> resultData = new ResultData<Integer>();
        
        //  查询出所需要插入的数据 
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("storeId", storeDecorationDto.getStoreId());
        
        // 查询的是 B/A-B && 审合通过
        StoreDecorationDto decorationDto = storeMapper.getDecorationInfo(reqMap);
        if(null != decorationDto) {
            
            Date dateCreate = storeDecorationDto.getDateCreate();
            String decorationNo = storeDecorationDto.getDecorationNo();
            Boolean delFlag = storeDecorationDto.getDelFlag();
            Integer storeId = storeDecorationDto.getStoreId();
            Integer userCreate = storeDecorationDto.getUserCreate();
            //新增门店装修默认状态：16301-未装修
            Integer decorationStatus = 16301;
        
            //前后台数据合并
        	BeanUtils.copyProperties(decorationDto,storeDecorationDto);
        	storeDecorationDto.setDateCreate(dateCreate);
        	storeDecorationDto.setUpdateDate(dateCreate);
        	storeDecorationDto.setDecorationNo(decorationNo);
            storeDecorationDto.setDelFlag(delFlag);
            storeDecorationDto.setStoreId(storeId);
            storeDecorationDto.setUserCreate(userCreate);
            storeDecorationDto.setDecorationStatus(decorationStatus);
            //新增装修数据
            int i = storeDecorationMapper.addDecorationRecord(storeDecorationDto);
            if(i>0){
            	// 组装OMS装修数据
            	Decoration decoration = buildOMSDec(storeDecorationDto);
            	if(storeDecorationDto.getUserCreate()!=null && StringUtil.isNotEmpty(storeDecorationDto.getUserName())){
            		User user = userMapper.getUserByUserId(userCreate);
            		decoration.setApplicant(storeDecorationDto.getUserName()+"("+user.getUserCode()+")"); 
                }
            	// 写OMS表
            	Integer num = omsInteractiveMapper.createDecrt(decoration);
            	resultData.setReturnData(num);
            }else{
                resultData.setReturnMsg("再次申请装修失败!");
            }
        } else {
        	resultData.setReturnMsg("只有合同类型为B或者A转B并且合同状态是审核通过的合同才可以申请再次装修!");
        }
        return resultData;
    }
    
    /**
	 * 对象转换MO--DTO
	 * @param List
	 * @return List<Decoration>
     * @throws ParseException 
	 */
	private Decoration buildOMSDec(StoreDecorationDto storeDecorationDto) throws ParseException
	{
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		Decoration decoration = new Decoration();
		decoration.setDecorateNo(storeDecorationDto.getDecorationNo()); 
        decoration.setStoreNo(storeDecorationDto.getStoreNo()); 
        decoration.setContractNo(storeDecorationDto.getContractNo()); 
        decoration.setDecorateStatus(storeDecorationDto.getDecorationStatus()); 
        decoration.setStoreName(storeDecorationDto.getStoreName()); 
        decoration.setStoreAddress(storeDecorationDto.getStoreAddress()); 
        decoration.setContractType(storeDecorationDto.getContractTypeName()); 
        decoration.setCompanyName(storeDecorationDto.getCompanyName()); 
        decoration.setCityNo(storeDecorationDto.getCityNo());
        decoration.setAgreementNo(storeDecorationDto.getAgreementNo());
        decoration.setDateLifeStart(format.parse(storeDecorationDto.getDateLifeStartStr()));
        decoration.setDateLifeEnd(format.parse(storeDecorationDto.getDateLifeEndStr()));
        decoration.setOafilpagency(storeDecorationDto.getOafilpagency());
		
		return decoration;
	}
}
