package crawler.tests;

import java.util.List;

import crawler.model.fourplayers.FeedMessage;
import crawler.saxread.SAXRSSParser;

public class SaxTest {

	public static void main(String[] args) {
		
		SAXRSSParser parser = new SAXRSSParser("http://feeds.4players.de/Allgemein/articles/-/rss.xml","ChipXSD");
		List<FeedMessage> list = parser.readFeed();
		
		for(FeedMessage message: list) {
			System.out.println(message);
		}
		
	}

}
