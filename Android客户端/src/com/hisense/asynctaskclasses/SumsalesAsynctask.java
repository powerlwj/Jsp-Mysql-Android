package com.hisense.asynctaskclasses;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import classes_for_JavaBean.SoldGoods;

import com.google.gson.Gson;

public class SumsalesAsynctask extends
		AsyncTask<String, Integer, List<SoldGoods>> {

	Context context;
	JSONObject jsObject;
	TextView t1, t2;
	double totalprice = 0;

	public SumsalesAsynctask(Context context, JSONObject jsObject, TextView t1,
			TextView t2) {
		super();
		this.context = context;
		this.jsObject = jsObject;
		this.t1 = t1;
		this.t2 = t2;
	}

	@Override
	protected void onPostExecute(List<SoldGoods> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		StringBuffer mess = new StringBuffer();
		List<SoldGoods> slist = new ArrayList<SoldGoods>();
		HashSet<SoldGoods> models = new HashSet<SoldGoods>();
		for (int i = 0; i < result.size(); i++) {
			mess.append(result.get(i).getModel() + "  " + " Totalnum :"
					+ result.get(i).getTnum() + " ;" + "Totalprice:  "
					+ result.get(i).getTprice() + "\n");
			totalprice = totalprice
					+ Double.parseDouble(result.get(i).getTprice());
			/*
			 * Map<String, String> listitem = new HashMap<String, String>();
			 * listitem.put("model", result.get(i).getModel());
			 * listitem.put("tnum", String.valueOf(result.get(i).getTnum()));
			 * listitem.put("tprice",
			 * String.valueOf(result.get(i).getTprice())); list.add(listitem);
			 */

		}
		t1.setText(mess);
		t2.setText("Total Price is:" + totalprice);

	}

	@Override
	protected List<SoldGoods> doInBackground(String... params) {
		// TODO Auto-generated method stub
		List<SoldGoods> list = new ArrayList<SoldGoods>();
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
				SoldGoods[] soldGoods = gson.fromJson(reArray.toString(),
						SoldGoods[].class);
				for (int i = 0; i < soldGoods.length; i++) {
					SoldGoods soldGoods2 = soldGoods[i];
					list.add(soldGoods2);
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

}
