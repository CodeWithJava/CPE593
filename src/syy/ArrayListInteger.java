package syy;
import java.util.Iterator;
import java.util.List;

public class ArrayListInteger implements InterfaceList, Iterator<Integer>
{
	private int capacity;//the hole size of array;
	private int size;//the use of capacity;
	private int [] array;// array table;
	
	ArrayListInteger()
	{
		this(10);
	}
	
	public ArrayListInteger(int capacity)
	{
		if(capcaity < 0)
			throw new IllegalArgumentException("Illegal Capcity:" + capacity);
		this.capacity = capacity;
		this.size = 0;
		this.array = new int [this.capacity];
	}
	
	private void expand()
	{
		if(this.size >= this.capacity-1)
			this.array = new int[2*this.capacity];
	}
	
	public int size()
	{
		if(this.size >Integer.MAX_VALUE - 1)
			return Integer.MAX_VALUE;
		else
			return this.size;
	}
	
	
	public boolean isEmpty()
	{
		return this.size == 0;
	}
	
	public Iterator<Interger> iterator()
	{
		Iterator<Interger> iterator = new Iterator<Interger>()
		{
			private int currentIndex = 0;
			public boolean hasNext()
			{
				return currentIndex < size;
			}
			
			public int next()
			{
				if(!hasNext())
					throw new java.util.NoSuchElementException();
				return array[currentIndex++];
			}
			
			public void remove()
			{
				ArrayListInteger.this.remove(--currentIndex);
			}
		};
		return iterator;
	}
	
	public int[] toArray()
	{
		int [] result = new int[size + 1];
		for(int i = 0; i < result.length ; i++)
			result[i] = array[i];
		return result;
	}
	
	//Modification Operations
	public boolean add(Object e)
	{
		if(e instanceof Integer)
		{
			int[]temp = this.array;
			expand();
			for(int i = 0; i<= this.size; i++)
				this.array[i] = temp[i];
			this.array[this.size + 1] = (Interger)e;
			this.size++;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean remove(Object e)
	{
		if(e instanceof Interger)
		{
			int [] temp = this.array;
			int indexOf = 0;
			for(int i = 0; i <= this.size; i++)
			{
				if(temp[i] == (Integer)e)
				{
					indexOf = i;
					break;
				}
			}
			for(int i = 0;i <= this.size; i++)
			{
				if(i < indexOf)
					this.array[i] = temp[i];
				else if(i > indexof)
					this.array[i] = temp[i-1];
			}
			this.size--;
			return true;
		}
		else
			return false;
	}
	
	public void clear()
	{
		this.array = new int[16];
		this.size = 0;
		this.capacity = 0;
	}
	
	public int trimToSize()
	{
		this.capacity = this.size;
		return this.capacity;
	}
	
	//Positional Access Operations
	public int get(int index)
	{
		return this.array[index];
	}
	
	public void set(int index,int val)
	{
		this.array[index] = val;
	}
	
	public void add(int index, int val)
	{
		int[] temp = this.array;
		expand();
		for(int i = 0; i <= this.size +1 ;i++)
		{
			if(i < index)
				this.array[i] = temp[i];
			else if(i == index)
				this.array[i] = val;
			else if(i > index)
				this.array[i] = temp[i-1];
		}
		this.size++;
	}
	
	public int remove(int index)
	{
		int[] temp = this.array;
		int elementOfIndex;
		for(int i = 0; i <= this.size ;i++)
		{
			if(i < index)
				this.array[i] = temp[i];
			else if(i == index)
				elementOfIndex = this.array[i] 
			else if(i > index)
				this.array[i] = temp[i +1];
		}
		return elementOfIndex;
	}
	
	public int indexOf(int val)
	{
		   	for (int i = 0; i <= this.size; i++)
    		if (this.array[i] == e)
    			return i;
    		else
    			if (i == this.size)
    				return -1;
	}
	/*
	*Return the index of the last occurrentce of the specified element;
	*/
	public int lastIndexOf(Object o)
	{
		if(o instanceof Integer)
		{
			for(int i = size -1 ; i >= 0 ; i--)
				if(o == array[i])
					return i;
				else
					System.out.prinltn("No such element");
		}
		else
		{
			System.out.println("the element is not a integer");
		}
	}
	
	public syy.List<Interger>subList(int formIndex,int toIndex)
	{
		if(formIndex > this.size || toIndex > this.size )
			throw new IndexOutofBoundsException("Index out of range.");
		if(formIndex > toIndex)
			throw new IllegalArgumentException("Indices are illegal.");
		int [] subList = new int[toIndex - formIndex];
		for(int i = 0; i < subList.length; i++)
			subList[i] = this.array[formIndex + i];
		return subList;
	}
}