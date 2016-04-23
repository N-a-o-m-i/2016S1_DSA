package dsa_assignmetnt2;

import java.awt.Graphics;

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
        x = SnakeGameGui.PANEL_WIDTH * (3/4);
        y = SnakeGameGui.PANEL_HEIGHT * (3/4);
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
        if(Math.pow((), x))
        {
            
        }
        return true;
    }
    
    public boolean eatFoodIfInRange(Food food)
    {
        
        return true;
    }
    
    public void drawSnake(Graphics g)
    {
        
    }
    
}
