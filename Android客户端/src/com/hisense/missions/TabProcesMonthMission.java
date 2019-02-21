package com.hisense.missions;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;


public class TabProcesMonthMission extends TabActivity{
	public TabHost tabHost;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("Monthly Targets");
		tabHost=getTabHost();
	
		tabHost.addTab(tabHost.newTabSpec("NM").setIndicator("Model Target").setContent(new Intent(TabProcesMonthMission.this, ProcessofMS_AM.class)));
		tabHost.addTab(tabHost.newTabSpec("PM" ).setIndicator("Type Target").setContent(new Intent(TabProcesMonthMission.this, ProcessofMS_TP.class)));
		 tabHost.setCurrentTab(0); //从零开始 
	}

}
