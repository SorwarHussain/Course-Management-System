
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
			style="float: right; font-size: 16px; color: white; text-align: center;">Welcome,<%=session.getAttribute("name")%>.<%=session.getAttribute("role")	%><a
			href="index.jsp" style="color: white">View Site</a></span>
	</h1>
	<div class="course">
		<!-- <h2 style="color:black;margin:0;text-align:center;"><span style="background:#417690;padding:0px;text-align:center;">Course</span></h2>-->
		<div class="cinsite">
			<p
				style="text-align: center; font-size: 24px; background: #417690; padding: 10px;">Course</p>
			<p>
				<span style="font-size: 20px;">Select course to change</span><a href="AddCourse" style="color:white;"><span
					style="float: right;" class="add_course">Add
					Course&nbsp;&nbsp;<i class='fas fa-plus'></i>
				</span></a>
			</p>
			
<%
  List<String> kjh= (List<String>)request.getAttribute("kjh");
  List<String> course_title= (List<String>)request.getAttribute("course_title");
 %>	
 <form method="post" action="Admin" class="table-form">	
  <label for="cars">Action:&nbsp;&nbsp;&nbsp;</label>
  <select  name="selectcourse">
    <option value="-----">----</option>
    <option value="delete">Delete selected courses</option>
 
  </select>
  <input type="submit" value="Go">
<table id="customers"> 
 <% for (int i=0;i<course_title.size();i++) { %>
  <tr>
 <td> <input type="checkbox" name="<%= kjh.get(i) %>" > <%= course_title.get(i) %> </td>
  </tr>
  <% } %>
</table>
</form>
<p	style="padding-left: 8px; border-bottom: 4px solid #161616; padding-bottom: 20px;"><%= course_title.size() %> Course</p>
<!-- <center><a href="display.jsp">View All </a></center>-->
<center><a href="viewCourse">View All </a></center>
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
	console.log(status,message);
	if(message!="null")
		swal(title,message,status);
	/*if(message=="null")
		console.log("ou7bare ");*/
	/*var status=document.getElementById("status").value;
	if(status == "success"){
		swal("Congrats","Account Created Successfully","success");
	}
	else if(status == "passwordNotMatch"){
		swal("Sorry","Password not matching.","error");
	}*/
	</script>

</body>
</html>