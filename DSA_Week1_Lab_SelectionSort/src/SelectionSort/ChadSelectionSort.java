package SelectionSort;

import java.util.*;

public class ChadSelectionSort {
    
    private static ArrayList<Integer> numbers;
    
    static {
        numbers = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            numbers.add(i+1);
        }
        Collections.shuffle(numbers);
    }
    
    public static void main(String args[]) {
        System.out.println("Unsorted 100 numbers:");
        
        for(int i = 0; i < 100; i++) {
            System.out.print(numbers.get(i));
            System.out.print(' ');
        }
        
        System.out.println();
        
        Iterator a = numbers.iterator();
        while (a.hasNext()) {
            System.out.print(a.next());
            System.out.print(' ');
        }
    }
    
}
