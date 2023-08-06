package cse.web.account;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static boolean isAlphaNumeric(String s) {
		return s != null && s.matches("^[a-zA-Z0-9]*$");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String fname = request.getParameter("fname");
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		String pass2 = request.getParameter("re_pass");
		String role = request.getParameter("role");
		RequestDispatcher dispatcher = null;
		Connection con = null;
		//System.out.println(password);
		//System.out.println(pass2);
		if (password.equals(pass2)) {
			//System.out.println(password);
			//System.out.println(pass2);
			//System.out.println("Password match");
			if (name.length() > 15 || name.length() < 4) {
				request.setAttribute("title", "Sorry");
				request.setAttribute("message", "Username must be taken between 4 and 15 characters");
				request.setAttribute("status", "error");
				dispatcher = request.getRequestDispatcher("registration.jsp");
				dispatcher.forward(request, response);

			} else if (!isAlphaNumeric(name)) {
				request.setAttribute("title", "Sorry");
				request.setAttribute("message", "Username should only contain letters and numbers");
				request.setAttribute("status", "error");
				dispatcher = request.getRequestDispatcher("registration.jsp");
				dispatcher.forward(request, response);
			} 
			else if (email.length() > 30) {
				request.setAttribute("title", "Sorry");
				request.setAttribute("message", "Please give a valid email.");
				request.setAttribute("status", "error");
				dispatcher = request.getRequestDispatcher("registration.jsp");
				dispatcher.forward(request, response);
			} 
			else if (password.length() <3) {
				request.setAttribute("title", "Sorry");
				request.setAttribute("message", "This password is too short. It must contain at least 6 characters.");
				request.setAttribute("status", "error");
				dispatcher = request.getRequestDispatcher("registration.jsp");
				dispatcher.forward(request, response);
			} 
			else if (password.length() >100) {
				request.setAttribute("title", "Sorry");
				request.setAttribute("message", "Please give a correct password.");
				request.setAttribute("status", "error");
				dispatcher = request.getRequestDispatcher("registration.jsp");
				dispatcher.forward(request, response);
			} 
			else {
				try {
					
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB?useSSL=false",
							"admin", "Password@1234");
					PreparedStatement pst1 = con.prepareStatement("select * from users where uname='" + name + "'");
					ResultSet rs1 = pst1.executeQuery();
					if (rs1.next()) {
						request.setAttribute("title", "Sorry");
						request.setAttribute("message", "This username is already taken.");
						request.setAttribute("status", "error");
						dispatcher = request.getRequestDispatcher("registration.jsp");
						dispatcher.forward(request, response);
					} else {
						
							//Class.forName("com.mysql.jdbc.Driver");
							//Connection con2 = DriverManager.getConnection(
									//"jdbc:mysql://localhost:3306/myDB?useSSL=false", "admin", "Password@1234");
							PreparedStatement pst2 = con.prepareStatement("select * from users where uemail='" + email + "'");
							ResultSet rs2 = pst2.executeQuery();
							if (rs2.next()) {
								request.setAttribute("title", "Sorry");
								request.setAttribute("message", "This email is already taken.");
								request.setAttribute("status", "error");
								dispatcher = request.getRequestDispatcher("registration.jsp");
								dispatcher.forward(request, response);
							}
							else {
								 try {
										//Class.forName("com.mysql.cj.jdbc.Driver");
										//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB?useSSL=false","admin","Password@1234");
										PreparedStatement pst=con.prepareStatement("insert into users(uname,efullname,uemail,upwd,role) values(?,?,?,?,?) ");
										pst.setString(1,name);
										pst.setString(2,fname);
										pst.setString(3,email);
										pst.setString(4,password);
										pst.setString(5, role);
										
										int rowCount=pst.executeUpdate();
										dispatcher =request.getRequestDispatcher("login.jsp");
										if(rowCount>0) {
											request.setAttribute("status", "success");
										}
										else {
											request.setAttribute("status", "failed");
										}
										dispatcher.forward(request, response);
									}
									catch(Exception e){
										e.printStackTrace();
									}
									finally {
										try {
											con.close();
										} catch (SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
							}
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} 
		else {
			request.setAttribute("title", "Sorry");
			request.setAttribute("message", "Password not matching.");
			request.setAttribute("status", "error");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
	}

}

