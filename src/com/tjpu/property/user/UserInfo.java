package com.tjpu.property.user;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tjpu.pojo.Content;
import com.tjpu.pojo.Cost;
import com.tjpu.pojo.Response;
import com.tjpu.pojo.Userinfo;
import com.tjpu.property.R;
import com.tjpu.property.http.MessageUtil;
import com.tjpu.property.util.DateOpt;
import com.tjpu.property.util.XmlUtil;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class UserInfo extends Activity{
	
	HashMap<String, String> map = new HashMap<String, String>();
	
	EditText phonenum_et;
	EditText cardnum_et;
	Button bt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);
        
        SharedPreferences sharedPreferences = getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
		int token = sharedPreferences.getInt("user", 2);
        
        List<Object> templist = new ArrayList<Object>();
		templist.add(new Userinfo());
		
		Content content = new Content();
    	content.setValue(templist);
    	content.setIdentify(token+"");
    	String createTime = "0000";
		try {
			createTime = DateOpt.getNowTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        String result = new MessageUtil().send(token+"", createTime, "select_1", content);
        Response resp = (Response) new XmlUtil().xmlToObject(result, new Response(), "msg");
        List<Object> values = resp.getValues();
        for (Object object : values) {
            map.put("name", ((Userinfo)object).getName());
            map.put("cardnum", ((Userinfo)object).getCardnum());
            map.put("phonenum", ((Userinfo)object).getPhonenum());
            map.put("starttime", ((Userinfo)object).getStarttime());
            map.put("money", ((Userinfo)object).getMoney());
		}
		
        
        TextView name_tv = (TextView) findViewById(R.id.user_name);
        name_tv.setText(map.get("name"));
        
        phonenum_et = (EditText) findViewById(R.id.user_phonenum);
        phonenum_et.setText(map.get("phonenum"));
        phonenum_et.setEnabled(false);
        
        cardnum_et = (EditText) findViewById(R.id.user_cardnum);
        cardnum_et.setText(map.get("cardnum"));
        cardnum_et.setEnabled(false);
        
        TextView money_tv = (TextView) findViewById(R.id.user_money);
        money_tv.setText(map.get("money"));
        
        TextView starttime_tv = (TextView) findViewById(R.id.user_starttime);
        starttime_tv.setText(map.get("starttime"));
        
        bt = (Button) findViewById(R.id.edit_info_bt);
        bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				phonenum_et.setEnabled(true);
				cardnum_et.setEnabled(true);
				bt.setText("完成");
				bt.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						List<Object> templist = new ArrayList<Object>();
						
						Userinfo userInfo = new Userinfo();
						userInfo.setCardnum(cardnum_et.getText().toString().trim());
						userInfo.setMoney(map.get("money"));
						userInfo.setName(map.get("name"));
						userInfo.setPhonenum(phonenum_et.getText().toString().trim());
						userInfo.setId(2);
						templist.add(userInfo);
						
						Content content = new Content();
				    	content.setValue(templist);
				    	content.setIdentify("2");
				    	String createTime = "0000";
						try {
							createTime = DateOpt.getNowTime();
						} catch (Exception e) {
							e.printStackTrace();
						}
						String result = new MessageUtil().send("2", createTime, "update", content);
						
						Response resp = (Response) new XmlUtil().xmlToObject(result, new Response(), "msg");
						String re = resp.getResult();
						if(re.equals("1")){
							Toast.makeText(getApplicationContext(), "添加成功",
								     Toast.LENGTH_SHORT).show();
							onCreate(null);
						}else{
							Toast.makeText(getApplicationContext(), "添加失败",
								     Toast.LENGTH_SHORT).show();
						}
					}
				});
				
			}
		});
        
    }
    
}
