/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReadXMLDocument;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 *
 * @author Naomi
 */
public class DOMDisplayer extends JPanel implements ActionListener
{
    private JButton openButton;
    private JButton saveButton;
    
    public DOMDisplayer()
    {
        super(new BorderLayout());
        //prepare the treePanel with no initial document
        treePanel = new TreePanel();
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args)
    {
        
    }
    
    //An inner class that displays an XML document as a tree in a panel
    private class TreePanel extends JPanel
    {
        private Document document;
        private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
        private static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
        
        //default constructor for no XML document
        public TreePanel()
        {
            this(null);
        }
        
        public TreePanel(InputStream in)
        {
            super(new BorderLayout());
            setPreferredSize(new Dimension(500,350));
            if(in==null)
            {
                document = null;
                JLabel label = new JLabel("Choose an XML file to open", SwingConstants.CENTER);
                add(label, BorderLayout.CENTER);
            }
            else //create a tree from the input stream
            {
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
                    DefaultMutableTreeNode rootTreeNode = new DefaultMutableTreeNode(getNodeLabel(rootXMLNode));
                    
                    
                }
                catch (Exception e) 
                {
                    
                    
                    
                }
                
                
                
            }
        }
            
        public void writeXMLToStream(OutputStream os) throws TransformerConfigurationException, TransformerException
        {
            if(document!=null)
            {
                TransformerFactory transFactory = TransformerFactory.newInstance();
                Transformer transformer = transFactory.newTransformer();
                transformer.transform(new DOMSource(document), new StreamResult(os));
            }
        }
        
        //recursive method that adds one new tree node to the
        //parentTreeNode for each child element of parentXMLNode
        private void addElementChildrenToTree(Node parentXMLNode, DefaultMutableTreeNode parentTreeNode)
        {
            NodeList childNodes = parentXMLNode.getChildNodes();
            int numChildNodes = childNodes.getLength();
            for(int i=0; i<numChildNodes; i++)
            {
                Node xmlNode = childNodes.item(i);
                String label;
                if(xmlNode instanceof Text || xmlNode instanceof Comment)
                {
                    //just give the text or comment trimmed of whitespace
                    label = xmlNode.getNodeValue().trim();
                }
                else
                    label = getNodeLabel(xmlNode);
                
                if(label!=null && label.length()>0)
                {
                    //add this label as a tree node
                    DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(label);
                    parentTreeNode.add(treeNode);
                    addElementChildrenToTree(xmlNode, treeNode);
                }                
            }
        }
        
        //helper method that prepares a string description of an XMLNode
        //consisting of the node name followed by its attributes
        //shown in brackets
        private String getNodeLabel(Node xmlNode)
        {
            String label = xmlNode.getNodeName();
            NamedNodeMap nodeAttributes = xmlNode.getAttributes();
            
            if(nodeAttributes!=null && nodeAttributes.getLength()>0)
            {
                label+=" (";
                int numAttributes = nodeAttributes.getLength();
                for(int i=0; i<numAttributes; i++)
                {
                    Node attribute = nodeAttributes.item(i);
                    if(i > 0)
                        label += ", ";
                    label += attribute.getNodeName() + "=" + attribute.getNodeValue();
                }
                label += ")";
            }
            return label;
        }  
    }
}
