<%@page import="classes_for_implement_of_interface.EmployeeImplement"%>
<%@page import="classes_for_JavaBean.Employee"%>
<%@page import="java.awt.List"%>
<%@page
	import="classes_for_implement_of_interface.AdministratorImplement"%>
<%@page import="classes_for_JavaBean.Administrator"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="classes_for_Tools.InputStreamTOString"%>
<%@page import="java.io.InputStream"%>

<%
	Employee em = new Employee();
	EmployeeImplement emp = new EmployeeImplement();
	InputStreamTOString its = new InputStreamTOString();
	InputStream jsonStream = request.getInputStream();
	String jsonString = its.inputStream2String(jsonStream);
	Gson gson = new Gson();
	em = gson.fromJson(jsonString, Employee.class);
	System.out.println("gsdf" + em.getID() + em.getPassword());
	boolean index = emp.updatepass(em);
	System.out.println("密码修改" + index);
	out.print(index);
%>