package xmlparsing;

/**
 *
 * @author Naomi
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class DOMDisplayer extends JPanel implements ActionListener
{
   private JButton openButton, saveButton;
   private TreePanel treePanel;

   public DOMDisplayer()
   {  super(new BorderLayout());
      // prepare the treePanel with no initial document
      treePanel = new TreePanel();
      add(treePanel, BorderLayout.CENTER);
      // prepare a panel for the two buttons
      openButton = new JButton("Open file");
      saveButton = new JButton("Save file");
      openButton.addActionListener(this);
      saveButton.addActionListener(this);
      JPanel buttonPanel = new JPanel();
      buttonPanel.add(openButton);
      buttonPanel.add(saveButton);
      add(buttonPanel, BorderLayout.SOUTH);
   }

   public void actionPerformed(ActionEvent e)
   {  Object source = e.getSource();
      if (source == openButton)
      {  // create file chooser GUI that defaults to current directory
         JFileChooser chooser = new JFileChooser(new File("."));
         int status = chooser.showOpenDialog(this);
         if (status == JFileChooser.APPROVE_OPTION)
         {  File fileToOpen = chooser.getSelectedFile();
            FileInputStream fis = null;
            try
            {  fis = new FileInputStream(fileToOpen);
               // remove the current treePanel from this panel
               remove(treePanel);
               // prepare a new treePanel
               treePanel = new TreePanel(fis);
               fis.close();
               // add the new treePanel to this panel
               add(treePanel);
               // validate this panel since its contents have changed
               validate();
            }
            catch (IOException ioe)
            {  System.out.println("IO exception:"+ioe);
            }
         }
      }
      else if (source == saveButton)
      {  // create file chooser GUI that defaults to current directory
         JFileChooser chooser = new JFileChooser(new File("."));
         int status = chooser.showSaveDialog(this);
         if (status == JFileChooser.APPROVE_OPTION)
         {  File fileToSave = chooser.getSelectedFile();
            try
            {  FileOutputStream fos = new FileOutputStream(fileToSave);
               treePanel.writeXMLToStream(fos);
               fos.close();
            }
            catch (IOException ioe)
            {  System.out.println("IO exception:"+ioe);
            }
            catch (TransformerConfigurationException tce)
            {  System.out.println("Can't get transformer:"+tce);
            }
            catch (TransformerException te)
            {  System.out.println("Exception transforming XML:"+te);
            }
         }
      }
   }

   public static void main(String[] args)
   {  JFrame frame = new JFrame("DOM Displayer");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(new DOMDisplayer());
      frame.pack();
      // position the frame in the middle of the screen
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension screenDimension = tk.getScreenSize();
      Dimension frameDimension = frame.getSize();
      frame.setLocation((screenDimension.width-frameDimension.width)/2,
         (screenDimension.height-frameDimension.height)/2);
      frame.setVisible(true);
   }

   // An inner class that displays an XML document as a tree in a panel
   private class TreePanel extends JPanel
   {
      private Document document;
      private static final String JAXP_SCHEMA_LANGUAGE
         = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
      private static final String W3C_XML_SCHEMA
         = "http://www.w3.org/2001/XMLSchema";

      // default constructor for no XML document
      public TreePanel()
      {  this(null);
      }

      // constructor that accepts an input stream holding XML document
      public TreePanel(InputStream in)
      {  super(new BorderLayout());
         setPreferredSize(new Dimension(500,350));
         if (in==null)
         {  document = null;
            JLabel label = new JLabel("Choose an XML file to open",
               SwingConstants.CENTER);
            add(label, BorderLayout.CENTER);
         }
         else // create a tree from the input stream
         {  try
            {  // create a validating DOM document builder
               // using the default parser
               DocumentBuilderFactory builderFactory
                  = DocumentBuilderFactory.newInstance();
               builderFactory.setNamespaceAware(true);
               builderFactory.setValidating(true);
               builderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE,
                  W3C_XML_SCHEMA);
               DocumentBuilder builder
                  = builderFactory.newDocumentBuilder();
               // parse the input stream
               document = builder.parse(in);
               document.getDocumentElement().normalize();
               // prepare a JTree UI from the document
               Node rootXMLNode = document.getDocumentElement();
               DefaultMutableTreeNode rootTreeNode = new
                  DefaultMutableTreeNode(getNodeLabel(rootXMLNode));
               addElementChildrenToTree(rootXMLNode, rootTreeNode);
               JTree tree = new JTree(rootTreeNode);
               add(new JScrollPane(tree), BorderLayout.CENTER);
            }
            catch (FactoryConfigurationError e)
            {  JLabel label = new JLabel("Can't get factory:"+e,
                  SwingConstants.CENTER);
               add(label, BorderLayout.CENTER);
            }
            catch (ParserConfigurationException e)
            {  JLabel label = new JLabel("Can't get builder:"+e,
                  SwingConstants.CENTER);
               add(label, BorderLayout.CENTER);
            }
            catch (SAXException e)
            {  JLabel label=new JLabel("Exception parsing document:"+e,
                  SwingConstants.CENTER);
               add(label, BorderLayout.CENTER);
            }
            catch (IOException e)
            {  JLabel label = new JLabel("IO exception:"+e,
                  SwingConstants.CENTER);
               add(label, BorderLayout.CENTER);
            }
         }
      }

      public void writeXMLToStream(OutputStream os)
         throws TransformerConfigurationException, TransformerException
      {  if (document != null)
         {  TransformerFactory transFactory
               = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            transformer.transform(new DOMSource(document),
               new StreamResult(os));
         }
      }

      // recursive method that adds one new tree node to the
      // parentTreeNode for each child element of parentXMLNode
      private void addElementChildrenToTree(Node parentXMLNode,
         DefaultMutableTreeNode parentTreeNode)
      {  NodeList childNodes = parentXMLNode.getChildNodes();
         int numChildNodes = childNodes.getLength();
         for (int i=0; i<numChildNodes; i++)
         {  Node xmlNode = childNodes.item(i);
            String label;
            if (xmlNode instanceof Text || xmlNode instanceof Comment)
               // just give the text or comment trimmed of whitespace
               label = xmlNode.getNodeValue().trim();
            else
               label = getNodeLabel(xmlNode);
            if (label!=null && label.length()>0)
            {  // add this label as a tree node
               DefaultMutableTreeNode treeNode
                  = new DefaultMutableTreeNode(label);
               parentTreeNode.add(treeNode);
               addElementChildrenToTree(xmlNode, treeNode);
            }
         }
      }

      // helper method that prepares a string description of an xmlNode
      // consisting of the node name followed by its attributes
      // shown in brackets
      private String getNodeLabel(Node xmlNode)
      {  String label = xmlNode.getNodeName();
         NamedNodeMap nodeAttributes = xmlNode.getAttributes();
         if (nodeAttributes!=null && nodeAttributes.getLength()>0)
         {  label += " (";
            int numAttributes = nodeAttributes.getLength();
            for(int i=0; i<numAttributes; i++)
            {  Node attribute = nodeAttributes.item(i);
               if (i > 0)
                  label += ", ";
               label += attribute.getNodeName() +
                  "=" + attribute.getNodeValue();
            }
            label += ")";
         }
         return label;
      }
   }
}
