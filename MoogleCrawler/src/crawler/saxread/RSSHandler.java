package crawler.saxread;

import javax.xml.stream.events.Attribute;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RSSHandler extends DefaultHandler{
	
	@Override
	public void startDocument() {
		System.out.println("Anfang");
	}
	
	@Override
	public void endDocument() {
		System.out.println("Ende");
	}
	
	@Override
	public void startElement(String uri, String localName, 
            String qName, Attributes attributes) throws SAXException {
//		System.out.println("URI: " + uri);
//		System.out.println("localName: " + localName);
		System.out.println("qName: " + qName);
		for(int i = 0; i < attributes.getLength(); i++) {
			System.out.printf( "Attribut no. %d: %s = %s%n", i,attributes.getQName(i),attributes.getValue(i));
			
		}
}
	@Override
    public void characters(char ch[], int start, int length) 
            throws SAXException { 
	    System.out.println( "Characters:" );

	    for ( int i = start; i < (start + length); i++ )
	      System.out.printf( "%1$c", (int) ch[i] );

	    System.out.println();
}  
	@Override
    public void endElement(String uri, String localName,
          String qName) throws SAXException {
}

}
