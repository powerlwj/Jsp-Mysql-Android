package com.hisense.asynctaskclasses;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import classes_for_JavaBean.CompetionGoods;

import com.google.gson.Gson;

public class CompetGoodsAsyncTask extends
		AsyncTask<String, Integer, List<CompetionGoods>> {

	Context context;
	private Spinner sp1, sp2;
	private String s1;
	CompetionGoods cg;
	public static String brandname,modelname;

	public CompetGoodsAsyncTask(Context context, Spinner sp1, Spinner sp2,
			String s1) {
		super();
		this.context = context;
		this.sp1 = sp1;
		this.sp2 = sp2;
		this.s1 = s1;
	}

	@Override
	protected List<CompetionGoods> doInBackground(String... params) {
		// TODO Auto-generated method stub
		HttpPost request = new HttpPost(params[0]);
		JSONObject p = new JSONObject();

		try {
			p.put("model", s1);
			StringEntity s;
			s = new StringEntity(p.toString());
			request.setEntity(s);
			HttpResponse res = new DefaultHttpClient().execute(request);
			String ret = EntityUtils.toString(res.getEntity());
			JSONArray reArray = new JSONArray(ret);
			System.out.println("reArray.toString()::" + reArray.toString());
			List<CompetionGoods> list = new ArrayList<CompetionGoods>();
			cg = new CompetionGoods();
			Gson gson = new Gson();
			for (int i = 0; i < reArray.length(); i++) {
				CompetionGoods[] cg = gson.fromJson(reArray.toString(),
						CompetionGoods[].class);
				list.add(cg[i]);
			}

			return list;

		} catch (JSONException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	protected void onPostExecute(List<CompetionGoods> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		List<String> l1 = new ArrayList<String>();
		String[] models = new String[result.size()];
		for (CompetionGoods c : result) {
			l1.add(c.getBrand());
		}
		Set<String> set = new HashSet<>(l1);
		setbrand(set.toArray(new String[set.size()]), result);

	}

	private void setModel(String name, List<CompetionGoods> result) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		for (CompetionGoods c : result) {
			if (c.getBrand().equals(name)) {
				list.add(c.getModel());
			}
		}
		ArrayAdapter<String> mo_adaptAdapter = new ArrayAdapter<String>(
				context, android.R.layout.select_dialog_item,
				list.toArray(new String[list.size()]));
		sp2.setAdapter(mo_adaptAdapter);
		
		sp2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				modelname=sp2.getSelectedItem().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		
		
	}

	private void setbrand(String[] brand, final List<CompetionGoods> result) {
		// TODO Auto-generated method stub
		ArrayAdapter<String> br_adaptAdapter = new ArrayAdapter<String>(
				context, android.R.layout.select_dialog_item, brand);
		sp1.setAdapter(br_adaptAdapter);
		sp1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				brandname = sp1.getSelectedItem().toString();
				setModel(brandname, result);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
