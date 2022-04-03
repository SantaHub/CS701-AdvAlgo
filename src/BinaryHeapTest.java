public class BinaryHeapTest {
    public static void main(String[] args) {
        BinaryHeapPriorityQueue<Integer> binaryHeap = new BinaryHeapPriorityQueue<>();
        binaryHeap.insert(12);
        binaryHeap.insert(14);
        binaryHeap.insert(16);
        binaryHeap.insert(13);
        binaryHeap.print();

        System.out.println("Max : "+ binaryHeap.extractMax());
        binaryHeap.print();
        binaryHeap.increaseKey(2, 16);
        binaryHeap.print();
    }
}
