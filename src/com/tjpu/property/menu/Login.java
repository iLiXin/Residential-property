package com.tjpu.property.menu;

import java.util.ArrayList;
import java.util.List;

import com.tjpu.pojo.Content;
import com.tjpu.pojo.Response;
import com.tjpu.pojo.User;
import com.tjpu.property.R;
import com.tjpu.property.http.MessageUtil;
import com.tjpu.property.util.DateOpt;
import com.tjpu.property.util.XmlUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity{
	
	EditText username;
	EditText password;
	Button login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		SharedPreferences sp = getSharedPreferences("TOKEN", MODE_PRIVATE);
        String oldu = sp.getString("u", "");
        String oldp = sp.getString("p", "");
		
		username = (EditText) findViewById(R.id.username);
		username.setText(oldu);
		password = (EditText) findViewById(R.id.password);
		password.setText(oldp);
		
		login = (Button) findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				String u = username.getText().toString().trim();
				String p = password.getText().toString().trim();
				
				if("".equals(u) || "".equals(p)){
					Toast.makeText(getApplicationContext(), "用户名或密码为空",
						     Toast.LENGTH_SHORT).show();
				}else{
					List<Object> templist = new ArrayList<Object>();
					User user = new User();
					user.setUsername(u);
					user.setPassword(p);
					
					templist.add(user);
					
					Content content = new Content();
			    	content.setValue(templist);
			    	content.setIdentify("user");
			    	String createTime = "0000";
					try {
						createTime = DateOpt.getNowTime();
					} catch (Exception e) {
						e.printStackTrace();
					}
			        
			        String result = new MessageUtil().send("user", createTime, "login", content);
					
					Response resp = (Response) new XmlUtil().xmlToObject(result, new Response(), "msg");
			        List<Object> values = resp.getValues();
			        if(values==null ||values.size()==0){
			        	Toast.makeText(getApplicationContext(), "用户名或密码错误",
							     Toast.LENGTH_SHORT).show();
			        }else{
			        	user = (User) values.get(0);
			        	int id = user.getId();
			        	int type = user.getType();
			        	
			        	SharedPreferences sharedPreferences = getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
						Editor editor = sharedPreferences.edit();
						editor.putInt("user", id);
						editor.commit();
						Intent intent = new Intent();
						if(type==0){
							intent.setClass(Login.this, ManagerMenu.class);
							finish();
						}else{
							intent.setClass(Login.this, UserMenu.class);
							finish();
						}
						SharedPreferences sharedPreferences1 = getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
						Editor editor1 = sharedPreferences.edit();
						editor1.putString("u", u);
						editor1.putString("p", p);
						editor1.commit();
						Login.this.startActivity(intent);
						
			        }
				}
				
			}
		});
		
		
	}

}
