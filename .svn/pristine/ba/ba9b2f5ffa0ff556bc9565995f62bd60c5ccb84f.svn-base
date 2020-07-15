package cn.com.eju.deal.contract.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.contract.service.ExtOmsService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.contract.ContractInfoDto;

/**
 * CRM-Service层调用OMS系统专用类
 * 
 * @author zhaoshengying
 * @date 2016年9月1日 下午5:44:23
 */
@RestController
@RequestMapping(value = "extOms")
public class ExtOmsController extends BaseController {
	/**
	 * 日志
	 */
	private final LogHelper logger = LogHelper.getLogger(this.getClass());

	@Resource(name = "extOmsService")
	private ExtOmsService extOmsService;

	/**
	 * 
	    * @Title: createPerformNodeRecordByFlowId
	    * @Description: 新增合同流水
	    * @return
	    * @throws Exception
	 */
	@RequestMapping(value = "/{flowId}", method = RequestMethod.GET)
	public String createPerformNodeRecordByFlowId(@PathVariable String flowId)
			throws Exception {
		// 构建返回
		ResultData<ContractInfoDto> resultData = new ResultData<ContractInfoDto>();
		try {
			extOmsService.createPerformNodeRecordByFlowId(flowId);
		} catch (Exception e) {
			resultData.setFail();
			logger.error("extOmsController", "ExtOmsController",
					"createPerformNodeRecordByFlowId", "", -1, "", "新增合同流水失败",
					e);
		}
		return resultData.toString();
	}
	
	/**
     * 变更终止通过-更新OMS保证金中合同状态
     * @param param
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/updateChgState/{param}", method = RequestMethod.GET)
    public String updateChgStatusToOmsSplit(@PathVariable String param) {
        // 构建返回
        ResultData<String> resultData = new ResultData<String>();
        Map<String, Object> queryParam = JsonUtil.parseToObject(param,
                Map.class);
        List<String> passList = (List<String>) queryParam.remove("pass");
        try {
            // 审核通过
            extOmsService.updateChgStatusToOmsSplit(passList);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("extOmsController", "ExtOmsController",
                    "updateChgStatusToOmsSplit", "", -1, "", "变更终止通过-更新OMS保证金中合同状态失败",
                    e);
        }
        return resultData.toString();
    }

}
