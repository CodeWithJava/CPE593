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

public class LinkedListDouble implements Iterator<Double>{
	private static class Node
	{
		private double val;
		Node prev;
		Node next;
		Node(double val)
		{
			this.val = val;
		}
	}
	
	private int capacity;
	private Node head;
	private Node tail;
	
	//Constructor
	LinkedListDouble()
	{
		this(10);
	}
	
	public LinkedListDouble(int capacity)
	{
		this.capacity = capacity;
		head = null;
		tail = null;
	}
	
	public int size()
	{
		return this.capacity;
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
		double[] result = new double[this.capacity];
		Node p = head;
		for (int i = 0; i < this.capacity; i++)
		{
			result[i] = p.val;
			p = p.next;
		}
		return result;			
	}
	
	// Modification Operations
	public boolean addFront(Double e)
	{
		Node node = new Node(e);
		node.next = head;
		node.prev = null;
		if (head != null)
			head.prev = node;
		head = node;
		if (tail == null)
			tail = head;
		this.capacity++;
		return true;
	}
	
	public boolean addLast(Double e)
	{
		Node node = new Node(e);
		node.prev = tail;
		node.next = null;
		if (tail != null)
			tail.next = node;
		tail = node;
		if (head == null)
			head = tail;
		this.capacity++;
		return true;
	}
	
	public boolean remove(Double e)
	{
		for(Node p = head; p != tail; p = p.next)
		{
			if (p.val == e)
			{
				p.prev.next = p.next;
				p.next.prev = p.prev;
				this.capacity--;
				break;
			}
			else
			{
				System.out.println("No such element in list.");
				return false;
			}
		}
		return true;
	}
	
	// Bulk Modification Operations
	public void clear()
	{
		head = null;
		head.next = null;
		tail = null;
		tail.prev = null;
		this.capacity = 0;
	}
	
	// Positional Access Operations
	public Node findNode(int pos)
	{
		Node temp = head;
		for (int i = 0; i < pos; i++)
			temp = temp.next;
		return temp;
	}
	
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
	
	public void insertAfter(int pos, double e)
	{
		Node temp = findNode(pos);
		if (temp == null)
		{
			System.out.println("Index out of range.");
			throw new IndexOutOfBoundsException();
		}
		else
		{
			Node node = new Node(e);
			temp.next.prev = node;
			node.next = temp.next;
			temp.next = node;
			node.prev = temp;
			this.capacity++;
		}
	}
	
	public void insertBefore(int pos, double e)
	{
		insertAfter(pos - 1, e);
	}
	
	public double remove(int pos)
	{
		Node temp = findNode(pos);
		if (temp == null)
		{
			System.out.println("Index out of range.");
			throw new IndexOutOfBoundsException();
		}
		temp.prev.next = temp.next;
		temp.next.prev = temp.prev;
		this.capacity--;
		return temp.val;
	}
	
	public syy.List<Double> subList(int formIndex, int toIndex)
    {
    	if (formIndex > this.capacity || toIndex > this.capacity)
    		throw new IndexOutOfBoundsException("Index out of range.");
    	if (formIndex > toIndex)
    		throw new IllegalArgumentException("Indices are illegal.");
    	Node former = findNode(formIndex);
    	Node to = findNode(toIndex);
    	LinkedListDouble subList = new LinkedListDouble(toIndex - formIndex);
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
}
