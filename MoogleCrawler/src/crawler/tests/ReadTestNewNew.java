package crawler.tests;

import java.io.IOException;
import java.util.List;

import crawler.model.Message;
import crawler.read.Boilerpipe;
import crawler.read.RSSParserALL;
import crawler.write.RSSWriterNew;
import io.LuceneWriter;
import ner.NERDemo;

public class ReadTestNewNew extends Thread {
	// public class ReadTestNewNew {

	private final static String fourplayers = "http://feeds.4players.de/Allgemein/articles/-/rss.xml";
	private final static String chip = "http://www.chip.de/rss/rss_spiele.xml";
	private final static String gamepro = "http://www.gamepro.de/rss/gpnews.rss";
	private final static String gamestar = "http://www.gamestar.de/news/rss/news.rss";
	private final static String giga = "http://www.giga.de/games/feed/";
	private final static String golem = "https://rss.golem.de/rss.php?tp=games&feed=RSS2.0";
	private final static String ign = "http://de.ign.com/news.xml";

	public void run() {
		// public static void main(String[] args) throws IOException,
		// ClassCastException, ClassNotFoundException {

		ReadTestNewNew crawler = new ReadTestNewNew();

		while (isAlive()) {
			NERDemo ner;
			try {
				ner = NERDemo.getInstance();
				LuceneWriter luceneWriter = LuceneWriter.getInstance();

				RSSParserALL parser = new RSSParserALL(fourplayers, "4PlayersXSD");
				List<Message> list = parser.readFeed();
				crawler.buildArchive(ner, list, luceneWriter);

				parser = new RSSParserALL(chip, "ChipXSD");
				list = parser.readFeed();
				crawler.buildArchive(ner, list, luceneWriter);

				parser = new RSSParserALL(gamepro, "GameproXSD");
				list = parser.readFeed();
				crawler.buildArchive(ner, list, luceneWriter);

				parser = new RSSParserALL(gamestar, "GamestarXSD");
				list = parser.readFeed();
				crawler.buildArchive(ner, list, luceneWriter);

				parser = new RSSParserALL(giga, "GigaXSD");
				list = parser.readFeed();
				crawler.buildArchive(ner, list, luceneWriter);

				parser = new RSSParserALL(golem, "GolemXSD");
				list = parser.readFeed();
				crawler.buildArchive(ner, list, luceneWriter);

				parser = new RSSParserALL(ign, "IgnXSD");
				list = parser.readFeed();
				crawler.buildArchive(ner, list, luceneWriter);
			} catch (ClassCastException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void buildArchive(NERDemo ner, List<Message> list, LuceneWriter luceneWriter)
			throws ClassNotFoundException, IOException {

		RSSWriterNew writer = new RSSWriterNew();
		for (Message message : list) {
			String path = writer.write(message);
			message.setPath(path);
			message.setExtractedText(Boilerpipe.useBoilerpipe(message.getGuid()));
			ner.clearSets();
			ner.fillSets(message.getExtractedText());
			luceneWriter.createDocIndex(message.getTitle(), message.getExtractedText(), message.getPubDate(),
					message.getGuid(), message.getPath(), message.getOrganisationen(), message.getPersonen());
		}
	}
}
