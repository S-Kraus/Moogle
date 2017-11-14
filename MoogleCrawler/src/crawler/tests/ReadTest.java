package crawler.tests;

import java.util.List;

import crawler.model.fourplayers.FeedMessage;
import crawler.read.RSSParser;

public class ReadTest {
	// Nur die ersten beiden Seiten funktionieren mit ihren Schemas
	final static String fourplayers = "http://feeds.4players.de/Allgemein/articles/-/rss.xml"; // Schema geht
	final static String chip = "http://www.chip.de/rss/rss_spiele.xml"; // Schema geht
	final static String gamepro = "http://www.gamepro.de/rss/gpnews.rss"; // Schema geht nicht
	final static String gamestar = "http://www.gamestar.de/news/rss/news.rss"; // Schema geht nicht
	final static String giga = "http://www.giga.de/games/feed/"; // Schema geht nicht
	final static String golem = "https://rss.golem.de/rss.php?tp=games&feed=RSS2.0"; // Schema geht nicht
	final static String ign = "http://de.ign.com/news.xml"; // Schema geht nicht

	public static void main(String[] args) {
		
		RSSParser parser = new RSSParser(fourplayers,"4PlayersXSD");
		List<FeedMessage> list = parser.readFeed();
		
		for(FeedMessage message: list) {
			System.out.println(message);
		}
		
	}

}
