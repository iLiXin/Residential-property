package com.tjpu.property.user;

import com.tjpu.property.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserCompDetail extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.usercompdetail);
		Intent intent = getIntent();
		String temp = intent.getStringExtra("id");
		System.out.println("收到的："+temp);
		TextView compcontent_tv = (TextView) findViewById(R.id.compcontent_tv);
		compcontent_tv.setText(temp);
		
		TextView reply_tv = (TextView) findViewById(R.id.reply_tv);
		reply_tv.setText("测试回复");
		
	}
	
	

}
