package cn.com.eju.deal.service.cypay;

import cn.com.eju.deal.mapper.cashier.CashierMapper;
import cn.com.eju.deal.mapper.cypay.CYPayMapper;
import cn.com.eju.deal.model.cashier.CashierDto;
import cn.com.eju.deal.model.common.ResultDto;
import cn.com.eju.deal.model.cypay.CYMerchantDto;
import cn.com.eju.deal.model.cypay.CYPayResponseDto;
import cn.com.eju.deal.model.cypay.PayLogDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/4/10 0010.
 */
@Service("cyPayService")
public class CYPayService {
    @Resource
    CYPayMapper cyPayMapper;
    @Resource
    CashierMapper cashierMapper;

    public void addPayResponseLog(PayLogDto log) {
        cyPayMapper.addPayResponseLog(log);
    }

    public ResultDto payOK(CashierDto cashierDto) {
        ResultDto resultDto = new ResultDto();
        CashierDto cashierInfo = cashierMapper.getCashierById(cashierDto);
        //查询不到订单
        if (cashierInfo == null) {
            resultDto.setState(-10);
            resultDto.setMessage("找不到对应的订单");
            return resultDto;
        }
        //状态不是已支付的全部为状态异常
        if (cashierInfo.getStatus() != 0) {
            resultDto.setState(-10);
            resultDto.setMessage("订单状态异常");
            return resultDto;
        }
        cashierDto.setStatus(10);
        cashierMapper.updateCashierById(cashierDto);
        return resultDto;
    }

    public ResultDto addPayResponse(CYPayResponseDto responseDto) {
        ResultDto resultDto = new ResultDto();
        cyPayMapper.addPayResponse(responseDto);
        if (responseDto.getResId() == 0) {
            resultDto.setState(-10);
            resultDto.setMessage("插入记录表失败");
            return resultDto;
        }
        //更新支付订单
        CashierDto cashierDto = new CashierDto();
        cashierDto.setReceiveNo(responseDto.getTraceNO());
        CashierDto cashierInfo = cashierMapper.getCashierByReceiveNo(cashierDto);
        //查询不到订单
        if (cashierInfo == null) {
            resultDto.setState(-10);
            resultDto.setMessage("找不到对应的订单");
            return resultDto;
        }
        //状态不是已支付的全部为状态异常
        if (cashierInfo.getStatus() != 10 && cashierInfo.getStatus() != 0) {
            resultDto.setState(-10);
            resultDto.setMessage("订单状态异常id：" + cashierInfo.getId() + "，状态：" + cashierInfo.getStatus());
            return resultDto;
        }
        //判断响应码
        if ("000".equals(responseDto.getResponseCode())) {
            cashierDto.setStatus(20);//处理成功
        } else if ("111".equals(responseDto.getResponseCode())) {
            cashierDto.setStatus(20);//处理中
        }
        cashierDto.setPayMoney(responseDto.getAmount());
        cashierDto.setPaySeq(responseDto.getPaySeq());
        if ("weixinBarcodePay".equals(responseDto.getToolCode())) {
            cashierDto.setToolCode("18603");
        } else if ("alipayBarcodePay".equals(responseDto.getToolCode())) {
            cashierDto.setToolCode("18604");
        } else {
            resultDto.setState(-10);
            resultDto.setMessage("支付工具异常");
            return resultDto;
        }

        cashierDto.setId(cashierInfo.getId());
        cashierMapper.updateCashierById(cashierDto);
        return resultDto;
    }


    public int saveMerchantInfo(CYMerchantDto dto) {
        int count = cyPayMapper.saveMerchantInfo(dto);

        return count;
    }

    public int updateMerchant(CYMerchantDto dto) {
        int count = cyPayMapper.updateMerchant(dto);

        return count;
    }

    public CYMerchantDto getMecharantId(CYMerchantDto dto) {
        CYMerchantDto res = cyPayMapper.getMecharantId(dto);

        return res;
    }
}
