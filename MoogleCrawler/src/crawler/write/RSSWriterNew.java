package crawler.write;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import crawler.model.Message;

public class RSSWriterNew {
	
	final static String FOURPLAYERS = "4Players.de";
	final static String CHIP = "![CDATA[CHIP Online Spiele]]";
	final static String GAMEPRO = "GamePro";
	final static String GAMESTAR = "GameStar";
	final static String GIGA = "GIGA GAMES";
	final static String GOLEM = "Golem.de - Games";
	final static String IGN = "IGN Deutschland";
	
	final static String dir = "output/";
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
	
	
	// Übergabe : Message, 
	public void write(Message message) {
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append(dir);
		switch(message.getHeadTitle()){
			case(FOURPLAYERS):
				sbuilder.append("4Players/");
				break;
			case(CHIP):
				sbuilder.append("chip/");
				break;
			case(GAMEPRO):
				sbuilder.append("gamepro/");
				break;
			case(GAMESTAR):
				sbuilder.append("gamestar/");
				break;
			case(GIGA):
				sbuilder.append("giga/");
				break;
			case(GOLEM):
				sbuilder.append("golem/");
				break;
			case(IGN):
				sbuilder.append("ign/");
				break;
			
				
		}
		sbuilder.append("RSS");
		sbuilder.append(Math.abs(message.getTitle().hashCode()));
		sbuilder.append(suffix);
		String fileName = sbuilder.toString();
		System.out.println(fileName);
//		File file = new File(fileName);
//		
//		try {
//			marshaller.marshal(message, file);
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
