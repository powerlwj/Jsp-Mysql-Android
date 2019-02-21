package com.hisense.tools;

import com.example.hisense.Login;
import com.example.hisense.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class FirstView extends Activity{
	
	private ImageView welcome;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.firstview);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Intent i=new Intent(FirstView.this, Login.class);
		startActivity(i);
		
	}

}
