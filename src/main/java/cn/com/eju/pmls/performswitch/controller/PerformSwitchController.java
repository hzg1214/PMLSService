package cn.com.eju.pmls.performswitch.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.model.City;
import cn.com.eju.deal.core.support.ResultData;

import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.pmls.performswitch.dto.CityUseDto;
import cn.com.eju.pmls.performswitch.dto.PerformSwitchDto;
import cn.com.eju.pmls.performswitch.model.PerformSwitch;
import cn.com.eju.pmls.performswitch.service.CityUseService;
import cn.com.eju.pmls.performswitch.service.PerformSwitchService;
import cn.com.eju.pmls.skStatement.dto.PmlsSkStatementDto;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * 开关账 controller
 */

@RestController
@RequestMapping(value = "performswitch")
public class PerformSwitchController extends BaseController {

	@Resource
	private PerformSwitchService performSwitchService;

	@Resource
	private CityUseService cityUseService;

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

	/**
	 * 根据  OMS 新房联动   查询开关账全部
	 * @param param 查询条件
	 * @return
	 */
	@RequestMapping(value = "/qMap/{param}", method = RequestMethod.GET)
	public String queryMap(@PathVariable String param)
	{
		//构建返回
		ResultData<?> resultData = new ResultData<>();

		try
		{
			Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

			resultData = performSwitchService.queryListDto(queryParam);


		}
		catch (Exception e)
		{
			logger.error("PerformSwitch", "PerformSwitchController", "queryMap", "input param: param=" + param, 0, "", "查询列表异常", e);

			resultData.setFail();
		}

		return resultData.toString();
	}


	/**
	 *  TODO PMLS 新房联动 开关账的业务逻辑
	 * @param jsonDto 对象字符串
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@RequestBody String jsonDto)
	{
		Integer relateSystem = 0;
		//构建返回
		ResultData<PerformSwitchDto> resultData = new ResultData<PerformSwitchDto>();

		List<PerformSwitch> molist=new ArrayList<PerformSwitch>();
		try {
			// 转换
			List<PerformSwitchDto> dtolist = JSONArray.parseArray(jsonDto,PerformSwitchDto.class);
			//赋值
			molist = convertData(dtolist);
		} catch (Exception e) {
			logger.error("PerformSwitch", "PerformSwitchController", "create", "input param:dtolist"+jsonDto.toString(), 0, "", "数据转换异常", e);
			resultData.setFail();
		}

		List<String> cityNos = new ArrayList<>();
		try{
			StringBuilder cityNamesr= new StringBuilder("");

			for (PerformSwitch performSwitch : molist) {
				relateSystem =  performSwitch.getRelateSystem();
				cityNos.add(performSwitch.getCityNo());
				try {
					//根据城市获取模板编号
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("cityNo",performSwitch.getCityNo());
					ResultData<City> data =cityUseService.getByCityNo(performSwitch.getCityNo());
					//关账时操作
					if(27502==performSwitch.getSwitchState().intValue()){
						PerformSwitch switch1=new PerformSwitch();
						switch1.setCityNo(performSwitch.getCityNo());
						switch1.setExchangeCenterId(performSwitch.getExchangeCenterId());
						switch1.setRelateSystem(performSwitch.getRelateSystem());
						//查询该城市有无记录
						PerformSwitch switch2=performSwitchService.selectBYSwitch(switch1);
						if(switch2!=null){
							//查询   城市  年月  前面有无"开"的记录
							PerformSwitch switch3=performSwitchService.selectBYSwitch(performSwitch);
							if(switch3==null){
								//查询前一个月有无记录
								PerformSwitch switch4=performSwitchService.checkAroundDate(performSwitch);
								if(switch4!=null){
									//查询当月有没有关账记录
									PerformSwitch switch5=performSwitchService.checkInDate(performSwitch);
									if(switch5!=null ){
										//判断系统状态和当次操作的状态是否一样   不一样就更新
										if(switch5.getSwitchState()!=null && !switch5.getSwitchState().equals(performSwitch.getSwitchState())){
											performSwitch.setUpdateUserCode(performSwitch.getCloseUserCode());
											performSwitch.setUpdateUserName(performSwitch.getCloseUserName());
											performSwitch.setId(switch5.getId());
											performSwitchService.update(performSwitch);
										}
									}else{
										performSwitchService.create(performSwitch);
									}
								}else{
									//查询当月有没有关账记录
									PerformSwitch switch5=performSwitchService.checkInDate(performSwitch);
									if(switch5!=null ){
										//判断系统状态和当次操作的状态是否一样   不一样就更新
										if(switch5.getSwitchState()!=null && !switch5.getSwitchState().equals(performSwitch.getSwitchState())){
											performSwitch.setUpdateUserCode(performSwitch.getCloseUserCode());
											performSwitch.setUpdateUserName(performSwitch.getCloseUserName());
											performSwitch.setId(switch5.getId());
											performSwitchService.update(performSwitch);
										}
									}else{
										Calendar c = Calendar.getInstance();
										c.set(Calendar.YEAR, Integer.valueOf(performSwitch.getSwitchYear()));
										c.set(Calendar.MONTH, Integer.valueOf(performSwitch.getSwitchMonth()));
										c.set(Calendar.DATE, 1);
										c.add(Calendar.MONTH,-2);
										cityNamesr.append(data.getReturnData().getCityName()+"该城市在"+ DateUtil.fmtDate(c.getTime(), "yyyy")+"年"+DateUtil.fmtDate(c.getTime(), "MM")+"月没有关账记录");
									}
								}
							}else{
								cityNamesr.append(data.getReturnData().getCityName()+"该城市在"+switch3.getSwitchYear()+"年"+switch3.getSwitchMonth()+"月已有开账记录");
							}

						}else{
							performSwitchService.create(performSwitch);
						}
						//开账时操作
					}else{
						//查询该城市后面有无关的记录              开账前必须判断后面没有关的
						PerformSwitch switch1=performSwitchService.selectBYSwitch(performSwitch);
						if(switch1==null){
							//查询当月有没有关账记录
							PerformSwitch switchas=performSwitchService.checkInDate(performSwitch);
							if(switchas!=null){
								//判断系统状态和当次操作的状态是否一样   不一样就更新
								if(switchas.getSwitchState()!=null && !switchas.getSwitchState().equals(performSwitch.getSwitchState())){
									if(StringUtil.isNotEmpty(performSwitch.getOpenUserCode())){
										performSwitch.setUpdateUserCode(performSwitch.getOpenUserCode());
										performSwitch.setUpdateUserName(performSwitch.getOpenUserName());
									}else{
										performSwitch.setUpdateUserCode(performSwitch.getCloseUserCode());
										performSwitch.setUpdateUserName(performSwitch.getCloseUserName());
									}
									performSwitch.setId(switchas.getId());
									performSwitchService.update(performSwitch);
								}
							}
						}else {
							cityNamesr.append(data.getReturnData().getCityName()+"该城市在"+switch1.getSwitchYear()+"年"+switch1.getSwitchMonth()+"月已有关账记录");
						}
					}

				} catch (Exception e) {
					logger.error("PerformSwitch", "PerformSwitchController", "selectBYSwitch", "input param performSwitch"+performSwitch.toString(), 0, "", "查询开关账信息异常", e);
					resultData.setFail();
				}
			}
			resultData.setReturnMsg(cityNamesr.toString());
		}
		catch (Exception e)
		{
			logger.error("PerformSwitch", "PerformSwitchController", "create", "input param dtolist"+jsonDto.toString(), 0, "", "", e);

			resultData.setFail();
		}

/*		//开关账调用房友接口，通知房友    新房联动才通知房友
		if(ReturnCode.SUCCESS.equals(resultData.getReturnCode()) && relateSystem==27601){
			if("".equals(resultData.getReturnMsg())){
				//开关账成功的时候才调用
				performSwitchService.postToFangYou(cityNos);
			}
		}*/
		return resultData.toString();
	}

	/**
	 * 查看该月份下城市是否尚有大定待审核的记录
	 * @return
	 */
	@RequestMapping(value="/checkRoughToValid/{param}",method= RequestMethod.GET)
	public ResultData<?> checkRoughToValid(@PathVariable String param){
		ResultData<?> resultData = new ResultData<>();
		try{
			Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
			resultData = performSwitchService.checkRoughToValid(queryParam);
		}catch(Exception e){
			logger.error("OMS", "PerformSwitchController", "checkRoughToValid", "parameter = " + param, null, null, "查看所选月份下城市是否尚有大定待审核的记录", e);
			resultData.setFail();
		}
		return resultData;
	}

	/**
	 * 对象转换MO--DTO
	 * @param dtoList
	 * @return List<Decoration>
	 */
	private List<PerformSwitch> convertData(List<PerformSwitchDto> dtoList)
			throws Exception {
//		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		List<PerformSwitch> moList = new ArrayList<PerformSwitch>();

		if (null != dtoList && !dtoList.isEmpty()) {
			PerformSwitch mo = null;
			for (PerformSwitchDto dto : dtoList) {
				mo = new PerformSwitch();
				BeanUtils.copyProperties(dto, mo);
				moList.add(mo);
			}
		}
		return moList;
	}

	/**
	 * 检验收款单的入账日期处于关账月份且未拆分完毕，不允许关账！
	 */
	@RequestMapping(value = "/checkSkAllocate/{param}", method = RequestMethod.GET)
	@ResponseBody
	public String checkSkAllocate(@PathVariable String param) {
		ResultData<String> resultData = new ResultData<String>();
		try {
			Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
			resultData = performSwitchService.checkSkAllocate(queryParam);
		} catch (Exception e) {
			logger.error("PerformSwitchController.checkSkAllocate 发生异常 param:" + param, e);
			resultData.setFail();
		}
		return resultData.toString();
	}
}
