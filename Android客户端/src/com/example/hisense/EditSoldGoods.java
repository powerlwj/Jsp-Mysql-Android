package com.example.hisense;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.hisense.asynctaskclasses.CheckAsyncTask;
import com.hisense.asynctaskclasses.SumsalesAsynctask;
import com.hisense.asynctaskclasses.ViewSaleAsyncTask;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.MyApplication;
import com.hisense.tools.PhotoUpload;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditSoldGoods  extends Activity{
	
	private String imei,type,model,price,solddate,mystuff;
	private EditText im,ty,mo,pr,sd;
	private Button updata,deldata;
	DialogForMe df;
	ViewSaleAsyncTask vstask;
	SumsalesAsynctask sumtask;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editgoods);
		
		Intent data=getIntent();
		type=data.getStringExtra("type");
		model=data.getStringExtra("model");
		imei=data.getStringExtra("imei");
		price=data.getStringExtra("price");
		mystuff=data.getStringExtra("mystuff");
		solddate=data.getStringExtra("solddate");
		//界面初始化
		init();
		updata.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				type=ty.getText().toString();
				model=mo.getText().toString();
				imei=im.getText().toString();
				solddate=sd.getText().toString();
				price = pr.getText().toString();
				
				System.out.println("测试是否为空"+type+model+price);
				if(!("".equals(model)||"".equals(price)))
				{
				AsyncHttpClient add = new AsyncHttpClient();
				JSONObject params=new JSONObject();
				df.showdialog();
				try {
					params.put("type", type);
					params.put("model", model);
					params.put("soldDate", solddate);
					params.put("soldPrice", price);
					params.put("imei", imei);
					params.put("sellerID", mystuff);
					System.out.println("上传数据为：》》》"+params.toString());
					StringEntity s=new StringEntity(params.toString());
					
					add.post(EditSoldGoods.this, MyApplication.setUrl("updatesoldgoods"), s, HTTP.UTF_8,new AsyncHttpResponseHandler() {

						@Override
						public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
							// TODO Auto-generated method stub
							if (arg0 == 200) {
								boolean b = Boolean.valueOf(new String(arg2).trim());
								if (b) {
									df.canceldialog();
									Toast.makeText(EditSoldGoods.this, "update successfully",
											Toast.LENGTH_SHORT).show();
									JSONObject jsObject = new JSONObject();
									try {
										jsObject.put("sellerID", mystuff);
										jsObject.put("targetTime", ViewSales.vmfd);
										jsObject.put("targetTime2", ViewSales.vmtd);
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									vstask = new ViewSaleAsyncTask(EditSoldGoods.this, ViewSales.lssales, jsObject);
									vstask.execute(MyApplication.setUrl("personalsalehistory"));
									sumtask = new SumsalesAsynctask(EditSoldGoods.this, jsObject
											,ViewSales.top,ViewSales.ton);
									sumtask.execute(MyApplication.setUrl("sumofsales"));
									
								} else {
									df.canceldialog();
									Toast.makeText(EditSoldGoods.this, "Failure",
											Toast.LENGTH_SHORT).show();
								}
							}
						}

						@Override
						public void onFailure(int arg0, Header[] arg1, byte[] arg2,
								Throwable arg3) {
							// TODO Auto-generated method stub
							df.canceldialog();
							Toast.makeText(EditSoldGoods.this, "Unknown failure,try again",
									Toast.LENGTH_SHORT).show();
						}
					});
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else
			{
				Toast.makeText(EditSoldGoods.this, "please input the information completely ", Toast.LENGTH_SHORT).show();
			}
			}
		});
		
		deldata.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						EditSoldGoods.this);
				dialog.setTitle("Delete!!!")
						.setIcon(android.R.drawable.ic_dialog_info)
						.setMessage("Really delete?")
						.setPositiveButton("YES",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										AsyncHttpClient del=new AsyncHttpClient();
										JSONObject params=new JSONObject();
										df.showdialog();
										try {
											params.put("imei", imei);
											System.out.println("上传数据为：》》》"+params.toString());
											StringEntity s=new StringEntity(params.toString());
											
											del.post(EditSoldGoods.this, MyApplication.setUrl("delsoldgoods"), s, HTTP.UTF_8,new AsyncHttpResponseHandler() {

												@Override
												public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
													// TODO Auto-generated method stub
													if (arg0 == 200) {
														boolean b = Boolean.valueOf(new String(arg2).trim());
														if (b) {
															df.canceldialog();
															ty.setText("");mo.setText("");pr.setText("");sd.setText("");
															Toast.makeText(EditSoldGoods.this, "delete successfully",
																	Toast.LENGTH_SHORT).show();
															JSONObject jsObject = new JSONObject();
															try {
																jsObject.put("sellerID", mystuff);
																jsObject.put("targetTime", ViewSales.vmfd);
																jsObject.put("targetTime2", ViewSales.vmtd);
															} catch (JSONException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
															vstask = new ViewSaleAsyncTask(EditSoldGoods.this, ViewSales.lssales, jsObject);
															vstask.execute(MyApplication.setUrl("personalsalehistory"));
															sumtask = new SumsalesAsynctask(EditSoldGoods.this, jsObject
																	,ViewSales.top,ViewSales.ton);
															sumtask.execute(MyApplication.setUrl("sumofsales"));
															
														} else {
															df.canceldialog();
															Toast.makeText(EditSoldGoods.this, "Failure",
																	Toast.LENGTH_SHORT).show();
														}
													}
												}

												@Override
												public void onFailure(int arg0, Header[] arg1, byte[] arg2,
														Throwable arg3) {
													// TODO Auto-generated method stub
													df.canceldialog();
													Toast.makeText(EditSoldGoods.this, "Unknown failure,try again",
															Toast.LENGTH_SHORT).show();
												}
											});
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (UnsupportedEncodingException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								})
						.setNegativeButton("NO",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										dialog.cancel();// 取消弹出框
										Toast.makeText(EditSoldGoods.this,
												"Cancled", Toast.LENGTH_SHORT)
												.show();
									}
								}).create().show();
				
			}
		});
	}
	
	
	private void init() {
		// TODO Auto-generated method stub
		im=(EditText)findViewById(R.id.imei);im.setText(imei);
		ty=(EditText)findViewById(R.id.type);ty.setText(type);
		mo=(EditText)findViewById(R.id.model);mo.setText(model);
		pr=(EditText)findViewById(R.id.price);pr.setText(price);
		sd=(EditText)findViewById(R.id.solddate);sd.setText(solddate);
		updata=(Button)findViewById(R.id.updata);
		deldata=(Button)findViewById(R.id.deldata);		
		df=new DialogForMe(EditSoldGoods.this);
	}

}
