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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

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
            
          
        //helper method that prepares a string description of an XMLNode
        //consisting of the node name followed by its attributes
        //shown in brackets
        private String getNodeLabel(Node xmlNode)
        {
            String label = xmlNode.getNodeName();
            NamedNodeMap nodeAttributes = xmlNode.getAttributes();
            
            if(nodeAttributes!=null && nodeAttributes.getLength()>0)
            {
                
                
                
            }
            return label;
        }  
    }
}
