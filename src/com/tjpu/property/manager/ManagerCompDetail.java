package com.tjpu.property.manager;

import com.tjpu.property.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ManagerCompDetail extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.managercompdetail);
		Intent intent = getIntent();
		String temp = intent.getStringExtra("id");
		System.out.println("收到的："+temp);
		TextView compcontent_tv = (TextView) findViewById(R.id.m_compcontent_tv);
		compcontent_tv.setText(temp);
		
		EditText reply_tv = (EditText) findViewById(R.id.reply_ed);
		reply_tv.setText("测试回复");
		
		Button bt = (Button) findViewById(R.id.reply_bt);
		bt.setText("回复");
		
	}
	
	

}
