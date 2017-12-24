package de.moogle.main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import de.moogle.crawler.Boilerpipe;
import de.moogle.crawler.Message;
import de.moogle.crawler.RSSParser;
import de.moogle.crawler.RSSWriter;
import de.moogle.ner.NERDemo;
import io.LuceneWriter;

public class Main {

	private final static String fourplayers = "http://feeds.4players.de/Allgemein/articles/-/rss.xml";
	private final static String chip = "http://www.chip.de/rss/rss_spiele.xml";
	private final static String gamepro = "http://www.gamepro.de/rss/gpnews.rss";
	private final static String gamestar = "http://www.gamestar.de/news/rss/news.rss";
	private final static String giga = "http://www.giga.de/games/feed/";
	private final static String golem = "https://rss.golem.de/rss.php?tp=games&feed=RSS2.0";
	private final static String ign = "http://de.ign.com/news.xml";

	public static void main(String[] args) throws ClassCastException, ClassNotFoundException, IOException {
		NERDemo ner = new NERDemo();
		RSSWriter writer = new RSSWriter();
		LuceneWriter luceneWriter = LuceneWriter.getInstance();

		RSSParser parser = new RSSParser(fourplayers, "4PlayersXSD");
		List<Message> list = parser.readFeed();
		buildArchive(ner, writer, list, luceneWriter);

		parser = new RSSParser(chip, "ChipXSD");
		list = parser.readFeed();
		buildArchive(ner, writer, list, luceneWriter);

		parser = new RSSParser(gamepro, "GameproXSD");
		list = parser.readFeed();
		buildArchive(ner, writer, list, luceneWriter);

		parser = new RSSParser(gamestar, "GamestarXSD");
		list = parser.readFeed();
		buildArchive(ner, writer, list, luceneWriter);

		parser = new RSSParser(giga, "GigaXSD");
		list = parser.readFeed();
		buildArchive(ner, writer, list, luceneWriter);

		parser = new RSSParser(golem, "GolemXSD");
		list = parser.readFeed();
		buildArchive(ner, writer, list, luceneWriter);

		parser = new RSSParser(ign, "IgnXSD");
		list = parser.readFeed();
		buildArchive(ner, writer, list, luceneWriter);

	}

	private static void buildArchive(NERDemo ner, RSSWriter writer, List<Message> list, LuceneWriter luceneWriter)
			throws ClassNotFoundException, IOException {
		for (Message message : list) {

			File file = new File(message.createFilename());
			if (!file.exists()) {
				message.setExtractedText(Boilerpipe.useBoilerpipe(message.getGuid()));
				ner.clearSets();
				ner.fillSets(message.getExtractedText());
				luceneWriter.createDocIndex(message.getTitle(), message.getExtractedText(), message.getPubDate(),
						message.getGuid(), message.getOrganisationen(), message.getPersonen());
				writer.write(message);
			}

		}
	}

}
