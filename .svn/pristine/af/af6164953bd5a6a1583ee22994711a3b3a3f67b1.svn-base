package cn.com.eju.deal.youFangTongBind.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.youFangTongBind.dao.YouFangTongBindMapper;
import cn.com.eju.deal.youFangTongBind.dto.YouFangTongBindDto;
import cn.com.eju.deal.youFangTongBind.model.YftEjuBind;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2019/9/3.
 */
@Service("youFangTongBindService")
public class YouFangTongBindService extends BaseService {

    @Resource
    private YouFangTongBindMapper youFangTongBindMapper;

    public ResultData bindYouFangTongInfo(YouFangTongBindDto dto) {
        ResultData resultData = new ResultData();
        YouFangTongBindDto youFangTongBindDto = youFangTongBindMapper.queryBindInfo(dto);
        if(youFangTongBindDto!=null){
            if(youFangTongBindDto.getEjuUserCode().equals(dto.getEjuUserCode())){
                //相同‘我’绑定数据库存在的经纪人，之前的状态更新为解绑
                youFangTongBindMapper.unBindById(youFangTongBindDto.getId());
            }else{
                resultData.setFail(youFangTongBindDto.getEjuUserName()+"与经纪人"+youFangTongBindDto.getYftUserName()+"已存在绑定关系");
                return resultData;
            }
        }
        Integer num = youFangTongBindMapper.insertBindInfo(dto);
        if(num>0){
            resultData.setSuccess(dto.getStartDate().replace("-","/")+"-"+dto.getEndDate().replace("-","/"));
        }
        return resultData;
    }

    public ResultData getBindList(Map<String, Object> queryParam) {
        ResultData<List<YouFangTongBindDto>> resultData = new ResultData<List<YouFangTongBindDto>>();
        List<YouFangTongBindDto> list = youFangTongBindMapper.getBindList(queryParam);
        resultData.setTotalCount("0");
        if (null != list && !list.isEmpty()) {
            resultData.setReturnData(list);
            resultData.setTotalCount(list.get(0).getDataCount()+"");
        }
        return resultData;
    }

    public ResultData getBindLogList(Map<String, Object> queryParam) {
        ResultData<List<YouFangTongBindDto>> resultData = new ResultData<List<YouFangTongBindDto>>();
        List<YouFangTongBindDto> list = youFangTongBindMapper.getBindLogList(queryParam);
        resultData.setTotalCount("0");
        if (null != list && !list.isEmpty()) {
            resultData.setReturnData(list);
            resultData.setTotalCount(list.get(0).getDataCount()+"");
        }
        return resultData;
    }

    public ResultData queryYFTBind(Map<String, Object> map) {
        ResultData<Integer> resultData = new ResultData<Integer>();
        List<YouFangTongBindDto> list = youFangTongBindMapper.queryYFTBind(map);
        resultData.setReturnData(0);
        if (null != list && !list.isEmpty()) {
            resultData.setReturnData(list.size());
            resultData.setReturnMsg("该经服与经纪人有绑定关系，不允许删除！");
        }
        return resultData;
    }
}
