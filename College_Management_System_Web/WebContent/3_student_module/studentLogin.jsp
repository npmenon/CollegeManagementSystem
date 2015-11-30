<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.system.management.college.common_module.ConstantClass" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../4_common_module/header.css"/>
<link rel="stylesheet" type="text/css" href="4_common_module/header.css"/>

<link rel="stylesheet" href="../jquery_lib/jquery-ui.min.css">
<link rel="stylesheet" href="jquery_lib/jquery-ui.min.css">

<script src="../jquery_lib/jquery-1.11.1.js"></script>
<script src="jquery_lib/jquery-1.11.1.js"></script>

<script src="../jquery_lib/jquery-ui.min.js"></script>
<script src="jquery_lib/jquery-ui.min.js"></script>

<script src="studentLoginScript.js"></script>
<script src="3_student_module/studentLoginScript.js"></script>


<title>Student Login Page</title>

<style type="text/css">

	div#login {
				position:relative;
	}
	
	div#signIn{
				position:relative;
				border-style:solid;
				border-width:thin;
				width:30%;
				top:120px;
				left:30px;
				padding:10px;
	}
	
	div#signUp{
				position:absolute;
				border-style:solid;
				border-width:thin;
				width:30%;
				top:100px;
				left:700px;
				padding:10px;
	}
	
	
</style>
</head>
<body>

	<div id="container">
		<div>
			<%@include file="../4_common_module/header.jsp"%>
		</div>
	
		<h2 style="text-align:center;color:red"> Welcome Student</h2>
		<h2 style="text-align:center;color:red"> Authenticate yourself by providing user id 
		and password</h2>
	
		<div id="login">
		
			<div id="signIn">
				<form method="post" action="/College_Management_System_Web/StudentFacultyLogin" 
				name="StudentSignIn"> 
				
				<table>	
					<caption style="text-align:center;text-decoration:underline;"> Sign In </caption>			  
				  	<tr>
				  	<th colspan="2">
				  	<% 
						if(request.getAttribute("messageSignIn") != null){		
							ConstantClass.errorMessage = "*" + request.getAttribute("messageSignIn").toString();
							out.write("<div id=\"formError\" style=\"color:red;padding:10px;\">" 
							+ ConstantClass.errorMessage + "</div>");
							//System.out.println(message);
							ConstantClass.message = null;	
							ConstantClass.errorMessage = null;
					}	
					%>	
					</th>
					</tr>
					
					<tr>
						<td>User name:</td>	
					    <td><input type="text" name="username"></td>
					</tr>
					<tr> 
						<td> Password:</td>
					  	<td><input type="password" name="password"></td>	
					</tr>
					
					<tr>
					<td colspan="2" style="text-align:center;"> 
						<input type="submit" value="Submit">
					</td>
					</tr>
					<input type="hidden" name="userType" value="student" />
					<input type="hidden" name="loginType" value="signIn"/>
				</table>
				</form>
			</div>
		
			<div id="signUp">
			<form  method="post" action="../StudentFacultyLogin" name="studentSignUp">
				<table>
					<caption style="text-align:center;text-decoration:underline;"> Change password </caption>
					<tr>
					<th colspan="2">
				  	<% 
						if(request.getAttribute("messageSignUp") != null){		
							ConstantClass.errorMessage = "*" + request.getAttribute("messageSignUp").toString();
							out.write("<div id=\"formError\" style=\"color:red;padding:10px;\">" 
							+ ConstantClass.errorMessage + "</div>");
							ConstantClass.message = null;	
							ConstantClass.errorMessage = null;
					}	
					%>
					</th>	
					</tr>
					
					<tr>
						<td> User name: </td>
						<td><input type="text" name="username"> </td>
					</tr>
					
					<tr>
						<td> Old Password: </td>
						<td> <input type="password" name="oldpassword"> </td>
					</tr>
					
					<tr>
						<td> New Password: </td>
						<td><input type="password" name="newpassword"> </td>
					</tr>
					
					<tr>
						<td> Confirm Password: </td>
						<td> <input type="password" name="confpassword"> </td>	
					</tr>
					
					<tr>
						<td colspan="2" style="text-align:center;">
							<input type="submit" value="Submit"> 
						</td>
					</tr>
					<input type="hidden" name="userType" value="student" />
					<input type="hidden" name="loginType" value="signUp"/>
				 </table>
				</form>
			</div>
		</div>

 </div>

</body>
</html>