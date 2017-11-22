import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.NIOFSDirectory;

public class LuceneTest {

	// public static void main(String[] args) throws Exception {
	// Document document = new Document();
	// String text = "This is a lucene test";
	// document.add(new TextField("title", "Title 1", Field.Store.YES));
	// document.add(new TextField("content", text, Field.Store.NO));
	//
	// NIOFSDirectory indexDir = new NIOFSDirectory(Paths.get("testIndexDir"));
	// Analyzer analyzer = new StandardAnalyzer();
	// IndexWriter writer = new IndexWriter(indexDir, new
	// IndexWriterConfig(analyzer));
	// writer.addDocument(document);
	// writer.close();
	//
	// DirectoryReader dr = DirectoryReader.open(indexDir);
	// IndexSearcher searcher = new IndexSearcher(dr);
	// QueryParser qp = new QueryParser("content",analyzer);
	// String search = "lucene";
	// Query query = qp.parse(search);
	//
	// TopDocs td = searcher.search(query,10);
	// ScoreDoc[] sd = td.scoreDocs;
	// for (int i=0; i < sd.length; i++) {
	// Document doc = searcher.doc(sd[i].doc);
	// System.out.println(doc.get("title"));
	// }
	//
	// /*
	// TopScoreDocCollector collector = TopScoreDocCollector.create(10, true);
	// searcher.search(query, collector);
	// ScoreDoc[] hits = collector.topDocs().scoreDocs;
	//
	// for(int i=0;i<hits.length;++i) {
	// int docId = hits[i].doc;
	// Document d = searcher.doc(docId);
	// System.out.println(d.get("title"));
	// }
	// */
	// System.out.println("done");
	// }

	public static void main(String[] args) throws IOException, ParseException {
		// 0. Specify the analyzer for tokenizing text.
		// The same analyzer should be used for indexing and searching
		StandardAnalyzer analyzer = new StandardAnalyzer();

		// 1. create the index
		NIOFSDirectory indexDir = new NIOFSDirectory(Paths.get("testIndexDir"));

		IndexWriterConfig config = new IndexWriterConfig(analyzer);

		IndexWriter w = new IndexWriter(indexDir, config);
		addDoc(w, "Lucene in Action", "193398817");
		addDoc(w, "Lucene for Dummies", "55320055Z");
		addDoc(w, "Managing Gigabytes", "55063554A");
		addDoc(w, "The Art of Computer Science", "9900333X");
		w.close();

		// 2. query
		String querystr = args.length > 0 ? args[0] : "lucene";

		// the "title" arg specifies the default field to use
		// when no field is explicitly specified in the query.
		Query q = new QueryParser("title", analyzer).parse(querystr);

		// 3. search
		int hitsPerPage = 10;
		IndexReader reader = DirectoryReader.open(indexDir);
		IndexSearcher searcher = new IndexSearcher(reader);
		TopDocs docs = searcher.search(q, hitsPerPage);
		ScoreDoc[] hits = docs.scoreDocs;

		// 4. display results
		System.out.println("Found " + hits.length + " hits.");
		for (int i = 0; i < hits.length; ++i) {
			int docId = hits[i].doc;
			Document d = searcher.doc(docId);
			System.out.println((i + 1) + ". " + d.get("isbn") + "\t" + d.get("title"));
		}

		// reader can only be closed when there
		// is no need to access the documents any more.
		reader.close();
	}

	private static void addDoc(IndexWriter w, String title, String isbn) throws IOException {
		Document doc = new Document();
		doc.add(new TextField("title", title, Field.Store.YES));

		// use a string field for isbn because we don't want it tokenized
		doc.add(new StringField("isbn", isbn, Field.Store.YES));
		w.addDocument(doc);
	}
}