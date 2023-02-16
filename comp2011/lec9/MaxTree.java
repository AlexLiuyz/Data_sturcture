package comp2011.lec9;

import java.util.Arrays;

/**
 * 
 * @author Yixin Cao (November 10, 2020)
 *
 * Use a maximum tree (introduced in Lecture 5) to sort an array.
 * 
 * To make it simple, I don't use generics here.
 * It's easy to revise it to use generics.
 */
public class MaxTree {
    private class Node {
        int element;
        public Node leftChild, rightChild;
        public Node(int element) { this.element = element; }
        public String toString() { return String.valueOf(element); }
    }
    Node root;

    // Build a maximum tree out of an array.
    public MaxTree(int[] a) {
      	int len=a.length;
    	Node[] nodes=new Node[len];
    	for(int i=0;i<len;i++) {
    		nodes[i]=new Node(a[i]);
    	}
    	while(len>1) {
    		for(int i=0;i<len/2;i++) {
    			Node n=new Node(nodes[2*i].element>nodes[2*i+1].element?nodes[2*i].element:nodes[2*i+1].element);
    			n.leftChild=nodes[2*i];
    			n.rightChild=nodes[2*i+1];
    			nodes[i]=n;
    		}
    		if(len%2!=0) {
    			Node n=new Node(nodes[len-1].element);
    			n.leftChild=nodes[len-1];
    			nodes[len/2]=n;
    		}
    		len=(len+1)/2;
    	}
    	root=nodes[0];
    }
    public int removeMax() {
        if (root == null) {
            System.out.print("underflow");
            return -1;
        }
        int res = root.element;
        if (removeMax(root)) root = null;
        return res;
    }
    /*
     * The real work of removeMax is done here.
     * Note the return value.
     */
    public boolean removeMax(Node curRoot) {
    	if(curRoot.leftChild==null&&curRoot.rightChild==null) {
    		return true;
    	}
    	if(curRoot.leftChild!=null&&curRoot.leftChild.element==curRoot.element) {
    		if(removeMax(curRoot.leftChild))curRoot.leftChild=null;
    	}
    	else {
    		if(removeMax(curRoot.rightChild))curRoot.rightChild=null;
    	}
    	if(curRoot.leftChild==null&&curRoot.rightChild==null) {
    		return true;
    	}
    	curRoot.element=Integer.MIN_VALUE;
    	if(curRoot.leftChild!=null) {
    		curRoot.element=curRoot.leftChild.element;
    	}
    	if(curRoot.rightChild!=null&&curRoot.rightChild.element>curRoot.element) {
    		curRoot.element=curRoot.rightChild.element;
    	}
    	return false;
    }
    
    static void inorder(Node node) {
    	if(node==null) {return;}
    	System.out.println(node.element);
    	inorder(node.leftChild);
    	inorder(node.rightChild);
    }
    public static void treeSort(int[] a) {
    	if(a.length==0) {
    		return;
    	}
    	MaxTree tree=new MaxTree(a);
    	for(int i=a.length-1;i>=0;i--) {
    		a[i]=tree.removeMax();
    	}
    }

    public static void main(String[] args) {
        int testData[][] = { // try different inputs.
                {},
                {1, 1, 1, 1, 1, 1, 1},
                {10, 8, -4, 89, 2, 0, 4, -19, 200},
                {5, 4, 3, 2, 1, 1, 0, 0, -1},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
                {1, 3, 2, 6, 7, 5, 4, 12, 13, 15, 14, 10, 11, 9, 8},
                {3, 2, 6, 13, 8, 4, 10, 7, 14, 11, 12, 5, 9},
                {65, 85, 17, 88, 66, 71, 45, 38, 95, 48, 18, 68, 60, 96, 55},
                {12,35,1,10,1,19,49,34} 
            };
        for (int[] a:testData) {
            System.out.println("The original array: " + Arrays.toString(a));
            treeSort(a);
            System.out.println("Sorted: " + Arrays.toString(a));
        }
    }
}
