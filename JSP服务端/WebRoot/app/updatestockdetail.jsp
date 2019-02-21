<%@page import="classes_for_implement_of_interface.StockChangeDetailIMP"%>
<%@page import="classes_for_JavaBean.StockChangeDetail"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="classes_for_implement_of_interface.StockImplement"%>
<%@page import="classes_for_JavaBean.Stock"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="java.io.InputStream"%>

<%
	StockChangeDetail stockcd=new StockChangeDetail();;
	StockChangeDetailIMP stockcdimp=new StockChangeDetailIMP();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString = its.inputStream2String(jsonStream);
	JSONObject jsonObj = JSONObject.fromObject(jsonString);
	Gson gson = new Gson();
	stockcd=gson.fromJson(jsonString, StockChangeDetail.class);
	List<StockChangeDetail> list = new ArrayList<StockChangeDetail>();
	boolean b=stockcdimp.updatestockdetail(stockcd);
	System.out.print("库存明细更新："+b);
	out.print(b);
%>