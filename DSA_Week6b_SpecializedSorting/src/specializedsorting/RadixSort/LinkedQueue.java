package specializedsorting.RadixSort;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 *
 * @author Naomi
 */
public class LinkedQueue<E> implements QueueADT<E>
{
    private int numElements;
    private Node<E> frontNode;//front of list is front of queue
    private Node<E> rearNode;//rear of list is rear of queue
    
    //default constructor that creates a new queue
    //that is initially empty
    public LinkedQueue()
    {
        super();
        numElements = 0;
        frontNode = null;
        rearNode = null;
    }
    
    //constructor for creating a new queue which
    //initially holds the elements in the collection c
    public LinkedQueue(Collection<? extends E> c)
    {
        this();
        for(E element:c)
        {
            enqueue(element);
        }
    }
    
    //adds one element to the rear of this queue
    @Override
    public void enqueue(E element) 
    {
        Node<E> newNode = new Node(element);
        //add the new node to the ened of the list
        if(rearNode == null)
        {
            frontNode = newNode;
            rearNode = newNode;
        }
        else
        {
            rearNode.next = newNode;
            rearNode = newNode;
        }
        numElements++;
    }

    //removes and returns the front element from this queue
    @Override
    public E dequeue() throws NoSuchElementException 
    {
        if(frontNode == null)
        {
            throw new NoSuchElementException();
        }
        else
        {
            E frontElement = frontNode.element;
            frontNode = frontNode.next;
            numElements--;
            if(numElements == 0)
                rearNode = null;
            return frontElement;
        }
    }

    //returns the first element in the list without removing the front element of this queue
    @Override
    public E first() throws NoSuchElementException 
    {
        if(numElements == 0)
        {
            throw new NoSuchElementException();
        }
        else
        {
            E frontElement = frontNode.element;
            return frontElement;
        }
    }

    //returns true if this queue contains no elements
    @Override
    public boolean isEmpty() 
    {
        return (numElements == 0);
    }

    //return the number of elements in this queue
    @Override
    public int size() 
    {
        return numElements;
    }
    
    //returns a string representation of the queue from front to rear
    public String toString()
    {
        String output = "[";
        Node currentNode = frontNode;
        while(currentNode != null)
        {
            output += currentNode.element;
            if(currentNode.next != null)
                output += ", ";
            currentNode = currentNode.next;
        }
        output += "]";
        return output;
    }
    
    //inner class which represents a node in a singly-linked list
    private class Node<E>
    {
        public E element;
        public Node<E> next;
        
        public Node(E element)
        {
            this.element = element;
            next = null;
        }
    }
    
}
