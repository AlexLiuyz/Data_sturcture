package comp2011.lab10a;

import java.util.Arrays;

/**
 * 
 * @author Yixin Cao (October 20, 2021)
 *
 * Merging k sorted arrays into a single sorted array.
 * 
 * Two different approaches. 
 * Both need to be stale, and run in O(n log k) time.
 * 
 */
public class StableMerger {
    static class Student {
        String name;
        double grade;
        public Student(String n, double g) {
            name = n; grade = g;
        }
        public String toString() {
            return "(" + name + ", " + grade + ")";
        }
    }

    public static Student[] merge2(Student[][] a) {
        return null;
    }
    
    /*
     * The same as used in mergesort (comp2011.lec6).
     */
    private static Student[] twoWayMerge(Student[] a1, Student[] a2) {
    	int i=0,j=0;
    	int l1=a1.length;
    	int l2=a2.length;
    	Student[] a=new Student[l1+l2];
    	int ind=0;
    	while(i<l1&&j<l2) {
    		if(a1[i].grade<=a2[j].grade) {
    			a[ind++]=a1[i++];
    		}
    		else {
    			a[ind++]=a2[j++];
    		}
    	}
    	while(i<l1) {
			a[ind++]=a1[i++];
    	}
    	while(j<l2) {
			a[ind++]=a2[j++];
    	}
    	return a;
    }    

    /*
     * The running time is O( ).
     */
    private static Student[] kWayMerge(Student[][] a, int begin, int end) {
    	if(begin>=end) {
    		return a[begin];
    	}
    	int mid=begin+(end-begin)/2;
    	Student[] a1=kWayMerge(a,begin,mid);
    	Student[] a2=kWayMerge(a,mid+1,end);
    	return twoWayMerge(a1,a2);
    }
    
    //  kickstarter, real work in merge
    public static Student[] merge1(Student[][] a) {
        return kWayMerge(a, 0, a.length - 1);
    }
    
    public static void main(String[] args) {
        String[][] names = {{"Mickey", "Teddy", "McDull"}, 
                {"Kay Tse", "Denise Ho", "Joey Yung", "Gloria Tang"},
                {"Eason Chan", "Andy Lau", "Cheung Jacky"}};
        double[][] grades = {{90, 90, 100}, {80, 90, 95, 100}, {40, 60, 90}};
        Student[][] a = new Student[names.length][];
        for (int i = 0; i < names.length; i++) {
            a[i] = new Student[names[i].length];
            for (int j = 0; j < names[i].length; j++) 
                a[i][j]= new Student(names[i][j], grades[i][j]);
        }
        System.out.println(Arrays.toString(merge1(a)));
        System.out.println(Arrays.toString(merge2(a)));
    }
} 
