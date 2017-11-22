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

import crawler.model.fourplayers.Feed;
import crawler.model.fourplayers.FeedMessage;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.document.TextDocument;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import de.l3s.boilerpipe.sax.BoilerpipeSAXInput;
import de.l3s.boilerpipe.sax.HTMLFetcher;

public class RSSHandler2 extends DefaultHandler{
	
	FeedMessage feed = null;
	List<FeedMessage> list = new ArrayList<FeedMessage>();
	Feed gesamtFeed = new Feed();
	
	private static final int HEAD_DEFAULT_STATE = 0;
	private static final int HEAD_TITLE_STATE = 1;
	private static final int HEAD_LINK_STATE = 2;
	private static final int HEAD_DESCRIPTION_STATE = 3;
	private static final int HEAD_LANGUAGE_STATE = 4;
	private static final int HEAD_IMAGE_STATE = 5;
	private static final int HEAD_FINISHED_STATE = 6;
	private int head_state = HEAD_DEFAULT_STATE;
	
	private static final int ITEM_DEFAULT_STATE = 0;
	private static final int ITEM_TITLE_STATE = 1;
	private static final int ITEM_LINK_STATE = 2;
	private static final int ITEM_DESCRIPTION_STATE = 3;
	private static final int ITEM_PUBDATE_STATE = 4;
	private static final int ITEM_GUID_STATE = 5;
	private static final int ITEM_FISNISHED_STATE = 6;
	private int item_state = HEAD_DEFAULT_STATE;
	
	StringBuilder textContent = new StringBuilder();
	
	public List<FeedMessage> getItems() {
		return list;
	}

	@Override
	public void startElement(String uri, String localName, 
            String qName, Attributes attributes) throws SAXException {
		
		textContent.setLength(0);
		
		if(head_state == HEAD_DEFAULT_STATE) {
			if(qName.equalsIgnoreCase("title")) {
				gesamtFeed = new Feed();
				head_state = HEAD_TITLE_STATE;
			} else if(qName.equalsIgnoreCase("link")) {
				head_state = HEAD_LINK_STATE;
			} else if(qName.equalsIgnoreCase("description")) {
				head_state = HEAD_DESCRIPTION_STATE;
			} else if(qName.equalsIgnoreCase("language")) {
				head_state = HEAD_LANGUAGE_STATE;
			} else if(qName.equalsIgnoreCase("url")) {
				head_state = HEAD_IMAGE_STATE;
			}
		} else {
			if(qName.equalsIgnoreCase("title")) {
				feed = new FeedMessage();
				item_state = ITEM_TITLE_STATE;
			} else if(qName.equalsIgnoreCase("link")) {
				item_state = ITEM_LINK_STATE;
			} else if(qName.equalsIgnoreCase("description")) {
				item_state = ITEM_DESCRIPTION_STATE;
			} else if(qName.equalsIgnoreCase("pubDate")) {
				item_state = ITEM_PUBDATE_STATE;
			} else if(qName.equalsIgnoreCase("guid")) {
				item_state = ITEM_GUID_STATE;
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
		String text = textContent.toString().replaceAll("\\s{2,}", " ").trim();
		
		if(head_state != HEAD_FINISHED_STATE) {
			switch(head_state) {
			
			case(HEAD_TITLE_STATE):
				gesamtFeed.setTitle(text);
				break;
			case(HEAD_LINK_STATE):
				gesamtFeed.setLink(text);
				break;
			case(HEAD_DESCRIPTION_STATE):
				gesamtFeed.setDescription(text);
				break;
			case(HEAD_LANGUAGE_STATE):
				gesamtFeed.setLanguage(text);
				break;
			case(HEAD_IMAGE_STATE):
				gesamtFeed.setImage(text);
				head_state = HEAD_FINISHED_STATE;
				break;
			default:
				break;
			}
			if(head_state != HEAD_FINISHED_STATE) {
				head_state = HEAD_DEFAULT_STATE;
			}
		} else {
			switch(item_state) {
			case(ITEM_TITLE_STATE):
				feed.setTitle(text);
				break;
			case(ITEM_LINK_STATE):
				feed.setLink(text);
				feed.setExtracedText(useBoilerpipe(text));
				break;
			case(ITEM_DESCRIPTION_STATE):
				feed.setDescription(text);
				break;
			case(ITEM_PUBDATE_STATE):
				feed.setPubDate(text);
				break;
			case(ITEM_GUID_STATE):
				feed.setGuid(text);
				feed.setHeadtitle(gesamtFeed.getTitle());
				feed.setHeadlink(gesamtFeed.getLink());
				feed.setHeaddescription(gesamtFeed.getDescription());
				feed.setHeadlanguage(gesamtFeed.getLanguage());
				feed.setHeadimage(gesamtFeed.getImage());
				list.add(feed);
				item_state = ITEM_FISNISHED_STATE;
				break;
			case(ITEM_FISNISHED_STATE):
				break;
			default:
				break;
				
			}
			
			if(item_state != ITEM_FISNISHED_STATE) {
				item_state = ITEM_DEFAULT_STATE;
			}
		}
		
}


	private String useBoilerpipe(String urlString){
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
