package com.example.hisense;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hisense.networkclasses.NetUtils;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.MyApplication;
import com.hisense.tools.SPUtils;
import com.hisense.tools.UpdateInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * @author powerliu
 *类说明：用户登录
 */
public class Login extends Activity{
	
	private EditText uesr,pass,ip;
	private Button log,cal,test1,test2;
	private String id,password,ipaddress;
	SPUtils userdata;
	private final static float TARGET_HEAP_UTILIZATION = 0.75f; 
	DialogForMe df;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		VMRuntime.getRuntime().setTargetHeapUtilization(TARGET_HEAP_UTILIZATION);
		final SharedPreferences mydata=getSharedPreferences("URL", MODE_PRIVATE);
		setTitle("Login");
		setContentView(R.layout.login);
		// 初始化
		init();
		//网络监测
		net();
//		int[] i={1};System.out.println(i[7]);
		log.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ipaddress=ip.getText().toString().trim();
				MyApplication.ipaddress=ipaddress;
				MyApplication.URL="http://"+ipaddress+":8080/hs/app/";
				System.out.println(MyApplication.ipaddress+"++++"+MyApplication.URL);
				System.out.println(">>>>>>>>>"+MyApplication.URL+"userlogin.jsp");
				id=uesr.getText().toString();
				password=pass.getText().toString();
				net();
				// TODO Auto-generated method stub
				if(!(id.equals("")||password.equals(""))){
				df.showdialog();
				AsyncHttpClient login=new AsyncHttpClient();
				JSONObject params = new JSONObject();
			
				try {
					params.put("ID", id);
					params.put("password", password);
					StringEntity s=new StringEntity(params.toString());
					System.out.println("用户登录信息："+id+"--"+password+"--"+ipaddress);
						login.post(Login.this, MyApplication.setUrl("userlogin"), s, HTTP.UTF_8, new JsonHttpResponseHandler() {
//					login.post(Login.this, MyApplication.URL+"userlogin.jsp", s, HTTP.UTF_8, new JsonHttpResponseHandler() {
							@SuppressWarnings("static-access")
							@Override
							public void onSuccess(int statusCode,
									Header[] headers, JSONObject response) {
								userdata=new SPUtils();
								String type="";
								System.out.println("成功连接到服务器");
								if(statusCode==200)
								{
									//登陆成功要返回id,location
									try {
										df.canceldialog();
										userdata.put(Login.this, "userID", id);
										userdata.put(Login.this, "password", password);
										userdata.put(Login.this, "location", response.get("region"));
										userdata.put(Login.this, "own", response.get("own"));
										userdata.put(Login.this, "Is_Special", response.get("Is_Special"));
										MyApplication.userid=id;
										MyApplication.password=password;
										MyApplication.location=response.get("region").toString();
										MyApplication.Is_special=(boolean) response.get("Is_Special");
										MyApplication.own=response.getString("own");
										type=response.getString("ad_type");
										MyApplication.ad_type=type;
										System.out.println("LOGCAT>>>"+response.toString());
										if(response.get("password").toString().equals(password))
										{
											if(type.endsWith("5"))
											{
												Toast.makeText(Login.this, MyApplication.userid+" "+"login successfully", Toast.LENGTH_SHORT).show();
												Intent i=new Intent(Login.this, ActivityForOrdinary.class);
												finish();
												startActivity(i);
											}else if(type.endsWith("4"))
											{
												Toast.makeText(Login.this, MyApplication.userid+" "+"login successfully", Toast.LENGTH_SHORT).show();
												Intent i=new Intent(Login.this, MainActivity.class);
												finish();
												startActivity(i);
											}else
											{
												Toast.makeText(Login.this, "Unknown  wrong", Toast.LENGTH_SHORT).show();
											}
										}else
										{
											Toast.makeText(Login.this, "Wrong Password!", Toast.LENGTH_SHORT).show();
										}
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
								}
							}
							
							public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
								df.canceldialog();
								Toast.makeText(Login.this, "Error ,sorry", Toast.LENGTH_SHORT).show();
							};
							
						});
				} catch (JSONException | UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else
			{
				Toast.makeText(Login.this, "please input  imformation completely", Toast.LENGTH_SHORT).show();
			}
			}
		});
		
		
	}

	private void net() {
		// TODO Auto-generated method stub
		if(!NetUtils.isConnected(Login.this))
		{
			Toast.makeText(Login.this, "Unable to connect to the network,Please check it", 1).show();
		}
	}

	private void init() {
		// TODO Auto-generated method stub
		df=new DialogForMe(Login.this, "Logining", "please wait");
		uesr=(EditText)findViewById(R.id.username);
		pass=(EditText)findViewById(R.id.password);
		log=(Button)findViewById(R.id.login);
		ip=(EditText)findViewById(R.id.ip);
		ip.setText(MyApplication.ipaddress);
	}


}

 