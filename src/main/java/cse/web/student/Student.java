package cse.web.student;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cse.web.course.CourseFieldShort;
import cse.web.course.CourseFieldShortDAO;

/**
 * Servlet implementation class Student
 */
@WebServlet("/Student")
public class Student extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CourseFieldShortDAO dao = new CourseFieldShortDAO();
		HttpSession session=request.getSession();
        try {
        	 int user_id=(int)session.getAttribute("user_id");         
            List<CourseFieldShort> book = dao.getList(user_id);            
            request.setAttribute("book", book);        
            String page = "/dindex.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            requestDispatcher.forward(request, response);              
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String course_id=request.getParameter("course_id");
		String user_id=request.getParameter("user_id");	
		String databaseURL = "jdbc:mysql://localhost:3306/myDB?useSSL=false";
	    String user = "admin";
	    String password = "Password@1234";  
	        String sql="insert into enrolled(course_id,user_id) values(?,?)";
	        HttpSession session=request.getSession();
			RequestDispatcher dispatcher=null;
	        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
	            PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setString(1, course_id);
	            statement.setString(2, user_id);
	            int rowCount= statement.executeUpdate();
	            
	            if(rowCount>0){
	           System.out.println("yes");              
	            }          
	             
	        } catch(Exception e){
				e.printStackTrace();
			}
	        dispatcher =request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request,response);
	}

}
