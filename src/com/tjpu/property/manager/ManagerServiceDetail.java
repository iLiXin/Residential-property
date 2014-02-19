package com.tjpu.property.manager;

import com.tjpu.property.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ManagerServiceDetail extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.managerservicedetail);
		Intent intent = getIntent();
		String temp = intent.getStringExtra("id");
		System.out.println("收到的："+temp);
		TextView type_tv = (TextView) findViewById(R.id.m_type_tv);
		type_tv.setText(temp);
		TextView content_tv = (TextView) findViewById(R.id.m_content_tv);
		content_tv.setText("换灯泡");
		TextView house_tv = (TextView) findViewById(R.id.m_house_tv);
		house_tv.setText("宿舍");
		TextView date_tv = (TextView) findViewById(R.id.m_date_tv);
		date_tv.setText("2014年2月29日");
		TextView fee_tv = (TextView) findViewById(R.id.m_fee_tv);
		fee_tv.setText("30元");
		TextView person_tv = (TextView) findViewById(R.id.m_person_tv);
		person_tv.setText("保洁");
		
	}
	
	

}
