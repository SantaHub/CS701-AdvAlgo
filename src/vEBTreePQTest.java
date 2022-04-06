import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class vEBTreePQTest {
    public static void main(String[] args) {
        int n = 100000;
        long startTime = System.nanoTime();
        vEBtreePriorityQueue vEBPQ = new vEBtreePriorityQueue((int) Math.pow(2, 20), -1, 1, 0);
        List<Integer> values = new ArrayList<>();
        List<Integer> priorities = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            values.add(i);
            priorities.add(i);
        }
        Collections.shuffle(values);
        Collections.shuffle(priorities);
        long buildTime = System.nanoTime() - startTime;
        System.out.println("Constructed the vEB Tree");
        startTime = System.nanoTime();
        for (int i = 1; i < n; i++) {
            int value = values.get(i);
            int priority = priorities.get(i);
            vEBPQ.insert(value, priority);
        }

        AbstractMap.SimpleEntry<Integer, Integer> max = vEBPQ.extractMax();
        System.out.println(String.format("Extracted Max value (%d) with priority (%d)", max.getKey(), max.getValue()));
        System.out.println(String.format("Build Time for the vEB Tree: %d ns", buildTime));
        System.out.println(String.format("Running time for the vEB Tree: %d ns", System.nanoTime() - startTime));

    }
}
