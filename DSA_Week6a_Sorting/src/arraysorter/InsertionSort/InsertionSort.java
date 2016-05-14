package arraysorter.InsertionSort;

/**
 *
 * @author Naomi
 */
public class InsertionSort<E extends Comparable>
{

    public void sort(E[] list)
    {
        E elementInsert;
        for(int i = 0; i < list.length; i++)
        {
            //get the element at index i to insert at some index <= i
            elementInsert = list[i];
            //find index where to insert element to maintain 0...i sorted
            int indexInsert = i;
            while(indexInsert>0 && list[indexInsert-1].compareTo(elementInsert)>0)
            {
                list[indexInsert] = list[indexInsert-1];
                indexInsert--;
            }
            //insert the element
            list[indexInsert] = elementInsert;
        }
    }
    
    public static void main(String[] args)
    {
        InsertionSort<String> sorter = new InsertionSort<>();
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
