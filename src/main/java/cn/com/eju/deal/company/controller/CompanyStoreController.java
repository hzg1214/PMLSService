package cn.com.eju.deal.company.controller;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.company.model.CompanyStore;
import cn.com.eju.deal.company.model.GpCompanyStore;
import cn.com.eju.deal.company.service.CompanyStoreService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.company.CompanyStoreDto;
import cn.com.eju.deal.dto.company.GpCompanyStoreDto;


/**   
* 公司门店关系
* @author 张文辉
* @date 2016年7月4日 下午5:23:42
*/
@RestController
@RequestMapping(value = "companyStore")
public class CompanyStoreController extends BaseController
{
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "companyStoreService")
    private CompanyStoreService companyStoreService;
    
    /**
     * 创建comanyStore关系
     *
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String createCompanyStore(@RequestBody String jsonDto)
    {
        ResultData<Integer> resultData = new ResultData<Integer>();
        CompanyStoreDto companyStoreDto = JsonUtil.parseToObject(jsonDto, CompanyStoreDto.class);
        try
        {
            CompanyStore companyStore = new CompanyStore();
            BeanUtils.copyProperties(companyStoreDto, companyStore);
            int num = companyStoreService.createCompanyStore(companyStore);
            resultData.setReturnData(num);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("company", "CompanyController", "createCompanyStore", "", null, "", "", e);
        }
        return resultData.toString();
    }
    
    /**
     * 删除comanyStore关系
     *
     */
    @RequestMapping(value = "deleteRelate", method = RequestMethod.PUT)
    public String deleteRelate(@RequestBody String jsonDto) {
        ResultData<Integer> resultData = new ResultData<Integer>();
        CompanyStoreDto companyStoreDto = JsonUtil.parseToObject(jsonDto, CompanyStoreDto.class);
        try
        {
            int num = companyStoreService.deleteCompanyStore(companyStoreDto);
            resultData.setReturnData(num);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("company", "CompanyController", "deleteCompanyStore", "", null, "", "", e);
        }
        return resultData.toString();
    }
    
    
    /**
     * 创建gpcomanyStore关系
     *
     */
    @RequestMapping(value = "gpCsAdd", method = RequestMethod.POST)
    public String createGpCompanyStore(@RequestBody String jsonDto)
    {
        ResultData<Integer> resultData = new ResultData<Integer>();
        GpCompanyStoreDto companyStoreDto = JsonUtil.parseToObject(jsonDto, GpCompanyStoreDto.class);
        try
        {
            GpCompanyStore companyStore = new GpCompanyStore();
            BeanUtils.copyProperties(companyStoreDto, companyStore);
            int num = companyStoreService.createGpCompanyStore(companyStore);
            resultData.setReturnData(num);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("company", "CompanyController", "createCompanyStore", "", null, "", "", e);
        }
        return resultData.toString();
    }
    /**
     * 删除gpcomanyStore关系
     *
     */
    @RequestMapping(value = "deletegpRelate", method = RequestMethod.PUT)
    public String deletegpRelate(@RequestBody String jsonDto) {
        ResultData<Integer> resultData = new ResultData<Integer>();
        GpCompanyStoreDto companyStoreDto = JsonUtil.parseToObject(jsonDto, GpCompanyStoreDto.class);
        try
        {
            int num = companyStoreService.deletegpCompanyStore(companyStoreDto);
            resultData.setReturnData(num);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("company", "CompanyController", "deleteGpCompanyStore", "", null, "", "", e);
        }
        return resultData.toString();
    }
}
