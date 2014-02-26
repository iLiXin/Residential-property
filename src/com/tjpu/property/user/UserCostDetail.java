package com.tjpu.property.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tjpu.pojo.Content;
import com.tjpu.pojo.Cost;
import com.tjpu.pojo.Response;
import com.tjpu.property.R;
import com.tjpu.property.http.MessageUtil;
import com.tjpu.property.util.DateOpt;
import com.tjpu.property.util.XmlUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserCostDetail extends Activity{

	HashMap<String, String> map = new HashMap<String, String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.usercostdetail);
		Intent intent = getIntent();
		String temp = intent.getStringExtra("id");
		System.out.println("收到的："+temp);
		
		List<Object> templist = new ArrayList<Object>();
		templist.add(new Cost());
		
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
        	
            map.put("name", ((Cost)object).getName());
            map.put("count", ((Cost)object).getCount()+"");
            map.put("price", ((Cost)object).getPrice()+"");
		}
		
		TextView name = (TextView) findViewById(R.id.costname_tv);
		name.setText(map.get("name"));
		TextView count = (TextView) findViewById(R.id.costcount_tv);
		count.setText(map.get("count"));
		TextView price = (TextView) findViewById(R.id.costprice_tv);
		price.setText(map.get("price"));
		
	}
	
	

}
