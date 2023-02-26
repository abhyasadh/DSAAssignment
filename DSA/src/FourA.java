import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


//Design and Implement LFU caching
class FourA {

    //A map with key as Integer and value as Node, which stores the actual data value of the cache
    // along with the key. It is used to get the value of a particular key in O(1) time complexity.
    private final Map<Integer, Node> valueMap = new HashMap<>();

    //A map with key as Integer and value as Integer, which stores the frequency count of each key in
    // the cache. It is used to get the frequency of a particular key in O(1) time complexity.
    private final Map<Integer, Integer> countMap = new HashMap<>();

    //A map with key as Integer and value as DoubleLinkedList. The DoubleLinkedList stores the Node
    // objects corresponding to the keys with the same frequency count. The frequencyMap is a sorted
    // map, sorted by the key (frequency count). The frequencyMap is used to get the least frequently
    // used key in O(1) time complexity and to update the frequency of a key.
    private final TreeMap<Integer, DoubleLinkedList> frequencyMap = new TreeMap<>();
    private final int size;

    //size of cache is set
    public FourA(int n) {
        size = n;
    }

    //function to return value if an element is present in cache
    public int get(int key) {
        // this function returns the value associated with the specified key if it exists in the cache.
        // If the key is not found in the cache or the cache size is 0, it returns -1. If the key is
        // found, it removes the node from its current frequency list and moves it to the next one.

        if (!valueMap.containsKey(key) || size == 0) {
            return -1;
        }

        Node nodeToDelete = valueMap.get(key);

        Node node = new Node(key, nodeToDelete.value());

        int frequency = countMap.get(key);
        frequencyMap.get(frequency).remove(nodeToDelete);
        removeIfListEmpty(frequency);
        valueMap.remove(key);
        countMap.remove(key);
        valueMap.put(key, node);
        countMap.put(key, frequency + 1);
        frequencyMap.computeIfAbsent(frequency + 1, k -> new DoubleLinkedList()).add(node);
        return valueMap.get(key).value;
    }

    public void put(int key, int value) {
        // this function adds or updates the key-value pair to the cache. If the key already exists in
        // the cache, it removes the node from its current frequency list and moves it to the next one.
        // If the cache is full, it removes the least frequently used element from the cache.

        if (!valueMap.containsKey(key) && size > 0){

            Node node = new Node(key, value);

            if (valueMap.size() == size) {
                // remove the first node(LRU) from the list the of smallest frequency(LFU)
                int lowestCount = frequencyMap.firstKey();
                Node nodeTodelete = frequencyMap.get(lowestCount).head();
                frequencyMap.get(lowestCount).remove(nodeTodelete);
                removeIfListEmpty(lowestCount);

                int keyToDelete = nodeTodelete.key();
                valueMap.remove(keyToDelete);
                countMap.remove(keyToDelete);
            }
            frequencyMap.computeIfAbsent(1, k -> new DoubleLinkedList()).add(node);
            valueMap.put(key, node);
            countMap.put(key, 1);
        }

        else if(size > 0){
            Node node = new Node(key, value);
            Node nodeToDelete = valueMap.get(key);
            int frequency = countMap.get(key);
            frequencyMap.get(frequency).remove(nodeToDelete);
            removeIfListEmpty(frequency);
            valueMap.remove(key);
            countMap.remove(key);
            valueMap.put(key, node);
            countMap.put(key, frequency + 1);
            frequencyMap.computeIfAbsent(frequency + 1, k -> new DoubleLinkedList()).add(node);
        }
    }

    private void removeIfListEmpty(int frequency) {
        // remove from map if list is empty
        if (frequencyMap.get(frequency).len() == 0) {
            frequencyMap.remove(frequency);
        }
    }

    //----------------------------------------------------------------------------------------------

    //This is a self-defined linked list class that stores two integers as a key value pair
    private static class Node {
        private final int key;
        private final int value;
        Node next;
        Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int key() {
            return key;
        }

        public int value() {
            return value;
        }
    }

    private static class DoubleLinkedList {
        private int n;
        private Node head;
        private Node tail;

        public void add(Node node) {
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
                node.prev = tail;
            }
            tail = node;
            n++;
        }

        public void remove(Node node) {

            if (node.next == null) tail = node.prev;
            else node.next.prev = node.prev;

            if (node.prev == null) head = node.next;
            else node.prev.next = node.next;

            n--;
        }

        public Node head() {
            return head;
        }

        public int len() {
            return n;
        }
    }
    public static void main(String[] args) {
        FourA cache = new FourA(2); // create a cache with capacity of 2

        // add elements to cache
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));    // returns 1
        cache.put(3, 3);                     // evicts key 2
        System.out.println(cache.get(2));    // returns -1 (not found) because cache is of size 2, 1's frequency is highest so it is on top, and 3 was added so 2 being in the bottom gets removed
        System.out.println(cache.get(3));    // returns 3
        cache.put(4, 4);                     // evicts key 1
        System.out.println(cache.get(1));    // returns -1 (not found)
        System.out.println(cache.get(3));    // returns 3
        System.out.println(cache.get(4));    // returns 4
    }

}
