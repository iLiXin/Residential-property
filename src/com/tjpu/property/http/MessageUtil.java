package com.tjpu.property.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import com.tjpu.property.entity.Constance;
import com.tjpu.property.entity.Content;
import com.tjpu.property.entity.Request;
import com.tjpu.property.util.XmlUtil;

public class MessageUtil {
	
	public void send(String fromUser, String createTime, String type, Content content){
		
		try {
			
			Request myRequest = new Request();
			myRequest.setFromUser(fromUser);
			myRequest.setCreateTime(createTime);
			myRequest.setType(type);
			myRequest.setContent(content);
			
			@SuppressWarnings("static-access")
			String msg = new XmlUtil().objectToXml(myRequest);
			
			System.out.println("-----"+msg);
			
			String baseUrl = Constance.IP + ":" + Constance.PORT + Constance.URL;
			HttpPost httpPost = new HttpPost(baseUrl);
			HttpClient httpClient = new DefaultHttpClient();
			
			StringEntity entity = new StringEntity(msg);
			httpPost.setEntity(entity);
			
			HttpResponse response = httpClient.execute(httpPost); //发起POST请求
            
            int resCode = response.getStatusLine().getStatusCode(); //获取响应码
            String result = EntityUtils.toString(response.getEntity(), "utf-8");//获取服务器响应内容
            System.out.println(resCode);
            System.out.println(result);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
	}
}
