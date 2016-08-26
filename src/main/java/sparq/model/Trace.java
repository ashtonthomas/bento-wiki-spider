package sparq.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Trace {
	private String id = UUID.randomUUID().toString();
	private String url;
	private boolean done = true; // maybe on another day I could implement this better
	private Date createdOn = new Date();
	private ArrayList<Path> pathList;
	
	public Trace(){
		
	}
	
	public void setPathList(ArrayList<Path> pathList){
		this.pathList = pathList;
	}
	
	public ArrayList<Path> getPathList(){
		return pathList;
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
