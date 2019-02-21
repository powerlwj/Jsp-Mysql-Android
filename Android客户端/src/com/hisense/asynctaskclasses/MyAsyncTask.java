package com.hisense.asynctaskclasses;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hisense.tools.MyApplication;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * @author powerliu 异步处理学习类
 */
public class MyAsyncTask extends AsyncTask<String, Integer, String[]> {

	private String[] list;
	private Spinner sp;
	private String choice;
	Context context;
	public static String salemodel;
	

	public MyAsyncTask(Context context, Spinner sp, String choice) {
		this.context = context;
		this.sp = sp;
		this.choice = choice;
	}

	// 自动得到获取的数据并进行处理
	@Override
	protected void onPostExecute(String[] result) {
		// TODO Auto-generated method stub
		setAdapter(result);
	}

	public void setAdapter(String[] result) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
				android.R.layout.select_dialog_item, result);
		sp.setAdapter(adapter);
		
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				salemodel = parent.getItemAtPosition(position).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	protected String[] doInBackground(String... params) {
		HttpPost request = new HttpPost(params[0]);
		JSONObject p = new JSONObject();

		try {
			p.put("model", choice);
			StringEntity s;
			s = new StringEntity(p.toString());
			request.setEntity(s);
			HttpResponse res = new DefaultHttpClient().execute(request);
			String ret = EntityUtils.toString(res.getEntity());
			JSONArray reArray = new JSONArray(ret);
			System.out.println(reArray.toString());
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < reArray.length(); i++) {
				list.add(reArray.getString(i));
			}
			String[] models = list.toArray(new String[list.size()]);
			MyApplication.models=models;
			return models;
		} catch (JSONException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
