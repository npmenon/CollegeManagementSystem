<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.system.management.college.department_module.DepartmentProcess"%>
<%@ page import="java.util.List" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Faculty Details</title>
</head>
<body>

	<%
		DepartmentProcess deptProcess = new DepartmentProcess();
		List<String> listOfDept = deptProcess.getDeptName();
		out.println("<select name=\"dept\"");
	
		for(int i=0;i<listOfDept.size();i++){
		
			String dept = listOfDept.get(i);
			out.println("<option value = \"" + dept + "\">" + dept + "</option>");											
	}								
										
	%>

</body>
</html>