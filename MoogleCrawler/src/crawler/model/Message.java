package crawler.model;


public class Message{
	
	private String headTitle;
	private String headLink;
	private String headDescription;
	private String title;
	private String description;
	private String pubDate;
	private String guid;
	private String extractedText;
	private String Organisationen;
	private String Personen;
	
	public Message() {
		
	}

	public Message(String headTitle, String headLink, String headDescription, String title, String description, String pubDate, String guid, String extractedText) {
		this.headTitle = headTitle;
		this.headLink = headLink;
		this.headDescription = headDescription;
		this.title = title;
		this.description = description;
		this.pubDate = pubDate;
		this.guid = guid;
		this.extractedText = extractedText;
	}

	public String getExtractedText() {
		return extractedText;
	}

	public void setExtractedText(String extractedText) {
		this.extractedText = extractedText;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getHeadTitle() {
		return headTitle;
	}

	public void setHeadTitle(String headTitle) {
		this.headTitle = headTitle;
	}

	public String getHeadLink() {
		return headLink;
	}

	public void setHeadLink(String headLink) {
		this.headLink = headLink;
	}

	public String getHeadDescription() {
		return headDescription;
	}

	public void setHeadDescription(String headDescription) {
		this.headDescription = headDescription;
	}

	public String getOrganisationen() {
		return Organisationen;
	}

	public void setOrganisationen(String organisationen) {
		Organisationen = organisationen;
	}

	public String getPersonen() {
		return Personen;
	}

	public void setPersonen(String personen) {
		Personen = personen;
	}

	

	@Override
	public String toString() {
		return "Message [headTitle=" + headTitle + ", headLink=" + headLink + ", headDescription=" + headDescription
				+ ", title=" + title + ", description=" + description + ", pubDate=" + pubDate + ", guid=" + guid
				+ ", extractedText=" + extractedText + ", Organisationen=" + Organisationen + ", Personen=" + Personen
				+ "]";
	}

	public Object clone() {
		Message clone = new Message(this.headTitle,this.headLink,this.headDescription,this.title,this.description,this.pubDate,this.guid,this.extractedText);
		return clone;
	}
	
	
}
