package crawler.read;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.helpers.DefaultHandler;

import crawler.model.Message;

public class RSSBaseHandler extends DefaultHandler{
	
	protected StringBuilder textContent = new StringBuilder();
	protected List<Message> items;
	protected Message message;
	
	protected String sHeadTitle;
	protected String sHeadLink;
	protected String sHeadDescription;

	protected boolean boolHeadTitle = false;
	protected boolean boolHeadLink = false;
	protected boolean boolHeadDescription = false;
	protected boolean boolHeadFinished = false;
	
	protected boolean boolItemTitle = false;
	protected boolean boolItemDescription = false;
	protected boolean boolItemPubDate = false;
	protected boolean boolItemGuid = false;
	protected boolean boolItemFinished = false;
	
	protected final String HEAD_TITLE = "title";
	protected final String HEAD_LINK = "link";
	protected final String HEAD_DESCRIPTION = "description";
	protected final String ITEM_BEGIN = "item";
	protected final String ITEM_TITLE = "title";
	protected final String ITEM_DESCRIPTION = "description";
	protected final String ITEM_PUBDATE = "pubdate";
	protected final String ITEM_GUID = "guid";

	public List<Message> getItems() {
		if(items == null) {
			items = new ArrayList<Message>();
		}
		
		return items;
	}

}
