package de.moogle.crawler;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import com.sun.media.sound.FFT;

public class RSSParser {

	private static final String XML_SUFFIX = ".xml";
	private final URL url;
	private final Source xsdSource;

	public RSSParser(String feedUrl, String xsdFileName) {
		try {

			this.url = new URL(feedUrl);
			ClassLoader cloader = Thread.currentThread().getContextClassLoader();
			this.xsdSource = new StreamSource(cloader.getResourceAsStream(xsdFileName.concat(XML_SUFFIX)));
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Message> readFeed() {
		List<Message> list = null;
		try {
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			Schema schema = schemaFactory.newSchema(this.xsdSource);
			SAXParserFactory saxFactory = SAXParserFactory.newInstance();
			saxFactory.setSchema(schema);
			SAXParser parser = saxFactory.newSAXParser();
			InputStream in = read();
			if(in != null) {
				RSSHandler handler = new RSSHandler();
				parser.parse(in, handler);
				list = handler.getItems();
				in.close();
			}


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
			System.out.println("Seite "+ url + " nicht erreichbar");
			return null;
		}
	}

}
