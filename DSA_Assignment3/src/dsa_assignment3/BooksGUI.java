package dsa_assignment3;

import java.awt.BorderLayout;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

/**
 *
 * @author Naomi
 */
public class BooksGUI extends JPanel implements ActionListener, KeyListener
{
    public final static int PANEL_WIDTH = 1000;
    public final static int PANEL_HEIGHT = 1000;
    
    
    private static JFrame frame;

    
    
    public BooksGUI()
    {
        super(new BorderLayout());
        frame = new JFrame("BookGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenuBar menuBar = new JMenuBar();
        JPanel northPanel = new JPanel();
        JLabel labelNorth = new JLabel("DESCRIPTION");
        JTextArea textAreaNorth = new JTextArea();
        textAreaNorth.setLineWrap(true);
        textAreaNorth.setWrapStyleWord(true);
        textAreaNorth.add(labelNorth);
        JSplitPane splitPane = new JSplitPane();
        
        northPanel.add(labelNorth);
        northPanel.add(textAreaNorth);
        
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(northPanel,BorderLayout.NORTH);
        frame.getContentPane().add(splitPane,BorderLayout.CENTER);
        //set menu
        JMenu file = new JMenu("File");
        JMenu other = new JMenu("Other");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem exit = new JMenuItem("Exit");
        
        menuBar.add(file);
        menuBar.add(other);
        file.add(open);
        file.add(save);
        file.add(exit);
        
        frame.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        frame.setVisible(true);
        
    }
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
        
        
        
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
}
