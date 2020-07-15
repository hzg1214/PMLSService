package cn.com.eju.deal.controller.exception;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.exception.ExceptionDto;
import cn.com.eju.deal.service.exception.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "exceptionController")
public class ExceptionController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private ExceptionService exceptionService;

    @RequestMapping(value = "/getExceptionList", method = RequestMethod.POST)
    public String getExceptionList(@RequestBody String jsonDto) {
        ResultData resultData = new ResultData();
        ExceptionDto dto=new ExceptionDto();
        try {
            dto = JsonUtil.parseToObject(jsonDto, ExceptionDto.class);
            resultData = exceptionService.getExceptionList(dto);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("", "ExceptionController", "getExceptionList", "", null, "", "查询异常信息失败："+dto.getUserCode(), e);
        }

        return resultData.toString();
    }

    @RequestMapping(value = "/updateMaintainer", method = RequestMethod.POST)
    public String updateMaintainer(@RequestBody String jsonDto) {
        ResultData resultData = new ResultData();
        try {
            ExceptionDto dto = JsonUtil.parseToObject(jsonDto, ExceptionDto.class);
            resultData = exceptionService.updateMaintainer(dto);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("", "ExceptionController", "getExceptionList", "", null, "", "变更维护人失败", e);
        }

        return resultData.toString();
    }

    @RequestMapping(value = "/queryMaintainerByCenterId", method = RequestMethod.POST)
    public String queryMaintainerByCenterId(@RequestBody String jsonDto) {
        ResultData resultData = new ResultData();
        ExceptionDto dto=new ExceptionDto();
        try {
            dto = JsonUtil.parseToObject(jsonDto, ExceptionDto.class);
            resultData = exceptionService.queryMaintainerByCenterId(dto);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("", "ExceptionController", "getExceptionList", "", null, "", "查询维护人失败："+dto.getUserCode(), e);
        }

        return resultData.toString();
    }
}
