package com.tjpu.property.user;


import com.tjpu.property.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;

public class UserInfo extends Activity{
	
	EditText phonenum_et;
	EditText cardnum_et;
	Button bt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);
        
        TextView name_tv = (TextView) findViewById(R.id.user_name);
        name_tv.setText("业主");
        
        phonenum_et = (EditText) findViewById(R.id.user_phonenum);
        phonenum_et.setText("13012345678");
        phonenum_et.setEnabled(false);
        
        cardnum_et = (EditText) findViewById(R.id.user_cardnum);
        cardnum_et.setText("123098765412431231");
        cardnum_et.setEnabled(false);
        
        TextView money_tv = (TextView) findViewById(R.id.user_money);
        money_tv.setText("200000");
        
        TextView starttime_tv = (TextView) findViewById(R.id.user_starttime);
        starttime_tv.setText("2014年2月29日");
        
        bt = (Button) findViewById(R.id.edit_info_bt);
        bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				phonenum_et.setEnabled(true);
				cardnum_et.setEnabled(true);
				bt.setText("完成");
				
			}
		});
        
    }
    
}
