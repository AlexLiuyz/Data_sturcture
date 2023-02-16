package comp2011.lec6;

import java.util.Arrays;

/**
 * 
 * @author Yixin Cao (October 6, 2020)
 *
 * Sorting algorithms, file 2.
 * 
 * The idea of mergesort is very simple, while the implementation is nontrivial.
 * 
 * This class contains three recursive implementations.
 * 
 */
public class Mergesort {

    /*
     * Merging two sorted arrays into one.
     * Both input arrays {@code a1} and {@code a2} are supposed to be already sorted. 
     * 
     * (You may let {@code merge} return the merged array and 
     * write {@code a = merge(b, c);}.
     * But that creates yet another temporary array.)
     */
    public static void mergeArrays(int[] a1, int[] a2, int[] a) {
        int ind1 = 0, ind2 = 0, ind = 0;
        while (ind1 < a1.length && ind2 < a2.length) {
            if ( a1[ind1] <= a2[ind2] ) a[ind++] = a1[ind1++];  // not stable if <= is replaced by <.
            else a[ind++] = a2[ind2++];
            /*
             *  The two lines above can be replaced by 
             *  
             *  a[ind++] = (a1[ind1] <= a2[ind2])? a1[ind1++]:a2[ind2++];
             */
            // 
        }
        // Don't forget the leftovers: we are not done yet!
        while (ind1 < a1.length) a[ind++] = a1[ind1++];
        while (ind2 < a2.length) a[ind++] = a2[ind2++];
    }
    
    /*
     * Merging two sorted parts into one.
     * The first part is from a[l] to a[m]; and the second from a[m + 1] to a[h].
     * The merged result will be stored from a[l] to a[h].
     * 
     * This version reduces it to {@code mergeArrays} above.
     * 
     */
    private static void mergeV1(int[] a, int low, int mid, int high) {
        /*
         * You can use the following lines if you prefer.
         int[] b = Arrays.copyOfRange(a, low, mid + 1);
         int[] c = Arrays.copyOfRange(a, mid + 1, high + 1);
         */
        int[] b = new int[mid - low + 1];  
        int[] c = new int[high - mid]; 
        for (int i = 0; i <= mid - low; i++) b[i] = a[low + i];
        for (int i = 0; i < high - mid; i++) c[i] = a[mid + 1 + i];
    	
        int ind1 = 0, ind2 = 0, ind = low;
        while (ind1 < b.length && ind2 < c.length) 
        	a[ind++] = (b[ind1] <= c[ind2])? b[ind1++]:c[ind2++];
        while (ind1 < b.length) a[ind++] = b[ind1++];
        while (ind2 < c.length) a[ind++] = c[ind2++];
    }

    /*
     * 1st recursive version of mergesort, and its wrapper.
     * This version requires us to build two auxiliary arrays a1 and a2.
     * 
     */
    public static void rMergesortV1(int[] a) {
    	rMergesortV1(a, 0, a.length- 1);
    }
    public static void rMergesortV1(int[] a, int low, int high) {
        if (high <= low) return; // put the base case at the top.

        int mid = low + (high - low) / 2;
        
        rMergesortV1(a, low, mid);
        rMergesortV1(a, mid + 1, high);
        mergeV1(a, low, mid, high);
    }
    
    /*
     * The second recursive version of mergesort.
     * Essentially the same as rMergesortV1, except that 
     * it directly calls <em>mergeArrays</em>.
     * 
     */
    public static void rMergesortV2(int[] a) {
        if (a.length <= 1) return; // put the base case at the top.
        int n = a.length;

        // step 1: partition (almost) evenly;
        int[] b = new int[(n + 1) / 2];  // equivalent to the ceiling of n/2.
        int[] c = new int[n / 2];  // equivalent to the floor of n/2.
        for (int i = 0; i < (n + 1) / 2; i++) b[i] = a[i];
        for (int i = 0; i < n / 2; i++) c[i] = a[(n + 1) / 2 + i];
        /*
         * The four lines above can be replaced by the following two lines
         *  
         *     int[] b = Arrays.copyOfRange(a, 0, (n + 1) / 2);
         *     int[] c = Arrays.copyOfRange(a, (n + 1) / 2, n);
         * 
         * Note: in copyOfRange, the final index of the range is exclusive.
         */

        // step 2: recursively sort the two subarrays;
        rMergesortV2(b);
        rMergesortV2(c);
        
        // step 3: and then merge the sorted subarrays.
        /* (You may let {@code merge} return the merged array 
         * and write a = merge(b, c);.
         * But that creates yet another temporary array.)
         */ 
        mergeArrays(b, c, a);
    }
    
    /*
     * Merging two sorted parts into one.
     * The first part is from a[l] to a[m]; and the second from a[m + 1] to a[h].
     * The merged result will be stored from a[l] to a[h].
     * 
     * We can save half space by only copying the first part (from low to mid).
     */
    private static void merge(int[] a, int low, int mid, int high) {
        /*
         * You can use the following line if you prefer.
         int[] temp = Arrays.copyOfRange(a, low, mid + 1);
         */
        int[] temp = new int[mid - low + 1];
        for (int i = 0; i < temp.length; i ++) 
            temp[i] = a[low + i];
        int i = 0, j = mid+1, k = low;
        while (i < temp.length && j <= high) 
            a[k++] = temp[i] <= a[j]?temp[i++]:a[j++];   // not stable if <= is replaced by <.
        while (i < temp.length) a[k++] = temp[i++];
        // There is no need to deal with the leftovers of the second part. why? 
    }
    
    /*
     * Mergesort version 3: the standard recursive version, and its wrapper.
     * 
     * For an recursive algorithm, it's user-friendly to have a wrap-up 
     * so that the user can simply call mergesortRecursive(a); 
     */
    public static void mergesort(int[] a) {
        mergesort(a, 0, a.length- 1);
    }
    public static void mergesort(int[] a, int low, int high) {
        if (high < 1 + low) return;
        int mid = low + (high - low) / 2;
        mergesort(a, low, mid);
        mergesort(a, mid+1, high);
        merge(a, low, mid, high);
    }
    
    /*
     * Mergesort: iterative version.
     * 
     * Try to write it.  Use the <em>merge</em> method above.
     */
    public static void iMergesort(int[] a) {
    }

    /*
     * In the following two variations,
     * we copy the right part, and start from the largest.
     */
    private static void merge2(int[] a, int low, int mid, int high) {
        int[] temp = Arrays.copyOfRange(a, mid + 1, high + 1); // from mid+1 to high.
        int i = mid, j = temp.length, k = high;
        while (i >= low && j > 0) 
            a[k--] = a[i] > temp[j-1]?a[i--]:temp[--j];
        while (j > 0) a[k--] = temp[--j];
    }
    private static void merge3(int[] a, int low, int mid, int high) {
        int[] temp = Arrays.copyOfRange(a, mid + 1, high + 1); // from mid+1 to high.
        int i = mid, j = temp.length - 1, k = high;
        while (i >= low && j >= 0) 
            a[k--] = a[i] > temp[j]?a[i--]:temp[j--];
        while (j >= 0) a[k--] = temp[j--];
    }
    

    public static void main(String[] args) {
        int testData[][] = { // try different inputs.
                {},
                {1, 1, 1, 1, 1, 1, 1},
                {10, 8, -4, 89, 2, 0, 4, -19, 200},
                {5, 4, 3, 2, 1, 1, 0, 0, -1},
                {10, 8, 14, 89, 32, 50, 77, 38} 
            };
        for (int[] a:testData) {
        	System.out.println("The origianl array: " + Arrays.toString(a));
        	//	rMergesortV1(a);
        	//	rMergesortV2(a);
        	mergesort(a);
        	System.out.println("Sorted: " + Arrays.toString(a));
        }
    }
}

/*
    void mergeArrays(int[] a1, int[] a2, int[] a) {
        int ind1 = , ind2 = , ind = ;
        while (ind1 < a1.length && ind2 < a2.length) {
            if ( a1[ind1] >= a2[ind2] ) a[++ind] = a2[ind2++];
            else a[++ind] = a1[ind1++];  
        }
        while (ind1 < a1.length) a[++ind] = a1[ind1++];
        while (ind2 < a2.length) a[++ind] = a2[ind2++];
    }
    

To make it work, the initial values for ind1, ind2, and ind should be ind1 = [A], ind2 = [B], and ind = [C]. 

If we use this method forst mergesort.  The algorithm is [D] (stable|instable).


 */
