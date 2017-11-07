package crawler.tests;

import crawler.saxread.SAXRSSParser;

public class SaxTest {

	public static void main(String[] args) {
		
		SAXRSSParser parser = new SAXRSSParser("http://www.chip.de/rss/rss_spiele.xml","ChipXSD");
		parser.readFeed();
		
	}

}
