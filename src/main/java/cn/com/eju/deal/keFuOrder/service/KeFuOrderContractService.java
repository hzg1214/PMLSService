package cn.com.eju.deal.keFuOrder.service;

import cn.com.eju.deal.base.dto.code.CommonCodeDto;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.keFuOrder.dao.KeFuOrderMapper;
import cn.com.eju.deal.keFuOrder.dao.KeFuOrderReplyMapper;
import cn.com.eju.deal.keFuOrder.dao.KefuOrderVerifyMapper;
import cn.com.eju.deal.keFuOrder.model.KeFuOrder;
import cn.com.eju.deal.keFuOrder.model.KeFuOrderCkDto;
import cn.com.eju.deal.keFuOrder.model.KeFuOrderDto;
import cn.com.eju.deal.keFuOrder.model.KeFuOrderReply;
import cn.com.eju.deal.keFuOrder.model.*;
import cn.com.eju.deal.oa.dao.OaOperatorMapper;
import cn.com.eju.deal.user.model.User;

import org.springframework.stereotype.Service;


import javax.annotation.Resource;

import java.util.*;
import java.util.stream.Collectors;

@Service("keFuOrderContractService")
public class KeFuOrderContractService extends BaseService {

	@Resource
	private KeFuOrderMapper keFuOrderMapper;
	@Resource
	private KeFuOrderReplyMapper keFuOrderReplyMapper;
	@Resource
    private KefuOrderVerifyMapper kefuOrderVerifyMapper;
    @Resource
    private FileRecordMainMapper fileRecordMainMapper;

    /**
     * 查询客服反馈工单列表
     * @param reqMap
     * @return
     */
    public ResultData<List<KeFuOrderDto>> getKeFuOrderList(Map<String, Object> reqMap) throws Exception {
        ResultData<List<KeFuOrderDto>> resultData = new ResultData<List<KeFuOrderDto>>();
        resultData.setFail();
        List<KeFuOrderDto> keFuOrderDtoList = keFuOrderMapper.getKeFuOrderList(reqMap);
        if (keFuOrderDtoList != null && keFuOrderDtoList.size() > 0) {
            resultData.setReturnData(keFuOrderDtoList);
            resultData.setTotalCount((String) reqMap.get(QueryConst.TOTAL_COUNT));
            resultData.setSuccess();
        }
        return resultData;
    }
    /**
     * 保存或更新客服反馈工单
     */
	public ResultData<?> saveKeFuOrder(Map<String, Object> reqMap) throws Exception {
        ResultData<KeFuOrderDto> resultData = new ResultData<>();

        KeFuOrder kefuOrder = new KeFuOrder();
        //归属城市
        String cityNo = (String) reqMap.get("cityNo");

        //客户姓名
        String customerName = reqMap.get("customerName").toString();
        String customerTel = reqMap.get("customerTel").toString();
        //咨询产品名称 typeId=239
        String consultProduct = reqMap.get("selectConsultProduct").toString();
        //一二级分类
        Integer categoryOne = Integer.valueOf(reqMap.get("categoryOne").toString());
        Integer categoryTwo = Integer.valueOf(reqMap.get("categoryTwo").toString());
        //问题概要
        String questionTopic = reqMap.get("questionTopic").toString();
        //问题描述
        String questionDesc = reqMap.get("questionDesc").toString();
        //订单优先级
        Integer questionLevel = Integer.valueOf(reqMap.get("questionLevel").toString());
        //经办人
        String userCode = reqMap.get("operatorValue").toString();
        //创建人
        Integer userCreate = Integer.valueOf(reqMap.get("userIdCreate").toString());
        //问题状态
        Integer dealStatus = Integer.valueOf(reqMap.get("dealStatus").toString());
        //附件id集
        String fileRecordMainIds = reqMap.get("fileRecordMainIds").toString();
        String type = (String) reqMap.get("type");//更新或新增
        String companyId = reqMap.get("companyId").toString();
        if(null != companyId && StringUtil.isNotEmpty(companyId)){
        	//公司Id
        	kefuOrder.setCompanyId(Integer.valueOf(companyId));
        }
        String storeId = reqMap.get("storeId").toString();
        if(null != storeId && StringUtil.isNotEmpty(storeId)){
        	//门店
            kefuOrder.setStoreId(Integer.valueOf(storeId));
        }
        //属性赋值
        kefuOrder.setCityNo(cityNo);
        kefuOrder.setCustomerName(customerName);
        kefuOrder.setCustomerTel(customerTel);
        kefuOrder.setConsultProduct(consultProduct);
        kefuOrder.setCategoryOne(categoryOne);
        kefuOrder.setCategoryTwo(categoryTwo);
        kefuOrder.setQuestionTopic(questionTopic);
        kefuOrder.setQuestionDesc(questionDesc);
        kefuOrder.setQuestionLevel(questionLevel);
        kefuOrder.setUserCode(userCode);
        //状态赋值
        kefuOrder.setDealStatus(dealStatus);
        kefuOrder.setDelFlag(false);
        // 如果状态是处理完成
        if(dealStatus==24203) {
			kefuOrder.setCheckStatus(24301);
		}else{
			kefuOrder.setCheckStatus(null);
		}
		if("create".equals(type)) {
			if(reqMap.containsKey("keFuOrderNo")) {
				//申请编号
				String keFuOrderNo = (String) reqMap.get("keFuOrderNo");
				kefuOrder.setOrderNo(keFuOrderNo);
				kefuOrder.setUserCreate(userCreate);
				kefuOrder.setDateCreate(new Date());
				//保存
				int count = keFuOrderMapper.insertSelective(kefuOrder);
				if(count <=0) {
					resultData.setFail("保存客服反馈工单失败");
					return resultData;
				}
				Integer gkefuOrderId =  kefuOrder.getId();
				if (fileRecordMainIds != null && StringUtil.isNotEmpty(fileRecordMainIds)) {
					String[] arrays = fileRecordMainIds.split(",");
					// 关联相关文件(RefId)
					for (int i = 0; i < arrays.length; i++){
						if (StringUtil.isNotEmpty(arrays[i])){
							FileRecordMain fileRecordMain = new FileRecordMain();
							fileRecordMain.setFileRecordMainId(Integer.valueOf(arrays[i]));
							fileRecordMain.setRefId(gkefuOrderId);
							fileRecordMain.setIsDelete(false);
							fileRecordMain.setCompanyId(null);
							fileRecordMainMapper.update(fileRecordMain);
						}
					}
				}
			}else {
				resultData.setFail("保存客服反馈工单失败,无申请编号");
				return resultData;
			}
		} else if("update".equals(type)) {
			String oldfileRecordMainIds = reqMap.get("oldfileRecordMainIds").toString();
			//变更主键
			Integer id = Integer.valueOf((String) reqMap.get("id"));
			kefuOrder.setUserUpdate(userCreate);
			kefuOrder.setDateUpdate(new Date());
			kefuOrder.setId(id);
			int count = keFuOrderMapper.updateByPrimaryKeySelective(kefuOrder);
			if(count <=0) {
				resultData.setFail("更新公盘初始会员合同失败");
                return resultData;
			}
			//对原上传文件删除
			if (oldfileRecordMainIds != null && StringUtil.isNotEmpty(oldfileRecordMainIds)) {
				String[] oldfileRecordMainIdsArray = oldfileRecordMainIds.split(",");
				for (int i = 0; i < oldfileRecordMainIdsArray.length; i++) {
					if (StringUtil.isNotEmpty(oldfileRecordMainIdsArray[i])) {
						FileRecordMain fileRecordMain = new FileRecordMain();
						fileRecordMain.setFileRecordMainId(Integer.valueOf(oldfileRecordMainIdsArray[i]));
						fileRecordMain.setRefId(null);
						fileRecordMain.setIsDelete(true);
						fileRecordMainMapper.update(fileRecordMain);
					}
				}
			}
			// 关联相关文件(RefId)
			if (fileRecordMainIds != null && StringUtil.isNotEmpty(fileRecordMainIds)) {
				String[] arrays = fileRecordMainIds.split(",");
				for (int i = 0; i < arrays.length; i++) {
					if (StringUtil.isNotEmpty(arrays[i])) {
						FileRecordMain fileRecordMain = new FileRecordMain();
						fileRecordMain.setFileRecordMainId(Integer.valueOf(arrays[i]));
						fileRecordMain.setRefId(id);
						fileRecordMain.setIsDelete(false);
						fileRecordMain.setCompanyId(null);
						fileRecordMainMapper.update(fileRecordMain);
					}
				}
			}
		}

		// 取得最新值，给web端发微信使用。
		KeFuOrderDto record = keFuOrderMapper.getKeFuOrderById(kefuOrder.getId());
		resultData.setReturnData(record);
		resultData.setSuccess();
        return resultData;
    }
    /**
	  * 根据id查询客服反馈工单详情
	  * @param id
	  * @return
	  * @throws Exception
	  */
   public ResultData<KeFuOrderDto> getKeFuOrderById(Integer id) throws Exception {
      //构建返回
  	  ResultData resultData = new ResultData();
      resultData.setFail();
      KeFuOrderDto keFuOrderDto = keFuOrderMapper.getKeFuOrderById(id);
      if(keFuOrderDto != null){
	       	/**
	       	 *  获取文件信息：客服反馈工单
	       	 */
	       	String fileRecordMainIds = "";
	       	FileRecordMain attachmentFile = new FileRecordMain();
	       	attachmentFile.setRefId(id);
	       	attachmentFile.setIsDelete(false);
	       	//文件
	       	List<ContractFileDto> orderFileList = new ArrayList<ContractFileDto>();
	       	List<FileRecordMain> fileMdlList = fileRecordMainMapper.getKeFuOrderListById(attachmentFile);
	       	fileRecordMainIds = pushFileRecord(fileMdlList, fileRecordMainIds, orderFileList);
	       	if (StringUtil.isNotEmpty(fileRecordMainIds) && fileRecordMainIds.length() > 0){
	       		fileRecordMainIds = fileRecordMainIds.substring(0, fileRecordMainIds.length() - 1);
	       	}
	       	keFuOrderDto.setFileRecordMainIds(fileRecordMainIds);
	       	keFuOrderDto.setOrderFileList(orderFileList);
	       	//返回
//	   		resultData.setReturnData(keFuOrderDto);
//	   		resultData.setSuccess();
	    }

//	   List<KeFuOrderCkDto> list = new ArrayList<>();
       // 工单回复
//       list.addAll(this.getKeFuOrderReplay(id));
//       // 工单审核
//       list.addAll(this.getKefuOrderVerify(id));
	   //返回排序
	   //keFuOrderDto.setKeFuOrderCkDtos(list.stream().sorted( Comparator.comparing(KeFuOrderCkDto::getCkDate).reversed()).collect(Collectors.toList()));

	    List<KeFuOrderCkDto> list = this.getKefuOrderDtlsByOrderId(id);
	    keFuOrderDto.setKeFuOrderCkDtos(list);
  		resultData.setReturnData(keFuOrderDto);
 		resultData.setSuccess();
		return resultData;
  	}


    private List<KeFuOrderCkDto> getKefuOrderDtlsByOrderId(Integer id)throws Exception{

		String fileRecordVerifyMainIds = "";

		Map<String,Object> map = new HashMap<>();
		map.put("orderId", id);
		List<KeFuOrderCkDto> kefuOrderDtlDtos = keFuOrderMapper.getKefuOrderDtls(map);
		if(kefuOrderDtlDtos!=null && kefuOrderDtlDtos.size()>0) {
			for(KeFuOrderCkDto dto :kefuOrderDtlDtos) {

				FileRecordMain file = new FileRecordMain();
				file.setRefId(dto.getId());
				file.setIsDelete(false);
				List<ContractFileDto> orderVerifyFileList = new ArrayList<ContractFileDto>();
				List<FileRecordMain> fileVerifyList = new ArrayList<>();
				// 回复
				if (dto.getType() == 1059){
					fileVerifyList = fileRecordMainMapper.getKeFuOrderReplyListById(file);
				}
				// 核验
				if (dto.getType() == 1060){
					fileVerifyList = fileRecordMainMapper.getKeFuOrderVerifyListById(file);
				}
				fileRecordVerifyMainIds = pushFileRecord(fileVerifyList, fileRecordVerifyMainIds, orderVerifyFileList);
				if (StringUtil.isNotEmpty(fileRecordVerifyMainIds) && fileRecordVerifyMainIds.length() > 0){
					fileRecordVerifyMainIds = fileRecordVerifyMainIds.substring(0, fileRecordVerifyMainIds.length() - 1);
				}
				dto.setOrderCkFileList(orderVerifyFileList);
			}
		}

		return kefuOrderDtlDtos;
	}

    /**
    * 查询经办人列表
    * @param reqMap
    * @return
    */
   public ResultData<List<User>> getOperatorList(Map<String, Object> reqMap) throws Exception {
       ResultData<List<User>> resultData = new ResultData<List<User>>();
       resultData.setFail();
       List<User> moList = keFuOrderMapper.getOperatorList(reqMap);
       if (moList != null && moList.size() > 0) {
           resultData.setReturnData(moList);
           resultData.setTotalCount((String) reqMap.get(QueryConst.TOTAL_COUNT));
           resultData.setSuccess();
       }
       return resultData;
   }
   /**
    * @Description: 根据一级分类类别查询二级分类
    */
   public ResultData<List<CommonCodeDto>> getCategoryTwo(Map<?, ?> param)
       throws Exception{
       //构建返回
       ResultData<List<CommonCodeDto>> resultData = new ResultData<List<CommonCodeDto>>();
       List<CommonCodeDto>  list = this.keFuOrderMapper.getCategoryTwo(param);
       if (null != list && list.size() > 0) {
           resultData.setReturnData(list);
       }
       return resultData;
   }
	/**
     * 图片信息
     * @param
     * @return
     */
    private String pushFileRecord(List<FileRecordMain> fileMdlList, String fileRecordMainIds, List<ContractFileDto> fileList)
        throws Exception {
        if (null != fileMdlList && fileMdlList.size() > 0) {
            for (int i = 0; i < fileMdlList.size(); i++) {
                ContractFileDto contractFileDto = new ContractFileDto();
                FileRecordMain fileRecordMain = fileMdlList.get(i);
                //对文件数据进行组装处理
                handleFileRecordMain(contractFileDto, fileRecordMain);
                //重新组装返回list
                fileList.add(contractFileDto);
                //对营业执照、法人身份证、公盘服务协议不拼接
                fileRecordMainIds = fileRecordMainIds + contractFileDto.getFileRecordMainId() + ",";
            }
        }
        return fileRecordMainIds;
    }
    /**
     * 对文件数据进行组装处理
     * @param contractFileDto
     * @param fileRecordMain
     * @throws Exception
     */
     private void handleFileRecordMain(ContractFileDto contractFileDto, FileRecordMain fileRecordMain)
         throws Exception {
         //获取图片路径
          contractFileDto.setFileAbbrUrl(fileRecordMain.getFileAbbrUrl());
          contractFileDto.setFileName(fileRecordMain.getFileFullName());
          contractFileDto.setFileRecordMainId(fileRecordMain.getFileRecordMainId().toString());
          contractFileDto.setFileUrl(fileRecordMain.getFileUrl());
          contractFileDto.setUrl50(fileRecordMain.getUrl50());
     }

     /**
      * desc:回复
      * 2019年3月20日
      * author:zhenggang.Huang
      */
	public ResultData<?> saveKeFuReply(Map<String, Object> reqMap) throws Exception {
		ResultData<KeFuOrderDto> resultData = new ResultData<>();

		KeFuOrderReply kefuOrderReply = new KeFuOrderReply();
		kefuOrderReply.setReplyDate(new Date());
		kefuOrderReply.setDateUpdate(new Date());
		kefuOrderReply.setDealStatus(Integer.parseInt((String) reqMap.get("dealStatus")));
		kefuOrderReply.setReplyDesc((String)reqMap.get("replyDesc"));
		kefuOrderReply.setOrderId((String)reqMap.get("orderId"));
		kefuOrderReply.setUserCreate((Integer)reqMap.get("userIdCreate"));
		kefuOrderReply.setUserUpdate((Integer)reqMap.get("userIdCreate"));
		int count = keFuOrderReplyMapper.insertSelective(kefuOrderReply);
		if(count <=0) {
			resultData.setFail("工单回复保存回复记录失败");
			return resultData;
		}

		//跟新客服问题状态
		KeFuOrder keFuOrder = new KeFuOrder();
		keFuOrder.setId(Integer.parseInt((String)reqMap.get("orderId")));
		keFuOrder.setDealStatus(Integer.parseInt((String) reqMap.get("dealStatus")));
		keFuOrder.setUserUpdate((Integer)reqMap.get("userIdCreate"));
		keFuOrder.setDateUpdate(new Date());
		// 处理完成
		if(keFuOrder.getDealStatus().intValue() == 24203){
			keFuOrder.setCheckStatus(24301);
		}
		int updateKeFuOrder = keFuOrderMapper.updateByPrimaryKeySelective(keFuOrder);
		if(updateKeFuOrder <=0) {
			resultData.setFail("更新客服问题状态失败");
            return resultData;
		}
		//附件id集
        String fileRecordMainIds = reqMap.get("fileRecordMainIds").toString();
        if (fileRecordMainIds != null && StringUtil.isNotEmpty(fileRecordMainIds)) {
			String[] arrays = fileRecordMainIds.split(",");
			// 关联相关文件(RefId)
			for (int i = 0; i < arrays.length; i++){
				if (StringUtil.isNotEmpty(arrays[i])){
					FileRecordMain fileRecordMain = new FileRecordMain();
					fileRecordMain.setFileRecordMainId(Integer.valueOf(arrays[i]));
					fileRecordMain.setRefId(kefuOrderReply.getId());
					fileRecordMain.setIsDelete(false);
					fileRecordMainMapper.update(fileRecordMain);
				}
			}
		}
		// 取得最新值，给web端发微信使用。
		KeFuOrderDto record = keFuOrderMapper.getKeFuOrderById(Integer.parseInt(kefuOrderReply.getOrderId()));
		resultData.setReturnData(record);
		resultData.setSuccess();
		return resultData;
	}

	public ResultData<?> saveKeFuVerify(Map<String, Object> reqMap) throws Exception{

        ResultData<KeFuOrderDto> resultData = new ResultData<>();

        KefuOrderVerify orderVerify = new KefuOrderVerify();
        orderVerify.setOrderId(Integer.parseInt((String) reqMap.get("orderId")));
        orderVerify.setCheckDate(new Date());
        orderVerify.setCheckDesc((String)reqMap.get("checkDesc"));
        orderVerify.setCheckStatus(Integer.parseInt((String) reqMap.get("checkStatus")));
        orderVerify.setDateCreate(new Date());
		orderVerify.setUserCreate((Integer)reqMap.get("userIdCreate"));
		orderVerify.setUserUpdate((Integer)reqMap.get("userIdCreate"));
        orderVerify.setDateUpdate(new Date());

        int count = kefuOrderVerifyMapper.insertSelective(orderVerify);
        if(count <=0) {
            resultData.setFail("工单核验保存记录失败");
            return resultData;
        }

        KeFuOrder keFuOrder = new KeFuOrder();
        keFuOrder.setId(Integer.parseInt((String)reqMap.get("orderId")));
        keFuOrder.setCheckStatus(Integer.parseInt((String) reqMap.get("checkStatus")));
		keFuOrder.setUserUpdate((Integer)reqMap.get("userIdCreate"));
		keFuOrder.setDateUpdate(new Date());
        // 核验驳回
        if(keFuOrder.getCheckStatus().intValue() == 24303){
            keFuOrder.setDealStatus(24202);
        }else{
            // 核验通过
            keFuOrder.setCheckDate(new Date());
            keFuOrder.setVerifyUserId((Integer)reqMap.get("userIdCreate"));
        }
        int updateKeFuOrder = keFuOrderMapper.updateByPrimaryKeySelective(keFuOrder);
        if(updateKeFuOrder <=0) {
            resultData.setFail("更新客服问题状态失败");
            return resultData;
        }
        //附件id集
        String fileRecordMainIds = reqMap.get("fileRecordMainIds").toString();
        if (fileRecordMainIds != null && StringUtil.isNotEmpty(fileRecordMainIds)) {
            String[] arrays = fileRecordMainIds.split(",");
            // 关联相关文件(RefId)
            for (int i = 0; i < arrays.length; i++){
                if (StringUtil.isNotEmpty(arrays[i])){
                    FileRecordMain fileRecordMain = new FileRecordMain();
                    fileRecordMain.setFileRecordMainId(Integer.valueOf(arrays[i]));
                    fileRecordMain.setRefId(orderVerify.getId());
                    fileRecordMain.setIsDelete(false);
                    fileRecordMainMapper.update(fileRecordMain);
                }
            }
        }

		// 取得最新值，给web端发微信使用。
		KeFuOrderDto record = keFuOrderMapper.getKeFuOrderById(orderVerify.getOrderId());
        resultData.setReturnData(record);
        resultData.setSuccess();
        return resultData;


    }

	public ResultData<WxKefuOrder> getKeFuOrderByIdForWx(Integer id) throws Exception {
		//构建返回
		ResultData<WxKefuOrder> resultData = new ResultData<>();
		WxKefuOrder wxKefuOrder = keFuOrderMapper.getKeFuOrderByIdForWx(id);
		if(wxKefuOrder != null){
			wxKefuOrder.setDealStatusNm(SystemParam.getDicValueByDicCode(wxKefuOrder.getDealStatus().toString()));
			if(wxKefuOrder.getCheckStatus() != null){
				wxKefuOrder.setCheckStatusNm(SystemParam.getDicValueByDicCode(wxKefuOrder.getCheckStatus().toString()));
			}
			List<WxKefuOrderReply> replyList = keFuOrderMapper.getReplyListById(id);
			wxKefuOrder.setReplyList(replyList);
		}
		resultData.setReturnData(wxKefuOrder);
		return resultData;
	}

	public ResultData<?> reply(WxKefuOrderReply reply) {
		ResultData<KeFuOrderDto> resultData = new ResultData<>();
		int i = keFuOrderMapper.insertReply(reply);
		if(i != 1){
			resultData.setFail("回复失败");
			return resultData;
		}else{
			KeFuOrder order = new KeFuOrder();
			order.setId(reply.getOrderId());
			order.setDealStatus(reply.getDealStatus());
            order.setUserCode(reply.getNewUserCode());
			if(reply.getDealStatus().intValue() == 24203){
				order.setCheckStatus(24301);
			}
			keFuOrderMapper.updateByPrimaryKeySelective(order);

			//图片
			if(StringUtil.isNotEmpty(reply.getFileIds())){
				String[] fileIdArr = reply.getFileIds().split(",");
				List ids = new ArrayList();

				for(String fileId : fileIdArr){
					ids.add(fileId);
				}
				Map<String,Object> map = new HashMap<>();
				map.put("contractId",reply.getId());
				map.put("ids",ids);
				fileRecordMainMapper.updateByCondition(map);
			}
		}

		// 取得最新值，给web端发微信使用。
		KeFuOrderDto reback = keFuOrderMapper.getKeFuOrderById(reply.getOrderId());
		resultData.setReturnData(reback);
		resultData.setSuccess();
		return resultData;
	}
	/**
     * 根据storeId查看客服反馈工单列表
     */
    public ResultData<List<KeFuOrderDto>> getKeFuOrderListByStoreId(Integer storeId) throws Exception {
        ResultData<List<KeFuOrderDto>> resultData = new ResultData<List<KeFuOrderDto>>();
//        resultData.setFail();
        List<KeFuOrderDto> keFuOrderDtoList = keFuOrderMapper.getKeFuOrderListByStoreId(storeId);
        if (keFuOrderDtoList != null && keFuOrderDtoList.size() > 0) {
            resultData.setReturnData(keFuOrderDtoList);
            resultData.setTotalCount(keFuOrderDtoList.size()+"");
            resultData.setSuccess();
        }
        return resultData;
    }
}

