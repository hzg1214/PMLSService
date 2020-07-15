package cn.com.eju.deal.common.util;

import cn.com.eju.deal.core.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eju on 2018/1/19.
 */
public class BaseUtils {

    /**
     * 将一个带分割符的字符串组装成另一个带分割符号的字符串
     * @param split 转换前的字符串
     * @param regex 转换前的分隔符
     * @param toRegex 转换后的分隔符
     * @param needMark 分隔符之间的字符串是否需要单引号 true需要
     * @return
     */
    public static String parseSplitToString(String split,String regex,String toRegex,boolean needMark){

        String returnValue = "";

        if(StringUtil.isEmpty(split) || StringUtil.isEmpty(regex) || StringUtil.isEmpty(toRegex)){
            return returnValue;
        }

        String[] beforeArray = split.split(regex);
        for(String s : beforeArray){
            if(needMark){
                returnValue += "'"+ s + "',";
            }else{
                returnValue += s + ",";
            }
        }

        returnValue = returnValue.substring(0,returnValue.length()-1);

        return returnValue;
    }

    public static final List<String> changeArrayToList(String Array, String regex){
        List<String> list = new ArrayList<>();

        String newArray[] = Array.split(regex);
        for(String s : newArray){
            if(StringUtil.isNotEmpty(s)){
                list.add(s);
            }
        }

        if(list.size() <= 0){
            list = null;
        }

        return list;
    }
}
