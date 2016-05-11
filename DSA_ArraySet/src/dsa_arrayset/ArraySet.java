package dsa_arrayset;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Naomi
 */
public class ArraySet<E> extends AbstractSet<E>
{
    private final int INITIAL_CAPACITY = 20;
    private int numElements;
    protected E[] elements;
    
    //default constructor that creates a new set
    //that is initially empty
    public ArraySet()
    {
        super();
        numElements = 0;
        elements = (E[])(new Object[INITIAL_CAPACITY]);//unchecked
    }
    
    //constructor for creating a new set which
    //initially holds the elements in the collection c
    public ArraySet(Collection<? extends E> c)
    {
        this();
        for(E element : c)
        {
            add(element);
        }
    }
    
    //adds the element to the set provided that it 
    //is not already in the set, and returns
    //true if the set did not already contain the element
    public boolean add(E o)
    {
        if(contains(o))
        {
           return false; 
        }
        if(numElements >= elements.length)
        {
            expandCapacity();
        }
        elements[numElements++] = o;
        return true;
    }
    
    //remove the element from the set and returns true if the 
    //element was in the set
    public boolean remove(Object o)
    {
        //search for the index of the element o in the set
        int index = 0;
        boolean found = false;
        for(int i = 0; i < numElements; i++)
        {
            if(elements[i]==null && o==null || elements[i]!=null && elements[i].equals(o))
            {
                index = i;
                found = true;
            }
        }
        if(found)//replace the element at index by last element
        {
            elements[index] = elements[numElements];
            elements[numElements-1] = null;//removes reference to element
            numElements--;
        }
        return found;
    }
    
    //returns an iterator for iterating through the elements in the set
    @Override
    public Iterator<E> iterator() 
    {
        return new ArrayIterator<E>(elements, numElements);
    }

    @Override
    public int size() 
    {
        return numElements;
    }
    
    //remove all the elements form the set
    public void clear()
    {
        for(int i = 0; i < numElements; i++)
        {
            elements[i] = null;
        }
        numElements = 0;
    }
    
    //helper method which doubles the current size of the array
    private void expandCapacity()
    {
        E[] largerArray = (E[])(new Object[elements.length*2]);//unchecked
        //copy the elements array to the largeArray
        for(int i = 0; i < numElements; i++)
        {
            largerArray[i] = elements[i];
        }
        elements = largerArray;
    }
    
    //inner class which represents an iterator for an array
    private class ArrayIterator<E> implements Iterator<E>
    {
        private int numElements;//number of elements in array to use
        private E[] elements;//the array to use
        private int nextIndex;//index of next element for the iterator
        
        //constructor which accepts an array of elements
        //and prepares an iterator which will iterate through the
        //first numElements elements of the array
        public ArrayIterator(E[] elements, int numElements)
        {
            if(numElements > elements.length)
            {
                numElements = elements.length;
            }
            this.numElements = numElements;
            this.elements = elements;
            nextIndex = 0;//start with index of first element in array
        }
        
        //returns whether there is still another element
        @Override
        public boolean hasNext() 
        {
            return (nextIndex < numElements);
        }

        //returns the next element or throws a NoSuchElementException
        //if there are no further elements
        @Override
        public E next() throws NoSuchElementException
        {
            if(!hasNext())
            {
                throw new NoSuchElementException();
            }
            return elements[nextIndex++];
        }  
        
        //remove method not supported by this iterator
        public void remove() throws UnsupportedOperationException
        {
            throw new UnsupportedOperationException();
        }   
        
    }   
    
}
