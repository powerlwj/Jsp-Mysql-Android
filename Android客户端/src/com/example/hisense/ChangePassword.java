package com.example.hisense;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.hisense.tools.DialogForMe;
import com.hisense.tools.MyApplication;
import com.hisense.tools.UpdateInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * @author powerliu
 *类说明：用户密码修改
 */
public class ChangePassword extends Activity 
{

	private EditText old, new1, new2;
	private Button change,s;
	private TextView id,date,location;
	DialogForMe df;
	private String oldpassword, newpassw1, newpassw2;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("Password change");
		setContentView(R.layout.change_password);
		// 初始化
		init();
		// 确认修改密码,村数据库中比对(建议写一个全局类MyApplication用于记录一些用户信息,将来非常有用)
		change.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				
				AsyncHttpClient change = new AsyncHttpClient();
				oldpassword = old.getText().toString();
				newpassw1 = new1.getText().toString();
				newpassw2 = new2.getText().toString();
				if(!(oldpassword.length()==0||newpassw1.length()==0||newpassw2.length()==0))//是否3个文本框都输入了数据？3个文本框都必须有有东西，不能空着！
				{
					//if (newpassw1.endsWith(newpassw2)) //两次输入的新密码是否一致？
					if (newpassw1.equals(newpassw2)) //两次输入的新密码是否一致？
					{
						if(oldpassword.endsWith(MyApplication.password)) //输入的旧密码是否正确？
						{
							df.showdialog("change password", "Password Changing...");
					
					JSONObject params=new JSONObject();
					try {
						params.put("ID", MyApplication.userid);
						params.put("password", newpassw1);
						StringEntity s=new StringEntity(params.toString());
						change.post(ChangePassword.this, MyApplication.setUrl("changepassword"), s, HTTP.UTF_8, new AsyncHttpResponseHandler() {
							
								@Override
								public void onSuccess(int arg0, Header[] arg1,
									byte[] arg2) {
									// TODO Auto-generated method stub
									if (arg0 == 200) {
										boolean b = Boolean.valueOf(new String(
											arg2).trim());
										if (b) {
											Toast.makeText(ChangePassword.this,
												"successfully",
												Toast.LENGTH_SHORT).show();
										} else {
											Toast.makeText(ChangePassword.this,
												"Failure,try again",
												Toast.LENGTH_SHORT).show();
										}
									}
									MyApplication.password = newpassw1;
									df.canceldialog();
									old.setText("");
									new1.setText("");
									new2.setText("");
								}

								@Override
								public void onFailure(int arg0, Header[] arg1,
									byte[] arg2, Throwable arg3) {
									// TODO Auto-generated method stub
									df.canceldialog();
									Toast.makeText(ChangePassword.this,
										"Unknown Error,try again", Toast.LENGTH_SHORT)
										.show();
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
							Toast.makeText(ChangePassword.this, "The old password is wrong",
								Toast.LENGTH_SHORT).show();
							old.setText("");
						}
					} 
					else 
					{
						Toast.makeText(ChangePassword.this, "make sure the two input new password is same",
								Toast.LENGTH_SHORT).show();
						new2.setText("");
					}
				}
				else
				{
					Toast.makeText(ChangePassword.this, "please input the information completely", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		df=new DialogForMe(ChangePassword.this);
		old = (EditText) findViewById(R.id.oldpass);
		new1 = (EditText) findViewById(R.id.newpass1);
		new2 = (EditText) findViewById(R.id.newpass2);
		change = (Button) findViewById(R.id.change);
		
		id=(TextView)findViewById(R.id.id);
		date=(TextView)findViewById(R.id.date);
		location=(TextView)findViewById(R.id.location);
		s=(Button)findViewById(R.id.specialTask);
		s.setVisibility(View.GONE);
		UpdateInfo updateinfo=new UpdateInfo();
		updateinfo.update(id, date, location,s);
	}
}