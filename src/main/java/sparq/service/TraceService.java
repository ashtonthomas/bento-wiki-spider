package sparq.service;

import java.util.ArrayList;
import java.util.Map;

import sparq.model.Path;
import sparq.model.Trace;
import sparq.util.PathTracer;

import com.google.gson.Gson;

public class TraceService {
		
	public Trace processNewTrace(String requestBody) {
		// Ran into bumps getting used to deserializing in Java
		Map obj = new Gson().fromJson(requestBody, Map.class);
		String url = (String) obj.get("url");
		
		Trace trace = new Trace();
		trace.setUrl(url);
		
		ArrayList<Path> pathList = PathTracer.tracePathList(trace.getUrl());
		trace.setPathList(pathList);

		return trace;
    }	
	
}
