import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.NIOFSDirectory;

public class LuceneTest {

  public static void main(String[] args) throws Exception {
    Document document = new Document();
    String text = "This is a lucene test";
    document.add(new TextField("title", "Title 1", Field.Store.YES));
    document.add(new TextField("content", text, Field.Store.NO));

    NIOFSDirectory indexDir = new NIOFSDirectory(Paths.get("testIndexDir")); 
    Analyzer analyzer = new StandardAnalyzer();
    IndexWriter writer = new IndexWriter(indexDir, new IndexWriterConfig(analyzer));
    writer.addDocument(document);
    writer.close();
    
    DirectoryReader dr = DirectoryReader.open(indexDir);
    IndexSearcher searcher = new IndexSearcher(dr);
    QueryParser qp = new QueryParser("content",analyzer);
    String search = "lucene";
    Query query = qp.parse(search);

    TopDocs td = searcher.search(query,10);
    ScoreDoc[] sd = td.scoreDocs;
    for (int i=0; i < sd.length; i++) {
        Document doc = searcher.doc(sd[i].doc);
        System.out.println(doc.get("title"));
    }
    
    /*
    TopScoreDocCollector collector = TopScoreDocCollector.create(10, true);
    searcher.search(query, collector);
    ScoreDoc[] hits = collector.topDocs().scoreDocs;

    for(int i=0;i<hits.length;++i) {
      int docId = hits[i].doc;
      Document d = searcher.doc(docId);
      System.out.println(d.get("title"));
    }
    */
    System.out.println("done");
  }
}
