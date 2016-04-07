/**
CopyRight:
Project:Java Replacement Data Structure
Module ID:
Comment:
Course: CPE 593 Applied Data Structures and Algorithms
Title: Implement ArrayList & HashMap
JDK Version: 1.8.0_77
Group Member: Songnian Yin, Yabin Han, Ying Cui
Author: Songnian Yin
Create Date: March 30th 2016
Finish Date: 
Description: Implement LinkedList in Double Type
*/

package syy;

import java.util.Iterator;
import java.util.List;

public class LinkedListDouble implements Iterator<Double>
{
	//ListNode
	private static class Node
	{
		public double val;
		public Node prev;
		public Node next;
		
		public Node(double val)
		{
			this.val = val;
			this.prev = null;
			this.next = null;
		}
	}

	private size;
	private Node head;
	private Node tail;
	
	//Constructor
	public LinkedListDouble()
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
	
	public Iterator<Double> iterator()
	{
		Iterator<Double> iterator = new Iterator<>()
		{
			private Node currentNode = head;
			@Override
			public boolean hasNext()
			{
				return currentNode.next != null;
			}
			@Override
			public Double next()
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
	
	public double[] toArray()
	{
		double[] result = new double[this.theSize];
		Node p = head;
		for (int i = 0; i < this.theSize; i++)
		{
			result[i] = p.val;
			p = p.next;
		}
		return result;			
	}
	
	// Modification Operations
	public boolean add(double val)
	{
		Node node = new Node(val);
		tail.next = node;
		node.prev = tail;
		node.next = null;
		tail = node;
		this.size++;
		return true;
	}
	
	public void add(int index,double val)
	{
		Node node = new Node(val);
		if(index < 0 || index > this.size)
			return false;
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
	
	public void addFirst(double val)
	{
		Node node = new Node(val);
		node.next = head;
		node.prev = null;
		head = node;
	}

	public void addLast(double val)
	{
		Node node = new Node(val);
		node.prev = tail;
		tail.next = node;
		node.next = null;
		tail = node;
	}
	// Remove the first node with value e
	public boolean remove(Double e)
	{
		Node p;
		for (p = head; p.next != null; p = p.next)
			if (p.val == e)
			{
				p.next.prev = p.prev;
				p.prev.next = p.next;
				theSize--;
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
	public double getVal(int pos)
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

	public double getFirst();
	{
		return head.val;
	}

	public double getLast();
	{
		return tail.val;
	}
	
	public List<Double> subList(int formIndex, int toIndex)
	{
		if (formIndex > this.theSize || toIndex > this.theSize)
			throw new IndexOutOfBoundsException("Index out of range.");
		if (formIndex > toIndex)
			throw new IllegalArgumentException("Indices are illegal.");
		Node former = findNode(formIndex);
		Node to = findNode(toIndex);
		LinkedListDouble subList = new LinkedListDouble();
		subList.theSize = toIndex - formIndex;
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
}
