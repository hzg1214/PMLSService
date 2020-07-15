package cn.com.eju.deal.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AmountUtils {
    /**
     * 判断金额是否合法
     * @param str
     * @return
     */
    public static boolean isValidAmount(String str){
        Pattern pattern= Pattern.compile("^((-?[1-9]{1}\\d*)|(-?[0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match=pattern.matcher(str);
        if(match.matches()==false){
            return false;
        }
        else{
            return true;
        }
    }
}
