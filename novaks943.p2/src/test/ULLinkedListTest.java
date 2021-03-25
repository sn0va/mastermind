package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mastermind.ULLinkedList;

class ULLinkedListTest {

	@Test
	void test() {
		ULLinkedList<String> testList = new ULLinkedList<>();
		
		//addFirst and getFirst
		testList.addFirst("Hello");
		
		assertEquals(testList.getFirst(), "Hello");
		
		System.out.println("List after addFirst Hello: " + testList.toString());
		
		testList.addFirst("Goodbye");
		testList.addFirst("Hi");
		
		assertEquals(testList.getFirst(), "Hi");
		
		System.out.println("List after addFirst Goodbye and Hi: " + testList.toString());
		System.out.println("Items in list: " + testList.size());
		
		//removeFirst
		String removedItem = testList.removeFirst();
		
		assertEquals(testList.getFirst(), "Goodbye");
		assertEquals(removedItem, "Hi");
		
		System.out.println("List after removeFirst: " + testList.toString());
		System.out.println("Items in list: " + testList.size());
		
		//clear
		testList.clear();
		
		assertEquals(testList.size(), 0);
		
		System.out.println("List after clear: " + testList.toString());
		System.out.println("Items in list: " + testList.size());
		
		//addLast and getLast
		testList.addLast("Hello");
		
		assertEquals(testList.getLast(), "Hello");
		
		testList.addLast("how");
		testList.addLast("are");
		testList.addLast("you?");
		
		assertEquals(testList.getFirst(), "Hello");
		assertEquals(testList.getLast(), "you?");
		
		System.out.println("List after addLast hello how are you: " + testList.toString());
		System.out.println("Items in list: " + testList.size());
		
		//removeLast
		removedItem = testList.removeLast();
		
		assertEquals(testList.getLast(), "are");
		assertEquals(removedItem, "you?");
		
		System.out.println("List after removeLast: " + testList.toString());
		System.out.println("Items in list: " + testList.size());
		
		//clone
		ULLinkedList<String> clone = testList.clone();
		
		assertEquals(clone.getFirst(), "Hello");
		assertEquals(clone.getLast(), "are");
		
		System.out.println("Clone list content: " + clone.toString());
		System.out.println("Clone list size: " + clone.size());
		
		//equals
		assertTrue(testList.equals(clone));
		
		clone.removeLast();
		clone.addLast("is");
		
		assertEquals(clone.size(), testList.size());
		assertFalse(clone.equals(testList));
		
		//Iterator
		java.util.Iterator<String> iter = testList.iterator();
		
		assertTrue(iter.hasNext());
		assertEquals(iter.next(), "Hello");
		assertEquals(iter.next(), "how");
		
		iter.remove();
		
		assertEquals(iter.next(), "are");
		assertFalse(iter.hasNext());
		
		System.out.println("List after iterator removed how: " + testList.toString());
		System.out.println("Items in list: " + testList.size());
		
		iter.remove();
		
		System.out.println("List after iterator removed are: " + testList.toString());
		System.out.println("Items in list: " + testList.size());
	}
}
