package com.tjpu.property.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.tjpu.pojo.Complain;
import com.tjpu.pojo.Content;
import com.tjpu.pojo.Response;
import com.tjpu.pojo.Service;
import com.tjpu.property.R;
import com.tjpu.property.http.MessageUtil;
import com.tjpu.property.util.DateOpt;
import com.tjpu.property.util.XmlUtil;
import com.tjpu.property.view.DropDownListView;
import com.tjpu.property.view.DropDownListView.OnDropDownListener;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class UserComplain extends Activity{

	
    private DropDownListView     listView  = null;
    private SimpleAdapter adapter;
    private Button bt = null;
    private String lasttime;
    private int index = 10;
    private int location;
    private String id;
    
    private LinkedList<HashMap<String, String>> data = new LinkedList<HashMap<String, String>>();


	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usercomplain);
        
        try {
			lasttime = DateOpt.getNowTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        bt = (Button) findViewById(R.id.addComplain);
        bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(UserComplain.this, AddComlplain.class);
				UserComplain.this.startActivity(intent);
			}
		});
        
        listView = (DropDownListView)findViewById(R.id.usercomplain_lv);
        // set drop down listener
        listView.setOnDropDownListener(new OnDropDownListener() {

            @Override
            public void onDropDown() {
                new GetDataTask(true).execute();
            }
        });

        // set on bottom listener
        listView.setOnBottomListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new GetDataTask(false).execute();
            }
        });
        
        
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				location = arg2;
				id = data.get(arg2-1).get("id");
				System.out.println("按下:"+id);
				new AlertDialog.Builder(UserComplain.this).setTitle("提示").setMessage("确定要删除吗？")
                .setPositiveButton("确定",new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					List<Object> templist = new ArrayList<Object>();
					templist.add(new Complain());
						
					Content content = new Content();
				    content.setValue(templist);
				    content.setIdentify(id);
				    String createTime = "0000";
					try {
						createTime = DateOpt.getNowTime();
					} catch (Exception e) {
						e.printStackTrace();
					}
					String result = new MessageUtil().send("2", createTime, "delete", content);
					data.remove(location-1);
					adapter.notifyDataSetChanged();
					listView.invalidate();
						
				}
								
			       }).setNegativeButton("取消",new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
			                  }
				}).create().show();
				return false;
			}
		});
        
        List<Object> templist = new ArrayList<Object>();
		templist.add(new Complain());
		
		Content content = new Content();
    	content.setValue(templist);
    	content.setIdentify("complain");
    	String createTime = "0000";
		try {
			createTime = DateOpt.getNowTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        String result = new MessageUtil().send("2", createTime, "select_*", content);
		
		Response resp = (Response) new XmlUtil().xmlToObject(result, new Response(), "msg");
        List<Object> values = resp.getValues();
        for (Object object : values) {
        	HashMap<String, String> map = new HashMap<String, String>();
            map.put("title", ((Complain)object).getTitle());
            map.put("id", ((Complain)object).getId()+"");
            String flag = "未回复";
            if(!((Complain)object).getReply().equals("0")){
            	flag = "已回复";
            }
            map.put("flag", flag);
            data.add(map);
		}
        
        
        adapter = new SimpleAdapter(this, data, R.layout.userservice_items, new String[]{"title","flag"}, new int[]{R.id.title,R.id.flag});
        listView.setAdapter(adapter);
        
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				System.out.println("点击了："+ arg3);
				id = data.get(arg2-1).get("id");
				System.out.println("id="+id);
				Intent intent = new Intent();
				
				intent.putExtra("id", id+"");
				intent.setClass(UserComplain.this, UserCompDetail.class);
				UserComplain.this.startActivity(intent);
				
			}
        });
    }

    private class GetDataTask extends AsyncTask<String, String, List<HashMap<String, String>>> {

        private boolean isDropDown;

        public GetDataTask(boolean isDropDown){
            this.isDropDown = isDropDown;
        }

        @Override
        protected List<HashMap<String, String>> doInBackground(String... params) {
            try {
            	
            	if(isDropDown){
            		
            		List<Object> templist = new ArrayList<Object>();
            		templist.add(new Complain());
            		
            		Content content = new Content();
                	content.setIdentify(lasttime);
                	content.setValue(templist);
                	
                    String result = new MessageUtil().send("2", lasttime, "select_*_drop", content);
                    Response resp = (Response) new XmlUtil().xmlToObject(result, new Response(), "msg");
                    List<Object> list =resp.getValues();
                    for (Object object : list) {
                    	HashMap<String, String> map = new HashMap<String, String>();
                        map.put("title", ((Complain)object).getTitle());
                        map.put("id", ((Complain)object).getId()+"");
                        String flag = "未回复";
                        if(((Complain)object).getReply().equals("1")){
                        	flag = "已回复";
                        }
                        map.put("flag", flag);
                        data.addFirst(map);
    				}
                    
                    lasttime = DateOpt.getNowTime();
            	}else{
            		List<Object> templist = new ArrayList<Object>();
            		templist.add(new Complain());
            		
            		Content content = new Content();
                	content.setIdentify(index+"");
                	content.setValue(templist);
            		
                    String result = new MessageUtil().send("2", index+"", "select_*_bottom", content);
                    Response resp = (Response) new XmlUtil().xmlToObject(result, new Response(), "msg");
                    List<Object> list =resp.getValues();
                    if(list.size()==0){
                    	listView.setHasMore(false);
                    }
                    
                    for (Object object : list) {
                    	HashMap<String, String> map = new HashMap<String, String>();
                        map.put("title", ((Complain)object).getTitle());
                        map.put("id", ((Complain)object).getId()+"");
                        String flag = "未回复";
                        if(((Complain)object).getReply().equals("1")){
                        	flag = "已回复";
                        }
                        map.put("flag", flag);
                        data.add(map);
    				}
                    
                    index+=10;
            	}
            	
            	
                
            } catch (Exception e) {
				e.printStackTrace();
			}
            return data;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> list) {

            if (isDropDown) {
            	
                adapter.notifyDataSetChanged();

                // should call onDropDownComplete function of DropDownListView at end of drop down complete.
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
                listView.onDropDownComplete(getString(R.string.update_at)
                                            + dateFormat.format(new Date()));
            } else {
                adapter.notifyDataSetChanged();

                // should call onBottomComplete function of DropDownListView at end of on bottom complete.
                listView.onBottomComplete();
            }

            super.onPostExecute(list);
        }

    }
}
