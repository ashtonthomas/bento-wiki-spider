package sparq.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.RuntimeErrorException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sparq.model.Path;

public class PathTracer {
	
	private static final String ENLIGHTENMENT_URL = "http://en.wikipedia.org/wiki/philosophy";
	private static final int MAX_HOPS = 30;
	
	// using java util - need to refresh on Java logging as this is not correct if I want to use slf4j
	private static final Logger LOGGER = Logger.getLogger( PathTracer.class.getName() ); 
	
	// This file should do a lot of things
	// be well organized
	// have a simple interface 
	// persist links in case we see a duplicated (i.e. infinite loop)
	// or if we already know some pages to have no appropriate solution (depending on number of max hops)
	// or we can just go ahead and output the solution, otherwise
	// gracefully handle edge cases
	// properly use constants and settings (Philosophy url, num max hops, etc)
	// I should also do this asynchronously and return without completing the request fully
	// (not sure I have time to implement this and my data store choices won't make this easier)
	
	
	public static ArrayList<Path> tracePathList(String url){		
		return findPhilosophy(url, new ArrayList<Path>());
	}
	
	public static ArrayList<Path> findPhilosophy(String url, ArrayList<Path> path) {
		ArrayList<Path> newList = addPathToList(url, path);
		
		if ( isEnlightened(url) || isError(url) ){
			return newList;
		} else if (path.size() >= MAX_HOPS) {
			return addPathToList("MAX LIMIT REACHED - SORRY", newList);
		} else {
			String nextUrl = getFirstLink(url);
			
			
			
			spit("GO TO NEXT LINK: " + nextUrl);
			
			return findPhilosophy(nextUrl, newList);
		}
		
	}
	
	public static ArrayList<Path> addPathToList(String url, ArrayList<Path> pathList){
		// This code is particularly bad as there are better ways to handle and manipulate this data...
		
		spit("---------------");
		spit(url);
		spit("");
		
		Path p = new Path();
		p.setOrder(pathList.size());
		p.setUrl(url);
		
		pathList.add(p);
		
		return pathList;
	}
	
	public static boolean isEnlightened(String url){
        return url.equalsIgnoreCase(ENLIGHTENMENT_URL);
	}
	
	public static boolean isError(String url){
		return url.startsWith("ERROR");
	}
	
	public static String getFirstLink(String url) {
		try {
			Document page = fetch(url);
			Elements links = extractContentLinks(page);
			
			return determineFirstLink(links);
		} catch (IOException e) {
			e.printStackTrace();
			spit(e.getMessage());
			
			
			
			try {
				URL foo = new URL(url);
			} catch (MalformedURLException ec) {
				ec.printStackTrace();
				
				spit("----------------");
				spit(url);
				spit("----------------");
				
				
			}
			
			return "ERROR (" + url + ") " + e.getMessage();
			
		}
		
		
	}
	
	public static Document fetch(String url) throws IOException{
		 return Jsoup.connect(url).get();
	}
	
	public static Elements extractContentLinks(Document doc){
		return doc.select("#mw-content-text p > a");
	}
	
	
	public static String determineFirstLink(Elements links){
				
		for (Element link : links){
			String linkUrl = link.toString();
			
			spit("linkUrl: "+linkUrl);
			
			String url = buildWikiUrlFromHref(linkUrl);
			
			spit("url: "+url);
			
			if(isGoodLink(url)){
				spit("GOOD LINK: " + url);
				return url;
			}
		}
		
		throw new RuntimeException("No Appropriate Links Found - Would be nice to handle this exception");
	}
	
	public static boolean isGoodLink(String link) {		
		
		boolean isWikiUrl = link.contains("http://en.wikipedia.org/wiki/");
		boolean isNotSpecial = !link.contains("Special:");
		boolean isNotFile = !link.contains("File:");
		
		boolean isValid = isWikiUrl && isNotSpecial && isNotFile;
		
        return isValid;
    }
	
	/**
	 * Also stealing this from:
	 * 
	 * http://ramonaharrison.github.io/accesscode/java/http/wikipedia/2015/03/27/wikipedia-philosophy/
	 * 
	 * @param link
	 * @return
	 */
	public static String buildWikiUrlFromHref(String link){
		return "http://en.wikipedia.org" + link.substring(9, link.indexOf("\"", 10));
	}
	
	private static void spit(String fire){
		LOGGER.log(Level.INFO, fire);
	}

}
