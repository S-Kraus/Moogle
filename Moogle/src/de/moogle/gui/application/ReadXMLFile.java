package de.moogle.gui.application;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

			SAXBuilder jdomBuilder = new SAXBuilder();
			Document jdomDocument = jdomBuilder.build(path);
			Element rss = jdomDocument.getRootElement();
			descr = rss.getChildText("description");
			
			Pattern pattern = Pattern.compile("(<).*?(>)");
			Matcher matcher = pattern.matcher(descr);
			descr = matcher.replaceAll("");
			
			Pattern pattern2 = Pattern.compile("&amp;");
			Matcher matcher2 = pattern2.matcher(descr);
			descr = matcher2.replaceAll("&");
			
			Pattern pattern3 = Pattern.compile("&quot;");
			Matcher matcher3 = pattern3.matcher(descr);
			descr = matcher3.replaceAll("\"");
			
			return descr;
	}
}
