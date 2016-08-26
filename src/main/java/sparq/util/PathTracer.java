package sparq.util;

import java.io.IOException;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sparq.model.Path;

public class PathTracer {
	
	private static final String ENLIGHTENMENT_URL = "http://en.wikipedia.org/wiki/philosophy";
	private static final int MAX_HOPS = 30;
	
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
		ArrayList<Path> pathList = new ArrayList<Path>();
		
		// Not able to handle exceptions with current time constraints
		// Would be nice to look at the exception structure and make sure
		// it makes the most sense (as I'm sure it doens't)
		try {
			findPhilosophy(url, pathList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return pathList;
	}
	
	public static ArrayList<Path> findPhilosophy(String url, ArrayList<Path> path) throws IOException{
		ArrayList<Path> newList = addPathToList(url, path);
		
		if ( isEnlightened(url) ){
			return newList;
		} else if (path.size() >= MAX_HOPS) {
			return addPathToList("MAX LIMIT REACHED - SORRY", newList);
		} else {
			String nextUrl = getFirstLink(url);
			
			return findPhilosophy(nextUrl, newList);
		}
		
	}
	
	public static ArrayList<Path> addPathToList(String url, ArrayList<Path> pathList){
		// This code is particularly bad as there are better ways to handle and manipulate this data...
		
		System.out.println("---------------");
		System.out.println(url);
		System.out.println();
		
		Path p = new Path();
		p.setOrder(pathList.size());
		p.setUrl(url);
		
		pathList.add(p);
		
		return pathList;
	}
	
	public static boolean isEnlightened(String url){
        return url.equalsIgnoreCase(ENLIGHTENMENT_URL);
	}
	
	public static String getFirstLink(String url) throws IOException{
		Document page = fetch(url);
		Elements links = extractContentLinks(page);
		
		return determineFirstLink(links);
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
			
			if(isGoodLink(linkUrl)){
				return buildWikiUrlFromHref(linkUrl);
			}
		}
		
		throw new RuntimeException("No Appropriate Links Found - Would be nice to handle this exception");
	}
	
	/**
	 * /**
	 * Referencing the edge cases for what criteria may denote a bad first link:
	 * 
	 * http://ramonaharrison.github.io/accesscode/java/http/wikipedia/2015/03/27/wikipedia-philosophy/
	 * 
	 * @param link
	 * @return
	 */
	public static boolean isGoodLink(String link) {
        return (link.contains("wiki") && !link.contains("Greek") && !link.contains("Latin") && !link.contains("wiktionary"));
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

}
