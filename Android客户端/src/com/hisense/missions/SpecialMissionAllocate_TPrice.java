package com.hisense.missions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
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
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hisense.R;
import com.hisense.myadapters.SetTy_MoforSpinner;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.MyApplication;
import com.hisense.tools.NumORChars;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * @author powerliu
 *类说明：二级经理的任务分配
 */
public class SpecialMissionAllocate_TPrice extends Activity {

	private Spinner ty, mod, stuff;
	private Spinner sp1, sp2, sp3, sp4, sp5, sp6;
	private String fyear, fmonth, fday, tyear, tmonth, tday;
	private EditText p1, p2, num;
	private Button alocate;
	private String totalPrice, target;
	public static String mystuff;
	SetTy_MoforSpinner stms;
	DialogForMe df;
	private Button from, to;
	NumORChars noc;
	private String fd,td;
	private TextView sf, st;
	public static int INDEX = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.specialmissionallocate);
		//界面初始化
		init();
		//为Spinner设置数据
		from.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(SpecialMissionAllocate_TPrice.this, d, dateAndTime
						.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH),
						dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
				INDEX = 1;
			}
		});
		to.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(SpecialMissionAllocate_TPrice.this, d, dateAndTime
						.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH),
						dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
				INDEX = 2;
			}
		});
		//setDataForSpinner();
		//加载商品类型
		stms.TypeSet();
		//获取二级经理的员工
		GetStuffList();
		//分配事件
		alocate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				System.out.println("特殊目标设置时间段为：" + fyear + fmonth + fday
						+ "----" + tyear + tmonth + tday);
				
				totalPrice=p2.getText().toString();
				target=num.getText().toString();
				
				//将时间字符串转化为时间戳
            	fd=fyear+"-"+fmonth+"-"+fday;
            	td=tyear+"-"+tmonth+"-"+tday; 
				
				//if(!(fyear.endsWith("Year")||fmonth.endsWith("Month")||fday.endsWith("Day")||tyear.endsWith("Yeah")||tmonth.endsWith("Month")||tday.endsWith("Day")))
				if(!(fd.equals("null-null-null")||td.equals("null-null-null")))
				{
					if (VerifyDate()) 
					{						
						if(!("".equalsIgnoreCase(totalPrice)&&"".equalsIgnoreCase(target)))
						{
							AsyncHttpClient aloc=new AsyncHttpClient();
							JSONObject jsObject=new JSONObject();
							try 
							{
								jsObject.put("index", 2);
								jsObject.put("type", SetTy_MoforSpinner.type);
								//jsObject.put("model", MyAsyncTask.salemodel);
								jsObject.put("ownerID", mystuff);
								jsObject.put("targetType", "Special");
								jsObject.put("targetTime", fd);
								jsObject.put("targetTime2", td);
//								jsObject.put("price", price);
								jsObject.put("targetAmount", totalPrice);
								jsObject.put("target", target);
							
								StringEntity s=new StringEntity(jsObject.toString());
							
								aloc.post(SpecialMissionAllocate_TPrice.this, MyApplication.setUrl("specialmissionalocate"), s, HTTP.UTF_8, new AsyncHttpResponseHandler() 
								{
								
									@Override
									public void onSuccess(int arg0, Header[] arg1, byte[] arg2) 
									{
										// TODO Auto-generated method stub
										if(arg0==200)
										{
											System.out.println("服务器连接成功");
											boolean index=Boolean.valueOf(new String(arg2).trim());
											if(index)
											{
												Toast.makeText(SpecialMissionAllocate_TPrice.this, "Target for "+mystuff+" is successful", Toast.LENGTH_SHORT).show();
												num.setText("");
												p1.setText("");
												p2.setText("");
											}
											else
											{
												Toast.makeText(SpecialMissionAllocate_TPrice.this, "Target for "+mystuff+" is failed", Toast.LENGTH_SHORT).show();
											}
										}
									}
								
									@Override
									public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) 
									{
										// TODO Auto-generated method stub
										Toast.makeText(SpecialMissionAllocate_TPrice.this, "Unknown Error", Toast.LENGTH_SHORT).show();
									}
								});
							} 
							catch (JSONException e) 
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
							catch (UnsupportedEncodingException e) 
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else
						{
							Toast.makeText(SpecialMissionAllocate_TPrice.this,MyApplication.totalinfo, Toast.LENGTH_SHORT).show();
						}
					}
					else if (!VerifyDate()) 
					{
						Toast.makeText(SpecialMissionAllocate_TPrice.this, "Time setting Wrong", Toast.LENGTH_SHORT).show();
					} 
					else 
					{
						Toast.makeText(SpecialMissionAllocate_TPrice.this, "Wrong Operation!", Toast.LENGTH_SHORT).show();
					}
				} 
				else
				{
					Toast.makeText(SpecialMissionAllocate_TPrice.this, "Please Enter a clear period of time", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		from = (Button) findViewById(R.id.from);
		to = (Button) findViewById(R.id.to);
		sf = (TextView) findViewById(R.id.fd);
		st = (TextView) findViewById(R.id.td);
		df=new DialogForMe(SpecialMissionAllocate_TPrice.this);
		ty = (Spinner) findViewById(R.id.mtype);
		mod = (Spinner) findViewById(R.id.mmodel);
		mod.setVisibility(View.GONE);
		stuff = (Spinner) findViewById(R.id.mstuff);
		p1 = (EditText) findViewById(R.id.sprice1);
		p1.setVisibility(View.GONE);
		p2 = (EditText) findViewById(R.id.sprice2);
		num = (EditText) findViewById(R.id.smissionnumber);
		alocate = (Button) findViewById(R.id.salocate);
		
		stms = new SetTy_MoforSpinner(SpecialMissionAllocate_TPrice.this, ty, mod);
		noc=new NumORChars();
	}
	
	private void GetStuffList() {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			df.showdialog();
			AsyncHttpClient st = new AsyncHttpClient();
			JSONObject params=new JSONObject();
			try {
				params.put("reporterID", MyApplication.userid);
				StringEntity s=new StringEntity(params.toString());
				st.post(SpecialMissionAllocate_TPrice.this, MyApplication.setUrl("stuff"), s, HTTP.UTF_8,  new AsyncHttpResponseHandler()
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
									SpecialMissionAllocate_TPrice.this, android.R.layout.select_dialog_item,
									stuffs);

							stuff.setAdapter(ty_adaptAdapter);

							stuff.setOnItemSelectedListener(new OnItemSelectedListener() {

								@Override
								public void onItemSelected(AdapterView<?> parent,
										View view, int position, long id) {
									// TODO Auto-generated method stub
									mystuff = parent.getItemAtPosition(position).toString();
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
						Toast.makeText(SpecialMissionAllocate_TPrice.this, MyApplication.serverwrong,
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
				sf.setText( date1);
				fyear = String.valueOf(year);
				fmonth = String.valueOf(monthOfYear + 1);
				fday = String.valueOf(dayOfMonth);

			} else if (INDEX == 2) {
				st.setText( date1);
				tyear = String.valueOf(year);
				tmonth = String.valueOf(monthOfYear + 1);
				tday = String.valueOf(dayOfMonth);
			} else {
				Toast.makeText(SpecialMissionAllocate_TPrice.this, "please select the full date",
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
				Toast.makeText(SpecialMissionAllocate_TPrice.this, "please select the full date",
						Toast.LENGTH_SHORT).show();
			}

		}
	};

	//判断用户输入的时间是否有误
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
					if (Integer.valueOf(fday)<=Integer.valueOf(tday)) {
						System.out.println("<<<<<<<<DDDD======");
						return true;
					}else{
						return false;
					}
				}
			}
			return false;
		}
		
	// 监测Spinner的点击并实时获取数据
	private void SpinnerDataWatcher() {
		// TODO Auto-generated method stub
		fyear = sp1.getSelectedItem().toString();
		fmonth = sp2.getSelectedItem().toString();
		fday = sp3.getSelectedItem().toString();
		tyear = sp4.getSelectedItem().toString();
		tmonth = sp5.getSelectedItem().toString();
		tday = sp6.getSelectedItem().toString();
	}

	private void setDataForSpinner() {
		// TODO Auto-generated method stub
		String[] arryyear = getResources().getStringArray(R.array.year);
		String[] arraymonth = getResources().getStringArray(R.array.month);
		String[] arryday = getResources().getStringArray(R.array.day);
		ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this,
				android.R.layout.select_dialog_item, arryyear);
		ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this,
				android.R.layout.select_dialog_item, arraymonth);
		ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.select_dialog_item, arryday);
		sp1.setAdapter(yearAdapter);
		sp2.setAdapter(monthAdapter);
		sp3.setAdapter(dayAdapter);
		sp4.setAdapter(yearAdapter);
		sp5.setAdapter(monthAdapter);
		sp6.setAdapter(dayAdapter);
	}

}
