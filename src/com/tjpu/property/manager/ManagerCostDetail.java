package com.tjpu.property.manager;

import com.tjpu.property.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ManagerCostDetail extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.managercostdetail);
		Intent intent = getIntent();
		String temp = intent.getStringExtra("id");
		System.out.println("收到的："+temp);
		TextView tv = (TextView) findViewById(R.id.managercostdetail_tv);
		tv.setText(temp);
		
	}
	
	

}
