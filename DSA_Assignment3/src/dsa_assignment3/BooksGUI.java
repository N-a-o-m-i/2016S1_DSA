package dsa_assignment3;

import java.awt.BorderLayout;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Naomi
 */
public class BooksGUI extends JPanel implements ActionListener, KeyListener, ListSelectionListener
{
    public final static int PANEL_WIDTH = 1000;
    public final static int PANEL_HEIGHT = 1000;
    
    
    private static JFrame frame;
    private JMenuBar menuBar;
    private JPanel northPanel;
    private JLabel labelNorth;
    private JTextArea textAreaNorth;
    private JSplitPane splitPane;
    private JMenu file;
    private JMenu other;
    private JMenuItem open;
    private JMenuItem save;
    private JMenuItem exit;
    private BookSet bookSet;
    private JList list;
    
    public BooksGUI()
    {
        super(new BorderLayout());
        frame = new JFrame("BookGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        menuBar = new JMenuBar();
        northPanel = new JPanel();
        labelNorth = new JLabel("DESCRIPTION");
        textAreaNorth = new JTextArea();
        textAreaNorth.setLineWrap(true);
        textAreaNorth.setWrapStyleWord(true);
        textAreaNorth.add(labelNorth);
        list = new JList<String>();
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(5);
        list.addListSelectionListener(this);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,list,new JPanel());
        splitPane.setVisible(true);
        northPanel.add(labelNorth);
        northPanel.add(textAreaNorth);
        
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(splitPane,BorderLayout.CENTER);
        frame.getContentPane().add(northPanel,BorderLayout.NORTH);
       
        //set menu
        file = new JMenu("File");
        other = new JMenu("Other");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        exit = new JMenuItem("Exit");
        open.addActionListener(this);
        
        
        menuBar.add(file);
        menuBar.add(other);
        file.add(open);
        file.add(save);
        file.add(exit);
       
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object source = e.getSource();
        if(source==open)
        {
            //create file chooser GUI that defaults to current directory
            JFileChooser chooser = new JFileChooser(new File("."));
            int status = chooser.showOpenDialog(this);
            if(status == JFileChooser.APPROVE_OPTION)
            {
                File fileToOpen = chooser.getSelectedFile();
                FileInputStream fis = null;
                try
                {
                    fis = new FileInputStream(fileToOpen);
                    bookSet = new BookSet(fis);
                    list.setModel(bookSet.getISBNListModel());
                    splitPane.setLeftComponent(list);
                    fis.close();
                    
                    
                    
                }
                catch(IOException ioe)
                {
                    System.out.println("IO exception: " + ioe);
                } 
                catch (ParserConfigurationException ex) 
                {
                    Logger.getLogger(BooksGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (SAXException ex) 
                {
                    Logger.getLogger(BooksGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   
        }
        
        
    }

    
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public static void main(String[] args)
    {
        BooksGUI GUI = new BooksGUI();
        
        
    }

    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        if(!list.isSelectionEmpty())
        {    
            splitPane.setRightComponent((bookSet.getBook(list.getSelectedValue().toString()).preparePanel()));
        }
	
        
        
    }
}
