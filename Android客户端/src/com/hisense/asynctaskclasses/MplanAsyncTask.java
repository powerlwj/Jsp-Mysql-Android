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

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import classes_for_JavaBean.Target;

import com.example.hisense.R;
import com.google.gson.Gson;
import com.hisense.tools.DateSwitch;

public class MplanAsyncTask extends AsyncTask<String, Integer, List<Target>> {

	Context context;
	JSONObject jsObject;
	ListView listview;
	String s1;
	String month;
	DateSwitch ds = new DateSwitch();
	
	public MplanAsyncTask(Context context, JSONObject jsObject,
			ListView listview) {
		super();
		this.context = context;
		this.jsObject = jsObject;
		this.listview = listview;
	}

	public MplanAsyncTask(Context context, JSONObject jsObject,
			ListView listview, String s1) {
		super();
		this.context = context;
		this.jsObject = jsObject;
		this.listview = listview;
		this.s1 = s1;
	}

	@Override
	protected void onPostExecute(List<Target> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<String> month = new ArrayList<String>();
		for (int i = 0; i < result.size(); i++) {
			Map<String, String> listitem = new HashMap<String, String>();
			System.out.println("''''''''"+result.get(i).getMonth());
			if (ds.switching(result.get(i).getMonth()).equals(s1)) {
				listitem.put("model", result.get(i).getModel());
				listitem.put("target",
						String.valueOf(result.get(i).getTarget()));
				listitem.put("achieved",
						String.valueOf(result.get(i).getAchieved()));
				listitem.put("remain",
						String.valueOf(result.get(i).getRemain()));
				month.add(ds.switching(result.get(i).getMonth()));
				list.add(listitem);
			}
		}

		SimpleAdapter adapter = new SimpleAdapter(context, list,
				R.layout.month_target_list, new String[] { "model", "target",
						"achieved", "remain" }, new int[] { R.id.tmodel,
						R.id.target, R.id.tacheive, R.id.tbalance });
		if (result.size() == 0) {
			Toast.makeText(context, "None Data searched", Toast.LENGTH_SHORT).show();
			listview.setAdapter(adapter);
		} else {
			
			listview.setAdapter(adapter);

		}

	}

	@Override
	protected List<Target> doInBackground(String... params) {
		// TODO Auto-generated method stub
		List<Target> list = new ArrayList<Target>();
		HttpPost request = new HttpPost(params[0]);
		try {
			StringEntity s;
			System.out.println("上传的数据为" + jsObject.toString());
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

}
