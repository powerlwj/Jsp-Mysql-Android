package com.example.hisense;

import org.json.JSONException;
import org.json.JSONObject;

import classes_for_JavaBean.StockChangeDetail;

import com.hisense.asynctaskclasses.ViewMyStockAsyncTask;
import com.hisense.tools.MyApplication;
import com.hisense.tools.UpdateInfo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author powerliu
 * 类说明：查看我的库存信息
 */
public class ViewMyStock extends Activity{
	
	ViewMyStockAsyncTask vstock;
	private ListView stock;
	Button s;
	JSONObject jsObject;
	private TextView id, data, location;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//去除标题栏的标题
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setTitle("View My Stock ");
		setContentView(R.layout.viewmystock);
		ActionBar ac=this.getActionBar();
		ac.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP,ActionBar.DISPLAY_HOME_AS_UP);
		//界面初始化
		init();
		//为list加载数据
		setDataForList();
	}
	private void setDataForList() {
		// TODO Auto-generated method stub
		jsObject=new JSONObject();
		try {
			jsObject.put("ownerID", MyApplication.userid);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//调用异步类，需要传入的参数为：含二级经理ID的一个json,和一个数据加载的listview
		vstock=new ViewMyStockAsyncTask(ViewMyStock.this,jsObject , stock);
		vstock.execute(MyApplication.setUrl("viewmystock"));
		
	}
	
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.stockdetail, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Intent i=new Intent(ViewMyStock.this, ShowStockChange.class);
		startActivity(i);
		return super.onOptionsItemSelected(item);
		
		
	}
	*/
	
	private void init() {
		// TODO Auto-generated method stub
		stock=(ListView)findViewById(R.id.stocklist);
		id = (TextView) findViewById(R.id.id);
		data = (TextView) findViewById(R.id.date);
		location = (TextView) findViewById(R.id.location);
		s=(Button)findViewById(R.id.specialTask);
		s.setVisibility(View.GONE);
		UpdateInfo updateinfo = new UpdateInfo();
		updateinfo.update(id, data, location,s);
	}

}
