package com.hisense.tools;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import classes_for_JavaBean.CompetionGoods;
import classes_for_JavaBean.GoodsType;

import com.google.gson.Gson;
import com.hisense.asynctaskclasses.MyAsyncTask;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class SpinnerUse {
	
	Context context;
	String str1;
	DialogForMe df;
	MyAsyncTask mytask;
	String url;
	Spinner spinner;
	
	
	public SpinnerUse() {
		// TODO Auto-generated constructor stub
		super();
	}

	public SpinnerUse(Context context, String url,String str1,Spinner spinner) {
		super();
		this.context = context;
		this.url=url;
		this.spinner=spinner;
		this.str1 = str1;
		df=new DialogForMe(context);
		
	}
	
	public void TypeSet() {
		// TODO Auto-generated method stub
		df.showdialog();
		AsyncHttpClient typ = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		CompetionGoods cp=new CompetionGoods();
		params.add("type", str1);
		typ.post(url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				// TODO Auto-generated method stub
				df.canceldialog();
				Toast.makeText(context, "loading successfuly",
						Toast.LENGTH_LONG).show();
				System.out.println(new String(arg2));
				Gson gson = new Gson();
				CompetionGoods[] cp = gson.fromJson(new String(arg2),
						CompetionGoods[].class);
				List<String> brandlist = new ArrayList<String>();
				List<String> modelist = new ArrayList<String>();
				for (int i = 0; i < cp.length; i++) {
					brandlist.add(cp[i].getBrand().toString());
					modelist.add(cp[i].getModel().toString());
				}
				String[] brands = brandlist.toArray(new String[brandlist.size()]);
				ArrayAdapter<String> ty_adaptAdapter = new ArrayAdapter<String>(
						context, android.R.layout.select_dialog_item,
						brands);

				spinner.setAdapter(ty_adaptAdapter);

				spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						String brand = parent.getItemAtPosition(position).toString();
						//ModelSet();
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

	

}
