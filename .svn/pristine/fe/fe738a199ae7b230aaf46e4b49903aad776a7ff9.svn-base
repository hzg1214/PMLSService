/**
 * @Title: StoreController.java
 * @Package cn.com.eju.deal.store.controller
 * @Description: 门店Controller包
 * @author 陆海丹
 * @date 2016年3月24日 上午11:02:27
 * @version V1.0
 */
package cn.com.eju.deal.store.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.store.model.Store;
import cn.com.eju.deal.store.model.StoreLog;
import cn.com.eju.deal.store.service.StoreService;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 陆海丹
 * @ClassName: StoreController
 * @Description: 门店Controller
 * @date 2016年3月24日 上午11:02:27
 */
@RestController
@RequestMapping(value = "store")
public class StoreController extends BaseController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "storeService")
    private StoreService storeService;

    @RequestMapping(value = "/brief/{id}/{userId}", method = RequestMethod.GET)
    public String getBriefById(@PathVariable int id, @PathVariable Integer userId) {
        //构建返回
        ResultData<StoreDto> resultData = new ResultData<StoreDto>();
        try {
            resultData = this.storeService.getBriefById(id, userId);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "getBriefById", id + "", null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * @param id
     * @return
     * @Title: getById
     * @Description: 根据门店编号查询门店详情
     */
    @RequestMapping(value = "/{id}/{userId}", method = RequestMethod.GET)
    public String getById(@PathVariable int id, @PathVariable Integer userId) {
        //构建返回
        ResultData<StoreDto> resultData = new ResultData<StoreDto>();
        try {
            resultData = this.storeService.getStrById(id, userId);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "getById", "", null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * @param id
     * @returnStoreDto
     * @Title: getReceivedMoneyByStoreId
     * @Description: 根据门店ID查询收款
     */
    @RequestMapping(value = "/receive/{storeId}", method = RequestMethod.GET)
    public String getReceivedMoneyByStoreId(@PathVariable Integer storeId) {
        //构建返回
        ResultData<List<StoreDto>> resultData = new ResultData<>();
        try {
            resultData = this.storeService.getReceivedMoneyByStoreId(storeId);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "ConcernedStoreController", "getReceivedMoneyByStoreId", "", null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * @param param
     * @return
     * @Title: list
     * @Description: 根据条件查询门店列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/q/{param}", method = RequestMethod.GET)
    public String list(@PathVariable String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        //Add By DengLin 2017/04/07 Start
        if (queryParam.containsKey("searchKey"))
            queryParam.put("searchKey", queryParam.get("searchKey").toString().trim());
        //Add By DengLin 2017/04/07 End
        //权限控制,参数转换
        //authParam(queryParam);
        //构建返回
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        try {
            List<String> districtsNo = (List<String>) queryParam.remove("districtNoList");
            if (null != districtsNo && !districtsNo.isEmpty()) {
                String[] arr = (String[]) districtsNo.toArray(new String[districtsNo.size()]);
                queryParam.put("districtNoList", arr);
            }
            if (queryParam.containsKey("cityNo") && queryParam.get("cityNo").toString() != "") {
                String acCityNoSQL = " AND x1.AcCityNo = '" + queryParam.get("cityNo").toString() + "'";
                queryParam.put("acCityNoSQL", acCityNoSQL);
            }

            //resultData = this.storeService.queryStrList(queryParam);
            resultData = this.storeService.queryListNew(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "ConcernedStoreController", "list", "", null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * @param param
     * @return
     * @Title: list
     * @Description: 根据公司ID查询关联门店列表
     */
    @RequestMapping(value = "/qr/{param}", method = RequestMethod.GET)
    public String queryRelatelist(@PathVariable String param) {
        @SuppressWarnings("unchecked")
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        //权限控制,参数转换
        //        authParam(queryParam);

        //构建返回
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();

        if (!queryParam.containsKey("companyId") || queryParam.get("companyId") == null) {
            logger.error("store", "StoreController", "queryRelatelist", queryParam.toString(), null, "", "", null);
            return resultData.toString();
        }
        try {
            resultData = this.storeService.queryRelatelistNew(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "queryRelatelist", "", null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * @param param
     * @return
     * @Title: querylistByCompanyId
     * @Description: 通过公司ID查找关联门店列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/qCompanyId/{param}", method = RequestMethod.GET)
    public String querylistByCompanyId(@PathVariable String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        try {
            List<String> districtsNo = (List<String>) queryParam.remove("districtNoList");
            if (null != districtsNo && !districtsNo.isEmpty()) {
                String[] arr = (String[]) districtsNo.toArray(new String[districtsNo.size()]);
                queryParam.put("districtNoList", arr);
            }
            resultData = this.storeService.querylistByCompanyId(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "ConcernedStoreController", "querylistByCompanyId", "", null, "", "", e);
        }
        return resultData.toString();
    }


    /**
     * @param param
     * @return
     * @Title: queryDisList
     * @Description: 查距离最近的30加门店的列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/qd/{param}/", method = RequestMethod.GET)
    public String queryDisList(@PathVariable String param) {

        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);


        //经度组装
        queryParam.put("latitude", queryParam.remove("prLatitude") + "." + queryParam.remove("suLatitude"));

        //纬度组装
        queryParam.put("longitude", queryParam.remove("prLongitude") + "." + queryParam.remove("suLongitude"));


        //构建返回
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        try {
            List<String> districtsNo = (List<String>) queryParam.remove("districtNoList");
            if (null != districtsNo && !districtsNo.isEmpty()) {
                String[] arr = (String[]) districtsNo.toArray(new String[districtsNo.size()]);
                queryParam.put("districtNoList", arr);
            }

            resultData = this.storeService.queryDisList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "queryDisList", "", null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * @param jsonDto
     * @return
     * @Title: create
     * @Description: 新增门店
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String create(@RequestBody String jsonDto) {
        StoreDto storeDto = JsonUtil.parseToObject(jsonDto, StoreDto.class);
        //构建返回
        ResultData<StoreDto> resultData = new ResultData<StoreDto>();
        try {
            //Add 2017/4/7 cning start------->
            storeDto.setRenewFlag(DictionaryConstants.RENEWFLAG_TYPE_OK);
            storeDto.setNeededRenew(DictionaryConstants.NEEDEDRENEW_TYPE_YXQ);
            //Add 2017/4/7 cning end<-------
            resultData = this.storeService.createStr(storeDto);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "create", "", null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * @param jsonDto
     * @return
     * @Title: update
     * @Description: 更新门店信息
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(@RequestBody String jsonDto) {
//        StoreDto storeDto = JsonUtil.parseToObject(jsonDto, StoreDto.class);
        StoreDto storeDto = JSON.parseObject(jsonDto, StoreDto.class);
        //构建返回
        ResultData<StoreDto> resultData = new ResultData<StoreDto>();
        try {
            resultData = this.storeService.updateStr(storeDto);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "update", "", null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * @param id
     * @return
     * @Title: delete
     * @Description: 删除门店
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable int id) {
        //构建返回
        ResultData<StoreDto> resultData = new ResultData<StoreDto>();
        try {
            resultData = this.storeService.deleteStr(id);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "delete", "", null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * 根据维护人的员工编号查询门店信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "qMaintainer/{param}", method = RequestMethod.GET)
    public String getByMaintainer(@PathVariable String param) {
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

            //构建返回
            resultData = this.storeService.getByMaintainer(queryParam);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "getByMaintainer", param, 0, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * 更新维护人到门店表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "uMtcToStore", method = RequestMethod.PUT)
    public String updateMtcToStore(@RequestBody String jsonDto) throws Exception {
        ResultData<?> resultData = new ResultData<>();
        try {
            StoreDto storeDto = JsonUtil.parseToObject(jsonDto, StoreDto.class);
            //构建返回
            resultData = this.storeService.updateMtcToStore(storeDto);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "updateMtcToStore", "", 0, "", "更新维护人到门店失败", e);
        }
        return resultData.toString();
    }

    /**
     * @param storeId
     * @return
     * @Title: getEditBanFlag
     * @Description: 获取是否编辑限制状态
     */
    @RequestMapping(value = "getEditBanFlag/{storeId}", method = RequestMethod.GET)
    public String getEditBanFlag(@PathVariable Integer storeId) throws Exception {
        ResultData<?> resultData = new ResultData<>();
        try {

            resultData = this.storeService.getEditBanFlag(storeId);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "getEditBanFlag", "", 0, "", "获取是否编辑限制状态失败", e);
        }
        return resultData.toString();
    }


    //Add By NingChao 2017/04/07 Start

    /**
     * 续签，关联门店列表
     *
     * @param param
     * @return
     * @Title: list
     * @Description: 根据公司ID查询关联门店列表
     */
    @RequestMapping(value = "/qrRenew/{param}", method = RequestMethod.GET)
    public String queryRelatelistByRenew(@PathVariable String param) {
        @SuppressWarnings("unchecked")
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        //构建返回
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        try {
            resultData = this.storeService.queryRelatelistByRenew(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "queryRelatelist", "", null, "", "", e);
        }
        return resultData.toString();
    }
    //Add By NingChao 2017/04/07 End

    //Add By WangLei 2017/04/07 Start
    @RequestMapping(value = "renew/{storeId}/{neededrenew}", method = RequestMethod.GET)
    public String updateNeededRenew(@PathVariable Integer storeId, @PathVariable Integer neededrenew) throws Exception {
        ResultData<?> resultData = new ResultData<>();
        try {
            //构建返回
            resultData = this.storeService.updateNeededRenewFlag(storeId, neededrenew);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "updateMtcToStore", "", 0, "", "更新维护人到门店失败", e);
        }
        return resultData.toString();
    }
    //Add By WangLei 2017/04/07 End

    /**
     * @param userCode 当前登陆人code
     * @return 待续签门店列表
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/qrenew/{param}", method = RequestMethod.GET)
    public String getRenewStoreList(@PathVariable String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        // 构建返回
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        try {
            resultData = storeService.getRenewStoreList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "ConcernedStoreController", "list", "", null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * 分配维护人时 check选择的维护人是否是当前维护人
     *
     * @param storeId,maitainer
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/checkmtner/{param}", method = RequestMethod.GET)
    public ResultData<Boolean> checkMtner(@PathVariable String param) {
        ResultData<Boolean> resultData = new ResultData<Boolean>();
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        try {
            boolean exist = storeService.checkMtner(queryParam);
            resultData.setReturnData(exist);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "checkMtner", "", null, "", "", e);
        }
        return resultData;
    }

    /**
     * @param id
     * @return
     * @Title: getById
     * @Description: 根据门店编号查询门店详情
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getStoreById(@PathVariable int id) {
        //构建返回
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        try {
            resultData = this.storeService.getStoreById(id);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "ConcernedStoreController", "getStoreById", "", null, "", "", e);
        }
        return resultData.toString();
    }


    /**
     * 修改OMS装修记录的门店地址
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/updateOmsAdress/{param}", method = RequestMethod.GET)
    public String UpdateOmsStoreAdress(@PathVariable String param) {
        Map<String, Object> qparam = JsonUtil.parseToObject(param, Map.class);
        //构建返回
        ResultData<StoreDto> resultData = new ResultData<StoreDto>();
        try {
            Integer rtn = this.storeService.UpdateOmsStoreAdress(qparam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "UpdateOmsStoreAdress", "", null, "", "", e);
        }
        return resultData.toString();
    }

    //Add By cning 2017/07/10 Start

    /**
     * 获取门店审核状态
     *
     * @param URL
     * @param fileName
     * @param file
     * @return
     */
    @RequestMapping(value = "/status/{storeId}", method = RequestMethod.GET)
    public String getAuditStatus(@PathVariable Integer storeId) throws Exception {
        //构建返回
        ResultData<Integer> resultData = new ResultData<>();
        try {
            Integer rtn = this.storeService.getAuditStatusById(storeId);
            resultData.setReturnData(rtn);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "getAuditStatus", "", null, "", "", e);
        }
        return resultData.toString();
    }
    //Add By cning 2017/07/10 End

    /**
     * 查询门店修改日志List
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/findStoreLogList/{param}", method = RequestMethod.GET)
    public String FindStoreLogList(@PathVariable String param) {
        Map<String, Object> qparam = JsonUtil.parseToObject(param, Map.class);
        //构建返回
        ResultData<List<StoreLog>> resultData = new ResultData<List<StoreLog>>();
        try {
            resultData = storeService.queryStoreLogList(qparam);
        } catch (Exception e) {
            logger.error("查询门店修改日志列表异常", e);
            resultData.setFail();
            logger.error("store", "StoreController", "SelectStoreLogList", "", null, "", "", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/findStoreLogById/{param}", method = RequestMethod.GET)
    public String FindStoreLogById(@PathVariable String param) {
        Map<String, Object> qparam = JsonUtil.parseToObject(param, Map.class);
        //构建返回
        ResultData<StoreLog> resultData  = new ResultData<StoreLog>();
        try {
            resultData = storeService.queryStoreLog(qparam);
        } catch (Exception e) {
            logger.error("查询门店修改日志详情异常", e);
            resultData.setFail();
            logger.error("store", "StoreController", "SelectStoreLogList", "", null, "", "", e);
        }
        return resultData.toString();
    }


// Add By QJP 2017/07/13  Start

    /**
     * @param jsonDto
     * @return
     * @Title: update
     * @Description: 更新门店店招, 地址, 同时插入一条更新记录信息
     */
    @RequestMapping(value = "/updateS", method = RequestMethod.PUT)
    public String updateS(@RequestBody String jsonDto) {
        //StoreDto storeDto = JsonUtil.parseToObject(jsonDto, StoreDto.class);
        StoreDto storeDto = JSON.parseObject(jsonDto, StoreDto.class);
        //构建返回
        ResultData<StoreDto> resultData = new ResultData<StoreDto>();
        try {
            resultData = this.storeService.updateDetail(storeDto);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "updateS", "", null, "", "", e);
        }
        return resultData.toString();
    }
// Add By QJP 2017/07/13  End  

    @RequestMapping(value = "/check/{param}", method = RequestMethod.GET)
    public ResultData<?> checkStoreAddress(@PathVariable String param) {
        Map<String, Object> qparam = JsonUtil.parseToObject(param, Map.class);
        ResultData<Integer> resultData = new ResultData<Integer>();
        Integer count = storeService.checkAddress(qparam);
        resultData.setReturnData(count);
        if (count > 0) {
            resultData.setFail();
            resultData.setReturnCode(ReturnCode.FAILURE);
        } else {
            resultData.setSuccess();
            resultData.setReturnCode(ReturnCode.SUCCESS);
        }
        return resultData;
    }

    /**
     * @param param
     * @return
     * @Title: querylistByCompanyId
     * @Description: 通过公司ID查找未关联门店列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/companyStore/{param}", method = RequestMethod.GET)
    public String queryCompanyStore(@PathVariable String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        try {
            resultData = this.storeService.queryCompanyStore(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "queryCompanyStore", "", null, "", "", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/bToAAlert/{id}", method = RequestMethod.GET)
    public String bToAAlert(@PathVariable Integer id) {
        ResultData<String> resultData = new ResultData<>();
        try {
            Integer result = this.storeService.checkBToAAlert(id);
            if (result != null && result.equals(id)) {
                resultData.setReturnData("1");
            } else {
                resultData.setFail();
                resultData.setReturnData("0");
            }
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "bToAAlert", "", null, "", "检查是否乙转甲提醒失败", e);
        }
        return resultData.toString();
    }

    @RequestMapping("/queryServiceFrameContract/{storeId}")
    public String queryServiceFrameContract(@PathVariable Integer storeId) {
        ResultData<List> resultData = new ResultData<>();

        try {
            resultData = this.storeService.queryServiceFrameContract(storeId);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "queryServiceFrameContract", storeId.toString(), null, "", "查询门店服务框架合同异常", e);
        }

        return resultData.toString();
    }

    /**
     * 判断门店资质等级是否为甲类或乙类（乙3），如果不是，则提示：门店资质等级为乙类（乙x），不允许续签。
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/checkGrade/{param}", method = RequestMethod.GET)
    public ResultData<?> checkGrade(@PathVariable String param) {
        Map<String, Object> qparam = JsonUtil.parseToObject(param, Map.class);
        ResultData<?> resultData = storeService.checkGrade(qparam);
        return resultData;
    }

    /**
     * @param param
     * @return
     * @Title: querylistByCompanyId
     * @Description: 通过公司ID查找未关联门店列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/gpcompanyStore/{param}", method = RequestMethod.GET)
    public String querygpCompanyStore(@PathVariable String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        try {
            resultData = this.storeService.querygpCompanyStore(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "queryCompanyStore", "", null, "", "", e);
        }
        return resultData.toString();
    }


    /**
     * @param param
     * @return
     * @Title: querylistByCompanyId
     * @Description: 通过公司ID查找关联门店列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/qCompanyIdgp/{param}", method = RequestMethod.GET)
    public String querygplistByCompanyId(@PathVariable String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        try {
            List<String> districtsNo = (List<String>) queryParam.remove("districtNoList");
            if (null != districtsNo && !districtsNo.isEmpty()) {
                String[] arr = (String[]) districtsNo.toArray(new String[districtsNo.size()]);
                queryParam.put("districtNoList", arr);
            }
            resultData = this.storeService.querygplistByCompanyId(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "ConcernedStoreController", "querylistByCompanyId", "", null, "", "", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/gpqr/{param}", method = RequestMethod.GET)
    public String queryGpRelatelist(@PathVariable String param) {
        @SuppressWarnings("unchecked")
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        //构建返回
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        try {
            resultData = this.storeService.querygplistByCompanyId(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "queryGpRelatelist", "", null, "", "", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getPlainInfoById/{id}", method = RequestMethod.GET)
    public String getPlainInfoById(@PathVariable int id) {
        //构建返回
        ResultData<Store> resultData = new ResultData<>();
        try {
            resultData = this.storeService.getPlainInfoById(id);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("store", "StoreController", "getPlainInfoById", "storeId=" + id, null, "", "查询门店异常", e);
        }
        return resultData.toString();
    }
}
