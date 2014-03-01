package com.tjpu.property.manager;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ManagerHouseDetail extends Activity{

	EditText doornum_tv;
	EditText buliding_tv;
	EditText unit_tv;
	EditText floor_tv;
	EditText housetype_tv;
	EditText direction_tv;
	EditText area_tv;
	EditText owner_tv;
	Button bt;
	String temp;
	
	HashMap<String, String> map = new HashMap<String, String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.managerhousedetail);
		Intent intent = getIntent();
		temp = intent.getStringExtra("id");
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
            map.put("owner", ((House)object).getOwner()+"");
		}
		
        doornum_tv = (EditText) findViewById(R.id.m_doornum_tv);
		doornum_tv.setText(map.get("doornum"));
		doornum_tv.setEnabled(false);
		buliding_tv = (EditText) findViewById(R.id.m_buliding_tv);
		buliding_tv.setText(map.get("buliding"));
		buliding_tv.setEnabled(false);
		unit_tv = (EditText) findViewById(R.id.m_unit_tv);
		unit_tv.setText(map.get("unit"));
		unit_tv.setEnabled(false);
		floor_tv = (EditText) findViewById(R.id.m_floor_tv);
		floor_tv.setText(map.get("floor"));
		floor_tv.setEnabled(false);
		housetype_tv = (EditText) findViewById(R.id.m_housetype_tv);
		housetype_tv.setText(map.get("housetype"));
		housetype_tv.setEnabled(false);
		direction_tv = (EditText) findViewById(R.id.m_direction_tv);
		direction_tv.setText(map.get("direction"));
		direction_tv.setEnabled(false);
		area_tv = (EditText) findViewById(R.id.m_area_tv);
		area_tv.setText(map.get("area"));
		area_tv.setEnabled(false);
		owner_tv = (EditText) findViewById(R.id.m_owner_tv);
		owner_tv.setText(map.get("owner"));
		owner_tv.setEnabled(false);
		
		bt = (Button) findViewById(R.id.edit_house_bt);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				doornum_tv.setEnabled(true);
				buliding_tv.setEnabled(true);
				unit_tv.setEnabled(true);
				floor_tv.setEnabled(true);
				housetype_tv.setEnabled(true);
				direction_tv.setEnabled(true);
				area_tv.setEnabled(true);
				owner_tv.setEnabled(true);
				bt.setText("完成");
				bt.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						List<Object> templist = new ArrayList<Object>();
						House house = new House();
						house.setBuilding(buliding_tv.getText().toString());
						house.setDirection(direction_tv.getText().toString());
						house.setDoornum(doornum_tv.getText().toString());
						house.setFloor(floor_tv.getText().toString());
						house.setHousetype(housetype_tv.getText().toString());
						house.setId(Integer.parseInt(temp));
						house.setOther(area_tv.getText().toString());
						house.setOwner(Integer.parseInt(owner_tv.getText().toString()));
						try {
							house.setTime(DateOpt.getNowTime());
						} catch (Exception e) {
							e.printStackTrace();
						}
						house.setUnit(unit_tv.getText().toString());
						
						templist.add(house);
						Content content = new Content();
				    	content.setValue(templist);
				    	content.setIdentify(temp);
				    	String createTime = "0000";
						try {
							createTime = DateOpt.getNowTime();
						} catch (Exception e) {
							e.printStackTrace();
						}
						String result = new MessageUtil().send("2", createTime, "update", content);
						
						Response resp = (Response) new XmlUtil().xmlToObject(result, new Response(), "msg");
						String re = resp.getResult();
						if(re.equals("1")){
							Toast.makeText(getApplicationContext(), "修改成功",
								     Toast.LENGTH_SHORT).show();
							onCreate(null);
						}else{
							Toast.makeText(getApplicationContext(), "修改失败",
								     Toast.LENGTH_SHORT).show();
						}
					}
				});
			}
		});
		
		
	}
	
	

}
