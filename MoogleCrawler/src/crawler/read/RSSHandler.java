package crawler.read;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import crawler.model.fourplayers.FeedMessage;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.document.TextDocument;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import de.l3s.boilerpipe.sax.BoilerpipeSAXInput;
import de.l3s.boilerpipe.sax.HTMLFetcher;

public class RSSHandler extends DefaultHandler{
	
	List<FeedMessage> items = new ArrayList<FeedMessage>();
	FeedMessage feed = null;
	boolean bItem = false;
	boolean bTitle = false;
	boolean bLink = false;
	boolean bDescription = false;
	boolean bPubDate = false;
	boolean bGuid = false;
	
	StringBuilder textContent = new StringBuilder();
	
	public List<FeedMessage> getItems() {
		return items;
	}

	@Override
	public void startElement(String uri, String localName, 
            String qName, Attributes attributes) throws SAXException {
		
		textContent.setLength(0);
		if(qName.equalsIgnoreCase("item")) {
			feed = new FeedMessage();
			bItem = true;
		} else if(bItem) {
			if(qName.equalsIgnoreCase("title")) {
				bTitle = true;			
			} else if(qName.equalsIgnoreCase("link")) {
				bLink = true;
			} else if(qName.equalsIgnoreCase("description")) {
				bDescription = true;
			} else if(qName.equalsIgnoreCase("pubDate")) {
				bPubDate = true;
			} else if(qName.equalsIgnoreCase("guid")) {
				bGuid = true;
			}
		}
}
	@Override
    public void characters(char ch[], int start, int length) 
            throws SAXException { 
		textContent.append(ch, start, length);

}  
	@Override
    public void endElement(String uri, String localName,
          String qName) throws SAXException {
		String text = textContent.toString();
		
		if(bTitle) {
			feed.setTitle(text);
			bTitle = false;
		} else if(bLink) {
			feed.setLink(text);
			bLink = false;
		} else if(bDescription) {
			feed.setDescription(text);
			bDescription = false;
		} else if(bPubDate) {
			feed.setPubDate(text);
			bPubDate = false;
		} else if(bGuid) {
			feed.setGuid(text);
			bGuid = false;
		}else if(qName.equalsIgnoreCase("item")) {
			try {
				feed.setExtracedText(useBoilerpipe(feed.getGuid()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			items.add(feed);
		}
}
	
	public String useBoilerpipe(String urlString){
		URL url;
		try {
			url = new URL(urlString);
			final InputSource is = HTMLFetcher.fetch(url).toInputSource();
			final BoilerpipeSAXInput in = new BoilerpipeSAXInput(is);
			final TextDocument doc = in.getTextDocument();
			return ArticleExtractor.INSTANCE.getText(doc);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (BoilerpipeProcessingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (SAXException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

}
