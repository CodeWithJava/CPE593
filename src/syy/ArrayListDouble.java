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

public class ArrayListDouble implements InterfaceList, Iterator<Double>
{
    private int capacity;
    private int size;
    private double[] array;
    
    ArrayListDouble()
    {
    	this(10);
    }
    
    public ArrayListDouble(int capacity)
    {
    	if (capacity < 0)
    		throw new IllegalArgumentException("Illegal Capacity: " + capacity);
    	this.capacity = capacity;
    	this.size = 0;
    	this.array = new double[this.capacity];
    }
    
    private void expand()
    {
    	if (this.size >= this.capacity - 1)
			this.array = new double[2 * this.capacity];
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
    
    @Override
    public Iterator<Double> iterator()
    {
    	Iterator<Double> iterator = new Iterator<Double>()
    	{
    		private int currentIndex = 0;
    		@Override
    		public boolean hasNext()
    		{
    			return currentIndex < size;
    		}
    		@Override
    		public Double next()
    		{
    			if (!hasNext())
    				throw new java.util.NoSuchElementException();
    			return array[currentIndex++];
    		}
    		@Override
    		public void remove()
    		{
    			ArrayListDouble.this.remove(--currentIndex);
    		}
    	};
    	return iterator;
    }
    
    public double[] toArray()
    {
    	double[] result = new double[size + 1];
    	for (int i = 0; i < result.length; i++)
    		result[i] = array[i];
    	return result;
    }
    

    // Modification Operations
    public boolean add(Object e)
    {
    	if (e instanceof Double)
    	{
    		double[] temp = this.array;
    		expand();
    		for (int i = 0; i <= this.size; i++)
    			this.array[i] = temp[i];
    		this.array[this.size + 1] = (double) e;
    		this.size++;
    		return true;
    	}
    	else
    		return false;
    }
    
    public boolean remove(Object e)
    {
    	if (e instanceof Double)
    	{
    		double[] temp = this.array;
    		this.array = new double[this.capacity]; // Maybe it can be deleted.
    		int indexOfe = 0;
    		for (int i = 0; i <= this.size; i++)
    		{
    			if (temp[i] == (double)e)
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
    	this.array = new double[10];
    	this.size = 0;
    	this.capacity = 10;
    } 
    
    public int trimToSize()
    {
    	this.capacity = this.size;
    	return this.capacity;
    }
    
    // Positional Access Operations
    public double get(int index)
    {
    	return this.array[index];
    }
    
    public void set(int index, double e)
    {
    	this.array[index] = e;
    }
    
    public void add(int index, double e)
    {
    	if (index > this.size)
    	{
    		System.out.println("Index out of range.");
			throw new IndexOutOfBoundsException();
    	}
    	double[] temp = this.array;
		expand();
		for (int i = 0; i <= this.size + 1; i++)
		{
			if (i < index)
				this.array[i] = temp[i];
			else if (i == index)
				this.array[i] = e;
			else if (i > index)
				this.array[i - 1] = temp[i];	
		}
		this.size++;
    }
    
    public double remove(int index)
    {
    	if (index > this.size)
    	{
    		System.out.println("Index out of range.");
			throw new IndexOutOfBoundsException();
    	}
    	double[] temp = this.array;
    	this.array = new double[this.capacity]; // deleted??
    	double elementOfIndex;
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

    public int indexOf(double e)
    {
    	for (int i = 0; i <= this.size; i++)
    		if (this.array[i] == e)
    			return i;
    		else
    			if (i == this.size)
    				return -1;
    }
    
    public ArrayListDouble subList(int formIndex, int toIndex)
    {
    	if (formIndex > this.size || toIndex > this.size)
    		throw new IndexOutOfBoundsException("Index out of range.");
    	if (formIndex > toIndex)
    		throw new IllegalArgumentException("Indices are illegal.");
    	ArrayListDouble subList = new ArrayListDouble(toIndex - formIndex);
    	for (int i = 0; i < subList.size; i++)
    		subList.array[i] = this.array[formIndex + i];
    	return subList;
    }
}
    
    
    
    
    
    
    
    
    
    
}