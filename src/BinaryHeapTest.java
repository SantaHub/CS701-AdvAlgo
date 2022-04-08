import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinaryHeapTest {
    public static void main(String[] args) {
        BinaryHeapPriorityQueue<Integer> binaryHeapPQ = new BinaryHeapPriorityQueue<>();

        double size = Math.pow(10,2);
        int i =0;
        List<Integer> randomN = generateRandomN(size);
        System.out.println("Inserting "+size+ " elements into the Binary heap PQ");
        long startTime = System.nanoTime();
        binaryHeapPQ.insert(1);
        binaryHeapPQ.insert(2);
        for(i =2; i < size; i++) {
            binaryHeapPQ.insert(randomN.get(i));
        }
        System.out.println("Construction : "+((System.nanoTime() - startTime) /1000000.0 ) + " ms");
//        System.out.println("Extract Max : "+ binaryHeapPQ.extractMax());
        startTime = System.nanoTime();
        for(i =2; i < size; i++) {
            binaryHeapPQ.increaseKey(i, (int) (size + i));
        }
//        System.out.println("Max size " + (int) (size + i));
//        System.out.println("Extract Max : "+ binaryHeapPQ.extractMax());
//        System.out.println("Testing increase key false condition");
        binaryHeapPQ.increaseKey(8, 45);
        System.out.println("m > n Operations : "+ ((System.nanoTime() - startTime)/1000000.0) + " ms");
    }

    private static List<Integer> generateRandomN(double size) {
        Random rand = new Random(); //instance of random class
        List<Integer> randomInt = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            randomInt.add( rand.nextInt((int)size) );
        }
        return randomInt;
    }
}
