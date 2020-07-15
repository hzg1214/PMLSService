package cn.com.eju.deal.achievement.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.eju.deal.store.dao.StoreMapper;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.achievement.dao.AchievementMapper;
import cn.com.eju.deal.achievement.model.Achievement;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.user.dao.GroupMapper;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.User;

/**
 * 业绩归属人操作
 * @author wenhui.zhang
 * date: 2017年3月6日 下午4:51:40
 */
@Service
public class AchievementService extends BaseService<Achievement> {
	/*
	 * 注入DAO
	 */
	@Resource
	private AchievementMapper achievementMapper;
	
	@Resource
	private GroupMapper groupMapper;

    @Resource
    private UserMapper userMapper;

	@Resource
	private StoreMapper storeMapper;

	/**
	 * 新增业绩归属人
	 */
	public int createAchievement(Achievement achievement) {
		return this.achievementMapper.create(achievement);
	}

	/**
	 * 删除业绩归属人
	 */
	public int deleteAchievement(Integer relateId) throws Exception {
		return this.achievementMapper.delAchievement(relateId);
	}

	/**
	 * 获取归属信息（门店维护人）
	 */
	public Achievement getAchievementInfo(String userCode,String achieveType) throws Exception {
		String typeCode = SystemParam.getWebConfigValue("centerConfig");
		return this.achievementMapper.getAchievementInfo(userCode,typeCode,achieveType);
	}

	/**
	 * 获取合同的业绩信息
	 */
	public Achievement getAchievementInfoContract(Map<String,Object> reqMap) throws Exception {
		return this.achievementMapper.getAchievementInfoContract(reqMap);
	}

	/**
	 * 获取归属信息(门店)
	 */
	public Achievement getAchievementInfoNew(Map<String,Object> reqMap) throws Exception {
		return this.achievementMapper.getAchievementInfoNew(reqMap);
	}
	/**
	 * 获取归属信息(门店)
	 */
	public Achievement getAchievementInfoNewByCenterId(Map<String,Object> reqMap) throws Exception {
		return this.achievementMapper.getAchievementInfoNewByCenterId(reqMap);
	}

	/**
	 * 1.查询业绩归属信息 2.保存业绩归属信息
	 * @Param relateId:合同操作时存储的是合同Id 新房联动时存储的报备Id
	 * @param type:'create'代表新增合同 'update' 代表 修改合同 'LinkCreate' 代表联动的新增
	 * @Param from:'Link'代表 新房联动 'contract'代表合同操作
	 * @throws Exception
	 */
	public Boolean saveAchievementInfo(String userCode, Integer relateId, String type, String from,String achieveType,Integer storeId,String accountProject,String accountProjectNo) throws Exception {
		Boolean isSaved = true;
		try{

			Achievement achievement = null;

			if (from.equals("Link")) {
				Map<String,Object> reqMap = new HashMap<>();
				reqMap.put("achieveType",achieveType);
				reqMap.put("storeId",storeId);
				achievement = this.getAchievementInfoNew(reqMap);
				
				
			}else{
				// 调用UMS接口获取归属人列表信息
				achievement = this.getAchievementInfo(userCode,achieveType);
			}




			if (null != achievement) {
				// 业绩类型 -门店业绩:17901
				if (from.equals("contract")) {
					if (type.equals("update")) {
						this.deleteAchievement(relateId);
					}
					achievement.setAchieveType(DictionaryConstants.ACHIEVETYPE_STORE);
				}
				// 业绩类型 -新房联动业绩:17902
				if (from.equals("Link")) {
					achievement.setAchieveType(DictionaryConstants.ACHIEVETYPE_LINK);
					//--------
					User user= userMapper.getUserByCode(userCode);
					achievement.setExpenderCode(userCode);
					achievement.setExpenderName(user.getUserName());
					//--------
				}
				achievement.setRelateId(relateId);
				achievement.setAccountProject(accountProject);
				achievement.setAccountProjectNo(accountProjectNo);
				String groupUserFlag = achievement.getGroupUserFlag();
				if ("1".equals(groupUserFlag)) {
					achievement.setGroupId(null);
					achievement.setGroupName(null);
					achievement.setGroupLeaderCode(null);
					achievement.setGroupLeaderName(null);
					Map<String, String> param = new HashMap<String, String>();
					param.put("userCode", userCode);
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
				int num = achievementMapper.create(achievement);
				if (num != 1) {
					isSaved = false;
				}
			} else {
				isSaved = false;
			}			
		}catch(Exception e){
			isSaved = false;
		}
		return isSaved;
	}
	
	/**
	 * 1.查询业绩归属信息 2.保存业绩归属信息
	 * @Param relateId:合同操作时存储的是合同Id 新房联动时存储的报备Id
	 * @param type:'create'代表新增合同 'update' 代表 修改合同 'LinkCreate' 代表联动的新增
	 * @Param from:'Link'代表 新房联动 'contract'代表合同操作
	 * @throws Exception
	 */
	public Boolean saveContractAchievementInfo(String achieveType,String userCode, String userName,Integer relateId, Integer centerId,String accountProject,String accountProjectNo) throws Exception {
		Boolean isSaved = true;
		try{
			Map<String,Object> reqMap = new HashMap<String,Object> ();
			reqMap.put("centerGroupId", centerId);
			reqMap.put("achieveType", achieveType);
			Achievement achievement = this.getAchievementInfoContract(reqMap);
		if (null != achievement) {
			
			achievement.setExpenderCode(userCode);
			achievement.setExpenderName(userName);
			achievement.setRelateId(relateId);
			achievement.setAccountProjectNo(accountProjectNo);
			achievement.setAccountProject(accountProject);
				String groupUserFlag = achievement.getGroupUserFlag();
				if ("1".equals(groupUserFlag)) {
					achievement.setGroupId(null);
					achievement.setGroupName(null);
					achievement.setGroupLeaderCode(null);
					achievement.setGroupLeaderName(null);
					Map<String, String> param = new HashMap<String, String>();
					param.put("userCode", userCode);
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
				int num = achievementMapper.create(achievement);
				if (num != 1) {
					isSaved = false;
				}
			} else {
				isSaved = false;
			}			
		}catch(Exception e){
			isSaved = false;
		}
		return isSaved;
	}
	/**
	 * 1.查询业绩归属信息 2.保存业绩归属信息
	 * @Param relateId:合同操作时存储的是合同Id 新房联动时存储的报备Id
	 * @param type:'create'代表新增合同 'update' 代表 修改合同 'LinkCreate' 代表联动的新增
	 * @Param from:'Link'代表 新房联动 'contract'代表合同操作
	 * @throws Exception
	 */
	public Boolean saveAchievementInfo2(String userCode, Integer relateId, String type, String from,String achieveType,Integer centerId,String accountProject,String accountProjectNo) throws Exception {
		Boolean isSaved = true;
		try{
			Achievement achievement = null;
			if (from.equals("Link")) {
				Map<String,Object> reqMap = new HashMap<>();
				reqMap.put("achieveType",achieveType);
				reqMap.put("centerGroupId",centerId);
				achievement = this.getAchievementInfoNewByCenterId(reqMap);
			}
			if (null != achievement) {
				// 业绩类型 -新房联动业绩:17902
				if (from.equals("Link")) {
					achievement.setAchieveType(DictionaryConstants.ACHIEVETYPE_LINK);
					User user = userMapper.getUserByCode(userCode);
					achievement.setExpenderCode(userCode);
					if (null != user) {
						achievement.setExpenderName(user.getUserName());
					}
				}
				achievement.setRelateId(relateId);
				achievement.setAccountProject(accountProject);
				achievement.setAccountProjectNo(accountProjectNo);
				String groupUserFlag = achievement.getGroupUserFlag();
				if ("1".equals(groupUserFlag)) {
					achievement.setGroupId(null);
					achievement.setGroupName(null);
					achievement.setGroupLeaderCode(null);
					achievement.setGroupLeaderName(null);
					Map<String, String> param = new HashMap<String, String>();
					param.put("userCode", userCode);
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
				int num = achievementMapper.create(achievement);
				if (num != 1) {
					isSaved = false;
				}
			} else {
				isSaved = false;
			}			
		}catch(Exception e){
			isSaved = false;
		}
		return isSaved;
	}



	/**
	 * 1.查询业绩归属信息
	 * 2.保存业绩归属信息
	 * @Param relateId:报备id
	 */
	public Boolean saveQtAchievementInfo(User user, Integer relateId,String achieveType, Integer centerId, String accountProject, String accountProjectNo) throws Exception {
		Boolean isSaved = true;
		try {
			Achievement achievement = null;

			Map<String, Object> reqMap = new HashMap<>();
			reqMap.put("achieveType", achieveType);
			reqMap.put("centerGroupId", centerId);
			achievement = this.getAchievementInfoNewByCenterId(reqMap);

			if (null != achievement) {

				achievement.setAchieveType(DictionaryConstants.ACHIEVETYPE_QT);
				achievement.setExpenderCode(user.getUserCode());
				if (null != user) {
					achievement.setExpenderName(user.getUserName());
				}

				achievement.setRelateId(relateId);
				achievement.setAccountProject(accountProject);
				achievement.setAccountProjectNo(accountProjectNo);
				String groupUserFlag = achievement.getGroupUserFlag();
				if ("1".equals(groupUserFlag)) {
					achievement.setGroupId(null);
					achievement.setGroupName(null);
					achievement.setGroupLeaderCode(null);
					achievement.setGroupLeaderName(null);
					Map<String, String> param = new HashMap<String, String>();
					param.put("userCode", user.getUserCode());
					// SJBM
					String typeCode = SystemParam.getWebConfigValue("lnkGroupConfig");
					param.put("typeCode", typeCode);
					Map<String, ?> groupInfoMap = groupMapper.getAchieveLinkGroupInfo(param);
					if (null != groupInfoMap) {
						// 给组重新赋值
						Integer groupId = null;
						if (null != groupInfoMap.get("groupId") && groupInfoMap.containsKey("groupId")) {
							groupId = Integer.parseInt(groupInfoMap.get("groupId").toString());
							achievement.setGroupId(groupId);
						}
						if (null != groupInfoMap.get("groupName") && groupInfoMap.containsKey("groupName")) {
							achievement.setGroupName(groupInfoMap.get("groupName").toString());
						}
						if (null != groupInfoMap.get("groupLeaderCode") && groupInfoMap.containsKey("groupLeaderCode")) {
							achievement.setGroupLeaderCode(groupInfoMap.get("groupLeaderCode").toString());
						}
						if (null != groupInfoMap.get("groupLeaderName") && groupInfoMap.containsKey("groupLeaderName")) {
							achievement.setGroupLeaderName(groupInfoMap.get("groupLeaderName").toString());
						}
					}
				}
				int num = achievementMapper.createQt(achievement);
				if (num != 1) {
					isSaved = false;
				}
			} else {
				isSaved = false;
			}
		} catch (Exception e) {
			isSaved = false;
		}
		return isSaved;
	}

	/**
	 * 删除其他收入业绩
	 */
	public int deleteQtAchievement(Integer relateId) throws Exception {
		return this.achievementMapper.delQtAchievement(relateId);
	}
}
