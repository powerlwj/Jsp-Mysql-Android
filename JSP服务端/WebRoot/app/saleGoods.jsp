<%@page import="classes_for_JavaBean.SoldGoods"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="classes_for_implement_of_interface.Sold_Goods"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
   String  index="0";
	InputStreamTOString its = new InputStreamTOString();
	Sold_Goods sold_goods = new Sold_Goods();
	SoldGoods soldgoods = new SoldGoods();
	Gson gson = new Gson();
	InputStream jsonStream = request.getInputStream();
	String saleinfo = its.inputStream2String(jsonStream);
	System.out.println("saleinfo:>>"+saleinfo);
	soldgoods = gson.fromJson(saleinfo, SoldGoods.class);
	//测试soldgoods=gson.fromJson(json, SoldGoods.class);
	System.out.println("soldgoods.jsp测试：>>" + soldgoods.getImei());
	boolean a=sold_goods.updateStock(soldgoods);
	if(a)
	{
		System.out.print("库存有余");
		//添加入数据库
		boolean flag = sold_goods.add(soldgoods);
		if(flag)
		{
			index="1";
		}else
		{
			index="0";
		}
		System.out.println("添加成功？" + flag);
	}else{
		System.out.println("库存不足，请补充");
		index="2";
	}
	out.print(index);
	//返回1表示上传成功，2表示余额不足，0表示上传失败
	
%>
