package sparq.resource;

import static spark.Spark.get;
import static spark.Spark.post;
import sparq.model.Trace;
import sparq.service.TraceService;
import sparq.util.JsonTransformer;

public class TraceResource {
	
	private static final String API_CONTEXT = "/api/v1";
	
	private final TraceService traceService;
	
	public TraceResource(TraceService traceService){
		this.traceService = traceService;
		setupEndpoints();
	}
	
	private void setupEndpoints(){
		
		post(API_CONTEXT + "/trace", "application/json", (request, response) -> {
			Trace trace = traceService.processNewTrace(request.body());
			
			// Sorry, couldn't get Hibernate working quickly enough..
			request.session().attribute(trace.getId(), trace);
			
            response.status(201);
                        
            return trace;
            
        }, new JsonTransformer());
		
		get(API_CONTEXT + "/trace/:id", "application/json", (request, response) -> {
			String id = request.params(":id");
			
			Trace trace = request.session().attribute(id);
			
			return trace;
			
		}, new JsonTransformer());
		
	}

}
