
package dsa_assignment4;

/**
 *
 * @author Naomi
 */
public class HashTableWithChaining<E>
{
    private int number;//the number of the elements in the table
//    private int[] bin;
    private int binSize;//the size of the bin
    private HashNode[] ht;//keep the linkedArray on the table
    
    //hash function
    private int h(Object theKey)
    {
        return (Integer)theKey % binSize;
    }
    
    public HashTableWithChaining(int aBinSize) 
    {
        if(aBinSize < 10)
        {
            binSize = 10;
        }
        else
        {
            binSize = aBinSize;
        }
        number = 0;
        ht = (HashNode[]) new Object[binSize];
    }
    
    public boolean add(Object theKey, Object obj)
    {
        int d = h(theKey);
        HashNode node = ht[d];
        //search the node of theKey from the singly linked list
        while(node != null)
        {
            if(node.key.equals(theKey) == true)
                break;
            else
                node = node.next;
        }
        
        if(node != null)
        {
            //the element already exist
            node.element = obj;
            return false;
        }
        else
        {
            //the element is not exist
            node = new HashNode(theKey, obj);
            node.next = ht[d];
            ht[d] = node;
            number++;
            return true;
        }
    }
    
    public boolean remove(Object theKey)
    {
        int d = h(theKey);
        HashNode node = ht[d];
        HashNode previousNode = null;
        
        while(node != null)
        {
            if(node.key.equals(theKey))
                break;
            else
            {
                previousNode = node;
                node = node.next;
            }
        }
        
        if(node == null)
            //don't delete the element, return false
            return false;
        else if(previousNode == null)
            ht[d] = node.next;
        else
            previousNode.next = node.next;
        number--;
        
        return true;
    }
    
    public Object contains(Object theKey)
    {
        int d = h(theKey);
        HashNode node = ht[d];
        
        while(node != null)
        {
            if(node.key.equals(theKey))
                return node.element;
            else
                node = node.next;
        }
        return null;
    }
    
    public int size()
    {
        return number;
    }
 
    public int capacity()
    {
        return binSize;
    }
    
    public boolean isEmpty()
    {
        return number == 0;
    }
    
    public void clear()
    {
        for(int i=0; i<binSize; i++)
        {
            ht[i] = null;
        }
        number=0;
    }
    
    public void output()
    {
        for(int i=0; i<binSize; i++)
        {
            HashNode node = ht[i];
            while(node != null)
            {
                System.out.println("(" + node.key + " " + node.element + "),");
                node = node.next;
            }
        }
        System.out.println();
    }
    
    public class HashNode
    {
        Object key;
        Object element;
        HashNode next;
        
        public HashNode(Object theKey, Object obj)
        {
            key = theKey;
            element = obj;
            next = null;
        }
    }
}
