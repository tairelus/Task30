package bjs.task30;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by U-1 on 17.01.2016.
 */
public class ComputersStoreLoader extends DefaultHandler {
    /**Object which presents hierarchy for whole XML document*/
    private ComputersStore computersStore;

    /**Name of the current tag*/
    private String currentElement;
    /**Object which presents hierarchy for catalog tag*/
    private ComputersCatalog computersCatalog;
    /**Object which presents hierarchy for computer tag*/
    private ComputerType computerType;
    /**Indicated that current tag is a root of the document*/
    private boolean isRootTag;

    public ComputersStoreLoader (ComputersStore computersStore) {
        this.computersStore = computersStore;
        isRootTag = false;
    }

    /**
     * Parses XML document
     * @param xmlFile Path to the XML file
     * @return True if successfully parsed
     */
    public boolean parse(String xmlFile) {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(xmlFile, this);
        }
        catch (Exception e ) {
            System.out.println("Failed to parse XML. " + e);
            return false;
        }

        return true;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parse XML...");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Stop parse XML...");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;

        if (currentElement.equalsIgnoreCase("computerstore")) {
            isRootTag = true;
        }

        if (currentElement.equalsIgnoreCase("catalogcomputers")) {
            if (!isRootTag) {
                throw new SAXException("\"catalogcomputers\" tag not expected here.");
            }

            computersCatalog = new ComputersCatalog(Integer.parseInt(attributes.getValue("id")));
        }

        if (currentElement.equalsIgnoreCase("computer")) {
            if (computersCatalog == null) {
                throw new SAXException("\"computer\" tag not expected here.");
            }

            computerType = new ComputerType(Integer.parseInt(attributes.getValue("id")));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("computerstore")) {
            isRootTag = false;
        }

        if (qName.equalsIgnoreCase("catalogcomputers")) {
            computersStore.setCatalog(computersCatalog);
            computersCatalog = null;
        }

        if (qName.equalsIgnoreCase("computer")) {
            computersCatalog.setComputerType(computerType);
            computerType = null;
        }

        currentElement = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentElement.equalsIgnoreCase("title")) {
            if (computerType == null) {
                throw new SAXException("\"title\" tag not expected here.");
            }

            computerType.setTitle(new String(ch, start, length));
        }

        if (currentElement.equalsIgnoreCase("type")) {
            if (computerType == null) {
                throw new SAXException("\"type\" tag not expected here.");
            }

            computerType.setType(new String(ch, start, length));
        }

        if (currentElement.equalsIgnoreCase("amount")) {
            if (computerType == null) {
                throw new SAXException("\"amount\" tag not expected here.");
            }

            computerType.setAmount(Integer.parseInt(new String(ch, start, length)));
        }
    }
}
