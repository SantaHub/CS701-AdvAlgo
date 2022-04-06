import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import vEBtreePriorityQueue;

public class vEBTreePQTest {
    public static void main(String[] args) {
        int n = 100000;
        long startTime = System.nanoTime();
        vEBtreePriorityQueue tree = new vEBtreePriorityQueue((int) Math.pow(2, 20), -1, 1, 0);
        List<Integer> values = new ArrayList<>();
        List<Integer> priorites = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            values.add(i);
            priorites.add(i);
        }
        Collections.shuffle(values);
        Collections.shuffle(priorites);
        long buildTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 1; i < n; i++) {
            int value = values.get(i);
            int priority = priorites.get(i);
            tree.insert(value, priority);
            System.out.println(String.format("INSERT: value(%d), priority(%d)", value, priority));
        }

        AbstractMap.SimpleEntry<Integer, Integer> max = tree.extractMax();
        System.out.println(String.format("EXTRACT MAX VALUE(%d), PRIORITY(%d)", max.getKey(), max.getValue()));
        System.out.println(String.format("BUILD TIME: %d ns", buildTime));
        System.out.println(String.format("RUNNING TIME: %d ns", System.nanoTime() - startTime));

    }
}
