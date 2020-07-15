package cn.com.eju.deal.controller.cypay;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.model.cashier.CashierDto;
import cn.com.eju.deal.model.common.ResultDto;
import cn.com.eju.deal.model.cypay.CYMerchantDto;
import cn.com.eju.deal.model.cypay.CYPayResponseDto;
import cn.com.eju.deal.model.cypay.PayLogDto;
import cn.com.eju.deal.service.cypay.CYPayService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

@RestController
@RequestMapping(value = "cyPay")
public class CYPayController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    CYPayService cyPayService;

    @RequestMapping(value = "payOK", method = RequestMethod.POST)
    public String payOK(@RequestBody String jsonDto)
    {

        ResultData resultData = new ResultData();
        try{
            CashierDto dto = JsonUtil.parseToObject(jsonDto, CashierDto.class);
            ResultDto resultDto = cyPayService.payOK(dto);
            PayLogDto payLogDto = new PayLogDto();
            payLogDto.setLogContent("cashierid == " + dto.getId() + "result == " + JsonUtil.parseToJson(resultDto));
            cyPayService.addPayResponseLog(payLogDto);
        }catch (Exception e){
            resultData.setFail(e.toString());
            logger.error("CYPayController", "CYPayController", "payOK", "", null, "", "支付成功", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "addPayResponseLog", method = RequestMethod.POST)
    public String addPayResponseLog(@RequestBody String jsonDto)
    {

        ResultData resultData = new ResultData();
        try{
            PayLogDto dto = JsonUtil.parseToObject(jsonDto, PayLogDto.class);
            cyPayService.addPayResponseLog(dto);
        }catch (Exception e){
            resultData.setFail(e.toString());
            logger.error("CYPayController", "CYPayController", "addPayResponseLog", "", null, "", "保存支付回调信息日志", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "addPayResponse", method = RequestMethod.POST)
    public String addPayResponse(@RequestBody String jsonDto)
    {

        ResultData resultData = new ResultData();
        try{
            CYPayResponseDto dto = JsonUtil.parseToObject(jsonDto, CYPayResponseDto.class);
            ResultDto res = cyPayService.addPayResponse(dto);
            PayLogDto log = new PayLogDto();
            log.setLogContent("dto.id = " +dto.getTraceNO() + "resultDto" + JsonUtil.parseToJson(res));
            cyPayService.addPayResponseLog(log);
            if(res.getState() ==-10){
                resultData.setFail("更新数据失败");
            }
            resultData.setReturnData(res);
        }catch (Exception e){
            resultData.setFail(e.toString());
            logger.error("CYPayController", "CYPayController", "addPayResponse", "", null, "", "保存支付回调信息", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "saveMerchantInfo", method = RequestMethod.POST)
    public String saveMerchantInfo(@RequestBody String jsonDto)
    {

        ResultData resultData = new ResultData();
        try{
            CYMerchantDto dto = JsonUtil.parseToObject(jsonDto, CYMerchantDto.class);
            int count = cyPayService.saveMerchantInfo(dto);
            if(count<=0){
                resultData.setFail("执行失败");
            }else{
                dto.setMemberFlag(dto.getMerchantId()+"");
                resultData.setReturnData(dto);
            }
        }catch (Exception e){
            resultData.setFail(e.toString());
            logger.error("CYPayController", "CYPayController", "saveMerchantInfo", "", null, "", "保存商户信息", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "updateMerchant", method = RequestMethod.POST)
    public String updateMerchant(@RequestBody String jsonDto)
    {

        ResultData resultData = new ResultData();
        try{
            CYMerchantDto dto = JsonUtil.parseToObject(jsonDto, CYMerchantDto.class);
            int count = cyPayService.updateMerchant(dto);
            if(count<=0){
                resultData.setFail("执行失败");
            }else{
                resultData.setReturnData(dto);
            }
        }catch (Exception e){
            resultData.setFail(e.toString());
            logger.error("CYPayController", "CYPayController", "updateMerchant", "", null, "", "修改商户信息", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "getMecharantId", method = RequestMethod.POST)
    public String getMecharantId(@RequestBody String jsonDto)
    {

        ResultData resultData = new ResultData();
        try{
            CYMerchantDto dto = JsonUtil.parseToObject(jsonDto, CYMerchantDto.class);
            CYMerchantDto res = cyPayService.getMecharantId(dto);

            resultData.setReturnData(res);

        }catch (Exception e){
            resultData.setFail(e.toString());
            logger.error("CYPayController", "CYPayController", "getMecharantId", "", null, "", "获取商户信息", e);
        }
        return resultData.toString();
    }
}
