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
	//JSONObject jsonObj = JSONObject.fromObject(jsonString);
	//String userID=jsonObj.optString("id");
	//String passWord=jsonObj.optString("password");
	admin = gson.fromJson(jsonString, Administrator.class);
	admin2 = adminip.queryuer(admin);
	JSONObject jsObject=new JSONObject();
	jsObject.put("ad_type", admin2.getAdmin_type());
	jsObject.put("region", admin2.getRegion());
	System.out.println("生成json测试："+jsObject);
	
	out.println(jsObject);
%>