package comp2011.lec12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

/**
 * 
 * @author Yixin Cao (November 24, 2021)
 *
 * Some nontrivial applications of hash tables. 
 * 
 * While you can easily find solutions to them on the Internet, 
 * you are strongly suggested to work them out by yourself, with
 * the hints provided in the slides.
 * 
 * They are in a sense best exercises for you practice.
 * If you read the solutions directly, you are squandering them.
 */
public class Applications {

    /*
     * Find the first element occurring k times
     */
    static int firstElement(int a[], int n, int k) { 
        return -1; 
    } 

    /*
     * Check if a given array contains duplicate elements within k distance from each other
     * 
     */
    static boolean checkDuplicatesWithinK(int a[], int k) { 
        return false; 
    }     
    
    /*
     * Find maximum distance between two occurrences of a same element
     * 
     */
    static int maxDistance(int[] a) { 
        return -1; 
    }
  
    /*
     * Group elements, ordered by first occurrence
     * 
     * [4, 6, 9, 2, 3, 4, 9, 6, 10, 4] -> [4, 4, 4, 6, 6, 9, 9, 2, 3, 10]
     * [5, 3, 5, 1, 3, 3] -> [5, 5, 3, 3, 3, 1]
     */
    static void orderedGroup(int a[]) { 
    } 
    
    /*
     * Are two given sets disjoint?
     * 
     */
    static boolean areDisjoint(int set1[], int set2[]) {
        return false; 
    }

    /*
     * Find symmetric pairs
     */
    static void findSymPairs(int a[][]) { 
    } 
    
    public static void main(String[] args) {
        int[] a = {3, 2, 1, 2, 1, 4, 5, 8, 6, 7, 4, 2}; 
        System.out.println("maxDistance " + maxDistance(a)); 

        int b[] = {10, 5, 3, 7, 4, 3, 5, 6};         
        System.out.println("checkDuplicatesWithinK " + checkDuplicatesWithinK(a, 3));
        System.out.println("checkDuplicatesWithinK " + checkDuplicatesWithinK(b, 2));

        int ar[] = {1, 7, 4, 3, 4, 8, 7};
        System.out.println("firstElement " + firstElement(a, a.length, 4));
        
        int a2[][] = {{20, 11}, {30, 40}, {5, 10}, {20, 20}, {40, 30}, {11, 20}, {10, 5}}; 
        findSymPairs(a2);
        
        int arr[] = {10, 5, 3, 5, 10, 4, 1, 3, 5}; 
        orderedGroup(arr);
        System.out.println("\norderedGroup "); 
    }
}
