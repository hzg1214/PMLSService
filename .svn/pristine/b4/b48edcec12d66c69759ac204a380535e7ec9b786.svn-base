package cn.com.eju.deal.houseLinkage.linkConversionRate.service;

import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.houseLinkage.linkConversionRate.LinkConversionRateDto;
import cn.com.eju.deal.houseLinkage.linkConversionRate.dao.LinkConversionRateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by haidan on 2019/8/28.
 */
@Service("linkConversionRate")
public class LinkConversionRateService {

    @Autowired
    private LinkConversionRateMapper linkConversionRateMapper;

    public ResultData<List<LinkConversionRateDto>> queryList(Map<String, Object> param) {
        ResultData<List<LinkConversionRateDto>> resultData = new ResultData<>();

        int pageIndex = 1;
        int pageSize = 10;
        int curPage = 1;

        if (param.get("pageIndex") != null)
            pageIndex = Integer.parseInt(param.get("pageIndex").toString());
        if (param.get("pageSize") != null)
            pageSize = Integer.parseInt(param.get("pageSize").toString());
        if (param.get("curPage") != null)
            curPage = Integer.parseInt(param.get("curPage").toString());

        param.remove("pageIndex");
        param.remove("pageSize");
        param.remove("curPage");

        if (param.get("optFlag") == null) {//查询
            param.put("offset", (pageIndex - 1) * pageSize);
            param.put("rows", pageSize);
            param.put("total", 0);
            param.put("clickType",1);
        }else{//导出
            param.put("offset", 0);
            param.put("rows", 0);
            param.put("total", 0);
            param.put("clickType",2);
        }

        List<LinkConversionRateDto> list = linkConversionRateMapper.queryList(param);

        if (null != list && list.size() > 0) {
            int size = Integer.parseInt(param.get("total").toString());
            resultData.setTotalCount(String.valueOf(size));
            resultData.setReturnData(list);
        }
        return resultData;
    }
}
