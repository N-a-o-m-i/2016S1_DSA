package dsa_arrayset;

import java.util.Collection;

/**
 *
 * @author Naomi
 */
public class SyncArraySet<E> extends ArraySet<E>
{
    public SyncArraySet()
    {
        super();
    }
    
    public SyncArraySet(Collection<? extends E> c)
    {
        super(c);
    }
    
    public synchronized boolean add(E o)
    {
        return super.add(o);
    }
    
    public synchronized boolean remove(Object o)
    {
        return super.remove(o);
    }
    
    
}
