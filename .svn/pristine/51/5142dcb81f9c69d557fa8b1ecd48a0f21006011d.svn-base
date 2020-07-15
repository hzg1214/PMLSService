package cn.com.eju.deal.service.followMap;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.util.HttpClientUtil;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.mapper.followMap.FollowMapMapper;
import cn.com.eju.deal.mapper.image.ImgMapper;
import cn.com.eju.deal.mapper.sweepStreets.SweepStreetsMapper;
import cn.com.eju.deal.model.followMap.ContactsDto;
import cn.com.eju.deal.model.followMap.FollowCommentDto;
import cn.com.eju.deal.model.followMap.FollowRecordDto;
import cn.com.eju.deal.model.followMap.WjdcRecordDto;
import cn.com.eju.deal.model.followMap.WorkSummaryDto;
import cn.com.eju.deal.model.sweepStreets.StoreNewDto;
import cn.com.eju.deal.model.sweepStreets.WXPictureDto;
import cn.com.eju.deal.store.model.StoreAuthCheck;
import cn.com.eju.deal.store.service.AuthCheckService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xu on 2017/4/26.
 */
@Service("followMapService")
public class FollowMapService {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private FollowMapMapper followMapMapper;
    @Resource
    private SweepStreetsMapper sweepStreetsMapper;
    @Resource
    private ImgMapper imgMapper;

    @Resource
    private AuthCheckService authCheckService;

    public ResultData<List<StoreNewDto>> getLocalStoreList(Map<?, ?> queryParam) throws Exception{
        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        List<StoreNewDto> list=followMapMapper.getLocalStoreList(queryParam);
        if(null!=list && !list.isEmpty()){
            resultData.setTotalCount((String)queryParam.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(list);
        }
        return resultData;
    }
    public ResultData getStoreFollowList(StoreNewDto dto) throws Exception{
        ResultData resultData = new ResultData();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String nowDate=df.format(new Date());

        StoreNewDto storeNewDto=followMapMapper.getStoreById(dto);
        if(storeNewDto==null){
            resultData.setFail("门店信息不存在");
            return resultData;
        }

        String fpStatusStr = storeNewDto.getFpStatusStr();//翻牌状态
        String contractTypeNameStr = storeNewDto.getContractTypeNameStr();//合同类型名称
        String contractStatusStr = storeNewDto.getContractStatusStr();
        if(!StringUtil.isEmpty(fpStatusStr) || !StringUtil.isEmpty(contractTypeNameStr)
        		|| !StringUtil.isEmpty(contractStatusStr) ) {
        	storeNewDto.setWhetherStatus("1");//表示有值显示元素位置
        }
        
        Map<String,Object> queryParam = new HashMap<>();
        queryParam.put("storeId",dto.getStoreId());
        List<StoreAuthCheck> storeAuthCheckList = authCheckService.queryList(queryParam);
        if(storeAuthCheckList != null && storeAuthCheckList.size() > 0) {
        	storeNewDto.setStoreAuthCheckStatus("1");//显示手牌记录栏
        }
        storeNewDto.setStoreAuthCheckList(storeAuthCheckList);

        List<FollowRecordDto> result = new ArrayList<FollowRecordDto>();
        List<FollowRecordDto> followRecordDtoList=followMapMapper.selectFollowRecordList(dto);
        List<ContactsDto> contactsDtoList=followMapMapper.selectContactsList(dto);
        //获取该门店问卷调查记录 hzg 2019/7/1
        List<WjdcRecordDto> wjdcRecordDtoList=followMapMapper.getWjdcRecordList(dto);
        if(wjdcRecordDtoList != null && wjdcRecordDtoList.size() > 0) {
        	storeNewDto.setWjdcRecordStatus("1");//显示问卷调查栏
        }
        if(followRecordDtoList==null){
            followRecordDtoList=new ArrayList<FollowRecordDto>();
        }else{
            //跟进记录的点评
            List<FollowCommentDto> comments = followMapMapper.getAllFollowComment(dto);

            Map<String,List<FollowRecordDto>> mapList=new HashMap<String, List<FollowRecordDto>>();
            for(FollowRecordDto pro : followRecordDtoList){
                String key = pro.getDateCreate();
                List<FollowRecordDto> dateCreateList=mapList.get(key);
                if(dateCreateList==null){
                    dateCreateList=new ArrayList<FollowRecordDto>();
                    mapList.put(key,dateCreateList);

                    FollowRecordDto followRecordDto = new FollowRecordDto();
                    followRecordDto.setFollowRecordList(dateCreateList);
                    followRecordDto.setDateCreate(key);
                    if(key.equals(nowDate)){
                        followRecordDto.setOperationType("update");
                    }else{
                        followRecordDto.setOperationType("show");
                    }
                    result.add(followRecordDto);
                }
                FollowRecordDto follow = new FollowRecordDto();
                follow.setFollowId(pro.getFollowId());
                follow.setDateCreate(pro.getDateCreate());
                follow.setUserCreate(pro.getUserCreate());
                follow.setUserNameCreate(pro.getUserNameCreate());
                follow.setFollowPurpose(pro.getFollowPurpose());
                follow.setFollowPurposeName(pro.getFollowPurposeName());
                follow.setFollowType(pro.getFollowType());
                follow.setFollowTypeName(pro.getFollowTypeName());
                follow.setContent(pro.getContent());
                follow.setOperationType(pro.getOperationType());
                //点评权限
                follow.setAuth(pro.getAuth());

                List<FollowCommentDto> commentDtos = new ArrayList<>();
                if (comments != null) {
                    for (FollowCommentDto comment : comments) {
                        if (pro.getFollowId().equals(comment.getFollowId())){
                            commentDtos.add(comment);
                        }
                    }
                }
                follow.setFollowCommentList(commentDtos);
                if (commentDtos.size() == 0) {
                    follow.setCommented(false);
                }else{
                    follow.setCommented(true);
                }
                dateCreateList.add(follow);
            }
        }
        if(contactsDtoList==null){
            contactsDtoList=new ArrayList<ContactsDto>();
        }
        if(wjdcRecordDtoList==null){
        	wjdcRecordDtoList=new ArrayList<WjdcRecordDto>();
        }
        storeNewDto.setFollowRecordList(result);
        storeNewDto.setContactsList(contactsDtoList);
        storeNewDto.setWjdcRecordList(wjdcRecordDtoList);

        resultData.setReturnData(storeNewDto);

        return resultData;
    }

    public ResultData addContacts(ContactsDto dto) throws Exception{
        ResultData resultData = new ResultData();

        StoreNewDto storeNewDto=new StoreNewDto();
        storeNewDto.setStoreId(dto.getStoreId());
        StoreNewDto storeDto = sweepStreetsMapper.getStoreById(storeNewDto);
        if(storeDto.getBusinessStatus()==20903){
            resultData.setFail("门店已停业，不能添加跟进记录");
            return resultData;
        }

        int result=followMapMapper.checkContacts(dto);
        if(result>0){
            resultData.setFail("联系人已存在");
            return resultData;
        }
        int count=followMapMapper.addContacts(dto);
        if(count>=1){
            return resultData;
        }else{
            resultData.setFail("新增联系人失败");
        }
        return resultData;
    }
    public ResultData updateContacts(ContactsDto dto) throws Exception{
        ResultData resultData = new ResultData();

        StoreNewDto storeNewDto=new StoreNewDto();
        storeNewDto.setStoreId(dto.getStoreId());
        StoreNewDto storeDto = sweepStreetsMapper.getStoreById(storeNewDto);
        if(storeDto.getBusinessStatus()==20903){
            resultData.setFail("门店已停业，不能添加跟进记录");
            return resultData;
        }

        int result=followMapMapper.checkContacts(dto);
        if(result>0){
            resultData.setFail("联系人已存在");
            return resultData;
        }
        int count=followMapMapper.updateContacts(dto);
        if(count>=1){
            return resultData;
        }else{
            resultData.setFail("修改联系人失败");
        }
        return resultData;
    }

    public ResultData addFollow(FollowRecordDto dto) throws Exception{
        ResultData resultData = new ResultData();

        StoreNewDto storeNewDto=new StoreNewDto();
        storeNewDto.setStoreId(dto.getStoreId());
        StoreNewDto storeDto = sweepStreetsMapper.getStoreById(storeNewDto);
        if(storeDto.getBusinessStatus()==20903){
            resultData.setFail("门店已停业，不能添加跟进记录");
            return resultData;
        }
        int count=followMapMapper.addFollow(dto);
        if(count>=1){
            //经纪人数有值，更新门店表的经纪人数字段
            if(dto.getAgentNum()!=null && dto.getAgentNum()!=0){
                followMapMapper.updateStore(dto);
            }

            if(dto.getSubmitWorkSummaryString()!=null && !"".equals(dto.getSubmitWorkSummaryString())){
                FollowRecordDto followRecordDto=JsonUtil.parseToObject(dto.getSubmitWorkSummaryString(),FollowRecordDto.class);
                List<WorkSummaryDto> workSummaryDtoList=followRecordDto.getWorkSummarySubList();
                dto.setWorkSummarySubList(workSummaryDtoList);
                dto.setPicString(followRecordDto.getPicString());
            }
            if(dto.getPicString()!=null && !"-".equals(dto.getPicString()) && !"".equals(dto.getPicString())){
                String[] picArray=dto.getPicString().split(",");
                String pRefId= UUID.randomUUID().toString();
                List<WXPictureDto> pictureList=new ArrayList<WXPictureDto>();
                for(String picString:picArray){
                    String[] picDtoArray=picString.split("\\^");
                    if(picDtoArray.length>0){
                        WXPictureDto pictureDto=new WXPictureDto();
                        pictureDto.setPictureRefId(pRefId);
                        pictureDto.setSmallPictureUrl(picDtoArray[0]);
                        pictureDto.setMiddlePictureUrl(picDtoArray[1]);
                        pictureDto.setBigPictureUrl(picDtoArray[2]);
                        pictureDto.setCreateUser(dto.getUserCreate()+"");
                        pictureList.add(pictureDto);
                    }
                }
                //上传图片
                imgMapper.addImg(pictureList);
                dto.setSignPictureRefId(pRefId);
            }

            if(dto.getWorkSummarySubList()!=null && dto.getWorkSummarySubList().size()>0){
                followMapMapper.deleteWorkSummaryDetail(dto);//先删除工作总结明细在添加
                for(WorkSummaryDto wsSubDto:dto.getWorkSummarySubList()){
                    wsSubDto.setFollowId(dto.getFollowId()+"");
                    wsSubDto.setCreateUser(dto.getUserCreate()+"");
                    if("30".equals(wsSubDto.getWsType())){
                        wsSubDto.setPictureRefId(dto.getSignPictureRefId());
                    }
                    followMapMapper.addWorkSummarySub(wsSubDto);
                    if("10".equals(wsSubDto.getWsType()) && wsSubDto.getWorkSummarySubServiceList()!=null){
                        for(WorkSummaryDto wsDto:wsSubDto.getWorkSummarySubServiceList()){
                            wsDto.setCreateUser(dto.getUserCreate()+"");
                            wsDto.setWsSubId(wsSubDto.getWsSubId());
                        }
                        followMapMapper.addWorkSummaryDetail(wsSubDto.getWorkSummarySubServiceList());
                    }else if("20".equals(wsSubDto.getWsType()) && wsSubDto.getWorkSummarySubFindList()!=null){
                        for(WorkSummaryDto wsDto:wsSubDto.getWorkSummarySubFindList()){
                            wsDto.setCreateUser(dto.getUserCreate()+"");
                            wsDto.setWsSubId(wsSubDto.getWsSubId());
                        }
                        followMapMapper.addWorkSummaryDetail(wsSubDto.getWorkSummarySubFindList());
                    }else if("30".equals(wsSubDto.getWsType()) && wsSubDto.getWorkSummarySubCheckList()!=null){
                        for(WorkSummaryDto wsDto:wsSubDto.getWorkSummarySubCheckList()){
                            wsDto.setCreateUser(dto.getUserCreate()+"");
                            wsDto.setWsSubId(wsSubDto.getWsSubId());
                        }
                        followMapMapper.addWorkSummaryDetail(wsSubDto.getWorkSummarySubCheckList());
                    }
                }
            }

            return resultData;
        }else{
            resultData.setFail("操作失败");
        }
        return resultData;
    }
    public ResultData updateFollow(FollowRecordDto dto) throws Exception{
        ResultData resultData = new ResultData();

        StoreNewDto storeNewDto=new StoreNewDto();
        storeNewDto.setStoreId(dto.getStoreId());
        StoreNewDto storeDto = sweepStreetsMapper.getStoreById(storeNewDto);
        if(storeDto.getBusinessStatus()==20903){
            resultData.setFail("门店已停业，不能添加跟进记录");
            return resultData;
        }

        int count=followMapMapper.updateFollow(dto);
        if(count>=1){
            //经纪人数有值，更新门店表的经纪人数字段
            if(dto.getAgentNum()!=null && dto.getAgentNum()!=0){
                followMapMapper.updateStore(dto);
            }

            if(dto.getSubmitWorkSummaryString()!=null && !"".equals(dto.getSubmitWorkSummaryString())){
                FollowRecordDto followRecordDto=JsonUtil.parseToObject(dto.getSubmitWorkSummaryString(),FollowRecordDto.class);
                List<WorkSummaryDto> workSummaryDtoList=followRecordDto.getWorkSummarySubList();
                dto.setWorkSummarySubList(workSummaryDtoList);
                dto.setPicString(followRecordDto.getPicString());
            }
            if(dto.getPicString()!=null && !"-".equals(dto.getPicString()) && !"".equals(dto.getPicString())){
                String[] picArray=dto.getPicString().split(",");
                String pRefId= UUID.randomUUID().toString();
                List<WXPictureDto> pictureList=new ArrayList<WXPictureDto>();
                for(String picString:picArray){
                    String[] picDtoArray=picString.split("\\^");
                    if(picDtoArray.length>0){
                        WXPictureDto pictureDto=new WXPictureDto();
                        pictureDto.setPictureRefId(pRefId);
                        pictureDto.setSmallPictureUrl(picDtoArray[0]);
                        pictureDto.setMiddlePictureUrl(picDtoArray[1]);
                        pictureDto.setBigPictureUrl(picDtoArray[2]);
                        pictureDto.setCreateUser(dto.getUserCreate()+"");
                        pictureList.add(pictureDto);
                    }
                }
                //上传图片
                imgMapper.addImg(pictureList);
                dto.setSignPictureRefId(pRefId);
            }

            if(dto.getWorkSummarySubList()!=null && dto.getWorkSummarySubList().size()>0){
                followMapMapper.deleteWorkSummaryDetail(dto);//先删除工作总结明细在添加
                for(WorkSummaryDto wsSubDto:dto.getWorkSummarySubList()){
                    wsSubDto.setFollowId(dto.getFollowId()+"");
                    wsSubDto.setCreateUser(dto.getUserCreate()+"");
                    if("30".equals(wsSubDto.getWsType())){
                        wsSubDto.setPictureRefId(dto.getSignPictureRefId());
                    }
                    followMapMapper.addWorkSummarySub(wsSubDto);
                    if("10".equals(wsSubDto.getWsType()) && wsSubDto.getWorkSummarySubServiceList()!=null){
                        for(WorkSummaryDto wsDto:wsSubDto.getWorkSummarySubServiceList()){
                            wsDto.setCreateUser(dto.getUserCreate()+"");
                            wsDto.setWsSubId(wsSubDto.getWsSubId());
                        }
                        followMapMapper.addWorkSummaryDetail(wsSubDto.getWorkSummarySubServiceList());
                    }else if("20".equals(wsSubDto.getWsType()) && wsSubDto.getWorkSummarySubFindList()!=null){
                        for(WorkSummaryDto wsDto:wsSubDto.getWorkSummarySubFindList()){
                            wsDto.setCreateUser(dto.getUserCreate()+"");
                            wsDto.setWsSubId(wsSubDto.getWsSubId());
                        }
                        followMapMapper.addWorkSummaryDetail(wsSubDto.getWorkSummarySubFindList());
                    }else if("30".equals(wsSubDto.getWsType()) && wsSubDto.getWorkSummarySubCheckList()!=null){
                        for(WorkSummaryDto wsDto:wsSubDto.getWorkSummarySubCheckList()){
                            wsDto.setCreateUser(dto.getUserCreate()+"");
                            wsDto.setWsSubId(wsSubDto.getWsSubId());
                        }
                        followMapMapper.addWorkSummaryDetail(wsSubDto.getWorkSummarySubCheckList());
                    }
                }
            }
            return resultData;
        }else{
            resultData.setFail("操作失败");
        }
        return resultData;
    }
    public ResultData addFollowSign(FollowRecordDto dto) throws Exception{
        ResultData resultData = new ResultData();

        StoreNewDto storeNewDto=new StoreNewDto();
        storeNewDto.setStoreId(dto.getStoreId());
        StoreNewDto storeDto = sweepStreetsMapper.getStoreById(storeNewDto);
        if(storeDto.getBusinessStatus()==20903){
            resultData.setFail("门店已停业，不能添加跟进记录");
            return resultData;
        }
        //一天只能签到跟进一次，校验记录是否存在
        int num=followMapMapper.checkStoreFollowSign(dto);
        if(num>0){
            resultData.setFail("签到记录已添加，请勿重复签到！");
            return resultData;
        }


        if(dto.getPicString()!=null && !"-".equals(dto.getPicString()) && !"".equals(dto.getPicString())){
            WXPictureDto wxPictureDto=new WXPictureDto();
            wxPictureDto.setPictureRefId(dto.getSignPictureRefId());
            imgMapper.deleteImg(wxPictureDto);

            String[] picArray=dto.getPicString().split(",");
            String pRefId= UUID.randomUUID().toString();
            List<WXPictureDto> pictureList=new ArrayList<WXPictureDto>();
            for(String picString:picArray){
                String[] picDtoArray=picString.split("\\^");
                if(picDtoArray.length>0){
                    WXPictureDto pictureDto=new WXPictureDto();
                    pictureDto.setPictureRefId(pRefId);
                    pictureDto.setSmallPictureUrl(picDtoArray[0]);
                    pictureDto.setMiddlePictureUrl(picDtoArray[1]);
                    pictureDto.setBigPictureUrl(picDtoArray[2]);
                    pictureDto.setCreateUser(dto.getUserCreate()+"");
                    pictureList.add(pictureDto);
                }
            }
            //上传图片
            imgMapper.addImg(pictureList);
            dto.setSignPictureRefId(pRefId);
        }

        int count=followMapMapper.addFollowSign(dto);
        if(count>=1){
            resultData.setReturnData(dto.getFollowId());
            return resultData;
        }else{
            resultData.setFail("操作失败");
        }
        return resultData;
    }
    public ResultData updateFollowSignOut(FollowRecordDto dto) throws Exception{
        ResultData resultData = new ResultData();

        StoreNewDto storeNewDto=new StoreNewDto();
        storeNewDto.setStoreId(dto.getStoreId());
        StoreNewDto storeDto = sweepStreetsMapper.getStoreById(storeNewDto);
        if(storeDto.getBusinessStatus()==20903){
            resultData.setFail("门店已停业，不能添加跟进记录");
            return resultData;
        }

        if(dto.getPicString()!=null && !"-".equals(dto.getPicString()) && !"".equals(dto.getPicString())){
            WXPictureDto wxPictureDto=new WXPictureDto();
            wxPictureDto.setPictureRefId(dto.getSignOutPictureRefId());
            imgMapper.deleteImg(wxPictureDto);

            String[] picArray=dto.getPicString().split(",");
            String pRefId= UUID.randomUUID().toString();
            List<WXPictureDto> pictureList=new ArrayList<WXPictureDto>();
            for(String picString:picArray){
                String[] picDtoArray=picString.split("\\^");
                if(picDtoArray.length>0){
                    WXPictureDto pictureDto=new WXPictureDto();
                    pictureDto.setPictureRefId(pRefId);
                    pictureDto.setSmallPictureUrl(picDtoArray[0]);
                    pictureDto.setMiddlePictureUrl(picDtoArray[1]);
                    pictureDto.setBigPictureUrl(picDtoArray[2]);
                    pictureDto.setCreateUser(dto.getUserCreate()+"");
                    pictureList.add(pictureDto);
                }
            }
            //上传图片
            imgMapper.addImg(pictureList);
            dto.setSignOutPictureRefId(pRefId);
        }

        int count=followMapMapper.updateFollowSignOut(dto);
        if(count>=1){
            return resultData;
        }else{
            resultData.setFail("操作失败");
        }
        return resultData;
    }
    public ResultData getFollowById(FollowRecordDto dto) throws Exception{
        ResultData resultData = new ResultData();

        FollowRecordDto followRecordDto=followMapMapper.getFollowById(dto);
        //跟进点评
        List<FollowCommentDto> commentDtos=followMapMapper.getFollowCommentById(dto.getFollowId());
        if(followRecordDto!=null){
            if (commentDtos != null && !commentDtos.isEmpty()){
                followRecordDto.setCommented(true);
                followRecordDto.setFollowCommentList(commentDtos);
            }

            resultData.setReturnData(followRecordDto);
        }else{
            resultData.setFail("跟进记录不存在");
        }
        return resultData;
    }

    public ResultData getWorkSummaryData(FollowRecordDto dto) throws Exception{
        ResultData resultData = new ResultData();
        List<WorkSummaryDto> list;
        if(dto.getFollowId()!=null&&dto.getFollowId()!=0){
            list=followMapMapper.getWorkSummaryByFollowId(dto);
            if(list!=null&&list.size()>0){
                resultData.setReturnData(list);
            }else{
                list=followMapMapper.getWorkSummaryType(dto);
            }
        }else{
            list=followMapMapper.getWorkSummaryType(dto);
        }
        resultData.setReturnData(list);
        return resultData;
    }


    public ResultData saveFollowComment(FollowCommentDto dto) {
        ResultData resultData = new ResultData();
        try {
            followMapMapper.saveFollowComment(dto);
            resultData.setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setFail();
        }
        return resultData;
    }

    public ResultData updateEmployDirect(ContactsDto dto) throws Exception{
        ResultData resultData = new ResultData();

        if(dto.getEmployDirectFlag().intValue() == 1){
            //切换直聘账号，需将原直聘账号修改为否
            followMapMapper.updateEmployDirectFlagToNot(dto.getStoreId());
        }
        int count=followMapMapper.updateContacts(dto);
        if(count>=1){

            //推送房友直聘系统
            if(dto.getEmployDirectFlag().intValue() == 1) {
                try {
                    Map<String, String> info = followMapMapper.getContactsInfo(dto.getContactsId());
                    if (StringUtil.isNotEmpty(info.get("storeNo"))
                            && StringUtil.isNotEmpty(info.get("userName"))
                            && StringUtil.isNotEmpty(info.get("telephone"))) {

                        String url = SystemParam.getWebConfigValue("zhipinFangyouUrl");

                        String UserName= URLEncoder.encode(info.get("userName"), "utf-8");
                        url += "ejuemploy/storeUserController/crmAddStoreUser?storeNo="
                                +info.get("storeNo")+"&telephone="
                                +info.get("telephone")+"&userName="
                                +UserName;
                        logger.info("推送房友直聘系统start#####请求#url=" + url);
                        String json = HttpClientUtil.jsonPostHttps(url, "");
                        logger.info("房友直聘系统end#####请求结果：" + json);
                    }
                } catch (Exception e) {
                    logger.error("zhipinFangyou", "FollowMapService", "crmAddStoreUser", null, null, null, "推送房友直聘系统异常。", e);
                    resultData.setFail("推送房友直聘系统异常。");
                }
            }

            return resultData;
        }else{
            resultData.setFail("修改直聘账号失败,请刷新页面");
        }
        return resultData;
    }
}
