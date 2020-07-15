package cn.com.eju.deal.Report.service;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.Report.model.ExcelTask;
import cn.com.eju.deal.core.support.ResultData;

public interface IExcelTaskService {

    int save(ExcelTask record);

    ResultData<List<ExcelTask>> queryExcelTaskByParam(Map<String,Object> map);
    
    int update(ExcelTask record);
    
    int saveExcelTask(Map<String, Object> map,String exportType,String fileName);
}
