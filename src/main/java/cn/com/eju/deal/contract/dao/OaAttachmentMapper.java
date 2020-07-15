package cn.com.eju.deal.contract.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.base.file.model.OaAttachment;
import cn.com.eju.deal.core.dao.IDao;

/**   
* 合同变更上传附件关联信息Dao层
* @author sunmm
* @date 2016年8月10日 下午1:46:57
*/
public interface OaAttachmentMapper extends IDao<OaAttachment> {

	 /**
	 * 新增--合同变更上传附件关联信息
	 * @param obj 实体对象
	 * @return 影响记录条数
	 */
    int create(OaAttachment obj);

	 /**
	 * 更新-合同变更上传附件关联信息
	 * @param obj 实体对象
	 * @return 影响记录条数
	 */
	 int update(OaAttachment obj);
	
	/** 
	* 根据contractStopId和fileTypeCode查询附件关联信息
	* @param params 合同变更ID、文件类型
	* @return
	* @throws Exception
	*/
	List<OaAttachment> getOaAttachment(Map<String,Object> params) throws Exception;
	
	/**
	 * 批量删除图片数据
	 * @param params
	 * @return
	 */
	int updateMore(Map<String,Object> params);
	
}