<%@page import="classes_for_implement_of_interface.TargetImplement"%>
<%@page import="classes_for_JavaBean.Target"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="java.io.InputStream"%>

<%
	Target target=new Target();
	TargetImplement tip=new TargetImplement();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString = its.inputStream2String(jsonStream);
	Gson gson = new Gson();
	target=gson.fromJson(jsonString, Target.class);
	int index=target.getIndex();
	List<Target>  list=new ArrayList<Target>();
	list=tip.viewallocatedmission(target);
	JSONArray jsArray=new JSONArray();
	JSONObject jsObject=new JSONObject();
	if(index==1)
	{
		for(Target t:list)
		{
			jsObject.put("target", t.getTarget());
			jsObject.put("type", t.getType());
			jsObject.put("model", t.getModel());
			jsObject.put("targetAmount", t.getTargetAmount());
			jsArray.add(jsObject);
			System.out.println("s.toString():"+t.toString());
		}
	}
	else if(index==2)
	{
		for(Target t:list)
		{
			jsObject.put("target", t.getTarget());
			jsObject.put("type", t.getType());
			jsObject.put("model", t.getModel());
			jsObject.put("targetAmount", t.getTargetAmount());
			jsArray.add(jsObject);
			System.out.println("s.toString():"+t.toString());
		}
	}
	
	out.print(jsArray);
%>