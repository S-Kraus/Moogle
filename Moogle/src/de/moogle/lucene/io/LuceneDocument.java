package de.moogle.lucene.io;

public class LuceneDocument {

	private String title;
	private String date;
	private String link;
	private String path;
	private String orgs;
	private String people;

	public LuceneDocument(String title, String date, String link, String path, String orgs, String people) {
		super();
		this.title = title;
		this.date = date;
		this.link = link;
		this.path = path;
		this.orgs = orgs;
		this.people = people;
	}

	@Override
	public String toString() {
		return "LuceneDocument [title=" + title + ", date=" + date + ", link=" + link + ", path=" + path + ", orgs="
				+ orgs + ", people=" + people + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getOrgs() {
		return orgs;
	}

	public void setOrgs(String orgs) {
		this.orgs = orgs;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

}
