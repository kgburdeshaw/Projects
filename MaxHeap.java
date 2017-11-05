package maxheap;
/**
 *
 * @author Kyle Burdeshaw
 */
public class MaxHeap {

    private int[] heap;
    private int swapCount = 0;
    private int lastIndex = 0; //where the last number is being stored
    
    //default constructor
    public MaxHeap(){
        this(100);
    }
    
    //contstructor that specifies heap array size
    public MaxHeap(int heapSize){
        heap = new int[heapSize+1];
    }
 //--------------------ADD-----------------
    public void add(int numberToAdd){
        int parentIndex, currentIndex;
        
        //check length
        if (lastIndex >= heap.length-1)
            incHeapSize();
        
        //increment last index
        lastIndex++;
        
        parentIndex = lastIndex/2;
        currentIndex = lastIndex;
        
        while((parentIndex > 0) && numberToAdd > heap[parentIndex]){
            heap[currentIndex] = heap[parentIndex];
            currentIndex = parentIndex;
            parentIndex = currentIndex / 2;
            swapCount++;
        }
        heap[currentIndex] = numberToAdd;
        
    }
//------------Smart Add--------------
    public void smartAdd(int [] numbersToAdd){
        for(int i = 0; i < numbersToAdd.length; i ++){
            if (lastIndex >= heap.length-1)
                incHeapSize();
            lastIndex++;
            heap[lastIndex] = numbersToAdd[i];
        }
        reheapUp();
    }

//---------------Remove----------------
    public int remove(){
        int temp = heap[1];
        heap[1] = heap[lastIndex];
        lastIndex--;
        reheapDown(1);
        return temp;
    }
//------------Misc Methods-------------
    public int getLastIndex(){
        return lastIndex;
    }
    
    public int getSwapCount(){
        return swapCount;
    }
    
    public void delete(){
        lastIndex = 0;
        swapCount = 0;
    }
    private void incHeapSize(){
        int[] newHeap = new int[heap.length*2];
        for(int i = 1; i < heap.length; i++)
            newHeap[i] = heap[i];
        
        heap = newHeap;
    }
    
    private void reheapDown(int rootIndex){
        int currentNode = 1;    //furthest right internal node
        int rightNode;
        int leftNode;
        while(currentNode <= lastIndex/2){
            leftNode = currentNode*2;
            rightNode = currentNode*2+1;
            if(rightNode > heap.length-1)
                rightNode = leftNode;
            
            if(heap[leftNode] > heap[currentNode] || heap[rightNode] > heap[currentNode]){
                if(heap[leftNode] >= heap[rightNode])
                    swapNodes(currentNode, leftNode);
                else
                    swapNodes(currentNode, rightNode);
                swapCount++;
            }
            currentNode = currentNode+1;
        }
    }
    
    private void reheapUp(){
        int currentNode = lastIndex/2;    //furthest right internal node
        int rightNode;
        int leftNode;
        while(currentNode > 0){
            leftNode = currentNode*2;
            rightNode = currentNode*2+1;
            if(rightNode > heap.length-1)
                rightNode = leftNode;
            
            if(heap[leftNode] > heap[currentNode] || heap[rightNode] > heap[currentNode]){
                if(heap[leftNode] >= heap[rightNode]){
                    swapNodes(currentNode, leftNode);
                    reheapDown(leftNode);
                }
                else{
                    swapNodes(currentNode, rightNode);
                    reheapDown(rightNode);
                }
                swapCount++;
            }
            currentNode = currentNode-1;
        }
        
    }
    
    private void swapNodes(int parentIndex, int childIndex){
        int temp = heap[parentIndex];
        heap[parentIndex] = heap[childIndex];
        heap[childIndex] = temp;
    }
    
    public String firstTen(){
        
        String s = "";
        for(int i = 1; i <=10; i ++)
            s += " " + heap[i];
        return s;
    }
    
    public String toString(){
        String s = "";
        for(int i : heap)
            s += " " + i;
        
        return s;
    }
    

    
}
