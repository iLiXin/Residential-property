package com.tjpu.property.menu;



import com.tjpu.property.R;
import com.tjpu.property.manager.ManagerComplain;
import com.tjpu.property.manager.ManagerCost;
import com.tjpu.property.manager.ManagerHouse;
import com.tjpu.property.manager.ManagerInfo;
import com.tjpu.property.manager.ManagerService;
import com.tjpu.property.user.UserHouse;

import android.os.Bundle;
import android.app.ActivityGroup;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

public class ManagerMenu extends ActivityGroup {
	SlidingMenuView slidingMenuView;
	
	ViewGroup tabcontent;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_main);
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
    	Intent i = new Intent(this,ManagerInfo.class);
    	View v = getLocalActivityManager().startActivity(ManagerInfo.class.getName(), i).getDecorView();
		tabcontent.removeAllViews();
		tabcontent.addView(v);
    }
    
    public void changeTab2(View view){
    	Intent i = new Intent(this,ManagerComplain.class);
    	View v = getLocalActivityManager().startActivity(ManagerComplain.class.getName(), i).getDecorView();
		tabcontent.removeAllViews();
		tabcontent.addView(v);
    }
    
    public void changeTab3(View view){
    	Intent i = new Intent(this,ManagerService.class);
    	View v = getLocalActivityManager().startActivity(ManagerService.class.getName(), i).getDecorView();
    	tabcontent.removeAllViews();
    	tabcontent.addView(v);
    }
    
    public void changeTab4(View view){
    	Intent i = new Intent(this,ManagerCost.class);
    	View v = getLocalActivityManager().startActivity(ManagerCost.class.getName(), i).getDecorView();
    	tabcontent.removeAllViews();
    	tabcontent.addView(v);
    }
    public void changeTab5(View view){
    	Intent i = new Intent(this,ManagerHouse.class);
    	View v = getLocalActivityManager().startActivity(UserHouse.class.getName(), i).getDecorView();
    	tabcontent.removeAllViews();
    	tabcontent.addView(v);
    }
    
    void showDefaultTab(){
    	Intent i = new Intent(this,ManagerInfo.class);
    	View v = getLocalActivityManager().startActivity(ManagerInfo.class.getName(), i).getDecorView();
		tabcontent.removeAllViews();
		tabcontent.addView(v);
    }
}
