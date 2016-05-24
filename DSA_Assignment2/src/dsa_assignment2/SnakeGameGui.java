package dsa_assignment2;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author sehall
 */
public class SnakeGameGui extends JPanel implements ActionListener,KeyListener
{
   public final static int PANEL_WIDTH = 800;
   public final static int PANEL_HEIGHT = 800;
   public final static int NUM_FOOD = 15;
   public Timer timer;
   private DrawPanel drawPanel;
   private JButton restartButton;
   private static JFrame frame;
   private DoublyLinkedDeque<Food> DLDeque = new DoublyLinkedDeque<Food>();
   private Snake snake;

   public SnakeGameGui()
   {
      super(new BorderLayout());
      drawPanel = new DrawPanel();
      timer = new Timer(50,this);

      JPanel sliderPanel = new JPanel();
      restartButton = new JButton("Restart");
      restartButton.addActionListener(this);
      sliderPanel.add(restartButton);

      snake = new Snake();



      add(drawPanel,BorderLayout.CENTER);
      add(sliderPanel,BorderLayout.SOUTH);
   }

   private class DrawPanel extends JPanel
   {
       public DrawPanel()
       {   super();
           setPreferredSize(new Dimension(PANEL_WIDTH ,PANEL_HEIGHT));
           setBackground(Color.WHITE);

       }
       //can be used to draw the snake and food
       @Override
       public void paintComponent(Graphics g)
       {
            super.paintComponent(g);

            snake.drawSnake(g);

            DLDeque.iterator(true);
            Iterator<Food> iterator = DLDeque.iterator(true);
            while(iterator.hasNext())
            {
                iterator.next().drawFood(g);
            }


       }
   }
   @Override
   public void actionPerformed(ActionEvent event)
   {
      Object source = event.getSource();

      if(source == timer)
      {  drawPanel.repaint();

      }
      if(source == restartButton)
      {   JOptionPane.showMessageDialog(drawPanel, "YOU HAVE PRESSED THE RESTART BUTTON, YOU THIS FOR GAME MESSAGES :-) ",
                                    "SNAKE GAME" , JOptionPane.INFORMATION_MESSAGE);
          //Give Keyboard Focus back to the Frame DO NOT REMOVE!
          frame.setFocusable(true);
          frame.requestFocusInWindow();

      }
   }

     @Override
     public void keyPressed(KeyEvent e)
     {
         if(e.getKeyCode() == KeyEvent.VK_UP)
         {
             System.out.println("UP PRESSED");
         }
         else if(e.getKeyCode() == KeyEvent.VK_DOWN)
         {
             System.out.println("DOWN PRESSED");
         }
         else if(e.getKeyCode() == KeyEvent.VK_LEFT)
         {
             System.out.println("LEFT PRESSED");
         }
         else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
         {
             System.out.println("RIGHT PRESSED");
         }
         else System.out.println("SOME DIFFERENT KEY!");
     }
     @Override
     public void keyTyped(KeyEvent e) {
         //IGNORE
     }
     @Override
     public void keyReleased(KeyEvent e) {
         //IGNORE
     }

     public void FoodCollection()
     {
        for(int i = 1; i < NUM_FOOD; i++)
        {
            int rating = i;
            Food food = new Food(PANEL_WIDTH,PANEL_HEIGHT,rating);
            Thread t = new Thread(food);
            t.start();
            DLDeque.enqueueRear(food);
        }

     }

   public static void main(String[] args)
   {
      System.out.println("============SNAKE===============");
      SnakeGameGui game = new SnakeGameGui();
      frame = new JFrame("SNAKE GAME GUI");
      frame.setFocusable(true);
      //add a keylistener
      frame.addKeyListener(game);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(game);
      //gets the dimensions for screen width and height to calculate center
      Toolkit toolkit = Toolkit.getDefaultToolkit();
      Dimension dimension = toolkit.getScreenSize();
      int screenHeight = dimension.height;
      int screenWidth = dimension.width;
      frame.pack();             //resize frame apropriately for its content
      //positions frame in center of screen
      frame.setLocation(new Point((screenWidth/2)-(frame.getWidth()/2),
         (screenHeight/2)-(frame.getHeight()/2)));
      frame.setVisible(true);
      game.timer.start();
      game.FoodCollection();
   }
}
