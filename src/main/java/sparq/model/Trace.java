package sparq.model;

import java.security.SecureRandom;
import java.util.Date;
import java.util.UUID;

public class Trace {
	private String id = UUID.randomUUID().toString();
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
