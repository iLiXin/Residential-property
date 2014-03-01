package com.tjpu.property.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tjpu.pojo.Complain;
import com.tjpu.pojo.Content;
import com.tjpu.pojo.House;
import com.tjpu.pojo.Response;
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

public class ManagerCompDetail extends Activity{
	
	HashMap<String, String> map = new HashMap<String, String>();
	Button bt;
	EditText reply_tv;
	String temp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.managercompdetail);
		Intent intent = getIntent();
		temp = intent.getStringExtra("id");
		System.out.println("收到的："+temp);
		
		List<Object> templist = new ArrayList<Object>();
		templist.add(new Complain());
		
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
        	
            map.put("content", ((Complain)object).getContent());
            map.put("reply", ((Complain)object).getReply());
            map.put("title", ((Complain)object).getTitle());
            map.put("time", ((Complain)object).getTime());
		}
		
		TextView compcontent_tv = (TextView) findViewById(R.id.m_compcontent_tv);
		compcontent_tv.setText(map.get("content"));
		
		reply_tv = (EditText) findViewById(R.id.m_reply_ed);
		reply_tv.setText(map.get("reply"));
		reply_tv.setEnabled(false);
		
		bt = (Button) findViewById(R.id.reply_bt);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				reply_tv.setEnabled(true);
				bt.setText("完成");
				bt.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						List<Object> templist = new ArrayList<Object>();
						Complain comp = new Complain();
						comp.setContent(map.get("content"));
						comp.setReply(reply_tv.getText().toString());
						comp.setTitle(map.get("title"));
						comp.setTime(map.get("time"));
						comp.setId(Integer.parseInt(temp));
						
						templist.add(comp);
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
						
						Response resp = (Response) new XmlUtil().xmlToObject(result, new Response(), "msg");
						String re = resp.getResult();
						if(re.equals("1")){
							Toast.makeText(getApplicationContext(), "修改成功",
								     Toast.LENGTH_SHORT).show();
							onCreate(null);
						}else{
							Toast.makeText(getApplicationContext(), "修改失败",
								     Toast.LENGTH_SHORT).show();
						}
					}
				});
				
			}
		});
		
	}
	
	

}
