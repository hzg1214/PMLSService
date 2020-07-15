package cn.com.eju.deal.cashbill.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.cashbill.model.CashBillCompany;
import cn.com.eju.deal.cashbill.model.CashBillProject;
import cn.com.eju.deal.cashbill.service.CashBillService;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.pmls.jsStatement.dto.PmlsJsStatementDtlDto;
import cn.com.eju.pmls.jsStatement.dto.PmlsJsStatementDto;
import cn.com.eju.pmls.jsStatement.model.PmlsJsStatementDtl;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by yinkun on 2018/8/13.
 */
@RestController
@RequestMapping("cashBill")
public class CashBillController extends BaseController{

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private CashBillService cashBillService;

    @RequestMapping(value = "/selectCashBill",method = RequestMethod.POST)
    public String selectCashBill(@RequestBody String param){

        ResultData<List<CashBillProject>> resultData = new ResultData<>();

        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        List<CashBillProject> list = cashBillService.selectCashBill(queryParam);
        if(CollectionUtils.isNotEmpty(list)){
            resultData.setReturnData(list);
            resultData.setTotalCount(list.size()+"");
        }else {
            resultData.setTotalCount("0");
        }

        return resultData.toString();
    }

    @RequestMapping(value = "/addToBatchCash",method = RequestMethod.POST)
    public String addToBatchCash(@RequestBody String param){

        ResultData<String> resultData;

        Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
        resultData = cashBillService.addToBatchCash(map);

        return resultData.toString();
    }

    @RequestMapping(value = "/selectCashBillForPop",method = RequestMethod.POST)
    public String selectCashBillForPop(@RequestBody String param){

        ResultData<CashBillProject> resultData = null;

        Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = cashBillService.selectCashBillForPop(map);
        } catch (Exception e) {
            resultData.setFail("批量请款页面加载异常");
            logger.error("cashBill", "CashBillController", "selectCashBillForPop", param, null, "", "批量请款页面加载异常", e);
        }

        return resultData.toString();
    }

    @RequestMapping(value = "/removeFromCashBill",method = RequestMethod.POST)
    public String removeFromCashBill(@RequestBody String param){

        ResultData<String> resultData;

        Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
        resultData = cashBillService.removeFromCashBill(map);

        return resultData.toString();
    }

    @RequestMapping(value = "/saveCashBill",method = RequestMethod.POST)
    public String saveCashBill(@RequestBody String param){

        ResultData<String> resultData = new ResultData<>();

        try{
            CashBillProject project = JsonUtil.parseToObject(param, CashBillProject.class);
            resultData = cashBillService.saveCashBill(project);
        }catch (Exception e){
            resultData.setFail("批量请款异常");
            logger.error("cashBill", "CashBillController", "saveCashBill", param, null, "", "保存或提交批量请款失败", e);
        }

        return resultData.toString();
    }
    /**
     * 查询请款单列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getCashBillList/{param}", method = RequestMethod.GET)
    public String getCashBillList(@PathVariable String param){
        ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = cashBillService.getCashBillList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "CashBillController", "getCashBillList", reqMap.toString(), null, "", "查询请款单列表list异常", e);
        }
        return resultData.toString();
    }
    /**
     * 查询请款单详情
     * @param param
     * @return
     */
    @RequestMapping(value = "/getCashBillDeatilById/{param}", method = RequestMethod.GET)
    public String getCashBillDeatilById(@PathVariable String param){
        //构建返回
        ResultData resultData = null;
        Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
        try{
            resultData = this.cashBillService.getCashBillDeatilById(map);
        }catch (Exception e){
        	if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "CashBillController", "getCashBillDeatilById", "", null, "", "", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/listToSubmitOa",method = RequestMethod.POST)
    public String listToSubmitOa(@RequestBody String param){

        ResultData<String> resultData = new ResultData<>();

        try{
            Map map = JsonUtil.parseToObject(param, Map.class);
            resultData = cashBillService.listToSubmitOa(map);
        }catch (Exception e){
            resultData.setFail("批量请款提交异常");
            logger.error("cashBill", "CashBillController", "listToSubmitOa", param, null, "", "批量请款提交失败", e);
        }

        return resultData.toString();
    }
    /**
     * 获取联动核算主体
     * @param cityNo
     * @return
     */
    @RequestMapping(value = "/getLnkAccountProjectList/{cityNo}", method = RequestMethod.GET)
    public String getLnkAccountProjectList(@PathVariable String cityNo) {
    	 //构建返回
        ResultData<List<Map<String,Object>>> resultData = new ResultData<List<Map<String,Object>>>();
    	try {
    		List<Map<String,Object>> projectList = cashBillService.getLnkAccountProjectList(cityNo);
			resultData.setReturnData(projectList);
		} catch (Exception e) {
			logger.error("cashBill", "CashBillController", "getLnkAccountProjectList", "", 0, "", "获取联动核算主体 ", e);
	        resultData.setFail();
		}
        return resultData.toString();
    }


    @RequestMapping(value = "/getOALnkAccountProjectList/{projectNo}", method = RequestMethod.GET)
    public String getOALnkAccountProjectList(@PathVariable String projectNo) {
        //构建返回
        ResultData<List<Map<String,Object>>> resultData = new ResultData<List<Map<String,Object>>>();
        try {
            List<Map<String,Object>> projectList = cashBillService.getOALnkAccountProjectList(projectNo);
            resultData.setReturnData(projectList);
        } catch (Exception e) {
            logger.error("cashBill", "CashBillController", "getOALnkAccountProjectList",
                    projectNo, 0, "", "获取OA联动核算主体 ", e);
            resultData.setFail();
        }
        return resultData.toString();
    }


    @RequestMapping(value = "/getOAFrmAgreementList/{param}", method = RequestMethod.GET)
    public String getOAFrmAgreementList(@PathVariable String param) {
        //构建返回
        ResultData<List<Map<String,Object>>> resultData = new ResultData<List<Map<String,Object>>>();
        try {
            Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
            List<Map<String,Object>> projectList = cashBillService.getOAFrmAgreementList(reqMap);
            resultData.setTotalCount((String) reqMap.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(projectList);
        } catch (Exception e) {
            logger.error("cashBill", "CashBillController", "getOAFrmAgreementList",
                    param, 0, "", "获取OA联动框架协议 ", e);
            resultData.setFail();
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getOAReceiveBankList/{param}", method = RequestMethod.GET)
    public String getOAReceiveBankList(@PathVariable String param) {
        //构建返回
        ResultData<List<Map<String,Object>>> resultData = new ResultData<List<Map<String,Object>>>();
        try {
            Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
            List<Map<String,Object>> projectList = cashBillService.getOAReceiveBankList(reqMap);
            resultData.setTotalCount((String) reqMap.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(projectList);
        } catch (Exception e) {
            logger.error("cashBill", "CashBillController", "getOAReceiveBankList",
                    param , 0, "", "获取OA联动银行信息 ", e);
            resultData.setFail();
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/getOffsetInfoList/{param}", method = RequestMethod.GET)
    public String getOffsetInfoList(@PathVariable String param) {
        //构建返回
        ResultData<List<Map<String,Object>>> resultData = new ResultData<List<Map<String,Object>>>();
        try {
            Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
            List<Map<String,Object>> offsetList = cashBillService.getOffsetInfoList(reqMap);
            resultData.setTotalCount((String) reqMap.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(offsetList);
        } catch (Exception e) {
            logger.error("cashBill", "CashBillController", "getOffsetInfoList",
                    param , 0, "", "获取冲抵房源信息 ", e);
            resultData.setFail();
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getOACheckBodyList/{param}", method = RequestMethod.GET)
    public String getOACheckBodyList(@PathVariable String param) {
        //构建返回
        ResultData<List<Map<String,Object>>> resultData = new ResultData<List<Map<String,Object>>>();
        try {
            Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
            List<Map<String,Object>> list = cashBillService.getOACheckBodyList(reqMap);
            resultData.setReturnData(list);
        } catch (Exception e) {
            logger.error("cashBill", "CashBillController", "getOACheckBodyList",
                    param , 0, "", "获取oa考核主体 ", e);
            resultData.setFail();
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/saveOACashBill",method = RequestMethod.POST)
    public String saveOACashBill(@RequestBody String param){

        ResultData<String> resultData = new ResultData<>();

        try{
            CashBillProject project = JsonUtil.parseToObject(param, CashBillProject.class);
            resultData = cashBillService.saveOACashBill(project);
        }catch (Exception e){
            resultData.setFail("批量请款异常");
            logger.error("cashBill", "CashBillController", "saveOACashBill", param, null, "", "保存或提交批量请款失败", e);
        }

        return resultData.toString();
    }
    
    /**
     * 问卷删除
     * @param param
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public String remove(@RequestBody String param) {
        ResultData resultData = new ResultData<>();
        int count = 0;
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            count = cashBillService.remove(queryParam);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("cashBill", "CashBillController", "remove", "",
                    null, "", "", e);
        }
        if (count <= 0) {
            resultData.setFail();
        }
        return resultData.toString();
    }


    /**
     * new
     * 查询请款单详情
     * @param param
     * @return
     */
    @RequestMapping(value = "/getCashBillDeatilByIdNew/{param}", method = RequestMethod.GET)
    public String getCashBillDeatilByIdNew(@PathVariable String param){
        //构建返回
        ResultData resultData = null;
        Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
        try{
            resultData = this.cashBillService.getCashBillDeatilByIdNew(map);
        }catch (Exception e){
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("查询请款单信息失败##param="+param,e);
            logger.error("PMLS", "CashBillController", "getCashBillDeatilByIdNew", "", null, "", "", e);
        }
        return resultData.toString();
    }

    //new
    @RequestMapping(value = "/selectJsStatementDtlListCanQk/{param}", method = RequestMethod.GET)
    public String selectJsStatementDtlListCanQk(@PathVariable String param) {
        ResultData<List<PmlsJsStatementDtlDto>> resultData = new ResultData<List<PmlsJsStatementDtlDto>>();
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = cashBillService.selectJsStatementDtlListCanQk(queryParam);
        } catch (Exception e) {
            logger.error("查询结算单请款订单明细信息失败##param="+param,e);
            logger.error("cashBill.selectJsStatementDtlListCanQk 发生异常 param:" + param, e);
            resultData.setFail();
        }
        return resultData.toString();
    }
    //new
    @RequestMapping(value = "/saveOACashBillNew",method = RequestMethod.POST)
    public String saveOACashBillNew(@RequestBody CashBillCompany cashBillCompany){

        ResultData<String> resultData = new ResultData<>();

        try{
            resultData = cashBillService.saveOACashBillNew(cashBillCompany);
        }catch (Exception e){
            resultData.setFail("操作失败");
            logger.error("请款单保存或提交失败##cashBillCompany="+JSON.toJSONString(cashBillCompany),e);
            logger.error("cashBill", "CashBillController", "saveOACashBill", JSON.toJSONString(cashBillCompany), null, "", "保存或提交请款失败", e);
        }
        return resultData.toString();
    }

    /**
     * 作废 new
     * @param param
     * @return
     */
    @RequestMapping(value = "/invalidBill", method = RequestMethod.POST)
    public String invalidBill(@RequestBody String param) {
        ResultData resultData = new ResultData<>();
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = cashBillService.invalidBill(queryParam);
        } catch (Exception e) {
            resultData.setFail("请款单作废失败");
            logger.error("请款单作废失败##param="+param,e);
            logger.error("cashBill", "CashBillController", "invalidBill", "",
                    null, "", "", e);
        }
        return resultData.toString();
    }
}
