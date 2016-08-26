package sparq.service;

import sparq.model.Trace;

import com.google.gson.Gson;

public class TraceService {
	
	public Trace createNewTrace(String body) {
        Trace trace = new Gson().fromJson(body, Trace.class);
        
        // DB work?
        
        return trace;
        
    }

}
