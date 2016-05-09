/**
CopyRight:
Project:Java Replacement Data Structure
Module ID:
Comment:
Course: CPE 593 Applied Data Structures and Algorithms
Title: Implement ArrayList & HashMap
JDK Version: 1.8.0_77
Group Member: Songnian Yin, Yabin Han, Ying Cui
Author: Ying Cui
Create Date: March 30th 2016
Finish Date: 
Description: Implement LinkedList in Double Type
*/

package syy;

import java.util.Iterator;

public class LinkedListInteger implements Iterator<Integer>
{
	// ListNode
	private static class Node
	{
		public int val;
		public Node prev;
		public Node next;
		
		public Node(int val)
		{
			this.val = val;
			this.prev = null;
			this.next = null;
		}
	}

	int size;
	Node head;
	private Node tail;
	
	// Constructor
	public LinkedListInteger()
	{
		clear();
	}
	
	public int size()
	{
		return this.size;
	}
	
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	public Iterator<Integer> iterator()
	{
		Iterator<Integer> iterator = new Iterator<Integer>()
		{
			private Node currentNode = head;
			@Override
			public boolean hasNext()
			{
				return currentNode.next != null;
			}
			@Override
			public Integer next()
			{
				if (!hasNext())
					throw new java.util.NoSuchElementException();
				return currentNode.next.val;
			}
			@Override
			public void remove()
			{
				if (head == null)
					System.out.println("It's an empty list.");
				else
				{
					Node temp = currentNode;
					temp.prev.next = temp.next;
					temp.next.prev = temp.prev;
					currentNode = temp.prev;
				}
			}
		};
		return iterator;
	}
	
	public int[] toArray()
	{
		int[] result = new int[this.size];
		Node p = head;
		for (int i = 0; i < this.size; i++)
		{
			result[i] = p.val;
			p = p.next;
		}
		return result;			
	}
	
	// Modification Operations
	public boolean add(int val)
	{
		Node node = new Node(val);
		tail.next = node;
		node.prev = tail;
		node.next = null;
		tail = node;
		this.size++;
		return true;
	}
	
	public void add(int index,int val)
	{
		Node node = new Node(val);
		if(index < 0 || index > this.size)
			System.out.println("Index out of range.");
		Node p = head;
		for(int i = 0;i < index;i++)
			p = p.next;
		Node temp = p.next;
		p.next = node;
		node.prev = p;
		node.next = temp;
		temp.prev = node;
		this.size++;
	}
	
	public void addFirst(int val)
	{
		Node node = new Node(val);
		node.next = head;
		node.prev = null;
		head = node;
	}

	public void addLast(int val)
	{
		Node node = new Node(val);
		if (head == null)
		{
			node.prev = null;
			node.next = tail;
			head = node;
		}
		else
		{
			node.prev = tail;
			tail.next = node;
			node.next = null;
			tail = node;
		}
	}
	
	// Remove the first node with value e
	public boolean remove(Integer e)
	{
		Node p;
		for (p = head; p.next != null; p = p.next)
			if (p.val == e)
			{
				p.next.prev = p.prev;
				p.prev.next = p.next;
				this.size--;
			}
			else
			{
				System.out.println("No such element found.");
				return false;
		}
		return true;
	}
	
	// Bulk Modification Operations
	public void clear()
	{
		doClear();
	}

	private void doClear()
	{
		head = null;
		tail = null;
		this.size = 0;
	}

	// Positional Access Operations
	public int getVal(int pos)
	{
		if (head == null)
		{
			System.out.println("Index out of range.");
			throw new IndexOutOfBoundsException();
		}
		Node temp = findNode(pos);
		if (temp == null)
		{
			System.out.println("Index out of range.");
			throw new IndexOutOfBoundsException();
		}
		return temp.val;
	}

	public int getFirst()
	{
		return head.val;
	}

	public int getLast()
	{
		return tail.val;
	}
	
	public LinkedListInteger subList(int formIndex, int toIndex)
	{
		if (formIndex > this.size || toIndex > this.size)
			throw new IndexOutOfBoundsException("Index out of range.");
		if (formIndex > toIndex)
			throw new IllegalArgumentException("Indices are illegal.");
		Node former = findNode(formIndex);
		Node to = findNode(toIndex);
		LinkedListInteger subList = new LinkedListInteger();
		subList.size = toIndex - formIndex;
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
	
	public Node findNode(int pos)
	{
		Node temp = head;
		for (int i = 0; i < pos; i++)
			temp = temp.next;
		return temp;
	}

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public Integer next() {
		return null;
	}	
}
