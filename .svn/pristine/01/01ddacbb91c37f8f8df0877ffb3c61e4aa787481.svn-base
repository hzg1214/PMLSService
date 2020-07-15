package cn.com.eju.deal.Report.service;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.Report.dao.ExpandDetailMapper;
import cn.com.eju.deal.Report.model.ExcelTask;
import cn.com.eju.deal.Report.model.ExpandDetail;
import cn.com.eju.deal.Report.model.UserCenterAuthDto;
import cn.com.eju.deal.reportbase.util.Constants;
import cn.com.eju.deal.reportbase.util.FormatStringUtil;
import cn.com.eju.deal.reportbase.util.TaskUtil;
import cn.com.eju.deal.reportbase.util.UrlUtil;
import cn.com.eju.deal.reporttask.constant.ExcelTaskConstant.ExcelTask_ExportType;

@Service("expandDetailService")
public class ExpandDetailServiceImpl {
	@Resource
	private ExpandDetailMapper expandDetailMapper;

	public List<ExpandDetail> queryExpandDetailList(Map<String, Object> map) {
		List<ExpandDetail> list =null;
		try {

			list = expandDetailMapper.searchExpandDetail(map);

		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}
	
	
	/**
	 * 根据userId查询城市
	 * @param map
	 * @return
	 */
	public  List<UserCenterAuthDto> getUserCenterAuthCityName(Map<String, Object> map){
		List<UserCenterAuthDto> list =null;
		try {
			list = expandDetailMapper.getUserCenterAuthCityName(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据userId城市Id查询区/事业部
	 * @param map
	 * @return
	 */
	public  List<UserCenterAuthDto> getAreaGroupName(Map<String, Object> map)
	{
		List<UserCenterAuthDto> list =null;
		try {
			list = expandDetailMapper.getAreaGroupName(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 *  根据登录人ID,城市ID，对应区/事业部ID 拿到对应部门/中心
	 * @param map
	 * @return
	 */
	public  List<UserCenterAuthDto> getCenterGroupName(Map<String, Object> map)
	{
		List<UserCenterAuthDto> list =null;
		try {
			list = expandDetailMapper.getCenterGroupName(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据登录人ID,城市ID，对应区/事业部ID 对应部门/中心ID查询组
	 * @param map
	 * @return
	 */
	public  List<UserCenterAuthDto> getGroupName(Map<String, Object> map)
	{
		List<UserCenterAuthDto> list =null;
		try {
			list = expandDetailMapper.getGroupName(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	@Resource
	private ITempletService templetService;

	@Resource
	private IExcelTaskService excelTaskService;

	/**
	 * 拓展明细报表异步下载
	 * @param map
	 * @return
	 */
	public String exportExpandDetailList(final Map<String, Object> map) {
		final String fileName = ExcelTask_ExportType.ExpandDetail.getCnName();
		String fileNameAll = FormatStringUtil.getExcelName(map, fileName);
		// 保存任务
		final int id = excelTaskService.saveExcelTask(map, ExcelTask_ExportType.ExpandDetail.getCode(), fileNameAll);

		// 开启线程导出excel到服务器中，并修改任务状态
		TaskUtil.EXECUTORS.execute(new Runnable() 
		{
			public void run() 
			{	
				handExpandDetailExcelTask(map, fileName, id);
			}
		});

		return fileNameAll;
	}

	
	/**
	 * 拓展明细导出线程
	 * @param queryParam
	 * @param fileName
	 * @param id
	 */
	public void handExpandDetailExcelTask(Map<String, Object> queryParam, String fileName, int id) 
	{
		FileOutputStream fileOutputStream = null;
		try {
			int tag = 10;//debug
			String filePath = FormatStringUtil.getExcelName(queryParam, fileName);
			String outPath = UrlUtil.getFilePath(fileName) + Constants.EXECL_SUFFIX;
			
			ExecutorService executorService = Executors.newFixedThreadPool(1);
			final Map<String, Object> Param = queryParam;
			final String path = outPath;
			 
			executorService.execute(new Runnable() 
			{
				@Override
				public void run() 
				{
					// Excel导出
					try 
					{
			            //页面数据
//						ExcelForExpend excelForExpend = new ExcelForExpend();
//						List<ExpandDetail> list= queryExpandDetailList(Param);
//						excelForExpend.downloadSheet(list, path);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		
			executorService.shutdown();
			executorService.awaitTermination(300, TimeUnit.SECONDS);
			// 修改任务状态
			ExcelTask obj = new ExcelTask();
			obj.setId(id);
			obj.setStatus(2);
			obj.setDownloadurl(path);
			excelTaskService.update(obj);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
