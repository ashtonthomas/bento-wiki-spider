package sparq;

import static spark.Spark.*;
import sparq.resource.TraceResource;
import sparq.service.TraceService;
import sparq.util.HibernateUtil;

public class App {
    
    public static void main(String[] args) {
        setPort(getHerokuAssignedPort());
        
    	externalStaticFileLocation("public");
    	
    	get("/", (request, response) -> {
    		response.redirect("/index.html");
    		return "";
    	});
    	
    	new TraceResource(new TraceService());
    	
//    	before((request, response) -> {
//            HibernateUtil.getSession().beginTransaction();
//        });
//
//        after((request, response) -> {
//            HibernateUtil.getSession().getTransaction().commit();
//            HibernateUtil.closeSession();
//        });
//
//        exception(Exception.class, (e, request, response) -> {
//            HibernateUtil.getSession().getTransaction().rollback();
//            HibernateUtil.closeSession();
//            response.status(500);
//        });
    }
    
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}