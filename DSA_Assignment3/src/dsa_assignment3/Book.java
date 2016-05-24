package dsa_assignment3;

/**
 *
 * @author Naomi
 */
/******************************************************************************************
PROGRAM NAME:   ASSIGNMENT 3: BOOK SET PROGRAM
FILE:           Book.java
DESCRIPTION:    Represents a Book and its data, can be displayed in a panel DO NOT EDIT
*******************************************************************************************/
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

public class Book
{
   public enum Cover{HARD, SOFT}; // enumerated constants (new to 1.5)
   private String isbn;
   private String title;
   private ArrayList<String> authorList;
   private Publisher publisher; // null if unknown
   private Cover cover; // either Cover.HARD or Cover.SOFT
   private int edition; // 0 if unknown
   private JPanel panel; // a panel for displaying and editing the Book

   /**
      Book constructor that creates a book with empty title, no authors,
      no publisher, HARD cover, and unknown edition.
   */
   public Book(String isbn)
   {  this(isbn, "", null);
   }

   /**
      Book constructor that creates a book with no publisher, HARD cover,
      and unknown edition.
   */
   public Book(String isbn, String title, Iterator<String> authors)
   {  this(isbn, title, authors, null, Cover.HARD, 0);
   }

   /**
      Book constructor that creates a book with all fields specified.
   */
   public Book(String isbn, String title, Iterator<String> authors,
      Publisher publisher, Cover cover, int edition)
   {  this.isbn = isbn;
      this.title = title;
      authorList = new ArrayList<String>();
      setAuthors(authors);
      this.publisher = publisher;
      this.cover = cover;
      this.edition = edition;
   }

   /**
      Returns the isbn code as a String. Note that there is no mutator method
      so the isbn cannot be altered once the book has been created.
   */
   public String getISBN()
   {  return isbn;
   }

   /**
      Returns the title of this book.
   */
   public String getTitle()
   {  return title;
   }

   /**
      Changes the title of this book.
   */
   public void setTitle(String title)
   {  this.title = title;
   }

   /**
      Returns an iterator of the author names for this book.
      Note that the remove method of the iterator throws an exception
      so the iterator cannot be used to modify the list of authors.
   */
   public Iterator<String> getAuthors()
   {  return authorList.iterator();
   }

   /**
      Sets the list of all author names for the book.
      Note that this replaces the previous list of authors.
   */
   public void setAuthors(Iterator<String> authors)
   {  authorList.clear();
      if (authors != null)
      {  while (authors.hasNext())
            authorList.add(authors.next());
      }
   }

   /**
      Returns whether or not the publisher of this book is known.
   */
   public boolean hasPublisher()
   {  return publisher != null;
   }

   /**
      Returns the publisher of this book, or null if unknown.
   */
   public Publisher getPublisher()
   {  return publisher;
   }

   /**
      Sets the publisher of this book.
   */
   public void setPublisher(Publisher publisher)
   {  this.publisher = publisher;
   }

   /**
      Returns the cover of this book as one of the enumerated type
      Book.Cover.HARD or Book.Cover.SOFT.
   */
   public Cover getCover()
   {  return cover;
   }

   /**
      Sets the cover of this book as one of the enumerated type
      Book.Cover.HARD or Book.Cover.SOFT.
   */
   public void setCover(Cover cover)
   {  this.cover = cover;
   }

   /**
      Returns whether or not the edition of this book is known.
   */
   public boolean hasEdition()
   {  return edition != 0;
   }

   /**
      Returns the edition of this book, or 0 if unknown.
   */
   public int getEdition()
   {  return edition;
   }

   /**
      Sets the edition of this book.
   */
   public void setEdition(int edition)
   {  this.edition = edition;
   }

   /**
      Returns a string representation of this book
      that lists all its fields.
   */
   public String toString()
   {  String output = "ISBN: " + isbn + "\n" + "Title: " + title + "\n";
      Iterator<String> iterator = authorList.iterator();
      output += "Authors: ";
      while(iterator.hasNext())
         output += iterator.next() + (iterator.hasNext() ? ", " : "\n");
      if (publisher == null)
         output += "No publisher\n";
      else
         output += "Publisher: " + publisher.toString() + "\n";
      output += "Cover: " + cover + "\n";
      if (edition < 1)
         output += "Edition unknown\n";
      else
         output += "Edition: " + edition + "\n";
      return output;
   }

   /**
      Returns a panel holding the attributes of the book that
      allows all fields except the isbn to be edited.
   */
   public JComponent preparePanel()
   {  if (panel == null)
      {  // prepare a new panel for this book
         panel = new BookPanel();
      }
      return panel;
   }

   /**
      Driver main method to demonstrate how to use the Book class
      and display the results in a GUI panel.
   */
   public static void main(String[] args)
   {  ArrayList<String> authors = new ArrayList<String>();
      authors.add("John Lewis");
      authors.add("Joseph Chase");
      Book javaSoftwareStructures = new Book("0-321-24584-9",
         "Java Software Structures", authors.iterator(),
         new Publisher("Addison Wesley", "http://www.aw-bc.com/"),
         Cover.SOFT, 2);
      System.out.println("Book created:\n" + javaSoftwareStructures);
      JFrame frame = new JFrame("Book Test");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(javaSoftwareStructures.preparePanel());
      frame.pack();
      // position the frame in the middle of the screen
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension screenDimension = tk.getScreenSize();
      Dimension frameDimension = frame.getSize();
      frame.setLocation((screenDimension.width-frameDimension.width)/2,
         (screenDimension.height-frameDimension.height)/2);
      frame.setVisible(true);
   }

   // inner class that prepares panel displaying and editing book info
   private class BookPanel extends JPanel
   {
      private JTextField titleField, pubNameField, pubWebSiteField;
      private JTextArea authorsArea;
      private JRadioButton hardCoverButton, softCoverButton;
      private JSpinner editionSpinner;

      public BookPanel()
      {  super();
         setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
         add(Box.createVerticalStrut(10));

         // prepare the isbn panel
         JPanel isbnPanel = new JPanel(new BorderLayout());
         isbnPanel.add(new JLabel("ISBN: " + isbn), BorderLayout.WEST);
         add(isbnPanel);
         add(Box.createVerticalStrut(10));

         // prepare title panel
         JPanel titlePanel = new JPanel(new BorderLayout());
         JPanel innerTitlePanel = new JPanel(new BorderLayout());
         innerTitlePanel.add(new JLabel("Title: "), BorderLayout.WEST);
         innerTitlePanel.add(titleField = new JTextField(title), BorderLayout.CENTER);
         titleField.getDocument().addDocumentListener(
            new TextChangeListener(titleField));
         // ensure innerTitlePanel appears at its preferred height
         titlePanel.add(innerTitlePanel, BorderLayout.NORTH);
         add(titlePanel);
         add(Box.createVerticalStrut(10));

         // prepare the authors panel
         JPanel authorsPanel = new JPanel(new BorderLayout());
         authorsPanel.add(new JLabel("Authors: "), BorderLayout.WEST);
         authorsPanel.add(new JScrollPane(authorsArea = new JTextArea()),
            BorderLayout.CENTER);
         Iterator<String> iterator = getAuthors();
         while (iterator.hasNext())
            authorsArea.append(iterator.next()+"\n");
         authorsArea.getDocument().addDocumentListener(
            new TextChangeListener(authorsArea));
         add(authorsPanel);
         add(Box.createVerticalStrut(10));

         // prepare the publisher panel
         JPanel publisherPanel = new JPanel(new GridLayout(1,2));
         JPanel leftPubPanel = new JPanel(new BorderLayout());
         JPanel rightPubPanel = new JPanel(new BorderLayout());
         leftPubPanel.add(new JLabel("Publisher Name: "), BorderLayout.WEST);
         leftPubPanel.add(pubNameField = new JTextField(), BorderLayout.CENTER);
         rightPubPanel.add(new JLabel("Web Site: "), BorderLayout.WEST);
         rightPubPanel.add(pubWebSiteField = new JTextField(), BorderLayout.CENTER);
         if (hasPublisher())
         {  pubNameField.setText(publisher.getName());
            pubWebSiteField.setText(publisher.getWebSite());
         }
         publisherPanel.add(leftPubPanel);
         publisherPanel.add(rightPubPanel);
         pubNameField.getDocument().addDocumentListener(
            new TextChangeListener(pubNameField));
         pubWebSiteField.getDocument().addDocumentListener(
            new TextChangeListener(pubWebSiteField));
         add(publisherPanel);
         add(Box.createVerticalStrut(10));

         // prepare the cover panel
         JPanel coverPanel = new JPanel(new BorderLayout());
         coverPanel.add(hardCoverButton = new JRadioButton("Hard Cover"),
            BorderLayout.WEST);
         coverPanel.add(softCoverButton = new JRadioButton("Soft Cover"),
            BorderLayout.EAST);
         ButtonGroup buttonGroup = new ButtonGroup();
         buttonGroup.add(hardCoverButton);
         buttonGroup.add(softCoverButton);
         if (cover == Cover.HARD)
            hardCoverButton.setSelected(true);
         else
            softCoverButton.setSelected(true);
            
         hardCoverButton.addActionListener(new ActionListener()
            {  public void actionPerformed(ActionEvent e)
               {  cover = Cover.HARD;
               }
            });
         softCoverButton.addActionListener(new ActionListener()
            {  public void actionPerformed(ActionEvent e)
               {  cover = Cover.SOFT;
               }
            });
         add(coverPanel);
         add(Box.createVerticalStrut(10));

         // prepare the edition panel
         JPanel editionPanel = new JPanel(new BorderLayout());
         JPanel innerEditionPanel = new JPanel(new BorderLayout());
         innerEditionPanel.add(new JLabel("Edition:"), BorderLayout.WEST);
         // create a spinner that only accepts integers between 0 and 50 inclusive
         editionSpinner = new JSpinner(new SpinnerNumberModel(edition, 0, 50, 1));
         innerEditionPanel.add(editionSpinner, BorderLayout.CENTER);
         editionSpinner.addChangeListener(new ChangeListener()
            {  public void stateChanged(ChangeEvent e)
               {  SpinnerModel model = editionSpinner.getModel();
                  if (model instanceof SpinnerNumberModel)
                     edition = ((SpinnerNumberModel)model).getNumber().intValue();
               }
            });
         // ensure innerEditionPanel appears at its preferred height
         editionPanel.add(innerEditionPanel, BorderLayout.NORTH);
         add(editionPanel);
         add(Box.createVerticalStrut(10));
      }

      // inner class that handles document change events on text components
      // such as JTextField and JTextArea
      private class TextChangeListener implements DocumentListener
      {
         private JTextComponent component;

         public TextChangeListener(JTextComponent component)
         {  this.component = component;
         }

         public void changedUpdate(DocumentEvent e)
         {  if (component == titleField)
               title = titleField.getText();
            else if (component == authorsArea)
            {  authorList.clear();
               StringTokenizer tokenizer
                  = new StringTokenizer(authorsArea.getText(), "\n\t,;");
               while (tokenizer.hasMoreTokens())
               {  String name = tokenizer.nextToken().trim();
                  if (name.length()>0)
                     authorList.add(name);
               }
            }
            else if (component==pubNameField || component==pubWebSiteField)
            {  if (publisher == null)
                  publisher = new Publisher(pubNameField.getText(),
                     pubWebSiteField.getText());
               else
               {  publisher.setName(pubNameField.getText());
                  publisher.setWebSite(pubWebSiteField.getText());
               }
            }
         }

         public void insertUpdate(DocumentEvent e)
         {  changedUpdate(e);
         }

         public void removeUpdate(DocumentEvent e)
         {  changedUpdate(e);
         }
      }
   }
}