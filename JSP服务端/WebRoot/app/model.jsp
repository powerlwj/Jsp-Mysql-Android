<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="classes_for_JavaBean.SoldGoods"%>
<%@page import="classes_for_implement_of_interface.Sold_Goods"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="java.io.InputStream"%>
<%@page import="classes_for_implement_of_interface.Goods_Type"%>
<%@page import="classes_for_JavaBean.GoodsType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	GoodsType goodstype=new GoodsType();
	SoldGoods soldgoods=new SoldGoods();
	Sold_Goods sold_goods=new Sold_Goods();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString=its.inputStream2String(jsonStream);
	JSONObject jsonObj = JSONObject.fromObject(jsonString);
	List<String> list=new ArrayList<String>();
	String model=jsonObj.optString("model");
	soldgoods.setType(model);
	System.out.println(soldgoods.toString());
  	list=sold_goods.queryModel(soldgoods);
  	System.out.println("list:>>"+list.size());
  	JSONArray jsArray=JSONArray.fromObject(list);
  	System.out.println("生成的Json:"+jsArray.toString());
  	out.println(jsArray);
  	
%>