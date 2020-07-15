package cn.com.eju.deal.reporttask.constant;

public class ExcelTaskConstant {
	public static enum ExcelTask_ExportType{
        ExpandDetail("拓展明细"),
        ExpandCollect("拓展汇总-时间段"),
        ExpandCollectMonth("拓展汇总-日"),
        ExpandCollectWeek("拓展汇总-周"),
		LinkageDetail("联动明细"),
		LinkageCollect("联动汇总-时间段"),
		LinkCollecMonth("联动汇总-日"),
		LinkCollecWeek("联动汇总-周"),
		ExchangeDetail("交易明细"),
		ExchangeCollect("交易汇总-时间段"),
		ExchangeCollectMonth("交易汇总-日"),
		ExchangeCollectWeek("交易汇总-周");
		private String cnName;
		ExcelTask_ExportType(String name){
			this.cnName=name;
		}
		public String getCode(){
			return this.name();
		}
		public String getCnName(){
			return this.cnName;
		}
		public static String getCnName(String code){
			for(ExcelTask_ExportType item:ExcelTask_ExportType.values()){
				if(item.getCode().equals(code))
				{
					return item.getCnName();
				}
			}
			return code;
		}
		public String toString(){
			return "code:"+this.name()+",cnName:"+this.cnName;
		}
	}
	
	public static final String getStatusZh(int status){
		String result = "";
		 if(status==1){
			 result = "下载中";
		 }else if(status==2){
			 result = "已完成";
		 }else{
			 result = "已删除";
		 }
		return result;
	}
}
