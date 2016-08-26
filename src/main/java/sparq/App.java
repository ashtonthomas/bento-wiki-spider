package sparq;

import static spark.Spark.*;
import sparq.resource.TraceResource;
import sparq.service.TraceService;

import com.google.gson.Gson;

public class App {
	private static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null ? System.getenv("OPENSHIFT_DIY_IP") : "localhost";
    private static final int PORT = System.getenv("OPENSHIFT_DIY_PORT") != null ? Integer.parseInt(System.getenv("OPENSHIFT_DIY_PORT")) : 8080;
    
    public static void main(String[] args) {
    	setIpAddress(IP_ADDRESS);
        setPort(PORT);
        
    	externalStaticFileLocation("public");
    	
    	get("/", (request, response) -> {
    		response.redirect("/index.html");
    		return "";
    	});
    	
    	new TraceResource(new TraceService());
    }
}