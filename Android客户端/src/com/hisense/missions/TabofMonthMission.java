package com.hisense.missions;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;


public class TabofMonthMission extends TabActivity{
	public TabHost tabHost;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("Monthly Targets");
		tabHost=getTabHost();
		
		tabHost.addTab(tabHost.newTabSpec("NM").setIndicator("Model Target").setContent(new Intent(TabofMonthMission.this, MissionAlocate_Amount.class)));
		tabHost.addTab(tabHost.newTabSpec("PM" ).setIndicator("Type Target").setContent(new Intent(TabofMonthMission.this, MissionAlocate_TPrice.class)));
		 tabHost.setCurrentTab(0); //从零开始 
	}

}
