package Heaps;

import java.util.Collection;
import java.util.Comparator;

/**
 *
 * @author Naomi
 */
public class ArrayHeap<E> implements HeapADT<E>
{
    private final int INITIAL_CAPACITY = 15;//initial heap height is 3
    private int numElements;
    private E[] elements;
    private Comparator<? super E> comparator;
    
    public ArrayHeap()
    {
        this(null);
    }
    
    public ArrayHeap(Comparator<? super E> comparator)
    {
        this.comparator = comparator;
        numElements = 0;
        elements = (E[]) (new Object[INITIAL_CAPACITY]);//unchecked
    }
    
    public ArrayHeap(Collection<? extends E> c, Comparator<? super E> comparator)
    {
        this(comparator);
        for(E element:c)
            add(element);
    }

    //adds one element to the heap in correct position to maintain heap
    @Override
    public boolean add(E element)
    {
        if(numElements >= elements.length)
            expandCapacity();
        elements[numElements++] = element;
        if(numElements > 1)
            restoreHeapAfterAdd();
        return true;
    }
    
    //helper method which increase the current size of the array to
    //handle another level of the heap
    public void expandCapacity()
    {
        E[] largerArray = (E[]) (new Object[elements.length*2 + 1]);//unchecked
        //copy the elements array to the largerArray
        for(int i = 0; i < numElements; i++)
            largerArray[i] = elements[i];
        elements = largerArray;
    }
    
    //helper method that restores the heap ordering after an element
    //has been added to index numElements-1, moving the element upward
    //toward the root untill order is restored
    public void restoreHeapAfterAdd()
    {
        int currentIndex = numElements-1;//start with added element
        int parentIndex = (currentIndex-1)/2;
        
        
        
    }
    
    

    @Override
    public E removeMin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E getMin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comparator<? super E> comparator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
}
