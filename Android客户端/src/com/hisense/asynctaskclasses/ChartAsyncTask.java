package com.hisense.asynctaskclasses;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.hisense.chart.SaleChart;

public class ChartAsyncTask extends AsyncTask<String, Integer, Map<Integer, Integer>>{

	Context context;
	String s1;
	String s2;
	SaleChart chart;
	public static Map<Integer, Integer> map;
	
	public ChartAsyncTask(Context context, String s1,String s2) {
		super();
		this.context = context;
		this.s1 = s1;
		this.s2=s2;
	}

	@SuppressLint("UseSparseArrays")
	@SuppressWarnings({ "unchecked", "null" })
	@Override
	protected Map<Integer, Integer> doInBackground(String... params) {
		// TODO Auto-generated method stub
		Map<Integer, Integer> map2 = null;
		HttpPost request = new HttpPost(params[0]);
		JSONObject p = new JSONObject();
			try {
				p.put("type", s1);
				p.put("model", s2);
				StringEntity s;
				System.out.println("上传的数据为"+p.toString());
				s = new StringEntity(p.toString());
				request.setEntity(s);
				HttpResponse res = new DefaultHttpClient().execute(request);
				String ret = EntityUtils.toString(res.getEntity());
				JSONObject reArray = new JSONObject(ret);
				System.out.println("reArray:::"+reArray);
//				Map<Integer, String> map=new HashMap<Integer, String>();
				@SuppressWarnings("rawtypes")
				Iterator it=reArray.keys();
				map2=new HashMap<Integer,Integer>();
				while(it.hasNext())
				{
//					int key=(int)(it.next());
					Integer key=Integer.valueOf(it.next().toString());
					int value=(int) reArray.get(String.valueOf(key));
					map2.put(key, value);
				}
				System.out.print("map2.toString():"+map2.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return map2;
	}
	
	@Override
	protected void onPostExecute(Map<Integer, Integer> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		System.out.println("绘图数据为："+result.toString());
		map=result;
		chart=new SaleChart(s2, result);
		
	}
	

}
