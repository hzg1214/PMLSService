/**   
* @Title: FollowService.java 
* @Package cn.com.eju.deal.follow.service 
* @Description: 跟进Service包
* @author 陆海丹
* @date 2016年3月24日 下午12:13:40 
* @version V1.0   
*/
package cn.com.eju.deal.follow.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import oracle.jrockit.jfr.tools.ConCatRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.core.support.AppMsg;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.dto.company.CompanyDto;
import cn.com.eju.deal.dto.follow.FollowDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.follow.dao.FollowMapper;
import cn.com.eju.deal.follow.model.Follow;
import cn.com.eju.deal.model.sweepStreets.WXPictureDto;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.store.model.Store;
import cn.com.eju.deal.store.service.StoreService;
import cn.com.eju.deal.user.api.impl.UserAPIImpl;
import cn.com.eju.deal.user.model.User;

/** 
* @ClassName: FollowService 
* @Description: 跟进Service
* @author 陆海丹
* @date 2016年3月24日 下午12:13:40 
*  
*/
@Service("followService")
public class FollowService extends BaseService
{
    @Resource
    private FollowMapper followMapper;
    
    @Resource
    private UserAPIImpl userAPIImpl;
    
    @Resource
    private CompanyMapper companyMapper;
    
    @Resource
    private StoreMapper storeMapper;
    
    @Resource(name="storeService")
    private StoreService storeService;
    
    /** 
    * @Title: getById 
    * @Description: 根据跟进编号查询跟进明细
    * @param id
    * @return     
    */
    public FollowDto getById(int id)throws Exception
    {
        FollowDto followDto=new FollowDto();
        List<FileRecordMainDto> signTimeFiles = new ArrayList<>();
        FileRecordMainDto signTimeFile = new FileRecordMainDto();
        Follow follow=this.followMapper.getById(id);
        
        //1、跟进详情-签到图片  
        List<WXPictureDto> signTimePicList = follow.getSignPicList();
        if(signTimePicList!=null && signTimePicList.size()>0) {
        	for (WXPictureDto wxPictureDto : signTimePicList) {
        		signTimeFile.setFileRecordMainId(Integer.parseInt(wxPictureDto.getId()));
        		signTimeFile.setFileUrl(wxPictureDto.getMiddlePictureUrl());
        		signTimeFile.setFileAbbrUrl(wxPictureDto.getSmallPictureUrl());
        		signTimeFiles.add(signTimeFile);
			}
        }
        followDto.setSignTimeFiles(signTimeFiles);
        
        //2、跟进详情-签退图片
        List<FileRecordMainDto> signOutTimeFiles = new ArrayList<>();
        List<WXPictureDto> signOutTimePicList = follow.getSignOutPicList();
        FileRecordMainDto signOutTimeFile = new FileRecordMainDto();
        if(signOutTimePicList!=null && signOutTimePicList.size()>0) {
        	for (WXPictureDto wxPictureDto : signOutTimePicList) {
        		signOutTimeFile.setFileRecordMainId(Integer.parseInt(wxPictureDto.getId()));
        		signOutTimeFile.setFileUrl(wxPictureDto.getMiddlePictureUrl());
        		signOutTimeFile.setFileAbbrUrl(wxPictureDto.getSmallPictureUrl());
        		signOutTimeFiles.add(signOutTimeFile);
        	}
        }
        followDto.setSignOutTimeFiles(signOutTimeFiles);
        
        
      //Model转换Dto
        BeanUtils.copyProperties(follow,followDto);
        this.formatFollow(followDto);
        return followDto;
    }
    
    /** 
    * @Title: getStrById 
    * @Description: 根据跟进编号查询跟进明细（供接口调用）
    * @param id
    * @return     
    */
    public ResultData<FollowDto>  getStrById(int id)throws Exception
    {
      //构建返回
        ResultData<FollowDto> resultData = new ResultData<FollowDto>();
        //查询详情
        FollowDto followDto=this.getById(id);
        resultData.setReturnData(followDto);       
        return resultData;
    }
    
    /**
     * 
     * desc:跟新详情
     * 2020年2月27日
     */
    public ResultData<FollowDto>  getFollowViewById(Map<String, Object> param)throws Exception
    {
    	//构建返回
    	ResultData<FollowDto> resultData = new ResultData<FollowDto>();
    	Integer id = (Integer) param.get("id");
    	//查询详情
    	FollowDto followDto=this.getById(id);
    	List<FileRecordMainDto> fileDtos = storeMapper.selectFile(param);
    	followDto.setFileDtos(fileDtos);
    	resultData.setReturnData(followDto);       
    	return resultData;
    }
    
    /** 
    * @Title: queryList 
    * @Description: 查询跟进列表
    * @param param
    * @return List<Follow>   
    */
    public List<FollowDto> queryList(Map<?, ?> param) throws Exception
    {
        List<Follow> lstFollows=this.followMapper.queryList(param);
        
        //TODO 这里需要加权限 自己可查看自己的跟进、同级不可看、经理及以上可查看全部的跟进记录
        List<FollowDto> lstFollowDtos=this.convertListData(lstFollows);
        
        return lstFollowDtos;
    }
    
    /** 
    * @Title: queryStrList 
    * @Description: 查询跟进列表（供接口调用）
    * @param param
    * @return ResultData<List<FollowDto>>
    */
    public ResultData<List<FollowDto>> queryStrList(Map<?, ?> param) throws Exception
    {
      //构建返回
        ResultData<List<FollowDto>> resultData = new ResultData<List<FollowDto>>();
        List<FollowDto> lstFollowDtos=this.queryList(param);
        resultData.setTotalCount("0");
        if(null!=lstFollowDtos && !lstFollowDtos.isEmpty()){
            resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(lstFollowDtos);
        }        
        return resultData;
    }
    
    /** 
    * @Title: queryFollowStoreList 
    * @Description: 我的跟进门店列表
    * @param param
    * @return     
    */
    public ResultData<List<FollowDto>> queryFollowStoreList(Map<?, ?> param) throws Exception
    {
      //构建返回
        ResultData<List<FollowDto>> resultData = new ResultData<List<FollowDto>>();
        List<FollowDto> lstFollowDtos=new ArrayList<>();
        resultData.setTotalCount("0");
        Integer userCreate=null;
        if( null!=param.get("userCreate")){
            userCreate=(Integer)param.get("userCreate");
        }
        else {
            resultData.setFail(AppMsg.getString("COMMON.LACK_PARAM"));
            return resultData;
        }
        List<Follow> follows=this.followMapper.getStoreFollows(userCreate);
        for (Follow follow : follows)
        {
            FollowDto followDto=new FollowDto();
            StoreDto storeDto=this.storeService.getById(follow.getStoreId(),userCreate);
            BeanUtils.copyProperties(follow, followDto);
            followDto.setStoreDto(storeDto);
            lstFollowDtos.add(followDto);
        }
        if(null!=lstFollowDtos && !lstFollowDtos.isEmpty()){
            resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(lstFollowDtos);
        }        
        return resultData;
    }
    
    /** 
    * @Title: queryMyFollows 
    * @Description: 我的跟进列表
    * @param param
    * @return     
    */
    public ResultData<List<FollowDto>> queryMyFollows(Map<?, ?> param) throws Exception
    {
        //构建返回
        ResultData<List<FollowDto>> resultData = new ResultData<List<FollowDto>>();
        List<FollowDto> lstFollowDtos=new ArrayList<>();
        Integer userCreate=null;
        if( null!=param.get("userCreate")){
            userCreate=(Integer)param.get("userCreate");
        }
        else {
            resultData.setFail(AppMsg.getString("COMMON.LACK_PARAM"));
            return resultData;
        }
        List<Follow> follows=this.followMapper.getMyFollows(param);
        if(null!=follows && !follows.isEmpty()){
            lstFollowDtos=this.convertListData(follows);
        }
        if(null!=lstFollowDtos && !lstFollowDtos.isEmpty()){
            resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(lstFollowDtos);
        }        
        return resultData;
    }
    
    /** 
    * @Title: create 
    * @Description: 添加跟进记录
    * @param follow
    * @return     
    */
    public int create(Follow follow) throws Exception
    {
        int count=this.followMapper.create(follow);
        return count;
    }
    
    /** 
    * @Title: createStr 
    * @Description: 添加跟进记录（供接口调用）
    * @param followDto
    * @return     
    */
    public ResultData<FollowDto> createStr(FollowDto followDto) throws Exception
    {
      //构建返回
        ResultData<FollowDto> resultData = new ResultData<FollowDto>();
        Follow follow=new Follow();
      //赋值
        BeanUtils.copyProperties(followDto, follow);
        int count=this.create(follow);
        if(count<=0)
        {
            resultData.setFail();
        }else {
          //赋值
            BeanUtils.copyProperties(follow,followDto );
            resultData.setReturnData(followDto);
        }
        return resultData;
    }
    
    /** 
    * @Title: update 
    * @Description: 更新跟进信息
    * @param store
    * @return     
    */
    public int update(Follow follow) throws Exception
    {
        return this.followMapper.update(follow);
    }
    
    /** 
    * @Title: updateStr 
    * @Description: 更新跟进信息（供接口调用）
    * @param storeDto
    * @return ResultData<StoreDto>    
    */
    public ResultData<FollowDto> updateStr(FollowDto followDto) throws Exception
    {
      //构建返回
        ResultData<FollowDto> resultData = new ResultData<FollowDto>();
        Follow follow=new Follow();
      //赋值
        BeanUtils.copyProperties(followDto, follow);
        int count=this.update(follow);
        if(count<=0)
        {
            resultData.setFail();
        }else {
            resultData.setReturnData(followDto);
        }
        return resultData;
    }
    
    /** 
    * @Title: delete 
    * @Description: 删除跟进记录
    * @param id
    * @return     
    */
    public int delete(int id) throws Exception
    {
        return this.followMapper.deleteById(id);
    }
    
    /** 
    * @Title: deleteStr 
    * @Description: 删除门店（供接口调用）
    * @param id
    * @return     
    */
    public ResultData<FollowDto> deleteStr(int id) throws Exception
    {
      //构建返回
        ResultData<FollowDto> resultData = new ResultData<FollowDto>();
        int count=this.delete(id);
        if(count<=0)
        {
            resultData.setFail();
        }
        return resultData;
    }
    
    /** 
    * @Title: getCompanyBySelf 
    * @Description: 获取userCreate创建的门店编号为storeId的客户信息
    * @param storeId
    * @param userCreate 客户创建者编号
    * @return     
    */
    @SuppressWarnings("unchecked")
    public ResultData<CompanyDto> getCompanyBySelf(Map<?, ?> queryParam)throws Exception
    {
        ResultData<CompanyDto> resultData=new ResultData<>();
        Integer storeId=null;Integer userCreate=null;
        
        List<Integer> userIdList = new ArrayList<Integer>();
        
        if(null!=queryParam.get("storeId") && null!=queryParam.get("userCreate")){
            storeId=(Integer)queryParam.get("storeId");
            userCreate=(Integer)queryParam.get("userCreate");
            
            userIdList = (List<Integer>)queryParam.remove("userIdList");
        }
        else {
            resultData.setFail(AppMsg.getString("COMMON.LACK_PARAM"));
            return resultData;
        }
        CompanyDto companyDto=null;
        List<Company> companies=this.companyMapper.getCompanyByStoreId(storeId);
        
        for (Company company : companies)
        {
            if(userCreate.equals(company.getUserCreate()))
            {
                companyDto=new CompanyDto();
                BeanUtils.copyProperties(company, companyDto);
                break;
            }else {
                continue;
            }            
        }
        resultData.setReturnData(companyDto);
        return resultData;
    }
    
    /*-----------------------------------------private function-----------------------------------------*/
    /** 
    * @Title: convertListData 
    * @Description: 将List<Follow>转换成List<FollowDto>
    * @param lstStores
    * @return List<FollowDto>    
    */
    private List<FollowDto> convertListData(List<Follow> lstFollows) throws Exception{
        List<FollowDto> lstFollowDtos=new ArrayList<FollowDto>();
        if(null!=lstFollows && !lstFollows.isEmpty()){
            FollowDto followDto=new FollowDto();
            for (Follow follow : lstFollows)
            {
                followDto=new FollowDto();
                BeanUtils.copyProperties(follow, followDto);
                formatFollow(followDto);
                lstFollowDtos.add(followDto);
            }
        }
        return lstFollowDtos;
    }
    

    private void formatFollow(FollowDto followDto) throws Exception
    {
       //录入者姓名
       if(null!=followDto.getUserCreate())
       {
           ResultData<User> userCreateData=this.userAPIImpl.getUserById(followDto.getUserCreate());
           if(userCreateData.getReturnCode()==ReturnCode.SUCCESS){
               followDto.setUserNameCreate(userCreateData.getReturnData().getUserName());
           }
       }
       //客户姓名
       if(null!=followDto.getCompanyId()){
           Company company=this.companyMapper.getById(followDto.getCompanyId());
           if(null!=company){
               followDto.setCompanyName(company.getCompanyName());
           }
       }
       //门店名称
       
       if(null!=followDto.getStoreId()){
           Store store=this.storeMapper.getById(followDto.getStoreId());
           if(null!=store){
               followDto.setStoreName(store.getName());
           }
       }
    }
    
    /*-----------------------------------------private function-----------------------------------------*/
}
