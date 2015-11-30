<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MAMCE - Main Page</title>
<link rel="stylesheet" type="text/css" href="mainview.css"/>
<link rel="stylesheet" type="text/css" href="4_common_module/header.css"/>
<script src="jquery_lib/jquery-1.11.1.js"></script>

</head>
<body>

 <div id="container">
 
	<div>
		<%@include file= "4_common_module/header.jsp" %>
	</div>
	
	<div id="imagegallery">
	
		<ul>		
			<li> <img alt="building" src="gfx/collegebuilding.png" width="600px"  height="250px" class="slider">
			<!-- <li> <img alt="graduation" src="gfx/graduation.jpg" width="550px"  height="250px" class="slider">  
			-->		
		</ul>
	
	</div>
	
	<div id="login">
	
	</div>

	<div id="container_portal">
	
		<marquee><h2 style="font-style:italic;">  WELCOME TO MAMCE </h2></marquee>
		
		<div id="portals">		
		
			<a href="1_admin_module/adminLogin.jsp" >
				<img alt="Administrator" src="gfx/mainview_gfx/admin.jpg" 
				id="admin" width="80%">
			</a>
			
			<a href="2_faculty_module/FacultyLogin.jsp" >					
				<img alt="Faculty" src="gfx/mainview_gfx/faculty.jpg" id="faculty"
				width="80%">
			</a>
				
			<a href="3_student_module/studentLogin.jsp" >		
				<img alt="Student" src="gfx/mainview_gfx/student.jpg" id="student"
				width="80%">
			</a>
		
		</div>
		
	</div>
	
	<div>
		<%@include file="../4_common_module/footer.jsp"%>	
	</div>
	
  </div>

</body>
</html>