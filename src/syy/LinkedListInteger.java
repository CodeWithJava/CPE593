package syy;

public class LinkedListInteger {
	int size;
	node head;
	node tail;
	
	//constructor
	LinkedListInteger()
	{
		this.head = null;
		this.tail = null;
	}
	
	public int size()
	{
		return this.size;
	}
	
	public boolean isEmpty()
	{
		return this.size == 0;
	}
	
	//Modification Operation
	public boolean addFront(int e)
	{
		Node node = new Node(e);
		node.next = head;
		node.pre = null;
		if(head != null)
			head.pre =node;
		head =node;

		
		if(tail == null)
			tail = head;
		
		this.size++;
		return true;
	}
	
	public boolean addLast(int e)
	{
		Node node = new Node(e);
		Node.pre = tial;
		node.next = null;
		if(tail != null)
		{
			tail.next = node;
		}
		tail = node;
		if( head == null)
			head = tail;
		this.size++
		return true;
	}
	
	public void remove(int e)
	{
		for(Node p = head; p! = null; p = p.next)
		{
			if(p.val == e)
			{
				p.pre.next = p.next;
				p.next.pre = p.pre;
				p.next = null;
				p.pre = null;
				this.size--;
				break;
			}
			else
			{
				System.out.println("No such element in list");
			}	
		}
	}
	
	public void clear()
	{
		head = null;
		tail = null;
		head.next = null;
		tail.pre = null;
		this.size = 0;
		
	}
	
	//Postional Access Operations
	public int getValue(int pos)//postion - value
	{
		if(pos > size -1)
			return -1;
		else
		{
			Node p = head;
			for(int i =0 ; i < pos; i++)
			p = p.next;
			return p.val;
		}
		
	}
	
	public void insertAfter(int pos, int e)
	{
		if(pos < 0 || pos > this.size)
			System.out.println("Out of range");
		else if(pos == this.size -1)
			addLast(int e);
		else
		{
			Node node = head;
			Node newnode = new Node(e);
			for(int i = 0; i < pos;i++)
				node = node.next;
			newnode.next = node.next;
			node.next.pre = newnode;
			node.next = newnode;
			newnode.pre = node;
			this.size++;
		}
		
	}
	
	public void insertBefore(int pos,int e)
	{
		if(pos < 0 || pos > this.size)
			System.out.println("out of range");
		else if(pos == 0)
			addFront(int e);
		else
		{
			Node p = head;
			Node temp = new Node(int e);
			for(int i = 0; i < pos; i++)
				p = p.next;
			temp.next = p;
			temp.pre = p.pre;
			temp.pre.next = temp;
			temp.next.pre = temp;
			this.size++;
		}
	}
	
	public Node findNode(int pos)
	{
		Node p = head;
		for(int i = 0; i < pos;i++)
		{
			p = p.next;
		}
		return p;
	}
	
	public syy.List<Integer> sublist(int formIndex, int toIndex)
	{
		if (formIndex > this.capacity || toIndex > this.capacity)
    		throw new IndexOutOfBoundsException("Index out of range.");
    	if (formIndex > toIndex)
    		throw new IllegalArgumentException("Indices are illegal.");
    	Node former = findNode(formIndex);
    	Node to = findNode(toIndex);
    	LinkedListInteger subList = new LinkedListInteger(toIndex - formIndex);
    	Node p = former;
    	Node q = subList.head;
    	while(p != to)
    	{
    		p = q;
    		p = p.next;
    		q = q.next;
    	}
    	return subList;
	}
	
	public ListIterator<Integer> listIterator(int index)
	{
		checkPositionIndex(index);
		return new ListItr(index);
	}
	private class ListItr implements ListIterator<Integer>
	{
		private node lastReturned;
		private node next;
		private int nextIndex;
		private int expectedModCount = modCount;
		
		ListItr(int index)
		{
			next = (index == size) ? null : node(index);
			nextIndex = index;
		}
		
		public boolean hasNext()
		{
			return nextIndex < size;
		}
		
		public int next()
		{
			checkForComodification();
			if(!hasNext())
				throw new NoSuchElementException();
			
			lastReturned = next;
			next = next.next;
			nextIndex++;
			return lastReturned.val;
		}
		
		public boolean hasPrevious()
		{
			return nextIndex > 0;
		}
		
		public int previous()
		{
			checkForComodification();
			if(!hasPrevious())
				throw new NoSuchElementException();
			
			lastReturned = next = (next == null) ? last : next.pre;
			nextIndex--;
			return lastReturned.val;
		}
		
		public int nextIndex()
		{
			return nextIndex;
		}
		
		pubic int previousIndex()
		{
			return nextIndex--;
		}
		
		public void remove()
		{
			checkForComodification();
			if(lastReturned == null)
				throw new IllegalStateException();
			node lastNext = lastReturned.next;
			unlink(lastReturned);//have to write unlink;
			if(next == lastReturned)
				next = lastNext;
			else
				nextIndex--;
			lastReturned = null;
			expectedModCount++;
		}
		
		final void checkForComodification()
		{
			if(modCount != expectedModCount)
				throw new ConcurrentModificationException();
		}
	}
	
	void unlink(node x)
	{
		final element = x.val;
		final node next = x.next;
		final node pre = x.pre;
		
		if(pre == null)
		{
			head = next;
		}
		else 
		{
			pre.next = next;
			x.pre = null;
		}
		
		if(next = null)
		{
			tail = pre;
		}
		else
		{
			next.pre = pre;
			x.next = null;
		}
		
		x.val = 0;
		size--;
			
	}
	
	
}
class node
{
	int val;
	node next;
	node pre;
	
	node(int val)
	{
		this.val = val;
		this.next = null;
		this.pre = null;
	}
	
}