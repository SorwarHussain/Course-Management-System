<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<html>
<head>
<!--for boostrap icon-->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
</head>
<body>
<%@ include file = "header.jsp" %> 

<div class="container my-5" style="padding-top:50px;">
<div class="container my-5">
    <h4 style="font-weight:900;color:blueviolet;text-align:center"> কোর্সসমূহ</h4>
</div>
<div class="row row-cols-1 row-cols-sm-2 row-cols-lg-3 g-4" style="padding:0 40px;">
    <c:forEach items="${book}" var="firstVar"> 
    <div class="col">
      <div class="card">
        <img src="data:image/jpg;base64,${firstVar.base64Image}" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title" style="font-weight:700;color:blueviolet"><c:out value="${firstVar.title}" /></h5>
          <%  if(!session.getAttribute("role").equals("teacher")){ %>
		<p class="card-text"><span><i class='fas fa-dollar-sign'></i> <c:out value="${firstVar.fee}" /></span>&emsp;<span><i class="bi bi-clock-history"></i> ${firstVar.duration}</span></p>
		<% } %>
          
    
        
		<%  if(session.getAttribute("role").equals("teacher")){ %>
		  <form method="post" action="ViewStudent" class="register-form">
             <div class="form-button">
		<input type="hidden" name="bistarito" id="signin"
									class="form-submit" value="${firstVar.id}" />
		</div>
		<div class="form-button" >
								<input type="submit" class="btn btn-primary" name="signin" id="signin"
									class="form-submit" value="View Student" />
		</div>
		 </form>
	<% } else {%>
		  <form method="post" action="courseDetails" class="register-form">
             <div class="form-button">
		<input type="hidden" name="bistarito" id="signin"
									class="form-submit" value="${firstVar.id}" />
		</div>
		<div class="form-button" >
								<input type="submit" class="btn btn-primary" name="signin" id="signin"
									class="form-submit" value="বিস্তারিত জানুন" />
		</div>
		 </form>
	<% } %>
         
          <!-- <a href="details.jsp" class="btn btn-primary">click</a>-->
        </div>
      </div>
    </div>
     </c:forEach>
  </div>
</div>
 <%@ include file = "footer.jsp" %> 
 
</body>
</html>