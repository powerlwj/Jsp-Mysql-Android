package com.hisense.missions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hisense.EditSoldGoods;
import com.example.hisense.R;
import com.example.hisense.ViewSales;
import com.hisense.asynctaskclasses.ViewMissionsTask;
import com.hisense.asynctaskclasses.ViewSaleAsyncTask;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.MyApplication;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class UpdateMonthPlan extends Activity {
	private EditText e1, e2, e3, e4;
	private Button update, delete;
	String type,model,target,targetAmount;
	JSONObject jsonObject=new JSONObject();
	AsyncHttpClient  upmonth=new AsyncHttpClient();
	DialogForMe df;
	ViewMissionsTask vmtask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_monthplan);
		//界面初始化
		init();
		//更新月计划
		update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					jsonObject.put("type", e1.getText());
					jsonObject.put("model", e2.getText());
					jsonObject.put("targetType", "Monthly");
					jsonObject.put("targetTime",MyApplication.targetTime );
					if(MyApplication.MONTH_INDEX==1)
					{
						jsonObject.put("index", 1);
						jsonObject.put("ownerID", ViewAlocatedMission_Amount.mystuff);
						System.out.println("MissionAlocate_Amount.mystuff>>>>>>>"+ViewAlocatedMission_Amount.mystuff);
						jsonObject.put("target", e3.getText());
						jsonObject.put("targetAmount", e4.getText());
					}else if(MyApplication.MONTH_INDEX==2)
					{
						jsonObject.put("index", 2);
						jsonObject.put("ownerID", ViewAlocatedMission_TPrice.mystuff);
						jsonObject.put("target", e3.getText());
						jsonObject.put("targetAmount", e4.getText());
					}
					StringEntity s=new StringEntity(jsonObject.toString());
					upmonth.post(UpdateMonthPlan.this, MyApplication.setUrl("updatemonthmission"), s, HTTP.UTF_8,new AsyncHttpResponseHandler() {
						
						@Override
						public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
							// TODO Auto-generated method stub
							if(arg0==200)
							{
								System.out.println("服务器连接成功");
								boolean index=Boolean.valueOf(new String(arg2).trim());
								if(index)
								{
									Toast.makeText(UpdateMonthPlan.this, "Target update successful", Toast.LENGTH_SHORT).show();
									e1.setText("");e2.setText("");e3.setText("");e4.setText("");
									//刷新界面
									
									flushview();
								}else
								{
									Toast.makeText(UpdateMonthPlan.this, "Target  update failed", Toast.LENGTH_SHORT).show();
								}
							}
						}
						
						@Override
						public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
							// TODO Auto-generated method stub
							Toast.makeText(UpdateMonthPlan.this, "Unknown Error", Toast.LENGTH_SHORT).show();
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
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						UpdateMonthPlan.this);
				dialog.setTitle("Delete!!!")
						.setIcon(android.R.drawable.ic_dialog_info)
						.setMessage("Really delete?")
						.setPositiveButton("YES",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										try {
											jsonObject.put("targetType", "Monthly");
											jsonObject.put("type", e1.getText());
											jsonObject.put("model", e2.getText());
											jsonObject.put("targetTime",MyApplication.targetTime );
											if(MyApplication.MONTH_INDEX==1)
											{
												jsonObject.put("index", 1);
												jsonObject.put("ownerID", ViewAlocatedMission_Amount.mystuff);
												jsonObject.put("target", e3.getText());
												jsonObject.put("targetAmount", e4.getText());
											}else if(MyApplication.MONTH_INDEX==2)
											{
												jsonObject.put("index", 2);
												jsonObject.put("ownerID", ViewAlocatedMission_TPrice.mystuff);
												jsonObject.put("targetAmount", e3.getText());
												jsonObject.put("target", e4.getText());
											}
											StringEntity s=new StringEntity(jsonObject.toString());
											upmonth.post(UpdateMonthPlan.this, MyApplication.setUrl("monthmissiondel"), s, HTTP.UTF_8,new AsyncHttpResponseHandler() {
												
												@Override
												public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
													// TODO Auto-generated method stub
													if(arg0==200)
													{
														System.out.println("服务器连接成功");
														boolean index=Boolean.valueOf(new String(arg2).trim());
														if(index)
														{
															Toast.makeText(UpdateMonthPlan.this, "delete  successful", Toast.LENGTH_SHORT).show();
															e1.setText("");e2.setText("");e3.setText("");e4.setText("");
															//刷新界面
															flushview();
														}else
														{
															Toast.makeText(UpdateMonthPlan.this, "delete  failed", Toast.LENGTH_SHORT).show();
														}
													}
												}
												
												@Override
												public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
													// TODO Auto-generated method stub
													Toast.makeText(UpdateMonthPlan.this, "Unknown Error", Toast.LENGTH_SHORT).show();
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
								})
						.setNegativeButton("NO",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										dialog.cancel();// 取消弹出框
										Toast.makeText(UpdateMonthPlan.this,
												"Cancled", Toast.LENGTH_SHORT)
												.show();
									}
								}).create().show();
				
			}
		});
		
		
	}

	private void flushview() {
		// TODO Auto-generated method stub
	
		try {
			jsonObject.put("ownerID", MyApplication.MYSTUFF);
			jsonObject.put("targetTime", MyApplication.targetTime);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(MyApplication.MONTH_INDEX==1)
		{
			try {
				jsonObject.put("index", 1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("..............................."+jsonObject.toString());
			vmtask=new ViewMissionsTask(UpdateMonthPlan.this, ViewAlocatedMission_Amount.upmission, jsonObject,1);
		}
		else if(MyApplication.MONTH_INDEX==2)
		{
			try 
			{
				jsonObject.put("index", 2);
			} 
			catch (JSONException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("..............................."+jsonObject.toString());
			vmtask=new ViewMissionsTask(UpdateMonthPlan.this, ViewAlocatedMission_TPrice.upmission2, jsonObject,2);
		}
		else
		{
			Toast.makeText(UpdateMonthPlan.this,
					"Flush view failed", Toast.LENGTH_SHORT)
					.show();
		}
		
		vmtask.execute(MyApplication.setUrl("viewallocatedmissions"));
	}

	private void init() {
		// TODO Auto-generated method stub
		df=new DialogForMe(UpdateMonthPlan.this);
		e1=(EditText)findViewById(R.id.e1);
		e2=(EditText)findViewById(R.id.e2);
		e3=(EditText)findViewById(R.id.e3);
		e4=(EditText)findViewById(R.id.e4);
		update=(Button)findViewById(R.id.up_month);
		delete=(Button)findViewById(R.id.del_month);
		Intent data=getIntent();
		if(MyApplication.MONTH_INDEX==1)
		{
			type=data.getStringExtra("type");
			model=data.getStringExtra("model");
			target=data.getStringExtra("target");
			targetAmount=data.getStringExtra("targetAmount");
			e3.setHint("target");
			e4.setHint("targetAmount");
			setEditText(type,model,target,targetAmount);
		}else if (MyApplication.MONTH_INDEX==2) {
			
			type=data.getStringExtra("type");
			model=data.getStringExtra("model");
			target=data.getStringExtra("target");
			targetAmount=data.getStringExtra("targetAmount");
			e2.setEnabled(false);
			e3.setHint("target");
			e4.setHint("targetAmount");
			setEditText(type,model,target,targetAmount);
		}else
		{
			Toast.makeText(UpdateMonthPlan.this, "WRONG", Toast.LENGTH_SHORT).show();
		}
		
	}

	private void setEditText(String s1, String s2, String s3,
			String s4) {
		// TODO Auto-generated method stub
		e1.setText(s1);
		e2.setText(s2);
		e3.setText(s3);
		e4.setText(s4);
		e1.setEnabled(false);
		e2.setEnabled(false);
		
	}

}
