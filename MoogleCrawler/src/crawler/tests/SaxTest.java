package crawler.tests;

import crawler.saxread.SAXRSSParser;

public class SaxTest {

	public static void main(String[] args) {
		
		SAXRSSParser parser = new SAXRSSParser("http://feeds.4players.de/Allgemein/articles/-/rss.xml","ChipXSD");
		parser.readFeed();
		
	}

}
