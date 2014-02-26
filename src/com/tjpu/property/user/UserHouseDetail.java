package com.tjpu.property.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tjpu.pojo.Complain;
import com.tjpu.pojo.Content;
import com.tjpu.pojo.House;
import com.tjpu.pojo.Response;
import com.tjpu.property.R;
import com.tjpu.property.http.MessageUtil;
import com.tjpu.property.util.DateOpt;
import com.tjpu.property.util.XmlUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserHouseDetail extends Activity{

	
	HashMap<String, String> map = new HashMap<String, String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userhousedetail);
		Intent intent = getIntent();
		String temp = intent.getStringExtra("id");
		System.out.println("收到的："+temp);
		
		List<Object> templist = new ArrayList<Object>();
		templist.add(new House());
		
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
        	
            map.put("doornum", ((House)object).getDoornum());
            map.put("buliding", ((House)object).getBuilding());
            map.put("unit", ((House)object).getUnit());
            map.put("floor", ((House)object).getFloor());
            map.put("housetype", ((House)object).getHousetype());
            map.put("direction", ((House)object).getDirection());
            map.put("area", ((House)object).getOther());
		}
		
		TextView doornum_tv = (TextView) findViewById(R.id.doornum_tv);
		doornum_tv.setText(map.get("doornum"));
		TextView buliding_tv = (TextView) findViewById(R.id.buliding_tv);
		buliding_tv.setText(map.get("buliding"));
		TextView unit_tv = (TextView) findViewById(R.id.unit_tv);
		unit_tv.setText(map.get("unit"));
		TextView floor_tv = (TextView) findViewById(R.id.floor_tv);
		floor_tv.setText(map.get("floor"));
		TextView housetype_tv = (TextView) findViewById(R.id.housetype_tv);
		housetype_tv.setText(map.get("housetype"));
		TextView direction_tv = (TextView) findViewById(R.id.direction_tv);
		direction_tv.setText(map.get("direction"));
		TextView area_tv = (TextView) findViewById(R.id.area_tv);
		area_tv.setText(map.get("area"));
		
	}
	
	

}
