package myProj;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import myProj.MyTestClass.SingleLinkedNode;
import myProj.MyTestClass.SinglyLinkedList;

public class LinkedListTest {

	public static void main(String[] args) {
		MyTestClass mtc = new MyTestClass();
		MyTestClass.SinglyLinkedList<String> Sll = mtc.new SinglyLinkedList<>();
        String[] test = {"A", "B", "C", "D", "E"};
        Sll.addAll(test);
        MyTestClass.prettyPrint(Sll);
        Sll.setHead(Reverse(Sll.getHead()));
        MyTestClass.prettyPrint(Sll);

//        SingleLinkedNode<String> node = Sll.getHead();
//        SingleLinkedNode<String> save = null;
//        while (node.getNext() != null) {
//        	if (node.getValue().equals("C")) {
//        		save = node;
//        	}
//        	node = node.getNext();
//        }
//        // At end of list, make the loop.
//        node.setNext(save);
//        System.out.println("Detected loop starting at " + detectLoop(Sll).getValue());
//		MyTestClass.SinglyLinkedList<Character> Sll2 = mtc.new SinglyLinkedList<>();
//		String testS = "abcdefgh";
//        Sll2.addAll((testS.chars().mapToObj(c -> (char)c).toArray(Character[]::new)));
//
//        System.out.println("find half " + findHalf(Sll2).getValue());
	}

	private static SingleLinkedNode detectLoop(SinglyLinkedList sll) {
        SingleLinkedNode node = sll.getHead();
        Set<SingleLinkedNode> loopDetect = new HashSet<>();
        while (node != null) { 
        	if (!loopDetect.add(node)) {
        		return node;
        	}
        	node = node.getNext();
        }
		return null;
	}

	private static SingleLinkedNode findHalf(SinglyLinkedList sll) {
		SingleLinkedNode slow = sll.getHead();
		if (slow == null) {
			return null;
		}
		SingleLinkedNode fast = slow;
		while(fast != null && fast.getNext() != null) {
			fast =  fast.getNext().getNext();
			slow = slow.getNext();
		}
		return slow;
	}
	
	private static SingleLinkedNode findFromEnd(SinglyLinkedList sll, int k) {
		// find the kth element from the end of the list.
		SingleLinkedNode fast = sll.getHead();
		SingleLinkedNode slow = fast;
		int count = 1;
		while (fast != null && count < k) {
			fast = fast.getNext();
			count++;
		}
		if (fast == null) {
			return null;  // not enough elements.
		}
		if (fast.getNext() == null) {
			// remove 1st element.
			sll.setHead(slow.getNext());
			return slow;
		}
		// Now slow and fast are k elements apart.
		// look 2 ahead so we can remove the element
		while (fast.getNext().getNext() != null) {
			fast = fast.getNext();
			slow = slow.getNext();
		}
		fast = slow.getNext(); // temp pointer
		slow.setNext(slow.getNext().getNext());
		return fast;
		
	}
	static SingleLinkedNode Reverse(SingleLinkedNode head) {

	    if (head.getNext() == null) {
	        return head;
	    }
	    SingleLinkedNode n = Reverse(head.getNext());
	    head.getNext().setNext(head); 
	    head.setNext(null);
	    return n;
	}
}
