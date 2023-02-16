package comp2011.lec6;

import java.util.Arrays;

/**
 * 
 * @author yixin cao (October 6, 2020)
 *
 * Recursive implementations of the three simple sorting algorithms:
 * bubble, insertion, and selection.
 * 
 */
class test{
	static int x=0;
}
public class Sorting {

    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    /*
     * The recursive bubble sort without a flag.
     * We always use a flag for bubble sort.
     * 
     * This method is to demonstrate the simplicity of recursion.
     */
    public static void recursiveBubbleFlagless(int[] a, int end) {
        if (end == 0) return;
        for (int i = 0; i < end; i++) {
            if (a[i] > a[i+1]) {
                swap(a, i, i+1);
            }
        }
        recursiveBubbleFlagless(a, end - 1);
    }
    
    
    /*
     * This version has the flag, which is standard for bubble sort.
     */
    public static void recursiveBubble(int[] a, int end) {
        if (end == 0) return;
        boolean flag = false;
        for (int i = 0; i < end; i++) 
            if (a[i] > a[i+1]) {
                flag = true;
                swap(a, i, i+1);
            }
        if (!flag) return; 
        recursiveBubble(a, end - 1);
    }
    

    /*
     * The recursive insertion sort.
     * 
     * From a student of 2020, not verified.
     * 
     */ 
    public static void recursiveInsertion (int arr[], int n){
        if(n<=1) return;
        recursiveInsertion(arr, n-1);
        int last = arr[n-1];
        System.out.println(last);
        int i = n-2;
        while(i >=0 && arr[i] > last){
            arr[i + 1] = arr[i];
            i--;
        }
        arr[i + 1] = last;
    }
    
    /*
     * The recursive selection sort.
     */ 
    public static void recursiveSelection(int[] a, int begin) {
        if (begin == a.length - 1) return;  //better to keep the base case at the top.
        int n = a.length;
        int min = begin;
        for (int i = begin+1; i < n; i++) 
            if (a[min] > a[i]) min = i;
        swap(a, begin, min);
        recursiveSelection(a, begin+1);
    }
        
    public static void main(String[] args) {
		int a[] = {10, 8, -4, 89, 2, 0};
		System.out.println(Arrays.toString(a));
		// recursiveBubble(a, a.length - 1);
		recursiveInsertion(a, a.length);
		System.out.println(Arrays.toString(a));
    }
}
