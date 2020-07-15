package cn.com.eju.deal.controller.cashier;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.dto.code.CommonCodeDto;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.model.cashier.BankInfo;
import cn.com.eju.deal.model.cashier.CashierDto;
import cn.com.eju.deal.model.signContract.ContractNewDto;
import cn.com.eju.deal.service.cashier.CashierService;
import cn.com.eju.deal.service.image.ImageService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

@RestController
@RequestMapping(value = "cashier")
public class CashierController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "cashierService")
    CashierService cashierService;
    @Resource(name = "imageService")
    ImageService imageService;

    @RequestMapping(value = "getBankAccountList", method = RequestMethod.POST)
    public String getBankAccountList(@RequestBody String jsonDto) {

        ResultData<List<BankInfo>> resultData = new ResultData<List<BankInfo>>();
        try {
            BankInfo dto = JsonUtil.parseToObject(jsonDto, BankInfo.class);
            List<BankInfo> list = cashierService.getBankAccountList(dto);
            resultData.setReturnData(list);
        } catch (Exception e) {
            resultData.setFail(e.toString());
            logger.error("CashierController", "CashierController", "getBankAccountList", "", null, "", "获取收款账号", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "getCollectionMethodList", method = RequestMethod.POST)
    public String getCollectionMethodList(@RequestBody String jsonDto) {

        ResultData<List<CommonCodeDto>> resultData = new ResultData<>();
        try {
            CashierDto dto = JsonUtil.parseToObject(jsonDto, CashierDto.class);
            List<CommonCodeDto> list = cashierService.getCollectionMethodList(dto);
            resultData.setReturnData(list);
        } catch (Exception e) {
            resultData.setFail(e.toString());
            logger.error("CashierController", "CashierController", "getCollectionMethodList", "", null, "", "获取收款方式", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "getSkTypeCode", method = RequestMethod.POST)
    public String getSkTypeCode(@RequestBody String jsonDto) {

        ResultData resultData = new ResultData();
        try {
            CashierDto dto = JsonUtil.parseToObject(jsonDto, CashierDto.class);
            String skTypeCode = cashierService.getSkTypeCode(dto);
            resultData.setReturnData(skTypeCode);
        } catch (Exception e) {
            resultData.setFail(e.toString());
            logger.error("CashierController", "CashierController", "getSkTypeCode", "", null, "", "获取收款code", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "saveCashier", method = RequestMethod.POST)
    public String saveCashier(@RequestBody String jsonDto) {

        ResultData resultData = new ResultData();
        try {
            CashierDto dto = JsonUtil.parseToObject(jsonDto, CashierDto.class);
            CashierDto resultDto = cashierService.saveCashier(dto);
            if (resultDto.getId() == null) {
                String name = "17904".equals(dto.getCollectionType()) ? "保证金" : "服务费";
                resultData.setFail("门店（" + dto.getStoreNo() + "）的实收" + name + "或在途" + name + "已改变，请刷新页面！");
            } else {
                resultData.setSuccess();
            }
            resultData.setReturnData(resultDto);
        } catch (Exception e) {
            resultData.setFail(e.toString());
            logger.error("CashierController", "CashierController", "saveCashier", "", null, "", "保存收款信息", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "getCashierContractList", method = RequestMethod.POST)
    public String getCashierContractList(@RequestBody String jsonDto) {

        ResultData<List<ContractNewDto>> resultData = new ResultData();
        try {
            ContractNewDto dto = JsonUtil.parseToObject(jsonDto, ContractNewDto.class);
            resultData = cashierService.getCashierContractList(dto);
        } catch (Exception e) {
            resultData.setFail(e.toString());
            logger.error("CashierController", "CashierController", "getCashierContractList", "", null, "", "获取收款合同信息失败", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "getCashierContractById", method = RequestMethod.POST)
    public String getCashierContractById(@RequestBody String jsonDto) {

        ResultData<List<ContractNewDto>> resultData = new ResultData();
        try {
            ContractNewDto dto = JsonUtil.parseToObject(jsonDto, ContractNewDto.class);
            resultData = cashierService.getCashierContractById(dto);
        } catch (Exception e) {
            resultData.setFail(e.toString());
            logger.error("CashierController", "CashierController", "getCashierContractById", "", null, "", "获取收款合同信息失败by id", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "deleteCashierContractById", method = RequestMethod.POST)
    public String deleteCashierContractById(@RequestBody String jsonDto) {

        ResultData resultData = new ResultData();
        try {
            CashierDto dto = JsonUtil.parseToObject(jsonDto, CashierDto.class);
            resultData = cashierService.deleteCashierContractById(dto);
            resultData.setSuccess();
        } catch (Exception e) {
            resultData.setFail(e.toString());
            logger.error("CashierController", "CashierController", "deleteCashierContractById", "", null, "", "删除待收款失败by id", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "getCashierById", method = RequestMethod.POST)
    public String getCashierById(@RequestBody String jsonDto) {

        ResultData resultData = new ResultData();
        try {
            CashierDto dto = JsonUtil.parseToObject(jsonDto, CashierDto.class);
            resultData = cashierService.getCashierById(dto);
            resultData.setSuccess();
        } catch (Exception e) {
            resultData.setFail(e.toString());
            logger.error("CashierController", "CashierController", "getCashierById", "", null, "", "获取待收款具体信息失败by id", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "getReceiveType", method = RequestMethod.POST)
    public String getReceiveType(@RequestBody String jsonDto) {
        ResultData resultData = new ResultData();
        try {
            CashierDto dto = JsonUtil.parseToObject(jsonDto, CashierDto.class);
            resultData = cashierService.getReceiveType(dto);
            resultData.setSuccess();
        } catch (Exception e) {
            resultData.setFail(e.toString());
            logger.error("CashierController", "CashierController", "getReceiveType", "", null, "", "获取收款类型", e);
        }
        return resultData.toString();
    }
}
