<%@page import="classes_for_implement_of_interface.GoodsImplement"%>
<%@page import="classes_for_JavaBean.Goods"%>
<%@page import="classes_for_Tools.PhotoProcess"%>
<%@page import="javax.print.attribute.standard.MediaSize.Other"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="java.io.InputStream"%>

<%
	Goods goods = new Goods();
	GoodsImplement goodsimp = new GoodsImplement();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString = its.inputStream2String(jsonStream);
	Gson gson = new Gson();
	goods = gson.fromJson(jsonString, Goods.class);
	String picString = goods.getPicPath();
	String picName = goods.getModel();
	PhotoProcess pps = new PhotoProcess();
	boolean is = pps.SavePhoto(picName, picString);
	if (is) {
		System.out.print("图片上传成功");
	} else {
		System.out.print("图片上传失败");
	}
	goods.setPicPath("upload/" + picName);
	boolean ind = goodsimp.add(goods);
	boolean index = ind && is;
	System.out.println("结果测试" + index);
	out.println(index);
%>