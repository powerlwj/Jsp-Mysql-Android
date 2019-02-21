package com.hisense.myadapters;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import classes_for_JavaBean.GoodsType;

import com.google.gson.Gson;
import com.hisense.asynctaskclasses.MyAsyncTask;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.MyApplication;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class SetTy_MoforSpinner {

	Context context;
	AsyncTask<String, Integer, String[]> mytask;
	Spinner ty, mod;
	DialogForMe df;
	public static String type;
	int index;

	public SetTy_MoforSpinner(Context context, Spinner ty, Spinner mod) {
		super();
		this.context = context;
		this.ty = ty;
		this.mod = mod;
	}
	
	
	public SetTy_MoforSpinner(Context context, Spinner ty, Spinner mod,
			int index) {
		super();
		this.context = context;
		this.ty = ty;
		this.mod = mod;
		this.index = index;
	}



	public void TypeSet() {
		// TODO Auto-generated method stub

		df = new DialogForMe(context);
		df.showdialog();
		AsyncHttpClient typ = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.add("model", "type");
		typ.post(MyApplication.setUrl("goodsType"), params,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						// TODO Auto-generated method stub
						df.canceldialog();
						Toast.makeText(context, "loading successfuly",
								Toast.LENGTH_SHORT).show();
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
								context, android.R.layout.select_dialog_item,
								types);
//						ArrayAdapter<String> ty_adaptAdapter = new ArrayAdapter<String>(
//								context, R.layout.my_spinner,types);

						ty.setAdapter(ty_adaptAdapter);

						ty.setOnItemSelectedListener(new OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> parent,
									View view, int position, long id) {
								// TODO Auto-generated method stub
								type = parent.getItemAtPosition(position)
										.toString();
								
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
						Toast.makeText(context, MyApplication.serverwrong,
								Toast.LENGTH_SHORT).show();
					}
				});
	}

	// 根据不同品牌获取相应品牌的型号并加载
	private void ModelSet() {
		// TODO Auto-generated method stub
		mytask = new MyAsyncTask(context, mod, type);
		mytask.execute(MyApplication.setUrl("model"));
		
	}
}