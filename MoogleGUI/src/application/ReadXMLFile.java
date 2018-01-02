package application;

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
			Pattern pattern = Pattern.compile("(<).*(>)");
			descr = rss.getChildText("description");
			Matcher matcher = pattern.matcher(descr);
			if (matcher.find())
			{
			    descr = descr.replaceAll(matcher.group(0), "");
			    descr = descr.replaceAll("&quot;", "\"");
			    descr = descr.replaceAll("&amp;", "&");
			}
		return descr;
	}
}
