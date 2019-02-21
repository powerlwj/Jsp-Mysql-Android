package com.example.hisense;

import java.util.Calendar;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hisense.asynctaskclasses.MplanAsyncTask;
import com.hisense.myadapters.SetModelForSpinner;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.MyApplication;
import com.hisense.tools.UpdateInfo;


/**
 * @author powerliu
 *类说明：月计划分配
 */
public class ViewMonthPlan extends Activity {

	private Spinner type, month, year;
	private Button refresh;
	private String typename, yearname, monthname;
	String[] years, months, weeks;
	private TextView pd, ld, nd;
	private ListView monthplanlist;
	SetModelForSpinner smfs;
	MplanAsyncTask mytask;
	private Button  setdate;
	public String date1;
	private TextView dateshow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.monthplan);

		init();
		// 从服务器加载商品类型
		typename = smfs.TypeSet();
		// 为Spinner设置年月周
		setdate=(Button)findViewById(R.id.setdate);
		setdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(ViewMonthPlan.this, d, dateAndTime
						.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH),
						dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		//setAdapterforSpinner();
		// 加载数据
		refresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				yearname = year.getSelectedItem().toString();
//				monthname = month.getSelectedItem().toString();
				yearname=String.valueOf(dateAndTime.get(Calendar.YEAR));
				monthname=String.valueOf(dateAndTime.get(Calendar.MONTH)+1);
				
				if (monthname.length()==1)
				{
					monthname="0"+monthname;
				}
				
				typename = type.getSelectedItem().toString();
				if (!(yearname.endsWith("Year") || monthname.endsWith("Month"))) {
					String targetTime = yearname + "-" + monthname;
					JSONObject jsObject = new JSONObject();
					try {
						jsObject.put("targetType", "Monthly");
						jsObject.put("type", typename);
						//jsObject.put("targetTime", yearname);
						jsObject.put("targetTime", targetTime);
						jsObject.put("ownerID", MyApplication.userid);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					mytask = new MplanAsyncTask(ViewMonthPlan.this, jsObject,
							monthplanlist, monthname);
					mytask.execute(MyApplication.monthTarget);
				} else {
					Toast.makeText(ViewMonthPlan.this,
							"Please choose a peroid of time!", 1).show();
				}
			}
		});
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
	// 界面初始化
	private void init() {
		// TODO Auto-generated method stub
		dateshow=(TextView)findViewById(R.id.dateshow);
		type = (Spinner) findViewById(R.id.sptype);
		refresh = (Button) findViewById(R.id.trefresh);
		pd = (TextView) findViewById(R.id.passday);
		ld = (TextView) findViewById(R.id.leftday);
		nd = (TextView) findViewById(R.id.check_time);
		monthplanlist = (ListView) findViewById(R.id.month_list);
		UpdateInfo updateinfo = new UpdateInfo();
		updateinfo.upplan(pd, nd, ld);

		smfs = new SetModelForSpinner(ViewMonthPlan.this, type);
	}

}