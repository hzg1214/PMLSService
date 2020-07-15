package cn.com.eju.deal.reportbase.util;

import java.text.DecimalFormat;
import java.util.Map;

import com.alibaba.dubbo.common.utils.StringUtils;

import cn.com.eju.deal.core.util.StringUtil;

public class FormatStringUtil
{

    public final static String format(String param){
        if(!StringUtil.isEmpty(param) && !"-".equals(param)){
            try{
                DecimalFormat df = new DecimalFormat("#.##"); 
                double value= Double.parseDouble(param);
                param = df.format(value);
            }catch(Exception e){
                
            }
        }
        return param;
    }

	public static final String handlerDate(String cDate){
		String result = cDate;
		if(!StringUtils.isEmpty(cDate) ){
			if(cDate.indexOf("-")>-1){
				String[] array = cDate.split("-");
				String str1 = array[0];
				String firstStr = str1.substring(0,str1.length()-8);
				str1 = str1.substring(str1.length()-8,str1.length());
				String str2 = array[1];
	
		    	String bdate = DateUtil.format(DateUtil.parseDateFormat(str1,"yyyyMMdd"),"yyyy-MM-dd");
		    	String edate = DateUtil.format(DateUtil.parseDateFormat(str2,"yyyyMMdd"),"yyyy-MM-dd");
		    	result = firstStr+"（"+bdate+"至"+edate+"）";
			}else if(cDate.length()>7){
				result = DateUtil.format(DateUtil.parseDateFormat(cDate,"yyyyMMdd"),"yyyy-MM-dd");
			}
		}
		return result;
	}
	
	public static final String getExcelName(Map<String, Object> map,String fileName){
        String edate = map.get("inDate2") != null ? map.get("inDate2").toString() : null;
        String bdate = map.get("inDate1") != null ? map.get("inDate1").toString() : null;
        if(edate.indexOf("-")==-1){
	        edate = DateUtil.format(DateUtil.parseDateFormat(edate,"yyyyMMdd"),"yyyy-MM-dd");
	        bdate = DateUtil.format(DateUtil.parseDateFormat(bdate,"yyyyMMdd"),"yyyy-MM-dd");
        }
        String fileNameAll = "";
        fileNameAll = fileName+"("+bdate+"至"+edate+")"+ Constants.EXECL_SUFFIX;
        return fileNameAll;
	}
}
