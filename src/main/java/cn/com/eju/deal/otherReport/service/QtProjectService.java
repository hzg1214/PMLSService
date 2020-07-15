package cn.com.eju.deal.otherReport.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateDto;
import cn.com.eju.deal.dto.scene.estate.SceneEstateSearchResultDto;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateMapper;

/**
 * desc:其它收入-项目service
 * @author :zhenggang.Huang
 * @date   :2019年10月12日
 */
@Service("qtProjectService")
public class QtProjectService extends BaseService {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private EstateMapper estateMapper;

    /**
     * 查询-list
     *
     * @param queryParam
     * @return
     */
    public ResultData<List<SceneEstateSearchResultDto>> queryList(Map<?, ?> param) throws Exception {
        //构建返回
        ResultData<List<SceneEstateSearchResultDto>> resultData = new ResultData<List<SceneEstateSearchResultDto>>();
        //查询
        final List<EstateDto> list = estateMapper.getProjectList(param);
        //转换
        List<SceneEstateSearchResultDto> estateDtoList = new ArrayList<SceneEstateSearchResultDto>();
        if (null != list && !list.isEmpty()) {
            SceneEstateSearchResultDto dto = null;
            for (int i = 0; i < list.size(); i++) {
                dto = new SceneEstateSearchResultDto();
                BeanUtils.copyProperties(list.get(i), dto);

                if (StringUtils.isNotBlank(list.get(i).getRealityCityNo()))
                    dto.setRealityCityNm(SystemParam.getCityNameByCityNo(list.get(i).getRealityCityNo()));
                if (StringUtils.isNotBlank(list.get(i).getCityNo()))
                    dto.setCityNoNm(SystemParam.getCityNameByCityNo(list.get(i).getCityNo()));
                dto.setAddress(list.get(i).getAddress());
                dto.setEstateType(list.get(i).getMgtKbn());
                dto.setEstateTypeNm(list.get(i).getMgtKbnStr());
                dto.setEstateNm(list.get(i).getEstateNm());
                estateDtoList.add(dto);
            }
        }
        resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(estateDtoList);
        return resultData;
    }

}
