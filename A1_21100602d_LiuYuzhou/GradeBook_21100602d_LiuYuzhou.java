package comp2011.a1;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * 
 * @author Yixin Cao (September 14, 2022)
 *
 * A class for grade book of COMP2011.
 * 
 * There are two sessions, denoted as 1 and 2.
 * There are four possible grades, 'A', 'B', 'C', and 'D.' (The Grading System of PolyU, which can be found at https://www.polyu.edu.hk/en/gs/current-students/graduation-requirements/, is more complicated.)
 * 
 * It needs to support two views.  
 * First, the grades are displayed session by session. In each session, the students are listed in alphabetical order.
 * Second, the grades are displayed by grades, and for students with the same grade, students from session 1 are listed before session 2.
 * 
 * Read the {@code Student} class carefully before you start.
 * 
 * If your implementation is correct, elements of the same value should respect their original order,
 * e.g., for input {1.25, 0, 1.25, 2.5, 10, 2.5, 1.25, 5, 2.5}, the output should be  
 * [0.0 (1), 1.25 (0), 1.25 (2), 1.25 (6), 2.5 (3), 2.5 (5), 2.5 (8), 5.0 (7), 10.0 (4)].
 */
public class GradeBook_21100602d_LiuYuzhou{// Please change!
    /**
     * 
     * No modification to the class {@code Student} is allowed.
     * If you change anything in this class, your work will not be graded.
     *
     * You are NOT allowed to call the constructor of the class {@code Student}, except in {@code main}.
     */
    static class Student {
        String id;
        byte session; // 1 or 2
        char grade; // 'A', 'B', 'C', or 'D'
        
        public Student(String id, byte session) {
            this.id = id;
            this.session = session;
        }
        
        public void setGrade(char grade) {
            this.grade = grade;
        }
        public String toString () {
            return id + " (" +  session + "): " + grade;
        }
    }
    
    /**
     * Do not modify the signatures of this method.
     * For comparing two strings, you need the {String.compareTo()} method.
     * https://docs.oracle.com/en/java/javase/19/docs/api/java.base/java/lang/String.html#compareTo(java.lang.String)
     * For this task, consider it as a single step (not true in general).
     * 
     * I've discussed this question with the following students:
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've sought help from the following Internet resources and books:
     *     1. comp2011 lab2
     *     2. 
     *     3. 
     *     ... 
     * 
     * Running time: O(n^2). (n is the total number of students.)
     */ 

    public static void sessionView(Student[] a) {
    	int length=a.length;
    	int j;
    	for(int i=1;i<length;i++) {
    		char key=a[i].grade;
    		byte keyb=a[i].session;
    		String id_num=a[i].id;
    		Student temp=a[i];
    		int low=0,high=i-1,position=0;
    		boolean flag=true;
    		while(low<=high) {
    			int mid=(low+high)/2;
    			if(a[mid].id.compareTo(id_num)==0&&a[mid].session==keyb) {
    				position=mid+1;
    				flag=false;
    				break;
    			}
    			else if(a[mid].session>keyb||(a[mid].session==keyb&&a[mid].id.compareTo(id_num)>0)) {
    				high=mid-1;
    			}
    			else if(a[mid].session<keyb||(a[mid].session==keyb&&a[mid].id.compareTo(id_num)<0)) {
    				low=mid+1;
    			}
    			
    		}
    		if(flag) {
    			position=low;
    		}
            for(j=i-1;j>=position;j--) {
            	a[j+1]=a[j];
            }
            a[j+1]=temp;
    	}
    	//check
//    	for(int i=0;i<length-1;i++) {
//    		if(a[i].session==a[i+1].session) {
//    			if(a[i].id.compareTo(a[i+1].id)>0) {
//    				System.out.println("False"+a[i].id+" "+a[i+1].id);
//    				return;
//    			}
//    		}
//    	}
//    	System.out.println("Correct");
    }
    //MinMaxSelectionsort
//  public static void sessionView(Student[] a) {
//      int length=a.length;
//      for(int i=0,j=length-1;i<j;j--,i++){
//          int min=i,max=i;
//          String max_value=a[max].id;
//          byte max_session=a[max].session;
//          char max_grade=a[max].grade;
//          for(int k=i;k<=j;k++){
//              if(a[min].session==a[k].session){
//                  if(a[k].id.compareTo(a[min].id)<0){
//                      min=k;
//                  }
//              }
//              else if(a[k].session<a[min].session){
//                  min=k;
//              }
//              if(a[max].session==a[k].session){
//                  if(a[k].id.compareTo(a[max].id)>0){
//                      max=k;
//                      max_value=a[max].id;
//                      max_session=a[max].session;
//                      max_grade=a[max].grade;
//                  }
//              }
//              else if(a[k].session>a[max].session){
//                  max=k;
//                  max_value=a[max].id;
//                  max_session=a[max].session;
//                  max_grade=a[max].grade;
//              }
//          }
//          byte temp=a[min].session;
//          a[min].session=a[i].session;
//          a[i].session=temp;
//          String temp_s=a[min].id;
//          a[min].id=a[i].id;
//          a[i].id=temp_s;
//          char temp_char=a[min].grade;
//          a[min].grade=a[i].grade;
//          a[i].grade=temp_char;
//          if(a[min].id.compareTo(max_value)==0&&a[min].grade==max_grade&&a[min].session==max_session){
//              temp=a[min].session;
//              a[min].session=a[j].session;
//              a[j].session=temp;
//              temp_s=a[min].id;
//              a[min].id=a[j].id;
//              a[j].id=temp_s;
//              temp_char=a[min].grade;
//              a[min].grade=a[j].grade;
//              a[j].grade=temp_char;
//          }
//          else{
//              temp=a[max].session;
//              a[max].session=a[j].session;
//              a[j].session=temp;
//              temp_s=a[max].id;
//              a[max].id=a[j].id;
//              a[j].id=temp_s;
//              temp_char=a[max].grade;
//              a[max].grade=a[j].grade;
//              a[j].grade=temp_char;
//          }
//      }
//  }

    /*
     * 
     * Arrange the grade book by grades, 'A' first and 'D' last.
     * For the same grade, list students from session 1 before session 2.
     *
     * My approach is to deal with sessions first, then grades.
     * The main benefit is that I can reuse the code from <code>sessionView</code>.
     * Alternatively, one can use eight counts for the number of students in each session for a different grade,
     * the number of students from session 1 with grade 'A,'
     * the number of students from session 2 with grade 'A,'
     * the number of students from session 1 with grade 'B,' etc.
     * Yet another (better) way is to do it in place.
     * Try to think about how to do it. You may get some hints from step 1.
     */
    /**
     * 1. Do not modify the signatures of this method.
     * 
     * I've discussed this question with the following students:
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've sought help from the following Internet resources and books:
     *     1. get the idea from quiz1
     *     2. 
     *     3. 
     *     ... 
     * 
     * Running time: O(n^2). (n is the total number of students.)
     */ 
    public static void gradeView(Student[] a) {
    	int length=a.length;
    	int j;
    	for(int i=1;i<length;i++) {
    		char key=a[i].grade;
    		byte keyb=a[i].session;
    		String id_num=a[i].id;
    		Student temp=a[i];
    		int low=0,high=i-1,position=0;
    		boolean flag=true;
    		while(low<=high) {
    			int mid=(low+high)/2;
    			if(a[mid].grade==key&&a[mid].session==keyb) {
    				position=mid+1;
    				flag=false;
    				break;
    			}
    			else if(a[mid].grade>key||(a[mid].grade==key&&a[mid].session>keyb)) {
    				high=mid-1;
    			}
    			else if(a[mid].grade<key||(a[mid].grade==key&&a[mid].session<keyb)) {
    				low=mid+1;
    			}
    		}
    		if(flag) {
    			position=low;
    		}
            for(j=i-1;j>=position;j--) {
            	a[j+1]=a[j];
            }
            a[j+1]=temp;
    	}
    }
    //insertion sort
//  public static void gradeView(Student[] a) {
//	  int length=a.length;
//	  int j;
//	  for(int i=1;i<length;i++){
//	      char key=a[i].grade;
//	      byte keyb=a[i].session;
//	      String id_num=a[i].id;
//	      for(j=i-1;j>=0;j--){
//	          if(key>a[j].grade){
//	              break;
//	          }
//	          else if(key==a[j].grade&&a[j].session<keyb){
//	              break;
//	          }
//	       a[j+1].grade=a[j].grade;
//	       a[j+1].session=a[j].session;
//	       a[j+1].id=a[j].id;
//	      }
//	      a[j+1].grade=key;
//	      a[j+1].session=keyb;
//	      a[j+1].id=id_num;
//	  }
//  }
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        int n = 200;
        Student[] s = new Student[n];
        char[] g = {'A', 'B', 'C', 'D'};
        int id = 65536;
        for (int i = 0; i < n; i++) {
            id += random.nextInt(100);
            s[i] = new Student(String.valueOf(id), (byte)(id % 2 + 1));	
            s[i].setGrade(g[random.nextInt(4)]);
        }
        System.out.println(Arrays.toString(s));
        long startTime = System.nanoTime();
        sessionView(s);
        System.out.println("\nThe session view: ");
        System.out.println(Arrays.toString(s));
        gradeView(s);
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
        System.out.println("\nAfter sorting by grade: ");
        System.out.println(Arrays.toString(s));
    }
}