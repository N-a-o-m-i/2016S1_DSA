package arraysorter;

/**
 *
 * @author Naomi
 */
public class SelectionSort<E extends Comparable>
{
    private int indexMin;//index of least element
    private E temp;//temporary reference to an element for swapping
    
    public SelectionSort()
    {
        this.indexMin = indexMin;
        this.temp = temp;
    }
    
    public void sort(E[] list)
    {
        for(int i = 0; i < list.length-1; i++)
        {
            //find the least element that has index >= i
            indexMin = i;
            for(int j = i+1; j < list.length; j++)
            {
                if(list[j].compareTo(list[indexMin]) < 0)
                {
                    indexMin = j;
                }
                //swap the element at indexMin with the element at i
                temp = list[indexMin];
                list[indexMin] = list[i];
                list[i] = temp;
            }
        }
    }

    public static void main(String[] args) 
    {
        SelectionSort<String> sorter = new SelectionSort<>();
        String[] list = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant"};
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
