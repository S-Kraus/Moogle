package crawler.read;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import crawler.model.fourplayers.FeedMessage;

public class XMLReader {
	
	public static FeedMessage readXML(File file) throws JAXBException {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(FeedMessage.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		FeedMessage feed = (FeedMessage) unmarshaller.unmarshal(file);
		
		return feed;
	}

}
