<%@page import="com.system.management.college.student_module.StudentPrevAcademicModel"%>
<%@page import="com.system.management.college.student_module.StudentCurrAcademicModel"%>
<%@page import="com.system.management.college.student_module.StudentProcess"%>
<%@page import="com.system.management.college.student_module.StudentPersonalModel"%>
<%@page import="com.system.management.college.department_module.DepartmentProcess"%>
<%@page import="com.system.management.college.common_module.login.LoginModel"%>
<%@page import="com.system.management.college.common_module.ConstantClass"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page import="java.util.List" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Student</title>
<link rel="stylesheet" type="text/css" href="../4_common_module/header.css"/>
<link rel="stylesheet" href="../jquery_lib/jquery-ui.min.css">
<script src="../jquery_lib/jquery.js"></script>
<script src="../jquery_lib/jquery-ui.min.js"></script>
<script src="studentportalforms.js"></script>
</head>
<style type="text/css">
	
	body{
				background-color: silver;
				overflow: scroll-y;
	}
	
	div#container{
				position:relative;
	}
	
	div#index{
				position:fixed;
			 	/*border:thin solid navy;*/
				width:40%;
				height:90%;
	}
		
	ol#indexElements{
			
			overflow:scroll-y;
			list-style-type: circle; 
	}
	
	ol#indexElements > li{
			
			padding-bottom: 58px;
	}
	
	ol#indexElements li a{
			color:black;
			font-family: sans-serif;
			font-weight: bold;
	}
	
	div#formContainer{
		
			position:absolute;
			width:61%;
			height:800px;
			/*background-color: yellow;*/
 			overflow:scroll-y;
			left:480px;
	}
	
	div#formType{
	
			position:relative;
			top:90px;
			left:190px;
			/*border:thin solid navy*/
			padding:30px;
			width:30%;
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
	
	.formborder{
			border:thin solid navy;
			padding:20px;
	}
	
	.showError{
				box-shadow:0 0 25px red;
	}
	
	
</style>

<%
LoginModel login = new LoginModel();
login.setUserId("S1000");
login.setPrivilege_level(1);
session.setAttribute("user",login);
	//LoginModel login = (LoginModel)session.getAttribute("user");
%>



<body>
	

	
	<div id="container"> 
	
		<div>
			<%@include file= "../4_common_module/header.jsp" %>
		</div>
		
		<div>
			<h2 style="text-align:center;"> Welcome Student: <%= login.getUserId() %> </h2>
		</div>
		
		<div id="index">
		
			<ol id="indexElements">
			 
				<li> <a href="" id="viewProfile">VIEW PROFILE</a> 
				<li> <a href="" id="updatePersonalDetails">	UPDATE PERSONAL DETAILS</a>
				
			</ol>
			
		</div>
		
		<div id="formContainer">
				<div id="formType">
				  
				  	<% 
						if(session.getAttribute("message") != null){
						ConstantClass.errorMessage = session.getAttribute("message").toString();
						out.write("<div id=\"formError\" style=\"color:red;border:thin solid navy;padding:30px;\">" 
						+ ConstantClass.errorMessage + "</div>");
						//System.out.println(message);
						ConstantClass.message = null;
						ConstantClass.errorMessage = null;
						session.removeAttribute("message");
					}	
					%>
	<!-- --------------------------VIEW PROFILE FORM--------------------------------------------------- -->			
					<form action="../StudentFunctions" method="post" name="viewProfileForm">
										
						<table>
						<%
						
						//show faculty profile
						StudentPersonalModel studentPersonalObj = new StudentPersonalModel();
						StudentProcess studentProcess = new StudentProcess();
						
						studentPersonalObj = new StudentPersonalModel();
						studentPersonalObj.setStudentId(login.getUserId());
						studentPersonalObj.setMessage("viewingPersonalProfile");
						
						List<StudentPersonalModel> listDet = studentProcess.viewStudentPersonalDetails(studentPersonalObj);
						if(listDet.isEmpty())
							out.write("<th>No personal details saved in the database..please enter your personal details and" +
										" then try again!</th>");
						else
						{
							out.write("<br><br>");
							out.write("<caption style=\"text-decoration:underline;\"><b> Personal Details of Student: "+ login.getUserId() +"</b></caption>");
							for(StudentPersonalModel obj : listDet){
								out.write("<tr><td><b>Faculty id: </b></td><td>" + obj.getStudentId() + "</td></tr>");
								out.write("<tr><td><b>First Name: </b></td><td>" + obj.getFirstName() + "</td></tr>");
								out.write("<tr><td><b>Last Name: </b></td><td>" + obj.getLastName() + "</td></tr>");
								out.write("<tr><td><b>Email id: </b></td><td>" + obj.getEmailId() + "</td></tr>");
								out.write("<tr><td><b>Contact: </b></td><td>" + obj.getContact() + "</td></tr>");
								out.write("<tr><td><b>Hire date: </b></td><td>" + obj.getJoinDate() + "</td></tr>");
								out.write("<tr><td><b>Date of birth: </b></td><td>" + obj.getDateOfBirth() + "</td></tr>");
							}
						}	
						%>
						</table>
	
						<table>
					
						<%
						//Viewing Current academic details 
						StudentCurrAcademicModel studentcurrModel = new StudentCurrAcademicModel();
						studentcurrModel.setStudentId(login.getUserId());
						
						List <StudentCurrAcademicModel> listDet2 = studentProcess.viewStudentCurrAcademicDetails(studentcurrModel);
						if(listDet2.isEmpty())
							out.write("<th>No Current Academic details saved in the database..please enter your personal details and" +
							" then try again!</th>");
						else{
							out.write("<caption style=\"text-decoration:underline;\"><b>Current Academic Details of Student: "+ login.getUserId() +"</b></caption>");
							for(StudentCurrAcademicModel obj : listDet2){
								out.write("<tr><td><b>Student id:</b></td><td>" + obj.getStudentId() + "</td></tr>");
								out.write("<tr><td><b>Department id: </b></td><td>" + obj.getDepartmentId() + "</td></tr>");
								out.write("<tr><td><b>Current Semester: </b></td><td>" + obj.getCurrentSem() + "</td></tr>");
								out.write("<tr><td><b>Semester 1 Marks: </b></td><td>" + obj.getSem1Marks()+ "</td></tr>");
								out.write("<tr><td><b>Semester 2 Marks: </b></td><td>" + obj.getSem2Marks() + "</td></tr>");
								out.write("<tr><td><b>Semester 3 Marks: </b></td><td>" + obj.getSem3Marks() + "</td></tr>");
								out.write("<tr><td><b>Semester 4 Marks: </b></td><td>" + obj.getSem4Marks() + "</td></tr>");
							}
						}	
						
						%>
						
						</table>
						
						<table>
					
						<%
						//Viewing Previous academic details 
						StudentPrevAcademicModel studentprevModel = new StudentPrevAcademicModel();
						studentprevModel.setStudentId(login.getUserId());

						
						List <StudentPrevAcademicModel> listDet3 = studentProcess.viewStudentPrevAcademicDetails(studentprevModel);
						if(listDet3.isEmpty())
							out.write("<th>No Previous Academic details saved in the database..please enter your personal details and" +
							" then try again!</th>");
						else{
							out.write("<caption style=\"text-decoration:underline;\"><b>Previous Academic Details of Student: "+ login.getUserId() +"</b></caption>");
							for(StudentPrevAcademicModel obj : listDet3){
								out.write("<tr><td><b>Student id:</b></td><td>" + obj.getStudentId() + "</td></tr>");
								out.write("<tr><td><b>Tenth Institute: </b></td><td>" + obj.getTenInstit() + "</td></tr>");
								out.write("<tr><td><b>Tenth Marks: </b></td><td>" + obj.getTenMarks() + "</td></tr>");
								out.write("<tr><td><b>Twelfth Institute: </b></td><td>" + obj.getTwelveInstit()+ "</td></tr>");
								out.write("<tr><td><b>Twelfth Marks: </b></td><td>" + obj.getTwelveMarks() + "</td></tr>");
								out.write("<tr><td><b>Previous Projects: </b></td><td>" + obj.getPrevProjects() + "</td></tr>");
							}
						}	
						
						%>
						
						</table>
					</form>
					
	<!-- --------------------------UPDATE PERSONAL DETAILS FORM----------------------------------------------- -->				
				  <form action="../StudentFunctions" method="post" name="updatePersonalDetailsForm">
						<table>
						<caption ><b> Update your personal Details </b></caption>
							<tr>
								<td id="updatePersonalFormError"
								style="color:red" colspan="2" align="center">*Update at least one field</td>
							</tr>
							
			
							<tr>
								<td>First Name:</td>
								<td><input type="text" name="firstName"></td> 
							</tr>
							<tr>
								<td>Last Name:</td>
								<td><input type="text" name="lastName"></td> 
							</tr>
							<tr>
								<td>Email id:</td>
								<td><input type="text" name="emailId"></td> 
							</tr>
							<tr>
								<td>Contact Number:</td>
								<td><input type="text" name="contact"></td> 
							</tr>
							<tr>
								<td>Date of Joining:</td>
								<td><input type="text" name="joinDate" class="date"></td> 
							</tr>
							<tr>
								<td>Date of Birth:</td>
								<td><input type="text" name="dateOfBirth" class="date"></td> 
							</tr>
							
							<tr>
								<td><input type="hidden" name="requestType" value="updatePersonalDetails"> </td> 
								<td><input type="submit" value="Submit" name="studentFunc"> </td>
							</tr>
						</table>
					</form>
				</div>
		</div>
	</div>
	 
</body>
</html>