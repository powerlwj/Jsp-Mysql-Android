<%@page import="com.google.gson.JsonArray"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="classes_for_implement_of_interface.Sold_Goods"%>
<%@page import="classes_for_implement_of_interface.GoodsImplement"%>
<%@page import="classes_for_implement_of_interface.Goods_Type"%>
<%@page import="classes_for_JavaBean.Goods"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="java.io.InputStream"%>

<%
	Goods goods = new Goods();
	Sold_Goods sg = new Sold_Goods();
	Map<Integer, Integer> map=new HashMap<Integer, Integer>();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString = its.inputStream2String(jsonStream);
	Gson gson = new Gson();
	System.out.println(">>>>>"+jsonString);
	goods=gson.fromJson(jsonString, Goods.class);
	List<String> list=new ArrayList<String>();
	map=sg.chart(goods);
	JSONObject jsObject=JSONObject.fromObject(map);
	System.out.println("返回数制表据位："+jsObject);
	out.println(jsObject);
	
%>