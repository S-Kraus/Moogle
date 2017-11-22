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

import crawler.model.fourplayers.FeedMessage;

public class LuceneWriter {

	public void createDocIndex(FeedMessage doc) throws IOException {
		Document document = new Document();
		document.add(new TextField("title", doc.getTitle(), Field.Store.YES));
		document.add(new TextField("content", doc.getExtracedText(), Field.Store.NO));
		document.add(new TextField("date", doc.getPubDate(), Field.Store.YES));
		document.add(new TextField("link", doc.getLink(), Field.Store.YES));

		NIOFSDirectory indexDir = new NIOFSDirectory(Paths.get("testIndexDir"));
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriter writer = new IndexWriter(indexDir, new IndexWriterConfig(analyzer));
		writer.addDocument(document);
		writer.close();
	}
}
