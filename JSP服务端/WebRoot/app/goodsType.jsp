<%@page import="classes_for_JavaBean.SoldGoods"%>
<%@page import="classes_for_implement_of_interface.Sold_Goods"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="com.google.gson.JsonArray"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="java.io.InputStream"%>
<%@page import="classes_for_implement_of_interface.Goods_Type"%>
<%@page import="classes_for_JavaBean.GoodsType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	GoodsType goodstype = new GoodsType();
	Goods_Type goods_type = new Goods_Type();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	Gson gson = new Gson();
	List<GoodsType> list = new ArrayList<GoodsType>();
	String parms = its.inputStream2String(jsonStream).substring(6);
	System.out.println("parms:>>"+parms);
	
	 if (parms.equals("type")) {
		System.out.println("商品类型请求调试");
		list = goods_type.queryAll();
		JsonObject jsonObject = new JsonObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println("商品类型请求调试生成的jsonArray为："+jsonArray);
		out.println(jsonArray);
	}
%>
