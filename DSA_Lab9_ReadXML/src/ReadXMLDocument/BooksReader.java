package ReadXMLDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author Naomi
 */
public class BooksReader 
{
    private static String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    private static String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    private static String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
    private static String schema = "books.xsd";
    
    public static void main(String[] args) 
    {
        try 
        {
            printXMLTree();
        }
        catch (SAXException | IOException | ParserConfigurationException e) 
        {
            e.printStackTrace();
        }
    }
    
    private static void printXMLTree() throws FileNotFoundException, ParserConfigurationException, SAXException, IOException
    {
        //Parse an XML document and build a normalized DOM tree
        FileInputStream input = new FileInputStream("ComputerBooks.xml");
        DocumentBuilder builder = getBuilder();
        Document document = builder.parse(input);
        document.getDocumentElement().normalize();
        
        //getting root node
        Node rootXMLNode = document.getDocumentElement();
        
        //printing out description
        Collection<Node> description = DOMUtilities.getAllChildNodes(rootXMLNode, "description");
        System.out.println("DESCRIPTION: " + DOMUtilities.getTextContent(description.iterator().next()).trim());
        
        //printing out books
        Collection<Node> bookCollection = DOMUtilities.getAllChildNodes(rootXMLNode, "book");
        Iterator<Node> bookIterator = bookCollection.iterator();
        
        while(bookIterator.hasNext())
        {
            Node book = bookIterator.next();
            //printing out book ISBN and title
            System.out.println("\nISBN: " + DOMUtilities.getAttributeString(book, "isbn"));
            Collection<Node> title = DOMUtilities.getAllChildNodes(book, "title");
            System.out.println("Title: " + DOMUtilities.getTextContent(title.iterator().next()));
            
            //printing out book authors
            Collection<Node> authors = DOMUtilities.getAllChildNodes(book, "author");
            Iterator<Node> authorIterator = authors.iterator();            
            /*
            for(Node n : authors)
            {
                System.out.println("Author: " + DOMUtilities.getTextContent(n));
            
            }
            */
            while(authorIterator.hasNext())
            {
                Node author = authorIterator.next();
                System.out.println("Author: " + DOMUtilities.getTextContent(author));
            }
        }
    }
    
    private static DocumentBuilder getBuilder() throws ParserConfigurationException
    {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setNamespaceAware(true);
        builderFactory.setValidating(true);
        builderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
        builderFactory.setAttribute(JAXP_SCHEMA_SOURCE, new File(schema));
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        return builder;
    }
    
}
