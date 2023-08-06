package cse.web;

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

import java.util.*;
/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int si=0,qid=0;
	List<Integer> arr = new ArrayList<Integer>();
	List<Integer> array = new ArrayList<Integer>();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selectcourse=request.getParameter("selectcourse");
		if(selectcourse.equals("delete")) {
		String cbox="";
		HttpSession session=request.getSession();
		RequestDispatcher dispatcher=null;
		Connection con = null;
		for(int it:arr) {
			cbox=request.getParameter(Integer.toString(it));
			if(cbox!=null) {
				array.add(it);
			}			
		}
		for(int it:array) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB?useSSL=false","admin","Password@1234");
				PreparedStatement pst=con.prepareStatement("delete from course where id='" + it + "' ");			
				int rowCount=pst.executeUpdate();			
				if(rowCount>0) {
					request.setAttribute("title", "Welcome");
					request.setAttribute("message", "Successfully added this course.");
					request.setAttribute("status", "success");
				}
				else {
					request.setAttribute("title", "Sorry");
					request.setAttribute("message", "Somthing wrong.");
					request.setAttribute("status", "error");
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}			
		}
		}
		response.sendRedirect("http://localhost:8110/projectWeb/Admin"); 
	}

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		RequestDispatcher dispatcher=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB?useSSL=false","admin","Password@1234");
			PreparedStatement pst=con.prepareStatement("select * from course");
			ResultSet rs=pst.executeQuery();
			List<String> kjh = new ArrayList<String>();
			List<String> course_title = new ArrayList<String>();
			while(rs.next()){
				
				request.setAttribute("status", "success");
				
				int id = rs.getInt("id");
			    String title = rs.getString("title");
			    String fee = rs.getString("fee");
			    String mega =Integer.toString(id);
			    kjh.add(mega);
			    arr.add(id);
			    course_title.add(title);
			    
			}
			request.setAttribute("kjh", kjh); 
			request.setAttribute("course_title", course_title); 
			session.setAttribute("kjh",kjh);	
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		dispatcher =request.getRequestDispatcher("admin.jsp");
		dispatcher.forward(request,response);
	}

}
