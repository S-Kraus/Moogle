package io;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.NIOFSDirectory;

public class LuceneSearcher {

	private final static Logger log = Logger.getLogger(LuceneSearcher.class.getName());
	
	
	public static List<String> getSearchResults(String searchQuery) throws IOException, ParseException  {
		NIOFSDirectory indexDir = new NIOFSDirectory(Paths.get("C:\\testDir"));
		Analyzer analyzer = new StandardAnalyzer();
		DirectoryReader dr = DirectoryReader.open(indexDir);
		IndexSearcher searcher = new IndexSearcher(dr);
		QueryParser qp = new QueryParser("content", analyzer);
		Query query = qp.parse(searchQuery);
		TopDocs td = searcher.search(query, 10);
		ScoreDoc[] sd = td.scoreDocs;
		List<String> resultList = new ArrayList<String>();
		for (int i = 0; i < sd.length; i++) {
			Document doc = searcher.doc(sd[i].doc);
			log.info(doc.get("title"));
			resultList.add(doc.get("title"));
		}
		return resultList;
	}
}
