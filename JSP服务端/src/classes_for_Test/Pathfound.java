package classes_for_Test;

import java.sql.Time;
import java.util.Calendar;

import classes_for_Tools.DateUtils;
import classes_for_Tools.TimeUtils;

public class Pathfound {

	static Calendar ca=Calendar.getInstance();
	  static Calendar cal=Calendar.getInstance();
	  java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
	public static void main(String[] args) {
		java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
		
		System.out.println(ca.get(Calendar.YEAR)+"年"+ca.get(Calendar.MONTH)+"月"+ca.get(Calendar.DATE)+"日");
		System.out.println(TimeUtils.getCurrentTimeInString());
		System.out.println(Time.valueOf(cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND)));
		System.out.println(DateUtils.getNowTime(DateUtils.DATE_FULL_STR));
		System.out.println(currentDate.toString());
		System.out.println(cal.getTime());
	}
}
