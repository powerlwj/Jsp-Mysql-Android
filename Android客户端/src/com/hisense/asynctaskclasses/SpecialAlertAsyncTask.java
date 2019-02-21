package com.hisense.asynctaskclasses;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.hisense.ActivityForOrdinary;
import com.example.hisense.R;
import com.google.gson.Gson;

import classes_for_JavaBean.Target;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class SpecialAlertAsyncTask extends AsyncTask<String, Integer, List<Target>>{

	Context context;
	JSONObject jsObject;
	TextView shsp;
	String s1;
	StringBuilder stringBuilder;
	
	
	public SpecialAlertAsyncTask(Context context, JSONObject jsObject)
 {
		super();
		this.context = context;
		this.jsObject = jsObject;
	}

	@Override
	protected List<Target> doInBackground(String... params) {
		// TODO Auto-generated method stub
		List<Target>  targetslist = new ArrayList<Target>();
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
					targetslist.add(target2);
				}
				System.out.println("返回的list为：" + targetslist.toString());
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

		return targetslist;
	}
	
	@Override
	protected void onPostExecute(List<Target> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		int a=result.size();
		stringBuilder=new StringBuilder();
		for (Target ta : result) {
		
			if(ta.getModel().equals("allmodels"))
			{
				stringBuilder=stringBuilder.append("Type:"+ta.getType()+",Model:"+ta.getModel()+",Target:"+
			ta.getTarget()+",TargetAmount:"+ta.getTargetAmount()+",From:"+ta.getTargetTime()+"-to:"+ta.getTargetTime2()+";\n");
			}else if(!ta.getModel().equals("allmodels"))
			{
				stringBuilder=stringBuilder.append("Type:"+ta.getType()+",Model:"+ta.getModel()+",Target:"+ta.getTarget()+
						",TargetAmount:"+ta.getTargetAmount()+",From"+ta.getTargetTime()+"-to:"+ta.getTargetTime2()+";\n");
			}
			
		}
		 AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Special Targets");
            //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
            View view = LayoutInflater.from(context).inflate(R.layout.shsp, null);
            //    设置我们自己定义的布局文件作为弹出框的Content
            builder.setView(view);
          final TextView message = (TextView)view.findViewById(R.id.showmessages);
          	  message.setText(stringBuilder);
            builder.show();
	}

}
