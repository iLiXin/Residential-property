package com.tjpu.property.user;

import java.util.ArrayList;
import java.util.List;

import com.tjpu.pojo.Complain;
import com.tjpu.pojo.Content;
import com.tjpu.pojo.Response;
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

public class AddComlplain extends Activity{
	
	EditText et;
	EditText title_et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addcomplain);
		
		et = (EditText) findViewById(R.id.addcomp_et);
		title_et = (EditText) findViewById(R.id.addcomp_title_et);
		
		Button bt = (Button) findViewById(R.id.addcomp_bt);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				SharedPreferences sp = getSharedPreferences("TOKEN", MODE_PRIVATE);
				String user = sp.getInt("user", 2)+"";
				
				String content = et.getText().toString().trim();
				String title = title_et.getText().toString().trim();
				Complain comp = new Complain();
				comp.setContent(content);
				comp.setUser(Integer.parseInt(user));
				comp.setTitle(title);
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
					AddComlplain.this.finish();
				}else{
					Toast.makeText(getApplicationContext(), "添加失败",
						     Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		
	}
	
	    

}
