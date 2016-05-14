package arraysorter.BubbleSort;

/**
 *
 * @author Naomi
 */
public class BubbleSort<E extends Comparable>
{
    
    public void sort(E[] list)
    {
        E temp;//temporary reference to an element for swapping
        for(int i=list.length-1; i>=0; i--)
        {
            //pass through indices 0...i and bubble (swap) adjacent
            //element if out of order
            for(int j=0; j<i; j++)
            {
                if(list[j].compareTo(list[j+1]) > 0)
                {
                    //swap the elements at indices j and j+1
                    temp = list[j+1];
                    list[j+1] = list[j];
                    list[j] = temp;
                }
            }
        }
    }
    
    public static void main(String[] args)
    {
        BubbleSort<String> sorter = new BubbleSort<>();
        String[] list = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
        System.out.println("Unsorted list: ");
        for(int i = 0; i < list.length; i++)
        {
            System.out.print(list[i] + ", ");
        }
        sorter.sort(list);
        //output the results
        System.out.println();
        System.out.println("Sorted list");
        for(int i = 0; i < list.length; i++)
        {
            System.out.print(list[i] + ", ");
        }
    }
}
