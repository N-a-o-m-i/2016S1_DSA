package dsa_assignment2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

/**
 *
 * @author Naomi
 */
public class Snake 
{
    private DoublyLinkedDeque<Segment> segments;
    private boolean alive;
    private int x;
    private int y;
    private static final int SIZE = 10;
    private int movement;
    public enum Direction {U,D,L,R};
    private Direction direction;
    
    public Snake()
    {
        segments = new DoublyLinkedDeque<Segment>();
        alive = true;
        x = SnakeGameGui.PANEL_WIDTH * (1/2);
        y = SnakeGameGui.PANEL_HEIGHT * (3/4);
        segments.enqueueFront(new Segment(x,y,SIZE,Color.DARK_GRAY));
        movement = 10;
        direction = Direction.U;
    }
    
    public void run()
    {
        while(alive)
        {
            moveSnake();
        }
    }
    
    private void moveSnake()
    {
        //if(snake hits the edge of panel)
        //{alive = false;}
        //else{update x,y depending on the direction}
        //iterator body segments uppdate each position{first segment goes to the head position}
        if(x > SnakeGameGui.PANEL_WIDTH || x < 0 || y > SnakeGameGui.PANEL_HEIGHT || y < 0)
        {
            alive = false;
        }
        else
        {
            if(direction == Direction.U)
            {
                y = y - movement;
                segments.first().setY(y);
            }
            else if(direction == Direction.D)
            {
                y = y + movement;
                segments.first().setY(y);
            }
            else if(direction == Direction.L)
            {
                x = x - movement;
                segments.first().setX(x);
            }
            else
            {
                x = x + movement;
                segments.first().setX(x);
            }
            
            Iterator<Segment> frontIterator = segments.iterator(true);
            Iterator<Segment> backIterator = segments.iterator(false);
            while(frontIterator.hasNext())
            {
                Segment nextSegment = frontIterator.next();
                Segment previousSegment = backIterator.next();
                int x;
                int y;
                nextSegment.setX(backIterator.next().getX());
                nextSegment.setY(backIterator.next().getY());
                
            }
            
        }
        
        
        
        
    }
    
    public boolean isAlive()
    {
        return alive;
    }
    
    public void killSnake()
    {
        segments.clear();
        alive = false;
    }
    
    public int getXPosition()
    {
        return x;
    }
    
    public int getYPosition()
    {
        return y;
    }
    
    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }
    
    public boolean checkIfSnakeHit(Food food)
    {
        Iterator<Segment> iterator = segments.iterator(true);
            while(iterator.hasNext())
            {
                Segment aSegment = iterator.next();
                if(Math.pow((aSegment.getX()-food.getX()), 2) + Math.pow((aSegment.getY()-food.getY()), 2) < Math.pow((food.getSize() + SIZE)/2, 2))
                {
                    return true;
                }
            }     
        return false;
    }
    
    public boolean eatFoodIfInRange(Food food)
    {
        //if(food in range) then {append new segments to the snake}
        //append new segments { enqueue new Segment() 
        //                      depending on 1. rating of food
                                        //      2. Colour of food
        //Note: new segments should appear at the front
        if(Math.pow((x - food.getX()), 2) + Math.pow((y - food.getY()), 2) < Math.pow((food.getSize() + SIZE)/2, 2))
        {
            Segment head = segments.dequeueFront();
            for(int i = 0; i < food.getValue(); i++)
            {
                segments.enqueueFront(new Segment(x, y, SIZE, food.getColour()));
            }
            segments.enqueueFront(head);
            return true;
        }
        
        return false;
    }
    
    public void drawSnake(Graphics g)
    {
        Iterator<Segment> iterator = segments.iterator(true);
            while(iterator.hasNext())
            {
                iterator.next().drawSegment(g);
            }  
    }
    
}
