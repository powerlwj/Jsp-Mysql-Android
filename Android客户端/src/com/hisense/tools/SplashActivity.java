package com.hisense.tools;


import com.example.hisense.Login;
import com.example.hisense.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;


public class SplashActivity extends Activity {

	private static final long DELAY_TIME = 2000L;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		redirectByTime();
	}

	private void redirectByTime() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				startActivity(new Intent(SplashActivity.this,Login.class));
				AnimationUtil.finishActivityAnimation(SplashActivity.this);
			}
		}, DELAY_TIME);
	}
}
