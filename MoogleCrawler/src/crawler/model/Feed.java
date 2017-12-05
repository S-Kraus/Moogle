package crawler.model;

import java.util.ArrayList;
import java.util.List;

public class Feed {
	
	private String title;
	private String link;
	private String description;
	private List<Message> messages;
	
	public Feed() {
		
	}
	
	public Feed(String title, String link, String description, ArrayList<Message> messages) {
		this.title = title;
		this.link = link;
		this.description = description;
		this.messages = messages;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Message> getMessages() {
		if(this.messages == null) {
			this.messages = new ArrayList<Message>();
		}
		
		List<Message> returnList = new ArrayList<Message>();
		for(Message message: this.messages) {
			returnList.add((Message) message.clone());
		}
		return returnList;
	}
	
	public void addMessage(Message message) {
		if(this.messages == null) {
			this.messages = new ArrayList<Message>();
		}
		this.messages.add(message);
	}

}
