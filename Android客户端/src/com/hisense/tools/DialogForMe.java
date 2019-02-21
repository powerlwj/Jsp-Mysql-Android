package com.hisense.tools;

import com.example.hisense.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

public class DialogForMe {
	private ProgressDialog mpDialog;
	Activity myactivity;
	String title;
	String message;

	public DialogForMe(Context c) {
		super();
		this.myactivity = (Activity) c;
	}

	public DialogForMe(Context c, String title, String message) {
		super();
		this.myactivity = (Activity) c;
		this.title = title;
		this.message=message;
	}

	public void showdialog() {
		mpDialog = new ProgressDialog(myactivity);
		mpDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置风格为圆形进度条
		mpDialog.setTitle("Tips");// 设置标题
		mpDialog.setIcon(R.drawable.newpic);// 设置图标
		mpDialog.setMessage("Loading...");
		mpDialog.setCanceledOnTouchOutside(false);
		mpDialog.setIndeterminate(false);// 设置进度条是否为不明确
		mpDialog.setCancelable(true);// 设置进度条是否可以按退回键取消
		mpDialog.show();
	}
	
	public void showdialog(String title,String message) {
		mpDialog = new ProgressDialog(myactivity);
		mpDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置风格为圆形进度条
		mpDialog.setTitle(title);// 设置标题
		mpDialog.setIcon(R.drawable.newpic);// 设置图标
		mpDialog.setMessage(message);
		mpDialog.setCanceledOnTouchOutside(false);
		mpDialog.setIndeterminate(false);// 设置进度条是否为不明确
		mpDialog.setCancelable(true);// 设置进度条是否可以按退回键取消
		mpDialog.show();
	}

	public void canceldialog() {
		// TODO Auto-generated method stub
		if (mpDialog.isShowing())
			mpDialog.dismiss();
	}

}
