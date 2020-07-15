package cn.com.eju.deal.controller.signContract;

import java.util.List;

import javax.annotation.Resource;

import cn.com.eju.deal.core.util.StringUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.contract.model.Contract;
import cn.com.eju.deal.contract.service.ContractService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dataSource.DbcontextHolder;
import cn.com.eju.deal.model.signContract.ContractNewDto;
import cn.com.eju.deal.model.sweepStreets.CompanyNewDto;
import cn.com.eju.deal.model.sweepStreets.StoreNewDto;
import cn.com.eju.deal.service.signContract.SignContractService;

/**
 * Created by xu on 2017/5/31.
 */
@RestController
@RequestMapping(value = "signContractController")
public class SignContractController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "signContractService")
    private SignContractService signContractService;

    @Resource(name = "contractService")
    private ContractService contractService;

    @RequestMapping(value = "/getStoreList", method = RequestMethod.POST)
    public String getStoreList(@RequestBody String jsonDto)
    {
        StoreNewDto dto = JsonUtil.parseToObject(jsonDto, StoreNewDto.class);
        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        try{
            resultData = signContractService.getStoreList(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("signContractController", "signContractController", "getStoreList", "", null, "", "获取签约门店列表", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getCompanyByStoreId", method = RequestMethod.POST)
    public String getCompanyByStoreId(@RequestBody String jsonDto)
    {
        StoreNewDto dto = JsonUtil.parseToObject(jsonDto, StoreNewDto.class);
        ResultData resultData = new ResultData();
        try{
            resultData = signContractService.getCompanyByStoreId(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("signContractController", "signContractController", "getCompanyByStoreId", "", null, "", "获取签约门店公司信息", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getCompanyList", method = RequestMethod.POST)
    public String getCompanyList(@RequestBody String jsonDto)
    {
        CompanyNewDto dto = JsonUtil.parseToObject(jsonDto, CompanyNewDto.class);
        ResultData<List<CompanyNewDto>> resultData = new ResultData<List<CompanyNewDto>>();
        try{
            resultData = signContractService.getCompanyList(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("signContractController", "signContractController", "getCompanyList", "", null, "", "获取签约公司列表", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getCompanyById", method = RequestMethod.POST)
    public String getCompanyById(@RequestBody String jsonDto)
    {
        CompanyNewDto dto = JsonUtil.parseToObject(jsonDto, CompanyNewDto.class);
        ResultData resultData = new ResultData();
        try{
            resultData = signContractService.getCompanyById(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("signContractController", "signContractController", "getCompanyById", "", null, "", "获取签约公司信息", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/addContract", method = RequestMethod.POST)
    public String addContract(@RequestBody String jsonDto)
    {
        ContractNewDto dto = JsonUtil.parseToObject(jsonDto, ContractNewDto.class);
        ResultData resultData = new ResultData();
        try{
            resultData = signContractService.addContract(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("signContractController", "signContractController", "addContract", "", null, "", "新增草签合同", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/checkContract", method = RequestMethod.POST)
    public String checkContract(@RequestBody String jsonDto)
    {
        ContractNewDto dto = JsonUtil.parseToObject(jsonDto, ContractNewDto.class);
        ResultData resultData = new ResultData();
        try{
            resultData = signContractService.checkContract(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("signContractController", "signContractController", "checkContract", "", null, "", "验证门店是否可以签约", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/checkAgreementNo", method = RequestMethod.POST)
    public String checkAgreementNo(@RequestBody String jsonDto)
    {
        ContractNewDto dto = JsonUtil.parseToObject(jsonDto, ContractNewDto.class);
        ResultData resultData = new ResultData();
        try{
            //resultData = signContractService.checkAgreementNo(dto);
            //和PC校验一样

            String agreementNo = dto.getAgreementNo();
            ResultData<List<Contract>> resultDataList = contractService.getContractByAgreementNo(agreementNo);
            List<Contract> list = resultDataList.getReturnData();
            if(list.size()>0)
            {
                String strContractNo="";
                String strMsg = "";
                int flag=0;
                for (Contract contract : list) {
                    //审核中或审核通过
                    if(10402 == contract.getContractStatus() || 10403 == contract.getContractStatus())
                    {
                        strContractNo += contract.getContractNo()+",";
                        flag = 1;
                    }
                    //草签
                    if(10401 == contract.getContractStatus())
                    {
                        strContractNo += contract.getContractNo()+",";
                        flag = 2;
                    }
                }

                if(strContractNo.length()>0)
                {
                    strContractNo = strContractNo.substring(0, strContractNo.length()-1);
                }
                if(flag==1)
                {
                    strMsg = "系统中存在相同协议书编号的合同，合同编号为"+strContractNo+"请勿重复提交！";
                    resultData.setFail(strMsg);
                }else if(flag==2){
                    strMsg = "系统中存在相同协议书编号的草签合同，合同编号为"+strContractNo+"。如有必要请作废后再操作，请勿重复提交！";
                    resultData.setFail(strMsg);
                }
                return resultData.toString();
            }
            if (StringUtil.isEmpty(dto.getOriginalContractNo())) {
                resultData = signContractService.checkContract(dto);
            }
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("signContractController", "signContractController", "checkAgreementNo", "", null, "", "验证协议书编号是否重复", e);
        }
        return resultData.toString();
    }

    /**
     * 获取签约合同列表
     * @param jsonDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getSingContractList", method = RequestMethod.POST)
    public String getSingContractList(@RequestBody String jsonDto)
    {
        ContractNewDto dto = JsonUtil.parseToObject(jsonDto, ContractNewDto.class);
        ResultData resultData = new ResultData();
        try{
            resultData = signContractService.getSignContractList(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("signContractController", "signContractController", "getSingContractList", "", null, "", "查询签约合同列表", e);
        }
        return resultData.toString();
    }
    /**
     * 获取签约合同列表
     * @param jsonDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getGpSignContractList", method = RequestMethod.POST)
    public String getGpSignContractList(@RequestBody String jsonDto)
    {
        ContractNewDto dto = JsonUtil.parseToObject(jsonDto, ContractNewDto.class);
        ResultData resultData = new ResultData();
        try{
            resultData = signContractService.getGpSignContractList(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("signContractController", "signContractController", "getGpSignContractList", "", null, "", "查询公盘合同列表", e);
        }
        return resultData.toString();
    }

    /**
     * 获取签约合同详情
     * @param jsonDto
     * @return
     */
    @RequestMapping(value = "/getSignContractInfo", method = RequestMethod.POST)
    public String getSingContractInfo(@RequestBody String jsonDto)
    {
        ContractNewDto dto = JsonUtil.parseToObject(jsonDto, ContractNewDto.class);
        ResultData resultData = new ResultData();
        try{
            resultData = signContractService.getSignContractInfo(dto);
        }catch (Exception e)
        {
            //异常时需设置 数据源为主数据源
            DbcontextHolder.setDbType("dataSourceMain");
            resultData.setFail();
            logger.error("signContractController", "signContractController", "getSingContractInfo", "ContractNewDto jsonDto：  "+jsonDto, null, "", "获取签约合同详情", e);
        }
        return resultData.toString();
    }
    /**
     * 获取公盘合同详情
     * @param jsonDto
     * @return
     */
    @RequestMapping(value = "/getGpSignContractInfo", method = RequestMethod.POST)
    public String getGpSignContractInfo(@RequestBody String jsonDto)
    {
        ContractNewDto dto = JsonUtil.parseToObject(jsonDto, ContractNewDto.class);
        ResultData resultData = new ResultData();
        try{
            resultData = signContractService.getGpSignContractInfo(dto);
        }catch (Exception e)
        {
            //异常时需设置 数据源为主数据源
            DbcontextHolder.setDbType("dataSourceMain");
            resultData.setFail();
            logger.error("signContractController", "signContractController", "getGpSignContractInfo", "", null, "", "获取签约合同详情", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getTodoSignStoreList", method = RequestMethod.POST)
    public String getTodoSignStoreList(@RequestBody String jsonDto)
    {
        StoreNewDto dto = JsonUtil.parseToObject(jsonDto, StoreNewDto.class);
        ResultData<List<StoreNewDto>> resultData = new ResultData<>();
        try{
            resultData = signContractService.getTodoSignStoreList(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("signContractController", "signContractController", "getTodoSignStoreList", jsonDto, null, "", "获取签约门店列表", e);
        }
        return resultData.toString();
    }
}
