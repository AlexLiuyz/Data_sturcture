package comp2011.lab6a;

/**
 * 
 * @author Yixin Cao (October 6, 2020)
 *
 * A doubly linked list that is organized circularly.
 * We only need one of the {@code head} and {@code tail} references.
 *  
 */
public class CDList<T> {
	/*
	 * The node class is the same as a regular doubly linked list. 
	 */
    private static class Node<T> {
        T element;
        Node<T> previous, next;
        Node(T element) { this.element = element; }
    }

    private Node<T> head;

	public CDList() {
		head = null;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public void err() { System.out.println("Oops..."); }

	public void insertFirst(T e) {
		Node<T> newNode = new Node<T>(e);
		if (isEmpty()) {
			newNode.next = newNode;
			newNode.previous = newNode;
			head = newNode;
			return;
		}
		newNode.previous = head.previous;
		newNode.next = head;
		head.previous = newNode;
		newNode.previous.next = newNode;
        head = newNode;
	}

	/*
	 * This operation is reduced to {@code insertFirst}.
	 */
	public void insertAtEnd(T e) {
		insertFirst(e); 
		head = head.next;
	}

	public T removeFirst() {
		if (isEmpty()) { err(); return null;}
		Node<T> deleted = head;
		if (head.previous == head) { head = null; return deleted.element; }
		head = head.next;
		deleted.previous.next = head;
		head.previous = deleted.previous;
        deleted.previous = null;
		deleted.next = null;
		return deleted.element;
	}

	/*
	 * This operation is reduced to {@code removeFirst}.
	 */
	public T removeLast() {
		if (isEmpty()) { err(); return null;}
		head = head.previous;
		return removeFirst();
	}

	public String toString() {
        if (head == null) return "The list is empty.";
        StringBuilder sb = new StringBuilder();
        Node<T> cur = head;
        sb.append(cur.element);
        cur = cur.next;
        while ( cur != head ) {
            sb.append(" -> " + cur.element);
            cur = cur.next;
        }
        return sb.toString();
	}
	
	public static void main(String[] args) {
		CDList<Integer> list = new CDList<Integer>();
		list.insertFirst(37);
		list.insertFirst(99);
		list.insertFirst(12);
		System.out.println(list);
        list.insertAtEnd(38);
		System.out.println(list);
        list.insertFirst(75);
		System.out.println(list);
        list.removeLast();
        System.out.println(list);
        list.removeFirst();
        System.out.println(list);
	}
}

