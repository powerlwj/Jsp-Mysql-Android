package com.example.hisense;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import classes_for_JavaBean.CompetionGoods;
import classes_for_JavaBean.GoodsType;

import com.google.gson.Gson;
import com.hisense.asynctaskclasses.CompetGoodsAsyncTask;
import com.hisense.asynctaskclasses.MyAsyncTask;
import com.hisense.myadapters.SetModelForSpinner;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.MyApplication;
import com.hisense.tools.SaveDataToSDcard;
import com.hisense.tools.SpinnerUse;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author powerliu
 *类说明：查看竞品信息
 */
public class ViewComInfo extends Activity {

	private Spinner ty, brand,mod;
	private TextView pri, dat,fea;
	private ImageView img;
	private Button vie;
	DialogForMe df;
	SaveDataToSDcard adt;
	private static int MARK = 0;
	private String brandname, model, type;
	SpinnerUse sp;
	CompetGoodsAsyncTask cgtask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("View Competitor Infomation");
		setContentView(R.layout.viewcom);
		adt = new SaveDataToSDcard();
		// 界面初始化
		init();
		//下拉框类型加载
		TypeSet();
		//相应点击事件
		vie.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AsyncHttpClient view = new AsyncHttpClient();
				JSONObject params=new JSONObject();
				try {
					params.put("type", type);
					params.put("brand", CompetGoodsAsyncTask.brandname);
					params.put("model", CompetGoodsAsyncTask.modelname);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				StringEntity s;
				try {
					s = new StringEntity(params.toString());
					System.out.println("上传的数据为：:"+params.toString());
					view.post(ViewComInfo.this, MyApplication.setUrl("querycompetionGoods"), s, HTTP.UTF_8, new JsonHttpResponseHandler()
					{
						@Override
						public void onSuccess(int statusCode, Header[] headers,
								JSONObject response) {
							// TODO Auto-generated method stub
							super.onSuccess(statusCode, headers, response);
							System.out.println("服务器连接成功");
							if (statusCode == 200) {
								String all = response.toString();
//								System.out.println("收到的数据为：：：："+all);
								CompetionGoods cg=new CompetionGoods();
								Gson gson=new Gson();
								cg=gson.fromJson(all, CompetionGoods.class);
								String pic = cg.getPicPath();
								String price=cg.getPrice();
								String date=cg.getPriceDate().toString();
								String feature=cg.getFeatures().toString();
//								System.out.println("cg.toString()::"+cg.toString());
								String imgurl = adt.savephoto(CompetGoodsAsyncTask.brandname+CompetGoodsAsyncTask.modelname, pic);
								Bitmap bitmap = BitmapFactory.decodeFile(imgurl);
								img.setImageBitmap(bitmap);
								pri.setText(price);
								dat.setText(date);
								fea.setText(feature);
								Toast.makeText(ViewComInfo.this, MyApplication.ok, 1).show();
							} else {
								Toast.makeText(ViewComInfo.this, MyApplication.no, 1).show();
							}
						}
						
						@Override
						public void onFailure(int statusCode, Header[] headers,
								String responseString, Throwable throwable) {
							// TODO Auto-generated method stub
							super.onFailure(statusCode, headers, responseString, throwable);
							Toast.makeText(ViewComInfo.this, "Unknown Errors", 1).show();
						}
					});

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			}
		});
	}

	public void TypeSet() {
		// TODO Auto-generated method stub
		df.showdialog();
		AsyncHttpClient typ = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.add("model", "type");
		typ.post(MyApplication.setUrl("goodsType"), params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				// TODO Auto-generated method stub
				df.canceldialog();
//				Toast.makeText(ViewComInfo.this, "类型加载成功，请选择品牌",
//						Toast.LENGTH_LONG).show();
				System.out.println(new String(arg2));
				Gson gson = new Gson();
				GoodsType[] goodstype = gson.fromJson(new String(arg2),
						GoodsType[].class);
				System.out.println(goodstype[0].getType());
				List<String> list = new ArrayList<String>();
				for (int i = 0; i < goodstype.length; i++) {
					list.add(goodstype[i].getType().toString());
				}
				String[] types = list.toArray(new String[list.size()]);
				ArrayAdapter<String> ty_adaptAdapter = new ArrayAdapter<String>(
						ViewComInfo.this, android.R.layout.select_dialog_item,
						types);

				ty.setAdapter(ty_adaptAdapter);

				ty.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						type = parent.getItemAtPosition(position).toString();
						ModelSet();
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub

					}
				});

			}

			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
				df.canceldialog();
				System.out.println("服务器请求失败");
				Toast.makeText(ViewComInfo.this, MyApplication.serverwrong,
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	// 根据不同品牌获取相应品牌的型号并加载
	private void ModelSet() {
		// TODO Auto-generated method stub
		cgtask=new CompetGoodsAsyncTask(ViewComInfo.this, brand, mod, type);
		cgtask.execute(MyApplication.setUrl("cbrandmodel"));
	}

	private void init() {
		// TODO Auto-generated method stub
		df=new DialogForMe(ViewComInfo.this);
		ty = (Spinner) findViewById(R.id.vtype);
		brand = (Spinner) findViewById(R.id.vbrand);
		mod = (Spinner) findViewById(R.id.vmodel);
		pri = (TextView) findViewById(R.id.price);
		dat = (TextView) findViewById(R.id.date);
		img = (ImageView) findViewById(R.id.image);
		vie = (Button) findViewById(R.id.view);
		fea=(TextView)findViewById(R.id.t_features);
		
	}

}
