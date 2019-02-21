/**
 * 
 */
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
import classes_for_JavaBean.Target;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/**
 * Package: com.hisense.asynctaskclasses
 *
 * File: ViewMissionsTask.java 
 *
 * Author: powerliu   Date: 2015年8月25日
 *
 * Copyright @ 2015 Corpration Name
 *
 *类说明： 
 */
public class ViewMissionsTask extends AsyncTask<String, Integer, List<Target>>{

	Context context;
	ListView listview;
	String mystuff;
	JSONObject jsObject;
	int index;
	
	
	public ViewMissionsTask(Context context, ListView listview, JSONObject jsObject,int index) {
		super();
		this.context = context;
		this.listview = listview;
		this.jsObject = jsObject;
		this.index=index;
	}

	
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
	 */
	@Override
	protected List<Target> doInBackground(String... params) {
		// TODO Auto-generated method stub
		List<Target> list = new ArrayList<Target>();
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
				Target[] target = gson.fromJson(reArray.toString(),
						Target[].class);
				for (int i = 0; i < target.length; i++) {
					Target target2 = target[i];
					list.add(target2);
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

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(List<Target> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		SimpleAdapter adapter=null;
		if(index==1)
		{
			for (int i = 0; i < result.size(); i++) 
			{
					Map<String, String> listitem = new HashMap<String, String>();
					listitem.put("type",String.valueOf(result.get(i).getType()));
					listitem.put("model", result.get(i).getModel());
					listitem.put("target",String.valueOf(result.get(i).getTarget()));
					listitem.put("targetAmount",String.valueOf(result.get(i).getTargetAmount()));
					list.add(listitem);
			}

			adapter = new SimpleAdapter(context, list,
					R.layout.amission, new String[] { "type", "model",
							"target", "targetAmount" }, new int[] { R.id.mtype,
							R.id.mmodel, R.id.mtarget, R.id.mprice });
		}
		else if(index==2)
		{
			for (int i = 0; i < result.size(); i++) 
			{
					Map<String, String> listitem = new HashMap<String, String>();
					listitem.put("type",result.get(i).getType());
					listitem.put("model", result.get(i).getModel());
					listitem.put("target",String.valueOf(result.get(i).getTarget()));
					listitem.put("targetAmount",String.valueOf(result.get(i).getTargetAmount()));
					//System.out.println(">>>>>"+String.valueOf(result.get(i).getTarget()));
					//System.out.println(">>>>>"+String.valueOf(result.get(i).getTargetAmount()));
					list.add(listitem);
			}

			adapter = new SimpleAdapter(context, list,
					R.layout.tmission, new String[] { "type", "model",
							"target", "targetAmount" }, new int[] { R.id.t_type,
							R.id.t_model, R.id.target, R.id.targetAmount });
		}
		else if(index==3)
		{
			for (int i = 0; i < result.size(); i++) 
			{
					Map<String, String> listitem = new HashMap<String, String>();
					listitem.put("target",String.valueOf(result.get(i).getTarget()));
					listitem.put("amountprices", String.valueOf(result.get(i).getAmountprices()));
					listitem.put("achieved",String.valueOf(result.get(i).getAchieved()));
					listitem.put("amountpricesachieve",String.valueOf(result.get(i).getAmountpricesachieve()));
					list.add(listitem);
			}

			 adapter = new SimpleAdapter(context, list,
						R.layout.month_target_list, new String[] { "target", "achieved",
						"amountprices", "amountpricesachieve" }, new int[] { R.id.tmodel,
								R.id.target, R.id.tacheive, R.id.tbalance });
		}
		
		if (result.size() == 0) {
			Toast.makeText(context, "None Data searched", Toast.LENGTH_SHORT).show();
			listview.setAdapter(adapter);
		} else {
			
			listview.setAdapter(adapter);
		}
		
	}
	
}
