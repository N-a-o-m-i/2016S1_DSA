package dsa_arrayset;

import java.util.Set;

/**
 *
 * @author Naomi
 */
public class SetExample 
{
    
    public static void main(String[] args)
    {
        Set<String> bookTitles = new ArraySet<String>();
        bookTitles.add("Java Software Structures");//added
        System.out.println(bookTitles);   
        bookTitles.add("Computer Graphics Using OpenGL");//added
        System.out.println(bookTitles);   
        bookTitles.add("Java Software Structures");//not added
        System.out.println(bookTitles);   
        bookTitles.add("Introduction to Algorithms");//added
        System.out.println(bookTitles);   
        bookTitles.add(null);//added
        System.out.println(bookTitles);   
        bookTitles.remove("Computer Graphics Using OpenGL");//removed and null fill the hole
        System.out.println(bookTitles);        
    }
    
    
}
