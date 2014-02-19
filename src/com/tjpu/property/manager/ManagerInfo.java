package com.tjpu.property.manager;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

import com.tjpu.property.R;
import com.tjpu.property.user.AddService;
import com.tjpu.property.user.UserService;
import com.tjpu.property.view.DropDownListView;
import com.tjpu.property.view.DropDownListView.OnDropDownListener;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.content.Intent;

public class ManagerInfo extends Activity{

	private LinkedList<String>   listItems = null;
    private DropDownListView     listView  = null;
    private ArrayAdapter<String> adapter;
    private Button bt = null;

    private String[] mStrings  = { "Aaaaaa", "Bbbbbb", "Cccccc", "Dddddd", "Eeeeee",
            "Ffffff", "Gggggg", "Hhhhhh", "Iiiiii", "Jjjjjj", "Kkkkkk", "Llllll", "Mmmmmm",
            "Nnnnnn"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managerinfo);

        bt = (Button) findViewById(R.id.addUser);
        bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(ManagerInfo.this, AddUser.class);
				ManagerInfo.this.startActivity(intent);
			}
		});
        
        listView = (DropDownListView)findViewById(R.id.managerinfo_lv);
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

        listItems = new LinkedList<String>();
        listItems.addAll(Arrays.asList(mStrings));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
        
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				System.out.println("点击了："+ arg3);
				
				Intent intent = new Intent();
				intent.putExtra("id", arg3+"");
				intent.setClass(ManagerInfo.this, ManagerInfoDetail.class);
				ManagerInfo.this.startActivity(intent);
				
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
