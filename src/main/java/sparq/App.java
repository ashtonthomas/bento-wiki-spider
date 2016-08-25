package sparq;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
    	setPort(8080);
    	externalStaticFileLocation("public");
    	
    	get("/", (request, response) -> {
    		response.redirect("/index.html");
    		return "";
    	});
    }
}