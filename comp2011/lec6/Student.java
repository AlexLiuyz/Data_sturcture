package comp2011.lec6;

public class Student implements Comparable<Student>{
    String name;
    String id;

    public int compareTo(Student s2) {
        return id.compareTo(s2.id);
    }
    Student[] students;
}

class Wrapper<T> implements Comparable<Wrapper<T>>{
    T data;
    int originalIndex;//the position of this element in the input array.
    
    public int compareTo(Wrapper<T> s2) {
        int diff = ((Comparable) data).compareTo(s2.data);
        if (diff != 0) return diff;
        return originalIndex - s2.originalIndex;
    }
}
