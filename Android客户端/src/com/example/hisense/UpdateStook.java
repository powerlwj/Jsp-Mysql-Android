package com.example.hisense;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hisense.tools.UpdateInfo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author powerliu
 *该类当前没有用到，但保留的原因为，以后很可能要用
 */
public class UpdateStook extends Activity{
	
	
	private ListView stvw;
	private TextView mod,id,data,location;
	private EditText num;
	private Button upst,s;
	public StockListAdapter adapter;
	//用来存放每个品牌的库存量,每次全部存取,可以为空
	String[] stocknumber=null;
	//定义一个HashMap，用来存放EditText的值，Key是position 
	HashMap<Integer, String> hs=new HashMap<Integer, String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updatestock);
		//界面初始化 
		init();

		setAdapterforList();
		
		upst.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//通过webservice方法上传修改的库存
			}
		});
		
	}
	
	private ArrayList<HashMap<String, Object> > getModelData(){
		
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,Object>>();
		String[] moditem=getResources().getStringArray(R.array.model);
		for(int i=0;i<9;i++)
		{
			HashMap<String, Object> map = new HashMap<String, Object>(); 
			map.put("model", moditem[i]);
			listItem.add(map);
		}
		return listItem;
	}
	
	private void setAdapterforList() {
		// TODO Auto-generated method stub
		System.out.println("调用了AdapterSet方法");
		adapter=new StockListAdapter(UpdateStook.this);
		stvw.setAdapter(adapter);
	}

	private void init() {
		// TODO Auto-generated method stub
		stvw=(ListView)findViewById(R.id.stocklist);
		upst=(Button)findViewById(R.id.upstoc);
		num=(EditText)findViewById(R.id.stonum);
		mod=(TextView)findViewById(R.id.modname);
		
		id=(TextView)findViewById(R.id.id);
		data=(TextView)findViewById(R.id.date);
		location=(TextView)findViewById(R.id.location);
		s=(Button)findViewById(R.id.specialTask);
		UpdateInfo updateinfo=new UpdateInfo();
		updateinfo.update(id, data, location,s);
	}
	

  class StockListAdapter extends BaseAdapter{

	  private List<Map<String, Object>> mData;// 存储的EditText值
	  String[] moditem=getResources().getStringArray(R.array.model);
	  private LayoutInflater mInflater;
	public StockListAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return getModelData().size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return getModelData().get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		
		if (convertView == null) {
			holder = new ViewHolder();
			 convertView = mInflater.inflate(R.layout.stock_list, null);
			 holder.model=(TextView)convertView.findViewById(R.id.modname);
			 holder.stcnumber=(EditText)convertView.findViewById(R.id.stonum);
			 
			 convertView.setTag(holder);
		}else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象                  
    }
		
		holder.model.setText(getModelData().get(position).get("model").toString());
		holder.stcnumber.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				hs.put(position, s.toString());
			}
		});
		
		if(hs.get(position) != null){ 
			System.out.println(hs.get(position));
//			int[] i={1,2};
//			System.out.println(i[9]);
        }  
		return convertView;
	}
			
	 }
  
  public final class ViewHolder{
      public TextView model;
      public EditText stcnumber;
  }
}




