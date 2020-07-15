package cn.com.eju.deal.common.util;

/**   
* 调用外部系统-请求的方法名
* @author (li_xiaodong)
* @date 2016年4月7日 下午6:43:04
*/
public interface FesbMethodConstants
{
    
    /**
    * OMS-创建业绩节点记录
    */
    String FESB_METHOD_CODE_PERFORM_RECORD = "OMS-performrecord";
    
    /**
    * OMS-批量创建业绩节点记录
    */
    String FESB_METHOD_CODE_PERFORM_RECORD_BATCH = "OMS-performrecord-batch";
    
    /**
     * OMS-更新业绩节点记录
     */
    String FESB_METHOD_CODE_PERFORM_RECORD_U = "OMS-performrecord-u";
    
    /**
     * OMS-批量更新业绩节点记录
     */
    String FESB_METHOD_CODE_PERFORM_RECORD_U_BATCH = "OMS-performrecord-u-batch";
    
    /**
     * OMS-新增合同流水
     */
    String FESB_METHOD_CODE_CONTRACT_FLOW = "OMS-contractflow";
    
    /**
     * OMS-批量新增合同流水
     */
    String FESB_METHOD_CODE_CONTRACT_FLOW_BATCH = "OMS-contractflow-batch";
    
    /**
     * OMS-批量更新保证金合同状态
     */
    String FESB_METHOD_CODE_CONTRACT_DEPOSIT_BATCH = "OMS-deposit-u-batch";
    
    /**
    * OMS新增装修
    */
    String FESB_METHOD_CODE_DEPOSIT_UPDATE = "OMS-decoration";
    
     /**
      * OMS保证金新增
      */
     String FESB_METHOD_CODE_STORE_QUERY = "OMS-deposit";
     
     /**
      * OMS开关账
      */
     String FESB_METHOD_CODE_SWITCH_QUERY = "OMS-queryswitch";
     
}
