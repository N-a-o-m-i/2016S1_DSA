package specializedsorting.SelectionProblem;

import java.util.Random;

/**
 *
 * @author Naomi
 */
public class SelectionProblem<E extends Comparable>
{
    private Random generator;
    
    public SelectionProblem()
    {
        generator = new Random();
    }
    
    //returns the (i+1)-th smallest element in the list, i.e. the
    //element which would appear at index i if the list were sorted
    public E find(E[] list, int i)
    {
        return find(list, i, 0, list.length);
    }
    
    //recursive method which returns the (i+1)-th smallest element
    //in the list, which is know to be between indices
    //start (inclusive) and end (exclusive)
    private E find(E[] list, int i, int start, int end)
    {
        //base case when only one element in range
        if(start+1>=end)
            return list[start];
        else
        {
            //get a random index between start and end and
            //swap element there with the element at start
            //so that the random element is used as partition element
            int randomIndex = generator.nextInt(end-start) + start;
            E temp = list[start];
            list[start] = list[randomIndex];
            list[randomIndex] = temp;
            //randomly partition the list between startIndex and endIndex
            int indexPartition = partition(list, start, end);
            if(indexPartition == i)//partition element is i-th smallest
                return list[indexPartition];
            else if(i < indexPartition)//i-th smallest in left side
                return find(list, i, start, indexPartition);
            else //i>indexPartition so i-th smallest in right side
                return find(list, i, indexPartition+1, end);
        }
        
    }
    
    //use the index start to partition the segment of the list
    //with the element at start as the partition element
    //separating the list segment into two parts, one less than
    //the partition, the other greater than the partition
    //returns the index where the partition element ends up
    private int partition(E[] list, int start, int end)
    {
        E temp;//temporary reference to an element for swapping
        E partitionElement = list[start];
        int leftIndex = start+1;//start at the left end
        int rightIndex = end-1;//start at the right end
        //swap elements so elements at left part are less than
        //partition element and at right part are greater
        while(leftIndex<rightIndex)
        {
            //find element starting from left greater than partition
            while(leftIndex<rightIndex && list[leftIndex].compareTo(partitionElement) <= 0)
                leftIndex++;//this index is on correct side of partition
            //find element starting from right less than partition
            while(list[rightIndex].compareTo(partitionElement) > 0)
                rightIndex--;//this index is on correct side of partition
            
            if(leftIndex<rightIndex)
            {
                //swap these two elements
                temp = list[leftIndex];
                list[leftIndex] = list[rightIndex];
                list[rightIndex] = temp;
            }
        }
        //put the partition element between the two parts at rightIndex
        list[start] = list[rightIndex];
        list[rightIndex] = partitionElement;
        return rightIndex;
    }
    
    public static void main(String[] args)
    {
        SelectionProblem<Integer> selector = new SelectionProblem<Integer>();
        Integer[] list = {4,78,3,123,7,56,2,8,0,33};
        for(int i=0; i<list.length; i++)
        {
            int element = selector.find(list, i);
            System.out.println("The " + (i+1) + "-th smallest element is: " + element);
        }
    }
    
}
