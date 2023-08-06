<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/index-styles.css" rel="stylesheet" />
<style>
 .special{
	background: none;
	color: inherit;
	border: none;
	padding: 0;
	font: inherit;
	cursor: pointer;
	outline: inherit;
}
</style>
</head>
<body>
	<!-- Navigation-->
	<nav
		class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top"
		id="mainNav">
		<div class="container">
			
			<a class="navbar-brand" href="index.jsp" style="padding-left:140px;"><img class="rounded special" src="assets/img/logo.png"
				alt="..." style="width:100px;height:60px;" /></a>
			<button
				class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded"
				type="button" data-bs-toggle="collapse"
				data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ms-auto">
	<% if(session.getAttribute("role").equals("admin")){ %>
		<li class="nav-item mx-0 mx-lg-1"><a
				class="nav-link py-3 px-0 px-lg-3 rounded" href="Admin">Admin Panel</a></li>
	<% } else if(session.getAttribute("role").equals("teacher")){ %>
	<li class="nav-item mx-0 mx-lg-1">
	<form method="post" action="Teacher">
          <div class="form-button">
		<input type="hidden" name="instructor" id="signin"
									class="form-submit" value="<%= session.getAttribute("name")%>" />
		</div>
		<div>
		<input class="nav-link py-3 px-0 px-lg-3 rounded text-white special" type="submit" value="MY COURSE" />
		</div>
		 </form>
	
	<% } else {%>
			<li class="nav-item mx-0 mx-lg-1"><a
				class="nav-link py-3 px-0 px-lg-3 rounded special" href="Student">My Course</a></li>
			<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="viewCourse">Course</a></li>
					
	<% } %>
					<!-- <li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="viewCourse">Course</a></li>
					--><li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="about.jsp">About</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="contact.jsp">Contact</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" ><%= session.getAttribute("name")%></a></li>
						<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="logout">logout</a></li>
					
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>