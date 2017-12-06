package io;

public class LuceneDocument {
	
	String title;
	String date;
	String link;
	String orgs;
	String people;
	
	
	public LuceneDocument(String title, String date, String link, String orgs, String people) {
		this.title = title;
		this.date = date;
		this.link = link;
		this.orgs = orgs;
		this.people = people;
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


	@Override
	public String toString() {
		return "title=" + title + ", date=" + date + ", link=" + link + ", orgs=" + orgs + ", people="
				+ people;
	}
	
	

}
