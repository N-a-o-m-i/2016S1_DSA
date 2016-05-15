package specializedsorting.RadixSort;

/**
 *
 * @author Naomi
 */
public class RadixSort 
{
    
    public static void main(String[] args)
    {
        //three-digit non-negative integers
        int[] list = {73, 521, 135, 291, 550, 153, 913, 910, 770, 321, 39, 125};
        final int NUM_DIGITS = 3;
        //create the 10 queues
        LinkedQueue<Integer>[] radixQueues = (LinkedQueue<Integer>[]) new LinkedQueue[10];//unchecked
        for(int queueNum=0; queueNum<10; queueNum++)
            radixQueues[queueNum] = new LinkedQueue<Integer>();
        //use radix sort on each of the digits
        int pwrOfTen = 1;//pwrOfTen = 10^digit
        int queueNum;//determines which queue to use for a value
        for(int digit=0; digit<NUM_DIGITS; digit++)
        {
            //parse through all the values in the list and enqueue each
            //into the appropriate queue
            for(int i=0; i<list.length;i++)
            {
                //extract digit from value to determine which queue to use
                queueNum = (list[i]/pwrOfTen)%10;
                radixQueues[queueNum].enqueue(new Integer(list[i]));
            }
            //dequeue each queue in turn and put values back into list
            int nextIndex = 0;
            for(queueNum=0; queueNum<10;queueNum++)
            {
                while()
                {
                    
                }
            }
            
        }
        
        
        
    }
    
}
