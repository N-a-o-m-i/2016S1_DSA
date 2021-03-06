package dsa_assignment3;

/**
 *
 * @author Naomi
 */
/******************************************************************************************
PROGRAM NAME:   ASSIGNMENT 3: BOOK SET PROGRAM
FILE:           BookSet.java
DESCRIPTION:    Holds a collection of books in a hash map with isbn codes as keys maintained
                in the order which books are added to the collection
*******************************************************************************************/
import javax.swing.ListModel;
import java.util.AbstractSet;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.Iterator;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.NamedNodeMap;
import javax.swing.AbstractListModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.InputStream;


public class BookSet extends AbstractSet
{
    private LinkedHashMap<String,Book> booksMap;
    private ISBNListModel isbnListModel;
    private String description;
    private Document document;
    private static final String JAXP_SCHEMA_LANGUAGE  = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    private static final String W3C_XML_SCHEMA  = "http://www.w3.org/2001/XMLSchema";
    
    //default constructor that inititialises LinkedHashMap and call to super class
    public BookSet()
    {
        super();
        //compete this
        booksMap = new LinkedHashMap<String, Book>();
        ArrayList<String> isbnData = new ArrayList<String>();
        description = "";
        
    }
    
    //constructor that will accept a Collection of Books
    public BookSet(Collection<? extends Book> books)
    {
        booksMap = new LinkedHashMap<String,Book>();
        Iterator<Book> iterator = (Iterator<Book>) books.iterator();
        ArrayList<String> isbnData = new ArrayList<String>();
        while(iterator.hasNext())
        {
            
            Book book = iterator.next();
            booksMap.put(book.getISBN(), book);
            isbnData.add(book.getISBN());
        }

        description = "";
    }
    
    //constructor that will accept a Document that holds XML information
    public BookSet(InputStream in) throws ParserConfigurationException, SAXException, IOException
    {
        //complete this metod by traversing the XML tree, start from the root node
        //and look for books
        booksMap = new LinkedHashMap<String, Book>();
        
        try
        {
            //create a validating DOM document builder
            //using the default parser
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setNamespaceAware(true);
            builderFactory.setValidating(true);
            builderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            //parse the input stream
            document = builder.parse(in);
            document.getDocumentElement().normalize();
            //prepare a JTree UI from the document
            Node rootXMLNode = document.getDocumentElement();
            
            //get description
            Collection<Node> descriptionCollection = getAllChildNodes(rootXMLNode, "description");
            description = getTextContent(descriptionCollection.iterator().next()).trim();

            //get books
            Collection<Node> bookCollection = getAllChildNodes(rootXMLNode, "book");
            Iterator<Node> bookIterator = bookCollection.iterator();

            while(bookIterator.hasNext())
            {
                Node bookNode = bookIterator.next();
                //get book's ISBN and title
                String isbn = getAttributeString(bookNode, "isbn");
                //get book's title
                Node titleNode = ((ArrayList<Node>) getAllChildNodes(bookNode, "title")).get(0);
                String title = titleNode.getTextContent();
                //get book's authors
                Collection<Node> authorsNode = getAllChildNodes(bookNode, "author");
                Iterator<Node> authorIterator = authorsNode.iterator();
                ArrayList<String> authors = new ArrayList<String>();
                while(authorIterator.hasNext())
                {
                    Node authorNode = authorIterator.next();
                    authors.add(authorNode.getTextContent());
                }
                //get book's publisher
                Node pubNode = ((ArrayList<Node>) getAllChildNodes(bookNode, "pub")).get(0);
                Publisher pub = null;
                if(pubNode != null)
                {
                    String pubName = getAttributeString(pubNode, "name");
                    String pubWebsite = null;
                    if(getAttributeString(pubNode, "website") != null)
                    {
                        pubWebsite = getAttributeString(pubNode, "website");
                    }   
                    pub = new Publisher(pubName, pubWebsite);
                }
                
                //get the cover
                String cover = getAttributeString(bookNode, "cover");
                Book.Cover newCover = Book.Cover.HARD;
                if(cover != null)
    			newCover = (cover.equalsIgnoreCase("soft")?Book.Cover.SOFT:Book.Cover.HARD);
                
                //get the edition
                String edition = getAttributeString(bookNode, "edition");
                int newEdition = 0;
                if(edition!=null)
                    newEdition = Integer.parseInt(edition);
                
                Book book = new Book(isbn, title, authors.iterator(), pub, newCover, newEdition);
                add(book);
            } 
        }
        catch (FactoryConfigurationError e)
        {
            System.out.println(e.getMessage());
        }
        catch (ParserConfigurationException e)
        {
            System.out.println(e.getMessage());
        }
        catch (SAXException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    //this method adds a Book to the hashmap if is doesn't already contain
    //that isbn number and also adds the isbn value to the isbnListModel
    public boolean add(Book book)
    {
        if(booksMap.containsKey(book.getISBN()))
            return false;
        booksMap.put(book.getISBN(), book);
        if(booksMap.size() == 1)
            isbnListModel = new ISBNListModel(booksMap.keySet());
        else
            isbnListModel.addISBN(book.getISBN());
        return true;
    }
    
    //this method removes a Book from the hashmap if it contains
    //that isbn number and also adds the isbn value to the isbnListModel
    public boolean remove(Book book)
    {
        for(int i=0; i<isbnListModel.getSize(); i++)
        {
            if(isbnListModel.getElementAt(i) == book.getISBN())
            {
                booksMap.remove(book.getISBN());
                return true;
            }
        }
        return false;
    }
    
    //returns an iterator to iterate through the list
    public Iterator<Book> iterator()
    {   
        //dont change
        return booksMap.values().iterator();
    }
    
    //returns the number of values in the hashmap
    public int size()
    {
        return booksMap.size();
    }
    
    //clears the hashmap
    public void clear()
    {
        booksMap.clear();
    }
    
    //returns the book from hashmap without removing it with
    //the neccessary isbn number
    public Book getBook(String isbn)
    {
        return booksMap.get(isbn);
    }
    
    //returns a ListModel with isbn values
    public ListModel getISBNListModel()
    {
        return isbnListModel;
    }
    
    //returns description of XML file
    public String getDescription()
    {
        return description;
    }
    
    //string representation of XML file
    public String toString()
    {   //dont change
        Iterator<Book> iterator = iterator();
        String data = "";
        while(iterator.hasNext())
        {
            Book book = iterator.next();
            data += book.toString()+"\n";
        }
        return data;
    }

    // This method returns all the child Nodes of the parentNode
    // that has a given NodeName (case insensitive)
    private Collection<Node> getAllChildNodes(Node parentNode, String name)
    {   //dont change
        ArrayList<Node> nodeList = new ArrayList<Node>();
        NodeList childNodes = parentNode.getChildNodes();
        if(name != null)
        {
            for (int i=0; i<childNodes.getLength(); i++)
            {
                Node childNode = childNodes.item(i);
                if(name.equalsIgnoreCase(childNode.getNodeName()))
                    nodeList.add(childNode);
            }
        }
        return nodeList;
    }
    
    // This method returns the attribute of the parentNode as a String
    // that has a given NodeName (case sensitive!) or null if no such
    // attribute is found
    private String getAttributeString(Node parentNode, String name)
    {  //dont change
        String attribute = null;
        if((parentNode != null) && (name != null))
        {
            NamedNodeMap attributeNodes = parentNode.getAttributes();
            if(attributeNodes != null)
            {
                Node attributeNode = attributeNodes.getNamedItem(name);
                if(attributeNode != null)
                    attribute = attributeNode.getNodeValue();
            }
        }
        return attribute;
    }
    
    // This method returns the text content of a node which should
    // occur as Text child nodes of the node that have name "#text"
    // Note there should actually only be a single text node if DOM
    // tree has been normalized
    private String getTextContent(Node node)
    {   //dont change
        String textContent = "";
        if(node != null)
        {
            NodeList childNodes = node.getChildNodes();
            for(int i=0; i<childNodes.getLength(); i++)
            {
                Node childNode = childNodes.item(i);
                if(childNode instanceof Text)
                    textContent += childNode.getNodeValue();
            }
        }
        return textContent;
    }
    
    //inner class that holds a ArrayList of strings that relate to
    //the isbn keys for the HashMap used for the Gui JList
    //DONT CHANGE THIS CLASS
    private class ISBNListModel extends AbstractListModel
    {
        private ArrayList<String> isbnList;

        //constructor initialises arrayList and sorts the collection out
        //in its natural order
        public ISBNListModel(Collection<String> isbnData)
        {
            super();
            isbnList = new ArrayList<String>();
            isbnList.addAll(isbnData);
            Collections.sort(isbnList);
        }
        
        //gets the element at the specified index
        public Object getElementAt(int index)
        {
            if(index < isbnList.size())
                return isbnList.get(index);
            else
                return null;
        }
        
        //gets the size of the arrayList
        public int getSize()
        {
            return isbnList.size();
        }
        
        //adds to the isbn to the arrayList and notifies
        //any listeners to it by fireIntervalAdded
        public boolean addISBN(String isbn)
        {
            int index = Collections.binarySearch(isbnList,isbn);
            index = (index * -1)-1;
            isbnList.add(index,isbn);
            fireIntervalAdded(this,index,index);
            return true;
        }
        
        //removes the isbn from the arrayList and notifies
        //any listeners to it by fireIntervalRemoved
        public boolean removeISBN(String isbn)
        {
            int index = isbnList.indexOf(isbn);
            isbnList.remove(isbn);
            fireIntervalRemoved(this,index,index);
            return true;
        }
    }
    
    //main method to test the program
    public static void main(String args[])
    {
        String file = "ComputerBooks.xml";
        System.out.println("==============BOOK SET===============");
        Collection<String> authors = new ArrayList<String>();
        authors.add("Seth Hall");
        authors.add("Bob Chen");

        Book book1 = new Book("1234","JAVA DEVELOPMENT",authors.iterator());
        Book book2 = new Book("1111","Lunch Time",authors.iterator());
        Collection<Book> coll = new ArrayList<Book>();
        coll.add(book1);
        coll.add(book2);
        BookSet bookSet = new BookSet(coll);
        
        Book book3 = new Book("2222");
        bookSet.add(book3);
        bookSet.remove(book2);
        
        System.out.println(bookSet.toString());
        
//        try
//        {
//            DocumentBuilderFactory builderFactory=DocumentBuilderFactory.newInstance();
//            builderFactory.setNamespaceAware(true);
//            builderFactory.setValidating(true);
//            builderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE,W3C_XML_SCHEMA);
//            DocumentBuilder builder = builderFactory.newDocumentBuilder();
//            // parse the input stream
//            Document document = builder.parse(file);
//            document.getDocumentElement().normalize();
//            
//            BookSet bookset = new BookSet(document);
//            System.out.println("=====Printing out ToString=====");
//            System.out.println(bookset.toString());
//        }
//        catch (FactoryConfigurationError e)
//        {
//            System.out.println(e.getMessage());
//        }
//        catch (ParserConfigurationException e)
//        {
//            System.out.println(e.getMessage());
//        }
//        catch (SAXException e)
//        {
//            System.out.println(e.getMessage());
//        }
//        catch (IOException e)
//        {
//            System.out.println(e.getMessage());
//        }
    }
}