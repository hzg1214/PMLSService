package cn.com.eju.deal.reportbase.util;


public interface Constants {

	public String DATE = "yyyy-MM-dd";

	public String DATE_TIME = "yyyy-MM-dd hh:mm:ss";
	//定时器组名称
	public static String  JOB_GROUP_AUTO="auto";
	
	public static String EXECL_SUFFIX=".xlsx";
	public static String EXECL_SUFFIX_PDF=".pdf";
	
	//增加
	public static String SCHEDULE_ADD="1";
	//编辑
	public static String SCHEDULE_EDIT="2";
	
	public static String OUTPUTDOC="outputDoc";
	
	public static String DELETE_DELFLAY="Y";
	
	//定时器状态 0 执行
	public static Integer JOB_STATE_ZERO=0;
	//定时器状态 1 暂停
	public static Integer JOB_STATE_ONE=1;
	
	//PDF生成
	public static Integer IS_PDF_ONE=1;
	//PDF不生成
	public static Integer IS_PDF_ZERO=0;

	String COOPERATION_E_VISA ="cooperationEVisa";
}
