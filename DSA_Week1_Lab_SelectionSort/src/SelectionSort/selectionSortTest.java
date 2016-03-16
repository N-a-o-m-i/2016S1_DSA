package SelectionSort;

import java.util.ArrayList;
import java.util.Random;

public class selectionSortTest {
    
	public static void main(String[] args) {
    	int size = 10;
    	ArrayList<Integer> list = new ArrayList<Integer>(size);
    	for(int i = 1; i <= size; i++) {
            list.add(i);
        }
    	Random rand = new Random();
    	
        System.out.println("Unsorted Numbers");
        while(list.size() > 0) {
            int index = rand.nextInt(list.size());
            System.out.println("Selected: "+list.remove(index));
        }
        System.out.println("Sorted Numbers");
//        list.selectionSort(list);
        
    }
    
    public void selectionSort(ArrayList<Integer> arr)
    {
    	int i, j, minIndex, tmp;
    	int n = arr.size();
    	   	
    	for(i = 0; i < arr.size() - 1; i++)
        {
    		minIndex = i;
        	for(j = i + 1; j<n; j++)
        	{
	    		if(arr.get(j) < arr.get(minIndex))
	        	{
	        		minIndex = j;
	        	}
	        	
	        	if(minIndex != i)
	        	{
	        		tmp = arr.get(i);
	        		arr.set(i, arr.get(minIndex));
	        		arr.set(minIndex, tmp);
	        	}
        	}
        }	
    }
}