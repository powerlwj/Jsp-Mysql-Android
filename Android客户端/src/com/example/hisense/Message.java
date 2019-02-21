package com.example.hisense;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import classes_for_JavaBean.Mess;

import com.google.gson.Gson;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.MyApplication;
import com.hisense.tools.UpdateInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * @author powerliu
 *类说明：消息查看
 */
public class Message extends Activity{
	
	private ListView listmessage;
	private Button refresh,s;
	private TextView id,data,location;
	public List<Mess> list;
	SimpleAdapter adapter;
	Mess[] mess;
	String[] writer,title,date;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message);
		//初始化
		init();
		
		//刷新消息列表
		refresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AsyncHttpClient message=new AsyncHttpClient();
				JSONObject js=new JSONObject();
				if(MyApplication.ad_type.endsWith("5"))
				{
					try {
						js.put("level", 5);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(MyApplication.ad_type.endsWith("4"))
				{
					try {
						js.put("level", 4);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				StringEntity s;
				try {
					System.out.println("上传数据为：》》》》"+js.toString());
					s = new StringEntity(js.toString());
					message.post(Message.this,MyApplication.setUrl("viewmessage"), s, HTTP.UTF_8, new JsonHttpResponseHandler()
					{
						@Override
						public void onSuccess(int statusCode, Header[] headers,
								JSONArray response) {
							// TODO Auto-generated method stub
							super.onSuccess(statusCode, headers, response);
							if(statusCode==200)
							{
								System.out.println("服务器连接成功");
								Gson gson=new Gson();
								mess=gson.fromJson(response.toString(), Mess[].class);
								setAdapterforMessageList();
							}
						}
					});
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
	}

	private void init() {
		// TODO Auto-generated method stub
		refresh=(Button)findViewById(R.id.refresh);
		listmessage=(ListView)findViewById(R.id.mess_list);
		
		id=(TextView)findViewById(R.id.id);
		data=(TextView)findViewById(R.id.date);
		location=(TextView)findViewById(R.id.location);
		s=(Button)findViewById(R.id.specialTask);
		UpdateInfo updateinfo=new UpdateInfo();
		updateinfo.update(id, data, location,s);
	}
	//为信息列表设置适配器
	private void setAdapterforMessageList() {
		// TODO Auto-generated method stub
		List<Map<String ,String>> messlist=new ArrayList<Map<String ,String>>();
		for(int i=0;i<mess.length;i++)
		{
			Map<String, String> listitem=new HashMap<String,String>();
			listitem.put("title", mess[i].getTitle());
			listitem.put("content",mess[i].getContent().toString());
			listitem.put("writer", mess[i].getSenderID());
			listitem.put("date", mess[i].getAddtime());
			messlist.add(listitem);
		}
		
		adapter=new SimpleAdapter(Message.this, messlist, R.layout.message_item,
				new String[]{"title","date","writer","content"}, new int[] {R.id.title,R.id.addtime,R.id.writer,R.id.content});
		listmessage.setAdapter(adapter);
		listmessage.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String messcontent="";
				// TODO Auto-generated method stub
				System.out.println("ID:::"+id+"position:::"+position);
				for (int i = 0; i < mess.length; i++) {
					 messcontent=mess[position].getContent();
				}
				Toast.makeText(Message.this, messcontent, 1).show();
				System.out.println("点击的消息内容为："+messcontent);
			}
		});
		
	}
	
}
