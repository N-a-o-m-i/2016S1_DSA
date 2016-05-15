package specializedsorting.BinSort;

/**
 *
 * @author Naomi
 */
public class BinSort 
{
    public static void main(String[] args)
    {
        //distict integer values between 0 and MAX_VALUE
        int[] list = {17, 2, 23, 7, 41, 29, 19, 43, 31, 5, 11, 47, 13, 3, 37};
        final int MAX_VALUE = 50;
        boolean[] flags = new boolean[MAX_VALUE+1];//initially all false
        //determine which bins should be set to true
        for(int i=0; i<list.length;i++)
        {
            flags[list[i]] = true;
        }
        //use the flags to put the numbers back in the list sorted
        int flagNo = 0;
        for(int i=0; i<list.length;i++)
        {
            //find the next flag that is true
            while(flagNo<flags.length && !flags[flagNo])
            {
                flagNo++;
            }
            list[i] = flagNo++;
        }
        //output the results
        for(int i=0; i<list.length;i++)
            System.out.print(((i>0)? ", ":"") + list[i]);
        System.out.println();
    }
    
}
