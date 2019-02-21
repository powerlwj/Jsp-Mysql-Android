<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="classes_for_implement_of_interface.TargetImplement"%>
<%@page import="classes_for_JavaBean.Target"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="java.io.InputStream"%>

<%
	System.out.print("specialplanalert.jsp: ++++++++++++++Special Alerting>>>>>>>"+"\n");
	Target target = new Target();
	Target t = new Target();
	List<Target> targList=new ArrayList<Target>();
	TargetImplement tmp = new TargetImplement();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString = its.inputStream2String(jsonStream);
	System.out.print("specialplanalert.jsp: ++++++++++++++"+jsonString+"\n");
	Gson gson = new Gson();
	target = gson.fromJson(jsonString, Target.class);
	targList = tmp.queryspe(target);
	JSONArray jsArray=new JSONArray();
	for(Target ta:targList)
	{
		JSONObject jsObject=new JSONObject();
		jsObject.put("model", ta.getModel());
		jsObject.put("target", ta.getTarget());
		jsObject.put("targetAmount", ta.getTargetAmount());
		jsObject.put("price", ta.getPrice());
		jsObject.put("marketPrice", ta.getMarketPrice());
		jsObject.put("targetTime", ta.getTargetTime());
		jsObject.put("targetTime2", ta.getTargetTime2());
		jsObject.put("type", ta.getType());
		jsArray.add(jsObject);
	}
	System.out.println("specialplanalert.jsp: 服务器返回的特殊计划为：:"+jsArray.toString()+"\n");
	out.print(jsArray);
%>