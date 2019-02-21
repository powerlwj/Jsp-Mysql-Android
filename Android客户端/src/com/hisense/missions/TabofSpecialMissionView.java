package com.hisense.missions;

import android.annotation.SuppressLint;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class TabofSpecialMissionView extends TabActivity {
	public TabHost tabHost;

	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("Special Targets View");
		
		tabHost = getTabHost();

		tabHost.addTab(tabHost
				.newTabSpec("NM")
				.setIndicator("Model Target")
				.setContent(
						new Intent(TabofSpecialMissionView.this,
								ViewAllocatedSpecialMission_Amount.class)));
		tabHost.addTab(tabHost
				.newTabSpec("PM")
				.setIndicator("Type Target")
				.setContent(
						new Intent(TabofSpecialMissionView.this,
								ViewAllocatedSpecialMission_TPrice.class)));
		// tabHost.setBackgroundColor(com.example.hisense.R.color.hisense);
		tabHost.setCurrentTab(0); // 从零开始

	}

}
