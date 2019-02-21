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
	attendence.setDate(DateUtils.getNowTime(DateUtils.DATE_SMALL_STR).toString());
	System.out.println(attendence.toString());
	java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
	java.sql.Time cuTime=Time.valueOf(cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND));
	System.out.println("当前时间："+cuTime);
	System.out.println(attendence.getDate()+attendence.getCindex()+attendence.getID());
	index=attImplement.ischecked(attendence);
	System.out.println("是否已经签到？"+index);
	out.println(index);
%>