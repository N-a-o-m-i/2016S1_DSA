package Others;

import java.util.ArrayList;
import java.util.Collections;

public class NaomiSelectionSort {
    private static ArrayList<Integer> numbers;
    private static final int SIZE = 100;
    
    static
    {
        numbers = new ArrayList<Integer>();
        for(int i=0; i<SIZE; i++)
        {
            numbers.add(i+1);
        }
        Collections.shuffle(numbers);
    }
    
    public static void main(String args[])
    {
        System.out.println("unsorted " + SIZE + " numbers:");
        for(int i=0; i<SIZE; i++)
        {
            if((i+1) % 20 == 0)
            {
                System.out.println("");
            }
            System.out.print(numbers.get(i) + " ");
        }
        System.out.println("\n" + "sorted " + SIZE + " numbers: ");
        selectionSort();
        for(int i=0; i<SIZE; i++)
        {
            if((i+1) % 20 == 0)
            {
                System.out.println("");
            }
            System.out.print(numbers.get(i) + " ");
        }
    }
    
    public static void selectionSort()
    {     
        for(int i=0; i<SIZE; i++)
        {
            int temp = numbers.get(i);
            int position = i;
            
            for(int j=i+1; j<SIZE;j++)
            {
                if(temp > numbers.get(i))
                {
                    temp = numbers.get(j);
                    position = j;
                }
            }
            numbers.set(i, numbers.get(i));
            numbers.set(i,temp);
        }
        
    }
}
