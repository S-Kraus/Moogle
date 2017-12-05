package crawler.tests;

import java.io.IOException;
import java.util.List;

import crawler.model.fourplayers.FeedMessage;
import crawler.read.RSSParser;
import io.LuceneWriter;

public class ReadTest {
	
	final static String fourplayers = "http://feeds.4players.de/Allgemein/articles/-/rss.xml";
	final static String chip = "http://www.chip.de/rss/rss_spiele.xml";
	final static String gamepro = "http://www.gamepro.de/rss/gpnews.rss";
	final static String gamestar = "http://www.gamestar.de/news/rss/news.rss";
	final static String giga = "http://www.giga.de/games/feed/";
	final static String golem = "https://rss.golem.de/rss.php?tp=games&feed=RSS2.0";
	final static String ign = "http://de.ign.com/news.xml";

	public static void main(String[] args) throws IOException   {
		
		RSSParser parser = new RSSParser(fourplayers,"4PlayersXSD");
		List<FeedMessage> list = parser.readFeed();
		
		for(FeedMessage message: list) {
			System.out.println(message);
			LuceneWriter.createDocIndex(message.getTitle(), message.getExtracedText(), message.getPubDate(), message.getLink());
		}
		
	}

}
