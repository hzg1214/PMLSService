package cn.com.eju.deal.houseLinkage.estate.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.houseLinkage.estate.LnkEstateIncomeplanDto;
import cn.com.eju.deal.houseLinkage.estate.service.LnkEstateIncomeplanSeivice;
import cn.com.eju.pmls.cooperation.model.CooperationDto;

/**
 * Created by eju on 2019/12/6.
 */
@RestController
@RequestMapping(value = "lnkEstateIncomeplan")
public class LnkEstateIncomeplanController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private LnkEstateIncomeplanSeivice lnkEstateIncomeplanSeivice;

    @RequestMapping(value = "/addIncomePlan", method = RequestMethod.POST)
    public ResultData<?> addIncomePlan(@RequestBody String jsonDto) {

        //构建返回
        ResultData<?> resultData = new ResultData<>();

        Map<String, Object> map = JsonUtil.parseToObject(jsonDto, Map.class);

        int count = 0;
        try {
            count = lnkEstateIncomeplanSeivice.addIncomePlan(map);
            if (count <= 0) {
                resultData.setFail();
            }
        } catch (Exception e) {
            resultData.setFail();
            logger.error("CRM", "LnkEstateIncomeplanController", "addIncomePlan", "", null, "", "新增失败", e);
        }

        return resultData;
    }

    @RequestMapping(value = "/toProhibit", method = RequestMethod.POST)
    public ResultData<?> toProhibit(@RequestBody String jsonDto) {
        Map<String, Object> map = JsonUtil.parseToObject(jsonDto, Map.class);

        //构建返回
        ResultData<?> resultData = new ResultData<>();
        int count = 0;
        try {
            count = lnkEstateIncomeplanSeivice.toProhibit(map);
            if (count <= 0) {
                resultData.setFail();
            }
        } catch (Exception e) {
            resultData.setFail();
            logger.error("CRM", "LnkEstateIncomeplanController", "toProhibit", "", null, "", "", e);
        }
        return resultData;
    }

    @RequestMapping(value = "/queryPlanById/{id}", method = RequestMethod.GET)
    public String queryPlanById(@PathVariable Integer id) {
        //构建返回
        ResultData<LnkEstateIncomeplanDto> resultData = new ResultData<LnkEstateIncomeplanDto>();
        try {
            resultData = lnkEstateIncomeplanSeivice.queryPlanById(id);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("CRM", "LnkEstateIncomeplanController", "queryPlanById", "", null, "", "", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/editIncomePlan", method = RequestMethod.POST)
    public ResultData<?> editIncomePlan(@RequestBody String jsonDto) {

        //构建返回
        ResultData<?> resultData = new ResultData<>();

        Map<String, Object> map = JsonUtil.parseToObject(jsonDto, Map.class);

        int count = 0;
        try {
            count = lnkEstateIncomeplanSeivice.editIncomePlan(map);
            if (count <= 0) {
                resultData.setFail();
            }
        } catch (Exception e) {
            resultData.setFail();
            logger.error("CRM", "LnkEstateIncomeplanController", "editIncomePlan", "", null, "", "修改失败", e);
        }

        return resultData;
    }

    @RequestMapping(value = "/queryCXSQAmount/{param}", method = RequestMethod.GET)
    public String queryCXSQAmount(@PathVariable String param) {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        //构建返回
        ResultData<LnkEstateIncomeplanDto> resultData = new ResultData<LnkEstateIncomeplanDto>();
        try {
            resultData = lnkEstateIncomeplanSeivice.queryCXSQAmount(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("CRM", "LnkEstateIncomeplanController", "queryCXSQAmount", "", null, "", "", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/queryIncomeplanList/{param}", method = RequestMethod.GET)
    public String selectIncomeplanListPopup(@PathVariable String param) {
        ResultData<List<LnkEstateIncomeplanDto>> resultData = new ResultData<List<LnkEstateIncomeplanDto>>();
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = lnkEstateIncomeplanSeivice.queryIncomeplanList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("PMLS", "LnkEstateIncomeplanController", "selectIncomeplanListPopup",
                    "", null, "", "", e);
        }

        return resultData.toString();
    }

    /**
     * desc:佣金方案维护-经纪公司列表
     * 2020年2月27日
     */
    @RequestMapping(value = "/getCompanyList/{param}", method = RequestMethod.GET)
    public String getCompanyList(@PathVariable String param)
    {
    	//构建返回
    	ResultData<List<CooperationDto>> resultData = new ResultData<List<CooperationDto>>();
    	try{
    		Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
    		resultData = lnkEstateIncomeplanSeivice.getCompanyList(queryParam);
    	}
    	catch (Exception e)
    	{
    		logger.error("PMLS", "LnkEstateIncomeplanController", "getCompanyList", "", 0, "", "佣金方案维护-经纪公司列表失败 ", e);
    		resultData.setFail();
    	}
    	return resultData.toString();
    }
    
    /**
     * desc:禁用佣金方案
     * 2020年3月2日
     */
    @RequestMapping(value = "/toIsEnable", method = RequestMethod.POST)
    public ResultData<?> toIsEnable(@RequestBody String jsonDto) {
        Map<String, Object> map = JsonUtil.parseToObject(jsonDto, Map.class);

        //构建返回
        ResultData<?> resultData = new ResultData<>();
        int count = 0;
        try {
            count = lnkEstateIncomeplanSeivice.toIsEnable(map);
            if (count <= 0) {
                resultData.setFail();
            }
        } catch (Exception e) {
            resultData.setFail();
            logger.error("PMLS", "LnkEstateIncomeplanController", "toIsEnable", "", null, "", "", e);
        }
        return resultData;
    }
}
