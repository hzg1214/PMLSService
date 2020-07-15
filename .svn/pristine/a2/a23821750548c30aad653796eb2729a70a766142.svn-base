package cn.com.eju.deal.gpCsMemberContract.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.gpCsMemberContract.model.GpCsMemberContractDto;
import cn.com.eju.deal.gpCsMemberContract.service.GpCsMemberContractService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("gpCsMemberContract")
public class GpCsMemberContractController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private GpCsMemberContractService  gpCsMemberContractService;
    
    /**
     * 查询公盘合同终止列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getGpCsMemberContractList/{param}", method = RequestMethod.GET)
    public String getGpCsMemberContractList(@PathVariable String param){
        ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = gpCsMemberContractService.getGpCsMemberContractList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "GpCsMemberContractController", "getGpCsMemberContractList", reqMap.toString(), null, "", "查询公盘初始化会员列表异常", e);
        }
        return resultData.toString();
    }
    /**
     * 根据id查询公盘初始会员合同详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/getGpCsMemberContractById/{id}", method = RequestMethod.GET)
    public String getGpCsMemberContractById(@PathVariable Integer id){
        //构建返回
        ResultData resultData = null;
        try{
            resultData = this.gpCsMemberContractService.getGpCsMemberContractById(id);
        }catch (Exception e){
        	if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("gpCsMemberContract", "GpCsMemberContractController", "getGpCsMemberContractById", "", null, "", "", e);
        }
        return resultData.toString();
    }
	/**
     * 保存或更新公盘初始会员合同
     */
	@RequestMapping(value = "/saveGpCsMemberContract",method = RequestMethod.POST)
    public ResultData<?> savegpCsMemberContract(@RequestBody String param){
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try{
            resultData = gpCsMemberContractService.saveGpCsMemberContract(reqMap);
        }catch (Exception e){
            resultData.setFail("保存公盘初始会员合同失败！");
            logger.error("gpCsMemberContract", "GpCsMemberContractController",
                    "saveGpCsMemberContract", param, -1, "", "保存公盘初始会员合同失败！", e);
        }
        return resultData;
    }
	
	
    /**
     * 公盘初始会员提交OA审核
     * @param reqMap
     */
	@RequestMapping(value = "/submitGpCsMemberContractOa",method = RequestMethod.POST)
    public String submitGpCsMemberContractOa(@RequestBody String param){

        ResultData<String> resultData = new ResultData<>();
        try{
            Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
            resultData = gpCsMemberContractService.submitGpCsMemberContractOa(map);
        }catch (Exception e){
			if(resultData == null){
					resultData = new ResultData();
			}
            resultData.setFail("公盘初始会员提交OA异常");
            logger.error("gpCsMemberContract", "GpCsMemberContractController", "gpContractStopSubmitOa", param, null, "", "公盘合同终止提交提交失败", e);
        }

        return resultData.toString();
    }
	/**
     * @Description: 作废
     */
    @RequestMapping(value = "/updateStatus", method = RequestMethod.PUT)
    public String update(@RequestBody String param){
        //构建返回
    	ResultData resultData = null;
        try{
            resultData = this.gpCsMemberContractService.updateStatus(param);
        }
        catch (Exception e){
        	if(resultData == null){
        		resultData = new ResultData();
        	}
            resultData.setFail();
            logger.error("gpCsMemberContract", "GpCsMemberContractController", "updateStatus", "", null, "", "", e);
        }
        return resultData.toString();
    }
    /**
     * 查询关联公盘列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getGpInfoByCompanyId/{param}", method = RequestMethod.GET)
    public String getGpInfoByCompanyId(@PathVariable String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        //构建返回
        ResultData resultData =new ResultData();
        try{
            resultData = this.gpCsMemberContractService.getGpInfoByCompanyId(queryParam);
        }
        catch (Exception e){
            resultData.setFail();
            logger.error("gpCsMemberContract", "GpCsMemberContractController", "getGpInfoByCompanyId", "", null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * 运营变更合同状态并补记录
     * @param
     * @return
     */
    @RequestMapping(value = "operateChangeCt/{id}", method = RequestMethod.GET)
    public String operateChangeCt(@PathVariable Integer id) {
        //构建返回
        ResultData<GpCsMemberContractDto> resultData = new ResultData<GpCsMemberContractDto>();
        int count = 0;
        try {
            count = gpCsMemberContractService.operateChangeCt(id);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("gpCsMemberContract", "GpCsMemberContractController", "operateChangeCt", "", 0, "", "状态变更失败", e);
        }
        if (count <= 0) {
            resultData.setFail();
        }
        return resultData.toString();
    }
	
}
