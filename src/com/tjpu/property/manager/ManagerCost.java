package com.tjpu.property.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.tjpu.pojo.Content;
import com.tjpu.pojo.Cost;
import com.tjpu.pojo.Response;
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
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class ManagerCost extends Activity{

	private LinkedList<String>   listItems = null;
	private LinkedList<String>   idItems = null;
    private DropDownListView     listView  = null;
    private ArrayAdapter<String> adapter;
    private String lasttime;
    private int index = 10;
    private Button bt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managercost);
        
        try {
			lasttime = DateOpt.getNowTime();
		} catch (Exception e) {
			e.printStackTrace();
		}

        listView = (DropDownListView)findViewById(R.id.managercost_lv);
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

        List<Object> templist = new ArrayList<Object>();
		templist.add(new Cost());
		
		Content content = new Content();
    	content.setValue(templist);
    	content.setIdentify("cost");
    	String createTime = "0000";
		try {
			createTime = DateOpt.getNowTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        listItems = new LinkedList<String>();
        idItems = new LinkedList<String>();
        String result = new MessageUtil().send("0 or 1=1", createTime, "select_*", content);
		
		Response resp = (Response) new XmlUtil().xmlToObject(result, new Response(), "msg");
        List<Object> values = resp.getValues();
        for (Object object : values) {
            listItems.add(((Cost)object).getName());
            idItems.add(((Cost)object).getId()+"");
		}
        
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
        
        bt = (Button) findViewById(R.id.addCost);
        bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent();
				intent.setClass(ManagerCost.this, AddCost.class);
				ManagerCost.this.startActivity(intent);
				
			}
		});
        
    }

    private class GetDataTask extends AsyncTask<Void, Void, LinkedList<String>> {

        private boolean isDropDown;

        public GetDataTask(boolean isDropDown){
            this.isDropDown = isDropDown;
        }

        @Override
        protected LinkedList<String> doInBackground(Void... params) {
        	if (isDropDown) {
	            List<Object> templist = new ArrayList<Object>();
	    		templist.add(new Cost());
	    		
	    		Content content = new Content();
	        	content.setIdentify(lasttime);
	        	content.setValue(templist);
	        	
	            String res = new MessageUtil().send("0 or 1=1", lasttime, "select_*_drop", content);
	            Response resp = (Response) new XmlUtil().xmlToObject(res, new Response(), "msg");
	            List<Object> list =resp.getValues();
	            for (Object object : list) {
	                listItems.addFirst(((Cost)object).getName());
	                idItems.addFirst(((Cost)object).getId()+"");
				}
	            
	            try {
					lasttime = DateOpt.getNowTime();
				} catch (Exception e) {
					e.printStackTrace();
				}
            } else {
        		List<Object> templist = new ArrayList<Object>();
        		templist.add(new Cost());
        		
        		Content content = new Content();
            	content.setIdentify(index+"");
            	content.setValue(templist);
        		
                String res = new MessageUtil().send("0 or 1=1", index+"", "select_*_bottom", content);
                Response resp = (Response) new XmlUtil().xmlToObject(res, new Response(), "msg");
                List<Object> list =resp.getValues();
                if(list.size()==0){
                	listView.setHasMore(false);
                }
                
                for (Object object : list) {
                	listItems.add(((Cost)object).getName());
                	idItems.add(((Cost)object).getId()+"");
				}
                
                index+=10;
        	}
            return listItems;
        }

        @Override
        protected void onPostExecute(LinkedList<String> result) {

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

            super.onPostExecute(result);
        }
    }
}
