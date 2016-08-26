package sparq;

import static spark.Spark.*;
import sparq.resource.TraceResource;
import sparq.service.TraceService;

public class App {
    
	// I spent too much time wrestling with setup
	// The organization of this project is awful
    public static void main(String[] args) {
        setPort(getHerokuAssignedPort());
        
    	externalStaticFileLocation("public");
    	
    	get("/", (request, response) -> {
    		response.redirect("/index.html");
    		return "";
    	});
    	
    	new TraceResource(new TraceService());
    }
    
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}