package cse.web.course;

public class FildList {
	private int id;
	private String title;
    private int fee;
    private String level;
    private String duration;
    private String audience;
    private String aboutCourse;
    private String instructor;
    private String base64Image;
    
    public int getId() {
        return id;
    }
     public void setId(int id) {
        this.id = id;
    }
     
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getFee() {
        return fee;
    }
    public void setFee(int fee) {
        this.fee = fee;
    }
    
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    //duration
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    //Enrolled
    
    //aboutcourse
    public String getAboutCourse() {
        return aboutCourse;
    }
    public void setAboutCourse(String aboutCourse) {
        this.aboutCourse = aboutCourse;
    }
    //audience
    public String getAudience() {
        return audience;
    }
    public void setAudience(String audience) {
        this.audience = audience;
    }
  //Instructor
    public String getInstructor() {
        return instructor;
    }
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    //image
    public String getBase64Image() {
        return base64Image;
    }
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
