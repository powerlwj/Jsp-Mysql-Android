package com.example.hisense;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import classes_for_JavaBean.SoldGoods;

import com.google.gson.Gson;
import com.hisense.missions.ProcessofSpSForOD_AM;
import com.hisense.missions.ViewAllocatedSpecialMission_Amount;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.MyApplication;
import com.hisense.tools.UpdateInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * @author powerliu
 *类说明：　销售员工自己查看自己的日销量信息
 */
public class ViewMyDailySales extends Activity{
	
	private TextView t1,t2,t3,id,location,date;
	private String fyear, fmonth, fday, tyear, tmonth, tday,fd,td;
	private TextView tnum,tpri;
	private TextView sf, st;
	private Button s,from,to,view;
	private ListView listview;
	DialogForMe df;
	public static int INDEX = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("View My Daily Sales ");
		setContentView(R.layout.viewmysales);
		
		ActionBar ac=this.getActionBar();
		ac.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP,ActionBar.DISPLAY_HOME_AS_UP);
		//界面初始哈
		init();
		from.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(ViewMyDailySales.this, d, dateAndTime
						.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH),
						dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
				INDEX = 1;
			}
		});
		to.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(ViewMyDailySales.this, d, dateAndTime
						.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH),
						dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
				INDEX = 2;
			}
		});
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println("特殊目标设置时间段为：" + fyear + fmonth + fday
						+ "----" + tyear + tmonth + tday);
			
						//将时间字符串转化为时间戳
						 fd=fyear+"-"+fmonth+"-"+fday;
						 td=tyear+"-"+tmonth+"-"+tday;
						 MyApplication.targetTime=fd;
						 MyApplication.targetTime2=td;
				df.showdialog();
				AsyncHttpClient st = new AsyncHttpClient();
				if(!(fd.equals("null-null-null")||td.equals("null-null-null"))){
				JSONObject params=new JSONObject();
				try {
					final List<SoldGoods> goodslist = new ArrayList<SoldGoods>();
					params.put("sellerID", MyApplication.userid);
					params.put("time1", fd);
					params.put("time2", td);
					StringEntity s=new StringEntity(params.toString());
					st.post(ViewMyDailySales.this, MyApplication.setUrl("viewmysales"), s,
							HTTP.UTF_8,  new JsonHttpResponseHandler(){
						@Override
						public void onSuccess(int statusCode,
								Header[] headers, JSONArray response) {
							// TODO Auto-generated method stub
							super.onSuccess(statusCode, headers, response);
							if(statusCode==200)
							{
								df.canceldialog();
								System.out.println("服务器连接成功");
								System.out.println("服务器返回的数据为：" + response.toString());
								Gson gson = new Gson();
								SoldGoods[] soldGoods = gson.fromJson(response.toString(),
										SoldGoods[].class);
								for (int i = 0; i < soldGoods.length; i++) {
									SoldGoods soldGoods2 = soldGoods[i];
									goodslist.add(soldGoods2);
								}
//								加载数据
								dateset(goodslist);
							}
						}
						@Override
								public void onFailure(int statusCode,
										Header[] headers, Throwable throwable,
										JSONArray errorResponse) {
									// TODO Auto-generated method stub
									super.onFailure(statusCode, headers, throwable, errorResponse);
								}
					});
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else
			{
				Toast.makeText(ViewMyDailySales.this, "Wrong Operation!",
						Toast.LENGTH_SHORT).show();
			}
			
			}
		});
		
		
	}
	
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.returnset, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Intent i=new Intent(ViewMyDailySales.this, SalesReturnManage.class);
		startActivity(i);
		return super.onOptionsItemSelected(item);			
	}
	*/
	
	private void init() {
		// TODO Auto-generated method stub
		from = (Button) findViewById(R.id.vmmfro);
		to = (Button) findViewById(R.id.vmmto);
		sf = (TextView) findViewById(R.id.vmmd);
		st = (TextView) findViewById(R.id.vmmt);
		df=new DialogForMe(ViewMyDailySales.this);
		tnum=(TextView)findViewById(R.id.totalnum);
		tpri=(TextView)findViewById(R.id.totalpri);
		listview=(ListView)findViewById(R.id.vsales);
		view=(Button)findViewById(R.id.viewmysales);
		df=new DialogForMe(ViewMyDailySales.this);
		id=(TextView)findViewById(R.id.id);
		date=(TextView)findViewById(R.id.date);
		location=(TextView)findViewById(R.id.location);
		s=(Button)findViewById(R.id.specialTask);
		s.setVisibility(View.GONE);
		UpdateInfo updateinfo=new UpdateInfo();
		updateinfo.update(id, date, location,s);
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
						Toast.makeText(ViewMyDailySales.this, "please select the full date",
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
						Toast.makeText(ViewMyDailySales.this, "please select the full date",
								Toast.LENGTH_SHORT).show();
					}

				}
			};
	
	protected void dateset(List<SoldGoods> result) {
		// TODO Auto-generated method stub
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		int totalprice=0;
		for (int i = 0; i < result.size(); i++) {
			Map<String, String> listitem = new HashMap<String, String>();
			listitem.put("type",String.valueOf(result.get(i).getType()));
				listitem.put("model", result.get(i).getModel());
				listitem.put("imei",String.valueOf(result.get(i).getImei()));
				listitem.put("soldPrice",String.valueOf(result.get(i).getSoldPrice()));
				list.add(listitem);
				totalprice=totalprice+Integer.valueOf(result.get(i).getSoldPrice());
			}

		SimpleAdapter adapter = new SimpleAdapter(ViewMyDailySales.this, list,
				R.layout.view_salelist, new String[] { "type", "model",
						"imei","soldPrice" }, new int[] { R.id.ttype,
						R.id.tmodel, R.id.timei ,R.id.tprice});
		if (result.size() == 0) {
			Toast.makeText(ViewMyDailySales.this, "None Data searched", Toast.LENGTH_SHORT).show();
			listview.setAdapter(adapter);
			tnum.setText("Totalnumber:0");
			tpri.setText("TotalPrice:0");
		} else {
			System.out.println("adapter.toString()>>>>"+adapter.toString());
			listview.setAdapter(adapter);
			tnum.setText("Totalnumber:"+result.size());
			tpri.setText("TotalPrice:"+totalprice);
		}

	}

}
