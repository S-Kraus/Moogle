package crawler.tests;

import java.io.IOException;
import java.util.List;

import crawler.model.Message;
import crawler.read.Boilerpipe;
import crawler.read.RSSHandler4Players;
import crawler.read.RSSHandlerChip;
import crawler.read.RSSHandlerGamepro;
import crawler.read.RSSHandlerGamestar;
import crawler.read.RSSHandlerGiga;
import crawler.read.RSSHandlerGolem;
import crawler.read.RSSHandlerIgn;
import crawler.read.RSSParserNew;
import ner.NERDemo;
/**
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 			Achtung!
 * Program dauert SEHR LANGE (mehrere Minuten)
 * -Es liest alle Seiten
 * -holt sich mit der boilerpipe den Artikeltext
 * -lässt danach noch das NER-Tool über den Text laufen
 * 
 * @author Simon.Kraus
 *
 */
public class ReadTestNew {
	
	final static String fourplayers = "http://feeds.4players.de/Allgemein/articles/-/rss.xml";
	final static String chip = "http://www.chip.de/rss/rss_spiele.xml";
	final static String gamepro = "http://www.gamepro.de/rss/gpnews.rss";
	final static String gamestar = "http://www.gamestar.de/news/rss/news.rss";
	final static String giga = "http://www.giga.de/games/feed/";
	final static String golem = "https://rss.golem.de/rss.php?tp=games&feed=RSS2.0";
	final static String ign = "http://de.ign.com/news.xml";

	public static void main(String[] args) throws ClassCastException, ClassNotFoundException, IOException {
		
		long a = System.currentTimeMillis();
		
		RSSParserNew parser4Players = new RSSParserNew(fourplayers,"4PlayersXSD", new RSSHandler4Players());
		List<Message> list4Players = parser4Players.readFeed();
		for(Message message: list4Players) {
			message.setExtractedText(Boilerpipe.useBoilerpipe(message.getGuid()));
			NERDemo ner = new NERDemo();
			ner.fillSets(message.getExtractedText());
			message.setOrganisationen(ner.getOrgs().toString());
			message.setPersonen(ner.getPers().toString());
		
		}
		System.out.println("Finished 4Players");
		
		
		RSSParserNew parserChip = new RSSParserNew(chip,"ChipXSD", new RSSHandlerChip());
		List<Message> listChip = parserChip.readFeed();
		for(Message message: listChip) {
			message.setExtractedText(Boilerpipe.useBoilerpipe(message.getGuid()));
			NERDemo ner = new NERDemo();
			ner.fillSets(message.getExtractedText());
			message.setOrganisationen(ner.getOrgs().toString());
			message.setPersonen(ner.getPers().toString());
		}
		System.out.println("Finished Chip");
		
		RSSParserNew parserGamepro = new RSSParserNew(gamepro,"GameproXSD", new RSSHandlerGamepro());
		List<Message> listGamepro = parserGamepro.readFeed();
		for(Message message: listGamepro) {
			message.setExtractedText(Boilerpipe.useBoilerpipe(message.getGuid()));
			NERDemo ner = new NERDemo();
			ner.fillSets(message.getExtractedText());
			message.setOrganisationen(ner.getOrgs().toString());
			message.setPersonen(ner.getPers().toString());
		}
		System.out.println("Finished Gamepro");
		
		RSSParserNew parserGamestar = new RSSParserNew(gamestar,"GamestarXSD", new RSSHandlerGamestar());
		List<Message> listGamestar = parserGamestar.readFeed();
		for(Message message: listGamestar) {
			message.setExtractedText(Boilerpipe.useBoilerpipe(message.getGuid()));
			NERDemo ner = new NERDemo();
			ner.fillSets(message.getExtractedText());
			message.setOrganisationen(ner.getOrgs().toString());
			message.setPersonen(ner.getPers().toString());
		}
		System.out.println("Finished Gamestar");
		
		RSSParserNew parserGiga = new RSSParserNew(giga,"GigaXSD", new RSSHandlerGiga());
		List<Message> listGiga = parserGiga.readFeed();
		for(Message message: listGiga) {
			message.setExtractedText(Boilerpipe.useBoilerpipe(message.getGuid()));
			NERDemo ner = new NERDemo();
			ner.fillSets(message.getExtractedText());
			message.setOrganisationen(ner.getOrgs().toString());
			message.setPersonen(ner.getPers().toString());
		}
		System.out.println("Finished Giga");
		
		RSSParserNew parserGolem = new RSSParserNew(golem,"GolemXSD", new RSSHandlerGolem());
		List<Message> listGolem = parserGolem.readFeed();
		for(Message message: listGolem) {
			message.setExtractedText(Boilerpipe.useBoilerpipe(message.getGuid()));
			NERDemo ner = new NERDemo();
			ner.fillSets(message.getExtractedText());
			message.setOrganisationen(ner.getOrgs().toString());
			message.setPersonen(ner.getPers().toString());
		}
		System.out.println("Finished Golem");
		
		RSSParserNew parserIgn = new RSSParserNew(ign,"IgnXSD", new RSSHandlerIgn());
		List<Message> listIgn = parserIgn.readFeed();
		for(Message message: listIgn) {
			message.setExtractedText(Boilerpipe.useBoilerpipe(message.getGuid()));
			NERDemo ner = new NERDemo();
			ner.fillSets(message.getExtractedText());
			message.setOrganisationen(ner.getOrgs().toString());
			message.setPersonen(ner.getPers().toString());


		}
		System.out.println("Finished Ign");		
		System.out.println("Finished All");
		long b = System.currentTimeMillis() - a;
		System.out.println(b + " Millisekunden");
		System.out.println(b/1000 + " Sekunden");
	
	
	
	}
	

}
