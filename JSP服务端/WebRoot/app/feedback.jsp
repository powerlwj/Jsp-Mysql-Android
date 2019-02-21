<%@page import="classes_for_implement_of_interface.FeedbackImplement"%>
<%@page import="classes_for_JavaBean.FeedBack"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="java.io.InputStream"%>

<%
	FeedBack feedback = new FeedBack();
	FeedbackImplement fbi = new FeedbackImplement();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString = its.inputStream2String(jsonStream);
	Gson gson = new Gson();
	feedback=gson.fromJson(jsonString, FeedBack.class);
	boolean index=fbi.add(feedback);
	JSONObject json=new JSONObject();
	json.put("index", index);
	out.print(json);
%>