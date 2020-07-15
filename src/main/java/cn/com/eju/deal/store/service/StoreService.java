/**   
* @Title: StoreService.java 
* @Package cn.com.eju.deal.store.service 
* @Description: 门店模块Service包
* @author 陆海丹
* @date 2016年3月24日 上午9:00:27 
* @version V1.0
*/
package cn.com.eju.deal.store.service;

import cn.com.eju.deal.base.code.dao.CommonCodeMapper;
import cn.com.eju.deal.base.code.model.Code;
import cn.com.eju.deal.base.dto.file.FileDto;
import cn.com.eju.deal.base.file.service.FilesService;
import cn.com.eju.deal.base.file.util.FileAssist;
import cn.com.eju.deal.base.helper.FileHelper;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.WeiphotoHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.seqNo.api.ISeqNoAPI;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.company.dao.CompanyStoreMapper;
import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.company.model.CompanyStore;
import cn.com.eju.deal.company.service.CompanyService;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.contract.dao.ContractStoreMapper;
import cn.com.eju.deal.contract.model.Contract;
import cn.com.eju.deal.contract.model.ContractSearchResult;
import cn.com.eju.deal.contract.model.ContractStore;
import cn.com.eju.deal.contract.service.ContractService;
import cn.com.eju.deal.core.support.AppMsg;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.dto.contract.ContractStoreDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.follow.dao.FollowMapper;
import cn.com.eju.deal.follow.model.Follow;
import cn.com.eju.deal.gpContract.dao.GpContractStoreMapper;
import cn.com.eju.deal.gpContract.model.GpContractStore;
import cn.com.eju.deal.mapper.followMap.FollowMapMapper;
import cn.com.eju.deal.mapper.image.ImgMapper;
import cn.com.eju.deal.model.followMap.ContactsDto;
import cn.com.eju.deal.model.sweepStreets.WXPictureDto;
import cn.com.eju.deal.store.dao.StoreMaintainerMapper;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.store.model.Store;
import cn.com.eju.deal.store.model.StoreLog;
import cn.com.eju.deal.store.model.StoreMaintainer;
import cn.com.eju.deal.user.api.impl.UserAPIImpl;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.User;
import cn.com.eju.pmls.channelBusiness.dao.BusinessManagerMapper;
import cn.com.eju.pmls.channelBusiness.model.BusinessManagerDto;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/** 
* @ClassName: StoreService 
* @Description: 门店模块Service
* @author 陆海丹
* @date 2016年3月24日 上午9:00:27 
*/
@Service("storeService")
public class StoreService extends BaseService<Store>
{
	 /**
	    * 日志
	    */
	private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private StoreMapper storeMapper;
    
    @Resource
    private UserAPIImpl userAPIImpl;
    
    @Resource
    private CompanyService companyService;
    
    @Resource
    private FileRecordMainMapper fileRecordMainMapper;
    
    @Resource
    private CompanyMapper companyMapper;
    
    @Resource
    private CompanyStoreMapper companyStoreMapper;
    
    @Resource
    private FollowMapper followMapper;
    
    @Resource(name = "contractService")
    private ContractService contractService;
    
    @Resource(name = "filesService")
    private FilesService filesService;
    
    @Resource(name = "userMapper")
    private UserMapper userMapper;
    
    @Resource(name = "contractStoreMapper")
    private ContractStoreMapper contractStoreMapper;
    
    @Resource
    private StoreMaintainerMapper storeMaintainerMapper;

    @Resource
    private ImgMapper imgMapper;

    @Resource
    private FollowMapMapper followMapMapper;

    @Resource
    private ISeqNoAPI seqNoAPI;
    
    @Resource
    private ContractMapper  contractMapper;

    @Resource
    private CommonCodeMapper commonCodeMapper;
    
    @Resource
    private StoreDepositService storeDepositService;
    @Resource
    private GpContractStoreMapper gpContractStoreMapper;
    
    @Resource
    private BusinessManagerMapper businessManagerMapper;
    /**
     * 获取门店的基本信息
     * @param id
     * @param userId
     * @return
     * @throws Exception
     */
    public ResultData<StoreDto> getBriefById(int id, Integer userId) throws Exception {
        //构建返回
        ResultData<StoreDto> resultData = new ResultData<StoreDto>();
        StoreDto storeDto = new StoreDto();
        Store store = this.storeMapper.getBriefById(id);
        if (store != null) {
            BeanUtils.copyProperties(store, storeDto);
            //录入人姓名
            if (null != storeDto.getUserCreate()) {
                storeDto.setUserNameCreate(getNameByUserId(storeDto.getUserCreate()));
            }
            //门店图片
            List<FileRecordMain> fileList = this.fileRecordMainMapper.getByStoreId(storeDto.getStoreId());
            if (null != fileList && !fileList.isEmpty()) {
                FileRecordMain fileRecordMain = fileList.get(fileList.size() - 1);
                //对文件数据进行组装处理
                handleFileRecordMain(storeDto, fileRecordMain);
            }
            
            if(null == store.getContractType() || "0".equals(store.getContractType()))
        	{
                storeDto.setContractType(0);
        		storeDto.setContractTypeName("未签");
        	}else
        	{
	        	 Code code = commonCodeMapper.queryByDicCode(store.getContractType().toString());
	             if(null != code){
	            	 storeDto.setContractTypeName(code.getDicValue());
	             }
        	}
        	//门店业务类型
            if (null != store.getBizType()) {
                Code bizTypeVal = commonCodeMapper.queryByDicCode(store.getBizType().toString());
                if (null != bizTypeVal) {
                    storeDto.setBizTypeVal(bizTypeVal.getDicValue());
                }
            }
            String depositFlag = storeDepositService.getNewDepositOpenFlagByCenterId(store.getCenterId());
            if(depositFlag != null && "0".equals(depositFlag)) {
                storeDto.setDepositFlag("0");
            }else {
                storeDto.setDepositFlag("1");
            }
        }
        //Integer storeRelocationStatus = storeMapper.getStoreRelocationStatusById(id);
        //Integer storePartyChangeStatus = storeMapper.getStorePartyChangeStatusById(id);
        Map<String, Integer> statusMap = storeMapper.getStoreRelocationStatusByMap(id);
        if(null !=statusMap) {
        	String storeRelocationStatus = statusMap.get("storeRelocationStatus").toString();
        	String storePartyChangeStatus = statusMap.get("storePartyChangeStatus").toString();
        	String storeB2AChangeStatus = statusMap.get("storeB2AChangeStatus").toString();
        	storeDto.setStoreRelocationStatus(storeRelocationStatus);
        	storeDto.setStorePartyChangeStatus(storePartyChangeStatus);
        	storeDto.setStoreB2AChangeStatus(storeB2AChangeStatus);
        }

        Map<String ,Object> map = new HashMap();
        map.put("storeId",id);
        map.put("storeNo",store.getStoreNo());
        List<FileRecordMainDto>  storeFiles = storeMapper.selectFile(map);
        List<FileRecordMainDto>  storeDecorationFiles  = storeMapper.selectDecorationFile(map);

        storeDto.setFileDtos(storeFiles);
        storeDto.setFileDecorationDtos(storeDecorationFiles);

        resultData.setReturnData(storeDto);
        return resultData;
    }

    /**
    * @Title: getStoreById 
    * @Description: 根据门店编号查询门店详情
    * @param id
    * @return     
    */
    public StoreDto getById(int id, Integer userId)
        throws Exception
    {
        StoreDto storeDto = new StoreDto();
        Store store = this.storeMapper.getById(id);
        // 非空判断
        if(null != store){
          //Model转换Dto
          BeanUtils.copyProperties(store, storeDto);
          this.formatStore(storeDto, userId);
        }
        return storeDto;
    }
    
    /** 
    * @Title: getStoreStrById 
    * @Description: 根据门店编号查询门店详情（供接口用）
    * @param id
    * @return ResultData<StoreDto>  
    */
    public ResultData<StoreDto> getStrById(int id, Integer userId)
        throws Exception
    {
        //构建返回
        ResultData<StoreDto> resultData = new ResultData<StoreDto>();
        //查询详情
        StoreDto storeDto = this.getById(id, userId);
        if (null != storeDto)
        {
        	String storeNo = storeDto.getStoreNo();
        	if(!"".equals(storeNo)) {
        		Integer storeBusinessPlaceEditFlag = this.getStoreBusinessPlaceEditFlag(storeNo);
        		storeDto.setBusinessPlaceEditFlag(storeBusinessPlaceEditFlag);
        	}
            resultData.setReturnData(storeDto);
        }
        return resultData;
    }
    
    /** 
    * @Title: queryList 
    * @Description: 查询门店列表
    * @param param
    * @return List<Store>   
    */
    public List<StoreDto> queryList(Map<?, ?> param, Integer userId)
        throws Exception
    {
        List<Store> lstStores = this.storeMapper.queryList(param);
        List<StoreDto> lstStoreDtos = this.convertListData(lstStores, userId);
        return lstStoreDtos;
    }
    
    /** 
    * @Title: queryDisList 
    * @Description: 查距离最近的30加门店的列表
    * @param param
    * @return     
    */
    public ResultData<List<StoreDto>> queryDisList(Map<?, ?> param)
        throws Exception
    {
        //构建返回
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        List<Store> lstStores = this.storeMapper.queryListByDistance(param);
        List<StoreDto> lstStoreDtos = this.convertListData(lstStores, null);
        if (null != lstStoreDtos && !lstStoreDtos.isEmpty())
        {
            resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(lstStoreDtos);
        }
        return resultData;
    }
    
    /** 
     * @Title: queryList 
     * @Description: 查询客户关联门店列表
     * @param param
     * @return List<Store>   
     */
    @Deprecated
    public ResultData<List<StoreDto>> queryRelatelist(Map<?, ?> param)
        throws Exception
    {
        //构建返回
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        List<StoreDto> lstStoreDtos = new ArrayList<StoreDto>();
        List<CompanyStore> list =
            companyStoreMapper.getCompanyStoreList(Integer.valueOf(String.valueOf(param.get("companyId"))));
        for (int i = 0; i < list.size(); i++)
        {
            StoreDto storeDto = new StoreDto();
            Store store = storeMapper.getById(list.get(i).getStoreId());
            // 非空判断
            if(null != store){
                BeanUtils.copyProperties(store, storeDto);
                lstStoreDtos.add(storeDto);
            }
        }
        if (null != lstStoreDtos && !lstStoreDtos.isEmpty())
        {
            resultData.setReturnData(lstStoreDtos);
        }
        return resultData;
    }

    public ResultData<List<StoreDto>> queryRelatelistNew(Map<?, ?> param)
            throws Exception
    {
        //构建返回
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        if (param.containsKey("companyId")){
	        List<StoreDto> lstStoreDtos = companyStoreMapper.getCompanyStoreListNew(param);
	        if (null != lstStoreDtos && !lstStoreDtos.isEmpty())
	        {
	            resultData.setReturnData(lstStoreDtos);
	        }
        }
        return resultData;
    }


    //Add By NingChao 2017/04/07 Start
      /** 续签，关联门店列表
       * @Title: queryList
       * @Description: 查询客户关联门店列表
       * @param param
       * @return List<Store>
       */
      public ResultData<List<StoreDto>> queryRelatelistByRenew(Map<?, ?> param)
          throws Exception
      {
          //构建返回
          ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
          List<StoreDto> lstStoreDtos = new ArrayList<StoreDto>();

          StoreDto storeDto = new StoreDto();
          Store store = storeMapper.getById(Integer.valueOf(param.get("storeId").toString()));
          // 非空判断
          if(null != store){
              BeanUtils.copyProperties(store, storeDto);
              lstStoreDtos.add(storeDto);
          }

          if (null != lstStoreDtos && !lstStoreDtos.isEmpty())
          {
              resultData.setReturnData(lstStoreDtos);
          }
          return resultData;
      }
    //Add By NingChao 2017/04/07 End
    /**
     * @Title: querylistByCompanyId 
     * @Description: 通过公司ID查找关联门店列表
     * @param param
     * @return List<Store>   
     */
    public ResultData<List<StoreDto>> querylistByCompanyId(Map<?, ?> param)
        throws Exception
    {
        //构建返回
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        List<StoreDto> lstStores = this.storeMapper.querylistByCompanyId(param);
        if (null != lstStores && !lstStores.isEmpty())
        {
            resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(lstStores);
        }
//        List<StoreDto> lstStoreDtos = this.convertListData(lstStores, null);
//        if (null != lstStoreDtos && !lstStoreDtos.isEmpty())
//        {
//            resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
//            resultData.setReturnData(lstStoreDtos);
//        }
        return resultData;
    }
    
  
    
    /** 
    * @Title: queryStrList 
    * @Description: 查询门店列表（供接口调用）
    * @param param
    * @return ResultData<List<StoreDto>>
    */
    public ResultData<List<StoreDto>> queryStrList(Map<?, ?> param)
        throws Exception
    {
        //构建返回
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        List<StoreDto> lstStoreDtos = this.queryList(param, null);
        if (null != lstStoreDtos && !lstStoreDtos.isEmpty())
        {
            resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(lstStoreDtos);
        }
        return resultData;
    }

    /**
    * @Title: queryStrList
    * @Description: 查询门店列表（供接口调用）
    * @param param
    * @return ResultData<List<StoreDto>>
    */
    public ResultData<List<StoreDto>> queryListNew(Map<?, ?> param)
        throws Exception
    {
        //构建返回
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        List<StoreDto> lstStoreDtos = storeMapper.queryListNew(param);
        if (null != lstStoreDtos && !lstStoreDtos.isEmpty())
        {
            resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(lstStoreDtos);
        }
        return resultData;
    }

    /**
     * 获取客户的门店
     */
    public List<StoreDto> companyStoreList(Map<?, ?> param)
        throws Exception
    {
        List<Store> lstStores = this.storeMapper.getCompanyStore(param);
        List<StoreDto> lstStoreDtos = this.convertListData(lstStores, null);
        return lstStoreDtos;
    }
    
    /** 
    * @Title: create 
    * @Description: 新增门店
    * @param store
    * @return     
    */
    public int create(Store store)
        throws Exception
    {
        int count = this.storeMapper.create(store);
        return count;
    }
    
    /** 
    * @Title: createStr 
    * @Description: 新增门店（供接口调用）
    * @param storeDto
    * @return     
    */
    public ResultData<StoreDto> createStr(StoreDto storeDto)
        throws Exception
    {
        //构建返回
        ResultData<StoreDto> resultData = new ResultData<StoreDto>();
        Store store = new Store();
        //赋值
        BeanUtils.copyProperties(storeDto, store);
        //检查地址是否重复
        if (!this.checkAdd(store))
        {
            resultData.setFail(AppMsg.getString("STORE.SAVE_REPEAT"));
            resultData.setReturnCode("-1");
            return resultData;
        }
        String addressDetail =
            SystemParam.getCityNameByCityNo(store.getCityNo())
                + SystemParam.getDistrictNameByDistrictNo(store.getDistrictNo())
                + SystemParam.getAreaNameByAreaNo(store.getAreaNo()) + store.getAddress();
        store.setAddressDetail(addressDetail);
        Integer userCreateId =store.getUserCreate();
        User maintainer = null;
        if(userCreateId!=null){
        	 maintainer = userMapper.getUserByUserId(userCreateId);        
            store.setMaintainerName(maintainer.getUserName());
            store.setMaintainer(maintainer.getUserCode());
        }
        int count = this.create(store);
        if (count <= 0)
        {
            resultData.setFail();
        }
        else
        {
            //赋值回来
            BeanUtils.copyProperties(store, storeDto);

            if(maintainer!=null){
  	      	  // 新增门店成功后,新增一条维护人记录
  	            StoreMaintainer sm = new StoreMaintainer();
  	            sm.setStoreId(store.getStoreId());
  	            sm.setUserCode(maintainer.getUserCode());
  	            sm.setUserIdCreate(store.getUserCreate());
  	            sm.setDelFlag("N");
  	            sm.setDateCreate(new Date());
  	            sm.setSetUserName(maintainer.getUserName());
  	            sm.setSetUserCode(maintainer.getUserCode());
  	            sm.setDateMtcStart(new Date());
  	            storeMaintainerMapper.insert(sm);
              }
            resultData.setReturnData(storeDto);
        }
        return resultData;
    }
    
    /** 
    * @Title: update 
    * @Description: 更新门店信息
    * @param store
    * @return     
    */
    public int update(Store store)
        throws Exception
    {
        return this.storeMapper.update(store);
    }
    
    /** 
    * @Title: updateStr 
    * @Description: 更新门店信息（供接口调用）
    * @param storeDto
    * @return ResultData<StoreDto>    
    */
    public ResultData<StoreDto> updateStr(StoreDto storeDto)
        throws Exception
    {
        //构建返回
        ResultData<StoreDto> resultData = new ResultData<StoreDto>();
        Store store = new Store();
        //赋值
        BeanUtils.copyProperties(storeDto, store);
        String addressDetail =
            SystemParam.getCityNameByCityNo(store.getCityNo())
                + SystemParam.getDistrictNameByDistrictNo(store.getDistrictNo())
                + SystemParam.getAreaNameByAreaNo(store.getAreaNo()) + store.getAddress();
        if(addressDetail!=null && !"null".equals(addressDetail)){
        	store.setAddressDetail(addressDetail);
        	ContractStoreDto contractStore = contractStoreMapper.getTopContractStoreByStore(store.getStoreId());
        	if(contractStore!=null 
        			&& DictionaryConstants.CONTRACT_STATUS_PENDING.intValue()== (contractStore.getContractStatus())){
        		contractStore.setAddressDetail(addressDetail);
        		contractStoreMapper.update(contractStore);
        	}
        }
//        pictureRefId

        String pictureRefId = store.getPictureRefId();
        if(null==pictureRefId || pictureRefId.isEmpty()){
            pictureRefId = UUID.randomUUID().toString();
            store.setPictureRefId(pictureRefId);
        }

        //判断是否填写门店负责人，如果有，添加到联系人表
        if(null!=store.getStoreManager()&& !store.getStoreManager().isEmpty() && null !=store.getStoreManagerPhone() && !store.getStoreManagerPhone().isEmpty()){
            //创建门店联系人
            ContactsDto contactsDto=new ContactsDto();
            //
            ResultData<String> back = seqNoAPI.getSeqNoByTypeCode("TYPE_CONTACT");
            String contactsNo = back.getReturnData();
            contactsDto.setContactsNo(contactsNo);
            contactsDto.setStoreId(store.getStoreId());
            contactsDto.setContactsName(store.getStoreManager());
            contactsDto.setContactsPhone(store.getStoreManagerPhone());
            contactsDto.setUserCreate(store.getUserUpdate()+"");
            //判断联系人是否重复
            int result=followMapMapper.checkContacts(contactsDto);
            if(result>0){
                //如果重复 忽略
            }else{
                //插入门店联系人信息
                followMapMapper.addContacts(contactsDto);
            }
        }

        int count = this.update(store);

        List<WXPictureDto> pictureDtoList = JSON.parseArray(storeDto.getStorePicListJson(),WXPictureDto.class);
        if(pictureDtoList!=null && pictureDtoList.size()>0){
            for(WXPictureDto pictureDto:pictureDtoList){
                pictureDto.setPictureRefId(pictureRefId);
                pictureDto.setCreateUser(storeDto.getUserUpdate().toString());
            }
            //上传图片
            imgMapper.addImg(pictureDtoList);
        }


        if (count <= 0)
        {
            resultData.setFail();
        }
        else
        {
            resultData.setReturnData(storeDto);
        }
        return resultData;
    }
    
    /** 
    * @Title: delete 
    * @Description: 删除门店
    * @param id
    * @return     
    */
    public int delete(int id)
        throws Exception
    {
        return this.storeMapper.deleteById(id);
    }
    
    /** 
    * @Title: deleteStr 
    * @Description: 删除门店（供接口调用）
    * @param id
    * @return     
    */
    public ResultData<StoreDto> deleteStr(int id)
        throws Exception
    {
        //构建返回
        ResultData<StoreDto> resultData = new ResultData<StoreDto>();
        int count = this.delete(id);
        if (count <= 0)
        {
            resultData.setFail();
        }
        return resultData;
    }
    
    /**
     * 根据维护人的员工编号查询门店信息
     * 
     * @param maintainer
     *            维护人的员工编号
     * @return
     */
    public ResultData<List<StoreDto>> getByMaintainer(Map<?, ?> param)
        throws Exception
    {
        // 构建返回
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        // 根据维护人的员工编号查询门店信息
        List<Store> lstStores = this.storeMapper.getByMaintainer(param);
        
        List<StoreDto> lstStoreDtos = new ArrayList<StoreDto>();
        if (null != lstStores && !lstStores.isEmpty())
        {
            StoreDto storeDto = new StoreDto();
            for (Store store : lstStores)
            {
                storeDto = new StoreDto();
                BeanUtils.copyProperties(store, storeDto);
                // 获取门店的名片(门店的一些拓展字段)
                this.getStoreExpandContent(storeDto);
                lstStoreDtos.add(storeDto);
            }
        }
        
        if (null != lstStoreDtos && !lstStoreDtos.isEmpty())
        {
            resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(lstStoreDtos);
        }
        return resultData;
    }
    
    
    /** 
     * 更新维护人到门店表
     * @param param
     * @return
     */
     public ResultData<?> updateMtcToStore(StoreDto storeDto)
         throws Exception
     {
         //构建返回
         ResultData<?> resultData = new ResultData<>();
         Store store = new Store();
    	 // 根据维护人code取中心
    	 Map<String,Object> param = new HashMap<String,Object>();
//    	 // BTEJBM
//         String typeCode = SystemParam.getWebConfigValue("centerConfig");
//         param.put("typeCode", typeCode);
//         // 有userCode条件的话 只会有一条记录
//    	 param.put("userCode", storeDto.getPmlsMaintainCode());
//    	 param.put("cityNo", storeDto.getCityNo());
//    	 List<User> users = userMapper.getWeiHuUser(param);
//    	 if(null != users && !users.isEmpty()){
//    		Integer mtcnerCenterId = users.get(0).getCenterId();
//    		storeDto.setPmlsCenterId(mtcnerCenterId);
//    	 }
    	 Map<String,Object> map = new HashMap<String, Object>();
    	 //跟新公司分销状态和中心
    	 if(storeDto.getIsUpdateCompanyFlag()==1) {
    		 map.put("updateIteam", "修改信息-业务类型");
    		 Integer storeId = storeDto.getStoreId();
    		 param.put("storeId", storeId);
    		 //根据门店id查询关联公司
    		 Map<String,Object> storeCom = storeMapper.getCompanyByStoreId(param);
    		 if(!storeCom.isEmpty()) {
    			 BusinessManagerDto dto = new BusinessManagerDto();
    			 Integer id = (Integer) storeCom.get("companyId");
    			 dto.setId(id);
    			 dto.setPmlsCenterId(storeDto.getPmlsCenterId());
    			 dto.setBrandType(storeDto.getBrandType());
    			 businessManagerMapper.updateBusiness(dto);
    		 }
    		 
    	 }else {
    		 map.put("updateIteam", "分配维护人");
    	 }
         BeanUtils.copyProperties(storeDto, store);
         storeMapper.update(store);
         //添加日志
         map.put("storeId", store.getStoreId());
         map.put("updateUserId", store.getUserUpdate());
         map.put("updateDate", new Date());
         storeMapper.insertStoreLog(map);
         return resultData;
     }
    /*-----------------------------------------private function-----------------------------------------*/
    /** 
    * @Title: convertListData 
    * @Description: 将List<Store>转换成List<StoreDto>
    * @param lstStores
    * @return List<StoreDto>    
    */
    private List<StoreDto> convertListData(List<Store> lstStores, Integer userId)
        throws Exception
    {
        List<StoreDto> lstStoreDtos = new ArrayList<StoreDto>();
        if (null != lstStores && !lstStores.isEmpty())
        {
            StoreDto storeDto = new StoreDto();
            for (Store store : lstStores)
            {
                storeDto = new StoreDto();
                BeanUtils.copyProperties(store, storeDto);
                this.formatStore(storeDto, userId);
                lstStoreDtos.add(storeDto);
            }
        }
        return lstStoreDtos;
    }
    
    /** 
    *  获取门店的名片(门店的一些拓展字段)
    * @param storeDto 门店信息
    */
    private void getStoreExpandContent(StoreDto storeDto)
        throws Exception
    {
        //1.装修进度
        storeDto.setDecorationState(storeDto.getDecorationState());
        storeDto.setDecorationStateNm(SystemParam.getDicValueByDicCode(String.valueOf(storeDto.getDecorationState())));
        //2.门店图片
        List<FileRecordMain> fileList = this.fileRecordMainMapper.getByStoreId(storeDto.getStoreId());
        if (null != fileList && !fileList.isEmpty())
        {
            FileRecordMain fileRecordMain = fileList.get(0);
            
            //对文件数据进行组装处理
            handleFileRecordMain(storeDto, fileRecordMain);
        }
    }
    
    /** 
    * @Title: formatStore 
    * @Description: 门店的一些拓展字段赋值
    * @param storeDto
    * @param userId 自己的工号    
    */
    private void formatStore(StoreDto storeDto, Integer userId)
        throws Exception
    {
        //1.门店所属公司
        //所属公司：若有公司并且合同签的为AB版则可直接展示所属公司
        String companyName = "";
        List<Company> companys = this.companyMapper.getCompanyByStoreId(storeDto.getStoreId());
        if (null != companys && !companys.isEmpty())
        {
            if (null != userId)
            {
                //查询自己建的关联客户信息
                for (Company company : companys)
                {
                    if (userId == company.getUserCreate().intValue())
                    {//int不要直接和Integer直接比较大小
                     //自己建的关联的客户编号 不一定是签过合同的
                     //TODO 若一个userId建了多个公司 应该取哪一个关联客户？暂时先取第一个
                        storeDto.setRelatedCompanyId(company.getId());
                        break;
                    }
                }
            }
            //以下是查询签过合同的信息
            for (Company company : companys)
            {
                Map<String, Object> param = new HashMap<>();
                param.put("storeId", storeDto.getStoreId());
                param.put("companyId", company.getId());
                ResultData<Contract> resultData = this.contractService.queryContractTypeByStoreCompanyId(param);
                Contract contract = resultData.getReturnData();
                if (null != contract)
                {
                    companyName = company.getCompanyName();
                    //已经签过的公司算公店 可以显示
                    //                    storeDto.setRelatedCompanyId(company.getId());
                    storeDto.setCompanyId(company.getId());
                    storeDto.setCompanyName(companyName);
                    storeDto.setCompanyStatus(DictionaryConstants.DIC_CODE_COMPANY_STATUS_Y);
                    storeDto.setCompanyStatusStr(SystemParam.getDicValueByDicCode(String.valueOf(DictionaryConstants.DIC_CODE_COMPANY_STATUS_Y)));
                    //签署合同日期
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    storeDto.setContractDate(sdf.format(contract.getDateCreate()));
                    //签署人
                    String contractName = "";
                    //获取签约片区
                    String contractArea = "";
                    String contractCity = "";
                    ResultData<UserInfo> userResultData = this.userAPIImpl.getUserInfoById(1, contract.getUserCreate());
                    if (userResultData.getReturnCode() == ReturnCode.SUCCESS)
                    {
                        UserInfo userInfo = userResultData.getReturnData();
                        if (null != userInfo)
                        {
                            contractArea = userInfo.getGroupName() == null ? "" : userInfo.getGroupName();
                            contractName = userInfo.getUserName() == null ? "" : userInfo.getUserName();
                            contractCity = userInfo.getCityName() == null ? "" : userInfo.getCityName();
                        }
                    }
                    storeDto.setContractDept(contractCity + "-" + contractArea + "-" + contractName);
                    break;
                }
                else
                {
                    storeDto.setCompanyStatus(DictionaryConstants.DIC_CODE_COMPANY_STATUS_N);
                    storeDto.setCompanyStatusStr(SystemParam.getDicValueByDicCode(String.valueOf(DictionaryConstants.DIC_CODE_COMPANY_STATUS_N)));
                    continue;
                }
            }
        }
        else
        {
            storeDto.setCompanyStatus(DictionaryConstants.DIC_CODE_COMPANY_STATUS_N);
            storeDto.setCompanyStatusStr(SystemParam.getDicValueByDicCode(String.valueOf(DictionaryConstants.DIC_CODE_COMPANY_STATUS_N)));
        }
        //2.录入人姓名
        if (null != storeDto.getUserCreate())
        {
            storeDto.setUserNameCreate(getNameByUserId(storeDto.getUserCreate()));
        }
        //3.最近修改者姓名
        if (null != storeDto.getUserUpdate())
        {
            storeDto.setUserNameUpdate(getNameByUserId(storeDto.getUserUpdate()));
        }
        
        //4.门店图片
        List<FileRecordMain> fileList = this.fileRecordMainMapper.getByStoreId(storeDto.getStoreId());
        if (null != fileList && !fileList.isEmpty())
        {
            FileRecordMain fileRecordMain = fileList.get(fileList.size()-1);
            
            //对文件数据进行组装处理
            handleFileRecordMain(storeDto, fileRecordMain);
            
        }
        
        //5.最新跟进人员 展示最新的一次跟进人信息（普通员工跟进：可查看自己的、经理及以上可查看全部、此处只展示最新的，若自己有跟进过则优先展示自己的记录）
        Map<String, Object> param = new HashMap<>();
        param.put("storeId", storeDto.getStoreId());
        param.put("orderName", "dateCreate");
        param.put("orderType", "DESC");
        List<Follow> follows = this.followMapper.queryList(param);
        //有自己的跟进记录优先显示
        if (null != follows && !follows.isEmpty())
        {
            Follow follow = follows.get(0);
            storeDto.setUserIdFollow(follow.getUserCreate());
            if (null != follow.getUserCreate())
            {
                storeDto.setUserNameFollow(getNameByUserId(follow.getUserCreate()));
                storeDto.setDateFollow(follow.getDateCreate());
            }
        }
        
        // 6.装修进度
        storeDto.setDecorationState(storeDto.getDecorationState());
        storeDto.setDecorationStateNm(SystemParam.getDicValueByDicCode(String.valueOf(storeDto.getDecorationState())));
    }

    /** 
    * 对文件数据进行组装处理
    * @param contractFileDto
    * @param fileRecordMain
    * @throws Exception
    */
    private void handleFileRecordMain(StoreDto storeDto, FileRecordMain fileRecordMain)
        throws Exception
    {
        //获取图片路径
    	storeDto.setFileAbbrUrl(fileRecordMain.getFileAbbrUrl());
    	storeDto.setFileName(fileRecordMain.getFileFullName());
    	storeDto.setFileRecordMainId(fileRecordMain.getFileRecordMainId().toString());
    	storeDto.setFileUrl(fileRecordMain.getFileUrl());
    }
    
    /** 
    * 对文件数据进行组装处理
    * @param storeDto
    * @param fileRecordMain
    * @throws Exception
    */
    public void handleFile(StoreDto storeDto, FileRecordMain fileRecordMain)
        throws Exception
    {
        // 1、先从关系表中取，如果取不到 ，则通过fileNo 去渠道系统主表 拿到渠道及fileCode，去相应文件系统取出文件地址；
        // 获取文件渠道系统配置（外部文件系统的配置）
        Map<?, ?> mapTemp = getChannelConfig();
        
        //取关系表中的文件Code
        String fileId = fileRecordMain.getFileId();
        
        //关系主键Id
        String fileRecordMainId = fileRecordMain.getFileRecordMainId().toString();
        
        if (StringUtil.isNotEmpty(fileId))
        {
            // 查询路径
            String queryUrl = (String)mapTemp.get("CRIC_queryfile_path");
            // 下载路径
            String downloadUrl = (String)mapTemp.get("CRIC_downloadfile_path");
            // 授权号
            String permitCode = (String)mapTemp.get("CRIC_file_permit_code");
            
            //获取图片路径
            String fileUrl = FileHelper.getFilePath(fileId, queryUrl, downloadUrl, permitCode);
            Map<String, Object> handleMap = new HashMap<>();
            handleMap.put("width", "100");
            handleMap.put("height", "100");
            handleMap.put("waterPic", "0");// 无水印图片
            handleMap.put("waterPosition", "0");//  3:左下角
            handleMap.put("cutType", "0");//0-不裁剪
            String fileAbbrUrl =
                FileHelper.getFilePath(fileRecordMain.getFileId(),
                    FileAssist.UPLOAD_FILE_IS_HANDLE_YES,
                    queryUrl,
                    downloadUrl,
                    permitCode,
                    handleMap);
            storeDto.setFileAbbrUrl(fileAbbrUrl);
            storeDto.setFileName(fileRecordMain.getFileFullName());
            storeDto.setFileRecordMainId(fileRecordMainId);
            storeDto.setFileUrl(fileUrl);
        }
        else
        {
            //取出fileNo
            String fileNo = fileRecordMain.getFileNo();
            
            //调用渠道系统，获取文件记录信息
            ResultData<?> reback = filesService.getByFileNo(fileNo);
            Map<?, ?> mapTemppp = (Map<?, ?>)reback.getReturnData();
            if (null != mapTemppp)
            {
                String fileAbbrUrl = null;
                String fileUrl = null;
                
                FileDto fileDto = MapToEntityConvertUtil.convert(mapTemppp, FileDto.class, "");
                
                //fileCode
                String fileCode = fileDto.getFileCode();
                //渠道Code
                String channelCode = fileDto.getChannelCode();
                if ("CRIC".equals(channelCode))
                {
                    // 查询路径
                    String queryUrl = (String)mapTemp.get("CRIC_queryfile_path");
                    // 下载路径
                    String downloadUrl = (String)mapTemp.get("CRIC_downloadfile_path");
                    // 授权号
                    String permitCode = (String)mapTemp.get("CRIC_file_permit_code");
                    
                    //获取图片路径
                    fileUrl = FileHelper.getFilePath(fileCode, queryUrl, downloadUrl, permitCode);
                    Map<String, Object> handleMap = new HashMap<>();
                    handleMap.put("width", "100");
                    handleMap.put("height", "100");
                    handleMap.put("waterPic", "0");// 无水印图片
                    handleMap.put("waterPosition", "0");//  3:左下角
                    handleMap.put("cutType", "0");//0-不裁剪
                    fileAbbrUrl =
                        FileHelper.getFilePath(fileCode,
                            FileAssist.UPLOAD_FILE_IS_HANDLE_YES,
                            queryUrl,
                            downloadUrl,
                            permitCode,
                            handleMap);
                    
                }
                else if ("weiphoto".equals(channelCode))
                {
                    // 查询路径
                    String fileViewUrl = (String)mapTemp.get("wp_view_url");
                    
                    fileAbbrUrl = WeiphotoHelper.getFilePath(fileViewUrl, fileCode, "200");
                    fileUrl = WeiphotoHelper.getFilePath(fileViewUrl, fileCode, "5000");
                }
                
                //设值
                storeDto.setFileAbbrUrl(fileAbbrUrl);//缩略图地址
                storeDto.setFileRecordMainId(fileRecordMainId);
                storeDto.setFileUrl(fileUrl);//原图地址
                storeDto.setFileNo(fileNo);//渠道系统关系
                
            }
        }
    }
    
    /** 
    * @Title: getNameByUserId 
    * @Description: 跟进用户编号获取用户姓名
    * @param userId
    * @return     
    */
    private String getNameByUserId(Integer userId)
        throws Exception
    {
        String userName = "";
        ResultData<User> userData = this.userAPIImpl.getUserById(userId);
        if (userData.getReturnCode() == ReturnCode.SUCCESS)
        {
            userName = userData.getReturnData().getUserName();
        }
        return userName;
    }
    
    /** 
    * @Title: checkAdd 
    * @Description: 根据门店地址判断门店是否可以新建
    * @param store
    * @return     
    */
    private Boolean checkAdd(Store store)
        throws Exception
    {
        Boolean canAdd = true;
        Map<String, Object> param = new HashMap<>();
        param.put("cityNo", store.getCityNo());
        param.put("districtNo", store.getDistrictNo());
        param.put("areaNo", store.getAreaNo());
        param.put("address", store.getAddress());
        List<StoreDto> storeDtos = this.queryList(param, null);
        if (null != storeDtos && !storeDtos.isEmpty())
        {
            canAdd = false;
        }
        return canAdd;
    }
    /*-----------------------------------------private function-----------------------------------------*/

    /** 
    * @Title: getEditBanFlag 
    * @Description: 获取是否编辑限制状态
    * @param storeId
    * @return     
    */
    public ResultData<Boolean> getEditBanFlag(Integer storeId)
        throws Exception
    {
        //构建返回
        ResultData<Boolean> resultData = new ResultData<>();
        Boolean banFlag = false;//不限制
        StoreDto storeDto = this.storeMapper.getTopOneContractStore(storeId);
        if(storeDto!=null 
        	&& (
        			DictionaryConstants.CONTRACT_STATUS_AUDITING.equals(storeDto.getContractStatus()) 
        			|| DictionaryConstants.CONTRACT_STATUS_AUDIT_PASS.equals(storeDto.getContractStatus())
        	   )
        	&& !DictionaryConstants.STORESTATE_ISCANCEL_ISCANCEL.equals(storeDto.getIsCancel()) 
        ){
        	banFlag = true;
        }
        resultData.setReturnData(banFlag);
        return resultData;
    }

    /**
     * @param id
     * @return
     * @Title: getReceivedMoneyByStoreId
     * @Description: 根据门店ID查询收款
     */
    public ResultData<List<StoreDto>> getReceivedMoneyByStoreId(Integer storeId) {
        //构建返回
        ResultData<List<StoreDto>> resultData = new ResultData<>();
        //查询详情
        List<StoreDto> storeDto = storeMapper.getReceivedMoneyByStoreId(storeId);
        if (null != storeDto)
        {
            resultData.setReturnData(storeDto);
        }
        return resultData;
    }

    //Add By NingChao 2017/04/07 Start
      /**
       * @Title:updateNeededRenew
       * @param storeId
       * @param neededRenew
       * @return
       * @throws Exception
       */
      public ResultData<?> updateNeededRenewFlag(Integer storeId,Integer neededRenew)
              throws Exception
          {
              //构建返回
              ResultData<?> resultData = new ResultData<>();
              Map<String, Object> param = new HashMap<>();
              param.put("storeId", storeId);
              param.put("neededRenew", neededRenew);
              param.put("neededRenewY", DictionaryConstants.NEEDEDRENEW_TYPE_YXQ);  //需续签
              param.put("neededRenewN", DictionaryConstants.NEEDEDRENEW_TYPE_NXQ);  //无需续签
              storeMapper.updateParam(param);
              return resultData;
          }

      /**
       * 修改门店待续签状态为正常
       * @Title:updateRenewFlag
       * @param storeId
       * @return
       * @throws Exception
       */
      public ResultData<?> updateRenewFlag(Integer storeId)
              throws Exception
          {
              //构建返回
              ResultData<?> resultData = new ResultData<>();
              Map<String, Object> param = new HashMap<>();
              param.put("storeId", storeId);
              param.put("renewFlag", DictionaryConstants.RENEWFLAG_TYPE_OK);
              storeMapper.updateRenewFlag(param);
              return resultData;
          }
    //Add By NingChao 2017/04/07 End

  	/**
  	 * @param userCode
  	 *            当前登陆人code
  	 * @return 待续签门店列表
  	 * @throws Exception
  	 */
  	public ResultData<List<StoreDto>> getRenewStoreList(Map<String, Object> param) throws Exception {
  		// 构建返回
  		ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
  		List<Store> renewList = storeMapper.getRenewStoreList(param);
  		if (null != renewList && !renewList.isEmpty()) {
  			DateFormat df = new SimpleDateFormat("YY-MM-dd");
  			List<StoreDto> dtoList = new ArrayList<StoreDto>();
  			for (Store store : renewList) {
  				StoreDto dto = new StoreDto();
  				BeanUtils.copyProperties(store, dto);

  				// 设置字符串格式的日期 YY-MM-dd
  				dto.setDateLifeStartStr(df.format(store.getDateLifeStart()));
  				dto.setDateLifeEndStr(df.format(store.getDateLifeEnd()));

  				dtoList.add(dto);
  			}
  			resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
  			resultData.setReturnData(dtoList);
  		}
  		return resultData;
  	}
  	
  	/**
     * 分配维护人时 check选择的维护人是否是当前维护人
     * @param param
     * @return
     * @throws Exception
     */
    public Boolean checkMtner(Map<String,Object> param) throws Exception{
    	return storeMapper.checkMtner(param);
    }
    
// Add By GuoPengFei 2017/06/05 合规性 start
    /** 
    * @Title: getStoreStrById 
    * @Description: 根据门店编号查询门店详情
    * @param id
    * @return ResultData<StoreDto>  
    */
    public ResultData<List<StoreDto>> getStoreById(int id)
        throws Exception
    {
        //构建返回
    	StoreDto storeDto = new StoreDto();
    	List<StoreDto> lstStoreDtos = new ArrayList<StoreDto>();
    	ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        //查询详情
        Store store = this.storeMapper.getById(id);
        
        if (null != storeDto)
        {
        	BeanUtils.copyProperties(store, storeDto);
        	lstStoreDtos.add(storeDto);
            resultData.setReturnData(lstStoreDtos);
        }
        return resultData;
    }
    
    /**
     * 修改OMS装修记录的门店地址
     * @param param
     * @return
     * @throws Exception
     */
    public Integer UpdateOmsStoreAdress(Map<String, Object> param)throws Exception
    {
        //构建返回
        Integer rtn = storeMapper.UpdateOmsStoreAdress(param);
        return rtn;
    }
    
 // Add By GuoPengFei 2017/06/05 合规性 end    
    
// Add By QJP 2017/07/12  START    
    /**
     * 查询门店修改日志List
     * @param id
     * @return
     */
    public ResultData<List<StoreLog>> queryStoreLogList(Map<String, Object> param) {
        //构建返回
        ResultData<List<StoreLog>> resultData = new ResultData<>();
        List<StoreLog> storeLogList = new ArrayList<StoreLog>();
        try {
            storeLogList = storeMapper.getStoreLogList(param);
        } catch (Exception e) {
            logger.error("门店修改日志列表异常", e);
        }
        resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(storeLogList);

        return resultData;
    }
 // Add By QJP 2017/07/12  End 

    public ResultData<StoreLog> queryStoreLog(Map<String, Object> param) {
        ResultData<StoreLog> resultData = new ResultData<>();
        try {
            StoreLog  storeLog = storeMapper.getStoreLogById(param);
            List<FileRecordMainDto> fileDtos = storeMapper.selectFile(param);
            storeLog.setFileDtos(fileDtos);
            resultData.setReturnData(storeLog);
        } catch (Exception e) {
            logger.error("门店修改日志列表异常", e);
        }
        return resultData;
    }
    
    
 // Add By QJP 2017/07/13  Start    
    /** 
     * @Title: updateDetail
     * @Description: 更新门店名称,地址,营业执照图片同时插入一条更新记录信息
     * @param storeDto
     * @return ResultData<StoreDto>    
     */
     public ResultData<StoreDto> updateDetail(StoreDto storeDto)
         throws Exception
     {
         //构建返回
         ResultData<StoreDto> resultData = new ResultData<StoreDto>();
         Store store = new Store();
         //赋值
         BeanUtils.copyProperties(storeDto, store);      
         
         //根据门店ID查询门店详情
//         Store s = this.storeMapper.getById(store.getStoreId());
         
         Map<String,Object> map = new HashMap<String, Object>();
         map.put("storeId", store.getStoreId());
         map.put("newStoreName", store.getName());
         map.put("newCityNo", store.getCityNo());
         map.put("newDistrictNo", store.getDistrictNo());
         map.put("newStoreAddress", store.getAddress());
         map.put("updateUserId", store.getUserUpdate());
         map.put("updateDate", new Date());
         map.put("BusinessLicenseNo", null);
         map.put("oldStoreName", storeDto.getOldStoreLogName());
         map.put("changeName", storeDto.getChangeName());
         map.put("changeAddress", storeDto.getChangeAddress());
         map.put("oldCityNo", storeDto.getOldCityNo());
         map.put("oldDistrictNo", storeDto.getOldDistrictNo());
         map.put("oldStoreAddress", storeDto.getOldStoreAddress());
         
         String updateIteam = "";
         
         String[] str = {null,"门店名称","门店地址","营业状态","经营场所(原:{0})","门店规模(原:{1})","门店类型(原:{2})"};
         
         int i1 = 0,i2 = 0,i3 = 0,i4=0,i5=0,i6=0;
         i1 = storeDto.getChangeName() == 1 ? 1:0;
         i2 = storeDto.getChangeAddress() == 1 ? 2:0;
         i3 = storeDto.getBusinessStatus() != null ? 3:0;
         i4 = storeDto.getChangeBusinessPlaceType() != null ? 4:0;
         i5 = storeDto.getChangeStoreSizeScale() != null ? 5:0;
         i6 = storeDto.getStoreType()!= null ? 6:0;

         List<String> itemList = new ArrayList<>();
         itemList.add(str[i1]);
         itemList.add(str[i2]);
         itemList.add(str[i3]);
         itemList.add(str[i4]);
         itemList.add(str[i5]);
         itemList.add(str[i6]);

         StringBuilder sb = new StringBuilder();  
         for (int i = 0; i < itemList.size(); i++) {
             if(itemList.get(i) != null) {
                 sb.append(itemList.get(i)).append(",");  
             }
         }  
         updateIteam = i1+i2+i3+i4+i5+i6 == 0?"":sb.toString().substring(0, sb.toString().length() - 1);

         if(i4 == 4){
             store.setBusinessPlaceType(storeDto.getNewBusinessPlace());
             updateIteam = updateIteam.replace("{0}",SystemParam.getDicValueByDicCode(storeDto.getOldBusinessPlace().toString()));
         }
         if(i5 == 5){
             store.setStoreSizeScale(storeDto.getNewStoreSize());
             updateIteam = updateIteam.replace("{1}",SystemParam.getDicValueByDicCode(storeDto.getOldStoreSize().toString()));
         }
         if(i6 == 6){
             store.setStoreType(storeDto.getNewStoreType());
             updateIteam = updateIteam.replace("{2}",SystemParam.getDicValueByDicCode(storeDto.getOldStoreType().toString()));
         }

         if(storeDto.getFlag() != null){
             updateIteam = "运维修改:"+updateIteam;
             String tempStr = "";
             if(storeDto.getContractId() != null){
                 tempStr="修改合同";
             }
             if(storeDto.getGpContractId() != null){
            	 tempStr="修改公盘合同";
             }
             if(!"".equals(tempStr)){
                 updateIteam += "("+tempStr+")";
             }
         }
         
         map.put("updateIteam", updateIteam);
         int num = storeMapper.insertStoreLog(map);
         if(num <0)
         {
        	 resultData.setFail();
         }

         //根据合同状态更新合同信息
         String addressDetail = "";
         if(num >0){
             Map<String,Object> mop = new HashMap<>();
             mop.put("storeId", store.getStoreId());
             List<StoreLog> storeloglist = this.storeMapper.getStoreLogList(mop);
             StoreLog storeLog = storeloglist.get(0);
             addressDetail = storeLog.getNewAddressDetail();
             store.setAddressDetail(addressDetail);
         }
         if(addressDetail != null &&  !"".equals(addressDetail) && !"1".equals(storeDto.getFlag()))
         {
             Map<String, Object> resMap = new HashMap<>();
             resMap.put("storeId", store.getStoreId());
             List<ContractSearchResult> list = contractMapper.getSignHisByStoreId(resMap);
             if(!list.isEmpty() && list.size()>0){
                 ContractSearchResult contractSearchResult = (ContractSearchResult) list.get(0);
                 Integer contractStatus = contractSearchResult.getContractStatus();
                 Integer contractId = contractSearchResult.getId();
                 if(contractStatus == 10404 || contractStatus == 10401)
                 {
                     ContractStore contractStore = new ContractStore();
                     contractStore.setContractId(contractId);
                     contractStore.setStoreId(store.getStoreId());
                     contractStore.setAddressDetail(addressDetail);
                     contractStoreMapper.update(contractStore);
                 }else{
                     logger.error("store", "StoreService", "updateDetail", "",store.getUserUpdate(), "", "无草签、审核不通过合同，不修改合同门店信息", new Exception("无草签、审核不通过合同，不修改合同门店信息"));
                 }
             }
             //只查公盘id和状态，复用合同的类
             List<ContractSearchResult> list2 = gpContractStoreMapper.getGpContractByStoreId(store.getStoreId());
             if(!list2.isEmpty() && list2.size()>0) {
            	 for (ContractSearchResult contractSearchResult : list2) {
            		 Integer gpContractId = contractSearchResult.getId();
            		 Integer contractStatus = contractSearchResult.getContractStatus();
            		 if(10404 == contractStatus || 10401 == contractStatus) {
            			 GpContractStore gpContractStore = new GpContractStore();
                    	 gpContractStore.setGpContractId(gpContractId);
                    	 gpContractStore.setStoreId(store.getStoreId());
                    	 gpContractStore.setAddressDetail(addressDetail);
                    	 gpContractStoreMapper.update(gpContractStore);
            		 }
            		 
				}
             } else {
            	 logger.error("store", "StoreService", "updateDetail", "",store.getUserUpdate(), "", "无草签、审核不通过的公盘合同，不修改公盘合同门店信息", new Exception("无草签、审核不通过公盘合同，不修改公盘合同门店信息"));
             }
         }else {
             //运营维护公司信息且选择修改合同
             if(addressDetail != null &&  !"".equals(addressDetail) && storeDto.getContractId() != null){
                 ContractStore contractStore = new ContractStore();
                 contractStore.setContractId(storeDto.getContractId());
                 contractStore.setStoreId(store.getStoreId());
                 contractStore.setAddressDetail(addressDetail);
                 contractStoreMapper.update(contractStore);
             }
             //运营维护门店信息且选择修改公盘合同
             if(addressDetail != null &&  !"".equals(addressDetail) && storeDto.getGpContractId() != null){
            	 GpContractStore gpContractStore = new GpContractStore();
            	 gpContractStore.setGpContractId(storeDto.getGpContractId());
            	 gpContractStore.setStoreId(store.getStoreId());
            	 gpContractStore.setAddressDetail(addressDetail);
            	 gpContractStoreMapper.update(gpContractStore);
             }
         }

         
         String pictureRefId = store.getPictureRefId();
         if(null==pictureRefId || pictureRefId.isEmpty()){
             pictureRefId = UUID.randomUUID().toString();
             store.setPictureRefId(pictureRefId);
         }
         
         //更新门店信息
         int count = this.update(store);     

         List<WXPictureDto> pictureDtoList = JSON.parseArray(storeDto.getStorePicListJson(),WXPictureDto.class);
         if(pictureDtoList!=null && pictureDtoList.size()>0){
             for(WXPictureDto pictureDto:pictureDtoList){
                 pictureDto.setPictureRefId(pictureRefId);
                 pictureDto.setCreateUser(storeDto.getUserUpdate().toString());
             }
             //上传图片
             imgMapper.addImg(pictureDtoList);
         }
         
         if (count <= 0)
         {
             resultData.setFail();
         }
         else
         {
             resultData.setReturnData(storeDto);
         }
         
         return resultData;
     }
// Add By QJP 2017/07/13  End  

    //Add By cning 2017/07/10 Start
    /** 
     * @Title: getAuditStatusById 
     * @Description: 查询门店的审核状态
     * @param 门店id
     * @return	门店审核状态
     */
     public Integer getAuditStatusById(Integer id)
         throws Exception
     {
         Integer auditStatus = this.storeMapper.getAuditStatusById(id);
         return auditStatus;
     }
     //Add By cning 2017/07/10 End

     
     /**
      * 验证门店地址是否重复
      * @param addressDetail
      * @return
      */
     public Integer checkAddress(Map<String,Object> map)
     {	 
    	 Integer count = 0;
    	try {
    		count = storeMapper.checkStoreAddress(map);
		} catch (Exception e) {
			e.printStackTrace();	
		} 
    	return count;
     }

     /**
      * @Title: queryCompanyStore 
      * @Description: 通过公司ID查找未关联门店列表
      * @param param
      * @return List<Store>   
      */
     public ResultData<List<StoreDto>> queryCompanyStore(Map<?, ?> param)
         throws Exception
     {
         //构建返回
         ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
         List<Store> lstStores = this.storeMapper.queryCompanyStore(param);
         List<StoreDto> lstStoreDtos = this.convertListData(lstStores, null);
         if (null != lstStoreDtos && !lstStoreDtos.isEmpty())
         {
             resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
             resultData.setReturnData(lstStoreDtos);
         }
         return resultData;
     }

    public Integer checkBToAAlert(Integer id) throws Exception{
        
        return storeMapper.checkBToAAlert(id);
    }
    
    /**
     * 查询公司信息根据StoreNO
     *
     * @param queryParam 查询条件
     * @return 日志列表
     */
    public Store getStoreByStoreNo(String storeNo) throws Exception{
    	Store store = this.storeMapper.getStoreByStoreNo(storeNo);
        return store;
    }

    public ResultData<List> queryServiceFrameContract(Integer storeId) {

        ResultData<List> resultData = new ResultData<>();

        Map<String,Object> map = new HashMap<>();
        map.put("refId",storeId);
        map.put("fileTypeId",1031);
        map.put("fileSourceId",2);
        List<FileRecordMain> fileRecordMains = fileRecordMainMapper.queryList(map);

        resultData.setReturnData(fileRecordMains);

        return resultData;
    }
    /**
     * 根据门店编号查询是否可以编辑
     * 0 标识自费装修且有翻牌完成日期 ，1表示我司装修且翻牌申请中，2表示我司装修且翻牌审核通过，其他表示可以编辑
     */
	public Integer getStoreBusinessPlaceEditFlag(String storeNo) throws Exception{
        Integer editFlag = this.storeMapper.getStoreBusinessPlaceEditFlag(storeNo);
        return editFlag;
    }

    /**
     *判断门店资质等级是否为甲类或乙类（乙3），如果不是，则提示：门店资质等级为乙类（乙x），不允许续签。
     * @param qparam
     * @return
     */
    public ResultData<?> checkGrade(Map<String, Object> qparam) {
        ResultData<?> resultData = new ResultData<>();
        resultData.setSuccess();
        Integer storeId = Integer.valueOf((String)qparam.get("storeId"));
        Store store = storeMapper.getById(storeId);
        if (DictionaryConstants.Store_QualityGrade_B == store.getABTypeStore()) {
            if ("20004".equals(store.getBTypeStore()+"")) {
                resultData.setFail("门店资质等级为乙类(" + store.getBTypeStoreName() + ")，不允许续签。");
                return resultData;
            }
        }
        return resultData;
    }
    
    /**
     * @Title: querylistByCompanyId 
     * @Description: 通过公司ID查找关联门店列表
     * @param param
     * @return List<Store>   
     */
    public ResultData<List<StoreDto>> querygplistByCompanyId(Map<?, ?> param)
        throws Exception
    {
        //构建返回
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        List<StoreDto> lstStores = this.storeMapper.querygplistByCompanyId(param);
        if (null != lstStores && !lstStores.isEmpty())
        {
            resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(lstStores);
        }
        return resultData;
    }
    
    /**
     * @Title: queryCompanyStore 
     * @Description: 通过公司ID查找未关联门店列表
     * @param param
     * @return List<Store>   
     */
    public ResultData<List<StoreDto>> querygpCompanyStore(Map<?, ?> param)
        throws Exception
    {
        //构建返回
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        List<Store> lstStores = this.storeMapper.querygpCompanyStore(param);
        List<StoreDto> lstStoreDtos = this.convertListData(lstStores, null);
        if (null != lstStoreDtos && !lstStoreDtos.isEmpty())
        {
            resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(lstStoreDtos);
        }
        return resultData;
    }

    public ResultData<Store> getPlainInfoById(Integer storeId) throws Exception{
        ResultData<Store> resultData = new ResultData<>();

        Store store = storeMapper.getPlainInfoById(storeId);

        resultData.setReturnData(store);

        return resultData;
    }

    public void updateAuthCheckStatus(String flowId) {
        storeMapper.updateAuthCheckStatus(flowId);
    }
}
