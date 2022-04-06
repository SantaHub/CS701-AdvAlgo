import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class vEBtreePriorityQueue {

    class Node {
        public int u;
        public SimpleEntry<Integer, Integer> min;
        public SimpleEntry<Integer, Integer> max;
        public Node summary;
        public Node[] cluster;

        public Node(int u) {
            this.u = u;
            min = NIL;
            max = NIL;

            initialize(u);
        }

        private void initialize(int u) {
            if (u <= 2) {
                summary = null;
                cluster = null;
            } else {
                int size = higherSquareRoot();

                summary = new Node(size);
                cluster = new vEBtreePriorityQueue.Node[size];

                for (int i = 0; i < size; i++) {
                    cluster[i] = new Node(size);
                }
            }
        }

        /**
         * Higher Square Root
         */
        private int higherSquareRoot() {
            return (int) Math.pow(2, Math.ceil((Math.log10(u) / Math.log10(2)) / 2));
        }
    }

    // Define NIL value to initialize min, max;
    private SimpleEntry<Integer, Integer> NIL;
    private T ONE;
    private T ZERO;
    private Node root;

    /*
     * Construction method
     */
    public vEBtreePriorityQueue(int u, T NIL, T ONE, T ZERO) throws Exception {
        this.NIL = new SimpleEntry<Integer, Integer>(NIL, NIL);
        this.ONE = ONE;
        this.ZERO = ZERO;
        if (!isPowerOf2(u)) {
            throw new Exception("Tree size must be a power of 2!");
        }
        root = new Node(u);
    }

    /*
     * Insert x
     */
    public void insert(T value, T priority) {
        insert(root, value, priority);
    }

    /*
     * Delete x
     */
    public boolean decreaseKey(T value, T priority) {
        return decreaseKey(root, new SimpleEntry<Integer, Integer>(value, priority));
    }

    /*
     * Returns the maximum value in the tree or -1 if the tree is empty.
     */
    public SimpleEntry<Integer, Integer> extractMax() {
        SimpleEntry<Integer, Integer> max = root.max;
        decreaseKey(max.getKey(), max.getValue());
        return max;
    }

    private void insertEmptyNode(Node v, SimpleEntry<Integer, Integer> s) {
        v.min = s;
        v.max = s;
    }

    /**
     * Insert new value to vEBTree
     *
     * @param v        Root
     * @param priority value to insert
     */
    private void insert(Node v, T value, T priority) {
        SimpleEntry<Integer, Integer> x = new SimpleEntry<Integer, Integer>(value, priority);
        if (v.min == null) {
            insertEmptyNode(v, x);
            return;
        }

        increaseKey(v, x);

    }

    private void increaseKey(Node v, SimpleEntry<Integer, Integer> x) {
        if (compareTo(x, v.min) < 0) {
            // Exchange x with v.min
            SimpleEntry<Integer, Integer> temp = x;
            x = v.min;
            v.min = temp;
        }
        if (v.u > 2) {
            if (v.cluster[high(v, x)].min == null) {
                insert(v.summary, x.getKey(), (T) Integer.valueOf(high(v, x)));
                insertEmptyNode(v.cluster[(int) high(v, x)], new SimpleEntry<Integer, Integer>(x.getKey(), low(v, x)));
            } else {
                insert(v.cluster[(int) high(v, x)], x.getKey(), low(v, x));
            }
        }

        if (compareTo(x, v.max) > 0) {
            v.max = x;
        }
    }

    /**
     * Compare 2 elements of heap
     *
     * @param min
     * @param max
     * @return
     */
    private int compareTo(SimpleEntry<Integer, Integer> min, SimpleEntry<Integer, Integer> max) {

        if (min.getValue().equals(max.getValue())) {
            return min.getKey().compareTo(max.getKey());
        }
        return min.getValue().compareTo(max.getValue());
    }

    /**
     * Compare 2 elements of heap equals or not
     *
     * @param x
     * @param min
     * @return
     */
    private boolean equals(SimpleEntry<Integer, Integer> x, SimpleEntry<Integer, Integer> min) {
        if (x == null || min == null) {
            return false;
        }
        return x.getKey().equals(min.getKey()) && x.getValue().equals(min.getValue());
    }

    private boolean decreaseKey(Node v, SimpleEntry<Integer, Integer> x) {
        if (compareTo(v.min, v.max) == 0) {
            v.min = NIL;
            v.max = NIL;
            return false;
        }
        if (v.u == 2) {
            v.min = ZERO.equals(x.getValue()) ? new SimpleEntry<Integer, Integer>(x.getKey(), ONE)
                    : new SimpleEntry<Integer, Integer>(x.getKey(), ZERO);
            v.max = v.min;
            return false;
        }
        if (!equals(x, v.min)) {
            return false;
        }

        SimpleEntry<Integer, Integer> first_cluster = v.summary.min;
        T priority = index(v, first_cluster, v.cluster[(Integer)first_cluster.getValue()].min);
        v.min = new SimpleEntry<Integer, Integer>(x.getValue(), priority);

        decreaseKey(v.cluster[(int) high(v, x)], new SimpleEntry<Integer, Integer>(x.getKey(), low(v, x)));
        if (v.cluster[(int) high(v, x)].min == null) {
            decreaseKey(v.summary, new SimpleEntry<Integer, Integer>(x.getKey(), (T) Integer.valueOf(high(v, x))));
            if (equals(x, v.max)) {
                SimpleEntry<Integer, Integer> summary_max = v.summary.max;
                if (summary_max == null) {
                    v.max = v.min;
                } else {
                    priority = index(v, summary_max, v.cluster[ (Integer) summary_max.getValue()].max);
                    v.max = new SimpleEntry<Integer, Integer>(x.getValue(), priority);
                }
            }
        } else if (equals(x, v.max)) {
            priority = index(v, new SimpleEntry<Integer, Integer>(x.getValue(), (T) Integer.valueOf(high(v, x))), v.cluster[(int) high(v, x)].max);
            v.max = new SimpleEntry<Integer, Integer>(x.getValue(), priority);
        }
        return true;

    }

    /*
     * Returns the integer value of the first half of the bits of x.
     */
    private int high(Node node, SimpleEntry<Integer, Integer> x) {
        return  Integer.valueOf((int) Math.floor((Integer) x.getValue() / lowerSquareRoot(node)));
    }

    /**
     * The integer value of the second half of the bits of x.
     */
    private T low(Node node, SimpleEntry<Integer, Integer> x) {
        return (T) Integer.valueOf((Integer) x.getValue() % (int) lowerSquareRoot(node));
    }

    /**
     * The value of the least significant bits of x.
     */
    private double lowerSquareRoot(Node node) {
        return Math.pow(2, Math.floor((Math.log10(node.u) / Math.log10(2)) / 2.0));
    }

    /**
     * The index in the tree of the given value.
     */
    private T index(Node node, SimpleEntry<Integer, Integer> first_cluster, SimpleEntry<Integer, Integer> min) {
        return (T) Integer.valueOf((int) ((Integer) first_cluster.getValue() * lowerSquareRoot(node) + (Integer) min.getValue()));
    }

    /**
     * Returns true if x is a power of 2, false otherwise.
     */
    private static boolean isPowerOf2(int x) {
        if (0 == x) {
            return false;
        }

        while (x % 2 == 0) {
            x = x / 2;
        }

        if (x > 1) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        int n = 100000;
        long startTime = System.nanoTime();
        vEBtreePriorityQueue<Integer> tree = new vEBtreePriorityQueue<Integer>((int) Math.pow(2, 20), -1, 1, 0);
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

        SimpleEntry<Integer, Integer> max = tree.extractMax();
        System.out.println(String.format("EXTRACT MAX VALUE(%d), PRIORITY(%d)", max.getKey(), max.getValue()));
        System.out.println(String.format("BUILD TIME: %d ns", buildTime));
        System.out.println(String.format("RUNNING TIME: %d ns", System.nanoTime() - startTime));
    }
}