package comp2011.lec12;

import comp2011_lec.lec5.List;

/**
 * 
 * @author Yixin Cao (November 20, 2020)
 *
 * A naive implementation of hash table with separate chaining.
 *  
 * This hash table can only store integers, and it never expands.
 * 
 * A version with generics can be found at Section 10.2.4 of the textbook. 
 */
public class SeparateChainning {
    private List<Integer>[] data;
    private final int size;
    
    @SuppressWarnings("unchecked")
    public SeparateChainning(int size) {
        this.size = size;
        data = new List[size];
        for(int i = 0; i < size; i++) 
            data[i] = new List<Integer>();
    }
    
    // very stupid one.
    public int hash(int key) {
        return key % size;
    }
    
    /*
     * worst: O(1).
     */
    public void insert(int key) {
        int h = hash(key);
        data[h].insertFirst(key); // O(1) time.
    }

    /**
     * return a boolean value to indicate whether key is in the hash table.
     * worst: O(n); average: O(1).
     */
    public boolean search(int key) {
        int h = hash(key);
        return (data[h].search(key) != null); 
    }
    
    /**
     * TODO: to make it work, we need to add delete() to class LinkedList.
     * 
     * worst: O(n); average: O(1).
     */
    public void delete(int key) {
        int h = hash(key);
        // data[h].delete(key);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++)
            sb.append("BUCKET " + i + ": " + data[i] + '\n');
        return sb.toString();
    }

    public static void main(String[] args) {
        SeparateChainning table = new SeparateChainning(13);
        table.insert(14);
        table.insert(41);
        table.insert(40);        
        System.out.println("looking for 40: " + table.search(40));
        System.out.println("looking for 42: " + table.search(42));
        System.out.println(table);
    }
}
