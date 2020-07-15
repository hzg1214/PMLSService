package cn.com.eju.deal.api.store.controller;

import cn.com.eju.deal.api.store.constant.StoreConstant;
import cn.com.eju.deal.api.store.dto.APPStoreDto;
import cn.com.eju.deal.api.store.service.APIStoreService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import org.apache.http.Consts;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * Created by haidan on 2018/3/13.
 */
@RestController
@RequestMapping(value = "APIStore")
public class APIStoreController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "apiStoreService")
    private APIStoreService apiStoreService;

    /**
     * 根据公司编号信息获取合同审核通过的门店信息(门店编号、门店名称、门店地址)
     * 提供房友APP
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/app/getStoresByCompanyNo", method = RequestMethod.POST)
    public String getCompanyStores(@RequestBody String jsonStr) {
        //构建返回
        ResultData<List<APPStoreDto>> resultData = new ResultData<>();
        try {
            String jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            Map<String, Object> queryParam = JsonUtil.parseToObject(jsonData, Map.class);
            if (StringUtil.isEmpty(String.valueOf(queryParam.get("companyNo")))) {
                resultData.setReturnCode("-1");
                resultData.setFail("companyNo参数值不能为空");
                return resultData.toString();
            }
            List<APPStoreDto> storeList = apiStoreService.getStoresByCompanyNo(queryParam);
            resultData.setReturnData(storeList);
            if (storeList != null && storeList.size() > 0) {
                resultData.setTotalCount(String.valueOf(storeList.size()));
            } else {
                resultData.setTotalCount("0");
            }

        } catch (Exception e) {
            logger.error("api.store", "APIStoreController", "getCompanyStores", "", null, "", "", e);
            resultData.setFail(StoreConstant.FAIL_REMOTE_GET_STORE_LIST_BY_COMPANYNO);
            return resultData.toString();
        }
        return resultData.toString();
    }

    /**
     * 根据门店编号查询门店详情
     *
     * @param jsonStr
     * @return
     */
    @RequestMapping(value = "app/getStoreDetailByNo", method = RequestMethod.POST)
    public String getStoreDetailByNo(@RequestBody String jsonStr) {
        //构建返回
        ResultData<APPStoreDto> resultData = new ResultData<>();
        try {
            String jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            Map<String, Object> queryParam = JsonUtil.parseToObject(jsonData, Map.class);
            if (StringUtil.isEmpty(String.valueOf(queryParam.get("storeNo")))) {
                resultData.setReturnCode("-1");
                resultData.setFail("storeNo参数值不能为空");
                return resultData.toString();
            }
            APPStoreDto appStoreDto = apiStoreService.getStoreDetailByNo(queryParam);
            if(appStoreDto == null){
                resultData.setFail(StoreConstant.FAIL_REMOTE_DATA_IS_NULL);
            }else{
                resultData.setReturnData(appStoreDto);
            }
        } catch (Exception e) {
            logger.error("api.store", "APIStoreController", "getStoreDetailByNo", "", null, "", "", e);
            resultData.setFail(StoreConstant.FAIL_REMOTE_GET_STORE_DETAIL);
            return resultData.toString();
        }
        return resultData.toString();
    }

    /**
     * 公盘中根据公司编号获取门店列表
     * @param jsonStr
     * @return
     */
    @RequestMapping(value = "/app/getStoresByCompanyNoGp", method = RequestMethod.POST)
    public String getGpCompanyStores(@RequestBody String jsonStr) {
        //构建返回
        ResultData<List<APPStoreDto>> resultData = new ResultData<>();
        try {
            String jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            Map<String, Object> queryParam = JsonUtil.parseToObject(jsonData, Map.class);
            if (StringUtil.isEmpty(String.valueOf(queryParam.get("companyNo")))) {
                resultData.setReturnCode("-1");
                resultData.setFail("companyNo参数值不能为空");
                return resultData.toString();
            }
            List<APPStoreDto> storeList = apiStoreService.getStoresByCompanyNoGp(queryParam);
            resultData.setReturnData(storeList);
            if (storeList != null && storeList.size() > 0) {
                resultData.setTotalCount(String.valueOf(storeList.size()));
            } else {
                resultData.setTotalCount("0");
            }

        } catch (Exception e) {
            logger.error("api.store", "APIStoreController", "getGpCompanyStores", "", null, "", "", e);
            resultData.setFail(StoreConstant.FAIL_REMOTE_GET_STORE_LIST_BY_COMPANYNO);
            return resultData.toString();
        }
        return resultData.toString();
    }
}
