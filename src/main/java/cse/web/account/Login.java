package cse.web.account;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("username");
		String password=request.getParameter("password");
		HttpSession session=request.getSession();
		RequestDispatcher dispatcher=null;
		//session.setAttribute("name",name);
		PrintWriter out=response.getWriter();
		out.println(name+" "+" "+password+" "+session.getAttribute("name"));
		if(name==null || name.equals("")){
			request.setAttribute("status","invalidName");
			dispatcher =request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request,response);
		}
		if(password==null || password.equals("")){
			request.setAttribute("status","invalidPassword");
			dispatcher =request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request,response);
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB?useSSL=false","admin","Password@1234");
			PreparedStatement pst=con.prepareStatement("select * from users where uname = ? and upwd = ?");
			pst.setString(1,name);
			pst.setString(2,password);
			
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				String role=rs.getString("role");
				int id=rs.getInt("id");
				//System.out.println(role);
				session.setAttribute("name",name);
				session.setAttribute("user_id", id);
				session.setAttribute("role", role);
				request.setAttribute("status", "success");
				dispatcher =request.getRequestDispatcher("index.jsp");
				//PrintWriter out=response.getWriter();
				//out.println(rs.getString("uname"));
				
			}else {
				request.setAttribute("status", "failed");
				dispatcher =request.getRequestDispatcher("login.jsp");
			}
			dispatcher.forward(request,response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
