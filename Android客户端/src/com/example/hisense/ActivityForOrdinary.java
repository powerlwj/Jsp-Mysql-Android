package com.example.hisense;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import classes_for_JavaBean.Target;

import com.google.gson.Gson;
import com.hisense.asynctaskclasses.CheckAsyncTask;
import com.hisense.asynctaskclasses.IscheckAsyncTask;
import com.hisense.asynctaskclasses.SpecialAlertAsyncTask;
import com.hisense.asynctaskclasses.SpecialAsyncTask;
import com.hisense.missions.ProcessofSpSForOD_TP;
import com.hisense.missions.TabProcesMonthMission;
import com.hisense.missions.TabProcesSpcilMissionForOrd;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.GetLocation;
import com.hisense.tools.MyApplication;
import com.hisense.tools.SPUtils;
import com.hisense.tools.UpdateInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * @author powerliu
 * 
 *         类说明：这是普通员工的主界面Activity,分布了十多个功能按钮
 * 
 */
public class ActivityForOrdinary extends Activity implements OnClickListener {

	public static int CHECK_INDEX = 0;
	 SharedPreferences checksp;
	SPUtils mydata;
	String date, loc;
	Button s;
	private TextView id, data, location;
	CheckAsyncTask mycheck;
	MyApplication myapp = new MyApplication();
	DialogForMe df;
	SpecialAsyncTask sptask;
	GetLocation check = new GetLocation();
	public static ActivityForOrdinary mcontext;
	private Button add, updatecom, updatesa, mess, month, viewmysales, special,
			checkin, checkout, change, logout, set;
	public static List<Target> targetslist;
	private static Boolean isExit = false;
    private static Boolean hasTask = false;
    static StringBuilder targetslists;
    IscheckAsyncTask ischeck;
    Timer tExit = new Timer();
    SpecialAlertAsyncTask salert;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setTitle("Welcome, have a nice day!");
		setContentView(R.layout.ordinary);
		mcontext = this;
		init();
		// 测试
		// 初始化特殊任務
		Initspecial();
		//
		Is_checkedEvening();
		//特殊任务跳转
		s.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				JSONObject params = new JSONObject();
				try {
					params.put("ownerID", MyApplication.userid);
					salert=new SpecialAlertAsyncTask(ActivityForOrdinary.this, params);
					salert.execute(MyApplication.setUrl("specialplanalert"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// TODO Auto-generated method stub
//				int a=ActivityForOrdinary.targetslist.size();
//				targetslists=new StringBuilder();
//				for (Target ta : ActivityForOrdinary.targetslist) {
//				
//					if(ta.getModel().equals("allmodels"))
//					{
//						targetslists=targetslists.append("Type:"+ta.getType()+",Model:"+ta.getModel()+",TargetPrices:"+
//					ta.getTargetPrices()+",TargetAmount:"+ta.getTargetAmount()+",From:"+ta.getTargetTime()+"-to:"+ta.getTargetTime2()+";\n");
//					}else if(!ta.getModel().equals("allmodels"))
//					{
//						targetslists=targetslists.append("Type:"+ta.getType()+",Model:"+ta.getModel()+",Target:"+ta.getTarget()+
//								",TargetPrices:"+ta.getTargetPrices()+",From"+ta.getTargetTime()+"-to:"+ta.getTargetTime2()+";\n");
//					}
//					
//				}
//				 AlertDialog.Builder builder = new AlertDialog.Builder(ActivityForOrdinary.this);
//	                builder.setTitle("Special Targets");
//	                //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
//	                View view = LayoutInflater.from(ActivityForOrdinary.this).inflate(R.layout.shsp, null);
//	                //    设置我们自己定义的布局文件作为弹出框的Content
//	                builder.setView(view);
//	              final TextView message = (TextView)view.findViewById(R.id.showmessages);
//	              message.setText(targetslists);
//	                builder.show();
			}
		});
//		 int [] i={1};System.out.println(i[4]);
	}


	private void Initspecial() {
		// TODO Auto-generated method stub
		df.showdialog();
		AsyncHttpClient stt = new AsyncHttpClient();
		JSONObject params = new JSONObject();
		try {
			targetslist = new ArrayList<Target>();
			System.out.println("Special Target initing");
			params.put("ownerID", MyApplication.userid);
			StringEntity s = new StringEntity(params.toString());
			stt.post(ActivityForOrdinary.this, MyApplication.setUrl("specialplanalert"),
					s, HTTP.UTF_8, new JsonHttpResponseHandler() {
						@Override
						public void onSuccess(int statusCode, Header[] headers,
								JSONArray response) {
							// TODO Auto-generated method stub
							df.canceldialog();
							
							super.onSuccess(statusCode, headers, response);
							if (statusCode == 200) {
								System.out.println("服务器返回的数据为："
										+ response.toString());
								Gson gson = new Gson();
								Target[] targets = gson.fromJson(
										response.toString(), Target[].class);
								for (int i = 0; i < targets.length; i++) {
									Target t = targets[i];
									System.out.println("特殊计划有："+t.toString());
									targetslist.add(t);
								}

							}
						}

						@Override
						public void onFailure(int statusCode, Header[] headers,
								Throwable throwable, JSONArray errorResponse) {
							// TODO Auto-generated method stub
							df.canceldialog();
							System.out.println("Wrong<><><><>");
							super.onFailure(statusCode, headers, throwable,
									errorResponse);
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

	public static Context getAppContext() {
		// TODO Auto-generated method stub
		return mcontext;
	}

	// 按钮点击响应事件
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 checksp=getSharedPreferences("check", 0);
		switch (v.getId()) {
		// 添加货物
		// case R.id.add:
		// Intent i = new Intent(this, Add.class);
		// startActivity(i);
		// break;
		// 修改密码
		case R.id.change:
			Intent ic = new Intent(ActivityForOrdinary.this,
					ChangePassword.class);
			startActivity(ic);
			break;
		// 更新竞品信息
		case R.id.updatecom:
			Intent iu = new Intent(ActivityForOrdinary.this, UpdateCom.class);
			startActivity(iu);
			break;
		// 上传日销量
		case R.id.updatesale:
			Intent us = new Intent(ActivityForOrdinary.this, SaleGoods.class);
			startActivity(us);
			break;
		// 查看我的销售情况
		case R.id.viewmysales:
			Intent vds = new Intent(ActivityForOrdinary.this,
					ViewMyDailySales.class);
			startActivity(vds);
			break;
		// 查看月销售计划
		case R.id.month:
			Intent imo = new Intent(ActivityForOrdinary.this, TabProcesMonthMission.class);
			startActivity(imo);
			break;
		// 查看消息
		//case R.id.message:
		//	Intent mess = new Intent(ActivityForOrdinary.this, Message.class);
		//	startActivity(mess);
		//	break;
		// 查看特殊计划
		case R.id.special:
			Intent spe = new Intent(ActivityForOrdinary.this, TabProcesSpcilMissionForOrd.class);
			startActivity(spe);
			break;
		// 用户签到
		case R.id.checkin:
			if(!MyApplication.Ismorning_check){
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						ActivityForOrdinary.this);
				dialog.setTitle("Clock In")
						.setIcon(android.R.drawable.ic_dialog_info)
						.setMessage("Clock In Now?")
						.setPositiveButton("YES",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										// 签到功能的调用
										CHECK_INDEX=1;
										String loc = check.getloc();
										mycheck = new CheckAsyncTask(CHECK_INDEX,loc,
												MyApplication.userid,
												ActivityForOrdinary.this,checksp);
										mycheck.execute(MyApplication.setUrl("attence"));
										
									}
								})
						.setNegativeButton("NO",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										dialog.cancel();// 取消弹出框
										Toast.makeText(ActivityForOrdinary.this,
												"Cancled", Toast.LENGTH_SHORT)
												.show();
									}
								}).create().show();
			}else
			{
				Toast.makeText(ActivityForOrdinary.this,
						"sorry,You already check_in", Toast.LENGTH_SHORT)
						.show();
			}
	
			break;
		// 下班签到
		case R.id.checkout:
			if( MyApplication.Ismorning_check)
			{
				if(!MyApplication.Isevenning_check)
				{
					AlertDialog.Builder dialogout = new AlertDialog.Builder(
							ActivityForOrdinary.this);
					dialogout
							.setTitle("Clock Out")
							.setIcon(android.R.drawable.ic_dialog_info)
							.setMessage("Clock IOut Now?")
							.setPositiveButton("YES",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											// 签到功能的调用
											// check=new GetLocation();
											String	loc = check.getloc();
											System.out.println("loc is >>>" + loc);
											CHECK_INDEX=2;
											mycheck = new CheckAsyncTask(CHECK_INDEX,loc,
													MyApplication.userid,
													ActivityForOrdinary.this,checksp);
											mycheck.execute(MyApplication.setUrl("attence"));
											checksp.edit().putBoolean("check_out", MyApplication.Isevenning_check).commit();
											// Toast.makeText(ActivityForOrdinary.this,"位置信息"+loc,
											// 1).show();
										}
									})
							.setNegativeButton("NO",
									new DialogInterface.OnClickListener() {

										public void onClick(DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											dialog.cancel();// 取消弹出框
											Toast.makeText(ActivityForOrdinary.this,
													"Cancled", Toast.LENGTH_SHORT)
													.show();
										}
									}).create().show();
				}else
				{
					Toast.makeText(ActivityForOrdinary.this,
							"sorry,You already check_out", Toast.LENGTH_SHORT)
							.show();
				}
			}else
			{
				Toast.makeText(ActivityForOrdinary.this,
						"sorry,You  din't check_in today,please check_in first", Toast.LENGTH_LONG)
						.show();
			}
		
			break;
		// 注销当前登陆账号
		case R.id.logout:
			AlertDialog.Builder log = new AlertDialog.Builder(
					ActivityForOrdinary.this);
			log.setTitle("Sign Out")
					.setIcon(android.R.drawable.ic_lock_power_off)
					.setMessage("You want to Sign Out?")
					.setPositiveButton("YES",
							new DialogInterface.OnClickListener() {

								@SuppressWarnings("static-access")
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									// 注销登录,跳到注销登录界面
									mydata = new SPUtils();
									mydata.remove(ActivityForOrdinary.this,
											"userID");
									Intent ilogin = new Intent(
											ActivityForOrdinary.this,
											Login.class);
									startActivity(ilogin);
								}
							})
					.setNegativeButton("NO",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									dialog.cancel();// 取消弹出框
									Toast.makeText(ActivityForOrdinary.this,
											"Cancled", Toast.LENGTH_SHORT)
											.show();
								}
							}).create().show();
			break;
		// 设置界面
		case R.id.settings:
			Intent set = new Intent(ActivityForOrdinary.this, Setting.class);
			startActivity(set);
			break;
		default:
			break;
		}
	}
	TimerTask task=new TimerTask() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			isExit = false;
            hasTask = true;
		}
	};
	
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		
		if (keyCode == android.view.KeyEvent.KEYCODE_BACK) {
            if(isExit == false ) {
                isExit = true;
                Toast.makeText(this, "Press  Again to Exit",
                		Toast.LENGTH_SHORT).show();
                if(!hasTask) {
                	tExit.schedule(task, 2000);
                }
            } else {
                finish();
                System.exit(0);
            }}
		return false;
	
	};

	private void Is_checkedMorning() {
		// TODO Auto-generated method stub
		JSONObject jsonObject=new JSONObject();
		try {
			jsonObject.put("cindex", 1);
			jsonObject.put("ID", MyApplication.userid);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ischeck=new IscheckAsyncTask(ActivityForOrdinary.this,jsonObject , 1);
		ischeck.execute(MyApplication.setUrl("ischecked"));
	}
	private void Is_checkedEvening() {
		// TODO Auto-generated method stub
		JSONObject jsonObject=new JSONObject();
		try {
			jsonObject.put("cindex", 2);
			jsonObject.put("ID", MyApplication.userid);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ischeck=new IscheckAsyncTask(ActivityForOrdinary.this, jsonObject, 2);
		ischeck.execute(MyApplication.setUrl("ischecked"));
	}
	private void init() {
		// TODO Auto-generated method stub

		// add = (Button) findViewById(R.id.add);
		// add.setOnClickListener(this);
		df = new DialogForMe(ActivityForOrdinary.this);
		Is_checkedMorning();
		updatecom = (Button) findViewById(R.id.updatecom);
		updatecom.setOnClickListener(this);
		updatesa = (Button) findViewById(R.id.updatesale);
		updatesa.setOnClickListener(this);
		//mess = (Button) findViewById(R.id.message);
		//mess.setOnClickListener(this);
		month = (Button) findViewById(R.id.month);
		month.setOnClickListener(this);
		viewmysales = (Button) findViewById(R.id.viewmysales);
		viewmysales.setOnClickListener(this);
		special = (Button) findViewById(R.id.special);
		special.setOnClickListener(this);
		checkin = (Button) findViewById(R.id.checkin);
		checkin.setOnClickListener(this);
		checkout = (Button) findViewById(R.id.checkout);
		checkout.setOnClickListener(this);
		change = (Button) findViewById(R.id.change);
		change.setOnClickListener(this);
		logout = (Button) findViewById(R.id.logout);
		logout.setOnClickListener(this);
		set = (Button) findViewById(R.id.settings);
		set.setOnClickListener(this);
		

		id = (TextView) findViewById(R.id.id);
		data = (TextView) findViewById(R.id.date);
		location = (TextView) findViewById(R.id.location);
		s=(Button)findViewById(R.id.specialTask);
		UpdateInfo updateinfo = new UpdateInfo();
		updateinfo.update(id, data, location,s);
//		SharedPreferences checkinfo =getSharedPreferences("check", 0);
//		  SharedPreferences.Editor editor=checkinfo.edit();
//		editor.putBoolean("check_in", false).commit();
//		editor.putBoolean("check_out", false).commit();
		
	}

}
