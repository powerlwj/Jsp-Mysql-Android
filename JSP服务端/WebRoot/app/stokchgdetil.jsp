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
	StockChangeDetail stockcd;
	StockChangeDetailIMP stockcdimp=new StockChangeDetailIMP();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString = its.inputStream2String(jsonStream);
	JSONObject jsonObj = JSONObject.fromObject(jsonString);
	Gson gson = new Gson();
	List<StockChangeDetail> list = new ArrayList<StockChangeDetail>();
	String id=jsonObj.optString("id");
	list=stockcdimp.showdetail(id);
	JSONArray js=new JSONArray();
	for(StockChangeDetail s:list)
	{
		JSONObject jsObject=new JSONObject();
		jsObject.put("id", s.getId());
		jsObject.put("model", s.getModel());
		jsObject.put("addtime", s.getAddtime());
		jsObject.put("comment", s.getComment());
		jsObject.put("txOwnerID", s.getTxOwnerID());
		jsObject.put("rxOwnerID", s.getRxOwnerID());
		jsObject.put("txQuantity", s.getTxQuantity());
		jsObject.put("rxQuantity", s.getRxQuantity());
		jsObject.put("changeQuantity", s.getChangeQuantity());
		jsObject.put("newTxQuantity", s.getNewTxQuantity());
		jsObject.put("newRxQuantity", s.getNewRxQuantity());
		js.add(jsObject);
	}
	System.out.print("返回我的库存明细："+js.toString());
	out.print(js);
%>