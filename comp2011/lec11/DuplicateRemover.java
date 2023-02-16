package comp2011.lec11;

/**
 * 
 * @author Yixin Cao (November 20, 2020)
 *
 * Algorithms for removing/detecting duplicates in a linked list or array.
 * 
 * There is no fundamental difference between a linked list or a array,
 * but whether the input is sorted is crucial.  
 * 
 */
public class DuplicateRemover {
    static class Node {
        int element;
        Node next;

        public Node(int a) {
            element = a;
            next = null;
        }
    }

    /*
     * To remove duplicate elements from a *sorted* linked list. 
     */    
    static void removeDuplicate(Node head) {}
    
    /*
     * To remove duplicate elements from a *sorted* array. 
     */    
    static void removeDuplicate(int[] a) {
    }
    
    /*
     * To remove duplicate elements from a *unsorted* linked list. 
     */    
    static void removeDuplicateUnsorted(Node head) {}
    
    /*
     * To remove duplicate elements from a *unsorted* array. 
     */    
    static void removeDuplicateUnsorted(int[] a) {
    }
    
    /*
     * A way of detecting duplicates that does *NOT* work in general.
     */
    static boolean duplicateChar(String s) {
        int range = 65536; // 256 for ASCII, and 65536 for Unicode.
        boolean[] b = new boolean[range];
        
        if (s.length() > range) return true; // pigeonhole's principle.
        
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            if (b[c]) return true;
            else b[c] = true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        System.out.println(duplicateChar("Hong Kong"));
        System.out.println(duplicateChar("America"));
        System.out.println(duplicateChar("polytechnic"));
        System.out.println(duplicateChar("34003195"));
        System.out.println(duplicateChar("香港\u9999"));
    }

}
