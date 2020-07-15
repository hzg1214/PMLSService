package cn.com.eju.deal.mapper.cypay;

import cn.com.eju.deal.model.cypay.CYMerchantDto;
import cn.com.eju.deal.model.cypay.CYPayResponseDto;
import cn.com.eju.deal.model.cypay.PayLogDto;

/**
 * Created by Administrator on 2017/4/10 0010.
 */
public interface CYPayMapper {
    public void addPayResponseLog(PayLogDto dto);
    public int addPayResponse(CYPayResponseDto dto);
    public int saveMerchantInfo(CYMerchantDto dto);
    public int updateMerchant(CYMerchantDto dto);
    public CYMerchantDto getMecharantId(CYMerchantDto dto);
}
