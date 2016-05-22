/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReadXMLDocument;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.w3c.dom.Document;

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
            
            
        }
        
        
        
    }
    
    
    
}
