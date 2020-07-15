package cn.com.eju.deal.open.controller;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.lang3.StringUtils;

import com.seeyon.client.CTPRestClient;
import com.seeyon.client.CTPServiceClientManager;

import cn.com.eju.deal.base.support.SystemParam;

public class UploadOAUtil {
    /**
     * OA附件上传
     *
     * @param absolutePath 上传附件的绝对路径
     * @return
     * @throws Exception
     */
    public static String oaAttachmentUpload(String fileUrl, String fileName, String senderLoginName,String tempFilePath) throws Exception {

        // 指定协议、IP和端口，获取ClientManager
        CTPServiceClientManager clientManager = CTPServiceClientManager
                .getInstance(SystemParam.getWebConfigValue("oaUrl"));
        
        // 取得REST动态客户机实例
        CTPRestClient client = clientManager.getRestClient();
        // 获取token认证
        String token = client.get("token/" + SystemParam.getWebConfigValue("oaUserName") + "/" + SystemParam.getWebConfigValue("oaPassword"), String.class, "text/plain");

        String attachmentId = oaUpload(SystemParam.getWebConfigValue("oaUrl"), token, senderLoginName, fileUrl, fileName,tempFilePath);

        return attachmentId;
    }
    
    public static String oaUpload(String oaUrl, String token, String senderLoginName, String fileUrl, String fileName,String tempFilePath)throws Exception{

        HttpURLConnection httpUrl = null;

        URL url = null;

        //上传方法
        String uploadMethod = "/seeyon/uploadService.do?method=processUploadService";

        //上传参数
        String uploadParam = "&senderLoginName=" + senderLoginName + "&token=" + token;

        //上传请求URL
        String uploadUrl = oaUrl + uploadMethod + uploadParam;

        //输出
        StringBuilder strBd = new StringBuilder();


        URL preUrl = null;

        HttpURLConnection hc = null;

        DataOutputStream dos = null;

        InputStream is = null;
        
        FileOutputStream tempFile = null;

        try {
            
            if(StringUtils.isNotEmpty(tempFilePath)) {
                tempFile = new FileOutputStream(tempFilePath+fileName);
            }

            url = new URL(fileUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            // 连接指定的资源
            httpUrl.connect();


            preUrl = new URL(uploadUrl);
            URLConnection uc = preUrl.openConnection();
            hc = (HttpURLConnection)uc;

            hc.setDoOutput(true);
            hc.setUseCaches(false);
            hc.setRequestProperty("contentType", "charset=utf-8");
            hc.setRequestMethod("POST");

            BufferedInputStream input = new BufferedInputStream(httpUrl.getInputStream());

            String BOUNDARY = "---------------------------7d4a6d158c9"; // 分隔符

            StringBuffer sb = new StringBuffer();
            sb.append("--");
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data; \r\n name=\"1\"; filename=\"" + fileName + "\"\r\n");
            sb.append("Content-Type: application/msword\r\n\r\n");

            hc.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + "---------------------------7d4a6d158c9");

            byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();

            dos = new DataOutputStream(hc.getOutputStream());

            dos.write(sb.toString().getBytes("utf-8"));
            
            //同时记录本地文件
            int cc = 0;
            while ((cc = input.read()) != -1)
            {
                dos.write(cc);
                
                if(tempFile != null) {
                    tempFile.write(cc);
                }
            }

            dos.write(end_data);

            is = hc.getInputStream();

            int ch;
            while ((ch = is.read()) != -1)
            {
                strBd.append((char)ch);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }finally {
            if(dos != null){
                dos.flush();
                dos.close();
            }
            if(is != null){
                is.close();
            }
            if(httpUrl != null){
                httpUrl.disconnect();
            }
            if(hc != null){
                hc.disconnect();
            }
            if(tempFile != null) {
                tempFile.close();
            }
        }

        return strBd.toString().replace("\r\n", "");
    }
}
