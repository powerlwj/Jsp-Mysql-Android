package com.example.hisense;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.hisense.tools.DialogForMe;
import com.hisense.tools.MyApplication;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * @author powerliu 类说明：二级经理的信息发送
 */
public class Message_Send extends Activity {

	DialogForMe df;
	private String title, message, receiverID;
	private String readFlag = "0";
	private EditText tit, mess;
	private Spinner stu;
	private Button send;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("Message Send");
		setContentView(R.layout.message_send);
		// 界面初始化
		init();
		// 为Spinner下拉框加载二级经理的员工
//		GetStuffList();
		// 发送信息的按钮
		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				title = tit.getText().toString().trim();
				message = mess.getText().toString().trim();
				if (!(title.equals("") && message.equals(""))) {
					AsyncHttpClient send = new AsyncHttpClient();
					JSONObject jsObject = new JSONObject();
					try {
						jsObject.put("title", title);
						jsObject.put("content", message);
						jsObject.put("readFlag", readFlag);
						jsObject.put("senderID", MyApplication.userid);
						jsObject.put("receiverID", "all");
						jsObject.put("level", MyApplication.ad_type);
						System.out.println("上传数据为：" + jsObject.toString());
						StringEntity s = new StringEntity(jsObject.toString());

						send.post(Message_Send.this, MyApplication.setUrl("addmessage"),
								s, HTTP.UTF_8, new AsyncHttpResponseHandler() {

									@Override
									public void onSuccess(int arg0,
											Header[] arg1, byte[] arg2) {
										// TODO Auto-generated method stub
										if (arg0 == 200) {
											System.out.println("服务器连接成功");
											boolean b = Boolean
													.valueOf(new String(arg2)
															.trim());
											if (b) {
												Toast.makeText(
														Message_Send.this,
														"Successfully",
														Toast.LENGTH_SHORT)
														.show();
												tit.setText("");
												mess.setText("");
											} else {
												Toast.makeText(
														Message_Send.this,
														"Sorry ,Try again",
														Toast.LENGTH_SHORT)
														.show();
											}
										}
									}

									@Override
									public void onFailure(int arg0,
											Header[] arg1, byte[] arg2,
											Throwable arg3) {
										// TODO Auto-generated method stub
										System.out.println("服务器连接失败");
									}
								});
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					Toast.makeText(Message_Send.this,
							"Input 'title and message' ", Toast.LENGTH_SHORT)
							.show();
				}
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
			st.post(Message_Send.this, MyApplication.setUrl("stuff"), s, HTTP.UTF_8,
					new AsyncHttpResponseHandler() {

						@Override
						public void onSuccess(int arg0, Header[] arg1,
								byte[] arg2) {
							// TODO Auto-generated method stub
							df.canceldialog();
							Toast.makeText(Message_Send.this,
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
										Message_Send.this,
										android.R.layout.select_dialog_item,
										stuffs);

								stu.setAdapter(ty_adaptAdapter);

								stu.setOnItemSelectedListener(new OnItemSelectedListener() {

									@Override
									public void onItemSelected(
											AdapterView<?> parent, View view,
											int position, long id) {
										// TODO Auto-generated method stub
										receiverID = parent.getItemAtPosition(
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
							Toast.makeText(Message_Send.this,
									"sorry there is an error,try again",
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

	private void init() {
		// TODO Auto-generated method stub
		df = new DialogForMe(Message_Send.this);
		send = (Button) findViewById(R.id.bt_send);
		tit = (EditText) findViewById(R.id.title);
		mess = (EditText) findViewById(R.id.message);
//		stu = (Spinner) findViewById(R.id.stuffs);

	}

}
