package cn.com.eju.deal.controller.gpSignContract;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dataSource.DbcontextHolder;
import cn.com.eju.deal.model.signContract.ContractNewDto;
import cn.com.eju.deal.model.sweepStreets.CompanyNewDto;
import cn.com.eju.deal.model.sweepStreets.StoreNewDto;
import cn.com.eju.deal.service.gpSignContract.GpSignContractService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xu on 2017/5/31.
 */
@RestController
@RequestMapping(value = "gpSignContractController")
public class GpSignContractController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "gpSignContractService")
    private GpSignContractService gpSignContractService;

    @RequestMapping(value = "/getCompanyList", method = RequestMethod.POST)
    public String getCompanyList(@RequestBody String jsonDto) {
        CompanyNewDto dto = JsonUtil.parseToObject(jsonDto, CompanyNewDto.class);
        ResultData<List<CompanyNewDto>> resultData = new ResultData<List<CompanyNewDto>>();
        try {
            resultData = gpSignContractService.getCompanyList(dto);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("gpSignContractController", "gpSignContractController", "getCompanyList", "", null, "", "获取签约公司列表", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getRelativeStoreList", method = RequestMethod.POST)
    public String getRelativeStoreList(@RequestBody String jsonDto) {
        CompanyNewDto dto = JsonUtil.parseToObject(jsonDto, CompanyNewDto.class);
        ResultData<List<StoreNewDto>> resultData = new ResultData<>();
        try {
            resultData = gpSignContractService.getRelativeStoreList(dto);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("gpSignContractController", "gpSignContractController", "getRelativeStoreList", "", null, "", "获取关联门店列表", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/addRelativeStore", method = RequestMethod.POST)
    public String addRelativeStore(@RequestBody String jsonDto) {
        CompanyNewDto dto = JsonUtil.parseToObject(jsonDto, CompanyNewDto.class);
        ResultData resultData = new ResultData();
        try {
            resultData = gpSignContractService.addRelativeStore(dto);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("gpSignContractController", "gpSignContractController", "addRelativeStore", "", null, "", "关联门店", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getCompanyById", method = RequestMethod.POST)
    public String getCompanyById(@RequestBody String jsonDto) {
        CompanyNewDto dto = JsonUtil.parseToObject(jsonDto, CompanyNewDto.class);
        ResultData resultData = new ResultData();
        try {
            resultData = gpSignContractService.getCompanyById(dto);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("gpSignContractController", "gpSignContractController", "getCompanyById", "", null, "", "获取签约公司信息", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/addContract", method = RequestMethod.POST)
    public String addContract(@RequestBody String jsonDto) {
        ContractNewDto dto = JsonUtil.parseToObject(jsonDto, ContractNewDto.class);
        ResultData resultData = new ResultData();
        try {
            resultData = gpSignContractService.addContract(dto);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("gpSignContractController", "gpSignContractController", "addContract", "", null, "", "新增草签合同", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/checkAgreementNo", method = RequestMethod.POST)
    public String checkAgreementNo(@RequestBody String jsonDto) {
        ContractNewDto dto = JsonUtil.parseToObject(jsonDto, ContractNewDto.class);
        ResultData resultData = new ResultData();
        try {
            resultData = gpSignContractService.checkAgreementNo(dto);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("gpSignContractController", "gpSignContractController", "checkAgreementNo", "", null, "", "验证协议书编号是否重复", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getTodoList", method = RequestMethod.POST)
    public String getTodoList(@RequestBody String jsonDto) {
        ContractNewDto dto = JsonUtil.parseToObject(jsonDto, ContractNewDto.class);
        ResultData resultData = new ResultData();
        try {
            resultData = gpSignContractService.getTodoList(dto);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("gpSignContractController", "gpSignContractController", "getTodoList", "", null, "", "获取签约公盘合同列表", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getGpContractById", method = RequestMethod.POST)
    public String getGpContractById(@RequestBody String jsonDto) {
        ContractNewDto dto = JsonUtil.parseToObject(jsonDto, ContractNewDto.class);
        ResultData resultData = new ResultData();
        try {
            resultData = gpSignContractService.getGpContractById(dto.getContractId());
        } catch (Exception e) {
            //异常时需设置 数据源为主数据源
            DbcontextHolder.setDbType("dataSourceMain");
            resultData.setFail();
            logger.error("gpSignContractController", "gpSignContractController", "getGpContractById", "", null, "", "根据id获取公盘合同详情信息", e);
        }
        return resultData.toString();
    }
}
