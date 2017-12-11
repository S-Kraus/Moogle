package crawler.tests;

import java.io.File;
import java.io.IOException;
import java.util.List;

import crawler.model.Message;
import crawler.read.Boilerpipe;
import crawler.read.RSSParserALL;
import crawler.write.RSSWriterNew;
import ner.NERDemo;


public class MainTest {
	final static String fourplayers = "http://feeds.4players.de/Allgemein/articles/-/rss.xml";
	final static String chip = "http://www.chip.de/rss/rss_spiele.xml";
	final static String gamepro = "http://www.gamepro.de/rss/gpnews.rss";
	final static String gamestar = "http://www.gamestar.de/news/rss/news.rss";
	final static String giga = "http://www.giga.de/games/feed/";
	final static String golem = "https://rss.golem.de/rss.php?tp=games&feed=RSS2.0";
	final static String ign = "http://de.ign.com/news.xml";
	
	public static void main(String[] args) throws ClassCastException, ClassNotFoundException, IOException {
		RSSParserALL parser = new RSSParserALL(fourplayers,"4PlayersXSD");
		List<Message> list = parser.readFeed();
		RSSWriterNew writer = new RSSWriterNew();
		NERDemo ner = new NERDemo();
		for(Message message : list) {
			File file = new File(message.createFilename());
			if( !file.exists()) {
				
				message.setExtractedText(Boilerpipe.useBoilerpipe(message.getGuid()));
				
				ner.clearSets();
				ner.fillSets(message.getExtractedText());
				message.setOrganisationen(ner.getOrgs().toString());
				message.setPersonen(ner.getPers().toString());
				
				// Lucene
				
				
				writer.write(message);
			}

			
			
		}
	}


}
