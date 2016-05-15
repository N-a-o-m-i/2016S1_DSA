package specializedsorting.RadixSort;

import java.util.NoSuchElementException;

/**
 *
 * @author Naomi
 */
public interface QueueADT<E> 
{
    //adds one element to the rear of this queue
    public void enqueue(E element);
    //removes and returns the front element of the queue
    public E dequeue() throws NoSuchElementException;
    //returns the first element without removing the front element of this queue
    public E first() throws NoSuchElementException;
    //returns true if this queue contains no element
    public boolean isEmpty();
    //returns the number of elements in this queue
    public int size();
}
