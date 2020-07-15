package cn.com.eju.deal.student.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.cashbill.dao.CashBillCompanyMapper;
import cn.com.eju.deal.cashbill.model.CashBillCompany;
import cn.com.eju.deal.cashbill.model.OaCashBillReturn;
import cn.com.eju.deal.cashbill.service.CashBillService;
import cn.com.eju.deal.student.dao.StudentTestMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eju on 2019/12/25.
 */
@Service("studentTestService")
public class StudentTestService extends BaseService{

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private StudentTestMapper studentTestMapper;
    
    @Resource
    private CashBillService cashBillService;
    
    @Resource
    private CashBillCompanyMapper cashBillCompanyMapper;

    public int oaFrameContract(Map<String, Object> map) {
        Integer oaType = Integer.valueOf(map.get("oaType").toString());
        if(oaType==1){
            map.put("approveStatus", 10403);
        }else{
            map.put("approveStatus", 10404);
        }
        int count = studentTestMapper.oaFrameContract(map);
        return count;
    }

    public int oaProject(Map<String, Object> map) {
        int count = studentTestMapper.oaProject(map);
        return count;
    }

    public int oaSignOrApproach(Map<String, Object> map) {
        Integer projectType = Integer.valueOf(map.get("projectType").toString());
        String newId = studentTestMapper.getNewId();
        int count = 0;
        map.put("newId",newId);
        if(projectType==1){
            map.put("oaReturnType",20803);
            count = studentTestMapper.oaSign(map);
        }else{
            map.put("oaReturnType",20809);
            count = studentTestMapper.oaApproach(map);
        }
        return count;
    }

    public int oaDistribution(Map<String, Object> map) {
        Integer oaType = Integer.valueOf(map.get("oaType").toString());
        int count = 0;
        if(oaType==1){
            map.put("approveStatus", 1);
        }else{
            map.put("approveStatus", 3);
        }
        map.put("tableName","PMLS_Cooperation");
        count = studentTestMapper.update(map);
        if(count>0){
            count = studentTestMapper.oaDistribution(map);
        }
        return count;
    }

    public int oaStatement(Map<String, Object> map) {
        Integer oaType = Integer.valueOf(map.get("oaType").toString());
        String newId = studentTestMapper.getNewId();
        int count = 0;
        if(oaType==1){
            map.put("approveStatus", 1);
        }else{
            map.put("approveStatus", 3);
        }
        map.put("tableName","PMLS_JsStatement");
        count = studentTestMapper.update(map);
        map.put("newId",newId);
        if(count>0){
            count = studentTestMapper.oaStatement(map);
        }
        return count;
    }

    public int oaAgreement(Map<String, Object> map) {
        Integer oaType = Integer.valueOf(map.get("oaType").toString());
        String newId = studentTestMapper.getNewId();
        int count = 0;
        if(oaType==1){
            map.put("approveStatus", 1);
        }else{
            map.put("approveStatus", 3);
        }
        map.put("tableName","PMLS_Triple");
        count = studentTestMapper.update(map);
        map.put("newId",newId);
        if(count>0){
            count = studentTestMapper.oaAgreement(map);
        }
        return count;
    }

    public int oaReceivables(Map<String, Object> map) {
        Integer oaType = Integer.valueOf(map.get("oaType").toString());
        String newId = studentTestMapper.getNewId();
        int count = 0;
        if(oaType==1){
            map.put("approveStatus", 1);
        }else{
            map.put("approveStatus", 3);
        }
        map.put("tableName","PMLS_JY_QkBill");
        count = studentTestMapper.update(map);
        map.put("newId",newId);
        if(count>0){
            count = studentTestMapper.oaReceivables(map);
        }
        return count;
    }

    public int oaExpenditure(Map<String, Object> map) {
        Integer oaType = Integer.valueOf(map.get("oaType").toString());
        String newId = studentTestMapper.getNewId();
        int count = 0;
        if(oaType==1){
            map.put("approveStatus", 1);
        }else{
            map.put("approveStatus", 3);
        }
        map.put("tableName","PMLS_JY_ZCBill");
        count = studentTestMapper.update(map);
        map.put("newId",newId);
        if(count>0){
            count = studentTestMapper.oaExpenditure(map);
        }
        return count;
    }

    public int oaQk(Map<String, Object> map) {
        Integer oaType = Integer.valueOf(map.get("oaType").toString());
        String oaNo = map.get("oaNo").toString();
        //check  不能重复审核
        int count = 0;
        String cashBillNo="";
        CashBillCompany cashBillCompany = studentTestMapper.selCashBillCompanyByOaNo(map);
        if(cashBillCompany!=null && !StringUtils.isEmpty(cashBillCompany)) {
        	Integer submitOaStatus = cashBillCompany.getSubmitOaStatus();
        	cashBillNo = cashBillCompany.getCashBillNo();
        	if(submitOaStatus!=null && submitOaStatus.intValue()!=21202) {
        		count = 10;
        		return count;
        	}
        }
        if(oaType==1){
            map.put("approveStatus", 25402);
            map.put("submitOaStatus",21203);
            map.put("approveStatusTwo",1);
        }else{
            map.put("approveStatus", 25401);
            map.put("submitOaStatus",21204);
            map.put("approveStatusTwo",0);
        }
        count = studentTestMapper.updateQk(map);
        if(count>0){
            count = studentTestMapper.oaCashBill(map);
            if(count>0){
                Integer oaRetId = studentTestMapper.queryCashBillReturnId(map);
                map.put("oaRetId",oaRetId);
                count = studentTestMapper.oaCashBillProject(map);
            }
            //调用定时任务
            try {
				cashBillService.handleCashBill();
				if(oaType==1){//审核通过
					
					Map<String,Object> execMap = new HashMap<>();
					execMap.put("cashBillNo",cashBillNo);
					execMap.put("returnCode","");
					execMap.put("returnMsg","");
					cashBillCompanyMapper.execQkZeroSync(execMap);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("批量请款-调用定时任务失败。入参:"+map);
			}
        }
        return count;
    }
}
