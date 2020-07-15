package cn.com.eju.pmls.performswitch.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.dao.CityMapper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.linkage.model.City;
import cn.com.eju.deal.core.support.ResultData;

import cn.com.eju.pmls.performswitch.dto.CityUseDto;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * service层
 */
@Service("cityUseService")
public class CityUseService extends BaseService {
    
/*    @Resource
    private CityUseMapper cityUseMapper;*/

    @Resource
    private CityMapper cityMapper;

    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    /**
     * 根据城市编号获取城市相关信息
     * @return
     */
    public ResultData<City> getByCityNo(String cityNo)throws Exception {
        //构建返回
        ResultData<City> resultData = new ResultData<City>();
        //查询
        final City city = cityMapper.getCityByCityNo(cityNo);

        resultData.setReturnData(city);
        return resultData;
    }
    

}
