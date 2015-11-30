<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.system.management.college.department_module.DepartmentProcess"%>
<%@page import="com.system.management.college.common_module.ConstantClass"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page import="java.util.List" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Administrator</title>
<link rel="stylesheet" type="text/css" href="../4_common_module/header.css"/>
<script src="../jquery_lib/jquery-1.11.1.js"></script>
<script src="adminportalforms.js"></script>
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
</head>


<body>
	

	
	<div id="container"> 
	
		<div>
			<%@include file= "../4_common_module/header.jsp" %>
		</div>
		
		<div id="index">
		
			<ol id="indexElements">
			 
				<li> <a href="" id="addUser">ADD USER</a> 
				<li> <a href="" id="deleteUser">DELETE USER</a>
				<li> <a href="" id="addDept">ADD DEPARTMENT</a>
				<li> <a href="" id="updateFacProfDet">UPDATE FACULTY PROFESSIONAL DETAILS</a>
				<li> <a href="" id="updateStudPrevAcaDet">UPDATE STUDENT PREVIOUS ACADEMIC DETAILS</a>
				
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
	<!-- --------------------------ADD USER FORM--------------------------------------------------- -->			
					<form action="../AdminFunctions" method="post" name="addUserForm">
						<table>
							<caption ><b> Enter User Details </b></caption>
							<tr>
								<td>User Type:</td>
									<td>
										<select name="userType">
											<option value="student">Student</option>
											<option value="faculty">Faculty</option>
										 </select>  
									</td> 
							</tr>
							<tr>
								<td>User ID:</td>
								<td>
									<input type="text" name="userId">
								</td>
							</tr>
							<tr>
								<td>Department:</td>
								<td>
								<%
										DepartmentProcess deptProcess = new DepartmentProcess();
										List<String> listOfDept = deptProcess.getDeptName();
										out.println("<select name=\"dept\"");
										
										for(int i=0;i<listOfDept.size();i++){
											
											String dept = listOfDept.get(i);
											out.println("<option value = \"" + dept + "\">" + dept + "</option>");											
										}
										
								%><!--  
										<select name="dept">
											<option value="CSE">Computer Science and Engineering</option>
											<option value="IT">Information Technology</option>
											<option value="EEE">Electrical and Electronics Engineering</option>
											<option value="ECE">Electronics and Communications Engineering </option>
										 </select> 
									-->
								</td>
							</tr>
							<tr>
								<td><input type="hidden" name="requestType" value="adduser"> </td>  
								<td><input type="submit" value="Submit" name="adminFunc"> </td>
							</tr>
						</table>
					</form>
					
	<!-- --------------------------DELETE USER FORM----------------------------------------------- -->				
				  <form action="../AdminFunctions" method="post" name="deleteUserForm">
						<table>
						<caption ><b> Delete User </b></caption>
							<tr>
								<td>User Type:</td>
									<td>
										<select name="userType">
											<option value="student">Student</option>
											<option value="faculty">Faculty</option>
										 </select>  
									</td> 
							</tr>
							<tr>
								<td>User id:</td>
								<td><input type="text" name="userId"></td> 
							</tr>
							<tr>
								<td><input type="hidden" name="requestType" value="deleteUser"> </td> 
								<td><input type="submit" value="Submit" name="adminFunc"> </td>
							</tr>
						</table>
				  </form>
				  
	<!-- ------------------------ADD DEPT FORM--------------------------------------------------- -->				  
					 <form action="../AdminFunctions" method="post" name="addDeptForm">
						<table>
						<caption ><b> Enter Department Details </b></caption>
							<tr>
								<td>Department id:</td>
								<td><input type="text" name="deptId"></td> 
							</tr>
							<tr>
								<td>Department Name:</td>
								<td><input type="text" name="deptName"></td> 
							</tr>
							<tr>
								<td><input type="hidden" name="requestType" value="adddept"> </td> 
								<td><input type="submit" value="Submit" name="adminFunc"> </td>
							</tr>
						</table>
					</form>
					
	<!-- ----------------------UPDATE FACULTY PROFESSIONAL DETAILS----------------------------- -->					
					<form action="../AdminFunctions" method="post" name="updateFacultyProfDetForm">
						<table>
						<caption ><b> Update Faculty Professional Details </b></caption>
							<tr>
								<td id="facFormError"
								style="color:red" colspan="2" align="center">*Update atleast one field</td>
							</tr>
							<tr>
								<td>Faculty id:</td>
								<td><input type="text" name="facultyId"></td> 
							</tr>
							<tr>
								<td>Graduation Institute:</td>
								<td><input type="text" name="gradInstit"></td> 
							</tr>
							<tr>
								<td>Graduation Marks:</td>
								<td><input type="text" name="gradMarks"></td> 
							</tr>
							<tr>
								<td>Post Graduation Institute:</td>
								<td><input type="text" name="postGradInstit"></td> 
							</tr>
							<tr>
								<td>Post Graduation Marks:</td>
								<td><input type="text" name="postGradMarks"></td> 
							</tr>
							<tr>
								<td>Current Designation:</td>
								<td><input type="text" name="currentDesign"></td> 
							</tr>
							<tr>
								<td><input type="hidden" name="requestType" value="updfacprofdet"> </td> 
								<td><input type="submit" value="Submit" name="adminFunc"> </td>
							</tr>
						</table>
					</form>
					
	<!-- ---------------------UPDATE STUDENT PREVIOUS ACADEMIC DETAILS--------------------------------------------- -->	
						<form action="../AdminFunctions" method="post" name="updateStudPrevAcadDetForm">
						<table>
						<caption ><b> Update Student Previous Academic Details </b></caption>
							<tr>
								<td id="studFormError"
								style="color:red" colspan="2" align="center">*Update atleast one field</td>
							</tr>
							<tr>
								<td>Student id:</td>
								<td><input type="text" name="studentId"></td> 
							</tr>
							<tr>
								<td>Tenth Institute:</td>
								<td><input type="text" name="tenInstit"></td> 
							</tr>
							<tr>
								<td>Tenth Marks:</td>
								<td><input type="text" name="tenMarks"></td> 
							</tr>
							<tr>
								<td>Twelfth Institute:</td>
								<td><input type="text" name="twelveInstit"></td> 
							</tr>
							<tr>
								<td>Twelfth Marks:</td>
								<td><input type="text" name="twelveMarks"></td> 
							</tr>
							<tr>
								<td>Previous Projects:</td>
								<td><input type="text" name="prevProjects"></td> 
							</tr>
							<tr>
								<td><input type="hidden" name="requestType" value="updstupreacadet"> </td> 
								<td><input type="submit" value="Submit" name="adminFunc"> </td>
							</tr>
						</table>
					</form>
				</div>
		</div>
	</div>
</body>
</html>