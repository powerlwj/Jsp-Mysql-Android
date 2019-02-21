package com.hisense.tools;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;

public class UpdateInfo {
	
	String id;
	String date;
	String location;
	Boolean Is_special;
	Button special;
	GetLocation getloacation;
	MyApplication myApp;
	java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
	public UpdateInfo()
	{
		 getloacation=new GetLocation();
		 myApp=new MyApplication();
	}
	//更新用户头信息:用户名，所在地区，当前日期,是否有特殊任务
	public void update(TextView tv1,TextView tv2,TextView tv3,Button special)
	{
		System.out.println(getId());
		tv1.setText(getId());
		tv2.setText(getDate());
		tv3.setText(getLocation());
		if(getIs_special()){
			special.setBackgroundColor(Color.RED);
		}
	
	}
	
	//更新月计划或特殊计划所剩余的日期，已过日期,当前日期
		public void upplan(TextView tv1,TextView tv2,TextView tv3)
		{
			DateCalculate dc=new DateCalculate();
			String this_month="Now:"+dc.getYear()+"-"+dc.getMonth();
			String pass_day=dc.getPassedDays()+":days Passed";
			String left_day=dc.getLeftDays()+":days Left";
			tv1.setText(pass_day);
			tv2.setText(this_month);
			tv3.setText(left_day);
		}

		public void upplandate(TextView tv1,TextView tv2,TextView tv3,String from,String to)
		{
			DateCalculate dc=new DateCalculate();
			//String转换为java.sql.Date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date fdate = null; //初始化
			java.sql.Date tdate = null; //初始化
			try {
				java.util.Date fromdate = sdf.parse(from);
				java.util.Date todate = sdf.parse(to);
				fdate = new java.sql.Date(fromdate.getTime());
				tdate = new java.sql.Date(fromdate.getTime()); 
				String this_month="Now:"+dc.getYear()+"-"+dc.getMonth()+"-"+dc.getDay();
				String pass_day=dc.differentDays(fromdate, currentDate)+":days Passed";
				String left_day=dc.differentDays(currentDate, todate)+":days Left";
				tv1.setText(pass_day);
				tv2.setText(this_month);
				tv3.setText(left_day);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
				
				
			
		}
	public String getId() {
		System.out.println("User ID:"+myApp.getUserid());
		return myApp.getUserid();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		DateCalculate dc=new DateCalculate();
		java.sql.Date date=new java.sql.Date(System.currentTimeMillis());
		return date.toString();
	}
	
	

	public void setDate(String date) {
		this.date = date;
	}

	public String getLocation() {
		
		return myApp.getLocation();
		
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public Boolean getIs_special() {
		return myApp.Is_special;
	}
	
	public void setIs_special(Boolean is_special) {
		Is_special = is_special;
	}
	
	

}
