package com.tjpu.property.manager;

import com.tjpu.property.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddUser extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adduser);
		
		EditText manager_name_add = (EditText) findViewById(R.id.manager_name_add);
		EditText manager_phonenum_add = (EditText) findViewById(R.id.manager_phonenum_add);
		EditText manager_cardnum_add = (EditText) findViewById(R.id.manager_cardnum_add);
		EditText manager_money_add = (EditText) findViewById(R.id.manager_money_add);
		Button m_add_info_bt = (Button) findViewById(R.id.m_add_info_bt);
	}
	
	

}
