<%@page import="classes_for_Tools.Islate"%>
<%@page import="java.sql.Time"%>
<%@page import="java.sql.Date"%>
<%@page import="classes_for_Tools.DateUtils"%>
<%@page import="java.util.Calendar"%>
<%@page import="classes_for_implement_of_interface.AttendenceImplement"%>
<%@page import="classes_for_JavaBean.Attendence"%>
<%@page
	import="classes_for_implement_of_interface.AdministratorImplement"%>
<%@page import="classes_for_JavaBean.Administrator"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="java.io.InputStream"%>
<%
	
    int index=0;
	Attendence attendence = new Attendence();
	AttendenceImplement attImplement = new AttendenceImplement();
	 Calendar cal=Calendar.getInstance();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString = its.inputStream2String(jsonStream);
	Gson gson = new Gson();
	attendence=gson.fromJson(jsonString, Attendence.class);
	System.out.println(attendence.toString());
	java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
	java.sql.Time cuTime=Time.valueOf(cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND));
	System.out.print("当前时间："+cuTime);
	//boolean late=islate.judge(cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),cal.get(Calendar.SECOND));
	//int a=islate.MorningOREvening(cal.get(Calendar.HOUR_OF_DAY));
	if(attendence.getCindex()==1)
	{
		attendence.setDate(DateUtils.getNowTime(DateUtils.DATE_SMALL_STR).toString());
		attendence.setTime1(cuTime.toString());
		//attendence.setLate(late);
	    index=attImplement.checkin(attendence);
	}else if(attendence.getCindex()==2)
	{
		attendence.setDate(DateUtils.getNowTime(DateUtils.DATE_SMALL_STR).toString());
		attendence.setTime2(cuTime.toString());
		//attendence.setLate(late);
		index=attImplement.checkout(attendence);
	}
	System.out.println("测试结果如下"+index);
	out.println(index);
%>