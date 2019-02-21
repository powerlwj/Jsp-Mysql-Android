<%@page import="java.io.File"%>
<%@page import="classes_for_Tools.PhotoProcess"%>
<%@page import="javax.print.attribute.standard.MediaSize.Other"%>
<%@page import="classes_for_JavaBean.CompetionGoods"%>
<%@page import="classes_for_implement_of_interface.Competion_Goods"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="java.io.InputStream"%>
<%  
	Competion_Goods com_Goods=new Competion_Goods();
	CompetionGoods comGoods=new CompetionGoods();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString = its.inputStream2String(jsonStream);
	Gson gson = new Gson();
	comGoods=gson.fromJson(jsonString, CompetionGoods.class);
	System.out.print("comGoods.toString():"+comGoods.toString());
	CompetionGoods comGoods2=new CompetionGoods();
	comGoods2=com_Goods.query(comGoods);
	System.out.print("comGoods2.toString():"+comGoods2.toString());
	String picpa=comGoods2.getPicPath();
	File picpath=new File("E:\\Apache Software Foundation\\Tomcat 6.0\\webapps\\hs\\"+picpa);
	JSONObject jsObject=new JSONObject();
	PhotoProcess pps=new PhotoProcess();
	jsObject.put("price", comGoods2.getPrice());
	jsObject.put("priceDate", comGoods2.getPriceDate().toString());
	jsObject.put("picPath", pps.readPhoto(picpath));
	jsObject.put("feature", comGoods2.getFeatures());
	//System.out.print("返回的数据为：:"+jsObject);
	out.println(jsObject);
%>