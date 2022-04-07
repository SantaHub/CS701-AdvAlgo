import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BinaryHeapTest {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        BinaryHeapPriorityQueue<Integer> binaryHeapPQ = new BinaryHeapPriorityQueue<>();
        long millisecond = TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime),TimeUnit.NANOSECONDS);
        System.out.println("Construction : "+ millisecond + " ms");

        double size = Math.pow(4,10);
        List<Integer> randomN = generateRandomN(size);
        System.out.println("Inserting "+size+ " elements into the Binary heap PQ");
        startTime = System.nanoTime();
        binaryHeapPQ.insert(1);
        binaryHeapPQ.insert(2);
        for(int i =2; i < size; i++) {
            binaryHeapPQ.insert(randomN.get(i));
        }

        System.out.println("Extract Max : "+ binaryHeapPQ.extractMax());
        System.out.println("Increasing key to 100001");
        binaryHeapPQ.increaseKey(0, 100000);
        binaryHeapPQ.increaseKey(1, 100001);
        System.out.println("Extract Max : "+ binaryHeapPQ.extractMax());
        System.out.println("Testing increase key false condition");
        binaryHeapPQ.increaseKey(8, 45);
        millisecond = TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime),TimeUnit.NANOSECONDS);
        System.out.println("m > n Operations : "+ millisecond + " ms");
    }

    private static List<Integer> generateRandomN(double size) {
        Random rand = new Random(); //instance of random class
        List<Integer> randomInt = new ArrayList<>();

        int bound = 100000;
        for (int i = 0; i < size; i++) {
            randomInt.add( rand.nextInt(bound) );
        }
        return randomInt;
    }
}
