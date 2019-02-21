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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import classes_for_JavaBean.SoldGoods;

import com.example.hisense.R;
import com.google.gson.Gson;

public class ViewSaleAsyncTask extends AsyncTask<String, Integer, List<SoldGoods>>{

	Context context;
	ListView listview;
	String mystuff;
	JSONObject jsObject;
	TextView t1,t2;
	int totalprice=0,totalnumber=0;
	
	
	
	public ViewSaleAsyncTask(Context context, ListView listview,
			JSONObject jsObject) {
		super();
		this.context = context;
		this.listview = listview;
		this.jsObject = jsObject;
	}

	public ViewSaleAsyncTask(Context context, ListView listview, JSONObject jsObject,TextView t1,TextView t2) {
		super();
		this.t1=t1;
		this.t2=t2;
		this.context = context;
		this.listview = listview;
		this.jsObject = jsObject;
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
	
	@Override
	protected void onPostExecute(List<SoldGoods> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<SoldGoods> slist=new ArrayList<SoldGoods>();
		HashSet<SoldGoods> models=new HashSet<SoldGoods>();
		totalnumber=result.size();
		System.out.println("Total Number is>>>>"+totalnumber);
		for (int i = 0; i < result.size(); i++) {
			Map<String, String> listitem = new HashMap<String, String>();
			listitem.put("type",String.valueOf(result.get(i).getType()));
				listitem.put("model", result.get(i).getModel());
				listitem.put("imei",String.valueOf(result.get(i).getImei()));
				listitem.put("solddate",String.valueOf(result.get(i).getSoldDate()));
				listitem.put("price",String.valueOf(result.get(i).getSoldPrice()));
				totalprice=totalprice+Integer.parseInt(result.get(i).getSoldPrice());
				System.out.println("Total price is>>>>"+totalprice);
				list.add(listitem);
//				models.add(result.get(i).getModel());
				System.out.println("*********"+models);
				//分条显示
				/*switch (result.get(i).getModel()) {
				case models:
					
					break;

				default:
					break;
				}*/
			}

		SimpleAdapter adapter = new SimpleAdapter(context, list,
				R.layout.view_salelist2, new String[] { "type", "model",
						"imei", "price","solddate"}, new int[] { R.id.ttype,
						R.id.tmodel, R.id.timei,R.id.tprice,R.id.tdate });
		if (result.size() == 0) {
			Toast.makeText(context, "None Data searched", Toast.LENGTH_SHORT).show();
			
			listview.setAdapter(adapter);
		
		} else {
			System.out.println(totalnumber+"<><><>"+totalprice);
//			t1.setText("TotalPrice:"+totalprice);t2.setText("TotalNumber:"+totalnumber);
			listview.setAdapter(adapter);
		}

	}

}
