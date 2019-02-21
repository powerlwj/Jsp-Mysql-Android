package com.hisense.tools;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewHolder {

	private SparseArray<View> mviews;
	private int position;
	private View mConvertView;

	public ViewHolder(Context context, ViewGroup parent, int layoutId,
			int position) {
		this.position=position;
		this.mviews=new SparseArray<View>();
		
		mConvertView=LayoutInflater.from(context).inflate(layoutId, parent,false);
		mConvertView.setTag(this);

	}

	public static ViewHolder get(Context context, View convertView,
			ViewGroup parent, int layoutId, int position) {
		if (convertView == null) {
			return new ViewHolder(context, parent, layoutId, position);
		} else {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.position=position;
			return holder;
		}

	}
	/**
	 * 通过viewId获取控件
	 * @param viewId
	 * @return
	 */
	
	public <T extends View >  T getView(int viewId)
	{
		View view =mviews.get(viewId);
		if(view==null)
		{
			view=mConvertView.findViewById(viewId);
			mviews.put(viewId, view);
		}
		return (T) view;
	}
	
	public View getmConvertView() {
		return mConvertView;
	}


}
