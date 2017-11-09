package crawler.tests;
/*
 * 
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * Beim Ausführen werden im Ordner output Dateien erzeugt
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 
 * 
 */
import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import crawler.model.fourplayers.FeedMessage;
import crawler.read.SAXRSSParser;

public class WriteTest {

	public static void main(String[] args) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(FeedMessage.class);
		Marshaller m = jc.createMarshaller();
		
		SAXRSSParser parser = new SAXRSSParser("http://feeds.4players.de/Allgemein/articles/-/rss.xml","ChipXSD");
		List<FeedMessage> list = parser.readFeed();
		final String dir = "output/4players_";
		final String suffix = ".xml";
		for(int i = 0; i < list.size(); i++) {
			String f = (dir.concat(Integer.toString(i))).concat(suffix);
			File file = new File(f);
			m.marshal(list.get(i), file);
			
		}

		
	}
}
