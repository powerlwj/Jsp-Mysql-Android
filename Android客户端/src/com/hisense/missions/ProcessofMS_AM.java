package com.hisense.missions;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.hisense.R;
import com.example.hisense.ViewMonthPlan;
import com.hisense.asynctaskclasses.MyAsyncTask;
import com.hisense.asynctaskclasses.ViewMissionsTask;
import com.hisense.myadapters.SetModelForSpinner;
import com.hisense.myadapters.SetTy_MoforSpinner;
import com.hisense.tools.MyApplication;
import com.hisense.tools.UpdateInfo;

public class ProcessofMS_AM extends Activity {
	
	private Spinner ty,mod;
	private Button setdate,refresh;
	private String type,model;
	private ListView plist;
	private TextView pd, ld, nd;
	public String date1;
	private String typename, yearname, monthname,dayname;
	private TextView dateshow;
	ViewMissionsTask vmtask;
	public static  String targetTime;
	SetTy_MoforSpinner stms;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.process_monthmission);
		
		initview();
		stms.TypeSet();
		setdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(ProcessofMS_AM.this, d, dateAndTime
						.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH),
						dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
				refresh.setEnabled(true);
			}
		});
		
		refresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				yearname=String.valueOf(dateAndTime.get(Calendar.YEAR));
				monthname=String.valueOf(dateAndTime.get(Calendar.MONTH)+1);
				
				if (monthname.length()==1)
				{
					monthname="0"+monthname;
				}
				
				//dayname=String.valueOf(dateAndTime.get(Calendar.DAY_OF_MONTH));
				// TODO Auto-generated method stub
				if (!(yearname.endsWith("Year") || monthname.endsWith("Month"))) 
				{
					//targetTime = yearname + "-" + monthname+"-"+dayname;
					targetTime = yearname + "-" + monthname;
					MyApplication.targetTime=targetTime;
					JSONObject jsObject=new JSONObject();
					try 
					{
						jsObject.put("index", 1);
						jsObject.put("type", SetTy_MoforSpinner.type);
						jsObject.put("model", MyAsyncTask.salemodel);
						jsObject.put("ownerID", MyApplication.userid);
						jsObject.put("targetTime", targetTime);
					} 
					catch (JSONException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					vmtask=new ViewMissionsTask(ProcessofMS_AM.this, plist, jsObject,3);
					vmtask.execute(MyApplication.setUrl("process_ms"));
				}
				else 
				{
					Toast.makeText(ProcessofMS_AM.this, "Please choose a peroid of time!", 1).show();
				}
				
			}
			
		});
	}

	private void initview() {
		// TODO Auto-generated method stub
		dateshow=(TextView)findViewById(R.id.showdate);
		setdate=(Button)findViewById(R.id.psetdate);
		ty= (Spinner) findViewById(R.id.ptype);
		mod=(Spinner)findViewById(R.id.pmodel);
		refresh = (Button) findViewById(R.id.refresh);
		refresh.setEnabled(false);
		pd = (TextView) findViewById(R.id.passday);
		ld = (TextView) findViewById(R.id.leftday);
		nd = (TextView) findViewById(R.id.check_time);
		plist = (ListView) findViewById(R.id.pmonth_list);
		UpdateInfo updateinfo = new UpdateInfo();
		updateinfo.upplan(pd, nd, ld);
		stms = new SetTy_MoforSpinner(ProcessofMS_AM.this, ty, mod);

	}
	
	// 获取一个日历对象
			Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
			
			// 当点击DatePickerDialog控件的设置按钮时，调用该方法
			DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					// 修改日历控件的年，月，日
					// 这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
					dateAndTime.set(Calendar.YEAR, year);
					dateAndTime.set(Calendar.MONTH, monthOfYear);
					dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
					
					// 将页面TextView的显示更新为最新时间
					if ((monthOfYear<=9) && (dayOfMonth>9))
					{
						date1=dateAndTime.get(Calendar.YEAR)+"-"+
								"0"+Integer.valueOf(dateAndTime.get(Calendar.MONTH)+1)+"-"+dateAndTime.get(Calendar.DAY_OF_MONTH);
					}
					else if ((monthOfYear>9) && (dayOfMonth<=9))
					{
						date1=dateAndTime.get(Calendar.YEAR)+"-"+
								Integer.valueOf(dateAndTime.get(Calendar.MONTH)+1)+"-"+"0"+dateAndTime.get(Calendar.DAY_OF_MONTH);					
					}
					else if ((monthOfYear<=9) && (dayOfMonth<=9))
					{
						date1=dateAndTime.get(Calendar.YEAR)+"-"+
								"0"+Integer.valueOf(dateAndTime.get(Calendar.MONTH)+1)+"-"+"0"+dateAndTime.get(Calendar.DAY_OF_MONTH);					
					}
					else
					{
						date1=dateAndTime.get(Calendar.YEAR)+"-"+
								Integer.valueOf(dateAndTime.get(Calendar.MONTH)+1)+"-"+dateAndTime.get(Calendar.DAY_OF_MONTH);
					}
					
					dateshow.setText("The selected date:"+date1);
				}
			};

}
