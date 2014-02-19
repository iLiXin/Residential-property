package com.tjpu.property.manager;


import com.tjpu.property.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;

public class ManagerInfoDetail extends Activity{
	
	EditText name_et;
	EditText phonenum_et;
	EditText cardnum_et;
	EditText money_et;
	Button bt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managerinfodetail);
        
        name_et = (EditText) findViewById(R.id.manager_name);
        name_et.setText("业主");
        name_et.setEnabled(false);
        name_et.setFocusable(false);
        
        phonenum_et = (EditText) findViewById(R.id.manager_phonenum);
        phonenum_et.setText("13012345678");
        phonenum_et.setEnabled(false);
        phonenum_et.setFocusable(false);
        
        cardnum_et = (EditText) findViewById(R.id.manager_cardnum);
        cardnum_et.setText("123098765412431231");
        cardnum_et.setEnabled(false);
        cardnum_et.setFocusable(false);
        
        money_et = (EditText) findViewById(R.id.manager_money);
        money_et.setText("200000");
        money_et.setEnabled(false);
        money_et.setFocusable(false);
        
        
        TextView starttime_tv = (TextView) findViewById(R.id.manager_starttime);
        starttime_tv.setText("2014年2月29日");
        
        bt = (Button) findViewById(R.id.m_edit_info_bt);
        bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				name_et.setEnabled(true);
				phonenum_et.setEnabled(true);
				cardnum_et.setEnabled(true);
				money_et.setEnabled(true);
				bt.setText("完成");
				
			}
		});
        
    }
    
}
