package cse.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Contact
 */
@WebServlet("/Contact")
public class Contact extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String databaseURL = "jdbc:mysql://localhost:3306/myDB?useSSL=false";
    String user = "admin";
    String password = "Password@1234";  
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String subject=request.getParameter("subject");
		String message=request.getParameter("message");		
		String sql = "insert into contact(name,email,subject,message) values(?,?,?,?) ";
		
		try(Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
			PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, subject);
            statement.setString(4, message);
            int rowCount=statement.executeUpdate();
			if(rowCount>0) {
				request.setAttribute("title", "Welcome");
				request.setAttribute("message", "Successfully! Received Your Message!.");
				request.setAttribute("status", "success");
			}
			else {
				request.setAttribute("title", "Sorry");
				request.setAttribute("message", "Sorry! Somthing wrong.");
				request.setAttribute("status", "error");
			}
           /* ResultSet result = statement.executeQuery();
            if (result.next()) {
            	System.out.println("ok");
            }*/
            response.sendRedirect("http://localhost:8110/projectWeb"); 
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		RequestDispatcher dispatcher=null;
		dispatcher =request.getRequestDispatcher("contact.jsp");
		dispatcher.forward(request,response);
	}

}
