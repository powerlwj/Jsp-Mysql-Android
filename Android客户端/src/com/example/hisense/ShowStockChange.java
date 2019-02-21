package com.example.hisense;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hisense.asynctaskclasses.StckChgDtilAsyncTask;
import com.hisense.missions.UpdateMonthPlan;
import com.hisense.missions.ViewAlocatedMission_Amount;
import com.hisense.tools.MyApplication;

public class ShowStockChange extends Activity{
	
	public static  ListView stockchangelist;
	StckChgDtilAsyncTask stctask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("Stock Change Detials");
		setContentView(R.layout.stockchangedetail);
		//界面初始化
		initview();
		//请求数据
		JSONObject jsonObject=new JSONObject();
		try {
			jsonObject.put("id", MyApplication.userid);
			stctask=new StckChgDtilAsyncTask(ShowStockChange.this, stockchangelist, jsonObject);
			stctask.execute(MyApplication.setUrl("stokchgdetil"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stockchangelist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ListView listView = (ListView) arg0;
				HashMap<String, String> map = (HashMap<String, String>) listView
						.getItemAtPosition(arg2);
				String model = map.get("model");
				String addtime = map.get("addtime");
				String comment = map.get("comment");
				String txQuantity = map.get("txQuantity");
				String rxQuantity = map.get("rxQuantity");
				String changeQuantity = map.get("changeQuantity");
				String newTxQuantity = map.get("newTxQuantity");
				String newRxQuantity = map.get("newRxQuantity");
				Intent ed = new Intent(ShowStockChange.this,
						UpdateStockDetial.class);

				ed.putExtra("model", model);
				ed.putExtra("addtime", addtime);
				ed.putExtra("comment", comment);
				ed.putExtra("txQuantity", txQuantity);
				ed.putExtra("rxQuantity", rxQuantity);
				ed.putExtra("changeQuantity", changeQuantity);
				ed.putExtra("newTxQuantity", newTxQuantity);
				ed.putExtra("newRxQuantity", newRxQuantity);

				startActivity(ed);
			}
		});
	}
	
	private void initview() {
		// TODO Auto-generated method stub
		stockchangelist=(ListView)findViewById(R.id.stockchange_list);
	}

}
