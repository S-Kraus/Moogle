package crawler.read;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import crawler.model.Message;

public class XMLReader {
	
	public static Message readXML(File file) throws JAXBException {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Message feed = (Message) unmarshaller.unmarshal(file);
		
		return feed;
	}

}
