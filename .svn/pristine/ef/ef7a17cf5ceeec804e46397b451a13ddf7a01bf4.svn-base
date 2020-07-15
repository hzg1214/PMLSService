/**
 * Copyright (c) 2018, www.ehousechina.com.
 * Project Name:PMLSService
 * File Name:XmlTransferUtil.java
 * Package Name:cn.com.eju.deal.common.util
 * Date:2018年2月2日上午10:44:40
 */
package cn.com.eju.deal.common.util;

import cn.com.eju.deal.core.util.StringUtil;

/**
 * ClassName: XmlTransferUtil <br/>
 * Description: xml文件特殊字符转换 <br/>
 * &lt; < 小于号 
 * &amp; & 和 
 * 
 * @author yinkun
 * @date: 2018年2月2日 上午10:44:40 <br/>
 * @version V1.0
 */
public final class XmlTransferUtil {
    
    private XmlTransferUtil(){
        
    }
    
    public static String transfer(String source) {
        
        String target = source;
        
        if(StringUtil.isEmpty(target)) {
            return target;
        }
        
        if(target.indexOf("<") != -1) {
            target = target.replaceAll("<", "&lt;");
        }
       
        if(target.indexOf("&") != -1) {
            target = target.replaceAll("&", "&amp;");
        }
        
        return target;
    }
}

