package sparq.model;

import java.util.Date;

public class Trace {
	private String id;
	private String url;
	private boolean done;
	private Date createdOn = new Date();
	
	public Trace(){
		
	}
	
	public String getId() {
        return id;
    }
 
	public String getUrl(){
		return url;
	}
	
    public boolean isDone() {
        return done;
    }
 
    public Date getCreatedOn() {
        return createdOn;
    }

}
