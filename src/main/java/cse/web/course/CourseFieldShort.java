package cse.web.course;

public class CourseFieldShort {
	private int id;
	private String title;
    private int fee;
    private String duration;
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
    
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getBase64Image() {
        return base64Image;
    }
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
