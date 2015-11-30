<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.system.management.college.common_module.login.*" %>
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

<script src="adminLoginScript.js"></script>
<script src="1_admin_module/adminLoginScript.js"></script>

<title>Administrator Login Page</title>	

<style type="text/css">

	#container{
				position:relative;
	}
	
	div#login {
				position:relative;
	}
	
	div#signIn{
				position:relative;
				border-style:ridge;
				border-width:medium;
				width:30%;
				margin-left:auto;
				margin-right:auto;
				top:100px;
				left:30px;
				padding-top:20px;
				padding-left:30px;
				padding-right:30px;
				padding-bottom:35px;
	}
	
	input[type=text], textarea {
  		    -webkit-transition: all 0.30s ease-in-out;
  	   	    -moz-transition: all 0.30s ease-in-out;
  		    -ms-transition: all 0.30s ease-in-out;
  		    -o-transition: all 0.30s ease-in-out;
  			outline: none;
  			padding: 3px 0px 3px 3px;
  			margin: 5px 1px 3px 0px;
 			border: 1px solid #DDDDDD;
	}
 
	input[type=text]:focus, textarea:focus {
 			 box-shadow: 0 0 5px rgba(81, 203, 238, 1);
			 padding: 3px 0px 3px 3px;
		     margin: 5px 1px 3px 0px;
  			 border: 1px solid rgba(81, 203, 238, 1);
	}
	
	.showError{
				box-shadow:0 0 25px red;
	}
	
	
</style>
</head>
<body>
	
	<div id="container">
		<div>
			<%@include file="../4_common_module/header.jsp"%>
		</div>
		
		<h2 style="text-align:center;color:red"> Authenticate yourself by providing user id 
		and password</h2>
		<div id="login">
		
			<div id="signIn">
				<form method="post" action="../AdminValidation" name="AdminSignInForm">
					<table>
					<caption style="text-align:center;text-decoration:underline;"> Sign In </caption>			  
				  	<tr>
				  	<th colspan="2">
				  	<p style="color:red;"> ${message}</p>
					</th>				
					</tr>
					
					<tr>
						<td>User name:</td>	
					    <td><input type="text" name="username"></td>
					</tr>
					<tr> 
						<td>Password:</td>
					  	<td><input type="password" name="password"></td>	
					</tr>
					
					<tr>
						<td><input type="submit" value="Submit" name="login"></td>
					</tr>
					<!-- <p id="error" style="text-align:center;color:red"></p>  -->
					</table>
				</form>
			</div>
		</div>

 </div>
</body>
</html>