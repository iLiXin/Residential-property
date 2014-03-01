package com.tjpu.property.manager;

import java.util.ArrayList;
import java.util.List;

import com.tjpu.pojo.Content;
import com.tjpu.pojo.Cost;
import com.tjpu.pojo.Response;
import com.tjpu.pojo.Service;
import com.tjpu.pojo.Userinfo;
import com.tjpu.property.R;
import com.tjpu.property.http.MessageUtil;
import com.tjpu.property.util.DateOpt;
import com.tjpu.property.util.XmlUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCost extends Activity{
	EditText m_name_add;
	EditText m_price_add;
	EditText m_count_add ;
	EditText m_user_add;
	Button m_add_cost_bt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addcost);
		
		m_name_add = (EditText) findViewById(R.id.m_name_add);
		m_price_add = (EditText) findViewById(R.id.m_price_add);
		m_count_add = (EditText) findViewById(R.id.m_count_add);
		m_user_add = (EditText) findViewById(R.id.m_user_add);
		m_add_cost_bt = (Button) findViewById(R.id.m_add_cost_bt);
		m_add_cost_bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				List<Object> templist = new ArrayList<Object>();
				
				Cost cost = new Cost();
				cost.setCount(Integer.parseInt(m_count_add.getText().toString()));
				cost.setName(m_name_add.getText().toString());
				cost.setPrice(Integer.parseInt(m_price_add.getText().toString()));
				try {
					cost.setTime(DateOpt.getNowTime());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				cost.setUser(Integer.parseInt(m_user_add.getText().toString()));
				templist.add(cost);
				
				Content content = new Content();
		    	content.setValue(templist);
		    	content.setIdentify("admin");
		    	String createTime = "0000";
				try {
					createTime = DateOpt.getNowTime();
				} catch (Exception e) {
					e.printStackTrace();
				}
		        
		        String result = new MessageUtil().send("2", createTime, "insert", content);
		        List<Object> templist1 = new ArrayList<Object>();
				Userinfo userinfo = new Userinfo();
				userinfo.setId(Integer.parseInt(m_user_add.getText().toString()));
				userinfo.setMoney(m_price_add.getText().toString());
				templist1.add(userinfo);
				Content content1 = new Content();
		    	content1.setValue(templist1);
		    	content1.setIdentify("admin");
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
					Toast.makeText(getApplicationContext(), "添加成功",
						     Toast.LENGTH_SHORT).show();
					onCreate(null);
				}else{
					Toast.makeText(getApplicationContext(), "添加失败",
						     Toast.LENGTH_SHORT).show();
					onCreate(null);
				}
		        
			}
		});
	}

}
