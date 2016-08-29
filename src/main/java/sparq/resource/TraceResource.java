package sparq.resource;

import java.util.ArrayList;

import sparq.model.Trace;
import sparq.model.Path;
import sparq.service.TraceService;
import sparq.util.JsonTransformer;
import static spark.Spark.get;
import static spark.Spark.post;

public class TraceResource {
	
	private static final String API_CONTEXT = "/api/v1";
	
	private final TraceService traceService;
	
	public TraceResource(TraceService traceService){
		this.traceService = traceService;
		setupEndpoints();
	}
	
	private void setupEndpoints(){
		post(API_CONTEXT + "/foo", "application/json", (request, response) -> {
			return "foo bar";
		}, new JsonTransformer());
		
		post(API_CONTEXT + "/trace", "application/json", (request, response) -> {
			Trace trace = traceService.createNewTrace(request.body());
			
			// Forgive my sins as this code should note be here in this class..
			ArrayList<Path> pathList = sparq.util.PathTracer.tracePathList(trace.getUrl());
			trace.setPathList(pathList);

			
			// Um, I'm sorry?
			// Ran into too many road blocks with setting up Hibernate - punting...
			// running out of time so will bypass DB store
			request.session().attribute(trace.getId(), trace);
			
			
            response.status(201); // is this all doing what I expect?
                        
            return trace;
            
        }, new JsonTransformer());
		
		get(API_CONTEXT + "/trace/:id", "application/json", (request, response) -> {
			String id = request.params(":id");
			
			Trace trace = request.session().attribute(id);
			
			return trace;
			
		}, new JsonTransformer());
		
	}

}
