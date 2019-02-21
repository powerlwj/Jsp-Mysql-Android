package com.hisense.asynctaskclasses;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.example.hisense.R;
import com.google.gson.Gson;

import classes_for_JavaBean.SoldGoods;
import classes_for_JavaBean.Stock;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ViewMyStockAsyncTask extends AsyncTask<String, Integer, List<Stock>>{

	Context context;
	String s1;
	ListView listview;
	JSONObject jsObject;
	
	public ViewMyStockAsyncTask(Context context, JSONObject jsObject, ListView list) {
		super();
		this.context = context;
		this.jsObject = jsObject;
		this.listview = list;
	}

	@Override
	protected List<Stock> doInBackground(String... params) {
		// TODO Auto-generated method stub
		
		List<Stock> list = new ArrayList<Stock>();
		HttpPost request = new HttpPost(params[0]);
		try {
			StringEntity s;
			s = new StringEntity(jsObject.toString());
			request.setEntity(s);
			HttpResponse res = new DefaultHttpClient().execute(request);
			String ret = EntityUtils.toString(res.getEntity());
			try {
				JSONArray reArray = new JSONArray(ret);
				System.out.println("服务器返回的数据为：" + reArray.toString());
				Gson gson = new Gson();
				Stock[] stock = gson.fromJson(reArray.toString(),
						Stock[].class);
				for (int i = 0; i < stock.length; i++) {
					Stock stock2 = stock[i];
					list.add(stock2);
				}
				System.out.println("返回的list为：" + list.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	@Override
	protected void onPostExecute(List<Stock> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (int i = 0; i < result.size(); i++) {
			Map<String, String> listitem = new HashMap<String, String>();
			listitem.put("quantity",String.valueOf(result.get(i).getQuantity()));
				listitem.put("model", result.get(i).getModel());
				list.add(listitem);
			}

		SimpleAdapter adapter = new SimpleAdapter(context, list,
				R.layout.mystocklist, new String[] { "model",
						"quantity", }, new int[] { R.id.mo,
						R.id.qu });
		if (result.size() == 0) {
			Toast.makeText(context, "None Data searched", Toast.LENGTH_SHORT).show();
			listview.setAdapter(adapter);
		} else {
			listview.setAdapter(adapter);
		}
	}

}
