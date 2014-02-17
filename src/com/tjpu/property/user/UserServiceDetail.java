package com.tjpu.property.user;

import com.tjpu.property.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserServiceDetail extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userservicedetail);
		Intent intent = getIntent();
		String temp = intent.getStringExtra("id");
		System.out.println("收到的："+temp);
		TextView type_tv = (TextView) findViewById(R.id.type_tv);
		type_tv.setText(temp);
		TextView content_tv = (TextView) findViewById(R.id.content_tv);
		content_tv.setText("换灯泡");
		TextView house_tv = (TextView) findViewById(R.id.house_tv);
		house_tv.setText("宿舍");
		TextView date_tv = (TextView) findViewById(R.id.date_tv);
		date_tv.setText("2014年2月29日");
		TextView fee_tv = (TextView) findViewById(R.id.fee_tv);
		fee_tv.setText("30元");
		TextView person_tv = (TextView) findViewById(R.id.person_tv);
		person_tv.setText("保洁");
		
	}
	
	

}
