package cn.com.eju.deal.fangyou.service;

import cn.com.eju.deal.base.file.util.EncryptUtil;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.fangyou.model.FYCompany;
import cn.com.eju.deal.fangyou.model.FYEmp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Sky on 2016/4/4.
 * 房友服务层
 */
@Service("fangyouService")
public class FangyouService extends BaseService<Object> {

    /**
     * 房友接口地址
     */

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "companyMapper")
    private CompanyMapper companyMapper;

    /**
     * 获取员工信息
     *
     * @return 员工信息列表
     */
    public String getEmployee(Integer companyId)
            throws Exception {

        ResultData<List<FYEmp>> resultData = new ResultData<>();

        Company company = companyMapper.getById(companyId);

        String url = absoluteUrl("RMS", "fangyou/api/get_employees_by_companyId");

        String fangyouCompanyId = company.getFangyouCompanyId();

        if (StringUtil.isEmpty(fangyouCompanyId)) {

            resultData.setFail();
            return resultData.toString();
        }

        String sign = getSign(fangyouCompanyId);

        url = String.format(url + "?companyId=%s&signKey=%s", company.getFangyouCompanyId(), sign);

        try {
            String apiResultJson = getForFY(url);
            Map<String, Object> rspMap;

            rspMap = JsonUtil.parseToObject(apiResultJson, Map.class);

            String dataCode = rspMap.get("returnCode").toString();
            String msg = (String) rspMap.get("returnMsg");
            List<FYEmp> result = (List<FYEmp>) rspMap.get("returnData");

            if ("200".equals(dataCode)) {
                resultData.setSuccess();
                resultData.setReturnData(result);
            } else {
                resultData.setFail(msg);
            }

            return resultData.toString();

        } catch (IOException e) {
            logger.error("fangyou", "FangyouService", "getEmployee", "companyId=" + companyId, -1, "", "获取员工信息异常", e);
            resultData.setFail();
            return resultData.toString();
        }
    }

    /**
     * 修改密码
     *
     * @return 修改密码
     */
    public String changePassword(String password, String companyId)
            throws Exception {
        Company company = companyMapper.getById(Integer.valueOf(companyId));

        String url = absoluteUrl("RMS", "fangyou/api/changepwd11");

        String sign = getSign(company.getFangyouCompanyId());
        url = String.format(url + "?companyId=%s&signKey=%s&pwd=%s", company.getFangyouCompanyId(), sign, password);
        return getFyApiResult(url);
    }

    /**
     * 开通房友账号
     *
     * @return 开通的结果
     */
    public String createFangyou(FYCompany fyCompany)
            throws Exception {

        String url = absoluteUrl("RMS", "fangyou/api/initialize");

        String sign = getSign(fyCompany.getCompanyId());
        String param = fyCompany.ConvertToUrlParam();
        url += "?" + param + "&signKey=" + sign;
        return getFyApiResult(url);
    }

    /**
     * 获取房友接口请求参数
     *
     * @param url url
     * @return 返回result的json
     */
    private String getFyApiResult(String url)
            throws Exception {
        ResultData<String> resultData = new ResultData<>();
        try {
            String apiResultJson = getForFY(url);

            Map rspMap;

            rspMap = JsonUtil.parseToObject(apiResultJson, Map.class);

            String dataCode = rspMap.get("returnCode").toString();
            String msg = (String) rspMap.get("returnMsg");

            if ("200".equals(dataCode)) {
                resultData.setSuccess();
            } else {
                resultData.setFail(msg);
            }

            //TODO记录合同创建，开通房友账号日志
            logger.info("合同提交OA审核，开通房友账号，入参url=" + url + ";  返回值：apiResultJson=" + apiResultJson);

            //TODO记录合同创建，开通房友账号日志
            logger.error("fangyou",
                    "FangyouService ",
                    "getFyApiResult",
                    "创建房友账号入参url=" + url,
                    0,
                    "",
                    "记录房友开通情况:" + apiResultJson,
                    null);

            return resultData.toString();
        } catch (Exception e) {
            logger.error("fangyou", "FangyouService ", "getFyApiResult", "创建房友账号入参url=" + url, -1, "", "调用房友接口异常", e);

            resultData.setFail();
            return resultData.toString();
        }
    }

    /**
     * 获取Sign值
     *
     * @param companyId 公司编号
     * @return 参数sign
     */
    private String getSign(String companyId)
            throws Exception {

        String dateTime = (new SimpleDateFormat("yyyyMMddHH")).format(new Date());

        try {
            return EncryptUtil.MD5(companyId + dateTime);
        } catch (Exception e) {
            return "";
        }

    }
}
