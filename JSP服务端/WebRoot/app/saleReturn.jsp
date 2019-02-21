<%@page import="classes_for_JavaBean.SoldGoods"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="classes_for_implement_of_interface.Sold_Goods"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	InputStreamTOString its = new InputStreamTOString();
	Sold_Goods sold_goods = new Sold_Goods();
	SoldGoods soldgoods = new SoldGoods();
	Gson gson = new Gson();
	InputStream jsonStream = request.getInputStream();
	String saleinfo = its.inputStream2String(jsonStream);
	System.out.println("saleinfo:>>"+saleinfo);
	soldgoods = gson.fromJson(saleinfo, SoldGoods.class);
	//测试soldgoods=gson.fromJson(json, SoldGoods.class);
	System.out.println("已售商品状态测试：>>" + soldgoods.getSoldFlag());
	//更新数据库
	boolean flag = sold_goods.updateSoldGoods(soldgoods);
	out.print(flag);
	System.out.println("退货成功？" + flag);
%>
