package sparq.service;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import sparq.model.Path;
import sparq.model.Trace;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TraceService {
	
	private static final Logger tongue = Logger.getLogger(TraceService.class.getName());
	
	public Trace createNewTrace(String body) {
		tongue.log(Level.INFO, "parse: "+body);
		
		try {
			
			Map obj = new Gson().fromJson(body, Map.class);
			String url = (String) obj.get("url");
			Trace trace = new Trace();
			trace.setUrl(url);


			tongue.log(Level.INFO, "url: "+trace.getUrl());
			return trace;
		} catch (Exception e){
			tongue.log(Level.INFO, "ERROR: "+e.getMessage());
			tongue.log(Level.INFO, "--------");
			e.printStackTrace();
			return null;
		}
		
        
        
        
        
        
        
    }

}
