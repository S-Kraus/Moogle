package de.moogle.crawler;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


public class XMLReader {
	
	public static Message readXML(String headTitle,String title, String pubDate) throws JAXBException {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		
		Message message = new Message();
		message.setTitle(title);
		message.setHeadTitle(headTitle);
		message.setPubDate(pubDate);
		
		String fileName = message.createFilename();

		File file = new File(fileName);
		Message feed = (Message) unmarshaller.unmarshal(file);
		
		return feed;
	}

}
