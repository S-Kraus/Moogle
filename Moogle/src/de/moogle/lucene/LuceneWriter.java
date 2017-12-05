package de.moogle.lucene;

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

	public static void createDocIndex(String title, String content, String date, String link) throws IOException {
		Document document = new Document();
		document.add(new TextField("title", title, Field.Store.YES));
		document.add(new TextField("content", content, Field.Store.NO));
		document.add(new TextField("date", date, Field.Store.YES));
		document.add(new TextField("link", link, Field.Store.YES));

		NIOFSDirectory indexDir = new NIOFSDirectory(Paths.get("C:\\testDir"));
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriter writer = new IndexWriter(indexDir, new IndexWriterConfig(analyzer));
		writer.addDocument(document);
		writer.close();
	}
}
