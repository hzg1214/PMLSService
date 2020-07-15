package cn.com.eju.deal.wechat.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * 微信发送工具类
 * @author wenhui.zhang 
 * date: 2017年4月7日 上午10:37:38
 */
public class WechatSendUtil {

	/**
	 * 发送POST请求
	 * @param url
	 * @param content 发送的内容
	 * @return 响应信息
	 * @throws ParseException 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String sendPost(String url, String content) throws ParseException, IOException {
		CloseableHttpClient client = HttpClientBuilder.create().build();
		// 接口返回数据
		String responseJson;
		// 建立HTTPost对象
		HttpPost httpRequest = new HttpPost(url);
		StringEntity entity = new StringEntity(content, WechatConstant.ENCODING);
		// 设置类型
		entity.setContentEncoding(WechatConstant.ENCODING);
		entity.setContentType("application/json");
		httpRequest.setEntity(entity);
		// 发送请求并等待响应
		HttpResponse httpResponse = client.execute(httpRequest);
		Integer statusCode = httpResponse.getStatusLine().getStatusCode();
		responseJson = null;
		if (200 == statusCode) {
			responseJson = EntityUtils.toString(httpResponse.getEntity(), WechatConstant.ENCODING);
		}
		return responseJson;
	}

	/**
	 * 发送GET请求
	 * @param url
	 * @return 响应信息
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String sendGet(String url) throws ClientProtocolException, IOException {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost httppost = new HttpPost(url);
		// 传入参数的设定
		httppost.setEntity(new UrlEncodedFormEntity(list, WechatConstant.ENCODING));
		HttpResponse response = client.execute(httppost);
		return new String(EntityUtils.toString(response.getEntity(), WechatConstant.ENCODING));
	}
}
