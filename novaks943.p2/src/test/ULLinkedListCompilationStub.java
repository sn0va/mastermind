package test;

import java.util.Iterator;

import mastermind.ULLinkedList;

/**
 * This class is designed to help you test whether your ULLinkedList code will compile in my JUnit tests.
 * Be sure that you can compile this class, unmodified.  
 * If your code does not compile with this class, I will not be able to test your code.
 * If your code does compile with this class, odds are good it will compile in my JUnit tests.
 * Your code compiling with this class is not a sufficient level of testing.
 * @author Joe Meehean
 *
 */
public class ULLinkedListCompilationStub {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ULLinkedList<String> ll = new ULLinkedList<>();
		ULLinkedList<String> clone = ll.clone();
		
		ll.addFirst("hello");
		ll.addLast("goodbye");
		
		String item = ll.getFirst();
		item = ll.getLast();
		
		Iterator<String> iter = ll.iterator();
		boolean bool = iter.hasNext();
		item = iter.next();
		iter.remove();
		
		ll.addFirst(ll.removeFirst());
		ll.addLast(ll.removeLast());
		int integer = ll.size();
		ll.clear();		
	}

}
