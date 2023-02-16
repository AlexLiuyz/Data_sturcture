package comp2011.lab6a;

/**
 * 
 * @author yixin cao (October 6, 2020)
 *
 * A doubly linked lists with sentinels at both ends.
 * They decrease the boundary cases we need to consider.
 * 
 */
public class DListWithSentinels<T> {
	/*
	 * The node class is the same as a regular doubly linked list. 
	 */
    private static class Node<T> {
        T element;
        Node<T> next;
        Node<T> previous;

        Node(T a) {
            element = a;
            next = previous = null;
        }
    }

    private Node<T> head; // firstNode
	private Node<T> tail; // lastNode

	public DListWithSentinels() {
        head = new Node<T>(null);
        tail = new Node<T>(null);
        head.next = tail;
        tail.previous = head;
    }

    public boolean isEmpty() {
        return head.next == tail; 
    }

    public void err() {
        System.out.println("Oops...");
    }
    
    public void insertFirst(T e) {
        Node<T> newNode = new Node<T>(e);
        newNode.next = head.next;
        newNode.previous = head;
        head.next.previous = newNode;
        head.next = newNode;
    }
    
    public void insertLast(T e) {
        Node<T> newNode = new Node<T>(e);
        newNode.previous = tail.previous;
        newNode.next = tail;
        tail.previous.next = newNode;
        tail.previous = newNode;
    }

    public T removeFirst() {
        if (isEmpty()) { err(); return null;}
        Node<T> deleted = head.next;
        head.next = deleted.next;
        deleted.next.previous = head;
        return deleted.element;
    }

    public T removeLast() {
        if (isEmpty()) { err(); return null;}
        Node<T> deleted = tail.previous;
        tail.previous = deleted.previous;
        deleted.previous.next = tail;
        return deleted.element;
    }

    public T delete(Node<T> node) {
        if (node == head || node == tail) { err(); return null;}
        
        node.previous.next = node.next;
        node.next.previous = node.previous;
        node.previous = node.next = null;
        return node.element;
    }
    
    // from an anonymous student (F2020)
    public void recurReverse(Node<T> cur) {
    	if(cur==null)return;
		Node<T> temp=cur.next;
		cur.next=cur.previous;
		cur.previous=temp;	
		if(temp==null) {
	    	Node<T> node=head;
	    	head=tail;
	    	tail=node;
		}
    	recurReverse(temp);
    }    

    public String toString() {
        if (isEmpty()) return "The list is empty.";
        Node<T> cur = head.next;
        StringBuilder sb = new StringBuilder();
        sb.append(cur.element);
        cur = cur.next;
        while ( cur != tail ) {
            sb.append(" -> ");
            sb.append(cur.element);
            cur = cur.next;
        }
//        while ( cur != tail ) {
//            sb.append(" - Previous: " + (cur.previous == null ? " Null " : cur.previous.element) + "\n");
//            sb.append("    Current: " + cur.element + "\n");
//            cur = cur.next;
//        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        DListWithSentinels<String> list = new DListWithSentinels<String>();
        list.insertFirst("Eason Chan");
        list.insertFirst("Denise Ho");
        list.insertFirst("Jennifer Chan");
        System.out.println(list);
        list.insertFirst("Joey Yung");
        list.insertFirst("Kay Tse");
        list.insertLast("Cheung Jacky");
        System.out.println(list);
        System.out.println("delete the first, which is " + list.removeFirst());     
        System.out.println(list);
        System.out.println("delete the last, which is " + list.removeLast());       
        System.out.println(list);
        list.recurReverse(list.head);
        System.out.println(list);
    }
}
