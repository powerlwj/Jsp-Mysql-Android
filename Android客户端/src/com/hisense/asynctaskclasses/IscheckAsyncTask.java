package com.hisense.asynctaskclasses;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.hisense.tools.MyApplication;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


public class IscheckAsyncTask extends AsyncTask<String, Integer, Integer> {


	private Context context;
	private  String id;
	private int cindex;
	String ret="0";
	int index=0;
	JSONObject jsonObject;
	
	public IscheckAsyncTask(Context context, JSONObject jsonObject, int cindex) {
		super();
		this.context = context;
		this.jsonObject = jsonObject;
		this.cindex = cindex;
	}

	@Override
	protected Integer doInBackground(String... params) {
		// TODO Auto-generated method stub
		HttpPost request = new HttpPost(params[0]);
		JSONObject p = new JSONObject();
		try {
			StringEntity s;
			System.out.println("上传的数据为" + jsonObject.toString());
			s = new StringEntity(jsonObject.toString());
			request.setEntity(s);
			HttpResponse res = new DefaultHttpClient().execute(request);
			ret = EntityUtils.toString(res.getEntity());
			index = Integer.valueOf(ret.trim());
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
		return index;
	
	}

	@Override
	protected void onPostExecute(Integer result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (result==1) {
			if(cindex==1)
			{
				MyApplication.Ismorning_check=true;
			}else if(cindex==2)
			{
				MyApplication.Isevenning_check=true;
			}
			
		}else
		{
			if(cindex==1)
			{
				MyApplication.Ismorning_check=false;
			}else if(cindex==2)
			{
				MyApplication.Isevenning_check=false;
			}
		}
		
	
	}

}
