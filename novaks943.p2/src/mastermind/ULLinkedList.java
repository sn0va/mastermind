package mastermind;

public class ULLinkedList<E> implements java.lang.Cloneable, java.lang.Iterable<E>{
	private class Node
	{
		private Node next;
		private E value;
		private Node prev;
		
		Node()
		{
			this.prev = null;
			this.value = null;
			this.next = null;
		}
		
		Node(Node prev, E value, Node next)
		{
			this.prev = prev;
			this.value = value;
			this.next = next;
		}
	}
	
	public class ULIterator implements java.util.Iterator<E>
	{
		private Node current;
		private Node prevReturned;
		
		public ULIterator()
		{
			current = head.next;
			prevReturned = null;
		}
		
		public E next() throws java.util.NoSuchElementException
		{
			if( !hasNext() )
				throw new java.util.NoSuchElementException("No next element");
			
			E retVal = current.value;
			prevReturned = current;
			current = current.next;
			return retVal;
		}
		
		public boolean hasNext()
		{
			return current.next != null;
		}
		
		public E prev() throws java.util.NoSuchElementException
		{
			if( !hasPrev() )
				throw new java.util.NoSuchElementException("No previous element");
			
			current = current.prev;
			prevReturned = current;
			return current.value;
		}
		
		public boolean hasPrev()
		{
			return current.prev.prev != null;
		}
		
		public void remove() throws java.lang.IllegalStateException
		{
			if (prevReturned == null)
				throw new java.lang.IllegalStateException("No previously returned element to remove");
			
			current = prevReturned.next;
			prevReturned.prev.next = prevReturned.next;
			prevReturned.next.prev = prevReturned.prev;
			--size;			
		}
	}
	
	private Node head;
	private Node tail;
	private int size;
	
	public ULLinkedList()
	{
		Node header = new Node();
		Node trailer = new Node();
		
		header.next = trailer;
		trailer.prev = header;
		
		head = header;
		tail = trailer;
		size = 0;
	}
	
	public void addFirst(E item)
	{
		Node newNode = new Node(head, item, head.next);
		
		head.next.prev = newNode;
		head.next = newNode;
		
		++size;
	}
	
	public void addLast(E item)
	{
		Node newNode = new Node(tail.prev, item, tail);
		
		tail.prev.next = newNode;
		tail.prev = newNode;
		
		++size;
	}
	
	public E getFirst() throws java.util.NoSuchElementException
	{
		if(size <= 0)
			throw new java.util.NoSuchElementException("ULLinkedList is empty, cannot get first element");
		
		return head.next.value;
	}
	
	public E getLast() throws java.util.NoSuchElementException
	{
		if(size <= 0)
			throw new java.util.NoSuchElementException("ULLinkedList is empty, cannot get last element");
		
		return tail.prev.value;
	}
	
	public E removeFirst() throws java.util.NoSuchElementException
	{
		if(size <= 0)
			throw new java.util.NoSuchElementException("ULLinkedList is empty, cannot remove first element");
		
		E returnedItem = head.next.value;
		
		head.next.next.prev = head;
		head.next = head.next.next;
		
		--size;
		
		return returnedItem;
	}
	
	public E removeLast() throws java.util.NoSuchElementException
	{
		if(size <= 0)
			throw new java.util.NoSuchElementException("ULLinkedList is empty, cannot remove last element");
		
		E returnedItem = tail.prev.value;
		
		tail.prev.prev.next = tail;
		tail.prev = tail.prev.prev;
		
		--size;
		
		return returnedItem;
	}
	
	public int size() { return size; }
	
	public void clear()
	{
		head.next = tail;
		tail.prev = head;
		
		size = 0;
	}
	
	public java.util.Iterator<E> iterator()
	{
		return new ULIterator();
	}
	
	public ULLinkedList<E> clone()
	{
		ULLinkedList<E> clone = new ULLinkedList<E>();
		
		for (E value : this)
		{
			clone.addLast(value);
		}
		
		return clone;
	}
	
	public boolean equals(java.lang.Object otherObject)
	{
		boolean same = this == otherObject;
		
		boolean areEqual = true;                                       
		
		// Check to make sure they are the same type and safe for casting
		if( !same && getClass() == otherObject.getClass() )
		{
			@SuppressWarnings("unchecked")
			ULLinkedList<E> other = (ULLinkedList<E>) otherObject;
			
			if ( this.size == other.size() )
			{
				Node thisCursor = this.head;
				Node otherCursor = other.head;
				
				for (int i = 0; i < this.size; ++i)
				{
					thisCursor = thisCursor.next;
					otherCursor = otherCursor.next;
					
					if (!thisCursor.value.equals(otherCursor.value))
						areEqual = false;
				}
			}
			
			else
				areEqual = false;
		}
		
		return areEqual || same;
	}
	
	public java.lang.String toString()
	{
		java.lang.StringBuilder strBuilder = new java.lang.StringBuilder();
		
		strBuilder.append("<H>");
		
		for ( E value : this )
		{
			strBuilder.append( "--<" + value + ">");
		}
		
		strBuilder.append("--<T>");
		
		return strBuilder.toString();
	}
	
	
}
