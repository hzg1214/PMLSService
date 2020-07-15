package cn.com.eju.deal.storeinformation.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.storeinformation.StoreInformationDetailDto;
import cn.com.eju.deal.storeinformation.dao.StoreInformationDetailMapper;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.ExchangeCenter;

/**
 * desc: 门店信息明细  
 * @author :zhenggang.Huang
 * @date   :2019年1月2日
 */
@Service("storeInformationDetailService")
public class StoreInformationDetailService extends BaseService<StoreInformationDetailDto> {

	@Autowired
    private StoreInformationDetailMapper storeInformationDetailMapper;
	
	@Resource
	private UserMapper userDao;
    /**
     * 查询-list
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData queryInformationDetailList(Map<String,Object> param) throws Exception {
        //构建返回
        ResultData resultData = new ResultData();
        Integer userId = (Integer)param.get("userId");
        List<ExchangeCenter> centerList = userDao.getCenterListByUserId(userId);
        String centerIdStr = (String)param.get("centerIdStr");
        if(StringUtil.isEmpty(centerIdStr)){
            if (null != centerList && !centerList.isEmpty()) {
                for (ExchangeCenter exc : centerList) {
                    if(StringUtil.isEmpty(centerIdStr)){
                        centerIdStr = exc.getExchangeCenterId().toString();
                    }else{
                        centerIdStr = centerIdStr + ',' +exc.getExchangeCenterId().toString();
                    }
                }
            }
            if (!centerIdStr.isEmpty()) param.put("centerIdStr", centerIdStr);
        }
        //查询
        List<StoreInformationDetailDto> moList = storeInformationDetailMapper.queryInformationDetailList(param);
        // 转换
        resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(moList);

        return resultData;

    }

}
