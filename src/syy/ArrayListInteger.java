/**
CopyRight:
Project: Java Replacement Data Structure
Module ID:
Comment:
Course: CPE 593 Applied Data Structures and Algorithms
Title: Implement ArrayList & HashMap
JDK Version: 1.8.0_77
Group Member: Songnian Yin, Yabin Han, Ying Cui
Author: Ying Cui
Create Date: March 17th 2016
Finish Date: 
Description: Implement ArrayList in Double Type
*/
package syy;
import java.util.Iterator;
import java.util.List;

public class ArrayListInteger implements Iterator<Integer>
{
    private int capacity;
    private int size;
    private int[] array;
    
    ArrayListInteger()
    {
    	this(10);
    } 
    
    public ArrayListInteger(int capacity)
    {
    	if (capacity < 0)
    		throw new IllegalArgumentException("Illegal Capacity: " + capacity);
    	this.capacity = capacity;
    	this.size = 0;
    	this.array = new int[this.capacity];
    }
    
    private void expand()
    {
    	if (this.size >= this.capacity - 1)
			this.array = new int[2 * this.capacity];
    }
    
    public int size()
    {
    	if (this.size > Integer.MAX_VALUE - 1)
    		return Integer.MAX_VALUE;
    	else
    		return this.size;
    }
    
    public boolean isEmpty()
    {
    	return this.size == 0;
    }
    
    public Iterator<Integer> iterator()
    {
    	Iterator<Integer> iterator = new Iterator<Integer>()
    	{
    		private int currentIndex = 0;
    		@Override
    		public boolean hasNext()
    		{
    			return currentIndex < size;
    		}
    		@Override
    		public Integer next()
    		{
    			if (!hasNext())
    				throw new java.util.NoSuchElementException();
    			return array[currentIndex++];
    		}
    		@Override
    		public void remove()
    		{
    			ArrayListInteger.this.remove(--currentIndex);
    		}
    	};
    	return iterator;
    }
    
    public int[] toArray()
    {
    	int[] result = new int[size + 1];
    	for (int i = 0; i < result.length; i++)
    		result[i] = array[i];
    	return result;
    }
    

    // Modification Operations
	public void append(int value)
	{
		add2(size,value);
	}

	// add element after specific index
	private void add2(int index,int value)
	{
		if(index < 0 || index > size)
			System.out.println("Out of ArrayBound:" + index);
		if(size == capacity)
			expand();
		for(int i = size;i > index;i--)
			array[i] = array[i-1];
		array[index] = value;
		size++;
	}
    
    public boolean remove(Object e)
    {
    	if (e instanceof Double)
    	{
    		int[] temp = this.array;
    		this.array = new int[this.capacity]; // Maybe it can be deleted.
    		int indexOfe = 0;
    		for (int i = 0; i <= this.size; i++)
    		{
    			if (temp[i] == (int)e)
    			{
    				indexOfe = i;
    				break;
    			}
    		}
    		for (int i = 0; i <= this.size; i++)
    		{
    			if (i < indexOfe)
    				this.array[i] = temp[i];
    			else if (i > indexOfe)
    				this.array[i - 1] = temp[i];
    		}
    		this.size--;
    		return true;
    	}
    	else
    		return false;
    }
    
    // Bulk Modification Operations
    public void clear()
    {
    	this.array = new int[10];
    	this.size = 0;
    	this.capacity = 10;
    } 
    
    public int trimToSize()
    {
    	this.capacity = this.size;
    	return this.capacity;
    }
    
    // Positional Access Operations
    public int get(int index)
    {
    	return this.array[index];
    }
    
    public void set(int index, int e)
    {
    	this.array[index] = e;
    }
    
    public void add(int index, int e)
    {
    	if (index > this.size)
    	{
    		System.out.println("Index out of range.");
			throw new IndexOutOfBoundsException();
    	}
    	int[] temp = this.array;
		expand();
		for (int i = 0; i <= this.size + 1; i++)
		{
			if (i < index)
				this.array[i] = temp[i];
			else if (i == index)
				this.array[i] = e;
			else if (i > index)
				this.array[i-1] = temp[i];	
		}
		this.size++;
    }
    
    public int remove(int index)
    {
    	if (index > this.size)
    	{
    		System.out.println("Index out of range.");
			throw new IndexOutOfBoundsException();
    	}
    	int[] temp = this.array;
    	//this.array = new int[this.capacity]; // deleted??
    	int elementOfIndex = 0;
    	for (int i = 0; i <= this.size; i++)
    	{
    		if (i < index)
    			this.array[i] = temp[i];
    		else if (i == index)
    			elementOfIndex = temp[index];
    		else if (i > index)
    			this.array[i - 1] = temp[i];
    	}
    	return elementOfIndex;
    }

    public int indexOf(int e)
    {
    	for (int i = 0; i <= this.size; i++)
    	{
    		if (this.array[i] == e)
    			return i;
    	}
    	return -1;
    }
    
    public ArrayListInteger subList(int formIndex, int toIndex)
    {
    	if (formIndex > this.size || toIndex > this.size)
    		throw new IndexOutOfBoundsException("Index out of range.");
    	if (formIndex > toIndex)
    		throw new IllegalArgumentException("Indices are illegal.");
    	ArrayListInteger subList = new ArrayListInteger(toIndex - formIndex);
    	for (int i = 0; i < subList.size; i++)
    		subList.array[i] = this.array[formIndex + i];
    	return subList;
    }

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		return null;
	}
}