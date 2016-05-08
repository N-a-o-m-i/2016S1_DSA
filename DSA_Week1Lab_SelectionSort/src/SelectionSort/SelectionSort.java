package SelectionSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SelectionSort {
    
    private static ArrayList<Integer> numbers;
    private static final int SIZE = 100;
    
    static{
        numbers = new ArrayList();
        for(int i = 1; i < (SIZE+1); i++){
            numbers.add(i);
        }
        Collections.shuffle(numbers);
    }
    
    public static void selectionSort(ArrayList<Integer> numbers)
    {
        for(int i = 0; i < SIZE; i++){//the 'i'th sort
            int k = i;
            for(int j = k; j < SIZE; j++){
                if(numbers.get(j) < numbers.get(k)){
                    k = j;//record the index of the min number we find now
                }
            }
            
            //after find the min number of this loop, swap the number
            if(i != k){//swap the number of position i and k
                int temp = numbers.get(i);
                numbers.set(i, numbers.get(k));
                numbers.set(k, temp);
            }
        }
    }
    
    public static void main(String[] args){
        System.out.println("Unsorted " + SIZE + " numbers: ");
        System.out.print(numbers);
        selectionSort(numbers);
        
        System.out.println("Sorted numbers: ");
        System.out.print(numbers);
    }
}
