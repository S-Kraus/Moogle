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

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.NIOFSDirectory;

import tools.Site;

public class LuceneSearcher {

	private final static Logger log = Logger.getLogger(LuceneSearcher.class.getName());

	public static final int TYPE_TEXT_SEARCH = 0;
	public static final int TYPE_PERSON_SEARCH = 1;
	public static final int TYPE_ORG_SEARCH = 2;
	public static final int TYPE_PERSON_ORG_SEARCH = 3;

	private static LuceneSearcher lSearcher;

	private Site[] siteFilters;
	private Date fromDate;
	private Date toDate;
	private IndexSearcher searcher;

	public static LuceneSearcher getInstance() throws IOException {
		if (lSearcher == null) {
			lSearcher = new LuceneSearcher();
		}
		return lSearcher;
	}

	private LuceneSearcher() throws IOException {
		NIOFSDirectory indexDir = new NIOFSDirectory(Paths.get("C:\\testDir"));
		DirectoryReader dr = DirectoryReader.open(indexDir);
		searcher = new IndexSearcher(dr);
	}

	public List<LuceneDocument> getSearchResults(int searchType, String searchQuery)
			throws IOException, ParseException {
		if (!searchQuery.isEmpty()) {
			String[] fields = getFields(searchType);
			MultiFieldQueryParser qp = new MultiFieldQueryParser(fields, new StandardAnalyzer());
			Query query = qp.parse(searchQuery);
			TopDocs td = searcher.search(query, Integer.MAX_VALUE);
			ScoreDoc[] sd = td.scoreDocs;
			List<LuceneDocument> resultList = new ArrayList<LuceneDocument>();
			for (int i = 0; i < sd.length; i++) {
				Document doc = searcher.doc(sd[i].doc);
				boolean isFiltered = false;
				isFiltered = filterSites(doc);
				isFiltered = isFiltered ? true : filterDate(doc, true);
				isFiltered = isFiltered ? true : filterDate(doc, false);
				if (!isFiltered) {
					log.info(doc.get("title"));
					String title = doc.get("title");
					String date = doc.get("date");
					String link = doc.get("link");
					String path = doc.get("path");
					String orgs = doc.get("orgs");
					String people = doc.get("people");
					LuceneDocument ldoc = new LuceneDocument(title, date, link, path, orgs, people);
					resultList.add(ldoc);
				}
			}
			clearFilters();
			return resultList;
		}
		clearFilters();
		return new ArrayList<>();
	}

	private String[] getFields(int searchType) {
		String[] fields;
		switch (searchType) {
		case TYPE_TEXT_SEARCH:
			fields = new String[4];
			fields[0] = "content";
			fields[1] = "title";
			fields[2] = "orgs";
			fields[3] = "people";
			return fields;
		case TYPE_PERSON_SEARCH:
			fields = new String[1];
			fields[0] = "people";
			return fields;
		case TYPE_ORG_SEARCH:
			fields = new String[1];
			fields[0] = "orgs";
			return fields;
		case TYPE_PERSON_ORG_SEARCH:
			fields = new String[2];
			fields[0] = "people";
			fields[1] = "orgs";
			return fields;
		default:
			fields = new String[4];
			fields[0] = "content";
			fields[1] = "title";
			fields[2] = "orgs";
			fields[3] = "people";
			return fields;
		}
	}

	public LuceneSearcher setSiteFilters(Site[] filters) {
		this.siteFilters = filters;
		return this;
	}

	public LuceneSearcher setFromDate(Date date) {
		this.fromDate = date;
		return this;
	}

	public LuceneSearcher setToDate(Date date) {
		this.toDate = date;
		return this;
	}
	
	private boolean filterDate(Document doc, boolean isFromDate) {
		// DateFormat docFormatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
		DateFormat docFormatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
		try {
			Date docDate = docFormatter.parse(doc.get("date"));
			if (isFromDate) {
				if (fromDate != null) {
					if (docDate.before(fromDate)) {
						return true;
					}
				}
			} else {
				if (toDate != null) {
					if (docDate.after(toDate)) {
						return true;
					}
				}
			}
		} catch (java.text.ParseException pe) {
			log.log(Level.SEVERE, "Parsen auf Datum in Lucene nicht erfolgreich", pe);
		}
		return false;
	}

	private boolean filterSites(Document doc) {
		if (siteFilters != null) {
			boolean isFiltered = true;
			String url = doc.get("link");
			for (Site filterSite : siteFilters) {
				if (url.startsWith(filterSite.getSite())) {
					isFiltered = false;
				}
			}
			return isFiltered;
		} else {
			return false;
		}
	}

	private void clearFilters() {
		setFromDate(null);
		setToDate(null);
		setSiteFilters(null);
	}

}
