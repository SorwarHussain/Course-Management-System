<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<style>
table {
  border-collapse: collapse;
  width: 66%;
  margin:40px 200px 100px;
}

th, td {
  text-align: left;
  padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}
tr:hover {background-color: coral;}

th {
  background:#417690;
  color: white;
}
</style>
</head>
<body> 
<%@ include file = "header.jsp" %> 
<h1 style="text-align:center;margin-top:150px;">Student List:</h1>
<%
  List<String> kjh= (List<String>)request.getAttribute("book");
  
 %>	

<table>
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Email</th>
   
  </tr>
  <c:forEach items="${book}" var="firstVar"> 
  <tr>
    <td>${firstVar.id}</td>
    <td>${firstVar.name}</td>
    <td>${firstVar.email}</td>
  </tr>
    </c:forEach>
  
  
</table>
<%@ include file = "footer.jsp" %> 
</body>
</html>