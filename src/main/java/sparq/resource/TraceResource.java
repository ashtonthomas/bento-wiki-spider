package sparq.resource;

import sparq.model.Trace;
import sparq.service.TraceService;
import sparq.util.JsonTransformer;
import spark.Request;
import spark.Response;
import spark.Route;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

public class TraceResource {
	
	private static final String API_CONTEXT = "/api/v1";
	
	private final TraceService traceService;
	
	public TraceResource(TraceService traceService){
		this.traceService = traceService;
		setupEndpoints();
	}
	
	private void setupEndpoints(){
		post(API_CONTEXT + "/trace", "application/json", (request, response) -> {
			Trace trace = traceService.createNewTrace(request.body());
            response.status(201); // is this all doing what I expect?
                        
            return trace;
            
        }, new JsonTransformer());
		
	}

}
