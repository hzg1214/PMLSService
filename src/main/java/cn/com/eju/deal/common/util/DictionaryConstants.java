package cn.com.eju.deal.common.util;

/**
 * 数据字典对应的常量
 *
 * @author (li_xiaodong)
 * @date 2016年4月7日 下午6:43:04
 */
public interface DictionaryConstants {

    /**
     * 客户状态--已签约
     */
    int DIC_CODE_COMPANY_STATUS_Y = 12501;

    /**
     * 客户状态--未签约
     */
    int DIC_CODE_COMPANY_STATUS_N = 12502;

    /**
     * 门店状态--未锁定
     */
    int DIC_CODE_STORE_STATUS_N = 12601;

    /**
     * 门店状态--已锁定
     */
    int DIC_CODE_STORE_STATUS_Y = 12602;

    // 合同状态-待提交审核--> 草签
    Integer CONTRACT_STATUS_PENDING = 10401;

    // 合同状态-审核中
    Integer CONTRACT_STATUS_AUDITING = 10402;

    // 合同状态-审核通过
    Integer CONTRACT_STATUS_AUDIT_PASS = 10403;

    // 合同状态-审核未通过
    Integer CONTRACT_STATUS_AUDIT_NO_PASS = 10404;

    // 合同状态-作废
    Integer CONTRACT_STATUS_CANCEL = 10405;

    // 合同状态-终止
    Integer CONTRACT_STATUS_STOP = 10406;

    // 合同类型-A版
    Integer CONTRACT_TPYE_A = 10301;

    // 合同类型-B版
    Integer CONTRACT_TPYE_B = 10302;

    /**
     * 合同类型--C版本
     */
    Integer CONTRACT_TYPE_C = 10303;

    /**
     * 合同类型--A转B版本
     */
    Integer CONTRACT_TYPE_A_2_B = 10304;

    /**
     * 合同类型--D版
     */
    Integer CONTRACT_TYPE_D = 10306;
    /**
     * 合同类型--授牌
     */
    Integer CONTRACT_TYPE_SP = 10307;

    /**
     * 合作模式
     */
    int COOP_TYPE = 109;

    /**
     * 合作模式--A
     */
    int COOP_TYPE_A = 10901;

    /**
     * 合作模式--B
     */
    int COOP_TYPE_B = 10902;

    /**
     * 合作模式--A转B
     */
    int COOP_TYPE_A_2_B = 10903;

    //新房联动中的字段

    /**
     * 销售状态
     */
    String SALES_STATUS = String.valueOf(144);

    /**
     * 结佣方式
     */
    String PAY_KBN = String.valueOf(146);

    /**
     * 认证类型
     */
    String AUTHENTICATION_KBN = String.valueOf(147);

    /**
     * 佣金方式
     */
    String COMMISSION_KBN = String.valueOf(148);

    /**
     * 佣金方式--百分比
     */
    int COMMISSION_PERCENTAGE = 1482;

    /**
     * 佣金方式--固定值
     */
    int COMMISSION_YUAN = 1481;

    /**
     * 销售方式
     */
    String SALE_KBN = String.valueOf(149);

    /**
     * 报备方式
     */
    String REPORT_KBN = String.valueOf(150);

    /**
     * 朝向
     */
    String DIRECTION_KBN = String.valueOf(151);

    /**
     * 产权年限
     */
    String OWNYEAR_KBN = String.valueOf(153);

    /**
     * 装修情况
     */
    String DECORATION_KBN = String.valueOf(154);

    /**
     * 建筑类型
     */
    String TYPE_KBN = String.valueOf(155);

    /**
     * 供暖方式
     */
    String HEAT_KBN = String.valueOf(156);

    /**
     * 水电燃气
     */
    String HYDROPOWERGAS_KBN = String.valueOf(157);

    /**
     * 合作方
     */
    String PARTNER = String.valueOf(128);

    /**
     * 物业类型
     */
    String MGT_KBN = String.valueOf(134);

    //楼盘审核状态
    /**
     * 楼盘审核状态-未审核
     */
    int ESTATE_AUDIT_NEED = 12901;
    /**
     * 楼盘审核状态-不通过
     */
    int ESTATE_AUDIT_NO_PASS = 12902;
    /**
     * 楼盘审核状态-通过
     */
    int ESTATE_AUDIT_PASS = 12903;
    /**
     * 楼盘审核状态-未提交
     */
    int ESTATE_AUDIT_NO_PENDING = 12904;
    //楼盘发布状态
    /**
     * 楼盘发布状态-已发布
     */
    int ESTATE_PUBLISH_YES = 13001;
    /**
     * 楼盘发布状态-未发布
     */
    int ESTATE_PUBLISH_NO = 13002;

    //楼盘图片
    /**
     * 楼盘效果图
     */
    int ESTATE_DESIGN_IMG = 15901;

    /**
     * 楼盘样板间
     */
    int ESTATE_TEMPLATE_IMG = 15902;

    /**
     * 楼盘地理位置
     */
    int ESTATE_MAP_IMG = 15903;

    /**
     * 楼盘区域规划
     */
    int ESTATE_DISTRICT_IMG = 15904;

    /**
     * 楼盘实景图
     */
    int ESTATE_REAL_IMG = 15905;

    /**
     * 楼盘户型图
     */
    int HOUSE_TYPE_IMG = 15906;

    /**
     * 楼盘样板间
     */
    int HOUSE_TEMPLATE_IMG = 15907;

    /**
     * 带看奖励
     */
    int RELATION_REWARD = 14101;
    /**
     * 认筹奖励
     */
    int PLEDGE_REWARD = 14102;
    /**
     * 大定(认购)奖励
     */
    int SUBSCRIBED_REWARD = 14103;
    /**
     * 成销(成交)奖励
     */
    int BARGAIN_REWARD = 14104;

    /**
     * 门店合同状态-正常
     */
    Integer STORESTATE_NORMAL = 0;

    /**
     * 门店合同状态-变更中
     */
    Integer STORESTATE_CHANGE = 1;

    /**
     * 门店合同状态-终止
     */
    Integer STORESTATE_END = 2;


    /**
     * 合同保证金到账状态-未到账
     */
    Integer DEPOSITFEESTATE_INIT = 17501;

    /**
     * 合同保证金到账状态-部分到账
     */
    Integer DEPOSITFEESTATE_PART = 17502;

    /**
     * 合同保证金到账状态-已到账
     */
    Integer DEPOSITFEESTATE_COMPLETE = 17503;

    /**
     * 业绩类型 -门店业绩:17901
     */
    String ACHIEVETYPE_STORE = "17901";

    /**
     * 业绩类型 -新房联动业绩:17902
     */
    String ACHIEVETYPE_LINK = "17902";

    /**
     * 业绩类型 -交易业绩:17903
     */
    String ACHIEVETYPE_EX = "17903";
    /**
     * 业绩类型--公盘业绩：17904
     */
    String ACHIEVETYPE_GP = "17914";
    /**
     * 业绩类型--初始会员业绩：17904
     */
    String ACHIEVETYPE_CS = "17915";

    /**
     * 业绩类型--其他收入业绩：17916
     */
    String ACHIEVETYPE_QT = "17916";

    /**
     * 门店合同状态-业绩撤销-已撤销
     */
    String STORESTATE_ISCANCEL_ISCANCEL = String.valueOf(17203);

    /**
     * 门店合同状态-业绩撤销-撤销中
     */
    String STORESTATE_ISCANCEL_ISCANCELLING = String.valueOf(17202);

    /**
     * 门店合同状态-业绩撤销-未撤销
     */
    String STORESTATE_ISCANCEL_ISNOTCANCEL = String.valueOf(17201);

    /**
     * 门店合同状态-保证金退款-未退款
     */
    String STORESTATE_REFUNDSTATE_ISNOTREFUND = String.valueOf(17801);

    /**
     * 门店合同状态-保证金退款-部分退款
     */
    String STORESTATE_REFUNDSTATE_PARTREFUND = String.valueOf(17802);

    /**
     * 门店合同状态-保证金退款-已退款
     */
    String STORESTATE_REFUNDSTATE_ISREFUNDED = String.valueOf(17803);

    /**
     * 合同状态-门店业绩撤销审核-正常
     */
    String CONTRACT_ISCANCEL_ISNOTCANCEL = String.valueOf(17301);

    /**
     * 合同状态-门店业绩撤销审核-撤销中
     */
    String CONTRACT_ISCANCEL_ISCANCELLING = String.valueOf(17302);

    /**
     * 合同状态-门店业绩撤销审核-撤销失败
     */
    String CONTRACT_ISCANCEL_CANCELFAIL = String.valueOf(17303);

    /**
     * 合同状态-门店业绩撤销审核-已撤销
     */
    String CONTRACT_ISCANCEL_ISCANCELLED = String.valueOf(17304);


//Add 2017/4/7 cning start------->
    /**
     * 门店待续签
     */
    int RENEWFLAG_TYPE = 183;

    /**
     * 门店待续签_正常
     */
    int RENEWFLAG_TYPE_OK = 18301;

    /**
     * 门店待续签_待续签
     */
    int RENEWFLAG_TYPE_DX = 18302;

    /**
     * 门店是否无需续签
     */
    int NEEDEDRENEW_TYPE = 185;

    /**
     * 门店是否无需续签 _需要续签
     */
    int NEEDEDRENEW_TYPE_YXQ = 18501;

    /**
     * 门店是否无需续签 _不要续签
     */
    int NEEDEDRENEW_TYPE_NXQ = 18502;

    /**
     * 合同类别(新签，续签)
     */
    int OriginalContractdistinction_TYPE = 186;

    /**
     * 合同类别(新签，续签)_新签
     */
    int OriginalContractdistinction_TYPE_N = 18601;

    /**
     * 合同类别(新签，续签)_续签
     */
    int OriginalContractdistinction_TYPE_R = 18602;

    /**
     * 合同有效标识
     */
    int VALID_TYPE = 184;

    /**
     * 合同有效标识 _待生效
     */
    int VALID_TYPE_DSX = 18401;

    /**
     * 合同有效标识 _生效
     */
    int VALID_TYPE_SX = 18402;

    /**
     * 合同有效标识 _过期
     */
    int VALID_TYPE_GQ = 18403;
//Add 2017/4/7 cning end<-------

//Add By GuoPengFei 2017/05/26 合规性 start

    /**
     * 变更类型
     */
    int Contract_ChangeType = 170;

    /**
     * 终止
     */
    int Contract_ChangeType_END = 17001;

    /**
     * 乙转甲
     */
    int Contract_ChangeType_B2A = 17002;

    /**
     * 三方变更
     */
    int Contract_ChangeType_Third = 17003;
    /**
     * 门店迁址
     */
    int Contract_ChangeType_Relocation = 17004;


    /**
     * 门店资质等级
     */
    int Store_QualityGrade = 199;


    /**
     * 甲类门店
     */
    int Store_QualityGrade_A = 19901;

    /**
     * 乙类门店
     */
    int Store_QualityGrade_B = 19902;


    /**
     * 乙类门店等级
     */
    int Store_QualityGrade_AType = 200;

    /**
     * 门店（乙1）
     */
    int Store_QualityGrade_AType_1 = 20001;


    /**
     * 门店（乙2）
     */
    int Store_QualityGrade_AType_2 = 20002;

    /**
     * 门店（乙3）
     */
    int Store_QualityGrade_AType_3 = 20003;

    /**
     * 门店（乙4）
     */
    int Store_QualityGrade_AType_4 = 20004;

    /**
     * 门店乙类转甲类 类型
     */
    int ChangeType_B2A_Type = 201;

    /**
     * 公司经营范围变更
     */
    int ChangeType_B2A_Type_Company = 20101;

    /**
     * 公司注册地址变更
     */
    int ChangeType_B2A_Type_CompanyAdress = 20102;

    /**
     * 门店地址变更
     */
    int ChangeType_B2A_Type_StoreAdress = 20103;

    /**
     * 签约主体变更
     */
    int ChangeType_B2A_Type_Main = 20104;

    /**
     * 是否乙类转甲类
     */
    int Contract_TypeB2A = 202;

    /**
     * 是乙类转甲类
     */
    int Contract_TypeB2A_YES = 20201;

    /**
     * 不是乙类转甲类
     */
    int Contract_TypeB2A_NO = 20202;
//Add By GuoPengFei 2017/05/26 合规性 end

//Add By WangKanLin 2017/08/03 项目状态 start


    /**
     * 项目状态
     */
    int Project_Status = 203;

    /**
     * 项目状态：跟单
     */
    int Project_Status_Start = 20301;

    /**
     * 项目状态：签约
     */
    int Project_Status_Sign = 20302;

    /**
     * 项目状态：结案
     */
    int Project_Status_End = 20303;

    /**
     * 项目状态：取消跟单
     */
    int Project_Status_Start_Cancel = 20304;

//Add By WangKanLin 2017/08/03 项目状态 end


//Add By WangKanLin 2017/08/07 项目变更类型 start

    /**
     * 项目变更
     */
    int Project_Change = 205;

    /**
     * 项目变更：合作模式变更
     */
    int Project_Change_Cooperation_Mode = 20501;

    /**
     * 项目变更：项目状态变更
     */
    int Project_Change_Status = 20502;

    /**
     * 项目变更：上架
     */
    int Project_Change_Open = 20503;

    /**
     * 项目变更：下架
     */
    int Project_Change_Close = 20504;

//Add By WangKanLin 2017/08/07 项目变更类型 end

    //问卷状态

    //草签
    String Wj_Status_Initition = "24701";

    //未启用
    String Wj_Status_Not_Enabled = "24702";

    //已启用
    String Wj_Status_Enabled = "24703";

//                             问卷状态类型 end
    
    //问卷-业务类型
    String Wj_wjtmflType_pp = "25101";//	品牌服务	
    
    String Wj_wjtmflType_px = "25102";//	培训服务	
    
    String Wj_wjtmflType_zp = "25103";//	招聘服务	
    
    String Wj_wjtmflType_xt = "25104";//	系统服务	
    
    String Wj_wjtmflType_jy = "25105";//	交易服务	
    
    String Wj_wjtmflType_gp = "25106";//	公盘业务	
    
    String Wj_wjtmflType_ld = "25107";//	联动业务	
    
    String Wj_wjtmflType_qt = "25108";//	其他	
    
    //问卷-题型
    String Wj_wjtmType_dx = "25201";//	单选题	
    
    String Wj_wjtmType_dxs = "25202";//	多选题	
    
    String Wj_wjtmType_wd = "25203";//	问答题

    String WJDCSTATUS_CJ = "25001";//	已创建

    String WJDCSTATUS_WC = "25002";//	已完成
    
}
