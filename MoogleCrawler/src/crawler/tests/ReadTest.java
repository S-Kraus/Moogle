package crawler.tests;

import java.util.List;

import crawler.model.fourplayers.FeedMessage;
import crawler.read.RSSParser;

public class ReadTest {

	public static void main(String[] args) {
		
		RSSParser parser = new RSSParser("http://feeds.4players.de/Allgemein/articles/-/rss.xml","ChipXSD");
		List<FeedMessage> list = parser.readFeed();
		
		for(FeedMessage message: list) {
			System.out.println(message);
		}
		
	}

}
