package crawler.tests;

import java.util.List;

import crawler.model.Message;
import crawler.read.RSSParserALL;

public class ReadTestNewNew {
	
	final static String fourplayers = "http://feeds.4players.de/Allgemein/articles/-/rss.xml";
	final static String chip = "http://www.chip.de/rss/rss_spiele.xml";
	final static String gamepro = "http://www.gamepro.de/rss/gpnews.rss";
	final static String gamestar = "http://www.gamestar.de/news/rss/news.rss";
	final static String giga = "http://www.giga.de/games/feed/";
	final static String golem = "https://rss.golem.de/rss.php?tp=games&feed=RSS2.0";
	final static String ign = "http://de.ign.com/news.xml";
	
	public static void main(String[] args) {
		
		RSSParserALL parser4Players = new RSSParserALL(fourplayers,"4PlayersXSD");
		List<Message> list4Players = parser4Players.readFeed();
		for(Message message: list4Players) {
			System.out.println(message);
		}
		
		RSSParserALL parserChip = new RSSParserALL(chip,"ChipXSD");
		List<Message> listChip = parserChip.readFeed();
		for(Message message: listChip) {
			System.out.println(message);
		}
		
		RSSParserALL parserGamepro = new RSSParserALL(gamepro,"GameproXSD");
		List<Message> listGamepro = parserGamepro.readFeed();
		for(Message message: listGamepro) {
			System.out.println(message);
		}
		
		RSSParserALL parserGamestar = new RSSParserALL(gamestar,"GamestarXSD");
		List<Message> listGamestar = parserGamestar.readFeed();
		for(Message message: listGamestar) {
			System.out.println(message);
		}
		RSSParserALL parserGiga = new RSSParserALL(giga,"GigaXSD");
		List<Message> listGiga = parserGiga.readFeed();
		for(Message message: listGiga) {
			System.out.println(message);
		}
		
		RSSParserALL parserGolem = new RSSParserALL(golem,"GolemXSD");
		List<Message> listGolem = parserGolem.readFeed();
		for(Message message: listGolem) {
			System.out.println(message);
		}
		RSSParserALL parserIgn = new RSSParserALL(ign,"IgnXSD");
		List<Message> listIgn = parserIgn.readFeed();
		for(Message message: listIgn) {
			System.out.println(message);
		}
		
		System.out.println("Hallo");
	}

}
