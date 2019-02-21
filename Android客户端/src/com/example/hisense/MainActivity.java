package com.example.hisense;

import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hisense.asynctaskclasses.CheckAsyncTask;
import com.hisense.asynctaskclasses.IscheckAsyncTask;
import com.hisense.missions.TabProcesMonthMission;
import com.hisense.missions.TabProcesSpcilMission;
import com.hisense.missions.TabofMonthMission;
import com.hisense.missions.TabofMonthMissionView;
import com.hisense.missions.TabofSpecialMission;
import com.hisense.missions.TabofSpecialMissionView;
import com.hisense.tools.GetLocation;
import com.hisense.tools.MyApplication;
import com.hisense.tools.SPUtils;
import com.hisense.tools.UpdateInfo;

/**
 * @author powerliu 类说明：二级经理主界面，与普通员工基本相同，不做过多注释
 */
public class MainActivity extends Activity implements OnClickListener {

	public static int CHECK_INDEX=0;
	SPUtils mydata;
	 SharedPreferences checksp;
	String date;
	GetLocation check = new GetLocation();
	private TextView id, data, location;
	CheckAsyncTask mycheck;
	private Button add, stock, chart, mission, specialmission,
			viewspecialmission, viewstock, viewmission, viewcom, sendmess,
			mess, month, special, viewsales, checkin,checkout, change, logout, set,s;
	private static Boolean isExit = false;
    private static Boolean hasTask = false;
    Timer tExit = new Timer();
    IscheckAsyncTask ischeck;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setTitle("Welcome, have a nice day!");
		setContentView(R.layout.activity_main);
		// 初始化
		init();
		Is_checkedEvening();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 checksp=getSharedPreferences("check", 0);
		switch (v.getId()) {
		 //case R.id.add:
		 //Intent i=new Intent(this, Add.class);
		 //startActivity(i);
		 //break;
		case R.id.change:
			Intent ic = new Intent(MainActivity.this, ChangePassword.class);
			startActivity(ic);
			break;
		case R.id.viewcom:
			Intent iu = new Intent(MainActivity.this, ViewComInfo.class);
			startActivity(iu);
			break;
		case R.id.mission:
			Intent mss = new Intent(MainActivity.this, TabofMonthMission.class);
			startActivity(mss);
			break;
		case R.id.viewmymission:
			Intent vam = new Intent(MainActivity.this,
					TabofMonthMissionView.class);
			startActivity(vam);
			break;
		case R.id.specialmission:
			Intent sm = new Intent(MainActivity.this,
					TabofSpecialMission.class);
			startActivity(sm);
			break;
		case R.id.viewmyspecialmission:
			Intent vmsm = new Intent(MainActivity.this,
					TabofSpecialMissionView.class);
			startActivity(vmsm);
			break;
		case R.id.viewsales:
			Intent vs = new Intent(MainActivity.this, ViewSales.class);
			startActivity(vs);
			break;
		// case R.id.stock:
		// Intent is=new Intent(MainActivity.this, UpdateStock.class);
		// startActivity(is);
		// break;
		case R.id.viewmystock:
			Intent vms = new Intent(MainActivity.this, ViewMyStock.class);
			startActivity(vms);
			break;
		//case R.id.message_send:
		//	Intent ms = new Intent(MainActivity.this, Message_Send.class);
		//	startActivity(ms);
		//	break;

		//case R.id.message:
		//	Intent im = new Intent(MainActivity.this, Message.class);
		//	startActivity(im);
		//	break;
		case R.id.month:
//			Intent imo = new Intent(MainActivity.this, ViewMonthPlan.class);
			Intent imo = new Intent(MainActivity.this, TabProcesMonthMission.class);
			startActivity(imo);
			break;
		case R.id.sspecial:
//			Intent sp = new Intent(MainActivity.this, SpecialPlan2.class);
			Intent sp = new Intent(MainActivity.this, TabProcesSpcilMission.class);
			startActivity(sp);
			break;
		//case R.id.chart:
		//	Intent sa = new Intent(MainActivity.this, SaleAnalysis.class);
		//	startActivity(sa);
		//	break;
		case R.id.checkin:
			if(!MyApplication.Ismorning_check){
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						MainActivity.this);
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
												MainActivity.this,checksp);
										mycheck.execute(MyApplication.setUrl("attence"));
									}
								})
						.setNegativeButton("NO",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										dialog.cancel();// 取消弹出框
										Toast.makeText(MainActivity.this,
												"Cancled", Toast.LENGTH_SHORT)
												.show();
									}
								}).create().show();
			}else
			{
				Toast.makeText(MainActivity.this,
						"sorry,You already check_in", Toast.LENGTH_SHORT)
						.show();
			}
	
			break;
		case R.id.checkout:
			if( MyApplication.Ismorning_check)
			{
				if(!MyApplication.Isevenning_check)
				{
					AlertDialog.Builder dialogout = new AlertDialog.Builder(
							MainActivity.this);
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
													MainActivity.this,checksp);
											mycheck.execute(MyApplication.setUrl("attence"));
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
											Toast.makeText(MainActivity.this,
													"Cancled", Toast.LENGTH_SHORT)
													.show();
										}
									}).create().show();
				}else
				{
					Toast.makeText(MainActivity.this,
							"sorry,You already check_out", Toast.LENGTH_SHORT)
							.show();
				}
			}else
			{
				Toast.makeText(MainActivity.this,
						"sorry,You  din't check_in today,please check_in first", Toast.LENGTH_LONG)
						.show();
			}
		

			break;
		case R.id.logout:
			AlertDialog.Builder log = new AlertDialog.Builder(MainActivity.this);
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
									mydata.remove(MainActivity.this, "userID");
									Intent ilogin = new Intent(
											MainActivity.this, Login.class);
									startActivity(ilogin);
								}
							})
					.setNegativeButton("NO",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									dialog.cancel();// 取消弹出框
									Toast.makeText(MainActivity.this,
											"Cancled", Toast.LENGTH_SHORT)
											.show();
								}
							}).create().show();
			break;
		case R.id.settings:
			Intent set = new Intent(MainActivity.this, Setting.class);
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
		ischeck=new IscheckAsyncTask(MainActivity.this,jsonObject , 1);
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
		ischeck=new IscheckAsyncTask(MainActivity.this, jsonObject, 2);
		ischeck.execute(MyApplication.setUrl("ischecked"));
	}
	
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
	
	private void init() {
		// TODO Auto-generated method stub
		Is_checkedMorning();
		//add=(Button)findViewById(R.id.add);
		//add.setOnClickListener(this);
		//stock=(Button)findViewById(R.id.stock);
		//stock.setOnClickListener(this);
		viewstock = (Button) findViewById(R.id.viewmystock);
		viewstock.setOnClickListener(this);
		viewcom = (Button) findViewById(R.id.viewcom);
		viewcom.setOnClickListener(this);
		viewsales = (Button) findViewById(R.id.viewsales);
		viewsales.setOnClickListener(this);
		//sendmess = (Button) findViewById(R.id.message_send);
		//sendmess.setOnClickListener(this);
		//mess = (Button) findViewById(R.id.message);
		//mess.setOnClickListener(this);
		mission = (Button) findViewById(R.id.mission);
		mission.setOnClickListener(this);
		viewmission = (Button) findViewById(R.id.viewmymission);
		viewmission.setOnClickListener(this);
		specialmission = (Button) findViewById(R.id.specialmission);
		specialmission.setOnClickListener(this);
		viewspecialmission = (Button) findViewById(R.id.viewmyspecialmission);
		viewspecialmission.setOnClickListener(this);
		month = (Button) findViewById(R.id.month);
		month.setOnClickListener(this);
//二级经理查看一级经理为其分配的任务
		special = (Button) findViewById(R.id.sspecial);
//		special.setVisibility(View.GONE);
		special.setOnClickListener(this);
		//chart = (Button) findViewById(R.id.chart);
		//chart.setOnClickListener(this);
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
		s.setVisibility(View.GONE);
		UpdateInfo updateinfo = new UpdateInfo();
		updateinfo.update(id, data, location,s);

	}

}
