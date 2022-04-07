import java.util.ArrayList;
import java.util.List;

public class BinaryHeapPriorityQueue<T> {

    // ExtractMax()
    // Insert(Value, priority)
    // IncreaseKey(value, priority)

    private List<Integer> heap = new ArrayList<>();
    private int size = 0;

    public BinaryHeapPriorityQueue(){
        heap.add(0); // heap size starting index 1 for easy calculation
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
            System.out.print(heap.get(i) + " ");
        }
        System.out.println();
    }

    private void swap(int index1, int index2){
        int element = heap.get(index1);
        heap.add(index1, heap.get(index2));
        heap.add(index2, element);
    }

    private void maxHeapify(List<Integer> heap, int index){
        int leftChildIndex = getLeftChildIndex(index);
        int rightChildIndex = getRightChildIndex(index);
        int largest;
        if(leftChildIndex < size && heap.get(leftChildIndex) > heap.get(index)){
            largest = leftChildIndex;
        } else {
            largest = index;
        }
        if(rightChildIndex < size && heap.get(rightChildIndex) > heap.get(largest)){
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
        int max = heap.get(1);
        heap.add(1, heap.get(size-1));
        size =  size-1;
        maxHeapify(heap, 1);
        return max;
    }

    public void increaseKey(int index, int key ){
        if(key < heap.get(index)){
            System.out.println("New key smaller");
            return ;
        }
        heap.add(index, key);
        while(index > 1 && heap.get(getParentIndex(index)) < heap.get(index)){
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    public void insert(int key){
        heap.add(size, -1000000);
        size++;
        increaseKey(size-1, key);
    }

}
