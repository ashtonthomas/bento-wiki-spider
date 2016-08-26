package sparq.util;

import java.util.ArrayList;

import sparq.model.Path;

public class PathTracer {
	
	public static ArrayList<Path> tracePathList(String url){
		ArrayList<Path> pathList = new ArrayList<Path>();
		
		Path p0 = new Path();
		p0.setOrder(0);
		p0.setUrl("asdfasdfsa");
		
		Path p1 = new Path();
		p1.setOrder(1);
		p1.setUrl("aasdfsdddddsa");
		
		pathList.add(p0);
		pathList.add(p1);
		
		return pathList;
	}

}
