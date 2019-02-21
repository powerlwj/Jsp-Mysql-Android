<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
	StockImplement sip=new StockImplement();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString = its.inputStream2String(jsonStream);
	Gson gson = new Gson();
	List<Stock> list = new ArrayList<Stock>();
	stock=gson.fromJson(jsonString, Stock.class);
	list=sip.viewmystock(stock);
	JSONArray js=new JSONArray();
	for(Stock s:list)
	{
		JSONObject jsObject=new JSONObject();
		jsObject.put("model", s.getModel());
		jsObject.put("quantity", s.getQuantity());
		js.add(jsObject);
	}
	System.out.print("返回我的库存："+js.toString());
	out.print(js);
%>