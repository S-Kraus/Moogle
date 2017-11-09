package crawler.model.fourplayers;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "feedmessage")
public class FeedMessage {
	
	String title;
	String link;
	String description;
	String pubDate;
	String guid;
	String extracedText;
	

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
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getExtracedText() {
		return extracedText;
	}
	public void setExtracedText(String extracedText) {
		this.extracedText = extracedText;
	}
	@Override
	public String toString() {
		return "FeedMessage [title=" + title + ", link=" + link + ", description=" + description + ", pubDate="
				+ pubDate + ", guid=" + guid + ", extracedText=" + extracedText + "]";
	}
	

	
	

}
