package cse.web.course;

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

import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class CourseFieldShortDAO {
    String databaseURL = "jdbc:mysql://localhost:3306/myDB?useSSL=false";
    String user = "admin";
    String password = "Password@1234";  
   
    public List<CourseFieldShort> get() throws SQLException, IOException {
    	CourseFieldShort book = null;
        List<CourseFieldShort> kjh = new ArrayList<CourseFieldShort>();
        String sql = "SELECT * FROM course";
         
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                book = new CourseFieldShort();
                int id=result.getInt("id");
                String title = result.getString("title");
                int fee = result.getInt("fee");
                String duration = result.getString("duration");
                Blob blob = result.getBlob("photo");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }
                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                 
                 
                inputStream.close();
                outputStream.close();
                 
                book.setId(id);
                book.setTitle(title);
                book.setFee(fee);
                book.setDuration(duration);
                book.setBase64Image(base64Image);               
                kjh.add(book);              
            }          
             
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
            throw ex;
        }      
         
        return kjh;
    }
    public List<CourseFieldShort> get(String instructor) throws SQLException, IOException {
    	CourseFieldShort book = null;
        List<CourseFieldShort> kjh = new ArrayList<CourseFieldShort>();
        String sql = "SELECT * FROM course where instructor = ?";
         
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, instructor);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                book = new CourseFieldShort();
                int id=result.getInt("id");
                String title = result.getString("title");
                int fee = result.getInt("fee");
                String duration = result.getString("duration");
                Blob blob = result.getBlob("photo");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }
                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                 
                 
                inputStream.close();
                outputStream.close();
                 
                book.setId(id);
                book.setTitle(title);
                book.setFee(fee);
                book.setDuration(duration);
                book.setBase64Image(base64Image);
                kjh.add(book);
               
            }          
             
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
            throw ex;
        }      
         
        return kjh;
    }
    public List<CourseFieldShort> getList(int user_id) throws SQLException, IOException {
    	CourseFieldShort book = null;
        List<CourseFieldShort> kjh = new ArrayList<CourseFieldShort>();       
        String sql = "SELECT * FROM enrolled where user_id = ?";       
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user_id);
            ResultSet result = statement.executeQuery();
            Connection con = null;
            while(result.next()){
                book = new CourseFieldShort();
                int course_id=result.getInt("course_id");
                try {
                Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB?useSSL=false","admin","Password@1234");
				PreparedStatement pst=con.prepareStatement("select * from course where id='" + course_id + "' ");			
				ResultSet rs =pst.executeQuery();					
				while(rs.next()){                
                String title = rs.getString("title");
                int fee = rs.getInt("fee");
                String duration = rs.getString("duration");
                Blob blob = rs.getBlob("photo");             
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }
                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                 
                 
                inputStream.close();
                outputStream.close();
                 
                book.setId(course_id);
                book.setTitle(title);
                book.setFee(fee);
                book.setDuration(duration);
                book.setBase64Image(base64Image);
                kjh.add(book);             
				}
            } catch(Exception e){
    			e.printStackTrace();
    		} 
            }    
             
        }catch(Exception e){
			e.printStackTrace();
		} 
         
        return kjh;
    
    }
}