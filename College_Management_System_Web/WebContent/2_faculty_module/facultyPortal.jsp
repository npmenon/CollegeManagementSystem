<%@page import="com.system.management.college.student_module.StudentProcess"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.system.management.college.common_module.ConstantClass"%>
<%@page import="com.system.management.college.common_module.login.LoginModel"%>
<%@page import="com.system.management.college.faculty_module.*"%>
<%@page import="com.system.management.college.student_module.*"%>
<%@page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Faculty Portal</title>
<link rel="stylesheet" type="text/css" href="../4_common_module/header.css"/>
<link rel="stylesheet" href="../jquery_lib/jquery-ui.min.css">
<script src="../jquery_lib/jquery.js"></script>
<script src="../jquery_lib/jquery-ui.min.js"></script>
<script src="facultyportalforms.js"></script>
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
	login.setUserId("F1000");
	login.setPrivilege_level(2);
	session.setAttribute("user",login);
	//LoginModel login = (LoginModel)session.getAttribute("user");
%>

</head>
<body>
		<div id="container"> 
	
		<div>
			<%@include file= "../4_common_module/header.jsp" %>
		</div>
		
		<div>
			<h2 style="text-align:center;"> Welcome faculty: <%= login.getUserId() %> </h2>
		</div>
		
		<div id="index">
		
			<ol id="indexElements">
			 
				<li> <a href="" id="viewProfile">VIEW PROFILE</a> 
				<li> <a href="" id="modifyDetails">MODIFY PERSONAL DETAILS</a>
				<li> <a href="" id="updateStudAcaDet">UPDATE STUDENT ACADEMIC DETAILS</a>
				
			</ol>
			
		</div>
		
		<div id="formContainer">
				<div id="formType">
				  
	<!-- --------------------------Error messages------------------------------------------------ -->
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
	<!-- --------------------------VIEW PROFILE--------------------------------------------------- -->			
					<form action="../FacultyFunctions" method="post" name="viewProfileForm">
						<table>
						<%
						
						//show faculty profile
						FacultyPersonalModel facultyPersonalObj = new FacultyPersonalModel();
						FacultyProcess facultyProcess = new FacultyProcess();
						
						facultyPersonalObj = new FacultyPersonalModel();
						facultyPersonalObj.setFacultyId(login.getUserId());
						facultyPersonalObj.setMessage("viewingPersonalProfile");
						
						ArrayList<FacultyPersonalModel> listDet = facultyProcess.viewPersonalProfile(facultyPersonalObj);
						if(listDet.isEmpty())
							out.write("<th>No personal details saved in the database..please enter your personal details and" +
										" then try again!</th>");
						else
						{
							out.write("<br><br>");
							out.write("<caption style=\"text-decoration:underline;\"><b> Personal Details of faculty: "+ login.getUserId() +"</b></caption>");
							for(FacultyPersonalModel obj : listDet){
								out.write("<tr><td><b>Faculty id: </b></td><td>" + obj.getFacultyId() + "</td></tr>");
								out.write("<tr><td><b>First Name: </b></td><td>" + obj.getFirstName() + "</td></tr>");
								out.write("<tr><td><b>Last Name: </b></td><td>" + obj.getLastName() + "</td></tr>");
								out.write("<tr><td><b>Email id: </b></td><td>" + obj.getEmailId() + "</td></tr>");
								out.write("<tr><td><b>Contact: </b></td><td>" + obj.getContact() + "</td></tr>");
								out.write("<tr><td><b>Hire date: </b></td><td>" + obj.getHire_date() + "</td></tr>");
								out.write("<tr><td><b>Date of birth: </b></td><td>" + obj.getDateOfBirth() + "</td></tr>");
							}
						}	
						%>
						</table>
						<table>
					
						<%
						//Viewing Current academic details 
						FacultyProfModel facultyProfModel = new FacultyProfModel();
						facultyProfModel.setFacultyId(login.getUserId());
						
						ArrayList<FacultyProfModel> listDet2 = facultyProcess.viewProfessionalProfile(facultyProfModel);
						if(listDet2.isEmpty())
							out.write("<th>No professional details saved in the database..please enter your personal details and" +
							" then try again!</th>");
						else{
							out.write("<caption style=\"text-decoration:underline;\"><b> Professional Details of faculty: "+ login.getUserId() +"</b></caption>");
							for(FacultyProfModel obj : listDet2){
								out.write("<tr><td><b>Faculty id:</b></td><td>" + obj.getFacultyId() + "</td></tr>");
								out.write("<tr><td><b>Department id: </b></td><td>" + obj.getDepartmentId() + "</td></tr>");
								out.write("<tr><td><b>Graduation Institution: </b></td><td>" + obj.getGradInstit() + "</td></tr>");
								out.write("<tr><td><b>Graduation marks: </b></td><td>" + obj.getGradMarks() + "</td></tr>");
								out.write("<tr><td><b>Post graduation institution: </b></td><td>" + obj.getPostGradInstit() + "</td></tr>");
								out.write("<tr><td><b>Post graduation marks: </b></td><td>" + obj.getPostGradMarks() + "</td></tr>");
								out.write("<tr><td><b>Current designation: </b></td><td>" + obj.getCurrentDesign() + "</td></tr>");
							}
						}	
						
						%>
						
						</table>
					</form>
				
	<!-- ----------------------MODIFY PERSONAL DETAILS-------------------------------------------- -->					
					<form action="../FacultyFunctions" method="post" name="modifyPersDetForm">
						<table>
						<caption ><b> Modify Personal Details </b></caption>
							<tr>
								<td id="facFormError"
								style="color:red" colspan="2" align="center">*Update atleast one field</td>
							</tr>
							<tr>
								<td>First Name: </td>
								<td><input type="text" name="firstName"></td> 
							</tr>
							<tr>
								<td>Last Name: </td>
								<td><input type="text" name="lastName"></td> 
							</tr>
							<tr>
								<td>Email id: </td>
								<td><input type="text" name="emailId"></td> 
							</tr>
							<tr>
								<td>Contact: </td>
								<td><input type="text" name="contact"></td> 
							</tr>
							<tr>
								<td>Hire date: </td>
								<td><input type="text" name="hire_date" class="date"></td> 
							</tr>
							<tr>
								<td>Date of birth: </td>
								<td><input type="text" name="dateOfBirth" class="date"></td> 
							</tr>
							<tr>
								<td><input type="hidden" name="requestType" value="modifyperdet"> </td> 
								<td><input type="submit" value="Submit" name="facultyFunc"> </td>
							</tr>
						</table>
					</form>
					
	<!-- ---------------------UPDATE STUDENT ACADEMIC DETAILS--------------------------------------------- -->	
						<form action="../FacultyFunctions" method="post" name="updateStudCurrAcadDetForm">
						<table>
						<caption ><b> Update Student Current Academic Details </b></caption>
							<tr>
								<td id="studFormError"
								style="color:red" colspan="2" align="center">*Update atleast one field</td>
							</tr>
							<tr>
								<td>Student Id: </td>
								<td>
								<%
									StudentProcess studentProcess = new StudentProcess();
									List<String> listOfStud = studentProcess.getStudentList(login.getUserId());
									out.println("<select name=\"studentId\">");
									
									if(listOfStud.isEmpty())
										out.println("<option value = \"noStudent\" >" + "No students under you" + "</option>");
									else{
										for(int i=0;i<listOfStud.size();i++){
											
											String student = listOfStud.get(i);
											out.println("<option value = \"" + student + "\">" + student + "</option>");											
										}
									}
									
									out.println("<select>");

								%>
								</td>
				
							</tr>
							<tr>
								<td>Semester 1 marks: </td>
								<td><input type="text" name="sem1Marks"></td> 
							</tr>
							<tr>
								<td>Semester 2 marks: </td>
								<td><input type="text" name="sem2Marks"></td> 
							</tr>
							<tr>
								<td>Semester 3 marks: </td>
								<td><input type="text" name="sem3Marks"></td> 
							</tr>
							<tr>
								<td>Semester 4 marks: </td>
								<td><input type="text" name="sem4Marks"></td> 
							</tr>
							<tr>
								<td>Current Semester: </td>
								<td><input type="text" name="currentSem"></td> 
							</tr>
							<tr>
								<td><input type="hidden" name="requestType" value="updstucurracadet"> </td> 
								<td><input type="submit" value="Submit" name="facultyFunc"> </td>
							</tr>
						</table>
					</form>
				</div>
		</div>
	</div>
</body>
</html>