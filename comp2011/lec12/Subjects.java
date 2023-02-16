package comp2011.lec12;

import java.util.Arrays;

/**
 * 
 * @author Yixin Cao (November 20, 2020)
 *
 * Demonstration of hashing function % p for different prime numbers p.
 * 
 * This class uses Lambda functions (Line 38).
 * 
 */
public class Subjects {
	// print out subject codes % 97 in sorted order.
	private static void mod97() {
	    int[] a = {1001, 1003, 1011, 1901, 2011, 2012, 2021, 2022, 2121, 2222, 
	    		2322, 2411, 2421, 2422, 2432, 3011, 3021, 3121, 3122, 3131, 
	    		3133, 3134, 3211, 3222, 3233, 3235, 3334, 3335, 3421, 3422, 
	    		3432, 3438, 3511, 3512, 3531, 3911, 3921, 4000, 4001, 4121, 
	    		4122, 4123, 4125, 4127, 4133, 4134, 4135, 4141, 4142, 4146, 
	    		4322, 4332, 4334, 4342, 4422, 4431, 4433, 4434, 4435, 4438, 
	    		4441, 4442, 4512, 4531, 4911, 4913, 4921};
	    int[] b = new int[a.length];
	    for (int i=0;i < a.length;i++)
	        b[i] = (a[i] % 97);
	    Arrays.sort(b);
	    System.out.println(Arrays.toString(b));
	}

	public static void main(String args[]){
	    mod97();
	    int[] a = {1001, 1003, 1011, 2011, 2018, 2021, 2411, 2421, 2426};
	    int[] b = {17, 19, 23};
	    for (int M:b) {
	        System.out.println("\n M = " + M + ": ");	        
	        //for (int i:a) System.out.print(i % M + ", ");
	        Arrays.stream(a).mapToObj(i -> i % M).forEach(System.out::println);
	        //Arrays.stream(a).mapToObj(i -> i % M).sorted().forEach(System.out::println);
	    }	    
	}
}

