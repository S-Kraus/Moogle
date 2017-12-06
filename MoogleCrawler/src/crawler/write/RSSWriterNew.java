package crawler.write;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import crawler.model.Message;

public class RSSWriterNew {
	final static String dir = "output/RSS";
	final static String suffix = ".xml";
	
	JAXBContext jContext;
	Marshaller marshaller;
	
	public RSSWriterNew() {
		try {
			jContext = JAXBContext.newInstance(Message.class);
			marshaller = jContext.createMarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void write(Message message, String id) {
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
