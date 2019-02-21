<%@page import="classes_for_implement_of_interface.StockImplement"%>
<%@page import="classes_for_JavaBean.Stock"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="java.io.InputStream"%>

<%
	Stock stock = new Stock();
	StockImplement sImplement = new StockImplement();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString = its.inputStream2String(jsonStream);
	Gson gson = new Gson();
	stock=gson.fromJson(jsonString, Stock.class);
	System.out.println("传过来的数据："+stock.getQuantity()+stock.getOwnerID());
	boolean index=sImplement.update(stock);
	System.out.println("添加结果"+index);
	out.println(index);
%>