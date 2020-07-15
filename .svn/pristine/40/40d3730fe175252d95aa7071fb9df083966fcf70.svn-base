package cn.com.eju.deal.mapper.followMap;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.model.followMap.ContactsDto;
import cn.com.eju.deal.model.followMap.FollowCommentDto;
import cn.com.eju.deal.model.followMap.FollowRecordDto;
import cn.com.eju.deal.model.followMap.WjdcRecordDto;
import cn.com.eju.deal.model.followMap.WorkSummaryDto;
import cn.com.eju.deal.model.sweepStreets.StoreNewDto;

/**
 * Created by xu on 2017/4/26.
 */
public interface FollowMapMapper {
    List<StoreNewDto> getLocalStoreList(Map<?, ?> queryParam) throws Exception;
    StoreNewDto getStoreById(StoreNewDto dto);
    List<FollowRecordDto> selectFollowRecordList(StoreNewDto dto);
    List<ContactsDto> selectContactsList(StoreNewDto dto);
    /**
     * desc:调查门店问卷记录
     * 2019年7月1日
     * author:zhenggang.Huang
     */
    List<WjdcRecordDto> getWjdcRecordList(StoreNewDto dto);
    
    int checkContacts(ContactsDto dto);
    int addContacts(ContactsDto dto);
    int updateContacts(ContactsDto dto);

    int addFollow(FollowRecordDto dto);
    int updateFollow(FollowRecordDto dto);
    int addFollowSign(FollowRecordDto dto);
    int updateFollowSignOut(FollowRecordDto dto);
    FollowRecordDto getFollowById(FollowRecordDto dto);

    List<WorkSummaryDto> getWorkSummaryType(FollowRecordDto dto);
    List<WorkSummaryDto> getWorkSummaryByFollowId(FollowRecordDto dto);

    int addWorkSummarySub(WorkSummaryDto dto);
    int addWorkSummaryDetail(List<WorkSummaryDto> list);
    int deleteWorkSummaryDetail(FollowRecordDto dto);

    void saveFollowComment(FollowCommentDto dto);

    List<FollowCommentDto> getAllFollowComment(StoreNewDto dto);

    List<FollowCommentDto> getFollowCommentById(int followId);
    int updateStore(FollowRecordDto dto);
    int checkStoreFollowSign(FollowRecordDto dto);

    void updateEmployDirectFlagToNot(Integer storeId);

    Map<String, String> getContactsInfo(Integer contactsId);
}
