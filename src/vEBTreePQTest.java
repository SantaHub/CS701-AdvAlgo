import java.util.*;
import java.util.concurrent.TimeUnit;

public class vEBTreePQTest {
    public static void main(String[] args) {
        int n = 9900;
        long startTime = System.nanoTime();
        vEBtreePriorityQueue vEBPQ = new vEBtreePriorityQueue((int) Math.pow(5, 10), -1, 1, 0);
        long millisecond = TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime),TimeUnit.NANOSECONDS);
        System.out.println("Construction time of vEB : "+millisecond+ " ms." );

        List<Integer> values = generateRandomN(n);
        List<Integer> priorities = generateRandomN(n);

        startTime = System.nanoTime();
        // Insert
        for (int i = 0; i < n; i++) {
            vEBPQ.insert(values.get(i), priorities.get(i));
        }
        // Extract Max
        AbstractMap.SimpleEntry<Integer, Integer> max = vEBPQ.extractMax();
        System.out.println(String.format("Extracted Max value (%d) with priority (%d)", max.getKey(), max.getValue()));

        // IncreaseKey
        System.out.println("Increasing key for 100 times");
        for(int i=1;i<100;i++){
            max.setValue(i+n);
            vEBPQ.increaseKey(vEBPQ.getRoot(), max);
        }
        max = vEBPQ.extractMax();
        System.out.println(String.format("Extracted Max value (%d) with priority (%d)", max.getKey(), max.getValue()));
        millisecond = TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime),TimeUnit.NANOSECONDS);
        System.out.println(String.format("Running time for the vEB Tree: %d ms", millisecond));

    }


    private static List<Integer> generateRandomN(int size) {
        Random rand = new Random(); //instance of random class
        List<Integer>  randomInt = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            randomInt.add(rand.nextInt(size));
        }
        return randomInt;
    }
}
