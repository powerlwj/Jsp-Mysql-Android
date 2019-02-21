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

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import classes_for_JavaBean.StockChangeDetail;
import classes_for_JavaBean.Target;

public class StckChgDtilAsyncTask  extends AsyncTask<String, Integer, List<StockChangeDetail>> {
	
	Context context;
	ListView listview;
	JSONObject jsonObject;
	public static List<Map<String, String>> stockdelist;
	
	public StckChgDtilAsyncTask(Context context, ListView listview,
			JSONObject jsonObject) {
		super();
		this.context = context;
		this.listview = listview;
		this.jsonObject = jsonObject;
	}

	@Override
	protected List<StockChangeDetail> doInBackground(String... params) {
		// TODO Auto-generated method stub
		List<StockChangeDetail> list = new ArrayList<StockChangeDetail>();
		HttpPost request = new HttpPost(params[0]);
		try {
			StringEntity s;
			s = new StringEntity(jsonObject.toString());
			request.setEntity(s);
			HttpResponse res = new DefaultHttpClient().execute(request);
			String ret = EntityUtils.toString(res.getEntity());
			try {
				JSONArray reArray = new JSONArray(ret);
				System.out.println("服务器返回的数据为：" + reArray.toString());
				Gson gson = new Gson();
				StockChangeDetail[] stockdetail = gson.fromJson(reArray.toString(),
						StockChangeDetail[].class);
				for (int i = 0; i < stockdetail.length; i++) {
					StockChangeDetail s2 = stockdetail[i];
					list.add(s2);
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
	protected void onPostExecute(List<StockChangeDetail> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		stockdelist = new ArrayList<Map<String, String>>();
		List<Map<String, String>> slist=new ArrayList<Map<String, String>>();
		SimpleAdapter adapter=null;
		
		for (int i = 0; i < result.size(); i++) 
		{
				Map<String, String> listitem = new HashMap<String, String>();
				
				listitem.put("model", result.get(i).getModel());
				listitem.put("addtime", result.get(i).getAddtime().toString());
				listitem.put("comment", result.get(i).getComment());
				listitem.put("txQuantity",String.valueOf(result.get(i).getTxQuantity()));
				listitem.put("rxQuantity",String.valueOf(result.get(i).getRxQuantity()));
				listitem.put("changeQuantity",String.valueOf(result.get(i).getChangeQuantity()));
				listitem.put("newTxQuantity",String.valueOf(result.get(i).getNewTxQuantity()));
				listitem.put("newRxQuantity",String.valueOf(result.get(i).getNewRxQuantity()));
				slist.add(listitem);
		}
			System.out.println("TAG>>>>>>>>>>>>>>"+slist);
			
			adapter = new SimpleAdapter(context,slist,
					R.layout.stock_change_list, new String[] { "model", "addtime",
							"comment" }, new int[] { R.id.st_model,
							R.id.st_time,  R.id.st_comment });
			
			System.out.println(">>>>>>>>>>>."+adapter.toString());
			
			if (result.size() == 0) {
				Toast.makeText(context, "None Data searched", Toast.LENGTH_SHORT).show();
				listview.setAdapter(adapter);
			} else {
				
				listview.setAdapter(adapter);
			}
		
	}

}
