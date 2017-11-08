package crawler.model.fourplayers;

import java.util.ArrayList;
import java.util.List;

public class Feed {
	

	String title;
	String link;
	String description;
	String language;
	String image;
	List<FeedMessage> items = new ArrayList<FeedMessage>();
	
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


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public List<FeedMessage> getItems() {
		return items;
	}
	
	public void addMessage(FeedMessage message) {
		getItems().add(message);
	}


	@Override
	public String toString() {
		return "Feed [title=" + title + ", link=" + link + ", description=" + description + ", language=" + language
				+ ", image=" + image + ", items=" + items + "]";
	}
	
	
	
	

}
