package com.tjpu.property.user;

import java.util.ArrayList;
import java.util.List;

import com.tjpu.pojo.Complain;
import com.tjpu.pojo.Content;
import com.tjpu.pojo.Response;
import com.tjpu.pojo.Service;
import com.tjpu.property.R;
import com.tjpu.property.http.MessageUtil;
import com.tjpu.property.util.DateOpt;
import com.tjpu.property.util.XmlUtil;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddService extends Activity{
	
	EditText addserv_type_et;
	EditText addserv_content_et;
	EditText addserv_title_et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addservice);
		
		addserv_type_et = (EditText) findViewById(R.id.addserv_type_et);
		addserv_content_et = (EditText) findViewById(R.id.addserv_content_et);
		addserv_title_et = (EditText) findViewById(R.id.addserv_title_et);
		
		Button bt = (Button) findViewById(R.id.addserv_bt);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				SharedPreferences sp = getSharedPreferences("user", MODE_WORLD_WRITEABLE);
				sp.edit().putString("user", "3");
				String user = sp.getString("user", "2");
				
				String type = addserv_type_et.getText().toString().trim();
				String content = addserv_content_et.getText().toString().trim();
				String title = addserv_title_et.getText().toString().trim();
				
				Service comp = new Service();
				comp.setContent(content);
				comp.setUser(Integer.parseInt(user));
				comp.setTitle(title);
				comp.setType(type);
				comp.setPerson(" ");
				comp.setDate(" ");
				comp.setState("0");
				
				
				try {
					comp.setTime(DateOpt.getNowTime());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				List<Object> list = new ArrayList<Object>();
				list.add(comp);
				
				Content con = new Content();
				con.setValue(list);
				
				String nowTime = "0000";
				try {
					nowTime = DateOpt.getNowTime();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				String result = new MessageUtil().send(user, nowTime, "insert", con);
				
				Response resp = (Response) new XmlUtil().xmlToObject(result, new Response(), "msg");
				String re = resp.getResult();
				if(re.equals("1")){
					Toast.makeText(getApplicationContext(), "添加成功",
						     Toast.LENGTH_SHORT).show();
					AddService.this.finish();
				}else{
					Toast.makeText(getApplicationContext(), "添加失败",
						     Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		
	}
	
	

}
