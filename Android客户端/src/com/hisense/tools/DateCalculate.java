package com.hisense.tools;

import java.sql.Date;
import java.util.Calendar;

public class DateCalculate {
	
	int day,month,year;
	
	Calendar c = Calendar.getInstance();//可以对每个时间域单独修改

	int hour=c.get(Calendar.HOUR_OF_DAY);
	int miniute=c.get(Calendar.MINUTE);
	int seconds=c.get(Calendar.SECOND);
	
	public int getDay() {
		day=c.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		 month=c.get(Calendar.MONTH)+1;
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		 year=c.get(Calendar.YEAR);
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
	//计算该月天数
	public int daysOFMonth()
	{
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	//计算该月剩余天数
	public int getLeftDays()
	{
		int left=daysOFMonth()-getDay();
		return left;
	}
	
	//计算今天为本月第几天( 也就是今天的日期)
	public int getPassedDays()
	{
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public  int differentDays(java.util.Date fromdate,java.util.Date todate)
	{
	Calendar cal1 = Calendar.getInstance();
	cal1.setTime(fromdate);
	Calendar cal2 = Calendar.getInstance();
	cal2.setTime(todate);
	int day1= cal1.get(Calendar.DAY_OF_YEAR);
	int day2 = cal2.get(Calendar.DAY_OF_YEAR);
	int year1 = cal1.get(Calendar.YEAR);
	int year2 = cal2.get(Calendar.YEAR);
	if(year1 != year2)   //同一年
	{
	int timeDistance = 0 ;
	for(int i = year1 ; i < year2 ; i ++)
	{
	if(i%4==0 && i%100!=0 || i%400==0)    //闰年
	{
	timeDistance += 366;
	}
	else    //不是闰年
	{
	timeDistance += 365;
	}
	}
	return timeDistance + (day2-day1) ;
	}
	else    //不同年
	{
	System.out.println("判断day2 - day1 : " + (day2-day1));
	return day2-day1;
	}
	}
}
