package com.example.hisense;

import java.util.Calendar;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import classes_for_JavaBean.Target;

import com.hisense.asynctaskclasses.MyAsyncTask;
import com.hisense.asynctaskclasses.SpecialAsyncTask;
import com.hisense.missions.ProcessofSpSForOD_AM;
import com.hisense.myadapters.SetTy_MoforSpinner;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.MyApplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
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

/**
 * @author powerliu 类说明：特殊计划查看
 */
public class SpecialPlan extends Activity {

	private Button refresh;
	private Spinner ty, mod;
	private String fyear, fmonth, fday, tyear, tmonth, tday;
	static ListView list;
	MyAsyncTask mytask;
	public static int INDEX = 0;
	DialogForMe df;
	SpecialAsyncTask sptask;
	SetTy_MoforSpinner stms;
	private Button from, to;
	private TextView sf, st, shsp;
	static StringBuilder targetslists=new StringBuilder();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("View Special Plan");
		setContentView(R.layout.special_plan);
		// 界面初始化
		init();
		// 为Spinner设置数据
		from.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(SpecialPlan.this, d, dateAndTime
						.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH),
						dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
				INDEX = 1;
			}
		});
		to.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(SpecialPlan.this, d, dateAndTime
						.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH),
						dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
				INDEX = 2;
			}
		});
		// setDataForSpinner();
		// 从服务器加载商品类型
		stms.TypeSet();
		// 点击按钮响应
		refresh.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				// SpinnerDataWatcher();
				System.out.println("特殊目标设置时间段为：" + fyear + fmonth + fday
						+ "----" + tyear + tmonth + tday);
				if (!(fyear.endsWith("Year") || fmonth.endsWith("Month")
						|| fday.endsWith("Day") || tyear.endsWith("Yeah")
						|| tmonth.endsWith("Month") || tday.endsWith("Day"))) 
				{
					if (VerifyDate()) 
					{
						// 将时间字符串转化为时间戳
						String fd = fyear + "-" + fmonth + "-" + fday;
						String td = tyear + "-" + tmonth + "-" + tday;
						JSONObject jsObject = new JSONObject();
						try 
						{
							jsObject.put("type", SetTy_MoforSpinner.type);
							jsObject.put("model", MyAsyncTask.salemodel);
							jsObject.put("targetType", "Special");
							jsObject.put("targetTime", fd);
							jsObject.put("targetTime2", td);
							jsObject.put("ownerID", MyApplication.userid);
							System.out.println("要上传的数据为：" + jsObject);
						} 
						catch (JSONException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						sptask = new SpecialAsyncTask(SpecialPlan.this,
								jsObject, list,3);
						sptask.execute(MyApplication.setUrl("viewmissionprocess"));

					} 
					else if (!VerifyDate())
					{
						Toast.makeText(SpecialPlan.this, "Time Setting Wrong!",
								Toast.LENGTH_SHORT).show();
					} 
					else 
					{
						Toast.makeText(SpecialPlan.this, "Wrong Operation!",
								Toast.LENGTH_SHORT).show();
					}

				} 
				else 
				{
					Toast.makeText(SpecialPlan.this,
							"Please Enter a clear period of time",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	// 获取一个日历对象
	Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);

	// 当点击DatePickerDialog控件的设置按钮时，调用该方法
	/* DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
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
				Toast.makeText(SpecialPlan.this, "please select the full date",
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
				Toast.makeText(SpecialPlan.this, "please select the full date",
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

	private void init() {
		// TODO Auto-generated method stub
		shsp = (TextView) findViewById(R.id.showsp);
//		shsp.setVisibility(View.GONE);
		//当为二级经理显示的时候隐藏
		if (!ActivityForOrdinary.targetslist.isEmpty()) {
			shsp.setTextColor(Color.RED);
			int a=ActivityForOrdinary.targetslist.size();
			for (Target ta : ActivityForOrdinary.targetslist) {
				if(ta.getModel().equals("allmodels"))
				{
					targetslists=targetslists.append(ta.getType()+"-"+ta.getModel()+"-"+ta.getTargetAmount()+"&"+ta.getTarget()+"--"+ta.getTargetTime()+"to"+ta.getTargetTime2()+";\n");
				}else if(!ta.getModel().equals("allmodels"))
				{
					targetslists=targetslists.append(ta.getType()+"-"+ta.getModel()+"-"+ta.getTarget()+"*"+ta.getMarketPrice()+"--"+ta.getTargetTime()+"to"+ta.getTargetTime2()+";\n");
				}
				
			}
			shsp.setText(targetslists);
			/*shsp.setText("Model:" + ta.getModel() + ",Type:" + ta.getType()
					+ ",Target:" + ta.getTarget() + ",From:"
					+ ta.getTargetTime() + "To:" + ta.getTargetTime2());*/
		}
		from = (Button) findViewById(R.id.from);
		to = (Button) findViewById(R.id.to);
		sf = (TextView) findViewById(R.id.showf);
		st = (TextView) findViewById(R.id.showt);
		df = new DialogForMe(SpecialPlan.this);
		refresh = (Button) findViewById(R.id.regresh);
		ty = (Spinner) findViewById(R.id.sptype);
		mod = (Spinner) findViewById(R.id.spmodel);
		list = (ListView) findViewById(R.id.specialist);
		stms = new SetTy_MoforSpinner(SpecialPlan.this, ty, mod);
	}

}