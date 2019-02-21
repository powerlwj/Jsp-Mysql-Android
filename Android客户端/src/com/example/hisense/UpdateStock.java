package com.example.hisense;

import java.io.UnsupportedEncodingException;
import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.hisense.asynctaskclasses.MyAsyncTask;
import com.hisense.myadapters.ComSaleAdapter;
import com.hisense.myadapters.SetTy_MoforSpinner;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.MyApplication;
import com.hisense.tools.UpdateInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

//竞品库存信息更新类
public class UpdateStock extends Activity implements OnClickListener {

	private Spinner ty, mod;
	private EditText quan;
	private Button uploadsale,s;
	private String quantity;
	String[] modelname;
	DialogForMe df;
	ComSaleAdapter listadapter;
	private TextView id, data, location;
	MyAsyncTask myTask;
	SetTy_MoforSpinner stms;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comsale_input);
		//界面初始化
		init();
		//为下拉框设置数据源
		stms.TypeSet();
	}

	// 数据上传点击事件
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.up_comstock) {
			quantity = quan.getText().toString();
			if(!("".equalsIgnoreCase(quantity)))
			{
			AsyncHttpClient stock = new AsyncHttpClient();
			JSONObject params = new JSONObject();
			try {
				params.put("model", MyAsyncTask.salemodel);
				params.put("quantity", quantity);
				params.put("ownerID", MyApplication.userid);
				System.out.println("上传的数据为：" + params.toString());
				StringEntity s = new StringEntity(params.toString());
				stock.post(UpdateStock.this, MyApplication.setUrl("addStock"), s,
						HTTP.UTF_8, new AsyncHttpResponseHandler() {

							@Override
							public void onSuccess(int arg0, Header[] arg1,
									byte[] arg2) {
								// TODO Auto-generated method stub
								if (arg0 == 200) {
									System.out.println("服务器连接成功");
									boolean b = Boolean
											.valueOf(new String(arg2).trim());
									if (b) {
										Toast.makeText(UpdateStock.this,
												"The stock has been update", Toast.LENGTH_SHORT)
												.show();
										quan.setText("");
									} else {
										Toast.makeText(UpdateStock.this,
												"Failure for updating", Toast.LENGTH_SHORT)
												.show();
									}
								}
							}

							@Override
							public void onFailure(int arg0, Header[] arg1,
									byte[] arg2, Throwable arg3) {
								// TODO Auto-generated method stub
								Toast.makeText(UpdateStock.this,
										"Connecting error", Toast.LENGTH_SHORT).show();
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
			Toast.makeText(UpdateStock.this, MyApplication.totalinfo, Toast.LENGTH_SHORT).show();
		}
		}
	}

	// 界面初始化
	private void init() {
		// TODO Auto-generated method stub
		df = new DialogForMe(UpdateStock.this);
		ty = (Spinner) findViewById(R.id.stype);
		mod = (Spinner) findViewById(R.id.smodel);
		uploadsale = (Button) findViewById(R.id.up_comstock);
		uploadsale.setOnClickListener(this);
		quan = (EditText) findViewById(R.id.quantity);
		id = (TextView) findViewById(R.id.id);
		data = (TextView) findViewById(R.id.date);
		location = (TextView) findViewById(R.id.location);
		s=(Button)findViewById(R.id.specialTask);
		UpdateInfo updateinfo = new UpdateInfo();
		updateinfo.update(id, data, location,s);

		stms = new SetTy_MoforSpinner(UpdateStock.this, ty, mod);
	}

}