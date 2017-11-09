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
	
	

	public static void main(String[] args) throws JAXBException {
		
		RSSParser parser = new RSSParser("http://feeds.4players.de/Allgemein/articles/-/rss.xml","ChipXSD");
		List<FeedMessage> list = parser.readFeed();
		
		RSSWriter writer = new RSSWriter();
		
		for(int i = 0; i < list.size(); i++) {
			String id = Integer.toString(i);
			writer.write(list.get(i), id);
		}

		
	}
}
