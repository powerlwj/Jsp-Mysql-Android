package com.hisense.missions;

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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hisense.R;
import com.hisense.asynctaskclasses.MyAsyncTask;
import com.hisense.myadapters.SetTy_MoforSpinner;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.MyApplication;
import com.hisense.tools.NumORChars;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * @author powerliu
 *类说明：二级经理的任务分配
 */
public class MissionAlocate_Amount extends Activity {

	private Spinner ty, mod, stuff;
	private EditText p1, p2, num;
	private Button alocate;
	private String targetAmount, price, target;
    private   String mystuff;
	SetTy_MoforSpinner stms;
	DialogForMe df;
	NumORChars noc;
	int index;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("Missions Allocate");
		setContentView(R.layout.mission_alocate);
		//界面初始化
		init();
		//加载商品类型
		stms.TypeSet();
		//获取二级经理的员工
		GetStuffList();
		//分配事件
		alocate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				targetAmount=p1.getText().toString();
//				price=p2.getText().toString();
				target=num.getText().toString();
				if(!("".equalsIgnoreCase(targetAmount)&&"".equalsIgnoreCase(target)))
				{
				AsyncHttpClient aloc=new AsyncHttpClient();
				JSONObject jsObject=new JSONObject();
				try {
					jsObject.put("index", 1);
					jsObject.put("type", SetTy_MoforSpinner.type);
					jsObject.put("model", MyAsyncTask.salemodel);
					jsObject.put("ownerID", mystuff);
					jsObject.put("targetType", "Monthly");
//					jsObject.put("price", price);
					jsObject.put("targetAmount", targetAmount);
					jsObject.put("target", target);
					
					StringEntity s=new StringEntity(jsObject.toString());
					
					aloc.post(MissionAlocate_Amount.this, MyApplication.setUrl("missionalocate"), s, HTTP.UTF_8, new AsyncHttpResponseHandler() {
						
						@Override
						public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
							// TODO Auto-generated method stub
							if(arg0==200)
							{
								System.out.println("服务器连接成功");
								boolean index=Boolean.valueOf(new String(arg2).trim());
								if(index)
								{
									Toast.makeText(MissionAlocate_Amount.this, "Target for "+mystuff+" is successful", Toast.LENGTH_SHORT).show();
									p1.setText("");num.setText("");
								}else
								{
									Toast.makeText(MissionAlocate_Amount.this, "Target for"+mystuff+"is failed", Toast.LENGTH_SHORT).show();
								}
							}
						}
						
						@Override
						public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
							// TODO Auto-generated method stub
							Toast.makeText(MissionAlocate_Amount.this, "Unknown Error", Toast.LENGTH_SHORT).show();
						}
					});
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else
			{
				Toast.makeText(MissionAlocate_Amount.this,MyApplication.totalinfo, Toast.LENGTH_SHORT).show();
			}
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		df=new DialogForMe(MissionAlocate_Amount.this);
		ty = (Spinner) findViewById(R.id.mtype);
		mod = (Spinner) findViewById(R.id.mmodel);
		stuff = (Spinner) findViewById(R.id.mstuff);
		p1 = (EditText) findViewById(R.id.price1);
		p2 = (EditText) findViewById(R.id.price2);
		p2.setVisibility(View.GONE);
		num = (EditText) findViewById(R.id.missionnumber);
		alocate = (Button) findViewById(R.id.alocate);
		
		stms = new SetTy_MoforSpinner(MissionAlocate_Amount.this, ty, mod);
		noc=new NumORChars();
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
				st.post(MissionAlocate_Amount.this, MyApplication.setUrl("stuff"), s, HTTP.UTF_8,  new AsyncHttpResponseHandler()
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
									MissionAlocate_Amount.this, R.layout.my_spinner,
									stuffs);

							stuff.setAdapter(ty_adaptAdapter);

							stuff.setOnItemSelectedListener(new OnItemSelectedListener() {

								@Override
								public void onItemSelected(AdapterView<?> parent,
										View view, int position, long id) {
									// TODO Auto-generated method stub
									mystuff = parent.getItemAtPosition(position).toString();
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
						Toast.makeText(MissionAlocate_Amount.this, MyApplication.serverwrong,
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

}
