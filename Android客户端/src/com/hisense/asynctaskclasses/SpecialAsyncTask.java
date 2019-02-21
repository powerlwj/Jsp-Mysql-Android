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
import com.hisense.tools.DateSwitch;

import classes_for_JavaBean.Target;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SpecialAsyncTask extends AsyncTask<String, Integer, List<Target>>{
	
	Context context;
	JSONObject jsObject;
	ListView listview;
	String s1;
	int index;
	
	public SpecialAsyncTask(Context context, JSONObject jsObject,
			ListView listview,int index) {
		super();
		this.context = context;
		this.jsObject = jsObject;
		this.listview = listview;
		this.index=index;
	}

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
	
	@Override
	protected void onPostExecute(List<Target> result) {
		// TODO Auto-generated method stub
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		SimpleAdapter adapter=null;
		if(index==1)
		{
			for (int i = 0; i < result.size(); i++) 
			{
					Map<String, String> listitem = new HashMap<String, String>();
//					listitem.put("type",result.get(i).getType());
//					listitem.put("model", result.get(i).getModel());
					listitem.put("from", result.get(i).getTargetTime());
					listitem.put("to", result.get(i).getTargetTime2());
					listitem.put("target",String.valueOf(result.get(i).getTarget()));
					listitem.put("targetAmount",String.valueOf(result.get(i).getTargetAmount()));
					list.add(listitem);
			}
			System.out.println("TAG>>>>>>>>>>>>>>"+list);
			
			adapter = new SimpleAdapter(context, list,
					R.layout.smisson_list, new String[] { "from", "to",
							"target", "targetAmount" }, new int[] { R.id.sfrom,
							R.id.sto, R.id.starget, R.id.sprice });
			
			System.out.println(">>>>>>>>>>>."+adapter.toString());
		}
		else if(index==2)
		{
			for (int i = 0; i < result.size(); i++) 
			{
					Map<String, String> listitem = new HashMap<String, String>();
//					listitem.put("type",result.get(i).getType());
//					listitem.put("model", result.get(i).getModel());
					listitem.put("from", result.get(i).getTargetTime());
					listitem.put("to", result.get(i).getTargetTime2());
					listitem.put("target",String.valueOf(result.get(i).getTarget()));
					listitem.put("targetAmount",String.valueOf(result.get(i).getTargetAmount()));
					list.add(listitem);
			}

			adapter = new SimpleAdapter(context, list,
					R.layout.smission_list2, new String[] { "from", "to",
							"target", "targetAmount" }, new int[] { R.id.sfrom1,
							R.id.sto1, R.id.stp, R.id.stmount });
		}
		else if(index==3)
		{
			for (int i = 0; i < result.size(); i++) 
			{
					Map<String, String> listitem = new HashMap<String, String>();
					listitem.put("from",String.valueOf(result.get(i).getTargetTime()));
					listitem.put("to",String.valueOf(result.get(i).getTargetTime2()));
					listitem.put("target",String.valueOf(result.get(i).getTarget()));
					listitem.put("amountprices", String.valueOf(result.get(i).getAmountprices()));
					listitem.put("achieved",String.valueOf(result.get(i).getAchieved()));
					listitem.put("amountpricesachieve",String.valueOf(result.get(i).getAmountpricesachieve()));
					list.add(listitem);
			}

			adapter = new SimpleAdapter(context, list,
						R.layout.special_target_self_list, new String[] { "from", "to", "target", "achieved",
								"amountprices", "amountpricesachieve" }, new int[] { R.id.tfrom,
								R.id.tto, R.id.ttarget, R.id.ttargetachieved, R.id.ttargetamount, R.id.ttargetamountachieved });
		}
		else
		{
			Toast.makeText(context, "Unknown Wrong", Toast.LENGTH_SHORT).show();
		}
		
		if (result.size() == 0) {
			Toast.makeText(context, "None Data searched", Toast.LENGTH_SHORT).show();
			listview.setAdapter(adapter);
		} else {
			
			listview.setAdapter(adapter);
		}
		
	}

}
