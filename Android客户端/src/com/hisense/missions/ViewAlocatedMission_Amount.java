/**
 * 
 */
package com.hisense.missions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hisense.R;
import com.example.hisense.ViewMonthPlan;
import com.hisense.asynctaskclasses.ViewMissionsTask;
import com.hisense.tools.DateCalculate;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.MyApplication;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Package: com.example.hisense
 *
 * File: ViewAlocatedMission.java 
 *
 * Author: powerliu   Date: 2015年8月24日
 *
 * Copyright @ 2015 Corpration Name
 *
 *类说明： 查看分配给各位员工的任务
 */

public class ViewAlocatedMission_Amount extends Activity{
	
	private Spinner stuff;
	//private Spinner type;
	private Button setdate;
	private TextView showdates;
	private String yearname, monthname;
	private Button view;
	DialogForMe df;
	public static String mystuff;
	ListView missions;
	public static ListView upmission;
	//SetModelForSpinner smfs;
	public String date1;
	ViewMissionsTask vmtask;
	public static  String targetTime;
	DateCalculate dc=new DateCalculate();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewallocatedmission);
		//界面初始化
		init();
		//获取二级经理的员工
		GetStuffList();
		setdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(ViewAlocatedMission_Amount.this, d, dateAndTime
						.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH),
						dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
				view.setEnabled(true);
			}
		});
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				yearname=String.valueOf(dateAndTime.get(Calendar.YEAR));
				monthname=String.valueOf(dateAndTime.get(Calendar.MONTH)+1);
				
				if (monthname.length()==1)
				{
					monthname="0"+monthname;
				}
				
				targetTime = yearname + "-" + monthname;				
				// TODO Auto-generated method stub
				//if (!(yearname.endsWith("Year") || monthname.endsWith("Month"))) 
				if(!(targetTime.equals("null-null")))
				{					 
					//if (monthname.length()==1)
					//{
					//	monthname="0"+monthname;
					//}
						
					MyApplication.targetTime=targetTime;
					System.out.println("MyApplication.targetTime>>>"+MyApplication.targetTime);
					JSONObject jsObject=new JSONObject();
					try 
					{
						jsObject.put("index", 1);
						jsObject.put("ownerID", mystuff);
						jsObject.put("targetTime", targetTime);
						//jsObject.put("targetType", "Monthly");
						//jsObject.put("type", typename);
					} 
					catch (JSONException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					vmtask=new ViewMissionsTask(ViewAlocatedMission_Amount.this, missions, jsObject,1);
					vmtask.execute(MyApplication.setUrl("viewallocatedmissions"));
					
					missions.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							// TODO Auto-generated method stub
							ListView listView = (ListView) arg0;
							@SuppressWarnings("unchecked")
							HashMap<String, String> map = (HashMap<String, String>) listView
									.getItemAtPosition(arg2);
							MyApplication.MONTH_INDEX=1;
							String type = map.get("type");
							String model = map.get("model");
							String target = map.get("target");
							String targetAmount = map.get("targetAmount");
							Intent ed = new Intent(ViewAlocatedMission_Amount.this,
									UpdateMonthPlan.class);

							ed.putExtra("type", type);
							ed.putExtra("model", model);
							ed.putExtra("target", target);
							ed.putExtra("targetAmount", targetAmount);

							startActivity(ed);

							
						}
					});
				}
				else 
				{
					Toast.makeText(ViewAlocatedMission_Amount.this, "Please choose a peroid of time!", 1).show();
				}
				
			}
		});
	}
	private void init() {
		// TODO Auto-generated method stub
		df=new DialogForMe(ViewAlocatedMission_Amount.this);
		stuff=(Spinner)findViewById(R.id.mstuff);
		setdate=(Button)findViewById(R.id.setdate);
		showdates=(TextView)findViewById(R.id.showdates);
		view=(Button)findViewById(R.id.mview);
		view.setEnabled(false);
		missions=(ListView)findViewById(R.id.missions);
		upmission=(ListView)findViewById(R.id.missions);
		//smfs = new SetModelForSpinner(ViewAlocatedMission.this, type);
	}
	
	// 获取一个日历对象
			Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
			
			// 当点击DatePickerDialog控件的设置按钮时，调用该方法
			DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					
					String month = null;
					
					// 修改日历控件的年，月，日
					// 这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
					dateAndTime.set(Calendar.YEAR, year);
					dateAndTime.set(Calendar.MONTH, monthOfYear);
					dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
					
					// 将页面TextView的显示更新为最新时间
					if (monthOfYear<=9)
					{
						month=dateAndTime.get(Calendar.YEAR)+"-"+
								"0"+Integer.valueOf(dateAndTime.get(Calendar.MONTH)+1);
					}
					else
					{
						month=dateAndTime.get(Calendar.YEAR)+"-"+
								Integer.valueOf(dateAndTime.get(Calendar.MONTH)+1);
					}			

					showdates.setText("The selected month:"+month);
				}
			};
	
	private void GetStuffList() {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			df.showdialog();
			AsyncHttpClient st = new AsyncHttpClient();
			JSONObject params=new JSONObject();
			try {
				params.put("reporterID", MyApplication.userid);
				StringEntity s=new StringEntity(params.toString());
				st.post(ViewAlocatedMission_Amount.this, MyApplication.setUrl("stuff"), s, HTTP.UTF_8,  new AsyncHttpResponseHandler()
				 {

					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						// TODO Auto-generated method stub
						df.canceldialog();
//						Toast.makeText(MissionAlocate.this, null,
//								Toast.LENGTH_SHORT).show();
						System.out.println(new String(arg2));
						JSONArray jsArray;
						try {
							jsArray = new JSONArray(new String(arg2).trim());
							List<String> list = new ArrayList<String>();
							for (int i = 0; i < jsArray.length(); i++) {
								list.add(jsArray.getString(i));
							}
							String[] stuffs = list.toArray(new String[list.size()]);
							ArrayAdapter<String> ty_adaptAdapter = new ArrayAdapter<String>(
									ViewAlocatedMission_Amount.this, android.R.layout.select_dialog_item,
									stuffs);

							stuff.setAdapter(ty_adaptAdapter);

							stuff.setOnItemSelectedListener(new OnItemSelectedListener() {

								@Override
								public void onItemSelected(AdapterView<?> parent,
										View view, int position, long id) {
									// TODO Auto-generated method stub
									mystuff = parent.getItemAtPosition(position).toString();
									MyApplication.MYSTUFF=mystuff;
								}

								@Override
								public void onNothingSelected(AdapterView<?> parent) {
									// TODO Auto-generated method stub

								}
							});
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						// TODO Auto-generated method stub
						df.canceldialog();
						System.out.println("服务器请求失败");
						Toast.makeText(ViewAlocatedMission_Amount.this, MyApplication.serverwrong,
								Toast.LENGTH_SHORT).show();
					}
				});
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	}

}
