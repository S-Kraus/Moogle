package de.moogle.crawler;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pubDate == null) ? 0 : pubDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (pubDate == null) {
			if (other.pubDate != null)
				return false;
		} else if (!pubDate.equals(other.pubDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	public String convertHash() {
		int hash = this.hashCode();
		String convertedHash = Integer.toString(hash);
		if( hash < 0 ) {
			convertedHash = convertedHash.replace('-', '0');
		}
		return convertedHash;
	}
	
	public String createFilename() {
		final String FOURPLAYERS = "4Players.de";
		final String CHIP = "![CDATA[CHIP Online Spiele]]";
		final String GAMEPRO = "GamePro";
		final String GAMESTAR = "GameStar";
		final String GIGA = "GIGA GAMES";
		final String GOLEM = "Golem.de - Games";
		final String IGN = "IGN Deutschland";
		
		final String dir = "output/";
		final String suffix = ".xml";
		
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append(dir);
		switch(this.getHeadTitle()){
			case(FOURPLAYERS):
				sbuilder.append("4Players/");
				break;
			case(CHIP):
				sbuilder.append("chip/");
				break;
			case(GAMEPRO):
				sbuilder.append("gamepro/");
				break;
			case(GAMESTAR):
				sbuilder.append("gamestar/");
				break;
			case(GIGA):
				sbuilder.append("giga/");
				break;
			case(GOLEM):
				sbuilder.append("golem/");
				break;
			case(IGN):
				sbuilder.append("ign/");
				break;
			
		}
		sbuilder.append("RSS");
		sbuilder.append(this.convertHash());
		sbuilder.append(suffix);
		
		return sbuilder.toString();
	}
}
