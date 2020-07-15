package cn.com.eju.deal.controller.cashierInfo;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.model.signContract.ContractNewDto;
import cn.com.eju.deal.service.cashierInfo.CashierInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xu on 2017/4/11.
 */
@RestController
@RequestMapping(value = "cashierInfoController")
public class CashierInfoController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "cashierInfoService")
    private CashierInfoService cashierInfoService;

    @RequestMapping(value = "getCashierInfoListData", method = RequestMethod.POST)
    public String getCashierInfoListData(@RequestBody String jsonDto) {

        ResultData<List<ContractNewDto>> resultData = new ResultData();
        try {
            ContractNewDto dto = JsonUtil.parseToObject(jsonDto, ContractNewDto.class);
            resultData = cashierInfoService.getCashierInfoListData(dto);
        } catch (Exception e) {
            resultData.setFail(e.toString());
            logger.error("CashierInfoController", "CashierInfoController", "getCashierInfoListData", "", null, "", "获取收款记录列表", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "getCashierInfoById", method = RequestMethod.POST)
    public String getCashierInfoById(@RequestBody String jsonDto) {

        ResultData<List<ContractNewDto>> resultData = new ResultData();
        try {
            ContractNewDto dto = JsonUtil.parseToObject(jsonDto, ContractNewDto.class);
            resultData = cashierInfoService.getCashierInfoById(dto);
        } catch (Exception e) {
            resultData.setFail(e.toString());
            logger.error("CashierInfoController", "CashierInfoController", "getCashierInfoById", "", null, "", "获取收款记录失败by id", e);
        }
        return resultData.toString();
    }

}
