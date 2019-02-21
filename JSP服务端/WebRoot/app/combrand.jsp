<%@page import="classes_for_implement_of_interface.Competion_Goods"%>
<%@page import="classes_for_JavaBean.CompetionGoods"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="com.google.gson.JsonArray"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="java.io.InputStream"%>

<%
	CompetionGoods comGoods = new CompetionGoods();
	CompetionGoods comGoods2 = new CompetionGoods();
	Competion_Goods com_Goods = new Competion_Goods();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString = its.inputStream2String(jsonStream);
	Gson gson = new Gson();
	List<CompetionGoods> list=new ArrayList<CompetionGoods>();
	JSONObject jsonObj = JSONObject.fromObject(jsonString);
	String type = jsonObj.optString("type");
	comGoods.setType(type);
	list=com_Goods.querysimple(comGoods);
	JSONArray js=new JSONArray();
	for(CompetionGoods c: list)
	{
		JSONObject jsObject=new JSONObject();
		jsObject.put("brand", c.getBrand());
		jsObject.put("model", c.getModel());
		
		js.element(jsObject);
	}
	out.println(js);
	
%>