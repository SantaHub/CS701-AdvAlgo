import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BinaryHeapTest {
    public static void main(String[] args) {
        BinaryHeapPriorityQueue<Integer> binaryHeapPQ = new BinaryHeapPriorityQueue<>();
        int size = 100000;
        int[] randomN = generateRandomN(size);
        System.out.println("Inserting "+size+ " elements into the Binary heap PQ");
        long startTime = System.nanoTime();
        binaryHeapPQ.insert(1);
        binaryHeapPQ.insert(2);
        for(int i =2; i < size; i++) {
            binaryHeapPQ.insert(randomN[i]);
        }

        System.out.println("Extract Max : "+ binaryHeapPQ.extractMax());
        System.out.println("Increasing key to 100001");
        binaryHeapPQ.increaseKey(0, 100000);
        binaryHeapPQ.increaseKey(1, 100001);
        System.out.println("Extract Max : "+ binaryHeapPQ.extractMax());
        System.out.println("Testing increase key false condition");
        binaryHeapPQ.increaseKey(8, 45);
        long seconds = TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime),TimeUnit.NANOSECONDS);
        System.out.println("m > n Operations : "+ seconds + " ms");
    }

    private static int[] generateRandomN(int size) {
        Random rand = new Random(); //instance of random class
        int[] randomInt = new int[size];
        for (int i = 0; i < size; i++) {
            randomInt[i] = rand.nextInt(size);
        }
        return randomInt;
    }
}
