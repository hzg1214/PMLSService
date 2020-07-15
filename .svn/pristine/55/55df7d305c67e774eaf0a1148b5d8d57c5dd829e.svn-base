package cn.com.eju.deal.open.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.achievement.model.Achievement;
import cn.com.eju.deal.achievement.service.AchievementService;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.contacts.dao.ContactsMapper;
import cn.com.eju.deal.contacts.model.Contacts;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.company.CompanyDto;
import cn.com.eju.deal.dto.contacts.ContactsDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.open.model.CompanyInfoDto;
import cn.com.eju.deal.open.model.ExchangeAchievementDto;
import cn.com.eju.deal.open.model.ExchangeAchievementDtoOld;
import cn.com.eju.deal.open.model.ExchangeStoreDto;
import cn.com.eju.deal.store.dao.StoreMaintainerMapper;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.store.model.Store;
import cn.com.eju.deal.store.model.StoreMaintainer;
import cn.com.eju.deal.user.dao.GroupMapper;
import cn.com.eju.deal.user.dao.PostMapper;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.Group;
import cn.com.eju.deal.user.model.Post;
import cn.com.eju.deal.user.model.User;

/**   
* APIExchangeService
* @author wenhui.zhang
* @date 2016年6月23日 下午4:23:45
*/
@Service("apiExchangeService")
public class APIExchangeService extends BaseService<Object> {
    
    @Resource
    private CompanyMapper companyMapper;
    
    @Resource
    private StoreMapper storeMapper;
    
    @Resource
    private ContactsMapper contactsMapper;
    
    @Resource
    private StoreMaintainerMapper storeMaintainerMapper;
    
    @Resource
    private UserMapper userMapper;
    
    @Resource
    private PostMapper postMapper;
    
    @Resource
    private GroupMapper groupMapper;
    
    @Resource
	private AchievementService achievementService;
    /**
     * 根据公司名称获取公司信息
     * @param companyName
     * @return CompanyInfoDto
     * @throws Exception 
     */
    public List<CompanyInfoDto> getCompanyInfo(Map<String, Object> queryParam) throws Exception {
        List<CompanyInfoDto> companyInfoList = new ArrayList<CompanyInfoDto>();
        // 公司信息
        List<Company> companyList = companyMapper.getCompanyByNameFuzzy(queryParam);
        if (null != companyList && !companyList.isEmpty()) {
            for (Company company : companyList) {
                Integer companyId = company.getId();
                // 公司信息
                CompanyInfoDto companyInfoDto = new CompanyInfoDto();
                CompanyDto companyDto = new CompanyDto();
                BeanUtils.copyProperties(company, companyDto);
                companyInfoDto.setCompanyDto(companyDto);
                // 门店信息
                setStoreList(companyInfoDto,companyId);
                // 联系人信息
                setContactsList(companyInfoDto, companyId);
                
                companyInfoList.add(companyInfoDto);
            }
        }
        return companyInfoList;
    }
    
    /** 
    * 给CompanyInfoDto设置storeList
    * @param companyInfoDto
    * @param companyId
     * @throws Exception 
    */
    private void setStoreList(CompanyInfoDto companyInfoDto, Integer companyId) throws Exception {
        // 公司下的门店信息
        Map<String,Object> paramsMap = new HashMap<String,Object>();
        paramsMap.put("companyId", companyId);
        List<Store> storeList = storeMapper.getStoreListByCmIdForApi(paramsMap);
        List<StoreDto> storeDtoList = new ArrayList<StoreDto>();
        for(Store store: storeList) {
            StoreDto storeDto = new StoreDto();
            BeanUtils.copyProperties(store, storeDto);
            storeDtoList.add(storeDto);
        }
        companyInfoDto.setStoreList(storeDtoList);
    }
    
    /** 
     * 给CompanyInfoDto设置contactsList
     * @param companyInfoDto
     * @param companyId
     */
     private void setContactsList(CompanyInfoDto companyInfoDto, Integer companyId) {
         // 公司下的联系人信息
         Map<String,Object> queryMap = new HashMap<String,Object>();
         queryMap.put("companyId", companyId);
         List<Contacts> contactsList = contactsMapper.queryList(queryMap);
         List<ContactsDto> contactsDtoList = new ArrayList<ContactsDto>();
         for(Contacts contacts: contactsList) {
             ContactsDto contactsDto = new ContactsDto();
             BeanUtils.copyProperties(contacts, contactsDto);
             contactsDtoList.add(contactsDto);
         }
         companyInfoDto.setContactsList(contactsDtoList);
     }
     
     /**
      * 获取所有未签约门店名称（给交易中心使用）
      * @param queryParam
      * @return
      * @throws Exception
      */
     public List<ExchangeStoreDto> getStoreList(Map<String, Object> queryParam) throws Exception {
//         List<StoreDto> companyInfoList = new ArrayList<StoreDto>();
         // 公司信息
         List<ExchangeStoreDto> companyList = storeMapper.queryWqList(queryParam);
         
         return companyList;
     }

	/**
	 * 
	 * 获取门店维护人业绩归属架构
	 * 
	 * @param queryParam
	 * @return
	 * @throws Exception
	 */
	public ResultData<ExchangeAchievementDto> getMaintainer(String storeNo,String cityNo) throws Exception {
		ResultData<ExchangeAchievementDto> resultData = new ResultData<>();
		Boolean boo = true;

		// 维护人工号
		String maintainerId = null;
		Map<String, Object> reqMap = new HashMap<>();

		// 查询到门店维护人工号，名称
		reqMap.put("storeNo", storeNo);
		List<StoreMaintainer> userList = this.storeMaintainerMapper.getStoreMaintainerHisList(reqMap);
		if (userList != null && userList.size() > 0
				&& StringUtils.isNotBlank(String.valueOf(userList.get(0).getUserCode()))
				&& StringUtils.isNotBlank(String.valueOf(userList.get(0).getUserName()))) {
			maintainerId = userList.get(0).getUserCode();
		} else {
			boo = false;
		}

		// 查询门店维护人相关领导和职位所属组等信息
		if (boo) {
//			Achievement achievement = achievementService.getAchievementInfo(maintainerId,DictionaryConstants.ACHIEVETYPE_EX);
			Map<String,Object> reqMap1 = new HashMap<>();
            reqMap1.put("storeNo",storeNo);
            reqMap1.put("achieveType",DictionaryConstants.ACHIEVETYPE_EX);
            Achievement achievement = achievementService.getAchievementInfoNew(reqMap1);

			if(achievement!=null){
				String groupUserFlag = achievement.getGroupUserFlag();
				if ("1".equals(groupUserFlag)) {
					achievement.setGroupId(null);
					achievement.setGroupName(null);
					achievement.setGroupLeaderCode(null);
					achievement.setGroupLeaderName(null);
					Map<String, String> param = new HashMap<String, String>();
					param.put("userCode", maintainerId);
					// SJBM
					String typeCode = SystemParam.getWebConfigValue("lnkGroupConfig");
					param.put("typeCode", typeCode);
					Map<String, ?> groupInfoMap = groupMapper.getAchieveLinkGroupInfo(param);
					if (null != groupInfoMap) {
						// 给组重新赋值
						Integer groupId = null;
						if (null != groupInfoMap.get("groupId")&&groupInfoMap.containsKey("groupId")) {
							groupId = Integer.parseInt(groupInfoMap.get("groupId").toString());
							achievement.setGroupId(groupId);
						}
						if(null!=groupInfoMap.get("groupName")&&groupInfoMap.containsKey("groupName")){
							achievement.setGroupName(groupInfoMap.get("groupName").toString());						
						}
						if(null!=groupInfoMap.get("groupLeaderCode")&&groupInfoMap.containsKey("groupLeaderCode")){
							achievement.setGroupLeaderCode(groupInfoMap.get("groupLeaderCode").toString());
						}
						if(null!=groupInfoMap.get("groupLeaderName")&&groupInfoMap.containsKey("groupLeaderName")){
							achievement.setGroupLeaderName(groupInfoMap.get("groupLeaderName").toString());
						}
					}
				}
			}
			
			ExchangeAchievementDto eaDto = new ExchangeAchievementDto();
			BeanUtils.copyProperties(achievement, eaDto);
			resultData.setReturnData(eaDto);
		} else {
			resultData.setFail("获取业绩归属人组织信息失败！param =" + storeNo);
		}
		return resultData;
	}

	
     
	/**
     * 
    * 获取门店维护人业绩归属架构
    * @param queryParam
    * @return
     * @throws Exception 
     */
    public ResultData<ExchangeAchievementDtoOld> getMaintainerOld(String storeNo) throws Exception
    {
        ResultData<ExchangeAchievementDtoOld> resultData = new ResultData<>();
        Boolean boo = true;

        //维护人工号
        String maintainerId = null;
        
        ExchangeAchievementDtoOld exchangeAchieve = new ExchangeAchievementDtoOld();
        
        Map<String, Object> reqMap = new HashMap<>();
        
        //查询到门店维护人工号，名称
        reqMap.put("storeNo", storeNo);
        List<StoreMaintainer> userList = this.storeMaintainerMapper.getStoreMaintainerHisList(reqMap);
        if (userList != null && userList.size() > 0
            && StringUtils.isNotBlank(String.valueOf(userList.get(0).getUserCode()))
            && StringUtils.isNotBlank(String.valueOf(userList.get(0).getUserName())))
        {
            maintainerId = userList.get(0).getUserCode();
        }
        else
        {
            boo = false;
        }
        
        //查询门店维护人相关领导和职位所属组等信息
        if (boo)
        {
            // 查询当前【业绩归属维护人】的所属组和所属岗的信息
            // 1.根据userId查询用户信息
            User currentUser = userMapper.getUserByCode(maintainerId);
            //-------------------*--------------------
            // 门店维护人工号
            exchangeAchieve.setMaintainerId(currentUser.getUserCode());
            // 门店维护人姓名
            exchangeAchieve.setMaintainer(currentUser.getUserName());
            //-------------------*--------------------
            
            // ------------------- 可能多岗位的场合 start -------------- //
            // 有可能存在多岗因此需要用List接收
            List<Post> currentPostList = null;
            // 2.查询门店维护人员所属岗位信息(可能存在多岗的场合，即用List集合接收)
            currentPostList = postMapper.getExpandPostByUserId(currentUser.getUserId());
            Group currentGroup = null;
            // 岗位类型(18:拓展总监   20:拓展经理    21:拓展专员   22:事业部总经理)
            int currentPostType = 0;
            if (currentPostList != null && !currentPostList.isEmpty())
            {
                // 多岗的场合
                for (Post currentPost : currentPostList)
                {
                    String currentPostGroupId = null;
                    if (currentPost.getGroupId() != null)
                    {
                        currentPostGroupId = String.valueOf(currentPost.getGroupId());
        }
                    String currentUserGgroupId = null;
                    if (currentUser.getGroupId() != null)
                    {
                        currentUserGgroupId = String.valueOf(currentUser.getGroupId());
    }
    
                    // 当前岗对应的组
                    if (currentPostGroupId.equals(currentUserGgroupId))
                    {
                        // 3.查询所属组信息
                        currentGroup = groupMapper.selectByPrimaryKey(currentPost.getGroupId());
                        // 设置当前业绩归属拓展人员信息
                        this.setRelatePersonInfo(currentUser, currentGroup, currentPost, exchangeAchieve);
                        // 获取当前拓展人员的岗位类型
                        currentPostType = currentPost.getTypeId();
                    }
                }
            }
            // ------------------- 可能多岗位的场合 end -------------- //
					
            // =============== 查询当前拓展人员的上级信息 start ================ //
            // 当前归属拓展人所属组为【拓展组】的场合
            if (currentGroup != null)
            {
                if (currentGroup.getTypeId() == 4)
                {
                    // 拓展经理的场合 (20:拓展经理   21:拓展专员)
                    if (currentPostType == 20)
                    {
                        //  1. 查找【区域总监】所属组（固定在【拓展经理】的基础上往跳1级）
                        Group leadersGroup = groupMapper.selectByGroupId(currentGroup.getParentId());
                        // 非空判断
                        if (null != leadersGroup.getGroupManagerId()
                            && StringUtil.isNotEmpty(leadersGroup.getGroupManagerId()))
                        {
                            int userId = Integer.parseInt(leadersGroup.getGroupManagerId());
                            // 设置【区域总监】的信息
                            this.setLeaderInfo(userId, exchangeAchieve);
                        }
                        else
                        {
                            // 上级领导人不存在的场合、就只存它上级所在组的信息
                            this.setPerformTeamInfo(leadersGroup, exchangeAchieve);
                        }
					
                        //  2. 查找【事业部总经理】所属组（固定在【区域总监】的基础上往跳2级）
                        Group firstLeadersGroup = groupMapper.selectByGroupId(leadersGroup.getParentId());
                        Group secondLeadersGroup = groupMapper.selectByGroupId(firstLeadersGroup.getParentId());
                        // 非空判断
                        if (null != secondLeadersGroup.getGroupManagerId()
                            && StringUtil.isNotEmpty(secondLeadersGroup.getGroupManagerId()))
                        {
                            int userId = Integer.parseInt(secondLeadersGroup.getGroupManagerId());
                            // 设置【事业部总经理】的信息
                            this.setLeaderInfo(userId, exchangeAchieve);
				}	
                        else
                        {
                            // 上级领导人不存在的场合、就只存它上级所在组的信息
                            this.setPerformTeamInfo(secondLeadersGroup, exchangeAchieve);
			}
                        
                        // 拓展专员的场合
		}
                    else
                    {
                        // 1. 查找【拓展经理】所属组（根据【拓展专员】的GroupManagerId找到他的上级）
                        if (null != currentGroup.getGroupManagerId()
                            && StringUtil.isNotEmpty(currentGroup.getGroupManagerId()))
                        {
                            int userId = Integer.parseInt(currentGroup.getGroupManagerId());
                            // 设置【拓展经理】的信息
                            this.setLeaderInfo(userId, exchangeAchieve);
    }
                        else
                        {
                            // 上级领导人不存在的场合、就只存它上级所在组的信息 (该处拓展经理同专员所属同一个组)
                            this.setPerformTeamInfo(currentGroup, exchangeAchieve);
                        }
                        
                        //  2. 查找【区域总监】所属组（固定在【拓展经理】的基础上往上跳1级）
                        Group leadersGroup = groupMapper.selectByGroupId(currentGroup.getParentId());
                        // 非空判断
                        if (null != leadersGroup.getGroupManagerId()
                            && StringUtil.isNotEmpty(leadersGroup.getGroupManagerId()))
                        {
                            int userId = Integer.parseInt(leadersGroup.getGroupManagerId());
                            // 设置【区域总监】的信息
                            this.setLeaderInfo(userId, exchangeAchieve);
                        }
                        else
                        {
                            // 上级领导人不存在的场合、就只存它上级所在组的信息
                            this.setPerformTeamInfo(leadersGroup, exchangeAchieve);
                        }
    
                        //  3. 查找【事业部总经理】所属组（固定在【区域总监】的基础上往上跳2级）
                        Group firstLeadersGroup = groupMapper.selectByGroupId(leadersGroup.getParentId());
                        Group secondLeadersGroup = groupMapper.selectByGroupId(firstLeadersGroup.getParentId());
                        // 非空判断
                        if (null != secondLeadersGroup.getGroupManagerId()
                            && StringUtil.isNotEmpty(secondLeadersGroup.getGroupManagerId()))
                        {
                            int userId = Integer.parseInt(secondLeadersGroup.getGroupManagerId());
                            // 设置【事业部总经理】的信息
                            this.setLeaderInfo(userId, exchangeAchieve);
                        }
                        else
                        {
                            // 上级领导人不存在的场合、就只存它上级所在组的信息
                            this.setPerformTeamInfo(secondLeadersGroup, exchangeAchieve);
                        }
                    }
                    
                    // 当前归属拓展人所属组为【拓展事业部】的场合
                }
                else if (currentGroup.getTypeId() == 8)
                {
                    
                    //  查找【事业部总经理】所属组（固定在【区域总监】的基础上往上跳2级）
                    Group firstLeadersGroup = groupMapper.selectByGroupId(currentGroup.getParentId());
                    Group secondLeadersGroup = groupMapper.selectByGroupId(firstLeadersGroup.getParentId());
                    // 非空判断
                    if (null != secondLeadersGroup.getGroupManagerId()
                        && StringUtil.isNotEmpty(secondLeadersGroup.getGroupManagerId()))
                    {
                        int userId = Integer.parseInt(secondLeadersGroup.getGroupManagerId());
                        // 设置【事业部总经理】的信息
                        this.setLeaderInfo(userId, exchangeAchieve);
                    }
                    else
                    {
                        // 上级领导人不存在的场合、就只存它上级所在组的信息
                        this.setPerformTeamInfo(secondLeadersGroup, exchangeAchieve);
                    }
                }
                // =============== 查询当前拓展人员的上级信息 end ================ //
            }
        }
        if(boo == true){
            resultData.setReturnData(exchangeAchieve);
            return resultData;
        }else{
            resultData.setFail("获取业绩归属人组织信息失败！param =" + storeNo);
            return resultData;
        }
    }
    
/************************************************************业绩归属专用-开始**************************************************************/
    /** 
     * 设置业绩归属门店维护人信息（包含拓展专员、拓展经理、区域总监、事业部总经理）
     * @param paramUser 用户信息
     * @param paramGroup 组信息
     * @param paramPost 岗位信息
     * @param exchangeAchieve 业绩归属信息
     * @return
     * @throws Exception
     */
     private void setRelatePersonInfo(User paramUser, Group paramGroup, Post paramPost, ExchangeAchievementDtoOld exchangeAchieve)
         throws Exception
     {
         // 门店维护人所属组为【拓展组】的场合（专员和拓展经理都属于拓展组）
         if (paramGroup.getTypeId() == 4)
         {
             // 门店维护人拓展经理的场合 (20:拓展经理   21:拓展专员)
             if (paramPost.getTypeId() == 20)
             {
                 // 门店维护人拓展经理工号
                 exchangeAchieve.setMaintainerLeaderId(paramUser.getUserCode());
                 // 门店维护人拓展经理姓名
                 exchangeAchieve.setMaintainerLeader(paramUser.getUserName());
                 // 门店维护人拓展经理所属组
                 exchangeAchieve.setPerformTeam3(paramGroup.getGroupName());
                 // 门店维护人拓展经理所属岗位
                 exchangeAchieve.setPostTypeName3(paramPost.getPostName());
                 // 门店维护人拓展经理所属组Id
                 exchangeAchieve.setMaintainerLeaderGroupId(paramGroup.getGroupId());
                 // 门店维护人拓展经理所属岗位Id
                 exchangeAchieve.setMaintainerLeaderPostId(paramPost.getPostId());
             }
             else// 拓展专员的场合
             {
                 // 门店维护人工号
                 exchangeAchieve.setMaintainerId(paramUser.getUserCode());
                 // 门店维护人姓名
                 exchangeAchieve.setMaintainer(paramUser.getUserName());
                 // 门店维护人所属组（业绩归属团队四）
                 exchangeAchieve.setPerformTeam4(paramGroup.getGroupName());
                 // 门店维护人所属岗位
                 exchangeAchieve.setPostTypeName4(paramPost.getPostName());
                 // 门店维护人所属组Id
                 exchangeAchieve.setMaintainerGroupId(paramGroup.getGroupId());
                 // 门店维护人所属岗位Id
                 exchangeAchieve.setMaintainerPostId(paramPost.getPostId());
             }
             
             // 所属组为【拓展事业部】的场合
         }
         else if (paramGroup.getTypeId() == 8)
         {
             // 区域总监工号
             exchangeAchieve.setRegionalDirectorId(paramUser.getUserCode());
             // 区域总监姓名
             exchangeAchieve.setRegionalDirector(paramUser.getUserName());
             // 区域总监所属组
             exchangeAchieve.setPerformTeam2(paramGroup.getGroupName());
             // 区域总监所属岗位
             exchangeAchieve.setPostTypeName2(paramPost.getPostName());
             // 区域总监所属组Id
             exchangeAchieve.setRegionalDirectorGroupId(paramGroup.getGroupId());
             // 区域总监所属岗位Id
             exchangeAchieve.setRegionalDirectorPostId(paramPost.getPostId());
             
             // 所属组为【事业部】的场合
         }
         else if (paramGroup.getTypeId() == 7)
         {
             // 事业部总经理工号
             exchangeAchieve.setBusinessManagerId(paramUser.getUserCode());
             // 事业部总经理姓名
             exchangeAchieve.setBusinessManager(paramUser.getUserName());
             // 事业部总经理所属组
             exchangeAchieve.setPerformTeam1(paramGroup.getGroupName());
             // 事业部总经理所属岗位
             exchangeAchieve.setPostTypeName1(paramPost.getPostName());
             // 事业部总经理所属组Id
             exchangeAchieve.setBusinessManagerGroupId(paramGroup.getGroupId());
             // 事业部总经理所属岗位Id
             exchangeAchieve.setBusinessManagerPostId(paramPost.getPostId());
         }
     }
     
     
     /** 
     * 设置业绩归属门店维护人员（上级）信息
     * @param userId 维护人Id
     * @param exchangeAchieve 业绩归属信息
     */
     private void setLeaderInfo(Integer userId, ExchangeAchievementDtoOld exchangeAchieve)
         throws Exception
     {
         // 1.查找上级领导用户信息
         User leadershipUser = userMapper.getUserByUserId(userId);
         
         // ------------------- 可能多岗位的场合 start -------------- //
         // 多岗的场合
         List<Post> leadershipPostList = null;
         // 2.查询门店维护人员所属岗位信息(可能存在多岗的场合，即用List集合接收)
         leadershipPostList = postMapper.getExpandPostByUserId(leadershipUser.getUserId());
         Group leadershipGroup = new Group();
         if (leadershipPostList != null)
         {
             // 多岗的场合
             for (Post leadershipPost : leadershipPostList)
             {
                 String leadershipPostGroupId = null;
                 if (leadershipPost.getGroupId() != null)
                 {
                     leadershipPostGroupId = String.valueOf(leadershipPost.getGroupId());
                 }
                 String leadershipUserGgroupId = null;
                 if (leadershipUser.getGroupId() != null)
                 {
                     leadershipUserGgroupId = String.valueOf(leadershipUser.getGroupId());
                 }
                 
                 // 当前岗对应的组
                 if (leadershipPostGroupId.equals(leadershipUserGgroupId))
                 {
                     // 3.查询所属组信息
                     leadershipGroup = groupMapper.selectByPrimaryKey(leadershipPost.getGroupId());
                     // 设置当前业绩归属门店维护人员信息
                     this.setRelatePersonInfo(leadershipUser, leadershipGroup, leadershipPost, exchangeAchieve);
                 }
             }
         }
         // ------------------- 可能多岗位的场合 end -------------- //
     }
     
     /** 
      * 设置业绩归属团队信息（包含拓展专员、拓展经理、区域总监、事业部总经理）
      * @param paramGroup 组信息
      * @param exchangeAchieve 业绩关联人员信息
      * @throws Exception
      */
      private void setPerformTeamInfo(Group paramGroup, ExchangeAchievementDtoOld exchangeAchieve)
          throws Exception
      {
          // 门店维护人所属组为【拓展组】的场合（门店维护人和拓展经理都属于拓展组）
          if (paramGroup.getTypeId() == 4)
          {
              // 拓展专员和拓展经理的场合
              // 拓展经理所属组 (业绩归属团队三)
              exchangeAchieve.setPerformTeam3(paramGroup.getGroupName());
              // 拓展经理所属组Id
              exchangeAchieve.setMaintainerLeaderGroupId(paramGroup.getGroupId());
              // 业绩拓展专员所属组（业绩归属团队四）
              exchangeAchieve.setPerformTeam4(paramGroup.getGroupName());
              // 拓展专员所属组Id
              exchangeAchieve.setMaintainerGroupId(paramGroup.getGroupId());
              
              // 所属组为【拓展事业部】的场合
          }
          else if (paramGroup.getTypeId() == 8)
          {
              // 区域总监所属组 (业绩归属团队二)
              exchangeAchieve.setPerformTeam2(paramGroup.getGroupName());
              // 区域总监所属组Id
              exchangeAchieve.setRegionalDirectorGroupId(paramGroup.getGroupId());
              
              // 所属组为【事业部】的场合
          }
          else if (paramGroup.getTypeId() == 7)
          {
              // 事业部总经理所属组 (业绩归属团队一)
              exchangeAchieve.setPerformTeam1(paramGroup.getGroupName());
              // 事业部总经理所属组Id
              exchangeAchieve.setBusinessManagerGroupId(paramGroup.getGroupId());
          }
      }
      /************************************************************业绩归属专用-结束**************************************************************/    
}
