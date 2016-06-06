
package dsa_assignment4;

/**
 *
 * @author Naomi
 */
public class HashTableWithChaining<E>
{
    private Person[] person;
    private int capacity;

    public HashTableWithChaining() 
    {
        person = new Person[20];
        capacity = 0;
    }
    
    
    
    public int hash1(String s)
    {
        //hash function
        return Math.abs(s.hashCode()) % person.length;
    }
    
    public int hash2(String s)
    {
        //the hash function to deal with collision
        
        return ;
    }
    
    public void add()
    {
        
    }
    
    public void remove()
    {
        
    }
    
    public void contains()
    {
        
    }
    
    public int size()
    {
        
        return ;
    }
    
    public String toString()
    {
        return ;
    }
}
