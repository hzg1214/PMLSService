package cn.com.eju.pmls.performswitch.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.pmls.performswitch.dao.PerformSwitchMapper;
import cn.com.eju.pmls.performswitch.dto.CheckRoughDate;
import cn.com.eju.pmls.performswitch.dto.PerformSwitchDto;
import cn.com.eju.pmls.performswitch.dto.ZTree;
import cn.com.eju.pmls.performswitch.model.PerformSwitch;
import cn.com.eju.pmls.skStatement.dao.PmlsSkStatementMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 开关账service层
 */
@Service("performSwitchService")
public class PerformSwitchService extends BaseService<Object> {

    @Resource
    private PerformSwitchMapper performSwitchMapper;


    @Autowired
    private PmlsSkStatementMapper pmlsSkStatementMapper;

    /**
     * 根据  pmls新房联动   查询开关账全部
     *
     * @param param
     * @return
     */
    public ResultData<ZTree> queryListDto(Map<?, ?> param) throws Exception {

        //构建返回
        ResultData<ZTree> resultData = new ResultData<ZTree>();
        //定义字体颜色
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("color", "red");
        Map<String, Object> mapweight = new HashMap<String, Object>();
        mapweight.put("font-weight", "bold");
        List<ZTree> listsum = new ArrayList<ZTree>();
        //查询 OMS 新房联动   开关账全部
        List<PerformSwitchDto> moList = performSwitchMapper.queryLisDto(param);
        for (PerformSwitchDto performSwitchDto : moList) {
            ZTree zTree = new ZTree();
            zTree.setCityNo(performSwitchDto.getCityNo());
            zTree.setName(performSwitchDto.getCityName());
            //判断是否关账，如果关账，就设置红色
            if (27502 == performSwitchDto.getSwitchState().intValue()) {
                zTree.setFont(map);
            }
            listsum.add(zTree);

        }
        //最后设置一份全国数据
        ZTree sum = new ZTree();
        sum.setName("全国");
        sum.setId(1);
        sum.setFont(mapweight);
        sum.setChildren(listsum);
        resultData.setReturnData(sum);
        return resultData;
    }


    /**
     * 查询开关账记录
     *
     * @param param
     * @return
     * @throws Exception
     */
    public PerformSwitch selectBYSwitch(PerformSwitch param) throws Exception {
        PerformSwitch performSwitch = performSwitchMapper.selectBYSwitch(param);
        return performSwitch;
    }

    /**
     * 判断当前时间前后一个月后开关账记录
     *
     * @param param
     * @return
     * @throws Exception
     */
    public PerformSwitch checkAroundDate(PerformSwitch param) throws Exception {
        PerformSwitch paramSwitch = new PerformSwitch();
        BeanUtils.copyProperties(param, paramSwitch);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, Integer.valueOf(paramSwitch.getSwitchYear()));
        c.set(Calendar.MONTH, Integer.valueOf(paramSwitch.getSwitchMonth()));
        c.set(Calendar.DATE, 1);
        c.add(Calendar.MONTH, -2);
        paramSwitch.setSwitchYear(DateUtil.fmtDate(c.getTime(), "yyyy"));
        paramSwitch.setSwitchMonth(DateUtil.fmtDate(c.getTime(), "MM"));
        PerformSwitch performSwitch = performSwitchMapper.checkAroundDate(paramSwitch);
        return performSwitch;
    }


    /**
     * 判断当前时间有没有开关账记录
     *
     * @param param
     * @return
     * @throws Exception
     */
    public PerformSwitch checkInDate(PerformSwitch param) throws Exception {
        PerformSwitch performSwitch = performSwitchMapper.checkInDate(param);
        return performSwitch;
    }


    /**
     * 查看该月份下城市是否尚有大定待审核的记录-关账
     *
     * @return
     */
    public ResultData<?> checkRoughToValid(Map<?, ?> param) {
        ResultData resultData = new ResultData();
        String[] cityNoStr = param.get("cityNoStr").toString().split(",");
        String switchYear = param.get("switchYear").toString();
        String switchMonth = param.get("switchMonth").toString();
        for (String cityNo : cityNoStr) {
            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("cityNo", cityNo);
            hashMap.put("switchDate", switchYear + "-" + switchMonth);
            List<CheckRoughDate> list = performSwitchMapper.checkRoughToValid(hashMap);
            if (list != null && list.size() > 0) {
                //还有审核的
                resultData.setReturnData(list);
                resultData.setReturnMsg("该月份下城市尚有大定待审核的记录，不能关帐");
                resultData.setSuccess();
                return resultData;
            }
        }
        resultData.setReturnData(null);
        resultData.setSuccess();
        return resultData;
    }

    /**
     * 更新
     *
     * @param performSwitch
     * @return
     */
    public int update(PerformSwitch performSwitch) throws Exception {
        int count = performSwitchMapper.update(performSwitch);
        return count;
    }


    /**
     * 创建
     *
     * @param performSwitch
     * @return
     */
    public int create(PerformSwitch performSwitch) throws Exception {
        int count = performSwitchMapper.create(performSwitch);
        return count;
    }


    /**
     * 检验收款单的入账日期处于关账月份且未拆分完毕，不允许关账！
     */
    public ResultData<String> checkSkAllocate(Map<String, Object> queryParam) throws Exception {

        ResultData<String> resultData = new ResultData<String>();
        String switchDate = queryParam.get("switchYear").toString() + "-" + queryParam.get("switchMonth").toString() + "-01";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(switchDate);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.MONTH, 1);
        Date recordDate = c.getTime();
        queryParam.put("recordDate", recordDate);

        String[] cityNoArray = queryParam.get("cityNoStr").toString().split(",");
        List<String> cityNoList = Arrays.asList(cityNoArray);
        queryParam.put("cityNoList", cityNoList);

        List<String> strList = pmlsSkStatementMapper.checkSkAllocate(queryParam);

        if (strList.size() > 0) {
            resultData.setReturnCode("201");
            resultData.setReturnMsg("存在收款单的入账日期处于关账月份且未拆分完毕,不允许关账!");
            resultData.setReturnData(String.join(",", strList));
        }
        return resultData;
    }


}
