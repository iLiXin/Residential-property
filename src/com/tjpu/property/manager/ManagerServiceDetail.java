package com.tjpu.property.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tjpu.pojo.Complain;
import com.tjpu.pojo.Content;
import com.tjpu.pojo.Response;
import com.tjpu.pojo.Service;
import com.tjpu.pojo.Userinfo;
import com.tjpu.property.R;
import com.tjpu.property.http.MessageUtil;
import com.tjpu.property.util.DateOpt;
import com.tjpu.property.util.XmlUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ManagerServiceDetail extends Activity{

	String temp;
	Button bt;
	EditText date_tv;
	EditText fee_tv;
	EditText person_tv;
	
	HashMap<String, String> map = new HashMap<String, String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.managerservicedetail);
		Intent intent = getIntent();
		temp = intent.getStringExtra("id");
		System.out.println("收到的："+temp);
		List<Object> templist = new ArrayList<Object>();
		templist.add(new Service());
		
		Content content = new Content();
    	content.setValue(templist);
    	content.setIdentify(temp);
    	String createTime = "0000";
		try {
			createTime = DateOpt.getNowTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        String result = new MessageUtil().send("2", createTime, "select_1", content);
		
		Response resp = (Response) new XmlUtil().xmlToObject(result, new Response(), "msg");
        List<Object> values = resp.getValues();
        for (Object object : values) {
        	
            map.put("type", ((Service)object).getType());
            map.put("content", ((Service)object).getContent());
            map.put("date", ((Service)object).getDate());
            map.put("fee", ((Service)object).getFee()+"");
            map.put("person", ((Service)object).getPerson());
            map.put("title", ((Service)object).getTitle());
            map.put("user", ((Service)object).getUser()+"");
		}
		
		
		TextView type_tv = (TextView) findViewById(R.id.m_type_tv);
		type_tv.setText(map.get("type"));
		TextView content_tv = (TextView) findViewById(R.id.m_content_tv);
		content_tv.setText(map.get("content"));
		date_tv = (EditText) findViewById(R.id.m_date_tv);
		date_tv.setText(map.get("date"));
		date_tv.setEnabled(false);
		fee_tv = (EditText) findViewById(R.id.m_fee_tv);
		fee_tv.setText(map.get("fee"));
		fee_tv.setEnabled(false);
		person_tv = (EditText) findViewById(R.id.m_person_tv);
		person_tv.setText(map.get("person"));
		person_tv.setEnabled(false);
		
		bt = (Button) findViewById(R.id.eidt_service_bt);
		
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				date_tv.setEnabled(true);
				fee_tv.setEnabled(true);
				person_tv.setEnabled(true);
				bt.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						bt.setText("完成");
						List<Object> templist = new ArrayList<Object>();
						Service service = new Service();
						service.setContent(map.get("content"));
						service.setDate(date_tv.getText().toString());
						service.setFee(Integer.parseInt(fee_tv.getText().toString()));
						service.setId(Integer.parseInt(temp));
						service.setPerson(person_tv.getText().toString());
						service.setState("1");
						try {
							service.setTime(DateOpt.getNowTime());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						service.setTitle(map.get("title"));
						service.setType(map.get("type"));
						service.setUser(Integer.parseInt(map.get("user")));
						
						templist.add(service);
						Content content = new Content();
				    	content.setValue(templist);
				    	content.setIdentify(temp);
				    	String createTime = "0000";
						try {
							createTime = DateOpt.getNowTime();
						} catch (Exception e) {
							e.printStackTrace();
						}
						String result = new MessageUtil().send("1", createTime, "update", content);
						
						
						List<Object> templist1 = new ArrayList<Object>();
						Userinfo userinfo = new Userinfo();
						userinfo.setId(Integer.parseInt(map.get("user")));
						userinfo.setMoney(fee_tv.getText().toString());
						templist1.add(userinfo);
						Content content1 = new Content();
				    	content1.setValue(templist1);
				    	content1.setIdentify(temp);
				    	String createTime1 = "0000";
				    	try {
							createTime = DateOpt.getNowTime();
						} catch (Exception e) {
							e.printStackTrace();
						}
						String result1 = new MessageUtil().send("1", createTime1, "update_user", content1);
						
						Response resp = (Response) new XmlUtil().xmlToObject(result, new Response(), "msg");
						String re = resp.getResult();
						Response resp1 = (Response) new XmlUtil().xmlToObject(result1, new Response(), "msg");
						String re1 = resp1.getResult();
						if(re.equals("1") && re1.equals("1")){
							Toast.makeText(getApplicationContext(), "处理成功",
								     Toast.LENGTH_SHORT).show();
							finish();
						}else{
							Toast.makeText(getApplicationContext(), "处理失败",
								     Toast.LENGTH_SHORT).show();
							onCreate(null);
						}
						
					}
				});
				
			}
		});
		
	}
	
	

}
