<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page import="java.sql.*"%>

<%@ page import="java.io.*"%>

<html>
<head>
<!--for boostrap icon-->

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<link href="css/course_details.css" rel="stylesheet" />
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
</head>
<body>
<%@ include file = "header.jsp" %> 
<div class="container p-4 border" style="margin-top:140px;">
      <div class="row">
        <div class="col-lg-8">
          <h2>${book.title}</h2>
      <div class="">
        <img class="img-fluid" src="data:image/jpg;base64,${book.base64Image}" class="rounded" alt="..." style="padding:40px 0" width=94% height="300">
      </div>
        </div>
        <div class="col-lg-4 border rounded" style="padding:0px">
          <div style="background:#f4f6f9;padding:40px 30px" >
    <c:choose>
    <c:when test="${book.fee=='0'}">
      <h3 style="padding-bottom: 17px;">Free</h3>
    </c:when>
     <c:otherwise>
        <p class="">${book.fee}  <i class='fas fa-dollar-sign'></i></p>
    </c:otherwise>
	</c:choose> 
		 	 <%
		 int user_id=(int)session.getAttribute("user_id");
		 int course_id=(int)request.getAttribute("course_id");
		 %>
	<div class="d-grid" >
              <!-- <a  href="{% url 'checkout' course.slug %}"  href="{% url 'login' %}" {% endif %} type="button" class="btn btn-outline-danger  btn-block" style="font-size:24px;font-weight:600">Enroll Now</a>-->
			<form method="post" action="Student">
          <div class="form-button">
		<input type="hidden" name="course_id" value="${book.id}" />
		</div>
		  <div class="form-button">
		<input type="hidden" name="user_id" value="<%= session.getAttribute("user_id")%>" />
		</div>
	<div class="form-button" >
		 <%    
		
            try {
            	Class.forName("com.mysql.jdbc.Driver");
    			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB?useSSL=false","admin","Password@1234");
    			PreparedStatement pst=con.prepareStatement("select * from enrolled where course_id = ? and user_id = ?");
    			pst.setInt(1,course_id);
    			pst.setInt(2,user_id);      	
    			ResultSet rs=pst.executeQuery();               
               if(!rs.next()) {
            	   %>
                  <input type="submit" class="btn btn-outline-danger btn-block" style="font-size:24px;font-weight:600" value="Enroll Now" />   			    
               <%} else { %>
            	   <input type="submit" class="btn btn-outline-danger btn-block" style="font-size:24px;font-weight:600" value="Already Enrolled" disabled />
              <% }
            } catch (Exception e) {
                out.println(e);
            }
        %>
		</div>
		 </form>
		 
		
          </div>
      
     <c:choose>
    <c:when test="${book.fee==0}">
     <h6 style="padding-top: 17px;font-size:18px" class="text-secondary">Free access this course</h6>
    </c:when> 
      
	</c:choose>
           
          </div>
          <div style="padding:40px 30px" >
        <p><span><i class='fas fa-sliders-h' style='font-size:20px'></i> &nbsp;Level</span> ....<span style="float:right;">${book.level}</span></p>
        <p><span><i class="bi bi-clock"></i> &nbsp;Duration</span> ....<span style="float:right;">${book.duration}</span></p>
        <p><span><i class="bi bi-play-circle"></i> &nbsp; Lectures</span> ....<span style="float:right;">{} lectures </span></p>
        
           <%    
            try {
            	Class.forName("com.mysql.jdbc.Driver");
    			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB?useSSL=false","admin","Password@1234");
    			PreparedStatement pst=con.prepareStatement("select * from enrolled where course_id = ?");
    			pst.setInt(1,course_id);
    			int e=0;    	
    			ResultSet rs=pst.executeQuery();               
               while(rs.next()) {
            	   e+=1;
               }%>
               <p><span><i class="bi bi-person-fill"></i> &nbsp; ভর্তি হয়েছেন</span> ....<span style="float:right;"><%=e %> জন </span></p>
               <%
               } 
           	catch (Exception e) {
                out.println(e);
            }
        %>
          </div>
        </div>
        <div class="col-lg-8  border">
          <h5 style="font-weight: 600;font-family:  HindSiliguri_Medium;">Instructor</h5>
      <div class="">
        
        <div class="row">
        <div class="col-lg-2  border">         
          <img class="img rounded-circle mx-auto d-block" src="assets/img/i8.png" alt="..."  width="70" height="70">     
        </div>
        <div class="col-lg-10  border">
        <div style="padding-bottom:5px;">
        <a href="{% url 'ustaz' instructor.name %}" class="text-dark" style="text-decoration:none;font-weight:600;"></a>
        <p style="font-size:16px;font-weight:500;padding-top:26px;" >${book.instructor}</p>
      </div>
    </div>
      </div>
      
      </div>
      
        </div>
        <div class="col-lg-4 border rounded pt-2 mt-2" style="padding:20px 0px;">
          <div style="background:#f4f6f9;padding:20px" >          
          <h6 style="padding-top: 17px;font-weight: 600;font-family:  HindSiliguri_Medium;" class="text-center ">কোর্সটি যাদের জন্য</h6>
          </div>
          <div style="padding:20px 14px" >
            <ul style="list-style-type: square;">
              <li>${book.audience}</li>
            </ul>
          </div>
        </div>
        <div class="col-lg-8  border">
          <h5 style="font-weight: 600;font-family:  HindSiliguri_Medium;padding-top:20px;">কোর্স সম্পর্কে বিস্তারিত</h5>
          <div class="">
            <p>
           ${book.aboutCourse}
            </p>
          </div>
        </div> 
      </div>   
      </div>
 <%@ include file = "footer.jsp" %> 
 <script>
    function myFunction() {
      var dots = document.getElementById("dots");
      var moreText = document.getElementById("more");
      var btnText = document.getElementById("myBtn");
    
      if (dots.style.display === "none") {
        dots.style.display = "inline";
        btnText.innerHTML = "Read more";
        moreText.style.display = "none";
      } else {
        dots.style.display = "none";
        btnText.innerHTML = "Read less";
        moreText.style.display = "inline";
      }
    } 
  </script>  
    
      <!-- jqurey code -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  
  <script>
    $(document).ready(function(){
      $(".card-header").click(function(){
        if($(this).children("span").hasClass("fa-minus")){
          $(this).children("span").removeClass("fa-minus").addClass("fa-plus")
        }
        else{
          $(this).children("span").removeClass("fa-plus").addClass("fa-minus")
        }
      
      })
    })
  
  </script> 
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
      $(".show-hide-btn").click(function() {
        var id = $(this).data("id");
        $("#half-" + id).toggle();//hide/show..
        $("#full-" + id).toggle();
      })
    })
    </script>
    <!--next live class-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script>
    $(document).ready(function(){
    $("#button_next").click(function(){
    $("#foyjul_vai").toggle();
    });
    });
    </script>
</body>
</html>