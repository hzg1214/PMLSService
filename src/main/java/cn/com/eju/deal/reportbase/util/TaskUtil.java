package cn.com.eju.deal.reportbase.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.dubbo.common.utils.CollectionUtils;

import cn.com.eju.deal.core.util.StringUtil;

public class TaskUtil {
    private static final String splitOne = "[=]";
    private static final String splitTwo = "{&}";
    private static final String splitThree = "<^>";

    /**
     * 线程池
     */
    public static final ExecutorService EXECUTORS = Executors.newFixedThreadPool(20);

    /**
     * 将Map转成字符串
     * @param map
     * @return
     */
    public static String handlerParam(Map<String, Object> map){
    	String result = "";
    	
    	for(Entry<String, Object> entry : map.entrySet()){
    		Object value = entry.getValue();
    		result += entry.getKey()+splitOne;
    		if(value instanceof List ) {
    			if(!CollectionUtils.isEmpty((List)value)) {
        			List list = (List)value;
    				String str = "";
    				for (int i = 0; i < list.size(); i++) {
    					str += list.get(i)+",";
					}
    				if(!StringUtils.isEmpty(str)){
    					str = str.substring(0,str.length()-1);
    				}
    				result += str+splitThree+"array";
    			}else{
    				result += ""+splitThree+"array";
    			}
    		} else {
    			result += entry.getValue();
			}
    		result += splitTwo;
    	}
    	if(!StringUtil.isEmpty(result)){
    		result = result.substring(0,result.length()-splitTwo.length());
    	}
    	return result;
    }
    
    /**
     * 将字符串转成Map
     * @param param
     * @return
     */
    public static Map<String, Object> handlerParam(String param ){
    	Map<String, Object> map = new HashMap<String, Object>();
    	if(!StringUtil.isEmpty(param)){
    		String[] array = StringUtils.split(param,splitTwo);
    		if (array!=null && array.length>0) {
				for (int i = 0; i < array.length; i++) {
					String obj = array[i];
					if(!StringUtil.isEmpty(obj)){
						String[] arr = StringUtils.split(obj,splitOne); 
						
						if(arr.length==2 && !StringUtils.isEmpty(arr[1])){
							String[] arrOne = StringUtils.split(arr[1],splitThree);
							if(arrOne.length==2){
								List<Object> list = new ArrayList<>();
								String [] id = arrOne[0].split(",");
								for (int j = 0; j < id.length; j++) {
									String str = id[j];
									if(!StringUtils.isEmpty(str)) {
										list.add(str.trim());
									}
								}
								map.put(arr[0], list);
							}else if(arrOne[0].equals("array")){
								map.put(arr[0], new ArrayList<>());
							}else{
								map.put(arr[0], arr[1]);
							}
						}else{
							map.put(arr[0], "");
						}
						
					}
				}
			}
    	}
    	return map;
    }
    
    public static void main(String[] args) {
    	/*Map<String, Object> map = new HashMap<String, Object>();
    	List<Integer> list = new ArrayList<>();
    	list.add(1);
    	map.put("test4", null);
    	map.put("test", "20161212");
    	map.put("test2", new ArrayList<>());
    	map.put("test3", list);
    	String result = handlerParam(map);
    	System.out.println(result);*/
    	
    	String result = "test[=]Fri Jan 13 10:54:41 CST 2017{&}test4[=]null{&}test2[=]{&}test3[=]1<^>array";

    	Map<String, Object> map = handlerParam(result);
    	System.out.println(map);
	}
}
