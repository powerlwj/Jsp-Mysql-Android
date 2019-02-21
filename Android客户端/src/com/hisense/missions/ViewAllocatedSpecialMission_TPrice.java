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
import com.hisense.asynctaskclasses.MyAsyncTask;
import com.hisense.asynctaskclasses.SpecialAsyncTask;
import com.hisense.myadapters.SetTy_MoforSpinner;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.MyApplication;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * @author powerliu
 *类说明：特殊计划查看
 */
public class ViewAllocatedSpecialMission_TPrice extends Activity {

	private Button refresh;
	private Spinner sp1, sp2, sp3, sp4, sp5, sp6,ty,mod,stuff;
	private String fyear, fmonth, fday, tyear, tmonth, tday,fd,td;
	private ListView list;
	public static ListView uplist2;
	public static String mystuff;
	MyAsyncTask mytask;
	DialogForMe df;
	SpecialAsyncTask sptask;
	SetTy_MoforSpinner stms;
	private Button from, to;
	private TextView sf, st;
	public static int INDEX = 0;
	int a=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewspecialmission_tp);
		// 界面初始化
		vinit();
		from.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(ViewAllocatedSpecialMission_TPrice.this, d, dateAndTime
						.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH),
						dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
				INDEX = 1;
			}
		});
		to.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(ViewAllocatedSpecialMission_TPrice.this, d, dateAndTime
						.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH),
						dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
				INDEX = 2;
			}
		});
		//从服务器加载商品类型
		stms.TypeSet();
		//获取二级经理的员工
		GetStuffList();
		// 点击按钮响应
		refresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("特殊目标设置时间段为：" + fyear + fmonth + fday
						+ "----" + tyear + tmonth + tday);
			
						//将时间字符串转化为时间戳
						 fd=fyear+"-"+fmonth+"-"+fday;
						 td=tyear+"-"+tmonth+"-"+tday;
						 MyApplication.targetTime=fd;
						 MyApplication.targetTime2=td;
						if(!(fd.equals("null-null-null")||td.equals("null-null-null"))){
						JSONObject jsObject=new JSONObject();
						try {
							jsObject.put("index", 2);
							jsObject.put("type", SetTy_MoforSpinner.type);
//							jsObject.put("model", MyAsyncTask.salemodel);
							jsObject.put("targetType", "Special");
							jsObject.put("targetTime", fd);
							jsObject.put("targetTime2", td);
							//jsObject.put("ownerID", MyApplication.userid);
							jsObject.put("ownerID", mystuff);
							System.out.println("要上传的数据为："+jsObject);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						sptask=new SpecialAsyncTask(ViewAllocatedSpecialMission_TPrice.this, jsObject, list,2);
						sptask.execute(MyApplication.setUrl("specialPlan"));
						
						list.setOnItemClickListener(new OnItemClickListener()
						{
							@Override
							public void onItemClick(AdapterView<?> arg0, View arg1,
									int arg2, long arg3) {
								// TODO Auto-generated method stub
								ListView listView = (ListView) arg0;
								@SuppressWarnings("unchecked")
								HashMap<String, String> map = (HashMap<String, String>) listView
										.getItemAtPosition(arg2);
								MyApplication.SPECIAL_INDEX=2; 
								String targetTime = map.get("from");
								String targetTime2 = map.get("to");
								String target = map.get("target");
								String targetAmount = map.get("targetAmount");
								Intent ed = new Intent(ViewAllocatedSpecialMission_TPrice.this,
										UpdateSpecialPlan.class);
								
								ed.putExtra("type", SetTy_MoforSpinner.type);
								//ed.putExtra("model", "allmodels");
								ed.putExtra("model", SetTy_MoforSpinner.type);
								ed.putExtra("target", target);					
								ed.putExtra("targetAmount", targetAmount);								
								ed.putExtra("targetTime", targetTime);
								ed.putExtra("targetTime2", targetTime2);
								startActivity(ed);								
							}
						});
						
						
			}else
			{
				Toast.makeText(ViewAllocatedSpecialMission_TPrice.this, "Wrong Operation!",
						Toast.LENGTH_SHORT).show();
			}
			}
		});

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
				st.post(ViewAllocatedSpecialMission_TPrice.this, MyApplication.setUrl("stuff"), s, HTTP.UTF_8,  new AsyncHttpResponseHandler()
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
									ViewAllocatedSpecialMission_TPrice.this, android.R.layout.select_dialog_item,
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
						Toast.makeText(ViewAllocatedSpecialMission_TPrice.this, MyApplication.serverwrong,
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
					st.setText(date1);
					tyear = String.valueOf(year);
					tmonth = String.valueOf(monthOfYear + 1);
					tday = String.valueOf(dayOfMonth);
				} else {
					Toast.makeText(ViewAllocatedSpecialMission_TPrice.this, "please select the full date",
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
					Toast.makeText(ViewAllocatedSpecialMission_TPrice.this, "please select the full date",
							Toast.LENGTH_SHORT).show();
				}

			}
		};
  
	private void vinit() {
		// TODO Auto-generated method stub
		from = (Button) findViewById(R.id.vmfrom);
		to = (Button) findViewById(R.id.vmto);
		sf = (TextView) findViewById(R.id.vmd);
		st = (TextView) findViewById(R.id.vmt);
		df=new DialogForMe(ViewAllocatedSpecialMission_TPrice.this);
		refresh = (Button) findViewById(R.id.regresh);
		ty=(Spinner)findViewById(R.id.sptype);
		mod=(Spinner)findViewById(R.id.spmodel);
		mod.setVisibility(View.GONE);
		stuff=(Spinner)findViewById(R.id.mstuff);
		list = (ListView) findViewById(R.id.missions);
		uplist2=(ListView) findViewById(R.id.missions);
		stms=new SetTy_MoforSpinner(ViewAllocatedSpecialMission_TPrice.this, ty, mod);
	}

}