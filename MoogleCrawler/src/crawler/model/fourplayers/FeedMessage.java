package crawler.model.fourplayers;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "feedmessage")
public class FeedMessage {
	
	String headtitle;
	String headlink;
	String headdescription;
	String headlanguage;
	String headimage;
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
	public String getHeadtitle() {
		return headtitle;
	}
	public void setHeadtitle(String headtitle) {
		this.headtitle = headtitle;
	}
	public String getHeadlink() {
		return headlink;
	}
	public void setHeadlink(String headlink) {
		this.headlink = headlink;
	}
	public String getHeaddescription() {
		return headdescription;
	}
	public void setHeaddescription(String headdescription) {
		this.headdescription = headdescription;
	}
	public String getHeadlanguage() {
		return headlanguage;
	}
	public void setHeadlanguage(String headlanguage) {
		this.headlanguage = headlanguage;
	}
	public String getHeadimage() {
		return headimage;
	}
	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}
	@Override
	public String toString() {
		return "FeedMessage [headtitle=" + headtitle + ", headlink=" + headlink + ", headdescription=" + headdescription
				+ ", headlanguage=" + headlanguage + ", headimage=" + headimage + ", title=" + title + ", link=" + link
				+ ", description=" + description + ", pubDate=" + pubDate + ", guid=" + guid + ", extracedText="
				+ extracedText + "]";
	}

}
