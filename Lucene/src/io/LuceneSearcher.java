package io;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.NIOFSDirectory;

public class LuceneSearcher {

	private final static Logger log = Logger.getLogger(LuceneSearcher.class.getName());

	private static LuceneSearcher lSearcher;

	public static LuceneSearcher getInstance() {
		if (lSearcher == null) {
			lSearcher = new LuceneSearcher();
		}
		return lSearcher;
	}

	private LuceneSearcher() {

	}

	public List<LuceneDocument> getFullSearchResults(String searchQuery, Sites[] sites, String[] dates)
			throws IOException, ParseException {
		if (!searchQuery.isEmpty()) {
			NIOFSDirectory indexDir = new NIOFSDirectory(Paths.get("C:\\testDir"));
			Analyzer analyzer = new StandardAnalyzer();
			DirectoryReader dr = DirectoryReader.open(indexDir);
			IndexSearcher searcher = new IndexSearcher(dr);
			String[] fields = { "content", "title" };
			MultiFieldQueryParser qp = new MultiFieldQueryParser(fields, analyzer);
			Query query = qp.parse(searchQuery);
			TopDocs td = searcher.search(query, Integer.MAX_VALUE);
			ScoreDoc[] sd = td.scoreDocs;
			List<LuceneDocument> resultList = new ArrayList<LuceneDocument>();
			for (int i = 0; i < sd.length; i++) {
				Document doc = searcher.doc(sd[i].doc);
				boolean isFiltered = false;
				if (sites != null) {
					isFiltered = filterSites(doc, sites);
				}
				if (dates != null) {
					isFiltered = isFiltered ? true : filterDate(doc, dates);
				}
				if (!isFiltered) {
					log.info(doc.get("title"));
					String title = doc.get("title");
					String date = doc.get("date");
					String link = doc.get("link");
					String orgs = doc.get("orgs");
					String people = doc.get("people");
					LuceneDocument ldoc = new LuceneDocument(title, date, link, orgs, people);
					resultList.add(ldoc);
				}
			}
			return resultList;
		} else {
			return new ArrayList<>();
		}
	}

//	public List<LuceneDocument> getOrgSearchResults(String searchQuery) throws IOException, ParseException {
//		NIOFSDirectory indexDir = new NIOFSDirectory(Paths.get("C:\\testDir"));
//		Analyzer analyzer = new StandardAnalyzer();
//		DirectoryReader dr = DirectoryReader.open(indexDir);
//		IndexSearcher searcher = new IndexSearcher(dr);
//		QueryParser qp = new QueryParser("orgs", analyzer);
//		Query query = qp.parse(searchQuery);
//		TopDocs td = searcher.search(query, 10);
//		ScoreDoc[] sd = td.scoreDocs;
//		List<LuceneDocument> resultList = new ArrayList<LuceneDocument>();
//		for (int i = 0; i < sd.length; i++) {
//			Document doc = searcher.doc(sd[i].doc);
//			log.info(doc.get("title"));
//			String title = doc.get("title");
//			String date = doc.get("date");
//			String link = doc.get("link");
//			String orgs = doc.get("orgs");
//			String people = doc.get("people");
//			LuceneDocument ldoc = new LuceneDocument(title, date, link, orgs, people);
//			resultList.add(ldoc);
//		}
//		return resultList;
//	}
//
//	public List<LuceneDocument> getPeopleSearchResults(String searchQuery) throws IOException, ParseException {
//		NIOFSDirectory indexDir = new NIOFSDirectory(Paths.get("C:\\testDir"));
//		Analyzer analyzer = new StandardAnalyzer();
//		DirectoryReader dr = DirectoryReader.open(indexDir);
//		IndexSearcher searcher = new IndexSearcher(dr);
//		QueryParser qp = new QueryParser("people", analyzer);
//		Query query = qp.parse(searchQuery);
//		TopDocs td = searcher.search(query, 10);
//		ScoreDoc[] sd = td.scoreDocs;
//		List<LuceneDocument> resultList = new ArrayList<LuceneDocument>();
//		for (int i = 0; i < sd.length; i++) {
//			Document doc = searcher.doc(sd[i].doc);
//			log.info(doc.get("title"));
//			String title = doc.get("title");
//			String date = doc.get("date");
//			String link = doc.get("link");
//			String orgs = doc.get("orgs");
//			String people = doc.get("people");
//			LuceneDocument ldoc = new LuceneDocument(title, date, link, orgs, people);
//			resultList.add(ldoc);
//		}
//		return resultList;
//	}

	private boolean filterDate(Document doc, String[] dates) {
		DateFormat datesFormatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
		DateFormat docFormatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
		try {
			Date from = datesFormatter.parse(dates[0]);
			Date to = datesFormatter.parse(dates[1]);
			Date docDate = docFormatter.parse(doc.get("date"));
			if (docDate.before(from) || docDate.after(to)) {
				return true;
			}
		} catch (java.text.ParseException pe) {
			log.log(Level.SEVERE, "Parsen auf Datum in Lucene nicht erfolgreich", pe);
		}
		return false;
	}

	private boolean filterSites(Document doc, Sites[] sites) {
		boolean isFiltered = true;
		String url = doc.get("link");
		for (Sites filterSite : sites) {
			if (url.startsWith(filterSite.getSite())) {
				isFiltered = false;
			}
		}
		return isFiltered;
	}

	public enum Sites {
		GAMESTAR("http://www.gamestar.de"),
		GAMEPRO("http://www.gamepro.de"),
		FOURPLAYERS("http://feeds.4players.de"),
		CHIP("http://www.chip.de"),
		GIGA("http://www.giga.de"),
		GOLEM("https://rss.golem.de"),
		IGN("http://de.ign.com");
		
		private final String site;
		
		Sites(String site) {
			this.site = site;
		}
		
		public String getSite() {
			return this.site;
		}
	}

}
