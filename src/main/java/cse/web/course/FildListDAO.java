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

public class FildListDAO {
	String databaseURL = "jdbc:mysql://localhost:3306/myDB?useSSL=false";
    String user = "admin";
    String password = "Password@1234";
     
    public FildList get(int id) throws SQLException, IOException {
        FildList book = null;
        String sql = "SELECT * FROM course WHERE id = ?";
         
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                book = new FildList();
                int idd=result.getInt("id");
                String title = result.getString("title");
                int fee = result.getInt("fee");
                String level = result.getString("level");
                String duration = result.getString("duration");
                String audience = result.getString("audience");
                String aboutCourse = result.getString("aboutCourse");
                String instructor = result.getString("instructor");
                
                String sqlfullName = "SELECT * FROM users WHERE uname = ?";
                try (Connection connection1 = DriverManager.getConnection(databaseURL, user, password)) {
                    PreparedStatement statement1 = connection.prepareStatement(sqlfullName);
                    statement1.setString(1, instructor );
                    ResultSet result1 = statement1.executeQuery();
                    if (result1.next()) {
                    	instructor=result1.getString("efullname");
                    	//System.out.println(instructor_fullName);
                    }
	            } 
                catch(Exception e){
    				e.printStackTrace();
    			}
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
               
                book.setId(idd);
                book.setTitle(title);
                book.setFee(fee);
                book.setLevel(level);
                book.setDuration(duration);
                book.setAudience(audience);
                book.setAboutCourse(aboutCourse);
                book.setInstructor(instructor);
                book.setBase64Image(base64Image);
            }                     
             
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
            throw ex;
        }      
         
        return book;
    }
}
