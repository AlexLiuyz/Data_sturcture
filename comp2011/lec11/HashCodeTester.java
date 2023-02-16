package comp2011.lec11;

import java.util.Objects;

/**
 * 
 * @author yixin cao (November 20, 2020)
 *
 * Demonstration of {\code String.hashCode()}, and some pitfalls of programming (not limited to Java).
 * 
 * Key points:
 * 1. For a class without {\code hashCode} implemented, the address of the object is used.
 *    You may see this through the object o of the class HashCodeTester.
 * 2. Two objects that are equal (o1.equals(o2) = true) must have the same hash code. 
 * 3. A pathetic example "polygenelubricants".hashCode() shows the limit of computers.
 *    They may make stupid mistakes, and it's our (programers') responsibility to avoid them.
 * 
 */
public class HashCodeTester {
    class Record {
        String name = "Peppa";
        int age = 7;
        char gender = 'F';
        
        // (31 * name.hashCode() + Integer.valueOf(age).hashCode() ) * 31 + ... 
        @Override public int hashCode() {
            return Objects.hash(name, age, gender);
        }
    }
    
    public static void main(String[] args) {
        HashCodeTester o = new HashCodeTester();
        // since we haven't implemented one here, it will call the default one provided by Object
        System.out.println(o.hashCode());
        // this translate the hash code into hexadecimal format.
        System.out.println(String.format("0x%08X", o.hashCode()));        
        System.out.println(o); // return its reference (address).
        
         String s = "Hong Kong";
        System.out.println("hashcode of 'Hong Kong': " + s.hashCode());
        System.out.println("hashcode of 'Hong Kong': " + "Hong Kong".hashCode());
        System.out.println("Note that they are different objects: " + (s == "Hong Kong"));

        int h = "polygenelubricants".hashCode();
        System.out.println("hashcode of polygenelubricants: " + h);
        System.out.println("hashcode of polygenelubricants: " + Math.abs(h));
//        int[] a = {81, 686, 873, 317, 919, 378, 746, 779, 128, 997, 383};
//        for (int i = 0; i < a.length; i ++) System.out.println(a[i]% 17);
    }
    //-2147483648 -- 2147483647 
}





/*
 * Click them with ctrl pressed.
  
   Integer, Long, Double, Inet4Address, Boolean, String, ArrayList
*/

