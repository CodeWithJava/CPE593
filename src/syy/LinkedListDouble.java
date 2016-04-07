/**
CopyRight:
Project:
Module ID:
Comment:
Course: CPE 593 Applied Data Structures and Algorithms
Title: Implement ArrayList & HashMap
JDK Version: 1.8.0_71
Author: Ying Cui
Create Date: March 30th 2016
Finish Date:  2016
Description: Implement LinkedList in Double Type
*/


package syy;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class LinkedListDouble implements Iterator<Double>
{
	private int theSize;
	private Node head;
	private Node tail;
	
	private static class Node
	{
		public double val;
		public Node prev;
		public Node next;
		
		public Node(double val, Node prev, Node next)
		{
			this.val = val;
			this.prev = prev;
			this.next = next;
		}
	}
	
	//Constructor
	public LinkedListDouble()
	{
		doClear();
	}
	
	private void doClear()
	{
		head = new Node(0, null, null);
		tail = new Node(0, head, null);
		head.next = tail;
		this.theSize = 0;
	}
	
	public int size()
	{
		return this.theSize;
	}
	
	public boolean isEmpty()
	{
		return head == null;
	}
	
	public Iterator<Double> iterator()
    {
    	Iterator<Double> iterator = new Iterator<Double>()
    	{
    		private Node currentNode = head;
    		@Override
    		public boolean hasNext()
    		{
    			return currentNode.next == null;
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
	public boolean addBefore(Node p, Double e)
	{
		Node node = new Node(e, p.prev, p);
		node.prev.next = node;
		p.prev = node;
		this.theSize++;
		return true;
	}
	
	public boolean addLater(Node p, Double e)
	{
		Node node = new Node(e, p, p.next);
		node.next.prev = node;
		p.next = node;
		this.theSize++;
		return true;
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
	
//	public void insertAfter(int pos, double e)
//	{
//		Node temp = findNode(pos);
//		if (temp == null)
//		{
//			System.out.println("Index out of range.");
//			throw new IndexOutOfBoundsException();
//		}
//		else
//		{
//			Node node = new Node(e, temp, temp.next);
//			temp.next.prev = node;
//			temp.next = node;
//			this.theSize++;
//		}
//	}
//	
//	public void insertBefore(int pos, double e)
//	{
//		insertAfter(pos - 1, e);
//	}
//	
//	public double remove(int pos)
//	{
//		Node temp = findNode(pos);
//		if (temp == null)
//		{
//			System.out.println("Index out of range.");
//			throw new IndexOutOfBoundsException();
//		}
//		temp.prev.next = temp.next;
//		temp.next.prev = temp.prev;
//		this.theSize--;
//		return temp.val;
//	}
//	
	public syy.List<Double> subList(int formIndex, int toIndex)
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
