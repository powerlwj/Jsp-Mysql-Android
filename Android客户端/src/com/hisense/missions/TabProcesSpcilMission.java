package com.hisense.missions;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;


public class TabProcesSpcilMission extends TabActivity{
	public TabHost tabHost;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("Special Targets");
		tabHost=getTabHost();
		
		tabHost.addTab(tabHost.newTabSpec("NM").setIndicator("Model Target ").setContent(new Intent(TabProcesSpcilMission.this, ProcessofSpS_AM.class)));
		tabHost.addTab(tabHost.newTabSpec("PM" ).setIndicator("Type Target").setContent(new Intent(TabProcesSpcilMission.this, ProcessofSpS_TP.class)));
		 tabHost.setCurrentTab(0); //从零开始 
	}

}
