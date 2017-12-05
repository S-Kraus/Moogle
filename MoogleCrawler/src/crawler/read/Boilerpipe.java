package crawler.read;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.document.TextDocument;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import de.l3s.boilerpipe.sax.BoilerpipeSAXInput;
import de.l3s.boilerpipe.sax.HTMLFetcher;

public class Boilerpipe {
	
	public static String useBoilerpipe(String urlString){
		URL url;
		try {
			url = new URL(urlString);
			final InputSource is = HTMLFetcher.fetch(url).toInputSource();
			final BoilerpipeSAXInput in = new BoilerpipeSAXInput(is);
			final TextDocument doc = in.getTextDocument();
			return ArticleExtractor.INSTANCE.getText(doc);
		} catch(FileNotFoundException e){
			// Error Handling noch machen
			return "";
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return "";
		} catch (BoilerpipeProcessingException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		} catch (SAXException e) {
			e.printStackTrace();
			return "";
		}
		
		
	}

}
