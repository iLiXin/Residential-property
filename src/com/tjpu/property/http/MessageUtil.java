package com.tjpu.property.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import com.tjpu.pojo.Constance;
import com.tjpu.pojo.Content;
import com.tjpu.pojo.Request;
import com.tjpu.pojo.Response;
import com.tjpu.property.util.XmlUtil;

public class MessageUtil {
	
	public String send(String fromUser, String createTime, String type, Content content){
		
		try {
			
			Request myRequest = new Request();
			myRequest.setFromUser(fromUser);
			myRequest.setCreateTime(createTime);
			myRequest.setType(type);
			myRequest.setContent(content);
			
			String msg = new XmlUtil().objectToXml(myRequest,"msg");
			
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
            
            return result;
            
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "error";
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return "error";
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}   
	}
}
