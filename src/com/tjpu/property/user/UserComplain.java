package com.tjpu.property.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.tjpu.property.R;
import com.tjpu.property.view.DropDownListView;
import com.tjpu.property.view.DropDownListView.OnDropDownListener;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;

public class UserComplain extends Activity{

	
	private LinkedList<String>   listItems = null;
    private DropDownListView     listView  = null;
    private SimpleAdapter adapter;
    private Button bt = null;

    private String[] mStrings  = { "Aaaaaa", "Bbbbbb", "Cccccc", "Dddddd", "Eeeeee",
            "Ffffff", "Gggggg", "Hhhhhh", "Iiiiii", "Jjjjjj", "Kkkkkk", "Llllll", "Mmmmmm",
            "Nnnnnn"};
    
    private List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();


	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usercomplain);
        
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
				System.out.println("按下");
				new AlertDialog.Builder(getParent())
				.setMessage("确定删除吗？")  
				.setPositiveButton("是", null)  
				.setNegativeButton("否", null)  
				.show();
				return false;
			}
		});
        
        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("title", "标题1");
        map1.put("flag", "0");
        HashMap<String, String> map2 = new HashMap<String, String>();
        map2.put("title", "标题2");
        map2.put("flag", "1");
        data.add(map1);
        data.add(map2);
        data.add(map2);
        data.add(map2);
        data.add(map2);
        data.add(map2);
        data.add(map2);
        data.add(map2);
        data.add(map2);
        data.add(map2);
        data.add(map2);
        
        adapter = new SimpleAdapter(this, data, R.layout.userservice_items, new String[]{"title","flag"}, new int[]{R.id.title,R.id.flag});
        listView.setAdapter(adapter);
        
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				System.out.println("点击了："+ arg3);
				
				Intent intent = new Intent();
				intent.putExtra("id", arg3+"");
				intent.setClass(UserComplain.this, UserCompDetail.class);
				UserComplain.this.startActivity(intent);
				
			}
        });
    }

    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        private boolean isDropDown;

        public GetDataTask(boolean isDropDown){
            this.isDropDown = isDropDown;
        }

        @Override
        protected String[] doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                ;
            }
            return mStrings;
        }

        @Override
        protected void onPostExecute(String[] result) {

            if (isDropDown) {
                listItems.addFirst("Added after drop down");
                adapter.notifyDataSetChanged();

                // should call onDropDownComplete function of DropDownListView at end of drop down complete.
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
                listView.onDropDownComplete(getString(R.string.update_at)
                                            + dateFormat.format(new Date()));
            } else {
                listItems.add("Added after on bottom");
                adapter.notifyDataSetChanged();

                // should call onBottomComplete function of DropDownListView at end of on bottom complete.
                listView.onBottomComplete();
            }

            super.onPostExecute(result);
        }

    }
}
