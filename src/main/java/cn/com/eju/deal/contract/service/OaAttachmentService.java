package cn.com.eju.deal.contract.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.file.model.OaAttachment;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.contract.dao.OaAttachmentMapper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.contract.ContractChangeDto;
import cn.com.eju.deal.dto.oa.OaAttachmentDto;

/**   
* 合同变更上传附件关联信息Service层
* @author sunmm
* @date 2016年8月10日 下午2:26:05
*/
@Service("oaAttachmentService")
public class OaAttachmentService extends BaseService<OaAttachment> {

	@Resource
	private OaAttachmentMapper oaAttachmentMapper;
	/**
	 * 创建图片数据
	 */
    public Integer createOAPicIdRecord(OaAttachment oaAttachment)
        throws Exception
    {
        ResultData<ContractChangeDto> resultData = new ResultData<ContractChangeDto>();
        oaAttachmentMapper.create(oaAttachment);
        ContractChangeDto contractChangeDto = new ContractChangeDto();
        Integer picId = oaAttachment.getId();
        return picId;
    }

    /**
     * 更新图片数据
     */
    public void updateOAPicByPicId(OaAttachment oaAttachment)
        throws Exception
    {
        oaAttachmentMapper.update(oaAttachment);
    }
	
	/**
	 * 根据contractStopId和fileTypeCode查询附件关联信息
	 * 
	 * @param contractStopId
	 *            合同变更ID
	 * @param fileTypeCode 
	 * 			  文件类型
	 * @return
	 * @throws Exception
	 */
	public ResultData<List<OaAttachmentDto>> getOaAttachment(
			int contractStopId, String fileTypeCode) throws Exception {
		// 构建返回
		ResultData<List<OaAttachmentDto>> resultData = new ResultData<List<OaAttachmentDto>>();
		List<OaAttachmentDto> oaAttachmentDtoList = new ArrayList<OaAttachmentDto>();
		
		Map<String, Object> param = new HashMap<>();
		param.put("contractStopId", contractStopId);
		param.put("fileTypeCode", fileTypeCode);
		// 查询操作
		List<OaAttachment> oaAttachmentList = oaAttachmentMapper.getOaAttachment(param);
		for (OaAttachment oaAttachment : oaAttachmentList) {
			OaAttachmentDto ccDto = new OaAttachmentDto();
			BeanUtils.copyProperties(oaAttachment, ccDto);
			oaAttachmentDtoList.add(ccDto);
		}
		resultData.setReturnData(oaAttachmentDtoList);
		return resultData;
	}
}
