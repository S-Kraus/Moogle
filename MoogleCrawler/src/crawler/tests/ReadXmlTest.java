package crawler.tests;

import java.io.File;
import java.io.FilenameFilter;

import javax.xml.bind.JAXBException;

import crawler.model.Message;
import crawler.read.XMLReader;

public class ReadXmlTest {
	public static void main(String[] args) throws JAXBException {
		ClassLoader cloader = Thread.currentThread().getContextClassLoader();
		File file = new File(cloader.getResource("").getPath());
		File[] fileArray = file.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.startsWith("RSS");
			}
		});
		for(File f : fileArray) {
			Message feed = XMLReader.readXML(f);
			System.out.println(feed.getTitle());
		}
	}

}
