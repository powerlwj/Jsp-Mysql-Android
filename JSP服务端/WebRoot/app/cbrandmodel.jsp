<%@page import="classes_for_implement_of_interface.Competion_Goods"%>
<%@page import="classes_for_JavaBean.CompetionGoods"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	CompetionGoods cg = new CompetionGoods();
	Competion_Goods cgp = new Competion_Goods();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString = its.inputStream2String(jsonStream);
	JSONObject jsonObj = JSONObject.fromObject(jsonString);
	List<CompetionGoods> list = new ArrayList<CompetionGoods>();
	String type = jsonObj.optString("model");
	cg.setType(type);
	list = cgp.querysimple(cg);
	JSONArray js = new JSONArray();
	for (CompetionGoods c : list) {
		JSONObject jsObject = new JSONObject();
		jsObject.put("model", c.getModel());
		jsObject.put("brand", c.getBrand());
		System.out.println(c.getBrand() + c.getModel());
		js.add(jsObject);
	}
	out.println(js);
%>