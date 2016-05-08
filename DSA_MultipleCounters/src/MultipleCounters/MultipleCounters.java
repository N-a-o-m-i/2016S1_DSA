package MultipleCounters;

/**
 *
 * @author Naomi
 */
public class MultipleCounters 
{
    public static void main(String[] args) throws InterruptedException
    {
        Counter counterA = new Counter("A", 20);
        Counter counterB = new Counter("B", 20);
        Counter counterC = new Counter("C", 20);
        Thread threadA = new Thread(counterA);
        Thread threadB = new Thread(counterB);
        Thread threadC = new Thread(counterC);
        //start the three threads one by one
        threadA.start();
        threadA.join();
        threadB.start();
        threadB.join();
        threadC.start();
    }
}

class Counter implements Runnable
{
    private String name;
    private int maxCounts;
    
    public Counter(String name, int maxCounts)
    {
        this.name = name;
        this.maxCounts = maxCounts;
    }
    
    public synchronized void run()
    {
        for(int i = 0; i < maxCounts; i++)
        {
            System.out.print("Counter " + name + " is " + (i+1) + ", ");
            System.out.println("Counter " + name + " about to loop");
        }
    }
}