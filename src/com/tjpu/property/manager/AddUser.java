package com.tjpu.property.manager;

import java.util.ArrayList;
import java.util.List;

import com.tjpu.pojo.Complain;
import com.tjpu.pojo.Content;
import com.tjpu.pojo.Response;
import com.tjpu.pojo.User;
import com.tjpu.pojo.Userinfo;
import com.tjpu.property.R;
import com.tjpu.property.http.MessageUtil;
import com.tjpu.property.user.AddComlplain;
import com.tjpu.property.util.DateOpt;
import com.tjpu.property.util.XmlUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUser extends Activity{
	
	EditText manager_name_add;
	EditText manager_phonenum_add;
	EditText manager_cardnum_add;
	EditText manager_money_add;
	EditText manager_username_add;
	EditText manager_password_add;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adduser);
		
		manager_name_add = (EditText) findViewById(R.id.manager_name_add);
		manager_phonenum_add = (EditText) findViewById(R.id.manager_phonenum_add);
		manager_cardnum_add = (EditText) findViewById(R.id.manager_cardnum_add);
		manager_money_add = (EditText) findViewById(R.id.manager_money_add);
		manager_username_add = (EditText) findViewById(R.id.manager_username_add);
		manager_password_add = (EditText) findViewById(R.id.manager_password_add);
		Button m_add_info_bt = (Button) findViewById(R.id.m_add_info_bt);
		m_add_info_bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				User user = new User();
				user.setPassword(manager_password_add.getText().toString().trim());
				user.setType(1);
				user.setUsername(manager_username_add.getText().toString().trim());
				
				Userinfo info = new Userinfo();
				info.setCardnum(manager_cardnum_add.getText().toString().trim());
				info.setMoney(manager_money_add.getText().toString().trim());
				info.setName(manager_name_add.getText().toString().trim());
				info.setPhonenum(manager_phonenum_add.getText().toString().trim());
				try {
					info.setStarttime(DateOpt.getNowDate());
					info.setTime(DateOpt.getNowTime());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				info.setUser(3);
				
				List<Object> list = new ArrayList<Object>();
				list.add(user);
				list.add(info);
				
				Content con = new Content();
				con.setValue(list);
				
				String nowTime = "0000";
				try {
					nowTime = DateOpt.getNowTime();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				String result = new MessageUtil().send("", nowTime, "insert", con);
				
				Response resp = (Response) new XmlUtil().xmlToObject(result, new Response(), "msg");
				String re = resp.getResult();
				if(re.equals("2")){
					Toast.makeText(getApplicationContext(), "添加成功",
						     Toast.LENGTH_SHORT).show();
					AddUser.this.finish();
				}else{
					Toast.makeText(getApplicationContext(), "添加失败",
						     Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		
	}
	
	

}
