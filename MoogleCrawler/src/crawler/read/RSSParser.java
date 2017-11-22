package crawler.read;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import crawler.model.fourplayers.FeedMessage;




public class RSSParser {
	
	final URL url;
	final File xsdFile;
	final String suffix = ".xml";
	
	public RSSParser(String feedUrl, String xsdFileName) {
		try{
			
			this.url = new URL(feedUrl);
			ClassLoader cloader = Thread.currentThread().getContextClassLoader();
			this.xsdFile = new File(cloader.getResource(xsdFileName.concat(suffix)).getFile());
		} catch( MalformedURLException e){
			throw new RuntimeException(e);
		}
	}
	
	public List<FeedMessage> readFeed() {
		List<FeedMessage> list = null;
		try {
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			
			Schema schema = schemaFactory.newSchema(this.xsdFile);
			SAXParserFactory saxFactory = SAXParserFactory.newInstance();
			saxFactory.setSchema(schema);
			SAXParser parser = saxFactory.newSAXParser();
			InputStream in = read();
			RSSHandler2 handler = new RSSHandler2();
			parser.parse(in, handler);
			list = handler.getItems();

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	private InputStream read() {
		
		try {
			return url.openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
