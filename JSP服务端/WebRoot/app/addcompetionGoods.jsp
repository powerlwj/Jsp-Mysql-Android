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
	String picString=comGoods.getPicPath();
	String picName=comGoods.getBrand()+comGoods.getModel();
	PhotoProcess pps=new PhotoProcess();
	boolean is=pps.SavePhoto(picName, picString);
	if(is)
	{
		System.out.print("图片上传成功");
	}else
	{
		System.out.print("图片上传失败");
	}
	comGoods.setPicPath("upload/"+picName);
	boolean ind=com_Goods.add(comGoods);
	boolean index=ind&&is;
	System.out.println("结果测试"+index);
	out.println(index);
%>