package sparq.util;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sparq.model.Path;

public class PathTracer {
	
	private static final String ENLIGHTENMENT_URL = "://en.wikipedia.org/wiki/philosophy"; //catch http or http
	private static final int MAX_HOPS = 30;
	
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
			
			return findPhilosophy(nextUrl, newList);
		}
		
	}
	
	public static ArrayList<Path> addPathToList(String url, ArrayList<Path> pathList){		
		Path p = new Path();
		
		p.setOrder(pathList.size());
		p.setUrl(url);
		
		pathList.add(p);
		
		return pathList;
	}
	
	public static boolean isEnlightened(String url){
		return url.toLowerCase().contains(ENLIGHTENMENT_URL.toLowerCase());
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
			
			String url = buildWikiUrlFromHref(linkUrl);
						
			if(isGoodLink(url)){
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
	 * stealing this from:
	 * 
	 * http://ramonaharrison.github.io/accesscode/java/http/wikipedia/2015/03/27/wikipedia-philosophy/
	 * 
	 * @param link
	 * @return
	 */
	public static String buildWikiUrlFromHref(String link){
		return "http://en.wikipedia.org" + link.substring(9, link.indexOf("\"", 10));
	}

}
