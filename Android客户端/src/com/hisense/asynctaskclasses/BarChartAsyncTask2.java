package com.hisense.asynctaskclasses;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.hisense.chart.BarChart;

import classes_for_JavaBean.SoldGoods;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

public class BarChartAsyncTask2 extends
		AsyncTask<String, Integer, Map<Integer, Integer>> {

	Context context;
	JSONObject jsObject;
	String m3;
	public static int[] model3 = new int[50];
	BarChart bc;
	public static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	ArrayList<Integer> list = new ArrayList<>();

	public BarChartAsyncTask2(Context context, JSONObject jsObject, String m3) {
		super();
		this.context = context;
		this.jsObject = jsObject;
		this.m3 = m3;
	}

	@SuppressLint("UseSparseArrays")
	@Override
	protected Map<Integer, Integer> doInBackground(String... params) {
		// TODO Auto-generated method stub
		HttpPost request = new HttpPost(params[0]);
		Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
		List<SoldGoods> list = new ArrayList<SoldGoods>();
		StringEntity s;
		System.out.println("上传的数据为" + jsObject.toString());
		try {
			s = new StringEntity(jsObject.toString());
			request.setEntity(s);
			HttpResponse res = new DefaultHttpClient().execute(request);
			String ret = EntityUtils.toString(res.getEntity());
			JSONObject reArray = new JSONObject(ret);
			Iterator it = reArray.keys();
			map2 = new HashMap<Integer, Integer>();
			while (it.hasNext()) {
				// int key=(int)(it.next());
				Integer key = Integer.valueOf(it.next().toString());
				int value = (int) reArray.get(String.valueOf(key));
				map2.put(key, value);
			}
			System.out.print("map2.toString():" + map2.toString());

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

		return map2;
	}

	@Override
	protected void onPostExecute(Map<Integer, Integer> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		map = result;
		for (int i = 1; i < 13; i++) {
			if (map.get(i) == null) {
				model3[i - 1] = 0;
			} else {
				model3[i - 1] = map.get(i);
			}
		}
		// model3= new int[list.size()];
		// for(int i=0;i<list.size();i++)
		// {
		// model3[i] = list.get(i);
		// }
		System.out.println();
		for (int i = 0; i < 12; i++) {
			System.out.print("model3 " + i + " " + model3[i] + " ");
		}

	}
}
