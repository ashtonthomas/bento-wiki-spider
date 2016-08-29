package sparq;
import static spark.Spark.*;
import sparq.resource.TraceResource;
import sparq.service.TraceService;

public class App {
    
    public static void main(String[] args) {
        setPort(getHerokuAssignedPort());
        
    	externalStaticFileLocation("public");
    	
    	registerHostPage();
    	
    	// Would be nice to use Guice or some other IoC to wire upt he app
    	new TraceResource(new TraceService());
    }
    
    private static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    
    private static void registerHostPage(){
    	get("/", (request, response) -> {
    		response.redirect("/index.html");
    		return "";
    	});
    }
}