public class BinaryHeapPriorityQueue<T> {

    // ExtractMax()
    // Insert(Value, priority)
    // IncreaseKey(value, priority)

    private int heap[] = new int[100];
    private int size = 0;

    public BinaryHeapPriorityQueue(){
        heap[0] = 0; // heap size starting index 1 for easy calculation
        size++;
    }

    private int getParentIndex(int childIndex){
        return childIndex/2;
    }

    private int getLeftChildIndex(int parentIndex){
        return 2* parentIndex;
    }
    private  int getRightChildIndex(int parentIndex){
        return 2*parentIndex+1;
    }

    public void print(){
        for(int i =1; i < size; i++){
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    private void swap(int index1, int index2){
        int element = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = element;
    }

    private void maxHeapify(int[] heap, int index){
        int leftChildIndex = getLeftChildIndex(index);
        int rightChildIndex = getRightChildIndex(index);
        int largest;
        if(leftChildIndex < size && heap[leftChildIndex] > heap[index]){
            largest = leftChildIndex;
        } else {
            largest = index;
        }
        if(rightChildIndex < size && heap[rightChildIndex] > heap[largest]){
            largest = rightChildIndex;
        }
        if(largest != index) {
            swap(index, largest);
            maxHeapify(heap, largest);
        }
    }

    public int extractMax(){
        if (size < 2){ // heap starting from index 1 for me.
            System.out.println("Heap underflow");
            return 0;
        }
        int max = heap[1];
        heap[1] = heap[size-1];
        size =  size-1;
        maxHeapify(heap, 1);
        return max;
    }

    public void increaseKey(int index, int key ){
        if(key < heap[index]){
            System.out.println("New key smaller");
            return ;
        }
        heap[index] = key;
        while(index > 1 && heap[getParentIndex(index)] < heap[index]){
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    public void insert(int key){
        heap[size] = -1000000;
        size++;
        increaseKey(size-1, key);
    }

}
