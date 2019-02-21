<%@page import="classes_for_implement_of_interface.Sold_Goods"%>
<%@page import="classes_for_JavaBean.SoldGoods"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="java.io.InputStream"%>

<%
	SoldGoods soldGoods = new SoldGoods();
	Sold_Goods sold_Goods=new Sold_Goods();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString = its.inputStream2String(jsonStream);
	Gson gson = new Gson();
	soldGoods=gson.fromJson(jsonString, SoldGoods.class);
	List<SoldGoods>  list=new ArrayList<SoldGoods>();
	list=sold_Goods.viewmysales(soldGoods);
	JSONArray jsArray=new JSONArray();
	for(SoldGoods s:list)
	{
		JSONObject jsObject=new JSONObject();
		jsObject.put("imei", s.getImei());
		jsObject.put("type", s.getType());
		jsObject.put("model", s.getModel());
		jsObject.put("soldPrice", s.getSoldPrice());
		jsArray.add(jsObject);
		System.out.println("s.toString():"+s.toString());
	}
	out.print(jsArray);
%>