<%@page import="classes_for_implement_of_interface.MessageImplement"%>
<%@page import="classes_for_JavaBean.Message"%>
<%@page import="classes_for_implement_of_interface.GoodsImplement"%>
<%@page import="classes_for_JavaBean.Goods"%>
<%@page import=" classes_for_Tools.TimeUtils"%>
<%@page import="classes_for_Tools.PhotoProcess"%>
<%@page import="javax.print.attribute.standard.MediaSize.Other"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="java.io.InputStream"%>

<%
	Message message=new Message();
	MessageImplement mip=new MessageImplement();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
	String jsonString = its.inputStream2String(jsonStream);
	Gson gson = new Gson();
	message=gson.fromJson(jsonString, Message.class);
	message.setAddtime(TimeUtils.getCurrentTimeInString().toString());
	boolean b=mip.add(message);
	System.out.print("添加结果："+b);
	out.print(b);
%>