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

import com.google.gson.Gson;
import com.hisense.chart.PieChart;
import com.hisense.myadapters.SetTy_MoforSpinner;

import classes_for_JavaBean.SoldGoods;
import android.content.Context;
import android.os.AsyncTask;

public class PieChartAsyncTask extends AsyncTask<String, Integer, List<SoldGoods>>{

	Context context;
	JSONObject jsObject;
	String s1;
	public static String[] models;
	public static Integer[] numbers;
	PieChart pc;
	
	public PieChartAsyncTask(Context context, JSONObject jsObject) {
		super();
		this.context = context;
		this.jsObject = jsObject;
	}

	@Override
	protected List<SoldGoods> doInBackground(String... params) {
		// TODO Auto-generated method stub
		HttpPost request = new HttpPost(params[0]);
		List<SoldGoods> list=new ArrayList<SoldGoods>();
				StringEntity s;
				System.out.println("上传的数据为"+jsObject.toString());
				try {
					s = new StringEntity(jsObject.toString());
					request.setEntity(s);
					HttpResponse res = new DefaultHttpClient().execute(request);
					String ret = EntityUtils.toString(res.getEntity());
					JSONArray jsArray=new JSONArray(ret);
					System.out.println("reArray:::"+jsArray);
					Gson gson=new Gson();
					SoldGoods[] soldGoods = gson.fromJson(jsArray.toString(),
							SoldGoods[].class);
					for (int i = 0; i < soldGoods.length; i++) {
						SoldGoods soldGoods2 = soldGoods[i];
						list.add(soldGoods2);
					}
					
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		return list;
	}
	
	@Override
	protected void onPostExecute(List<SoldGoods> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		List<String> l1=new ArrayList<String>();
		List<Integer> l2=new ArrayList<Integer>();
		for (int i = 0; i < result.size(); i++) {
		
			l1.add(result.get(i).getModel());
			l2.add(result.get(i).getSoldNumber());
		}
		models=l1.toArray(new String[l1.size()]);
		numbers=l2.toArray(new Integer[l2.size()]);
		
		
	}

}
