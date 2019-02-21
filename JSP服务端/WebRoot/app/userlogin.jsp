<%@page import="com.sun.org.apache.xalan.internal.xsltc.compiler.sym"%>
<%@page import="classes_for_implement_of_interface.EmployeeImplement"%>
<%@page import="classes_for_JavaBean.Employee"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="java.io.InputStream"%>

<%
	Employee em1 = new Employee();
	Employee em2 = new Employee();
	EmployeeImplement emp = new EmployeeImplement();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString = its.inputStream2String(jsonStream);
	Gson gson = new Gson();
	em1 = gson.fromJson(jsonString, Employee.class);
	String password=em1.getPassword();
	em2=emp.queryuser(em1);
	System.out.println(">>>>>>>" + em2.toString());
	if(em2.getLevel()==5||em2.getLevel()==4)
	{
		if(password.equals(em2.getPassword()))
		{
			boolean b=emp.createcheck(em1);
			System.out.println("签到信息产生？：" + b);
		}
	}
	JSONObject jsObject = new JSONObject();
	jsObject.put("ad_type", em2.getLevel());
	jsObject.put("region", em2.getRegion());
	jsObject.put("own", em2.getOwn());
	jsObject.put("Is_Special", em2.getIs_Special());
	jsObject.put("password", em2.getPassword());
	System.out.println("password is:"+em2.getPassword());
	System.out.println("生成json测试：" + jsObject);

	out.println(jsObject);
%>