package cn.com.eju.deal.controller.sweepStreets;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.model.sweepStreets.CompanyBusinessDto;
import cn.com.eju.deal.model.sweepStreets.CompanyNewDto;
import cn.com.eju.deal.model.sweepStreets.StoreInfoDto;
import cn.com.eju.deal.model.sweepStreets.StoreNewDto;
import cn.com.eju.deal.service.sweepStreets.SweepStreetsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xu on 2017/4/13.
 */
@RestController
@RequestMapping(value = "sweepStreetsController")
public class SweepStreetsController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "sweepStreetsService")
    private SweepStreetsService sweepStreetsService;

    @RequestMapping(value = "/getStoreListData", method = RequestMethod.POST)
    public String getStoreListData(@RequestBody String jsonDto)
    {
        StoreNewDto dto = JsonUtil.parseToObject(jsonDto, StoreNewDto.class);
        ResultData<List<StoreInfoDto>> resultData = new ResultData<List<StoreInfoDto>>();
        try
        {
            resultData = sweepStreetsService.getStoreListData(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("sweepStreetsController", "sweepStreetsController", "getStoreListData", "", null, "", "获取门店列表", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/getLocalStoreListData", method = RequestMethod.POST)
    public String getLocalStoreListData(@RequestBody String jsonDto)
    {
        StoreNewDto dto = JsonUtil.parseToObject(jsonDto, StoreNewDto.class);
        ResultData<List<StoreInfoDto>> resultData = new ResultData<List<StoreInfoDto>>();
        try
        {
            resultData = sweepStreetsService.getLocalStoreListData(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("sweepStreetsController", "sweepStreetsController", "getLocalStoreListData", "", null, "", "获取地图门店列表", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/addStore", method = RequestMethod.POST)
    public String addStore(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try{
            StoreNewDto dto = JsonUtil.parseToObject(jsonDto, StoreNewDto.class);
            //验证门店地址是否存在
            int checkCount=sweepStreetsService.checkStore(dto);
            if(checkCount>=1){
                resultData.setFail("系统中已存在相同的门店地址或者门店地址已被锁定，请重新填写!");
            }else{
                int count = sweepStreetsService.addStore(dto);
                if(count<=0){
                    resultData.setFail("新增门店失败");
                }else{
                    resultData.setReturnData(dto.getStoreId());
                }
            }

        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("sweepStreetsController", "sweepStreetsController", "addStore", "", null, "", "添加门店", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/updateStore", method = RequestMethod.POST)
    public String updateStore(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try{
            StoreNewDto dto = JsonUtil.parseToObject(jsonDto, StoreNewDto.class);
            //验证门店地址是否存在
            int checkCount=sweepStreetsService.checkStore(dto);
            if(checkCount>=1){
                resultData.setFail("系统中已存在相同的门店地址或者门店地址已被锁定，请重新填写!");
            }else{
                int count = sweepStreetsService.updateStore(dto);
                if(count<=0){
                    resultData.setFail("修改门店失败");
                }else{
                    resultData.setReturnData(dto);
                }
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("sweepStreetsController", "sweepStreetsController", "updateStore", "", null, "", "修改门店", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/updateStoreAuditStatus", method = RequestMethod.POST)
    public String updateStoreAuditStatus(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try {
            StoreNewDto dto = JsonUtil.parseToObject(jsonDto, StoreNewDto.class);
            int count = sweepStreetsService.updateStoreAuditStatus(dto);
            if(count<=0){
                resultData.setFail("修改门店审核状态失败");
            }else{
                resultData.setReturnData(dto);
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("sweepStreetsController", "sweepStreetsController", "updateStoreAuditStatus", "", null, "", "修改门店审核状态", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/deleteStoreById", method = RequestMethod.POST)
    public String deleteStoreById(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try{
            StoreNewDto dto = JsonUtil.parseToObject(jsonDto, StoreNewDto.class);
            int count = sweepStreetsService.deleteStoreById(dto);
            if(count<=0){
                resultData.setFail("删除门店失败");
            }else{
                resultData.setReturnData(dto);
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("sweepStreetsController", "sweepStreetsController", "deleteStoreById", "", null, "", "删除门店", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/getStoreById", method = RequestMethod.POST)
    public String getStoreById(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try
        {
            StoreNewDto dto = JsonUtil.parseToObject(jsonDto, StoreNewDto.class);
            resultData = sweepStreetsService.getStoreById(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("sweepStreetsController", "sweepStreetsController", "getStoreById", "", null, "", "获取门店信息", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getCompanyListData", method = RequestMethod.POST)
    public String getCompanyListData(@RequestBody String jsonDto)
    {
        CompanyNewDto dto = JsonUtil.parseToObject(jsonDto, CompanyNewDto.class);
        ResultData<List<CompanyNewDto>> resultData = new ResultData<List<CompanyNewDto>>();
        try
        {
            resultData = sweepStreetsService.getCompanyListData(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("sweepStreetsController", "sweepStreetsController", "getCompanyListData", "", null, "", "获取公司列表", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/addCompany", method = RequestMethod.POST)
    public String addCompany(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try{
            CompanyNewDto dto = JsonUtil.parseToObject(jsonDto, CompanyNewDto.class);
            List<CompanyNewDto> nameList=sweepStreetsService.checkCompanyByName(dto);
            if (nameList != null && nameList.size() > 0){
                String companyStr="";
                for(CompanyNewDto cnDto:nameList){
                    companyStr+=cnDto.getCompanyNo()+",";
                }
                if(!"".equals(companyStr)){
                    companyStr=companyStr.substring(0,companyStr.length()-1);
                }

                resultData.setFail("系统中已存在相同的公司名称，公司编号："+companyStr);
                return resultData.toString();
            }

            //验证公司营业执照是否存在
            List<CompanyNewDto> list=sweepStreetsService.checkCompany(dto);
            if(list!=null && list.size()>=1){
                String companyStr="";
                for(CompanyNewDto cnDto:list){
                    companyStr+=cnDto.getCompanyNo()+",";
                }
                if(!"".equals(companyStr)){
                    companyStr=companyStr.substring(0,companyStr.length()-1);
                }
                resultData.setFail("该营业执照号对应公司已存在两家或以上，请上OA单进行合并处理，公司编号："+companyStr);
            }else{
                resultData = sweepStreetsService.addCompany(dto);
                resultData.setReturnData(dto.getCompanyId());
            }

        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("sweepStreetsController", "sweepStreetsController", "addCompany", jsonDto, null, "", "添加公司", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/updateCompany", method = RequestMethod.POST)
    public String updateCompany(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try{
            CompanyNewDto dto = JsonUtil.parseToObject(jsonDto, CompanyNewDto.class);
            List<CompanyNewDto> nameList=sweepStreetsService.checkCompanyByName(dto);
            if (nameList != null && nameList.size() > 0){
                String companyStr="";
                for(CompanyNewDto cnDto:nameList){
                    companyStr+=cnDto.getCompanyNo()+",";
                }
                if(!"".equals(companyStr)){
                    companyStr=companyStr.substring(0,companyStr.length()-1);
                }

                resultData.setFail("系统中已存在相同的公司名称，公司编号："+companyStr);
                return resultData.toString();
            }
            //验证公司营业执照是否存在
            List<CompanyNewDto> list=sweepStreetsService.checkCompany(dto);
            if(list!=null && list.size()>=1){
                String companyStr="";
                for(CompanyNewDto cnDto:list){
                    companyStr+=cnDto.getCompanyNo()+",";
                }
                if(companyStr!=""){
                    companyStr=companyStr.substring(0,companyStr.length()-1);
                }
                resultData.setFail("该营业执照号对应公司已存在两家或以上，请上OA单进行合并处理，公司编号："+companyStr);
            }else{
                resultData = sweepStreetsService.updateCompany(dto);
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("sweepStreetsController", "sweepStreetsController", "updateCompany", jsonDto, null, "", "修改公司", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getCompanyById", method = RequestMethod.POST)
    public String getCompanyById(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try
        {
            CompanyNewDto dto = JsonUtil.parseToObject(jsonDto, CompanyNewDto.class);
            if(null!=dto.getBusinessLicenseNo() && !"".equals(dto.getBusinessLicenseNo())){
                //modify by haidan 2019-09-09 18:40
                //校验归属城市下有没有重复的社会统一代码
                List<CompanyNewDto> list=sweepStreetsService.checkCompany(dto);
                if(list!=null && list.size()>1){
                    String companyStr="";
                    for(CompanyNewDto cnDto:list){
                        companyStr+=cnDto.getCompanyNo()+",";
                    }
                    if(companyStr!=""){
                        companyStr=companyStr.substring(0,companyStr.length()-1);
                    }
                    resultData.setFail("该营业执照号对应公司已存在两家或以上，请上OA单进行合并处理，公司编号："+companyStr);
                    return resultData.toString();
                }
            }
            resultData = sweepStreetsService.getCompanyById(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("sweepStreetsController", "sweepStreetsController", "getCompanyById", jsonDto, null, "", "获取公司信息", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getNotBindStoreList", method = RequestMethod.POST)
    public String getNotBindStoreList(@RequestBody String jsonDto)
    {

        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        try
        {
            StoreNewDto dto = JsonUtil.parseToObject(jsonDto, StoreNewDto.class);
            resultData = sweepStreetsService.getNotBindStoreList(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("sweepStreetsController", "sweepStreetsController", "getNotBindStoreList", "", null, "", "获取未绑定的门店列表", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/getBToAStoreList", method = RequestMethod.POST)
    public String getBToAStoreList(@RequestBody String jsonDto)
    {

        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        try
        {
            StoreNewDto dto = JsonUtil.parseToObject(jsonDto, StoreNewDto.class);
            resultData = sweepStreetsService.getBToAStoreList(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("sweepStreetsController", "sweepStreetsController", "getBToAStoreList", "", null, "", "获取待乙转甲门店列表", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/getBToAStoreListForPush", method = RequestMethod.POST)
    public String getBToAStoreListForPush(@RequestBody String jsonDto)
    {

        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        try
        {
            StoreNewDto dto = JsonUtil.parseToObject(jsonDto, StoreNewDto.class);
            resultData = sweepStreetsService.getBToAStoreListForPush(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("sweepStreetsController", "sweepStreetsController", "getBToAStoreListForPush", "", null, "", "获取待乙转甲门店列表", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/updateStoreBToAAlert", method = RequestMethod.POST)
    public String updateStoreBToAAlert(@RequestBody String jsonDto)
    {
        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        try
        {
            StoreNewDto dto = JsonUtil.parseToObject(jsonDto, StoreNewDto.class);
            resultData = sweepStreetsService.updateStoreBToAAlert(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("sweepStreetsController", "sweepStreetsController", "updateStoreBToAAlert", "", null, "", "修改待乙转甲提醒状态", e);
        }
        return resultData.toString();
    }


    @RequestMapping(value = "/addCompanyBusinessInfo", method = RequestMethod.POST)
    public String addCompanyBusinessInfo(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try{
            CompanyBusinessDto dto = JsonUtil.parseToObject(jsonDto, CompanyBusinessDto.class);

            resultData = sweepStreetsService.addCompanyBusinessInfo(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("sweepStreetsController", "sweepStreetsController", "addCompanyBusinessInfo", "", null, "", "微信扫描二维码添加营业执照信息", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/refershStoreContacts", method = RequestMethod.POST)
    public String refershStoreContacts(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try{
            resultData = sweepStreetsService.refershStoreContacts();
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("sweepStreetsController", "sweepStreetsController", "refershStoreContacts", "", null, "", "刷新门店负责人到联系人表", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getAllCityList", method = RequestMethod.POST)
    public String getAllCityList(@RequestBody String jsonDto)
    {

        ResultData resultData = new ResultData();
        try
        {
            resultData = sweepStreetsService.getAllCityList();
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("sweepStreetsController", "sweepStreetsController", "getAllCityList", "", null, "", "获取所有城市列表", e);
        }
        return resultData.toString();
    }
}
