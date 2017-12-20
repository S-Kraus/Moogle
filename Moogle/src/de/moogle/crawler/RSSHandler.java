package de.moogle.crawler;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class RSSHandler extends DefaultHandler{
	private StringBuilder textContent = new StringBuilder();
	private List<Message> items;
	private Message message;
	
	private String sHeadTitle;
	private String sHeadLink;
	private String sHeadDescription;

	private boolean boolHeadTitle = false;
	private boolean boolHeadLink = false;
	private boolean boolHeadDescription = false;
	private boolean boolHeadFinished = false;
	
	private boolean boolItemTitle = false;
	private boolean boolItemDescription = false;
	private boolean boolItemPubDate = false;
	private boolean boolItemGuid = false;
	private boolean boolItemBegin = false;
	
	private final String HEAD_TITLE = "title";
	private final String HEAD_LINK = "link";
	private final String HEAD_DESCRIPTION = "description";
	private final String ITEM_BEGIN = "item";
	private final String ITEM_TITLE = "title";
	private final String ITEM_DESCRIPTION = "description";
	private final String ITEM_PUBDATE = "pubdate";
	private final String ITEM_GUID = "guid";
	
	private DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z",Locale.US);


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
			if(qName.equalsIgnoreCase(ITEM_BEGIN)) {
				boolItemBegin = true;
				message = new Message();
			} else if(qName.equalsIgnoreCase(ITEM_TITLE)) {
				boolItemTitle = true;
			} else if(qName.equalsIgnoreCase(ITEM_DESCRIPTION)) {
				boolItemDescription = true;
			} else if(qName.equalsIgnoreCase(ITEM_PUBDATE)) {
				boolItemPubDate = true;
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
		} else if(boolItemBegin){
			
			if(boolItemTitle) {
				message.setTitle(text);
				boolItemTitle = false;
			} else if(boolItemDescription) {
				message.setDescription(text);
				boolItemDescription = false;
			} else if(boolItemPubDate) {
				try {
					message.setPubDate(formatter.parse(text).toString());
				} catch (ParseException e) {
					message.setPubDate(text);
				}
				boolItemPubDate = false;
			} else if(boolItemGuid) {
				message.setGuid(text);
				boolItemGuid = false;
			} else if(qName.equalsIgnoreCase(ITEM_BEGIN)) {
				message.setHeadTitle(sHeadTitle);
				message.setHeadLink(sHeadLink);
				message.setHeadDescription(sHeadDescription);
				getItems().add(message);
				message = null;
				boolItemBegin = false;
			}
		}
	}

}
