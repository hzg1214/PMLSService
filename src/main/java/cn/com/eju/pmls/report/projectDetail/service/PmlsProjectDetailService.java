package cn.com.eju.pmls.report.projectDetail.service;

import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.report.projectDetail.dao.PmlsProjectDetailMapper;
import cn.com.eju.pmls.report.projectDetail.dto.ProjectDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by haidan on 2020/6/17.
 */
@Service("pmlsProjectDetailService")
public class PmlsProjectDetailService {
    @Autowired
    private PmlsProjectDetailMapper pmlsProjectDetailMapper;

    public ResultData<List<ProjectDetailDto>> queryList(Map<String, Object> param) {
        ResultData<List<ProjectDetailDto>> resultData = new ResultData<>();
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

        List<ProjectDetailDto> list = pmlsProjectDetailMapper.queryList(param);

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
}
