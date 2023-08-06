package cse.web;
import java.io.File;
import javax.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
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


import javax.servlet.annotation.MultipartConfig;



import java.util.*;
/**
 * Servlet implementation class AddCourse
 */
@WebServlet("/AddCourse")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class AddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static final String UPLOAD_DIR = "images";
    public String dbFileName = "";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title=request.getParameter("title");
		String fee=request.getParameter("fee");
		String level=request.getParameter("level");
		String duration=request.getParameter("duration");
		String audience=request.getParameter("audience");
		String aboutCourse=request.getParameter("aboutCourse");
		String instructor=request.getParameter("instructor");
		Part part = request.getPart("file");//
		InputStream inputStream = null; // input stream of the upload file
		if (part != null) {
            // obtains input stream of the upload file
            inputStream = part.getInputStream();
           // System.out.println(inputStream);
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
           // System.out.println(fileName);
		}
		HttpSession session=request.getSession();
		RequestDispatcher dispatcher=null;
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB?useSSL=false","admin","Password@1234");
			PreparedStatement pst=con.prepareStatement("insert into course(title,fee,level,duration,audience,aboutCourse,instructor,photo) values(?,?,?,?,?,?,?,?) ");
			pst.setString(1,title);
			pst.setString(2,fee);
			pst.setString(3,level);
			pst.setString(4,duration);
			pst.setString(5,audience);
			pst.setString(6,aboutCourse);
			pst.setString(7, instructor);
			 if (inputStream != null) {
	                // fetches input stream of the upload file for the blob column
	                pst.setBlob(8, inputStream);
	            }
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
				System.out.println("erroor erer");
			}
			response.sendRedirect("http://localhost:8110/projectWeb/Admin");  
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			    String mega = id + " "+ title +" "+fee;
			    kjh.add(mega);
			    course_title.add(title);			    
			}
			request.setAttribute("kjh", kjh); 
			request.setAttribute("course_title", course_title); 
			session.setAttribute("kjh",kjh);
			String role="teacher";
			try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB?useSSL=false","admin","Password@1234");
			PreparedStatement pst1=con1.prepareStatement("select * from users where role='" + role  + "' ");
			ResultSet rs1=pst1.executeQuery();
			List<String> teacher = new ArrayList<String>();
			while(rs1.next()){
				int id = rs1.getInt("id");
			    String name = rs1.getString("uname");
			    teacher.add(name);			    
			}
			request.setAttribute("teacher", teacher); 
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}		
		dispatcher =request.getRequestDispatcher("addCourse.jsp");
		dispatcher.forward(request,response);
	}


}
