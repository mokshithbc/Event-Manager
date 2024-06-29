package model;

public class Task {
    private String timeRange;
    private String title;
    private String description;
    private	String id;

  
    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }
    
    public String getTimeRange() {
        return timeRange;
    }
    
    public void setTaskId(String id) {
        this.id = id;
    }
    
    public String getTaskId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}
