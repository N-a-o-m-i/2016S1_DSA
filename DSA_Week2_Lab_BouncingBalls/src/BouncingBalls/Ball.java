package BouncingBalls;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Random;

public class Ball implements Runnable
{   
    private int width;
    private int height;
    private int x;
    private int y;
    private int dx;
    private int dy;
    private boolean stopRequest;
    private Random gen;
    private Color color;
    private int RADIUS = 15;
    private List<Ball> neighbours;

    public Ball(int width, int height, List<Ball> neighbours) 
    {
        this.width = width;
        this.height = height;
        this.neighbours = neighbours;
        gen = new Random();
        //start in center of panel
        x = width/2;
        y = height/2;
        //create a random position for the movements
        do
        {
            dx = gen.nextInt(8)-4;
            dy = gen.nextInt(8)-4;
        }while(dx == 0 && dy == 0);
        //random colour 
        color = new Color(gen.nextFloat(),gen.nextFloat(),gen.nextFloat());
    }
    
    
    public void run()
    {
        stopRequest = false;
        //loop until finished
        while(!stopRequest)
        {
            x += dx;
            y += dy;
            //if outside bounds of world flip directions
            if(x <= 0 || (x + RADIUS) >= width)
            {
                dx = -dx;
            }
            if(y <= 0 || (y + RADIUS) >= height)
            {
                dy = -dy;
            }
            
            try
            {
                Thread.sleep(10);
            }
            catch(InterruptedException e){}
        }            
    }
    
    public void requestStop()
    {
        stopRequest = true;
    }
        
    
    public void drawBall(Graphics g)
    {
        //draw the ball
        g.setColor(color);
        g.fillOval((int)x, (int)y, RADIUS, RADIUS);
        
    }
    
}
