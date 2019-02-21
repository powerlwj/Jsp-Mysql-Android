package com.hisense.missions;

import java.util.Calendar;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import classes_for_JavaBean.Target;

import com.example.hisense.ActivityForOrdinary;
import com.example.hisense.MainActivity;
import com.example.hisense.R;
import com.hisense.asynctaskclasses.SpecialAlertAsyncTask;
import com.hisense.asynctaskclasses.SpecialAsyncTask;
import com.hisense.myadapters.SetTy_MoforSpinner;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.MyApplication;
import com.hisense.tools.UpdateInfo;

public class ProcessofSpSForOD_TP extends Activity {
	
	private Spinner ty,mod;
	private Button setdate,refresh,from, to,shsp;
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
	private TextView sf, st;
	static StringBuilder targetslists=new StringBuilder();
	SpecialAlertAsyncTask salert;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.process_specialmissionforord);
		initview();
		stms.TypeSet();
		from.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(ProcessofSpSForOD_TP.this, d, dateAndTime
						.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH),
						dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
				INDEX = 1;
			}
		});
		to.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(ProcessofSpSForOD_TP.this, d, dateAndTime
						.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH),
						dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
				INDEX = 2;
			}
		});
		shsp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				JSONObject params = new JSONObject();
				try {
				params.put("ownerID", MyApplication.userid);
				salert=new SpecialAlertAsyncTask(ProcessofSpSForOD_TP.this, params);
				salert.execute(MyApplication.setUrl("specialplanalert"));
				} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
				 /*AlertDialog.Builder builder = new AlertDialog.Builder(ProcessofSpSForOD_TP.this);
	                builder.setTitle("Special Target");
	                //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
	                View view = LayoutInflater.from(ProcessofSpSForOD_TP.this).inflate(R.layout.shsp, null);
	                //    设置我们自己定义的布局文件作为弹出框的Content
	                builder.setView(view);
	              final TextView message = (TextView)view.findViewById(R.id.showmessages);
	              message.setText(targetslists);
//	                builder.setMessage(targetslists);
	                builder.show();*/
			}
		});
		
		refresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				UpdateInfo updateinfo = new UpdateInfo();
				String fd = fyear + "-" + fmonth + "-" + fday;
				String td = tyear + "-" + tmonth + "-" + tday;
				System.out.println("特殊目标设置时间段为：" + fyear + fmonth + fday
						+ "----" + tyear + tmonth + tday);
				if(!(fd.equals("null-null-null")||td.equals("null-null-null"))){
				//if (!(fyear.endsWith("Year") || fmonth.endsWith("Month")
				//		|| fday.endsWith("Day") || tyear.endsWith("Yeah")
				//		|| tmonth.endsWith("Month") || tday.endsWith("Day"))) {
					if (VerifyDate()) {
						// 将时间字符串转化为时间戳
						
						MyApplication.targetTime = fd;
						MyApplication.targetTime = td;	
						
						updateinfo.upplandate(pd, nd, ld, fd, td);
						JSONObject jsObject = new JSONObject();
						try {
							jsObject.put("index", 2);
							jsObject.put("type", SetTy_MoforSpinner.type);
							jsObject.put("targetType", "Special");
							jsObject.put("targetTime", fd);
							jsObject.put("targetTime2", td);
							jsObject.put("ownerID", MyApplication.userid);
							System.out.println("要上传的数据为：" + jsObject);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						sptask = new SpecialAsyncTask(ProcessofSpSForOD_TP.this,
								jsObject, list,3);
						sptask.execute(MyApplication.setUrl("process_special"));

					} else if (!VerifyDate()) {
						Toast.makeText(ProcessofSpSForOD_TP.this, "Time setting Wrong",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(ProcessofSpSForOD_TP.this, "Wrong Operation!",
								Toast.LENGTH_SHORT).show();
					}

				} else {
					Toast.makeText(ProcessofSpSForOD_TP.this,
							"Please Enter a clear period of time",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	private void initview() {
		// TODO Auto-generated method stub
		shsp = (Button) findViewById(R.id.showsp);
//		shsp.setVisibility(View.GONE);
		//当为二级经理显示的时候隐藏
		if (!ActivityForOrdinary.targetslist.isEmpty()) {
			shsp.setBackgroundColor(Color.RED);
			int a=ActivityForOrdinary.targetslist.size();
			for (Target ta : ActivityForOrdinary.targetslist) {
				if(ta.getModel().equals("allmodels"))
				{
					targetslists=targetslists.append("Type:"+ta.getType()+",Model:"+ta.getModel()+",targetAmount:"+
				ta.getTargetAmount()+",target:"+ta.getTarget()+",From:"+ta.getTargetTime()+"-to:"+ta.getTargetTime2()+";\n");
				}else if(!ta.getModel().equals("allmodels"))
				{
					targetslists=targetslists.append("Type:"+ta.getType()+",Model:"+ta.getModel()+",Target:"+ta.getTarget()+
							",targetAmount:"+ta.getTargetAmount()+",From"+ta.getTargetTime()+"-to:"+ta.getTargetTime2()+";\n");
				}
				
			}
		}
		from = (Button) findViewById(R.id.from);
		to = (Button) findViewById(R.id.to);
		df = new DialogForMe(ProcessofSpSForOD_TP.this);
		refresh = (Button) findViewById(R.id.regresh);
		ty = (Spinner) findViewById(R.id.sptype);
		sf = (TextView) findViewById(R.id.showf);
		st = (TextView) findViewById(R.id.showt);
		pd = (TextView) findViewById(R.id.passday);
		ld = (TextView) findViewById(R.id.leftday);
		nd = (TextView) findViewById(R.id.check_time);
		mod = (Spinner) findViewById(R.id.spmodel);
		mod.setVisibility(View.GONE);
		list = (ListView) findViewById(R.id.specialist);
		stms = new SetTy_MoforSpinner(ProcessofSpSForOD_TP.this, ty, mod);
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
				st.setText( date1);
				
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
				Toast.makeText(ProcessofSpSForOD_TP.this, "please select the full date",
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
