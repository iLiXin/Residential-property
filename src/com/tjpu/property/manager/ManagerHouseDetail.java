package com.tjpu.property.manager;

import com.tjpu.property.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ManagerHouseDetail extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.managerhousedetail);
		Intent intent = getIntent();
		String temp = intent.getStringExtra("id");
		System.out.println("收到的："+temp);
		TextView doornum_tv = (TextView) findViewById(R.id.m_doornum_tv);
		doornum_tv.setText(temp);
		TextView buliding_tv = (TextView) findViewById(R.id.m_buliding_tv);
		buliding_tv.setText("22号楼");
		TextView unit_tv = (TextView) findViewById(R.id.m_unit_tv);
		unit_tv.setText("2单元");
		TextView floor_tv = (TextView) findViewById(R.id.m_floor_tv);
		floor_tv.setText("2层");
		TextView housetype_tv = (TextView) findViewById(R.id.m_housetype_tv);
		housetype_tv.setText("普通住宅楼");
		TextView direction_tv = (TextView) findViewById(R.id.m_direction_tv);
		direction_tv.setText("南");
		TextView area_tv = (TextView) findViewById(R.id.m_area_tv);
		area_tv.setText("天津市西青区宾水西道399号");
		
		Button bt = (Button) findViewById(R.id.edit_house_bt);
		bt.setText("修改");
		
	}
	
	

}
