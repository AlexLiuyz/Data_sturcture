package comp2011.lec7;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * 
 * @author Yixin Cao (October 8, 2021)
 *
 * Algorithms for sorting an array with few distinct keys.
 * All of them run in O( n ) time.
 * 
 */
public class BinarySort {

    static class Student {
        String id;
        String surname;
        String givenName;
        char gender; // 'F' or 'M'
        char grade; // 'A', 'B', 'C', or 'D'
        
        public Student(String id, char gender, char grade) {
            this.id = id;
            this.gender = gender;
            this.grade = grade;
        }
        
        public String toString () {
            return (gender == 'F'? "Miss ":"Mr. ") + surname + " " + givenName + " (" +  id + "): " + grade;
        }
    }
    
    /*
     * 
     * Sort an array of students such that all girls come before boys.
     * This version uses a temporary array. 
     */
    public static void ladyFirst(Student[] a) {
        int n = a.length;
        Student[] temp = new Student[n];
        for (int i = 0; i < n; i++) temp[i] = a[i];
        
        int girls = 0, boys = n - 1;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].gender == 'F') a[girls++] = temp[i];
            else a[boys--] = temp[i];
        }
    }

    /*
     * 
     * Sort an array of students such that all girls come before boys.
     * This version is in-place. 
     */
    public static void ladyFirstInsitu(Student[] a) {
        int l = 0, h = a.length - 1;
        while (l < h) {
            while (l < h && a[l].gender == 'F') l++;
            while (l < h && a[h].gender == 'M') h--;
            Student temp = a[l];
            a[l] = a[h];
            a[h] = temp;
        }
    }
    
    /*
     * 
     * Sort an array of students by their grades.
     * This algorithm is stable. 
     */
    public static void sortGrades(Student[] a) {
        int n = a.length;
        Student[] copy = new Student[n];
        for(int i = 0; i<n; i++) copy[i] = a[i];

        int count[] = {0, 0, 0, 0}; // counts of different grades.
        for(Student c: a) count[c.grade - 'A']++; // 'A' - 'A' = 0.
        int cur[] = {0, 0, 0, 0}; // cursors for different grades
        cur[1] = count[0];
        cur[2] = cur[1] + count[1];
        cur[3] = cur[2] + count[2];
        for(int i = 0; i < n; i++) a[cur[copy[i].grade - 'A']++] = copy[i];
    }    
    
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        int size = 10;
        Student[] s = new Student[size];
        char[] g = {'A', 'B', 'C', 'D'};
        // build 100 students with random id and gender.
        for (int i = 0; i < size; i++) {
            int id = Math.abs(random.nextInt());
            s[i] = new Student(String.valueOf(id), (id % 2 == 0?'F':'M'), g[id % 4]);
        }
        System.out.println(Arrays.toString(s));
        ladyFirst(s);
        System.out.println("\nAfter sorting by gender: ");
        System.out.println(Arrays.toString(s));
        sortGrades(s);
        System.out.println("\nAfter sorting by grade: ");
        System.out.println(Arrays.toString(s));
    }
}
