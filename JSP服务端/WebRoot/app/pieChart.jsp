<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.Calendar"%>
<%@page import="classes_for_JavaBean.SoldGoods"%>
<%@page import="com.google.gson.JsonArray"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="classes_for_implement_of_interface.Sold_Goods"%>
<%@page import="classes_for_implement_of_interface.GoodsImplement"%>
<%@page import="classes_for_JavaBean.Goods"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="java.io.InputStream"%>
<%
	SoldGoods soldGoods = new SoldGoods();
	Sold_Goods sold_Goods = new Sold_Goods();
	Calendar c=Calendar.getInstance();
	int mon=c.get(Calendar.MONTH)+1;
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString = its.inputStream2String(jsonStream);
	Gson gson = new Gson();
	soldGoods=gson.fromJson(jsonString, SoldGoods.class);
	soldGoods.setSoldDate(String.valueOf(mon));
	List<SoldGoods> list = new ArrayList<SoldGoods>();
	list=sold_Goods.singlemonthsales(soldGoods);
	JSONArray js=new JSONArray();
	for (SoldGoods s :list) {
		JSONObject jsObject=new JSONObject();
		jsObject.put("model", s.getModel());
		jsObject.put("soldNumber", s.getSoldNumber());
		js.add(jsObject);
	}
	System.out.print("要传送的数据为："+js.toString());
	out.println(js);
%>