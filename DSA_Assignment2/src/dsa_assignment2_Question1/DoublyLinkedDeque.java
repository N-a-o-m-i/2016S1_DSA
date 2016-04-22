package dsa_assignmetnt2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedDeque <E extends Comparable> implements DequeADT<E> 
{
    private int size = 0;
    private DLNode firstNode;
    private DLNode lastNode;
    
    public DoublyLinkedDeque()
    {
        firstNode = null;
        lastNode = null;
    }
    
    
    private class DLNode
    {
        private E data;
        private DLNode next;
        private DLNode previous;
        
        public E getData()
        {
            return data;
        }
        
        public void setData(E data)
        {
            this.data = data;
        }
        
        public DLNode getNext()
        {
            return next;
        }
        
        public void setNext(DLNode next)
        {
            this.next = next;
        }
        
        public DLNode getPrevious()
        {
            return previous;
        }
        
        public void setPrevious(DLNode previous)
        {
            this.previous = previous;
        }
        
        public DLNode(E data, DLNode next, DLNode previous)
        {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }
    }
    
    public class FrontIterator implements Iterator<E>
    {
        public DLNode node;
        
        public FrontIterator()
        {
            node = firstNode;
        }
        
        @Override
        public boolean hasNext() 
        {   
            if(node != null)
            {
                return true;
            }
            return false;
        }

        @Override
        public E next() 
        {
            E element = null;
            if(hasNext())
            {
                element = node.getData();
                node = node.next;
            }
            return element;
        }
    }
    
    public class BackIterator implements Iterator<E>
    {
        public DLNode node;
        
        public BackIterator()
        {
            node = lastNode;
        }
        
        @Override
        public boolean hasNext() 
        {   
            if(node != null)
            {
                return true;
            }
            return false;
        }

        @Override
        public E next() 
        {
            E element = null;
            if(hasNext())
            {
                element = node.getData();
                node = node.previous;
            }
            return element;
        }
    }
    
    @Override
    public void enqueueFront(E element)
    {
        DLNode newNode = new DLNode(element, firstNode, null);
        if(isEmpty())
        {
            lastNode = newNode;
        }
        else
        {
            firstNode.setPrevious(newNode);
        }
        firstNode = newNode;
        size++;
    }
    
    @Override
    public void enqueueRear(E element) 
    {
        DLNode newNode = new DLNode(element, null, lastNode);
        if(isEmpty())
        {
            firstNode = newNode;
        }
        else
        {
            lastNode.setNext(newNode);
        }
        lastNode = newNode;
        size++;
    }

    @Override
    public E dequeueFront() throws NoSuchElementException 
    {
        E front = null;
        if(!isEmpty())
        {
            front = firstNode.getData();
            firstNode = firstNode.getNext();
            if(firstNode == null)
            {
                lastNode = null;
            }
            else
            {
                firstNode.setPrevious(null);
            }
        }
        size--;
        return front;
    }
    
    @Override
    public E dequeueRear() throws NoSuchElementException 
    {
        E back = null;
        if(!isEmpty())
        {
            back = lastNode.getData();
            lastNode = lastNode.getPrevious();
            if(lastNode == null)
            {
                firstNode = null;
            }
            else
            {
                lastNode.setNext(null);
            }
        }
        size--;
        return back;
    }

    
    @Override
    public E first() throws NoSuchElementException
    {
        E front = null;
        if(!isEmpty())
        {
            front = firstNode.getData();
        }
        return front;
    }
    
    @Override
    public E last() throws NoSuchElementException 
    {
        E back = null;
        if(!isEmpty())
        {
            back = lastNode.getData();
        }
        return back;
    }

    @Override
    public Iterator<E> iterator(boolean fromFront)
    {
        if(fromFront)
        {
            return new FrontIterator();
        }
        return new BackIterator();
    }

    @Override
    public boolean isEmpty() 
    {
        return firstNode == null;
    }

    @Override
    public int size() 
    {
        return size;
    }

    @Override
    public void clear()
    {
        firstNode.next.previous = null;
        firstNode.next = null;
        lastNode.previous.next = null;
        lastNode.previous = null;
        firstNode = null;
        lastNode = null;
        size = 0;
    }
    
    public static void main(String[] args)
    {
        DoublyLinkedDeque<Integer> DLDeque = new DoublyLinkedDeque<Integer>();
        
        DLDeque.enqueueFront(0);
        DLDeque.enqueueFront(1);
        DLDeque.enqueueFront(2);
        DLDeque.enqueueFront(3);
        DLDeque.enqueueFront(4);
        int size = DLDeque.size;
        
        Iterator<Integer> iterator = DLDeque.iterator(true);
        while(iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
        
        iterator = DLDeque.iterator(false);
        while(iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
        
    }
  
}
