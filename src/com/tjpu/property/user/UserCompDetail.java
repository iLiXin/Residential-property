package com.tjpu.property.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tjpu.pojo.Complain;
import com.tjpu.pojo.Content;
import com.tjpu.pojo.Response;
import com.tjpu.property.R;
import com.tjpu.property.http.MessageUtil;
import com.tjpu.property.util.DateOpt;
import com.tjpu.property.util.XmlUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserCompDetail extends Activity{
	
	HashMap<String, String> map = new HashMap<String, String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.usercompdetail);
		Intent intent = getIntent();
		String temp = intent.getStringExtra("id");
		System.out.println("收到的："+temp);
		
		List<Object> templist = new ArrayList<Object>();
		templist.add(new Complain());
		
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
        	
            map.put("content", ((Complain)object).getContent());
            map.put("reply", ((Complain)object).getReply());
		}
		
		TextView compcontent_tv = (TextView) findViewById(R.id.compcontent_tv);
		compcontent_tv.setText(map.get("content"));
		
		TextView reply_tv = (TextView) findViewById(R.id.reply_tv);
		reply_tv.setText(map.get("reply"));
		
	}
	
	

}
