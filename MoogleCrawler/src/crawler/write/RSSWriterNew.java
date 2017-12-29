package crawler.write;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import crawler.model.Message;

public class RSSWriterNew {
	
	JAXBContext jContext;
	Marshaller marshaller;
	
	public RSSWriterNew() {
		try {
			jContext = JAXBContext.newInstance(Message.class);
			marshaller = jContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public String write(Message message) {

		String fileName = message.createFilename();
		File file = new File(fileName);
		file.getParentFile().mkdirs();
		
		try {
			marshaller.marshal(message, file);
			return file.getAbsolutePath();
		} catch (JAXBException e) {
			e.printStackTrace();
			return "";
		}
	}
}
