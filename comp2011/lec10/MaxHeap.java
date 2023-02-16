package comp2011.lec10;

import java.util.Arrays;

/*
 * @author Yixin Cao (October 20, 2021)
 *
 * A maximum heap data structure storing ints.
 * It has some non-standard operations. Pay attention.
 */
public class MaxHeap {
    private int[] data;
    private int size; 
    
    public MaxHeap(int capacity) {
        data = new int[capacity];
    }
    
    private void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
    
    public void down(int p) {
        if (p * 2 + 2 > size) return;
        int larger = p * 2 + 1;
        if (larger + 1 < size && data[larger] < data[larger+1]) 
            larger++;
        if (data[p] >= data[larger]) return;
        swap(p, larger);
        down(larger);
    }
    
    public void up(int index) {
        int parent = (index - 1) / 2;
        if (index == 0 || data[parent] > data[index]) return;
        swap(index, parent);
        up(parent);
    }    

    private void err(String message) {
        System.out.println("oops..." + message);
    }
    
    public void insert(int e) {
        // a more friendly way is to double the size of data
        if (size == data.length) {err("overflow"); return;}
        data[size] = e;
        up(size++); // don't forget to increase size.
    }
    
    void setRoot(int e) {
        data[0] = e;
    }

    public int removeMax() {
        if (size == 0) {err("underflow"); return -1;}
        int ans = data[0];
        data[0] = data[--size]; //don't forget to decrease size.
        down(0);
        return ans; 
    }

    public int peek() {
        if (size == 0) {err("underflow"); return -1;}
        return data[0];
    }
    
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(data, 0, size));
    }
    
    public static void main(String[] args) {
        int[] a = {9, 7, 6, 5, 4, 3, 2, 1, 8};
        MaxHeap heap = new MaxHeap(a.length);
        // you may do some test here.z
        
        for(int i : a) {
        	heap.insert(i);
        }
        for(int i:heap.data) {
        	System.out.print(i+" ");
        }
    }
}

