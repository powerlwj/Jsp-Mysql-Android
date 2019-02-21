package com.example.hisense;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.hisense.asynctaskclasses.StckChgDtilAsyncTask;
import com.hisense.missions.UpdateMonthPlan;
import com.hisense.missions.ViewAlocatedMission_Amount;
import com.hisense.missions.ViewAlocatedMission_TPrice;
import com.hisense.tools.MyApplication;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateStockDetial extends Activity{
	
	private EditText e1,e2,e3,e4,e5,e6,e7,e8;
	private Button upload;
	StckChgDtilAsyncTask stctask;
	String model,addtime,comment,txQuantity,rxQuantity,changeQuantity,newTxQuantity,newRxQuantity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_stockdetail);
		//界面初始化
		initview();
		//更新库存明细
		upload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AsyncHttpClient  upstockdetail=new AsyncHttpClient();
				JSONObject jsonObject=new JSONObject();
				try {
					     jsonObject.put("model", e1.getText());
					     jsonObject.put("addtime", e2.getText());
					     jsonObject.put("comment", e3.getText());
						jsonObject.put("txQuantity", e4.getText());
						jsonObject.put("rxQuantity", e5.getText());
						jsonObject.put("changeQuantity", e6.getText());
						jsonObject.put("newTxQuantity", e7.getText());
						jsonObject.put("newRxQuantity", e8.getText());
						 jsonObject.put("rxOwnerID", MyApplication.userid);
					     jsonObject.put("txOwnerID",MyApplication.userid);
					StringEntity s=new StringEntity(jsonObject.toString());
					
					upstockdetail.post(UpdateStockDetial.this, MyApplication.setUrl("updatestockdetail"), s, HTTP.UTF_8,new AsyncHttpResponseHandler() {
						
						@Override
						public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
							// TODO Auto-generated method stub
							if(arg0==200)
							{
								System.out.println("服务器连接成功");
								boolean index=Boolean.valueOf(new String(arg2).trim());
								if(index)
								{
									Toast.makeText(UpdateStockDetial.this, " update successful", Toast.LENGTH_SHORT).show();
									e1.setText("");e2.setText("");e3.setText("");e4.setText("");
									e5.setText("");e6.setText("");e7.setText("");e8.setText("");
									//刷新界面
									
									flushview();
								}else
								{
									Toast.makeText(UpdateStockDetial.this, " update failed", Toast.LENGTH_SHORT).show();
								}
							}
						}
						
						private void flushview() {
							// TODO Auto-generated method stub
							JSONObject jsonObject=new JSONObject();
							try {
								jsonObject.put("id", MyApplication.userid);
								stctask=new StckChgDtilAsyncTask(UpdateStockDetial.this, ShowStockChange.stockchangelist, jsonObject);
								stctask.execute(MyApplication.setUrl("stokchgdetil"));
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							

						}

						@Override
						public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
							// TODO Auto-generated method stub
							Toast.makeText(UpdateStockDetial.this, "Unknown Error", Toast.LENGTH_SHORT).show();
						}
					});
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}
	private void initview() {
		// TODO Auto-generated method stub
		e1=(EditText)findViewById(R.id.e1);
		e2=(EditText)findViewById(R.id.e2);
		e3=(EditText)findViewById(R.id.e3);
		e4=(EditText)findViewById(R.id.e4);
		e5=(EditText)findViewById(R.id.e5);
		e6=(EditText)findViewById(R.id.e6);
		e7=(EditText)findViewById(R.id.e7);
		e8=(EditText)findViewById(R.id.e8);
		upload=(Button)findViewById(R.id.up_stock);
		Intent data=getIntent();
			model=data.getStringExtra("model");
			addtime=data.getStringExtra("addtime");
			comment=data.getStringExtra("comment");
			txQuantity=data.getStringExtra("txQuantity");
			rxQuantity=data.getStringExtra("rxQuantity");
			changeQuantity=data.getStringExtra("changeQuantity");
			newTxQuantity=data.getStringExtra("newTxQuantity");
			newRxQuantity=data.getStringExtra("newRxQuantity");
			setEditText(model,addtime,comment,txQuantity,rxQuantity,changeQuantity,newTxQuantity,newRxQuantity);
			e1.setEnabled(false);e2.setEnabled(false);e4.setEnabled(false);e5.setEnabled(false);e6.setEnabled(false);
			e7.setEnabled(false);e8.setEnabled(false);
	}
	
	
	
	private void setEditText(String s1, String s2, String s3,
			String s4,String s5, String s6, String s7,
			String s8) {
		// TODO Auto-generated method stub
		e1.setText(s1);
		e2.setText(s2);
		e3.setText(s3);
		e4.setText(s4);
		e5.setText(s5);
		e6.setText(s6);
		e7.setText(s7);
		e8.setText(s8);
		
	}

}
