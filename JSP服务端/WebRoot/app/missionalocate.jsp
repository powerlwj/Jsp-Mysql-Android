<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="classes_for_implement_of_interface.TargetImplement"%>
<%@page import="classes_for_JavaBean.Target"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Time"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="java.io.InputStream"%>

<%
	Target target = new Target();
	TargetImplement tmp = new TargetImplement();
	Calendar now = Calendar.getInstance();
	String time = null;
	int m=now.get(Calendar.MONTH) + 1;
	
	if (m<=9)
	{
		time = now.get(Calendar.YEAR) + "-0"+m;
	}
	else
	{
		time = now.get(Calendar.YEAR) + "-"+m;
	}
	
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString = its.inputStream2String(jsonStream);
	Gson gson = new Gson();
	target = gson.fromJson(jsonString, Target.class);
	target.setTargetTime(time);
	boolean index = tmp.add(target);
	System.out.print("计划添加状态：：" + index);
	out.println(index);
%>