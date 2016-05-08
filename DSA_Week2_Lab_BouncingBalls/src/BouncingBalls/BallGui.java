package BouncingBalls;
/*******************************************************************************************
FILE:           BallGUI.java
AUTHER:         Seth Hall
DATE:           March 2016
DESCRIPTION:    Creates the class to hold the balls
*******************************************************************************************/
                      //useful imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;


public class BallGui extends JPanel implements ActionListener
{
    private List<Ball> list;
    private Timer timer;
    private InnerClass innerclass;
    private JButton stopButton,exitButton,addButton,priority;
    boolean setPriority;
    private JPanel ballPanel;
 
    public BallGui()
    {   super();
        setLayout(new BorderLayout());
        try
        {  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e){}
        
        list = new ArrayList<Ball>();
        list = Collections.synchronizedList(list);
        int delay = 80;//initial value of delay
        timer = new Timer(delay,this);
        
        innerclass = new InnerClass();
        
        stopButton = new JButton(" stop ");
        exitButton = new JButton(" quit ");
        addButton = new JButton("add");
        priority = new JButton("add 100");
        stopButton.addActionListener(this);
        exitButton.addActionListener(this);
        addButton.addActionListener(this);
        priority.addActionListener(this);
        
        timer.start();
        setPriority = false;
        
        JPanel panel = new JPanel();
        panel.add(stopButton);
        panel.add(exitButton);
        panel.add(addButton);
        panel.add(priority);
        //panel.setPreferredSize(new Dimension(200,800));

        add(panel,BorderLayout.SOUTH);
        add(innerclass,BorderLayout.NORTH);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

        if(source == stopButton)
        {
            for(Ball ball:list)
            {
                ball.requestStop();
            }
            System.out.println("STOP BUTTON PRESSED");
        }
        else if(source == exitButton)
        {
            System.exit(0);
        }else if(source == addButton)
        {
            Ball ball = new Ball(innerclass.getWidth(),innerclass.getHeight(),list);
            Thread thread = new Thread(ball);
            //thread.setPriority(Thread.MIN_PRIORITY);
            list.add(ball);
            thread.start();
            System.out.println("ADD BUTTON PRESSED");
        }else if(source == timer)
        {
            innerclass.repaint();
        }else if(source == priority)
        {
            for(int i = 0; i < 100; i++)
            {
                Ball ball = new Ball(innerclass.getWidth(),innerclass.getHeight(),list);
                Thread thread = new Thread(ball);
                list.add(ball);
                thread.start();
            }
        }
    }

    public class InnerClass extends JPanel
    {
        public InnerClass()
        {
            setPreferredSize(new Dimension(500,500));
            setBackground(Color.WHITE);   
        }

        @Override
        protected void paintComponent(Graphics g) 
        {
            super.paintComponent(g);
            for(Ball ball:list)
            {
                ball.drawBall(g);
            }
        }   
    }
    
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("THE BALL BOUNCER");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new BallGui());
        frame.pack();                                      //pack frame
        frame.setVisible(true);                                      //show the frame
    }
}
