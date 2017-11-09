package crawler.read;


import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import crawler.model.fourplayers.FeedMessage;

public class RSSHandler extends DefaultHandler{
	
	List<FeedMessage> items = new ArrayList<FeedMessage>();
	FeedMessage feed = null;
	boolean bItem = false;
	boolean bTitle = false;
	boolean bLink = false;
	boolean bDescription = false;
	boolean bPubDate = false;
	boolean bGuid = false;
	
	public List<FeedMessage> getItems() {
		return items;
	}

	@Override
	public void startElement(String uri, String localName, 
            String qName, Attributes attributes) throws SAXException {

		if(qName.equalsIgnoreCase("item")) {
			feed = new FeedMessage();
			feed.setTitle("Test");
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

		if(bTitle) {
			feed.setTitle(new String(ch, start, length));
			bTitle = false;
		} else if(bLink) {
			feed.setLink(new String(ch, start, length));
			bLink = false;
		} else if(bDescription) {
			feed.setDescription(new String(ch, start, length));
			bDescription = false;
		} else if(bPubDate) {
			feed.setPubDate(new String(ch, start, length));
			bPubDate = false;
		} else if(bGuid) {
			feed.setGuid(new String(ch, start, length));
			bGuid = false;
		}
}  
	@Override
    public void endElement(String uri, String localName,
          String qName) throws SAXException {
		if(qName.equalsIgnoreCase("item")) {
			items.add(feed);
		}
}

}
