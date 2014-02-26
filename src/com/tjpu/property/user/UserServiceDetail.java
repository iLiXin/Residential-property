package com.tjpu.property.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tjpu.pojo.Complain;
import com.tjpu.pojo.Content;
import com.tjpu.pojo.Response;
import com.tjpu.pojo.Service;
import com.tjpu.property.R;
import com.tjpu.property.http.MessageUtil;
import com.tjpu.property.util.DateOpt;
import com.tjpu.property.util.XmlUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserServiceDetail extends Activity{

	HashMap<String, String> map = new HashMap<String, String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userservicedetail);
		Intent intent = getIntent();
		String temp = intent.getStringExtra("id");
		System.out.println("收到的："+temp);
		List<Object> templist = new ArrayList<Object>();
		templist.add(new Service());
		
		Content content = new Content();
    	content.setValue(templist);
    	content.setIdentify(temp);
    	String createTime = "0000";
		try {
			createTime = DateOpt.getNowTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        String result = new MessageUtil().send("2", createTime, "select_1", content);
		
		Response resp = (Response) new XmlUtil().xmlToObject(result, new Response(), "msg");
        List<Object> values = resp.getValues();
        for (Object object : values) {
        	
            map.put("type", ((Service)object).getType());
            map.put("content", ((Service)object).getContent());
            map.put("date", ((Service)object).getDate());
            map.put("fee", ((Service)object).getFee()+"");
            map.put("person", ((Service)object).getPerson());
		}
		
		
		TextView type_tv = (TextView) findViewById(R.id.type_tv);
		type_tv.setText(map.get("type"));
		TextView content_tv = (TextView) findViewById(R.id.content_tv);
		content_tv.setText(map.get("content"));
		TextView date_tv = (TextView) findViewById(R.id.date_tv);
		date_tv.setText(map.get("date"));
		TextView fee_tv = (TextView) findViewById(R.id.fee_tv);
		fee_tv.setText(map.get("fee"));
		TextView person_tv = (TextView) findViewById(R.id.person_tv);
		person_tv.setText(map.get("person"));
		
	}
	
	

}
