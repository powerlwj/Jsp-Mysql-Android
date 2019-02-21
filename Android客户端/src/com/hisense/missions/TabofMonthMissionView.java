package com.hisense.missions;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;


public class TabofMonthMissionView extends TabActivity {
	public TabHost tabHost;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("Monthly Targets View");
		tabHost = getTabHost();
		
		tabHost.addTab(tabHost
				.newTabSpec("NM")
				.setIndicator("Model Target")
				.setContent(
						new Intent(TabofMonthMissionView.this,
								ViewAlocatedMission_Amount.class)));
		tabHost.addTab(tabHost
				.newTabSpec("PM")
				.setIndicator("Type Target")
				.setContent(
						new Intent(TabofMonthMissionView.this,
								ViewAlocatedMission_TPrice.class)));
		tabHost.setCurrentTab(0); // 从零开始
	}

}
