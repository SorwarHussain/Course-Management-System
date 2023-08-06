<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Main css -->
<link rel="stylesheet" href="css/admin.css">
<script src='https://kit.fontawesome.com/a076d05399.js'
	crossorigin='anonymous'></script>
</head>
<body>
<input type="hidden" id="title" value="<%= request.getAttribute("title") %>">
<input type="hidden" id="message" value="<%= request.getAttribute("message") %>">
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
	<h1>
		Admin panel <span
			style="float: right; font-size: 16px; color: white; text-align: center;">Welcome,<%=session.getAttribute("name")%>.<a
			href="index.jsp" style="color: white">View Site</a></span>
	</h1>
	<div style="display:flex;">
	<div class="course">
	<div class="cinsite_1">
			<p
				style="text-align: center; font-size: 24px; background: #417690; padding: 10px;">Course</p>
<%
  List<String> kjh= (List<String>)request.getAttribute("kjh");
  List<String> course_title= (List<String>)request.getAttribute("course_title");
 %>	
<%
  List<String> teacher= (List<String>)request.getAttribute("teacher");
 %>	
<table id="customers"> 
  <% for (int i=0;i<course_title.size();i++) { %>
  <tr>
 <td> <%= course_title.get(i) %> </td>
  </tr>
  <% } %>
</table>
			<p
				style="padding-left: 8px; border-bottom: 4px solid #161616; padding-bottom: 20px;"><%= course_title.size() %> Course</p>
				
	</div>
	</div>
	<div class="add_course_details">
	<h5 style="font-size:23px;font-weight:300;padding-top:0px;margin-top:30px;">Add Course</h5>
	<form method="post" action="AddCourse" class="register-form"
							id="login-form" enctype="multipart/form-data">
							<div class="form-group">
							 <label for="username">Title:</label>
  <input type="text" id="username" name="title"><br>
  </div>
  <div class="form-group">
  <label for="username">Fee:</label>
  <input type="text" id="username" name="fee"><br>
  </div>
   <div class="form-group">
  <label for="username">Level:</label>
  <input type="text" id="username" name="level"><br>
  </div>
   <div class="form-group">
  <label for="username">Duration:</label>
  <input type="text" id="username" name="duration"><br>
  </div>
   <div class="form-group">
  <label for="username">Audience:</label>
  <input type="text" id="username" name="audience"><br>
  </div>
   <div class="form-group">
  <label for="username">AboutCourse:</label>
  <input type="text" id="username" name="aboutCourse"><br>
  </div>
  <div class="form-group">
  <label for="username">Instructor:</label>
  		<select id="cars" name="instructor">
  		 <% for (int i=0;i<teacher.size();i++) { %>
  		 <option value="<%= teacher.get(i) %>"><%= teacher.get(i) %></option>
  		   <% } %>
</select>
  </div>
  <div class="form-group">
  <label>Image:</label>
  <input type="file" name="file">
  </div>
									
							
							<div class="form-button">
								<input type="submit" name="signin" id="signin"
									class="form-submit" value="Save" />
							</div>
						</form>
	</div>
	</div>
<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<!-- <link rel="stylesheet" href="alert/dist/sweetalert.css">-->
	<script type="text/javascript">
	var title=document.getElementById("title").value;
	var message=document.getElementById("message").value;
	var status=document.getElementById("status").value;
	if(message!="null")
		swal(title,message,status);
	</script>
</body>
</html>