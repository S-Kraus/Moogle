package crawler.read;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import crawler.model.Message;

public class RSSHandlerGiga extends RSSBaseHandler{

	public List<Message> getItems() {
		if(items == null) {
			items = new ArrayList<Message>();
		}
		
		return items;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		textContent.setLength(0);
		
		if(!boolHeadFinished){
			if(qName.equalsIgnoreCase(HEAD_TITLE)) {
				boolHeadTitle = true;
			} else if(qName.equalsIgnoreCase(HEAD_LINK)) {
				boolHeadLink = true;
			} else if(qName.equalsIgnoreCase(HEAD_DESCRIPTION)) {
				boolHeadDescription = true;
			} else if(qName.equalsIgnoreCase(ITEM_BEGIN)) {
				boolHeadFinished = true;
			}
		} else{
			if(qName.equalsIgnoreCase(ITEM_PUBDATE)) {
				message = new Message();
				boolItemPubDate = true;
			} else if(qName.equalsIgnoreCase(ITEM_TITLE)) {
				boolItemTitle = true;
			} else if(qName.equalsIgnoreCase(ITEM_DESCRIPTION)) {
				boolItemDescription = true;
			} else if(qName.equalsIgnoreCase(ITEM_GUID)) {
				boolItemGuid = true;
			}
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		textContent.append(ch,start,length);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		String text = textContent.toString().replaceAll("\\s{2,}", " ").trim();
		
		if(!boolHeadFinished){
			if(boolHeadTitle) {
				sHeadTitle = text;
				boolHeadTitle = false;
			} else if(boolHeadLink) {
				sHeadLink = text;
				boolHeadLink = false;
			} else if(boolHeadDescription) {
				sHeadDescription = text;
				boolHeadDescription = false;
			}
		} else {
			if(boolItemPubDate) {
				message.setPubDate(text);
				boolItemPubDate = false;
			} else if(boolItemTitle) {
				message.setTitle(text);
				boolItemTitle = false;
			} else if(boolItemDescription) {
				message.setDescription(text);
				boolItemDescription = false;
			} else if(boolItemGuid) {
				message.setGuid(text);
				boolItemGuid = false;
				boolItemFinished = true;
			}
			
			if(boolItemFinished) {
				message.setHeadTitle(sHeadTitle);
				message.setHeadLink(sHeadLink);
				message.setHeadDescription(sHeadDescription);
				getItems().add(message);
				message = null;
				boolItemFinished = false;
			}
		}
	}

}
