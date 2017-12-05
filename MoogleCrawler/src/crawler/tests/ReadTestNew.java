package crawler.tests;

import java.util.List;

import crawler.model.Message;
import crawler.read.RSSHandler4Players;
import crawler.read.RSSHandlerChip;
import crawler.read.RSSParserNew;

public class ReadTestNew {
	
	final static String fourplayers = "http://feeds.4players.de/Allgemein/articles/-/rss.xml";
	final static String chip = "http://www.chip.de/rss/rss_spiele.xml";
	final static String gamepro = "http://www.gamepro.de/rss/gpnews.rss";
	final static String gamestar = "http://www.gamestar.de/news/rss/news.rss";
	final static String giga = "http://www.giga.de/games/feed/";
	final static String golem = "https://rss.golem.de/rss.php?tp=games&feed=RSS2.0";
	final static String ign = "http://de.ign.com/news.xml";

	public static void main(String[] args) {
		
		RSSParserNew parser4Players = new RSSParserNew(fourplayers,"4PlayersXSD", new RSSHandler4Players());
		List<Message> list4Players = parser4Players.readFeed();
		for(Message message: list4Players) {
			System.out.println(message);
		}
		
//		RSSParserNew parserChip = new RSSParserNew(chip,"ChipXSD", new RSSHandlerChip());
//		List<Message> listChip = parserChip.readFeed();
//		for(Message message: listChip) {
//			System.out.println(message);
//		}
		
	}

}
