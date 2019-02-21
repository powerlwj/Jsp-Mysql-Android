package com.hisense.missions;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;


public class TabProcesSpcilMissionForOrd extends TabActivity{
	public TabHost tabHost;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("Special Targets");
		tabHost=getTabHost();
		
		tabHost.addTab(tabHost.newTabSpec("NM").setIndicator("Model Target ").setContent(new Intent(TabProcesSpcilMissionForOrd.this, ProcessofSpSForOD_AM.class)));
		tabHost.addTab(tabHost.newTabSpec("PM" ).setIndicator("Type Target").setContent(new Intent(TabProcesSpcilMissionForOrd.this, ProcessofSpSForOD_TP.class)));
		 tabHost.setCurrentTab(0); //从零开始 
	}

}
