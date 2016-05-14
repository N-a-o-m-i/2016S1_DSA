package dsa_binarysearch;

/**
 * This code will only work on elements that implement the
 * Comparable interface
 * This is the reason for the <E extends Comparable> generic
 */

/**
 * A class which demonstrates binary search of an array
 * @author Naomi
 */
public class BinarySearch<E extends Comparable>
{
    private E[] elements;
    
    public BinarySearch(E[] elements)
    {
        this.elements = elements;
    }
    
    public int search(E target)
    {
        if(target==null)
            throw new NullPointerException("search target is null");
        return search(target, 0, elements.length);
    }
    
    // recursive method which searches through the elements array
    // between start index (inclusive) and end index (exclusive) for the 
    // index of specified target, or returns -(insertion)-1 if not found
    private int search(E target, int start, int end)
    {
        if(start > end)
            return -start-1;
        else
        {
            int midPoint = (start + end)/2;
            int comparison = target.compareTo(elements[midPoint]);
            if(comparison == 0)
                return midPoint;
            else if(comparison < 0)
            {
                return search(target, start, midPoint);
            }
            else//comparison > 0
                return search(target, (midPoint + 1), end);
        }
    }

    public static void main(String[] args)
    {
        String[] list = {"ant", "bat", "cat", "cow", "dog", "eel",
                        "fly", "fox", "owl", "pig", "rat"};
        BinarySearch<String> bin = new BinarySearch<String>(list);
        String target = "dog";
        int index = bin.search(target);
        if(index >= 0)
            System.out.println(target + " found at index " + index);
        else
            System.out.println(target + " not at index " + (-index-1));
    }
    
}
