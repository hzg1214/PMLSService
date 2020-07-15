package cn.com.eju.deal.houseLinkage.linkMarginDetail.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.houseLinkage.linkDetail.LinkDetailDto;
import cn.com.eju.deal.dto.houseLinkage.linkMarginDetail.LinkMarginDetailDto;
import cn.com.eju.deal.houseLinkage.linkMarginDetail.dao.LinkMarginDetailMapper;

/**
 * desc:联动资金成本(保证金、诚意金)
 *
 * @author :zhenggang.Huang
 * @date :2019年9月12日
 */
@Service("linkMarginDetailService")
public class LinkMarginDetailService extends BaseService<LinkDetailDto> {

    @Autowired
    private LinkMarginDetailMapper linkMarginDetailMapper;

    /**
     * desc:查询list
     * 2019年9月12日
     * author:zhenggang.Huang
     */
    public ResultData queryLinkMarginDetailList(Map<String, Object> param) throws Exception {
        //构建返回
        ResultData resultData = new ResultData();
        //查询
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
        List<LinkMarginDetailDto> moList = linkMarginDetailMapper.selLinkMarginDetailList(param);
        // 转换
        resultData.setTotalCount("0");
        if (null != moList && !moList.isEmpty()) {
            resultData.setTotalCount(moList.size() + "");
            int endRow = pageIndex * pageSize;
            int startCount =(pageIndex - 1) * pageSize;
            int endCount = endRow > moList.size() ? moList.size() : endRow;
            if(endCount > startCount) {
            	
            	moList = moList.subList(startCount, endCount);
            }
            param.put("pageIndex", pageIndex);
            param.put("pageSize", pageSize);
            param.put("curPage", curPage);
            resultData.setReturnData(moList);
            resultData.setSuccess();
        }
//        resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
//        resultData.setReturnData(moList);

        return resultData;
    }
    
    /**
     * desc:查询成本中心列表
     * 2019年9月24日
     * author:zhenggang.Huang
     */
    public List<Map<String,Object>> queryCostCenterList(String organization) {
        return linkMarginDetailMapper.queryCostCenterList(organization);
    }

}
