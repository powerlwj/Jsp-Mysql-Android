package com.hisense.missions;

import java.util.Calendar;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.hisense.R;
import com.example.hisense.SpecialPlan2;
import com.hisense.asynctaskclasses.MyAsyncTask;
import com.hisense.asynctaskclasses.SpecialAsyncTask;
import com.hisense.asynctaskclasses.ViewMissionsTask;
import com.hisense.myadapters.SetTy_MoforSpinner;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.MyApplication;
import com.hisense.tools.UpdateInfo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ProcessofSpS_AM extends Activity {
	
	private Spinner ty,mod;
	private Button setdate,refresh,from, to;
	private String type,model;
	private ListView plist;
	private TextView pd, ld, nd;
	public String date1;
	private String fyear, fmonth, fday, tyear, tmonth, tday;
	private TextView dateshow;
	SpecialAsyncTask sptask;
	public static  String targetTime,targetTime2;
	SetTy_MoforSpinner stms;
	public static int INDEX = 0;
	DialogForMe df;
	static ListView list;
	private TextView sf, st, shsp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.process_specialmission);
		initview();
		stms.TypeSet();
		from.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(ProcessofSpS_AM.this, d, dateAndTime
						.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH),
						dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
				INDEX = 1;
			}
		});
		to.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(ProcessofSpS_AM.this, d, dateAndTime
						.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH),
						dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
				INDEX = 2;
			}
		});
		
		refresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				UpdateInfo updateinfo = new UpdateInfo();
				// 将时间字符串转化为时间戳
				String fd = fyear + "-" + fmonth + "-" + fday;
				String td = tyear + "-" + tmonth + "-" + tday;
				System.out.println("特殊目标设置时间段为：" + fyear + fmonth + fday
						+ "----" + tyear + tmonth + tday);
				if(!(fd.equals("null-null-null")||td.equals("null-null-null"))){
					if (VerifyDate()) {
						updateinfo.upplandate(pd, nd, ld, fd, td);
						JSONObject jsObject = new JSONObject();
						try {
							jsObject.put("index", 1);
							jsObject.put("type", SetTy_MoforSpinner.type);
							jsObject.put("model", MyAsyncTask.salemodel);
							jsObject.put("targetType", "Special");
							jsObject.put("targetTime", fd);
							jsObject.put("targetTime2", td);
							jsObject.put("ownerID", MyApplication.userid);
							System.out.println("要上传的数据为：" + jsObject);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						sptask = new SpecialAsyncTask(ProcessofSpS_AM.this,
								jsObject, list,3);
						sptask.execute(MyApplication.setUrl("process_special"));

					} else if (!VerifyDate()) {
						Toast.makeText(ProcessofSpS_AM.this, "Time setting Wrong",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(ProcessofSpS_AM.this, "Wrong Operation!",
								Toast.LENGTH_SHORT).show();
					}

				} else {
					Toast.makeText(ProcessofSpS_AM.this,
							"Please Enter a clear period of time",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	private void initview() {
		// TODO Auto-generated method stub
		from = (Button) findViewById(R.id.from);
		to = (Button) findViewById(R.id.to);
		df = new DialogForMe(ProcessofSpS_AM.this);
		refresh = (Button) findViewById(R.id.regresh);
		ty = (Spinner) findViewById(R.id.sptype);
		sf = (TextView) findViewById(R.id.showf);
		st = (TextView) findViewById(R.id.showt);
		pd = (TextView) findViewById(R.id.passday);
		ld = (TextView) findViewById(R.id.leftday);
		nd = (TextView) findViewById(R.id.check_time);
		
		mod = (Spinner) findViewById(R.id.spmodel);
		list = (ListView) findViewById(R.id.specialist);
		stms = new SetTy_MoforSpinner(ProcessofSpS_AM.this, ty, mod);
	}
	
	// 获取一个日历对象
	Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);

	// 当点击DatePickerDialog控件的设置按钮时，调用该方法
	/*DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {

			// 修改日历控件的年，月，日
			// 这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
			String date1 = String.valueOf(year) + "-"
					+ String.valueOf(monthOfYear + 1) + "-"
					+ String.valueOf(dayOfMonth);
			if (INDEX == 1) {
				sf.setText(date1);
				fyear = String.valueOf(year);
				fmonth = String.valueOf(monthOfYear + 1);
				fday = String.valueOf(dayOfMonth);

			} else if (INDEX == 2) {
				st.setText( date1);
				tyear = String.valueOf(year);
				tmonth = String.valueOf(monthOfYear + 1);
				tday = String.valueOf(dayOfMonth);
			} else {
				Toast.makeText(ProcessofSpS_AM.this, "please select the full date",
						Toast.LENGTH_SHORT).show();
			}

		}
	};*/
	
	// 当点击DatePickerDialog控件的设置按钮时，调用该方法
	DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() 
	{
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {

			// 修改日历控件的年，月，日
			// 这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
			String date1 = String.valueOf(year) + "-"
					+ String.valueOf(monthOfYear + 1) + "-"
					+ String.valueOf(dayOfMonth);
				
			if (INDEX == 1) 
			{
				sf.setText(date1);
				
				fyear = String.valueOf(year);
				
				if(monthOfYear<=9)
				{
					fmonth="0"+(monthOfYear+1);
				}
				else
				{
					fmonth=String.valueOf(monthOfYear+1);
				}
				
				if(dayOfMonth<=9)
				{
					fday="0"+dayOfMonth;
				}
				else
				{
					fday=String.valueOf(dayOfMonth);
				}
			} 
			else if (INDEX == 2) 
			{
				st.setText(date1);
				
				tyear = String.valueOf(year);
				
				if(monthOfYear<=9)
				{
					tmonth="0"+(monthOfYear+1);
				}
				else
				{
					tmonth=String.valueOf(monthOfYear+1);
				}
				
				if(dayOfMonth<=9)
				{
					tday="0"+dayOfMonth;
				}
				else
				{
					tday=String.valueOf(dayOfMonth);
				}
			} 
			else 
			{
				Toast.makeText(ProcessofSpS_AM.this, "please select the full date",
						Toast.LENGTH_SHORT).show();
			}

		}
	};
	
	// 判断用户输入的时间是否有误
		public boolean VerifyDate() {

			if (Integer.valueOf(fyear) < Integer.valueOf(tyear)) {
				System.out.println("<<<<<<<<YYYY<<<<<<<<");
				return true;
			} else if (fyear.endsWith(tyear)) {
				System.out.println("=======YYY======");
				if (Integer.valueOf(fmonth) < Integer.valueOf(tmonth)) {
					System.out.println("<<<<<<<<MMMMM<<<<<<<<");
					return true;
				} else if (Integer.valueOf(fmonth) == Integer.valueOf(tmonth)) {
					System.out.println("=======MMMM======");
					if (Integer.valueOf(fday) <= Integer.valueOf(tday)) {
						System.out.println("<<<<<<<<DDDD======");
						return true;
					} else {
						return false;
					}
				}
			}
			return false;
		}

}
