package cn.com.eju.deal.open.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.contract.dao.AchievementCancelMapper;
import cn.com.eju.deal.contract.dao.ContractStoreMapper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.open.model.OaModifyDto;
import cn.com.eju.deal.open.util.OaAttachmentUtil;

import com.seeyon.client.CTPRestClient;
import com.seeyon.client.CTPServiceClientManager;

/**   
* 提交OA审核
* @author mimi.sun
* @date 2016年10月8日 上午11:22:46
*/
@Service("apiOaService")
public class APIOaService extends APIOaBaseService<OaModifyDto>
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource
    private AchievementCancelMapper achievementCancelMapper;
    
    @Resource
    private ContractStoreMapper contractStoreMapper;
    
    /**
     * 合同提交OA审核
     * 
     * @param reqMap
     * @return
     * @throws Exception
     */
    public Long toOaAuth(Map<String, Object> reqMap, String oaTemplateCode)
        throws Exception
    {
        logger.info("CRM 提交审核 toOaAuth reqMap=", reqMap);
        
        // 取得REST动态客户机实例
        CTPRestClient client = this.getClient();
        
        // token认证
        client.authenticate(SystemParam.getWebConfigValue("oaUserName"), SystemParam.getWebConfigValue("oaPassword"));
        
        // 表单post
        String url = "flow/" + oaTemplateCode;
        Long flowId = client.post(url, reqMap, Long.class);
        
        return flowId;
    }
    
    /**
     * 获取OA审核状态
     * 
     * @param flowId
     * @return
     * @throws Exception
     */
    public Integer getOaState(String flowId)
        throws Exception
    {
        
        // 取得REST动态客户机实例
        CTPRestClient client = this.getClient();
        // token认证
        client.authenticate(SystemParam.getWebConfigValue("oaUserName"), SystemParam.getWebConfigValue("oaPassword"));
        
        String url = "flow/state/" + flowId;
        
        // get流程状态
        Integer state = client.get(url, Integer.class);
        
        return state;
    }
    
    /**
     * 获取OA模板
     * 
     * @param
     * @return
     * @throws Exception
     */
    public String getOaTemplate(String flowId)
        throws Exception
    {
        
        // 取得REST动态客户机实例
        CTPRestClient client = this.getClient();
        // token认证
        client.authenticate(SystemParam.getWebConfigValue("oaUserName"), SystemParam.getWebConfigValue("oaPassword"));
        
        String url = "flow/data/" + flowId;
        
        // get流程正文数据
        String xmlStr = client.get(url, String.class);
        
        return xmlStr;
    }
    
    /**
     * OA附件上传
     * 
     * @param absolutePath
     *            上传附件的绝对路径
     * @return
     * @throws Exception
     */
    public String oaAttachmentUpload(String absolutePath, String fileName, String senderLoginName)
        throws Exception
    {
        
        // 取得REST动态客户机实例
        CTPRestClient client = this.getClient();
        // 获取token认证
        String token =
            client.get("token/" + SystemParam.getWebConfigValue("oaUserName") + "/"
                + SystemParam.getWebConfigValue("oaPassword"),
                String.class,
                "text/plain");
        
        String attachmentId =
            OaAttachmentUtil.oaupload(SystemParam.getWebConfigValue("oaUrl"),
                token,
                senderLoginName,
                absolutePath,
                fileName);
        
        return attachmentId;
    }
    
    /**
     * 修改表单
     * 
     * @param contractInfoDto
     * @return
     * @throws Exception
     */
    public String modify(OaModifyDto oaModifyDto)
        throws Exception
    {
        
        String url = "http://10.0.3.5:88/fcjy_return/fcjy_update.asmx";
        
        // 参数对象转json字符串
        String jsonsStr = JsonUtil.parseToJson(oaModifyDto);
        
        String jsonStr =
            "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"
                + "<soap12:Body><fcjy_Update xmlns=\"http://tempuri.org/\"><formVals>" + jsonsStr
                + "</formVals></fcjy_Update></soap12:Body>" + "</soap12:Envelope>";
        
        String backResult = post(url, jsonStr);
        
        return backResult;
    }
    
    /**
     * 获取审核批注原因
     * 
     * @param contractInfoDto
     * @return
     * @throws Exception
     */
    public String getOpinions(String flowId)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("oaApiUrl") + "fcjy_update.asmx";
        
        String jsonStr =
            "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"
                + "<soap12:Body>" + "<fcjy_getOpinions xmlns=\"http://tempuri.org/\">" + "<id>" + flowId + "</id>"
                + "</fcjy_getOpinions>" + "</soap12:Body>" + "</soap12:Envelope>";
        
        String backResult = post(url, jsonStr);
        
        return backResult;
    }
    
    /** 
    * 取得REST动态客户机实例
    * @return
    */
    private CTPRestClient getClient()
    {
        
        // 指定协议、IP和端口，获取ClientManager
        CTPServiceClientManager clientManager =
            CTPServiceClientManager.getInstance(SystemParam.getWebConfigValue("oaUrl"));
        
        // 取得REST动态客户机实例
        CTPRestClient client = clientManager.getRestClient();
        return client;
    }
    
    public static void main(String[] args)
        throws Exception
    {
        APIOaService oaService = new APIOaService();
        String flowIds =
            "-9199279135699155752 ,2910749234478399981 ,-4571578795560580484 ,9066950192172450713 ,-8126769831657455395 ,8473907710594992080 ,8804861274799296358 ,-3577991705510525100 ,6965480226361263814 ,-8615795806668566998 ,8287869575552912723 ,5546605676797513508 ,-228575833154305930 ,-849132168560095382 ,2118592569858474972 ,-1263806801638899484 ,-6956467731872593065 ,-7377259000141559478 ,-7324446060858752073 ,-6226988647819949558 ,5130999427482210109 ,3317225590389687027 ,2837012977429857548 ,-494163198690179610 ,5174193992044660256 ,5329763057616309864 ,-8927067773704189471 ,8317800260330670347 ,-882013808002121393 ,7364935332723967180 ,-3556650525380624002 ,2901191123775867821 ,-628438612637891210 ,5527515353558764029 ,-5084239025011536317 ,-6417518667094866596 ,5778569779660409240 ,-3007556559184575543 ,3937114546879152339 ,1694536956076638600 ,-8797239115687442882 ,6307210962658150047 ,1793901967570062899 ,4191352162669383405 ,-8072687040941109651 ,-7763844055292952470 ,-1745401376458390986 ,6381260373255340218 ,-8435656495039165831 ,7599199122140581167 ,-5695011579110340867 ,-4274202418543964762 ,-4711761415876426096 ,-3603175276712671329 ,3133481857927302063 ,565881327978106477 ,953148569463768886 ,7339621395927025550 ,505610159972108212 ,8233920324332899822 ,-3179370555672802681 ,7746469997962346624 ,-8999786034326649300 ,-8600956087792154841 ,-2488019939140024854 ,5438490235564671018 ,-4149308169082353905 ,2113962231361195328 ,4232854817704266264 ,1525495465448391558 ,2339940449605471753 ,4719261683523272802 ,5441521095142424589 ,1573152621822365395 ,-5235120311439307326 ,7137030737217997388 ,-8656773858528770359 ,8416066701409992970 ,-5970962336672233579 ,-8479974012618453919 ,-3429795426486434979 ,6365764084464698953 ,-4709074163793050278 ,5776331671263660274 ,7893341209105681411 ,-8161934833854492971 ,-4148364120346048848 ,8447738696782005338 ,-5419007035803417955 ,-3670551735975720355 ,8798780857738740428 ,-3250177601747007616 ,5675151180751652376 ,-2697432896628175714 ,-7687783509549954669 ,-5136078205498211483 ,-4926045160250914654 ,-8571353921568421471 ,74333126425919794 ,-3701928245546104467 ,-1470612377725481600 ,63568208115027674 ,-3855818844611196871 ,-1549401731928077069 ,6607326006274445243 ,-3409905177893517435 ,-4047620574037522040 ,-6028801309039178818 ,-4816093545688054940 ,-8948571241806803029 ,-767901231431925748 ,-1305812131229776003 ,7349680201543011508 ,5741673902032046566 ,-559250736860740218 ,8714001987609969515 ,-1375055386285553477 ,-8329734772560081188 ,396850363561558173 ,8311445633615743609 ,-6464250796852884438";
        String[] flowIdArr = flowIds.split(",");
        StringBuilder dateCreateStr = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> dates = new ArrayList<Date>();
        for (int i = 0; i < flowIdArr.length; i++)
        {
            // List<Date>
            String flowId = flowIdArr[i];
            String backResult = oaService.getOpinions(flowId);
            backResult = backResult.substring(0, backResult.indexOf("<?xml"));
            Map<?, ?> rebackMap = JsonUtil.parseToObject(backResult, Map.class);
            List<?> tData = (List<?>)rebackMap.get("TData");
            for (int index = 0; index < tData.size(); index++)
            {
                Map<?, ?> data = (Map)tData.get(index);
                String date = (String)data.get("create_date");
                Date d = format.parse(date.substring(0, 19));
                dates.add(d);
            }
            Collections.sort(dates);
            dateCreateStr.append(format1.format(dates.get(dates.size() - 1)) + ",");
            dates.removeAll(dates);
        }
        String returnDates = dateCreateStr.subSequence(0, dateCreateStr.length() - 1).toString();
        
        System.out.println(returnDates);
    }
    
    /**
     * 
    * OA审核后调用接口-修改门店撤销审核状态
     * @param queryParam 
    * @return
     * @throws Exception 
     */
    public ResultData<String> updateCancelState(Map<String, Object> queryParam) throws Exception
    {
        ResultData<String> resultData = new ResultData<>();
        //更新撤销记录中状态
       int count = achievementCancelMapper.updateCancelState(queryParam);
       if(count < 1){
           resultData.setFail("更新撤销数据失败！queryParam = " + queryParam.toString());
           return resultData;
       }
       
        //更新合同门店关系表中的状态
        String state = (String)queryParam.get("approveState");
        if(state.equals("17303")){
            queryParam.put("storeApproveState", DictionaryConstants.STORESTATE_ISCANCEL_ISNOTCANCEL);
        }else{
            queryParam.put("storeApproveState", DictionaryConstants.STORESTATE_ISCANCEL_ISCANCEL);
        }
        
        int ucount = contractStoreMapper.updateIsCancelState(queryParam);
        if(ucount < 1){
            resultData.setFail("更新撤销数据失败！queryParam = " + queryParam.toString());
            return resultData;
        }
        
        return resultData;
    }
}
