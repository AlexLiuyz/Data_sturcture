package comp2011.a1;

import java.security.SecureRandom;
import java.util.Arrays;

/**
 * 
 * @author Yixin Cao (September 14, 2022)
 *
 * A tool for car dealers.
 * 
 * A car has a lot of technical specifications. 
 * We use a boolean array {@code specifications} to denote whether a specific car has each feature, with decreasing priority. 
 * For example, if the first four features are "Driver Airbag," "Passenger Airbag," "Cruise Control," and "Front Parking Sensors," and  
 * the array for a car object is [T, T, F, T, ...], then the car has Driver Airbag, Passenger Airbag, and Front Parking Sensors, but not Cruise Control.
 * 
 * The task is to arrange the cars by their features. 
 * If two cars A and B are equivalent in the first i features and differ on the (i+1)st, then the one having the (i+1)st feature is put before the one that does not.
 * For example, if there are four car models, {A [T, T]}, {B, [F, F]}, {C, [T, F]}, and {D, [F, T]}, then 
 * the output should be in the order of A->C->D->B.
 */
public class CarDealer_21100602d_LiuYuzhou {// Please change!
    /**
     * 
     * No modification to the class {@code Node} is allowed.
     * If you change anything in this class, your work will not be graded.
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
    /*
     * This class is from {@link comp2011.lec5}.
     */
    private static class DList<T> {
        private Node<T> head, tail;

        public DList() {
            head = tail = null;
        }

        public boolean isEmpty() {
            return tail == null; //or head == null 
        }

        public void err() {
            System.out.println("Oops...");
        }

        public void insertFirst(T e) {
            Node<T> newNode = new Node<T>(e);
            newNode.next = head;
            newNode.previous = null;
            if (isEmpty()) { 
                tail = head = newNode; 
                return;
            }
            head.previous = newNode;
            head = newNode;
        }

        public void insertLast(T e) {
            Node<T> newNode = new Node<T>(e);
            newNode.previous = tail;
            newNode.next = null;
            if (isEmpty()) { 
                tail = head = newNode; 
                return;
            }
            tail.next = newNode;
            tail = newNode;
        }
        
        public T removeFirst() {
            if (isEmpty()) { err(); return null;}
            T res = head.element;
            if (head == tail) { head = tail = null; return res; } 
            head = head.next;
            head.previous.next = null;
            head.previous = null;
            return res;
        }

        public T removeLast() {
            if (head == tail) return removeFirst();
            T res = tail.element;
            tail = tail.previous;
            tail.next.previous = null;
            tail.next = null;
            return res;
        }
    }    

    /**
     * 1. Do not modify the signatures of this method.
     * 2. You can only re-arrange the nodes from the input, without making new ones.
     * (Other objects may refer to a node. If you create a new node with the same element, those references become invalid.)
     * If you are not certain, please check the addresses in the output.
     * 
     * VERY IMPORTANT.
     * 
     * I've discussed this question with the following students:
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've sought help from the following Internet resources and books:
     *     1. 
     *     2. 
     *     3. 
     *     ...
     * 
     * Running time: O(n^3). (n is the total number of cars.)
     */ 
    public static DList<Car> sort(DList<Car> cars, int  k) {
    	if(cars.head==cars.tail) {
    		return cars;
    	}
    	Node<Car> cur=cars.head.next;
    	Node<Car> iterator=cars.head;
    	boolean flag=false;
    	if(cur.next==null) {
			for(int i=0;i<k;i++) {
				if(iterator.element.specifications[i]==false&&cur.element.specifications[i]==true) {
					flag=true;
				}
				else if(iterator.element.specifications[i]==true&&cur.element.specifications[i]==false) {
					break;
				}
			}
			if(flag) {
    			cars.tail=cur.previous;
    			cars.head=cur;
    			cur.previous.next=null;
    			cur.next=iterator;
    			iterator.previous=cur;
    			cur.previous=null;
			}
    	}
    	flag=true;
    	//insert cur
    	for(;cur!=null;) {
    		Node<Car> key=cur; 
    		iterator=cars.head;
    		flag=false;
    		for(;iterator!=key;) {
    			//compare
				for(int i=0;i<k;i++) {
					if(iterator.element.specifications[i]==false&&key.element.specifications[i]==true) {
						flag=true;
					}
					else if(iterator.element.specifications[i]==true&&key.element.specifications[i]==false) {
						break;
					}
				}
				if(flag) {
					break;
				}
				iterator=iterator.next;
    		}
    		cur=cur.next;
    		if(iterator!=key) {
	    		if(iterator==cars.head&&key==cars.tail) {
	    			cars.tail=key.previous;
	    			cars.head=key;
	    			key.previous.next=null;
	    			key.next=iterator;
	    			iterator.previous=key;
	    			key.previous=null;
	    		}
	    		else if(iterator==cars.head&&key!=cars.tail) {
	    			cars.head=key;
	    			key.previous.next=key.next;
	    			key.next.previous=key.previous;
	    			iterator.previous=key;
	    			key.next=iterator;
	    			key.previous=null;
	    		}
	    		else if(iterator!=cars.head&&key==cars.tail) {
	    			cars.tail=key.previous;
	    			key.previous.next=null;
	    			key.next=iterator;
	    			iterator.previous.next=key;
	    			key.previous=iterator.previous;
	    			iterator.previous=key;
	    		}
	    		else if(key!=cars.tail&&iterator!=cars.head) {
	        		key.previous.next=key.next;
	        		key.next.previous=key.previous;
	        		key.next=iterator;
	        		iterator.previous.next=key;
	        		key.previous=iterator.previous;
	        		iterator.previous=key;	
	    		}
    		}
    	}
    	//check
//    	int time=0;
//    	iterator=cars.head;
//    	Node<Car> last=iterator.next;
//    	while(last.next!=null) {
//    		time++;
//    		iterator=iterator.next;
//    		last=last.next;
//    		flag=true;
//			for(int i=0;i<k;i++) {
//				if(iterator.element.specifications[i]==false&&last.element.specifications[i]==true) {
//					flag=false;
//					System.out.println("error/n/n");
//				}
//				else if(iterator.element.specifications[i]==true&&last.element.specifications[i]==false) {
//					break;
//				}
//			}
//    	}
//    	if(flag) {
//    		System.out.println("succeed");
//    	}
//    	System.out.println(time+2);
        return cars;
    }
    
    /*
     * These methods are for testing. You can write the algorithm without understanding them.
     * However, you are suggested to add more testing cases to test your algorithm.
     * Do make sure your algorithm works for all boundary cases.
     */
    private static void shortOutput(DList<Car> cars) {
        Node<Car> cur = cars.head;
        while (cur != null) {
            System.out.println(cur.element + "(@0x" + Integer.toHexString(System.identityHashCode(cur.element)) + ")");
            cur = cur.next;
        }
    }
    private static void longOutput(DList<Car> cars) {
        Node<Car> cur = cars.head;
        while (cur != null) {
            System.out.println(cur.element.toLongString());
            cur = cur.next;
        }
    }
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        int n = 128; // number of cars.
        int k = 25;  // number of specifications.
        DList<Car> cars = new DList<Car>();
        // Node<Car> head = null, tail, cur = null;

        String[] brands = {"Tesla", "BMW", "Ford", "Audi", "Chevrolet", "Mercedes-Benz", "Rolls-Royce", "Jaguar", "Buick", "Volkswagen", "Peugeot", "Lincoln", "GMC"};
        // build 100 students with random id and gender.
        for (int i = 0; i < n; i++) {
            String b = brands[random.nextInt(13)];
            Car car = new Car(b, k);
            boolean[] s = new boolean[k];
            for (int j = 0; j < k; j++) 
                s[j] = random.nextBoolean();
            car.setSpec(s);
            cars.insertFirst(car);
        }
        shortOutput(cars);
//        long startTime = System.nanoTime();
        cars = sort(cars, k);
//        long endTime   = System.nanoTime();
//        long totalTime = endTime - startTime;
//        System.out.println(totalTime);
        System.out.println("\nAfter sorting: ");
        shortOutput(cars);
        // longOutput(cars);
    }
}

/**
 * 
 * No modification to the class {@code Car} is allowed.
 * If you change anything in this class, your work will not be graded.
 *
 * You are NOT allowed to call the constructor of the class {@code Car}, except in {@code main}.
 */
class Car {
    boolean[] specifications;
    String brand;
    
    Car (String brand, int k) {
        this.brand = brand;
        specifications = new boolean[k];
    }
    void setSpec(boolean[] specifications) {
        this.specifications = specifications;
    }
    public String toString () {
        return brand + ": " + Arrays.toString(specifications);
    }
    public String toLongString () {
        // (This list is supposed to be an example.)
        String[] features = {"Driver Airbag", "Passenger Airbag", "Cruise Control", "Front Parking Sensors", "Rear Parking Sensors", "Power Steering", "Power Windows-Front", "Power Windows-Rear", "Air Conditioner", "Heater", "Adjustable Steering", "Remote Engine Start/Stop", "Rear Reading Lamp", "Rear Seat Headrest", "Adjustable Headrest", "Foldable Rear Seat", "KeyLess Entry", "Voice Control", "Leather Seats", "Height Adjustable Driver Seat", "Moon Roof", "Automatic Headlamps", "Seat Belt Warning", "Touch Screen", "Rear Camera"};
        StringBuilder sb = new StringBuilder(brand + ": ");
        for (int i = 0; i < specifications.length; i++) 
        	if(specifications[i]) sb.append(features[i] + ", ");
        return sb.toString();
    }
}
