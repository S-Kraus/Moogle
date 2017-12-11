package crawler.tests;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import javax.sql.rowset.spi.XmlReader;
import javax.xml.bind.JAXBException;

import crawler.model.Message;
import crawler.read.RSSParserALL;
import crawler.read.XMLReader;

public class ReadXmlTest {
	
	final static String fourplayers = "http://feeds.4players.de/Allgemein/articles/-/rss.xml";
	final static String chip = "http://www.chip.de/rss/rss_spiele.xml";
	final static String gamepro = "http://www.gamepro.de/rss/gpnews.rss";
	final static String gamestar = "http://www.gamestar.de/news/rss/news.rss";
	final static String giga = "http://www.giga.de/games/feed/";
	final static String golem = "https://rss.golem.de/rss.php?tp=games&feed=RSS2.0";
	final static String ign = "http://de.ign.com/news.xml";
	
	public static void main(String[] args) throws JAXBException {
		RSSParserALL parser = new RSSParserALL(fourplayers,"4PlayersXSD");
		List<Message> list = parser.readFeed();
		
		for(Message message: list) {
			Message vergleich = XMLReader.readXML(message.getHeadTitle(), message.getTitle(), message.getPubDate());
			System.out.println(vergleich.toString());
		}
		
	}

}
