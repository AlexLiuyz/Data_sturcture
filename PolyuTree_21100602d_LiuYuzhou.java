package comp2011.a2;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.LinkedList;

import java.util.Scanner;


/**
 * 
 * @author Yixin Cao (November 1, 2022)
 *
 * A binary search tree for Polyu students.
 * 
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * Update on November 8: fix a bug in the main method. 
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 
 * Since we only store students, the class doesn't use generics.
 * 
 */
public class PolyuTree_21100602d_LiuYuzhou { // Please change!
    /**
     * No modification to the class {@code Student} is allowed. 
     * If you change anything in this class, your work will not be graded.
     */
	static class Student {
		String id;
	    String name;
	    public Student(String id, String name) {
	        this.id = id;
	        this.name = name;
	    }
	    
	    public String toString() {return id + ", " + name;}
	}
	
    /**
     * No modification to the class {@code Node} is allowed. 
     * If you change anything in this class, your work will not be graded.
     */
    private class Node {
        Student e;
        public Node lc, rc; // left child; right child

        @SuppressWarnings("unused")
		public Node(Student data) {
            this.e = data;
        }

        public String toString() {
            return e.toString();
        }
    }

    Node root;
//    private int num=0;
    /**
     * Insert a new student into the tree.
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
     *     1. https://stackoverflow.com/questions/16180569/turning-a-binary-tree-to-a-sorted-array
     *     2. https://www.geeksforgeeks.org/sorted-array-to-balanced-bst/
     *     3. 
     *     ... 
     * 
     * Running time: O(n). A function of d and n.//for normal insrtion it is O(d);because for my method each time I use inorder traversal to traverse the tree, then I build the balance tree by using the sorted array, so it is O(n) not original O(d) 
     */
    public void insert(Student s) {
    	if(s==null) {
    		return;
    	}
    	root=recurInsert(root,s);
    	int num=size(root);
    	Node[] nodes=new Node[num];
    	inorder(root,nodes,0);
    	root=buidbalance_tree(nodes,0,nodes.length-1);
    }
    //O(n)
    private Node recurInsert(Node node, Student s) {
    	if(node==null) {
    		Node ns=new Node(s);
    		node=ns;
    		return node;
    	}

    	if(node.e.name.compareTo(s.name)<=0) {
    		node.rc=recurInsert(node.rc,s);
    	}
    	else if(node.e.name.compareTo(s.name)>0) {
    		node.lc=recurInsert(node.lc,s);
    	}
    	return node;
    }
    
    private Node buidbalance_tree(Node[] node,int l,int h) {
    	if(l>h) {
    		return null;
    	}
    	int mid=(l+h+1)/2;
    	Node newnode=new Node(node[mid].e);
    	newnode.lc=buidbalance_tree(node,l,mid-1);
    	newnode.rc=buidbalance_tree(node,mid+1,h);
    	return newnode;
    }

    private int inorder(Node node, Node [] list, int pos) {
        if (node.lc != null) {
            pos = inorder(node.lc, list, pos);
        }
        list[pos++] = node;
        if (node.rc != null) {
            pos = inorder(node.rc, list, pos);
        }
        return pos;
    }
    
//    private void display() {
//    	Queue<Node> queue=new LinkedList<>();
//    	if(root==null) {
//    		return;
//    	}
//    	Node cur=root;
//    	queue.add(cur);
//    	int thislevel=1,nextlevel=0;
//    	while(!queue.isEmpty()) {
//    		thislevel--;
//    		Node node=queue.remove();
//    		System.out.print(node.e.name+"   ");
//    		if(node.lc!=null) {
//    			queue.add(node.lc);
//    			nextlevel++;
//    		}
//    		if(node.rc!=null) {
//    			queue.add(node.rc);
//    			nextlevel++;
//    		}
//    		if(thislevel==0) {
//    			thislevel=nextlevel;
//    			System.out.print(nextlevel);
//    			nextlevel=0;
//    			System.out.println();
//    		}
//    	}
//    }

    /**
     * Calculate the largest difference between the depths of the two subtrees of a node.
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
     * Running time: O(n*d). A function of d and n. because the tree is balanced
     */
  public int maxDepthDiff() {
     return find_max_diff(root,0);
  }
  private int find_max_diff(Node node,int diff) {
	  if(node==null||(node.lc==null&&node.rc==null)) {
		  return diff;
	  }
	  if(node.lc==null) {
		  return hight(node.rc,0)>diff?hight(node.rc,0):diff;
	  }
	  else if(node.rc==null) {
		  return hight(node.lc,0)>diff?hight(node.lc,0):diff;
	  }
	  else {
		  int h1=hight(node.rc,0);
		  int h2=hight(node.lc,0);
		  int cur_diff=h1>h2?h1-h2:h2-h1;
		  diff=cur_diff>diff?cur_diff:diff;
		  int num1=find_max_diff(node.rc,diff);
		  int num2=find_max_diff(node.lc,diff);
		  return num1>num2?num1:num2;
	  }
  }
  //O(d)
  private int hight(Node x,int hight) {
  	if(x==null) {
  		return hight;
  	}
  	int h1=hight(x.lc,hight+1);
  	int h2=hight(x.rc,hight+1);
  	if(h1<h2) {
  		return h2;
  	}
  	return h1;
  }

    /**
     * Calculate the largest difference between the sizes of the two subtrees of a node.
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
     *     1. https://algorithms.tutorialhorizon.com/find-the-size-of-the-binary-tree/
     *     2. 
     *     3. 
     *     ... 
     * 
     * Running time: O(n^n). A function of d and n.
     */
    public int maxSizeDiff() {
    	return max_diff(root,0);
    }
    private int max_diff(Node node,int diff) {
    	if(node==null||(node.lc==null&&node.rc==null)) {
    		return diff;
    	}
    	if(node.rc==null) {
    		int cur_diff=size(node.lc);
    		return cur_diff>diff?cur_diff:diff;
    	}
    	else if(node.lc==null) {
    		int cur_diff=size(node.rc);
    		return cur_diff>diff?cur_diff:diff;
    	}
    	else {
    		int cur_diff=size(node.rc)-size(node.lc);
    		if(cur_diff<0) {
    			cur_diff=-cur_diff;
    		}
    		diff=cur_diff>diff?cur_diff:diff;
    		int num1=max_diff(node.lc,diff);
    		int num2=max_diff(node.rc,diff);
    		return num1>num2?num1:num2;
    	}
    }
    
    private int size(Node node) {
    	if(node==null) {
        	return 0;
    	}
    	return size(node.lc)+size(node.rc)+1;
    }
    /**
     * Calculate the number of nodes that have only one child.
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
     * Running time: O(n). A function of d and n.
     */
    public int nodesWithOneChild() {
    	return oneChild(root);
    }
    
    private int oneChild(Node node) {
    	if(node==null) {
    		return 0;
    	}
    	if(node.lc==null&&node.rc==null) {
    		return 0;
    	}
    	if(node.lc!=null&&node.rc==null) {
    		System.out.println(node);
    		return oneChild(node.lc)+1;
    	}
    	else if(node.lc==null&&node.rc!=null) {
    		System.out.println(node);
    		return oneChild(node.rc)+1;
    	}
    	else {
    		return oneChild(node.lc)+oneChild(node.rc);
    	}
    }

    /*
     * Find a student with the specified name.
     * You may return any of them if there are multiple students of this name.
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
     * Running time: O(d). A function of d and n.
     */
    public Student searchFullname(String name) {
    	return search_full(name,root).e;
    }
    private Node search_full(String name,Node node) {
    	if(node==null) {
    		return null;
    	}
    	if(name.compareTo(node.e.name)<0) {
    		return search_full(name,node.lc);
    	}
    	if(name.compareTo(node.e.name)>0) {
    		return search_full(name,node.rc);
    	}
    	else {
    		return node;
    	}
    }
    
    /*
     * Find all students with the specified surname.
     * Consider the first word as the surname. 
     * Warning: Make sure "Liu Dennis" does not show when you search "Li."
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
     * Running time: O(n). A function of d and n.
     */
    public Student[] searchSurname(String surname) {
    	Node cur_node=Surname(root,surname);
    	int num=count(cur_node,surname);
    	Student[] student=new Student[num];
    	list(cur_node,student,0,surname);
    	return student;
    }
    private Node Surname(Node node,String surname) {
    	if(node==null) {
    		return null;
    	}
    	String cur_sur=node.e.name.substring(0,node.e.name.indexOf(" "));
    	if(cur_sur.compareTo(surname)>0) {
    		return Surname(node.lc,surname);
    	}
    	else if(cur_sur.compareTo(surname)<0) {
    		return Surname(node.rc,surname);
    	}
    	else {
    		return node;
    	}
    }
    private int list(Node node, Student [] list, int pos,String surname) {
    	if(node==null) {
    		return -1;
    	}
        if (node.lc != null) {
            pos = list(node.lc, list, pos,surname);
        }
        String cur_sur=node.e.name.substring(0,node.e.name.indexOf(" "));
        if(cur_sur.equals(surname)) {
            list[pos++] = node.e;
        }
        if (node.rc != null) {
            pos = list(node.rc, list, pos,surname);
        }
        return pos;
    }
    
    private int count(Node node, String surname)
    {
        if(node == null)
            return 0;
        String cur_sur=node.e.name.substring(0,node.e.name.indexOf(" "));
        if(surname.equals(cur_sur)) {
        	return count(node.lc,surname)+count(node.rc,surname)+1;
        }
        else {
        	return count(node.lc,surname)+count(node.rc,surname);
        }
    }
    
    /*
     * Find all students who are taking a certain class.
     * The input is an array of student names.
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
     *     1. lec10
     *     2. https://www.baeldung.com/cs/arrays-common-elements
     *     3. https://stackoverflow.com/questions/16180569/turning-a-binary-tree-to-a-sorted-array
     *     ... 
     * 
     * Running time: O(c*log(c)+n). A function of d, n, and c.
     */
    public Student[] searchClass(String[] roster) {
    	Student[] ans = new Student[roster.length];
    	heapsort(roster);
    	System.out.println("heapsort");
    	for(String element:roster) {
    		System.out.println(element);
    	}
    	int num=size(root);
    	Node[] nodes=new Node[num];
    	inorder(root,nodes,0);
    	int j=0;
    	int i=0;
    	int ind=0;
    	while(i<nodes.length&&j<roster.length) {
			if(nodes[i].e.name.compareTo(roster[j])==0){
				ans[ind++]=nodes[i].e;
				i++;
				j++;
			}
    		else if(nodes[i].e.name.compareTo(roster[j])<0) {
    			i++;
    		}
    		else {
				j++;
    		}
    	}
    	int k=0;
    	Student[] result=new Student[ind];
    	for(Student element:ans) {
    		if(element!=null) {
    			result[k++]=element;
    		}
    	}
    	System.out.println(result.length);
    	return result;
    }
    private static void up(String[] a, int c) {
    	int parent=(c-1)/2;
    	if(c==0||a[c].compareTo(a[parent])<0) {
    		return;
    	}
    	swap(a,c,parent);
    	up(a,parent);
    }
    private static void swap(String [] a, int x, int y) {
        String temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }
    private static void down(String[] a, int p, int size) {
    	if(2*p+2>size) {
    		return;
    	}
    	int larger=2*p+1;
    	if(larger+1<size&&a[larger].compareTo(a[larger+1])<0) {
    		larger++;
    	}
    	if(a[p].compareTo(a[larger])>0) {
    		return;
    	}
    	swap(a,p,larger);
    	down(a,larger,size);
    }
    
    public static void heapsort(String[] a) {
    	for(int i=1;i<a.length;i++) {
    		up(a,i);
    	}
    	for(int i=a.length-1;i>0;i--) {
    		swap(a,i,0);
    		down(a,0,i);
    	}
    }
    /**
     * You are free to make any change to this method.
     * You can even remove it if you want to test your code with other means.  
     */
    public static void main(String[] args) {
        PolyuTree_21100602d_LiuYuzhou tree = new PolyuTree_21100602d_LiuYuzhou();
        /*
         * Here are 192 names you can use for testing.
         * 
         * You should test your code with more names (>= 1 million). One way is to generate random names.  
         * Tips: Given names can be random strings. You can assign a random surname from this list 
         * https://surnam.es/hong-kong.
         */
        // String[] names = {"Chang Chi Fung", "Lee Cheuk Kwan", "Liu Tsz Ki", "Vallo David Jonathan Delos Reyes", "Jiang Han", "Park Taejoon", "Shin Hyuk", "Jung Junyoung", "Lam Wun Yiu", "Kwok Shan Shan", "Chui Cheuk Wai", "Lam Yik Chun", "Luo Yuehan", "Wang Hao", "Mansoor Haris", "Liang Wendi", "Meng Guanlin", "Wang Zhiyu", "Mak Ho Lun", "Liu Zixian", "Geng Qiyang", "Fong Chun Ming", "Cheung Chun Hei", "Lau Tsun Hang Ryan", "Cheung Cheuk Hang", "Liu Chi Hang", "Wong Yiu Nam", "Cheng Kok Yin", "Lam Wai Kit", "Liu Valerie", "Tam Chung Man", "Yan Tin Chun", "Lok Yin Chun", "Ng Ming Hei", "Lo Chun Hin", "Lam Pui Wa", "Lo Cho Hung", "Chu Tsz Fui", "Chow Ho Man", "Gao Ivan", "Ng Man Chau", "Iu Lam Ah", "Hung Wai Hin", "Tong Ka Yan", "Lo Ching Sau", "Lee Lee Ling", "Lam Ho Yin", "Sze Kin Ho", "Ng Siu Chi", "Wong Cheuk Laam", "Chan Yat Chun", "Lee Lap Shun", "Deng Chun Yung", "Fung Ki Ho", "Yeung Ting Kit", "Shiu Chi Yeung", "Kwan Yat Ming", "Chan Kin Kwan", "Leung Man Yi", "Yau Minki", "Hong Yuling", "Yung Wing Kiu", "Yuen Marco Siu Tung", "Lo Yung Sum", "Cheung Tsz Ho", "Chu Ka Hang", "Chan Man Yi", "Ng Yuet Kwan", "Lui Cheuk Lam Lily", "Tai Cheuk Hin", "Ong Chun Wa", "Yiu Pun Cham", "Cheng Ho Wing", "Wong Tsz Wai Desmond", "Lai Ho Sum", "Lee Siu Wai", "Lai Ming Hin", "Leung Hoi Ming", "To Ka Hei", "Tang Tsz Yeung", "Au Yeung Chun Yi", "Lau Ue Tin", "Yau Sin Yan", "Lam Ho Yan", "Tong Mei Chun", "Cheung Tsz Kwan", "Chiang Tin Hang", "Lai Kit Lun", "Cheung Sum Yin", "Wang Matthew Moyin", "Jiang Guanlin", "Edgar Christopher", "Liang Zhihong", "Bai Ruiyuan", "Chen Ru Bing", "Hu Wenqing", "Zhou Siyu", "Wang Yukai", "Lam Hei Yung", "Zhang Wanyu", "Wei Xianger", "Conte Chan Gabriel Alejandro", "Pratento Dylan Jefferson", "Lam Wan Yuet", "Chen Ziyang", "Jiang Zheng", "Xu Le", "He Boyan", "Liu Minghao", "Zhang Zhiyuan", "Chen Yuxuan", "Jin Cheng", "Liu Chenxi", "Qiu Siyi", "Han Wenyu", "Chan Cheuk Hei", "Ho Tsz Kan", "Du Haoxun", "Zheng Shouwen", "Ye Feng", "Yu Kaijing", "Lee Jer Tao", "Shen Ziqi", "Wang Yihe", "Liu Yanqi", "Zhang Wenxuan", "Huang Tianji", "Lu Zhoudao", "Zhang Tianyi", "Yuan Yunchen", "Liu Chengju", "Wei Siqi", "Liu Yuzhou", "Zhao Letao", "Huo Shuaining", "Li Kin Lung Anson", "Qin Qipeng", "Li Jiale", "He Rong", "Hiu Jason Kenneth", "Lam Ka Hang", "Li Tong", "Lau Choi Yu Elise", "Liu Dong", "Li Shuhang", "Zeng Yuejia", "Cai Zhenyu", "Lau Siu Hin", "Szeto Siu Chun", "Leung Cheuk Kit", "Cai Haoyu", "Ye Chenwei", "Huang Yidan", "Lee Kam Hung", "Wang Zhengyang", "Bao Yucheng", "Niyitegeka Berwa Aime Noel", "Lyateva Paulina Veselinova", "Zhang Boyu", "Chen Junru", "Fang Yuji", "Lin Qinfeng", "Tang Haichuan", "Hu Yuhang", "Zhou Taiqi", "Fang Anshu", "Wu Chao", "Zhang Haolin", "Ivanichev Mikhail", "Luo Yi", "Ieong Mei Leng", "Lee Wang Ho", "Jian Junxi", "Tam Tin", "Kjoelbro Niklas August", "Lee Hau Laam", "Pak Yi Ching", "Pang Kin Hei", "Xue Bingxin", "Lau Sin Yee", "Kwok Sze Ming", "Chan Lok Hin", "Chan Ho Yin Francis", "Chung Wai Ching", "Hu Hongjian", "Yiu Chi Chiu", "Tso Yuet Yan", "Chow Chun Wang", "Li Wun Wun", "Chen Junyu", "Kan Wai Yi", "Fong Chun Ho"};
        String[] names = { "Tse Kay", "Ho Denise", "Yung Joey", "Tang Gloria", "Chan Eason", "Lau Andy", "Cheung Jacky" };
        SecureRandom random = new SecureRandom();
        int id = 22222222;
        for (String name : names) {
            id += random.nextInt(100);
            tree.insert(new Student(String.valueOf(id), name));
        }
//        tree.display();
        // Statistics
        System.out.println("The largest depth difference of a node is: " + tree.maxDepthDiff());
        System.out.println("The largest size difference of a node is: " + tree.maxSizeDiff());
        System.out.println(tree.nodesWithOneChild() + " nodes have a single child.");

        String[] comp2011 = {"Chang Chi Fung", "Lee Cheuk Kwan", "Liu Tsz Ki", "Vallo David Jonathan Delos Reyes", "Jiang Han", "Park Taejoon", "Shin Hyuk", "Jung Junyoung", "Lam Wun Yiu", "Kwok Shan Shan", "Chui Cheuk Wai", "Lam Yik Chun", "Luo Yuehan", "Wang Hao", "Mansoor Haris", "Liang Wendi", "Meng Guanlin", "Wang Zhiyu", "Mak Ho Lun", "Liu Zixian", "Geng Qiyang", "Fong Chun Ming", "Cheung Chun Hei", "Lau Tsun Hang Ryan", "Cheung Cheuk Hang", "Liu Chi Hang", "Wong Yiu Nam", "Cheng Kok Yin", "Lam Wai Kit", "Liu Valerie", "Tam Chung Man", "Yan Tin Chun", "Lok Yin Chun", "Ng Ming Hei", "Lo Chun Hin", "Lam Pui Wa", "Lo Cho Hung", "Chu Tsz Fui", "Chow Ho Man", "Gao Ivan", "Ng Man Chau", "Iu Lam Ah", "Hung Wai Hin", "Tong Ka Yan", "Lo Ching Sau", "Lee Lee Ling", "Lam Ho Yin", "Sze Kin Ho", "Ng Siu Chi", "Wong Cheuk Laam", "Chan Yat Chun", "Lee Lap Shun", "Deng Chun Yung", "Fung Ki Ho", "Yeung Ting Kit", "Shiu Chi Yeung", "Kwan Yat Ming", "Chan Kin Kwan", "Leung Man Yi", "Yau Minki", "Hong Yuling", "Yung Wing Kiu", "Yuen Marco Siu Tung", "Lo Yung Sum", "Cheung Tsz Ho", "Chu Ka Hang", "Chan Man Yi", "Ng Yuet Kwan", "Lui Cheuk Lam Lily", "Tai Cheuk Hin", "Ong Chun Wa", "Yiu Pun Cham", "Cheng Ho Wing", "Wong Tsz Wai Desmond", "Lai Ho Sum", "Lee Siu Wai", "Lai Ming Hin", "Leung Hoi Ming", "To Ka Hei", "Tang Tsz Yeung", "Au Yeung Chun Yi", "Lau Ue Tin", "Yau Sin Yan", "Lam Ho Yan", "Tong Mei Chun", "Cheung Tsz Kwan", "Chiang Tin Hang", "Lai Kit Lun", "Cheung Sum Yin", "Wang Matthew Moyin", "Jiang Guanlin", "Edgar Christopher", "Liang Zhihong", "Bai Ruiyuan", "Chen Ru Bing", "Hu Wenqing", "Zhou Siyu", "Wang Yukai", "Lam Hei Yung", "Zhang Wanyu", "Wei Xianger", "Conte Chan Gabriel Alejandro", "Pratento Dylan Jefferson", "Lam Wan Yuet", "Chen Ziyang", "Jiang Zheng", "Xu Le", "He Boyan", "Liu Minghao", "Zhang Zhiyuan", "Chen Yuxuan", "Jin Cheng", "Liu Chenxi", "Qiu Siyi", "Han Wenyu", "Chan Cheuk Hei", "Ho Tsz Kan", "Du Haoxun", "Zheng Shouwen", "Ye Feng", "Yu Kaijing", "Lee Jer Tao", "Shen Ziqi", "Wang Yihe", "Liu Yanqi", "Zhang Wenxuan", "Huang Tianji", "Lu Zhoudao", "Zhang Tianyi", "Yuan Yunchen", "Liu Chengju", "Wei Siqi", "Liu Yuzhou", "Zhao Letao", "Huo Shuaining", "Li Kin Lung Anson", "Qin Qipeng", "Li Jiale", "He Rong", "Hiu Jason Kenneth", "Lam Ka Hang", "Li Tong", "Lau Choi Yu Elise", "Liu Dong", "Li Shuhang", "Zeng Yuejia", "Cai Zhenyu", "Lau Siu Hin", "Szeto Siu Chun", "Leung Cheuk Kit", "Cai Haoyu", "Ye Chenwei", "Huang Yidan", "Lee Kam Hung", "Wang Zhengyang", "Bao Yucheng", "Niyitegeka Berwa Aime Noel", "Lyateva Paulina Veselinova", "Zhang Boyu", "Chen Junru", "Fang Yuji", "Lin Qinfeng", "Tang Haichuan", "Hu Yuhang", "Zhou Taiqi", "Fang Anshu", "Wu Chao", "Zhang Haolin", "Ivanichev Mikhail", "Luo Yi", "Ieong Mei Leng", "Lee Wang Ho", "Jian Junxi", "Tam Tin", "Kjoelbro Niklas August", "Lee Hau Laam", "Pak Yi Ching", "Pang Kin Hei", "Xue Bingxin", "Lau Sin Yee", "Kwok Sze Ming", "Chan Lok Hin", "Chan Ho Yin Francis", "Chung Wai Ching", "Hu Hongjian", "Yiu Chi Chiu", "Tso Yuet Yan", "Chow Chun Wang", "Li Wun Wun", "Chen Junyu", "Kan Wai Yi", "Fong Chun Ho"};
        String[] comp9999 = {"sadasdad","Chang Chi Fung"," xcz", "Lee Cheuk Kwan", "Liu Tsz Ki", "Vallo David Jonathan Delos Reyes", "Jiang Han", "Park Taejoon", "Shin Hyuk", "Jung Junyoung", "Lam Wun Yiu", "Kwok Shan Shan", "Chui Cheuk Wai", "Lam Yik Chun", "Luo Yuehan", "Wang Hao", "Mansoor Haris", "Liang Wendi", "Meng Guanlin", "Wang Zhiyu", "Mak Ho Lun", "Liu Zixian", "Geng Qiyang", "Fong Chun Ming", "Cheung Chun Hei", "Lau Tsun Hang Ryan", "Cheung Cheuk Hang", "Liu Chi Hang", "Wong Yiu Nam", "Cheng Kok Yin", "Lam Wai Kit", "Liu Valerie", "Tam Chung Man", "Yan Tin Chun", "Lok Yin Chun", "Ng Ming Hei", "Lo Chun Hin", "Lam Pui Wa", "Lo Cho Hung", "Chu Tsz Fui", "Chow Ho Man", "Gao Ivan", "Ng Man Chau", "Iu Lam Ah", "Hung Wai Hin", "Tong Ka Yan", "Lo Ching Sau", "Lee Lee Ling", "Lam Ho Yin", "Sze Kin Ho", "Ng Siu Chi", "Wong Cheuk Laam", "Chan Yat Chun", "Lee Lap Shun", "Deng Chun Yung", "Fung Ki Ho", "Yeung Ting Kit", "Shiu Chi Yeung", "Kwan Yat Ming", "Chan Kin Kwan", "Leung Man Yi", "Yau Minki", "Hong Yuling", "Yung Wing Kiu", "Yuen Marco Siu Tung", "Lo Yung Sum", "Cheung Tsz Ho", "Chu Ka Hang", "Chan Man Yi", "Ng Yuet Kwan", "Lui Cheuk Lam Lily", "Tai Cheuk Hin", "Ong Chun Wa", "Yiu Pun Cham", "Cheng Ho Wing", "Wong Tsz Wai Desmond", "Lai Ho Sum", "Lee Siu Wai", "Lai Ming Hin", "Leung Hoi Ming", "To Ka Hei", "Tang Tsz Yeung", "Au Yeung Chun Yi", "Lau Ue Tin", "Yau Sin Yan", "Lam Ho Yan", "Tong Mei Chun", "Cheung Tsz Kwan", "Chiang Tin Hang", "Lai Kit Lun", "Cheung Sum Yin", "Wang Matthew Moyin", "Jiang Guanlin", "Edgar Christopher", "Liang Zhihong", "Bai Ruiyuan", "Chen Ru Bing", "Hu Wenqing", "Zhou Siyu", "Wang Yukai", "Lam Hei Yung", "Zhang Wanyu", "Wei Xianger", "Conte Chan Gabriel Alejandro", "Pratento Dylan Jefferson", "Lam Wan Yuet", "Chen Ziyang", "Jiang Zheng", "Xu Le", "He Boyan", "Liu Minghao", "Zhang Zhiyuan", "Chen Yuxuan", "Jin Cheng", "Liu Chenxi", "Qiu Siyi", "Han Wenyu", "Chan Cheuk Hei", "Ho Tsz Kan", "Du Haoxun", "Zheng Shouwen", "Ye Feng", "Yu Kaijing", "Lee Jer Tao", "Shen Ziqi", "Wang Yihe", "Liu Yanqi", "Zhang Wenxuan", "Huang Tianji", "Lu Zhoudao", "Zhang Tianyi", "Yuan Yunchen", "Liu Chengju", "Wei Siqi", "Liu Yuzhou", "Zhao Letao", "Huo Shuaining", "Li Kin Lung Anson", "Qin Qipeng", "Li Jiale", "He Rong", "Hiu Jason Kenneth", "Lam Ka Hang", "Li Tong", "Lau Choi Yu Elise", "Liu Dong", "Li Shuhang", "Zeng Yuejia", "Cai Zhenyu", "Lau Siu Hin", "Szeto Siu Chun", "Leung Cheuk Kit", "Cai Haoyu", "Ye Chenwei", "Huang Yidan", "Lee Kam Hung", "Wang Zhengyang", "Bao Yucheng", "Niyitegeka Berwa Aime Noel", "Lyateva Paulina Veselinova", "Zhang Boyu", "Chen Junru", "Fang Yuji", "Lin Qinfeng", "Tang Haichuan", "Hu Yuhang", "Zhou Taiqi", "Fang Anshu", "Wu Chao", "Zhang Haolin", "Ivanichev Mikhail", "Luo Yi", "Ieong Mei Leng", "Lee Wang Ho", "Jian Junxi", "Tam Tin", "Kjoelbro Niklas August", "Lee Hau Laam", "Pak Yi Ching", "Pang Kin Hei", "Xue Bingxin", "Lau Sin Yee", "Kwok Sze Ming", "Chan Lok Hin", "Chan Ho Yin Francis", "Chung Wai Ching", "Hu Hongjian", "Yiu Chi Chiu", "Tso Yuet Yan", "Chow Chun Wang", "Li Wun Wun", "Chen Junyu", "Kan Wai Yi", "Fong Chun Ho"};
        for (String name : comp2011) {
            id += random.nextInt(100);
            tree.insert(new Student(String.valueOf(id), name));
        }
        Student[] ss = tree.searchClass(comp9999);
       for(Student element:ss) {
    	   System.out.println(element);
       }
        System.out.println((comp9999.length - ss.length) + " Not Found\n" + ((ss != null)?Arrays.toString(ss):""));
        System.out.println("The largest depth difference of a node is: " + tree.maxDepthDiff());
        System.out.println("The largest size difference of a node is: " + tree.maxSizeDiff());
        System.out.println(tree.nodesWithOneChild() + " nodes have a single child.");
//        tree.display();
        @SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
        System.out.println("Enter a name for search, end in a '*' for surname search." + " q to quit");
        while (input.hasNext()) {
            String search = input.nextLine().trim();
            if (search.equals("q"))
                break;
            if (search.indexOf('*') > 0) {
            	// call surname search
            	search = search.substring(0, search.length()-1);
            	Student[] list = tree.searchSurname(search);
                if (list == null)
                    System.out.println("Not Found");
                else
                    System.out.println(list.length + " students with surname \"" + search + "\" found:\n" + Arrays.toString(list));
            } else {
                // call full name search
                Student student = tree.searchFullname(search);
                if (student == null)
                    System.out.println("Not Found");
                else
                    System.out.println(student);
            }
        }
    }
}
