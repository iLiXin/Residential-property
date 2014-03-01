package com.tjpu.property.manager;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tjpu.pojo.Content;
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
import android.widget.TextView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class ManagerInfoDetail extends Activity{
	
	HashMap<String, String> map = new HashMap<String, String>();
	TextView name_et;
	TextView phonenum_et;
	TextView cardnum_et;
	TextView money_et;
	Button bt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managerinfodetail);
        
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        
        List<Object> templist = new ArrayList<Object>();
		templist.add(new Userinfo());
		
		Content content = new Content();
    	content.setValue(templist);
    	content.setIdentify(id);
    	String createTime = "0000";
		try {
			createTime = DateOpt.getNowTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        String result = new MessageUtil().send(id, createTime, "select_1", content);
        Response resp = (Response) new XmlUtil().xmlToObject(result, new Response(), "msg");
        List<Object> values = resp.getValues();
        for (Object object : values) {
            map.put("name", ((Userinfo)object).getName());
            map.put("cardnum", ((Userinfo)object).getCardnum());
            map.put("phonenum", ((Userinfo)object).getPhonenum());
            map.put("starttime", ((Userinfo)object).getStarttime());
            map.put("money", ((Userinfo)object).getMoney());
		}
        
        name_et = (TextView) findViewById(R.id.manager_name);
        name_et.setText(map.get("name"));
        
        phonenum_et = (TextView) findViewById(R.id.manager_phonenum);
        phonenum_et.setText(map.get("phonenum"));
        
        cardnum_et = (TextView) findViewById(R.id.manager_cardnum);
        cardnum_et.setText(map.get("cardnum"));
        
        money_et = (TextView) findViewById(R.id.manager_money);
        money_et.setText(map.get("money"));
        
        TextView starttime_tv = (TextView) findViewById(R.id.manager_starttime);
        starttime_tv.setText(map.get("starttime"));
        
        
    }
    
}
