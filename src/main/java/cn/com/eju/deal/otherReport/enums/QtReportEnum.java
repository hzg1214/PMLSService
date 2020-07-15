package cn.com.eju.deal.otherReport.enums;


public enum QtReportEnum {
	DK_CODE("27201","带看奖"),
	CJ_CODE("27202","成交奖"),
    GG_CODE("27203","广告咨询费"),
    KK_CODE("27204","拓客服务费"),
    QT_CODE("27205","其他")
	;
	
    private QtReportEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    private String code;
    private String name;
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public static String getNameByCode(String code){
        if(code != null && code!=""){
            for (QtReportEnum obj : QtReportEnum.values()) {
                if(code == obj.getCode() || code.equals(obj.getCode())){
                    return obj.getName();
                }
            }
        }
        return ""+code;
    }
}
