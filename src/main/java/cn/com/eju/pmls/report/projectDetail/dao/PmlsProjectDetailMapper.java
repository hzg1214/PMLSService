package cn.com.eju.pmls.report.projectDetail.dao;

import cn.com.eju.pmls.report.projectDetail.dto.ProjectDetailDto;

import java.util.List;
import java.util.Map;

/**
 * Created by haidan on 2019/8/28.
 */
public interface PmlsProjectDetailMapper {

    List<ProjectDetailDto> queryList(Map<?, ?> param);
}
