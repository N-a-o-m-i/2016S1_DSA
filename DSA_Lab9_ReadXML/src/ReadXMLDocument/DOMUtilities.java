package ReadXMLDocument;

/**
   Some utility methods for extracting information from a DOM tree
   @author Andrew Ensor
*/
import java.util.ArrayList;
import java.util.Collection;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class DOMUtilities
{
   // This method returns all the child Nodes of the parentNode
   // that has a given NodeName (case insensitive)
   public static Collection<Node> getAllChildNodes(Node parentNode, 
      String name)
   {  ArrayList<Node> nodeList = new ArrayList<Node>();
      NodeList childNodes = parentNode.getChildNodes();
      if (name != null)
         for (int i=0; i<childNodes.getLength(); i++)
         {  Node childNode = childNodes.item(i);
            if (name.equalsIgnoreCase(childNode.getNodeName()))
               nodeList.add(childNode);
         }
      return nodeList;
   }
   
   // This method returns the attribute of the parentNode as a String
   // that has a given NodeName (case sensitive!) or null if no such
   // attribute is found
   public static String getAttributeString(Node parentNode, String name)
   {  String attribute = null;
      if ((parentNode != null) && (name != null))
      {  NamedNodeMap attributeNodes = parentNode.getAttributes();
         if (attributeNodes != null)
         {  Node attributeNode = attributeNodes.getNamedItem(name);
            if (attributeNode != null)
               attribute = attributeNode.getNodeValue();
         }
      }
      return attribute;
   }
   
   // This method returns the text content of a node which should
   // occur as Text child nodes of the node that have name "#text"
   // Note there should actually only be a single text node if DOM
   // tree has been normalized
   public static String getTextContent(Node node)
   {  String textContent = "";
      if (node != null)
      {  NodeList childNodes = node.getChildNodes();
         for(int i=0; i<childNodes.getLength(); i++)
         {  Node childNode = childNodes.item(i);
            if (childNode instanceof Text)
               textContent += childNode.getNodeValue();
         }
      }
      return textContent;
   }
}