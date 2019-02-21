<%@page import="classes_for_implement_of_interface.EmployeeImplement"%>
<%@page import="classes_for_JavaBean.Employee"%>
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
	Employee em = new Employee();
	EmployeeImplement empl = new EmployeeImplement();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString=its.inputStream2String(jsonStream);
	JSONObject js=new JSONObject();
	js.optString(jsonString);
	JSONObject jsonObj = JSONObject.fromObject(jsonString);
	Gson gson=new Gson();
	String model=jsonObj.optString("reporterID");
	System.out.println("model>>>>>"+model);
	em.setReporterID(model);
	List<String> list=new ArrayList<String>();
	list=empl.queryperson(em);
	JSONArray jsArray=JSONArray.fromObject(list);
	System.out.println("生成的Json:"+jsArray.toString());
	out.print(jsArray);
%>