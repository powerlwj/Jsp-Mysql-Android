package com.hisense.myadapters;

import java.util.HashMap;
import java.util.Iterator;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hisense.R;

public class ComSaleAdapter extends BaseAdapter {

	private LayoutInflater mylayoutinflater;
	private String[] modelname;// 型号名
	private String brandname;// 品牌名
	//定义一个HashMap，用来存放EditText的值，Key是position  
    HashMap<String, String> hashMap = new HashMap<String, String>(); 
	// 可以有多个构造函数，以满足不同listview的需求
	public ComSaleAdapter(Context context, String brandname, String[] modelname) {
		this.mylayoutinflater = LayoutInflater.from(context);
		this.brandname = brandname;
		this.modelname = modelname;
	}

	// 最先调用，显示listview的长度
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return modelname.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView( int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;

		if (convertView == null) {
			
			convertView = mylayoutinflater.inflate(R.layout.comsale_input_list,
					null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();// 取出ViewHolder对象
		}
		holder.braname.setText(brandname);
		holder.modname.setText(modelname[position]);
		holder.stcnumber.setText(hashMap.get(position));
		return  convertView;
		
	}

	public class ViewHolder {
		 TextView modname, braname;
		 EditText stcnumber;
		 int position;
		public ViewHolder(View v)
		{
			modname=(TextView)v.findViewById(R.id.model_name);
			braname=(TextView)v.findViewById(R.id.brand_name);
			stcnumber=(EditText)v.findViewById(R.id.stock_number);
			
			stcnumber.addTextChangedListener(new TextWatcher() {
				
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
					for (int i = 0; i <6; i++) {
						hashMap.put(modelname[i], s.toString());
					}
					
				}
			});
		
			
		}
		
		/*if(hashMap.get(position) != null){  
            stcnumber.setText(hashMap.get(position));  
        }  */
		
	}
	
	
	public HashMap<String, String> returnData()
	{
		Iterator iter = hashMap.entrySet().iterator();  
		while (iter.hasNext()) {  
			@SuppressWarnings("rawtypes")
			HashMap.Entry entry = (HashMap.Entry) iter.next();  
		    Object key = entry.getKey();  
		    Object val = entry.getValue();  
		    System.out.println(key.toString()+val.toString());
		}  
		System.out.println("大小："+hashMap.size()+"String>>>"+hashMap.toString());
		System.out.println("Huawei P6的销量为："+hashMap.get("Hauwei"));
		
		return hashMap;
	}

}
