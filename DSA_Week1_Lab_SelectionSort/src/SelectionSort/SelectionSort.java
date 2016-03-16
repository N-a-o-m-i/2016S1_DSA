package SelectionSort;

import java.util.Random;

public class SelectionSort {
	
    public static final void main(String[] args)
	{	
            final int SIZE = 10;
            int[] values = new int[SIZE];     
            System.out.println("Unsorted "+ SIZE +" numbers: ");
            
            
            
            for(int i=0;i<values.length;i++)
            {
                Random pickedNumber = new Random(); 
                values[i] = pickedNumber.nextInt(SIZE) + 1;
                
                
                for(int j=0; j<values.length; j++)
                {
                    Random testNumber = new Random();
                    values[i] = testNumber.nextInt(SIZE) + 1;
                    
                    if(i==0)
                    {
                        values[i] = pickedNumber.nextInt(SIZE) + 1;
                    }else if(i > 0 && values[i] != values[j])
                    {
                        values[i] = values[j];
                    }
                }
                System.out.println("The " + (i+1) + " number is :" + values[i]);
            }
            
             
        }
    
//    public static int[] random(int[] arr)
//    {
//        final int SIZE = 10;
//        int[] array = new int[SIZE];
//        
//        for(int i=0; i<array.length; i++)
//        {
//            Random rn = new Random();
//            int number = rn.nextInt(10) + 1;
//            for(int j=0; j<array.length;j++)
//            {
//                if(number != array[j])
//                {
//                    array[i] = number;
//                }
//            }
//            
//        }
//        
////        for(int i=0; i<10; i++)
////        {
////            int tmp;
////            tmp = array[i];
////            Random rn = new Random();
////            array[i] = array[rn.nextInt(10)];
////            System.out.println(array[i]);
////        }  
//        return arr;
//    }	
}
