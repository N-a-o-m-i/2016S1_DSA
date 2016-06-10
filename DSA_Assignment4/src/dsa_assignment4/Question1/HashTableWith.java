package dsa_assignment4.Question1;

/**
 *
 * @author Naomi
 */
public class HashTableWith 
{
    //The table, resized as necessary. Length MUST always be a power of two
    private Entry[] table;
    //The number of key-value mappings contained in this identity hashmap
    private int size;
    //The next size value at which to resize(capacity * load factor)
    private int threshold;
    
    /**
     * Constructs an empty <tt>HashMap<tt> with the default initial capacity
     * (16) and the default load factor(0.75)
     */
    public HashTableWith()
    {
        threshold = 12;
        table = new Entry[17];
    }
    
    /**
     * Returns the number of key-value mappings in this map.
     */
    public int size()
    {
        return size;
    }
    
    /**
     * Returns <tt>true<tt> if this map contains no key-value mappings
     */
    public final boolean isEmpty()
    {
        return size == 0;
    }
    
    /**
     * Returns the first Object for the given key
     */
    public final Object get(int key)
    {
        int i = (int)(key % table.length);
        Entry e = table[i];
        while(true)
        {
            if(e == null)
                return null;
            if(e.key == key)
                return e.value;
            e = e.next;
        }
    }
    
    /**
     * Returns <tt>true<tt> if this map contains the object for the specified key
     */
    public final boolean contains(int key)
    {
        return (get(key) != null);
    }
    
    /**
     * Add the Object with the key. 
     */
    public final Object add(int key, Object value)
    {
//        System.out.println("key: " + key + "value: " +value);
        int i = (int)(key % table.length);
//        System.out.println("i: " + i);
        table[i] = new Entry(key, value, table[i]);
//        System.out.println("table: " + table[i]);
        if(size++ >= threshold)
            resize(2 * table.length);
        
        return null;
    }
    
    /**
     * Rehashes the contents of this map into a new array with a
     * larger capacity.  This method is called automatically when the
     * number of keys in this map reaches its threshold.
     */
    final private void resize(int newCapacity)
    {
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)(newCapacity * 0.75f);
    }
    
    /**
     * Transfer all entries from current table to newTable
     */
    final private void transfer(Entry[] newTable)
    {
        Entry[] src = table;
        int newCapacity = newTable.length;
        for(int j=0; j<src.length;j++)
        {
            Entry e = src[j];
            if(e != null)
            {
                do
                {
                    Entry next = e.next;
                    e.next = null;
                    int i = (int)(e.key % newCapacity);
                    // The order for Object with the same key must not change
                    // that we need to find the end of the link list.
                    if(newTable[i] == null)
                    {
                        newTable[i] = e;
                    }
                    else
                    {
                        Entry entry = newTable[i];
                        while(entry.next != null)
                            entry = entry.next;
                        entry.next = e;
                    }
                    e=next;
                }while(e != null);
            }   
        }
    }
    
    /**
     * Removes the mapping for this key from this map if present
     */
    public final Object remove(int key)
    {
        int i = (int)(key % table.length);
        Entry prev = table[i];
        Entry e = prev;
        
        while(e != null)
        {
            Entry next = e.next;
            if(e.key == key)
            {
                size--;
                if(prev == e)
                    table[i] = next;
                else
                    prev.next = next;
                return e.value;
            }
            prev = e;
            e = next;
        }
        return null;
    }
    
    /**
     * Removes all mappings from this map
     */
    public final void clear()
    {
        Entry tab[] = table;
        for(int i = 0; i<tab.length; i++)
            tab[i] = null;
        size = 0;
    }
    
    /**
     * Returns <tt>true<tt> if this map maps one or more keys to the specified value.
     */
    public final boolean containsValue(Object value)
    {
        Entry tab[] = table;
        for(int i = 0; i < tab.length; i++)
            for(Entry e = tab[i]; e != null; e = e.next)
                if(value.equals(e.value))
                    return true;
        return false;
    }
    
    public String output()
    {
        String output = "";
        for(int i=0; i<table.length; i++)
        {
            output += (i+1) + ": ";
            Entry node = table[i];
            if(node == null)
            {
                output += "()";
            }
            while(node != null)
            {
                output += "(" + (node.key % size) + " " + node.value + "),";
                node = node.next;
            }
            output += "\t\n";
        }
        return output;
    }
    
    public static class Entry
    {
        final int key;
        final Object value;
        Entry next;
        
        //Create new entry
        Entry(int k, Object v, Entry n)
        {
            key = k;
            value = v;
            next = n;
        }
    }
}
