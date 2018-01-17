package de.moogle.gui.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.moogle.lucene.tools.Site;

public class SearchResults {
	
	public static final String TEXT = "SearchText";
	public static final String TYPE = "SearchType";
	public static final String SITELIST = "SiteList";
	public static final String DATE_FROM = "DateFrom";
	public static final String DATE_TO = "DateTo";

	private static List<Map<String, Object>> resultList = new ArrayList<>();
	
	public static void addResult(String text, String type, List<Site> sites, LocalDate fromDate, LocalDate toDate) {
		Map<String, Object> map = new HashMap<>();
		map.put(TEXT, text);
		map.put(TYPE, type);
		map.put(SITELIST, sites);
		map.put(DATE_FROM, fromDate);
		map.put(DATE_TO, toDate);
		resultList.add(map);
	}
	
	public static Map<String, Object> getLastResult(){
		return resultList.get(resultList.size()-1);
	}
}
