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
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.hisense.asynctaskclasses.MyAsyncTask;
import com.hisense.missions.TabProcesSpcilMissionForOrd;
import com.hisense.myadapters.SetTy_MoforSpinner;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.MyApplication;
import com.hisense.tools.UpdateInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mining.app.imei.scan.MipcaActivityCapture;

/**
 * @author powerliu 类说明：上传销售情况
 */
public class SaleGoods extends Activity {

	private final static int SCANNIN_GREQUEST_CODE = 1;
	private Button upl, sc, s;
	private Spinner ty, mod;
	private TextView location, id, data, t1, t2, t3;
	private EditText ime, pri;
	private String imei, type, model, price;
	DialogForMe df;
	private String choice;
	MyAsyncTask mytask;
	MyApplication myapp = new MyApplication();
	SetTy_MoforSpinner stms;
	String judge="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("Upload My Sales");
		setContentView(R.layout.sale_goods);
		// 初始化
		init();
		s.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent spec = new Intent(SaleGoods.this, TabProcesSpcilMissionForOrd.class);
				startActivity(spec);
			}
		});
		// 从服务器上加载商品类型（手机，电脑，电视，电冰箱，空调.....）
		stms.TypeSet();
		// 添加点击事件
		upl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imei = ime.getText().toString().trim();
				price = pri.getText().toString().trim();
				// 显示一个提示上传的信息对话框，点击确定，开始上传
				if (!(imei.equals("")||price.equals(""))) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							SaleGoods.this);
					builder.setTitle("确认上传信息");
					builder.setMessage(stms.type + "\n" + MyAsyncTask.salemodel
							+ "\n" + imei + "\n" + price);
					builder.setPositiveButton("上传",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									AsyncHttpClient sale = new AsyncHttpClient();
									JSONObject p = new JSONObject();
									try {
										p.put("sellerID", myapp.userid);
										p.put("imei", imei);
										p.put("type", SetTy_MoforSpinner.type);
										p.put("model", MyAsyncTask.salemodel);
										p.put("soldPrice", price);
										p.put("soldFlag", 1);

										StringEntity s = new StringEntity(p
												.toString());
										sale.post(SaleGoods.this,
												MyApplication.setUrl("saleGoods"), s,
												HTTP.UTF_8,
												new AsyncHttpResponseHandler() {

													@Override
													public void onSuccess(
															int arg0,
															Header[] arg1,
															byte[] arg2) {
														// TODO Auto-generated
														// method stub
														if (arg0 == 200) {
															String flag = new String(
																	arg2)
																	.trim();
															System.out
																	.println("服务器返回内容为："
																			+ new String(
																					arg2));
															if(flag.equals("1"))
															{
																Toast.makeText(
																		SaleGoods.this,
																		MyApplication.ok,
																		Toast.LENGTH_LONG)
																		.show();
																pri.setText("");
																ime.setText("");
															}else if (flag.equals("2"))
															{
																Toast.makeText(
																		SaleGoods.this,
																		"库存不足，请补充 ",
																		Toast.LENGTH_LONG)
																		.show();
															}else if(flag.equals("0"))
															{
																Toast.makeText(
																		SaleGoods.this,
																		MyApplication.no,
																		Toast.LENGTH_LONG)
																		.show();
															}else {
																Toast.makeText(
																		SaleGoods.this,
																		MyApplication.serverwrong,
																		Toast.LENGTH_LONG)
																		.show();
															}
														
														}
													}

													@Override
													public void onFailure(
															int arg0,
															Header[] arg1,
															byte[] arg2,
															Throwable arg3) {
														// TODO Auto-generated
														// method stub
														Toast.makeText(
																SaleGoods.this,
																MyApplication.serverwrong,
																Toast.LENGTH_LONG)
																.show();
													}
												});
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (UnsupportedEncodingException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}
							});
					builder.setNegativeButton("Calcle",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									Toast.makeText(SaleGoods.this,
											"choose again", Toast.LENGTH_SHORT)
											.show();
									ime.setText("");
								}
							});
					builder.create().show();
				} else {
					Toast.makeText(SaleGoods.this, MyApplication.totalinfo,
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		sc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i1 = new Intent(SaleGoods.this,
						MipcaActivityCapture.class);
				startActivityForResult(i1, SCANNIN_GREQUEST_CODE);
			}
		});
	}

	// 扫条形码的方法调用
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case SCANNIN_GREQUEST_CODE:
			if (resultCode == RESULT_OK) {
				Bundle bundle = data.getExtras();
				ime.setText(bundle.getString("result"));
				System.out.println("二维码结果：" + bundle.getString("result"));
				imei = ime.getText().toString();
			}
			break;
		}
	}

	private void init() {
		// TODO Auto-generated method stub
		df = new DialogForMe(SaleGoods.this);
		upl = (Button) findViewById(R.id.upload_sales);
		pri = (EditText) findViewById(R.id.price);
		sc = (Button) findViewById(R.id.scan_imei);
		ty = (Spinner) findViewById(R.id.type);
		mod = (Spinner) findViewById(R.id.model);
		ime = (EditText) findViewById(R.id.imei);
		id = (TextView) findViewById(R.id.id);
		data = (TextView) findViewById(R.id.date);
		location = (TextView) findViewById(R.id.location);
		s = (Button) findViewById(R.id.specialTask);
		UpdateInfo updateinfo = new UpdateInfo();
		updateinfo.update(id, data, location, s);
		t1 = (TextView) findViewById(R.id.pingpai);
		t2 = (TextView) findViewById(R.id.xinghao);
		t3 = (TextView) findViewById(R.id.imeihao);

		stms = new SetTy_MoforSpinner(SaleGoods.this, ty, mod);
	}
}