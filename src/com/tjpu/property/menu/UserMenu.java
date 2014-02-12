package com.tjpu.property.menu;


import com.tjpu.property.R;
import com.tjpu.property.user.UserInfo;
import com.tjpu.property.user.UserComplain;
import com.tjpu.property.user.UserService;
import com.tjpu.property.user.UserCost;

import android.os.Bundle;
import android.app.ActivityGroup;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

public class UserMenu extends ActivityGroup {
	SlidingMenuView slidingMenuView;
	
	ViewGroup tabcontent;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main);
        slidingMenuView = (SlidingMenuView) findViewById(R.id.sliding_menu_view);
        
        tabcontent = (ViewGroup) slidingMenuView.findViewById(R.id.sliding_body);
        showDefaultTab();
    }
    
    public void hideMenu(View view){
    	slidingMenuView.snapToScreen(1);
    }
    
    public void showLeftMenu(View view){
    	slidingMenuView.snapToScreen(0);
    }
    
    public void showRightMenu(View view){
    	slidingMenuView.snapToScreen(2);
    }
    
    public void changeTab1(View view){
    	Intent i = new Intent(this,UserInfo.class);
    	View v = getLocalActivityManager().startActivity(UserInfo.class.getName(), i).getDecorView();
		tabcontent.removeAllViews();
		tabcontent.addView(v);
    }
    
    public void changeTab2(View view){
    	Intent i = new Intent(this,UserComplain.class);
    	View v = getLocalActivityManager().startActivity(UserComplain.class.getName(), i).getDecorView();
		tabcontent.removeAllViews();
		tabcontent.addView(v);
    }
    
    public void changeTab3(View view){
    	Intent i = new Intent(this,UserService.class);
    	View v = getLocalActivityManager().startActivity(UserService.class.getName(), i).getDecorView();
    	tabcontent.removeAllViews();
    	tabcontent.addView(v);
    }
    
    public void changeTab4(View view){
    	Intent i = new Intent(this,UserCost.class);
    	View v = getLocalActivityManager().startActivity(UserCost.class.getName(), i).getDecorView();
    	tabcontent.removeAllViews();
    	tabcontent.addView(v);
    }
    
    void showDefaultTab(){
    	Intent i = new Intent(this,UserInfo.class);
    	View v = getLocalActivityManager().startActivity(UserInfo.class.getName(), i).getDecorView();
		tabcontent.removeAllViews();
		tabcontent.addView(v);
    }
}
