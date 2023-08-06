package cse.web.teacher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cse.web.course.CourseFieldShort;
import cse.web.course.CourseFieldShortDAO;

/**
 * Servlet implementation class Teacher
 */
@WebServlet("/Teacher")
public class Teacher extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Teacher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CourseFieldShortDAO dao = new CourseFieldShortDAO();
         //System.out.println(request.getAttribute("name"));
	        try {
	            List<CourseFieldShort> book = dao.get("teacher");
	            
	            request.setAttribute("book", book);
	           
	            //request.setAttribute("kjh", book); 
	            String page = "/dindex.jsp";
	            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
	            requestDispatcher.forward(request, response);              
	        } catch (SQLException ex) {
	            throw new ServletException(ex);
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String instructor=request.getParameter("instructor");
		//System.out.println(instructor);
		CourseFieldShortDAO dao = new CourseFieldShortDAO();
        //System.out.println(request.getAttribute("name"));
	        try {
	            List<CourseFieldShort> book = dao.get(instructor);
	            
	            request.setAttribute("book", book);
	           
	            //request.setAttribute("kjh", book); 
	            String page = "/dindex.jsp";
	            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
	            requestDispatcher.forward(request, response);              
	        } catch (SQLException ex) {
	            throw new ServletException(ex);
	        }
	}

}
