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
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

public class CheckAsyncTask extends AsyncTask<String, Integer, Integer> {

	String url;
	String location;
	String id;
	int index=0;
	Context context;
	int cindex,res;
	SharedPreferences shasp;
	String ret="0";

	public CheckAsyncTask(int cindex, String location, String id,
			Context context,SharedPreferences shasp) {
		super();
		this.cindex = cindex;
		this.location = location;
		this.id = id;
		this.context = context;
		this.shasp=shasp;
	}

	@Override
	protected Integer doInBackground(String... params) {
		// TODO Auto-generated method stub
		HttpPost request = new HttpPost(params[0]);
		JSONObject p = new JSONObject();
		try {
			p.put("ID", id);
			p.put("cindex", cindex);
			if (cindex == 1) {
				p.put("morninggps", location);
			} else if (cindex == 2) {
				p.put("eveninggps", location);
			}

			StringEntity s;
			System.out.println("上传的数据为" + p.toString());
			s = new StringEntity(p.toString());
			request.setEntity(s);
			HttpResponse res = new DefaultHttpClient().execute(request);
			ret = EntityUtils.toString(res.getEntity());
			System.out.println("签到——服务器返回的数据位：》》" + ret);
			// JSONArray reArray = new JSONArray(ret);
			index = Integer.valueOf(ret.trim());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			Toast.makeText(context, "successfully", Toast.LENGTH_SHORT).show();
			if(cindex==1)
			{
				MyApplication.Ismorning_check=true;
				shasp.edit().putBoolean("check_in", true).commit();
			}else if(cindex==2)
			{
				MyApplication.Isevenning_check=true;
				shasp.edit().putBoolean("check_out", true).commit();
			}else
			{
				Toast.makeText(context, "Unknown Wrong", Toast.LENGTH_SHORT).show();
			}
			
		}else if(result==2)
		{
			Toast.makeText(context, "Already checked", Toast.LENGTH_SHORT).show();
		}
		else {
			Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
		}
	}

}
