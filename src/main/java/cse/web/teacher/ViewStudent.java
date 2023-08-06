package cse.web.teacher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cse.web.course.CourseFieldShort;
import cse.web.course.FildList;
import cse.web.course.FildListDAO;

/**
 * Servlet implementation class ViewStudent
 */
@WebServlet("/ViewStudent")
public class ViewStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String databaseURL = "jdbc:mysql://localhost:3306/myDB?useSSL=false";
    String user = "admin";
    String password = "Password@1234";    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.valueOf(request.getParameter("bistarito"));
		//StudentFild book=new StudentFild();
		StudentFild book = null;
        List<StudentFild> kjh = new ArrayList<StudentFild>();
		// List<Book> book = dao.get();
          
         
		 // FildListDAO dao = new FildListDAO();
		 String sql = "SELECT * FROM enrolled where course_id = ?"; 
		   try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
	            PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setInt(1,id);
	            ResultSet result = statement.executeQuery();
	            Connection con = null;
	            while(result.next()){
	            	book = new StudentFild();
	            	int user_id=result.getInt("user_id");
	            	//System.out.println(user_id);
	            	try {
	                    Class.forName("com.mysql.jdbc.Driver");
	    				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB?useSSL=false","admin","Password@1234");
	    				PreparedStatement pst=con.prepareStatement("select * from users where id='" + user_id + "' ");			
	    				//ResultSet rs=pst.executeQuery();
	    				ResultSet rs =pst.executeQuery();		
	    				//System.out.println(rowCount);
	    			  if(rs.next()){                
	                    String name = rs.getString("efullname");
	                    String email = rs.getString("uemail");
	                    
	                
	                    System.out.println(name+" "+email+" "+id);
	                    book.setId(user_id);
		                book.setName(name);
		                book.setEmail(email);
	                    kjh.add(book);             
	    				}
	                } catch(Exception e){
	        			e.printStackTrace();
	        		} 
	            	
	            }
	            request.setAttribute("book", kjh);
	            String page = "/viewStudent.jsp";
	            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
	            requestDispatcher.forward(request, response);              
	        } catch(Exception e){
				e.printStackTrace();
			}
		   
	}

}
