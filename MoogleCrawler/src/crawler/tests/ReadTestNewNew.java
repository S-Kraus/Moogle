package crawler.tests;

import java.io.IOException;
import java.util.List;

import crawler.model.Message;
import crawler.read.Boilerpipe;
import crawler.read.RSSParserALL;
import io.LuceneWriter;
import ner.NERDemo;

public class ReadTestNewNew {
	
	final static String fourplayers = "http://feeds.4players.de/Allgemein/articles/-/rss.xml";
	final static String chip = "http://www.chip.de/rss/rss_spiele.xml";
	final static String gamepro = "http://www.gamepro.de/rss/gpnews.rss";
	final static String gamestar = "http://www.gamestar.de/news/rss/news.rss";
	final static String giga = "http://www.giga.de/games/feed/";
	final static String golem = "https://rss.golem.de/rss.php?tp=games&feed=RSS2.0";
	final static String ign = "http://de.ign.com/news.xml";
	
	public static void main(String[] args) throws IOException, ClassCastException, ClassNotFoundException {
		
		NERDemo ner = NERDemo.getInstance();
		LuceneWriter luceneWriter = LuceneWriter.getInstance();
		
		
		RSSParserALL parser = new RSSParserALL(fourplayers, "4PlayersXSD");
		List<Message> list = parser.readFeed();
		buildArchive(ner, list, luceneWriter);
		
		parser = new RSSParserALL(chip,"ChipXSD");
		list = parser.readFeed();
		buildArchive(ner, list, luceneWriter);
		
		parser = new RSSParserALL(gamepro,"GameproXSD");
		list = parser.readFeed();
		buildArchive(ner, list, luceneWriter);
		
		parser = new RSSParserALL(gamestar,"GamestarXSD");
		list = parser.readFeed();
		buildArchive(ner, list, luceneWriter);
		
		parser = new RSSParserALL(giga,"GigaXSD");
		list = parser.readFeed();
		buildArchive(ner, list, luceneWriter);
		
		parser = new RSSParserALL(golem,"GolemXSD");
		list = parser.readFeed();
		buildArchive(ner, list, luceneWriter);
		
		parser = new RSSParserALL(ign,"IgnXSD");
		list = parser.readFeed();
		buildArchive(ner, list, luceneWriter);
	}
	
	private static void buildArchive(NERDemo ner, List<Message> list, LuceneWriter luceneWriter)
			throws ClassNotFoundException, IOException {
		for(Message message : list) {


			message.setExtractedText(Boilerpipe.useBoilerpipe(message.getGuid()));
			ner.clearSets();
			ner.fillSets(message.getExtractedText());
			luceneWriter.createDocIndex(message.getTitle(), message.getExtractedText(), message.getPubDate(), message.getGuid(), message.getOrganisationen(), message.getPersonen());

			 	
		}
	}
}
