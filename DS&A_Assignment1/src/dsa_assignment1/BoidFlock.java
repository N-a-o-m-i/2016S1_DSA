package dsa_assignment1;

import java.awt.Graphics;
import java.util.*;

public class BoidFlock 
{
    public static int DETERCTRADIUS = 50;
    private int panelWidth;
    private int panelHeight;
    private List<Boid> boidList;   
    private Random RANDOM;
    
    static
    {
        Random RANDOM = new Random();
    }
    
    public BoidFlock()
    {
        this.boidList = new ArrayList();
    }    
    
    public BoidFlock(int numBoidsToStart, int panelWith, int panelHeight)
    {
        BoidFlock specifiedBF = new BoidFlock(numBoidsToStart);
        this.panelWidth = panelWidth;
        this.panelHeight = panelWidth;
    }
    
    public void addBoidToFlock()
    {
        Boid aBoid = new Boid(new BoidFlock(), RANDOM.nextInt(panelWidth), RANDOM.nextInt(panelHeight), 
                            RANDOM.nextInt(Boid.MAX_SPEED), RANDOM.nextInt(Boid.MAX_SPEED),
                            panelWidth, panelHeight);
        boidList.add(aBoid);
    }
    
    public void removeBoidFromFlock()
    {
        
    }
    
    public void drawAllBoids(Graphics g)
    {
        
    }
    
    public int getNumberOfBoids()
    {
        
        return 1;
    }
    
    public void destroyAllBoids()
    {
        
    }
    
    public ArrayList<Boid> getNeighbours(Boid boidToTest)
    {
        
        return boidlist;
    }
    
}
