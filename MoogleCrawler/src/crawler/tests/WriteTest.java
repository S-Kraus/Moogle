package crawler.tests;
/*
 * 
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * Beim Ausführen werden im Ordner output Dateien erzeugt.
 * Es muss einen Ordner output im Projekt geben oder man
 * muss einen neuen Ordner angeben bei bei der Konstanten 
 * in der Klasse RSSWriter
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 
 * 
 */
import java.util.List;

import javax.xml.bind.JAXBException;

import crawler.model.fourplayers.FeedMessage;
import crawler.read.RSSParser;
import crawler.write.RSSWriter;

public class WriteTest {
	
	final static String fourplayers = "http://feeds.4players.de/Allgemein/articles/-/rss.xml";
	final static String chip = "http://www.chip.de/rss/rss_spiele.xml";
	final static String gamepro = "http://www.gamepro.de/rss/gpnews.rss";
	final static String gamestar = "http://www.gamestar.de/news/rss/news.rss";
	final static String giga = "http://www.giga.de/games/feed/";
	final static String golem = "https://rss.golem.de/rss.php?tp=games&feed=RSS2.0";
	final static String ign = "http://de.ign.com/news.xml";
	
	

	public static void main(String[] args) throws JAXBException {
		
		RSSParser parser = new RSSParser(fourplayers,"ChipXSD");
		List<FeedMessage> list = parser.readFeed();
		
		RSSWriter writer = new RSSWriter();
		
		for(int i = 0; i < list.size(); i++) {
			String hash = Integer.toString(Math.abs(list.get(i).getTitle().hashCode()));
			writer.write(list.get(i), hash);
		}

		
	}
}
