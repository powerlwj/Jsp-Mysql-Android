package com.example.hisense;

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

import com.hisense.asynctaskclasses.MyAsyncTask;
import com.hisense.asynctaskclasses.SpecialAsyncTask;
import com.hisense.asynctaskclasses.SumsalesAsynctask;
import com.hisense.asynctaskclasses.ViewSaleAsyncTask;
import com.hisense.missions.ProcessofSpSForOD_AM;
import com.hisense.myadapters.SetTy_MoforSpinner;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.MyApplication;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * @author powerliu 类说明：二级经理查看我的员工的销售信息
 */
public class ViewSales extends Activity {

	private Button view;
	public static ListView lssales;
	private Spinner stuff;
	private String mystuff;
	DialogForMe df;
	ViewSaleAsyncTask vstask;
	static TextView top, ton;
	private Button from, to;
	private TextView sf, st;
	public static int INDEX = 0;
	private String fyear, fmonth, fday, tyear, tmonth, tday;
	SumsalesAsynctask sumtask;
	public static String vmfd,vmtd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("View Stuffs'Sales ");
		setContentView(R.layout.view_sales);
		// 界面初始化
		init();
		from.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(ViewSales.this, d, dateAndTime
						.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH),
						dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
				INDEX = 1;
			}
		});
		to.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(ViewSales.this, d, dateAndTime
						.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH),
						dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
				INDEX = 2;
			}
		});
		// 加载我的员工
		GetStuffList();
		// 点击查看响应事件
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// 将时间字符串转化为时间戳
				 vmfd = fyear + "-" + fmonth + "-" + fday;
				 vmtd = tyear + "-" + tmonth + "-" + tday;
				System.out.println(">>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<"+vmfd+"&&"+vmtd);
				if (!(vmfd.equals("null-null-null") ||vmtd.equals("null-null-null"))) {
					JSONObject jsObject = new JSONObject();
					try {
						jsObject.put("sellerID", mystuff);
						jsObject.put("targetTime", vmfd);
						jsObject.put("targetTime2", vmtd);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					vstask = new ViewSaleAsyncTask(ViewSales.this, lssales,
							jsObject);
					vstask.execute(MyApplication.setUrl("personalsalehistory"));
					sumtask = new SumsalesAsynctask(ViewSales.this, jsObject,
							top, ton);
					sumtask.execute(MyApplication.setUrl("sumofsales"));
				} 
				else {
					Toast.makeText(ViewSales.this, "Time setting Wrong",
							Toast.LENGTH_SHORT).show();
				}

				/*
				 * } else if (!VerifyDate()) { Toast.makeText(ViewSales.this,
				 * "Time setting Wrong", Toast.LENGTH_SHORT).show(); } else {
				 * 
				 * }else { Toast.makeText(ViewSales.this,
				 * "Please Enter a clear period of time",
				 * Toast.LENGTH_SHORT).show(); }
				 */

				// 长按响应
				lssales.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						ListView listView = (ListView) arg0;
						@SuppressWarnings("unchecked")
						HashMap<String, String> map = (HashMap<String, String>) listView
								.getItemAtPosition(arg2);
						String type = map.get("type");
						String model = map.get("model");
						String imei = map.get("imei");
						String price = map.get("price");
						String solddate = map.get("solddate");
						Intent ed = new Intent(ViewSales.this,
								EditSoldGoods.class);

						ed.putExtra("type", type);
						ed.putExtra("model", model);
						ed.putExtra("type", type);
						ed.putExtra("imei", imei);
						ed.putExtra("mystuff", mystuff);
						ed.putExtra("price", price);
						ed.putExtra("solddate", solddate);

						startActivity(ed);

					}
				});
			}
		});
	}

	private void GetStuffList() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		df.showdialog();
		AsyncHttpClient st = new AsyncHttpClient();
		JSONObject params = new JSONObject();
		try {
			params.put("reporterID", MyApplication.userid);
			StringEntity s = new StringEntity(params.toString());
			st.post(ViewSales.this, MyApplication.stuff, s, HTTP.UTF_8,
					new AsyncHttpResponseHandler() {

						@Override
						public void onSuccess(int arg0, Header[] arg1,
								byte[] arg2) {
							// TODO Auto-generated method stub
							df.canceldialog();
							Toast.makeText(ViewSales.this,
									"loading successfully", Toast.LENGTH_LONG)
									.show();
							System.out.println(new String(arg2));
							JSONArray jsArray;
							try {
								jsArray = new JSONArray(new String(arg2).trim());
								List<String> list = new ArrayList<String>();
								for (int i = 0; i < jsArray.length(); i++) {
									list.add(jsArray.getString(i));
								}
								String[] stuffs = list.toArray(new String[list
										.size()]);
								ArrayAdapter<String> ty_adaptAdapter = new ArrayAdapter<String>(
										ViewSales.this,
										android.R.layout.select_dialog_item,
										stuffs);

								stuff.setAdapter(ty_adaptAdapter);

								stuff.setOnItemSelectedListener(new OnItemSelectedListener() {

									@Override
									public void onItemSelected(
											AdapterView<?> parent, View view,
											int position, long id) {
										// TODO Auto-generated method stub
										mystuff = parent.getItemAtPosition(
												position).toString();
									}

									@Override
									public void onNothingSelected(
											AdapterView<?> parent) {
										// TODO Auto-generated method stub

									}
								});
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

						@Override
						public void onFailure(int arg0, Header[] arg1,
								byte[] arg2, Throwable arg3) {
							// TODO Auto-generated method stub
							df.canceldialog();
							System.out.println("服务器请求失败");
							Toast.makeText(ViewSales.this,
									MyApplication.serverwrong,
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
				st.setText(date1);
				tyear = String.valueOf(year);
				tmonth = String.valueOf(monthOfYear + 1);
				tday = String.valueOf(dayOfMonth);
			} else {
				Toast.makeText(ViewSales.this, "please select the full date",
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
				Toast.makeText(ViewSales.this, "please select the full date",
						Toast.LENGTH_SHORT).show();
			}

		}
	};

	private void init() {
		// TODO Auto-generated method stub
		from = (Button) findViewById(R.id.vfrom);
		to = (Button) findViewById(R.id.vto);
		sf = (TextView) findViewById(R.id.vfd);
		st = (TextView) findViewById(R.id.vtd);
		top = (TextView) findViewById(R.id.totalp);
		ton = (TextView) findViewById(R.id.totalprices);
		df = new DialogForMe(ViewSales.this);
		view = (Button) findViewById(R.id.view);
		lssales = (ListView) findViewById(R.id.rsales);
		stuff = (Spinner) findViewById(R.id.stuff);
	}

}
