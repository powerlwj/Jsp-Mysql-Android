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
	Target target = new Target();
	//Target t = new Target();
	TargetImplement tmp = new TargetImplement();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString = its.inputStream2String(jsonStream);
	System.out.print("viewmissionprocess.jsp: ++++++++++++++"+jsonString+"\n");
	Gson gson = new Gson();
	target = gson.fromJson(jsonString, Target.class);
	List<Target>  list=new ArrayList<Target>();
	list= tmp.smissionprocess(target);
	JSONArray jsArray=new JSONArray();
		for(Target t:list)	
		{
			JSONObject jsObject = new JSONObject();
			jsObject.put("target", t.getTarget());
			jsObject.put("achieved", t.getAchieved());
			jsObject.put("model", t.getModel());
			jsObject.put("remain", t.getRemain());
			jsArray.add(jsObject);
			System.out.println("viewmissionprocess.jsp: s.toString():"+t.toString()+"\n");
		}
	
	System.out.println("viewmissionprocess.jsp: 传送的数据为：" + jsArray.toString()+"\n");
	out.println(jsArray);
%>