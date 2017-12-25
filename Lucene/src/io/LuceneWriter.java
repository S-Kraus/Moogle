package io;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.NIOFSDirectory;

public class LuceneWriter {

	private static LuceneWriter lWriter;

	private IndexWriter writer;

	public static LuceneWriter getInstance() throws IOException {
		if (lWriter == null) {
			lWriter = new LuceneWriter();
		}
		return lWriter;
	}

	private LuceneWriter() throws IOException {
		NIOFSDirectory indexDir = new NIOFSDirectory(Paths.get("C:\\testDir"));
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
		writer = new IndexWriter(indexDir, config);
	}
	
	// TODO: Bei Umzug auf Moogle Projekt -> FeedMessage als Parameter
	public void createDocIndex(String title, String content, String date, String link, String orgs, String people)
			throws IOException{
		Document document = new Document();
		document.add(new TextField("title", title, Field.Store.YES));
		document.add(new TextField("content", content, Field.Store.NO));
		document.add(new TextField("date", date, Field.Store.YES));
		document.add(new TextField("link", link, Field.Store.YES));
		if (orgs != null && !orgs.isEmpty()) {
			document.add(new TextField("orgs", orgs, Field.Store.YES));
		}
		if (people != null && !people.isEmpty()) {
			document.add(new TextField("people", people, Field.Store.YES));
		}

		writer.addDocument(document);
		writer.commit();
	}

}
