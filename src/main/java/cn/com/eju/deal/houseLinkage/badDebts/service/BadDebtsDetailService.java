package cn.com.eju.deal.houseLinkage.badDebts.service;

import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.houseLinkage.badDebts.BadDebtsDetailDto;
import cn.com.eju.deal.dto.houseLinkage.badDebts.LongBadDebtsDto;
import cn.com.eju.deal.houseLinkage.badDebts.dao.BadDebtsDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by haidan on 2019/8/28.
 */
@Service("badDebtsDetailService")
public class BadDebtsDetailService {

    @Autowired
    private BadDebtsDetailMapper badDebtsDetailMapper;

    public ResultData queryBadDebtsList(Map<String, Object> param) {
        ResultData resultData = new ResultData<>();

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

        List<BadDebtsDetailDto> list = badDebtsDetailMapper.queryBadDebtsList(param);

        if (null != list && list.size() > 0) {
            int size = list.size();
            resultData.setTotalCount(String.valueOf(size));
            if (param.get("optFlag") == null) {//导出标记
                int endRow = pageIndex * pageSize;
                list = list.subList((pageIndex - 1) * pageSize, endRow > size ? size : endRow);

                param.put("pageIndex", pageIndex);
                param.put("pageSize", pageSize);
                param.put("curPage", curPage);
            }
            resultData.setReturnData(list);
        }


        return resultData;
    }

    public ResultData queryLongBadDebtsList(Map<?, ?> param) throws Exception {
        ResultData resultData = new ResultData<>();
        //查询
        List<LongBadDebtsDto> list = badDebtsDetailMapper.queryLongBadDebtsList(param);
        if(!CollectionUtils.isEmpty(list)){
            resultData.setReturnData(list);
            resultData.setTotalCount(String.valueOf(list.size()));
        }
        return resultData;
    }
}
