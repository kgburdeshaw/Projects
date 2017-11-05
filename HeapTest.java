package maxheap;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author Kyle Burdeshaw
 */
public class HeapTest {
    
    public static void main(String [] args){
        
        MaxHeap heap = new MaxHeap(101);
        MaxHeap smartHeap = new MaxHeap(101);
        int[] heapVals = new int[100];
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        
    //-----------Menu--------------
        System.out.print("Please select how to test the program: "
                        + "\n(1) 20 sets of 100 randomly generated integers"
                        + "\n(2) Fixed integer values 1-100"
                        + "\n Enter choice: ");
        int input = sc.nextInt();
        
        switch(input){
            
            case 1 :
                int swapSums = 0, smartSwapSums = 0;
                int temp;

                for(int j = 0; j < 20; j++){
                    for(int i = 0; i < 100; i ++){
                        temp = Math.abs(rand.nextInt());
                        heap.add( temp );
                        heapVals[i] = temp;
                    }
                    swapSums += heap.getSwapCount();
                    smartHeap.smartAdd(heapVals);
                    smartSwapSums += smartHeap.getSwapCount();
                    heap.delete();
                    smartHeap.delete();
                }

                float avgSwaps = swapSums / 20f;
                float smartAvgSwaps = smartSwapSums / 20f;
                System.out.print("Average swaps for series of insertions: " + avgSwaps
                                    + "\nAverage swaps for optimal method " + smartAvgSwaps);
                break;
            
            case 2 :
                heap.delete();
                smartHeap.delete();
                for(int i = 1; i <= 100; i ++){
                    heap.add( i );
                    heapVals[i-1] = i;
                }
                smartHeap.smartAdd(heapVals);

                System.out.print("\nHeap using series of insertion: " + heap.firstTen() + "..."
                                    + "\nNumber of swaps: " + heap.getSwapCount()
                                    + "\nHeap after 10 removals: ");
                for(int i = 0; i < 10; i ++)
                    heap.remove();
                System.out.println(heap.firstTen() + "...");

                System.out.print("\nHeap using optimal method: " + smartHeap.firstTen() + "..."
                                    + "\nNumber of swaps: " + smartHeap.getSwapCount()
                                    + "\nHeap after 10 removals: ");
                for(int i = 0; i < 10; i ++)
                    smartHeap.remove();
                System.out.println(smartHeap.firstTen() + "...");
                break;
        } 
    }
    
    
}
