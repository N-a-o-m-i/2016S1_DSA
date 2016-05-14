package arraysorter.QuickSort;

/**
 *
 * @author Naomi
 */
public class QuickSort<E extends Comparable>
{
    
    public void quickSort(E[] list)
    {
        quickSortSegment(list, 0, list.length);
    }
    
    //recursive method which applies quick sort to the portion
    //of the array between start (inclusive) and end (exclusive)
    private void quickSortSegment(E[] list, int start, int end)
    {
        if(end-start>1)//then more than one element to sort (at least three elements)
        {
            //partition the segment into two segments
            int indexPartition = partition(list, start, end);
            //sort the segment to the left of the partition element
            quickSortSegment(list, start, indexPartition);
            //sort the segment to the right of the partition element
            quickSortSegment(list, indexPartition+1, end);
        }
    }
    
    //use the index start to partition the segment of the list
    //with the element at start as the partition element
    //separating the list segment into two parts,
    //one less than the partition, the other greater than the partition
    //returns the index where the partition element ends up
    private int partition(E[] list, int start, int end)
    {
        E temp;//temporary references to an element for swapping
        E partitionElement = list[start];
        int leftIndex = start;//start at the left end
        int rightIndex = end-1;//start at the right end
        //swap elements so elements at left are less than
        //partition element and at right part are greater
        while(leftIndex < rightIndex)
        {
            //find element starting from left greater than partition
            while(list[leftIndex].compareTo(partitionElement)<=0 && leftIndex<rightIndex)
            {
                leftIndex++;//this index from left greater than partition
            }
            //find element starting from right less than partition
            while(list[rightIndex].compareTo(partitionElement)>0)
            {
                rightIndex--;//this index is on correct side of partition
            }
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
        QuickSort<String> sorter = new QuickSort<>();
        String[] list = {"shoe", "cloth", "bed", "bad", "apple", "box", "chair", "bottle", "fox", "tree"};
        System.out.println("Unsorted list: ");
        for(int i = 0; i < list.length; i++)
        {
            System.out.print(list[i] + ", ");
        }
        sorter.quickSort(list);
        //output the results
        System.out.println();
        System.out.println("Sorted list");
        for(int i = 0; i < list.length; i++)
        {
            System.out.print(list[i] + ", ");
        }
    }
    
}
