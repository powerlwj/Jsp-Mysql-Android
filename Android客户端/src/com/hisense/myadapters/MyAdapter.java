package com.hisense.myadapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import classes_for_JavaBean.StockChangeDetail;

import com.example.hisense.R;
import com.hisense.tools.ViewHolder;

public class MyAdapter extends BaseAdapter {


	private LayoutInflater mInflater;
	private List<StockChangeDetail> mdatas;
	private Context context;
	
	public MyAdapter(Context context,List<StockChangeDetail> mdatas) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.mInflater=LayoutInflater.from(context);
		this.mdatas=mdatas;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mdatas.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mdatas.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder=ViewHolder.get(context, convertView, parent, R.layout.stock_change_list, position);
		
		StockChangeDetail stockcd=mdatas.get(position);
		
		TextView model=holder.getView(R.id.st_model);
		model.setText(stockcd.getModel());
		TextView time=holder.getView(R.id.st_time);
		time.setText(stockcd.getAddtime().toString());
		TextView comment=holder.getView(R.id.st_comment);
		model.setText(stockcd.getComment());
		
		return holder.getmConvertView();
	}

}
