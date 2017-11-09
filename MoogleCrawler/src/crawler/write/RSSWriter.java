package crawler.write;

/*
 * Schreibt für die übergeben FeedMesssage eine XML-Datei.
 * Enthält bislang aber nur die Daten der FeedMessage und
 * nicht die Daten vom Feed selbst.
 */

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import crawler.model.fourplayers.FeedMessage;


public class RSSWriter {
	
	final static String dir = "output/4players_";
	final static String suffix = ".xml";
	
	JAXBContext jContext;
	Marshaller marshaller;
	
	public RSSWriter() {
		try {
			jContext = JAXBContext.newInstance(FeedMessage.class);
			marshaller = jContext.createMarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void write(FeedMessage message, String id) {
		String fileName = dir.concat(id).concat(suffix);
		File file = new File(fileName);
		
		try {
			marshaller.marshal(message, file);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
