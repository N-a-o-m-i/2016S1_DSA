package dsa_arrayset;

/**
 * ArraySetJumbler shows possible errors that can occur when two threads are accessing the same set.
 * This is because threads are accessing, removing and adding elements to the shared array of elements
 * The term of the problem is "A Race Condition"
 */

import java.util.Set;

/**
 *
 * @author Naomi
 */
public class ArraySetJumbler implements Runnable
{
    private Set<Integer> set;
    
    public ArraySetJumbler()
    {
        set = new ArraySet<Integer>();
        Thread threadA = new Thread(this);
        Thread threadB = new Thread(this);
        threadA.start();
        threadB.start();
    }
    
    //method which continually adds and removes a specific eger
    @Override
    public void run() 
    {
        while(true)
        {
            set.add(new Integer(1));
            set.remove(new Integer(1));
        }
    }
    
    public static void main(String[] args)
    {
        new ArraySetJumbler();
    }
    
}
