package cn.com.eju.deal.store.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.core.support.AppMsg;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.dto.store.ConcernDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.follow.dao.FollowMapper;
import cn.com.eju.deal.follow.model.Follow;
import cn.com.eju.deal.store.dao.ConcernedStoreMapper;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.store.model.ConcernedStore;
import cn.com.eju.deal.store.model.Store;
import cn.com.eju.deal.user.api.impl.UserAPIImpl;
import cn.com.eju.deal.user.model.User;

/** 
* @ClassName: ConcernedStoreService 
* @Description: 关注的门店Service
* @author 陆海丹 
* @date 2016年4月5日 上午10:21:46 
*/
@Service("concernedStoreService")
public class ConcernedStoreService
{
    @Resource
    private ConcernedStoreMapper concernedStoreMapper;
    
    @Resource
    private StoreMapper storeMapper;
    
    @Resource(name = "storeService")
    private StoreService storeService;
    
    @Resource
    private CompanyMapper companyMapper;
    
    @Resource
    private FileRecordMainMapper fileRecordMainMapper;
    
    @Resource
    private FollowMapper followMapper;
    
    @Resource
    private UserAPIImpl userAPIImpl;
    
    /** 
    * @Title: queryConcernedStoreList 
    * @Description: 查询关注的门店列表
    * @param param userId
    * @return 
    */
    public ResultData<List<ConcernDto>> queryConcernedStoreList(Map<?, ?> param)
        throws Exception
    {
        ResultData<List<ConcernDto>> resultData = new ResultData<>();
        List<ConcernDto> concernDtos = new ArrayList<>();
        ConcernDto concernDto = new ConcernDto();
        Integer userId = null;
        if (null == param.get("userId") || (Integer)param.get("userId") == null)
        {
            resultData.setFail(AppMsg.getString("COMMON.LACK_PARAM"));
            return resultData;
        }
        else
        {
            userId = (Integer)param.get("userId");
        }
        //查出关注列表门店ID
        List<ConcernedStore> concernedStores = this.concernedStoreMapper.queryList(param);
        if (null != concernedStores && !concernedStores.isEmpty())
        {
            for (ConcernedStore concernedStore : concernedStores)
            {
                //                Boolean isAdd=false;
                Store store = this.storeMapper.getById(concernedStore.getStoreId());
                StoreDto storeDto = this.formatMyStore(store, userId);
                concernDto = new ConcernDto();
                concernDto.setConcernedId(concernedStore.getConcernedId());
                concernDto.setDateCreate(concernedStore.getDateCreate());
                concernDto.setIsDelete(concernedStore.getIsDelete());
                concernDto.setStoreId(concernedStore.getStoreId());
                concernDto.setUserId(concernedStore.getUserId());
                concernDto.setStoreDto(storeDto);
                //带查询条件
                this.formatConcernDto(concernDto);
                concernDtos.add(concernDto);
            }
        }
        if (!concernDtos.isEmpty())
        {
            resultData.setReturnData(concernDtos);
        }
        return resultData;
    }
    
    /** 
    * @Title: getById 
    * @Description: 根据id获取详情
    * @param id关注编号
    * @return     
    */
    public ResultData<ConcernDto> getById(Integer id, Integer userId)
        throws Exception
    {
        ResultData<ConcernDto> resultData = new ResultData<>();
        ConcernedStore concernedStore = this.concernedStoreMapper.getById(id);
        Store store = this.storeMapper.getById(concernedStore.getConcernedId());
        StoreDto storeDto = this.formatMyStore(store, userId);
        ConcernDto concernDto = new ConcernDto();
        concernDto.setStoreDto(storeDto);
        concernDto.setConcernedId(concernedStore.getConcernedId());
        concernDto.setDateCreate(concernedStore.getDateCreate());
        concernDto.setIsDelete(concernedStore.getIsDelete());
        concernDto.setStoreId(concernedStore.getStoreId());
        concernDto.setUserId(concernedStore.getUserId());
        this.formatConcernDto(concernDto);
        resultData.setReturnData(concernDto);
        return resultData;
    }
    
    /** 
    * @Title: createStr 
    * @Description: 添加关注
    * @param concernedStore
    * @return     
    */
    public ResultData<ConcernDto> createStr(ConcernedStore concernedStore)
        throws Exception
    {
        ResultData<ConcernDto> resultData = new ResultData<>();
        ConcernDto concernDto = new ConcernDto();
        int count = this.concernedStoreMapper.create(concernedStore);
        if (count > 0)
        {
            concernDto.setConcernedId(concernedStore.getConcernedId());
            concernDto.setDateCreate(concernedStore.getDateCreate());
            concernDto.setIsDelete(concernedStore.getIsDelete());
            concernDto.setStoreId(concernedStore.getStoreId());
            concernDto.setUserId(concernedStore.getUserId());
            resultData.setReturnData(concernDto);
        }
        else
        {
            resultData.setFail();
        }
        return resultData;
    }
    
    /** 
    * @Title: cancelStr 
    * @Description: 取消关注
    * @param id
    * @return     
    */
    public ResultData<ConcernDto> cancelStr(int id)
        throws Exception
    {
        ResultData<ConcernDto> resultData = new ResultData<>();
        int count = this.concernedStoreMapper.deleteById(id);
        if (count <= 0)
        {
            resultData.setFail();
        }
        return resultData;
    }
    
    /*-----------------------------------------private function-----------------------------------------*/
    
    /** 
    * @Title: formatMyStore 
    * @Description: 给StoreDto扩展字段赋值
    * @param store
    * @param userId
    * @return     
    */
    private StoreDto formatMyStore(Store store, Integer userId)
        throws Exception
    {
        StoreDto storeDto = new StoreDto();
        BeanUtils.copyProperties(store, storeDto);
        
        //2.门店图片
        List<FileRecordMain> fileList = this.fileRecordMainMapper.getByStoreId(storeDto.getStoreId());
        if (null != fileList && !fileList.isEmpty())
        {
            FileRecordMain fileRecordMain = fileList.get(0);
            
            //对文件数据进行组装处理
            //StoreService storeService = new StoreService();
            storeService.handleFile(storeDto, fileRecordMain);
            
        }
        //3.最新跟进人员 展示最新的一次跟进人信息（普通员工跟进：可查看自己的、经理及以上可查看全部、此处只展示最新的，若自己有跟进过则优先展示自己的记录）
        Map<String, Object> param = new HashMap<>();
        param.put("storeId", storeDto.getStoreId());
        param.put("userCreate", userId);
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
                ResultData<User> userCreateData = this.userAPIImpl.getUserById(follow.getUserCreate());
                if (userCreateData.getReturnCode() == ReturnCode.SUCCESS)
                {
                    storeDto.setUserNameFollow(userCreateData.getReturnData().getUserName());
                }
                storeDto.setDateFollow(follow.getDateCreate());
            }
        }
        
        return storeDto;
    }
    
    private void formatConcernDto(ConcernDto concernDto)
        throws Exception
    {
        //4.微信端用到 是否在自己的关注中
        Map<String, Object> param = new HashMap<>();
        param.put("storeId", concernDto.getStoreId());
        param.put("userId", concernDto.getUserId());
        param.put("orderName", "dateCreate");
        param.put("orderType", "DESC");
        List<ConcernedStore> concernedStores = this.concernedStoreMapper.queryList(param);
        if (null != concernedStores && !concernedStores.isEmpty())
        {
            concernDto.setIsConcerned(true);
        }
        else
        {
            concernDto.setIsConcerned(false);
        }
    }
    /*-----------------------------------------private function-----------------------------------------*/
}
