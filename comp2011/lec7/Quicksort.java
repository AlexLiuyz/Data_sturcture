package comp2011.lec7;

import java.util.*;
import java.security.SecureRandom;

/**
 * 
 * @author Yixin Cao (October 10, 2020)
 *
 * Sorting algorithms, file 3.
 * 
 * The focus of this week is quicksort. 
 * Its idea is very simple, while the in-place implementation is very challenging.
 * 
 * There are two ways to partition, the Hoare partition scheme and the Lomuto partition scheme. 
 * 
 */
public class Quicksort {
    /*
     * A naive implementation of quicksort and its wrapper.
     * It uses extra space for partition.  Similar as the first version of binarySort.
     * 
     * It's easy to write and let you have the basic ideas of quicksort.
     * Running time: O(n^2).
     */
   private static void naive(int[] a, int low, int high) {
        if(low >= high) return;
        int pivot = a[high];
        int[] b = new int[high - low + 1];
        for (int i = 0; i < b.length; i++)
           b[i] = a[low + i];
        /* 
        int[] b = Arrays.copyOfRange(a, begin, end + 1);
         */
        int l = low, r = high;
        for (int i = 0; i < b.length; i++) {
            if (b[i] <= pivot) a[l++] = b[i];
            else a[r--] = b[i];
        }
        System.out.println(Arrays.toString(a));
        // It would NOT work if: naive(a, begin, l - 1);
        naive(a, low, l - 2); // also ok r - 1.
        naive(a, r + 1, high);   // also ok r.
    }
   
    public static void naiveQuicksort(int[] a) {
            naive(a, 0, a.length - 1);
    }

    /*
     * An in-place implementation of quicksort with the lomuto partition scheme.
     */
    @SuppressWarnings("unused")
	private static void lomuto(int[] a, int low, int high) {
    	
    	
    }
    
    /*
     * An in-place implementation of quicksort with the hoare partition scheme.
     */
    @SuppressWarnings("unused")
    private static void quicksort() {
    	
    }
	private static void hoare(int[] a, int low, int high) {
    	
    }
    
    public static void quicksort(int[] a) {
        lomuto(a, 0, a.length - 1); // or hoare.
    }
    
    public static void main(String args[]){
        int testData[][] = { // try different inputs.
                {},
                {1, 1, 1, 1, 1, 1, 1},
                {10, 8, -4, 89, 2, 0, 4, -19, 200},
                {5, 4, 3, 2, 1, 1, 0, 0, -1},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
                {1, 3, 2, 6, 7, 5, 4, 12, 13, 15, 14, 10, 11, 9, 8},
                {10, 8, 14, 89, 32, 50, 77, 38} 
            };
        for (int[] a:testData) {
            System.out.println("The original array: " + Arrays.toString(a));
            naiveQuicksort(a);
            System.out.println("Sorted: " + Arrays.toString(a));
        }
        /* 
        int size = 100;
        int[] a = new int[size];
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < size; i++)
            a[i] = random.nextInt(size);
         */
    }

    void dummy() {
        int[] a = null;
        String[] s = null;
        Arrays.sort(a); // click me, with CTRL pressed.
        Arrays.sort(s); // click me, with CTRL pressed.
    }
}
