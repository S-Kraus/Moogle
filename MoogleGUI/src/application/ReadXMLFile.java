package application;

import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;


public class ReadXMLFile {

	private static ReadXMLFile instance;
	static String descr;

	public ReadXMLFile() {
		setInstance(this);
	}

	public static ReadXMLFile getInstance() {
		return instance;
	}

	public static void setInstance(ReadXMLFile instance) {
		ReadXMLFile.instance = instance;
	}

	public static String showXmlContent(String path) throws JDOMException, IOException {

		try {
			SAXBuilder jdomBuilder = new SAXBuilder();
			Document jdomDocument = jdomBuilder.build(path);
			Element rss = jdomDocument.getRootElement();
			descr = rss.getChildText("description");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return descr.toString();
	}
}
