package comp2011.lec12;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author Yixin Cao (November 20, 2020)
 *
 * A naive implementation of hash table with linear probing, 
 * for the purpose of demonstration.
 * 
 * This hash table can only store positive integers.
 * 
 * A version with generics can be found at Section 10.2.4 of the textbook. 
 */
public class LinearProbingHash {
    private int M;
    private int[] data;
    private boolean[] tombstone;
    private int size = 0;
    
    LinearProbingHash(int initialCapacity) {
        this.M = initialCapacity;
        data = new int[M];
        tombstone = new boolean[M];
        // initial every entry to be -1 (meaning null).
        Arrays.fill(data, -1); 
        // The following can be omitted, because Java always do this during its initialization.
        Arrays.fill(tombstone, false); 
    }
    
    public void insert(int key) {
        // Actually, we should take action once size/M >= 0.5.
    	if(size==M) {
    		System.out.println("overflow");
    		return;
    	}
    	int i;
    	for(i=hash(key);!tombstone[i]&&data[i]!=-1;i=(i+1)%M) {
    		if(data[i]==key) {
    			break;
    		}
    	}
    	data[i]=key;
    	tombstone[i]=false;
    	size++;
    }
    public int search(int key) {
    	if(size==M) {
    		for(int i=0;i<M;i++) {
    			if(data[i]==key) {
    				return i;
    			}
    		}
    	}
    	else {
    		for(int i=hash(key);tombstone[i]||data[i]!=-1;i=(i+1)%M) {
    			if(!tombstone[i]&&data[i]==key) {
    				return i;
    			}
    		}
    	}
    	return -1;
    }

    public void delete(int key) {
    	int i;
    	if(size==M) {
    		for(i=0;i<M;i++) {
    			if(data[i]==key) {
    				break;
    			}
    		}
    	}
    	else {
    		for(i=hash(key);tombstone[i]||data[i]!=-1;i=(i+1)%M) {
    			if(data[i]==key&&!tombstone[i]) {
    				break;
    			}
    		}
    	}
    	if(data[i]!=key) {
    		return;
    	}
    	data[i]=-1;
    	tombstone[i]=true;
    }
    
    int hash(int key) { 
        return key % M;
    }
    
    public static void main(String[] args) {
        // For demonstration, we use a small table with few elements.
        LinearProbingHash table = new LinearProbingHash(13);
        int[] subjects = {2012, 4141, 4142, 4146, 4334, 4342, 4431, 4433, 4434, 4435, 4531, 4921};
        for (int s:subjects) {
            table.insert(s);
        }
        table.delete(2012);
        // table.search();
        System.out.println(Arrays.toString(table.data));
        System.out.println(Arrays.toString(table.tombstone));
        table.insert(2011);
        System.out.println(Arrays.toString(table.data));        
        System.out.println(Arrays.toString(table.tombstone));
        //loadFactorTest();
    }

    /**
     * Count the lengths of all the nontrivial clusters in the hash table. 
     */
    int[] clusters() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int[] ans = {M};
        if (size == M) return ans;
        int last = M - 1;
        for (; data[last] != -1; last--) ;
        int i = 0;
        int c = M - 1 - last;
        while (i < last) {
            for (; i < last && (tombstone[i] || data[i] != -1); i++) c++;
            if (c > 1) list.add(c);
            c = 0;
            i++;
        }
        ans = new int[list.size()];
        for (int j = 0; j < list.size(); j++) ans[j] = list.get(j);
        return ans;
    }
    
    /*
     * Tests on impacts of the load factor on the cluster lengths.
     * 100 (TURNS) times for load factor 40/97 and 
     * 100 times for load factor 49/97 (slightly greater than 0.5).
     * 
     * The keys are between 1 and 2^30 (RANGE).
     */
    static void loadFactorTest() {
        int TURNS = 100; int RANGE = 1<<30;
        System.out.println("RANGE: " + RANGE);
        SecureRandom random = new SecureRandom();

        System.out.println("Part I: testing of low load factor, 40/97.");
        for (int i = 0; i < TURNS; i++) {
            LinearProbingHash table = new LinearProbingHash(97);
            for (int j = 0; j < 40; j++) {
                table.insert(random.nextInt(RANGE));
            }
            System.out.println(Arrays.toString(table.clusters()));
        }

        System.out.println("\n\nPart II: testing of high load factor, 49/97.");
        for (int i = 0; i < TURNS; i++) {
            LinearProbingHash table = new LinearProbingHash(97);
            for (int j = 0; j < 49; j++) {
                table.insert(random.nextInt(RANGE));
            }
            System.out.println(Arrays.toString(table.clusters()));
        }
    }
}
