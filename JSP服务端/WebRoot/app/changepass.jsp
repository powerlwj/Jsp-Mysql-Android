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
	Administrator admin = new Administrator();
	Administrator admin2 = new Administrator();
	AdministratorImplement adminip = new AdministratorImplement();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString = its.inputStream2String(jsonStream);
	Gson gson = new Gson();
	admin = gson.fromJson(jsonString, Administrator.class);
	boolean index=adminip.updateuer(admin);
	System.out.println("登录结果测试输出"+index);
	out.println(index);
	
%>