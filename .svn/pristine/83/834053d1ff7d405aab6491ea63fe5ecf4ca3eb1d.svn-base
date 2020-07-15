package cn.com.eju.deal.open.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.achievement.dao.CitySettingMapper;
import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.open.model.CompanyInfoDto;
import cn.com.eju.deal.open.model.ExchangeStoreDto;
import cn.com.eju.deal.open.service.APIExchangeService;
import cn.com.eju.deal.store.dao.StoreMapper;

/**   
* 提供给Exchange系统的API
* @author wenhui.zhang
* @date 2016年6月23日 下午4:03:16
*/
@RestController
@RequestMapping(value = "crm/exchange")
public class APIExchangeController extends BaseController
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource
    private APIExchangeService apiExchangeService;
    
    @Resource
    private CitySettingMapper citySettingMapper;
    
    @Resource
    private StoreMapper storeMapper;
    
    /** 
     * 查询-对象
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "company/{param}", method = RequestMethod.GET)
    public String getCompanyInfo(@PathVariable String param)
    {
        //构建返回
        ResultData<List<CompanyInfoDto>> resultData = new ResultData<List<CompanyInfoDto>>();
        try
        {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            //查询
            List<CompanyInfoDto> companyInfoDtoList = apiExchangeService.getCompanyInfo(queryParam);
            resultData.setReturnData(companyInfoDtoList);
            resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("APIExchange", "APIExchangeController", "getCompanyInfo", "companyName", 1, "", "获取公司信息失败！", e);
        }
        return resultData.toString();
    }
    
    
    /** 
     * 查询-对象
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "store/{param}", method = RequestMethod.GET)
    public String getStoreList(@PathVariable String param)
    {
        //构建返回
        ResultData<List<ExchangeStoreDto>> resultData = new ResultData<List<ExchangeStoreDto>>();
        try
        {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            //查询
            List<ExchangeStoreDto> storeList = apiExchangeService.getStoreList(queryParam);
            resultData.setReturnData(storeList);
            resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("APIExchange", "APIExchangeController", "getStoreList", "storeList", 1, "", "获取门店信息失败！", e);
        }
        return resultData.toString();
    }
    
	/**
	 * 
	 * 获取门店维护人业绩归属架构
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "maintainer/{param}", method = RequestMethod.GET)
	public String getMaintainer(@PathVariable String param) {
		// 构建返回
		ResultData<?> resultData = new ResultData<>();
		// TMS传过来的参数
		Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
		if (queryParam.size() <= 0 || queryParam == null) {
			resultData.setFail("参数不能为空 param =" + param);
			return resultData.toString();
		}
		// TMS传过来的参数 门店编号
		String storeNo = (String) queryParam.get("storeNo");
		if (StringUtil.isEmpty(storeNo)) {
			resultData.setFail("参数不正确！");
			return resultData.toString();
		}
		// 根据门店编号获取门店所在城市编号
		//String cityNo = storeMapper.getCityNoByStoreNo(storeNo);
		
		String cityNo = storeMapper.getAcCityNoByStoreNo(storeNo);
		// 根据城市编号获取城市 业绩开关
		String achievemntTypeFlag = citySettingMapper.getOpenFlagByCityNo(cityNo);
		if ("1".equals(achievemntTypeFlag)) {
			// 查询
			try {
				resultData = apiExchangeService.getMaintainer(storeNo,cityNo);
				resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
			} catch (Exception e) {
				logger.error("APIExchange", "APIExchangeController", "getMaintainer", "", 1, "",
						"获取门店维护人业绩归属架构失败！param =" + param.toString(), e);
			}

		} else {
			// 查询
			try {
				resultData = apiExchangeService.getMaintainerOld(storeNo);
				resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
			} catch (Exception e) {
				logger.error("APIExchange", "APIExchangeController", "getMaintainerOld", "", 1, "",
						"获取门店维护人业绩归属架构失败！param =" + param.toString(), e);
			}
		}
		return resultData.toString();
	}

}
