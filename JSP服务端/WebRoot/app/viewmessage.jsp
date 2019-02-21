<%@page import="com.google.gson.JsonArray"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="classes_for_implement_of_interface.MessageImplement"%>
<%@page import="classes_for_JavaBean.Message"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="java.io.InputStream"%>

<%
	Message message=new Message();
	MessageImplement messimp=new MessageImplement();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString = its.inputStream2String(jsonStream);
	Gson gson = new Gson();
	message=gson.fromJson(jsonString, Message.class);
	List<Message> list=new ArrayList<Message>();
	System.out.println("Level's size::"+message.getLevel());
	if(message.getLevel()==5)
	{
		//普通员工请求二级经理信息
		list=messimp.ordquery();
		
	}else if(message.getLevel()==4)
	{
		//二级经理请求一级经理信息
		list=messimp.sedquery();
	}
	JSONArray js=new JSONArray();
	for(Message m: list)
	{
		JSONObject jsObject=new JSONObject();
		jsObject.put("senderID", m.getSenderID());
		jsObject.put("title", m.getTitle());
		jsObject.put("content", m.getContent());
		jsObject.put("level", m.getLevel());
		jsObject.put("addtime", m.getAddtime());
		
		
		js.element(jsObject);
	}
	
	System.out.println("jsArray.toString():"+js.toString());
	out.print(js);

%>