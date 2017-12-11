package de.moogle.main;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		
		/*
		 * 1. Ruf eine Seite auf und erzeuge Liste<Message>
		 * 				RSSParser parser = new RSSParser(feedUrl, xsdFileName);
						List<Message> list = parser.readFeed();
		 * 2. Überprüfe, ob Message schon exisitert.
		 * 				File file = new File(message.createFilename());
						if( !file.exists())
		 * 3. Boilerpipe
		 * 				message.setExtractedText(Boilerpipe.useBoilerpipe(message.getGuid()));
		 * 4. NER
		 * 				NERDemo ner = new NERDemo();
		 * 				ner.clearSets();
		 *				ner.fillSets(message.getExtractedText());
		 * 5. Lucene
		 * 				LuceneWriter.createDocIndex(String title, String content, String date, String link);
		 * 6. Writer
		 * 				RSSWriter writer = new RSSWriter();
		 * 				writer.write(message);
		 * 7. Wieder 1. mit neuer Seite
		 */

	}

}
