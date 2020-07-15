package cn.com.eju.deal.Report.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.Report.dao.ExcelTaskMapper;
import cn.com.eju.deal.Report.model.ExcelTask;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.reportbase.util.TaskUtil;


@Service("excelTaskService")
public class ExcelTaskService implements IExcelTaskService {
	@Resource
	private ExcelTaskMapper excelTaskMapper;
	
	@Override
	public int save(ExcelTask record) {
		// TODO 自动生成的方法存根
		record.setStatus(1);
//		record.setCreateuserid(200);
		return excelTaskMapper.insert(record);
	}
	
	public int saveExcelTask(Map<String, Object> map,String exportType,String fileName){
    	int userId =Integer.parseInt(map.get("createUserId").toString());
        //保存任务
        ExcelTask excelTask = new ExcelTask();
        excelTask.setCreatetime(new Date());
        excelTask.setCreateuserid(userId);
        excelTask.setExportType(exportType);
//      excelTask.setStatus(1);
        excelTask.setUrlParam(TaskUtil.handlerParam(map));
        excelTask.setFileName(fileName);
        save(excelTask);
        return excelTask.getId();
    }
	
	@Override
	public ResultData<List<ExcelTask>> queryExcelTaskByParam(Map<String, Object> map) {
		 //构建返回
        ResultData<List<ExcelTask>> resultData = new ResultData<List<ExcelTask>>();
		
		map.put("noStatus", 3);
//		map.put("createUserId", 200);
		// TODO 自动生成的方法存根
		List<ExcelTask> excelTaskList = excelTaskMapper.queryExcelTaskByParam(map);
		resultData.setTotalCount((String)map.get(QueryConst.TOTAL_COUNT));       
		resultData.setReturnData(excelTaskList);
		return  resultData;
	}

	@Override
	public int update(ExcelTask record) {
		// TODO 自动生成的方法存根
		return excelTaskMapper.update(record);
	}

}
