package com.example.hisense;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hisense.tools.MyApplication;
import com.hisense.tools.UpdateInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * @author powerliu
 *类说明：设置界面
 */
public class Setting extends Activity implements OnClickListener{
	
	private TextView id,data,location;
	private Button s;
	String feebContent;
	private RelativeLayout feedback,upgrade,reachus,aboutapp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("Settings");
		setContentView(R.layout.settings);
		//界面初始化
		init();
	}
	//按钮点击响应事件
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		//用户反馈
		case R.id.feedback:
			AlertDialog.Builder builder =new AlertDialog.Builder(this);
			LayoutInflater inflater=getLayoutInflater();
			final View layout=inflater.inflate(R.layout.feedback, null);
			final EditText feedcontent=(EditText)layout.findViewById(R.id.feedcontent);
			builder.setTitle("Enter your comments:");
			builder.setView(layout);
			builder.setNegativeButton("Cancel",null );
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					feebContent=feedcontent.getText().toString();
					//上传反馈
					if(feedcontent.length()>=5)
					{
					System.out.println("反馈内容为：：："+feebContent);
					AsyncHttpClient up=new AsyncHttpClient();
					JSONObject params=new JSONObject();
					try {
						params.put("ID", MyApplication.userid);
						params.put("content", feebContent);
						StringEntity s=new StringEntity(params.toString());
						up.post(Setting.this, MyApplication.setUrl("feedback"), s, HTTP.UTF_8, new JsonHttpResponseHandler()
						{
							@Override
							public void onSuccess(int statusCode, Header[] headers,
									JSONObject response) {
								try {
									System.out.println("dfasgrfgasegr"+response.getString("index").toString());
								} catch (JSONException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								// TODO Auto-generated method stub
								if(statusCode==200)
								{
									try {
										if(response.getString("index").endsWith("true"))
										{
											Toast.makeText(Setting.this, "Feedback's been received ",1).show();
										}else
										{
											Toast.makeText(Setting.this, "Failure",1).show();
										}
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
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
					Toast.makeText(Setting.this, "please input at leat 5 letters",1).show();
				}
				}
			});
			builder.show();

			break;
		//软件升级，可做可不做
		case R.id.upgrade:
			Toast.makeText(Setting.this, "It's the latest version ",Toast.LENGTH_SHORT).show();
			break;
		case R.id.reachus:
			Toast.makeText(Setting.this, "Please call Doctor  Lin of QUST  ",Toast.LENGTH_SHORT).show();
			break;
		case R.id.aboutus:
			Toast.makeText(Setting.this, "Hisense  Software development ",Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
	
	private void init() {
		// TODO Auto-generated method stub
		feedback=(RelativeLayout)findViewById(R.id.feedback);
		feedback.setOnClickListener(this);
		upgrade=(RelativeLayout)findViewById(R.id.upgrade);
		upgrade.setOnClickListener(this);
		reachus=(RelativeLayout)findViewById(R.id.reachus);
		reachus.setOnClickListener(this);
		aboutapp=(RelativeLayout)findViewById(R.id.aboutus);
		aboutapp.setOnClickListener(this);
		
		id=(TextView)findViewById(R.id.id);
		data=(TextView)findViewById(R.id.date);
		location=(TextView)findViewById(R.id.location);
		s=(Button)findViewById(R.id.specialTask);
		s.setVisibility(View.GONE);
		UpdateInfo updateinfo=new UpdateInfo();
		updateinfo.update(id, data, location,s);
	}
	

}
