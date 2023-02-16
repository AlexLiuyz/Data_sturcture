package comp2011.lec10;

import java.util.Arrays;

import lec10.max_heap;

/**
 * 
 * @author Yixin Cao (October 28, 2021)
 *
 * Two applications of heaps.
 * Both are trivial when the array is sorted. 
 * The main difficulty is how to solve the problem without sorting.
 * 
 * You need to write a heap data structure storing ints.
 * Think carefully: do you need a maximum heap or a minimum heap?
 * 
 */
public class Applications {
    /*
     * Find the kth smallest element from an unsorted array.
     */
    static int kth(int[] a, int k) {
    	int n=a.length;
    	if(k>n) {
    		return -1;
    	}
    	MaxHeap heap=new MaxHeap(k);
    	for(int i=0;i<k;i++) {
    		heap.insert(a[i]);
    	}
    	for(int i=k;i<n;i++) {
    		if(a[i]<heap.peek()) {
    			heap.setRoot(a[i]);
    			heap.down(0);
    		}
    	}
    	return heap.peek();
    }
    
    /*
     * Find the difference between the k largest elements and the k smallest elements.
     */
    static int wealthGap(int[] a, int k) {
        int n = a.length;
        if (n < k) return -1;  // IllegalInput
        MaxHeap smallest = new MaxHeap(k);
        MaxHeap largest = new MaxHeap(k); // I'm really lazy.
        for (int i = 0; i < k; i++) {
            smallest.insert(a[i]);
            largest.insert(-a[i]);
        }
        for (int i = k; i < n; i++) {
            if (smallest.peek() > a[i]) {
                smallest.setRoot(a[i]);
                smallest.down(0);
            }
            else if (largest.peek() > -a[i]) {
                largest.setRoot(-a[i]);
                largest.down(0);
            }
        }
        int max = 0, min = 0;
        for (int i = 0; i < k; i++) {
            // you can add a <em>sum</em> method to Heap.
            max -= largest.removeMax();
            min += smallest.removeMax();
        }

        return max - min;
    }
    
    public static void main(String args[]){
        int testData[][] = { // try different inputs.
                {},
                {1, 1, 1, 1, 1, 1, 1},
                {10, 8, -4, 89, 2, 0, 4, -19, 200},
                {5, 4, 3, 2, 1, 1, 0, 0, -1},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
                {1, 3, 2, 6, 7, 5, 4, 12, 13, 15, 14, 10, 11, 9, 8},
                {3, 2, 6, 13, 8, 4, 10, 7, 14, 11, 12, 5, 9},
                {10, 8, 14, 89, 32, 50, 77, 38},
                {65, 85, 17, 88, 66, 71, 45, 38, 95, 48, 18, 68, 60, 96, 55}
            };
        for (int[] a:testData) {
            System.out.println("The original array: " + Arrays.toString(a));
            int k = 5;
            System.out.println("No." + k + " is: " + kth(a, k));
            System.out.println("the wealth gap between richest " + k + " and poorest " + k + " is: " + wealthGap(a, k));
            System.out.println();
        }
    }
}
