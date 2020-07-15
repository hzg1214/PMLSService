package cn.com.eju.deal.common.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpClientUtil {

	private static Logger logger = LoggerFactory
			.getLogger(HttpClientUtil.class);
	
	private static RestTemplate restTemplate = new RestTemplate();

	private static String errMsg = "{\"returnCode\":-1,\"returnMsg\":\"调用接口异常\"}";
    private static String SUCCESS = "{\"returnCode\":200,\"returnMsg\":\"调用接口成功\"}";
    
    /** 
     * 发送HttpPost请求 
     *  
     * @param strURL 
     *            服务地址 
     * @param params 
     *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/> 
     * @return 成功:返回json字符串<br/> 
     */  
    public static String jsonPost(String strURL, String params) {  
        
        try {  
            URL url = new URL(strURL);// 创建连接  
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
            connection.setRequestMethod("POST"); // 设置请求方式  
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式  
            connection.connect();  
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码  
            out.append(params);  
            out.flush();  
            out.close();  
            // 读取响应  如果采用gzip压缩,getContentLength会一直返回-1
            /*int length = connection.getContentLength();// 获取长度  
            InputStream is = connection.getInputStream();  
            if (length != -1) {  
                byte[] data = new byte[length];  
                byte[] temp = new byte[512];  
                int readLen = 0;  
                int destPos = 0;  
                while ((readLen = is.read(temp)) > 0) {  
                    System.arraycopy(temp, 0, data, destPos, readLen);  
                    destPos += readLen;  
                }  
                String result = new String(data, "UTF-8"); // utf-8编码  
                
                return result;  
            }*/  
            InputStream is = connection.getInputStream();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String dataLine = null;
            StringBuilder strBuilder = new StringBuilder();
            while((dataLine = reader.readLine()) != null) {
                strBuilder.append(dataLine);
            }
            return strBuilder.toString();
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return errMsg; // 自定义错误信息  
    }

    /** 
     * 发送HttpPost请求 
     *  
     * @param strURL 
     *            服务地址 
     * @param params 
     *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/> 
     * @return 成功:返回json字符串<br/> 
     */  
    public static String jsonPut(String strURL, String params) {  
        
        try {  
            URL url = new URL(strURL);// 创建连接  
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
            connection.setRequestMethod("PUT"); // 设置请求方式  
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式  
            connection.connect();  
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码  
            out.append(params);  
            out.flush();  
            out.close();  
            // 读取响应  如果采用gzip压缩,getContentLength会一直返回-1
            /*int length = connection.getContentLength();// 获取长度  
            InputStream is = connection.getInputStream();  
            if (length != -1) {  
                byte[] data = new byte[length];  
                byte[] temp = new byte[512];  
                int readLen = 0;  
                int destPos = 0;  
                while ((readLen = is.read(temp)) > 0) {  
                    System.arraycopy(temp, 0, data, destPos, readLen);  
                    destPos += readLen;  
                }  
                String result = new String(data, "UTF-8"); // utf-8编码  
                
                return result;  
            }  */
            InputStream is = connection.getInputStream(); 
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String dataLine = null;
            StringBuilder strBuilder = new StringBuilder();
            while((dataLine = reader.readLine()) != null) {
                strBuilder.append(dataLine);
            }
            return strBuilder.toString();
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return errMsg; // 自定义错误信息  
    }
    
    public static String jsonPatch(String url, String param){
        CloseableHttpClient client = HttpClientBuilder.create().build();
        // 建立HTTPatch对象
        HttpPatch httpRequest = new HttpPatch(url);
        String responseJson = null;
        try{
            // 添加请求参数到请求对象
            StringEntity entity = new StringEntity(param,"UTF-8");
            httpRequest.setHeader("Accept", "application/json");
            httpRequest.setHeader("Content-Type", "application/json-patch+json");
            httpRequest.setEntity(entity);
            // 发送请求并等待响应
            HttpResponse response = client.execute(httpRequest);
            return EntityUtils.toString(response.getEntity(), "UTF-8");
            /*InputStream is = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String dataLine = null;
            StringBuilder strBuilder = new StringBuilder();
            while((dataLine = reader.readLine()) != null) {
                strBuilder.append(dataLine);
            }
            return strBuilder.toString();*/
        }
        catch (Exception e){
            responseJson = e.getMessage().toString();
            logger.error(responseJson);
        }
        return errMsg;
    }
    
    public static String jsonGet(String url,String param) {
        return restTemplate.getForObject(url, String.class, param);
    }
    
    
    /** 
     * 发送HttpPost请求 
     *  
     * @param strURL 
     *            服务地址 
     * @param params 
     *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/> 
     * @return 成功:返回json字符串<br/> 
     */  
    public static String jsonPostFangyou(String strURL, String params) {  
        
        try {  
            URL url = new URL(strURL);// 创建连接  
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
            connection.setRequestMethod("POST"); // 设置请求方式  
//            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
            connection.setRequestProperty("Accept", "application/vnd.fangyou.v1+json"); // 设置接收数据的格式 
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式  
            connection.connect();  
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码  
            out.append(params);  
            out.flush();  
            out.close();  
            // 读取响应  如果采用gzip压缩,getContentLength会一直返回-1
            /*int length = connection.getContentLength();// 获取长度  
            InputStream is = connection.getInputStream();  
            if (length != -1) {  
                byte[] data = new byte[length];  
                byte[] temp = new byte[512];  
                int readLen = 0;  
                int destPos = 0;  
                while ((readLen = is.read(temp)) > 0) {  
                    System.arraycopy(temp, 0, data, destPos, readLen);  
                    destPos += readLen;  
                }  
                String result = new String(data, "UTF-8"); // utf-8编码  
                
                return result;  
            }*/
            /*System.out.println(connection.getResponseCode());

            InputStream is = connection.getInputStream();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String dataLine = null;
            StringBuilder strBuilder = new StringBuilder();
            while((dataLine = reader.readLine()) != null) {
                strBuilder.append(dataLine);
            }*/
            if(200 ==connection.getResponseCode()){
                return SUCCESS;
            }else{
                return errMsg; // 自定义错误信息
            }
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return errMsg; // 自定义错误信息  
    }
    

    
    public static String httpPostYF(String urlStr,String paramMap){
        String output="";
        String readMsg = "";
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String input = "formVals="+paramMap;
            input = input.replaceAll("&","%FFFF%");
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes("UTF-8"));
            os.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), "UTF-8"));
            while ((output = br.readLine()) != null) {
                readMsg = output;
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return readMsg;
    }
    
	public static void main(String[] args) {
		try{
            String json=jsonPostHttps("https://zhipin.fangyou.com/ejuemploy/storeUserController/crmAddStoreUser?storeNo=MD036334&telephone=18019925393&userName=333","");
//            String json=jsonPostHttps("https://zhipin.fangyou.com/ejuemploy/storeUserController/crmAddStoreUser","{\"storeNo\":\"MD036334\",\"telephone\":\"18019925395\",\"userName\":\"吴小娟\"}");
// String url = "http://172.28.28.64:8088/service/companies";
//		    String json = jsonGet(url,"202939");
			System.out.println(json);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * 发送非json格式，接收json格式
     * @param strURL
     * @param params
     * @return
     */
    public static String jsonPostHttps(String strURL, String params) {

        try {
            URL url = new URL(strURL);// 创建连接
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // 设置发送数据的格式
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();
            // 读取响应  如果采用gzip压缩,getContentLength会一直返回-1
            /*int length = connection.getContentLength();// 获取长度
            InputStream is = connection.getInputStream();
            if (length != -1) {
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = is.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                String result = new String(data, "UTF-8"); // utf-8编码

                return result;
            }*/
            InputStream is = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String dataLine = null;
            StringBuilder strBuilder = new StringBuilder();
            while((dataLine = reader.readLine()) != null) {
                strBuilder.append(dataLine);
            }
            return strBuilder.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return errMsg; // 自定义错误信息
    }
    
    
    /** 
     * 发送HttpPost请求 
     *  
     * @param strURL 
     *            服务地址 
     * @param params 
     *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/> 
     * @return 成功:返回json字符串<br/> 
     */  
    public static String jsonPostUtf8(String strURL, String params) {  
        
        try {  
            URL url = new URL(strURL);// 创建连接  
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
            connection.setRequestMethod("POST"); // 设置请求方式  
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8"); // 设置发送数据的格式  
            connection.connect();  
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码  
            out.append(params);  
            out.flush();  
            out.close();  
            // 读取响应  如果采用gzip压缩,getContentLength会一直返回-1
            /*int length = connection.getContentLength();// 获取长度  
            InputStream is = connection.getInputStream();  
            if (length != -1) {  
                byte[] data = new byte[length];  
                byte[] temp = new byte[512];  
                int readLen = 0;  
                int destPos = 0;  
                while ((readLen = is.read(temp)) > 0) {  
                    System.arraycopy(temp, 0, data, destPos, readLen);  
                    destPos += readLen;  
                }  
                String result = new String(data, "UTF-8"); // utf-8编码  
                
                return result;  
            }*/  
            InputStream is = connection.getInputStream();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            String dataLine = null;
            StringBuilder strBuilder = new StringBuilder();
            while((dataLine = reader.readLine()) != null) {
                strBuilder.append(dataLine);
            }
            return strBuilder.toString();
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return errMsg; // 自定义错误信息  
    }


}
